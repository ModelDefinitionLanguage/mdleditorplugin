/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.loader

import java.util.List;

import eu.ddmore.libpharmml.dom.commontypes.LevelReferenceType
import eu.ddmore.libpharmml.dom.commontypes.RealValueType
import eu.ddmore.libpharmml.dom.commontypes.SymbolRefType
import eu.ddmore.libpharmml.dom.dataset.DataSetType
import eu.ddmore.libpharmml.dom.maths.BinopType
import eu.ddmore.libpharmml.dom.trialdesign.BolusType
import eu.ddmore.libpharmml.dom.trialdesign.DosingTimesPointsType
import eu.ddmore.libpharmml.dom.trialdesign.DosingVariableType
import eu.ddmore.libpharmml.dom.trialdesign.SteadyStateType
import eu.ddmore.libpharmml.dom.trialdesign.TrialDesignType
import eu.ddmore.libpharmml.dom.trialdesign.InfusionType.DoseAmount

import eu.ddmore.pharmacometrics.model.data.DataSet
import eu.ddmore.pharmacometrics.model.trialdesign.Subject
import eu.ddmore.pharmacometrics.model.trialdesign.math.BinaryExpression
import eu.ddmore.pharmacometrics.model.trialdesign.math.Constant
import eu.ddmore.pharmacometrics.model.trialdesign.math.Expression
import eu.ddmore.pharmacometrics.model.trialdesign.math.Variable
import eu.ddmore.pharmacometrics.model.trialdesign.math.BinaryExpression.Operator
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Activity
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Arm
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Bolus
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Cell
import eu.ddmore.pharmacometrics.model.trialdesign.structure.DosingTimes
import eu.ddmore.pharmacometrics.model.trialdesign.structure.DosingTimesConstant
import eu.ddmore.pharmacometrics.model.trialdesign.structure.DosingTimesSequence
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Epoch
import eu.ddmore.pharmacometrics.model.trialdesign.structure.IndividualDosing
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Infusion
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Segment
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Structure
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Variability

import com.google.common.base.Preconditions;

/**
 * This class is responsible for loading in manin memory the TrialDesign element by using 
 * the data structures defined in pharmacometrics model.
 */
class TrialDesignLoader {

    private TrialDesignType trialDesign
    private Structure structure
    private Map<String,Object> idToDomainObject
    private static final String isAssignedToODESymbol = "derivativeVariable"
    private String individualColumnName
    private String armColumnName
    private String replicateColumnName
    private int subjectCounter=1;
    private int armCounter=1;
    

    public final void load() {
        structure = new Structure()
        idToDomainObject = [:]
        loadStructure()
        loadPopulation()
    }

    private void loadStructure() {
        //the order of calls should not change.
        loadEpochs()
        loadArms()
        loadActivities()
        loadSegments()
        loadCells()
    }

    private void loadEpochs() {
        Preconditions.checkNotNull(trialDesign)
        Preconditions.checkNotNull(trialDesign.structure)
        Preconditions.checkNotNull(trialDesign.structure.epoch)

        trialDesign.structure.epoch.each {
            Epoch epoch = new Epoch()
            idToDomainObject.put(it.oid, epoch)
            epoch.setStart(Double.valueOf(it.start.real.value))
            epoch.setEnd(Double.valueOf(it.end.real.value))
            structure.setEpochAt(epoch, it.order.value-1)
        }
    }

    private void loadArms() {
        def arms = trialDesign.structure.arm
        trialDesign.structure.arm.each {
            Arm arm = new Arm(it.oid, armCounter++)
            structure.setArm(arm)
            idToDomainObject.put(it.oid, arm)
        }
    }

    private void loadActivities() {
        trialDesign.structure.activity.each {
            Activity activity;
            if (it.dosingRegimen) {
                if (it.dosingRegimen.value instanceof BolusType) {
                    activity = loadBolusAcivity(it.dosingRegimen.value)
                } else {
                    activity = new Infusion()
                    //TODO: what goes here?
                    throw new RuntimeException("Infusions are not yet supported.")
                }
                idToDomainObject.put(it.oid, activity)
            } else {
                //TODO: how can I model washout (it.washout)?
                throw new RuntimeException("Washouts are not yet supported.")
            }
        }
    }
    
    private Activity loadBolusAcivity(BolusType bolusType) {
        Activity activity
        
        Expression doseAmount = loadDoseAmount(bolusType.doseAmount)
        DosingTimes dosingTimes
        if (doseAmount) {
            if (bolusType.dosingTimes) {
                dosingTimes = loadDosingTimes(bolusType.dosingTimes)
            } else if (bolusType.steadyState) {
                dosingTimes = loadDosingTimes(bolusType.steadyState)
            }
            boolean isAssignedToODE = isAssignedToODE(bolusType.doseAmount)
            activity = new Bolus(doseAmount, dosingTimes, isAssignedToODE)
        } else {
            activity = loadIndividualDosing()
        }
        activity
    }

    private IndividualDosing loadIndividualDosing() {
        //TODO: Is it possible to have more than one IndividualDosing elements here?
        if (trialDesign.individualDosing.size()>1) {
            throw new RuntimeException("Multiple IndividualDosing elements are not yet supported.")
        } 
        DataSet dataset = DataSetLoader.load(trialDesign.individualDosing[0].dataSet)
        new IndividualDosing(dataset);
    }

    private Expression loadDoseAmount(DosingVariableType doseAmount) {
        Preconditions.checkNotNull(doseAmount)

        Expression expression
        if (doseAmount.assign) {
            if (doseAmount.assign.equation) {
                BinopType binopType = doseAmount.assign.equation.binop
                def left = binopType.content.value[0]
                def right = binopType.content.value[1]
                Expression leftAsExpression = createExpression(left)
                Expression rightAsExpression = createExpression(right)
                if (binopType.op == "times") {
                    expression = new BinaryExpression(leftAsExpression, rightAsExpression, Operator.TIMES);
                } else if (binopType.op == "divide") {
                    expression = new BinaryExpression(leftAsExpression, rightAsExpression, Operator.DIVIDE);
                } else if (binopType.op == "minus") {
                    expression = new BinaryExpression(leftAsExpression, rightAsExpression, Operator.MINUS);
                } else if (binopType.op == "plus") {
                    expression = new BinaryExpression(leftAsExpression, rightAsExpression, Operator.PLUS);
                }
            } else {
                expression = new Constant(doseAmount.assign.scalar.value.value);
            }
        }
        return expression;
    }

    private Expression createExpression(elem) {
        Preconditions.checkNotNull(elem)
        Expression exp
        if (elem instanceof eu.ddmore.libpharmml.dom.commontypes.RealValueType) {
            RealValueType type = elem;
            exp = new Constant(type.value)
        } else if (elem instanceof eu.ddmore.libpharmml.dom.commontypes.SymbolRefType) {
            SymbolRefType type = elem;
            exp = new Variable(type.symbIdRef)
        } else {
        //TODO: what other types are allowed here?
            throw new RuntimeException("Expression not yet supported: "+ elem.getClass())
        }
        exp
    }

    private DosingTimes loadDosingTimes(DosingTimesPointsType doseAmount) {
        Preconditions.checkNotNull(doseAmount)
        DosingTimes dosingTimes
        if (doseAmount.assign.sequence) {
            def begin = doseAmount.assign.sequence.begin.scalar.value.value
            def step = doseAmount.assign.sequence.stepSize.scalar.value.value
            def end = doseAmount.assign.sequence.end.scalar.value.value
            dosingTimes = new DosingTimesSequence((int)begin, (int)step, (int)end)
        } else if (doseAmount.assign.scalar) {
            def time = doseAmount.assign.scalar.value.value
            dosingTimes = new DosingTimesConstant((int)time)
        }
        dosingTimes
    }

    private DosingTimes loadDosingTimes(SteadyStateType steadyState) {
        Preconditions.checkNotNull(steadyState)
        int end = steadyState.endTime.assign.scalar.value.value
        int step = steadyState.interval.assign.scalar.value.value
        DosingTimes dosingTimes = new DosingTimesSequence(0, step, end);
        dosingTimes
    }

    private boolean isAssignedToODE(DosingVariableType doseAmount) {
        Preconditions.checkNotNull(doseAmount)
        doseAmount.inputTarget.value == isAssignedToODESymbol
    }

    private void loadSegments() {
        trialDesign.structure.segment.each {
            Segment segment = new Segment()
            it.activityRef.each {
                def activity = idToDomainObject[it.oidRef]
                segment.addActivity(activity)
            }
            idToDomainObject.put(it.oid, segment)
        }
    }

    private void loadCells() {
        trialDesign.structure.cell.each {
            Epoch e = idToDomainObject[it.epochRef.oidRef]
            List<Arm> arms = new ArrayList<Arm>()
            List<Segment> segments = new ArrayList<Segment>()
            it.armRef.each {
                arms.add( idToDomainObject[it.oidRef] )
            }
            it.segmentRef.each {
                segments.add( idToDomainObject[it.oidRef] )
            }
            Cell cell = new Cell(e, arms, segments)
            structure.addCell(cell)
            idToDomainObject.put(it.oid, cell)
        }
    }

    private void loadPopulation() {
        Variability variability = loadVariability(trialDesign.population.variabilityReference)

        individualColumnName = 'ID'
        armColumnName = 'ARM'
        replicateColumnName = 'REP'
        assignIndividualsToArms(trialDesign.population.dataSet)
    }

    def loadMapping(type) {
        type ? type.columnRef.columnIdRef : null
    }

    private Variability loadVariability(LevelReferenceType type) {
        Variability variability
        if (type) {
            variability = new Variability()
            idToDomainObject.put(type.symbRef.symbIdRef, variability)
        }
        variability
    }

    private void assignIndividualsToArms(DataSetType dataSet) {
        def columns = []
        dataSet.definition.column.each {
            columns.add( it.columnId )
        }
        
        dataSet.table.row.each {
            def row = []
            it.scalar.each {
                row.add( it.value.value )
            }
            createSubject(columns, row)
        }
    }

    private void createSubject(columns, row) {
        String individualName
        String armName
        Map<String,String> attributeToValue = new HashMap<String,String>()
        int replicate=0
        for (int i=0; i<columns.size(); i++) {
            String colValue = columns[i].toUpperCase()
            if (colValue.equals(individualColumnName)) {
                individualName = row[i]
            } else if (colValue.equals(armColumnName)) {
                armName = row[i]
            } else if (colValue.equals(replicateColumnName)) {
                replicate = Integer.valueOf(row[i])
            } else {
                attributeToValue.put(colValue, row[i])
            }
        }
        for (int i=1; i<=replicate; i++) {
            Subject subject = new Subject(individualName+i, subjectCounter++)
            subject.addAllAttributeValues(attributeToValue)
            idToDomainObject[individualName] = subject
            Arm arm = idToDomainObject[armName]
            arm.add(subject);
        }
        if (replicate==0) {
            Subject subject = new Subject(individualName, subjectCounter++)
            subject.addAllAttributeValues(attributeToValue)
            idToDomainObject[individualName] = subject
            Arm arm = idToDomainObject[armName]
            arm.add(subject);
        }
    }

    public String toString() {
        structure
    }
}

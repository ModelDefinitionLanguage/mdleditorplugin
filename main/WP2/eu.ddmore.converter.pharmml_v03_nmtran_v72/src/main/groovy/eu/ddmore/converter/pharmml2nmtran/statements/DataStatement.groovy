/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.statements

import java.sql.ResultSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Multimap
import com.google.common.collect.TreeMultimap
import eu.ddmore.converter.pharmml2nmtran.loader.ModellingStepsLoader
import eu.ddmore.converter.pharmml2nmtran.loader.TrialDesignLoader
import eu.ddmore.converter.pharmml2nmtran.model.SubjectComparator
import eu.ddmore.converter.pharmml2nmtran.utils.TextFileWriter;
import eu.ddmore.libpharmml.dom.PharmML
import eu.ddmore.libpharmml.dom.commontypes.IdValueType
import eu.ddmore.libpharmml.dom.commontypes.RealValueType
import eu.ddmore.libpharmml.dom.dataset.DatasetRowType
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.trialdesign.ActivityType
import eu.ddmore.libpharmml.dom.trialdesign.BolusType;
import eu.ddmore.libpharmml.dom.trialdesign.DosingTimesPointsType
import eu.ddmore.libpharmml.dom.trialdesign.DosingVariableType
import eu.ddmore.pharmacometrics.model.modellingsteps.ModellingSteps
import eu.ddmore.pharmacometrics.model.trialdesign.Subject
import eu.ddmore.pharmacometrics.model.trialdesign.math.Expression
import eu.ddmore.pharmacometrics.model.trialdesign.population.DataRow
import eu.ddmore.pharmacometrics.model.trialdesign.population.DataSet
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Activity;
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Arm;
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Bolus;
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Cell
import eu.ddmore.pharmacometrics.model.trialdesign.structure.DosingTimes
import eu.ddmore.pharmacometrics.model.trialdesign.structure.IndividualDosing;
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Structure


class DataStatement extends NMTranFormatter {

    private PharmML pmlDOM
    private String outputPath
    private Structure structure
    private ModellingSteps modellingSteps
    private File file
    private TextFileWriter textWriter
    private List<String> headers

    public DataStatement(PharmML pmlDOM, String outputPath) {
        this.pmlDOM = pmlDOM
        this.outputPath = outputPath

        TrialDesignLoader trialDesignloader = new TrialDesignLoader("trialDesign":pmlDOM.trialDesign)
        trialDesignloader.load()
        structure = trialDesignloader.structure

        ModellingStepsLoader modellingStepsLoader = new ModellingStepsLoader(pmlDOM.modellingSteps)
        modellingStepsLoader.load()
        modellingSteps = modellingStepsLoader.modellingSteps

        file = new File(outputPath + "_data.csv")
        textWriter = new TextFileWriter();
    }

    public DataStatement() {
    }

    public String createDataFile() {
        composeData()
        "\$DATA $file.name IGNORE=@\n"
    }

    public List<String> getHeaders() {
        return headers
    }

    private void computeHeaders(List<String> dosingColumns) {
        headers = new ArrayList<String>()
        DataSet dataSet = modellingSteps.estimationSteps[0].objectiveDataSets[0].dataSet
        List<String> dataColumns = modellingSteps.estimationSteps[0].objectiveDataSets[0].dataSet.columns
        for (String dataColumn : dataColumns) {
            headers.add(dataColumn.toUpperCase())
        }
        headers.add('ARM')

        for (String dosingColumn : dosingColumns) {
            if (!dataColumns.contains(dosingColumn)) {
                if (dosingColumn.equals('DOSE')) {
                    headers.add('AMT')
                } else {
                    headers.add(dosingColumn)
                }
            }
        }
        headers.add('MDV')
        headers.add('EVID')
    }

    public void composeData() {
        for (Object epoch : structure.epochs) {
            List<Cell> cellsOfEpoch = structure.getCellsOfEpoch(epoch)
            for (Cell cell : cellsOfEpoch){
                cell.segments.each {
                    it.activities.each { activity ->
                        printArms(cell, activity)
                    }
                }
            }
        }
    }


    private void printArms(Cell cell, Activity activity) {
        cell.getArms().each {
            printArm(it, cell, activity)
        }
    }

    private void printArm(Arm arm, Cell cell, Bolus activity) {
        Expression doseAmount = activity.doseAmount
        DosingTimes dosingTimes = activity.dosingTimes

        DataSet dataSet
        if (modellingSteps.estimationSteps) {
            printEstimationStepArm(arm, cell, activity)
        } else {
            printSimulationStepArm(arm, cell, activity)
        }
    }

    private void printEstimationStepArm(Arm arm, Cell cell, Bolus activity) {
        Expression doseAmount = activity.doseAmount
        DosingTimes dosingTimes = activity.dosingTimes

        DataSet dataSet = modellingSteps.estimationSteps[0].objectiveDataSets[0].dataSet
        TreeSet<Subject> orderedSubjects = orderSubjects(arm.subjects)

        computeHeaders(orderedSubjects.first())

        orderedSubjects.each { subject ->
            Collection<DataRow> dataRows = dataSet.getRowsFor(subject.name)
            if (dataRows) {
                Set<Double> times = new TreeSet<Double>()
                TreeMultimap<Double, String> dosingLines = createDosingRowsForSubject(subject, activity, arm.index)
                times.addAll(dosingLines.keySet())
                TreeMultimap<Double, String> dataLines = createDataRowsForSubject(subject, dataRows, capitalize(dataSet.getColumns()), arm.index)
                times.addAll(dataLines.keySet())

                printRecordsInTimeOrder(times, dosingLines, dataLines)
            }
        }
    }

    private void computeHeaders(Subject subject) {
        List<String> columns = new ArrayList<String>()
        columns.addAll(subject.getAttributeToValue().keySet())
        columns.add('AMT')
        printHeaders(columns)
    }

    private void printSimulationStepArm(Arm arm, Cell cell, Bolus activity) {
        Expression doseAmount = activity.doseAmount
        DosingTimes dosingTimes = activity.dosingTimes

        DataSet dataSet = modellingSteps.estimationSteps[0].objectiveDataSets[0].dataSet
        TreeSet<Subject> orderedSubjects = orderSubjects(arm.subjects)
        computeHeaders(orderedSubjects.first())
        
        orderedSubjects.each { subject ->
            Collection<DataRow> dataRows = dataSet.getRowsFor(subject.name)
            if (dataRows) {
                Set<Double> times = new TreeSet<Double>()
                TreeMultimap<Double, String> dosingLines = createDosingRowsForSubject(subject, activity, arm.index)
                times.addAll(dosingLines.keySet())
                TreeMultimap<Double, String> dataLines = createDataRowsForSubject(subject, dataRows, capitalize(dataSet.getColumns()), arm.index)
                times.addAll(dataLines.keySet())

                printRecordsInTimeOrder(times, dosingLines, dataLines)
            }
        }
    }

    private List<String> capitalize(List<String> input) {
        List<String> result = new ArrayList<String>()
        for (String s : input) {
            result.add(s.toUpperCase())
        }
        result
    }

    private void printArm(Arm arm, Cell cell, IndividualDosing activity) {
        //TODO: Don't know what to do if the above have more than 1 elements
        if (modellingSteps.estimationSteps.size()>1) {
            throw new RuntimeException("Multiple EstimationSteps are not yet supported.")
        }
        if (modellingSteps.estimationSteps[0].objectiveDataSets.size()>1) {
            throw new RuntimeException("Multiple ObjectiveDataSets inside EstimationSteps are not yet supported.")
        }
        
        DataSet dataSet = modellingSteps.estimationSteps[0].objectiveDataSets[0].dataSet

        TreeSet<Subject> orderedSubjects = orderSubjects(arm.subjects)

        printHeaders(activity.dataset.getColumns())
        
        orderedSubjects.each { subject ->
            Collection<DataRow> dataRows = dataSet.getRowsFor(''+ subject.id)
            if (dataRows) {
                Collection<DataRow> dosingRows = activity.getDosingInfoFor(''+ subject.id)

                Set<Double> times = new TreeSet<Double>()
                TreeMultimap<Double, String> dosingLines = createDosingRowsForSubject(subject, dosingRows, activity.dataset.getColumns(), arm.index)
                times.addAll(dosingLines.keySet())
                TreeMultimap<Double, String> dataLines = createDataRowsForSubject(subject, dataRows, dataSet.getColumns(), arm.index)
                times.addAll(dataLines.keySet())

                printRecordsInTimeOrder(times, dosingLines, dataLines)
            }
        }
    }

    private void printHeaders(List<String> dosingColumns) {
        computeHeaders(dosingColumns)

        //Sets the headeer of the csv. Starts with '@' to ommit the first line.
        textWriter.writeToFile(file, '@' + headers.join(','))
    }

    private Set<Subject> orderSubjects(Set<Subject> subjects) {
        Set<Subject> orderedSubjects = new TreeSet<Subject>(new SubjectComparator())
        orderedSubjects.addAll(subjects)
        orderedSubjects
    }

    private void printRecordsInTimeOrder(Set<Double> times, Multimap<Double, String> dosingLines, Multimap<Double, String> dataLines) {
        for (Double time : times) {
            Collection<String> dosingLinesForTime = dosingLines.get(time)
            Collection<String> dataLinesForTime = dataLines.get(time)

            for (String dosingLineForTime : dosingLinesForTime) {
                textWriter.appendToFile(file, '\n' + dosingLineForTime)
            }
            for (String dataLineForTime : dataLinesForTime) {
                textWriter.appendToFile(file, '\n' + dataLineForTime)
            }
        }
    }

    private TreeMultimap<Double, String> createDataRowsForSubject(Subject subject, Collection<DataRow> dataRows, List<String> columns, int armIndex) {
        TreeMultimap<Double, String> timeToLine = TreeMultimap.create()
        for (DataRow dataRow : dataRows) {
            def dataLine = new StringBuilder()
            dataLine << "${subject.id}"
            for (int i=1; i<dataRow.data.size(); i++ ) {
                dataLine << ", ${dataRow.data.get(i)}"
            }
            dataLine << ", ${armIndex}"
            for (String value: subject.getAttributeToValue().values()) {
                dataLine << ", ${value}"
            }
            dataLine << ", ., ., 0"

            def timeDatum = dataRow.asMap(columns).get('TIME')
            Double time = Double.valueOf(timeDatum)
            timeToLine.put(time, dataLine.toString())
        }
        timeToLine
    }

    private TreeMultimap<Double, String> createDosingRowsForSubject(Subject subject, Bolus activity, int armIndex) {
        Expression doseAmount = activity.doseAmount
        DosingTimes dosingTimes = activity.dosingTimes

        TreeMultimap<Double, String> timeToLine = TreeMultimap.create()

        for (Integer dosingTime : dosingTimes.getTimes()) {
            def dataLine = new StringBuilder(subject.id)
            dataLine << "${subject.id}, ${dosingTime}, ., ${armIndex}"
            for (String value: subject.getAttributeToValue().values()) {
                dataLine << ", ${value}"
            }
            dataLine << ", ${doseAmount.evaluate()}, 1, 1"

            Double time = Double.valueOf(dosingTime)
            timeToLine.put(time, dataLine.toString())
        }

        timeToLine
    }

    private TreeMultimap<Double, String> createDosingRowsForSubject(Subject subject, Collection<DataRow> dosingRows, List<String> columns, int armIndex) {
        TreeMultimap<Double, String> timeToLine = TreeMultimap.create()
        for (DataRow dataRow : dosingRows) {
            def dataLine = new StringBuilder(subject.id)
            def dosingMap = dataRow.asMap(columns)
            dataLine << "${subject.id}, ${dosingMap.get('TIME')}, ., ${armIndex}, ${dosingMap.get('DOSE')}, 1, 1"

            Double time = Double.valueOf(dataRow.asMap(columns).get('TIME'))
            timeToLine.put(time, dataLine.toString())
        }
        timeToLine
    }

}

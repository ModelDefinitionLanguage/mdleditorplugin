/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.utils

import java.util.List;
import java.util.Map;

import eu.ddmore.converter.pharmml2nmtran.model.CorrelationKey
import eu.ddmore.converter.pharmml2nmtran.model.Omega;
import eu.ddmore.converter.pharmml2nmtran.model.Sigma;
import eu.ddmore.converter.pharmml2nmtran.model.Theta;
import eu.ddmore.libpharmml.dom.PharmML;
import eu.ddmore.libpharmml.dom.commontypes.CommonVariableDefinitionType
import eu.ddmore.libpharmml.dom.commontypes.RealValueType
import eu.ddmore.libpharmml.dom.commontypes.VariableDefinitionType
import eu.ddmore.libpharmml.dom.dataset.ColumnMappingType
import eu.ddmore.libpharmml.dom.maths.PiecewiseType
import eu.ddmore.libpharmml.dom.modeldefn.CorrelationType
import eu.ddmore.libpharmml.dom.modeldefn.ParameterModelType
import eu.ddmore.libpharmml.dom.modeldefn.ParameterRandomVariableType
import eu.ddmore.libpharmml.dom.modeldefn.SimpleParameterType
import eu.ddmore.libpharmml.dom.modeldefn.StructuralModelType
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.modellingsteps.ParameterEstimateType
import eu.ddmore.libpharmml.dom.trialdesign.BolusType
import eu.ddmore.libpharmml.dom.trialdesign.CovariateMappingType
import javax.xml.bind.JAXBElement


public class Parameters {

    private PharmML pmlDOM
    private Map<String, Theta> thetas;
    private Map<String, Omega> omegas;
    private Map<String, Sigma> sigmas;
    private Map<String, String> etaToOmega
    private Map<String, SimpleParameterType> groups;
    private Map<String, CommonVariableDefinitionType> structuralVars;
    private Set<String> etas;
    private Double dosingTime;
    private String dosingTimeVarname;
    private Map<String, CorrelationType> correlations
    private Map<CorrelationKey, String> correlationkeyToName
    private Map<String, String> varToName
    private int thetasCounter=1
    private int sigmasCounter=1
    private int omegasCounter=1

    public Parameters(PharmML pmlDOM) {
        this.pmlDOM = pmlDOM;
        thetas = new HashMap<String, Theta>();
        omegas = new HashMap<String, Omega>();
        sigmas = new HashMap<String, Sigma>();
        groups = new HashMap<String, SimpleParameterType>()
        structuralVars = new HashMap<String, CommonVariableDefinitionType>()
        etaToOmega = new HashMap<String, String>()
        correlations = new HashMap<String, CorrelationType>()
        correlationkeyToName = new HashMap<CorrelationKey, String>()
        varToName = new HashMap<String, String>()
    }

    /**
     * Needs to be called immediately after the Constructor.
     */
    public void init() {
        findCorrelations()
        findSimpleParameters();
        findStructuralVariables();

        //If a TrialDesign does not exist, this seems to be fatal for NMTRAN.
        //If that is true, we should report a conversion error.
        if (pmlDOM.trialDesign) {
            findDosingTime();
            findVariableMappings()
        }

        findThetasAndOmegasInCovariates()

    }

    public Map<String, Theta> getThetas() {
        thetas
    }

    public Map<String, Omega> getOmegas() {
        omegas
    }

    public Map<String, Sigma> getSigmas() {
        sigmas
    }

    public Set<String> getEtas() {
        etas
    }

    public SimpleParameterType getGroupVariable(String s) {
        groups.get(s)
    }

    public CommonVariableDefinitionType getStructuralVariable(String s) {
        structuralVars.get(s)
    }

    public Collection<SimpleParameterType> getGroupsAsCollection() {
        groups.values()
    }

    public double getDosingTime() {
        dosingTime
    }

    public String getDosingTimeVarname() {
        dosingTimeVarname
    }

    private void findVariableMappings() {
        pmlDOM.trialDesign.population.columnMapping.each {
            varToName.put(it.symbRef.symbIdRef, it.columnRef.columnIdRef)
        }
        //TODO: Is D handcoded in PharmML?
        varToName.put("D", "DOSE")
        varToName.put("Cmodel", "C")
    }

    private void findCorrelations() {
        pmlDOM.modelDefinition.parameterModel.each {
            it.correlation.each {
                if (it?.pairwise?.covariance) {
                    throw new RuntimeException("'Correlation/Pairwise/Covariance' element is not yet supported.")
                }
				if(it.pairwise) {
					String name = it.pairwise.correlationCoefficient.symbRef.symbIdRef
					correlations.put(name, it)
					String eta1 = it.pairwise.randomVariable1.symbRef.symbIdRef
					String eta2 = it.pairwise.randomVariable2.symbRef.symbIdRef
					correlationkeyToName.put(new CorrelationKey(eta1, eta2), name)
				}
            }
        }
    }

    private void findDosingTime() {
        if (pmlDOM.trialDesign.structure.activity[0].washout) {
            throw new RuntimeException("Washouts are not yet supported")
        }
        BolusType bolusType = pmlDOM.trialDesign.structure.activity[0].dosingRegimen.value
        if (bolusType.dosingTimes) {
            if (bolusType.dosingTimes.symbRef) {
                dosingTimeVarname = bolusType.dosingTimes.symbRef.symbIdRef
            }
            if (bolusType.dosingTimes.assign.scalar) {
                RealValueType realValueType  = bolusType.dosingTimes.assign.scalar.value
                dosingTime = realValueType.value
            } else if (bolusType.dosingTimes.assign.sequence) {
                dosingTime = bolusType.dosingTimes.assign.sequence.begin.scalar.value.value
            }
        }
    }

    private void findStructuralVariables() {
        for (StructuralModelType structuralModel in pmlDOM.modelDefinition.structuralModel ) {
            for (CommonVariableDefinitionType varType in structuralModel.commonVariable.value ) {
                String variableName = varType.symbId

                if ( (varType.assign) && (! containsPieceWise(varType.assign.equation) ) ) {
                    structuralVars.put(variableName, varType)
                }
            }
        }
    }

    private boolean containsPieceWise(equation) {
        if (equation.piecewise) {
            return true;
        }
        if (!equation.scalarOrSymbRefOrBinop.size()) {
            return false;
        }
        if (equation.scalarOrSymbRefOrBinop.get(0).value instanceof PiecewiseType) {
            return true;
        }
    }

    private void findSimpleParameters() {
        List<String> simpleParametersInParameterModel = findSimpleParametersInParameterModel()

        etaToOmega = mapEtasToOmegas()
        etas = etaToOmega.keySet()

        if (modellingStepsExist() && isEstimation()) {
            findSimpleparametersInModelingSteps(etaToOmega.values(), simpleParametersInParameterModel)
        } else {
            categorizeParameters(etaToOmega.values(), simpleParametersInParameterModel)
        }

        registerOmegas(etaToOmega.values())
    }

    /**
     * 
     * 20/03/2014: Current MDL 2 PharmML conversion misses modellingsteps.
     * However, NMTRAN needs them to run an estimation. 
     * So we should report a conversion error here. 
     * But we should discuss that first in more detail.
     */
    private boolean modellingStepsExist() {
        return pmlDOM.modellingSteps
    }

    private void categorizeParameters(Collection<String> omegasFromEtas, List<String> simpleParametersInParameterModel) {
        simpleParametersInParameterModel.each {
            registerParameter(it, omegasFromEtas, simpleParametersInParameterModel)
        }
    }

    private List<String> findSimpleParametersInParameterModel() {
        List<String> simpleParametersInParameterModel = new ArrayList<String>()
        pmlDOM.modelDefinition.parameterModel.each {
            simpleParametersInParameterModel.addAll( findSimpleParametersInParameterModel(it) )
        }
        simpleParametersInParameterModel
    }

    private List<String> findSimpleParametersInParameterModel(ParameterModelType parameterModelType) {
        List<String> simpleParametersInParameterModel = new ArrayList<String>()
        parameterModelType.commonParameterElement.each {
            if (it.value instanceof SimpleParameterType) {
                SimpleParameterType simpleParameterType = it.value
                String name = simpleParameterType.symbId

                if (!simpleParametersInParameterModel.contains(name) && (!simpleParameterType.assign || !simpleParameterType.assign.equation)) {
                    simpleParametersInParameterModel.add(name)
                }
                if (simpleParameterType.assign) {
                    groups.put(name, simpleParameterType)
                }
            }
        }
        simpleParametersInParameterModel
    }

    private Map<String, String> mapEtasToOmegas() {
        Map<String, String> etaToOmega = new HashMap<String, String>()
        pmlDOM.modelDefinition.parameterModel.each {
            etaToOmega.putAll(mapEtasToOmegas(it))
        }
        etaToOmega
    }

    private Map<String, String> mapEtasToOmegas(ParameterModelType parameterModelType) {
        Map<String, String> etaToOmega = new HashMap<String, String>()

        parameterModelType.commonParameterElement.each {
            if (it.value instanceof ParameterRandomVariableType) {
                ParameterRandomVariableType parameterRandomVariableType = (ParameterRandomVariableType) it.value;
                String etaName = parameterRandomVariableType.symbId
                String omegaName
                if (parameterRandomVariableType.abstractContinuousUnivariateDistribution.value.variance && parameterRandomVariableType.abstractContinuousUnivariateDistribution.value.variance.var) {
                    omegaName = parameterRandomVariableType.abstractContinuousUnivariateDistribution.value.variance.var.varId
                } else if (parameterRandomVariableType.abstractContinuousUnivariateDistribution.value.stddev && parameterRandomVariableType.abstractContinuousUnivariateDistribution.value.stddev.var) {
                    omegaName = parameterRandomVariableType.abstractContinuousUnivariateDistribution.value.stddev.var.varId
                }
                if (omegaName) {
                    etaToOmega.put(etaName, omegaName);
                }
            }
        }
        etaToOmega
    }

    private void findSimpleparametersInModelingSteps(Collection<String> omegasFromEtas, List<String> simpleParametersInParameterModel) {
        for (JAXBElement elem in pmlDOM.modellingSteps.commonModellingStep) {
            if (elem.value instanceof EstimationStepType) {
                EstimationStepType estStep = (EstimationStepType) elem.value
                estStep.parametersToEstimate.parameterEstimation.each {
                    registerParameter(it, omegasFromEtas, simpleParametersInParameterModel)
                }
            }
        }
    }

    private boolean isEstimation() {
        pmlDOM.modellingSteps.commonModellingStep.find { it.value instanceof EstimationStepType }
    }

    private void registerParameter(ParameterEstimateType type, Collection<String> omegasFromEtas, List<String> simpleParametersInParameterModel) {
        String name = type.symbRef.symbIdRef
        registerParameter(name, omegasFromEtas, simpleParametersInParameterModel)
    }

    private void registerParameter(String name, Collection<String> omegasFromEtas, List<String> simpleParametersInParameterModel) {
        if (!omegasFromEtas.contains(name)) {
            if (name.startsWith("sigma")) {
                Sigma sigma = new Sigma(name, sigmasCounter++, false)
                sigmas.put(name, sigma)
            } else if (isNotContainedInCorrelationsAndNonVisitedTheta(name)) {
                Theta theta = new Theta(name, simpleParametersInParameterModel.contains(name), thetasCounter++)
                thetas.put(name, theta)
            }
        }
    }

    private void registerOmegas(Collection<String> omegasFromEtas) {
        pmlDOM.modelDefinition.parameterModel.each { paramModel ->
            paramModel.commonParameterElement.each { commonParamElem ->
                if (commonParamElem.value instanceof SimpleParameterType) {
                    String name = commonParamElem.value.symbId
                    if (omegasFromEtas.contains(name)) {
                        Omega omega = new Omega(name, omegasCounter++, false)
                        omegas.put(name, omega)
                        groups.remove(name)
                    }
                }
            }
        }
    }

    private boolean isNotContainedInCorrelationsAndNonVisitedTheta(String name) {
        !correlations.containsKey(name) && (!thetas.containsKey(name))
    }

    public Theta isTheta(String s) {
        thetas.get(s)
    }

    public Omega isOmega(String s) {
        omegas.get(s)
    }

    public Sigma isSigma(String s) {
        sigmas.get(s)
    }

    public int getOmegaIndexFromEta(String etaName) {
        String omegaName = etaToOmega.get(etaName)
        omegas.get(omegaName).index
    }

    private void findThetasAndOmegasInCovariates() {
        pmlDOM.modelDefinition.covariateModel.each {
            Set<String> vars = new HashSet<String>();
            Set<String> omegaVars = new HashSet<String>();

            it.simpleParameter.each {
                it.assign
                vars.add(it.symbId)
            }
            it.covariate.each {
                if (it.continuous) {
                    if (it.continuous.abstractContinuousUnivariateDistribution) {
                        if (it.continuous.abstractContinuousUnivariateDistribution.value.stddev) {
                            omegaVars.add( it.continuous.abstractContinuousUnivariateDistribution.value.stddev.var.varId )
                        } else if (it.continuous.abstractContinuousUnivariateDistribution.value.variance) {
                            omegaVars.add( it.continuous.abstractContinuousUnivariateDistribution.value.variance.var.varId )
                        }
                    }
                }
            }
            vars.each {
                if (omegaVars.contains(it)) {
                    Omega omega = new Omega(it, omegasCounter++, false);
                    omegas.put(it, omega)
                } else if (!thetas.containsKey(it)) {
                    Theta theta = new Theta(it, false, thetasCounter++);
                    thetas.putAt(it, theta);
                }

            }
        }
    }

    public Sigma createConstantSigma() {
        Sigma sigma = new Sigma(false, sigmasCounter++)
        sigmas.put(sigma.representation, sigma)
        return sigma
    }
}
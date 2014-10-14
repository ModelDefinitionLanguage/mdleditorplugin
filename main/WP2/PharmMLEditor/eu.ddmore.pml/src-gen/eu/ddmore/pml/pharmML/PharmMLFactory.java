/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see eu.ddmore.pml.pharmML.PharmMLPackage
 * @generated
 */
public interface PharmMLFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PharmMLFactory eINSTANCE = eu.ddmore.pml.pharmML.impl.PharmMLFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Mml Pharm ML</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mml Pharm ML</em>'.
   * @generated
   */
  Mml_PharmML createMml_PharmML();

  /**
   * Returns a new object of class '<em>Mml Pharma ML Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mml Pharma ML Content</em>'.
   * @generated
   */
  Mml_PharmaMLContent createMml_PharmaMLContent();

  /**
   * Returns a new object of class '<em>Mml Symbol Definition Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mml Symbol Definition Type</em>'.
   * @generated
   */
  Mml_SymbolDefinitionType createMml_SymbolDefinitionType();

  /**
   * Returns a new object of class '<em>Mml Function Definition Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mml Function Definition Type</em>'.
   * @generated
   */
  Mml_FunctionDefinitionType createMml_FunctionDefinitionType();

  /**
   * Returns a new object of class '<em>Mml Func Parameter Definition Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mml Func Parameter Definition Type</em>'.
   * @generated
   */
  Mml_FuncParameterDefinitionType createMml_FuncParameterDefinitionType();

  /**
   * Returns a new object of class '<em>mdef Model Definition Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Model Definition Type</em>'.
   * @generated
   */
  mdef_ModelDefinitionType createmdef_ModelDefinitionType();

  /**
   * Returns a new object of class '<em>mdef Parameter Type Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Parameter Type Name</em>'.
   * @generated
   */
  mdef_ParameterTypeName createmdef_ParameterTypeName();

  /**
   * Returns a new object of class '<em>mdef Structural Model Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Structural Model Type</em>'.
   * @generated
   */
  mdef_StructuralModelType createmdef_StructuralModelType();

  /**
   * Returns a new object of class '<em>mdef Observation Model Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Observation Model Type</em>'.
   * @generated
   */
  mdef_ObservationModelType createmdef_ObservationModelType();

  /**
   * Returns a new object of class '<em>mdef Continuous Obs Model Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Continuous Obs Model Type</em>'.
   * @generated
   */
  mdef_ContinuousObsModelType createmdef_ContinuousObsModelType();

  /**
   * Returns a new object of class '<em>mdef Error Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Error Model</em>'.
   * @generated
   */
  mdef_ErrorModel createmdef_ErrorModel();

  /**
   * Returns a new object of class '<em>mdef Random Effect Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Random Effect Type</em>'.
   * @generated
   */
  mdef_RandomEffectType createmdef_RandomEffectType();

  /**
   * Returns a new object of class '<em>mdef Parameter Model Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Parameter Model Type</em>'.
   * @generated
   */
  mdef_ParameterModelType createmdef_ParameterModelType();

  /**
   * Returns a new object of class '<em>mdef Correlation Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Correlation Type</em>'.
   * @generated
   */
  mdef_CorrelationType createmdef_CorrelationType();

  /**
   * Returns a new object of class '<em>mdef Param1 Var Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Param1 Var Type</em>'.
   * @generated
   */
  mdef_Param1VarType createmdef_Param1VarType();

  /**
   * Returns a new object of class '<em>mdef Param2 Var Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Param2 Var Type</em>'.
   * @generated
   */
  mdef_Param2VarType createmdef_Param2VarType();

  /**
   * Returns a new object of class '<em>mdef Covariance Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Covariance Type</em>'.
   * @generated
   */
  mdefCovarianceType createmdefCovarianceType();

  /**
   * Returns a new object of class '<em>mdef Correlation Coefficient Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Correlation Coefficient Type</em>'.
   * @generated
   */
  mdef_CorrelationCoefficientType createmdef_CorrelationCoefficientType();

  /**
   * Returns a new object of class '<em>mdef Covariate Model Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Covariate Model Type</em>'.
   * @generated
   */
  mdef_CovariateModelType createmdef_CovariateModelType();

  /**
   * Returns a new object of class '<em>mdef Parameter Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Parameter Type</em>'.
   * @generated
   */
  mdef_ParameterType createmdef_ParameterType();

  /**
   * Returns a new object of class '<em>mdef Covariate Variability Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Covariate Variability Type</em>'.
   * @generated
   */
  mdef_CovariateVariabilityType createmdef_CovariateVariabilityType();

  /**
   * Returns a new object of class '<em>mdef transformation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef transformation</em>'.
   * @generated
   */
  mdef_transformation createmdef_transformation();

  /**
   * Returns a new object of class '<em>mdef Categorical Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Categorical Type</em>'.
   * @generated
   */
  mdef_CategoricalType createmdef_CategoricalType();

  /**
   * Returns a new object of class '<em>mdef Continuous Covariate Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Continuous Covariate Type</em>'.
   * @generated
   */
  mdef_ContinuousCovariateType createmdef_ContinuousCovariateType();

  /**
   * Returns a new object of class '<em>mdef Transformation Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Transformation Type</em>'.
   * @generated
   */
  mdef_TransformationType createmdef_TransformationType();

  /**
   * Returns a new object of class '<em>mdef Variability Level Defn Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Variability Level Defn Type</em>'.
   * @generated
   */
  mdef_VariabilityLevelDefnType createmdef_VariabilityLevelDefnType();

  /**
   * Returns a new object of class '<em>mdef Import Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Import Type</em>'.
   * @generated
   */
  mdef_ImportType createmdef_ImportType();

  /**
   * Returns a new object of class '<em>mdef Import Link Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Import Link Type</em>'.
   * @generated
   */
  mdef_ImportLinkType createmdef_ImportLinkType();

  /**
   * Returns a new object of class '<em>mdef Import Resource Type Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Import Resource Type Type</em>'.
   * @generated
   */
  mdef_ImportResourceTypeType createmdef_ImportResourceTypeType();

  /**
   * Returns a new object of class '<em>mdef Covariate Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Covariate Type</em>'.
   * @generated
   */
  mdef_CovariateType createmdef_CovariateType();

  /**
   * Returns a new object of class '<em>mdef Fixed Effect Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Fixed Effect Type</em>'.
   * @generated
   */
  mdef_FixedEffectType createmdef_FixedEffectType();

  /**
   * Returns a new object of class '<em>mdef Category Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Category Type</em>'.
   * @generated
   */
  mdef_CategoryType createmdef_CategoryType();

  /**
   * Returns a new object of class '<em>mdef Probability Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Probability Type</em>'.
   * @generated
   */
  mdef_ProbabilityType createmdef_ProbabilityType();

  /**
   * Returns a new object of class '<em>mdef Parameter Random Effect Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Parameter Random Effect Type</em>'.
   * @generated
   */
  mdef_ParameterRandomEffectType createmdef_ParameterRandomEffectType();

  /**
   * Returns a new object of class '<em>mdef Initial Condition Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mdef Initial Condition Type</em>'.
   * @generated
   */
  mdef_InitialConditionType createmdef_InitialConditionType();

  /**
   * Returns a new object of class '<em>msteps Modeling Steps</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Modeling Steps</em>'.
   * @generated
   */
  msteps_ModelingSteps createmsteps_ModelingSteps();

  /**
   * Returns a new object of class '<em>msteps Modeling Steps Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Modeling Steps Content</em>'.
   * @generated
   */
  msteps_ModelingStepsContent createmsteps_ModelingStepsContent();

  /**
   * Returns a new object of class '<em>msteps Step Dependencies Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Step Dependencies Type</em>'.
   * @generated
   */
  msteps_StepDependenciesType createmsteps_StepDependenciesType();

  /**
   * Returns a new object of class '<em>msteps Step Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Step Type</em>'.
   * @generated
   */
  msteps_StepType createmsteps_StepType();

  /**
   * Returns a new object of class '<em>msteps Dependant Step Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Dependant Step Type</em>'.
   * @generated
   */
  msteps_DependantStepType createmsteps_DependantStepType();

  /**
   * Returns a new object of class '<em>msteps Estimation Step</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Estimation Step</em>'.
   * @generated
   */
  msteps_EstimationStep createmsteps_EstimationStep();

  /**
   * Returns a new object of class '<em>msteps Parameters To Estimate Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Parameters To Estimate Type</em>'.
   * @generated
   */
  msteps_ParametersToEstimateType createmsteps_ParametersToEstimateType();

  /**
   * Returns a new object of class '<em>mSteps Variable Estimate Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mSteps Variable Estimate Type</em>'.
   * @generated
   */
  mSteps_VariableEstimateType createmSteps_VariableEstimateType();

  /**
   * Returns a new object of class '<em>mSteps fixed</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mSteps fixed</em>'.
   * @generated
   */
  mSteps_fixed createmSteps_fixed();

  /**
   * Returns a new object of class '<em>mSteps Upper Bound Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mSteps Upper Bound Type</em>'.
   * @generated
   */
  mSteps_UpperBoundType createmSteps_UpperBoundType();

  /**
   * Returns a new object of class '<em>mSteps Lower Bound Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mSteps Lower Bound Type</em>'.
   * @generated
   */
  mSteps_LowerBoundType createmSteps_LowerBoundType();

  /**
   * Returns a new object of class '<em>mSteps Initial Estimate Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>mSteps Initial Estimate Type</em>'.
   * @generated
   */
  mSteps_InitialEstimateType createmSteps_InitialEstimateType();

  /**
   * Returns a new object of class '<em>msteps Objective Data Set Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Objective Data Set Type</em>'.
   * @generated
   */
  msteps_ObjectiveDataSetType createmsteps_ObjectiveDataSetType();

  /**
   * Returns a new object of class '<em>msteps Column Mapping Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Column Mapping Type</em>'.
   * @generated
   */
  msteps_ColumnMappingType createmsteps_ColumnMappingType();

  /**
   * Returns a new object of class '<em>msteps Column Mapping Type Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Column Mapping Type Content</em>'.
   * @generated
   */
  msteps_ColumnMappingTypeContent createmsteps_ColumnMappingTypeContent();

  /**
   * Returns a new object of class '<em>msteps Output Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Output Type</em>'.
   * @generated
   */
  msteps_OutputType createmsteps_OutputType();

  /**
   * Returns a new object of class '<em>msteps Assign Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Assign Type</em>'.
   * @generated
   */
  msteps_AssignType createmsteps_AssignType();

  /**
   * Returns a new object of class '<em>msteps Target Var Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Target Var Type</em>'.
   * @generated
   */
  msteps_TargetVarType createmsteps_TargetVarType();

  /**
   * Returns a new object of class '<em>msteps Use Variability Level</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Use Variability Level</em>'.
   * @generated
   */
  msteps_UseVariabilityLevel createmsteps_UseVariabilityLevel();

  /**
   * Returns a new object of class '<em>msteps Use Variability Node</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Use Variability Node</em>'.
   * @generated
   */
  msteps_UseVariabilityNode createmsteps_UseVariabilityNode();

  /**
   * Returns a new object of class '<em>msteps Simulation Step</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Simulation Step</em>'.
   * @generated
   */
  msteps_SimulationStep createmsteps_SimulationStep();

  /**
   * Returns a new object of class '<em>msteps Sim Data Set Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Sim Data Set Type</em>'.
   * @generated
   */
  msteps_SimDataSetType createmsteps_SimDataSetType();

  /**
   * Returns a new object of class '<em>msteps Observations Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Observations Type</em>'.
   * @generated
   */
  msteps_ObservationsType createmsteps_ObservationsType();

  /**
   * Returns a new object of class '<em>msteps Write To Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Write To Type</em>'.
   * @generated
   */
  msteps_WriteToType createmsteps_WriteToType();

  /**
   * Returns a new object of class '<em>msteps Timepoints Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Timepoints Type</em>'.
   * @generated
   */
  msteps_TimepointsType createmsteps_TimepointsType();

  /**
   * Returns a new object of class '<em>msteps Initial Value Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Initial Value Type</em>'.
   * @generated
   */
  msteps_InitialValueType createmsteps_InitialValueType();

  /**
   * Returns a new object of class '<em>msteps Estimation Operation Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>msteps Estimation Operation Type</em>'.
   * @generated
   */
  msteps_EstimationOperationType createmsteps_EstimationOperationType();

  /**
   * Returns a new object of class '<em>design Trial Design Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Trial Design Type</em>'.
   * @generated
   */
  design_TrialDesignType createdesign_TrialDesignType();

  /**
   * Returns a new object of class '<em>design Group Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Group Type</em>'.
   * @generated
   */
  design_GroupType createdesign_GroupType();

  /**
   * Returns a new object of class '<em>design Group Type Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Group Type Content</em>'.
   * @generated
   */
  design_GroupTypeContent createdesign_GroupTypeContent();

  /**
   * Returns a new object of class '<em>design Idividuals Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Idividuals Type</em>'.
   * @generated
   */
  design_IdividualsType createdesign_IdividualsType();

  /**
   * Returns a new object of class '<em>design Washout Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Washout Type</em>'.
   * @generated
   */
  design_WashoutType createdesign_WashoutType();

  /**
   * Returns a new object of class '<em>design Treatment Epoh Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Treatment Epoh Type</em>'.
   * @generated
   */
  design_TreatmentEpohType createdesign_TreatmentEpohType();

  /**
   * Returns a new object of class '<em>design Occasion Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Occasion Type</em>'.
   * @generated
   */
  design_OccasionType createdesign_OccasionType();

  /**
   * Returns a new object of class '<em>design Begin Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Begin Type</em>'.
   * @generated
   */
  design_BeginType createdesign_BeginType();

  /**
   * Returns a new object of class '<em>design End Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design End Type</em>'.
   * @generated
   */
  design_EndType createdesign_EndType();

  /**
   * Returns a new object of class '<em>design Start Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Start Type</em>'.
   * @generated
   */
  design_StartType createdesign_StartType();

  /**
   * Returns a new object of class '<em>design Treatment Ref Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Treatment Ref Type</em>'.
   * @generated
   */
  design_TreatmentRefType createdesign_TreatmentRefType();

  /**
   * Returns a new object of class '<em>design Treatment Epoch Ref Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Treatment Epoch Ref Type</em>'.
   * @generated
   */
  design_TreatmentEpochRefType createdesign_TreatmentEpochRefType();

  /**
   * Returns a new object of class '<em>design Treatment Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Treatment Type</em>'.
   * @generated
   */
  design_TreatmentType createdesign_TreatmentType();

  /**
   * Returns a new object of class '<em>design Dosing Regimen Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Dosing Regimen Type</em>'.
   * @generated
   */
  design_DosingRegimenType createdesign_DosingRegimenType();

  /**
   * Returns a new object of class '<em>design Infusion Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Infusion Type</em>'.
   * @generated
   */
  design_InfusionType createdesign_InfusionType();

  /**
   * Returns a new object of class '<em>design Duration Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Duration Type</em>'.
   * @generated
   */
  design_DurationType createdesign_DurationType();

  /**
   * Returns a new object of class '<em>design Bolus Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Bolus Type</em>'.
   * @generated
   */
  design_BolusType createdesign_BolusType();

  /**
   * Returns a new object of class '<em>design Dosing Times Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Dosing Times Type</em>'.
   * @generated
   */
  design_DosingTimesType createdesign_DosingTimesType();

  /**
   * Returns a new object of class '<em>design Dosing Times Type Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Dosing Times Type Content</em>'.
   * @generated
   */
  design_DosingTimesTypeContent createdesign_DosingTimesTypeContent();

  /**
   * Returns a new object of class '<em>design Steady State Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Steady State Type</em>'.
   * @generated
   */
  design_SteadyStateType createdesign_SteadyStateType();

  /**
   * Returns a new object of class '<em>design Dosing Start Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Dosing Start Type</em>'.
   * @generated
   */
  design_DosingStartType createdesign_DosingStartType();

  /**
   * Returns a new object of class '<em>design Dosing Period Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Dosing Period Type</em>'.
   * @generated
   */
  design_DosingPeriodType createdesign_DosingPeriodType();

  /**
   * Returns a new object of class '<em>design Dose Amount Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Dose Amount Type</em>'.
   * @generated
   */
  design_DoseAmountType createdesign_DoseAmountType();

  /**
   * Returns a new object of class '<em>design Dosing Variable Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Dosing Variable Type</em>'.
   * @generated
   */
  design_DosingVariableType createdesign_DosingVariableType();

  /**
   * Returns a new object of class '<em>design Dosing Variable Type Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Dosing Variable Type Content</em>'.
   * @generated
   */
  design_DosingVariableTypeContent createdesign_DosingVariableTypeContent();

  /**
   * Returns a new object of class '<em>design Amount Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Amount Type</em>'.
   * @generated
   */
  design_AmountType createdesign_AmountType();

  /**
   * Returns a new object of class '<em>design Target Var Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Target Var Type</em>'.
   * @generated
   */
  design_TargetVarType createdesign_TargetVarType();

  /**
   * Returns a new object of class '<em>design Dose Var Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>design Dose Var Type</em>'.
   * @generated
   */
  design_DoseVarType createdesign_DoseVarType();

  /**
   * Returns a new object of class '<em>uncert Distribution Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Distribution Type</em>'.
   * @generated
   */
  uncert_DistributionType createuncert_DistributionType();

  /**
   * Returns a new object of class '<em>uncert Distribution Type Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Distribution Type Content</em>'.
   * @generated
   */
  uncert_DistributionTypeContent createuncert_DistributionTypeContent();

  /**
   * Returns a new object of class '<em>uncert Normal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Normal</em>'.
   * @generated
   */
  uncert_Normal createuncert_Normal();

  /**
   * Returns a new object of class '<em>uncert Coefficient Of Variance</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Coefficient Of Variance</em>'.
   * @generated
   */
  uncert_CoefficientOfVariance createuncert_CoefficientOfVariance();

  /**
   * Returns a new object of class '<em>uncert Std Dev</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Std Dev</em>'.
   * @generated
   */
  uncert_StdDev createuncert_StdDev();

  /**
   * Returns a new object of class '<em>uncert Distribution Param Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Distribution Param Type</em>'.
   * @generated
   */
  uncert_DistributionParamType createuncert_DistributionParamType();

  /**
   * Returns a new object of class '<em>uncert Mean Param Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Mean Param Type</em>'.
   * @generated
   */
  uncert_MeanParamType createuncert_MeanParamType();

  /**
   * Returns a new object of class '<em>uncert Mean</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Mean</em>'.
   * @generated
   */
  uncert_Mean createuncert_Mean();

  /**
   * Returns a new object of class '<em>uncert Variance</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Variance</em>'.
   * @generated
   */
  uncert_Variance createuncert_Variance();

  /**
   * Returns a new object of class '<em>uncert PDF</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert PDF</em>'.
   * @generated
   */
  uncert_PDF createuncert_PDF();

  /**
   * Returns a new object of class '<em>uncert Parameter Definition Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Parameter Definition Type</em>'.
   * @generated
   */
  uncert_ParameterDefinitionType createuncert_ParameterDefinitionType();

  /**
   * Returns a new object of class '<em>uncert Poison</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Poison</em>'.
   * @generated
   */
  uncert_Poison createuncert_Poison();

  /**
   * Returns a new object of class '<em>uncert Rate</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Rate</em>'.
   * @generated
   */
  uncert_Rate createuncert_Rate();

  /**
   * Returns a new object of class '<em>uncert Student t</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Student t</em>'.
   * @generated
   */
  uncert_Student_t createuncert_Student_t();

  /**
   * Returns a new object of class '<em>uncert Degrees Of Freedom</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Degrees Of Freedom</em>'.
   * @generated
   */
  uncertDegreesOfFreedom createuncertDegreesOfFreedom();

  /**
   * Returns a new object of class '<em>uncert Scale</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Scale</em>'.
   * @generated
   */
  uncert_Scale createuncert_Scale();

  /**
   * Returns a new object of class '<em>uncert Location</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Location</em>'.
   * @generated
   */
  uncert_Location createuncert_Location();

  /**
   * Returns a new object of class '<em>uncert Uniform</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>uncert Uniform</em>'.
   * @generated
   */
  uncert_Uniform createuncert_Uniform();

  /**
   * Returns a new object of class '<em>Math Equation Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Equation Type</em>'.
   * @generated
   */
  Math_EquationType createMath_EquationType();

  /**
   * Returns a new object of class '<em>Math Equation Type Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Equation Type Content</em>'.
   * @generated
   */
  Math_EquationTypeContent createMath_EquationTypeContent();

  /**
   * Returns a new object of class '<em>Math Scalar Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Scalar Type</em>'.
   * @generated
   */
  Math_ScalarType createMath_ScalarType();

  /**
   * Returns a new object of class '<em>Math Var Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Var Type</em>'.
   * @generated
   */
  Math_VarType createMath_VarType();

  /**
   * Returns a new object of class '<em>Math Binop Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Binop Type</em>'.
   * @generated
   */
  Math_BinopType createMath_BinopType();

  /**
   * Returns a new object of class '<em>Math Uniop Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Uniop Type</em>'.
   * @generated
   */
  Math_UniopType createMath_UniopType();

  /**
   * Returns a new object of class '<em>Math Expr Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Expr Type</em>'.
   * @generated
   */
  Math_ExprType createMath_ExprType();

  /**
   * Returns a new object of class '<em>Math Constant Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Constant Type</em>'.
   * @generated
   */
  Math_ConstantType createMath_ConstantType();

  /**
   * Returns a new object of class '<em>Math Function Call Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Function Call Type</em>'.
   * @generated
   */
  Math_FunctionCallType createMath_FunctionCallType();

  /**
   * Returns a new object of class '<em>Math Function Argument Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Function Argument Type</em>'.
   * @generated
   */
  Math_FunctionArgumentType createMath_FunctionArgumentType();

  /**
   * Returns a new object of class '<em>Math Func Expr Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Func Expr Type</em>'.
   * @generated
   */
  Math_FuncExprType createMath_FuncExprType();

  /**
   * Returns a new object of class '<em>Math Piecewise Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Piecewise Type</em>'.
   * @generated
   */
  Math_PiecewiseType createMath_PiecewiseType();

  /**
   * Returns a new object of class '<em>Math Piece Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Piece Type</em>'.
   * @generated
   */
  Math_PieceType createMath_PieceType();

  /**
   * Returns a new object of class '<em>Math Condition Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Condition Type</em>'.
   * @generated
   */
  Math_ConditionType createMath_ConditionType();

  /**
   * Returns a new object of class '<em>Math Logic Base Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Logic Base Type</em>'.
   * @generated
   */
  Math_LogicBaseType createMath_LogicBaseType();

  /**
   * Returns a new object of class '<em>Math Otherwise</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Otherwise</em>'.
   * @generated
   */
  Math_Otherwise createMath_Otherwise();

  /**
   * Returns a new object of class '<em>Math Logic Expr Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Logic Expr Type</em>'.
   * @generated
   */
  Math_LogicExprType createMath_LogicExprType();

  /**
   * Returns a new object of class '<em>Math String Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math String Type</em>'.
   * @generated
   */
  Math_StringType createMath_StringType();

  /**
   * Returns a new object of class '<em>Math TRUE</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math TRUE</em>'.
   * @generated
   */
  Math_TRUE createMath_TRUE();

  /**
   * Returns a new object of class '<em>Math FALSE</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math FALSE</em>'.
   * @generated
   */
  Math_FALSE createMath_FALSE();

  /**
   * Returns a new object of class '<em>Math Logic Binop Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Logic Binop Type</em>'.
   * @generated
   */
  Math_LogicBinopType createMath_LogicBinopType();

  /**
   * Returns a new object of class '<em>Math Logic Uniop Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Logic Uniop Type</em>'.
   * @generated
   */
  Math_LogicUniopType createMath_LogicUniopType();

  /**
   * Returns a new object of class '<em>Math Binop Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Binop Name</em>'.
   * @generated
   */
  Math_BinopName createMath_BinopName();

  /**
   * Returns a new object of class '<em>Math Uniop Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Uniop Name</em>'.
   * @generated
   */
  Math_UniopName createMath_UniopName();

  /**
   * Returns a new object of class '<em>Math Logic Uniop Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Logic Uniop Name</em>'.
   * @generated
   */
  Math_LogicUniopName createMath_LogicUniopName();

  /**
   * Returns a new object of class '<em>Math Logic Binop Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Logic Binop Name</em>'.
   * @generated
   */
  Math_LogicBinopName createMath_LogicBinopName();

  /**
   * Returns a new object of class '<em>Math Constant Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Constant Name</em>'.
   * @generated
   */
  Math_ConstantName createMath_ConstantName();

  /**
   * Returns a new object of class '<em>Math symb Id</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math symb Id</em>'.
   * @generated
   */
  Math_symbId createMath_symbId();

  /**
   * Returns a new object of class '<em>Math id</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math id</em>'.
   * @generated
   */
  Math_id createMath_id();

  /**
   * Returns a new object of class '<em>Math block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math block</em>'.
   * @generated
   */
  Math_block createMath_block();

  /**
   * Returns a new object of class '<em>Math Estimation Operation Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Math Estimation Operation Type</em>'.
   * @generated
   */
  Math_EstimationOperationType createMath_EstimationOperationType();

  /**
   * Returns a new object of class '<em>ct Replicates Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Replicates Type</em>'.
   * @generated
   */
  ct_ReplicatesType createct_ReplicatesType();

  /**
   * Returns a new object of class '<em>ct Annotation Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Annotation Type</em>'.
   * @generated
   */
  ct_AnnotationType createct_AnnotationType();

  /**
   * Returns a new object of class '<em>ct Rhs</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Rhs</em>'.
   * @generated
   */
  ct_Rhs createct_Rhs();

  /**
   * Returns a new object of class '<em>ct Data Set Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Data Set Type</em>'.
   * @generated
   */
  ct_DataSetType createct_DataSetType();

  /**
   * Returns a new object of class '<em>ct Definition Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Definition Type</em>'.
   * @generated
   */
  ct_DefinitionType createct_DefinitionType();

  /**
   * Returns a new object of class '<em>ct Definition Rhs Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Definition Rhs Type</em>'.
   * @generated
   */
  ct_DefinitionRhsType createct_DefinitionRhsType();

  /**
   * Returns a new object of class '<em>ct Column Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Column Type</em>'.
   * @generated
   */
  ct_ColumnType createct_ColumnType();

  /**
   * Returns a new object of class '<em>ct External Source Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct External Source Type</em>'.
   * @generated
   */
  ct_ExternalSourceType createct_ExternalSourceType();

  /**
   * Returns a new object of class '<em>ct Row Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Row Type</em>'.
   * @generated
   */
  ct_RowType createct_RowType();

  /**
   * Returns a new object of class '<em>ct Row Type Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Row Type Content</em>'.
   * @generated
   */
  ct_RowTypeContent createct_RowTypeContent();

  /**
   * Returns a new object of class '<em>ct Null Cell Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Null Cell Type</em>'.
   * @generated
   */
  ct_NullCellType createct_NullCellType();

  /**
   * Returns a new object of class '<em>ct Cell Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Cell Type</em>'.
   * @generated
   */
  ct_CellType createct_CellType();

  /**
   * Returns a new object of class '<em>ct Internal Source Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Internal Source Type</em>'.
   * @generated
   */
  ct_InternalSourceType createct_InternalSourceType();

  /**
   * Returns a new object of class '<em>ct Vector Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Vector Type</em>'.
   * @generated
   */
  ct_VectorType createct_VectorType();

  /**
   * Returns a new object of class '<em>ct Sequence Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Sequence Type</em>'.
   * @generated
   */
  ct_SequenceType createct_SequenceType();

  /**
   * Returns a new object of class '<em>ct Variable Definition Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Variable Definition Type</em>'.
   * @generated
   */
  ct_VariableDefinitionType createct_VariableDefinitionType();

  /**
   * Returns a new object of class '<em>ct Restriction Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Restriction Type</em>'.
   * @generated
   */
  ct_RestrictionType createct_RestrictionType();

  /**
   * Returns a new object of class '<em>ct Scalar Rhs</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Scalar Rhs</em>'.
   * @generated
   */
  ct_ScalarRhs createct_ScalarRhs();

  /**
   * Returns a new object of class '<em>ct independent Var</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct independent Var</em>'.
   * @generated
   */
  ct_independentVar createct_independentVar();

  /**
   * Returns a new object of class '<em>ct level Id</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct level Id</em>'.
   * @generated
   */
  ct_levelId createct_levelId();

  /**
   * Returns a new object of class '<em>ct name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct name</em>'.
   * @generated
   */
  ct_name createct_name();

  /**
   * Returns a new object of class '<em>ct symb Id</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct symb Id</em>'.
   * @generated
   */
  ct_symbId createct_symbId();

  /**
   * Returns a new object of class '<em>ct id</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct id</em>'.
   * @generated
   */
  ct_id createct_id();

  /**
   * Returns a new object of class '<em>ct symbol Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct symbol Type</em>'.
   * @generated
   */
  ct_symbolType createct_symbolType();

  /**
   * Returns a new object of class '<em>ct block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct block</em>'.
   * @generated
   */
  ct_block createct_block();

  /**
   * Returns a new object of class '<em>ct written Version</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct written Version</em>'.
   * @generated
   */
  ct_writtenVersion createct_writtenVersion();

  /**
   * Returns a new object of class '<em>ct Decimal Value</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Decimal Value</em>'.
   * @generated
   */
  ct_Decimal_Value createct_Decimal_Value();

  /**
   * Returns a new object of class '<em>ct String Value</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct String Value</em>'.
   * @generated
   */
  ct_String_Value createct_String_Value();

  /**
   * Returns a new object of class '<em>ct cat Id</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct cat Id</em>'.
   * @generated
   */
  ct_catId createct_catId();

  /**
   * Returns a new object of class '<em>ct Symbol Type Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>ct Symbol Type Type</em>'.
   * @generated
   */
  ct_SymbolTypeType createct_SymbolTypeType();

  /**
   * Returns a new object of class '<em>XS xmlns</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XS xmlns</em>'.
   * @generated
   */
  XS_xmlns createXS_xmlns();

  /**
   * Returns a new object of class '<em>Mml Func Parameter Definition Typ</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mml Func Parameter Definition Typ</em>'.
   * @generated
   */
  Mml_FuncParameterDefinitionTyp createMml_FuncParameterDefinitionTyp();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  PharmMLPackage getPharmMLPackage();

} //PharmMLFactory

/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PharmMLFactoryImpl extends EFactoryImpl implements PharmMLFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static PharmMLFactory init()
  {
    try
    {
      PharmMLFactory thePharmMLFactory = (PharmMLFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.ddmore.eu/pml/PharmML"); 
      if (thePharmMLFactory != null)
      {
        return thePharmMLFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new PharmMLFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PharmMLFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case PharmMLPackage.MML_PHARM_ML: return createMml_PharmML();
      case PharmMLPackage.MML_PHARMA_ML_CONTENT: return createMml_PharmaMLContent();
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE: return createMml_SymbolDefinitionType();
      case PharmMLPackage.MML_FUNCTION_DEFINITION_TYPE: return createMml_FunctionDefinitionType();
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYPE: return createMml_FuncParameterDefinitionType();
      case PharmMLPackage.MDEF_MODEL_DEFINITION_TYPE: return createmdef_ModelDefinitionType();
      case PharmMLPackage.MDEF_PARAMETER_TYPE_NAME: return createmdef_ParameterTypeName();
      case PharmMLPackage.MDEF_STRUCTURAL_MODEL_TYPE: return createmdef_StructuralModelType();
      case PharmMLPackage.MDEF_OBSERVATION_MODEL_TYPE: return createmdef_ObservationModelType();
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE: return createmdef_ContinuousObsModelType();
      case PharmMLPackage.MDEF_ERROR_MODEL: return createmdef_ErrorModel();
      case PharmMLPackage.MDEF_RANDOM_EFFECT_TYPE: return createmdef_RandomEffectType();
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE: return createmdef_ParameterModelType();
      case PharmMLPackage.MDEF_CORRELATION_TYPE: return createmdef_CorrelationType();
      case PharmMLPackage.MDEF_PARAM1_VAR_TYPE: return createmdef_Param1VarType();
      case PharmMLPackage.MDEF_PARAM2_VAR_TYPE: return createmdef_Param2VarType();
      case PharmMLPackage.MDEF_COVARIANCE_TYPE: return createmdefCovarianceType();
      case PharmMLPackage.MDEF_CORRELATION_COEFFICIENT_TYPE: return createmdef_CorrelationCoefficientType();
      case PharmMLPackage.MDEF_COVARIATE_MODEL_TYPE: return createmdef_CovariateModelType();
      case PharmMLPackage.MDEF_PARAMETER_TYPE: return createmdef_ParameterType();
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE: return createmdef_CovariateVariabilityType();
      case PharmMLPackage.MDEF_TRANSFORMATION: return createmdef_transformation();
      case PharmMLPackage.MDEF_CATEGORICAL_TYPE: return createmdef_CategoricalType();
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE: return createmdef_ContinuousCovariateType();
      case PharmMLPackage.MDEF_TRANSFORMATION_TYPE: return createmdef_TransformationType();
      case PharmMLPackage.MDEF_VARIABILITY_LEVEL_DEFN_TYPE: return createmdef_VariabilityLevelDefnType();
      case PharmMLPackage.MDEF_IMPORT_TYPE: return createmdef_ImportType();
      case PharmMLPackage.MDEF_IMPORT_LINK_TYPE: return createmdef_ImportLinkType();
      case PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE: return createmdef_ImportResourceTypeType();
      case PharmMLPackage.MDEF_COVARIATE_TYPE: return createmdef_CovariateType();
      case PharmMLPackage.MDEF_FIXED_EFFECT_TYPE: return createmdef_FixedEffectType();
      case PharmMLPackage.MDEF_CATEGORY_TYPE: return createmdef_CategoryType();
      case PharmMLPackage.MDEF_PROBABILITY_TYPE: return createmdef_ProbabilityType();
      case PharmMLPackage.MDEF_PARAMETER_RANDOM_EFFECT_TYPE: return createmdef_ParameterRandomEffectType();
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE: return createmdef_InitialConditionType();
      case PharmMLPackage.MSTEPS_MODELING_STEPS: return createmsteps_ModelingSteps();
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT: return createmsteps_ModelingStepsContent();
      case PharmMLPackage.MSTEPS_STEP_DEPENDENCIES_TYPE: return createmsteps_StepDependenciesType();
      case PharmMLPackage.MSTEPS_STEP_TYPE: return createmsteps_StepType();
      case PharmMLPackage.MSTEPS_DEPENDANT_STEP_TYPE: return createmsteps_DependantStepType();
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP: return createmsteps_EstimationStep();
      case PharmMLPackage.MSTEPS_PARAMETERS_TO_ESTIMATE_TYPE: return createmsteps_ParametersToEstimateType();
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE: return createmSteps_VariableEstimateType();
      case PharmMLPackage.MSTEPS_FIXED: return createmSteps_fixed();
      case PharmMLPackage.MSTEPS_UPPER_BOUND_TYPE: return createmSteps_UpperBoundType();
      case PharmMLPackage.MSTEPS_LOWER_BOUND_TYPE: return createmSteps_LowerBoundType();
      case PharmMLPackage.MSTEPS_INITIAL_ESTIMATE_TYPE: return createmSteps_InitialEstimateType();
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE: return createmsteps_ObjectiveDataSetType();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE: return createmsteps_ColumnMappingType();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT: return createmsteps_ColumnMappingTypeContent();
      case PharmMLPackage.MSTEPS_OUTPUT_TYPE: return createmsteps_OutputType();
      case PharmMLPackage.MSTEPS_ASSIGN_TYPE: return createmsteps_AssignType();
      case PharmMLPackage.MSTEPS_TARGET_VAR_TYPE: return createmsteps_TargetVarType();
      case PharmMLPackage.MSTEPS_USE_VARIABILITY_LEVEL: return createmsteps_UseVariabilityLevel();
      case PharmMLPackage.MSTEPS_USE_VARIABILITY_NODE: return createmsteps_UseVariabilityNode();
      case PharmMLPackage.MSTEPS_SIMULATION_STEP: return createmsteps_SimulationStep();
      case PharmMLPackage.MSTEPS_SIM_DATA_SET_TYPE: return createmsteps_SimDataSetType();
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE: return createmsteps_ObservationsType();
      case PharmMLPackage.MSTEPS_WRITE_TO_TYPE: return createmsteps_WriteToType();
      case PharmMLPackage.MSTEPS_TIMEPOINTS_TYPE: return createmsteps_TimepointsType();
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE: return createmsteps_InitialValueType();
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE: return createmsteps_EstimationOperationType();
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE: return createdesign_TrialDesignType();
      case PharmMLPackage.DESIGN_GROUP_TYPE: return createdesign_GroupType();
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT: return createdesign_GroupTypeContent();
      case PharmMLPackage.DESIGN_IDIVIDUALS_TYPE: return createdesign_IdividualsType();
      case PharmMLPackage.DESIGN_WASHOUT_TYPE: return createdesign_WashoutType();
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE: return createdesign_TreatmentEpohType();
      case PharmMLPackage.DESIGN_OCCASION_TYPE: return createdesign_OccasionType();
      case PharmMLPackage.DESIGN_BEGIN_TYPE: return createdesign_BeginType();
      case PharmMLPackage.DESIGN_END_TYPE: return createdesign_EndType();
      case PharmMLPackage.DESIGN_START_TYPE: return createdesign_StartType();
      case PharmMLPackage.DESIGN_TREATMENT_REF_TYPE: return createdesign_TreatmentRefType();
      case PharmMLPackage.DESIGN_TREATMENT_EPOCH_REF_TYPE: return createdesign_TreatmentEpochRefType();
      case PharmMLPackage.DESIGN_TREATMENT_TYPE: return createdesign_TreatmentType();
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE: return createdesign_DosingRegimenType();
      case PharmMLPackage.DESIGN_INFUSION_TYPE: return createdesign_InfusionType();
      case PharmMLPackage.DESIGN_DURATION_TYPE: return createdesign_DurationType();
      case PharmMLPackage.DESIGN_BOLUS_TYPE: return createdesign_BolusType();
      case PharmMLPackage.DESIGN_DOSING_TIMES_TYPE: return createdesign_DosingTimesType();
      case PharmMLPackage.DESIGN_DOSING_TIMES_TYPE_CONTENT: return createdesign_DosingTimesTypeContent();
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE: return createdesign_SteadyStateType();
      case PharmMLPackage.DESIGN_DOSING_START_TYPE: return createdesign_DosingStartType();
      case PharmMLPackage.DESIGN_DOSING_PERIOD_TYPE: return createdesign_DosingPeriodType();
      case PharmMLPackage.DESIGN_DOSE_AMOUNT_TYPE: return createdesign_DoseAmountType();
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE: return createdesign_DosingVariableType();
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT: return createdesign_DosingVariableTypeContent();
      case PharmMLPackage.DESIGN_AMOUNT_TYPE: return createdesign_AmountType();
      case PharmMLPackage.DESIGN_TARGET_VAR_TYPE: return createdesign_TargetVarType();
      case PharmMLPackage.DESIGN_DOSE_VAR_TYPE: return createdesign_DoseVarType();
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE: return createuncert_DistributionType();
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT: return createuncert_DistributionTypeContent();
      case PharmMLPackage.UNCERT_NORMAL: return createuncert_Normal();
      case PharmMLPackage.UNCERT_COEFFICIENT_OF_VARIANCE: return createuncert_CoefficientOfVariance();
      case PharmMLPackage.UNCERT_STD_DEV: return createuncert_StdDev();
      case PharmMLPackage.UNCERT_DISTRIBUTION_PARAM_TYPE: return createuncert_DistributionParamType();
      case PharmMLPackage.UNCERT_MEAN_PARAM_TYPE: return createuncert_MeanParamType();
      case PharmMLPackage.UNCERT_MEAN: return createuncert_Mean();
      case PharmMLPackage.UNCERT_VARIANCE: return createuncert_Variance();
      case PharmMLPackage.UNCERT_PDF: return createuncert_PDF();
      case PharmMLPackage.UNCERT_PARAMETER_DEFINITION_TYPE: return createuncert_ParameterDefinitionType();
      case PharmMLPackage.UNCERT_POISON: return createuncert_Poison();
      case PharmMLPackage.UNCERT_RATE: return createuncert_Rate();
      case PharmMLPackage.UNCERT_STUDENT_T: return createuncert_Student_t();
      case PharmMLPackage.UNCERT_DEGREES_OF_FREEDOM: return createuncertDegreesOfFreedom();
      case PharmMLPackage.UNCERT_SCALE: return createuncert_Scale();
      case PharmMLPackage.UNCERT_LOCATION: return createuncert_Location();
      case PharmMLPackage.UNCERT_UNIFORM: return createuncert_Uniform();
      case PharmMLPackage.MATH_EQUATION_TYPE: return createMath_EquationType();
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT: return createMath_EquationTypeContent();
      case PharmMLPackage.MATH_SCALAR_TYPE: return createMath_ScalarType();
      case PharmMLPackage.MATH_VAR_TYPE: return createMath_VarType();
      case PharmMLPackage.MATH_BINOP_TYPE: return createMath_BinopType();
      case PharmMLPackage.MATH_UNIOP_TYPE: return createMath_UniopType();
      case PharmMLPackage.MATH_EXPR_TYPE: return createMath_ExprType();
      case PharmMLPackage.MATH_CONSTANT_TYPE: return createMath_ConstantType();
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE: return createMath_FunctionCallType();
      case PharmMLPackage.MATH_FUNCTION_ARGUMENT_TYPE: return createMath_FunctionArgumentType();
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE: return createMath_FuncExprType();
      case PharmMLPackage.MATH_PIECEWISE_TYPE: return createMath_PiecewiseType();
      case PharmMLPackage.MATH_PIECE_TYPE: return createMath_PieceType();
      case PharmMLPackage.MATH_CONDITION_TYPE: return createMath_ConditionType();
      case PharmMLPackage.MATH_LOGIC_BASE_TYPE: return createMath_LogicBaseType();
      case PharmMLPackage.MATH_OTHERWISE: return createMath_Otherwise();
      case PharmMLPackage.MATH_LOGIC_EXPR_TYPE: return createMath_LogicExprType();
      case PharmMLPackage.MATH_STRING_TYPE: return createMath_StringType();
      case PharmMLPackage.MATH_TRUE: return createMath_TRUE();
      case PharmMLPackage.MATH_FALSE: return createMath_FALSE();
      case PharmMLPackage.MATH_LOGIC_BINOP_TYPE: return createMath_LogicBinopType();
      case PharmMLPackage.MATH_LOGIC_UNIOP_TYPE: return createMath_LogicUniopType();
      case PharmMLPackage.MATH_BINOP_NAME: return createMath_BinopName();
      case PharmMLPackage.MATH_UNIOP_NAME: return createMath_UniopName();
      case PharmMLPackage.MATH_LOGIC_UNIOP_NAME: return createMath_LogicUniopName();
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME: return createMath_LogicBinopName();
      case PharmMLPackage.MATH_CONSTANT_NAME: return createMath_ConstantName();
      case PharmMLPackage.MATH_SYMB_ID: return createMath_symbId();
      case PharmMLPackage.MATH_ID: return createMath_id();
      case PharmMLPackage.MATH_BLOCK: return createMath_block();
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE: return createMath_EstimationOperationType();
      case PharmMLPackage.CT_REPLICATES_TYPE: return createct_ReplicatesType();
      case PharmMLPackage.CT_ANNOTATION_TYPE: return createct_AnnotationType();
      case PharmMLPackage.CT_RHS: return createct_Rhs();
      case PharmMLPackage.CT_DATA_SET_TYPE: return createct_DataSetType();
      case PharmMLPackage.CT_DEFINITION_TYPE: return createct_DefinitionType();
      case PharmMLPackage.CT_DEFINITION_RHS_TYPE: return createct_DefinitionRhsType();
      case PharmMLPackage.CT_COLUMN_TYPE: return createct_ColumnType();
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE: return createct_ExternalSourceType();
      case PharmMLPackage.CT_ROW_TYPE: return createct_RowType();
      case PharmMLPackage.CT_ROW_TYPE_CONTENT: return createct_RowTypeContent();
      case PharmMLPackage.CT_NULL_CELL_TYPE: return createct_NullCellType();
      case PharmMLPackage.CT_CELL_TYPE: return createct_CellType();
      case PharmMLPackage.CT_INTERNAL_SOURCE_TYPE: return createct_InternalSourceType();
      case PharmMLPackage.CT_VECTOR_TYPE: return createct_VectorType();
      case PharmMLPackage.CT_SEQUENCE_TYPE: return createct_SequenceType();
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE: return createct_VariableDefinitionType();
      case PharmMLPackage.CT_RESTRICTION_TYPE: return createct_RestrictionType();
      case PharmMLPackage.CT_SCALAR_RHS: return createct_ScalarRhs();
      case PharmMLPackage.CT_INDEPENDENT_VAR: return createct_independentVar();
      case PharmMLPackage.CT_LEVEL_ID: return createct_levelId();
      case PharmMLPackage.CT_NAME: return createct_name();
      case PharmMLPackage.CT_SYMB_ID: return createct_symbId();
      case PharmMLPackage.CT_ID: return createct_id();
      case PharmMLPackage.CT_SYMBOL_TYPE: return createct_symbolType();
      case PharmMLPackage.CT_BLOCK: return createct_block();
      case PharmMLPackage.CT_WRITTEN_VERSION: return createct_writtenVersion();
      case PharmMLPackage.CT_DECIMAL_VALUE: return createct_Decimal_Value();
      case PharmMLPackage.CT_STRING_VALUE: return createct_String_Value();
      case PharmMLPackage.CT_CAT_ID: return createct_catId();
      case PharmMLPackage.CT_SYMBOL_TYPE_TYPE: return createct_SymbolTypeType();
      case PharmMLPackage.XS_XMLNS: return createXS_xmlns();
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP: return createMml_FuncParameterDefinitionTyp();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mml_PharmML createMml_PharmML()
  {
    Mml_PharmMLImpl mml_PharmML = new Mml_PharmMLImpl();
    return mml_PharmML;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mml_PharmaMLContent createMml_PharmaMLContent()
  {
    Mml_PharmaMLContentImpl mml_PharmaMLContent = new Mml_PharmaMLContentImpl();
    return mml_PharmaMLContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mml_SymbolDefinitionType createMml_SymbolDefinitionType()
  {
    Mml_SymbolDefinitionTypeImpl mml_SymbolDefinitionType = new Mml_SymbolDefinitionTypeImpl();
    return mml_SymbolDefinitionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mml_FunctionDefinitionType createMml_FunctionDefinitionType()
  {
    Mml_FunctionDefinitionTypeImpl mml_FunctionDefinitionType = new Mml_FunctionDefinitionTypeImpl();
    return mml_FunctionDefinitionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mml_FuncParameterDefinitionType createMml_FuncParameterDefinitionType()
  {
    Mml_FuncParameterDefinitionTypeImpl mml_FuncParameterDefinitionType = new Mml_FuncParameterDefinitionTypeImpl();
    return mml_FuncParameterDefinitionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ModelDefinitionType createmdef_ModelDefinitionType()
  {
    mdef_ModelDefinitionTypeImpl mdef_ModelDefinitionType = new mdef_ModelDefinitionTypeImpl();
    return mdef_ModelDefinitionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ParameterTypeName createmdef_ParameterTypeName()
  {
    mdef_ParameterTypeNameImpl mdef_ParameterTypeName = new mdef_ParameterTypeNameImpl();
    return mdef_ParameterTypeName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_StructuralModelType createmdef_StructuralModelType()
  {
    mdef_StructuralModelTypeImpl mdef_StructuralModelType = new mdef_StructuralModelTypeImpl();
    return mdef_StructuralModelType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ObservationModelType createmdef_ObservationModelType()
  {
    mdef_ObservationModelTypeImpl mdef_ObservationModelType = new mdef_ObservationModelTypeImpl();
    return mdef_ObservationModelType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ContinuousObsModelType createmdef_ContinuousObsModelType()
  {
    mdef_ContinuousObsModelTypeImpl mdef_ContinuousObsModelType = new mdef_ContinuousObsModelTypeImpl();
    return mdef_ContinuousObsModelType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ErrorModel createmdef_ErrorModel()
  {
    mdef_ErrorModelImpl mdef_ErrorModel = new mdef_ErrorModelImpl();
    return mdef_ErrorModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_RandomEffectType createmdef_RandomEffectType()
  {
    mdef_RandomEffectTypeImpl mdef_RandomEffectType = new mdef_RandomEffectTypeImpl();
    return mdef_RandomEffectType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ParameterModelType createmdef_ParameterModelType()
  {
    mdef_ParameterModelTypeImpl mdef_ParameterModelType = new mdef_ParameterModelTypeImpl();
    return mdef_ParameterModelType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CorrelationType createmdef_CorrelationType()
  {
    mdef_CorrelationTypeImpl mdef_CorrelationType = new mdef_CorrelationTypeImpl();
    return mdef_CorrelationType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_Param1VarType createmdef_Param1VarType()
  {
    mdef_Param1VarTypeImpl mdef_Param1VarType = new mdef_Param1VarTypeImpl();
    return mdef_Param1VarType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_Param2VarType createmdef_Param2VarType()
  {
    mdef_Param2VarTypeImpl mdef_Param2VarType = new mdef_Param2VarTypeImpl();
    return mdef_Param2VarType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdefCovarianceType createmdefCovarianceType()
  {
    mdefCovarianceTypeImpl mdefCovarianceType = new mdefCovarianceTypeImpl();
    return mdefCovarianceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CorrelationCoefficientType createmdef_CorrelationCoefficientType()
  {
    mdef_CorrelationCoefficientTypeImpl mdef_CorrelationCoefficientType = new mdef_CorrelationCoefficientTypeImpl();
    return mdef_CorrelationCoefficientType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CovariateModelType createmdef_CovariateModelType()
  {
    mdef_CovariateModelTypeImpl mdef_CovariateModelType = new mdef_CovariateModelTypeImpl();
    return mdef_CovariateModelType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ParameterType createmdef_ParameterType()
  {
    mdef_ParameterTypeImpl mdef_ParameterType = new mdef_ParameterTypeImpl();
    return mdef_ParameterType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CovariateVariabilityType createmdef_CovariateVariabilityType()
  {
    mdef_CovariateVariabilityTypeImpl mdef_CovariateVariabilityType = new mdef_CovariateVariabilityTypeImpl();
    return mdef_CovariateVariabilityType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_transformation createmdef_transformation()
  {
    mdef_transformationImpl mdef_transformation = new mdef_transformationImpl();
    return mdef_transformation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CategoricalType createmdef_CategoricalType()
  {
    mdef_CategoricalTypeImpl mdef_CategoricalType = new mdef_CategoricalTypeImpl();
    return mdef_CategoricalType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ContinuousCovariateType createmdef_ContinuousCovariateType()
  {
    mdef_ContinuousCovariateTypeImpl mdef_ContinuousCovariateType = new mdef_ContinuousCovariateTypeImpl();
    return mdef_ContinuousCovariateType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_TransformationType createmdef_TransformationType()
  {
    mdef_TransformationTypeImpl mdef_TransformationType = new mdef_TransformationTypeImpl();
    return mdef_TransformationType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_VariabilityLevelDefnType createmdef_VariabilityLevelDefnType()
  {
    mdef_VariabilityLevelDefnTypeImpl mdef_VariabilityLevelDefnType = new mdef_VariabilityLevelDefnTypeImpl();
    return mdef_VariabilityLevelDefnType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ImportType createmdef_ImportType()
  {
    mdef_ImportTypeImpl mdef_ImportType = new mdef_ImportTypeImpl();
    return mdef_ImportType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ImportLinkType createmdef_ImportLinkType()
  {
    mdef_ImportLinkTypeImpl mdef_ImportLinkType = new mdef_ImportLinkTypeImpl();
    return mdef_ImportLinkType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ImportResourceTypeType createmdef_ImportResourceTypeType()
  {
    mdef_ImportResourceTypeTypeImpl mdef_ImportResourceTypeType = new mdef_ImportResourceTypeTypeImpl();
    return mdef_ImportResourceTypeType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CovariateType createmdef_CovariateType()
  {
    mdef_CovariateTypeImpl mdef_CovariateType = new mdef_CovariateTypeImpl();
    return mdef_CovariateType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_FixedEffectType createmdef_FixedEffectType()
  {
    mdef_FixedEffectTypeImpl mdef_FixedEffectType = new mdef_FixedEffectTypeImpl();
    return mdef_FixedEffectType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CategoryType createmdef_CategoryType()
  {
    mdef_CategoryTypeImpl mdef_CategoryType = new mdef_CategoryTypeImpl();
    return mdef_CategoryType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ProbabilityType createmdef_ProbabilityType()
  {
    mdef_ProbabilityTypeImpl mdef_ProbabilityType = new mdef_ProbabilityTypeImpl();
    return mdef_ProbabilityType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ParameterRandomEffectType createmdef_ParameterRandomEffectType()
  {
    mdef_ParameterRandomEffectTypeImpl mdef_ParameterRandomEffectType = new mdef_ParameterRandomEffectTypeImpl();
    return mdef_ParameterRandomEffectType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_InitialConditionType createmdef_InitialConditionType()
  {
    mdef_InitialConditionTypeImpl mdef_InitialConditionType = new mdef_InitialConditionTypeImpl();
    return mdef_InitialConditionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ModelingSteps createmsteps_ModelingSteps()
  {
    msteps_ModelingStepsImpl msteps_ModelingSteps = new msteps_ModelingStepsImpl();
    return msteps_ModelingSteps;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ModelingStepsContent createmsteps_ModelingStepsContent()
  {
    msteps_ModelingStepsContentImpl msteps_ModelingStepsContent = new msteps_ModelingStepsContentImpl();
    return msteps_ModelingStepsContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_StepDependenciesType createmsteps_StepDependenciesType()
  {
    msteps_StepDependenciesTypeImpl msteps_StepDependenciesType = new msteps_StepDependenciesTypeImpl();
    return msteps_StepDependenciesType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_StepType createmsteps_StepType()
  {
    msteps_StepTypeImpl msteps_StepType = new msteps_StepTypeImpl();
    return msteps_StepType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_DependantStepType createmsteps_DependantStepType()
  {
    msteps_DependantStepTypeImpl msteps_DependantStepType = new msteps_DependantStepTypeImpl();
    return msteps_DependantStepType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_EstimationStep createmsteps_EstimationStep()
  {
    msteps_EstimationStepImpl msteps_EstimationStep = new msteps_EstimationStepImpl();
    return msteps_EstimationStep;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ParametersToEstimateType createmsteps_ParametersToEstimateType()
  {
    msteps_ParametersToEstimateTypeImpl msteps_ParametersToEstimateType = new msteps_ParametersToEstimateTypeImpl();
    return msteps_ParametersToEstimateType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mSteps_VariableEstimateType createmSteps_VariableEstimateType()
  {
    mSteps_VariableEstimateTypeImpl mSteps_VariableEstimateType = new mSteps_VariableEstimateTypeImpl();
    return mSteps_VariableEstimateType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mSteps_fixed createmSteps_fixed()
  {
    mSteps_fixedImpl mSteps_fixed = new mSteps_fixedImpl();
    return mSteps_fixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mSteps_UpperBoundType createmSteps_UpperBoundType()
  {
    mSteps_UpperBoundTypeImpl mSteps_UpperBoundType = new mSteps_UpperBoundTypeImpl();
    return mSteps_UpperBoundType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mSteps_LowerBoundType createmSteps_LowerBoundType()
  {
    mSteps_LowerBoundTypeImpl mSteps_LowerBoundType = new mSteps_LowerBoundTypeImpl();
    return mSteps_LowerBoundType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mSteps_InitialEstimateType createmSteps_InitialEstimateType()
  {
    mSteps_InitialEstimateTypeImpl mSteps_InitialEstimateType = new mSteps_InitialEstimateTypeImpl();
    return mSteps_InitialEstimateType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ObjectiveDataSetType createmsteps_ObjectiveDataSetType()
  {
    msteps_ObjectiveDataSetTypeImpl msteps_ObjectiveDataSetType = new msteps_ObjectiveDataSetTypeImpl();
    return msteps_ObjectiveDataSetType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ColumnMappingType createmsteps_ColumnMappingType()
  {
    msteps_ColumnMappingTypeImpl msteps_ColumnMappingType = new msteps_ColumnMappingTypeImpl();
    return msteps_ColumnMappingType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ColumnMappingTypeContent createmsteps_ColumnMappingTypeContent()
  {
    msteps_ColumnMappingTypeContentImpl msteps_ColumnMappingTypeContent = new msteps_ColumnMappingTypeContentImpl();
    return msteps_ColumnMappingTypeContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_OutputType createmsteps_OutputType()
  {
    msteps_OutputTypeImpl msteps_OutputType = new msteps_OutputTypeImpl();
    return msteps_OutputType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_AssignType createmsteps_AssignType()
  {
    msteps_AssignTypeImpl msteps_AssignType = new msteps_AssignTypeImpl();
    return msteps_AssignType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_TargetVarType createmsteps_TargetVarType()
  {
    msteps_TargetVarTypeImpl msteps_TargetVarType = new msteps_TargetVarTypeImpl();
    return msteps_TargetVarType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_UseVariabilityLevel createmsteps_UseVariabilityLevel()
  {
    msteps_UseVariabilityLevelImpl msteps_UseVariabilityLevel = new msteps_UseVariabilityLevelImpl();
    return msteps_UseVariabilityLevel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_UseVariabilityNode createmsteps_UseVariabilityNode()
  {
    msteps_UseVariabilityNodeImpl msteps_UseVariabilityNode = new msteps_UseVariabilityNodeImpl();
    return msteps_UseVariabilityNode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_SimulationStep createmsteps_SimulationStep()
  {
    msteps_SimulationStepImpl msteps_SimulationStep = new msteps_SimulationStepImpl();
    return msteps_SimulationStep;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_SimDataSetType createmsteps_SimDataSetType()
  {
    msteps_SimDataSetTypeImpl msteps_SimDataSetType = new msteps_SimDataSetTypeImpl();
    return msteps_SimDataSetType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ObservationsType createmsteps_ObservationsType()
  {
    msteps_ObservationsTypeImpl msteps_ObservationsType = new msteps_ObservationsTypeImpl();
    return msteps_ObservationsType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_WriteToType createmsteps_WriteToType()
  {
    msteps_WriteToTypeImpl msteps_WriteToType = new msteps_WriteToTypeImpl();
    return msteps_WriteToType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_TimepointsType createmsteps_TimepointsType()
  {
    msteps_TimepointsTypeImpl msteps_TimepointsType = new msteps_TimepointsTypeImpl();
    return msteps_TimepointsType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_InitialValueType createmsteps_InitialValueType()
  {
    msteps_InitialValueTypeImpl msteps_InitialValueType = new msteps_InitialValueTypeImpl();
    return msteps_InitialValueType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_EstimationOperationType createmsteps_EstimationOperationType()
  {
    msteps_EstimationOperationTypeImpl msteps_EstimationOperationType = new msteps_EstimationOperationTypeImpl();
    return msteps_EstimationOperationType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_TrialDesignType createdesign_TrialDesignType()
  {
    design_TrialDesignTypeImpl design_TrialDesignType = new design_TrialDesignTypeImpl();
    return design_TrialDesignType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_GroupType createdesign_GroupType()
  {
    design_GroupTypeImpl design_GroupType = new design_GroupTypeImpl();
    return design_GroupType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_GroupTypeContent createdesign_GroupTypeContent()
  {
    design_GroupTypeContentImpl design_GroupTypeContent = new design_GroupTypeContentImpl();
    return design_GroupTypeContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_IdividualsType createdesign_IdividualsType()
  {
    design_IdividualsTypeImpl design_IdividualsType = new design_IdividualsTypeImpl();
    return design_IdividualsType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_WashoutType createdesign_WashoutType()
  {
    design_WashoutTypeImpl design_WashoutType = new design_WashoutTypeImpl();
    return design_WashoutType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_TreatmentEpohType createdesign_TreatmentEpohType()
  {
    design_TreatmentEpohTypeImpl design_TreatmentEpohType = new design_TreatmentEpohTypeImpl();
    return design_TreatmentEpohType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_OccasionType createdesign_OccasionType()
  {
    design_OccasionTypeImpl design_OccasionType = new design_OccasionTypeImpl();
    return design_OccasionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_BeginType createdesign_BeginType()
  {
    design_BeginTypeImpl design_BeginType = new design_BeginTypeImpl();
    return design_BeginType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_EndType createdesign_EndType()
  {
    design_EndTypeImpl design_EndType = new design_EndTypeImpl();
    return design_EndType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_StartType createdesign_StartType()
  {
    design_StartTypeImpl design_StartType = new design_StartTypeImpl();
    return design_StartType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_TreatmentRefType createdesign_TreatmentRefType()
  {
    design_TreatmentRefTypeImpl design_TreatmentRefType = new design_TreatmentRefTypeImpl();
    return design_TreatmentRefType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_TreatmentEpochRefType createdesign_TreatmentEpochRefType()
  {
    design_TreatmentEpochRefTypeImpl design_TreatmentEpochRefType = new design_TreatmentEpochRefTypeImpl();
    return design_TreatmentEpochRefType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_TreatmentType createdesign_TreatmentType()
  {
    design_TreatmentTypeImpl design_TreatmentType = new design_TreatmentTypeImpl();
    return design_TreatmentType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingRegimenType createdesign_DosingRegimenType()
  {
    design_DosingRegimenTypeImpl design_DosingRegimenType = new design_DosingRegimenTypeImpl();
    return design_DosingRegimenType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_InfusionType createdesign_InfusionType()
  {
    design_InfusionTypeImpl design_InfusionType = new design_InfusionTypeImpl();
    return design_InfusionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DurationType createdesign_DurationType()
  {
    design_DurationTypeImpl design_DurationType = new design_DurationTypeImpl();
    return design_DurationType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_BolusType createdesign_BolusType()
  {
    design_BolusTypeImpl design_BolusType = new design_BolusTypeImpl();
    return design_BolusType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingTimesType createdesign_DosingTimesType()
  {
    design_DosingTimesTypeImpl design_DosingTimesType = new design_DosingTimesTypeImpl();
    return design_DosingTimesType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingTimesTypeContent createdesign_DosingTimesTypeContent()
  {
    design_DosingTimesTypeContentImpl design_DosingTimesTypeContent = new design_DosingTimesTypeContentImpl();
    return design_DosingTimesTypeContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_SteadyStateType createdesign_SteadyStateType()
  {
    design_SteadyStateTypeImpl design_SteadyStateType = new design_SteadyStateTypeImpl();
    return design_SteadyStateType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingStartType createdesign_DosingStartType()
  {
    design_DosingStartTypeImpl design_DosingStartType = new design_DosingStartTypeImpl();
    return design_DosingStartType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingPeriodType createdesign_DosingPeriodType()
  {
    design_DosingPeriodTypeImpl design_DosingPeriodType = new design_DosingPeriodTypeImpl();
    return design_DosingPeriodType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DoseAmountType createdesign_DoseAmountType()
  {
    design_DoseAmountTypeImpl design_DoseAmountType = new design_DoseAmountTypeImpl();
    return design_DoseAmountType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingVariableType createdesign_DosingVariableType()
  {
    design_DosingVariableTypeImpl design_DosingVariableType = new design_DosingVariableTypeImpl();
    return design_DosingVariableType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingVariableTypeContent createdesign_DosingVariableTypeContent()
  {
    design_DosingVariableTypeContentImpl design_DosingVariableTypeContent = new design_DosingVariableTypeContentImpl();
    return design_DosingVariableTypeContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_AmountType createdesign_AmountType()
  {
    design_AmountTypeImpl design_AmountType = new design_AmountTypeImpl();
    return design_AmountType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_TargetVarType createdesign_TargetVarType()
  {
    design_TargetVarTypeImpl design_TargetVarType = new design_TargetVarTypeImpl();
    return design_TargetVarType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DoseVarType createdesign_DoseVarType()
  {
    design_DoseVarTypeImpl design_DoseVarType = new design_DoseVarTypeImpl();
    return design_DoseVarType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_DistributionType createuncert_DistributionType()
  {
    uncert_DistributionTypeImpl uncert_DistributionType = new uncert_DistributionTypeImpl();
    return uncert_DistributionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_DistributionTypeContent createuncert_DistributionTypeContent()
  {
    uncert_DistributionTypeContentImpl uncert_DistributionTypeContent = new uncert_DistributionTypeContentImpl();
    return uncert_DistributionTypeContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Normal createuncert_Normal()
  {
    uncert_NormalImpl uncert_Normal = new uncert_NormalImpl();
    return uncert_Normal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_CoefficientOfVariance createuncert_CoefficientOfVariance()
  {
    uncert_CoefficientOfVarianceImpl uncert_CoefficientOfVariance = new uncert_CoefficientOfVarianceImpl();
    return uncert_CoefficientOfVariance;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_StdDev createuncert_StdDev()
  {
    uncert_StdDevImpl uncert_StdDev = new uncert_StdDevImpl();
    return uncert_StdDev;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_DistributionParamType createuncert_DistributionParamType()
  {
    uncert_DistributionParamTypeImpl uncert_DistributionParamType = new uncert_DistributionParamTypeImpl();
    return uncert_DistributionParamType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_MeanParamType createuncert_MeanParamType()
  {
    uncert_MeanParamTypeImpl uncert_MeanParamType = new uncert_MeanParamTypeImpl();
    return uncert_MeanParamType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Mean createuncert_Mean()
  {
    uncert_MeanImpl uncert_Mean = new uncert_MeanImpl();
    return uncert_Mean;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Variance createuncert_Variance()
  {
    uncert_VarianceImpl uncert_Variance = new uncert_VarianceImpl();
    return uncert_Variance;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_PDF createuncert_PDF()
  {
    uncert_PDFImpl uncert_PDF = new uncert_PDFImpl();
    return uncert_PDF;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_ParameterDefinitionType createuncert_ParameterDefinitionType()
  {
    uncert_ParameterDefinitionTypeImpl uncert_ParameterDefinitionType = new uncert_ParameterDefinitionTypeImpl();
    return uncert_ParameterDefinitionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Poison createuncert_Poison()
  {
    uncert_PoisonImpl uncert_Poison = new uncert_PoisonImpl();
    return uncert_Poison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Rate createuncert_Rate()
  {
    uncert_RateImpl uncert_Rate = new uncert_RateImpl();
    return uncert_Rate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Student_t createuncert_Student_t()
  {
    uncert_Student_tImpl uncert_Student_t = new uncert_Student_tImpl();
    return uncert_Student_t;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncertDegreesOfFreedom createuncertDegreesOfFreedom()
  {
    uncertDegreesOfFreedomImpl uncertDegreesOfFreedom = new uncertDegreesOfFreedomImpl();
    return uncertDegreesOfFreedom;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Scale createuncert_Scale()
  {
    uncert_ScaleImpl uncert_Scale = new uncert_ScaleImpl();
    return uncert_Scale;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Location createuncert_Location()
  {
    uncert_LocationImpl uncert_Location = new uncert_LocationImpl();
    return uncert_Location;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Uniform createuncert_Uniform()
  {
    uncert_UniformImpl uncert_Uniform = new uncert_UniformImpl();
    return uncert_Uniform;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_EquationType createMath_EquationType()
  {
    Math_EquationTypeImpl math_EquationType = new Math_EquationTypeImpl();
    return math_EquationType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_EquationTypeContent createMath_EquationTypeContent()
  {
    Math_EquationTypeContentImpl math_EquationTypeContent = new Math_EquationTypeContentImpl();
    return math_EquationTypeContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_ScalarType createMath_ScalarType()
  {
    Math_ScalarTypeImpl math_ScalarType = new Math_ScalarTypeImpl();
    return math_ScalarType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_VarType createMath_VarType()
  {
    Math_VarTypeImpl math_VarType = new Math_VarTypeImpl();
    return math_VarType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_BinopType createMath_BinopType()
  {
    Math_BinopTypeImpl math_BinopType = new Math_BinopTypeImpl();
    return math_BinopType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_UniopType createMath_UniopType()
  {
    Math_UniopTypeImpl math_UniopType = new Math_UniopTypeImpl();
    return math_UniopType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_ExprType createMath_ExprType()
  {
    Math_ExprTypeImpl math_ExprType = new Math_ExprTypeImpl();
    return math_ExprType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_ConstantType createMath_ConstantType()
  {
    Math_ConstantTypeImpl math_ConstantType = new Math_ConstantTypeImpl();
    return math_ConstantType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_FunctionCallType createMath_FunctionCallType()
  {
    Math_FunctionCallTypeImpl math_FunctionCallType = new Math_FunctionCallTypeImpl();
    return math_FunctionCallType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_FunctionArgumentType createMath_FunctionArgumentType()
  {
    Math_FunctionArgumentTypeImpl math_FunctionArgumentType = new Math_FunctionArgumentTypeImpl();
    return math_FunctionArgumentType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_FuncExprType createMath_FuncExprType()
  {
    Math_FuncExprTypeImpl math_FuncExprType = new Math_FuncExprTypeImpl();
    return math_FuncExprType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_PiecewiseType createMath_PiecewiseType()
  {
    Math_PiecewiseTypeImpl math_PiecewiseType = new Math_PiecewiseTypeImpl();
    return math_PiecewiseType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_PieceType createMath_PieceType()
  {
    Math_PieceTypeImpl math_PieceType = new Math_PieceTypeImpl();
    return math_PieceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_ConditionType createMath_ConditionType()
  {
    Math_ConditionTypeImpl math_ConditionType = new Math_ConditionTypeImpl();
    return math_ConditionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_LogicBaseType createMath_LogicBaseType()
  {
    Math_LogicBaseTypeImpl math_LogicBaseType = new Math_LogicBaseTypeImpl();
    return math_LogicBaseType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_Otherwise createMath_Otherwise()
  {
    Math_OtherwiseImpl math_Otherwise = new Math_OtherwiseImpl();
    return math_Otherwise;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_LogicExprType createMath_LogicExprType()
  {
    Math_LogicExprTypeImpl math_LogicExprType = new Math_LogicExprTypeImpl();
    return math_LogicExprType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_StringType createMath_StringType()
  {
    Math_StringTypeImpl math_StringType = new Math_StringTypeImpl();
    return math_StringType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_TRUE createMath_TRUE()
  {
    Math_TRUEImpl math_TRUE = new Math_TRUEImpl();
    return math_TRUE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_FALSE createMath_FALSE()
  {
    Math_FALSEImpl math_FALSE = new Math_FALSEImpl();
    return math_FALSE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_LogicBinopType createMath_LogicBinopType()
  {
    Math_LogicBinopTypeImpl math_LogicBinopType = new Math_LogicBinopTypeImpl();
    return math_LogicBinopType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_LogicUniopType createMath_LogicUniopType()
  {
    Math_LogicUniopTypeImpl math_LogicUniopType = new Math_LogicUniopTypeImpl();
    return math_LogicUniopType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_BinopName createMath_BinopName()
  {
    Math_BinopNameImpl math_BinopName = new Math_BinopNameImpl();
    return math_BinopName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_UniopName createMath_UniopName()
  {
    Math_UniopNameImpl math_UniopName = new Math_UniopNameImpl();
    return math_UniopName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_LogicUniopName createMath_LogicUniopName()
  {
    Math_LogicUniopNameImpl math_LogicUniopName = new Math_LogicUniopNameImpl();
    return math_LogicUniopName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_LogicBinopName createMath_LogicBinopName()
  {
    Math_LogicBinopNameImpl math_LogicBinopName = new Math_LogicBinopNameImpl();
    return math_LogicBinopName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_ConstantName createMath_ConstantName()
  {
    Math_ConstantNameImpl math_ConstantName = new Math_ConstantNameImpl();
    return math_ConstantName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_symbId createMath_symbId()
  {
    Math_symbIdImpl math_symbId = new Math_symbIdImpl();
    return math_symbId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_id createMath_id()
  {
    Math_idImpl math_id = new Math_idImpl();
    return math_id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_block createMath_block()
  {
    Math_blockImpl math_block = new Math_blockImpl();
    return math_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_EstimationOperationType createMath_EstimationOperationType()
  {
    Math_EstimationOperationTypeImpl math_EstimationOperationType = new Math_EstimationOperationTypeImpl();
    return math_EstimationOperationType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_ReplicatesType createct_ReplicatesType()
  {
    ct_ReplicatesTypeImpl ct_ReplicatesType = new ct_ReplicatesTypeImpl();
    return ct_ReplicatesType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_AnnotationType createct_AnnotationType()
  {
    ct_AnnotationTypeImpl ct_AnnotationType = new ct_AnnotationTypeImpl();
    return ct_AnnotationType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Rhs createct_Rhs()
  {
    ct_RhsImpl ct_Rhs = new ct_RhsImpl();
    return ct_Rhs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_DataSetType createct_DataSetType()
  {
    ct_DataSetTypeImpl ct_DataSetType = new ct_DataSetTypeImpl();
    return ct_DataSetType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_DefinitionType createct_DefinitionType()
  {
    ct_DefinitionTypeImpl ct_DefinitionType = new ct_DefinitionTypeImpl();
    return ct_DefinitionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_DefinitionRhsType createct_DefinitionRhsType()
  {
    ct_DefinitionRhsTypeImpl ct_DefinitionRhsType = new ct_DefinitionRhsTypeImpl();
    return ct_DefinitionRhsType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_ColumnType createct_ColumnType()
  {
    ct_ColumnTypeImpl ct_ColumnType = new ct_ColumnTypeImpl();
    return ct_ColumnType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_ExternalSourceType createct_ExternalSourceType()
  {
    ct_ExternalSourceTypeImpl ct_ExternalSourceType = new ct_ExternalSourceTypeImpl();
    return ct_ExternalSourceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_RowType createct_RowType()
  {
    ct_RowTypeImpl ct_RowType = new ct_RowTypeImpl();
    return ct_RowType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_RowTypeContent createct_RowTypeContent()
  {
    ct_RowTypeContentImpl ct_RowTypeContent = new ct_RowTypeContentImpl();
    return ct_RowTypeContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_NullCellType createct_NullCellType()
  {
    ct_NullCellTypeImpl ct_NullCellType = new ct_NullCellTypeImpl();
    return ct_NullCellType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_CellType createct_CellType()
  {
    ct_CellTypeImpl ct_CellType = new ct_CellTypeImpl();
    return ct_CellType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_InternalSourceType createct_InternalSourceType()
  {
    ct_InternalSourceTypeImpl ct_InternalSourceType = new ct_InternalSourceTypeImpl();
    return ct_InternalSourceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_VectorType createct_VectorType()
  {
    ct_VectorTypeImpl ct_VectorType = new ct_VectorTypeImpl();
    return ct_VectorType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_SequenceType createct_SequenceType()
  {
    ct_SequenceTypeImpl ct_SequenceType = new ct_SequenceTypeImpl();
    return ct_SequenceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_VariableDefinitionType createct_VariableDefinitionType()
  {
    ct_VariableDefinitionTypeImpl ct_VariableDefinitionType = new ct_VariableDefinitionTypeImpl();
    return ct_VariableDefinitionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_RestrictionType createct_RestrictionType()
  {
    ct_RestrictionTypeImpl ct_RestrictionType = new ct_RestrictionTypeImpl();
    return ct_RestrictionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_ScalarRhs createct_ScalarRhs()
  {
    ct_ScalarRhsImpl ct_ScalarRhs = new ct_ScalarRhsImpl();
    return ct_ScalarRhs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_independentVar createct_independentVar()
  {
    ct_independentVarImpl ct_independentVar = new ct_independentVarImpl();
    return ct_independentVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_levelId createct_levelId()
  {
    ct_levelIdImpl ct_levelId = new ct_levelIdImpl();
    return ct_levelId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_name createct_name()
  {
    ct_nameImpl ct_name = new ct_nameImpl();
    return ct_name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_symbId createct_symbId()
  {
    ct_symbIdImpl ct_symbId = new ct_symbIdImpl();
    return ct_symbId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_id createct_id()
  {
    ct_idImpl ct_id = new ct_idImpl();
    return ct_id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_symbolType createct_symbolType()
  {
    ct_symbolTypeImpl ct_symbolType = new ct_symbolTypeImpl();
    return ct_symbolType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_block createct_block()
  {
    ct_blockImpl ct_block = new ct_blockImpl();
    return ct_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_writtenVersion createct_writtenVersion()
  {
    ct_writtenVersionImpl ct_writtenVersion = new ct_writtenVersionImpl();
    return ct_writtenVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Decimal_Value createct_Decimal_Value()
  {
    ct_Decimal_ValueImpl ct_Decimal_Value = new ct_Decimal_ValueImpl();
    return ct_Decimal_Value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_String_Value createct_String_Value()
  {
    ct_String_ValueImpl ct_String_Value = new ct_String_ValueImpl();
    return ct_String_Value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_catId createct_catId()
  {
    ct_catIdImpl ct_catId = new ct_catIdImpl();
    return ct_catId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_SymbolTypeType createct_SymbolTypeType()
  {
    ct_SymbolTypeTypeImpl ct_SymbolTypeType = new ct_SymbolTypeTypeImpl();
    return ct_SymbolTypeType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XS_xmlns createXS_xmlns()
  {
    XS_xmlnsImpl xS_xmlns = new XS_xmlnsImpl();
    return xS_xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mml_FuncParameterDefinitionTyp createMml_FuncParameterDefinitionTyp()
  {
    Mml_FuncParameterDefinitionTypImpl mml_FuncParameterDefinitionTyp = new Mml_FuncParameterDefinitionTypImpl();
    return mml_FuncParameterDefinitionTyp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PharmMLPackage getPharmMLPackage()
  {
    return (PharmMLPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static PharmMLPackage getPackage()
  {
    return PharmMLPackage.eINSTANCE;
  }

} //PharmMLFactoryImpl

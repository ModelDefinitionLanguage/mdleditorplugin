/**
 */
package org.ddmore.pml.pharmaML.impl;

import org.ddmore.pml.pharmaML.*;

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
public class PharmaMLFactoryImpl extends EFactoryImpl implements PharmaMLFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static PharmaMLFactory init()
  {
    try
    {
      PharmaMLFactory thePharmaMLFactory = (PharmaMLFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.ddmore.org/pml/PharmaML"); 
      if (thePharmaMLFactory != null)
      {
        return thePharmaMLFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new PharmaMLFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PharmaMLFactoryImpl()
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
      case PharmaMLPackage.MML_PHARMA_ML: return createMml_PharmaML();
      case PharmaMLPackage.MML_SYMBOL_DEFINITION_TYPE: return createMml_SymbolDefinitionType();
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE: return createMml_FunctionDefinitionType();
      case PharmaMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYPE: return createMml_FuncParameterDefinitionType();
      case PharmaMLPackage.MATH_EQUATION_TYPE: return createMath_EquationType();
      case PharmaMLPackage.MATH_EQUATION_TYPE_CONTENT: return createMath_EquationTypeContent();
      case PharmaMLPackage.MATH_SCALAR_TYPE: return createMath_ScalarType();
      case PharmaMLPackage.MATH_VAR_TYPE: return createMath_VarType();
      case PharmaMLPackage.MATH_BINOP_TYPE: return createMath_BinopType();
      case PharmaMLPackage.MATH_UNIOP_TYPE: return createMath_UniopType();
      case PharmaMLPackage.MATH_EXPR_TYPE: return createMath_ExprType();
      case PharmaMLPackage.MATH_CONSTANT_TYPE: return createMath_ConstantType();
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE: return createMath_FunctionCallType();
      case PharmaMLPackage.MATH_FUNCTION_ARGUMENT_TYPE: return createMath_FunctionArgumentType();
      case PharmaMLPackage.MATH_FUNC_EXPR_TYPE: return createMath_FuncExprType();
      case PharmaMLPackage.MATH_PIECEWISE_TYPE: return createMath_PiecewiseType();
      case PharmaMLPackage.MATH_PIECE_TYPE: return createMath_PieceType();
      case PharmaMLPackage.MATH_CONDITION_TYPE: return createMath_ConditionType();
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE: return createMath_LogicBaseType();
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE: return createMath_LogicExprType();
      case PharmaMLPackage.MATH_STRING_TYPE: return createMath_StringType();
      case PharmaMLPackage.MATH_TRUE: return createMath_TRUE();
      case PharmaMLPackage.MATH_FALSE: return createMath_FALSE();
      case PharmaMLPackage.MATH_LOGIC_BINOP_TYPE: return createMath_LogicBinopType();
      case PharmaMLPackage.MATH_LOGIC_UNIOP_TYPE: return createMath_LogicUniopType();
      case PharmaMLPackage.MATH_BINOP_NAME: return createMath_BinopName();
      case PharmaMLPackage.MATH_UNIOP_NAME: return createMath_UniopName();
      case PharmaMLPackage.MATH_LOGIC_UNIOP_NAME: return createMath_LogicUniopName();
      case PharmaMLPackage.MATH_LOGIC_BINOP_NAME: return createMath_LogicBinopName();
      case PharmaMLPackage.MATH_CONSTANT_NAME: return createMath_ConstantName();
      case PharmaMLPackage.MATH_SYMB_ID: return createMath_symbId();
      case PharmaMLPackage.MATH_ID: return createMath_id();
      case PharmaMLPackage.MATH_BLOCK: return createMath_block();
      case PharmaMLPackage.CT_INDEPENDENT_VAR: return createct_independentVar();
      case PharmaMLPackage.MATH_ESTIMATION_OPERATION_TYPE: return createMath_EstimationOperationType();
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME: return createmdef_ParameterTypeName();
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE: return createmdef_ModelDefinitionType();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE: return createmdef_StructuralModelType();
      case PharmaMLPackage.MDEF_OBSERVATION_MODEL_TYPE: return createmdef_ObservationModelType();
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE: return createmdef_ParameterModelType();
      case PharmaMLPackage.MDEF_CORRELATION_TYPE: return createmdef_CorrelationType();
      case PharmaMLPackage.MDEF_COVARIANCE_TYPE: return createmdefCovarianceType();
      case PharmaMLPackage.MDEF_CORRELATION_COEFFICIENT_TYPE: return createmdef_CorrelationCoefficientType();
      case PharmaMLPackage.MDEF_COVARIATE_MODEL_TYPE: return createmdef_CovariateModelType();
      case PharmaMLPackage.MDEF_PARAMETER_TYPE: return createmdef_ParameterType();
      case PharmaMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE: return createmdef_CovariateVariabilityType();
      case PharmaMLPackage.MDEF_TRANSFORMATION: return createmdef_transformation();
      case PharmaMLPackage.MDEF_CATEGORICAL_TYPE: return createmdef_CategoricalType();
      case PharmaMLPackage.MDEF_CONTINUOUS_TYPE: return createmdef_ContinuousType();
      case PharmaMLPackage.MDEF_TRANSFORMATION_TYPE: return createmdef_TransformationType();
      case PharmaMLPackage.MDEF_VARIABILITY_LEVEL_DEFN_TYPE: return createmdef_VariabilityLevelDefnType();
      case PharmaMLPackage.MDEF_IMPORT_TYPE: return createmdef_ImportType();
      case PharmaMLPackage.MDEF_IMPORT_LINK_TYPE: return createmdef_ImportLinkType();
      case PharmaMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE: return createmdef_ImportResourceTypeType();
      case PharmaMLPackage.MDEF_COVARIATE_TYPE: return createmdef_CovariateType();
      case PharmaMLPackage.MDEF_FIXED_EFFECT_TYPE: return createmdef_FixedEffectType();
      case PharmaMLPackage.MDEF_CATEGORY_TYPE: return createmdef_CategoryType();
      case PharmaMLPackage.MDEF_PROBABILITY_TYPE: return createmdef_ProbabilityType();
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE: return createmdef_RandomEffectType();
      case PharmaMLPackage.MDEF_INITIAL_CONDITION_TYPE: return createmdef_InitialConditionType();
      case PharmaMLPackage.DESIGN_TRIAL_DESIGN_TYPE: return createdesign_TrialDesignType();
      case PharmaMLPackage.DESIGN_GROUP_TYPE: return createdesign_GroupType();
      case PharmaMLPackage.DESIGN_IDIVIDUALS_TYPE: return createdesign_IdividualsType();
      case PharmaMLPackage.DESIGN_WASHOUT_TYPE: return createdesign_WashoutType();
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE: return createdesign_TreatmentEpohType();
      case PharmaMLPackage.DESIGN_OCCASION_TYPE: return createdesign_OccasionType();
      case PharmaMLPackage.DESIGN_BEGIN_TYPE: return createdesign_BeginType();
      case PharmaMLPackage.DESIGN_END_TYPE: return createdesign_EndType();
      case PharmaMLPackage.DESIGN_START_TYPE: return createdesign_StartType();
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE: return createdesign_TreatmentRefType();
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE: return createdesign_TreatmentType();
      case PharmaMLPackage.DESIGN_DOSING_REGIMEN_TYPE: return createdesign_DosingRegimenType();
      case PharmaMLPackage.DESIGN_INFUSION_TYPE: return createdesign_InfusionType();
      case PharmaMLPackage.DESIGN_DURATION_TYPE: return createdesign_DurationType();
      case PharmaMLPackage.DESIGN_BOLUS_TYPE: return createdesign_BolusType();
      case PharmaMLPackage.DESIGN_DOSING_TIMES_TYPE: return createdesign_DosingTimesType();
      case PharmaMLPackage.DESIGN_STEADY_STATE_TYPE: return createdesign_SteadyStateType();
      case PharmaMLPackage.DESIGN_DOSING_START_TYPE: return createdesign_DosingStartType();
      case PharmaMLPackage.DESIGN_DOSING_PERIOD_TYPE: return createdesign_DosingPeriodType();
      case PharmaMLPackage.DESIGN_DOSE_AMOUNT_TYPE: return createdesign_DoseAmountType();
      case PharmaMLPackage.DESIGN_DOSING_VARIABLE_TYPE: return createdesign_DosingVariableType();
      case PharmaMLPackage.DESIGN_AMOUNT_TYPE: return createdesign_AmountType();
      case PharmaMLPackage.DESIGN_TARGET_VAR_TYPE: return createdesign_TargetVarType();
      case PharmaMLPackage.DESIGN_DOSE_VAR_TYPE: return createdesign_DoseVarType();
      case PharmaMLPackage.MSTEPS_MODELING_STEPS: return createmsteps_ModelingSteps();
      case PharmaMLPackage.MSTEPS_STEP_DEPENDENCIES_TYPE: return createmsteps_StepDependenciesType();
      case PharmaMLPackage.MSTEPS_STEP_TYPE: return createmsteps_StepType();
      case PharmaMLPackage.MSTEPS_DEPENDANT_STEP_TYPE: return createmsteps_DependantStepType();
      case PharmaMLPackage.MSTEPS_ESTIMATION_STEP: return createmsteps_EstimationStep();
      case PharmaMLPackage.MSTEPS_PARAMETERS_TO_ESTIMATE_TYPE: return createmsteps_ParametersToEstimateType();
      case PharmaMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE: return createmSteps_VariableEstimateType();
      case PharmaMLPackage.MSTEPS_FIXED: return createmSteps_fixed();
      case PharmaMLPackage.MSTEPS_UPPER_BOUND_TYPE: return createmSteps_UpperBoundType();
      case PharmaMLPackage.MSTEPS_LOWER_BOUND_TYPE: return createmSteps_LowerBoundType();
      case PharmaMLPackage.MSTEPS_INITIAL_ESTIMATE_TYPE: return createmSteps_InitialEstimateType();
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE: return createmsteps_ObjectiveDataSetType();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE: return createmsteps_MappingType();
      case PharmaMLPackage.MSTEPS_OUTPUT_TYPE: return createmsteps_OutputType();
      case PharmaMLPackage.MSTEPS_ASSIGN_TYPE: return createmsteps_AssignType();
      case PharmaMLPackage.MSTEPS_TARGET_VAR_TYPE: return createmsteps_TargetVarType();
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL: return createmsteps_UseVariabilityLevel();
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_NODE: return createmsteps_UseVariabilityNode();
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP: return createmsteps_SimulationStep();
      case PharmaMLPackage.MSTEPS_SIM_DATA_SET_TYPE: return createmsteps_SimDataSetType();
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE: return createmsteps_ObservationsType();
      case PharmaMLPackage.MSTEPS_WRITE_TO_TYPE: return createmsteps_WriteToType();
      case PharmaMLPackage.MSTEPS_TIMEPOINTS_TYPE: return createmsteps_TimepointsType();
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE: return createmsteps_InitialValueType();
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE: return createmsteps_EstimationOperationType();
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE: return createuncert_DistributionType();
      case PharmaMLPackage.UNCERT_NORMAL: return createuncert_Normal();
      case PharmaMLPackage.UNCERT_COEFFICIENT_OF_VARIANCE: return createuncert_CoefficientOfVariance();
      case PharmaMLPackage.UNCERT_STD_DEV: return createuncert_StdDev();
      case PharmaMLPackage.UNCERT_DISTRIBUTION_PARAM_TYPE: return createuncert_DistributionParamType();
      case PharmaMLPackage.UNCERT_MEAN_PARAM_TYPE: return createuncert_MeanParamType();
      case PharmaMLPackage.UNCERT_MEAN: return createuncert_Mean();
      case PharmaMLPackage.UNCERT_VARIANCE: return createuncert_Variance();
      case PharmaMLPackage.UNCERT_PDF: return createuncert_PDF();
      case PharmaMLPackage.UNCERT_PARAMETER_DEFINITION_TYPE: return createuncert_ParameterDefinitionType();
      case PharmaMLPackage.UNCERT_POISON: return createuncert_Poison();
      case PharmaMLPackage.UNCERT_RATE: return createuncert_Rate();
      case PharmaMLPackage.UNCERT_STUDENT_T: return createuncert_Student_t();
      case PharmaMLPackage.UNCERT_DEGREES_OF_FREEDOM: return createuncertDegreesOfFreedom();
      case PharmaMLPackage.UNCERT_SCALE: return createuncert_Scale();
      case PharmaMLPackage.UNCERT_LOCATION: return createuncert_Location();
      case PharmaMLPackage.UNCERT_UNIFORM: return createuncert_Uniform();
      case PharmaMLPackage.CT_SYMB_ID: return createct_symbId();
      case PharmaMLPackage.CT_ID: return createct_id();
      case PharmaMLPackage.CT_SYMBOL_TYPE: return createct_symbolType();
      case PharmaMLPackage.CT_BLOCK: return createct_block();
      case PharmaMLPackage.CT_WRITTEN_VERSION: return createct_writtenVersion();
      case PharmaMLPackage.CT_DECIMAL_VALUE: return createct_Decimal_Value();
      case PharmaMLPackage.CT_STRING_VALUE: return createct_String_Value();
      case PharmaMLPackage.CT_INT_VALUE: return createct_Int_Value();
      case PharmaMLPackage.CT_CAT_ID: return createct_catId();
      case PharmaMLPackage.CT_REPLICATES_TYPE: return createct_ReplicatesType();
      case PharmaMLPackage.CT_ANNOTATION_TYPE: return createct_AnnotationType();
      case PharmaMLPackage.CT_RHS: return createct_Rhs();
      case PharmaMLPackage.CT_DATA_SET_TYPE: return createct_DataSetType();
      case PharmaMLPackage.CT_DEFINITION_TYPE: return createct_DefinitionType();
      case PharmaMLPackage.CT_DEFINITION_RHS_TYPE: return createct_DefinitionRhsType();
      case PharmaMLPackage.CT_COLUMN_TYPE: return createct_ColumnType();
      case PharmaMLPackage.CT_EXTERNAL_SOURCE_TYPE: return createct_ExternalSourceType();
      case PharmaMLPackage.CT_ROW_TYPE: return createct_RowType();
      case PharmaMLPackage.CT_ROW_TYPE_CONTENT: return createct_RowTypeContent();
      case PharmaMLPackage.CT_CELL_TYPE: return createct_CellType();
      case PharmaMLPackage.CT_INTERNAL_SOURCE_TYPE: return createct_InternalSourceType();
      case PharmaMLPackage.CT_SCALAR_RHS: return createct_ScalarRhs();
      case PharmaMLPackage.CT_LEVEL_ID: return createct_levelId();
      case PharmaMLPackage.CT_NAME: return createct_Name();
      case PharmaMLPackage.CT_VECTOR_TYPE: return createct_VectorType();
      case PharmaMLPackage.CT_SEQUENCE_TYPE: return createct_SequenceType();
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE: return createct_SymbolTypeType();
      case PharmaMLPackage.CT_VARIABLE_DEFINITION_TYPE: return createct_VariableDefinitionType();
      case PharmaMLPackage.CT_VARIABLE_ASSIGNMENT_TYPE: return createct_VariableAssignmentType();
      case PharmaMLPackage.CT_RESTRICTION_TYPE: return createct_RestrictionType();
      case PharmaMLPackage.CT_XMLNS: return createct_xmlns();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mml_PharmaML createMml_PharmaML()
  {
    Mml_PharmaMLImpl mml_PharmaML = new Mml_PharmaMLImpl();
    return mml_PharmaML;
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
  public mdef_ContinuousType createmdef_ContinuousType()
  {
    mdef_ContinuousTypeImpl mdef_ContinuousType = new mdef_ContinuousTypeImpl();
    return mdef_ContinuousType;
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
  public msteps_MappingType createmsteps_MappingType()
  {
    msteps_MappingTypeImpl msteps_MappingType = new msteps_MappingTypeImpl();
    return msteps_MappingType;
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
  public ct_Int_Value createct_Int_Value()
  {
    ct_Int_ValueImpl ct_Int_Value = new ct_Int_ValueImpl();
    return ct_Int_Value;
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
  public ct_Name createct_Name()
  {
    ct_NameImpl ct_Name = new ct_NameImpl();
    return ct_Name;
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
  public ct_VariableAssignmentType createct_VariableAssignmentType()
  {
    ct_VariableAssignmentTypeImpl ct_VariableAssignmentType = new ct_VariableAssignmentTypeImpl();
    return ct_VariableAssignmentType;
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
  public ct_xmlns createct_xmlns()
  {
    ct_xmlnsImpl ct_xmlns = new ct_xmlnsImpl();
    return ct_xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PharmaMLPackage getPharmaMLPackage()
  {
    return (PharmaMLPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static PharmaMLPackage getPackage()
  {
    return PharmaMLPackage.eINSTANCE;
  }

} //PharmaMLFactoryImpl

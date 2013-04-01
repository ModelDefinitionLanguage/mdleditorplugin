/**
 */
package org.ddmore.pml.pharmaML.util;

import org.ddmore.pml.pharmaML.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage
 * @generated
 */
public class PharmaMLSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static PharmaMLPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PharmaMLSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = PharmaMLPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case PharmaMLPackage.MML_PHARMA_ML:
      {
        Mml_PharmaML mml_PharmaML = (Mml_PharmaML)theEObject;
        T result = caseMml_PharmaML(mml_PharmaML);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MML_SYMBOL_DEFINITION_TYPE:
      {
        Mml_SymbolDefinitionType mml_SymbolDefinitionType = (Mml_SymbolDefinitionType)theEObject;
        T result = caseMml_SymbolDefinitionType(mml_SymbolDefinitionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE:
      {
        Mml_FunctionDefinitionType mml_FunctionDefinitionType = (Mml_FunctionDefinitionType)theEObject;
        T result = caseMml_FunctionDefinitionType(mml_FunctionDefinitionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYPE:
      {
        Mml_FuncParameterDefinitionType mml_FuncParameterDefinitionType = (Mml_FuncParameterDefinitionType)theEObject;
        T result = caseMml_FuncParameterDefinitionType(mml_FuncParameterDefinitionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_EQUATION_TYPE:
      {
        Math_EquationType math_EquationType = (Math_EquationType)theEObject;
        T result = caseMath_EquationType(math_EquationType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_EQUATION_TYPE_CONTENT:
      {
        Math_EquationTypeContent math_EquationTypeContent = (Math_EquationTypeContent)theEObject;
        T result = caseMath_EquationTypeContent(math_EquationTypeContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_SCALAR_TYPE:
      {
        Math_ScalarType math_ScalarType = (Math_ScalarType)theEObject;
        T result = caseMath_ScalarType(math_ScalarType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_VAR_TYPE:
      {
        Math_VarType math_VarType = (Math_VarType)theEObject;
        T result = caseMath_VarType(math_VarType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_BINOP_TYPE:
      {
        Math_BinopType math_BinopType = (Math_BinopType)theEObject;
        T result = caseMath_BinopType(math_BinopType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_UNIOP_TYPE:
      {
        Math_UniopType math_UniopType = (Math_UniopType)theEObject;
        T result = caseMath_UniopType(math_UniopType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_EXPR_TYPE:
      {
        Math_ExprType math_ExprType = (Math_ExprType)theEObject;
        T result = caseMath_ExprType(math_ExprType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_CONSTANT_TYPE:
      {
        Math_ConstantType math_ConstantType = (Math_ConstantType)theEObject;
        T result = caseMath_ConstantType(math_ConstantType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE:
      {
        Math_FunctionCallType math_FunctionCallType = (Math_FunctionCallType)theEObject;
        T result = caseMath_FunctionCallType(math_FunctionCallType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_FUNCTION_ARGUMENT_TYPE:
      {
        Math_FunctionArgumentType math_FunctionArgumentType = (Math_FunctionArgumentType)theEObject;
        T result = caseMath_FunctionArgumentType(math_FunctionArgumentType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_FUNC_EXPR_TYPE:
      {
        Math_FuncExprType math_FuncExprType = (Math_FuncExprType)theEObject;
        T result = caseMath_FuncExprType(math_FuncExprType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_PIECEWISE_TYPE:
      {
        Math_PiecewiseType math_PiecewiseType = (Math_PiecewiseType)theEObject;
        T result = caseMath_PiecewiseType(math_PiecewiseType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_PIECE_TYPE:
      {
        Math_PieceType math_PieceType = (Math_PieceType)theEObject;
        T result = caseMath_PieceType(math_PieceType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_CONDITION_TYPE:
      {
        Math_ConditionType math_ConditionType = (Math_ConditionType)theEObject;
        T result = caseMath_ConditionType(math_ConditionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE:
      {
        Math_LogicBaseType math_LogicBaseType = (Math_LogicBaseType)theEObject;
        T result = caseMath_LogicBaseType(math_LogicBaseType);
        if (result == null) result = caseMath_LogicExprType(math_LogicBaseType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE:
      {
        Math_LogicExprType math_LogicExprType = (Math_LogicExprType)theEObject;
        T result = caseMath_LogicExprType(math_LogicExprType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_STRING_TYPE:
      {
        Math_StringType math_StringType = (Math_StringType)theEObject;
        T result = caseMath_StringType(math_StringType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_TRUE:
      {
        Math_TRUE math_TRUE = (Math_TRUE)theEObject;
        T result = caseMath_TRUE(math_TRUE);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_FALSE:
      {
        Math_FALSE math_FALSE = (Math_FALSE)theEObject;
        T result = caseMath_FALSE(math_FALSE);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_LOGIC_BINOP_TYPE:
      {
        Math_LogicBinopType math_LogicBinopType = (Math_LogicBinopType)theEObject;
        T result = caseMath_LogicBinopType(math_LogicBinopType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_LOGIC_UNIOP_TYPE:
      {
        Math_LogicUniopType math_LogicUniopType = (Math_LogicUniopType)theEObject;
        T result = caseMath_LogicUniopType(math_LogicUniopType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_BINOP_NAME:
      {
        Math_BinopName math_BinopName = (Math_BinopName)theEObject;
        T result = caseMath_BinopName(math_BinopName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_UNIOP_NAME:
      {
        Math_UniopName math_UniopName = (Math_UniopName)theEObject;
        T result = caseMath_UniopName(math_UniopName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_LOGIC_UNIOP_NAME:
      {
        Math_LogicUniopName math_LogicUniopName = (Math_LogicUniopName)theEObject;
        T result = caseMath_LogicUniopName(math_LogicUniopName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_LOGIC_BINOP_NAME:
      {
        Math_LogicBinopName math_LogicBinopName = (Math_LogicBinopName)theEObject;
        T result = caseMath_LogicBinopName(math_LogicBinopName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_CONSTANT_NAME:
      {
        Math_ConstantName math_ConstantName = (Math_ConstantName)theEObject;
        T result = caseMath_ConstantName(math_ConstantName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_SYMB_ID:
      {
        Math_symbId math_symbId = (Math_symbId)theEObject;
        T result = caseMath_symbId(math_symbId);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_ID:
      {
        Math_id math_id = (Math_id)theEObject;
        T result = caseMath_id(math_id);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_BLOCK:
      {
        Math_block math_block = (Math_block)theEObject;
        T result = caseMath_block(math_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_INDEPENDENT_VAR:
      {
        ct_independentVar ct_independentVar = (ct_independentVar)theEObject;
        T result = casect_independentVar(ct_independentVar);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MATH_ESTIMATION_OPERATION_TYPE:
      {
        Math_EstimationOperationType math_EstimationOperationType = (Math_EstimationOperationType)theEObject;
        T result = caseMath_EstimationOperationType(math_EstimationOperationType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME:
      {
        mdef_ParameterTypeName mdef_ParameterTypeName = (mdef_ParameterTypeName)theEObject;
        T result = casemdef_ParameterTypeName(mdef_ParameterTypeName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE:
      {
        mdef_ModelDefinitionType mdef_ModelDefinitionType = (mdef_ModelDefinitionType)theEObject;
        T result = casemdef_ModelDefinitionType(mdef_ModelDefinitionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE:
      {
        mdef_StructuralModelType mdef_StructuralModelType = (mdef_StructuralModelType)theEObject;
        T result = casemdef_StructuralModelType(mdef_StructuralModelType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_OBSERVATION_MODEL_TYPE:
      {
        mdef_ObservationModelType mdef_ObservationModelType = (mdef_ObservationModelType)theEObject;
        T result = casemdef_ObservationModelType(mdef_ObservationModelType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE:
      {
        mdef_ParameterModelType mdef_ParameterModelType = (mdef_ParameterModelType)theEObject;
        T result = casemdef_ParameterModelType(mdef_ParameterModelType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_CORRELATION_TYPE:
      {
        mdef_CorrelationType mdef_CorrelationType = (mdef_CorrelationType)theEObject;
        T result = casemdef_CorrelationType(mdef_CorrelationType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_COVARIANCE_TYPE:
      {
        mdefCovarianceType mdefCovarianceType = (mdefCovarianceType)theEObject;
        T result = casemdefCovarianceType(mdefCovarianceType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_CORRELATION_COEFFICIENT_TYPE:
      {
        mdef_CorrelationCoefficientType mdef_CorrelationCoefficientType = (mdef_CorrelationCoefficientType)theEObject;
        T result = casemdef_CorrelationCoefficientType(mdef_CorrelationCoefficientType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_COVARIATE_MODEL_TYPE:
      {
        mdef_CovariateModelType mdef_CovariateModelType = (mdef_CovariateModelType)theEObject;
        T result = casemdef_CovariateModelType(mdef_CovariateModelType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_PARAMETER_TYPE:
      {
        mdef_ParameterType mdef_ParameterType = (mdef_ParameterType)theEObject;
        T result = casemdef_ParameterType(mdef_ParameterType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE:
      {
        mdef_CovariateVariabilityType mdef_CovariateVariabilityType = (mdef_CovariateVariabilityType)theEObject;
        T result = casemdef_CovariateVariabilityType(mdef_CovariateVariabilityType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_TRANSFORMATION:
      {
        mdef_transformation mdef_transformation = (mdef_transformation)theEObject;
        T result = casemdef_transformation(mdef_transformation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_CATEGORICAL_TYPE:
      {
        mdef_CategoricalType mdef_CategoricalType = (mdef_CategoricalType)theEObject;
        T result = casemdef_CategoricalType(mdef_CategoricalType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_CONTINUOUS_TYPE:
      {
        mdef_ContinuousType mdef_ContinuousType = (mdef_ContinuousType)theEObject;
        T result = casemdef_ContinuousType(mdef_ContinuousType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_TRANSFORMATION_TYPE:
      {
        mdef_TransformationType mdef_TransformationType = (mdef_TransformationType)theEObject;
        T result = casemdef_TransformationType(mdef_TransformationType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_VARIABILITY_LEVEL_DEFN_TYPE:
      {
        mdef_VariabilityLevelDefnType mdef_VariabilityLevelDefnType = (mdef_VariabilityLevelDefnType)theEObject;
        T result = casemdef_VariabilityLevelDefnType(mdef_VariabilityLevelDefnType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_IMPORT_TYPE:
      {
        mdef_ImportType mdef_ImportType = (mdef_ImportType)theEObject;
        T result = casemdef_ImportType(mdef_ImportType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_IMPORT_LINK_TYPE:
      {
        mdef_ImportLinkType mdef_ImportLinkType = (mdef_ImportLinkType)theEObject;
        T result = casemdef_ImportLinkType(mdef_ImportLinkType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE:
      {
        mdef_ImportResourceTypeType mdef_ImportResourceTypeType = (mdef_ImportResourceTypeType)theEObject;
        T result = casemdef_ImportResourceTypeType(mdef_ImportResourceTypeType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_COVARIATE_TYPE:
      {
        mdef_CovariateType mdef_CovariateType = (mdef_CovariateType)theEObject;
        T result = casemdef_CovariateType(mdef_CovariateType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_FIXED_EFFECT_TYPE:
      {
        mdef_FixedEffectType mdef_FixedEffectType = (mdef_FixedEffectType)theEObject;
        T result = casemdef_FixedEffectType(mdef_FixedEffectType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_CATEGORY_TYPE:
      {
        mdef_CategoryType mdef_CategoryType = (mdef_CategoryType)theEObject;
        T result = casemdef_CategoryType(mdef_CategoryType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_PROBABILITY_TYPE:
      {
        mdef_ProbabilityType mdef_ProbabilityType = (mdef_ProbabilityType)theEObject;
        T result = casemdef_ProbabilityType(mdef_ProbabilityType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE:
      {
        mdef_RandomEffectType mdef_RandomEffectType = (mdef_RandomEffectType)theEObject;
        T result = casemdef_RandomEffectType(mdef_RandomEffectType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MDEF_INITIAL_CONDITION_TYPE:
      {
        mdef_InitialConditionType mdef_InitialConditionType = (mdef_InitialConditionType)theEObject;
        T result = casemdef_InitialConditionType(mdef_InitialConditionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_TRIAL_DESIGN_TYPE:
      {
        design_TrialDesignType design_TrialDesignType = (design_TrialDesignType)theEObject;
        T result = casedesign_TrialDesignType(design_TrialDesignType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_GROUP_TYPE:
      {
        design_GroupType design_GroupType = (design_GroupType)theEObject;
        T result = casedesign_GroupType(design_GroupType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_IDIVIDUALS_TYPE:
      {
        design_IdividualsType design_IdividualsType = (design_IdividualsType)theEObject;
        T result = casedesign_IdividualsType(design_IdividualsType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_WASHOUT_TYPE:
      {
        design_WashoutType design_WashoutType = (design_WashoutType)theEObject;
        T result = casedesign_WashoutType(design_WashoutType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE:
      {
        design_TreatmentEpohType design_TreatmentEpohType = (design_TreatmentEpohType)theEObject;
        T result = casedesign_TreatmentEpohType(design_TreatmentEpohType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_OCCASION_TYPE:
      {
        design_OccasionType design_OccasionType = (design_OccasionType)theEObject;
        T result = casedesign_OccasionType(design_OccasionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_BEGIN_TYPE:
      {
        design_BeginType design_BeginType = (design_BeginType)theEObject;
        T result = casedesign_BeginType(design_BeginType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_END_TYPE:
      {
        design_EndType design_EndType = (design_EndType)theEObject;
        T result = casedesign_EndType(design_EndType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_START_TYPE:
      {
        design_StartType design_StartType = (design_StartType)theEObject;
        T result = casedesign_StartType(design_StartType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE:
      {
        design_TreatmentRefType design_TreatmentRefType = (design_TreatmentRefType)theEObject;
        T result = casedesign_TreatmentRefType(design_TreatmentRefType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE:
      {
        design_TreatmentType design_TreatmentType = (design_TreatmentType)theEObject;
        T result = casedesign_TreatmentType(design_TreatmentType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_DOSING_REGIMEN_TYPE:
      {
        design_DosingRegimenType design_DosingRegimenType = (design_DosingRegimenType)theEObject;
        T result = casedesign_DosingRegimenType(design_DosingRegimenType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_INFUSION_TYPE:
      {
        design_InfusionType design_InfusionType = (design_InfusionType)theEObject;
        T result = casedesign_InfusionType(design_InfusionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_DURATION_TYPE:
      {
        design_DurationType design_DurationType = (design_DurationType)theEObject;
        T result = casedesign_DurationType(design_DurationType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_BOLUS_TYPE:
      {
        design_BolusType design_BolusType = (design_BolusType)theEObject;
        T result = casedesign_BolusType(design_BolusType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_DOSING_TIMES_TYPE:
      {
        design_DosingTimesType design_DosingTimesType = (design_DosingTimesType)theEObject;
        T result = casedesign_DosingTimesType(design_DosingTimesType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_STEADY_STATE_TYPE:
      {
        design_SteadyStateType design_SteadyStateType = (design_SteadyStateType)theEObject;
        T result = casedesign_SteadyStateType(design_SteadyStateType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_DOSING_START_TYPE:
      {
        design_DosingStartType design_DosingStartType = (design_DosingStartType)theEObject;
        T result = casedesign_DosingStartType(design_DosingStartType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_DOSING_PERIOD_TYPE:
      {
        design_DosingPeriodType design_DosingPeriodType = (design_DosingPeriodType)theEObject;
        T result = casedesign_DosingPeriodType(design_DosingPeriodType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_DOSE_AMOUNT_TYPE:
      {
        design_DoseAmountType design_DoseAmountType = (design_DoseAmountType)theEObject;
        T result = casedesign_DoseAmountType(design_DoseAmountType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_DOSING_VARIABLE_TYPE:
      {
        design_DosingVariableType design_DosingVariableType = (design_DosingVariableType)theEObject;
        T result = casedesign_DosingVariableType(design_DosingVariableType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_AMOUNT_TYPE:
      {
        design_AmountType design_AmountType = (design_AmountType)theEObject;
        T result = casedesign_AmountType(design_AmountType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_TARGET_VAR_TYPE:
      {
        design_TargetVarType design_TargetVarType = (design_TargetVarType)theEObject;
        T result = casedesign_TargetVarType(design_TargetVarType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.DESIGN_DOSE_VAR_TYPE:
      {
        design_DoseVarType design_DoseVarType = (design_DoseVarType)theEObject;
        T result = casedesign_DoseVarType(design_DoseVarType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_MODELING_STEPS:
      {
        msteps_ModelingSteps msteps_ModelingSteps = (msteps_ModelingSteps)theEObject;
        T result = casemsteps_ModelingSteps(msteps_ModelingSteps);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_STEP_DEPENDENCIES_TYPE:
      {
        msteps_StepDependenciesType msteps_StepDependenciesType = (msteps_StepDependenciesType)theEObject;
        T result = casemsteps_StepDependenciesType(msteps_StepDependenciesType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_STEP_TYPE:
      {
        msteps_StepType msteps_StepType = (msteps_StepType)theEObject;
        T result = casemsteps_StepType(msteps_StepType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_DEPENDANT_STEP_TYPE:
      {
        msteps_DependantStepType msteps_DependantStepType = (msteps_DependantStepType)theEObject;
        T result = casemsteps_DependantStepType(msteps_DependantStepType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_ESTIMATION_STEP:
      {
        msteps_EstimationStep msteps_EstimationStep = (msteps_EstimationStep)theEObject;
        T result = casemsteps_EstimationStep(msteps_EstimationStep);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_PARAMETERS_TO_ESTIMATE_TYPE:
      {
        msteps_ParametersToEstimateType msteps_ParametersToEstimateType = (msteps_ParametersToEstimateType)theEObject;
        T result = casemsteps_ParametersToEstimateType(msteps_ParametersToEstimateType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE:
      {
        mSteps_VariableEstimateType mSteps_VariableEstimateType = (mSteps_VariableEstimateType)theEObject;
        T result = casemSteps_VariableEstimateType(mSteps_VariableEstimateType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_FIXED:
      {
        mSteps_fixed mSteps_fixed = (mSteps_fixed)theEObject;
        T result = casemSteps_fixed(mSteps_fixed);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_UPPER_BOUND_TYPE:
      {
        mSteps_UpperBoundType mSteps_UpperBoundType = (mSteps_UpperBoundType)theEObject;
        T result = casemSteps_UpperBoundType(mSteps_UpperBoundType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_LOWER_BOUND_TYPE:
      {
        mSteps_LowerBoundType mSteps_LowerBoundType = (mSteps_LowerBoundType)theEObject;
        T result = casemSteps_LowerBoundType(mSteps_LowerBoundType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_INITIAL_ESTIMATE_TYPE:
      {
        mSteps_InitialEstimateType mSteps_InitialEstimateType = (mSteps_InitialEstimateType)theEObject;
        T result = casemSteps_InitialEstimateType(mSteps_InitialEstimateType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE:
      {
        msteps_ObjectiveDataSetType msteps_ObjectiveDataSetType = (msteps_ObjectiveDataSetType)theEObject;
        T result = casemsteps_ObjectiveDataSetType(msteps_ObjectiveDataSetType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE:
      {
        msteps_MappingType msteps_MappingType = (msteps_MappingType)theEObject;
        T result = casemsteps_MappingType(msteps_MappingType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_OUTPUT_TYPE:
      {
        msteps_OutputType msteps_OutputType = (msteps_OutputType)theEObject;
        T result = casemsteps_OutputType(msteps_OutputType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_ASSIGN_TYPE:
      {
        msteps_AssignType msteps_AssignType = (msteps_AssignType)theEObject;
        T result = casemsteps_AssignType(msteps_AssignType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_TARGET_VAR_TYPE:
      {
        msteps_TargetVarType msteps_TargetVarType = (msteps_TargetVarType)theEObject;
        T result = casemsteps_TargetVarType(msteps_TargetVarType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL:
      {
        msteps_UseVariabilityLevel msteps_UseVariabilityLevel = (msteps_UseVariabilityLevel)theEObject;
        T result = casemsteps_UseVariabilityLevel(msteps_UseVariabilityLevel);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_NODE:
      {
        msteps_UseVariabilityNode msteps_UseVariabilityNode = (msteps_UseVariabilityNode)theEObject;
        T result = casemsteps_UseVariabilityNode(msteps_UseVariabilityNode);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP:
      {
        msteps_SimulationStep msteps_SimulationStep = (msteps_SimulationStep)theEObject;
        T result = casemsteps_SimulationStep(msteps_SimulationStep);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_SIM_DATA_SET_TYPE:
      {
        msteps_SimDataSetType msteps_SimDataSetType = (msteps_SimDataSetType)theEObject;
        T result = casemsteps_SimDataSetType(msteps_SimDataSetType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE:
      {
        msteps_ObservationsType msteps_ObservationsType = (msteps_ObservationsType)theEObject;
        T result = casemsteps_ObservationsType(msteps_ObservationsType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_WRITE_TO_TYPE:
      {
        msteps_WriteToType msteps_WriteToType = (msteps_WriteToType)theEObject;
        T result = casemsteps_WriteToType(msteps_WriteToType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_TIMEPOINTS_TYPE:
      {
        msteps_TimepointsType msteps_TimepointsType = (msteps_TimepointsType)theEObject;
        T result = casemsteps_TimepointsType(msteps_TimepointsType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE:
      {
        msteps_InitialValueType msteps_InitialValueType = (msteps_InitialValueType)theEObject;
        T result = casemsteps_InitialValueType(msteps_InitialValueType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE:
      {
        msteps_EstimationOperationType msteps_EstimationOperationType = (msteps_EstimationOperationType)theEObject;
        T result = casemsteps_EstimationOperationType(msteps_EstimationOperationType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE:
      {
        uncert_DistributionType uncert_DistributionType = (uncert_DistributionType)theEObject;
        T result = caseuncert_DistributionType(uncert_DistributionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_NORMAL:
      {
        uncert_Normal uncert_Normal = (uncert_Normal)theEObject;
        T result = caseuncert_Normal(uncert_Normal);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_COEFFICIENT_OF_VARIANCE:
      {
        uncert_CoefficientOfVariance uncert_CoefficientOfVariance = (uncert_CoefficientOfVariance)theEObject;
        T result = caseuncert_CoefficientOfVariance(uncert_CoefficientOfVariance);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_STD_DEV:
      {
        uncert_StdDev uncert_StdDev = (uncert_StdDev)theEObject;
        T result = caseuncert_StdDev(uncert_StdDev);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_DISTRIBUTION_PARAM_TYPE:
      {
        uncert_DistributionParamType uncert_DistributionParamType = (uncert_DistributionParamType)theEObject;
        T result = caseuncert_DistributionParamType(uncert_DistributionParamType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_MEAN_PARAM_TYPE:
      {
        uncert_MeanParamType uncert_MeanParamType = (uncert_MeanParamType)theEObject;
        T result = caseuncert_MeanParamType(uncert_MeanParamType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_MEAN:
      {
        uncert_Mean uncert_Mean = (uncert_Mean)theEObject;
        T result = caseuncert_Mean(uncert_Mean);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_VARIANCE:
      {
        uncert_Variance uncert_Variance = (uncert_Variance)theEObject;
        T result = caseuncert_Variance(uncert_Variance);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_PDF:
      {
        uncert_PDF uncert_PDF = (uncert_PDF)theEObject;
        T result = caseuncert_PDF(uncert_PDF);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_PARAMETER_DEFINITION_TYPE:
      {
        uncert_ParameterDefinitionType uncert_ParameterDefinitionType = (uncert_ParameterDefinitionType)theEObject;
        T result = caseuncert_ParameterDefinitionType(uncert_ParameterDefinitionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_POISON:
      {
        uncert_Poison uncert_Poison = (uncert_Poison)theEObject;
        T result = caseuncert_Poison(uncert_Poison);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_RATE:
      {
        uncert_Rate uncert_Rate = (uncert_Rate)theEObject;
        T result = caseuncert_Rate(uncert_Rate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_STUDENT_T:
      {
        uncert_Student_t uncert_Student_t = (uncert_Student_t)theEObject;
        T result = caseuncert_Student_t(uncert_Student_t);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_DEGREES_OF_FREEDOM:
      {
        uncertDegreesOfFreedom uncertDegreesOfFreedom = (uncertDegreesOfFreedom)theEObject;
        T result = caseuncertDegreesOfFreedom(uncertDegreesOfFreedom);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_SCALE:
      {
        uncert_Scale uncert_Scale = (uncert_Scale)theEObject;
        T result = caseuncert_Scale(uncert_Scale);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_LOCATION:
      {
        uncert_Location uncert_Location = (uncert_Location)theEObject;
        T result = caseuncert_Location(uncert_Location);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.UNCERT_UNIFORM:
      {
        uncert_Uniform uncert_Uniform = (uncert_Uniform)theEObject;
        T result = caseuncert_Uniform(uncert_Uniform);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_SYMB_ID:
      {
        ct_symbId ct_symbId = (ct_symbId)theEObject;
        T result = casect_symbId(ct_symbId);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_ID:
      {
        ct_id ct_id = (ct_id)theEObject;
        T result = casect_id(ct_id);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_SYMBOL_TYPE:
      {
        ct_symbolType ct_symbolType = (ct_symbolType)theEObject;
        T result = casect_symbolType(ct_symbolType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_BLOCK:
      {
        ct_block ct_block = (ct_block)theEObject;
        T result = casect_block(ct_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_WRITTEN_VERSION:
      {
        ct_writtenVersion ct_writtenVersion = (ct_writtenVersion)theEObject;
        T result = casect_writtenVersion(ct_writtenVersion);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_DECIMAL_VALUE:
      {
        ct_Decimal_Value ct_Decimal_Value = (ct_Decimal_Value)theEObject;
        T result = casect_Decimal_Value(ct_Decimal_Value);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_STRING_VALUE:
      {
        ct_String_Value ct_String_Value = (ct_String_Value)theEObject;
        T result = casect_String_Value(ct_String_Value);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_INT_VALUE:
      {
        ct_Int_Value ct_Int_Value = (ct_Int_Value)theEObject;
        T result = casect_Int_Value(ct_Int_Value);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_CAT_ID:
      {
        ct_catId ct_catId = (ct_catId)theEObject;
        T result = casect_catId(ct_catId);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_REPLICATES_TYPE:
      {
        ct_ReplicatesType ct_ReplicatesType = (ct_ReplicatesType)theEObject;
        T result = casect_ReplicatesType(ct_ReplicatesType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_ANNOTATION_TYPE:
      {
        ct_AnnotationType ct_AnnotationType = (ct_AnnotationType)theEObject;
        T result = casect_AnnotationType(ct_AnnotationType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_RHS:
      {
        ct_Rhs ct_Rhs = (ct_Rhs)theEObject;
        T result = casect_Rhs(ct_Rhs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_DATA_SET_TYPE:
      {
        ct_DataSetType ct_DataSetType = (ct_DataSetType)theEObject;
        T result = casect_DataSetType(ct_DataSetType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_DEFINITION_TYPE:
      {
        ct_DefinitionType ct_DefinitionType = (ct_DefinitionType)theEObject;
        T result = casect_DefinitionType(ct_DefinitionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_DEFINITION_RHS_TYPE:
      {
        ct_DefinitionRhsType ct_DefinitionRhsType = (ct_DefinitionRhsType)theEObject;
        T result = casect_DefinitionRhsType(ct_DefinitionRhsType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_COLUMN_TYPE:
      {
        ct_ColumnType ct_ColumnType = (ct_ColumnType)theEObject;
        T result = casect_ColumnType(ct_ColumnType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_EXTERNAL_SOURCE_TYPE:
      {
        ct_ExternalSourceType ct_ExternalSourceType = (ct_ExternalSourceType)theEObject;
        T result = casect_ExternalSourceType(ct_ExternalSourceType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_ROW_TYPE:
      {
        ct_RowType ct_RowType = (ct_RowType)theEObject;
        T result = casect_RowType(ct_RowType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_ROW_TYPE_CONTENT:
      {
        ct_RowTypeContent ct_RowTypeContent = (ct_RowTypeContent)theEObject;
        T result = casect_RowTypeContent(ct_RowTypeContent);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_CELL_TYPE:
      {
        ct_CellType ct_CellType = (ct_CellType)theEObject;
        T result = casect_CellType(ct_CellType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_INTERNAL_SOURCE_TYPE:
      {
        ct_InternalSourceType ct_InternalSourceType = (ct_InternalSourceType)theEObject;
        T result = casect_InternalSourceType(ct_InternalSourceType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_SCALAR_RHS:
      {
        ct_ScalarRhs ct_ScalarRhs = (ct_ScalarRhs)theEObject;
        T result = casect_ScalarRhs(ct_ScalarRhs);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_LEVEL_ID:
      {
        ct_levelId ct_levelId = (ct_levelId)theEObject;
        T result = casect_levelId(ct_levelId);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_NAME:
      {
        ct_Name ct_Name = (ct_Name)theEObject;
        T result = casect_Name(ct_Name);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_VECTOR_TYPE:
      {
        ct_VectorType ct_VectorType = (ct_VectorType)theEObject;
        T result = casect_VectorType(ct_VectorType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_SEQUENCE_TYPE:
      {
        ct_SequenceType ct_SequenceType = (ct_SequenceType)theEObject;
        T result = casect_SequenceType(ct_SequenceType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE:
      {
        ct_SymbolTypeType ct_SymbolTypeType = (ct_SymbolTypeType)theEObject;
        T result = casect_SymbolTypeType(ct_SymbolTypeType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_VARIABLE_DEFINITION_TYPE:
      {
        ct_VariableDefinitionType ct_VariableDefinitionType = (ct_VariableDefinitionType)theEObject;
        T result = casect_VariableDefinitionType(ct_VariableDefinitionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_VARIABLE_ASSIGNMENT_TYPE:
      {
        ct_VariableAssignmentType ct_VariableAssignmentType = (ct_VariableAssignmentType)theEObject;
        T result = casect_VariableAssignmentType(ct_VariableAssignmentType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_RESTRICTION_TYPE:
      {
        ct_RestrictionType ct_RestrictionType = (ct_RestrictionType)theEObject;
        T result = casect_RestrictionType(ct_RestrictionType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case PharmaMLPackage.CT_XMLNS:
      {
        ct_xmlns ct_xmlns = (ct_xmlns)theEObject;
        T result = casect_xmlns(ct_xmlns);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mml Pharma ML</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mml Pharma ML</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMml_PharmaML(Mml_PharmaML object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mml Symbol Definition Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mml Symbol Definition Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMml_SymbolDefinitionType(Mml_SymbolDefinitionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mml Function Definition Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mml Function Definition Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMml_FunctionDefinitionType(Mml_FunctionDefinitionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mml Func Parameter Definition Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mml Func Parameter Definition Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMml_FuncParameterDefinitionType(Mml_FuncParameterDefinitionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Equation Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Equation Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_EquationType(Math_EquationType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Equation Type Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Equation Type Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_EquationTypeContent(Math_EquationTypeContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Scalar Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Scalar Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_ScalarType(Math_ScalarType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Var Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Var Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_VarType(Math_VarType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Binop Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Binop Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_BinopType(Math_BinopType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Uniop Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Uniop Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_UniopType(Math_UniopType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Expr Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Expr Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_ExprType(Math_ExprType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Constant Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Constant Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_ConstantType(Math_ConstantType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Function Call Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Function Call Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_FunctionCallType(Math_FunctionCallType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Function Argument Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Function Argument Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_FunctionArgumentType(Math_FunctionArgumentType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Func Expr Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Func Expr Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_FuncExprType(Math_FuncExprType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Piecewise Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Piecewise Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_PiecewiseType(Math_PiecewiseType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Piece Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Piece Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_PieceType(Math_PieceType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Condition Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Condition Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_ConditionType(Math_ConditionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Logic Base Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Logic Base Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_LogicBaseType(Math_LogicBaseType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Logic Expr Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Logic Expr Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_LogicExprType(Math_LogicExprType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math String Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math String Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_StringType(Math_StringType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math TRUE</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math TRUE</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_TRUE(Math_TRUE object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math FALSE</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math FALSE</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_FALSE(Math_FALSE object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Logic Binop Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Logic Binop Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_LogicBinopType(Math_LogicBinopType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Logic Uniop Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Logic Uniop Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_LogicUniopType(Math_LogicUniopType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Binop Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Binop Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_BinopName(Math_BinopName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Uniop Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Uniop Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_UniopName(Math_UniopName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Logic Uniop Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Logic Uniop Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_LogicUniopName(Math_LogicUniopName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Logic Binop Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Logic Binop Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_LogicBinopName(Math_LogicBinopName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Constant Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Constant Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_ConstantName(Math_ConstantName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math symb Id</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math symb Id</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_symbId(Math_symbId object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math id</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math id</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_id(Math_id object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_block(Math_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct independent Var</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct independent Var</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_independentVar(ct_independentVar object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Math Estimation Operation Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Math Estimation Operation Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMath_EstimationOperationType(Math_EstimationOperationType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Parameter Type Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Parameter Type Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ParameterTypeName(mdef_ParameterTypeName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Model Definition Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Model Definition Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ModelDefinitionType(mdef_ModelDefinitionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Structural Model Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Structural Model Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_StructuralModelType(mdef_StructuralModelType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Observation Model Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Observation Model Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ObservationModelType(mdef_ObservationModelType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Parameter Model Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Parameter Model Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ParameterModelType(mdef_ParameterModelType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Correlation Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Correlation Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_CorrelationType(mdef_CorrelationType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Covariance Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Covariance Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdefCovarianceType(mdefCovarianceType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Correlation Coefficient Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Correlation Coefficient Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_CorrelationCoefficientType(mdef_CorrelationCoefficientType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Covariate Model Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Covariate Model Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_CovariateModelType(mdef_CovariateModelType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Parameter Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Parameter Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ParameterType(mdef_ParameterType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Covariate Variability Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Covariate Variability Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_CovariateVariabilityType(mdef_CovariateVariabilityType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef transformation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef transformation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_transformation(mdef_transformation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Categorical Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Categorical Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_CategoricalType(mdef_CategoricalType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Continuous Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Continuous Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ContinuousType(mdef_ContinuousType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Transformation Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Transformation Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_TransformationType(mdef_TransformationType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Variability Level Defn Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Variability Level Defn Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_VariabilityLevelDefnType(mdef_VariabilityLevelDefnType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Import Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Import Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ImportType(mdef_ImportType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Import Link Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Import Link Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ImportLinkType(mdef_ImportLinkType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Import Resource Type Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Import Resource Type Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ImportResourceTypeType(mdef_ImportResourceTypeType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Covariate Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Covariate Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_CovariateType(mdef_CovariateType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Fixed Effect Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Fixed Effect Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_FixedEffectType(mdef_FixedEffectType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Category Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Category Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_CategoryType(mdef_CategoryType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Probability Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Probability Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_ProbabilityType(mdef_ProbabilityType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Random Effect Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Random Effect Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_RandomEffectType(mdef_RandomEffectType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mdef Initial Condition Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mdef Initial Condition Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemdef_InitialConditionType(mdef_InitialConditionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Trial Design Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Trial Design Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_TrialDesignType(design_TrialDesignType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Group Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Group Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_GroupType(design_GroupType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Idividuals Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Idividuals Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_IdividualsType(design_IdividualsType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Washout Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Washout Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_WashoutType(design_WashoutType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Treatment Epoh Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Treatment Epoh Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_TreatmentEpohType(design_TreatmentEpohType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Occasion Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Occasion Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_OccasionType(design_OccasionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Begin Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Begin Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_BeginType(design_BeginType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design End Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design End Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_EndType(design_EndType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Start Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Start Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_StartType(design_StartType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Treatment Ref Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Treatment Ref Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_TreatmentRefType(design_TreatmentRefType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Treatment Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Treatment Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_TreatmentType(design_TreatmentType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Dosing Regimen Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Dosing Regimen Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_DosingRegimenType(design_DosingRegimenType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Infusion Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Infusion Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_InfusionType(design_InfusionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Duration Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Duration Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_DurationType(design_DurationType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Bolus Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Bolus Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_BolusType(design_BolusType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Dosing Times Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Dosing Times Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_DosingTimesType(design_DosingTimesType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Steady State Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Steady State Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_SteadyStateType(design_SteadyStateType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Dosing Start Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Dosing Start Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_DosingStartType(design_DosingStartType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Dosing Period Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Dosing Period Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_DosingPeriodType(design_DosingPeriodType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Dose Amount Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Dose Amount Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_DoseAmountType(design_DoseAmountType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Dosing Variable Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Dosing Variable Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_DosingVariableType(design_DosingVariableType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Amount Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Amount Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_AmountType(design_AmountType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Target Var Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Target Var Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_TargetVarType(design_TargetVarType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design Dose Var Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design Dose Var Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_DoseVarType(design_DoseVarType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Modeling Steps</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Modeling Steps</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_ModelingSteps(msteps_ModelingSteps object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Step Dependencies Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Step Dependencies Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_StepDependenciesType(msteps_StepDependenciesType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Step Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Step Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_StepType(msteps_StepType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Dependant Step Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Dependant Step Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_DependantStepType(msteps_DependantStepType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Estimation Step</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Estimation Step</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_EstimationStep(msteps_EstimationStep object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Parameters To Estimate Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Parameters To Estimate Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_ParametersToEstimateType(msteps_ParametersToEstimateType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mSteps Variable Estimate Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mSteps Variable Estimate Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemSteps_VariableEstimateType(mSteps_VariableEstimateType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mSteps fixed</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mSteps fixed</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemSteps_fixed(mSteps_fixed object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mSteps Upper Bound Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mSteps Upper Bound Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemSteps_UpperBoundType(mSteps_UpperBoundType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mSteps Lower Bound Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mSteps Lower Bound Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemSteps_LowerBoundType(mSteps_LowerBoundType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mSteps Initial Estimate Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mSteps Initial Estimate Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemSteps_InitialEstimateType(mSteps_InitialEstimateType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Objective Data Set Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Objective Data Set Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_ObjectiveDataSetType(msteps_ObjectiveDataSetType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Mapping Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Mapping Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_MappingType(msteps_MappingType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Output Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Output Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_OutputType(msteps_OutputType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Assign Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Assign Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_AssignType(msteps_AssignType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Target Var Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Target Var Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_TargetVarType(msteps_TargetVarType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Use Variability Level</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Use Variability Level</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_UseVariabilityLevel(msteps_UseVariabilityLevel object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Use Variability Node</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Use Variability Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_UseVariabilityNode(msteps_UseVariabilityNode object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Simulation Step</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Simulation Step</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_SimulationStep(msteps_SimulationStep object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Sim Data Set Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Sim Data Set Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_SimDataSetType(msteps_SimDataSetType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Observations Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Observations Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_ObservationsType(msteps_ObservationsType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Write To Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Write To Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_WriteToType(msteps_WriteToType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Timepoints Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Timepoints Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_TimepointsType(msteps_TimepointsType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Initial Value Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Initial Value Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_InitialValueType(msteps_InitialValueType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>msteps Estimation Operation Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>msteps Estimation Operation Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemsteps_EstimationOperationType(msteps_EstimationOperationType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Distribution Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Distribution Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_DistributionType(uncert_DistributionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Normal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Normal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_Normal(uncert_Normal object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Coefficient Of Variance</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Coefficient Of Variance</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_CoefficientOfVariance(uncert_CoefficientOfVariance object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Std Dev</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Std Dev</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_StdDev(uncert_StdDev object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Distribution Param Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Distribution Param Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_DistributionParamType(uncert_DistributionParamType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Mean Param Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Mean Param Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_MeanParamType(uncert_MeanParamType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Mean</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Mean</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_Mean(uncert_Mean object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Variance</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Variance</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_Variance(uncert_Variance object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert PDF</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert PDF</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_PDF(uncert_PDF object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Parameter Definition Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Parameter Definition Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_ParameterDefinitionType(uncert_ParameterDefinitionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Poison</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Poison</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_Poison(uncert_Poison object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Rate</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Rate</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_Rate(uncert_Rate object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Student t</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Student t</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_Student_t(uncert_Student_t object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Degrees Of Freedom</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Degrees Of Freedom</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncertDegreesOfFreedom(uncertDegreesOfFreedom object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Scale</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Scale</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_Scale(uncert_Scale object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Location</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Location</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_Location(uncert_Location object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>uncert Uniform</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>uncert Uniform</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseuncert_Uniform(uncert_Uniform object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct symb Id</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct symb Id</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_symbId(ct_symbId object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct id</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct id</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_id(ct_id object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct symbol Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct symbol Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_symbolType(ct_symbolType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_block(ct_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct written Version</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct written Version</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_writtenVersion(ct_writtenVersion object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Decimal Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Decimal Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_Decimal_Value(ct_Decimal_Value object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct String Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct String Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_String_Value(ct_String_Value object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Int Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Int Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_Int_Value(ct_Int_Value object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct cat Id</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct cat Id</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_catId(ct_catId object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Replicates Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Replicates Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_ReplicatesType(ct_ReplicatesType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Annotation Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Annotation Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_AnnotationType(ct_AnnotationType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Rhs</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Rhs</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_Rhs(ct_Rhs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Data Set Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Data Set Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_DataSetType(ct_DataSetType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Definition Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Definition Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_DefinitionType(ct_DefinitionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Definition Rhs Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Definition Rhs Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_DefinitionRhsType(ct_DefinitionRhsType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Column Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Column Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_ColumnType(ct_ColumnType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct External Source Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct External Source Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_ExternalSourceType(ct_ExternalSourceType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Row Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Row Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_RowType(ct_RowType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Row Type Content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Row Type Content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_RowTypeContent(ct_RowTypeContent object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Cell Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Cell Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_CellType(ct_CellType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Internal Source Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Internal Source Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_InternalSourceType(ct_InternalSourceType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Scalar Rhs</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Scalar Rhs</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_ScalarRhs(ct_ScalarRhs object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct level Id</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct level Id</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_levelId(ct_levelId object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_Name(ct_Name object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Vector Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Vector Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_VectorType(ct_VectorType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Sequence Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Sequence Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_SequenceType(ct_SequenceType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Symbol Type Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Symbol Type Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_SymbolTypeType(ct_SymbolTypeType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Variable Definition Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Variable Definition Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_VariableDefinitionType(ct_VariableDefinitionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Variable Assignment Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Variable Assignment Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_VariableAssignmentType(ct_VariableAssignmentType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct Restriction Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct Restriction Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_RestrictionType(ct_RestrictionType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ct xmlns</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ct xmlns</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casect_xmlns(ct_xmlns object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //PharmaMLSwitch

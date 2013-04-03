/**
 */
package org.ddmore.pml.pharmaML.util;

import org.ddmore.pml.pharmaML.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage
 * @generated
 */
public class PharmaMLAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static PharmaMLPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PharmaMLAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = PharmaMLPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PharmaMLSwitch<Adapter> modelSwitch =
    new PharmaMLSwitch<Adapter>()
    {
      @Override
      public Adapter caseMml_PharmaML(Mml_PharmaML object)
      {
        return createMml_PharmaMLAdapter();
      }
      @Override
      public Adapter caseMml_SymbolDefinitionType(Mml_SymbolDefinitionType object)
      {
        return createMml_SymbolDefinitionTypeAdapter();
      }
      @Override
      public Adapter caseMml_FunctionDefinitionType(Mml_FunctionDefinitionType object)
      {
        return createMml_FunctionDefinitionTypeAdapter();
      }
      @Override
      public Adapter caseMml_FuncParameterDefinitionType(Mml_FuncParameterDefinitionType object)
      {
        return createMml_FuncParameterDefinitionTypeAdapter();
      }
      @Override
      public Adapter caseMath_EquationType(Math_EquationType object)
      {
        return createMath_EquationTypeAdapter();
      }
      @Override
      public Adapter caseMath_EquationTypeContent(Math_EquationTypeContent object)
      {
        return createMath_EquationTypeContentAdapter();
      }
      @Override
      public Adapter caseMath_ScalarType(Math_ScalarType object)
      {
        return createMath_ScalarTypeAdapter();
      }
      @Override
      public Adapter caseMath_VarType(Math_VarType object)
      {
        return createMath_VarTypeAdapter();
      }
      @Override
      public Adapter caseMath_BinopType(Math_BinopType object)
      {
        return createMath_BinopTypeAdapter();
      }
      @Override
      public Adapter caseMath_UniopType(Math_UniopType object)
      {
        return createMath_UniopTypeAdapter();
      }
      @Override
      public Adapter caseMath_ExprType(Math_ExprType object)
      {
        return createMath_ExprTypeAdapter();
      }
      @Override
      public Adapter caseMath_ConstantType(Math_ConstantType object)
      {
        return createMath_ConstantTypeAdapter();
      }
      @Override
      public Adapter caseMath_FunctionCallType(Math_FunctionCallType object)
      {
        return createMath_FunctionCallTypeAdapter();
      }
      @Override
      public Adapter caseMath_FunctionArgumentType(Math_FunctionArgumentType object)
      {
        return createMath_FunctionArgumentTypeAdapter();
      }
      @Override
      public Adapter caseMath_FuncExprType(Math_FuncExprType object)
      {
        return createMath_FuncExprTypeAdapter();
      }
      @Override
      public Adapter caseMath_PiecewiseType(Math_PiecewiseType object)
      {
        return createMath_PiecewiseTypeAdapter();
      }
      @Override
      public Adapter caseMath_PieceType(Math_PieceType object)
      {
        return createMath_PieceTypeAdapter();
      }
      @Override
      public Adapter caseMath_ConditionType(Math_ConditionType object)
      {
        return createMath_ConditionTypeAdapter();
      }
      @Override
      public Adapter caseMath_LogicBaseType(Math_LogicBaseType object)
      {
        return createMath_LogicBaseTypeAdapter();
      }
      @Override
      public Adapter caseMath_LogicExprType(Math_LogicExprType object)
      {
        return createMath_LogicExprTypeAdapter();
      }
      @Override
      public Adapter caseMath_StringType(Math_StringType object)
      {
        return createMath_StringTypeAdapter();
      }
      @Override
      public Adapter caseMath_TRUE(Math_TRUE object)
      {
        return createMath_TRUEAdapter();
      }
      @Override
      public Adapter caseMath_FALSE(Math_FALSE object)
      {
        return createMath_FALSEAdapter();
      }
      @Override
      public Adapter caseMath_LogicBinopType(Math_LogicBinopType object)
      {
        return createMath_LogicBinopTypeAdapter();
      }
      @Override
      public Adapter caseMath_LogicUniopType(Math_LogicUniopType object)
      {
        return createMath_LogicUniopTypeAdapter();
      }
      @Override
      public Adapter caseMath_BinopName(Math_BinopName object)
      {
        return createMath_BinopNameAdapter();
      }
      @Override
      public Adapter caseMath_UniopName(Math_UniopName object)
      {
        return createMath_UniopNameAdapter();
      }
      @Override
      public Adapter caseMath_LogicUniopName(Math_LogicUniopName object)
      {
        return createMath_LogicUniopNameAdapter();
      }
      @Override
      public Adapter caseMath_LogicBinopName(Math_LogicBinopName object)
      {
        return createMath_LogicBinopNameAdapter();
      }
      @Override
      public Adapter caseMath_ConstantName(Math_ConstantName object)
      {
        return createMath_ConstantNameAdapter();
      }
      @Override
      public Adapter caseMath_symbId(Math_symbId object)
      {
        return createMath_symbIdAdapter();
      }
      @Override
      public Adapter caseMath_id(Math_id object)
      {
        return createMath_idAdapter();
      }
      @Override
      public Adapter caseMath_block(Math_block object)
      {
        return createMath_blockAdapter();
      }
      @Override
      public Adapter casect_independentVar(ct_independentVar object)
      {
        return createct_independentVarAdapter();
      }
      @Override
      public Adapter caseMath_EstimationOperationType(Math_EstimationOperationType object)
      {
        return createMath_EstimationOperationTypeAdapter();
      }
      @Override
      public Adapter casemdef_ParameterTypeName(mdef_ParameterTypeName object)
      {
        return createmdef_ParameterTypeNameAdapter();
      }
      @Override
      public Adapter casemdef_ModelDefinitionType(mdef_ModelDefinitionType object)
      {
        return createmdef_ModelDefinitionTypeAdapter();
      }
      @Override
      public Adapter casemdef_StructuralModelType(mdef_StructuralModelType object)
      {
        return createmdef_StructuralModelTypeAdapter();
      }
      @Override
      public Adapter casemdef_ObservationModelType(mdef_ObservationModelType object)
      {
        return createmdef_ObservationModelTypeAdapter();
      }
      @Override
      public Adapter casemdef_ParameterModelType(mdef_ParameterModelType object)
      {
        return createmdef_ParameterModelTypeAdapter();
      }
      @Override
      public Adapter casemdef_CorrelationType(mdef_CorrelationType object)
      {
        return createmdef_CorrelationTypeAdapter();
      }
      @Override
      public Adapter casemdefCovarianceType(mdefCovarianceType object)
      {
        return createmdefCovarianceTypeAdapter();
      }
      @Override
      public Adapter casemdef_CorrelationCoefficientType(mdef_CorrelationCoefficientType object)
      {
        return createmdef_CorrelationCoefficientTypeAdapter();
      }
      @Override
      public Adapter casemdef_CovariateModelType(mdef_CovariateModelType object)
      {
        return createmdef_CovariateModelTypeAdapter();
      }
      @Override
      public Adapter casemdef_ParameterType(mdef_ParameterType object)
      {
        return createmdef_ParameterTypeAdapter();
      }
      @Override
      public Adapter casemdef_CovariateVariabilityType(mdef_CovariateVariabilityType object)
      {
        return createmdef_CovariateVariabilityTypeAdapter();
      }
      @Override
      public Adapter casemdef_transformation(mdef_transformation object)
      {
        return createmdef_transformationAdapter();
      }
      @Override
      public Adapter casemdef_CategoricalType(mdef_CategoricalType object)
      {
        return createmdef_CategoricalTypeAdapter();
      }
      @Override
      public Adapter casemdef_ContinuousType(mdef_ContinuousType object)
      {
        return createmdef_ContinuousTypeAdapter();
      }
      @Override
      public Adapter casemdef_TransformationType(mdef_TransformationType object)
      {
        return createmdef_TransformationTypeAdapter();
      }
      @Override
      public Adapter casemdef_VariabilityLevelDefnType(mdef_VariabilityLevelDefnType object)
      {
        return createmdef_VariabilityLevelDefnTypeAdapter();
      }
      @Override
      public Adapter casemdef_ImportType(mdef_ImportType object)
      {
        return createmdef_ImportTypeAdapter();
      }
      @Override
      public Adapter casemdef_ImportLinkType(mdef_ImportLinkType object)
      {
        return createmdef_ImportLinkTypeAdapter();
      }
      @Override
      public Adapter casemdef_ImportResourceTypeType(mdef_ImportResourceTypeType object)
      {
        return createmdef_ImportResourceTypeTypeAdapter();
      }
      @Override
      public Adapter casemdef_CovariateType(mdef_CovariateType object)
      {
        return createmdef_CovariateTypeAdapter();
      }
      @Override
      public Adapter casemdef_FixedEffectType(mdef_FixedEffectType object)
      {
        return createmdef_FixedEffectTypeAdapter();
      }
      @Override
      public Adapter casemdef_CategoryType(mdef_CategoryType object)
      {
        return createmdef_CategoryTypeAdapter();
      }
      @Override
      public Adapter casemdef_ProbabilityType(mdef_ProbabilityType object)
      {
        return createmdef_ProbabilityTypeAdapter();
      }
      @Override
      public Adapter casemdef_RandomEffectType(mdef_RandomEffectType object)
      {
        return createmdef_RandomEffectTypeAdapter();
      }
      @Override
      public Adapter casemdef_InitialConditionType(mdef_InitialConditionType object)
      {
        return createmdef_InitialConditionTypeAdapter();
      }
      @Override
      public Adapter casedesign_TrialDesignType(design_TrialDesignType object)
      {
        return createdesign_TrialDesignTypeAdapter();
      }
      @Override
      public Adapter casedesign_GroupType(design_GroupType object)
      {
        return createdesign_GroupTypeAdapter();
      }
      @Override
      public Adapter casedesign_IdividualsType(design_IdividualsType object)
      {
        return createdesign_IdividualsTypeAdapter();
      }
      @Override
      public Adapter casedesign_WashoutType(design_WashoutType object)
      {
        return createdesign_WashoutTypeAdapter();
      }
      @Override
      public Adapter casedesign_TreatmentEpohType(design_TreatmentEpohType object)
      {
        return createdesign_TreatmentEpohTypeAdapter();
      }
      @Override
      public Adapter casedesign_OccasionType(design_OccasionType object)
      {
        return createdesign_OccasionTypeAdapter();
      }
      @Override
      public Adapter casedesign_BeginType(design_BeginType object)
      {
        return createdesign_BeginTypeAdapter();
      }
      @Override
      public Adapter casedesign_EndType(design_EndType object)
      {
        return createdesign_EndTypeAdapter();
      }
      @Override
      public Adapter casedesign_StartType(design_StartType object)
      {
        return createdesign_StartTypeAdapter();
      }
      @Override
      public Adapter casedesign_TreatmentRefType(design_TreatmentRefType object)
      {
        return createdesign_TreatmentRefTypeAdapter();
      }
      @Override
      public Adapter casedesign_TreatmentType(design_TreatmentType object)
      {
        return createdesign_TreatmentTypeAdapter();
      }
      @Override
      public Adapter casedesign_DosingRegimenType(design_DosingRegimenType object)
      {
        return createdesign_DosingRegimenTypeAdapter();
      }
      @Override
      public Adapter casedesign_InfusionType(design_InfusionType object)
      {
        return createdesign_InfusionTypeAdapter();
      }
      @Override
      public Adapter casedesign_DurationType(design_DurationType object)
      {
        return createdesign_DurationTypeAdapter();
      }
      @Override
      public Adapter casedesign_BolusType(design_BolusType object)
      {
        return createdesign_BolusTypeAdapter();
      }
      @Override
      public Adapter casedesign_DosingTimesType(design_DosingTimesType object)
      {
        return createdesign_DosingTimesTypeAdapter();
      }
      @Override
      public Adapter casedesign_SteadyStateType(design_SteadyStateType object)
      {
        return createdesign_SteadyStateTypeAdapter();
      }
      @Override
      public Adapter casedesign_DosingStartType(design_DosingStartType object)
      {
        return createdesign_DosingStartTypeAdapter();
      }
      @Override
      public Adapter casedesign_DosingPeriodType(design_DosingPeriodType object)
      {
        return createdesign_DosingPeriodTypeAdapter();
      }
      @Override
      public Adapter casedesign_DoseAmountType(design_DoseAmountType object)
      {
        return createdesign_DoseAmountTypeAdapter();
      }
      @Override
      public Adapter casedesign_DosingVariableType(design_DosingVariableType object)
      {
        return createdesign_DosingVariableTypeAdapter();
      }
      @Override
      public Adapter casedesign_AmountType(design_AmountType object)
      {
        return createdesign_AmountTypeAdapter();
      }
      @Override
      public Adapter casedesign_TargetVarType(design_TargetVarType object)
      {
        return createdesign_TargetVarTypeAdapter();
      }
      @Override
      public Adapter casedesign_DoseVarType(design_DoseVarType object)
      {
        return createdesign_DoseVarTypeAdapter();
      }
      @Override
      public Adapter casemsteps_ModelingSteps(msteps_ModelingSteps object)
      {
        return createmsteps_ModelingStepsAdapter();
      }
      @Override
      public Adapter casemsteps_StepDependenciesType(msteps_StepDependenciesType object)
      {
        return createmsteps_StepDependenciesTypeAdapter();
      }
      @Override
      public Adapter casemsteps_StepType(msteps_StepType object)
      {
        return createmsteps_StepTypeAdapter();
      }
      @Override
      public Adapter casemsteps_DependantStepType(msteps_DependantStepType object)
      {
        return createmsteps_DependantStepTypeAdapter();
      }
      @Override
      public Adapter casemsteps_EstimationStep(msteps_EstimationStep object)
      {
        return createmsteps_EstimationStepAdapter();
      }
      @Override
      public Adapter casemsteps_ParametersToEstimateType(msteps_ParametersToEstimateType object)
      {
        return createmsteps_ParametersToEstimateTypeAdapter();
      }
      @Override
      public Adapter casemSteps_VariableEstimateType(mSteps_VariableEstimateType object)
      {
        return createmSteps_VariableEstimateTypeAdapter();
      }
      @Override
      public Adapter casemSteps_fixed(mSteps_fixed object)
      {
        return createmSteps_fixedAdapter();
      }
      @Override
      public Adapter casemSteps_UpperBoundType(mSteps_UpperBoundType object)
      {
        return createmSteps_UpperBoundTypeAdapter();
      }
      @Override
      public Adapter casemSteps_LowerBoundType(mSteps_LowerBoundType object)
      {
        return createmSteps_LowerBoundTypeAdapter();
      }
      @Override
      public Adapter casemSteps_InitialEstimateType(mSteps_InitialEstimateType object)
      {
        return createmSteps_InitialEstimateTypeAdapter();
      }
      @Override
      public Adapter casemsteps_ObjectiveDataSetType(msteps_ObjectiveDataSetType object)
      {
        return createmsteps_ObjectiveDataSetTypeAdapter();
      }
      @Override
      public Adapter casemsteps_MappingType(msteps_MappingType object)
      {
        return createmsteps_MappingTypeAdapter();
      }
      @Override
      public Adapter casemsteps_OutputType(msteps_OutputType object)
      {
        return createmsteps_OutputTypeAdapter();
      }
      @Override
      public Adapter casemsteps_AssignType(msteps_AssignType object)
      {
        return createmsteps_AssignTypeAdapter();
      }
      @Override
      public Adapter casemsteps_TargetVarType(msteps_TargetVarType object)
      {
        return createmsteps_TargetVarTypeAdapter();
      }
      @Override
      public Adapter casemsteps_UseVariabilityLevel(msteps_UseVariabilityLevel object)
      {
        return createmsteps_UseVariabilityLevelAdapter();
      }
      @Override
      public Adapter casemsteps_UseVariabilityNode(msteps_UseVariabilityNode object)
      {
        return createmsteps_UseVariabilityNodeAdapter();
      }
      @Override
      public Adapter casemsteps_SimulationStep(msteps_SimulationStep object)
      {
        return createmsteps_SimulationStepAdapter();
      }
      @Override
      public Adapter casemsteps_SimDataSetType(msteps_SimDataSetType object)
      {
        return createmsteps_SimDataSetTypeAdapter();
      }
      @Override
      public Adapter casemsteps_ObservationsType(msteps_ObservationsType object)
      {
        return createmsteps_ObservationsTypeAdapter();
      }
      @Override
      public Adapter casemsteps_WriteToType(msteps_WriteToType object)
      {
        return createmsteps_WriteToTypeAdapter();
      }
      @Override
      public Adapter casemsteps_TimepointsType(msteps_TimepointsType object)
      {
        return createmsteps_TimepointsTypeAdapter();
      }
      @Override
      public Adapter casemsteps_InitialValueType(msteps_InitialValueType object)
      {
        return createmsteps_InitialValueTypeAdapter();
      }
      @Override
      public Adapter casemsteps_EstimationOperationType(msteps_EstimationOperationType object)
      {
        return createmsteps_EstimationOperationTypeAdapter();
      }
      @Override
      public Adapter caseuncert_DistributionType(uncert_DistributionType object)
      {
        return createuncert_DistributionTypeAdapter();
      }
      @Override
      public Adapter caseuncert_Normal(uncert_Normal object)
      {
        return createuncert_NormalAdapter();
      }
      @Override
      public Adapter caseuncert_CoefficientOfVariance(uncert_CoefficientOfVariance object)
      {
        return createuncert_CoefficientOfVarianceAdapter();
      }
      @Override
      public Adapter caseuncert_StdDev(uncert_StdDev object)
      {
        return createuncert_StdDevAdapter();
      }
      @Override
      public Adapter caseuncert_DistributionParamType(uncert_DistributionParamType object)
      {
        return createuncert_DistributionParamTypeAdapter();
      }
      @Override
      public Adapter caseuncert_MeanParamType(uncert_MeanParamType object)
      {
        return createuncert_MeanParamTypeAdapter();
      }
      @Override
      public Adapter caseuncert_Mean(uncert_Mean object)
      {
        return createuncert_MeanAdapter();
      }
      @Override
      public Adapter caseuncert_Variance(uncert_Variance object)
      {
        return createuncert_VarianceAdapter();
      }
      @Override
      public Adapter caseuncert_PDF(uncert_PDF object)
      {
        return createuncert_PDFAdapter();
      }
      @Override
      public Adapter caseuncert_ParameterDefinitionType(uncert_ParameterDefinitionType object)
      {
        return createuncert_ParameterDefinitionTypeAdapter();
      }
      @Override
      public Adapter caseuncert_Poison(uncert_Poison object)
      {
        return createuncert_PoisonAdapter();
      }
      @Override
      public Adapter caseuncert_Rate(uncert_Rate object)
      {
        return createuncert_RateAdapter();
      }
      @Override
      public Adapter caseuncert_Student_t(uncert_Student_t object)
      {
        return createuncert_Student_tAdapter();
      }
      @Override
      public Adapter caseuncertDegreesOfFreedom(uncertDegreesOfFreedom object)
      {
        return createuncertDegreesOfFreedomAdapter();
      }
      @Override
      public Adapter caseuncert_Scale(uncert_Scale object)
      {
        return createuncert_ScaleAdapter();
      }
      @Override
      public Adapter caseuncert_Location(uncert_Location object)
      {
        return createuncert_LocationAdapter();
      }
      @Override
      public Adapter caseuncert_Uniform(uncert_Uniform object)
      {
        return createuncert_UniformAdapter();
      }
      @Override
      public Adapter casect_symbId(ct_symbId object)
      {
        return createct_symbIdAdapter();
      }
      @Override
      public Adapter casect_id(ct_id object)
      {
        return createct_idAdapter();
      }
      @Override
      public Adapter casect_symbolType(ct_symbolType object)
      {
        return createct_symbolTypeAdapter();
      }
      @Override
      public Adapter casect_block(ct_block object)
      {
        return createct_blockAdapter();
      }
      @Override
      public Adapter casect_writtenVersion(ct_writtenVersion object)
      {
        return createct_writtenVersionAdapter();
      }
      @Override
      public Adapter casect_Decimal_Value(ct_Decimal_Value object)
      {
        return createct_Decimal_ValueAdapter();
      }
      @Override
      public Adapter casect_String_Value(ct_String_Value object)
      {
        return createct_String_ValueAdapter();
      }
      @Override
      public Adapter casect_Int_Value(ct_Int_Value object)
      {
        return createct_Int_ValueAdapter();
      }
      @Override
      public Adapter casect_catId(ct_catId object)
      {
        return createct_catIdAdapter();
      }
      @Override
      public Adapter casect_ReplicatesType(ct_ReplicatesType object)
      {
        return createct_ReplicatesTypeAdapter();
      }
      @Override
      public Adapter casect_AnnotationType(ct_AnnotationType object)
      {
        return createct_AnnotationTypeAdapter();
      }
      @Override
      public Adapter casect_Rhs(ct_Rhs object)
      {
        return createct_RhsAdapter();
      }
      @Override
      public Adapter casect_DataSetType(ct_DataSetType object)
      {
        return createct_DataSetTypeAdapter();
      }
      @Override
      public Adapter casect_DefinitionType(ct_DefinitionType object)
      {
        return createct_DefinitionTypeAdapter();
      }
      @Override
      public Adapter casect_DefinitionRhsType(ct_DefinitionRhsType object)
      {
        return createct_DefinitionRhsTypeAdapter();
      }
      @Override
      public Adapter casect_ColumnType(ct_ColumnType object)
      {
        return createct_ColumnTypeAdapter();
      }
      @Override
      public Adapter casect_ExternalSourceType(ct_ExternalSourceType object)
      {
        return createct_ExternalSourceTypeAdapter();
      }
      @Override
      public Adapter casect_RowType(ct_RowType object)
      {
        return createct_RowTypeAdapter();
      }
      @Override
      public Adapter casect_RowTypeContent(ct_RowTypeContent object)
      {
        return createct_RowTypeContentAdapter();
      }
      @Override
      public Adapter casect_CellType(ct_CellType object)
      {
        return createct_CellTypeAdapter();
      }
      @Override
      public Adapter casect_InternalSourceType(ct_InternalSourceType object)
      {
        return createct_InternalSourceTypeAdapter();
      }
      @Override
      public Adapter casect_ScalarRhs(ct_ScalarRhs object)
      {
        return createct_ScalarRhsAdapter();
      }
      @Override
      public Adapter casect_levelId(ct_levelId object)
      {
        return createct_levelIdAdapter();
      }
      @Override
      public Adapter casect_Name(ct_Name object)
      {
        return createct_NameAdapter();
      }
      @Override
      public Adapter casect_VectorType(ct_VectorType object)
      {
        return createct_VectorTypeAdapter();
      }
      @Override
      public Adapter casect_SequenceType(ct_SequenceType object)
      {
        return createct_SequenceTypeAdapter();
      }
      @Override
      public Adapter casect_SymbolTypeType(ct_SymbolTypeType object)
      {
        return createct_SymbolTypeTypeAdapter();
      }
      @Override
      public Adapter casect_VariableDefinitionType(ct_VariableDefinitionType object)
      {
        return createct_VariableDefinitionTypeAdapter();
      }
      @Override
      public Adapter casect_VariableAssignmentType(ct_VariableAssignmentType object)
      {
        return createct_VariableAssignmentTypeAdapter();
      }
      @Override
      public Adapter casect_RestrictionType(ct_RestrictionType object)
      {
        return createct_RestrictionTypeAdapter();
      }
      @Override
      public Adapter casect_xmlns(ct_xmlns object)
      {
        return createct_xmlnsAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Mml_PharmaML <em>Mml Pharma ML</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML
   * @generated
   */
  public Adapter createMml_PharmaMLAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType <em>Mml Symbol Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType
   * @generated
   */
  public Adapter createMml_SymbolDefinitionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType <em>Mml Function Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType
   * @generated
   */
  public Adapter createMml_FunctionDefinitionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType <em>Mml Func Parameter Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType
   * @generated
   */
  public Adapter createMml_FuncParameterDefinitionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_EquationType <em>Math Equation Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_EquationType
   * @generated
   */
  public Adapter createMath_EquationTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_EquationTypeContent <em>Math Equation Type Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_EquationTypeContent
   * @generated
   */
  public Adapter createMath_EquationTypeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_ScalarType <em>Math Scalar Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_ScalarType
   * @generated
   */
  public Adapter createMath_ScalarTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_VarType <em>Math Var Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_VarType
   * @generated
   */
  public Adapter createMath_VarTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_BinopType <em>Math Binop Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_BinopType
   * @generated
   */
  public Adapter createMath_BinopTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_UniopType <em>Math Uniop Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_UniopType
   * @generated
   */
  public Adapter createMath_UniopTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_ExprType <em>Math Expr Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_ExprType
   * @generated
   */
  public Adapter createMath_ExprTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_ConstantType <em>Math Constant Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_ConstantType
   * @generated
   */
  public Adapter createMath_ConstantTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_FunctionCallType <em>Math Function Call Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_FunctionCallType
   * @generated
   */
  public Adapter createMath_FunctionCallTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_FunctionArgumentType <em>Math Function Argument Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_FunctionArgumentType
   * @generated
   */
  public Adapter createMath_FunctionArgumentTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_FuncExprType <em>Math Func Expr Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_FuncExprType
   * @generated
   */
  public Adapter createMath_FuncExprTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_PiecewiseType <em>Math Piecewise Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_PiecewiseType
   * @generated
   */
  public Adapter createMath_PiecewiseTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_PieceType <em>Math Piece Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_PieceType
   * @generated
   */
  public Adapter createMath_PieceTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_ConditionType <em>Math Condition Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_ConditionType
   * @generated
   */
  public Adapter createMath_ConditionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_LogicBaseType <em>Math Logic Base Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_LogicBaseType
   * @generated
   */
  public Adapter createMath_LogicBaseTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_LogicExprType <em>Math Logic Expr Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_LogicExprType
   * @generated
   */
  public Adapter createMath_LogicExprTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_StringType <em>Math String Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_StringType
   * @generated
   */
  public Adapter createMath_StringTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_TRUE <em>Math TRUE</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_TRUE
   * @generated
   */
  public Adapter createMath_TRUEAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_FALSE <em>Math FALSE</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_FALSE
   * @generated
   */
  public Adapter createMath_FALSEAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_LogicBinopType <em>Math Logic Binop Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopType
   * @generated
   */
  public Adapter createMath_LogicBinopTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_LogicUniopType <em>Math Logic Uniop Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_LogicUniopType
   * @generated
   */
  public Adapter createMath_LogicUniopTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_BinopName <em>Math Binop Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_BinopName
   * @generated
   */
  public Adapter createMath_BinopNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_UniopName <em>Math Uniop Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_UniopName
   * @generated
   */
  public Adapter createMath_UniopNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_LogicUniopName <em>Math Logic Uniop Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_LogicUniopName
   * @generated
   */
  public Adapter createMath_LogicUniopNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName <em>Math Logic Binop Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName
   * @generated
   */
  public Adapter createMath_LogicBinopNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_ConstantName <em>Math Constant Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_ConstantName
   * @generated
   */
  public Adapter createMath_ConstantNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_symbId <em>Math symb Id</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_symbId
   * @generated
   */
  public Adapter createMath_symbIdAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_id <em>Math id</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_id
   * @generated
   */
  public Adapter createMath_idAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_block <em>Math block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_block
   * @generated
   */
  public Adapter createMath_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_independentVar <em>ct independent Var</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_independentVar
   * @generated
   */
  public Adapter createct_independentVarAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.Math_EstimationOperationType <em>Math Estimation Operation Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.Math_EstimationOperationType
   * @generated
   */
  public Adapter createMath_EstimationOperationTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ParameterTypeName <em>mdef Parameter Type Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterTypeName
   * @generated
   */
  public Adapter createmdef_ParameterTypeNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ModelDefinitionType <em>mdef Model Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ModelDefinitionType
   * @generated
   */
  public Adapter createmdef_ModelDefinitionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_StructuralModelType <em>mdef Structural Model Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_StructuralModelType
   * @generated
   */
  public Adapter createmdef_StructuralModelTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ObservationModelType <em>mdef Observation Model Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ObservationModelType
   * @generated
   */
  public Adapter createmdef_ObservationModelTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ParameterModelType <em>mdef Parameter Model Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterModelType
   * @generated
   */
  public Adapter createmdef_ParameterModelTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_CorrelationType <em>mdef Correlation Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationType
   * @generated
   */
  public Adapter createmdef_CorrelationTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdefCovarianceType <em>mdef Covariance Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdefCovarianceType
   * @generated
   */
  public Adapter createmdefCovarianceTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType <em>mdef Correlation Coefficient Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType
   * @generated
   */
  public Adapter createmdef_CorrelationCoefficientTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_CovariateModelType <em>mdef Covariate Model Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateModelType
   * @generated
   */
  public Adapter createmdef_CovariateModelTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ParameterType <em>mdef Parameter Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType
   * @generated
   */
  public Adapter createmdef_ParameterTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType <em>mdef Covariate Variability Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType
   * @generated
   */
  public Adapter createmdef_CovariateVariabilityTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_transformation <em>mdef transformation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_transformation
   * @generated
   */
  public Adapter createmdef_transformationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_CategoricalType <em>mdef Categorical Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_CategoricalType
   * @generated
   */
  public Adapter createmdef_CategoricalTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ContinuousType <em>mdef Continuous Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ContinuousType
   * @generated
   */
  public Adapter createmdef_ContinuousTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_TransformationType <em>mdef Transformation Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_TransformationType
   * @generated
   */
  public Adapter createmdef_TransformationTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType <em>mdef Variability Level Defn Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType
   * @generated
   */
  public Adapter createmdef_VariabilityLevelDefnTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ImportType <em>mdef Import Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ImportType
   * @generated
   */
  public Adapter createmdef_ImportTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ImportLinkType <em>mdef Import Link Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ImportLinkType
   * @generated
   */
  public Adapter createmdef_ImportLinkTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType <em>mdef Import Resource Type Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType
   * @generated
   */
  public Adapter createmdef_ImportResourceTypeTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_CovariateType <em>mdef Covariate Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateType
   * @generated
   */
  public Adapter createmdef_CovariateTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_FixedEffectType <em>mdef Fixed Effect Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_FixedEffectType
   * @generated
   */
  public Adapter createmdef_FixedEffectTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_CategoryType <em>mdef Category Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_CategoryType
   * @generated
   */
  public Adapter createmdef_CategoryTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_ProbabilityType <em>mdef Probability Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_ProbabilityType
   * @generated
   */
  public Adapter createmdef_ProbabilityTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType <em>mdef Random Effect Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_RandomEffectType
   * @generated
   */
  public Adapter createmdef_RandomEffectTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mdef_InitialConditionType <em>mdef Initial Condition Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mdef_InitialConditionType
   * @generated
   */
  public Adapter createmdef_InitialConditionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_TrialDesignType <em>design Trial Design Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_TrialDesignType
   * @generated
   */
  public Adapter createdesign_TrialDesignTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_GroupType <em>design Group Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_GroupType
   * @generated
   */
  public Adapter createdesign_GroupTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_IdividualsType <em>design Idividuals Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_IdividualsType
   * @generated
   */
  public Adapter createdesign_IdividualsTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_WashoutType <em>design Washout Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_WashoutType
   * @generated
   */
  public Adapter createdesign_WashoutTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType <em>design Treatment Epoh Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_TreatmentEpohType
   * @generated
   */
  public Adapter createdesign_TreatmentEpohTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_OccasionType <em>design Occasion Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_OccasionType
   * @generated
   */
  public Adapter createdesign_OccasionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_BeginType <em>design Begin Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_BeginType
   * @generated
   */
  public Adapter createdesign_BeginTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_EndType <em>design End Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_EndType
   * @generated
   */
  public Adapter createdesign_EndTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_StartType <em>design Start Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_StartType
   * @generated
   */
  public Adapter createdesign_StartTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_TreatmentRefType <em>design Treatment Ref Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_TreatmentRefType
   * @generated
   */
  public Adapter createdesign_TreatmentRefTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_TreatmentType <em>design Treatment Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_TreatmentType
   * @generated
   */
  public Adapter createdesign_TreatmentTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_DosingRegimenType <em>design Dosing Regimen Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_DosingRegimenType
   * @generated
   */
  public Adapter createdesign_DosingRegimenTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_InfusionType <em>design Infusion Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_InfusionType
   * @generated
   */
  public Adapter createdesign_InfusionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_DurationType <em>design Duration Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_DurationType
   * @generated
   */
  public Adapter createdesign_DurationTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_BolusType <em>design Bolus Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_BolusType
   * @generated
   */
  public Adapter createdesign_BolusTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_DosingTimesType <em>design Dosing Times Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_DosingTimesType
   * @generated
   */
  public Adapter createdesign_DosingTimesTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_SteadyStateType <em>design Steady State Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_SteadyStateType
   * @generated
   */
  public Adapter createdesign_SteadyStateTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_DosingStartType <em>design Dosing Start Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_DosingStartType
   * @generated
   */
  public Adapter createdesign_DosingStartTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_DosingPeriodType <em>design Dosing Period Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_DosingPeriodType
   * @generated
   */
  public Adapter createdesign_DosingPeriodTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_DoseAmountType <em>design Dose Amount Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_DoseAmountType
   * @generated
   */
  public Adapter createdesign_DoseAmountTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_DosingVariableType <em>design Dosing Variable Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_DosingVariableType
   * @generated
   */
  public Adapter createdesign_DosingVariableTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_AmountType <em>design Amount Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_AmountType
   * @generated
   */
  public Adapter createdesign_AmountTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_TargetVarType <em>design Target Var Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_TargetVarType
   * @generated
   */
  public Adapter createdesign_TargetVarTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.design_DoseVarType <em>design Dose Var Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.design_DoseVarType
   * @generated
   */
  public Adapter createdesign_DoseVarTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_ModelingSteps <em>msteps Modeling Steps</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_ModelingSteps
   * @generated
   */
  public Adapter createmsteps_ModelingStepsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_StepDependenciesType <em>msteps Step Dependencies Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_StepDependenciesType
   * @generated
   */
  public Adapter createmsteps_StepDependenciesTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_StepType <em>msteps Step Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_StepType
   * @generated
   */
  public Adapter createmsteps_StepTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_DependantStepType <em>msteps Dependant Step Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_DependantStepType
   * @generated
   */
  public Adapter createmsteps_DependantStepTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_EstimationStep <em>msteps Estimation Step</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationStep
   * @generated
   */
  public Adapter createmsteps_EstimationStepAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_ParametersToEstimateType <em>msteps Parameters To Estimate Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_ParametersToEstimateType
   * @generated
   */
  public Adapter createmsteps_ParametersToEstimateTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType <em>mSteps Variable Estimate Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mSteps_VariableEstimateType
   * @generated
   */
  public Adapter createmSteps_VariableEstimateTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mSteps_fixed <em>mSteps fixed</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mSteps_fixed
   * @generated
   */
  public Adapter createmSteps_fixedAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mSteps_UpperBoundType <em>mSteps Upper Bound Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mSteps_UpperBoundType
   * @generated
   */
  public Adapter createmSteps_UpperBoundTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mSteps_LowerBoundType <em>mSteps Lower Bound Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mSteps_LowerBoundType
   * @generated
   */
  public Adapter createmSteps_LowerBoundTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.mSteps_InitialEstimateType <em>mSteps Initial Estimate Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.mSteps_InitialEstimateType
   * @generated
   */
  public Adapter createmSteps_InitialEstimateTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType <em>msteps Objective Data Set Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType
   * @generated
   */
  public Adapter createmsteps_ObjectiveDataSetTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_MappingType <em>msteps Mapping Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType
   * @generated
   */
  public Adapter createmsteps_MappingTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_OutputType <em>msteps Output Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_OutputType
   * @generated
   */
  public Adapter createmsteps_OutputTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_AssignType <em>msteps Assign Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_AssignType
   * @generated
   */
  public Adapter createmsteps_AssignTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_TargetVarType <em>msteps Target Var Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_TargetVarType
   * @generated
   */
  public Adapter createmsteps_TargetVarTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel <em>msteps Use Variability Level</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel
   * @generated
   */
  public Adapter createmsteps_UseVariabilityLevelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityNode <em>msteps Use Variability Node</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_UseVariabilityNode
   * @generated
   */
  public Adapter createmsteps_UseVariabilityNodeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_SimulationStep <em>msteps Simulation Step</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_SimulationStep
   * @generated
   */
  public Adapter createmsteps_SimulationStepAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_SimDataSetType <em>msteps Sim Data Set Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_SimDataSetType
   * @generated
   */
  public Adapter createmsteps_SimDataSetTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_ObservationsType <em>msteps Observations Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_ObservationsType
   * @generated
   */
  public Adapter createmsteps_ObservationsTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_WriteToType <em>msteps Write To Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_WriteToType
   * @generated
   */
  public Adapter createmsteps_WriteToTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_TimepointsType <em>msteps Timepoints Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_TimepointsType
   * @generated
   */
  public Adapter createmsteps_TimepointsTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_InitialValueType <em>msteps Initial Value Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_InitialValueType
   * @generated
   */
  public Adapter createmsteps_InitialValueTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.msteps_EstimationOperationType <em>msteps Estimation Operation Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationOperationType
   * @generated
   */
  public Adapter createmsteps_EstimationOperationTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_DistributionType <em>uncert Distribution Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionType
   * @generated
   */
  public Adapter createuncert_DistributionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_Normal <em>uncert Normal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_Normal
   * @generated
   */
  public Adapter createuncert_NormalAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_CoefficientOfVariance <em>uncert Coefficient Of Variance</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_CoefficientOfVariance
   * @generated
   */
  public Adapter createuncert_CoefficientOfVarianceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_StdDev <em>uncert Std Dev</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_StdDev
   * @generated
   */
  public Adapter createuncert_StdDevAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_DistributionParamType <em>uncert Distribution Param Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionParamType
   * @generated
   */
  public Adapter createuncert_DistributionParamTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_MeanParamType <em>uncert Mean Param Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_MeanParamType
   * @generated
   */
  public Adapter createuncert_MeanParamTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_Mean <em>uncert Mean</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_Mean
   * @generated
   */
  public Adapter createuncert_MeanAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_Variance <em>uncert Variance</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_Variance
   * @generated
   */
  public Adapter createuncert_VarianceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_PDF <em>uncert PDF</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_PDF
   * @generated
   */
  public Adapter createuncert_PDFAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType <em>uncert Parameter Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType
   * @generated
   */
  public Adapter createuncert_ParameterDefinitionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_Poison <em>uncert Poison</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_Poison
   * @generated
   */
  public Adapter createuncert_PoisonAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_Rate <em>uncert Rate</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_Rate
   * @generated
   */
  public Adapter createuncert_RateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_Student_t <em>uncert Student t</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_Student_t
   * @generated
   */
  public Adapter createuncert_Student_tAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncertDegreesOfFreedom <em>uncert Degrees Of Freedom</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncertDegreesOfFreedom
   * @generated
   */
  public Adapter createuncertDegreesOfFreedomAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_Scale <em>uncert Scale</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_Scale
   * @generated
   */
  public Adapter createuncert_ScaleAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_Location <em>uncert Location</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_Location
   * @generated
   */
  public Adapter createuncert_LocationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.uncert_Uniform <em>uncert Uniform</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.uncert_Uniform
   * @generated
   */
  public Adapter createuncert_UniformAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_symbId <em>ct symb Id</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_symbId
   * @generated
   */
  public Adapter createct_symbIdAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_id <em>ct id</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_id
   * @generated
   */
  public Adapter createct_idAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_symbolType <em>ct symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_symbolType
   * @generated
   */
  public Adapter createct_symbolTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_block <em>ct block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_block
   * @generated
   */
  public Adapter createct_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_writtenVersion <em>ct written Version</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_writtenVersion
   * @generated
   */
  public Adapter createct_writtenVersionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_Decimal_Value <em>ct Decimal Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_Decimal_Value
   * @generated
   */
  public Adapter createct_Decimal_ValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_String_Value <em>ct String Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_String_Value
   * @generated
   */
  public Adapter createct_String_ValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_Int_Value <em>ct Int Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_Int_Value
   * @generated
   */
  public Adapter createct_Int_ValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_catId <em>ct cat Id</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_catId
   * @generated
   */
  public Adapter createct_catIdAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_ReplicatesType <em>ct Replicates Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_ReplicatesType
   * @generated
   */
  public Adapter createct_ReplicatesTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_AnnotationType <em>ct Annotation Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_AnnotationType
   * @generated
   */
  public Adapter createct_AnnotationTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_Rhs <em>ct Rhs</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_Rhs
   * @generated
   */
  public Adapter createct_RhsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_DataSetType <em>ct Data Set Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_DataSetType
   * @generated
   */
  public Adapter createct_DataSetTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_DefinitionType <em>ct Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_DefinitionType
   * @generated
   */
  public Adapter createct_DefinitionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_DefinitionRhsType <em>ct Definition Rhs Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_DefinitionRhsType
   * @generated
   */
  public Adapter createct_DefinitionRhsTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_ColumnType <em>ct Column Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_ColumnType
   * @generated
   */
  public Adapter createct_ColumnTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType <em>ct External Source Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_ExternalSourceType
   * @generated
   */
  public Adapter createct_ExternalSourceTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_RowType <em>ct Row Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_RowType
   * @generated
   */
  public Adapter createct_RowTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_RowTypeContent <em>ct Row Type Content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_RowTypeContent
   * @generated
   */
  public Adapter createct_RowTypeContentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_CellType <em>ct Cell Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_CellType
   * @generated
   */
  public Adapter createct_CellTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_InternalSourceType <em>ct Internal Source Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_InternalSourceType
   * @generated
   */
  public Adapter createct_InternalSourceTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_ScalarRhs <em>ct Scalar Rhs</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_ScalarRhs
   * @generated
   */
  public Adapter createct_ScalarRhsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_levelId <em>ct level Id</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_levelId
   * @generated
   */
  public Adapter createct_levelIdAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_Name <em>ct Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_Name
   * @generated
   */
  public Adapter createct_NameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_VectorType <em>ct Vector Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_VectorType
   * @generated
   */
  public Adapter createct_VectorTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_SequenceType <em>ct Sequence Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_SequenceType
   * @generated
   */
  public Adapter createct_SequenceTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_SymbolTypeType <em>ct Symbol Type Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_SymbolTypeType
   * @generated
   */
  public Adapter createct_SymbolTypeTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType <em>ct Variable Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_VariableDefinitionType
   * @generated
   */
  public Adapter createct_VariableDefinitionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_VariableAssignmentType <em>ct Variable Assignment Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_VariableAssignmentType
   * @generated
   */
  public Adapter createct_VariableAssignmentTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_RestrictionType <em>ct Restriction Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_RestrictionType
   * @generated
   */
  public Adapter createct_RestrictionTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.pml.pharmaML.ct_xmlns <em>ct xmlns</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.pml.pharmaML.ct_xmlns
   * @generated
   */
  public Adapter createct_xmlnsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //PharmaMLAdapterFactory

/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.io.IOException;

import java.net.URL;

import org.ddmore.pml.pharmaML.PharmaMLFactory;
import org.ddmore.pml.pharmaML.PharmaMLPackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PharmaMLPackageImpl extends EPackageImpl implements PharmaMLPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected String packageFilename = "pharmaML.ecore";

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mml_PharmaMLEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mml_SymbolDefinitionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mml_FunctionDefinitionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mml_FuncParameterDefinitionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_EquationTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_EquationTypeContentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_ScalarTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_VarTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_BinopTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_UniopTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_ExprTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_ConstantTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_FunctionCallTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_FunctionArgumentTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_FuncExprTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_PiecewiseTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_PieceTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_ConditionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_LogicBaseTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_LogicExprTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_StringTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_TRUEEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_FALSEEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_LogicBinopTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_LogicUniopTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_BinopNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_UniopNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_LogicUniopNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_LogicBinopNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_ConstantNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_symbIdEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_idEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_independentVarEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass math_EstimationOperationTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ParameterTypeNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ModelDefinitionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_StructuralModelTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ObservationModelTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ParameterModelTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_CorrelationTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdefCovarianceTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_CorrelationCoefficientTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_CovariateModelTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ParameterTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_CovariateVariabilityTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_transformationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_CategoricalTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ContinuousTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_TransformationTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_VariabilityLevelDefnTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ImportTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ImportLinkTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ImportResourceTypeTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_CovariateTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_FixedEffectTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_CategoryTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_ProbabilityTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_RandomEffectTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mdef_InitialConditionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_TrialDesignTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_GroupTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_IdividualsTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_WashoutTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_TreatmentEpohTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_OccasionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_BeginTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_EndTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_StartTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_TreatmentRefTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_TreatmentTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_DosingRegimenTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_InfusionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_DurationTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_BolusTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_DosingTimesTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_SteadyStateTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_DosingStartTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_DosingPeriodTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_DoseAmountTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_DosingVariableTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_AmountTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_TargetVarTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_DoseVarTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_ModelingStepsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_StepDependenciesTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_StepTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_DependantStepTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_EstimationStepEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_ParametersToEstimateTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mSteps_VariableEstimateTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mSteps_fixedEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mSteps_UpperBoundTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mSteps_LowerBoundTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mSteps_InitialEstimateTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_ObjectiveDataSetTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_MappingTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_OutputTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_AssignTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_TargetVarTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_UseVariabilityLevelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_UseVariabilityNodeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_SimulationStepEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_SimDataSetTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_ObservationsTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_WriteToTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_TimepointsTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_InitialValueTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass msteps_EstimationOperationTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_DistributionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_NormalEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_CoefficientOfVarianceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_StdDevEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_DistributionParamTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_MeanParamTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_MeanEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_VarianceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_PDFEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_ParameterDefinitionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_PoisonEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_RateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_Student_tEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncertDegreesOfFreedomEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_ScaleEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_LocationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass uncert_UniformEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_symbIdEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_idEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_symbolTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_writtenVersionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_Decimal_ValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_String_ValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_Int_ValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_catIdEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_ReplicatesTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_AnnotationTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_RhsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_DataSetTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_DefinitionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_DefinitionRhsTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_ColumnTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_ExternalSourceTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_RowTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_RowTypeContentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_CellTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_InternalSourceTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_ScalarRhsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_levelIdEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_NameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_VectorTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_SequenceTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_SymbolTypeTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_VariableDefinitionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_VariableAssignmentTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_RestrictionTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ct_xmlnsEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private PharmaMLPackageImpl()
  {
    super(eNS_URI, PharmaMLFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link PharmaMLPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @generated
   */
  public static PharmaMLPackage init()
  {
    if (isInited) return (PharmaMLPackage)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI);

    // Obtain or create and register package
    PharmaMLPackageImpl thePharmaMLPackage = (PharmaMLPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PharmaMLPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PharmaMLPackageImpl());

    isInited = true;

    // Load packages
    thePharmaMLPackage.loadPackage();

    // Fix loaded packages
    thePharmaMLPackage.fixPackageContents();

    // Mark meta-data to indicate it can't be changed
    thePharmaMLPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(PharmaMLPackage.eNS_URI, thePharmaMLPackage);
    return thePharmaMLPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMml_PharmaML()
  {
    if (mml_PharmaMLEClass == null)
    {
      mml_PharmaMLEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(0);
    }
    return mml_PharmaMLEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_PharmaML_Xmlns()
  {
        return (EReference)getMml_PharmaML().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_PharmaML_Name()
  {
        return (EReference)getMml_PharmaML().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_PharmaML_IndependentVar()
  {
        return (EReference)getMml_PharmaML().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_PharmaML_WrittenVersion()
  {
        return (EReference)getMml_PharmaML().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_PharmaML_SymbolDefinition()
  {
        return (EReference)getMml_PharmaML().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_PharmaML_StructuralModel()
  {
        return (EReference)getMml_PharmaML().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_PharmaML_ModelDefinition()
  {
        return (EReference)getMml_PharmaML().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_PharmaML_Design()
  {
        return (EReference)getMml_PharmaML().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_PharmaML_ModelingSteps()
  {
        return (EReference)getMml_PharmaML().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMml_SymbolDefinitionType()
  {
    if (mml_SymbolDefinitionTypeEClass == null)
    {
      mml_SymbolDefinitionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(1);
    }
    return mml_SymbolDefinitionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_SymbolDefinitionType_SymbId()
  {
        return (EReference)getMml_SymbolDefinitionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_SymbolDefinitionType_SymbolType()
  {
        return (EReference)getMml_SymbolDefinitionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_SymbolDefinitionType_Name()
  {
        return (EReference)getMml_SymbolDefinitionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_SymbolDefinitionType_Xmlns()
  {
        return (EReference)getMml_SymbolDefinitionType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_SymbolDefinitionType_Description()
  {
        return (EReference)getMml_SymbolDefinitionType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_SymbolDefinitionType_FunctionDefinition()
  {
        return (EReference)getMml_SymbolDefinitionType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMml_FunctionDefinitionType()
  {
    if (mml_FunctionDefinitionTypeEClass == null)
    {
      mml_FunctionDefinitionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(2);
    }
    return mml_FunctionDefinitionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_FunctionDefinitionType_Xmlns()
  {
        return (EReference)getMml_FunctionDefinitionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_FunctionDefinitionType_FunctionArguments()
  {
        return (EReference)getMml_FunctionDefinitionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_FunctionDefinitionType_Definition()
  {
        return (EReference)getMml_FunctionDefinitionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMml_FuncParameterDefinitionType()
  {
    if (mml_FuncParameterDefinitionTypeEClass == null)
    {
      mml_FuncParameterDefinitionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(3);
    }
    return mml_FuncParameterDefinitionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_FuncParameterDefinitionType_SumbId()
  {
        return (EReference)getMml_FuncParameterDefinitionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_FuncParameterDefinitionType_SymbolType()
  {
        return (EReference)getMml_FuncParameterDefinitionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_FuncParameterDefinitionType_Xmlns()
  {
        return (EReference)getMml_FuncParameterDefinitionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMml_FuncParameterDefinitionType_Decsription()
  {
        return (EReference)getMml_FuncParameterDefinitionType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_EquationType()
  {
    if (math_EquationTypeEClass == null)
    {
      math_EquationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(4);
    }
    return math_EquationTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_EquationType_Xmlns()
  {
        return (EReference)getMath_EquationType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_EquationType_WrittenVersion()
  {
        return (EReference)getMath_EquationType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_EquationType_Expr()
  {
        return (EReference)getMath_EquationType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_EquationTypeContent()
  {
    if (math_EquationTypeContentEClass == null)
    {
      math_EquationTypeContentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(5);
    }
    return math_EquationTypeContentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_EquationTypeContent_Scalar()
  {
        return (EReference)getMath_EquationTypeContent().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_EquationTypeContent_Var()
  {
        return (EReference)getMath_EquationTypeContent().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_EquationTypeContent_Binop()
  {
        return (EReference)getMath_EquationTypeContent().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_EquationTypeContent_Uniop()
  {
        return (EReference)getMath_EquationTypeContent().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_EquationTypeContent_Piecewise()
  {
        return (EReference)getMath_EquationTypeContent().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_ScalarType()
  {
    if (math_ScalarTypeEClass == null)
    {
      math_ScalarTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(6);
    }
    return math_ScalarTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ScalarType_Value()
  {
        return (EReference)getMath_ScalarType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ScalarType_Xmlns()
  {
        return (EReference)getMath_ScalarType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_VarType()
  {
    if (math_VarTypeEClass == null)
    {
      math_VarTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(7);
    }
    return math_VarTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_VarType_Block()
  {
        return (EReference)getMath_VarType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_VarType_SymbId()
  {
        return (EReference)getMath_VarType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_VarType_Xmlns()
  {
        return (EReference)getMath_VarType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_BinopType()
  {
    if (math_BinopTypeEClass == null)
    {
      math_BinopTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(8);
    }
    return math_BinopTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_BinopType_Op()
  {
        return (EReference)getMath_BinopType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_BinopType_Xmlns()
  {
        return (EReference)getMath_BinopType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_BinopType_LeftExpr()
  {
        return (EReference)getMath_BinopType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_BinopType_RightExpr()
  {
        return (EReference)getMath_BinopType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_UniopType()
  {
    if (math_UniopTypeEClass == null)
    {
      math_UniopTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(9);
    }
    return math_UniopTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_UniopType_Op()
  {
        return (EReference)getMath_UniopType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_UniopType_Xmlns()
  {
        return (EReference)getMath_UniopType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_UniopType_Expr()
  {
        return (EReference)getMath_UniopType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_ExprType()
  {
    if (math_ExprTypeEClass == null)
    {
      math_ExprTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(10);
    }
    return math_ExprTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ExprType_Scalar()
  {
        return (EReference)getMath_ExprType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ExprType_Var()
  {
        return (EReference)getMath_ExprType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ExprType_Uniop()
  {
        return (EReference)getMath_ExprType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ExprType_Binop()
  {
        return (EReference)getMath_ExprType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ExprType_Constant()
  {
        return (EReference)getMath_ExprType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ExprType_FunctionCall()
  {
        return (EReference)getMath_ExprType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_ConstantType()
  {
    if (math_ConstantTypeEClass == null)
    {
      math_ConstantTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(11);
    }
    return math_ConstantTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ConstantType_Op()
  {
        return (EReference)getMath_ConstantType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ConstantType_Xmlns()
  {
        return (EReference)getMath_ConstantType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_FunctionCallType()
  {
    if (math_FunctionCallTypeEClass == null)
    {
      math_FunctionCallTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(12);
    }
    return math_FunctionCallTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FunctionCallType_Xmlns()
  {
        return (EReference)getMath_FunctionCallType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FunctionCallType_FunctionName()
  {
        return (EReference)getMath_FunctionCallType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FunctionCallType_FunctionArguments()
  {
        return (EReference)getMath_FunctionCallType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_FunctionArgumentType()
  {
    if (math_FunctionArgumentTypeEClass == null)
    {
      math_FunctionArgumentTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(13);
    }
    return math_FunctionArgumentTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FunctionArgumentType_SymbId()
  {
        return (EReference)getMath_FunctionArgumentType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FunctionArgumentType_Xmlns()
  {
        return (EReference)getMath_FunctionArgumentType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FunctionArgumentType_Expr()
  {
        return (EReference)getMath_FunctionArgumentType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_FuncExprType()
  {
    if (math_FuncExprTypeEClass == null)
    {
      math_FuncExprTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(14);
    }
    return math_FuncExprTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FuncExprType_Equation()
  {
        return (EReference)getMath_FuncExprType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FuncExprType_Scalar()
  {
        return (EReference)getMath_FuncExprType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FuncExprType_Constant()
  {
        return (EReference)getMath_FuncExprType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_FuncExprType_Var()
  {
        return (EReference)getMath_FuncExprType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_PiecewiseType()
  {
    if (math_PiecewiseTypeEClass == null)
    {
      math_PiecewiseTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(15);
    }
    return math_PiecewiseTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_PiecewiseType_Xmlns()
  {
        return (EReference)getMath_PiecewiseType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_PiecewiseType_Piecewise()
  {
        return (EReference)getMath_PiecewiseType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_PieceType()
  {
    if (math_PieceTypeEClass == null)
    {
      math_PieceTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(16);
    }
    return math_PieceTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_PieceType_Xmlns()
  {
        return (EReference)getMath_PieceType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_PieceType_Content()
  {
        return (EReference)getMath_PieceType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_PieceType_Condition()
  {
        return (EReference)getMath_PieceType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_ConditionType()
  {
    if (math_ConditionTypeEClass == null)
    {
      math_ConditionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(17);
    }
    return math_ConditionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ConditionType_WrittenVersion()
  {
        return (EReference)getMath_ConditionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ConditionType_Xmlns()
  {
        return (EReference)getMath_ConditionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_ConditionType_Expr()
  {
        return (EReference)getMath_ConditionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_LogicBaseType()
  {
    if (math_LogicBaseTypeEClass == null)
    {
      math_LogicBaseTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(18);
    }
    return math_LogicBaseTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicBaseType_LogicBinopType()
  {
        return (EReference)getMath_LogicBaseType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicBaseType_LogicUniopType()
  {
        return (EReference)getMath_LogicBaseType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicBaseType_True()
  {
        return (EReference)getMath_LogicBaseType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicBaseType_False()
  {
        return (EReference)getMath_LogicBaseType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicBaseType_Scalar()
  {
        return (EReference)getMath_LogicBaseType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_LogicExprType()
  {
    if (math_LogicExprTypeEClass == null)
    {
      math_LogicExprTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(19);
    }
    return math_LogicExprTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicExprType_Constant()
  {
        return (EReference)getMath_LogicExprType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicExprType_Var()
  {
        return (EReference)getMath_LogicExprType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicExprType_Uniop()
  {
        return (EReference)getMath_LogicExprType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicExprType_Binop()
  {
        return (EReference)getMath_LogicExprType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicExprType_FunctionCall()
  {
        return (EReference)getMath_LogicExprType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicExprType_String()
  {
        return (EReference)getMath_LogicExprType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_StringType()
  {
    if (math_StringTypeEClass == null)
    {
      math_StringTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(20);
    }
    return math_StringTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_StringType_Value()
  {
        return (EReference)getMath_StringType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_StringType_Xmlns()
  {
        return (EReference)getMath_StringType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_TRUE()
  {
    if (math_TRUEEClass == null)
    {
      math_TRUEEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(21);
    }
    return math_TRUEEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_TRUE_True()
  {
        return (EAttribute)getMath_TRUE().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_FALSE()
  {
    if (math_FALSEEClass == null)
    {
      math_FALSEEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(22);
    }
    return math_FALSEEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_FALSE_False()
  {
        return (EAttribute)getMath_FALSE().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_LogicBinopType()
  {
    if (math_LogicBinopTypeEClass == null)
    {
      math_LogicBinopTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(23);
    }
    return math_LogicBinopTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicBinopType_Op()
  {
        return (EReference)getMath_LogicBinopType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicBinopType_Xmlns()
  {
        return (EReference)getMath_LogicBinopType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicBinopType_LeftExpr()
  {
        return (EReference)getMath_LogicBinopType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicBinopType_RightExpr()
  {
        return (EReference)getMath_LogicBinopType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_LogicUniopType()
  {
    if (math_LogicUniopTypeEClass == null)
    {
      math_LogicUniopTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(24);
    }
    return math_LogicUniopTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicUniopType_Op()
  {
        return (EReference)getMath_LogicUniopType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicUniopType_Xmlns()
  {
        return (EReference)getMath_LogicUniopType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMath_LogicUniopType_Expr()
  {
        return (EReference)getMath_LogicUniopType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_BinopName()
  {
    if (math_BinopNameEClass == null)
    {
      math_BinopNameEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(25);
    }
    return math_BinopNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_BinopName_BinopName()
  {
        return (EAttribute)getMath_BinopName().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_BinopName_Minus()
  {
        return (EAttribute)getMath_BinopName().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_BinopName_Times()
  {
        return (EAttribute)getMath_BinopName().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_BinopName_Divide()
  {
        return (EAttribute)getMath_BinopName().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_BinopName_Power()
  {
        return (EAttribute)getMath_BinopName().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_BinopName_Log()
  {
        return (EAttribute)getMath_BinopName().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_BinopName_Root()
  {
        return (EAttribute)getMath_BinopName().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_UniopName()
  {
    if (math_UniopNameEClass == null)
    {
      math_UniopNameEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(26);
    }
    return math_UniopNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Exp()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Ln()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Minus()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Factorial()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Sin()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Cos()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Tan()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Sec()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Csc()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Cot()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Sinh()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Csch()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Coth()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(12);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arcsin()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(13);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arccos()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(14);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arctan()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(15);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arcsec()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(16);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arccsc()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(17);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arccot()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(18);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arcsinh()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(19);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arccosh()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(20);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arctanh()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(21);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arcsech()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(22);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arccsch()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(23);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Arccoth()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(24);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Floor()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(25);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Abs()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(26);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Ceiling()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(27);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_UniopName_Logit()
  {
        return (EAttribute)getMath_UniopName().getEStructuralFeatures().get(28);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_LogicUniopName()
  {
    if (math_LogicUniopNameEClass == null)
    {
      math_LogicUniopNameEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(27);
    }
    return math_LogicUniopNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicUniopName_IsDefined()
  {
        return (EAttribute)getMath_LogicUniopName().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicUniopName_Not()
  {
        return (EAttribute)getMath_LogicUniopName().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_LogicBinopName()
  {
    if (math_LogicBinopNameEClass == null)
    {
      math_LogicBinopNameEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(28);
    }
    return math_LogicBinopNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicBinopName_Lt()
  {
        return (EAttribute)getMath_LogicBinopName().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicBinopName_Leq()
  {
        return (EAttribute)getMath_LogicBinopName().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicBinopName_Gt()
  {
        return (EAttribute)getMath_LogicBinopName().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicBinopName_Geq()
  {
        return (EAttribute)getMath_LogicBinopName().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicBinopName_Eq()
  {
        return (EAttribute)getMath_LogicBinopName().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicBinopName_Neq()
  {
        return (EAttribute)getMath_LogicBinopName().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicBinopName_And()
  {
        return (EAttribute)getMath_LogicBinopName().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicBinopName_Or()
  {
        return (EAttribute)getMath_LogicBinopName().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_LogicBinopName_Xor()
  {
        return (EAttribute)getMath_LogicBinopName().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_ConstantName()
  {
    if (math_ConstantNameEClass == null)
    {
      math_ConstantNameEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(29);
    }
    return math_ConstantNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_ConstantName_Notanumber()
  {
        return (EAttribute)getMath_ConstantName().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_ConstantName_Pi()
  {
        return (EAttribute)getMath_ConstantName().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_ConstantName_Exponentiale()
  {
        return (EAttribute)getMath_ConstantName().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_ConstantName_Infinity()
  {
        return (EAttribute)getMath_ConstantName().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_symbId()
  {
    if (math_symbIdEClass == null)
    {
      math_symbIdEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(30);
    }
    return math_symbIdEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_symbId_SymbId()
  {
        return (EAttribute)getMath_symbId().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_id()
  {
    if (math_idEClass == null)
    {
      math_idEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(31);
    }
    return math_idEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_id_Id()
  {
        return (EAttribute)getMath_id().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_block()
  {
    if (math_blockEClass == null)
    {
      math_blockEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(32);
    }
    return math_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_block_Block()
  {
        return (EAttribute)getMath_block().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_independentVar()
  {
    if (ct_independentVarEClass == null)
    {
      ct_independentVarEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(33);
    }
    return ct_independentVarEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_independentVar_IndependentVar()
  {
        return (EAttribute)getct_independentVar().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMath_EstimationOperationType()
  {
    if (math_EstimationOperationTypeEClass == null)
    {
      math_EstimationOperationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(34);
    }
    return math_EstimationOperationTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_EstimationOperationType_EstPop()
  {
        return (EAttribute)getMath_EstimationOperationType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_EstimationOperationType_EstFIM()
  {
        return (EAttribute)getMath_EstimationOperationType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMath_EstimationOperationType_EstIndiv()
  {
        return (EAttribute)getMath_EstimationOperationType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ParameterTypeName()
  {
    if (mdef_ParameterTypeNameEClass == null)
    {
      mdef_ParameterTypeNameEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(35);
    }
    return mdef_ParameterTypeNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmdef_ParameterTypeName_Log()
  {
        return (EAttribute)getmdef_ParameterTypeName().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmdef_ParameterTypeName_Logit()
  {
        return (EAttribute)getmdef_ParameterTypeName().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmdef_ParameterTypeName_None()
  {
        return (EAttribute)getmdef_ParameterTypeName().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ModelDefinitionType()
  {
    if (mdef_ModelDefinitionTypeEClass == null)
    {
      mdef_ModelDefinitionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(36);
    }
    return mdef_ModelDefinitionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ModelDefinitionType_Xmlns()
  {
        return (EReference)getmdef_ModelDefinitionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ModelDefinitionType_VariabilityLevel()
  {
        return (EReference)getmdef_ModelDefinitionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ModelDefinitionType_CovariateModel()
  {
        return (EReference)getmdef_ModelDefinitionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ModelDefinitionType_ParameterModel()
  {
        return (EReference)getmdef_ModelDefinitionType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ModelDefinitionType_StructuraModel()
  {
        return (EReference)getmdef_ModelDefinitionType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ModelDefinitionType_ObservationModel()
  {
        return (EReference)getmdef_ModelDefinitionType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_StructuralModelType()
  {
    if (mdef_StructuralModelTypeEClass == null)
    {
      mdef_StructuralModelTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(37);
    }
    return mdef_StructuralModelTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_StructuralModelType_Id()
  {
        return (EReference)getmdef_StructuralModelType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_StructuralModelType_Xmlns()
  {
        return (EReference)getmdef_StructuralModelType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_StructuralModelType_Description()
  {
        return (EReference)getmdef_StructuralModelType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_StructuralModelType_Parameter()
  {
        return (EReference)getmdef_StructuralModelType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_StructuralModelType_Variable()
  {
        return (EReference)getmdef_StructuralModelType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_StructuralModelType_Import()
  {
        return (EReference)getmdef_StructuralModelType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_StructuralModelType_InitiaCondition()
  {
        return (EReference)getmdef_StructuralModelType().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ObservationModelType()
  {
    if (mdef_ObservationModelTypeEClass == null)
    {
      mdef_ObservationModelTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(38);
    }
    return mdef_ObservationModelTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ObservationModelType_Id()
  {
        return (EReference)getmdef_ObservationModelType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ObservationModelType_Name()
  {
        return (EReference)getmdef_ObservationModelType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ObservationModelType_Xmlns()
  {
        return (EReference)getmdef_ObservationModelType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ObservationModelType_Parameter()
  {
        return (EReference)getmdef_ObservationModelType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ObservationModelType_Continuous()
  {
        return (EReference)getmdef_ObservationModelType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ParameterModelType()
  {
    if (mdef_ParameterModelTypeEClass == null)
    {
      mdef_ParameterModelTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(39);
    }
    return mdef_ParameterModelTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterModelType_Id()
  {
        return (EReference)getmdef_ParameterModelType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterModelType_Name()
  {
        return (EReference)getmdef_ParameterModelType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterModelType_Xmlns()
  {
        return (EReference)getmdef_ParameterModelType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterModelType_Parameter()
  {
        return (EReference)getmdef_ParameterModelType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterModelType_Correlation()
  {
        return (EReference)getmdef_ParameterModelType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_CorrelationType()
  {
    if (mdef_CorrelationTypeEClass == null)
    {
      mdef_CorrelationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(40);
    }
    return mdef_CorrelationTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CorrelationType_LevelId()
  {
        return (EReference)getmdef_CorrelationType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CorrelationType_Xmlns()
  {
        return (EReference)getmdef_CorrelationType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CorrelationType_ParamVar1()
  {
        return (EReference)getmdef_CorrelationType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CorrelationType_ParamVar2()
  {
        return (EReference)getmdef_CorrelationType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CorrelationType_CorrelationCoefficient()
  {
        return (EReference)getmdef_CorrelationType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CorrelationType_Covariance()
  {
        return (EReference)getmdef_CorrelationType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdefCovarianceType()
  {
    if (mdefCovarianceTypeEClass == null)
    {
      mdefCovarianceTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(41);
    }
    return mdefCovarianceTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdefCovarianceType_Xmlns()
  {
        return (EReference)getmdefCovarianceType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdefCovarianceType_Expr()
  {
        return (EReference)getmdefCovarianceType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_CorrelationCoefficientType()
  {
    if (mdef_CorrelationCoefficientTypeEClass == null)
    {
      mdef_CorrelationCoefficientTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(42);
    }
    return mdef_CorrelationCoefficientTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CorrelationCoefficientType_Xmlns()
  {
        return (EReference)getmdef_CorrelationCoefficientType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CorrelationCoefficientType_Expr()
  {
        return (EReference)getmdef_CorrelationCoefficientType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_CovariateModelType()
  {
    if (mdef_CovariateModelTypeEClass == null)
    {
      mdef_CovariateModelTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(43);
    }
    return mdef_CovariateModelTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateModelType_Id()
  {
        return (EReference)getmdef_CovariateModelType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateModelType_Name()
  {
        return (EReference)getmdef_CovariateModelType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateModelType_Xmlns()
  {
        return (EReference)getmdef_CovariateModelType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateModelType_Parameter()
  {
        return (EReference)getmdef_CovariateModelType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateModelType_Covariate()
  {
        return (EReference)getmdef_CovariateModelType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ParameterType()
  {
    if (mdef_ParameterTypeEClass == null)
    {
      mdef_ParameterTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(44);
    }
    return mdef_ParameterTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterType_Name()
  {
        return (EReference)getmdef_ParameterType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterType_SymbId()
  {
        return (EReference)getmdef_ParameterType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterType_Transformation()
  {
        return (EReference)getmdef_ParameterType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterType_Xmlns()
  {
        return (EReference)getmdef_ParameterType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterType_Var()
  {
        return (EReference)getmdef_ParameterType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterType_Scalar()
  {
        return (EReference)getmdef_ParameterType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterType_Equation()
  {
        return (EReference)getmdef_ParameterType().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterType_RandomEffect()
  {
        return (EReference)getmdef_ParameterType().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ParameterType_Covariate()
  {
        return (EReference)getmdef_ParameterType().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_CovariateVariabilityType()
  {
    if (mdef_CovariateVariabilityTypeEClass == null)
    {
      mdef_CovariateVariabilityTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(45);
    }
    return mdef_CovariateVariabilityTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateVariabilityType_Name()
  {
        return (EReference)getmdef_CovariateVariabilityType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateVariabilityType_SymbId()
  {
        return (EReference)getmdef_CovariateVariabilityType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateVariabilityType_Transformation()
  {
        return (EReference)getmdef_CovariateVariabilityType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateVariabilityType_Xmlns()
  {
        return (EReference)getmdef_CovariateVariabilityType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateVariabilityType_Continuous()
  {
        return (EReference)getmdef_CovariateVariabilityType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateVariabilityType_Categorical()
  {
        return (EReference)getmdef_CovariateVariabilityType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_transformation()
  {
    if (mdef_transformationEClass == null)
    {
      mdef_transformationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(46);
    }
    return mdef_transformationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_transformation_Transformation()
  {
        return (EReference)getmdef_transformation().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_CategoricalType()
  {
    if (mdef_CategoricalTypeEClass == null)
    {
      mdef_CategoricalTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(47);
    }
    return mdef_CategoricalTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CategoricalType_Xmlns()
  {
        return (EReference)getmdef_CategoricalType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CategoricalType_Category()
  {
        return (EReference)getmdef_CategoricalType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ContinuousType()
  {
    if (mdef_ContinuousTypeEClass == null)
    {
      mdef_ContinuousTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(48);
    }
    return mdef_ContinuousTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ContinuousType_Xmlns()
  {
        return (EReference)getmdef_ContinuousType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ContinuousType_Distribution()
  {
        return (EReference)getmdef_ContinuousType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ContinuousType_Transformation()
  {
        return (EReference)getmdef_ContinuousType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_TransformationType()
  {
    if (mdef_TransformationTypeEClass == null)
    {
      mdef_TransformationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(49);
    }
    return mdef_TransformationTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_TransformationType_Expr()
  {
        return (EReference)getmdef_TransformationType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_VariabilityLevelDefnType()
  {
    if (mdef_VariabilityLevelDefnTypeEClass == null)
    {
      mdef_VariabilityLevelDefnTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(50);
    }
    return mdef_VariabilityLevelDefnTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_VariabilityLevelDefnType_Id()
  {
        return (EReference)getmdef_VariabilityLevelDefnType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_VariabilityLevelDefnType_Name()
  {
        return (EReference)getmdef_VariabilityLevelDefnType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_VariabilityLevelDefnType_Xmlns()
  {
        return (EReference)getmdef_VariabilityLevelDefnType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ImportType()
  {
    if (mdef_ImportTypeEClass == null)
    {
      mdef_ImportTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(51);
    }
    return mdef_ImportTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmdef_ImportType_Resource()
  {
        return (EAttribute)getmdef_ImportType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ImportType_ResourceType()
  {
        return (EReference)getmdef_ImportType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmdef_ImportType_Source()
  {
        return (EAttribute)getmdef_ImportType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ImportType_Xmlns()
  {
        return (EReference)getmdef_ImportType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ImportType_Link()
  {
        return (EReference)getmdef_ImportType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ImportLinkType()
  {
    if (mdef_ImportLinkTypeEClass == null)
    {
      mdef_ImportLinkTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(52);
    }
    return mdef_ImportLinkTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmdef_ImportLinkType_SymbolPtr()
  {
        return (EAttribute)getmdef_ImportLinkType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ImportLinkType_Xmlns()
  {
        return (EReference)getmdef_ImportLinkType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ImportLinkType_Var()
  {
        return (EReference)getmdef_ImportLinkType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ImportResourceTypeType()
  {
    if (mdef_ImportResourceTypeTypeEClass == null)
    {
      mdef_ImportResourceTypeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(53);
    }
    return mdef_ImportResourceTypeTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmdef_ImportResourceTypeType_Sbml()
  {
        return (EAttribute)getmdef_ImportResourceTypeType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmdef_ImportResourceTypeType_Pharmml()
  {
        return (EAttribute)getmdef_ImportResourceTypeType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_CovariateType()
  {
    if (mdef_CovariateTypeEClass == null)
    {
      mdef_CovariateTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(54);
    }
    return mdef_CovariateTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateType_Xmlns()
  {
        return (EReference)getmdef_CovariateType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateType_Var()
  {
        return (EReference)getmdef_CovariateType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CovariateType_FixedEffect()
  {
        return (EReference)getmdef_CovariateType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_FixedEffectType()
  {
    if (mdef_FixedEffectTypeEClass == null)
    {
      mdef_FixedEffectTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(55);
    }
    return mdef_FixedEffectTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_FixedEffectType_Xmlns()
  {
        return (EReference)getmdef_FixedEffectType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_FixedEffectType_Var()
  {
        return (EReference)getmdef_FixedEffectType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_FixedEffectType_Category()
  {
        return (EReference)getmdef_FixedEffectType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_CategoryType()
  {
    if (mdef_CategoryTypeEClass == null)
    {
      mdef_CategoryTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(56);
    }
    return mdef_CategoryTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CategoryType_CatId()
  {
        return (EReference)getmdef_CategoryType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CategoryType_Name()
  {
        return (EReference)getmdef_CategoryType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CategoryType_Xmlns()
  {
        return (EReference)getmdef_CategoryType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_CategoryType_Probability()
  {
        return (EReference)getmdef_CategoryType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_ProbabilityType()
  {
    if (mdef_ProbabilityTypeEClass == null)
    {
      mdef_ProbabilityTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(57);
    }
    return mdef_ProbabilityTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ProbabilityType_Xmlns()
  {
        return (EReference)getmdef_ProbabilityType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_ProbabilityType_Expr()
  {
        return (EReference)getmdef_ProbabilityType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_RandomEffectType()
  {
    if (mdef_RandomEffectTypeEClass == null)
    {
      mdef_RandomEffectTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(58);
    }
    return mdef_RandomEffectTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_RandomEffectType_LevelId()
  {
        return (EReference)getmdef_RandomEffectType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_RandomEffectType_Name()
  {
        return (EReference)getmdef_RandomEffectType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_RandomEffectType_SymbId()
  {
        return (EReference)getmdef_RandomEffectType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_RandomEffectType_Xmlns()
  {
        return (EReference)getmdef_RandomEffectType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_RandomEffectType_Distribution()
  {
        return (EReference)getmdef_RandomEffectType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmdef_InitialConditionType()
  {
    if (mdef_InitialConditionTypeEClass == null)
    {
      mdef_InitialConditionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(59);
    }
    return mdef_InitialConditionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_InitialConditionType_SymbID()
  {
        return (EReference)getmdef_InitialConditionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_InitialConditionType_Block()
  {
        return (EReference)getmdef_InitialConditionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_InitialConditionType_Xmlns()
  {
        return (EReference)getmdef_InitialConditionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmdef_InitialConditionType_Expr()
  {
        return (EReference)getmdef_InitialConditionType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_TrialDesignType()
  {
    if (design_TrialDesignTypeEClass == null)
    {
      design_TrialDesignTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(60);
    }
    return design_TrialDesignTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TrialDesignType_Xmlns()
  {
        return (EReference)getdesign_TrialDesignType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TrialDesignType_Treatment()
  {
        return (EReference)getdesign_TrialDesignType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TrialDesignType_TreatmentEpoch()
  {
        return (EReference)getdesign_TrialDesignType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TrialDesignType_Group()
  {
        return (EReference)getdesign_TrialDesignType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_GroupType()
  {
    if (design_GroupTypeEClass == null)
    {
      design_GroupTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(61);
    }
    return design_GroupTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_GroupType_Id()
  {
        return (EReference)getdesign_GroupType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_GroupType_Name()
  {
        return (EReference)getdesign_GroupType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_GroupType_Xmlns()
  {
        return (EReference)getdesign_GroupType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_GroupType_TreatmentEpochRef()
  {
        return (EReference)getdesign_GroupType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_GroupType_Washout()
  {
        return (EReference)getdesign_GroupType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_GroupType_Individuals()
  {
        return (EReference)getdesign_GroupType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_IdividualsType()
  {
    if (design_IdividualsTypeEClass == null)
    {
      design_IdividualsTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(62);
    }
    return design_IdividualsTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_IdividualsType_SymbId()
  {
        return (EReference)getdesign_IdividualsType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_IdividualsType_Name()
  {
        return (EReference)getdesign_IdividualsType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_IdividualsType_LevelId()
  {
        return (EReference)getdesign_IdividualsType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_IdividualsType_Xmlns()
  {
        return (EReference)getdesign_IdividualsType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_IdividualsType_Expr()
  {
        return (EReference)getdesign_IdividualsType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_WashoutType()
  {
    if (design_WashoutTypeEClass == null)
    {
      design_WashoutTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(63);
    }
    return design_WashoutTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_WashoutType_Xmlns()
  {
        return (EReference)getdesign_WashoutType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_TreatmentEpohType()
  {
    if (design_TreatmentEpohTypeEClass == null)
    {
      design_TreatmentEpohTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(64);
    }
    return design_TreatmentEpohTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentEpohType_SymbId()
  {
        return (EReference)getdesign_TreatmentEpohType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentEpohType_Name()
  {
        return (EReference)getdesign_TreatmentEpohType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentEpohType_Xmlns()
  {
        return (EReference)getdesign_TreatmentEpohType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentEpohType_TreatmentRef()
  {
        return (EReference)getdesign_TreatmentEpohType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentEpohType_Start()
  {
        return (EReference)getdesign_TreatmentEpohType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentEpohType_End()
  {
        return (EReference)getdesign_TreatmentEpohType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentEpohType_Occasion()
  {
        return (EReference)getdesign_TreatmentEpohType().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_OccasionType()
  {
    if (design_OccasionTypeEClass == null)
    {
      design_OccasionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(65);
    }
    return design_OccasionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_OccasionType_SymbId()
  {
        return (EReference)getdesign_OccasionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_OccasionType_Name()
  {
        return (EReference)getdesign_OccasionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_OccasionType_LevelId()
  {
        return (EReference)getdesign_OccasionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_OccasionType_Xmlns()
  {
        return (EReference)getdesign_OccasionType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_OccasionType_Begin()
  {
        return (EReference)getdesign_OccasionType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_OccasionType_End()
  {
        return (EReference)getdesign_OccasionType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_BeginType()
  {
    if (design_BeginTypeEClass == null)
    {
      design_BeginTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(66);
    }
    return design_BeginTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_BeginType_Xmlns()
  {
        return (EReference)getdesign_BeginType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_BeginType_Expr()
  {
        return (EReference)getdesign_BeginType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_EndType()
  {
    if (design_EndTypeEClass == null)
    {
      design_EndTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(67);
    }
    return design_EndTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_EndType_Xmlns()
  {
        return (EReference)getdesign_EndType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_EndType_Expr()
  {
        return (EReference)getdesign_EndType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_StartType()
  {
    if (design_StartTypeEClass == null)
    {
      design_StartTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(68);
    }
    return design_StartTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_StartType_Xmlns()
  {
        return (EReference)getdesign_StartType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_StartType_Expr()
  {
        return (EReference)getdesign_StartType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_TreatmentRefType()
  {
    if (design_TreatmentRefTypeEClass == null)
    {
      design_TreatmentRefTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(69);
    }
    return design_TreatmentRefTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdesign_TreatmentRefType_IdRef()
  {
        return (EAttribute)getdesign_TreatmentRefType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentRefType_Xmlns()
  {
        return (EReference)getdesign_TreatmentRefType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_TreatmentType()
  {
    if (design_TreatmentTypeEClass == null)
    {
      design_TreatmentTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(70);
    }
    return design_TreatmentTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentType_SymbId()
  {
        return (EReference)getdesign_TreatmentType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentType_Name()
  {
        return (EReference)getdesign_TreatmentType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentType_Xmlns()
  {
        return (EReference)getdesign_TreatmentType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TreatmentType_DosingRegimen()
  {
        return (EReference)getdesign_TreatmentType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_DosingRegimenType()
  {
    if (design_DosingRegimenTypeEClass == null)
    {
      design_DosingRegimenTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(71);
    }
    return design_DosingRegimenTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingRegimenType_Xmlns()
  {
        return (EReference)getdesign_DosingRegimenType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingRegimenType_Bolus()
  {
        return (EReference)getdesign_DosingRegimenType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingRegimenType_Infusion()
  {
        return (EReference)getdesign_DosingRegimenType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_InfusionType()
  {
    if (design_InfusionTypeEClass == null)
    {
      design_InfusionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(72);
    }
    return design_InfusionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_InfusionType_Xmlns()
  {
        return (EReference)getdesign_InfusionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_InfusionType_DoseAmount()
  {
        return (EReference)getdesign_InfusionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_InfusionType_SteadyState()
  {
        return (EReference)getdesign_InfusionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_InfusionType_DosingTimes()
  {
        return (EReference)getdesign_InfusionType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_InfusionType_Duration()
  {
        return (EReference)getdesign_InfusionType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_DurationType()
  {
    if (design_DurationTypeEClass == null)
    {
      design_DurationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(73);
    }
    return design_DurationTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DurationType_Xmlns()
  {
        return (EReference)getdesign_DurationType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DurationType_Expr()
  {
        return (EReference)getdesign_DurationType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_BolusType()
  {
    if (design_BolusTypeEClass == null)
    {
      design_BolusTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(74);
    }
    return design_BolusTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_BolusType_Xmlns()
  {
        return (EReference)getdesign_BolusType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_BolusType_DoseAmount()
  {
        return (EReference)getdesign_BolusType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_BolusType_SteadyState()
  {
        return (EReference)getdesign_BolusType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_BolusType_DosingTimes()
  {
        return (EReference)getdesign_BolusType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_DosingTimesType()
  {
    if (design_DosingTimesTypeEClass == null)
    {
      design_DosingTimesTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(75);
    }
    return design_DosingTimesTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingTimesType_Xmlns()
  {
        return (EReference)getdesign_DosingTimesType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingTimesType_Expr()
  {
        return (EReference)getdesign_DosingTimesType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_SteadyStateType()
  {
    if (design_SteadyStateTypeEClass == null)
    {
      design_SteadyStateTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(76);
    }
    return design_SteadyStateTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_SteadyStateType_Xmlns()
  {
        return (EReference)getdesign_SteadyStateType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_SteadyStateType_DosingStart()
  {
        return (EReference)getdesign_SteadyStateType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_SteadyStateType_DosingPeriod()
  {
        return (EReference)getdesign_SteadyStateType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_DosingStartType()
  {
    if (design_DosingStartTypeEClass == null)
    {
      design_DosingStartTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(77);
    }
    return design_DosingStartTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingStartType_Xmlns()
  {
        return (EReference)getdesign_DosingStartType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingStartType_Expr()
  {
        return (EReference)getdesign_DosingStartType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_DosingPeriodType()
  {
    if (design_DosingPeriodTypeEClass == null)
    {
      design_DosingPeriodTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(78);
    }
    return design_DosingPeriodTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingPeriodType_Xmlns()
  {
        return (EReference)getdesign_DosingPeriodType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingPeriodType_Expr()
  {
        return (EReference)getdesign_DosingPeriodType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_DoseAmountType()
  {
    if (design_DoseAmountTypeEClass == null)
    {
      design_DoseAmountTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(79);
    }
    return design_DoseAmountTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DoseAmountType_Xmlns()
  {
        return (EReference)getdesign_DoseAmountType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DoseAmountType_Expr()
  {
        return (EReference)getdesign_DoseAmountType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_DosingVariableType()
  {
    if (design_DosingVariableTypeEClass == null)
    {
      design_DosingVariableTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(80);
    }
    return design_DosingVariableTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingVariableType_DoseVar()
  {
        return (EReference)getdesign_DosingVariableType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingVariableType_TargetVar()
  {
        return (EReference)getdesign_DosingVariableType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DosingVariableType_Amount()
  {
        return (EReference)getdesign_DosingVariableType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_AmountType()
  {
    if (design_AmountTypeEClass == null)
    {
      design_AmountTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(81);
    }
    return design_AmountTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_AmountType_Xmlns()
  {
        return (EReference)getdesign_AmountType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_AmountType_Expr()
  {
        return (EReference)getdesign_AmountType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_TargetVarType()
  {
    if (design_TargetVarTypeEClass == null)
    {
      design_TargetVarTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(82);
    }
    return design_TargetVarTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TargetVarType_SymbId()
  {
        return (EReference)getdesign_TargetVarType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TargetVarType_Block()
  {
        return (EReference)getdesign_TargetVarType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_TargetVarType_Xmlns()
  {
        return (EReference)getdesign_TargetVarType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_DoseVarType()
  {
    if (design_DoseVarTypeEClass == null)
    {
      design_DoseVarTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(83);
    }
    return design_DoseVarTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DoseVarType_SymbId()
  {
        return (EReference)getdesign_DoseVarType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DoseVarType_Block()
  {
        return (EReference)getdesign_DoseVarType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_DoseVarType_Xmlns()
  {
        return (EReference)getdesign_DoseVarType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_ModelingSteps()
  {
    if (msteps_ModelingStepsEClass == null)
    {
      msteps_ModelingStepsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(84);
    }
    return msteps_ModelingStepsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ModelingSteps_Xmlns()
  {
        return (EReference)getmsteps_ModelingSteps().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ModelingSteps_Variable()
  {
        return (EReference)getmsteps_ModelingSteps().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ModelingSteps_EstimationStep()
  {
        return (EReference)getmsteps_ModelingSteps().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ModelingSteps_SimulationStep()
  {
        return (EReference)getmsteps_ModelingSteps().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ModelingSteps_StepDependencies()
  {
        return (EReference)getmsteps_ModelingSteps().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_StepDependenciesType()
  {
    if (msteps_StepDependenciesTypeEClass == null)
    {
      msteps_StepDependenciesTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(85);
    }
    return msteps_StepDependenciesTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_StepDependenciesType_Xmlns()
  {
        return (EReference)getmsteps_StepDependenciesType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_StepDependenciesType_Description()
  {
        return (EReference)getmsteps_StepDependenciesType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_StepDependenciesType_Step()
  {
        return (EReference)getmsteps_StepDependenciesType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_StepType()
  {
    if (msteps_StepTypeEClass == null)
    {
      msteps_StepTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(86);
    }
    return msteps_StepTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmsteps_StepType_IdRef()
  {
        return (EAttribute)getmsteps_StepType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_StepType_Xmlns()
  {
        return (EReference)getmsteps_StepType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_StepType_DependantStep()
  {
        return (EReference)getmsteps_StepType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_DependantStepType()
  {
    if (msteps_DependantStepTypeEClass == null)
    {
      msteps_DependantStepTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(87);
    }
    return msteps_DependantStepTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmsteps_DependantStepType_IdRef()
  {
        return (EAttribute)getmsteps_DependantStepType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_DependantStepType_Xmlns()
  {
        return (EReference)getmsteps_DependantStepType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_EstimationStep()
  {
    if (msteps_EstimationStepEClass == null)
    {
      msteps_EstimationStepEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(88);
    }
    return msteps_EstimationStepEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_EstimationStep_Id()
  {
        return (EReference)getmsteps_EstimationStep().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_EstimationStep_Xmlns()
  {
        return (EReference)getmsteps_EstimationStep().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_EstimationStep_Description()
  {
        return (EReference)getmsteps_EstimationStep().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_EstimationStep_InitialValue()
  {
        return (EReference)getmsteps_EstimationStep().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_EstimationStep_ObjectiveDataSet()
  {
        return (EReference)getmsteps_EstimationStep().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_EstimationStep_ParametersToEstimate()
  {
        return (EReference)getmsteps_EstimationStep().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_EstimationStep_EstimationOperation()
  {
        return (EReference)getmsteps_EstimationStep().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_ParametersToEstimateType()
  {
    if (msteps_ParametersToEstimateTypeEClass == null)
    {
      msteps_ParametersToEstimateTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(89);
    }
    return msteps_ParametersToEstimateTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ParametersToEstimateType_Xmlns()
  {
        return (EReference)getmsteps_ParametersToEstimateType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ParametersToEstimateType_Variable()
  {
        return (EReference)getmsteps_ParametersToEstimateType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmSteps_VariableEstimateType()
  {
    if (mSteps_VariableEstimateTypeEClass == null)
    {
      mSteps_VariableEstimateTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(90);
    }
    return mSteps_VariableEstimateTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_VariableEstimateType_SymbID()
  {
        return (EReference)getmSteps_VariableEstimateType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_VariableEstimateType_Fixed()
  {
        return (EReference)getmSteps_VariableEstimateType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_VariableEstimateType_Block()
  {
        return (EReference)getmSteps_VariableEstimateType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_VariableEstimateType_Xmlns()
  {
        return (EReference)getmSteps_VariableEstimateType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_VariableEstimateType_InitialEstimate()
  {
        return (EReference)getmSteps_VariableEstimateType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_VariableEstimateType_LowerBound()
  {
        return (EReference)getmSteps_VariableEstimateType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_VariableEstimateType_UpperBound()
  {
        return (EReference)getmSteps_VariableEstimateType().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmSteps_fixed()
  {
    if (mSteps_fixedEClass == null)
    {
      mSteps_fixedEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(91);
    }
    return mSteps_fixedEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_fixed_Fixed()
  {
        return (EReference)getmSteps_fixed().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmSteps_UpperBoundType()
  {
    if (mSteps_UpperBoundTypeEClass == null)
    {
      mSteps_UpperBoundTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(92);
    }
    return mSteps_UpperBoundTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_UpperBoundType_Xmlns()
  {
        return (EReference)getmSteps_UpperBoundType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_UpperBoundType_Expr()
  {
        return (EReference)getmSteps_UpperBoundType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmSteps_LowerBoundType()
  {
    if (mSteps_LowerBoundTypeEClass == null)
    {
      mSteps_LowerBoundTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(93);
    }
    return mSteps_LowerBoundTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_LowerBoundType_Xmlns()
  {
        return (EReference)getmSteps_LowerBoundType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_LowerBoundType_Expr()
  {
        return (EReference)getmSteps_LowerBoundType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmSteps_InitialEstimateType()
  {
    if (mSteps_InitialEstimateTypeEClass == null)
    {
      mSteps_InitialEstimateTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(94);
    }
    return mSteps_InitialEstimateTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_InitialEstimateType_Xmlns()
  {
        return (EReference)getmSteps_InitialEstimateType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmSteps_InitialEstimateType_Expr()
  {
        return (EReference)getmSteps_InitialEstimateType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_ObjectiveDataSetType()
  {
    if (msteps_ObjectiveDataSetTypeEClass == null)
    {
      msteps_ObjectiveDataSetTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(95);
    }
    return msteps_ObjectiveDataSetTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmsteps_ObjectiveDataSetType_DataSetRef()
  {
        return (EAttribute)getmsteps_ObjectiveDataSetType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ObjectiveDataSetType_Xmlns()
  {
        return (EReference)getmsteps_ObjectiveDataSetType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ObjectiveDataSetType_Mapping()
  {
        return (EReference)getmsteps_ObjectiveDataSetType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_MappingType()
  {
    if (msteps_MappingTypeEClass == null)
    {
      msteps_MappingTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(96);
    }
    return msteps_MappingTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmsteps_MappingType_ColumnName()
  {
        return (EAttribute)getmsteps_MappingType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_MappingType_Xmlns()
  {
        return (EReference)getmsteps_MappingType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_MappingType_Var()
  {
        return (EReference)getmsteps_MappingType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_MappingType_UseVariabilityLevel()
  {
        return (EReference)getmsteps_MappingType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_MappingType_UseVariabilityNode()
  {
        return (EReference)getmsteps_MappingType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_MappingType_DoseVar()
  {
        return (EReference)getmsteps_MappingType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_MappingType_TargetVar()
  {
        return (EReference)getmsteps_MappingType().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_MappingType_Assign()
  {
        return (EReference)getmsteps_MappingType().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_MappingType_Output()
  {
        return (EReference)getmsteps_MappingType().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_MappingType_Restriction()
  {
        return (EReference)getmsteps_MappingType().getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_OutputType()
  {
    if (msteps_OutputTypeEClass == null)
    {
      msteps_OutputTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(97);
    }
    return msteps_OutputTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_OutputType_Xmlns()
  {
        return (EReference)getmsteps_OutputType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_OutputType_Vars()
  {
        return (EReference)getmsteps_OutputType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_AssignType()
  {
    if (msteps_AssignTypeEClass == null)
    {
      msteps_AssignTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(98);
    }
    return msteps_AssignTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_AssignType_SymbId()
  {
        return (EReference)getmsteps_AssignType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_AssignType_Block()
  {
        return (EReference)getmsteps_AssignType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_AssignType_Xmlns()
  {
        return (EReference)getmsteps_AssignType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_AssignType_Expr()
  {
        return (EReference)getmsteps_AssignType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_TargetVarType()
  {
    if (msteps_TargetVarTypeEClass == null)
    {
      msteps_TargetVarTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(99);
    }
    return msteps_TargetVarTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_TargetVarType_SymbId()
  {
        return (EReference)getmsteps_TargetVarType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_TargetVarType_Block()
  {
        return (EReference)getmsteps_TargetVarType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_TargetVarType_Xmlns()
  {
        return (EReference)getmsteps_TargetVarType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_UseVariabilityLevel()
  {
    if (msteps_UseVariabilityLevelEClass == null)
    {
      msteps_UseVariabilityLevelEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(100);
    }
    return msteps_UseVariabilityLevelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmsteps_UseVariabilityLevel_LevelId()
  {
        return (EAttribute)getmsteps_UseVariabilityLevel().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_UseVariabilityLevel_Xmlns()
  {
        return (EReference)getmsteps_UseVariabilityLevel().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_UseVariabilityNode()
  {
    if (msteps_UseVariabilityNodeEClass == null)
    {
      msteps_UseVariabilityNodeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(101);
    }
    return msteps_UseVariabilityNodeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_UseVariabilityNode_SymbId()
  {
        return (EReference)getmsteps_UseVariabilityNode().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_UseVariabilityNode_Block()
  {
        return (EReference)getmsteps_UseVariabilityNode().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_UseVariabilityNode_Xmlns()
  {
        return (EReference)getmsteps_UseVariabilityNode().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_SimulationStep()
  {
    if (msteps_SimulationStepEClass == null)
    {
      msteps_SimulationStepEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(102);
    }
    return msteps_SimulationStepEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_SimulationStep_Id()
  {
        return (EReference)getmsteps_SimulationStep().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_SimulationStep_Xmlns()
  {
        return (EReference)getmsteps_SimulationStep().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_SimulationStep_Description()
  {
        return (EReference)getmsteps_SimulationStep().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_SimulationStep_Replicates()
  {
        return (EReference)getmsteps_SimulationStep().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_SimulationStep_InitialValue()
  {
        return (EReference)getmsteps_SimulationStep().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_SimulationStep_SimDataSet()
  {
        return (EReference)getmsteps_SimulationStep().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_SimulationStep_Observations()
  {
        return (EReference)getmsteps_SimulationStep().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_SimDataSetType()
  {
    if (msteps_SimDataSetTypeEClass == null)
    {
      msteps_SimDataSetTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(103);
    }
    return msteps_SimDataSetTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmsteps_SimDataSetType_DataSetRef()
  {
        return (EAttribute)getmsteps_SimDataSetType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_SimDataSetType_Xmlns()
  {
        return (EReference)getmsteps_SimDataSetType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_SimDataSetType_Mapping()
  {
        return (EReference)getmsteps_SimDataSetType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_ObservationsType()
  {
    if (msteps_ObservationsTypeEClass == null)
    {
      msteps_ObservationsTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(104);
    }
    return msteps_ObservationsTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ObservationsType_Xmlns()
  {
        return (EReference)getmsteps_ObservationsType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ObservationsType_Timepoints()
  {
        return (EReference)getmsteps_ObservationsType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ObservationsType_Output()
  {
        return (EReference)getmsteps_ObservationsType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_ObservationsType_WriteTo()
  {
        return (EReference)getmsteps_ObservationsType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_WriteToType()
  {
    if (msteps_WriteToTypeEClass == null)
    {
      msteps_WriteToTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(105);
    }
    return msteps_WriteToTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_WriteToType_Xmlns()
  {
        return (EReference)getmsteps_WriteToType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_WriteToType_Var()
  {
        return (EReference)getmsteps_WriteToType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_WriteToType_Mapping()
  {
        return (EReference)getmsteps_WriteToType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_TimepointsType()
  {
    if (msteps_TimepointsTypeEClass == null)
    {
      msteps_TimepointsTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(106);
    }
    return msteps_TimepointsTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_TimepointsType_Xmlns()
  {
        return (EReference)getmsteps_TimepointsType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_TimepointsType_Expr()
  {
        return (EReference)getmsteps_TimepointsType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_InitialValueType()
  {
    if (msteps_InitialValueTypeEClass == null)
    {
      msteps_InitialValueTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(107);
    }
    return msteps_InitialValueTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_InitialValueType_SymbId()
  {
        return (EReference)getmsteps_InitialValueType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_InitialValueType_Xmlns()
  {
        return (EReference)getmsteps_InitialValueType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_InitialValueType_Content()
  {
        return (EReference)getmsteps_InitialValueType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmsteps_EstimationOperationType()
  {
    if (msteps_EstimationOperationTypeEClass == null)
    {
      msteps_EstimationOperationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(108);
    }
    return msteps_EstimationOperationTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_EstimationOperationType_OpType()
  {
        return (EReference)getmsteps_EstimationOperationType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmsteps_EstimationOperationType_Xmlns()
  {
        return (EReference)getmsteps_EstimationOperationType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_DistributionType()
  {
    if (uncert_DistributionTypeEClass == null)
    {
      uncert_DistributionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(109);
    }
    return uncert_DistributionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionType_Xmlns()
  {
        return (EReference)getuncert_DistributionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionType_WrittenVersion()
  {
        return (EReference)getuncert_DistributionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionType_Normal()
  {
        return (EReference)getuncert_DistributionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionType_PDF()
  {
        return (EReference)getuncert_DistributionType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionType_Poison()
  {
        return (EReference)getuncert_DistributionType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionType_Student()
  {
        return (EReference)getuncert_DistributionType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionType_Uniform()
  {
        return (EReference)getuncert_DistributionType().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_Normal()
  {
    if (uncert_NormalEClass == null)
    {
      uncert_NormalEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(110);
    }
    return uncert_NormalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Normal_Xmlns()
  {
        return (EReference)getuncert_Normal().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Normal_Mean()
  {
        return (EReference)getuncert_Normal().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Normal_StdDev()
  {
        return (EReference)getuncert_Normal().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Normal_Variance()
  {
        return (EReference)getuncert_Normal().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Normal_CoefficientOfVariance()
  {
        return (EReference)getuncert_Normal().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_CoefficientOfVariance()
  {
    if (uncert_CoefficientOfVarianceEClass == null)
    {
      uncert_CoefficientOfVarianceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(111);
    }
    return uncert_CoefficientOfVarianceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_CoefficientOfVariance_Xmlns()
  {
        return (EReference)getuncert_CoefficientOfVariance().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_CoefficientOfVariance_Expr()
  {
        return (EReference)getuncert_CoefficientOfVariance().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_StdDev()
  {
    if (uncert_StdDevEClass == null)
    {
      uncert_StdDevEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(112);
    }
    return uncert_StdDevEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_StdDev_Xmlns()
  {
        return (EReference)getuncert_StdDev().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_StdDev_Expr()
  {
        return (EReference)getuncert_StdDev().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_DistributionParamType()
  {
    if (uncert_DistributionParamTypeEClass == null)
    {
      uncert_DistributionParamTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(113);
    }
    return uncert_DistributionParamTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionParamType_Equation()
  {
        return (EReference)getuncert_DistributionParamType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionParamType_Scalar()
  {
        return (EReference)getuncert_DistributionParamType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_DistributionParamType_Var()
  {
        return (EReference)getuncert_DistributionParamType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_MeanParamType()
  {
    if (uncert_MeanParamTypeEClass == null)
    {
      uncert_MeanParamTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(114);
    }
    return uncert_MeanParamTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_MeanParamType_Expr()
  {
        return (EReference)getuncert_MeanParamType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_Mean()
  {
    if (uncert_MeanEClass == null)
    {
      uncert_MeanEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(115);
    }
    return uncert_MeanEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Mean_Xmlns()
  {
        return (EReference)getuncert_Mean().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Mean_Expr()
  {
        return (EReference)getuncert_Mean().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_Variance()
  {
    if (uncert_VarianceEClass == null)
    {
      uncert_VarianceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(116);
    }
    return uncert_VarianceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Variance_Xmlns()
  {
        return (EReference)getuncert_Variance().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Variance_Expr()
  {
        return (EReference)getuncert_Variance().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_PDF()
  {
    if (uncert_PDFEClass == null)
    {
      uncert_PDFEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(117);
    }
    return uncert_PDFEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_PDF_Xmlns()
  {
        return (EReference)getuncert_PDF().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_PDF_FunctionParameter()
  {
        return (EReference)getuncert_PDF().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_PDF_Equation()
  {
        return (EReference)getuncert_PDF().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_ParameterDefinitionType()
  {
    if (uncert_ParameterDefinitionTypeEClass == null)
    {
      uncert_ParameterDefinitionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(118);
    }
    return uncert_ParameterDefinitionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_ParameterDefinitionType_Id()
  {
        return (EReference)getuncert_ParameterDefinitionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_ParameterDefinitionType_Name()
  {
        return (EReference)getuncert_ParameterDefinitionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_ParameterDefinitionType_Xmlns()
  {
        return (EReference)getuncert_ParameterDefinitionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_Poison()
  {
    if (uncert_PoisonEClass == null)
    {
      uncert_PoisonEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(119);
    }
    return uncert_PoisonEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Poison_Xmlns()
  {
        return (EReference)getuncert_Poison().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Poison_Rate()
  {
        return (EReference)getuncert_Poison().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_Rate()
  {
    if (uncert_RateEClass == null)
    {
      uncert_RateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(120);
    }
    return uncert_RateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Rate_Xmlns()
  {
        return (EReference)getuncert_Rate().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Rate_Expr()
  {
        return (EReference)getuncert_Rate().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_Student_t()
  {
    if (uncert_Student_tEClass == null)
    {
      uncert_Student_tEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(121);
    }
    return uncert_Student_tEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Student_t_Xmlns()
  {
        return (EReference)getuncert_Student_t().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Student_t_Location()
  {
        return (EReference)getuncert_Student_t().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Student_t_Scale()
  {
        return (EReference)getuncert_Student_t().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Student_t_DegreesOfFreedom()
  {
        return (EReference)getuncert_Student_t().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncertDegreesOfFreedom()
  {
    if (uncertDegreesOfFreedomEClass == null)
    {
      uncertDegreesOfFreedomEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(122);
    }
    return uncertDegreesOfFreedomEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncertDegreesOfFreedom_Xmlns()
  {
        return (EReference)getuncertDegreesOfFreedom().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncertDegreesOfFreedom_Expr()
  {
        return (EReference)getuncertDegreesOfFreedom().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_Scale()
  {
    if (uncert_ScaleEClass == null)
    {
      uncert_ScaleEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(123);
    }
    return uncert_ScaleEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Scale_Xmlns()
  {
        return (EReference)getuncert_Scale().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Scale_Expr()
  {
        return (EReference)getuncert_Scale().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_Location()
  {
    if (uncert_LocationEClass == null)
    {
      uncert_LocationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(124);
    }
    return uncert_LocationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Location_Xmlns()
  {
        return (EReference)getuncert_Location().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Location_Expr()
  {
        return (EReference)getuncert_Location().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getuncert_Uniform()
  {
    if (uncert_UniformEClass == null)
    {
      uncert_UniformEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(125);
    }
    return uncert_UniformEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getuncert_Uniform_Xmlns()
  {
        return (EReference)getuncert_Uniform().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_symbId()
  {
    if (ct_symbIdEClass == null)
    {
      ct_symbIdEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(126);
    }
    return ct_symbIdEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_symbId_SymbId()
  {
        return (EAttribute)getct_symbId().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_id()
  {
    if (ct_idEClass == null)
    {
      ct_idEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(127);
    }
    return ct_idEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_id_Id()
  {
        return (EAttribute)getct_id().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_symbolType()
  {
    if (ct_symbolTypeEClass == null)
    {
      ct_symbolTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(128);
    }
    return ct_symbolTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_symbolType_SymbolType()
  {
        return (EReference)getct_symbolType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_block()
  {
    if (ct_blockEClass == null)
    {
      ct_blockEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(129);
    }
    return ct_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_block_Block()
  {
        return (EAttribute)getct_block().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_writtenVersion()
  {
    if (ct_writtenVersionEClass == null)
    {
      ct_writtenVersionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(130);
    }
    return ct_writtenVersionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_writtenVersion_WrittenVersion()
  {
        return (EAttribute)getct_writtenVersion().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_Decimal_Value()
  {
    if (ct_Decimal_ValueEClass == null)
    {
      ct_Decimal_ValueEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(131);
    }
    return ct_Decimal_ValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_Decimal_Value_Value()
  {
        return (EAttribute)getct_Decimal_Value().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_String_Value()
  {
    if (ct_String_ValueEClass == null)
    {
      ct_String_ValueEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(132);
    }
    return ct_String_ValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_String_Value_Value()
  {
        return (EAttribute)getct_String_Value().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_Int_Value()
  {
    if (ct_Int_ValueEClass == null)
    {
      ct_Int_ValueEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(133);
    }
    return ct_Int_ValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_Int_Value_Value()
  {
        return (EAttribute)getct_Int_Value().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_catId()
  {
    if (ct_catIdEClass == null)
    {
      ct_catIdEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(134);
    }
    return ct_catIdEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_catId_CatId()
  {
        return (EAttribute)getct_catId().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_ReplicatesType()
  {
    if (ct_ReplicatesTypeEClass == null)
    {
      ct_ReplicatesTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(135);
    }
    return ct_ReplicatesTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ReplicatesType_SymbId()
  {
        return (EReference)getct_ReplicatesType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ReplicatesType_Xmlns()
  {
        return (EReference)getct_ReplicatesType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ReplicatesType_Expr()
  {
        return (EReference)getct_ReplicatesType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_AnnotationType()
  {
    if (ct_AnnotationTypeEClass == null)
    {
      ct_AnnotationTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(136);
    }
    return ct_AnnotationTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_AnnotationType_Xmlns()
  {
        return (EReference)getct_AnnotationType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_AnnotationType_Description()
  {
        return (EAttribute)getct_AnnotationType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_Rhs()
  {
    if (ct_RhsEClass == null)
    {
      ct_RhsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(137);
    }
    return ct_RhsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_Equation()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_Scalar()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_Constant()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_String()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_Sequence()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_Vector()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_FunctionCall()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_DataSet()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_Var()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_Rhs_Distribution()
  {
        return (EReference)getct_Rhs().getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_DataSetType()
  {
    if (ct_DataSetTypeEClass == null)
    {
      ct_DataSetTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(138);
    }
    return ct_DataSetTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_DataSetType_Xmlns()
  {
        return (EReference)getct_DataSetType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_DataSetType_Definition()
  {
        return (EReference)getct_DataSetType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_DataSetType_ExternalSource()
  {
        return (EReference)getct_DataSetType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_DataSetType_Row()
  {
        return (EReference)getct_DataSetType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_DataSetType_InternalSource()
  {
        return (EReference)getct_DataSetType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_DefinitionType()
  {
    if (ct_DefinitionTypeEClass == null)
    {
      ct_DefinitionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(139);
    }
    return ct_DefinitionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_DefinitionType_Xmlns()
  {
        return (EReference)getct_DefinitionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_DefinitionType_Definition()
  {
        return (EReference)getct_DefinitionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_DefinitionRhsType()
  {
    if (ct_DefinitionRhsTypeEClass == null)
    {
      ct_DefinitionRhsTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(140);
    }
    return ct_DefinitionRhsTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_DefinitionRhsType_Xmlns()
  {
        return (EReference)getct_DefinitionRhsType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_DefinitionRhsType_Definition()
  {
        return (EReference)getct_DefinitionRhsType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_ColumnType()
  {
    if (ct_ColumnTypeEClass == null)
    {
      ct_ColumnTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(141);
    }
    return ct_ColumnTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ColumnType_ColumnNum()
  {
        return (EReference)getct_ColumnType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_ColumnType_ColumnVar()
  {
        return (EAttribute)getct_ColumnType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ColumnType_Xmlns()
  {
        return (EReference)getct_ColumnType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_ExternalSourceType()
  {
    if (ct_ExternalSourceTypeEClass == null)
    {
      ct_ExternalSourceTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(142);
    }
    return ct_ExternalSourceTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_ExternalSourceType_Format()
  {
        return (EAttribute)getct_ExternalSourceType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ExternalSourceType_HeaderLine()
  {
        return (EReference)getct_ExternalSourceType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_ExternalSourceType_IgnoreLineSymbol()
  {
        return (EAttribute)getct_ExternalSourceType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_ExternalSourceType_Url()
  {
        return (EAttribute)getct_ExternalSourceType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ExternalSourceType_Xmlns()
  {
        return (EReference)getct_ExternalSourceType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_RowType()
  {
    if (ct_RowTypeEClass == null)
    {
      ct_RowTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(143);
    }
    return ct_RowTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_RowType_Xmlns()
  {
        return (EReference)getct_RowType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_RowType_Row()
  {
        return (EReference)getct_RowType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_RowTypeContent()
  {
    if (ct_RowTypeContentEClass == null)
    {
      ct_RowTypeContentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(144);
    }
    return ct_RowTypeContentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_RowTypeContent_Cell()
  {
        return (EReference)getct_RowTypeContent().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_RowTypeContent_Null()
  {
        return (EAttribute)getct_RowTypeContent().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_CellType()
  {
    if (ct_CellTypeEClass == null)
    {
      ct_CellTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(145);
    }
    return ct_CellTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_CellType_Value()
  {
        return (EReference)getct_CellType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_CellType_Xmlns()
  {
        return (EReference)getct_CellType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_InternalSourceType()
  {
    if (ct_InternalSourceTypeEClass == null)
    {
      ct_InternalSourceTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(146);
    }
    return ct_InternalSourceTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_InternalSourceType_SymbId()
  {
        return (EReference)getct_InternalSourceType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_InternalSourceType_Block()
  {
        return (EReference)getct_InternalSourceType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_InternalSourceType_Xmlns()
  {
        return (EReference)getct_InternalSourceType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_ScalarRhs()
  {
    if (ct_ScalarRhsEClass == null)
    {
      ct_ScalarRhsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(147);
    }
    return ct_ScalarRhsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ScalarRhs_Equation()
  {
        return (EReference)getct_ScalarRhs().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ScalarRhs_Scalar()
  {
        return (EReference)getct_ScalarRhs().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ScalarRhs_Var()
  {
        return (EReference)getct_ScalarRhs().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ScalarRhs_String()
  {
        return (EReference)getct_ScalarRhs().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_ScalarRhs_FunctionCall()
  {
        return (EReference)getct_ScalarRhs().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_levelId()
  {
    if (ct_levelIdEClass == null)
    {
      ct_levelIdEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(148);
    }
    return ct_levelIdEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_levelId_LevelId()
  {
        return (EAttribute)getct_levelId().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_Name()
  {
    if (ct_NameEClass == null)
    {
      ct_NameEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(149);
    }
    return ct_NameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_Name_Name()
  {
        return (EAttribute)getct_Name().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_VectorType()
  {
    if (ct_VectorTypeEClass == null)
    {
      ct_VectorTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(150);
    }
    return ct_VectorTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VectorType_Xmlns()
  {
        return (EReference)getct_VectorType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VectorType_Sequence()
  {
        return (EReference)getct_VectorType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VectorType_Scalar()
  {
        return (EReference)getct_VectorType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_SequenceType()
  {
    if (ct_SequenceTypeEClass == null)
    {
      ct_SequenceTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(151);
    }
    return ct_SequenceTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_SequenceType_Begin()
  {
        return (EReference)getct_SequenceType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_SequenceType_End()
  {
        return (EReference)getct_SequenceType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_SequenceType_Repetition()
  {
        return (EReference)getct_SequenceType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_SequenceType_Stepwise()
  {
        return (EReference)getct_SequenceType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_SequenceType_Xmlns()
  {
        return (EReference)getct_SequenceType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_SymbolTypeType()
  {
    if (ct_SymbolTypeTypeEClass == null)
    {
      ct_SymbolTypeTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(152);
    }
    return ct_SymbolTypeTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_SymbolTypeType_Scalar()
  {
        return (EAttribute)getct_SymbolTypeType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_SymbolTypeType_Derivative()
  {
        return (EAttribute)getct_SymbolTypeType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_SymbolTypeType_Distrib()
  {
        return (EAttribute)getct_SymbolTypeType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_SymbolTypeType_DataSet()
  {
        return (EAttribute)getct_SymbolTypeType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_VariableDefinitionType()
  {
    if (ct_VariableDefinitionTypeEClass == null)
    {
      ct_VariableDefinitionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(153);
    }
    return ct_VariableDefinitionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableDefinitionType_SymbId()
  {
        return (EReference)getct_VariableDefinitionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableDefinitionType_SymbolType()
  {
        return (EReference)getct_VariableDefinitionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableDefinitionType_IndependentVar()
  {
        return (EReference)getct_VariableDefinitionType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableDefinitionType_Xmlns()
  {
        return (EReference)getct_VariableDefinitionType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableDefinitionType_Description()
  {
        return (EReference)getct_VariableDefinitionType().getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableDefinitionType_Expr()
  {
        return (EReference)getct_VariableDefinitionType().getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_VariableAssignmentType()
  {
    if (ct_VariableAssignmentTypeEClass == null)
    {
      ct_VariableAssignmentTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(154);
    }
    return ct_VariableAssignmentTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableAssignmentType_SymbId()
  {
        return (EReference)getct_VariableAssignmentType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableAssignmentType_Block()
  {
        return (EReference)getct_VariableAssignmentType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableAssignmentType_Xmlns()
  {
        return (EReference)getct_VariableAssignmentType().getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_VariableAssignmentType_Expr()
  {
        return (EReference)getct_VariableAssignmentType().getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_RestrictionType()
  {
    if (ct_RestrictionTypeEClass == null)
    {
      ct_RestrictionTypeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(155);
    }
    return ct_RestrictionTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_RestrictionType_Xmlns()
  {
        return (EReference)getct_RestrictionType().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getct_RestrictionType_Expr()
  {
        return (EReference)getct_RestrictionType().getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getct_xmlns()
  {
    if (ct_xmlnsEClass == null)
    {
      ct_xmlnsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(PharmaMLPackage.eNS_URI).getEClassifiers().get(156);
    }
    return ct_xmlnsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getct_xmlns_Xmlns()
  {
        return (EAttribute)getct_xmlns().getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PharmaMLFactory getPharmaMLFactory()
  {
    return (PharmaMLFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isLoaded = false;

  /**
   * Laods the package and any sub-packages from their serialized form.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void loadPackage()
  {
    if (isLoaded) return;
    isLoaded = true;

    URL url = getClass().getResource(packageFilename);
    if (url == null)
    {
      throw new RuntimeException("Missing serialized package: " + packageFilename);
    }
    URI uri = URI.createURI(url.toString());
    Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
    try
    {
      resource.load(null);
    }
    catch (IOException exception)
    {
      throw new WrappedException(exception);
    }
    initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
    createResource(eNS_URI);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isFixed = false;

  /**
   * Fixes up the loaded package, to make it appear as if it had been programmatically built.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void fixPackageContents()
  {
    if (isFixed) return;
    isFixed = true;
    fixEClassifiers();
  }

  /**
   * Sets the instance class on the given classifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void fixInstanceClass(EClassifier eClassifier)
  {
    if (eClassifier.getInstanceClassName() == null)
    {
      eClassifier.setInstanceClassName("org.ddmore.pml.pharmaML." + eClassifier.getName());
      setGeneratedClassName(eClassifier);
    }
  }

} //PharmaMLPackageImpl

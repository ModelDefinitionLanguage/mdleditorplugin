/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.ddmore.pml.pharmML.PharmMLFactory
 * @model kind="package"
 * @generated
 */
public interface PharmMLPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "pharmML";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.ddmore.eu/pml/PharmML";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "pharmML";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PharmMLPackage eINSTANCE = eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl.init();

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl <em>Mml Pharm ML</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMml_PharmML()
   * @generated
   */
  int MML_PHARM_ML = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARM_ML__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARM_ML__NAME = 1;

  /**
   * The feature id for the '<em><b>Independent Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARM_ML__INDEPENDENT_VAR = 2;

  /**
   * The feature id for the '<em><b>Written Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARM_ML__WRITTEN_VERSION = 3;

  /**
   * The feature id for the '<em><b>Symbol Definition</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARM_ML__SYMBOL_DEFINITION = 4;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARM_ML__EXPR = 5;

  /**
   * The feature id for the '<em><b>Design</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARM_ML__DESIGN = 6;

  /**
   * The feature id for the '<em><b>Modeling Steps</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARM_ML__MODELING_STEPS = 7;

  /**
   * The number of structural features of the '<em>Mml Pharm ML</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARM_ML_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Mml_PharmaMLContentImpl <em>Mml Pharma ML Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Mml_PharmaMLContentImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMml_PharmaMLContent()
   * @generated
   */
  int MML_PHARMA_ML_CONTENT = 1;

  /**
   * The feature id for the '<em><b>Structural Model</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL = 0;

  /**
   * The feature id for the '<em><b>Model Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML_CONTENT__MODEL_DEFINITION = 1;

  /**
   * The number of structural features of the '<em>Mml Pharma ML Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML_CONTENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Mml_SymbolDefinitionTypeImpl <em>Mml Symbol Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Mml_SymbolDefinitionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMml_SymbolDefinitionType()
   * @generated
   */
  int MML_SYMBOL_DEFINITION_TYPE = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__SYMB_ID = 2;

  /**
   * The feature id for the '<em><b>Symbol Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE = 3;

  /**
   * The feature id for the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION = 4;

  /**
   * The feature id for the '<em><b>Function Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION = 5;

  /**
   * The number of structural features of the '<em>Mml Symbol Definition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Mml_FunctionDefinitionTypeImpl <em>Mml Function Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Mml_FunctionDefinitionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMml_FunctionDefinitionType()
   * @generated
   */
  int MML_FUNCTION_DEFINITION_TYPE = 3;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNCTION_DEFINITION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Function Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNCTION_DEFINITION_TYPE__FUNCTION_ARGUMENTS = 1;

  /**
   * The feature id for the '<em><b>Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNCTION_DEFINITION_TYPE__DEFINITION = 2;

  /**
   * The number of structural features of the '<em>Mml Function Definition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNCTION_DEFINITION_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Mml_FuncParameterDefinitionTypeImpl <em>Mml Func Parameter Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Mml_FuncParameterDefinitionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMml_FuncParameterDefinitionType()
   * @generated
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYPE = 4;

  /**
   * The number of structural features of the '<em>Mml Func Parameter Definition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYPE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ModelDefinitionTypeImpl <em>mdef Model Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ModelDefinitionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ModelDefinitionType()
   * @generated
   */
  int MDEF_MODEL_DEFINITION_TYPE = 5;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_MODEL_DEFINITION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Variability Level</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_MODEL_DEFINITION_TYPE__VARIABILITY_LEVEL = 1;

  /**
   * The feature id for the '<em><b>Covariate Model</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL = 2;

  /**
   * The feature id for the '<em><b>Parameter Model</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL = 3;

  /**
   * The feature id for the '<em><b>Structura Model</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_MODEL_DEFINITION_TYPE__STRUCTURA_MODEL = 4;

  /**
   * The feature id for the '<em><b>Observation Model</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL = 5;

  /**
   * The number of structural features of the '<em>mdef Model Definition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_MODEL_DEFINITION_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeNameImpl <em>mdef Parameter Type Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeNameImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ParameterTypeName()
   * @generated
   */
  int MDEF_PARAMETER_TYPE_NAME = 6;

  /**
   * The feature id for the '<em><b>Log</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE_NAME__LOG = 0;

  /**
   * The feature id for the '<em><b>Logit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE_NAME__LOGIT = 1;

  /**
   * The feature id for the '<em><b>None</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE_NAME__NONE = 2;

  /**
   * The number of structural features of the '<em>mdef Parameter Type Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE_NAME_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_StructuralModelTypeImpl <em>mdef Structural Model Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_StructuralModelTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_StructuralModelType()
   * @generated
   */
  int MDEF_STRUCTURAL_MODEL_TYPE = 7;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE__ID = 1;

  /**
   * The feature id for the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION = 2;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE__PARAMETER = 3;

  /**
   * The feature id for the '<em><b>Variable</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE__VARIABLE = 4;

  /**
   * The feature id for the '<em><b>Import</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE__IMPORT = 5;

  /**
   * The feature id for the '<em><b>Initia Condition</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE__INITIA_CONDITION = 6;

  /**
   * The number of structural features of the '<em>mdef Structural Model Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ObservationModelTypeImpl <em>mdef Observation Model Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ObservationModelTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ObservationModelType()
   * @generated
   */
  int MDEF_OBSERVATION_MODEL_TYPE = 8;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_OBSERVATION_MODEL_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_OBSERVATION_MODEL_TYPE__ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_OBSERVATION_MODEL_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_OBSERVATION_MODEL_TYPE__PARAMETER = 3;

  /**
   * The feature id for the '<em><b>Continuous</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_OBSERVATION_MODEL_TYPE__CONTINUOUS = 4;

  /**
   * The number of structural features of the '<em>mdef Observation Model Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_OBSERVATION_MODEL_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousObsModelTypeImpl <em>mdef Continuous Obs Model Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ContinuousObsModelTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ContinuousObsModelType()
   * @generated
   */
  int MDEF_CONTINUOUS_OBS_MODEL_TYPE = 9;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_OBS_MODEL_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID = 1;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR = 2;

  /**
   * The feature id for the '<em><b>Error Model</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL = 3;

  /**
   * The feature id for the '<em><b>Random Effect</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_OBS_MODEL_TYPE__RANDOM_EFFECT = 4;

  /**
   * The number of structural features of the '<em>mdef Continuous Obs Model Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_OBS_MODEL_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ErrorModelImpl <em>mdef Error Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ErrorModelImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ErrorModel()
   * @generated
   */
  int MDEF_ERROR_MODEL = 10;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_ERROR_MODEL__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_ERROR_MODEL__VAR = 1;

  /**
   * The feature id for the '<em><b>Function Argument</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_ERROR_MODEL__FUNCTION_ARGUMENT = 2;

  /**
   * The number of structural features of the '<em>mdef Error Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_ERROR_MODEL_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_RandomEffectTypeImpl <em>mdef Random Effect Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_RandomEffectTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_RandomEffectType()
   * @generated
   */
  int MDEF_RANDOM_EFFECT_TYPE = 11;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_RANDOM_EFFECT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Distribution</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION = 1;

  /**
   * The number of structural features of the '<em>mdef Random Effect Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_RANDOM_EFFECT_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterModelTypeImpl <em>mdef Parameter Model Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ParameterModelTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ParameterModelType()
   * @generated
   */
  int MDEF_PARAMETER_MODEL_TYPE = 12;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_MODEL_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_MODEL_TYPE__ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_MODEL_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_MODEL_TYPE__PARAMETER = 3;

  /**
   * The feature id for the '<em><b>Correlation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_MODEL_TYPE__CORRELATION = 4;

  /**
   * The number of structural features of the '<em>mdef Parameter Model Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_MODEL_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_CorrelationTypeImpl <em>mdef Correlation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_CorrelationTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_CorrelationType()
   * @generated
   */
  int MDEF_CORRELATION_TYPE = 13;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_TYPE__LEVEL_ID = 1;

  /**
   * The feature id for the '<em><b>Param Var1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_TYPE__PARAM_VAR1 = 2;

  /**
   * The feature id for the '<em><b>Param Var2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_TYPE__PARAM_VAR2 = 3;

  /**
   * The feature id for the '<em><b>Correlation Coefficient</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT = 4;

  /**
   * The feature id for the '<em><b>Covariance</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_TYPE__COVARIANCE = 5;

  /**
   * The number of structural features of the '<em>mdef Correlation Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_Param1VarTypeImpl <em>mdef Param1 Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_Param1VarTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_Param1VarType()
   * @generated
   */
  int MDEF_PARAM1_VAR_TYPE = 14;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAM1_VAR_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAM1_VAR_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAM1_VAR_TYPE__SYMB_ID = 2;

  /**
   * The number of structural features of the '<em>mdef Param1 Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAM1_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_Param2VarTypeImpl <em>mdef Param2 Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_Param2VarTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_Param2VarType()
   * @generated
   */
  int MDEF_PARAM2_VAR_TYPE = 15;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAM2_VAR_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAM2_VAR_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAM2_VAR_TYPE__SYMB_ID = 2;

  /**
   * The number of structural features of the '<em>mdef Param2 Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAM2_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdefCovarianceTypeImpl <em>mdef Covariance Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdefCovarianceTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdefCovarianceType()
   * @generated
   */
  int MDEF_COVARIANCE_TYPE = 16;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIANCE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIANCE_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>mdef Covariance Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIANCE_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_CorrelationCoefficientTypeImpl <em>mdef Correlation Coefficient Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_CorrelationCoefficientTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_CorrelationCoefficientType()
   * @generated
   */
  int MDEF_CORRELATION_COEFFICIENT_TYPE = 17;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_COEFFICIENT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_COEFFICIENT_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>mdef Correlation Coefficient Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_COEFFICIENT_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_CovariateModelTypeImpl <em>mdef Covariate Model Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_CovariateModelTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_CovariateModelType()
   * @generated
   */
  int MDEF_COVARIATE_MODEL_TYPE = 18;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_MODEL_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_MODEL_TYPE__ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_MODEL_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_MODEL_TYPE__PARAMETER = 3;

  /**
   * The feature id for the '<em><b>Covariate</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_MODEL_TYPE__COVARIATE = 4;

  /**
   * The number of structural features of the '<em>mdef Covariate Model Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_MODEL_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl <em>mdef Parameter Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ParameterType()
   * @generated
   */
  int MDEF_PARAMETER_TYPE = 19;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__SYMB_ID = 2;

  /**
   * The feature id for the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__TRANSFORMATION = 3;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__VAR = 4;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__SCALAR = 5;

  /**
   * The feature id for the '<em><b>Equation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__EQUATION = 6;

  /**
   * The feature id for the '<em><b>Random Effect</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__RANDOM_EFFECT = 7;

  /**
   * The feature id for the '<em><b>Covariate</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__COVARIATE = 8;

  /**
   * The number of structural features of the '<em>mdef Parameter Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_CovariateVariabilityTypeImpl <em>mdef Covariate Variability Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_CovariateVariabilityTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_CovariateVariabilityType()
   * @generated
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE = 20;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID = 2;

  /**
   * The feature id for the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION = 3;

  /**
   * The feature id for the '<em><b>Continuous</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS = 4;

  /**
   * The feature id for the '<em><b>Categorical</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL = 5;

  /**
   * The number of structural features of the '<em>mdef Covariate Variability Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_transformationImpl <em>mdef transformation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_transformationImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_transformation()
   * @generated
   */
  int MDEF_TRANSFORMATION = 21;

  /**
   * The feature id for the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_TRANSFORMATION__TRANSFORMATION = 0;

  /**
   * The number of structural features of the '<em>mdef transformation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_TRANSFORMATION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_CategoricalTypeImpl <em>mdef Categorical Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_CategoricalTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_CategoricalType()
   * @generated
   */
  int MDEF_CATEGORICAL_TYPE = 22;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORICAL_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Category</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORICAL_TYPE__CATEGORY = 1;

  /**
   * The number of structural features of the '<em>mdef Categorical Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORICAL_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousCovariateTypeImpl <em>mdef Continuous Covariate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ContinuousCovariateTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ContinuousCovariateType()
   * @generated
   */
  int MDEF_CONTINUOUS_COVARIATE_TYPE = 23;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_COVARIATE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Distribution</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION = 1;

  /**
   * The feature id for the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION = 2;

  /**
   * The number of structural features of the '<em>mdef Continuous Covariate Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_COVARIATE_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_TransformationTypeImpl <em>mdef Transformation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_TransformationTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_TransformationType()
   * @generated
   */
  int MDEF_TRANSFORMATION_TYPE = 24;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_TRANSFORMATION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_TRANSFORMATION_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>mdef Transformation Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_TRANSFORMATION_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_VariabilityLevelDefnTypeImpl <em>mdef Variability Level Defn Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_VariabilityLevelDefnTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_VariabilityLevelDefnType()
   * @generated
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE = 25;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE__ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE__NAME = 2;

  /**
   * The number of structural features of the '<em>mdef Variability Level Defn Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ImportTypeImpl <em>mdef Import Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ImportTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ImportType()
   * @generated
   */
  int MDEF_IMPORT_TYPE = 26;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE__RESOURCE = 1;

  /**
   * The feature id for the '<em><b>Resource Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE__RESOURCE_TYPE = 2;

  /**
   * The feature id for the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE__SOURCE = 3;

  /**
   * The feature id for the '<em><b>Link</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE__LINK = 4;

  /**
   * The number of structural features of the '<em>mdef Import Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ImportLinkTypeImpl <em>mdef Import Link Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ImportLinkTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ImportLinkType()
   * @generated
   */
  int MDEF_IMPORT_LINK_TYPE = 27;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_LINK_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Symbol Ptr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_LINK_TYPE__SYMBOL_PTR = 1;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_LINK_TYPE__VAR = 2;

  /**
   * The number of structural features of the '<em>mdef Import Link Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_LINK_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ImportResourceTypeTypeImpl <em>mdef Import Resource Type Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ImportResourceTypeTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ImportResourceTypeType()
   * @generated
   */
  int MDEF_IMPORT_RESOURCE_TYPE_TYPE = 28;

  /**
   * The feature id for the '<em><b>Sbml</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_RESOURCE_TYPE_TYPE__SBML = 0;

  /**
   * The feature id for the '<em><b>Pharmml</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_RESOURCE_TYPE_TYPE__PHARMML = 1;

  /**
   * The number of structural features of the '<em>mdef Import Resource Type Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_RESOURCE_TYPE_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_CovariateTypeImpl <em>mdef Covariate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_CovariateTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_CovariateType()
   * @generated
   */
  int MDEF_COVARIATE_TYPE = 29;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_TYPE__VAR = 1;

  /**
   * The feature id for the '<em><b>Fixed Effect</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_TYPE__FIXED_EFFECT = 2;

  /**
   * The number of structural features of the '<em>mdef Covariate Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_FixedEffectTypeImpl <em>mdef Fixed Effect Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_FixedEffectTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_FixedEffectType()
   * @generated
   */
  int MDEF_FIXED_EFFECT_TYPE = 30;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_FIXED_EFFECT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_FIXED_EFFECT_TYPE__VAR = 1;

  /**
   * The feature id for the '<em><b>Category</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_FIXED_EFFECT_TYPE__CATEGORY = 2;

  /**
   * The number of structural features of the '<em>mdef Fixed Effect Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_FIXED_EFFECT_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_CategoryTypeImpl <em>mdef Category Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_CategoryTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_CategoryType()
   * @generated
   */
  int MDEF_CATEGORY_TYPE = 31;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORY_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Cat Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORY_TYPE__CAT_ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORY_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Probability</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORY_TYPE__PROBABILITY = 3;

  /**
   * The number of structural features of the '<em>mdef Category Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORY_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ProbabilityTypeImpl <em>mdef Probability Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ProbabilityTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ProbabilityType()
   * @generated
   */
  int MDEF_PROBABILITY_TYPE = 32;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PROBABILITY_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PROBABILITY_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>mdef Probability Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PROBABILITY_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterRandomEffectTypeImpl <em>mdef Parameter Random Effect Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_ParameterRandomEffectTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_ParameterRandomEffectType()
   * @generated
   */
  int MDEF_PARAMETER_RANDOM_EFFECT_TYPE = 33;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_RANDOM_EFFECT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_RANDOM_EFFECT_TYPE__LEVEL_ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_RANDOM_EFFECT_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_RANDOM_EFFECT_TYPE__SYMB_ID = 3;

  /**
   * The feature id for the '<em><b>Distribution</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_RANDOM_EFFECT_TYPE__DISTRIBUTION = 4;

  /**
   * The number of structural features of the '<em>mdef Parameter Random Effect Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_RANDOM_EFFECT_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mdef_InitialConditionTypeImpl <em>mdef Initial Condition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mdef_InitialConditionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmdef_InitialConditionType()
   * @generated
   */
  int MDEF_INITIAL_CONDITION_TYPE = 34;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_INITIAL_CONDITION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_INITIAL_CONDITION_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_INITIAL_CONDITION_TYPE__SYMB_ID = 2;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_INITIAL_CONDITION_TYPE__EXPR = 3;

  /**
   * The number of structural features of the '<em>mdef Initial Condition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_INITIAL_CONDITION_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsImpl <em>msteps Modeling Steps</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_ModelingSteps()
   * @generated
   */
  int MSTEPS_MODELING_STEPS = 35;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Variable</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS__VARIABLE = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS__EXPR = 2;

  /**
   * The feature id for the '<em><b>Step Dependencies</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES = 3;

  /**
   * The number of structural features of the '<em>msteps Modeling Steps</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsContentImpl <em>msteps Modeling Steps Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsContentImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_ModelingStepsContent()
   * @generated
   */
  int MSTEPS_MODELING_STEPS_CONTENT = 36;

  /**
   * The feature id for the '<em><b>Estimation Step</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP = 0;

  /**
   * The feature id for the '<em><b>Simulation Step</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP = 1;

  /**
   * The number of structural features of the '<em>msteps Modeling Steps Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS_CONTENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_StepDependenciesTypeImpl <em>msteps Step Dependencies Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_StepDependenciesTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_StepDependenciesType()
   * @generated
   */
  int MSTEPS_STEP_DEPENDENCIES_TYPE = 37;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_DEPENDENCIES_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_DEPENDENCIES_TYPE__DESCRIPTION = 1;

  /**
   * The feature id for the '<em><b>Step</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_DEPENDENCIES_TYPE__STEP = 2;

  /**
   * The number of structural features of the '<em>msteps Step Dependencies Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_DEPENDENCIES_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_StepTypeImpl <em>msteps Step Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_StepTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_StepType()
   * @generated
   */
  int MSTEPS_STEP_TYPE = 38;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_TYPE__ID_REF = 1;

  /**
   * The feature id for the '<em><b>Dependant Step</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_TYPE__DEPENDANT_STEP = 2;

  /**
   * The number of structural features of the '<em>msteps Step Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_DependantStepTypeImpl <em>msteps Dependant Step Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_DependantStepTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_DependantStepType()
   * @generated
   */
  int MSTEPS_DEPENDANT_STEP_TYPE = 39;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_DEPENDANT_STEP_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_DEPENDANT_STEP_TYPE__ID_REF = 1;

  /**
   * The number of structural features of the '<em>msteps Dependant Step Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_DEPENDANT_STEP_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationStepImpl <em>msteps Estimation Step</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_EstimationStepImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_EstimationStep()
   * @generated
   */
  int MSTEPS_ESTIMATION_STEP = 40;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP__ID = 1;

  /**
   * The feature id for the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP__DESCRIPTION = 2;

  /**
   * The feature id for the '<em><b>Initial Value</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP__INITIAL_VALUE = 3;

  /**
   * The feature id for the '<em><b>Objective Data Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET = 4;

  /**
   * The feature id for the '<em><b>Parameters To Estimate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE = 5;

  /**
   * The feature id for the '<em><b>Estimation Operation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP__ESTIMATION_OPERATION = 6;

  /**
   * The number of structural features of the '<em>msteps Estimation Step</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_ParametersToEstimateTypeImpl <em>msteps Parameters To Estimate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_ParametersToEstimateTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_ParametersToEstimateType()
   * @generated
   */
  int MSTEPS_PARAMETERS_TO_ESTIMATE_TYPE = 41;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_PARAMETERS_TO_ESTIMATE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Variable</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_PARAMETERS_TO_ESTIMATE_TYPE__VARIABLE = 1;

  /**
   * The number of structural features of the '<em>msteps Parameters To Estimate Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_PARAMETERS_TO_ESTIMATE_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mSteps_VariableEstimateTypeImpl <em>mSteps Variable Estimate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mSteps_VariableEstimateTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmSteps_VariableEstimateType()
   * @generated
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE = 42;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Fixed</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED = 2;

  /**
   * The feature id for the '<em><b>Symb ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID = 3;

  /**
   * The feature id for the '<em><b>Initial Estimate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE = 4;

  /**
   * The feature id for the '<em><b>Lower Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND = 5;

  /**
   * The feature id for the '<em><b>Upper Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND = 6;

  /**
   * The number of structural features of the '<em>mSteps Variable Estimate Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mSteps_fixedImpl <em>mSteps fixed</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mSteps_fixedImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmSteps_fixed()
   * @generated
   */
  int MSTEPS_FIXED = 43;

  /**
   * The feature id for the '<em><b>Fixed</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_FIXED__FIXED = 0;

  /**
   * The number of structural features of the '<em>mSteps fixed</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_FIXED_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mSteps_UpperBoundTypeImpl <em>mSteps Upper Bound Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mSteps_UpperBoundTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmSteps_UpperBoundType()
   * @generated
   */
  int MSTEPS_UPPER_BOUND_TYPE = 44;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_UPPER_BOUND_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_UPPER_BOUND_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>mSteps Upper Bound Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_UPPER_BOUND_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mSteps_LowerBoundTypeImpl <em>mSteps Lower Bound Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mSteps_LowerBoundTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmSteps_LowerBoundType()
   * @generated
   */
  int MSTEPS_LOWER_BOUND_TYPE = 45;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_LOWER_BOUND_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_LOWER_BOUND_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>mSteps Lower Bound Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_LOWER_BOUND_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.mSteps_InitialEstimateTypeImpl <em>mSteps Initial Estimate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.mSteps_InitialEstimateTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmSteps_InitialEstimateType()
   * @generated
   */
  int MSTEPS_INITIAL_ESTIMATE_TYPE = 46;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_ESTIMATE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_ESTIMATE_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>mSteps Initial Estimate Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_ESTIMATE_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_ObjectiveDataSetTypeImpl <em>msteps Objective Data Set Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_ObjectiveDataSetTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_ObjectiveDataSetType()
   * @generated
   */
  int MSTEPS_OBJECTIVE_DATA_SET_TYPE = 47;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Data Set Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF = 1;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING = 2;

  /**
   * The number of structural features of the '<em>msteps Objective Data Set Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBJECTIVE_DATA_SET_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeImpl <em>msteps Column Mapping Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_ColumnMappingType()
   * @generated
   */
  int MSTEPS_COLUMN_MAPPING_TYPE = 48;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Columnname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE__COLUMNNAME = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE__EXPR = 2;

  /**
   * The feature id for the '<em><b>Restriction</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION = 3;

  /**
   * The number of structural features of the '<em>msteps Column Mapping Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeContentImpl <em>msteps Column Mapping Type Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeContentImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_ColumnMappingTypeContent()
   * @generated
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_CONTENT = 49;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR = 0;

  /**
   * The feature id for the '<em><b>Use Variability Level</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL = 1;

  /**
   * The feature id for the '<em><b>Use Variability Node</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE = 2;

  /**
   * The feature id for the '<em><b>Dose Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR = 3;

  /**
   * The feature id for the '<em><b>Target Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR = 4;

  /**
   * The feature id for the '<em><b>Assign</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN = 5;

  /**
   * The feature id for the '<em><b>Output</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT = 6;

  /**
   * The number of structural features of the '<em>msteps Column Mapping Type Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_COLUMN_MAPPING_TYPE_CONTENT_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_OutputTypeImpl <em>msteps Output Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_OutputTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_OutputType()
   * @generated
   */
  int MSTEPS_OUTPUT_TYPE = 50;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OUTPUT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Vars</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OUTPUT_TYPE__VARS = 1;

  /**
   * The number of structural features of the '<em>msteps Output Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OUTPUT_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_AssignTypeImpl <em>msteps Assign Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_AssignTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_AssignType()
   * @generated
   */
  int MSTEPS_ASSIGN_TYPE = 51;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ASSIGN_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ASSIGN_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ASSIGN_TYPE__SYMB_ID = 2;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ASSIGN_TYPE__EXPR = 3;

  /**
   * The number of structural features of the '<em>msteps Assign Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ASSIGN_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_TargetVarTypeImpl <em>msteps Target Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_TargetVarTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_TargetVarType()
   * @generated
   */
  int MSTEPS_TARGET_VAR_TYPE = 52;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TARGET_VAR_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TARGET_VAR_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TARGET_VAR_TYPE__SYMB_ID = 2;

  /**
   * The number of structural features of the '<em>msteps Target Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TARGET_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_UseVariabilityLevelImpl <em>msteps Use Variability Level</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_UseVariabilityLevelImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_UseVariabilityLevel()
   * @generated
   */
  int MSTEPS_USE_VARIABILITY_LEVEL = 53;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_LEVEL__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_LEVEL__LEVEL_ID = 1;

  /**
   * The number of structural features of the '<em>msteps Use Variability Level</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_LEVEL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_UseVariabilityNodeImpl <em>msteps Use Variability Node</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_UseVariabilityNodeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_UseVariabilityNode()
   * @generated
   */
  int MSTEPS_USE_VARIABILITY_NODE = 54;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_NODE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_NODE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_NODE__SYMB_ID = 2;

  /**
   * The number of structural features of the '<em>msteps Use Variability Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_NODE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_SimulationStepImpl <em>msteps Simulation Step</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_SimulationStepImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_SimulationStep()
   * @generated
   */
  int MSTEPS_SIMULATION_STEP = 55;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP__ID = 1;

  /**
   * The feature id for the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP__DESCRIPTION = 2;

  /**
   * The feature id for the '<em><b>Replicates</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP__REPLICATES = 3;

  /**
   * The feature id for the '<em><b>Initial Value</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP__INITIAL_VALUE = 4;

  /**
   * The feature id for the '<em><b>Sim Data Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP__SIM_DATA_SET = 5;

  /**
   * The feature id for the '<em><b>Observations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP__OBSERVATIONS = 6;

  /**
   * The number of structural features of the '<em>msteps Simulation Step</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_SimDataSetTypeImpl <em>msteps Sim Data Set Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_SimDataSetTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_SimDataSetType()
   * @generated
   */
  int MSTEPS_SIM_DATA_SET_TYPE = 56;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIM_DATA_SET_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Data Set Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIM_DATA_SET_TYPE__DATA_SET_REF = 1;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIM_DATA_SET_TYPE__MAPPING = 2;

  /**
   * The number of structural features of the '<em>msteps Sim Data Set Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIM_DATA_SET_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_ObservationsTypeImpl <em>msteps Observations Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_ObservationsTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_ObservationsType()
   * @generated
   */
  int MSTEPS_OBSERVATIONS_TYPE = 57;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBSERVATIONS_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Timepoints</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS = 1;

  /**
   * The feature id for the '<em><b>Output</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBSERVATIONS_TYPE__OUTPUT = 2;

  /**
   * The feature id for the '<em><b>Write To</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBSERVATIONS_TYPE__WRITE_TO = 3;

  /**
   * The number of structural features of the '<em>msteps Observations Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBSERVATIONS_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_WriteToTypeImpl <em>msteps Write To Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_WriteToTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_WriteToType()
   * @generated
   */
  int MSTEPS_WRITE_TO_TYPE = 58;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_WRITE_TO_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_WRITE_TO_TYPE__VAR = 1;

  /**
   * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_WRITE_TO_TYPE__MAPPING = 2;

  /**
   * The number of structural features of the '<em>msteps Write To Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_WRITE_TO_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_TimepointsTypeImpl <em>msteps Timepoints Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_TimepointsTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_TimepointsType()
   * @generated
   */
  int MSTEPS_TIMEPOINTS_TYPE = 59;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TIMEPOINTS_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TIMEPOINTS_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>msteps Timepoints Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TIMEPOINTS_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_InitialValueTypeImpl <em>msteps Initial Value Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_InitialValueTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_InitialValueType()
   * @generated
   */
  int MSTEPS_INITIAL_VALUE_TYPE = 60;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_VALUE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_VALUE_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID = 2;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_VALUE_TYPE__EXPR = 3;

  /**
   * The number of structural features of the '<em>msteps Initial Value Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_VALUE_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationOperationTypeImpl <em>msteps Estimation Operation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.msteps_EstimationOperationTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getmsteps_EstimationOperationType()
   * @generated
   */
  int MSTEPS_ESTIMATION_OPERATION_TYPE = 61;

  /**
   * The feature id for the '<em><b>Op Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS = 1;

  /**
   * The number of structural features of the '<em>msteps Estimation Operation Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_OPERATION_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_TrialDesignTypeImpl <em>design Trial Design Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_TrialDesignTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_TrialDesignType()
   * @generated
   */
  int DESIGN_TRIAL_DESIGN_TYPE = 62;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TRIAL_DESIGN_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Treatment</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TRIAL_DESIGN_TYPE__TREATMENT = 1;

  /**
   * The feature id for the '<em><b>Treatment Epoch</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TRIAL_DESIGN_TYPE__TREATMENT_EPOCH = 2;

  /**
   * The feature id for the '<em><b>Group</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TRIAL_DESIGN_TYPE__GROUP = 3;

  /**
   * The number of structural features of the '<em>design Trial Design Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TRIAL_DESIGN_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_GroupTypeImpl <em>design Group Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_GroupTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_GroupType()
   * @generated
   */
  int DESIGN_GROUP_TYPE = 63;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__EXPR = 3;

  /**
   * The feature id for the '<em><b>Individuals</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__INDIVIDUALS = 4;

  /**
   * The number of structural features of the '<em>design Group Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_GroupTypeContentImpl <em>design Group Type Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_GroupTypeContentImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_GroupTypeContent()
   * @generated
   */
  int DESIGN_GROUP_TYPE_CONTENT = 64;

  /**
   * The feature id for the '<em><b>Treatment Epoch Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF = 0;

  /**
   * The feature id for the '<em><b>Washout</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE_CONTENT__WASHOUT = 1;

  /**
   * The number of structural features of the '<em>design Group Type Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE_CONTENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_IdividualsTypeImpl <em>design Idividuals Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_IdividualsTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_IdividualsType()
   * @generated
   */
  int DESIGN_IDIVIDUALS_TYPE = 65;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE__LEVEL_ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE__SYMB_ID = 3;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE__EXPR = 4;

  /**
   * The number of structural features of the '<em>design Idividuals Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_WashoutTypeImpl <em>design Washout Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_WashoutTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_WashoutType()
   * @generated
   */
  int DESIGN_WASHOUT_TYPE = 66;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_WASHOUT_TYPE__XMLNS = 0;

  /**
   * The number of structural features of the '<em>design Washout Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_WASHOUT_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_TreatmentEpohTypeImpl <em>design Treatment Epoh Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_TreatmentEpohTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_TreatmentEpohType()
   * @generated
   */
  int DESIGN_TREATMENT_EPOH_TYPE = 67;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Treatment Ref</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF = 3;

  /**
   * The feature id for the '<em><b>Start</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__START = 4;

  /**
   * The feature id for the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__END = 5;

  /**
   * The feature id for the '<em><b>Occasion</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__OCCASION = 6;

  /**
   * The number of structural features of the '<em>design Treatment Epoh Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_OccasionTypeImpl <em>design Occasion Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_OccasionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_OccasionType()
   * @generated
   */
  int DESIGN_OCCASION_TYPE = 68;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__LEVEL_ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__SYMB_ID = 3;

  /**
   * The feature id for the '<em><b>Begin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__BEGIN = 4;

  /**
   * The feature id for the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__END = 5;

  /**
   * The number of structural features of the '<em>design Occasion Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_BeginTypeImpl <em>design Begin Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_BeginTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_BeginType()
   * @generated
   */
  int DESIGN_BEGIN_TYPE = 69;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BEGIN_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BEGIN_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>design Begin Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BEGIN_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_EndTypeImpl <em>design End Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_EndTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_EndType()
   * @generated
   */
  int DESIGN_END_TYPE = 70;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_END_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_END_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>design End Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_END_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_StartTypeImpl <em>design Start Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_StartTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_StartType()
   * @generated
   */
  int DESIGN_START_TYPE = 71;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_START_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_START_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>design Start Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_START_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_TreatmentRefTypeImpl <em>design Treatment Ref Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_TreatmentRefTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_TreatmentRefType()
   * @generated
   */
  int DESIGN_TREATMENT_REF_TYPE = 72;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_REF_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_REF_TYPE__ID_REF = 1;

  /**
   * The number of structural features of the '<em>design Treatment Ref Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_REF_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_TreatmentEpochRefTypeImpl <em>design Treatment Epoch Ref Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_TreatmentEpochRefTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_TreatmentEpochRefType()
   * @generated
   */
  int DESIGN_TREATMENT_EPOCH_REF_TYPE = 73;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOCH_REF_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOCH_REF_TYPE__ID_REF = 1;

  /**
   * The number of structural features of the '<em>design Treatment Epoch Ref Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOCH_REF_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_TreatmentTypeImpl <em>design Treatment Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_TreatmentTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_TreatmentType()
   * @generated
   */
  int DESIGN_TREATMENT_TYPE = 74;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_TYPE__XMLNS = 2;

  /**
   * The feature id for the '<em><b>Dosing Regimen</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_TYPE__DOSING_REGIMEN = 3;

  /**
   * The number of structural features of the '<em>design Treatment Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DosingRegimenTypeImpl <em>design Dosing Regimen Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DosingRegimenTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DosingRegimenType()
   * @generated
   */
  int DESIGN_DOSING_REGIMEN_TYPE = 75;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_REGIMEN_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Bolus</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_REGIMEN_TYPE__BOLUS = 1;

  /**
   * The feature id for the '<em><b>Infusion</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_REGIMEN_TYPE__INFUSION = 2;

  /**
   * The number of structural features of the '<em>design Dosing Regimen Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_REGIMEN_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_InfusionTypeImpl <em>design Infusion Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_InfusionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_InfusionType()
   * @generated
   */
  int DESIGN_INFUSION_TYPE = 76;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_INFUSION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Dose Amount</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_INFUSION_TYPE__DOSE_AMOUNT = 1;

  /**
   * The feature id for the '<em><b>Steady State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_INFUSION_TYPE__STEADY_STATE = 2;

  /**
   * The feature id for the '<em><b>Dosing Times</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_INFUSION_TYPE__DOSING_TIMES = 3;

  /**
   * The feature id for the '<em><b>Duration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_INFUSION_TYPE__DURATION = 4;

  /**
   * The number of structural features of the '<em>design Infusion Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_INFUSION_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DurationTypeImpl <em>design Duration Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DurationTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DurationType()
   * @generated
   */
  int DESIGN_DURATION_TYPE = 77;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DURATION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DURATION_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>design Duration Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DURATION_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_BolusTypeImpl <em>design Bolus Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_BolusTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_BolusType()
   * @generated
   */
  int DESIGN_BOLUS_TYPE = 78;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BOLUS_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Dose Amount</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BOLUS_TYPE__DOSE_AMOUNT = 1;

  /**
   * The feature id for the '<em><b>Steady State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BOLUS_TYPE__STEADY_STATE = 2;

  /**
   * The feature id for the '<em><b>Dosing Times</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BOLUS_TYPE__DOSING_TIMES = 3;

  /**
   * The number of structural features of the '<em>design Bolus Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BOLUS_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DosingTimesTypeImpl <em>design Dosing Times Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DosingTimesTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DosingTimesType()
   * @generated
   */
  int DESIGN_DOSING_TIMES_TYPE = 79;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_TIMES_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_TIMES_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>design Dosing Times Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_TIMES_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DosingTimesTypeContentImpl <em>design Dosing Times Type Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DosingTimesTypeContentImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DosingTimesTypeContent()
   * @generated
   */
  int DESIGN_DOSING_TIMES_TYPE_CONTENT = 80;

  /**
   * The feature id for the '<em><b>Sequence</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_TIMES_TYPE_CONTENT__SEQUENCE = 0;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_TIMES_TYPE_CONTENT__SCALAR = 1;

  /**
   * The number of structural features of the '<em>design Dosing Times Type Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_TIMES_TYPE_CONTENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_SteadyStateTypeImpl <em>design Steady State Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_SteadyStateTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_SteadyStateType()
   * @generated
   */
  int DESIGN_STEADY_STATE_TYPE = 81;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_STEADY_STATE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Dosing Start</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_STEADY_STATE_TYPE__DOSING_START = 1;

  /**
   * The feature id for the '<em><b>Dosing Period</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD = 2;

  /**
   * The number of structural features of the '<em>design Steady State Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_STEADY_STATE_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DosingStartTypeImpl <em>design Dosing Start Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DosingStartTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DosingStartType()
   * @generated
   */
  int DESIGN_DOSING_START_TYPE = 82;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_START_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_START_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>design Dosing Start Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_START_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DosingPeriodTypeImpl <em>design Dosing Period Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DosingPeriodTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DosingPeriodType()
   * @generated
   */
  int DESIGN_DOSING_PERIOD_TYPE = 83;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_PERIOD_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_PERIOD_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>design Dosing Period Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_PERIOD_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DoseAmountTypeImpl <em>design Dose Amount Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DoseAmountTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DoseAmountType()
   * @generated
   */
  int DESIGN_DOSE_AMOUNT_TYPE = 84;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_AMOUNT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_AMOUNT_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>design Dose Amount Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_AMOUNT_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DosingVariableTypeImpl <em>design Dosing Variable Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DosingVariableTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DosingVariableType()
   * @generated
   */
  int DESIGN_DOSING_VARIABLE_TYPE = 85;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE__EXPR = 0;

  /**
   * The feature id for the '<em><b>Amount</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE__AMOUNT = 1;

  /**
   * The number of structural features of the '<em>design Dosing Variable Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DosingVariableTypeContentImpl <em>design Dosing Variable Type Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DosingVariableTypeContentImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DosingVariableTypeContent()
   * @generated
   */
  int DESIGN_DOSING_VARIABLE_TYPE_CONTENT = 86;

  /**
   * The feature id for the '<em><b>Dose Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR = 0;

  /**
   * The feature id for the '<em><b>Target Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR = 1;

  /**
   * The number of structural features of the '<em>design Dosing Variable Type Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE_CONTENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_AmountTypeImpl <em>design Amount Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_AmountTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_AmountType()
   * @generated
   */
  int DESIGN_AMOUNT_TYPE = 87;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_AMOUNT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_AMOUNT_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>design Amount Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_AMOUNT_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_TargetVarTypeImpl <em>design Target Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_TargetVarTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_TargetVarType()
   * @generated
   */
  int DESIGN_TARGET_VAR_TYPE = 88;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TARGET_VAR_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TARGET_VAR_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TARGET_VAR_TYPE__SYMB_ID = 2;

  /**
   * The number of structural features of the '<em>design Target Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TARGET_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.design_DoseVarTypeImpl <em>design Dose Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.design_DoseVarTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getdesign_DoseVarType()
   * @generated
   */
  int DESIGN_DOSE_VAR_TYPE = 89;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_VAR_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_VAR_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_VAR_TYPE__SYMB_ID = 2;

  /**
   * The number of structural features of the '<em>design Dose Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_DistributionTypeImpl <em>uncert Distribution Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_DistributionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_DistributionType()
   * @generated
   */
  int UNCERT_DISTRIBUTION_TYPE = 90;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Written Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE__EXPR = 2;

  /**
   * The number of structural features of the '<em>uncert Distribution Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_DistributionTypeContentImpl <em>uncert Distribution Type Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_DistributionTypeContentImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_DistributionTypeContent()
   * @generated
   */
  int UNCERT_DISTRIBUTION_TYPE_CONTENT = 91;

  /**
   * The feature id for the '<em><b>Normal</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL = 0;

  /**
   * The feature id for the '<em><b>PDF</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF = 1;

  /**
   * The feature id for the '<em><b>Poison</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON = 2;

  /**
   * The feature id for the '<em><b>Student</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT = 3;

  /**
   * The feature id for the '<em><b>Uniform</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM = 4;

  /**
   * The number of structural features of the '<em>uncert Distribution Type Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE_CONTENT_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_NormalImpl <em>uncert Normal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_NormalImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_Normal()
   * @generated
   */
  int UNCERT_NORMAL = 92;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_NORMAL__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Mean</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_NORMAL__MEAN = 1;

  /**
   * The feature id for the '<em><b>Std Dev</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_NORMAL__STD_DEV = 2;

  /**
   * The feature id for the '<em><b>Variance</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_NORMAL__VARIANCE = 3;

  /**
   * The feature id for the '<em><b>Coefficient Of Variance</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE = 4;

  /**
   * The number of structural features of the '<em>uncert Normal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_NORMAL_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_CoefficientOfVarianceImpl <em>uncert Coefficient Of Variance</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_CoefficientOfVarianceImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_CoefficientOfVariance()
   * @generated
   */
  int UNCERT_COEFFICIENT_OF_VARIANCE = 93;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_COEFFICIENT_OF_VARIANCE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_COEFFICIENT_OF_VARIANCE__EXPR = 1;

  /**
   * The number of structural features of the '<em>uncert Coefficient Of Variance</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_COEFFICIENT_OF_VARIANCE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_StdDevImpl <em>uncert Std Dev</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_StdDevImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_StdDev()
   * @generated
   */
  int UNCERT_STD_DEV = 94;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_STD_DEV__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_STD_DEV__EXPR = 1;

  /**
   * The number of structural features of the '<em>uncert Std Dev</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_STD_DEV_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_DistributionParamTypeImpl <em>uncert Distribution Param Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_DistributionParamTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_DistributionParamType()
   * @generated
   */
  int UNCERT_DISTRIBUTION_PARAM_TYPE = 95;

  /**
   * The feature id for the '<em><b>Equation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_PARAM_TYPE__EQUATION = 0;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_PARAM_TYPE__SCALAR = 1;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_PARAM_TYPE__VAR = 2;

  /**
   * The number of structural features of the '<em>uncert Distribution Param Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_PARAM_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_MeanParamTypeImpl <em>uncert Mean Param Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_MeanParamTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_MeanParamType()
   * @generated
   */
  int UNCERT_MEAN_PARAM_TYPE = 96;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_MEAN_PARAM_TYPE__EXPR = 0;

  /**
   * The number of structural features of the '<em>uncert Mean Param Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_MEAN_PARAM_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_MeanImpl <em>uncert Mean</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_MeanImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_Mean()
   * @generated
   */
  int UNCERT_MEAN = 97;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_MEAN__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_MEAN__EXPR = 1;

  /**
   * The number of structural features of the '<em>uncert Mean</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_MEAN_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_VarianceImpl <em>uncert Variance</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_VarianceImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_Variance()
   * @generated
   */
  int UNCERT_VARIANCE = 98;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_VARIANCE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_VARIANCE__EXPR = 1;

  /**
   * The number of structural features of the '<em>uncert Variance</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_VARIANCE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_PDFImpl <em>uncert PDF</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_PDFImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_PDF()
   * @generated
   */
  int UNCERT_PDF = 99;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PDF__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Function Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PDF__FUNCTION_PARAMETER = 1;

  /**
   * The feature id for the '<em><b>Equation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PDF__EQUATION = 2;

  /**
   * The number of structural features of the '<em>uncert PDF</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PDF_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_ParameterDefinitionTypeImpl <em>uncert Parameter Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_ParameterDefinitionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_ParameterDefinitionType()
   * @generated
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE = 100;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE__ID = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE__NAME = 2;

  /**
   * The number of structural features of the '<em>uncert Parameter Definition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_PoisonImpl <em>uncert Poison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_PoisonImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_Poison()
   * @generated
   */
  int UNCERT_POISON = 101;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_POISON__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Rate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_POISON__RATE = 1;

  /**
   * The number of structural features of the '<em>uncert Poison</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_POISON_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_RateImpl <em>uncert Rate</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_RateImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_Rate()
   * @generated
   */
  int UNCERT_RATE = 102;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_RATE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_RATE__EXPR = 1;

  /**
   * The number of structural features of the '<em>uncert Rate</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_RATE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_Student_tImpl <em>uncert Student t</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_Student_tImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_Student_t()
   * @generated
   */
  int UNCERT_STUDENT_T = 103;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_STUDENT_T__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Location</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_STUDENT_T__LOCATION = 1;

  /**
   * The feature id for the '<em><b>Scale</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_STUDENT_T__SCALE = 2;

  /**
   * The feature id for the '<em><b>Degrees Of Freedom</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_STUDENT_T__DEGREES_OF_FREEDOM = 3;

  /**
   * The number of structural features of the '<em>uncert Student t</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_STUDENT_T_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncertDegreesOfFreedomImpl <em>uncert Degrees Of Freedom</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncertDegreesOfFreedomImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncertDegreesOfFreedom()
   * @generated
   */
  int UNCERT_DEGREES_OF_FREEDOM = 104;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DEGREES_OF_FREEDOM__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DEGREES_OF_FREEDOM__EXPR = 1;

  /**
   * The number of structural features of the '<em>uncert Degrees Of Freedom</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DEGREES_OF_FREEDOM_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_ScaleImpl <em>uncert Scale</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_ScaleImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_Scale()
   * @generated
   */
  int UNCERT_SCALE = 105;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_SCALE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_SCALE__EXPR = 1;

  /**
   * The number of structural features of the '<em>uncert Scale</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_SCALE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_LocationImpl <em>uncert Location</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_LocationImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_Location()
   * @generated
   */
  int UNCERT_LOCATION = 106;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_LOCATION__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_LOCATION__EXPR = 1;

  /**
   * The number of structural features of the '<em>uncert Location</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_LOCATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.uncert_UniformImpl <em>uncert Uniform</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.uncert_UniformImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getuncert_Uniform()
   * @generated
   */
  int UNCERT_UNIFORM = 107;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_UNIFORM__XMLNS = 0;

  /**
   * The number of structural features of the '<em>uncert Uniform</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_UNIFORM_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeImpl <em>Math Equation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_EquationTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_EquationType()
   * @generated
   */
  int MATH_EQUATION_TYPE = 108;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Written Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE__WRITTEN_VERSION = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE__EXPR = 2;

  /**
   * The number of structural features of the '<em>Math Equation Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeContentImpl <em>Math Equation Type Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_EquationTypeContentImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_EquationTypeContent()
   * @generated
   */
  int MATH_EQUATION_TYPE_CONTENT = 109;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE_CONTENT__SCALAR = 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE_CONTENT__VAR = 1;

  /**
   * The feature id for the '<em><b>Binop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE_CONTENT__BINOP = 2;

  /**
   * The feature id for the '<em><b>Uniop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE_CONTENT__UNIOP = 3;

  /**
   * The feature id for the '<em><b>Piecewise</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE_CONTENT__PIECEWISE = 4;

  /**
   * The number of structural features of the '<em>Math Equation Type Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EQUATION_TYPE_CONTENT_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_ScalarTypeImpl <em>Math Scalar Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_ScalarTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_ScalarType()
   * @generated
   */
  int MATH_SCALAR_TYPE = 110;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_SCALAR_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_SCALAR_TYPE__VALUE = 1;

  /**
   * The number of structural features of the '<em>Math Scalar Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_SCALAR_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_VarTypeImpl <em>Math Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_VarTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_VarType()
   * @generated
   */
  int MATH_VAR_TYPE = 111;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_VAR_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_VAR_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_VAR_TYPE__SYMB_ID = 2;

  /**
   * The number of structural features of the '<em>Math Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_BinopTypeImpl <em>Math Binop Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_BinopTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_BinopType()
   * @generated
   */
  int MATH_BINOP_TYPE = 112;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_TYPE__OP = 1;

  /**
   * The feature id for the '<em><b>Left Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_TYPE__LEFT_EXPR = 2;

  /**
   * The feature id for the '<em><b>Right Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_TYPE__RIGHT_EXPR = 3;

  /**
   * The number of structural features of the '<em>Math Binop Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_UniopTypeImpl <em>Math Uniop Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_UniopTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_UniopType()
   * @generated
   */
  int MATH_UNIOP_TYPE = 113;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_TYPE__OP = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_TYPE__EXPR = 2;

  /**
   * The number of structural features of the '<em>Math Uniop Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_ExprTypeImpl <em>Math Expr Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_ExprTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_ExprType()
   * @generated
   */
  int MATH_EXPR_TYPE = 114;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EXPR_TYPE__SCALAR = 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EXPR_TYPE__VAR = 1;

  /**
   * The feature id for the '<em><b>Uniop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EXPR_TYPE__UNIOP = 2;

  /**
   * The feature id for the '<em><b>Binop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EXPR_TYPE__BINOP = 3;

  /**
   * The feature id for the '<em><b>Constant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EXPR_TYPE__CONSTANT = 4;

  /**
   * The feature id for the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EXPR_TYPE__FUNCTION_CALL = 5;

  /**
   * The number of structural features of the '<em>Math Expr Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_EXPR_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_ConstantTypeImpl <em>Math Constant Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_ConstantTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_ConstantType()
   * @generated
   */
  int MATH_CONSTANT_TYPE = 115;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_TYPE__OP = 1;

  /**
   * The number of structural features of the '<em>Math Constant Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_FunctionCallTypeImpl <em>Math Function Call Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_FunctionCallTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_FunctionCallType()
   * @generated
   */
  int MATH_FUNCTION_CALL_TYPE = 116;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_CALL_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Functionname</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME = 1;

  /**
   * The feature id for the '<em><b>Function Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS = 2;

  /**
   * The number of structural features of the '<em>Math Function Call Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_CALL_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_FunctionArgumentTypeImpl <em>Math Function Argument Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_FunctionArgumentTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_FunctionArgumentType()
   * @generated
   */
  int MATH_FUNCTION_ARGUMENT_TYPE = 117;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_ARGUMENT_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_ARGUMENT_TYPE__SYMB_ID = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_ARGUMENT_TYPE__EXPR = 2;

  /**
   * The number of structural features of the '<em>Math Function Argument Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_ARGUMENT_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_FuncExprTypeImpl <em>Math Func Expr Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_FuncExprTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_FuncExprType()
   * @generated
   */
  int MATH_FUNC_EXPR_TYPE = 118;

  /**
   * The feature id for the '<em><b>Equation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNC_EXPR_TYPE__EQUATION = 0;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNC_EXPR_TYPE__SCALAR = 1;

  /**
   * The feature id for the '<em><b>Constant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNC_EXPR_TYPE__CONSTANT = 2;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNC_EXPR_TYPE__VAR = 3;

  /**
   * The number of structural features of the '<em>Math Func Expr Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNC_EXPR_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_PiecewiseTypeImpl <em>Math Piecewise Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_PiecewiseTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_PiecewiseType()
   * @generated
   */
  int MATH_PIECEWISE_TYPE = 119;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_PIECEWISE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Piecewise</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_PIECEWISE_TYPE__PIECEWISE = 1;

  /**
   * The number of structural features of the '<em>Math Piecewise Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_PIECEWISE_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_PieceTypeImpl <em>Math Piece Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_PieceTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_PieceType()
   * @generated
   */
  int MATH_PIECE_TYPE = 120;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_PIECE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_PIECE_TYPE__CONTENT = 1;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_PIECE_TYPE__CONDITION = 2;

  /**
   * The number of structural features of the '<em>Math Piece Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_PIECE_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_ConditionTypeImpl <em>Math Condition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_ConditionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_ConditionType()
   * @generated
   */
  int MATH_CONDITION_TYPE = 121;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONDITION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Written Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONDITION_TYPE__WRITTEN_VERSION = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONDITION_TYPE__EXPR = 2;

  /**
   * The number of structural features of the '<em>Math Condition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONDITION_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_LogicExprTypeImpl <em>Math Logic Expr Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_LogicExprTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_LogicExprType()
   * @generated
   */
  int MATH_LOGIC_EXPR_TYPE = 124;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__SCALAR = 0;

  /**
   * The feature id for the '<em><b>Constant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__CONSTANT = 1;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__VAR = 2;

  /**
   * The feature id for the '<em><b>Uniop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__UNIOP = 3;

  /**
   * The feature id for the '<em><b>Binop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__BINOP = 4;

  /**
   * The feature id for the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL = 5;

  /**
   * The feature id for the '<em><b>String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__STRING = 6;

  /**
   * The number of structural features of the '<em>Math Logic Expr Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_LogicBaseTypeImpl <em>Math Logic Base Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_LogicBaseTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_LogicBaseType()
   * @generated
   */
  int MATH_LOGIC_BASE_TYPE = 122;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__SCALAR = MATH_LOGIC_EXPR_TYPE__SCALAR;

  /**
   * The feature id for the '<em><b>Constant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__CONSTANT = MATH_LOGIC_EXPR_TYPE__CONSTANT;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__VAR = MATH_LOGIC_EXPR_TYPE__VAR;

  /**
   * The feature id for the '<em><b>Uniop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__UNIOP = MATH_LOGIC_EXPR_TYPE__UNIOP;

  /**
   * The feature id for the '<em><b>Binop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__BINOP = MATH_LOGIC_EXPR_TYPE__BINOP;

  /**
   * The feature id for the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__FUNCTION_CALL = MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL;

  /**
   * The feature id for the '<em><b>String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__STRING = MATH_LOGIC_EXPR_TYPE__STRING;

  /**
   * The feature id for the '<em><b>Logic Binop Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE = MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Logic Uniop Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE = MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>True</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__TRUE = MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>False</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__FALSE = MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Otherwise</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__OTHERWISE = MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Math Logic Base Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE_FEATURE_COUNT = MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_OtherwiseImpl <em>Math Otherwise</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_OtherwiseImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_Otherwise()
   * @generated
   */
  int MATH_OTHERWISE = 123;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_OTHERWISE__XMLNS = 0;

  /**
   * The number of structural features of the '<em>Math Otherwise</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_OTHERWISE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_StringTypeImpl <em>Math String Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_StringTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_StringType()
   * @generated
   */
  int MATH_STRING_TYPE = 125;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_STRING_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_STRING_TYPE__VALUE = 1;

  /**
   * The number of structural features of the '<em>Math String Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_STRING_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_TRUEImpl <em>Math TRUE</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_TRUEImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_TRUE()
   * @generated
   */
  int MATH_TRUE = 126;

  /**
   * The feature id for the '<em><b>True</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_TRUE__TRUE = 0;

  /**
   * The number of structural features of the '<em>Math TRUE</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_TRUE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_FALSEImpl <em>Math FALSE</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_FALSEImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_FALSE()
   * @generated
   */
  int MATH_FALSE = 127;

  /**
   * The feature id for the '<em><b>False</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FALSE__FALSE = 0;

  /**
   * The number of structural features of the '<em>Math FALSE</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FALSE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopTypeImpl <em>Math Logic Binop Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_LogicBinopTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_LogicBinopType()
   * @generated
   */
  int MATH_LOGIC_BINOP_TYPE = 128;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_TYPE__OP = 1;

  /**
   * The feature id for the '<em><b>Left Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_TYPE__LEFT_EXPR = 2;

  /**
   * The feature id for the '<em><b>Right Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_TYPE__RIGHT_EXPR = 3;

  /**
   * The number of structural features of the '<em>Math Logic Binop Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_LogicUniopTypeImpl <em>Math Logic Uniop Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_LogicUniopTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_LogicUniopType()
   * @generated
   */
  int MATH_LOGIC_UNIOP_TYPE = 129;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_UNIOP_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_UNIOP_TYPE__OP = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_UNIOP_TYPE__EXPR = 2;

  /**
   * The number of structural features of the '<em>Math Logic Uniop Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_UNIOP_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_BinopNameImpl <em>Math Binop Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_BinopNameImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_BinopName()
   * @generated
   */
  int MATH_BINOP_NAME = 130;

  /**
   * The feature id for the '<em><b>Binopname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_NAME__BINOPNAME = 0;

  /**
   * The feature id for the '<em><b>Minus</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_NAME__MINUS = 1;

  /**
   * The feature id for the '<em><b>Times</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_NAME__TIMES = 2;

  /**
   * The feature id for the '<em><b>Divide</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_NAME__DIVIDE = 3;

  /**
   * The feature id for the '<em><b>Power</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_NAME__POWER = 4;

  /**
   * The feature id for the '<em><b>Log</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_NAME__LOG = 5;

  /**
   * The feature id for the '<em><b>Root</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_NAME__ROOT = 6;

  /**
   * The number of structural features of the '<em>Math Binop Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_NAME_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_UniopNameImpl <em>Math Uniop Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_UniopNameImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_UniopName()
   * @generated
   */
  int MATH_UNIOP_NAME = 131;

  /**
   * The feature id for the '<em><b>Exp</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__EXP = 0;

  /**
   * The feature id for the '<em><b>Ln</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__LN = 1;

  /**
   * The feature id for the '<em><b>Minus</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__MINUS = 2;

  /**
   * The feature id for the '<em><b>Factorial</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__FACTORIAL = 3;

  /**
   * The feature id for the '<em><b>Sin</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__SIN = 4;

  /**
   * The feature id for the '<em><b>Cos</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__COS = 5;

  /**
   * The feature id for the '<em><b>Tan</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__TAN = 6;

  /**
   * The feature id for the '<em><b>Sec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__SEC = 7;

  /**
   * The feature id for the '<em><b>Csc</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__CSC = 8;

  /**
   * The feature id for the '<em><b>Cot</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__COT = 9;

  /**
   * The feature id for the '<em><b>Sinh</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__SINH = 10;

  /**
   * The feature id for the '<em><b>Csch</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__CSCH = 11;

  /**
   * The feature id for the '<em><b>Coth</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__COTH = 12;

  /**
   * The feature id for the '<em><b>Arcsin</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCSIN = 13;

  /**
   * The feature id for the '<em><b>Arccos</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCCOS = 14;

  /**
   * The feature id for the '<em><b>Arctan</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCTAN = 15;

  /**
   * The feature id for the '<em><b>Arcsec</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCSEC = 16;

  /**
   * The feature id for the '<em><b>Arccsc</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCCSC = 17;

  /**
   * The feature id for the '<em><b>Arccot</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCCOT = 18;

  /**
   * The feature id for the '<em><b>Arcsinh</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCSINH = 19;

  /**
   * The feature id for the '<em><b>Arccosh</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCCOSH = 20;

  /**
   * The feature id for the '<em><b>Arctanh</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCTANH = 21;

  /**
   * The feature id for the '<em><b>Arcsech</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCSECH = 22;

  /**
   * The feature id for the '<em><b>Arccsch</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCCSCH = 23;

  /**
   * The feature id for the '<em><b>Arccoth</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ARCCOTH = 24;

  /**
   * The feature id for the '<em><b>Floor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__FLOOR = 25;

  /**
   * The feature id for the '<em><b>Abs</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__ABS = 26;

  /**
   * The feature id for the '<em><b>Ceiling</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__CEILING = 27;

  /**
   * The feature id for the '<em><b>Logit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME__LOGIT = 28;

  /**
   * The number of structural features of the '<em>Math Uniop Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_NAME_FEATURE_COUNT = 29;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_LogicUniopNameImpl <em>Math Logic Uniop Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_LogicUniopNameImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_LogicUniopName()
   * @generated
   */
  int MATH_LOGIC_UNIOP_NAME = 132;

  /**
   * The feature id for the '<em><b>Is Defined</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_UNIOP_NAME__IS_DEFINED = 0;

  /**
   * The feature id for the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_UNIOP_NAME__NOT = 1;

  /**
   * The number of structural features of the '<em>Math Logic Uniop Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_UNIOP_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl <em>Math Logic Binop Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_LogicBinopName()
   * @generated
   */
  int MATH_LOGIC_BINOP_NAME = 133;

  /**
   * The feature id for the '<em><b>Lt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME__LT = 0;

  /**
   * The feature id for the '<em><b>Leq</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME__LEQ = 1;

  /**
   * The feature id for the '<em><b>Gt</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME__GT = 2;

  /**
   * The feature id for the '<em><b>Geq</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME__GEQ = 3;

  /**
   * The feature id for the '<em><b>Eq</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME__EQ = 4;

  /**
   * The feature id for the '<em><b>Neq</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME__NEQ = 5;

  /**
   * The feature id for the '<em><b>And</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME__AND = 6;

  /**
   * The feature id for the '<em><b>Or</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME__OR = 7;

  /**
   * The feature id for the '<em><b>Xor</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME__XOR = 8;

  /**
   * The number of structural features of the '<em>Math Logic Binop Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_NAME_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_ConstantNameImpl <em>Math Constant Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_ConstantNameImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_ConstantName()
   * @generated
   */
  int MATH_CONSTANT_NAME = 134;

  /**
   * The feature id for the '<em><b>Notanumber</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_NAME__NOTANUMBER = 0;

  /**
   * The feature id for the '<em><b>Pi</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_NAME__PI = 1;

  /**
   * The feature id for the '<em><b>Exponentiale</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_NAME__EXPONENTIALE = 2;

  /**
   * The feature id for the '<em><b>Infinity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_NAME__INFINITY = 3;

  /**
   * The number of structural features of the '<em>Math Constant Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_NAME_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_symbIdImpl <em>Math symb Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_symbIdImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_symbId()
   * @generated
   */
  int MATH_SYMB_ID = 135;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_SYMB_ID__SYMB_ID = 0;

  /**
   * The number of structural features of the '<em>Math symb Id</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_SYMB_ID_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_idImpl <em>Math id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_idImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_id()
   * @generated
   */
  int MATH_ID = 136;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_ID__ID = 0;

  /**
   * The number of structural features of the '<em>Math id</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_ID_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_blockImpl <em>Math block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_blockImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_block()
   * @generated
   */
  int MATH_BLOCK = 137;

  /**
   * The feature id for the '<em><b>Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BLOCK__BLOCK = 0;

  /**
   * The number of structural features of the '<em>Math block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BLOCK_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Math_EstimationOperationTypeImpl <em>Math Estimation Operation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Math_EstimationOperationTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMath_EstimationOperationType()
   * @generated
   */
  int MATH_ESTIMATION_OPERATION_TYPE = 138;

  /**
   * The feature id for the '<em><b>Est Pop</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_ESTIMATION_OPERATION_TYPE__EST_POP = 0;

  /**
   * The feature id for the '<em><b>Est FIM</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_ESTIMATION_OPERATION_TYPE__EST_FIM = 1;

  /**
   * The feature id for the '<em><b>Est Indiv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_ESTIMATION_OPERATION_TYPE__EST_INDIV = 2;

  /**
   * The number of structural features of the '<em>Math Estimation Operation Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_ESTIMATION_OPERATION_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_ReplicatesTypeImpl <em>ct Replicates Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_ReplicatesTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_ReplicatesType()
   * @generated
   */
  int CT_REPLICATES_TYPE = 139;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_REPLICATES_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_REPLICATES_TYPE__SYMB_ID = 1;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_REPLICATES_TYPE__EXPR = 2;

  /**
   * The number of structural features of the '<em>ct Replicates Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_REPLICATES_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_AnnotationTypeImpl <em>ct Annotation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_AnnotationTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_AnnotationType()
   * @generated
   */
  int CT_ANNOTATION_TYPE = 140;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ANNOTATION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ANNOTATION_TYPE__DESCRIPTION = 1;

  /**
   * The number of structural features of the '<em>ct Annotation Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ANNOTATION_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_RhsImpl <em>ct Rhs</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_RhsImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_Rhs()
   * @generated
   */
  int CT_RHS = 141;

  /**
   * The feature id for the '<em><b>Equation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__EQUATION = 0;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__SCALAR = 1;

  /**
   * The feature id for the '<em><b>Constant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__CONSTANT = 2;

  /**
   * The feature id for the '<em><b>String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__STRING = 3;

  /**
   * The feature id for the '<em><b>Sequence</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__SEQUENCE = 4;

  /**
   * The feature id for the '<em><b>Vector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__VECTOR = 5;

  /**
   * The feature id for the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__FUNCTION_CALL = 6;

  /**
   * The feature id for the '<em><b>Data Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__DATA_SET = 7;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__VAR = 8;

  /**
   * The feature id for the '<em><b>Distribution</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS__DISTRIBUTION = 9;

  /**
   * The number of structural features of the '<em>ct Rhs</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RHS_FEATURE_COUNT = 10;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_DataSetTypeImpl <em>ct Data Set Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_DataSetTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_DataSetType()
   * @generated
   */
  int CT_DATA_SET_TYPE = 142;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DATA_SET_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DATA_SET_TYPE__DEFINITION = 1;

  /**
   * The feature id for the '<em><b>External Source</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DATA_SET_TYPE__EXTERNAL_SOURCE = 2;

  /**
   * The feature id for the '<em><b>Row</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DATA_SET_TYPE__ROW = 3;

  /**
   * The feature id for the '<em><b>Internal Source</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DATA_SET_TYPE__INTERNAL_SOURCE = 4;

  /**
   * The number of structural features of the '<em>ct Data Set Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DATA_SET_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_DefinitionTypeImpl <em>ct Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_DefinitionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_DefinitionType()
   * @generated
   */
  int CT_DEFINITION_TYPE = 143;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DEFINITION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Definition</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DEFINITION_TYPE__DEFINITION = 1;

  /**
   * The number of structural features of the '<em>ct Definition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DEFINITION_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_DefinitionRhsTypeImpl <em>ct Definition Rhs Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_DefinitionRhsTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_DefinitionRhsType()
   * @generated
   */
  int CT_DEFINITION_RHS_TYPE = 144;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DEFINITION_RHS_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DEFINITION_RHS_TYPE__DEFINITION = 1;

  /**
   * The number of structural features of the '<em>ct Definition Rhs Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DEFINITION_RHS_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_ColumnTypeImpl <em>ct Column Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_ColumnTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_ColumnType()
   * @generated
   */
  int CT_COLUMN_TYPE = 145;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_COLUMN_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Column Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_COLUMN_TYPE__COLUMN_NUM = 1;

  /**
   * The feature id for the '<em><b>Column Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_COLUMN_TYPE__COLUMN_VAR = 2;

  /**
   * The number of structural features of the '<em>ct Column Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_COLUMN_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_ExternalSourceTypeImpl <em>ct External Source Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_ExternalSourceTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_ExternalSourceType()
   * @generated
   */
  int CT_EXTERNAL_SOURCE_TYPE = 146;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__FORMAT = 1;

  /**
   * The feature id for the '<em><b>Header Line</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE = 2;

  /**
   * The feature id for the '<em><b>Ignore Line Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__IGNORE_LINE_SYMBOL = 3;

  /**
   * The feature id for the '<em><b>Url</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__URL = 4;

  /**
   * The number of structural features of the '<em>ct External Source Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_RowTypeImpl <em>ct Row Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_RowTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_RowType()
   * @generated
   */
  int CT_ROW_TYPE = 147;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ROW_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Row</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ROW_TYPE__ROW = 1;

  /**
   * The number of structural features of the '<em>ct Row Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ROW_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_RowTypeContentImpl <em>ct Row Type Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_RowTypeContentImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_RowTypeContent()
   * @generated
   */
  int CT_ROW_TYPE_CONTENT = 148;

  /**
   * The feature id for the '<em><b>Cell</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ROW_TYPE_CONTENT__CELL = 0;

  /**
   * The feature id for the '<em><b>Null</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ROW_TYPE_CONTENT__NULL = 1;

  /**
   * The number of structural features of the '<em>ct Row Type Content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ROW_TYPE_CONTENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_NullCellTypeImpl <em>ct Null Cell Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_NullCellTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_NullCellType()
   * @generated
   */
  int CT_NULL_CELL_TYPE = 149;

  /**
   * The feature id for the '<em><b>Null</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_NULL_CELL_TYPE__NULL = 0;

  /**
   * The number of structural features of the '<em>ct Null Cell Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_NULL_CELL_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_CellTypeImpl <em>ct Cell Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_CellTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_CellType()
   * @generated
   */
  int CT_CELL_TYPE = 150;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_CELL_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_CELL_TYPE__VALUE = 1;

  /**
   * The number of structural features of the '<em>ct Cell Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_CELL_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_InternalSourceTypeImpl <em>ct Internal Source Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_InternalSourceTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_InternalSourceType()
   * @generated
   */
  int CT_INTERNAL_SOURCE_TYPE = 151;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INTERNAL_SOURCE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INTERNAL_SOURCE_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INTERNAL_SOURCE_TYPE__SYMB_ID = 2;

  /**
   * The number of structural features of the '<em>ct Internal Source Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INTERNAL_SOURCE_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_VectorTypeImpl <em>ct Vector Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_VectorTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_VectorType()
   * @generated
   */
  int CT_VECTOR_TYPE = 152;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VECTOR_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VECTOR_TYPE__SCALAR = 1;

  /**
   * The feature id for the '<em><b>Sequence</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VECTOR_TYPE__SEQUENCE = 2;

  /**
   * The number of structural features of the '<em>ct Vector Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VECTOR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_SequenceTypeImpl <em>ct Sequence Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_SequenceTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_SequenceType()
   * @generated
   */
  int CT_SEQUENCE_TYPE = 153;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Begin</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__BEGIN = 1;

  /**
   * The feature id for the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__END = 2;

  /**
   * The feature id for the '<em><b>Stepwise</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__STEPWISE = 3;

  /**
   * The feature id for the '<em><b>Repetition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__REPETITION = 4;

  /**
   * The number of structural features of the '<em>ct Sequence Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_VariableDefinitionTypeImpl <em>ct Variable Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_VariableDefinitionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_VariableDefinitionType()
   * @generated
   */
  int CT_VARIABLE_DEFINITION_TYPE = 154;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Independent Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__SYMB_ID = 2;

  /**
   * The feature id for the '<em><b>Symbol Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE = 3;

  /**
   * The feature id for the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION = 4;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__EXPR = 5;

  /**
   * The number of structural features of the '<em>ct Variable Definition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_RestrictionTypeImpl <em>ct Restriction Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_RestrictionTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_RestrictionType()
   * @generated
   */
  int CT_RESTRICTION_TYPE = 155;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RESTRICTION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RESTRICTION_TYPE__EXPR = 1;

  /**
   * The number of structural features of the '<em>ct Restriction Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_RESTRICTION_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_ScalarRhsImpl <em>ct Scalar Rhs</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_ScalarRhsImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_ScalarRhs()
   * @generated
   */
  int CT_SCALAR_RHS = 156;

  /**
   * The feature id for the '<em><b>Equation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SCALAR_RHS__EQUATION = 0;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SCALAR_RHS__SCALAR = 1;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SCALAR_RHS__VAR = 2;

  /**
   * The feature id for the '<em><b>String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SCALAR_RHS__STRING = 3;

  /**
   * The feature id for the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SCALAR_RHS__FUNCTION_CALL = 4;

  /**
   * The number of structural features of the '<em>ct Scalar Rhs</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SCALAR_RHS_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_independentVarImpl <em>ct independent Var</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_independentVarImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_independentVar()
   * @generated
   */
  int CT_INDEPENDENT_VAR = 157;

  /**
   * The feature id for the '<em><b>Independent Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INDEPENDENT_VAR__INDEPENDENT_VAR = 0;

  /**
   * The number of structural features of the '<em>ct independent Var</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INDEPENDENT_VAR_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_levelIdImpl <em>ct level Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_levelIdImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_levelId()
   * @generated
   */
  int CT_LEVEL_ID = 158;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_LEVEL_ID__LEVEL_ID = 0;

  /**
   * The number of structural features of the '<em>ct level Id</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_LEVEL_ID_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_nameImpl <em>ct name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_nameImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_name()
   * @generated
   */
  int CT_NAME = 159;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_NAME__NAME = 0;

  /**
   * The number of structural features of the '<em>ct name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_NAME_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_symbIdImpl <em>ct symb Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_symbIdImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_symbId()
   * @generated
   */
  int CT_SYMB_ID = 160;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SYMB_ID__SYMB_ID = 0;

  /**
   * The number of structural features of the '<em>ct symb Id</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SYMB_ID_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_idImpl <em>ct id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_idImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_id()
   * @generated
   */
  int CT_ID = 161;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ID__ID = 0;

  /**
   * The number of structural features of the '<em>ct id</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ID_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_symbolTypeImpl <em>ct symbol Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_symbolTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_symbolType()
   * @generated
   */
  int CT_SYMBOL_TYPE = 162;

  /**
   * The feature id for the '<em><b>Symbol Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SYMBOL_TYPE__SYMBOL_TYPE = 0;

  /**
   * The number of structural features of the '<em>ct symbol Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SYMBOL_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_blockImpl <em>ct block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_blockImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_block()
   * @generated
   */
  int CT_BLOCK = 163;

  /**
   * The feature id for the '<em><b>Block</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_BLOCK__BLOCK = 0;

  /**
   * The number of structural features of the '<em>ct block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_BLOCK_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_writtenVersionImpl <em>ct written Version</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_writtenVersionImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_writtenVersion()
   * @generated
   */
  int CT_WRITTEN_VERSION = 164;

  /**
   * The feature id for the '<em><b>Written Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_WRITTEN_VERSION__WRITTEN_VERSION = 0;

  /**
   * The number of structural features of the '<em>ct written Version</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_WRITTEN_VERSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_Decimal_ValueImpl <em>ct Decimal Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_Decimal_ValueImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_Decimal_Value()
   * @generated
   */
  int CT_DECIMAL_VALUE = 165;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DECIMAL_VALUE__VALUE = 0;

  /**
   * The number of structural features of the '<em>ct Decimal Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_DECIMAL_VALUE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_String_ValueImpl <em>ct String Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_String_ValueImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_String_Value()
   * @generated
   */
  int CT_STRING_VALUE = 166;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_STRING_VALUE__VALUE = 0;

  /**
   * The number of structural features of the '<em>ct String Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_STRING_VALUE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_catIdImpl <em>ct cat Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_catIdImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_catId()
   * @generated
   */
  int CT_CAT_ID = 167;

  /**
   * The feature id for the '<em><b>Cat Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_CAT_ID__CAT_ID = 0;

  /**
   * The number of structural features of the '<em>ct cat Id</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_CAT_ID_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.ct_SymbolTypeTypeImpl <em>ct Symbol Type Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.ct_SymbolTypeTypeImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getct_SymbolTypeType()
   * @generated
   */
  int CT_SYMBOL_TYPE_TYPE = 168;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SYMBOL_TYPE_TYPE__SCALAR = 0;

  /**
   * The feature id for the '<em><b>Derivative</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SYMBOL_TYPE_TYPE__DERIVATIVE = 1;

  /**
   * The feature id for the '<em><b>Distrib</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SYMBOL_TYPE_TYPE__DISTRIB = 2;

  /**
   * The feature id for the '<em><b>Data Set</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SYMBOL_TYPE_TYPE__DATA_SET = 3;

  /**
   * The number of structural features of the '<em>ct Symbol Type Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SYMBOL_TYPE_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.XS_xmlnsImpl <em>XS xmlns</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.XS_xmlnsImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getXS_xmlns()
   * @generated
   */
  int XS_XMLNS = 169;

  /**
   * The feature id for the '<em><b>Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XS_XMLNS__LOCATION = 0;

  /**
   * The number of structural features of the '<em>XS xmlns</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XS_XMLNS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link eu.ddmore.pml.pharmML.impl.Mml_FuncParameterDefinitionTypImpl <em>Mml Func Parameter Definition Typ</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see eu.ddmore.pml.pharmML.impl.Mml_FuncParameterDefinitionTypImpl
   * @see eu.ddmore.pml.pharmML.impl.PharmMLPackageImpl#getMml_FuncParameterDefinitionTyp()
   * @generated
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYP = 170;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYP__XMLNS = MML_FUNC_PARAMETER_DEFINITION_TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Sumb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID = MML_FUNC_PARAMETER_DEFINITION_TYPE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Symbol Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE = MML_FUNC_PARAMETER_DEFINITION_TYPE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Decsription</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION = MML_FUNC_PARAMETER_DEFINITION_TYPE_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Mml Func Parameter Definition Typ</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYP_FEATURE_COUNT = MML_FUNC_PARAMETER_DEFINITION_TYPE_FEATURE_COUNT + 4;


  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Mml_PharmML <em>Mml Pharm ML</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Pharm ML</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmML
   * @generated
   */
  EClass getMml_PharmML();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmML#getXmlns()
   * @see #getMml_PharmML()
   * @generated
   */
  EReference getMml_PharmML_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmML#getName()
   * @see #getMml_PharmML()
   * @generated
   */
  EReference getMml_PharmML_Name();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getIndependentVar <em>Independent Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Independent Var</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmML#getIndependentVar()
   * @see #getMml_PharmML()
   * @generated
   */
  EReference getMml_PharmML_IndependentVar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Written Version</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmML#getWrittenVersion()
   * @see #getMml_PharmML()
   * @generated
   */
  EReference getMml_PharmML_WrittenVersion();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getSymbolDefinition <em>Symbol Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Symbol Definition</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmML#getSymbolDefinition()
   * @see #getMml_PharmML()
   * @generated
   */
  EReference getMml_PharmML_SymbolDefinition();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmML#getExpr()
   * @see #getMml_PharmML()
   * @generated
   */
  EReference getMml_PharmML_Expr();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getDesign <em>Design</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Design</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmML#getDesign()
   * @see #getMml_PharmML()
   * @generated
   */
  EReference getMml_PharmML_Design();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getModelingSteps <em>Modeling Steps</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modeling Steps</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmML#getModelingSteps()
   * @see #getMml_PharmML()
   * @generated
   */
  EReference getMml_PharmML_ModelingSteps();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Mml_PharmaMLContent <em>Mml Pharma ML Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Pharma ML Content</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmaMLContent
   * @generated
   */
  EClass getMml_PharmaMLContent();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_PharmaMLContent#getStructuralModel <em>Structural Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Structural Model</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmaMLContent#getStructuralModel()
   * @see #getMml_PharmaMLContent()
   * @generated
   */
  EReference getMml_PharmaMLContent_StructuralModel();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_PharmaMLContent#getModelDefinition <em>Model Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Model Definition</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_PharmaMLContent#getModelDefinition()
   * @see #getMml_PharmaMLContent()
   * @generated
   */
  EReference getMml_PharmaMLContent_ModelDefinition();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType <em>Mml Symbol Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Symbol Definition Type</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType
   * @generated
   */
  EClass getMml_SymbolDefinitionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getXmlns()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getName()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_Name();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getSymbId()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getSymbolType <em>Symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol Type</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getSymbolType()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_SymbolType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getDescription()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_Description();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getFunctionDefinition <em>Function Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Definition</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType#getFunctionDefinition()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_FunctionDefinition();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Mml_FunctionDefinitionType <em>Mml Function Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Function Definition Type</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FunctionDefinitionType
   * @generated
   */
  EClass getMml_FunctionDefinitionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Mml_FunctionDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FunctionDefinitionType#getXmlns()
   * @see #getMml_FunctionDefinitionType()
   * @generated
   */
  EReference getMml_FunctionDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Mml_FunctionDefinitionType#getFunctionArguments <em>Function Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Function Arguments</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FunctionDefinitionType#getFunctionArguments()
   * @see #getMml_FunctionDefinitionType()
   * @generated
   */
  EReference getMml_FunctionDefinitionType_FunctionArguments();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_FunctionDefinitionType#getDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Definition</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FunctionDefinitionType#getDefinition()
   * @see #getMml_FunctionDefinitionType()
   * @generated
   */
  EReference getMml_FunctionDefinitionType_Definition();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionType <em>Mml Func Parameter Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Func Parameter Definition Type</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionType
   * @generated
   */
  EClass getMml_FuncParameterDefinitionType();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType <em>mdef Model Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Model Definition Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ModelDefinitionType
   * @generated
   */
  EClass getmdef_ModelDefinitionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getXmlns()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getVariabilityLevel <em>Variability Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variability Level</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getVariabilityLevel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_VariabilityLevel();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getCovariateModel <em>Covariate Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Covariate Model</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getCovariateModel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_CovariateModel();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getParameterModel <em>Parameter Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameter Model</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getParameterModel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_ParameterModel();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getStructuraModel <em>Structura Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Structura Model</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getStructuraModel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_StructuraModel();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getObservationModel <em>Observation Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Observation Model</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getObservationModel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_ObservationModel();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName <em>mdef Parameter Type Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Parameter Type Name</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterTypeName
   * @generated
   */
  EClass getmdef_ParameterTypeName();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getLog <em>Log</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Log</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getLog()
   * @see #getmdef_ParameterTypeName()
   * @generated
   */
  EAttribute getmdef_ParameterTypeName_Log();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getLogit <em>Logit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Logit</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getLogit()
   * @see #getmdef_ParameterTypeName()
   * @generated
   */
  EAttribute getmdef_ParameterTypeName_Logit();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getNone <em>None</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>None</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getNone()
   * @see #getmdef_ParameterTypeName()
   * @generated
   */
  EAttribute getmdef_ParameterTypeName_None();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType <em>mdef Structural Model Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Structural Model Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_StructuralModelType
   * @generated
   */
  EClass getmdef_StructuralModelType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_StructuralModelType#getXmlns()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_StructuralModelType#getId()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_StructuralModelType#getDescription()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Description();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_StructuralModelType#getParameter()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Parameter();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_StructuralModelType#getVariable()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Variable();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getImport <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Import</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_StructuralModelType#getImport()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Import();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getInitiaCondition <em>Initia Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initia Condition</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_StructuralModelType#getInitiaCondition()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_InitiaCondition();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType <em>mdef Observation Model Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Observation Model Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ObservationModelType
   * @generated
   */
  EClass getmdef_ObservationModelType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ObservationModelType#getXmlns()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ObservationModelType#getId()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ObservationModelType#getName()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ObservationModelType#getParameter()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Parameter();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getContinuous <em>Continuous</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Continuous</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ObservationModelType#getContinuous()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Continuous();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType <em>mdef Continuous Obs Model Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Continuous Obs Model Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType
   * @generated
   */
  EClass getmdef_ContinuousObsModelType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getXmlns()
   * @see #getmdef_ContinuousObsModelType()
   * @generated
   */
  EReference getmdef_ContinuousObsModelType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getSymbId()
   * @see #getmdef_ContinuousObsModelType()
   * @generated
   */
  EReference getmdef_ContinuousObsModelType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getVar()
   * @see #getmdef_ContinuousObsModelType()
   * @generated
   */
  EReference getmdef_ContinuousObsModelType_Var();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getErrorModel <em>Error Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Error Model</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getErrorModel()
   * @see #getmdef_ContinuousObsModelType()
   * @generated
   */
  EReference getmdef_ContinuousObsModelType_ErrorModel();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getRandomEffect <em>Random Effect</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Random Effect</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType#getRandomEffect()
   * @see #getmdef_ContinuousObsModelType()
   * @generated
   */
  EReference getmdef_ContinuousObsModelType_RandomEffect();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ErrorModel <em>mdef Error Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Error Model</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ErrorModel
   * @generated
   */
  EClass getmdef_ErrorModel();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ErrorModel#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ErrorModel#getXmlns()
   * @see #getmdef_ErrorModel()
   * @generated
   */
  EReference getmdef_ErrorModel_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ErrorModel#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ErrorModel#getVar()
   * @see #getmdef_ErrorModel()
   * @generated
   */
  EReference getmdef_ErrorModel_Var();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ErrorModel#getFunctionArgument <em>Function Argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Function Argument</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ErrorModel#getFunctionArgument()
   * @see #getmdef_ErrorModel()
   * @generated
   */
  EReference getmdef_ErrorModel_FunctionArgument();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_RandomEffectType <em>mdef Random Effect Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Random Effect Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_RandomEffectType
   * @generated
   */
  EClass getmdef_RandomEffectType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_RandomEffectType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_RandomEffectType#getXmlns()
   * @see #getmdef_RandomEffectType()
   * @generated
   */
  EReference getmdef_RandomEffectType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_RandomEffectType#getDistribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Distribution</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_RandomEffectType#getDistribution()
   * @see #getmdef_RandomEffectType()
   * @generated
   */
  EReference getmdef_RandomEffectType_Distribution();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ParameterModelType <em>mdef Parameter Model Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Parameter Model Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterModelType
   * @generated
   */
  EClass getmdef_ParameterModelType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ParameterModelType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterModelType#getXmlns()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterModelType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterModelType#getId()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterModelType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterModelType#getName()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ParameterModelType#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterModelType#getParameter()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Parameter();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ParameterModelType#getCorrelation <em>Correlation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Correlation</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterModelType#getCorrelation()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Correlation();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_CorrelationType <em>mdef Correlation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Correlation Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationType
   * @generated
   */
  EClass getmdef_CorrelationType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CorrelationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationType#getXmlns()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CorrelationType#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Level Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationType#getLevelId()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_LevelId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CorrelationType#getParamVar1 <em>Param Var1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Param Var1</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationType#getParamVar1()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_ParamVar1();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CorrelationType#getParamVar2 <em>Param Var2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Param Var2</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationType#getParamVar2()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_ParamVar2();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CorrelationType#getCorrelationCoefficient <em>Correlation Coefficient</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Correlation Coefficient</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationType#getCorrelationCoefficient()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_CorrelationCoefficient();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CorrelationType#getCovariance <em>Covariance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Covariance</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationType#getCovariance()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_Covariance();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_Param1VarType <em>mdef Param1 Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Param1 Var Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_Param1VarType
   * @generated
   */
  EClass getmdef_Param1VarType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_Param1VarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_Param1VarType#getXmlns()
   * @see #getmdef_Param1VarType()
   * @generated
   */
  EReference getmdef_Param1VarType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_Param1VarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_Param1VarType#getBlock()
   * @see #getmdef_Param1VarType()
   * @generated
   */
  EReference getmdef_Param1VarType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_Param1VarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_Param1VarType#getSymbId()
   * @see #getmdef_Param1VarType()
   * @generated
   */
  EReference getmdef_Param1VarType_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_Param2VarType <em>mdef Param2 Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Param2 Var Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_Param2VarType
   * @generated
   */
  EClass getmdef_Param2VarType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_Param2VarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_Param2VarType#getXmlns()
   * @see #getmdef_Param2VarType()
   * @generated
   */
  EReference getmdef_Param2VarType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_Param2VarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_Param2VarType#getBlock()
   * @see #getmdef_Param2VarType()
   * @generated
   */
  EReference getmdef_Param2VarType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_Param2VarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_Param2VarType#getSymbId()
   * @see #getmdef_Param2VarType()
   * @generated
   */
  EReference getmdef_Param2VarType_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdefCovarianceType <em>mdef Covariance Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Covariance Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdefCovarianceType
   * @generated
   */
  EClass getmdefCovarianceType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdefCovarianceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdefCovarianceType#getXmlns()
   * @see #getmdefCovarianceType()
   * @generated
   */
  EReference getmdefCovarianceType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdefCovarianceType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.mdefCovarianceType#getExpr()
   * @see #getmdefCovarianceType()
   * @generated
   */
  EReference getmdefCovarianceType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_CorrelationCoefficientType <em>mdef Correlation Coefficient Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Correlation Coefficient Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationCoefficientType
   * @generated
   */
  EClass getmdef_CorrelationCoefficientType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CorrelationCoefficientType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationCoefficientType#getXmlns()
   * @see #getmdef_CorrelationCoefficientType()
   * @generated
   */
  EReference getmdef_CorrelationCoefficientType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CorrelationCoefficientType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CorrelationCoefficientType#getExpr()
   * @see #getmdef_CorrelationCoefficientType()
   * @generated
   */
  EReference getmdef_CorrelationCoefficientType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_CovariateModelType <em>mdef Covariate Model Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Covariate Model Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateModelType
   * @generated
   */
  EClass getmdef_CovariateModelType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CovariateModelType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateModelType#getXmlns()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CovariateModelType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateModelType#getId()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CovariateModelType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateModelType#getName()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CovariateModelType#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateModelType#getParameter()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Parameter();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CovariateModelType#getCovariate <em>Covariate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Covariate</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateModelType#getCovariate()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Covariate();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ParameterType <em>mdef Parameter Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Parameter Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType
   * @generated
   */
  EClass getmdef_ParameterType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ParameterType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType#getXmlns()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType#getName()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Name();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType#getSymbId()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterType#getTransformation <em>Transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Transformation</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType#getTransformation()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Transformation();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType#getVar()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Var();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType#getScalar()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterType#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType#getEquation()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Equation();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ParameterType#getRandomEffect <em>Random Effect</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Random Effect</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType#getRandomEffect()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_RandomEffect();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ParameterType#getCovariate <em>Covariate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Covariate</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterType#getCovariate()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Covariate();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType <em>mdef Covariate Variability Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Covariate Variability Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType
   * @generated
   */
  EClass getmdef_CovariateVariabilityType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getXmlns()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getName()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Name();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getSymbId()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getTransformation <em>Transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Transformation</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getTransformation()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Transformation();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getContinuous <em>Continuous</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Continuous</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getContinuous()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Continuous();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getCategorical <em>Categorical</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Categorical</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType#getCategorical()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Categorical();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_transformation <em>mdef transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef transformation</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_transformation
   * @generated
   */
  EClass getmdef_transformation();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_transformation#getTransformation <em>Transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Transformation</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_transformation#getTransformation()
   * @see #getmdef_transformation()
   * @generated
   */
  EReference getmdef_transformation_Transformation();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_CategoricalType <em>mdef Categorical Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Categorical Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CategoricalType
   * @generated
   */
  EClass getmdef_CategoricalType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CategoricalType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CategoricalType#getXmlns()
   * @see #getmdef_CategoricalType()
   * @generated
   */
  EReference getmdef_CategoricalType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CategoricalType#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Category</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CategoricalType#getCategory()
   * @see #getmdef_CategoricalType()
   * @generated
   */
  EReference getmdef_CategoricalType_Category();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType <em>mdef Continuous Covariate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Continuous Covariate Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType
   * @generated
   */
  EClass getmdef_ContinuousCovariateType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType#getXmlns()
   * @see #getmdef_ContinuousCovariateType()
   * @generated
   */
  EReference getmdef_ContinuousCovariateType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType#getDistribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Distribution</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType#getDistribution()
   * @see #getmdef_ContinuousCovariateType()
   * @generated
   */
  EReference getmdef_ContinuousCovariateType_Distribution();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType#getTransformation <em>Transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Transformation</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType#getTransformation()
   * @see #getmdef_ContinuousCovariateType()
   * @generated
   */
  EReference getmdef_ContinuousCovariateType_Transformation();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_TransformationType <em>mdef Transformation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Transformation Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_TransformationType
   * @generated
   */
  EClass getmdef_TransformationType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_TransformationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_TransformationType#getXmlns()
   * @see #getmdef_TransformationType()
   * @generated
   */
  EReference getmdef_TransformationType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_TransformationType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_TransformationType#getExpr()
   * @see #getmdef_TransformationType()
   * @generated
   */
  EReference getmdef_TransformationType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_VariabilityLevelDefnType <em>mdef Variability Level Defn Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Variability Level Defn Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_VariabilityLevelDefnType
   * @generated
   */
  EClass getmdef_VariabilityLevelDefnType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_VariabilityLevelDefnType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_VariabilityLevelDefnType#getXmlns()
   * @see #getmdef_VariabilityLevelDefnType()
   * @generated
   */
  EReference getmdef_VariabilityLevelDefnType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_VariabilityLevelDefnType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_VariabilityLevelDefnType#getId()
   * @see #getmdef_VariabilityLevelDefnType()
   * @generated
   */
  EReference getmdef_VariabilityLevelDefnType_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_VariabilityLevelDefnType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_VariabilityLevelDefnType#getName()
   * @see #getmdef_VariabilityLevelDefnType()
   * @generated
   */
  EReference getmdef_VariabilityLevelDefnType_Name();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ImportType <em>mdef Import Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Import Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportType
   * @generated
   */
  EClass getmdef_ImportType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ImportType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportType#getXmlns()
   * @see #getmdef_ImportType()
   * @generated
   */
  EReference getmdef_ImportType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.mdef_ImportType#getResource <em>Resource</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Resource</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportType#getResource()
   * @see #getmdef_ImportType()
   * @generated
   */
  EAttribute getmdef_ImportType_Resource();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ImportType#getResourceType <em>Resource Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Resource Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportType#getResourceType()
   * @see #getmdef_ImportType()
   * @generated
   */
  EReference getmdef_ImportType_ResourceType();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.mdef_ImportType#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportType#getSource()
   * @see #getmdef_ImportType()
   * @generated
   */
  EAttribute getmdef_ImportType_Source();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ImportType#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Link</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportType#getLink()
   * @see #getmdef_ImportType()
   * @generated
   */
  EReference getmdef_ImportType_Link();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ImportLinkType <em>mdef Import Link Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Import Link Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportLinkType
   * @generated
   */
  EClass getmdef_ImportLinkType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ImportLinkType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportLinkType#getXmlns()
   * @see #getmdef_ImportLinkType()
   * @generated
   */
  EReference getmdef_ImportLinkType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.mdef_ImportLinkType#getSymbolPtr <em>Symbol Ptr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Symbol Ptr</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportLinkType#getSymbolPtr()
   * @see #getmdef_ImportLinkType()
   * @generated
   */
  EAttribute getmdef_ImportLinkType_SymbolPtr();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ImportLinkType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportLinkType#getVar()
   * @see #getmdef_ImportLinkType()
   * @generated
   */
  EReference getmdef_ImportLinkType_Var();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType <em>mdef Import Resource Type Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Import Resource Type Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType
   * @generated
   */
  EClass getmdef_ImportResourceTypeType();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType#getSbml <em>Sbml</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sbml</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType#getSbml()
   * @see #getmdef_ImportResourceTypeType()
   * @generated
   */
  EAttribute getmdef_ImportResourceTypeType_Sbml();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType#getPharmml <em>Pharmml</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pharmml</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType#getPharmml()
   * @see #getmdef_ImportResourceTypeType()
   * @generated
   */
  EAttribute getmdef_ImportResourceTypeType_Pharmml();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_CovariateType <em>mdef Covariate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Covariate Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateType
   * @generated
   */
  EClass getmdef_CovariateType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CovariateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateType#getXmlns()
   * @see #getmdef_CovariateType()
   * @generated
   */
  EReference getmdef_CovariateType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CovariateType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateType#getVar()
   * @see #getmdef_CovariateType()
   * @generated
   */
  EReference getmdef_CovariateType_Var();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CovariateType#getFixedEffect <em>Fixed Effect</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Fixed Effect</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CovariateType#getFixedEffect()
   * @see #getmdef_CovariateType()
   * @generated
   */
  EReference getmdef_CovariateType_FixedEffect();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_FixedEffectType <em>mdef Fixed Effect Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Fixed Effect Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_FixedEffectType
   * @generated
   */
  EClass getmdef_FixedEffectType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_FixedEffectType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_FixedEffectType#getXmlns()
   * @see #getmdef_FixedEffectType()
   * @generated
   */
  EReference getmdef_FixedEffectType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_FixedEffectType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_FixedEffectType#getVar()
   * @see #getmdef_FixedEffectType()
   * @generated
   */
  EReference getmdef_FixedEffectType_Var();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_FixedEffectType#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Category</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_FixedEffectType#getCategory()
   * @see #getmdef_FixedEffectType()
   * @generated
   */
  EReference getmdef_FixedEffectType_Category();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_CategoryType <em>mdef Category Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Category Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CategoryType
   * @generated
   */
  EClass getmdef_CategoryType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_CategoryType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CategoryType#getXmlns()
   * @see #getmdef_CategoryType()
   * @generated
   */
  EReference getmdef_CategoryType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CategoryType#getCatId <em>Cat Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cat Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CategoryType#getCatId()
   * @see #getmdef_CategoryType()
   * @generated
   */
  EReference getmdef_CategoryType_CatId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CategoryType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CategoryType#getName()
   * @see #getmdef_CategoryType()
   * @generated
   */
  EReference getmdef_CategoryType_Name();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_CategoryType#getProbability <em>Probability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Probability</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_CategoryType#getProbability()
   * @see #getmdef_CategoryType()
   * @generated
   */
  EReference getmdef_CategoryType_Probability();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ProbabilityType <em>mdef Probability Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Probability Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ProbabilityType
   * @generated
   */
  EClass getmdef_ProbabilityType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ProbabilityType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ProbabilityType#getXmlns()
   * @see #getmdef_ProbabilityType()
   * @generated
   */
  EReference getmdef_ProbabilityType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ProbabilityType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ProbabilityType#getExpr()
   * @see #getmdef_ProbabilityType()
   * @generated
   */
  EReference getmdef_ProbabilityType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType <em>mdef Parameter Random Effect Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Parameter Random Effect Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType
   * @generated
   */
  EClass getmdef_ParameterRandomEffectType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getXmlns()
   * @see #getmdef_ParameterRandomEffectType()
   * @generated
   */
  EReference getmdef_ParameterRandomEffectType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Level Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getLevelId()
   * @see #getmdef_ParameterRandomEffectType()
   * @generated
   */
  EReference getmdef_ParameterRandomEffectType_LevelId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getName()
   * @see #getmdef_ParameterRandomEffectType()
   * @generated
   */
  EReference getmdef_ParameterRandomEffectType_Name();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getSymbId()
   * @see #getmdef_ParameterRandomEffectType()
   * @generated
   */
  EReference getmdef_ParameterRandomEffectType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getDistribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Distribution</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getDistribution()
   * @see #getmdef_ParameterRandomEffectType()
   * @generated
   */
  EReference getmdef_ParameterRandomEffectType_Distribution();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType <em>mdef Initial Condition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Initial Condition Type</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_InitialConditionType
   * @generated
   */
  EClass getmdef_InitialConditionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_InitialConditionType#getXmlns()
   * @see #getmdef_InitialConditionType()
   * @generated
   */
  EReference getmdef_InitialConditionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_InitialConditionType#getBlock()
   * @see #getmdef_InitialConditionType()
   * @generated
   */
  EReference getmdef_InitialConditionType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getSymbID <em>Symb ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb ID</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_InitialConditionType#getSymbID()
   * @see #getmdef_InitialConditionType()
   * @generated
   */
  EReference getmdef_InitialConditionType_SymbID();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.mdef_InitialConditionType#getExpr()
   * @see #getmdef_InitialConditionType()
   * @generated
   */
  EReference getmdef_InitialConditionType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps <em>msteps Modeling Steps</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Modeling Steps</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ModelingSteps
   * @generated
   */
  EClass getmsteps_ModelingSteps();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ModelingSteps#getXmlns()
   * @see #getmsteps_ModelingSteps()
   * @generated
   */
  EReference getmsteps_ModelingSteps_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ModelingSteps#getVariable()
   * @see #getmsteps_ModelingSteps()
   * @generated
   */
  EReference getmsteps_ModelingSteps_Variable();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ModelingSteps#getExpr()
   * @see #getmsteps_ModelingSteps()
   * @generated
   */
  EReference getmsteps_ModelingSteps_Expr();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps#getStepDependencies <em>Step Dependencies</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Step Dependencies</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ModelingSteps#getStepDependencies()
   * @see #getmsteps_ModelingSteps()
   * @generated
   */
  EReference getmsteps_ModelingSteps_StepDependencies();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_ModelingStepsContent <em>msteps Modeling Steps Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Modeling Steps Content</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ModelingStepsContent
   * @generated
   */
  EClass getmsteps_ModelingStepsContent();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ModelingStepsContent#getEstimationStep <em>Estimation Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Estimation Step</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ModelingStepsContent#getEstimationStep()
   * @see #getmsteps_ModelingStepsContent()
   * @generated
   */
  EReference getmsteps_ModelingStepsContent_EstimationStep();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ModelingStepsContent#getSimulationStep <em>Simulation Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Simulation Step</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ModelingStepsContent#getSimulationStep()
   * @see #getmsteps_ModelingStepsContent()
   * @generated
   */
  EReference getmsteps_ModelingStepsContent_SimulationStep();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_StepDependenciesType <em>msteps Step Dependencies Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Step Dependencies Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_StepDependenciesType
   * @generated
   */
  EClass getmsteps_StepDependenciesType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_StepDependenciesType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_StepDependenciesType#getXmlns()
   * @see #getmsteps_StepDependenciesType()
   * @generated
   */
  EReference getmsteps_StepDependenciesType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_StepDependenciesType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_StepDependenciesType#getDescription()
   * @see #getmsteps_StepDependenciesType()
   * @generated
   */
  EReference getmsteps_StepDependenciesType_Description();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_StepDependenciesType#getStep <em>Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Step</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_StepDependenciesType#getStep()
   * @see #getmsteps_StepDependenciesType()
   * @generated
   */
  EReference getmsteps_StepDependenciesType_Step();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_StepType <em>msteps Step Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Step Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_StepType
   * @generated
   */
  EClass getmsteps_StepType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_StepType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_StepType#getXmlns()
   * @see #getmsteps_StepType()
   * @generated
   */
  EReference getmsteps_StepType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.msteps_StepType#getIdRef <em>Id Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id Ref</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_StepType#getIdRef()
   * @see #getmsteps_StepType()
   * @generated
   */
  EAttribute getmsteps_StepType_IdRef();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_StepType#getDependantStep <em>Dependant Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Dependant Step</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_StepType#getDependantStep()
   * @see #getmsteps_StepType()
   * @generated
   */
  EReference getmsteps_StepType_DependantStep();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_DependantStepType <em>msteps Dependant Step Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Dependant Step Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_DependantStepType
   * @generated
   */
  EClass getmsteps_DependantStepType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_DependantStepType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_DependantStepType#getXmlns()
   * @see #getmsteps_DependantStepType()
   * @generated
   */
  EReference getmsteps_DependantStepType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.msteps_DependantStepType#getIdRef <em>Id Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id Ref</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_DependantStepType#getIdRef()
   * @see #getmsteps_DependantStepType()
   * @generated
   */
  EAttribute getmsteps_DependantStepType_IdRef();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep <em>msteps Estimation Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Estimation Step</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationStep
   * @generated
   */
  EClass getmsteps_EstimationStep();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationStep#getXmlns()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationStep#getId()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationStep#getDescription()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_Description();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getInitialValue <em>Initial Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initial Value</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationStep#getInitialValue()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_InitialValue();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getObjectiveDataSet <em>Objective Data Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Objective Data Set</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationStep#getObjectiveDataSet()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_ObjectiveDataSet();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getParametersToEstimate <em>Parameters To Estimate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters To Estimate</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationStep#getParametersToEstimate()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_ParametersToEstimate();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getEstimationOperation <em>Estimation Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Estimation Operation</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationStep#getEstimationOperation()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_EstimationOperation();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_ParametersToEstimateType <em>msteps Parameters To Estimate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Parameters To Estimate Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ParametersToEstimateType
   * @generated
   */
  EClass getmsteps_ParametersToEstimateType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ParametersToEstimateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ParametersToEstimateType#getXmlns()
   * @see #getmsteps_ParametersToEstimateType()
   * @generated
   */
  EReference getmsteps_ParametersToEstimateType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ParametersToEstimateType#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ParametersToEstimateType#getVariable()
   * @see #getmsteps_ParametersToEstimateType()
   * @generated
   */
  EReference getmsteps_ParametersToEstimateType_Variable();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mSteps_VariableEstimateType <em>mSteps Variable Estimate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps Variable Estimate Type</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_VariableEstimateType
   * @generated
   */
  EClass getmSteps_VariableEstimateType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getXmlns()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getBlock()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getFixed <em>Fixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fixed</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getFixed()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_Fixed();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getSymbID <em>Symb ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb ID</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getSymbID()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_SymbID();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getInitialEstimate <em>Initial Estimate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initial Estimate</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getInitialEstimate()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_InitialEstimate();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getLowerBound <em>Lower Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Bound</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getLowerBound()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_LowerBound();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getUpperBound <em>Upper Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Bound</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_VariableEstimateType#getUpperBound()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_UpperBound();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mSteps_fixed <em>mSteps fixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps fixed</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_fixed
   * @generated
   */
  EClass getmSteps_fixed();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_fixed#getFixed <em>Fixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fixed</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_fixed#getFixed()
   * @see #getmSteps_fixed()
   * @generated
   */
  EReference getmSteps_fixed_Fixed();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mSteps_UpperBoundType <em>mSteps Upper Bound Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps Upper Bound Type</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_UpperBoundType
   * @generated
   */
  EClass getmSteps_UpperBoundType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mSteps_UpperBoundType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_UpperBoundType#getXmlns()
   * @see #getmSteps_UpperBoundType()
   * @generated
   */
  EReference getmSteps_UpperBoundType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_UpperBoundType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_UpperBoundType#getExpr()
   * @see #getmSteps_UpperBoundType()
   * @generated
   */
  EReference getmSteps_UpperBoundType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mSteps_LowerBoundType <em>mSteps Lower Bound Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps Lower Bound Type</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_LowerBoundType
   * @generated
   */
  EClass getmSteps_LowerBoundType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mSteps_LowerBoundType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_LowerBoundType#getXmlns()
   * @see #getmSteps_LowerBoundType()
   * @generated
   */
  EReference getmSteps_LowerBoundType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_LowerBoundType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_LowerBoundType#getExpr()
   * @see #getmSteps_LowerBoundType()
   * @generated
   */
  EReference getmSteps_LowerBoundType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.mSteps_InitialEstimateType <em>mSteps Initial Estimate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps Initial Estimate Type</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_InitialEstimateType
   * @generated
   */
  EClass getmSteps_InitialEstimateType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.mSteps_InitialEstimateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_InitialEstimateType#getXmlns()
   * @see #getmSteps_InitialEstimateType()
   * @generated
   */
  EReference getmSteps_InitialEstimateType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.mSteps_InitialEstimateType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.mSteps_InitialEstimateType#getExpr()
   * @see #getmSteps_InitialEstimateType()
   * @generated
   */
  EReference getmSteps_InitialEstimateType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType <em>msteps Objective Data Set Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Objective Data Set Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType
   * @generated
   */
  EClass getmsteps_ObjectiveDataSetType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType#getXmlns()
   * @see #getmsteps_ObjectiveDataSetType()
   * @generated
   */
  EReference getmsteps_ObjectiveDataSetType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType#getDataSetRef <em>Data Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Set Ref</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType#getDataSetRef()
   * @see #getmsteps_ObjectiveDataSetType()
   * @generated
   */
  EAttribute getmsteps_ObjectiveDataSetType_DataSetRef();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Mapping</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType#getMapping()
   * @see #getmsteps_ObjectiveDataSetType()
   * @generated
   */
  EReference getmsteps_ObjectiveDataSetType_Mapping();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType <em>msteps Column Mapping Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Column Mapping Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingType
   * @generated
   */
  EClass getmsteps_ColumnMappingType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getXmlns()
   * @see #getmsteps_ColumnMappingType()
   * @generated
   */
  EReference getmsteps_ColumnMappingType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getColumnname <em>Columnname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Columnname</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getColumnname()
   * @see #getmsteps_ColumnMappingType()
   * @generated
   */
  EAttribute getmsteps_ColumnMappingType_Columnname();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getExpr()
   * @see #getmsteps_ColumnMappingType()
   * @generated
   */
  EReference getmsteps_ColumnMappingType_Expr();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getRestriction <em>Restriction</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Restriction</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getRestriction()
   * @see #getmsteps_ColumnMappingType()
   * @generated
   */
  EReference getmsteps_ColumnMappingType_Restriction();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent <em>msteps Column Mapping Type Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Column Mapping Type Content</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent
   * @generated
   */
  EClass getmsteps_ColumnMappingTypeContent();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getVar()
   * @see #getmsteps_ColumnMappingTypeContent()
   * @generated
   */
  EReference getmsteps_ColumnMappingTypeContent_Var();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getUseVariabilityLevel <em>Use Variability Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Use Variability Level</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getUseVariabilityLevel()
   * @see #getmsteps_ColumnMappingTypeContent()
   * @generated
   */
  EReference getmsteps_ColumnMappingTypeContent_UseVariabilityLevel();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getUseVariabilityNode <em>Use Variability Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Use Variability Node</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getUseVariabilityNode()
   * @see #getmsteps_ColumnMappingTypeContent()
   * @generated
   */
  EReference getmsteps_ColumnMappingTypeContent_UseVariabilityNode();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getDoseVar <em>Dose Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dose Var</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getDoseVar()
   * @see #getmsteps_ColumnMappingTypeContent()
   * @generated
   */
  EReference getmsteps_ColumnMappingTypeContent_DoseVar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getTargetVar <em>Target Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target Var</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getTargetVar()
   * @see #getmsteps_ColumnMappingTypeContent()
   * @generated
   */
  EReference getmsteps_ColumnMappingTypeContent_TargetVar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getAssign <em>Assign</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Assign</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getAssign()
   * @see #getmsteps_ColumnMappingTypeContent()
   * @generated
   */
  EReference getmsteps_ColumnMappingTypeContent_Assign();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getOutput <em>Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getOutput()
   * @see #getmsteps_ColumnMappingTypeContent()
   * @generated
   */
  EReference getmsteps_ColumnMappingTypeContent_Output();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_OutputType <em>msteps Output Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Output Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_OutputType
   * @generated
   */
  EClass getmsteps_OutputType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_OutputType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_OutputType#getXmlns()
   * @see #getmsteps_OutputType()
   * @generated
   */
  EReference getmsteps_OutputType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_OutputType#getVars <em>Vars</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Vars</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_OutputType#getVars()
   * @see #getmsteps_OutputType()
   * @generated
   */
  EReference getmsteps_OutputType_Vars();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_AssignType <em>msteps Assign Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Assign Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_AssignType
   * @generated
   */
  EClass getmsteps_AssignType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_AssignType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_AssignType#getXmlns()
   * @see #getmsteps_AssignType()
   * @generated
   */
  EReference getmsteps_AssignType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_AssignType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_AssignType#getBlock()
   * @see #getmsteps_AssignType()
   * @generated
   */
  EReference getmsteps_AssignType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_AssignType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_AssignType#getSymbId()
   * @see #getmsteps_AssignType()
   * @generated
   */
  EReference getmsteps_AssignType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_AssignType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_AssignType#getExpr()
   * @see #getmsteps_AssignType()
   * @generated
   */
  EReference getmsteps_AssignType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_TargetVarType <em>msteps Target Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Target Var Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_TargetVarType
   * @generated
   */
  EClass getmsteps_TargetVarType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_TargetVarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_TargetVarType#getXmlns()
   * @see #getmsteps_TargetVarType()
   * @generated
   */
  EReference getmsteps_TargetVarType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_TargetVarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_TargetVarType#getBlock()
   * @see #getmsteps_TargetVarType()
   * @generated
   */
  EReference getmsteps_TargetVarType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_TargetVarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_TargetVarType#getSymbId()
   * @see #getmsteps_TargetVarType()
   * @generated
   */
  EReference getmsteps_TargetVarType_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_UseVariabilityLevel <em>msteps Use Variability Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Use Variability Level</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_UseVariabilityLevel
   * @generated
   */
  EClass getmsteps_UseVariabilityLevel();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_UseVariabilityLevel#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_UseVariabilityLevel#getXmlns()
   * @see #getmsteps_UseVariabilityLevel()
   * @generated
   */
  EReference getmsteps_UseVariabilityLevel_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.msteps_UseVariabilityLevel#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Level Id</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_UseVariabilityLevel#getLevelId()
   * @see #getmsteps_UseVariabilityLevel()
   * @generated
   */
  EAttribute getmsteps_UseVariabilityLevel_LevelId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_UseVariabilityNode <em>msteps Use Variability Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Use Variability Node</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_UseVariabilityNode
   * @generated
   */
  EClass getmsteps_UseVariabilityNode();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_UseVariabilityNode#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_UseVariabilityNode#getXmlns()
   * @see #getmsteps_UseVariabilityNode()
   * @generated
   */
  EReference getmsteps_UseVariabilityNode_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_UseVariabilityNode#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_UseVariabilityNode#getBlock()
   * @see #getmsteps_UseVariabilityNode()
   * @generated
   */
  EReference getmsteps_UseVariabilityNode_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_UseVariabilityNode#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_UseVariabilityNode#getSymbId()
   * @see #getmsteps_UseVariabilityNode()
   * @generated
   */
  EReference getmsteps_UseVariabilityNode_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_SimulationStep <em>msteps Simulation Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Simulation Step</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimulationStep
   * @generated
   */
  EClass getmsteps_SimulationStep();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_SimulationStep#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimulationStep#getXmlns()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_SimulationStep#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimulationStep#getId()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_SimulationStep#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimulationStep#getDescription()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Description();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_SimulationStep#getReplicates <em>Replicates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Replicates</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimulationStep#getReplicates()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Replicates();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_SimulationStep#getInitialValue <em>Initial Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initial Value</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimulationStep#getInitialValue()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_InitialValue();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_SimulationStep#getSimDataSet <em>Sim Data Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sim Data Set</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimulationStep#getSimDataSet()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_SimDataSet();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_SimulationStep#getObservations <em>Observations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Observations</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimulationStep#getObservations()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Observations();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_SimDataSetType <em>msteps Sim Data Set Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Sim Data Set Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimDataSetType
   * @generated
   */
  EClass getmsteps_SimDataSetType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_SimDataSetType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimDataSetType#getXmlns()
   * @see #getmsteps_SimDataSetType()
   * @generated
   */
  EReference getmsteps_SimDataSetType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.msteps_SimDataSetType#getDataSetRef <em>Data Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Set Ref</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimDataSetType#getDataSetRef()
   * @see #getmsteps_SimDataSetType()
   * @generated
   */
  EAttribute getmsteps_SimDataSetType_DataSetRef();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_SimDataSetType#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Mapping</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_SimDataSetType#getMapping()
   * @see #getmsteps_SimDataSetType()
   * @generated
   */
  EReference getmsteps_SimDataSetType_Mapping();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_ObservationsType <em>msteps Observations Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Observations Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ObservationsType
   * @generated
   */
  EClass getmsteps_ObservationsType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ObservationsType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ObservationsType#getXmlns()
   * @see #getmsteps_ObservationsType()
   * @generated
   */
  EReference getmsteps_ObservationsType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_ObservationsType#getTimepoints <em>Timepoints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Timepoints</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ObservationsType#getTimepoints()
   * @see #getmsteps_ObservationsType()
   * @generated
   */
  EReference getmsteps_ObservationsType_Timepoints();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ObservationsType#getOutput <em>Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Output</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ObservationsType#getOutput()
   * @see #getmsteps_ObservationsType()
   * @generated
   */
  EReference getmsteps_ObservationsType_Output();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_ObservationsType#getWriteTo <em>Write To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Write To</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_ObservationsType#getWriteTo()
   * @see #getmsteps_ObservationsType()
   * @generated
   */
  EReference getmsteps_ObservationsType_WriteTo();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_WriteToType <em>msteps Write To Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Write To Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_WriteToType
   * @generated
   */
  EClass getmsteps_WriteToType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_WriteToType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_WriteToType#getXmlns()
   * @see #getmsteps_WriteToType()
   * @generated
   */
  EReference getmsteps_WriteToType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_WriteToType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_WriteToType#getVar()
   * @see #getmsteps_WriteToType()
   * @generated
   */
  EReference getmsteps_WriteToType_Var();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_WriteToType#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Mapping</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_WriteToType#getMapping()
   * @see #getmsteps_WriteToType()
   * @generated
   */
  EReference getmsteps_WriteToType_Mapping();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_TimepointsType <em>msteps Timepoints Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Timepoints Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_TimepointsType
   * @generated
   */
  EClass getmsteps_TimepointsType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_TimepointsType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_TimepointsType#getXmlns()
   * @see #getmsteps_TimepointsType()
   * @generated
   */
  EReference getmsteps_TimepointsType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_TimepointsType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_TimepointsType#getExpr()
   * @see #getmsteps_TimepointsType()
   * @generated
   */
  EReference getmsteps_TimepointsType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_InitialValueType <em>msteps Initial Value Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Initial Value Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_InitialValueType
   * @generated
   */
  EClass getmsteps_InitialValueType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_InitialValueType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_InitialValueType#getXmlns()
   * @see #getmsteps_InitialValueType()
   * @generated
   */
  EReference getmsteps_InitialValueType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_InitialValueType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_InitialValueType#getBlock()
   * @see #getmsteps_InitialValueType()
   * @generated
   */
  EReference getmsteps_InitialValueType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_InitialValueType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_InitialValueType#getSymbId()
   * @see #getmsteps_InitialValueType()
   * @generated
   */
  EReference getmsteps_InitialValueType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_InitialValueType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_InitialValueType#getExpr()
   * @see #getmsteps_InitialValueType()
   * @generated
   */
  EReference getmsteps_InitialValueType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.msteps_EstimationOperationType <em>msteps Estimation Operation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Estimation Operation Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationOperationType
   * @generated
   */
  EClass getmsteps_EstimationOperationType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.msteps_EstimationOperationType#getOpType <em>Op Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op Type</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationOperationType#getOpType()
   * @see #getmsteps_EstimationOperationType()
   * @generated
   */
  EReference getmsteps_EstimationOperationType_OpType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.msteps_EstimationOperationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.msteps_EstimationOperationType#getXmlns()
   * @see #getmsteps_EstimationOperationType()
   * @generated
   */
  EReference getmsteps_EstimationOperationType_Xmlns();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_TrialDesignType <em>design Trial Design Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Trial Design Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_TrialDesignType
   * @generated
   */
  EClass getdesign_TrialDesignType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TrialDesignType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_TrialDesignType#getXmlns()
   * @see #getdesign_TrialDesignType()
   * @generated
   */
  EReference getdesign_TrialDesignType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TrialDesignType#getTreatment <em>Treatment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Treatment</em>'.
   * @see eu.ddmore.pml.pharmML.design_TrialDesignType#getTreatment()
   * @see #getdesign_TrialDesignType()
   * @generated
   */
  EReference getdesign_TrialDesignType_Treatment();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TrialDesignType#getTreatmentEpoch <em>Treatment Epoch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Treatment Epoch</em>'.
   * @see eu.ddmore.pml.pharmML.design_TrialDesignType#getTreatmentEpoch()
   * @see #getdesign_TrialDesignType()
   * @generated
   */
  EReference getdesign_TrialDesignType_TreatmentEpoch();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TrialDesignType#getGroup <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Group</em>'.
   * @see eu.ddmore.pml.pharmML.design_TrialDesignType#getGroup()
   * @see #getdesign_TrialDesignType()
   * @generated
   */
  EReference getdesign_TrialDesignType_Group();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_GroupType <em>design Group Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Group Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_GroupType
   * @generated
   */
  EClass getdesign_GroupType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_GroupType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_GroupType#getXmlns()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_GroupType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.design_GroupType#getId()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_GroupType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.design_GroupType#getName()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_GroupType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_GroupType#getExpr()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Expr();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_GroupType#getIndividuals <em>Individuals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Individuals</em>'.
   * @see eu.ddmore.pml.pharmML.design_GroupType#getIndividuals()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Individuals();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_GroupTypeContent <em>design Group Type Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Group Type Content</em>'.
   * @see eu.ddmore.pml.pharmML.design_GroupTypeContent
   * @generated
   */
  EClass getdesign_GroupTypeContent();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_GroupTypeContent#getTreatmentEpochRef <em>Treatment Epoch Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Treatment Epoch Ref</em>'.
   * @see eu.ddmore.pml.pharmML.design_GroupTypeContent#getTreatmentEpochRef()
   * @see #getdesign_GroupTypeContent()
   * @generated
   */
  EReference getdesign_GroupTypeContent_TreatmentEpochRef();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_GroupTypeContent#getWashout <em>Washout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Washout</em>'.
   * @see eu.ddmore.pml.pharmML.design_GroupTypeContent#getWashout()
   * @see #getdesign_GroupTypeContent()
   * @generated
   */
  EReference getdesign_GroupTypeContent_Washout();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_IdividualsType <em>design Idividuals Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Idividuals Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_IdividualsType
   * @generated
   */
  EClass getdesign_IdividualsType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_IdividualsType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_IdividualsType#getXmlns()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_IdividualsType#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Level Id</em>'.
   * @see eu.ddmore.pml.pharmML.design_IdividualsType#getLevelId()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_LevelId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_IdividualsType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.design_IdividualsType#getName()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_Name();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_IdividualsType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.design_IdividualsType#getSymbId()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_IdividualsType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_IdividualsType#getExpr()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_WashoutType <em>design Washout Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Washout Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_WashoutType
   * @generated
   */
  EClass getdesign_WashoutType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_WashoutType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_WashoutType#getXmlns()
   * @see #getdesign_WashoutType()
   * @generated
   */
  EReference getdesign_WashoutType_Xmlns();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType <em>design Treatment Epoh Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Treatment Epoh Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpohType
   * @generated
   */
  EClass getdesign_TreatmentEpohType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpohType#getXmlns()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpohType#getId()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpohType#getName()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getTreatmentRef <em>Treatment Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Treatment Ref</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpohType#getTreatmentRef()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_TreatmentRef();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Start</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpohType#getStart()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_Start();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>End</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpohType#getEnd()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_End();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getOccasion <em>Occasion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Occasion</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpohType#getOccasion()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_Occasion();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_OccasionType <em>design Occasion Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Occasion Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_OccasionType
   * @generated
   */
  EClass getdesign_OccasionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_OccasionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_OccasionType#getXmlns()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_OccasionType#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Level Id</em>'.
   * @see eu.ddmore.pml.pharmML.design_OccasionType#getLevelId()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_LevelId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_OccasionType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.design_OccasionType#getName()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_Name();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_OccasionType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.design_OccasionType#getSymbId()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_OccasionType#getBegin <em>Begin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Begin</em>'.
   * @see eu.ddmore.pml.pharmML.design_OccasionType#getBegin()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_Begin();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_OccasionType#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>End</em>'.
   * @see eu.ddmore.pml.pharmML.design_OccasionType#getEnd()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_End();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_BeginType <em>design Begin Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Begin Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_BeginType
   * @generated
   */
  EClass getdesign_BeginType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_BeginType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_BeginType#getXmlns()
   * @see #getdesign_BeginType()
   * @generated
   */
  EReference getdesign_BeginType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_BeginType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_BeginType#getExpr()
   * @see #getdesign_BeginType()
   * @generated
   */
  EReference getdesign_BeginType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_EndType <em>design End Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design End Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_EndType
   * @generated
   */
  EClass getdesign_EndType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_EndType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_EndType#getXmlns()
   * @see #getdesign_EndType()
   * @generated
   */
  EReference getdesign_EndType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_EndType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_EndType#getExpr()
   * @see #getdesign_EndType()
   * @generated
   */
  EReference getdesign_EndType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_StartType <em>design Start Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Start Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_StartType
   * @generated
   */
  EClass getdesign_StartType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_StartType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_StartType#getXmlns()
   * @see #getdesign_StartType()
   * @generated
   */
  EReference getdesign_StartType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_StartType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_StartType#getExpr()
   * @see #getdesign_StartType()
   * @generated
   */
  EReference getdesign_StartType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_TreatmentRefType <em>design Treatment Ref Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Treatment Ref Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentRefType
   * @generated
   */
  EClass getdesign_TreatmentRefType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TreatmentRefType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentRefType#getXmlns()
   * @see #getdesign_TreatmentRefType()
   * @generated
   */
  EReference getdesign_TreatmentRefType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.design_TreatmentRefType#getIdRef <em>Id Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id Ref</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentRefType#getIdRef()
   * @see #getdesign_TreatmentRefType()
   * @generated
   */
  EAttribute getdesign_TreatmentRefType_IdRef();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_TreatmentEpochRefType <em>design Treatment Epoch Ref Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Treatment Epoch Ref Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpochRefType
   * @generated
   */
  EClass getdesign_TreatmentEpochRefType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TreatmentEpochRefType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpochRefType#getXmlns()
   * @see #getdesign_TreatmentEpochRefType()
   * @generated
   */
  EReference getdesign_TreatmentEpochRefType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.design_TreatmentEpochRefType#getIdRef <em>Id Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id Ref</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentEpochRefType#getIdRef()
   * @see #getdesign_TreatmentEpochRefType()
   * @generated
   */
  EAttribute getdesign_TreatmentEpochRefType_IdRef();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_TreatmentType <em>design Treatment Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Treatment Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentType
   * @generated
   */
  EClass getdesign_TreatmentType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_TreatmentType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentType#getId()
   * @see #getdesign_TreatmentType()
   * @generated
   */
  EReference getdesign_TreatmentType_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_TreatmentType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentType#getName()
   * @see #getdesign_TreatmentType()
   * @generated
   */
  EReference getdesign_TreatmentType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TreatmentType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentType#getXmlns()
   * @see #getdesign_TreatmentType()
   * @generated
   */
  EReference getdesign_TreatmentType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TreatmentType#getDosingRegimen <em>Dosing Regimen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Dosing Regimen</em>'.
   * @see eu.ddmore.pml.pharmML.design_TreatmentType#getDosingRegimen()
   * @see #getdesign_TreatmentType()
   * @generated
   */
  EReference getdesign_TreatmentType_DosingRegimen();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DosingRegimenType <em>design Dosing Regimen Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Regimen Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingRegimenType
   * @generated
   */
  EClass getdesign_DosingRegimenType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_DosingRegimenType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingRegimenType#getXmlns()
   * @see #getdesign_DosingRegimenType()
   * @generated
   */
  EReference getdesign_DosingRegimenType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingRegimenType#getBolus <em>Bolus</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bolus</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingRegimenType#getBolus()
   * @see #getdesign_DosingRegimenType()
   * @generated
   */
  EReference getdesign_DosingRegimenType_Bolus();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingRegimenType#getInfusion <em>Infusion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Infusion</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingRegimenType#getInfusion()
   * @see #getdesign_DosingRegimenType()
   * @generated
   */
  EReference getdesign_DosingRegimenType_Infusion();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_InfusionType <em>design Infusion Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Infusion Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_InfusionType
   * @generated
   */
  EClass getdesign_InfusionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_InfusionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_InfusionType#getXmlns()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_InfusionType#getDoseAmount <em>Dose Amount</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dose Amount</em>'.
   * @see eu.ddmore.pml.pharmML.design_InfusionType#getDoseAmount()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_DoseAmount();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_InfusionType#getSteadyState <em>Steady State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Steady State</em>'.
   * @see eu.ddmore.pml.pharmML.design_InfusionType#getSteadyState()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_SteadyState();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_InfusionType#getDosingTimes <em>Dosing Times</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dosing Times</em>'.
   * @see eu.ddmore.pml.pharmML.design_InfusionType#getDosingTimes()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_DosingTimes();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_InfusionType#getDuration <em>Duration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Duration</em>'.
   * @see eu.ddmore.pml.pharmML.design_InfusionType#getDuration()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_Duration();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DurationType <em>design Duration Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Duration Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_DurationType
   * @generated
   */
  EClass getdesign_DurationType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_DurationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_DurationType#getXmlns()
   * @see #getdesign_DurationType()
   * @generated
   */
  EReference getdesign_DurationType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DurationType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_DurationType#getExpr()
   * @see #getdesign_DurationType()
   * @generated
   */
  EReference getdesign_DurationType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_BolusType <em>design Bolus Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Bolus Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_BolusType
   * @generated
   */
  EClass getdesign_BolusType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_BolusType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_BolusType#getXmlns()
   * @see #getdesign_BolusType()
   * @generated
   */
  EReference getdesign_BolusType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_BolusType#getDoseAmount <em>Dose Amount</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dose Amount</em>'.
   * @see eu.ddmore.pml.pharmML.design_BolusType#getDoseAmount()
   * @see #getdesign_BolusType()
   * @generated
   */
  EReference getdesign_BolusType_DoseAmount();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_BolusType#getSteadyState <em>Steady State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Steady State</em>'.
   * @see eu.ddmore.pml.pharmML.design_BolusType#getSteadyState()
   * @see #getdesign_BolusType()
   * @generated
   */
  EReference getdesign_BolusType_SteadyState();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_BolusType#getDosingTimes <em>Dosing Times</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dosing Times</em>'.
   * @see eu.ddmore.pml.pharmML.design_BolusType#getDosingTimes()
   * @see #getdesign_BolusType()
   * @generated
   */
  EReference getdesign_BolusType_DosingTimes();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DosingTimesType <em>design Dosing Times Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Times Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingTimesType
   * @generated
   */
  EClass getdesign_DosingTimesType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_DosingTimesType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingTimesType#getXmlns()
   * @see #getdesign_DosingTimesType()
   * @generated
   */
  EReference getdesign_DosingTimesType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_DosingTimesType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingTimesType#getExpr()
   * @see #getdesign_DosingTimesType()
   * @generated
   */
  EReference getdesign_DosingTimesType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DosingTimesTypeContent <em>design Dosing Times Type Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Times Type Content</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingTimesTypeContent
   * @generated
   */
  EClass getdesign_DosingTimesTypeContent();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingTimesTypeContent#getSequence <em>Sequence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sequence</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingTimesTypeContent#getSequence()
   * @see #getdesign_DosingTimesTypeContent()
   * @generated
   */
  EReference getdesign_DosingTimesTypeContent_Sequence();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingTimesTypeContent#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingTimesTypeContent#getScalar()
   * @see #getdesign_DosingTimesTypeContent()
   * @generated
   */
  EReference getdesign_DosingTimesTypeContent_Scalar();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_SteadyStateType <em>design Steady State Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Steady State Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_SteadyStateType
   * @generated
   */
  EClass getdesign_SteadyStateType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_SteadyStateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_SteadyStateType#getXmlns()
   * @see #getdesign_SteadyStateType()
   * @generated
   */
  EReference getdesign_SteadyStateType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_SteadyStateType#getDosingStart <em>Dosing Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dosing Start</em>'.
   * @see eu.ddmore.pml.pharmML.design_SteadyStateType#getDosingStart()
   * @see #getdesign_SteadyStateType()
   * @generated
   */
  EReference getdesign_SteadyStateType_DosingStart();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_SteadyStateType#getDosingPeriod <em>Dosing Period</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dosing Period</em>'.
   * @see eu.ddmore.pml.pharmML.design_SteadyStateType#getDosingPeriod()
   * @see #getdesign_SteadyStateType()
   * @generated
   */
  EReference getdesign_SteadyStateType_DosingPeriod();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DosingStartType <em>design Dosing Start Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Start Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingStartType
   * @generated
   */
  EClass getdesign_DosingStartType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_DosingStartType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingStartType#getXmlns()
   * @see #getdesign_DosingStartType()
   * @generated
   */
  EReference getdesign_DosingStartType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingStartType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingStartType#getExpr()
   * @see #getdesign_DosingStartType()
   * @generated
   */
  EReference getdesign_DosingStartType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DosingPeriodType <em>design Dosing Period Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Period Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingPeriodType
   * @generated
   */
  EClass getdesign_DosingPeriodType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_DosingPeriodType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingPeriodType#getXmlns()
   * @see #getdesign_DosingPeriodType()
   * @generated
   */
  EReference getdesign_DosingPeriodType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingPeriodType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingPeriodType#getExpr()
   * @see #getdesign_DosingPeriodType()
   * @generated
   */
  EReference getdesign_DosingPeriodType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DoseAmountType <em>design Dose Amount Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dose Amount Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_DoseAmountType
   * @generated
   */
  EClass getdesign_DoseAmountType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_DoseAmountType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_DoseAmountType#getXmlns()
   * @see #getdesign_DoseAmountType()
   * @generated
   */
  EReference getdesign_DoseAmountType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DoseAmountType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_DoseAmountType#getExpr()
   * @see #getdesign_DoseAmountType()
   * @generated
   */
  EReference getdesign_DoseAmountType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DosingVariableType <em>design Dosing Variable Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Variable Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingVariableType
   * @generated
   */
  EClass getdesign_DosingVariableType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingVariableType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingVariableType#getExpr()
   * @see #getdesign_DosingVariableType()
   * @generated
   */
  EReference getdesign_DosingVariableType_Expr();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingVariableType#getAmount <em>Amount</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Amount</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingVariableType#getAmount()
   * @see #getdesign_DosingVariableType()
   * @generated
   */
  EReference getdesign_DosingVariableType_Amount();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DosingVariableTypeContent <em>design Dosing Variable Type Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Variable Type Content</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingVariableTypeContent
   * @generated
   */
  EClass getdesign_DosingVariableTypeContent();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingVariableTypeContent#getDoseVar <em>Dose Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dose Var</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingVariableTypeContent#getDoseVar()
   * @see #getdesign_DosingVariableTypeContent()
   * @generated
   */
  EReference getdesign_DosingVariableTypeContent_DoseVar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DosingVariableTypeContent#getTargetVar <em>Target Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target Var</em>'.
   * @see eu.ddmore.pml.pharmML.design_DosingVariableTypeContent#getTargetVar()
   * @see #getdesign_DosingVariableTypeContent()
   * @generated
   */
  EReference getdesign_DosingVariableTypeContent_TargetVar();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_AmountType <em>design Amount Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Amount Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_AmountType
   * @generated
   */
  EClass getdesign_AmountType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_AmountType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_AmountType#getXmlns()
   * @see #getdesign_AmountType()
   * @generated
   */
  EReference getdesign_AmountType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_AmountType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.design_AmountType#getExpr()
   * @see #getdesign_AmountType()
   * @generated
   */
  EReference getdesign_AmountType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_TargetVarType <em>design Target Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Target Var Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_TargetVarType
   * @generated
   */
  EClass getdesign_TargetVarType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_TargetVarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_TargetVarType#getXmlns()
   * @see #getdesign_TargetVarType()
   * @generated
   */
  EReference getdesign_TargetVarType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_TargetVarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.design_TargetVarType#getBlock()
   * @see #getdesign_TargetVarType()
   * @generated
   */
  EReference getdesign_TargetVarType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_TargetVarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.design_TargetVarType#getSymbId()
   * @see #getdesign_TargetVarType()
   * @generated
   */
  EReference getdesign_TargetVarType_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.design_DoseVarType <em>design Dose Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dose Var Type</em>'.
   * @see eu.ddmore.pml.pharmML.design_DoseVarType
   * @generated
   */
  EClass getdesign_DoseVarType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.design_DoseVarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.design_DoseVarType#getXmlns()
   * @see #getdesign_DoseVarType()
   * @generated
   */
  EReference getdesign_DoseVarType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DoseVarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.design_DoseVarType#getBlock()
   * @see #getdesign_DoseVarType()
   * @generated
   */
  EReference getdesign_DoseVarType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.design_DoseVarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.design_DoseVarType#getSymbId()
   * @see #getdesign_DoseVarType()
   * @generated
   */
  EReference getdesign_DoseVarType_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_DistributionType <em>uncert Distribution Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Distribution Type</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionType
   * @generated
   */
  EClass getuncert_DistributionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_DistributionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionType#getXmlns()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionType#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Written Version</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionType#getWrittenVersion()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_WrittenVersion();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionType#getExpr()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent <em>uncert Distribution Type Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Distribution Type Content</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionTypeContent
   * @generated
   */
  EClass getuncert_DistributionTypeContent();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getNormal <em>Normal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Normal</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getNormal()
   * @see #getuncert_DistributionTypeContent()
   * @generated
   */
  EReference getuncert_DistributionTypeContent_Normal();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getPDF <em>PDF</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>PDF</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getPDF()
   * @see #getuncert_DistributionTypeContent()
   * @generated
   */
  EReference getuncert_DistributionTypeContent_PDF();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getPoison <em>Poison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Poison</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getPoison()
   * @see #getuncert_DistributionTypeContent()
   * @generated
   */
  EReference getuncert_DistributionTypeContent_Poison();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getStudent <em>Student</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Student</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getStudent()
   * @see #getuncert_DistributionTypeContent()
   * @generated
   */
  EReference getuncert_DistributionTypeContent_Student();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getUniform <em>Uniform</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Uniform</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getUniform()
   * @see #getuncert_DistributionTypeContent()
   * @generated
   */
  EReference getuncert_DistributionTypeContent_Uniform();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_Normal <em>uncert Normal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Normal</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Normal
   * @generated
   */
  EClass getuncert_Normal();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_Normal#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Normal#getXmlns()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Normal#getMean <em>Mean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mean</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Normal#getMean()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_Mean();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Normal#getStdDev <em>Std Dev</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Std Dev</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Normal#getStdDev()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_StdDev();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Normal#getVariance <em>Variance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variance</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Normal#getVariance()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_Variance();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Normal#getCoefficientOfVariance <em>Coefficient Of Variance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Coefficient Of Variance</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Normal#getCoefficientOfVariance()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_CoefficientOfVariance();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_CoefficientOfVariance <em>uncert Coefficient Of Variance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Coefficient Of Variance</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_CoefficientOfVariance
   * @generated
   */
  EClass getuncert_CoefficientOfVariance();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_CoefficientOfVariance#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_CoefficientOfVariance#getXmlns()
   * @see #getuncert_CoefficientOfVariance()
   * @generated
   */
  EReference getuncert_CoefficientOfVariance_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_CoefficientOfVariance#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_CoefficientOfVariance#getExpr()
   * @see #getuncert_CoefficientOfVariance()
   * @generated
   */
  EReference getuncert_CoefficientOfVariance_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_StdDev <em>uncert Std Dev</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Std Dev</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_StdDev
   * @generated
   */
  EClass getuncert_StdDev();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_StdDev#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_StdDev#getXmlns()
   * @see #getuncert_StdDev()
   * @generated
   */
  EReference getuncert_StdDev_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_StdDev#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_StdDev#getExpr()
   * @see #getuncert_StdDev()
   * @generated
   */
  EReference getuncert_StdDev_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_DistributionParamType <em>uncert Distribution Param Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Distribution Param Type</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionParamType
   * @generated
   */
  EClass getuncert_DistributionParamType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionParamType#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionParamType#getEquation()
   * @see #getuncert_DistributionParamType()
   * @generated
   */
  EReference getuncert_DistributionParamType_Equation();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionParamType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionParamType#getScalar()
   * @see #getuncert_DistributionParamType()
   * @generated
   */
  EReference getuncert_DistributionParamType_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_DistributionParamType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_DistributionParamType#getVar()
   * @see #getuncert_DistributionParamType()
   * @generated
   */
  EReference getuncert_DistributionParamType_Var();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_MeanParamType <em>uncert Mean Param Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Mean Param Type</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_MeanParamType
   * @generated
   */
  EClass getuncert_MeanParamType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_MeanParamType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_MeanParamType#getExpr()
   * @see #getuncert_MeanParamType()
   * @generated
   */
  EReference getuncert_MeanParamType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_Mean <em>uncert Mean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Mean</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Mean
   * @generated
   */
  EClass getuncert_Mean();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_Mean#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Mean#getXmlns()
   * @see #getuncert_Mean()
   * @generated
   */
  EReference getuncert_Mean_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Mean#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Mean#getExpr()
   * @see #getuncert_Mean()
   * @generated
   */
  EReference getuncert_Mean_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_Variance <em>uncert Variance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Variance</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Variance
   * @generated
   */
  EClass getuncert_Variance();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_Variance#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Variance#getXmlns()
   * @see #getuncert_Variance()
   * @generated
   */
  EReference getuncert_Variance_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Variance#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Variance#getExpr()
   * @see #getuncert_Variance()
   * @generated
   */
  EReference getuncert_Variance_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_PDF <em>uncert PDF</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert PDF</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_PDF
   * @generated
   */
  EClass getuncert_PDF();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_PDF#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_PDF#getXmlns()
   * @see #getuncert_PDF()
   * @generated
   */
  EReference getuncert_PDF_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_PDF#getFunctionParameter <em>Function Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Function Parameter</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_PDF#getFunctionParameter()
   * @see #getuncert_PDF()
   * @generated
   */
  EReference getuncert_PDF_FunctionParameter();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_PDF#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_PDF#getEquation()
   * @see #getuncert_PDF()
   * @generated
   */
  EReference getuncert_PDF_Equation();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType <em>uncert Parameter Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Parameter Definition Type</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType
   * @generated
   */
  EClass getuncert_ParameterDefinitionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType#getXmlns()
   * @see #getuncert_ParameterDefinitionType()
   * @generated
   */
  EReference getuncert_ParameterDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType#getId()
   * @see #getuncert_ParameterDefinitionType()
   * @generated
   */
  EReference getuncert_ParameterDefinitionType_Id();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType#getName()
   * @see #getuncert_ParameterDefinitionType()
   * @generated
   */
  EReference getuncert_ParameterDefinitionType_Name();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_Poison <em>uncert Poison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Poison</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Poison
   * @generated
   */
  EClass getuncert_Poison();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_Poison#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Poison#getXmlns()
   * @see #getuncert_Poison()
   * @generated
   */
  EReference getuncert_Poison_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Poison#getRate <em>Rate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rate</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Poison#getRate()
   * @see #getuncert_Poison()
   * @generated
   */
  EReference getuncert_Poison_Rate();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_Rate <em>uncert Rate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Rate</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Rate
   * @generated
   */
  EClass getuncert_Rate();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_Rate#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Rate#getXmlns()
   * @see #getuncert_Rate()
   * @generated
   */
  EReference getuncert_Rate_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Rate#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Rate#getExpr()
   * @see #getuncert_Rate()
   * @generated
   */
  EReference getuncert_Rate_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_Student_t <em>uncert Student t</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Student t</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Student_t
   * @generated
   */
  EClass getuncert_Student_t();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_Student_t#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Student_t#getXmlns()
   * @see #getuncert_Student_t()
   * @generated
   */
  EReference getuncert_Student_t_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Student_t#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Location</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Student_t#getLocation()
   * @see #getuncert_Student_t()
   * @generated
   */
  EReference getuncert_Student_t_Location();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Student_t#getScale <em>Scale</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scale</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Student_t#getScale()
   * @see #getuncert_Student_t()
   * @generated
   */
  EReference getuncert_Student_t_Scale();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Student_t#getDegreesOfFreedom <em>Degrees Of Freedom</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Degrees Of Freedom</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Student_t#getDegreesOfFreedom()
   * @see #getuncert_Student_t()
   * @generated
   */
  EReference getuncert_Student_t_DegreesOfFreedom();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncertDegreesOfFreedom <em>uncert Degrees Of Freedom</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Degrees Of Freedom</em>'.
   * @see eu.ddmore.pml.pharmML.uncertDegreesOfFreedom
   * @generated
   */
  EClass getuncertDegreesOfFreedom();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncertDegreesOfFreedom#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncertDegreesOfFreedom#getXmlns()
   * @see #getuncertDegreesOfFreedom()
   * @generated
   */
  EReference getuncertDegreesOfFreedom_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncertDegreesOfFreedom#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncertDegreesOfFreedom#getExpr()
   * @see #getuncertDegreesOfFreedom()
   * @generated
   */
  EReference getuncertDegreesOfFreedom_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_Scale <em>uncert Scale</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Scale</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Scale
   * @generated
   */
  EClass getuncert_Scale();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_Scale#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Scale#getXmlns()
   * @see #getuncert_Scale()
   * @generated
   */
  EReference getuncert_Scale_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Scale#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Scale#getExpr()
   * @see #getuncert_Scale()
   * @generated
   */
  EReference getuncert_Scale_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_Location <em>uncert Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Location</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Location
   * @generated
   */
  EClass getuncert_Location();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_Location#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Location#getXmlns()
   * @see #getuncert_Location()
   * @generated
   */
  EReference getuncert_Location_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.uncert_Location#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Location#getExpr()
   * @see #getuncert_Location()
   * @generated
   */
  EReference getuncert_Location_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.uncert_Uniform <em>uncert Uniform</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Uniform</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Uniform
   * @generated
   */
  EClass getuncert_Uniform();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.uncert_Uniform#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.uncert_Uniform#getXmlns()
   * @see #getuncert_Uniform()
   * @generated
   */
  EReference getuncert_Uniform_Xmlns();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_EquationType <em>Math Equation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Equation Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationType
   * @generated
   */
  EClass getMath_EquationType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_EquationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationType#getXmlns()
   * @see #getMath_EquationType()
   * @generated
   */
  EReference getMath_EquationType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_EquationType#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Written Version</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationType#getWrittenVersion()
   * @see #getMath_EquationType()
   * @generated
   */
  EReference getMath_EquationType_WrittenVersion();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_EquationType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationType#getExpr()
   * @see #getMath_EquationType()
   * @generated
   */
  EReference getMath_EquationType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent <em>Math Equation Type Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Equation Type Content</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationTypeContent
   * @generated
   */
  EClass getMath_EquationTypeContent();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationTypeContent#getScalar()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationTypeContent#getVar()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Var();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getBinop <em>Binop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binop</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationTypeContent#getBinop()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Binop();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getUniop <em>Uniop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Uniop</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationTypeContent#getUniop()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Uniop();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getPiecewise <em>Piecewise</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Piecewise</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EquationTypeContent#getPiecewise()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Piecewise();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_ScalarType <em>Math Scalar Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Scalar Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ScalarType
   * @generated
   */
  EClass getMath_ScalarType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_ScalarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ScalarType#getXmlns()
   * @see #getMath_ScalarType()
   * @generated
   */
  EReference getMath_ScalarType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ScalarType#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ScalarType#getValue()
   * @see #getMath_ScalarType()
   * @generated
   */
  EReference getMath_ScalarType_Value();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_VarType <em>Math Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Var Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_VarType
   * @generated
   */
  EClass getMath_VarType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_VarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_VarType#getXmlns()
   * @see #getMath_VarType()
   * @generated
   */
  EReference getMath_VarType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_VarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.Math_VarType#getBlock()
   * @see #getMath_VarType()
   * @generated
   */
  EReference getMath_VarType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_VarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.Math_VarType#getSymbId()
   * @see #getMath_VarType()
   * @generated
   */
  EReference getMath_VarType_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_BinopType <em>Math Binop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Binop Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopType
   * @generated
   */
  EClass getMath_BinopType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_BinopType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopType#getXmlns()
   * @see #getMath_BinopType()
   * @generated
   */
  EReference getMath_BinopType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_BinopType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopType#getOp()
   * @see #getMath_BinopType()
   * @generated
   */
  EReference getMath_BinopType_Op();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_BinopType#getLeftExpr <em>Left Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopType#getLeftExpr()
   * @see #getMath_BinopType()
   * @generated
   */
  EReference getMath_BinopType_LeftExpr();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_BinopType#getRightExpr <em>Right Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopType#getRightExpr()
   * @see #getMath_BinopType()
   * @generated
   */
  EReference getMath_BinopType_RightExpr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_UniopType <em>Math Uniop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Uniop Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopType
   * @generated
   */
  EClass getMath_UniopType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_UniopType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopType#getXmlns()
   * @see #getMath_UniopType()
   * @generated
   */
  EReference getMath_UniopType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_UniopType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopType#getOp()
   * @see #getMath_UniopType()
   * @generated
   */
  EReference getMath_UniopType_Op();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_UniopType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopType#getExpr()
   * @see #getMath_UniopType()
   * @generated
   */
  EReference getMath_UniopType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_ExprType <em>Math Expr Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Expr Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ExprType
   * @generated
   */
  EClass getMath_ExprType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ExprType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ExprType#getScalar()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ExprType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ExprType#getVar()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Var();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ExprType#getUniop <em>Uniop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Uniop</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ExprType#getUniop()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Uniop();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ExprType#getBinop <em>Binop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binop</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ExprType#getBinop()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Binop();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ExprType#getConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constant</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ExprType#getConstant()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Constant();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ExprType#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ExprType#getFunctionCall()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_FunctionCall();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_ConstantType <em>Math Constant Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Constant Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConstantType
   * @generated
   */
  EClass getMath_ConstantType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_ConstantType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConstantType#getXmlns()
   * @see #getMath_ConstantType()
   * @generated
   */
  EReference getMath_ConstantType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ConstantType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConstantType#getOp()
   * @see #getMath_ConstantType()
   * @generated
   */
  EReference getMath_ConstantType_Op();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_FunctionCallType <em>Math Function Call Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Function Call Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FunctionCallType
   * @generated
   */
  EClass getMath_FunctionCallType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_FunctionCallType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FunctionCallType#getXmlns()
   * @see #getMath_FunctionCallType()
   * @generated
   */
  EReference getMath_FunctionCallType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_FunctionCallType#getFunctionname <em>Functionname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Functionname</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FunctionCallType#getFunctionname()
   * @see #getMath_FunctionCallType()
   * @generated
   */
  EReference getMath_FunctionCallType_Functionname();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_FunctionCallType#getFunctionArguments <em>Function Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Function Arguments</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FunctionCallType#getFunctionArguments()
   * @see #getMath_FunctionCallType()
   * @generated
   */
  EReference getMath_FunctionCallType_FunctionArguments();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType <em>Math Function Argument Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Function Argument Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FunctionArgumentType
   * @generated
   */
  EClass getMath_FunctionArgumentType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getXmlns()
   * @see #getMath_FunctionArgumentType()
   * @generated
   */
  EReference getMath_FunctionArgumentType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getSymbId()
   * @see #getMath_FunctionArgumentType()
   * @generated
   */
  EReference getMath_FunctionArgumentType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getExpr()
   * @see #getMath_FunctionArgumentType()
   * @generated
   */
  EReference getMath_FunctionArgumentType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_FuncExprType <em>Math Func Expr Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Func Expr Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FuncExprType
   * @generated
   */
  EClass getMath_FuncExprType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_FuncExprType#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FuncExprType#getEquation()
   * @see #getMath_FuncExprType()
   * @generated
   */
  EReference getMath_FuncExprType_Equation();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_FuncExprType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FuncExprType#getScalar()
   * @see #getMath_FuncExprType()
   * @generated
   */
  EReference getMath_FuncExprType_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_FuncExprType#getConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constant</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FuncExprType#getConstant()
   * @see #getMath_FuncExprType()
   * @generated
   */
  EReference getMath_FuncExprType_Constant();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_FuncExprType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FuncExprType#getVar()
   * @see #getMath_FuncExprType()
   * @generated
   */
  EReference getMath_FuncExprType_Var();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_PiecewiseType <em>Math Piecewise Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Piecewise Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_PiecewiseType
   * @generated
   */
  EClass getMath_PiecewiseType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_PiecewiseType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_PiecewiseType#getXmlns()
   * @see #getMath_PiecewiseType()
   * @generated
   */
  EReference getMath_PiecewiseType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_PiecewiseType#getPiecewise <em>Piecewise</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Piecewise</em>'.
   * @see eu.ddmore.pml.pharmML.Math_PiecewiseType#getPiecewise()
   * @see #getMath_PiecewiseType()
   * @generated
   */
  EReference getMath_PiecewiseType_Piecewise();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_PieceType <em>Math Piece Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Piece Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_PieceType
   * @generated
   */
  EClass getMath_PieceType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_PieceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_PieceType#getXmlns()
   * @see #getMath_PieceType()
   * @generated
   */
  EReference getMath_PieceType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_PieceType#getContent <em>Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Content</em>'.
   * @see eu.ddmore.pml.pharmML.Math_PieceType#getContent()
   * @see #getMath_PieceType()
   * @generated
   */
  EReference getMath_PieceType_Content();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_PieceType#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see eu.ddmore.pml.pharmML.Math_PieceType#getCondition()
   * @see #getMath_PieceType()
   * @generated
   */
  EReference getMath_PieceType_Condition();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_ConditionType <em>Math Condition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Condition Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConditionType
   * @generated
   */
  EClass getMath_ConditionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_ConditionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConditionType#getXmlns()
   * @see #getMath_ConditionType()
   * @generated
   */
  EReference getMath_ConditionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ConditionType#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Written Version</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConditionType#getWrittenVersion()
   * @see #getMath_ConditionType()
   * @generated
   */
  EReference getMath_ConditionType_WrittenVersion();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_ConditionType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConditionType#getExpr()
   * @see #getMath_ConditionType()
   * @generated
   */
  EReference getMath_ConditionType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType <em>Math Logic Base Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Base Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBaseType
   * @generated
   */
  EClass getMath_LogicBaseType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getLogicBinopType <em>Logic Binop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Logic Binop Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBaseType#getLogicBinopType()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_LogicBinopType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getLogicUniopType <em>Logic Uniop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Logic Uniop Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBaseType#getLogicUniopType()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_LogicUniopType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getTrue <em>True</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>True</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBaseType#getTrue()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_True();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getFalse <em>False</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>False</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBaseType#getFalse()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_False();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getOtherwise <em>Otherwise</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Otherwise</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBaseType#getOtherwise()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_Otherwise();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_Otherwise <em>Math Otherwise</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Otherwise</em>'.
   * @see eu.ddmore.pml.pharmML.Math_Otherwise
   * @generated
   */
  EClass getMath_Otherwise();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_Otherwise#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_Otherwise#getXmlns()
   * @see #getMath_Otherwise()
   * @generated
   */
  EReference getMath_Otherwise_Xmlns();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_LogicExprType <em>Math Logic Expr Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Expr Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicExprType
   * @generated
   */
  EClass getMath_LogicExprType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicExprType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicExprType#getScalar()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicExprType#getConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constant</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicExprType#getConstant()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_Constant();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicExprType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicExprType#getVar()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_Var();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicExprType#getUniop <em>Uniop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Uniop</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicExprType#getUniop()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_Uniop();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicExprType#getBinop <em>Binop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binop</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicExprType#getBinop()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_Binop();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicExprType#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicExprType#getFunctionCall()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_FunctionCall();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicExprType#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>String</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicExprType#getString()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_String();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_StringType <em>Math String Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math String Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_StringType
   * @generated
   */
  EClass getMath_StringType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_StringType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_StringType#getXmlns()
   * @see #getMath_StringType()
   * @generated
   */
  EReference getMath_StringType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_StringType#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see eu.ddmore.pml.pharmML.Math_StringType#getValue()
   * @see #getMath_StringType()
   * @generated
   */
  EReference getMath_StringType_Value();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_TRUE <em>Math TRUE</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math TRUE</em>'.
   * @see eu.ddmore.pml.pharmML.Math_TRUE
   * @generated
   */
  EClass getMath_TRUE();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_TRUE#getTrue <em>True</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>True</em>'.
   * @see eu.ddmore.pml.pharmML.Math_TRUE#getTrue()
   * @see #getMath_TRUE()
   * @generated
   */
  EAttribute getMath_TRUE_True();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_FALSE <em>Math FALSE</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math FALSE</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FALSE
   * @generated
   */
  EClass getMath_FALSE();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_FALSE#getFalse <em>False</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>False</em>'.
   * @see eu.ddmore.pml.pharmML.Math_FALSE#getFalse()
   * @see #getMath_FALSE()
   * @generated
   */
  EAttribute getMath_FALSE_False();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_LogicBinopType <em>Math Logic Binop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Binop Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopType
   * @generated
   */
  EClass getMath_LogicBinopType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopType#getXmlns()
   * @see #getMath_LogicBinopType()
   * @generated
   */
  EReference getMath_LogicBinopType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopType#getOp()
   * @see #getMath_LogicBinopType()
   * @generated
   */
  EReference getMath_LogicBinopType_Op();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getLeftExpr <em>Left Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopType#getLeftExpr()
   * @see #getMath_LogicBinopType()
   * @generated
   */
  EReference getMath_LogicBinopType_LeftExpr();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getRightExpr <em>Right Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopType#getRightExpr()
   * @see #getMath_LogicBinopType()
   * @generated
   */
  EReference getMath_LogicBinopType_RightExpr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_LogicUniopType <em>Math Logic Uniop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Uniop Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicUniopType
   * @generated
   */
  EClass getMath_LogicUniopType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Math_LogicUniopType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicUniopType#getXmlns()
   * @see #getMath_LogicUniopType()
   * @generated
   */
  EReference getMath_LogicUniopType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicUniopType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicUniopType#getOp()
   * @see #getMath_LogicUniopType()
   * @generated
   */
  EReference getMath_LogicUniopType_Op();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Math_LogicUniopType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicUniopType#getExpr()
   * @see #getMath_LogicUniopType()
   * @generated
   */
  EReference getMath_LogicUniopType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_BinopName <em>Math Binop Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Binop Name</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopName
   * @generated
   */
  EClass getMath_BinopName();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_BinopName#getBinopname <em>Binopname</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Binopname</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopName#getBinopname()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Binopname();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_BinopName#getMinus <em>Minus</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Minus</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopName#getMinus()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Minus();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_BinopName#getTimes <em>Times</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Times</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopName#getTimes()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Times();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_BinopName#getDivide <em>Divide</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Divide</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopName#getDivide()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Divide();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_BinopName#getPower <em>Power</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Power</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopName#getPower()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Power();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_BinopName#getLog <em>Log</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Log</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopName#getLog()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Log();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_BinopName#getRoot <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Root</em>'.
   * @see eu.ddmore.pml.pharmML.Math_BinopName#getRoot()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Root();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_UniopName <em>Math Uniop Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Uniop Name</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName
   * @generated
   */
  EClass getMath_UniopName();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getExp <em>Exp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Exp</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getExp()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Exp();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getLn <em>Ln</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ln</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getLn()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Ln();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getMinus <em>Minus</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Minus</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getMinus()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Minus();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getFactorial <em>Factorial</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Factorial</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getFactorial()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Factorial();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getSin <em>Sin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sin</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getSin()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Sin();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getCos <em>Cos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cos</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getCos()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Cos();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getTan <em>Tan</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tan</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getTan()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Tan();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getSec <em>Sec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sec</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getSec()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Sec();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getCsc <em>Csc</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Csc</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getCsc()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Csc();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getCot <em>Cot</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cot</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getCot()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Cot();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getSinh <em>Sinh</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sinh</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getSinh()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Sinh();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getCsch <em>Csch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Csch</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getCsch()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Csch();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getCoth <em>Coth</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Coth</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getCoth()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Coth();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArcsin <em>Arcsin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arcsin</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArcsin()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arcsin();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArccos <em>Arccos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccos</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArccos()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccos();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArctan <em>Arctan</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arctan</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArctan()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arctan();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArcsec <em>Arcsec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arcsec</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArcsec()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arcsec();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArccsc <em>Arccsc</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccsc</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArccsc()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccsc();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArccot <em>Arccot</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccot</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArccot()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccot();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArcsinh <em>Arcsinh</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arcsinh</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArcsinh()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arcsinh();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArccosh <em>Arccosh</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccosh</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArccosh()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccosh();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArctanh <em>Arctanh</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arctanh</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArctanh()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arctanh();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArcsech <em>Arcsech</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arcsech</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArcsech()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arcsech();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArccsch <em>Arccsch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccsch</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArccsch()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccsch();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getArccoth <em>Arccoth</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccoth</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getArccoth()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccoth();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getFloor <em>Floor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Floor</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getFloor()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Floor();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getAbs <em>Abs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Abs</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getAbs()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Abs();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getCeiling <em>Ceiling</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ceiling</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getCeiling()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Ceiling();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_UniopName#getLogit <em>Logit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Logit</em>'.
   * @see eu.ddmore.pml.pharmML.Math_UniopName#getLogit()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Logit();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_LogicUniopName <em>Math Logic Uniop Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Uniop Name</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicUniopName
   * @generated
   */
  EClass getMath_LogicUniopName();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicUniopName#getIsDefined <em>Is Defined</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Defined</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicUniopName#getIsDefined()
   * @see #getMath_LogicUniopName()
   * @generated
   */
  EAttribute getMath_LogicUniopName_IsDefined();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicUniopName#getNot <em>Not</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Not</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicUniopName#getNot()
   * @see #getMath_LogicUniopName()
   * @generated
   */
  EAttribute getMath_LogicUniopName_Not();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName <em>Math Logic Binop Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Binop Name</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName
   * @generated
   */
  EClass getMath_LogicBinopName();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName#getLt <em>Lt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lt</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName#getLt()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Lt();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName#getLeq <em>Leq</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Leq</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName#getLeq()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Leq();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName#getGt <em>Gt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Gt</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName#getGt()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Gt();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName#getGeq <em>Geq</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Geq</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName#getGeq()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Geq();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName#getEq <em>Eq</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Eq</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName#getEq()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Eq();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName#getNeq <em>Neq</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Neq</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName#getNeq()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Neq();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName#getAnd <em>And</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>And</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName#getAnd()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_And();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName#getOr <em>Or</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Or</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName#getOr()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Or();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_LogicBinopName#getXor <em>Xor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Xor</em>'.
   * @see eu.ddmore.pml.pharmML.Math_LogicBinopName#getXor()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Xor();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_ConstantName <em>Math Constant Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Constant Name</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConstantName
   * @generated
   */
  EClass getMath_ConstantName();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_ConstantName#getNotanumber <em>Notanumber</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Notanumber</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConstantName#getNotanumber()
   * @see #getMath_ConstantName()
   * @generated
   */
  EAttribute getMath_ConstantName_Notanumber();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_ConstantName#getPi <em>Pi</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pi</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConstantName#getPi()
   * @see #getMath_ConstantName()
   * @generated
   */
  EAttribute getMath_ConstantName_Pi();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_ConstantName#getExponentiale <em>Exponentiale</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Exponentiale</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConstantName#getExponentiale()
   * @see #getMath_ConstantName()
   * @generated
   */
  EAttribute getMath_ConstantName_Exponentiale();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_ConstantName#getInfinity <em>Infinity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Infinity</em>'.
   * @see eu.ddmore.pml.pharmML.Math_ConstantName#getInfinity()
   * @see #getMath_ConstantName()
   * @generated
   */
  EAttribute getMath_ConstantName_Infinity();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_symbId <em>Math symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.Math_symbId
   * @generated
   */
  EClass getMath_symbId();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_symbId#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.Math_symbId#getSymbId()
   * @see #getMath_symbId()
   * @generated
   */
  EAttribute getMath_symbId_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_id <em>Math id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math id</em>'.
   * @see eu.ddmore.pml.pharmML.Math_id
   * @generated
   */
  EClass getMath_id();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_id#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.Math_id#getId()
   * @see #getMath_id()
   * @generated
   */
  EAttribute getMath_id_Id();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_block <em>Math block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math block</em>'.
   * @see eu.ddmore.pml.pharmML.Math_block
   * @generated
   */
  EClass getMath_block();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.Math_block#getBlock()
   * @see #getMath_block()
   * @generated
   */
  EAttribute getMath_block_Block();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType <em>Math Estimation Operation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Estimation Operation Type</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EstimationOperationType
   * @generated
   */
  EClass getMath_EstimationOperationType();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstPop <em>Est Pop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Est Pop</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstPop()
   * @see #getMath_EstimationOperationType()
   * @generated
   */
  EAttribute getMath_EstimationOperationType_EstPop();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstFIM <em>Est FIM</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Est FIM</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstFIM()
   * @see #getMath_EstimationOperationType()
   * @generated
   */
  EAttribute getMath_EstimationOperationType_EstFIM();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstIndiv <em>Est Indiv</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Est Indiv</em>'.
   * @see eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstIndiv()
   * @see #getMath_EstimationOperationType()
   * @generated
   */
  EAttribute getMath_EstimationOperationType_EstIndiv();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_ReplicatesType <em>ct Replicates Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Replicates Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ReplicatesType
   * @generated
   */
  EClass getct_ReplicatesType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_ReplicatesType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ReplicatesType#getXmlns()
   * @see #getct_ReplicatesType()
   * @generated
   */
  EReference getct_ReplicatesType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_ReplicatesType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ReplicatesType#getSymbId()
   * @see #getct_ReplicatesType()
   * @generated
   */
  EReference getct_ReplicatesType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_ReplicatesType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ReplicatesType#getExpr()
   * @see #getct_ReplicatesType()
   * @generated
   */
  EReference getct_ReplicatesType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_AnnotationType <em>ct Annotation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Annotation Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_AnnotationType
   * @generated
   */
  EClass getct_AnnotationType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_AnnotationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_AnnotationType#getXmlns()
   * @see #getct_AnnotationType()
   * @generated
   */
  EReference getct_AnnotationType_Xmlns();

  /**
   * Returns the meta object for the attribute list '{@link eu.ddmore.pml.pharmML.ct_AnnotationType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Description</em>'.
   * @see eu.ddmore.pml.pharmML.ct_AnnotationType#getDescription()
   * @see #getct_AnnotationType()
   * @generated
   */
  EAttribute getct_AnnotationType_Description();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_Rhs <em>ct Rhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Rhs</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs
   * @generated
   */
  EClass getct_Rhs();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getEquation()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Equation();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getScalar()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constant</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getConstant()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Constant();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>String</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getString()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_String();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getSequence <em>Sequence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sequence</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getSequence()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Sequence();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getVector <em>Vector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Vector</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getVector()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Vector();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getFunctionCall()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_FunctionCall();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getDataSet <em>Data Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Data Set</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getDataSet()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_DataSet();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getVar()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Var();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_Rhs#getDistribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Distribution</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Rhs#getDistribution()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Distribution();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_DataSetType <em>ct Data Set Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Data Set Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DataSetType
   * @generated
   */
  EClass getct_DataSetType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_DataSetType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DataSetType#getXmlns()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_DataSetType#getDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Definition</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DataSetType#getDefinition()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_Definition();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_DataSetType#getExternalSource <em>External Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>External Source</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DataSetType#getExternalSource()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_ExternalSource();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_DataSetType#getRow <em>Row</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Row</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DataSetType#getRow()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_Row();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_DataSetType#getInternalSource <em>Internal Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Internal Source</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DataSetType#getInternalSource()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_InternalSource();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_DefinitionType <em>ct Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Definition Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DefinitionType
   * @generated
   */
  EClass getct_DefinitionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_DefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DefinitionType#getXmlns()
   * @see #getct_DefinitionType()
   * @generated
   */
  EReference getct_DefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_DefinitionType#getDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Definition</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DefinitionType#getDefinition()
   * @see #getct_DefinitionType()
   * @generated
   */
  EReference getct_DefinitionType_Definition();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_DefinitionRhsType <em>ct Definition Rhs Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Definition Rhs Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DefinitionRhsType
   * @generated
   */
  EClass getct_DefinitionRhsType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_DefinitionRhsType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DefinitionRhsType#getXmlns()
   * @see #getct_DefinitionRhsType()
   * @generated
   */
  EReference getct_DefinitionRhsType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_DefinitionRhsType#getDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Definition</em>'.
   * @see eu.ddmore.pml.pharmML.ct_DefinitionRhsType#getDefinition()
   * @see #getct_DefinitionRhsType()
   * @generated
   */
  EReference getct_DefinitionRhsType_Definition();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_ColumnType <em>ct Column Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Column Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ColumnType
   * @generated
   */
  EClass getct_ColumnType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_ColumnType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ColumnType#getXmlns()
   * @see #getct_ColumnType()
   * @generated
   */
  EReference getct_ColumnType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_ColumnType#getColumnNum <em>Column Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Column Num</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ColumnType#getColumnNum()
   * @see #getct_ColumnType()
   * @generated
   */
  EAttribute getct_ColumnType_ColumnNum();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_ColumnType#getColumnVar <em>Column Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Column Var</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ColumnType#getColumnVar()
   * @see #getct_ColumnType()
   * @generated
   */
  EAttribute getct_ColumnType_ColumnVar();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_ExternalSourceType <em>ct External Source Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct External Source Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ExternalSourceType
   * @generated
   */
  EClass getct_ExternalSourceType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_ExternalSourceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ExternalSourceType#getXmlns()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EReference getct_ExternalSourceType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_ExternalSourceType#getFormat <em>Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Format</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ExternalSourceType#getFormat()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EAttribute getct_ExternalSourceType_Format();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_ExternalSourceType#getHeaderLine <em>Header Line</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Header Line</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ExternalSourceType#getHeaderLine()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EReference getct_ExternalSourceType_HeaderLine();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_ExternalSourceType#getIgnoreLineSymbol <em>Ignore Line Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ignore Line Symbol</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ExternalSourceType#getIgnoreLineSymbol()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EAttribute getct_ExternalSourceType_IgnoreLineSymbol();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_ExternalSourceType#getUrl <em>Url</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Url</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ExternalSourceType#getUrl()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EAttribute getct_ExternalSourceType_Url();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_RowType <em>ct Row Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Row Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_RowType
   * @generated
   */
  EClass getct_RowType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_RowType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_RowType#getXmlns()
   * @see #getct_RowType()
   * @generated
   */
  EReference getct_RowType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_RowType#getRow <em>Row</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Row</em>'.
   * @see eu.ddmore.pml.pharmML.ct_RowType#getRow()
   * @see #getct_RowType()
   * @generated
   */
  EReference getct_RowType_Row();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_RowTypeContent <em>ct Row Type Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Row Type Content</em>'.
   * @see eu.ddmore.pml.pharmML.ct_RowTypeContent
   * @generated
   */
  EClass getct_RowTypeContent();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_RowTypeContent#getCell <em>Cell</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cell</em>'.
   * @see eu.ddmore.pml.pharmML.ct_RowTypeContent#getCell()
   * @see #getct_RowTypeContent()
   * @generated
   */
  EReference getct_RowTypeContent_Cell();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_RowTypeContent#getNull <em>Null</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Null</em>'.
   * @see eu.ddmore.pml.pharmML.ct_RowTypeContent#getNull()
   * @see #getct_RowTypeContent()
   * @generated
   */
  EReference getct_RowTypeContent_Null();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_NullCellType <em>ct Null Cell Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Null Cell Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_NullCellType
   * @generated
   */
  EClass getct_NullCellType();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_NullCellType#getNull <em>Null</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Null</em>'.
   * @see eu.ddmore.pml.pharmML.ct_NullCellType#getNull()
   * @see #getct_NullCellType()
   * @generated
   */
  EAttribute getct_NullCellType_Null();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_CellType <em>ct Cell Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Cell Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_CellType
   * @generated
   */
  EClass getct_CellType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_CellType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_CellType#getXmlns()
   * @see #getct_CellType()
   * @generated
   */
  EReference getct_CellType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_CellType#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see eu.ddmore.pml.pharmML.ct_CellType#getValue()
   * @see #getct_CellType()
   * @generated
   */
  EReference getct_CellType_Value();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_InternalSourceType <em>ct Internal Source Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Internal Source Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_InternalSourceType
   * @generated
   */
  EClass getct_InternalSourceType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_InternalSourceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_InternalSourceType#getXmlns()
   * @see #getct_InternalSourceType()
   * @generated
   */
  EReference getct_InternalSourceType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_InternalSourceType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.ct_InternalSourceType#getBlock()
   * @see #getct_InternalSourceType()
   * @generated
   */
  EReference getct_InternalSourceType_Block();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_InternalSourceType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_InternalSourceType#getSymbId()
   * @see #getct_InternalSourceType()
   * @generated
   */
  EReference getct_InternalSourceType_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_VectorType <em>ct Vector Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Vector Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VectorType
   * @generated
   */
  EClass getct_VectorType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_VectorType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VectorType#getXmlns()
   * @see #getct_VectorType()
   * @generated
   */
  EReference getct_VectorType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_VectorType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VectorType#getScalar()
   * @see #getct_VectorType()
   * @generated
   */
  EReference getct_VectorType_Scalar();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_VectorType#getSequence <em>Sequence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Sequence</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VectorType#getSequence()
   * @see #getct_VectorType()
   * @generated
   */
  EReference getct_VectorType_Sequence();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_SequenceType <em>ct Sequence Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Sequence Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SequenceType
   * @generated
   */
  EClass getct_SequenceType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_SequenceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SequenceType#getXmlns()
   * @see #getct_SequenceType()
   * @generated
   */
  EReference getct_SequenceType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_SequenceType#getBegin <em>Begin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Begin</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SequenceType#getBegin()
   * @see #getct_SequenceType()
   * @generated
   */
  EAttribute getct_SequenceType_Begin();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_SequenceType#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>End</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SequenceType#getEnd()
   * @see #getct_SequenceType()
   * @generated
   */
  EAttribute getct_SequenceType_End();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_SequenceType#getStepwise <em>Stepwise</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Stepwise</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SequenceType#getStepwise()
   * @see #getct_SequenceType()
   * @generated
   */
  EAttribute getct_SequenceType_Stepwise();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_SequenceType#getRepetition <em>Repetition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Repetition</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SequenceType#getRepetition()
   * @see #getct_SequenceType()
   * @generated
   */
  EAttribute getct_SequenceType_Repetition();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_VariableDefinitionType <em>ct Variable Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Variable Definition Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VariableDefinitionType
   * @generated
   */
  EClass getct_VariableDefinitionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getXmlns()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getIndependentVar <em>Independent Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Independent Var</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getIndependentVar()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_IndependentVar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getSymbId()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getSymbolType <em>Symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getSymbolType()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_SymbolType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getDescription()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_Description();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.ct_VariableDefinitionType#getExpr()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_RestrictionType <em>ct Restriction Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Restriction Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_RestrictionType
   * @generated
   */
  EClass getct_RestrictionType();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.ct_RestrictionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.ct_RestrictionType#getXmlns()
   * @see #getct_RestrictionType()
   * @generated
   */
  EReference getct_RestrictionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_RestrictionType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see eu.ddmore.pml.pharmML.ct_RestrictionType#getExpr()
   * @see #getct_RestrictionType()
   * @generated
   */
  EReference getct_RestrictionType_Expr();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_ScalarRhs <em>ct Scalar Rhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Scalar Rhs</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ScalarRhs
   * @generated
   */
  EClass getct_ScalarRhs();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_ScalarRhs#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ScalarRhs#getEquation()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_Equation();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_ScalarRhs#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ScalarRhs#getScalar()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_ScalarRhs#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ScalarRhs#getVar()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_Var();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_ScalarRhs#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>String</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ScalarRhs#getString()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_String();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_ScalarRhs#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see eu.ddmore.pml.pharmML.ct_ScalarRhs#getFunctionCall()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_FunctionCall();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_independentVar <em>ct independent Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct independent Var</em>'.
   * @see eu.ddmore.pml.pharmML.ct_independentVar
   * @generated
   */
  EClass getct_independentVar();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_independentVar#getIndependentVar <em>Independent Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Independent Var</em>'.
   * @see eu.ddmore.pml.pharmML.ct_independentVar#getIndependentVar()
   * @see #getct_independentVar()
   * @generated
   */
  EAttribute getct_independentVar_IndependentVar();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_levelId <em>ct level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct level Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_levelId
   * @generated
   */
  EClass getct_levelId();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_levelId#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Level Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_levelId#getLevelId()
   * @see #getct_levelId()
   * @generated
   */
  EAttribute getct_levelId_LevelId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_name <em>ct name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct name</em>'.
   * @see eu.ddmore.pml.pharmML.ct_name
   * @generated
   */
  EClass getct_name();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_name#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see eu.ddmore.pml.pharmML.ct_name#getName()
   * @see #getct_name()
   * @generated
   */
  EAttribute getct_name_Name();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_symbId <em>ct symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_symbId
   * @generated
   */
  EClass getct_symbId();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_symbId#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Symb Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_symbId#getSymbId()
   * @see #getct_symbId()
   * @generated
   */
  EAttribute getct_symbId_SymbId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_id <em>ct id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_id
   * @generated
   */
  EClass getct_id();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_id#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_id#getId()
   * @see #getct_id()
   * @generated
   */
  EAttribute getct_id_Id();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_symbolType <em>ct symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct symbol Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_symbolType
   * @generated
   */
  EClass getct_symbolType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.ct_symbolType#getSymbolType <em>Symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_symbolType#getSymbolType()
   * @see #getct_symbolType()
   * @generated
   */
  EReference getct_symbolType_SymbolType();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_block <em>ct block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct block</em>'.
   * @see eu.ddmore.pml.pharmML.ct_block
   * @generated
   */
  EClass getct_block();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Block</em>'.
   * @see eu.ddmore.pml.pharmML.ct_block#getBlock()
   * @see #getct_block()
   * @generated
   */
  EAttribute getct_block_Block();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_writtenVersion <em>ct written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct written Version</em>'.
   * @see eu.ddmore.pml.pharmML.ct_writtenVersion
   * @generated
   */
  EClass getct_writtenVersion();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_writtenVersion#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Written Version</em>'.
   * @see eu.ddmore.pml.pharmML.ct_writtenVersion#getWrittenVersion()
   * @see #getct_writtenVersion()
   * @generated
   */
  EAttribute getct_writtenVersion_WrittenVersion();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_Decimal_Value <em>ct Decimal Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Decimal Value</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Decimal_Value
   * @generated
   */
  EClass getct_Decimal_Value();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_Decimal_Value#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see eu.ddmore.pml.pharmML.ct_Decimal_Value#getValue()
   * @see #getct_Decimal_Value()
   * @generated
   */
  EAttribute getct_Decimal_Value_Value();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_String_Value <em>ct String Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct String Value</em>'.
   * @see eu.ddmore.pml.pharmML.ct_String_Value
   * @generated
   */
  EClass getct_String_Value();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_String_Value#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see eu.ddmore.pml.pharmML.ct_String_Value#getValue()
   * @see #getct_String_Value()
   * @generated
   */
  EAttribute getct_String_Value_Value();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_catId <em>ct cat Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct cat Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_catId
   * @generated
   */
  EClass getct_catId();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_catId#getCatId <em>Cat Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cat Id</em>'.
   * @see eu.ddmore.pml.pharmML.ct_catId#getCatId()
   * @see #getct_catId()
   * @generated
   */
  EAttribute getct_catId_CatId();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.ct_SymbolTypeType <em>ct Symbol Type Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Symbol Type Type</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SymbolTypeType
   * @generated
   */
  EClass getct_SymbolTypeType();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_SymbolTypeType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Scalar</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SymbolTypeType#getScalar()
   * @see #getct_SymbolTypeType()
   * @generated
   */
  EAttribute getct_SymbolTypeType_Scalar();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_SymbolTypeType#getDerivative <em>Derivative</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Derivative</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SymbolTypeType#getDerivative()
   * @see #getct_SymbolTypeType()
   * @generated
   */
  EAttribute getct_SymbolTypeType_Derivative();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_SymbolTypeType#getDistrib <em>Distrib</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Distrib</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SymbolTypeType#getDistrib()
   * @see #getct_SymbolTypeType()
   * @generated
   */
  EAttribute getct_SymbolTypeType_Distrib();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.ct_SymbolTypeType#getDataSet <em>Data Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Set</em>'.
   * @see eu.ddmore.pml.pharmML.ct_SymbolTypeType#getDataSet()
   * @see #getct_SymbolTypeType()
   * @generated
   */
  EAttribute getct_SymbolTypeType_DataSet();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.XS_xmlns <em>XS xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XS xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.XS_xmlns
   * @generated
   */
  EClass getXS_xmlns();

  /**
   * Returns the meta object for the attribute '{@link eu.ddmore.pml.pharmML.XS_xmlns#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Location</em>'.
   * @see eu.ddmore.pml.pharmML.XS_xmlns#getLocation()
   * @see #getXS_xmlns()
   * @generated
   */
  EAttribute getXS_xmlns_Location();

  /**
   * Returns the meta object for class '{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp <em>Mml Func Parameter Definition Typ</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Func Parameter Definition Typ</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp
   * @generated
   */
  EClass getMml_FuncParameterDefinitionTyp();

  /**
   * Returns the meta object for the containment reference list '{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getXmlns()
   * @see #getMml_FuncParameterDefinitionTyp()
   * @generated
   */
  EReference getMml_FuncParameterDefinitionTyp_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getSumbId <em>Sumb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sumb Id</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getSumbId()
   * @see #getMml_FuncParameterDefinitionTyp()
   * @generated
   */
  EReference getMml_FuncParameterDefinitionTyp_SumbId();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getSymbolType <em>Symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol Type</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getSymbolType()
   * @see #getMml_FuncParameterDefinitionTyp()
   * @generated
   */
  EReference getMml_FuncParameterDefinitionTyp_SymbolType();

  /**
   * Returns the meta object for the containment reference '{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getDecsription <em>Decsription</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Decsription</em>'.
   * @see eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getDecsription()
   * @see #getMml_FuncParameterDefinitionTyp()
   * @generated
   */
  EReference getMml_FuncParameterDefinitionTyp_Decsription();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  PharmMLFactory getPharmMLFactory();

} //PharmMLPackage

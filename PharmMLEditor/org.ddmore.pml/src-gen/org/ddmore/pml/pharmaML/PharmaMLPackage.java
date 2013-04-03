/**
 */
package org.ddmore.pml.pharmaML;

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
 * @see org.ddmore.pml.pharmaML.PharmaMLFactory
 * @model kind="package"
 * @generated
 */
public interface PharmaMLPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "pharmaML";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.ddmore.org/pml/PharmaML";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "pharmaML";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PharmaMLPackage eINSTANCE = org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl.init();

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl <em>Mml Pharma ML</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMml_PharmaML()
   * @generated
   */
  int MML_PHARMA_ML = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML__NAME = 1;

  /**
   * The feature id for the '<em><b>Independent Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML__INDEPENDENT_VAR = 2;

  /**
   * The feature id for the '<em><b>Written Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML__WRITTEN_VERSION = 3;

  /**
   * The feature id for the '<em><b>Symbol Definition</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML__SYMBOL_DEFINITION = 4;

  /**
   * The feature id for the '<em><b>Structural Model</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML__STRUCTURAL_MODEL = 5;

  /**
   * The feature id for the '<em><b>Model Definition</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML__MODEL_DEFINITION = 6;

  /**
   * The feature id for the '<em><b>Design</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML__DESIGN = 7;

  /**
   * The feature id for the '<em><b>Modeling Steps</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML__MODELING_STEPS = 8;

  /**
   * The number of structural features of the '<em>Mml Pharma ML</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_PHARMA_ML_FEATURE_COUNT = 9;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Mml_SymbolDefinitionTypeImpl <em>Mml Symbol Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Mml_SymbolDefinitionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMml_SymbolDefinitionType()
   * @generated
   */
  int MML_SYMBOL_DEFINITION_TYPE = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Symbol Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__NAME = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_SYMBOL_DEFINITION_TYPE__XMLNS = 3;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Mml_FunctionDefinitionTypeImpl <em>Mml Function Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Mml_FunctionDefinitionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMml_FunctionDefinitionType()
   * @generated
   */
  int MML_FUNCTION_DEFINITION_TYPE = 2;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Mml_FuncParameterDefinitionTypeImpl <em>Mml Func Parameter Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Mml_FuncParameterDefinitionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMml_FuncParameterDefinitionType()
   * @generated
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYPE = 3;

  /**
   * The feature id for the '<em><b>Sumb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYPE__SUMB_ID = 0;

  /**
   * The feature id for the '<em><b>Symbol Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYPE__SYMBOL_TYPE = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYPE__XMLNS = 2;

  /**
   * The feature id for the '<em><b>Decsription</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYPE__DECSRIPTION = 3;

  /**
   * The number of structural features of the '<em>Mml Func Parameter Definition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MML_FUNC_PARAMETER_DEFINITION_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_EquationTypeImpl <em>Math Equation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_EquationTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_EquationType()
   * @generated
   */
  int MATH_EQUATION_TYPE = 4;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_EquationTypeContentImpl <em>Math Equation Type Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_EquationTypeContentImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_EquationTypeContent()
   * @generated
   */
  int MATH_EQUATION_TYPE_CONTENT = 5;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_ScalarTypeImpl <em>Math Scalar Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_ScalarTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_ScalarType()
   * @generated
   */
  int MATH_SCALAR_TYPE = 6;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_SCALAR_TYPE__VALUE = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_SCALAR_TYPE__XMLNS = 1;

  /**
   * The number of structural features of the '<em>Math Scalar Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_SCALAR_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_VarTypeImpl <em>Math Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_VarTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_VarType()
   * @generated
   */
  int MATH_VAR_TYPE = 7;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_VAR_TYPE__BLOCK = 0;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_VAR_TYPE__SYMB_ID = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_VAR_TYPE__XMLNS = 2;

  /**
   * The number of structural features of the '<em>Math Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_BinopTypeImpl <em>Math Binop Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_BinopTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_BinopType()
   * @generated
   */
  int MATH_BINOP_TYPE = 8;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_TYPE__OP = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_UniopTypeImpl <em>Math Uniop Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_UniopTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_UniopType()
   * @generated
   */
  int MATH_UNIOP_TYPE = 9;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_TYPE__OP = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_UNIOP_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_ExprTypeImpl <em>Math Expr Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_ExprTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_ExprType()
   * @generated
   */
  int MATH_EXPR_TYPE = 10;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_ConstantTypeImpl <em>Math Constant Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_ConstantTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_ConstantType()
   * @generated
   */
  int MATH_CONSTANT_TYPE = 11;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_TYPE__OP = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_TYPE__XMLNS = 1;

  /**
   * The number of structural features of the '<em>Math Constant Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONSTANT_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_FunctionCallTypeImpl <em>Math Function Call Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_FunctionCallTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_FunctionCallType()
   * @generated
   */
  int MATH_FUNCTION_CALL_TYPE = 12;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_CALL_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Function Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_FunctionArgumentTypeImpl <em>Math Function Argument Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_FunctionArgumentTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_FunctionArgumentType()
   * @generated
   */
  int MATH_FUNCTION_ARGUMENT_TYPE = 13;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_ARGUMENT_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_FUNCTION_ARGUMENT_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_FuncExprTypeImpl <em>Math Func Expr Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_FuncExprTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_FuncExprType()
   * @generated
   */
  int MATH_FUNC_EXPR_TYPE = 14;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_PiecewiseTypeImpl <em>Math Piecewise Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_PiecewiseTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_PiecewiseType()
   * @generated
   */
  int MATH_PIECEWISE_TYPE = 15;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_PieceTypeImpl <em>Math Piece Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_PieceTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_PieceType()
   * @generated
   */
  int MATH_PIECE_TYPE = 16;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_ConditionTypeImpl <em>Math Condition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_ConditionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_ConditionType()
   * @generated
   */
  int MATH_CONDITION_TYPE = 17;

  /**
   * The feature id for the '<em><b>Written Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONDITION_TYPE__WRITTEN_VERSION = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_CONDITION_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_LogicExprTypeImpl <em>Math Logic Expr Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_LogicExprTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_LogicExprType()
   * @generated
   */
  int MATH_LOGIC_EXPR_TYPE = 19;

  /**
   * The feature id for the '<em><b>Constant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__CONSTANT = 0;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__VAR = 1;

  /**
   * The feature id for the '<em><b>Uniop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__UNIOP = 2;

  /**
   * The feature id for the '<em><b>Binop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__BINOP = 3;

  /**
   * The feature id for the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL = 4;

  /**
   * The feature id for the '<em><b>String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE__STRING = 5;

  /**
   * The number of structural features of the '<em>Math Logic Expr Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_LogicBaseTypeImpl <em>Math Logic Base Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_LogicBaseTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_LogicBaseType()
   * @generated
   */
  int MATH_LOGIC_BASE_TYPE = 18;

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
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE__SCALAR = MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Math Logic Base Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BASE_TYPE_FEATURE_COUNT = MATH_LOGIC_EXPR_TYPE_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_StringTypeImpl <em>Math String Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_StringTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_StringType()
   * @generated
   */
  int MATH_STRING_TYPE = 20;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_STRING_TYPE__VALUE = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_STRING_TYPE__XMLNS = 1;

  /**
   * The number of structural features of the '<em>Math String Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_STRING_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_TRUEImpl <em>Math TRUE</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_TRUEImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_TRUE()
   * @generated
   */
  int MATH_TRUE = 21;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_FALSEImpl <em>Math FALSE</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_FALSEImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_FALSE()
   * @generated
   */
  int MATH_FALSE = 22;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_LogicBinopTypeImpl <em>Math Logic Binop Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_LogicBinopTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_LogicBinopType()
   * @generated
   */
  int MATH_LOGIC_BINOP_TYPE = 23;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_TYPE__OP = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_BINOP_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_LogicUniopTypeImpl <em>Math Logic Uniop Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_LogicUniopTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_LogicUniopType()
   * @generated
   */
  int MATH_LOGIC_UNIOP_TYPE = 24;

  /**
   * The feature id for the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_UNIOP_TYPE__OP = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_LOGIC_UNIOP_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_BinopNameImpl <em>Math Binop Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_BinopNameImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_BinopName()
   * @generated
   */
  int MATH_BINOP_NAME = 25;

  /**
   * The feature id for the '<em><b>Binop Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MATH_BINOP_NAME__BINOP_NAME = 0;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_UniopNameImpl <em>Math Uniop Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_UniopNameImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_UniopName()
   * @generated
   */
  int MATH_UNIOP_NAME = 26;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_LogicUniopNameImpl <em>Math Logic Uniop Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_LogicUniopNameImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_LogicUniopName()
   * @generated
   */
  int MATH_LOGIC_UNIOP_NAME = 27;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_LogicBinopNameImpl <em>Math Logic Binop Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_LogicBinopNameImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_LogicBinopName()
   * @generated
   */
  int MATH_LOGIC_BINOP_NAME = 28;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_ConstantNameImpl <em>Math Constant Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_ConstantNameImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_ConstantName()
   * @generated
   */
  int MATH_CONSTANT_NAME = 29;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_symbIdImpl <em>Math symb Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_symbIdImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_symbId()
   * @generated
   */
  int MATH_SYMB_ID = 30;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_idImpl <em>Math id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_idImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_id()
   * @generated
   */
  int MATH_ID = 31;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_blockImpl <em>Math block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_blockImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_block()
   * @generated
   */
  int MATH_BLOCK = 32;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_independentVarImpl <em>ct independent Var</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_independentVarImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_independentVar()
   * @generated
   */
  int CT_INDEPENDENT_VAR = 33;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.Math_EstimationOperationTypeImpl <em>Math Estimation Operation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.Math_EstimationOperationTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getMath_EstimationOperationType()
   * @generated
   */
  int MATH_ESTIMATION_OPERATION_TYPE = 34;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterTypeNameImpl <em>mdef Parameter Type Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ParameterTypeNameImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ParameterTypeName()
   * @generated
   */
  int MDEF_PARAMETER_TYPE_NAME = 35;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ModelDefinitionTypeImpl <em>mdef Model Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ModelDefinitionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ModelDefinitionType()
   * @generated
   */
  int MDEF_MODEL_DEFINITION_TYPE = 36;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_StructuralModelTypeImpl <em>mdef Structural Model Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_StructuralModelTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_StructuralModelType()
   * @generated
   */
  int MDEF_STRUCTURAL_MODEL_TYPE = 37;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_STRUCTURAL_MODEL_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ObservationModelTypeImpl <em>mdef Observation Model Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ObservationModelTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ObservationModelType()
   * @generated
   */
  int MDEF_OBSERVATION_MODEL_TYPE = 38;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_OBSERVATION_MODEL_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_OBSERVATION_MODEL_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_OBSERVATION_MODEL_TYPE__XMLNS = 2;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterModelTypeImpl <em>mdef Parameter Model Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ParameterModelTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ParameterModelType()
   * @generated
   */
  int MDEF_PARAMETER_MODEL_TYPE = 39;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_MODEL_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_MODEL_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_MODEL_TYPE__XMLNS = 2;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_CorrelationTypeImpl <em>mdef Correlation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_CorrelationTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_CorrelationType()
   * @generated
   */
  int MDEF_CORRELATION_TYPE = 40;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_TYPE__LEVEL_ID = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CORRELATION_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdefCovarianceTypeImpl <em>mdef Covariance Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdefCovarianceTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdefCovarianceType()
   * @generated
   */
  int MDEF_COVARIANCE_TYPE = 41;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_CorrelationCoefficientTypeImpl <em>mdef Correlation Coefficient Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_CorrelationCoefficientTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_CorrelationCoefficientType()
   * @generated
   */
  int MDEF_CORRELATION_COEFFICIENT_TYPE = 42;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_CovariateModelTypeImpl <em>mdef Covariate Model Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_CovariateModelTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_CovariateModelType()
   * @generated
   */
  int MDEF_COVARIATE_MODEL_TYPE = 43;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_MODEL_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_MODEL_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_MODEL_TYPE__XMLNS = 2;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterTypeImpl <em>mdef Parameter Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ParameterTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ParameterType()
   * @generated
   */
  int MDEF_PARAMETER_TYPE = 44;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__NAME = 0;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__SYMB_ID = 1;

  /**
   * The feature id for the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__TRANSFORMATION = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_PARAMETER_TYPE__XMLNS = 3;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_CovariateVariabilityTypeImpl <em>mdef Covariate Variability Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_CovariateVariabilityTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_CovariateVariabilityType()
   * @generated
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE = 45;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__NAME = 0;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID = 1;

  /**
   * The feature id for the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_COVARIATE_VARIABILITY_TYPE__XMLNS = 3;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_transformationImpl <em>mdef transformation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_transformationImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_transformation()
   * @generated
   */
  int MDEF_TRANSFORMATION = 46;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_CategoricalTypeImpl <em>mdef Categorical Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_CategoricalTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_CategoricalType()
   * @generated
   */
  int MDEF_CATEGORICAL_TYPE = 47;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ContinuousTypeImpl <em>mdef Continuous Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ContinuousTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ContinuousType()
   * @generated
   */
  int MDEF_CONTINUOUS_TYPE = 48;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Distribution</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_TYPE__DISTRIBUTION = 1;

  /**
   * The feature id for the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_TYPE__TRANSFORMATION = 2;

  /**
   * The number of structural features of the '<em>mdef Continuous Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CONTINUOUS_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_TransformationTypeImpl <em>mdef Transformation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_TransformationTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_TransformationType()
   * @generated
   */
  int MDEF_TRANSFORMATION_TYPE = 49;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_TRANSFORMATION_TYPE__EXPR = 0;

  /**
   * The number of structural features of the '<em>mdef Transformation Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_TRANSFORMATION_TYPE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_VariabilityLevelDefnTypeImpl <em>mdef Variability Level Defn Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_VariabilityLevelDefnTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_VariabilityLevelDefnType()
   * @generated
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE = 50;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE__XMLNS = 2;

  /**
   * The number of structural features of the '<em>mdef Variability Level Defn Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_VARIABILITY_LEVEL_DEFN_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ImportTypeImpl <em>mdef Import Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ImportTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ImportType()
   * @generated
   */
  int MDEF_IMPORT_TYPE = 51;

  /**
   * The feature id for the '<em><b>Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE__RESOURCE = 0;

  /**
   * The feature id for the '<em><b>Resource Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE__RESOURCE_TYPE = 1;

  /**
   * The feature id for the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE__SOURCE = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_TYPE__XMLNS = 3;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ImportLinkTypeImpl <em>mdef Import Link Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ImportLinkTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ImportLinkType()
   * @generated
   */
  int MDEF_IMPORT_LINK_TYPE = 52;

  /**
   * The feature id for the '<em><b>Symbol Ptr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_LINK_TYPE__SYMBOL_PTR = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_IMPORT_LINK_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ImportResourceTypeTypeImpl <em>mdef Import Resource Type Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ImportResourceTypeTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ImportResourceTypeType()
   * @generated
   */
  int MDEF_IMPORT_RESOURCE_TYPE_TYPE = 53;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_CovariateTypeImpl <em>mdef Covariate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_CovariateTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_CovariateType()
   * @generated
   */
  int MDEF_COVARIATE_TYPE = 54;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_FixedEffectTypeImpl <em>mdef Fixed Effect Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_FixedEffectTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_FixedEffectType()
   * @generated
   */
  int MDEF_FIXED_EFFECT_TYPE = 55;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_CategoryTypeImpl <em>mdef Category Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_CategoryTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_CategoryType()
   * @generated
   */
  int MDEF_CATEGORY_TYPE = 56;

  /**
   * The feature id for the '<em><b>Cat Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORY_TYPE__CAT_ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORY_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_CATEGORY_TYPE__XMLNS = 2;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_ProbabilityTypeImpl <em>mdef Probability Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_ProbabilityTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_ProbabilityType()
   * @generated
   */
  int MDEF_PROBABILITY_TYPE = 57;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_RandomEffectTypeImpl <em>mdef Random Effect Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_RandomEffectTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_RandomEffectType()
   * @generated
   */
  int MDEF_RANDOM_EFFECT_TYPE = 58;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_RANDOM_EFFECT_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_RANDOM_EFFECT_TYPE__SYMB_ID = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_RANDOM_EFFECT_TYPE__XMLNS = 3;

  /**
   * The feature id for the '<em><b>Distribution</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION = 4;

  /**
   * The number of structural features of the '<em>mdef Random Effect Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_RANDOM_EFFECT_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mdef_InitialConditionTypeImpl <em>mdef Initial Condition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mdef_InitialConditionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmdef_InitialConditionType()
   * @generated
   */
  int MDEF_INITIAL_CONDITION_TYPE = 59;

  /**
   * The feature id for the '<em><b>Symb ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_INITIAL_CONDITION_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_INITIAL_CONDITION_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MDEF_INITIAL_CONDITION_TYPE__XMLNS = 2;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_TrialDesignTypeImpl <em>design Trial Design Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_TrialDesignTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_TrialDesignType()
   * @generated
   */
  int DESIGN_TRIAL_DESIGN_TYPE = 60;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_GroupTypeImpl <em>design Group Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_GroupTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_GroupType()
   * @generated
   */
  int DESIGN_GROUP_TYPE = 61;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__XMLNS = 2;

  /**
   * The feature id for the '<em><b>Treatment Epoch Ref</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__TREATMENT_EPOCH_REF = 3;

  /**
   * The feature id for the '<em><b>Washout</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__WASHOUT = 4;

  /**
   * The feature id for the '<em><b>Individuals</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE__INDIVIDUALS = 5;

  /**
   * The number of structural features of the '<em>design Group Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_GROUP_TYPE_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_IdividualsTypeImpl <em>design Idividuals Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_IdividualsTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_IdividualsType()
   * @generated
   */
  int DESIGN_IDIVIDUALS_TYPE = 62;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE__LEVEL_ID = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_IDIVIDUALS_TYPE__XMLNS = 3;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_WashoutTypeImpl <em>design Washout Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_WashoutTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_WashoutType()
   * @generated
   */
  int DESIGN_WASHOUT_TYPE = 63;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_TreatmentEpohTypeImpl <em>design Treatment Epoh Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_TreatmentEpohTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_TreatmentEpohType()
   * @generated
   */
  int DESIGN_TREATMENT_EPOH_TYPE = 64;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_EPOH_TYPE__XMLNS = 2;

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
   * The feature id for the '<em><b>Occasion</b></em>' containment reference.
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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_OccasionTypeImpl <em>design Occasion Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_OccasionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_OccasionType()
   * @generated
   */
  int DESIGN_OCCASION_TYPE = 65;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__LEVEL_ID = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_OCCASION_TYPE__XMLNS = 3;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_BeginTypeImpl <em>design Begin Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_BeginTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_BeginType()
   * @generated
   */
  int DESIGN_BEGIN_TYPE = 66;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_EndTypeImpl <em>design End Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_EndTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_EndType()
   * @generated
   */
  int DESIGN_END_TYPE = 67;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_StartTypeImpl <em>design Start Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_StartTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_StartType()
   * @generated
   */
  int DESIGN_START_TYPE = 68;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_TreatmentRefTypeImpl <em>design Treatment Ref Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_TreatmentRefTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_TreatmentRefType()
   * @generated
   */
  int DESIGN_TREATMENT_REF_TYPE = 69;

  /**
   * The feature id for the '<em><b>Id Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_REF_TYPE__ID_REF = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_REF_TYPE__XMLNS = 1;

  /**
   * The number of structural features of the '<em>design Treatment Ref Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_REF_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_TreatmentTypeImpl <em>design Treatment Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_TreatmentTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_TreatmentType()
   * @generated
   */
  int DESIGN_TREATMENT_TYPE = 70;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TREATMENT_TYPE__SYMB_ID = 0;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_DosingRegimenTypeImpl <em>design Dosing Regimen Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_DosingRegimenTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_DosingRegimenType()
   * @generated
   */
  int DESIGN_DOSING_REGIMEN_TYPE = 71;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_InfusionTypeImpl <em>design Infusion Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_InfusionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_InfusionType()
   * @generated
   */
  int DESIGN_INFUSION_TYPE = 72;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_DurationTypeImpl <em>design Duration Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_DurationTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_DurationType()
   * @generated
   */
  int DESIGN_DURATION_TYPE = 73;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_BolusTypeImpl <em>design Bolus Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_BolusTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_BolusType()
   * @generated
   */
  int DESIGN_BOLUS_TYPE = 74;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_DosingTimesTypeImpl <em>design Dosing Times Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_DosingTimesTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_DosingTimesType()
   * @generated
   */
  int DESIGN_DOSING_TIMES_TYPE = 75;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_TIMES_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_SteadyStateTypeImpl <em>design Steady State Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_SteadyStateTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_SteadyStateType()
   * @generated
   */
  int DESIGN_STEADY_STATE_TYPE = 76;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_DosingStartTypeImpl <em>design Dosing Start Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_DosingStartTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_DosingStartType()
   * @generated
   */
  int DESIGN_DOSING_START_TYPE = 77;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_DosingPeriodTypeImpl <em>design Dosing Period Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_DosingPeriodTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_DosingPeriodType()
   * @generated
   */
  int DESIGN_DOSING_PERIOD_TYPE = 78;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_DoseAmountTypeImpl <em>design Dose Amount Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_DoseAmountTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_DoseAmountType()
   * @generated
   */
  int DESIGN_DOSE_AMOUNT_TYPE = 79;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_DosingVariableTypeImpl <em>design Dosing Variable Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_DosingVariableTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_DosingVariableType()
   * @generated
   */
  int DESIGN_DOSING_VARIABLE_TYPE = 80;

  /**
   * The feature id for the '<em><b>Dose Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE__DOSE_VAR = 0;

  /**
   * The feature id for the '<em><b>Target Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE__TARGET_VAR = 1;

  /**
   * The feature id for the '<em><b>Amount</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE__AMOUNT = 2;

  /**
   * The number of structural features of the '<em>design Dosing Variable Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSING_VARIABLE_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_AmountTypeImpl <em>design Amount Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_AmountTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_AmountType()
   * @generated
   */
  int DESIGN_AMOUNT_TYPE = 81;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_TargetVarTypeImpl <em>design Target Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_TargetVarTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_TargetVarType()
   * @generated
   */
  int DESIGN_TARGET_VAR_TYPE = 82;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TARGET_VAR_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TARGET_VAR_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TARGET_VAR_TYPE__XMLNS = 2;

  /**
   * The number of structural features of the '<em>design Target Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_TARGET_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.design_DoseVarTypeImpl <em>design Dose Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.design_DoseVarTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getdesign_DoseVarType()
   * @generated
   */
  int DESIGN_DOSE_VAR_TYPE = 83;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_VAR_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_VAR_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_VAR_TYPE__XMLNS = 2;

  /**
   * The number of structural features of the '<em>design Dose Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_DOSE_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_ModelingStepsImpl <em>msteps Modeling Steps</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_ModelingStepsImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_ModelingSteps()
   * @generated
   */
  int MSTEPS_MODELING_STEPS = 84;

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
   * The feature id for the '<em><b>Estimation Step</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS__ESTIMATION_STEP = 2;

  /**
   * The feature id for the '<em><b>Simulation Step</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS__SIMULATION_STEP = 3;

  /**
   * The feature id for the '<em><b>Step Dependencies</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES = 4;

  /**
   * The number of structural features of the '<em>msteps Modeling Steps</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MODELING_STEPS_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_StepDependenciesTypeImpl <em>msteps Step Dependencies Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_StepDependenciesTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_StepDependenciesType()
   * @generated
   */
  int MSTEPS_STEP_DEPENDENCIES_TYPE = 85;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_StepTypeImpl <em>msteps Step Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_StepTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_StepType()
   * @generated
   */
  int MSTEPS_STEP_TYPE = 86;

  /**
   * The feature id for the '<em><b>Id Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_TYPE__ID_REF = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_STEP_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_DependantStepTypeImpl <em>msteps Dependant Step Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_DependantStepTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_DependantStepType()
   * @generated
   */
  int MSTEPS_DEPENDANT_STEP_TYPE = 87;

  /**
   * The feature id for the '<em><b>Id Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_DEPENDANT_STEP_TYPE__ID_REF = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_DEPENDANT_STEP_TYPE__XMLNS = 1;

  /**
   * The number of structural features of the '<em>msteps Dependant Step Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_DEPENDANT_STEP_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_EstimationStepImpl <em>msteps Estimation Step</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_EstimationStepImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_EstimationStep()
   * @generated
   */
  int MSTEPS_ESTIMATION_STEP = 88;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP__ID = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ESTIMATION_STEP__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_ParametersToEstimateTypeImpl <em>msteps Parameters To Estimate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_ParametersToEstimateTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_ParametersToEstimateType()
   * @generated
   */
  int MSTEPS_PARAMETERS_TO_ESTIMATE_TYPE = 89;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mSteps_VariableEstimateTypeImpl <em>mSteps Variable Estimate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mSteps_VariableEstimateTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmSteps_VariableEstimateType()
   * @generated
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE = 90;

  /**
   * The feature id for the '<em><b>Symb ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Fixed</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED = 1;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_VARIABLE_ESTIMATE_TYPE__XMLNS = 3;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mSteps_fixedImpl <em>mSteps fixed</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mSteps_fixedImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmSteps_fixed()
   * @generated
   */
  int MSTEPS_FIXED = 91;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mSteps_UpperBoundTypeImpl <em>mSteps Upper Bound Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mSteps_UpperBoundTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmSteps_UpperBoundType()
   * @generated
   */
  int MSTEPS_UPPER_BOUND_TYPE = 92;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mSteps_LowerBoundTypeImpl <em>mSteps Lower Bound Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mSteps_LowerBoundTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmSteps_LowerBoundType()
   * @generated
   */
  int MSTEPS_LOWER_BOUND_TYPE = 93;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.mSteps_InitialEstimateTypeImpl <em>mSteps Initial Estimate Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.mSteps_InitialEstimateTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmSteps_InitialEstimateType()
   * @generated
   */
  int MSTEPS_INITIAL_ESTIMATE_TYPE = 94;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_ObjectiveDataSetTypeImpl <em>msteps Objective Data Set Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_ObjectiveDataSetTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_ObjectiveDataSetType()
   * @generated
   */
  int MSTEPS_OBJECTIVE_DATA_SET_TYPE = 95;

  /**
   * The feature id for the '<em><b>Data Set Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl <em>msteps Mapping Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_MappingType()
   * @generated
   */
  int MSTEPS_MAPPING_TYPE = 96;

  /**
   * The feature id for the '<em><b>Column Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__COLUMN_NAME = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__XMLNS = 1;

  /**
   * The feature id for the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__VAR = 2;

  /**
   * The feature id for the '<em><b>Use Variability Level</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL = 3;

  /**
   * The feature id for the '<em><b>Use Variability Node</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE = 4;

  /**
   * The feature id for the '<em><b>Dose Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__DOSE_VAR = 5;

  /**
   * The feature id for the '<em><b>Target Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__TARGET_VAR = 6;

  /**
   * The feature id for the '<em><b>Assign</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__ASSIGN = 7;

  /**
   * The feature id for the '<em><b>Output</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__OUTPUT = 8;

  /**
   * The feature id for the '<em><b>Restriction</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE__RESTRICTION = 9;

  /**
   * The number of structural features of the '<em>msteps Mapping Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_MAPPING_TYPE_FEATURE_COUNT = 10;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_OutputTypeImpl <em>msteps Output Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_OutputTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_OutputType()
   * @generated
   */
  int MSTEPS_OUTPUT_TYPE = 97;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_AssignTypeImpl <em>msteps Assign Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_AssignTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_AssignType()
   * @generated
   */
  int MSTEPS_ASSIGN_TYPE = 98;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ASSIGN_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ASSIGN_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_ASSIGN_TYPE__XMLNS = 2;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_TargetVarTypeImpl <em>msteps Target Var Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_TargetVarTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_TargetVarType()
   * @generated
   */
  int MSTEPS_TARGET_VAR_TYPE = 99;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TARGET_VAR_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TARGET_VAR_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TARGET_VAR_TYPE__XMLNS = 2;

  /**
   * The number of structural features of the '<em>msteps Target Var Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_TARGET_VAR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_UseVariabilityLevelImpl <em>msteps Use Variability Level</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_UseVariabilityLevelImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_UseVariabilityLevel()
   * @generated
   */
  int MSTEPS_USE_VARIABILITY_LEVEL = 100;

  /**
   * The feature id for the '<em><b>Level Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_LEVEL__LEVEL_ID = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_LEVEL__XMLNS = 1;

  /**
   * The number of structural features of the '<em>msteps Use Variability Level</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_LEVEL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_UseVariabilityNodeImpl <em>msteps Use Variability Node</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_UseVariabilityNodeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_UseVariabilityNode()
   * @generated
   */
  int MSTEPS_USE_VARIABILITY_NODE = 101;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_NODE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_NODE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_NODE__XMLNS = 2;

  /**
   * The number of structural features of the '<em>msteps Use Variability Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_USE_VARIABILITY_NODE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_SimulationStepImpl <em>msteps Simulation Step</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_SimulationStepImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_SimulationStep()
   * @generated
   */
  int MSTEPS_SIMULATION_STEP = 102;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP__ID = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIMULATION_STEP__XMLNS = 1;

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
   * The feature id for the '<em><b>Observations</b></em>' containment reference.
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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_SimDataSetTypeImpl <em>msteps Sim Data Set Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_SimDataSetTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_SimDataSetType()
   * @generated
   */
  int MSTEPS_SIM_DATA_SET_TYPE = 103;

  /**
   * The feature id for the '<em><b>Data Set Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIM_DATA_SET_TYPE__DATA_SET_REF = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_SIM_DATA_SET_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_ObservationsTypeImpl <em>msteps Observations Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_ObservationsTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_ObservationsType()
   * @generated
   */
  int MSTEPS_OBSERVATIONS_TYPE = 104;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_WriteToTypeImpl <em>msteps Write To Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_WriteToTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_WriteToType()
   * @generated
   */
  int MSTEPS_WRITE_TO_TYPE = 105;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_TimepointsTypeImpl <em>msteps Timepoints Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_TimepointsTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_TimepointsType()
   * @generated
   */
  int MSTEPS_TIMEPOINTS_TYPE = 106;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_InitialValueTypeImpl <em>msteps Initial Value Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_InitialValueTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_InitialValueType()
   * @generated
   */
  int MSTEPS_INITIAL_VALUE_TYPE = 107;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_VALUE_TYPE__XMLNS = 1;

  /**
   * The feature id for the '<em><b>Content</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_VALUE_TYPE__CONTENT = 2;

  /**
   * The number of structural features of the '<em>msteps Initial Value Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MSTEPS_INITIAL_VALUE_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.msteps_EstimationOperationTypeImpl <em>msteps Estimation Operation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.msteps_EstimationOperationTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getmsteps_EstimationOperationType()
   * @generated
   */
  int MSTEPS_ESTIMATION_OPERATION_TYPE = 108;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_DistributionTypeImpl <em>uncert Distribution Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_DistributionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_DistributionType()
   * @generated
   */
  int UNCERT_DISTRIBUTION_TYPE = 109;

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
   * The feature id for the '<em><b>Normal</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE__NORMAL = 2;

  /**
   * The feature id for the '<em><b>PDF</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE__PDF = 3;

  /**
   * The feature id for the '<em><b>Poison</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE__POISON = 4;

  /**
   * The feature id for the '<em><b>Student</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE__STUDENT = 5;

  /**
   * The feature id for the '<em><b>Uniform</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE__UNIFORM = 6;

  /**
   * The number of structural features of the '<em>uncert Distribution Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_DISTRIBUTION_TYPE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_NormalImpl <em>uncert Normal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_NormalImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_Normal()
   * @generated
   */
  int UNCERT_NORMAL = 110;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_CoefficientOfVarianceImpl <em>uncert Coefficient Of Variance</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_CoefficientOfVarianceImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_CoefficientOfVariance()
   * @generated
   */
  int UNCERT_COEFFICIENT_OF_VARIANCE = 111;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_StdDevImpl <em>uncert Std Dev</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_StdDevImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_StdDev()
   * @generated
   */
  int UNCERT_STD_DEV = 112;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_STD_DEV__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_DistributionParamTypeImpl <em>uncert Distribution Param Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_DistributionParamTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_DistributionParamType()
   * @generated
   */
  int UNCERT_DISTRIBUTION_PARAM_TYPE = 113;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_MeanParamTypeImpl <em>uncert Mean Param Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_MeanParamTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_MeanParamType()
   * @generated
   */
  int UNCERT_MEAN_PARAM_TYPE = 114;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_MeanImpl <em>uncert Mean</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_MeanImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_Mean()
   * @generated
   */
  int UNCERT_MEAN = 115;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_VarianceImpl <em>uncert Variance</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_VarianceImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_Variance()
   * @generated
   */
  int UNCERT_VARIANCE = 116;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_PDFImpl <em>uncert PDF</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_PDFImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_PDF()
   * @generated
   */
  int UNCERT_PDF = 117;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_ParameterDefinitionTypeImpl <em>uncert Parameter Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_ParameterDefinitionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_ParameterDefinitionType()
   * @generated
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE = 118;

  /**
   * The feature id for the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE__ID = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE__NAME = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE__XMLNS = 2;

  /**
   * The number of structural features of the '<em>uncert Parameter Definition Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNCERT_PARAMETER_DEFINITION_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_PoisonImpl <em>uncert Poison</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_PoisonImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_Poison()
   * @generated
   */
  int UNCERT_POISON = 119;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_RateImpl <em>uncert Rate</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_RateImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_Rate()
   * @generated
   */
  int UNCERT_RATE = 120;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_Student_tImpl <em>uncert Student t</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_Student_tImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_Student_t()
   * @generated
   */
  int UNCERT_STUDENT_T = 121;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncertDegreesOfFreedomImpl <em>uncert Degrees Of Freedom</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncertDegreesOfFreedomImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncertDegreesOfFreedom()
   * @generated
   */
  int UNCERT_DEGREES_OF_FREEDOM = 122;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_ScaleImpl <em>uncert Scale</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_ScaleImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_Scale()
   * @generated
   */
  int UNCERT_SCALE = 123;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_LocationImpl <em>uncert Location</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_LocationImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_Location()
   * @generated
   */
  int UNCERT_LOCATION = 124;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.uncert_UniformImpl <em>uncert Uniform</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.uncert_UniformImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getuncert_Uniform()
   * @generated
   */
  int UNCERT_UNIFORM = 125;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_symbIdImpl <em>ct symb Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_symbIdImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_symbId()
   * @generated
   */
  int CT_SYMB_ID = 126;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_idImpl <em>ct id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_idImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_id()
   * @generated
   */
  int CT_ID = 127;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_symbolTypeImpl <em>ct symbol Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_symbolTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_symbolType()
   * @generated
   */
  int CT_SYMBOL_TYPE = 128;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_blockImpl <em>ct block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_blockImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_block()
   * @generated
   */
  int CT_BLOCK = 129;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_writtenVersionImpl <em>ct written Version</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_writtenVersionImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_writtenVersion()
   * @generated
   */
  int CT_WRITTEN_VERSION = 130;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_Decimal_ValueImpl <em>ct Decimal Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_Decimal_ValueImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_Decimal_Value()
   * @generated
   */
  int CT_DECIMAL_VALUE = 131;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_String_ValueImpl <em>ct String Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_String_ValueImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_String_Value()
   * @generated
   */
  int CT_STRING_VALUE = 132;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_Int_ValueImpl <em>ct Int Value</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_Int_ValueImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_Int_Value()
   * @generated
   */
  int CT_INT_VALUE = 133;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INT_VALUE__VALUE = 0;

  /**
   * The number of structural features of the '<em>ct Int Value</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INT_VALUE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_catIdImpl <em>ct cat Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_catIdImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_catId()
   * @generated
   */
  int CT_CAT_ID = 134;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_ReplicatesTypeImpl <em>ct Replicates Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_ReplicatesTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_ReplicatesType()
   * @generated
   */
  int CT_REPLICATES_TYPE = 135;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_REPLICATES_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_REPLICATES_TYPE__XMLNS = 1;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_AnnotationTypeImpl <em>ct Annotation Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_AnnotationTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_AnnotationType()
   * @generated
   */
  int CT_ANNOTATION_TYPE = 136;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ANNOTATION_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute.
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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl <em>ct Rhs</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_RhsImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_Rhs()
   * @generated
   */
  int CT_RHS = 137;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_DataSetTypeImpl <em>ct Data Set Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_DataSetTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_DataSetType()
   * @generated
   */
  int CT_DATA_SET_TYPE = 138;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_DefinitionTypeImpl <em>ct Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_DefinitionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_DefinitionType()
   * @generated
   */
  int CT_DEFINITION_TYPE = 139;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_DefinitionRhsTypeImpl <em>ct Definition Rhs Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_DefinitionRhsTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_DefinitionRhsType()
   * @generated
   */
  int CT_DEFINITION_RHS_TYPE = 140;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_ColumnTypeImpl <em>ct Column Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_ColumnTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_ColumnType()
   * @generated
   */
  int CT_COLUMN_TYPE = 141;

  /**
   * The feature id for the '<em><b>Column Num</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_COLUMN_TYPE__COLUMN_NUM = 0;

  /**
   * The feature id for the '<em><b>Column Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_COLUMN_TYPE__COLUMN_VAR = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_COLUMN_TYPE__XMLNS = 2;

  /**
   * The number of structural features of the '<em>ct Column Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_COLUMN_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_ExternalSourceTypeImpl <em>ct External Source Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_ExternalSourceTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_ExternalSourceType()
   * @generated
   */
  int CT_EXTERNAL_SOURCE_TYPE = 142;

  /**
   * The feature id for the '<em><b>Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__FORMAT = 0;

  /**
   * The feature id for the '<em><b>Header Line</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE = 1;

  /**
   * The feature id for the '<em><b>Ignore Line Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__IGNORE_LINE_SYMBOL = 2;

  /**
   * The feature id for the '<em><b>Url</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__URL = 3;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE__XMLNS = 4;

  /**
   * The number of structural features of the '<em>ct External Source Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_EXTERNAL_SOURCE_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_RowTypeImpl <em>ct Row Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_RowTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_RowType()
   * @generated
   */
  int CT_ROW_TYPE = 143;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_RowTypeContentImpl <em>ct Row Type Content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_RowTypeContentImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_RowTypeContent()
   * @generated
   */
  int CT_ROW_TYPE_CONTENT = 144;

  /**
   * The feature id for the '<em><b>Cell</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_ROW_TYPE_CONTENT__CELL = 0;

  /**
   * The feature id for the '<em><b>Null</b></em>' attribute.
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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_CellTypeImpl <em>ct Cell Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_CellTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_CellType()
   * @generated
   */
  int CT_CELL_TYPE = 145;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_CELL_TYPE__VALUE = 0;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_CELL_TYPE__XMLNS = 1;

  /**
   * The number of structural features of the '<em>ct Cell Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_CELL_TYPE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_InternalSourceTypeImpl <em>ct Internal Source Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_InternalSourceTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_InternalSourceType()
   * @generated
   */
  int CT_INTERNAL_SOURCE_TYPE = 146;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INTERNAL_SOURCE_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INTERNAL_SOURCE_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INTERNAL_SOURCE_TYPE__XMLNS = 2;

  /**
   * The number of structural features of the '<em>ct Internal Source Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_INTERNAL_SOURCE_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_ScalarRhsImpl <em>ct Scalar Rhs</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_ScalarRhsImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_ScalarRhs()
   * @generated
   */
  int CT_SCALAR_RHS = 147;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_levelIdImpl <em>ct level Id</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_levelIdImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_levelId()
   * @generated
   */
  int CT_LEVEL_ID = 148;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_NameImpl <em>ct Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_NameImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_Name()
   * @generated
   */
  int CT_NAME = 149;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_NAME__NAME = 0;

  /**
   * The number of structural features of the '<em>ct Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_NAME_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_VectorTypeImpl <em>ct Vector Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_VectorTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_VectorType()
   * @generated
   */
  int CT_VECTOR_TYPE = 150;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VECTOR_TYPE__XMLNS = 0;

  /**
   * The feature id for the '<em><b>Sequence</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VECTOR_TYPE__SEQUENCE = 1;

  /**
   * The feature id for the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VECTOR_TYPE__SCALAR = 2;

  /**
   * The number of structural features of the '<em>ct Vector Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VECTOR_TYPE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_SequenceTypeImpl <em>ct Sequence Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_SequenceTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_SequenceType()
   * @generated
   */
  int CT_SEQUENCE_TYPE = 151;

  /**
   * The feature id for the '<em><b>Begin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__BEGIN = 0;

  /**
   * The feature id for the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__END = 1;

  /**
   * The feature id for the '<em><b>Repetition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__REPETITION = 2;

  /**
   * The feature id for the '<em><b>Stepwise</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__STEPWISE = 3;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE__XMLNS = 4;

  /**
   * The number of structural features of the '<em>ct Sequence Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_SEQUENCE_TYPE_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_SymbolTypeTypeImpl <em>ct Symbol Type Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_SymbolTypeTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_SymbolTypeType()
   * @generated
   */
  int CT_SYMBOL_TYPE_TYPE = 152;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_VariableDefinitionTypeImpl <em>ct Variable Definition Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_VariableDefinitionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_VariableDefinitionType()
   * @generated
   */
  int CT_VARIABLE_DEFINITION_TYPE = 153;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Symbol Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE = 1;

  /**
   * The feature id for the '<em><b>Independent Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR = 2;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_DEFINITION_TYPE__XMLNS = 3;

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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_VariableAssignmentTypeImpl <em>ct Variable Assignment Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_VariableAssignmentTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_VariableAssignmentType()
   * @generated
   */
  int CT_VARIABLE_ASSIGNMENT_TYPE = 154;

  /**
   * The feature id for the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_ASSIGNMENT_TYPE__SYMB_ID = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_ASSIGNMENT_TYPE__BLOCK = 1;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_ASSIGNMENT_TYPE__XMLNS = 2;

  /**
   * The feature id for the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_ASSIGNMENT_TYPE__EXPR = 3;

  /**
   * The number of structural features of the '<em>ct Variable Assignment Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_VARIABLE_ASSIGNMENT_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_RestrictionTypeImpl <em>ct Restriction Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_RestrictionTypeImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_RestrictionType()
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
   * The meta object id for the '{@link org.ddmore.pml.pharmaML.impl.ct_xmlnsImpl <em>ct xmlns</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.pml.pharmaML.impl.ct_xmlnsImpl
   * @see org.ddmore.pml.pharmaML.impl.PharmaMLPackageImpl#getct_xmlns()
   * @generated
   */
  int CT_XMLNS = 156;

  /**
   * The feature id for the '<em><b>Xmlns</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_XMLNS__XMLNS = 0;

  /**
   * The number of structural features of the '<em>ct xmlns</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CT_XMLNS_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Mml_PharmaML <em>Mml Pharma ML</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Pharma ML</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML
   * @generated
   */
  EClass getMml_PharmaML();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML#getXmlns()
   * @see #getMml_PharmaML()
   * @generated
   */
  EReference getMml_PharmaML_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML#getName()
   * @see #getMml_PharmaML()
   * @generated
   */
  EReference getMml_PharmaML_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getIndependentVar <em>Independent Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Independent Var</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML#getIndependentVar()
   * @see #getMml_PharmaML()
   * @generated
   */
  EReference getMml_PharmaML_IndependentVar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Written Version</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML#getWrittenVersion()
   * @see #getMml_PharmaML()
   * @generated
   */
  EReference getMml_PharmaML_WrittenVersion();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getSymbolDefinition <em>Symbol Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Symbol Definition</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML#getSymbolDefinition()
   * @see #getMml_PharmaML()
   * @generated
   */
  EReference getMml_PharmaML_SymbolDefinition();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getStructuralModel <em>Structural Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Structural Model</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML#getStructuralModel()
   * @see #getMml_PharmaML()
   * @generated
   */
  EReference getMml_PharmaML_StructuralModel();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getModelDefinition <em>Model Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Model Definition</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML#getModelDefinition()
   * @see #getMml_PharmaML()
   * @generated
   */
  EReference getMml_PharmaML_ModelDefinition();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getDesign <em>Design</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Design</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML#getDesign()
   * @see #getMml_PharmaML()
   * @generated
   */
  EReference getMml_PharmaML_Design();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getModelingSteps <em>Modeling Steps</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Modeling Steps</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_PharmaML#getModelingSteps()
   * @see #getMml_PharmaML()
   * @generated
   */
  EReference getMml_PharmaML_ModelingSteps();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType <em>Mml Symbol Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Symbol Definition Type</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType
   * @generated
   */
  EClass getMml_SymbolDefinitionType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getSymbId()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getSymbolType <em>Symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol Type</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getSymbolType()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_SymbolType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getName()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getXmlns()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getDescription()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_Description();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getFunctionDefinition <em>Function Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Definition</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType#getFunctionDefinition()
   * @see #getMml_SymbolDefinitionType()
   * @generated
   */
  EReference getMml_SymbolDefinitionType_FunctionDefinition();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType <em>Mml Function Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Function Definition Type</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType
   * @generated
   */
  EClass getMml_FunctionDefinitionType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getXmlns()
   * @see #getMml_FunctionDefinitionType()
   * @generated
   */
  EReference getMml_FunctionDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getFunctionArguments <em>Function Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Function Arguments</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getFunctionArguments()
   * @see #getMml_FunctionDefinitionType()
   * @generated
   */
  EReference getMml_FunctionDefinitionType_FunctionArguments();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Definition</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getDefinition()
   * @see #getMml_FunctionDefinitionType()
   * @generated
   */
  EReference getMml_FunctionDefinitionType_Definition();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType <em>Mml Func Parameter Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mml Func Parameter Definition Type</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType
   * @generated
   */
  EClass getMml_FuncParameterDefinitionType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType#getSumbId <em>Sumb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sumb Id</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType#getSumbId()
   * @see #getMml_FuncParameterDefinitionType()
   * @generated
   */
  EReference getMml_FuncParameterDefinitionType_SumbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType#getSymbolType <em>Symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol Type</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType#getSymbolType()
   * @see #getMml_FuncParameterDefinitionType()
   * @generated
   */
  EReference getMml_FuncParameterDefinitionType_SymbolType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType#getXmlns()
   * @see #getMml_FuncParameterDefinitionType()
   * @generated
   */
  EReference getMml_FuncParameterDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType#getDecsription <em>Decsription</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Decsription</em>'.
   * @see org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType#getDecsription()
   * @see #getMml_FuncParameterDefinitionType()
   * @generated
   */
  EReference getMml_FuncParameterDefinitionType_Decsription();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_EquationType <em>Math Equation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Equation Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationType
   * @generated
   */
  EClass getMath_EquationType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_EquationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationType#getXmlns()
   * @see #getMath_EquationType()
   * @generated
   */
  EReference getMath_EquationType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_EquationType#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Written Version</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationType#getWrittenVersion()
   * @see #getMath_EquationType()
   * @generated
   */
  EReference getMath_EquationType_WrittenVersion();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_EquationType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationType#getExpr()
   * @see #getMath_EquationType()
   * @generated
   */
  EReference getMath_EquationType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_EquationTypeContent <em>Math Equation Type Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Equation Type Content</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationTypeContent
   * @generated
   */
  EClass getMath_EquationTypeContent();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_EquationTypeContent#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationTypeContent#getScalar()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_EquationTypeContent#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationTypeContent#getVar()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_EquationTypeContent#getBinop <em>Binop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binop</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationTypeContent#getBinop()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Binop();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_EquationTypeContent#getUniop <em>Uniop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Uniop</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationTypeContent#getUniop()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Uniop();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_EquationTypeContent#getPiecewise <em>Piecewise</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Piecewise</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EquationTypeContent#getPiecewise()
   * @see #getMath_EquationTypeContent()
   * @generated
   */
  EReference getMath_EquationTypeContent_Piecewise();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_ScalarType <em>Math Scalar Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Scalar Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ScalarType
   * @generated
   */
  EClass getMath_ScalarType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ScalarType#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ScalarType#getValue()
   * @see #getMath_ScalarType()
   * @generated
   */
  EReference getMath_ScalarType_Value();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_ScalarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ScalarType#getXmlns()
   * @see #getMath_ScalarType()
   * @generated
   */
  EReference getMath_ScalarType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_VarType <em>Math Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Var Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_VarType
   * @generated
   */
  EClass getMath_VarType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_VarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.Math_VarType#getBlock()
   * @see #getMath_VarType()
   * @generated
   */
  EReference getMath_VarType_Block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_VarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.Math_VarType#getSymbId()
   * @see #getMath_VarType()
   * @generated
   */
  EReference getMath_VarType_SymbId();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_VarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_VarType#getXmlns()
   * @see #getMath_VarType()
   * @generated
   */
  EReference getMath_VarType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_BinopType <em>Math Binop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Binop Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopType
   * @generated
   */
  EClass getMath_BinopType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_BinopType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopType#getOp()
   * @see #getMath_BinopType()
   * @generated
   */
  EReference getMath_BinopType_Op();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_BinopType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopType#getXmlns()
   * @see #getMath_BinopType()
   * @generated
   */
  EReference getMath_BinopType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_BinopType#getLeftExpr <em>Left Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left Expr</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopType#getLeftExpr()
   * @see #getMath_BinopType()
   * @generated
   */
  EReference getMath_BinopType_LeftExpr();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_BinopType#getRightExpr <em>Right Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right Expr</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopType#getRightExpr()
   * @see #getMath_BinopType()
   * @generated
   */
  EReference getMath_BinopType_RightExpr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_UniopType <em>Math Uniop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Uniop Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopType
   * @generated
   */
  EClass getMath_UniopType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_UniopType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopType#getOp()
   * @see #getMath_UniopType()
   * @generated
   */
  EReference getMath_UniopType_Op();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_UniopType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopType#getXmlns()
   * @see #getMath_UniopType()
   * @generated
   */
  EReference getMath_UniopType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_UniopType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopType#getExpr()
   * @see #getMath_UniopType()
   * @generated
   */
  EReference getMath_UniopType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_ExprType <em>Math Expr Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Expr Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ExprType
   * @generated
   */
  EClass getMath_ExprType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ExprType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ExprType#getScalar()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ExprType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ExprType#getVar()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ExprType#getUniop <em>Uniop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Uniop</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ExprType#getUniop()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Uniop();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ExprType#getBinop <em>Binop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binop</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ExprType#getBinop()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Binop();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ExprType#getConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constant</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ExprType#getConstant()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_Constant();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ExprType#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ExprType#getFunctionCall()
   * @see #getMath_ExprType()
   * @generated
   */
  EReference getMath_ExprType_FunctionCall();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_ConstantType <em>Math Constant Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Constant Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConstantType
   * @generated
   */
  EClass getMath_ConstantType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ConstantType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConstantType#getOp()
   * @see #getMath_ConstantType()
   * @generated
   */
  EReference getMath_ConstantType_Op();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_ConstantType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConstantType#getXmlns()
   * @see #getMath_ConstantType()
   * @generated
   */
  EReference getMath_ConstantType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_FunctionCallType <em>Math Function Call Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Function Call Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FunctionCallType
   * @generated
   */
  EClass getMath_FunctionCallType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_FunctionCallType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FunctionCallType#getXmlns()
   * @see #getMath_FunctionCallType()
   * @generated
   */
  EReference getMath_FunctionCallType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_FunctionCallType#getFunctionName <em>Function Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Name</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FunctionCallType#getFunctionName()
   * @see #getMath_FunctionCallType()
   * @generated
   */
  EReference getMath_FunctionCallType_FunctionName();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_FunctionCallType#getFunctionArguments <em>Function Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Function Arguments</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FunctionCallType#getFunctionArguments()
   * @see #getMath_FunctionCallType()
   * @generated
   */
  EReference getMath_FunctionCallType_FunctionArguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_FunctionArgumentType <em>Math Function Argument Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Function Argument Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FunctionArgumentType
   * @generated
   */
  EClass getMath_FunctionArgumentType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_FunctionArgumentType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FunctionArgumentType#getSymbId()
   * @see #getMath_FunctionArgumentType()
   * @generated
   */
  EReference getMath_FunctionArgumentType_SymbId();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_FunctionArgumentType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FunctionArgumentType#getXmlns()
   * @see #getMath_FunctionArgumentType()
   * @generated
   */
  EReference getMath_FunctionArgumentType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_FunctionArgumentType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FunctionArgumentType#getExpr()
   * @see #getMath_FunctionArgumentType()
   * @generated
   */
  EReference getMath_FunctionArgumentType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_FuncExprType <em>Math Func Expr Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Func Expr Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FuncExprType
   * @generated
   */
  EClass getMath_FuncExprType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_FuncExprType#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FuncExprType#getEquation()
   * @see #getMath_FuncExprType()
   * @generated
   */
  EReference getMath_FuncExprType_Equation();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_FuncExprType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FuncExprType#getScalar()
   * @see #getMath_FuncExprType()
   * @generated
   */
  EReference getMath_FuncExprType_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_FuncExprType#getConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constant</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FuncExprType#getConstant()
   * @see #getMath_FuncExprType()
   * @generated
   */
  EReference getMath_FuncExprType_Constant();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_FuncExprType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FuncExprType#getVar()
   * @see #getMath_FuncExprType()
   * @generated
   */
  EReference getMath_FuncExprType_Var();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_PiecewiseType <em>Math Piecewise Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Piecewise Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_PiecewiseType
   * @generated
   */
  EClass getMath_PiecewiseType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_PiecewiseType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_PiecewiseType#getXmlns()
   * @see #getMath_PiecewiseType()
   * @generated
   */
  EReference getMath_PiecewiseType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_PiecewiseType#getPiecewise <em>Piecewise</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Piecewise</em>'.
   * @see org.ddmore.pml.pharmaML.Math_PiecewiseType#getPiecewise()
   * @see #getMath_PiecewiseType()
   * @generated
   */
  EReference getMath_PiecewiseType_Piecewise();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_PieceType <em>Math Piece Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Piece Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_PieceType
   * @generated
   */
  EClass getMath_PieceType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_PieceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_PieceType#getXmlns()
   * @see #getMath_PieceType()
   * @generated
   */
  EReference getMath_PieceType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_PieceType#getContent <em>Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Content</em>'.
   * @see org.ddmore.pml.pharmaML.Math_PieceType#getContent()
   * @see #getMath_PieceType()
   * @generated
   */
  EReference getMath_PieceType_Content();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_PieceType#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see org.ddmore.pml.pharmaML.Math_PieceType#getCondition()
   * @see #getMath_PieceType()
   * @generated
   */
  EReference getMath_PieceType_Condition();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_ConditionType <em>Math Condition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Condition Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConditionType
   * @generated
   */
  EClass getMath_ConditionType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ConditionType#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Written Version</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConditionType#getWrittenVersion()
   * @see #getMath_ConditionType()
   * @generated
   */
  EReference getMath_ConditionType_WrittenVersion();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_ConditionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConditionType#getXmlns()
   * @see #getMath_ConditionType()
   * @generated
   */
  EReference getMath_ConditionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_ConditionType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConditionType#getExpr()
   * @see #getMath_ConditionType()
   * @generated
   */
  EReference getMath_ConditionType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_LogicBaseType <em>Math Logic Base Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Base Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBaseType
   * @generated
   */
  EClass getMath_LogicBaseType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicBaseType#getLogicBinopType <em>Logic Binop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Logic Binop Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBaseType#getLogicBinopType()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_LogicBinopType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicBaseType#getLogicUniopType <em>Logic Uniop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Logic Uniop Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBaseType#getLogicUniopType()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_LogicUniopType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicBaseType#getTrue <em>True</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>True</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBaseType#getTrue()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_True();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicBaseType#getFalse <em>False</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>False</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBaseType#getFalse()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_False();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicBaseType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBaseType#getScalar()
   * @see #getMath_LogicBaseType()
   * @generated
   */
  EReference getMath_LogicBaseType_Scalar();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_LogicExprType <em>Math Logic Expr Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Expr Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicExprType
   * @generated
   */
  EClass getMath_LogicExprType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicExprType#getConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constant</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicExprType#getConstant()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_Constant();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicExprType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicExprType#getVar()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicExprType#getUniop <em>Uniop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Uniop</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicExprType#getUniop()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_Uniop();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicExprType#getBinop <em>Binop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Binop</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicExprType#getBinop()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_Binop();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicExprType#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicExprType#getFunctionCall()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_FunctionCall();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicExprType#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>String</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicExprType#getString()
   * @see #getMath_LogicExprType()
   * @generated
   */
  EReference getMath_LogicExprType_String();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_StringType <em>Math String Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math String Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_StringType
   * @generated
   */
  EClass getMath_StringType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_StringType#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.ddmore.pml.pharmaML.Math_StringType#getValue()
   * @see #getMath_StringType()
   * @generated
   */
  EReference getMath_StringType_Value();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_StringType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_StringType#getXmlns()
   * @see #getMath_StringType()
   * @generated
   */
  EReference getMath_StringType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_TRUE <em>Math TRUE</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math TRUE</em>'.
   * @see org.ddmore.pml.pharmaML.Math_TRUE
   * @generated
   */
  EClass getMath_TRUE();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_TRUE#getTrue <em>True</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>True</em>'.
   * @see org.ddmore.pml.pharmaML.Math_TRUE#getTrue()
   * @see #getMath_TRUE()
   * @generated
   */
  EAttribute getMath_TRUE_True();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_FALSE <em>Math FALSE</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math FALSE</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FALSE
   * @generated
   */
  EClass getMath_FALSE();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_FALSE#getFalse <em>False</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>False</em>'.
   * @see org.ddmore.pml.pharmaML.Math_FALSE#getFalse()
   * @see #getMath_FALSE()
   * @generated
   */
  EAttribute getMath_FALSE_False();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_LogicBinopType <em>Math Logic Binop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Binop Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopType
   * @generated
   */
  EClass getMath_LogicBinopType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicBinopType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopType#getOp()
   * @see #getMath_LogicBinopType()
   * @generated
   */
  EReference getMath_LogicBinopType_Op();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_LogicBinopType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopType#getXmlns()
   * @see #getMath_LogicBinopType()
   * @generated
   */
  EReference getMath_LogicBinopType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicBinopType#getLeftExpr <em>Left Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Left Expr</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopType#getLeftExpr()
   * @see #getMath_LogicBinopType()
   * @generated
   */
  EReference getMath_LogicBinopType_LeftExpr();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicBinopType#getRightExpr <em>Right Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Right Expr</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopType#getRightExpr()
   * @see #getMath_LogicBinopType()
   * @generated
   */
  EReference getMath_LogicBinopType_RightExpr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_LogicUniopType <em>Math Logic Uniop Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Uniop Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicUniopType
   * @generated
   */
  EClass getMath_LogicUniopType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicUniopType#getOp <em>Op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicUniopType#getOp()
   * @see #getMath_LogicUniopType()
   * @generated
   */
  EReference getMath_LogicUniopType_Op();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.Math_LogicUniopType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicUniopType#getXmlns()
   * @see #getMath_LogicUniopType()
   * @generated
   */
  EReference getMath_LogicUniopType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.Math_LogicUniopType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicUniopType#getExpr()
   * @see #getMath_LogicUniopType()
   * @generated
   */
  EReference getMath_LogicUniopType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_BinopName <em>Math Binop Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Binop Name</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopName
   * @generated
   */
  EClass getMath_BinopName();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_BinopName#getBinopName <em>Binop Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Binop Name</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopName#getBinopName()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_BinopName();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_BinopName#getMinus <em>Minus</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Minus</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopName#getMinus()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Minus();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_BinopName#getTimes <em>Times</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Times</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopName#getTimes()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Times();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_BinopName#getDivide <em>Divide</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Divide</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopName#getDivide()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Divide();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_BinopName#getPower <em>Power</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Power</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopName#getPower()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Power();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_BinopName#getLog <em>Log</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Log</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopName#getLog()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Log();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_BinopName#getRoot <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Root</em>'.
   * @see org.ddmore.pml.pharmaML.Math_BinopName#getRoot()
   * @see #getMath_BinopName()
   * @generated
   */
  EAttribute getMath_BinopName_Root();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_UniopName <em>Math Uniop Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Uniop Name</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName
   * @generated
   */
  EClass getMath_UniopName();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getExp <em>Exp</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Exp</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getExp()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Exp();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getLn <em>Ln</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ln</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getLn()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Ln();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getMinus <em>Minus</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Minus</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getMinus()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Minus();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getFactorial <em>Factorial</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Factorial</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getFactorial()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Factorial();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getSin <em>Sin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sin</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getSin()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Sin();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getCos <em>Cos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cos</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getCos()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Cos();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getTan <em>Tan</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tan</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getTan()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Tan();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getSec <em>Sec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sec</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getSec()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Sec();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getCsc <em>Csc</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Csc</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getCsc()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Csc();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getCot <em>Cot</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cot</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getCot()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Cot();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getSinh <em>Sinh</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sinh</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getSinh()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Sinh();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getCsch <em>Csch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Csch</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getCsch()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Csch();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getCoth <em>Coth</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Coth</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getCoth()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Coth();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArcsin <em>Arcsin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arcsin</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArcsin()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arcsin();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArccos <em>Arccos</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccos</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArccos()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccos();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArctan <em>Arctan</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arctan</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArctan()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arctan();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArcsec <em>Arcsec</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arcsec</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArcsec()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arcsec();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArccsc <em>Arccsc</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccsc</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArccsc()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccsc();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArccot <em>Arccot</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccot</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArccot()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccot();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArcsinh <em>Arcsinh</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arcsinh</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArcsinh()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arcsinh();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArccosh <em>Arccosh</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccosh</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArccosh()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccosh();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArctanh <em>Arctanh</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arctanh</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArctanh()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arctanh();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArcsech <em>Arcsech</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arcsech</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArcsech()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arcsech();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArccsch <em>Arccsch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccsch</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArccsch()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccsch();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getArccoth <em>Arccoth</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Arccoth</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getArccoth()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Arccoth();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getFloor <em>Floor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Floor</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getFloor()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Floor();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getAbs <em>Abs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Abs</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getAbs()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Abs();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getCeiling <em>Ceiling</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ceiling</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getCeiling()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Ceiling();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_UniopName#getLogit <em>Logit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Logit</em>'.
   * @see org.ddmore.pml.pharmaML.Math_UniopName#getLogit()
   * @see #getMath_UniopName()
   * @generated
   */
  EAttribute getMath_UniopName_Logit();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_LogicUniopName <em>Math Logic Uniop Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Uniop Name</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicUniopName
   * @generated
   */
  EClass getMath_LogicUniopName();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicUniopName#getIsDefined <em>Is Defined</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Is Defined</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicUniopName#getIsDefined()
   * @see #getMath_LogicUniopName()
   * @generated
   */
  EAttribute getMath_LogicUniopName_IsDefined();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicUniopName#getNot <em>Not</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Not</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicUniopName#getNot()
   * @see #getMath_LogicUniopName()
   * @generated
   */
  EAttribute getMath_LogicUniopName_Not();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName <em>Math Logic Binop Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Logic Binop Name</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName
   * @generated
   */
  EClass getMath_LogicBinopName();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName#getLt <em>Lt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lt</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName#getLt()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Lt();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName#getLeq <em>Leq</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Leq</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName#getLeq()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Leq();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName#getGt <em>Gt</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Gt</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName#getGt()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Gt();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName#getGeq <em>Geq</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Geq</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName#getGeq()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Geq();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName#getEq <em>Eq</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Eq</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName#getEq()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Eq();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName#getNeq <em>Neq</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Neq</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName#getNeq()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Neq();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName#getAnd <em>And</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>And</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName#getAnd()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_And();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName#getOr <em>Or</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Or</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName#getOr()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Or();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_LogicBinopName#getXor <em>Xor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Xor</em>'.
   * @see org.ddmore.pml.pharmaML.Math_LogicBinopName#getXor()
   * @see #getMath_LogicBinopName()
   * @generated
   */
  EAttribute getMath_LogicBinopName_Xor();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_ConstantName <em>Math Constant Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Constant Name</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConstantName
   * @generated
   */
  EClass getMath_ConstantName();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_ConstantName#getNotanumber <em>Notanumber</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Notanumber</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConstantName#getNotanumber()
   * @see #getMath_ConstantName()
   * @generated
   */
  EAttribute getMath_ConstantName_Notanumber();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_ConstantName#getPi <em>Pi</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pi</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConstantName#getPi()
   * @see #getMath_ConstantName()
   * @generated
   */
  EAttribute getMath_ConstantName_Pi();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_ConstantName#getExponentiale <em>Exponentiale</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Exponentiale</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConstantName#getExponentiale()
   * @see #getMath_ConstantName()
   * @generated
   */
  EAttribute getMath_ConstantName_Exponentiale();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_ConstantName#getInfinity <em>Infinity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Infinity</em>'.
   * @see org.ddmore.pml.pharmaML.Math_ConstantName#getInfinity()
   * @see #getMath_ConstantName()
   * @generated
   */
  EAttribute getMath_ConstantName_Infinity();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_symbId <em>Math symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.Math_symbId
   * @generated
   */
  EClass getMath_symbId();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_symbId#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.Math_symbId#getSymbId()
   * @see #getMath_symbId()
   * @generated
   */
  EAttribute getMath_symbId_SymbId();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_id <em>Math id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math id</em>'.
   * @see org.ddmore.pml.pharmaML.Math_id
   * @generated
   */
  EClass getMath_id();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_id#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.Math_id#getId()
   * @see #getMath_id()
   * @generated
   */
  EAttribute getMath_id_Id();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_block <em>Math block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math block</em>'.
   * @see org.ddmore.pml.pharmaML.Math_block
   * @generated
   */
  EClass getMath_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.Math_block#getBlock()
   * @see #getMath_block()
   * @generated
   */
  EAttribute getMath_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_independentVar <em>ct independent Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct independent Var</em>'.
   * @see org.ddmore.pml.pharmaML.ct_independentVar
   * @generated
   */
  EClass getct_independentVar();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_independentVar#getIndependentVar <em>Independent Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Independent Var</em>'.
   * @see org.ddmore.pml.pharmaML.ct_independentVar#getIndependentVar()
   * @see #getct_independentVar()
   * @generated
   */
  EAttribute getct_independentVar_IndependentVar();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.Math_EstimationOperationType <em>Math Estimation Operation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Math Estimation Operation Type</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EstimationOperationType
   * @generated
   */
  EClass getMath_EstimationOperationType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_EstimationOperationType#getEstPop <em>Est Pop</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Est Pop</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EstimationOperationType#getEstPop()
   * @see #getMath_EstimationOperationType()
   * @generated
   */
  EAttribute getMath_EstimationOperationType_EstPop();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_EstimationOperationType#getEstFIM <em>Est FIM</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Est FIM</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EstimationOperationType#getEstFIM()
   * @see #getMath_EstimationOperationType()
   * @generated
   */
  EAttribute getMath_EstimationOperationType_EstFIM();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.Math_EstimationOperationType#getEstIndiv <em>Est Indiv</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Est Indiv</em>'.
   * @see org.ddmore.pml.pharmaML.Math_EstimationOperationType#getEstIndiv()
   * @see #getMath_EstimationOperationType()
   * @generated
   */
  EAttribute getMath_EstimationOperationType_EstIndiv();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ParameterTypeName <em>mdef Parameter Type Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Parameter Type Name</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterTypeName
   * @generated
   */
  EClass getmdef_ParameterTypeName();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.mdef_ParameterTypeName#getLog <em>Log</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Log</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterTypeName#getLog()
   * @see #getmdef_ParameterTypeName()
   * @generated
   */
  EAttribute getmdef_ParameterTypeName_Log();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.mdef_ParameterTypeName#getLogit <em>Logit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Logit</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterTypeName#getLogit()
   * @see #getmdef_ParameterTypeName()
   * @generated
   */
  EAttribute getmdef_ParameterTypeName_Logit();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.mdef_ParameterTypeName#getNone <em>None</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>None</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterTypeName#getNone()
   * @see #getmdef_ParameterTypeName()
   * @generated
   */
  EAttribute getmdef_ParameterTypeName_None();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ModelDefinitionType <em>mdef Model Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Model Definition Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ModelDefinitionType
   * @generated
   */
  EClass getmdef_ModelDefinitionType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getXmlns()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getVariabilityLevel <em>Variability Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variability Level</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getVariabilityLevel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_VariabilityLevel();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getCovariateModel <em>Covariate Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Covariate Model</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getCovariateModel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_CovariateModel();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getParameterModel <em>Parameter Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameter Model</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getParameterModel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_ParameterModel();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getStructuraModel <em>Structura Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Structura Model</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getStructuraModel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_StructuraModel();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getObservationModel <em>Observation Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Observation Model</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ModelDefinitionType#getObservationModel()
   * @see #getmdef_ModelDefinitionType()
   * @generated
   */
  EReference getmdef_ModelDefinitionType_ObservationModel();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_StructuralModelType <em>mdef Structural Model Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Structural Model Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_StructuralModelType
   * @generated
   */
  EClass getmdef_StructuralModelType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_StructuralModelType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_StructuralModelType#getId()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Id();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_StructuralModelType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_StructuralModelType#getXmlns()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_StructuralModelType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_StructuralModelType#getDescription()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Description();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_StructuralModelType#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_StructuralModelType#getParameter()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Parameter();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_StructuralModelType#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_StructuralModelType#getVariable()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Variable();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_StructuralModelType#getImport <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Import</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_StructuralModelType#getImport()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_Import();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_StructuralModelType#getInitiaCondition <em>Initia Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initia Condition</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_StructuralModelType#getInitiaCondition()
   * @see #getmdef_StructuralModelType()
   * @generated
   */
  EReference getmdef_StructuralModelType_InitiaCondition();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ObservationModelType <em>mdef Observation Model Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Observation Model Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ObservationModelType
   * @generated
   */
  EClass getmdef_ObservationModelType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ObservationModelType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ObservationModelType#getId()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Id();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ObservationModelType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ObservationModelType#getName()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ObservationModelType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ObservationModelType#getXmlns()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ObservationModelType#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ObservationModelType#getParameter()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Parameter();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ObservationModelType#getContinuous <em>Continuous</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Continuous</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ObservationModelType#getContinuous()
   * @see #getmdef_ObservationModelType()
   * @generated
   */
  EReference getmdef_ObservationModelType_Continuous();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ParameterModelType <em>mdef Parameter Model Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Parameter Model Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterModelType
   * @generated
   */
  EClass getmdef_ParameterModelType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ParameterModelType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterModelType#getId()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Id();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ParameterModelType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterModelType#getName()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ParameterModelType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterModelType#getXmlns()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ParameterModelType#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterModelType#getParameter()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Parameter();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ParameterModelType#getCorrelation <em>Correlation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Correlation</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterModelType#getCorrelation()
   * @see #getmdef_ParameterModelType()
   * @generated
   */
  EReference getmdef_ParameterModelType_Correlation();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_CorrelationType <em>mdef Correlation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Correlation Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationType
   * @generated
   */
  EClass getmdef_CorrelationType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CorrelationType#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Level Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationType#getLevelId()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_LevelId();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CorrelationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationType#getXmlns()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CorrelationType#getParamVar1 <em>Param Var1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Param Var1</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationType#getParamVar1()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_ParamVar1();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CorrelationType#getParamVar2 <em>Param Var2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Param Var2</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationType#getParamVar2()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_ParamVar2();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CorrelationType#getCorrelationCoefficient <em>Correlation Coefficient</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Correlation Coefficient</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationType#getCorrelationCoefficient()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_CorrelationCoefficient();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CorrelationType#getCovariance <em>Covariance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Covariance</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationType#getCovariance()
   * @see #getmdef_CorrelationType()
   * @generated
   */
  EReference getmdef_CorrelationType_Covariance();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdefCovarianceType <em>mdef Covariance Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Covariance Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdefCovarianceType
   * @generated
   */
  EClass getmdefCovarianceType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdefCovarianceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdefCovarianceType#getXmlns()
   * @see #getmdefCovarianceType()
   * @generated
   */
  EReference getmdefCovarianceType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdefCovarianceType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.mdefCovarianceType#getExpr()
   * @see #getmdefCovarianceType()
   * @generated
   */
  EReference getmdefCovarianceType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType <em>mdef Correlation Coefficient Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Correlation Coefficient Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType
   * @generated
   */
  EClass getmdef_CorrelationCoefficientType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType#getXmlns()
   * @see #getmdef_CorrelationCoefficientType()
   * @generated
   */
  EReference getmdef_CorrelationCoefficientType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType#getExpr()
   * @see #getmdef_CorrelationCoefficientType()
   * @generated
   */
  EReference getmdef_CorrelationCoefficientType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_CovariateModelType <em>mdef Covariate Model Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Covariate Model Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateModelType
   * @generated
   */
  EClass getmdef_CovariateModelType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CovariateModelType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateModelType#getId()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Id();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CovariateModelType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateModelType#getName()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CovariateModelType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateModelType#getXmlns()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CovariateModelType#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateModelType#getParameter()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Parameter();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CovariateModelType#getCovariate <em>Covariate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Covariate</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateModelType#getCovariate()
   * @see #getmdef_CovariateModelType()
   * @generated
   */
  EReference getmdef_CovariateModelType_Covariate();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ParameterType <em>mdef Parameter Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Parameter Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType
   * @generated
   */
  EClass getmdef_ParameterType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType#getName()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType#getSymbId()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getTransformation <em>Transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Transformation</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType#getTransformation()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Transformation();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType#getXmlns()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType#getVar()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType#getScalar()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType#getEquation()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Equation();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getRandomEffect <em>Random Effect</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Random Effect</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType#getRandomEffect()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_RandomEffect();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getCovariate <em>Covariate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Covariate</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ParameterType#getCovariate()
   * @see #getmdef_ParameterType()
   * @generated
   */
  EReference getmdef_ParameterType_Covariate();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType <em>mdef Covariate Variability Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Covariate Variability Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType
   * @generated
   */
  EClass getmdef_CovariateVariabilityType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getName()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getSymbId()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getTransformation <em>Transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Transformation</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getTransformation()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Transformation();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getXmlns()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getContinuous <em>Continuous</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Continuous</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getContinuous()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Continuous();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getCategorical <em>Categorical</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Categorical</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getCategorical()
   * @see #getmdef_CovariateVariabilityType()
   * @generated
   */
  EReference getmdef_CovariateVariabilityType_Categorical();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_transformation <em>mdef transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef transformation</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_transformation
   * @generated
   */
  EClass getmdef_transformation();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_transformation#getTransformation <em>Transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Transformation</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_transformation#getTransformation()
   * @see #getmdef_transformation()
   * @generated
   */
  EReference getmdef_transformation_Transformation();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_CategoricalType <em>mdef Categorical Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Categorical Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CategoricalType
   * @generated
   */
  EClass getmdef_CategoricalType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CategoricalType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CategoricalType#getXmlns()
   * @see #getmdef_CategoricalType()
   * @generated
   */
  EReference getmdef_CategoricalType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CategoricalType#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Category</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CategoricalType#getCategory()
   * @see #getmdef_CategoricalType()
   * @generated
   */
  EReference getmdef_CategoricalType_Category();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ContinuousType <em>mdef Continuous Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Continuous Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ContinuousType
   * @generated
   */
  EClass getmdef_ContinuousType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ContinuousType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ContinuousType#getXmlns()
   * @see #getmdef_ContinuousType()
   * @generated
   */
  EReference getmdef_ContinuousType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ContinuousType#getDistribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Distribution</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ContinuousType#getDistribution()
   * @see #getmdef_ContinuousType()
   * @generated
   */
  EReference getmdef_ContinuousType_Distribution();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ContinuousType#getTransformation <em>Transformation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Transformation</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ContinuousType#getTransformation()
   * @see #getmdef_ContinuousType()
   * @generated
   */
  EReference getmdef_ContinuousType_Transformation();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_TransformationType <em>mdef Transformation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Transformation Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_TransformationType
   * @generated
   */
  EClass getmdef_TransformationType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_TransformationType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_TransformationType#getExpr()
   * @see #getmdef_TransformationType()
   * @generated
   */
  EReference getmdef_TransformationType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType <em>mdef Variability Level Defn Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Variability Level Defn Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType
   * @generated
   */
  EClass getmdef_VariabilityLevelDefnType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType#getId()
   * @see #getmdef_VariabilityLevelDefnType()
   * @generated
   */
  EReference getmdef_VariabilityLevelDefnType_Id();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType#getName()
   * @see #getmdef_VariabilityLevelDefnType()
   * @generated
   */
  EReference getmdef_VariabilityLevelDefnType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType#getXmlns()
   * @see #getmdef_VariabilityLevelDefnType()
   * @generated
   */
  EReference getmdef_VariabilityLevelDefnType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ImportType <em>mdef Import Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Import Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportType
   * @generated
   */
  EClass getmdef_ImportType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.mdef_ImportType#getResource <em>Resource</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Resource</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportType#getResource()
   * @see #getmdef_ImportType()
   * @generated
   */
  EAttribute getmdef_ImportType_Resource();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ImportType#getResourceType <em>Resource Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Resource Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportType#getResourceType()
   * @see #getmdef_ImportType()
   * @generated
   */
  EReference getmdef_ImportType_ResourceType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.mdef_ImportType#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportType#getSource()
   * @see #getmdef_ImportType()
   * @generated
   */
  EAttribute getmdef_ImportType_Source();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ImportType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportType#getXmlns()
   * @see #getmdef_ImportType()
   * @generated
   */
  EReference getmdef_ImportType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ImportType#getLink <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Link</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportType#getLink()
   * @see #getmdef_ImportType()
   * @generated
   */
  EReference getmdef_ImportType_Link();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ImportLinkType <em>mdef Import Link Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Import Link Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportLinkType
   * @generated
   */
  EClass getmdef_ImportLinkType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.mdef_ImportLinkType#getSymbolPtr <em>Symbol Ptr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Symbol Ptr</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportLinkType#getSymbolPtr()
   * @see #getmdef_ImportLinkType()
   * @generated
   */
  EAttribute getmdef_ImportLinkType_SymbolPtr();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ImportLinkType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportLinkType#getXmlns()
   * @see #getmdef_ImportLinkType()
   * @generated
   */
  EReference getmdef_ImportLinkType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ImportLinkType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportLinkType#getVar()
   * @see #getmdef_ImportLinkType()
   * @generated
   */
  EReference getmdef_ImportLinkType_Var();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType <em>mdef Import Resource Type Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Import Resource Type Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType
   * @generated
   */
  EClass getmdef_ImportResourceTypeType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType#getSbml <em>Sbml</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sbml</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType#getSbml()
   * @see #getmdef_ImportResourceTypeType()
   * @generated
   */
  EAttribute getmdef_ImportResourceTypeType_Sbml();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType#getPharmml <em>Pharmml</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pharmml</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType#getPharmml()
   * @see #getmdef_ImportResourceTypeType()
   * @generated
   */
  EAttribute getmdef_ImportResourceTypeType_Pharmml();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_CovariateType <em>mdef Covariate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Covariate Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateType
   * @generated
   */
  EClass getmdef_CovariateType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CovariateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateType#getXmlns()
   * @see #getmdef_CovariateType()
   * @generated
   */
  EReference getmdef_CovariateType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CovariateType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateType#getVar()
   * @see #getmdef_CovariateType()
   * @generated
   */
  EReference getmdef_CovariateType_Var();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CovariateType#getFixedEffect <em>Fixed Effect</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Fixed Effect</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CovariateType#getFixedEffect()
   * @see #getmdef_CovariateType()
   * @generated
   */
  EReference getmdef_CovariateType_FixedEffect();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_FixedEffectType <em>mdef Fixed Effect Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Fixed Effect Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_FixedEffectType
   * @generated
   */
  EClass getmdef_FixedEffectType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_FixedEffectType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_FixedEffectType#getXmlns()
   * @see #getmdef_FixedEffectType()
   * @generated
   */
  EReference getmdef_FixedEffectType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_FixedEffectType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_FixedEffectType#getVar()
   * @see #getmdef_FixedEffectType()
   * @generated
   */
  EReference getmdef_FixedEffectType_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_FixedEffectType#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Category</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_FixedEffectType#getCategory()
   * @see #getmdef_FixedEffectType()
   * @generated
   */
  EReference getmdef_FixedEffectType_Category();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_CategoryType <em>mdef Category Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Category Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CategoryType
   * @generated
   */
  EClass getmdef_CategoryType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getCatId <em>Cat Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cat Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CategoryType#getCatId()
   * @see #getmdef_CategoryType()
   * @generated
   */
  EReference getmdef_CategoryType_CatId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CategoryType#getName()
   * @see #getmdef_CategoryType()
   * @generated
   */
  EReference getmdef_CategoryType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CategoryType#getXmlns()
   * @see #getmdef_CategoryType()
   * @generated
   */
  EReference getmdef_CategoryType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getProbability <em>Probability</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Probability</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_CategoryType#getProbability()
   * @see #getmdef_CategoryType()
   * @generated
   */
  EReference getmdef_CategoryType_Probability();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_ProbabilityType <em>mdef Probability Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Probability Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ProbabilityType
   * @generated
   */
  EClass getmdef_ProbabilityType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_ProbabilityType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ProbabilityType#getXmlns()
   * @see #getmdef_ProbabilityType()
   * @generated
   */
  EReference getmdef_ProbabilityType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_ProbabilityType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_ProbabilityType#getExpr()
   * @see #getmdef_ProbabilityType()
   * @generated
   */
  EReference getmdef_ProbabilityType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType <em>mdef Random Effect Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Random Effect Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_RandomEffectType
   * @generated
   */
  EClass getmdef_RandomEffectType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Level Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_RandomEffectType#getLevelId()
   * @see #getmdef_RandomEffectType()
   * @generated
   */
  EReference getmdef_RandomEffectType_LevelId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_RandomEffectType#getName()
   * @see #getmdef_RandomEffectType()
   * @generated
   */
  EReference getmdef_RandomEffectType_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_RandomEffectType#getSymbId()
   * @see #getmdef_RandomEffectType()
   * @generated
   */
  EReference getmdef_RandomEffectType_SymbId();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_RandomEffectType#getXmlns()
   * @see #getmdef_RandomEffectType()
   * @generated
   */
  EReference getmdef_RandomEffectType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getDistribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Distribution</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_RandomEffectType#getDistribution()
   * @see #getmdef_RandomEffectType()
   * @generated
   */
  EReference getmdef_RandomEffectType_Distribution();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mdef_InitialConditionType <em>mdef Initial Condition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mdef Initial Condition Type</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_InitialConditionType
   * @generated
   */
  EClass getmdef_InitialConditionType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_InitialConditionType#getSymbID <em>Symb ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb ID</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_InitialConditionType#getSymbID()
   * @see #getmdef_InitialConditionType()
   * @generated
   */
  EReference getmdef_InitialConditionType_SymbID();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_InitialConditionType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_InitialConditionType#getBlock()
   * @see #getmdef_InitialConditionType()
   * @generated
   */
  EReference getmdef_InitialConditionType_Block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mdef_InitialConditionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_InitialConditionType#getXmlns()
   * @see #getmdef_InitialConditionType()
   * @generated
   */
  EReference getmdef_InitialConditionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mdef_InitialConditionType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.mdef_InitialConditionType#getExpr()
   * @see #getmdef_InitialConditionType()
   * @generated
   */
  EReference getmdef_InitialConditionType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_TrialDesignType <em>design Trial Design Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Trial Design Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_TrialDesignType
   * @generated
   */
  EClass getdesign_TrialDesignType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TrialDesignType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_TrialDesignType#getXmlns()
   * @see #getdesign_TrialDesignType()
   * @generated
   */
  EReference getdesign_TrialDesignType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TrialDesignType#getTreatment <em>Treatment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Treatment</em>'.
   * @see org.ddmore.pml.pharmaML.design_TrialDesignType#getTreatment()
   * @see #getdesign_TrialDesignType()
   * @generated
   */
  EReference getdesign_TrialDesignType_Treatment();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TrialDesignType#getTreatmentEpoch <em>Treatment Epoch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Treatment Epoch</em>'.
   * @see org.ddmore.pml.pharmaML.design_TrialDesignType#getTreatmentEpoch()
   * @see #getdesign_TrialDesignType()
   * @generated
   */
  EReference getdesign_TrialDesignType_TreatmentEpoch();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TrialDesignType#getGroup <em>Group</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Group</em>'.
   * @see org.ddmore.pml.pharmaML.design_TrialDesignType#getGroup()
   * @see #getdesign_TrialDesignType()
   * @generated
   */
  EReference getdesign_TrialDesignType_Group();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_GroupType <em>design Group Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Group Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_GroupType
   * @generated
   */
  EClass getdesign_GroupType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_GroupType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.design_GroupType#getId()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Id();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_GroupType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.design_GroupType#getName()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_GroupType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_GroupType#getXmlns()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_GroupType#getTreatmentEpochRef <em>Treatment Epoch Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Treatment Epoch Ref</em>'.
   * @see org.ddmore.pml.pharmaML.design_GroupType#getTreatmentEpochRef()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_TreatmentEpochRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_GroupType#getWashout <em>Washout</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Washout</em>'.
   * @see org.ddmore.pml.pharmaML.design_GroupType#getWashout()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Washout();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_GroupType#getIndividuals <em>Individuals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Individuals</em>'.
   * @see org.ddmore.pml.pharmaML.design_GroupType#getIndividuals()
   * @see #getdesign_GroupType()
   * @generated
   */
  EReference getdesign_GroupType_Individuals();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_IdividualsType <em>design Idividuals Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Idividuals Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_IdividualsType
   * @generated
   */
  EClass getdesign_IdividualsType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_IdividualsType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.design_IdividualsType#getSymbId()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_IdividualsType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.design_IdividualsType#getName()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_IdividualsType#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Level Id</em>'.
   * @see org.ddmore.pml.pharmaML.design_IdividualsType#getLevelId()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_LevelId();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_IdividualsType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_IdividualsType#getXmlns()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_IdividualsType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_IdividualsType#getExpr()
   * @see #getdesign_IdividualsType()
   * @generated
   */
  EReference getdesign_IdividualsType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_WashoutType <em>design Washout Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Washout Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_WashoutType
   * @generated
   */
  EClass getdesign_WashoutType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_WashoutType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_WashoutType#getXmlns()
   * @see #getdesign_WashoutType()
   * @generated
   */
  EReference getdesign_WashoutType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType <em>design Treatment Epoh Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Treatment Epoh Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentEpohType
   * @generated
   */
  EClass getdesign_TreatmentEpohType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentEpohType#getSymbId()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentEpohType#getName()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentEpohType#getXmlns()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getTreatmentRef <em>Treatment Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Treatment Ref</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentEpohType#getTreatmentRef()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_TreatmentRef();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getStart <em>Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Start</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentEpohType#getStart()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_Start();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>End</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentEpohType#getEnd()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_End();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getOccasion <em>Occasion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Occasion</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentEpohType#getOccasion()
   * @see #getdesign_TreatmentEpohType()
   * @generated
   */
  EReference getdesign_TreatmentEpohType_Occasion();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_OccasionType <em>design Occasion Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Occasion Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_OccasionType
   * @generated
   */
  EClass getdesign_OccasionType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_OccasionType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.design_OccasionType#getSymbId()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_OccasionType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.design_OccasionType#getName()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_OccasionType#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Level Id</em>'.
   * @see org.ddmore.pml.pharmaML.design_OccasionType#getLevelId()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_LevelId();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_OccasionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_OccasionType#getXmlns()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_OccasionType#getBegin <em>Begin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Begin</em>'.
   * @see org.ddmore.pml.pharmaML.design_OccasionType#getBegin()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_Begin();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_OccasionType#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>End</em>'.
   * @see org.ddmore.pml.pharmaML.design_OccasionType#getEnd()
   * @see #getdesign_OccasionType()
   * @generated
   */
  EReference getdesign_OccasionType_End();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_BeginType <em>design Begin Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Begin Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_BeginType
   * @generated
   */
  EClass getdesign_BeginType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_BeginType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_BeginType#getXmlns()
   * @see #getdesign_BeginType()
   * @generated
   */
  EReference getdesign_BeginType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_BeginType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_BeginType#getExpr()
   * @see #getdesign_BeginType()
   * @generated
   */
  EReference getdesign_BeginType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_EndType <em>design End Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design End Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_EndType
   * @generated
   */
  EClass getdesign_EndType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_EndType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_EndType#getXmlns()
   * @see #getdesign_EndType()
   * @generated
   */
  EReference getdesign_EndType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_EndType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_EndType#getExpr()
   * @see #getdesign_EndType()
   * @generated
   */
  EReference getdesign_EndType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_StartType <em>design Start Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Start Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_StartType
   * @generated
   */
  EClass getdesign_StartType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_StartType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_StartType#getXmlns()
   * @see #getdesign_StartType()
   * @generated
   */
  EReference getdesign_StartType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_StartType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_StartType#getExpr()
   * @see #getdesign_StartType()
   * @generated
   */
  EReference getdesign_StartType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_TreatmentRefType <em>design Treatment Ref Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Treatment Ref Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentRefType
   * @generated
   */
  EClass getdesign_TreatmentRefType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.design_TreatmentRefType#getIdRef <em>Id Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id Ref</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentRefType#getIdRef()
   * @see #getdesign_TreatmentRefType()
   * @generated
   */
  EAttribute getdesign_TreatmentRefType_IdRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TreatmentRefType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentRefType#getXmlns()
   * @see #getdesign_TreatmentRefType()
   * @generated
   */
  EReference getdesign_TreatmentRefType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_TreatmentType <em>design Treatment Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Treatment Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentType
   * @generated
   */
  EClass getdesign_TreatmentType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_TreatmentType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentType#getSymbId()
   * @see #getdesign_TreatmentType()
   * @generated
   */
  EReference getdesign_TreatmentType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_TreatmentType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentType#getName()
   * @see #getdesign_TreatmentType()
   * @generated
   */
  EReference getdesign_TreatmentType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TreatmentType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentType#getXmlns()
   * @see #getdesign_TreatmentType()
   * @generated
   */
  EReference getdesign_TreatmentType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TreatmentType#getDosingRegimen <em>Dosing Regimen</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Dosing Regimen</em>'.
   * @see org.ddmore.pml.pharmaML.design_TreatmentType#getDosingRegimen()
   * @see #getdesign_TreatmentType()
   * @generated
   */
  EReference getdesign_TreatmentType_DosingRegimen();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_DosingRegimenType <em>design Dosing Regimen Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Regimen Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingRegimenType
   * @generated
   */
  EClass getdesign_DosingRegimenType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_DosingRegimenType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingRegimenType#getXmlns()
   * @see #getdesign_DosingRegimenType()
   * @generated
   */
  EReference getdesign_DosingRegimenType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DosingRegimenType#getBolus <em>Bolus</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Bolus</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingRegimenType#getBolus()
   * @see #getdesign_DosingRegimenType()
   * @generated
   */
  EReference getdesign_DosingRegimenType_Bolus();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DosingRegimenType#getInfusion <em>Infusion</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Infusion</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingRegimenType#getInfusion()
   * @see #getdesign_DosingRegimenType()
   * @generated
   */
  EReference getdesign_DosingRegimenType_Infusion();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_InfusionType <em>design Infusion Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Infusion Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_InfusionType
   * @generated
   */
  EClass getdesign_InfusionType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_InfusionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_InfusionType#getXmlns()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_InfusionType#getDoseAmount <em>Dose Amount</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dose Amount</em>'.
   * @see org.ddmore.pml.pharmaML.design_InfusionType#getDoseAmount()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_DoseAmount();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_InfusionType#getSteadyState <em>Steady State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Steady State</em>'.
   * @see org.ddmore.pml.pharmaML.design_InfusionType#getSteadyState()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_SteadyState();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_InfusionType#getDosingTimes <em>Dosing Times</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dosing Times</em>'.
   * @see org.ddmore.pml.pharmaML.design_InfusionType#getDosingTimes()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_DosingTimes();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_InfusionType#getDuration <em>Duration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Duration</em>'.
   * @see org.ddmore.pml.pharmaML.design_InfusionType#getDuration()
   * @see #getdesign_InfusionType()
   * @generated
   */
  EReference getdesign_InfusionType_Duration();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_DurationType <em>design Duration Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Duration Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_DurationType
   * @generated
   */
  EClass getdesign_DurationType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_DurationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_DurationType#getXmlns()
   * @see #getdesign_DurationType()
   * @generated
   */
  EReference getdesign_DurationType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DurationType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_DurationType#getExpr()
   * @see #getdesign_DurationType()
   * @generated
   */
  EReference getdesign_DurationType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_BolusType <em>design Bolus Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Bolus Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_BolusType
   * @generated
   */
  EClass getdesign_BolusType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_BolusType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_BolusType#getXmlns()
   * @see #getdesign_BolusType()
   * @generated
   */
  EReference getdesign_BolusType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_BolusType#getDoseAmount <em>Dose Amount</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dose Amount</em>'.
   * @see org.ddmore.pml.pharmaML.design_BolusType#getDoseAmount()
   * @see #getdesign_BolusType()
   * @generated
   */
  EReference getdesign_BolusType_DoseAmount();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_BolusType#getSteadyState <em>Steady State</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Steady State</em>'.
   * @see org.ddmore.pml.pharmaML.design_BolusType#getSteadyState()
   * @see #getdesign_BolusType()
   * @generated
   */
  EReference getdesign_BolusType_SteadyState();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_BolusType#getDosingTimes <em>Dosing Times</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dosing Times</em>'.
   * @see org.ddmore.pml.pharmaML.design_BolusType#getDosingTimes()
   * @see #getdesign_BolusType()
   * @generated
   */
  EReference getdesign_BolusType_DosingTimes();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_DosingTimesType <em>design Dosing Times Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Times Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingTimesType
   * @generated
   */
  EClass getdesign_DosingTimesType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_DosingTimesType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingTimesType#getXmlns()
   * @see #getdesign_DosingTimesType()
   * @generated
   */
  EReference getdesign_DosingTimesType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DosingTimesType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingTimesType#getExpr()
   * @see #getdesign_DosingTimesType()
   * @generated
   */
  EReference getdesign_DosingTimesType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_SteadyStateType <em>design Steady State Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Steady State Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_SteadyStateType
   * @generated
   */
  EClass getdesign_SteadyStateType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_SteadyStateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_SteadyStateType#getXmlns()
   * @see #getdesign_SteadyStateType()
   * @generated
   */
  EReference getdesign_SteadyStateType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_SteadyStateType#getDosingStart <em>Dosing Start</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dosing Start</em>'.
   * @see org.ddmore.pml.pharmaML.design_SteadyStateType#getDosingStart()
   * @see #getdesign_SteadyStateType()
   * @generated
   */
  EReference getdesign_SteadyStateType_DosingStart();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_SteadyStateType#getDosingPeriod <em>Dosing Period</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dosing Period</em>'.
   * @see org.ddmore.pml.pharmaML.design_SteadyStateType#getDosingPeriod()
   * @see #getdesign_SteadyStateType()
   * @generated
   */
  EReference getdesign_SteadyStateType_DosingPeriod();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_DosingStartType <em>design Dosing Start Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Start Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingStartType
   * @generated
   */
  EClass getdesign_DosingStartType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_DosingStartType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingStartType#getXmlns()
   * @see #getdesign_DosingStartType()
   * @generated
   */
  EReference getdesign_DosingStartType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DosingStartType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingStartType#getExpr()
   * @see #getdesign_DosingStartType()
   * @generated
   */
  EReference getdesign_DosingStartType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_DosingPeriodType <em>design Dosing Period Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Period Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingPeriodType
   * @generated
   */
  EClass getdesign_DosingPeriodType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_DosingPeriodType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingPeriodType#getXmlns()
   * @see #getdesign_DosingPeriodType()
   * @generated
   */
  EReference getdesign_DosingPeriodType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DosingPeriodType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingPeriodType#getExpr()
   * @see #getdesign_DosingPeriodType()
   * @generated
   */
  EReference getdesign_DosingPeriodType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_DoseAmountType <em>design Dose Amount Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dose Amount Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_DoseAmountType
   * @generated
   */
  EClass getdesign_DoseAmountType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_DoseAmountType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_DoseAmountType#getXmlns()
   * @see #getdesign_DoseAmountType()
   * @generated
   */
  EReference getdesign_DoseAmountType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DoseAmountType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_DoseAmountType#getExpr()
   * @see #getdesign_DoseAmountType()
   * @generated
   */
  EReference getdesign_DoseAmountType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_DosingVariableType <em>design Dosing Variable Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dosing Variable Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingVariableType
   * @generated
   */
  EClass getdesign_DosingVariableType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DosingVariableType#getDoseVar <em>Dose Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dose Var</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingVariableType#getDoseVar()
   * @see #getdesign_DosingVariableType()
   * @generated
   */
  EReference getdesign_DosingVariableType_DoseVar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DosingVariableType#getTargetVar <em>Target Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target Var</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingVariableType#getTargetVar()
   * @see #getdesign_DosingVariableType()
   * @generated
   */
  EReference getdesign_DosingVariableType_TargetVar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DosingVariableType#getAmount <em>Amount</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Amount</em>'.
   * @see org.ddmore.pml.pharmaML.design_DosingVariableType#getAmount()
   * @see #getdesign_DosingVariableType()
   * @generated
   */
  EReference getdesign_DosingVariableType_Amount();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_AmountType <em>design Amount Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Amount Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_AmountType
   * @generated
   */
  EClass getdesign_AmountType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_AmountType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_AmountType#getXmlns()
   * @see #getdesign_AmountType()
   * @generated
   */
  EReference getdesign_AmountType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_AmountType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.design_AmountType#getExpr()
   * @see #getdesign_AmountType()
   * @generated
   */
  EReference getdesign_AmountType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_TargetVarType <em>design Target Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Target Var Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_TargetVarType
   * @generated
   */
  EClass getdesign_TargetVarType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_TargetVarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.design_TargetVarType#getSymbId()
   * @see #getdesign_TargetVarType()
   * @generated
   */
  EReference getdesign_TargetVarType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_TargetVarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.design_TargetVarType#getBlock()
   * @see #getdesign_TargetVarType()
   * @generated
   */
  EReference getdesign_TargetVarType_Block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_TargetVarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_TargetVarType#getXmlns()
   * @see #getdesign_TargetVarType()
   * @generated
   */
  EReference getdesign_TargetVarType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.design_DoseVarType <em>design Dose Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design Dose Var Type</em>'.
   * @see org.ddmore.pml.pharmaML.design_DoseVarType
   * @generated
   */
  EClass getdesign_DoseVarType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DoseVarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.design_DoseVarType#getSymbId()
   * @see #getdesign_DoseVarType()
   * @generated
   */
  EReference getdesign_DoseVarType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.design_DoseVarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.design_DoseVarType#getBlock()
   * @see #getdesign_DoseVarType()
   * @generated
   */
  EReference getdesign_DoseVarType_Block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.design_DoseVarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.design_DoseVarType#getXmlns()
   * @see #getdesign_DoseVarType()
   * @generated
   */
  EReference getdesign_DoseVarType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_ModelingSteps <em>msteps Modeling Steps</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Modeling Steps</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ModelingSteps
   * @generated
   */
  EClass getmsteps_ModelingSteps();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ModelingSteps#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ModelingSteps#getXmlns()
   * @see #getmsteps_ModelingSteps()
   * @generated
   */
  EReference getmsteps_ModelingSteps_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ModelingSteps#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ModelingSteps#getVariable()
   * @see #getmsteps_ModelingSteps()
   * @generated
   */
  EReference getmsteps_ModelingSteps_Variable();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ModelingSteps#getEstimationStep <em>Estimation Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Estimation Step</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ModelingSteps#getEstimationStep()
   * @see #getmsteps_ModelingSteps()
   * @generated
   */
  EReference getmsteps_ModelingSteps_EstimationStep();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ModelingSteps#getSimulationStep <em>Simulation Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Simulation Step</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ModelingSteps#getSimulationStep()
   * @see #getmsteps_ModelingSteps()
   * @generated
   */
  EReference getmsteps_ModelingSteps_SimulationStep();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_ModelingSteps#getStepDependencies <em>Step Dependencies</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Step Dependencies</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ModelingSteps#getStepDependencies()
   * @see #getmsteps_ModelingSteps()
   * @generated
   */
  EReference getmsteps_ModelingSteps_StepDependencies();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_StepDependenciesType <em>msteps Step Dependencies Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Step Dependencies Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_StepDependenciesType
   * @generated
   */
  EClass getmsteps_StepDependenciesType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_StepDependenciesType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_StepDependenciesType#getXmlns()
   * @see #getmsteps_StepDependenciesType()
   * @generated
   */
  EReference getmsteps_StepDependenciesType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_StepDependenciesType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_StepDependenciesType#getDescription()
   * @see #getmsteps_StepDependenciesType()
   * @generated
   */
  EReference getmsteps_StepDependenciesType_Description();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_StepDependenciesType#getStep <em>Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Step</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_StepDependenciesType#getStep()
   * @see #getmsteps_StepDependenciesType()
   * @generated
   */
  EReference getmsteps_StepDependenciesType_Step();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_StepType <em>msteps Step Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Step Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_StepType
   * @generated
   */
  EClass getmsteps_StepType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.msteps_StepType#getIdRef <em>Id Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id Ref</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_StepType#getIdRef()
   * @see #getmsteps_StepType()
   * @generated
   */
  EAttribute getmsteps_StepType_IdRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_StepType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_StepType#getXmlns()
   * @see #getmsteps_StepType()
   * @generated
   */
  EReference getmsteps_StepType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_StepType#getDependantStep <em>Dependant Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Dependant Step</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_StepType#getDependantStep()
   * @see #getmsteps_StepType()
   * @generated
   */
  EReference getmsteps_StepType_DependantStep();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_DependantStepType <em>msteps Dependant Step Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Dependant Step Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_DependantStepType
   * @generated
   */
  EClass getmsteps_DependantStepType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.msteps_DependantStepType#getIdRef <em>Id Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id Ref</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_DependantStepType#getIdRef()
   * @see #getmsteps_DependantStepType()
   * @generated
   */
  EAttribute getmsteps_DependantStepType_IdRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_DependantStepType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_DependantStepType#getXmlns()
   * @see #getmsteps_DependantStepType()
   * @generated
   */
  EReference getmsteps_DependantStepType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_EstimationStep <em>msteps Estimation Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Estimation Step</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationStep
   * @generated
   */
  EClass getmsteps_EstimationStep();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_EstimationStep#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationStep#getId()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_Id();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_EstimationStep#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationStep#getXmlns()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_EstimationStep#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationStep#getDescription()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_Description();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_EstimationStep#getInitialValue <em>Initial Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initial Value</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationStep#getInitialValue()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_InitialValue();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_EstimationStep#getObjectiveDataSet <em>Objective Data Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Objective Data Set</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationStep#getObjectiveDataSet()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_ObjectiveDataSet();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_EstimationStep#getParametersToEstimate <em>Parameters To Estimate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters To Estimate</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationStep#getParametersToEstimate()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_ParametersToEstimate();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_EstimationStep#getEstimationOperation <em>Estimation Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Estimation Operation</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationStep#getEstimationOperation()
   * @see #getmsteps_EstimationStep()
   * @generated
   */
  EReference getmsteps_EstimationStep_EstimationOperation();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_ParametersToEstimateType <em>msteps Parameters To Estimate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Parameters To Estimate Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ParametersToEstimateType
   * @generated
   */
  EClass getmsteps_ParametersToEstimateType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ParametersToEstimateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ParametersToEstimateType#getXmlns()
   * @see #getmsteps_ParametersToEstimateType()
   * @generated
   */
  EReference getmsteps_ParametersToEstimateType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ParametersToEstimateType#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variable</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ParametersToEstimateType#getVariable()
   * @see #getmsteps_ParametersToEstimateType()
   * @generated
   */
  EReference getmsteps_ParametersToEstimateType_Variable();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType <em>mSteps Variable Estimate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps Variable Estimate Type</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_VariableEstimateType
   * @generated
   */
  EClass getmSteps_VariableEstimateType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getSymbID <em>Symb ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb ID</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getSymbID()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_SymbID();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getFixed <em>Fixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fixed</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getFixed()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_Fixed();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getBlock()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_Block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getXmlns()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getInitialEstimate <em>Initial Estimate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Initial Estimate</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getInitialEstimate()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_InitialEstimate();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getLowerBound <em>Lower Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Bound</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getLowerBound()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_LowerBound();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getUpperBound <em>Upper Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Bound</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getUpperBound()
   * @see #getmSteps_VariableEstimateType()
   * @generated
   */
  EReference getmSteps_VariableEstimateType_UpperBound();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mSteps_fixed <em>mSteps fixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps fixed</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_fixed
   * @generated
   */
  EClass getmSteps_fixed();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_fixed#getFixed <em>Fixed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Fixed</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_fixed#getFixed()
   * @see #getmSteps_fixed()
   * @generated
   */
  EReference getmSteps_fixed_Fixed();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mSteps_UpperBoundType <em>mSteps Upper Bound Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps Upper Bound Type</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_UpperBoundType
   * @generated
   */
  EClass getmSteps_UpperBoundType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mSteps_UpperBoundType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_UpperBoundType#getXmlns()
   * @see #getmSteps_UpperBoundType()
   * @generated
   */
  EReference getmSteps_UpperBoundType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_UpperBoundType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_UpperBoundType#getExpr()
   * @see #getmSteps_UpperBoundType()
   * @generated
   */
  EReference getmSteps_UpperBoundType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mSteps_LowerBoundType <em>mSteps Lower Bound Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps Lower Bound Type</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_LowerBoundType
   * @generated
   */
  EClass getmSteps_LowerBoundType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mSteps_LowerBoundType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_LowerBoundType#getXmlns()
   * @see #getmSteps_LowerBoundType()
   * @generated
   */
  EReference getmSteps_LowerBoundType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_LowerBoundType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_LowerBoundType#getExpr()
   * @see #getmSteps_LowerBoundType()
   * @generated
   */
  EReference getmSteps_LowerBoundType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.mSteps_InitialEstimateType <em>mSteps Initial Estimate Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mSteps Initial Estimate Type</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_InitialEstimateType
   * @generated
   */
  EClass getmSteps_InitialEstimateType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.mSteps_InitialEstimateType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_InitialEstimateType#getXmlns()
   * @see #getmSteps_InitialEstimateType()
   * @generated
   */
  EReference getmSteps_InitialEstimateType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.mSteps_InitialEstimateType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.mSteps_InitialEstimateType#getExpr()
   * @see #getmSteps_InitialEstimateType()
   * @generated
   */
  EReference getmSteps_InitialEstimateType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType <em>msteps Objective Data Set Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Objective Data Set Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType
   * @generated
   */
  EClass getmsteps_ObjectiveDataSetType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getDataSetRef <em>Data Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Set Ref</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getDataSetRef()
   * @see #getmsteps_ObjectiveDataSetType()
   * @generated
   */
  EAttribute getmsteps_ObjectiveDataSetType_DataSetRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getXmlns()
   * @see #getmsteps_ObjectiveDataSetType()
   * @generated
   */
  EReference getmsteps_ObjectiveDataSetType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Mapping</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getMapping()
   * @see #getmsteps_ObjectiveDataSetType()
   * @generated
   */
  EReference getmsteps_ObjectiveDataSetType_Mapping();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_MappingType <em>msteps Mapping Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Mapping Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType
   * @generated
   */
  EClass getmsteps_MappingType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getColumnName <em>Column Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Column Name</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getColumnName()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EAttribute getmsteps_MappingType_ColumnName();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getXmlns()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EReference getmsteps_MappingType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getVar()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EReference getmsteps_MappingType_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getUseVariabilityLevel <em>Use Variability Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Use Variability Level</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getUseVariabilityLevel()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EReference getmsteps_MappingType_UseVariabilityLevel();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getUseVariabilityNode <em>Use Variability Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Use Variability Node</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getUseVariabilityNode()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EReference getmsteps_MappingType_UseVariabilityNode();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getDoseVar <em>Dose Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Dose Var</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getDoseVar()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EReference getmsteps_MappingType_DoseVar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getTargetVar <em>Target Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Target Var</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getTargetVar()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EReference getmsteps_MappingType_TargetVar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getAssign <em>Assign</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Assign</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getAssign()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EReference getmsteps_MappingType_Assign();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getOutput <em>Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getOutput()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EReference getmsteps_MappingType_Output();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getRestriction <em>Restriction</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Restriction</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_MappingType#getRestriction()
   * @see #getmsteps_MappingType()
   * @generated
   */
  EReference getmsteps_MappingType_Restriction();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_OutputType <em>msteps Output Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Output Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_OutputType
   * @generated
   */
  EClass getmsteps_OutputType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_OutputType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_OutputType#getXmlns()
   * @see #getmsteps_OutputType()
   * @generated
   */
  EReference getmsteps_OutputType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_OutputType#getVars <em>Vars</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Vars</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_OutputType#getVars()
   * @see #getmsteps_OutputType()
   * @generated
   */
  EReference getmsteps_OutputType_Vars();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_AssignType <em>msteps Assign Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Assign Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_AssignType
   * @generated
   */
  EClass getmsteps_AssignType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_AssignType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_AssignType#getSymbId()
   * @see #getmsteps_AssignType()
   * @generated
   */
  EReference getmsteps_AssignType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_AssignType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_AssignType#getBlock()
   * @see #getmsteps_AssignType()
   * @generated
   */
  EReference getmsteps_AssignType_Block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_AssignType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_AssignType#getXmlns()
   * @see #getmsteps_AssignType()
   * @generated
   */
  EReference getmsteps_AssignType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_AssignType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_AssignType#getExpr()
   * @see #getmsteps_AssignType()
   * @generated
   */
  EReference getmsteps_AssignType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_TargetVarType <em>msteps Target Var Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Target Var Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_TargetVarType
   * @generated
   */
  EClass getmsteps_TargetVarType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_TargetVarType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_TargetVarType#getSymbId()
   * @see #getmsteps_TargetVarType()
   * @generated
   */
  EReference getmsteps_TargetVarType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_TargetVarType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_TargetVarType#getBlock()
   * @see #getmsteps_TargetVarType()
   * @generated
   */
  EReference getmsteps_TargetVarType_Block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_TargetVarType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_TargetVarType#getXmlns()
   * @see #getmsteps_TargetVarType()
   * @generated
   */
  EReference getmsteps_TargetVarType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel <em>msteps Use Variability Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Use Variability Level</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel
   * @generated
   */
  EClass getmsteps_UseVariabilityLevel();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Level Id</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel#getLevelId()
   * @see #getmsteps_UseVariabilityLevel()
   * @generated
   */
  EAttribute getmsteps_UseVariabilityLevel_LevelId();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel#getXmlns()
   * @see #getmsteps_UseVariabilityLevel()
   * @generated
   */
  EReference getmsteps_UseVariabilityLevel_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityNode <em>msteps Use Variability Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Use Variability Node</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_UseVariabilityNode
   * @generated
   */
  EClass getmsteps_UseVariabilityNode();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityNode#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_UseVariabilityNode#getSymbId()
   * @see #getmsteps_UseVariabilityNode()
   * @generated
   */
  EReference getmsteps_UseVariabilityNode_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityNode#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_UseVariabilityNode#getBlock()
   * @see #getmsteps_UseVariabilityNode()
   * @generated
   */
  EReference getmsteps_UseVariabilityNode_Block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityNode#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_UseVariabilityNode#getXmlns()
   * @see #getmsteps_UseVariabilityNode()
   * @generated
   */
  EReference getmsteps_UseVariabilityNode_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_SimulationStep <em>msteps Simulation Step</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Simulation Step</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimulationStep
   * @generated
   */
  EClass getmsteps_SimulationStep();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_SimulationStep#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimulationStep#getId()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Id();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_SimulationStep#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimulationStep#getXmlns()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_SimulationStep#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimulationStep#getDescription()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Description();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_SimulationStep#getReplicates <em>Replicates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Replicates</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimulationStep#getReplicates()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Replicates();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_SimulationStep#getInitialValue <em>Initial Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Initial Value</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimulationStep#getInitialValue()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_InitialValue();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_SimulationStep#getSimDataSet <em>Sim Data Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sim Data Set</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimulationStep#getSimDataSet()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_SimDataSet();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_SimulationStep#getObservations <em>Observations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Observations</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimulationStep#getObservations()
   * @see #getmsteps_SimulationStep()
   * @generated
   */
  EReference getmsteps_SimulationStep_Observations();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_SimDataSetType <em>msteps Sim Data Set Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Sim Data Set Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimDataSetType
   * @generated
   */
  EClass getmsteps_SimDataSetType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.msteps_SimDataSetType#getDataSetRef <em>Data Set Ref</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Set Ref</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimDataSetType#getDataSetRef()
   * @see #getmsteps_SimDataSetType()
   * @generated
   */
  EAttribute getmsteps_SimDataSetType_DataSetRef();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_SimDataSetType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimDataSetType#getXmlns()
   * @see #getmsteps_SimDataSetType()
   * @generated
   */
  EReference getmsteps_SimDataSetType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_SimDataSetType#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Mapping</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_SimDataSetType#getMapping()
   * @see #getmsteps_SimDataSetType()
   * @generated
   */
  EReference getmsteps_SimDataSetType_Mapping();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_ObservationsType <em>msteps Observations Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Observations Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ObservationsType
   * @generated
   */
  EClass getmsteps_ObservationsType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ObservationsType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ObservationsType#getXmlns()
   * @see #getmsteps_ObservationsType()
   * @generated
   */
  EReference getmsteps_ObservationsType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_ObservationsType#getTimepoints <em>Timepoints</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Timepoints</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ObservationsType#getTimepoints()
   * @see #getmsteps_ObservationsType()
   * @generated
   */
  EReference getmsteps_ObservationsType_Timepoints();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ObservationsType#getOutput <em>Output</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Output</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ObservationsType#getOutput()
   * @see #getmsteps_ObservationsType()
   * @generated
   */
  EReference getmsteps_ObservationsType_Output();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_ObservationsType#getWriteTo <em>Write To</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Write To</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_ObservationsType#getWriteTo()
   * @see #getmsteps_ObservationsType()
   * @generated
   */
  EReference getmsteps_ObservationsType_WriteTo();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_WriteToType <em>msteps Write To Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Write To Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_WriteToType
   * @generated
   */
  EClass getmsteps_WriteToType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_WriteToType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_WriteToType#getXmlns()
   * @see #getmsteps_WriteToType()
   * @generated
   */
  EReference getmsteps_WriteToType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_WriteToType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_WriteToType#getVar()
   * @see #getmsteps_WriteToType()
   * @generated
   */
  EReference getmsteps_WriteToType_Var();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_WriteToType#getMapping <em>Mapping</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Mapping</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_WriteToType#getMapping()
   * @see #getmsteps_WriteToType()
   * @generated
   */
  EReference getmsteps_WriteToType_Mapping();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_TimepointsType <em>msteps Timepoints Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Timepoints Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_TimepointsType
   * @generated
   */
  EClass getmsteps_TimepointsType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_TimepointsType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_TimepointsType#getXmlns()
   * @see #getmsteps_TimepointsType()
   * @generated
   */
  EReference getmsteps_TimepointsType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_TimepointsType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_TimepointsType#getExpr()
   * @see #getmsteps_TimepointsType()
   * @generated
   */
  EReference getmsteps_TimepointsType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_InitialValueType <em>msteps Initial Value Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Initial Value Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_InitialValueType
   * @generated
   */
  EClass getmsteps_InitialValueType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_InitialValueType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_InitialValueType#getSymbId()
   * @see #getmsteps_InitialValueType()
   * @generated
   */
  EReference getmsteps_InitialValueType_SymbId();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_InitialValueType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_InitialValueType#getXmlns()
   * @see #getmsteps_InitialValueType()
   * @generated
   */
  EReference getmsteps_InitialValueType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_InitialValueType#getContent <em>Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Content</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_InitialValueType#getContent()
   * @see #getmsteps_InitialValueType()
   * @generated
   */
  EReference getmsteps_InitialValueType_Content();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.msteps_EstimationOperationType <em>msteps Estimation Operation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>msteps Estimation Operation Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationOperationType
   * @generated
   */
  EClass getmsteps_EstimationOperationType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.msteps_EstimationOperationType#getOpType <em>Op Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Op Type</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationOperationType#getOpType()
   * @see #getmsteps_EstimationOperationType()
   * @generated
   */
  EReference getmsteps_EstimationOperationType_OpType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.msteps_EstimationOperationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.msteps_EstimationOperationType#getXmlns()
   * @see #getmsteps_EstimationOperationType()
   * @generated
   */
  EReference getmsteps_EstimationOperationType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_DistributionType <em>uncert Distribution Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Distribution Type</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionType
   * @generated
   */
  EClass getuncert_DistributionType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionType#getXmlns()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Written Version</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionType#getWrittenVersion()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_WrittenVersion();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getNormal <em>Normal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Normal</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionType#getNormal()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_Normal();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getPDF <em>PDF</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>PDF</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionType#getPDF()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_PDF();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getPoison <em>Poison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Poison</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionType#getPoison()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_Poison();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getStudent <em>Student</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Student</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionType#getStudent()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_Student();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getUniform <em>Uniform</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Uniform</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionType#getUniform()
   * @see #getuncert_DistributionType()
   * @generated
   */
  EReference getuncert_DistributionType_Uniform();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_Normal <em>uncert Normal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Normal</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Normal
   * @generated
   */
  EClass getuncert_Normal();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_Normal#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Normal#getXmlns()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Normal#getMean <em>Mean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mean</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Normal#getMean()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_Mean();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Normal#getStdDev <em>Std Dev</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Std Dev</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Normal#getStdDev()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_StdDev();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Normal#getVariance <em>Variance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variance</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Normal#getVariance()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_Variance();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Normal#getCoefficientOfVariance <em>Coefficient Of Variance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Coefficient Of Variance</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Normal#getCoefficientOfVariance()
   * @see #getuncert_Normal()
   * @generated
   */
  EReference getuncert_Normal_CoefficientOfVariance();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_CoefficientOfVariance <em>uncert Coefficient Of Variance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Coefficient Of Variance</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_CoefficientOfVariance
   * @generated
   */
  EClass getuncert_CoefficientOfVariance();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_CoefficientOfVariance#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_CoefficientOfVariance#getXmlns()
   * @see #getuncert_CoefficientOfVariance()
   * @generated
   */
  EReference getuncert_CoefficientOfVariance_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_CoefficientOfVariance#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_CoefficientOfVariance#getExpr()
   * @see #getuncert_CoefficientOfVariance()
   * @generated
   */
  EReference getuncert_CoefficientOfVariance_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_StdDev <em>uncert Std Dev</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Std Dev</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_StdDev
   * @generated
   */
  EClass getuncert_StdDev();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_StdDev#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_StdDev#getXmlns()
   * @see #getuncert_StdDev()
   * @generated
   */
  EReference getuncert_StdDev_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_StdDev#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_StdDev#getExpr()
   * @see #getuncert_StdDev()
   * @generated
   */
  EReference getuncert_StdDev_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_DistributionParamType <em>uncert Distribution Param Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Distribution Param Type</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionParamType
   * @generated
   */
  EClass getuncert_DistributionParamType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_DistributionParamType#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionParamType#getEquation()
   * @see #getuncert_DistributionParamType()
   * @generated
   */
  EReference getuncert_DistributionParamType_Equation();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_DistributionParamType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionParamType#getScalar()
   * @see #getuncert_DistributionParamType()
   * @generated
   */
  EReference getuncert_DistributionParamType_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_DistributionParamType#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_DistributionParamType#getVar()
   * @see #getuncert_DistributionParamType()
   * @generated
   */
  EReference getuncert_DistributionParamType_Var();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_MeanParamType <em>uncert Mean Param Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Mean Param Type</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_MeanParamType
   * @generated
   */
  EClass getuncert_MeanParamType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_MeanParamType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_MeanParamType#getExpr()
   * @see #getuncert_MeanParamType()
   * @generated
   */
  EReference getuncert_MeanParamType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_Mean <em>uncert Mean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Mean</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Mean
   * @generated
   */
  EClass getuncert_Mean();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_Mean#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Mean#getXmlns()
   * @see #getuncert_Mean()
   * @generated
   */
  EReference getuncert_Mean_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Mean#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Mean#getExpr()
   * @see #getuncert_Mean()
   * @generated
   */
  EReference getuncert_Mean_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_Variance <em>uncert Variance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Variance</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Variance
   * @generated
   */
  EClass getuncert_Variance();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_Variance#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Variance#getXmlns()
   * @see #getuncert_Variance()
   * @generated
   */
  EReference getuncert_Variance_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Variance#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Variance#getExpr()
   * @see #getuncert_Variance()
   * @generated
   */
  EReference getuncert_Variance_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_PDF <em>uncert PDF</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert PDF</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_PDF
   * @generated
   */
  EClass getuncert_PDF();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_PDF#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_PDF#getXmlns()
   * @see #getuncert_PDF()
   * @generated
   */
  EReference getuncert_PDF_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_PDF#getFunctionParameter <em>Function Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Function Parameter</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_PDF#getFunctionParameter()
   * @see #getuncert_PDF()
   * @generated
   */
  EReference getuncert_PDF_FunctionParameter();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_PDF#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_PDF#getEquation()
   * @see #getuncert_PDF()
   * @generated
   */
  EReference getuncert_PDF_Equation();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType <em>uncert Parameter Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Parameter Definition Type</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType
   * @generated
   */
  EClass getuncert_ParameterDefinitionType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType#getId()
   * @see #getuncert_ParameterDefinitionType()
   * @generated
   */
  EReference getuncert_ParameterDefinitionType_Id();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType#getName()
   * @see #getuncert_ParameterDefinitionType()
   * @generated
   */
  EReference getuncert_ParameterDefinitionType_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType#getXmlns()
   * @see #getuncert_ParameterDefinitionType()
   * @generated
   */
  EReference getuncert_ParameterDefinitionType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_Poison <em>uncert Poison</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Poison</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Poison
   * @generated
   */
  EClass getuncert_Poison();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_Poison#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Poison#getXmlns()
   * @see #getuncert_Poison()
   * @generated
   */
  EReference getuncert_Poison_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Poison#getRate <em>Rate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rate</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Poison#getRate()
   * @see #getuncert_Poison()
   * @generated
   */
  EReference getuncert_Poison_Rate();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_Rate <em>uncert Rate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Rate</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Rate
   * @generated
   */
  EClass getuncert_Rate();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_Rate#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Rate#getXmlns()
   * @see #getuncert_Rate()
   * @generated
   */
  EReference getuncert_Rate_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Rate#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Rate#getExpr()
   * @see #getuncert_Rate()
   * @generated
   */
  EReference getuncert_Rate_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_Student_t <em>uncert Student t</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Student t</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Student_t
   * @generated
   */
  EClass getuncert_Student_t();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_Student_t#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Student_t#getXmlns()
   * @see #getuncert_Student_t()
   * @generated
   */
  EReference getuncert_Student_t_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Student_t#getLocation <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Location</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Student_t#getLocation()
   * @see #getuncert_Student_t()
   * @generated
   */
  EReference getuncert_Student_t_Location();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Student_t#getScale <em>Scale</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scale</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Student_t#getScale()
   * @see #getuncert_Student_t()
   * @generated
   */
  EReference getuncert_Student_t_Scale();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Student_t#getDegreesOfFreedom <em>Degrees Of Freedom</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Degrees Of Freedom</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Student_t#getDegreesOfFreedom()
   * @see #getuncert_Student_t()
   * @generated
   */
  EReference getuncert_Student_t_DegreesOfFreedom();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncertDegreesOfFreedom <em>uncert Degrees Of Freedom</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Degrees Of Freedom</em>'.
   * @see org.ddmore.pml.pharmaML.uncertDegreesOfFreedom
   * @generated
   */
  EClass getuncertDegreesOfFreedom();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncertDegreesOfFreedom#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncertDegreesOfFreedom#getXmlns()
   * @see #getuncertDegreesOfFreedom()
   * @generated
   */
  EReference getuncertDegreesOfFreedom_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncertDegreesOfFreedom#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.uncertDegreesOfFreedom#getExpr()
   * @see #getuncertDegreesOfFreedom()
   * @generated
   */
  EReference getuncertDegreesOfFreedom_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_Scale <em>uncert Scale</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Scale</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Scale
   * @generated
   */
  EClass getuncert_Scale();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_Scale#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Scale#getXmlns()
   * @see #getuncert_Scale()
   * @generated
   */
  EReference getuncert_Scale_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Scale#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Scale#getExpr()
   * @see #getuncert_Scale()
   * @generated
   */
  EReference getuncert_Scale_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_Location <em>uncert Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Location</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Location
   * @generated
   */
  EClass getuncert_Location();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_Location#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Location#getXmlns()
   * @see #getuncert_Location()
   * @generated
   */
  EReference getuncert_Location_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.uncert_Location#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Location#getExpr()
   * @see #getuncert_Location()
   * @generated
   */
  EReference getuncert_Location_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.uncert_Uniform <em>uncert Uniform</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>uncert Uniform</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Uniform
   * @generated
   */
  EClass getuncert_Uniform();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.uncert_Uniform#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.uncert_Uniform#getXmlns()
   * @see #getuncert_Uniform()
   * @generated
   */
  EReference getuncert_Uniform_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_symbId <em>ct symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_symbId
   * @generated
   */
  EClass getct_symbId();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_symbId#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_symbId#getSymbId()
   * @see #getct_symbId()
   * @generated
   */
  EAttribute getct_symbId_SymbId();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_id <em>ct id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_id
   * @generated
   */
  EClass getct_id();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_id#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_id#getId()
   * @see #getct_id()
   * @generated
   */
  EAttribute getct_id_Id();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_symbolType <em>ct symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct symbol Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_symbolType
   * @generated
   */
  EClass getct_symbolType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_symbolType#getSymbolType <em>Symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_symbolType#getSymbolType()
   * @see #getct_symbolType()
   * @generated
   */
  EReference getct_symbolType_SymbolType();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_block <em>ct block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct block</em>'.
   * @see org.ddmore.pml.pharmaML.ct_block
   * @generated
   */
  EClass getct_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.ct_block#getBlock()
   * @see #getct_block()
   * @generated
   */
  EAttribute getct_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_writtenVersion <em>ct written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct written Version</em>'.
   * @see org.ddmore.pml.pharmaML.ct_writtenVersion
   * @generated
   */
  EClass getct_writtenVersion();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_writtenVersion#getWrittenVersion <em>Written Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Written Version</em>'.
   * @see org.ddmore.pml.pharmaML.ct_writtenVersion#getWrittenVersion()
   * @see #getct_writtenVersion()
   * @generated
   */
  EAttribute getct_writtenVersion_WrittenVersion();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_Decimal_Value <em>ct Decimal Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Decimal Value</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Decimal_Value
   * @generated
   */
  EClass getct_Decimal_Value();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_Decimal_Value#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Decimal_Value#getValue()
   * @see #getct_Decimal_Value()
   * @generated
   */
  EAttribute getct_Decimal_Value_Value();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_String_Value <em>ct String Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct String Value</em>'.
   * @see org.ddmore.pml.pharmaML.ct_String_Value
   * @generated
   */
  EClass getct_String_Value();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_String_Value#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.ddmore.pml.pharmaML.ct_String_Value#getValue()
   * @see #getct_String_Value()
   * @generated
   */
  EAttribute getct_String_Value_Value();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_Int_Value <em>ct Int Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Int Value</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Int_Value
   * @generated
   */
  EClass getct_Int_Value();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_Int_Value#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Int_Value#getValue()
   * @see #getct_Int_Value()
   * @generated
   */
  EAttribute getct_Int_Value_Value();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_catId <em>ct cat Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct cat Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_catId
   * @generated
   */
  EClass getct_catId();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_catId#getCatId <em>Cat Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Cat Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_catId#getCatId()
   * @see #getct_catId()
   * @generated
   */
  EAttribute getct_catId_CatId();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_ReplicatesType <em>ct Replicates Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Replicates Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ReplicatesType
   * @generated
   */
  EClass getct_ReplicatesType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_ReplicatesType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ReplicatesType#getSymbId()
   * @see #getct_ReplicatesType()
   * @generated
   */
  EReference getct_ReplicatesType_SymbId();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_ReplicatesType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ReplicatesType#getXmlns()
   * @see #getct_ReplicatesType()
   * @generated
   */
  EReference getct_ReplicatesType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_ReplicatesType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ReplicatesType#getExpr()
   * @see #getct_ReplicatesType()
   * @generated
   */
  EReference getct_ReplicatesType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_AnnotationType <em>ct Annotation Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Annotation Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_AnnotationType
   * @generated
   */
  EClass getct_AnnotationType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_AnnotationType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_AnnotationType#getXmlns()
   * @see #getct_AnnotationType()
   * @generated
   */
  EReference getct_AnnotationType_Xmlns();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_AnnotationType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.ddmore.pml.pharmaML.ct_AnnotationType#getDescription()
   * @see #getct_AnnotationType()
   * @generated
   */
  EAttribute getct_AnnotationType_Description();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_Rhs <em>ct Rhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Rhs</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs
   * @generated
   */
  EClass getct_Rhs();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getEquation()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Equation();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getScalar()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getConstant <em>Constant</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Constant</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getConstant()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Constant();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>String</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getString()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_String();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getSequence <em>Sequence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sequence</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getSequence()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Sequence();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getVector <em>Vector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Vector</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getVector()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Vector();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getFunctionCall()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_FunctionCall();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getDataSet <em>Data Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Data Set</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getDataSet()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_DataSet();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getVar()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_Rhs#getDistribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Distribution</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Rhs#getDistribution()
   * @see #getct_Rhs()
   * @generated
   */
  EReference getct_Rhs_Distribution();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_DataSetType <em>ct Data Set Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Data Set Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DataSetType
   * @generated
   */
  EClass getct_DataSetType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_DataSetType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DataSetType#getXmlns()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_DataSetType#getDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Definition</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DataSetType#getDefinition()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_Definition();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_DataSetType#getExternalSource <em>External Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>External Source</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DataSetType#getExternalSource()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_ExternalSource();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_DataSetType#getRow <em>Row</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Row</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DataSetType#getRow()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_Row();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_DataSetType#getInternalSource <em>Internal Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Internal Source</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DataSetType#getInternalSource()
   * @see #getct_DataSetType()
   * @generated
   */
  EReference getct_DataSetType_InternalSource();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_DefinitionType <em>ct Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Definition Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DefinitionType
   * @generated
   */
  EClass getct_DefinitionType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_DefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DefinitionType#getXmlns()
   * @see #getct_DefinitionType()
   * @generated
   */
  EReference getct_DefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_DefinitionType#getDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Definition</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DefinitionType#getDefinition()
   * @see #getct_DefinitionType()
   * @generated
   */
  EReference getct_DefinitionType_Definition();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_DefinitionRhsType <em>ct Definition Rhs Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Definition Rhs Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DefinitionRhsType
   * @generated
   */
  EClass getct_DefinitionRhsType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_DefinitionRhsType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DefinitionRhsType#getXmlns()
   * @see #getct_DefinitionRhsType()
   * @generated
   */
  EReference getct_DefinitionRhsType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_DefinitionRhsType#getDefinition <em>Definition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Definition</em>'.
   * @see org.ddmore.pml.pharmaML.ct_DefinitionRhsType#getDefinition()
   * @see #getct_DefinitionRhsType()
   * @generated
   */
  EReference getct_DefinitionRhsType_Definition();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_ColumnType <em>ct Column Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Column Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ColumnType
   * @generated
   */
  EClass getct_ColumnType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_ColumnType#getColumnNum <em>Column Num</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Column Num</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ColumnType#getColumnNum()
   * @see #getct_ColumnType()
   * @generated
   */
  EReference getct_ColumnType_ColumnNum();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_ColumnType#getColumnVar <em>Column Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Column Var</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ColumnType#getColumnVar()
   * @see #getct_ColumnType()
   * @generated
   */
  EAttribute getct_ColumnType_ColumnVar();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_ColumnType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ColumnType#getXmlns()
   * @see #getct_ColumnType()
   * @generated
   */
  EReference getct_ColumnType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType <em>ct External Source Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct External Source Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ExternalSourceType
   * @generated
   */
  EClass getct_ExternalSourceType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getFormat <em>Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Format</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ExternalSourceType#getFormat()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EAttribute getct_ExternalSourceType_Format();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getHeaderLine <em>Header Line</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Header Line</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ExternalSourceType#getHeaderLine()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EReference getct_ExternalSourceType_HeaderLine();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getIgnoreLineSymbol <em>Ignore Line Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ignore Line Symbol</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ExternalSourceType#getIgnoreLineSymbol()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EAttribute getct_ExternalSourceType_IgnoreLineSymbol();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getUrl <em>Url</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Url</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ExternalSourceType#getUrl()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EAttribute getct_ExternalSourceType_Url();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ExternalSourceType#getXmlns()
   * @see #getct_ExternalSourceType()
   * @generated
   */
  EReference getct_ExternalSourceType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_RowType <em>ct Row Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Row Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_RowType
   * @generated
   */
  EClass getct_RowType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_RowType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_RowType#getXmlns()
   * @see #getct_RowType()
   * @generated
   */
  EReference getct_RowType_Xmlns();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_RowType#getRow <em>Row</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Row</em>'.
   * @see org.ddmore.pml.pharmaML.ct_RowType#getRow()
   * @see #getct_RowType()
   * @generated
   */
  EReference getct_RowType_Row();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_RowTypeContent <em>ct Row Type Content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Row Type Content</em>'.
   * @see org.ddmore.pml.pharmaML.ct_RowTypeContent
   * @generated
   */
  EClass getct_RowTypeContent();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_RowTypeContent#getCell <em>Cell</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Cell</em>'.
   * @see org.ddmore.pml.pharmaML.ct_RowTypeContent#getCell()
   * @see #getct_RowTypeContent()
   * @generated
   */
  EReference getct_RowTypeContent_Cell();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_RowTypeContent#getNull <em>Null</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Null</em>'.
   * @see org.ddmore.pml.pharmaML.ct_RowTypeContent#getNull()
   * @see #getct_RowTypeContent()
   * @generated
   */
  EAttribute getct_RowTypeContent_Null();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_CellType <em>ct Cell Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Cell Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_CellType
   * @generated
   */
  EClass getct_CellType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_CellType#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.ddmore.pml.pharmaML.ct_CellType#getValue()
   * @see #getct_CellType()
   * @generated
   */
  EReference getct_CellType_Value();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_CellType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_CellType#getXmlns()
   * @see #getct_CellType()
   * @generated
   */
  EReference getct_CellType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_InternalSourceType <em>ct Internal Source Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Internal Source Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_InternalSourceType
   * @generated
   */
  EClass getct_InternalSourceType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_InternalSourceType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_InternalSourceType#getSymbId()
   * @see #getct_InternalSourceType()
   * @generated
   */
  EReference getct_InternalSourceType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_InternalSourceType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.ct_InternalSourceType#getBlock()
   * @see #getct_InternalSourceType()
   * @generated
   */
  EReference getct_InternalSourceType_Block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_InternalSourceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_InternalSourceType#getXmlns()
   * @see #getct_InternalSourceType()
   * @generated
   */
  EReference getct_InternalSourceType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_ScalarRhs <em>ct Scalar Rhs</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Scalar Rhs</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ScalarRhs
   * @generated
   */
  EClass getct_ScalarRhs();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_ScalarRhs#getEquation <em>Equation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Equation</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ScalarRhs#getEquation()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_Equation();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_ScalarRhs#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ScalarRhs#getScalar()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_Scalar();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_ScalarRhs#getVar <em>Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Var</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ScalarRhs#getVar()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_Var();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_ScalarRhs#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>String</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ScalarRhs#getString()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_String();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_ScalarRhs#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see org.ddmore.pml.pharmaML.ct_ScalarRhs#getFunctionCall()
   * @see #getct_ScalarRhs()
   * @generated
   */
  EReference getct_ScalarRhs_FunctionCall();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_levelId <em>ct level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct level Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_levelId
   * @generated
   */
  EClass getct_levelId();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_levelId#getLevelId <em>Level Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Level Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_levelId#getLevelId()
   * @see #getct_levelId()
   * @generated
   */
  EAttribute getct_levelId_LevelId();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_Name <em>ct Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Name</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Name
   * @generated
   */
  EClass getct_Name();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_Name#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.ddmore.pml.pharmaML.ct_Name#getName()
   * @see #getct_Name()
   * @generated
   */
  EAttribute getct_Name_Name();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_VectorType <em>ct Vector Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Vector Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VectorType
   * @generated
   */
  EClass getct_VectorType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_VectorType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VectorType#getXmlns()
   * @see #getct_VectorType()
   * @generated
   */
  EReference getct_VectorType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VectorType#getSequence <em>Sequence</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Sequence</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VectorType#getSequence()
   * @see #getct_VectorType()
   * @generated
   */
  EReference getct_VectorType_Sequence();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VectorType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VectorType#getScalar()
   * @see #getct_VectorType()
   * @generated
   */
  EReference getct_VectorType_Scalar();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_SequenceType <em>ct Sequence Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Sequence Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SequenceType
   * @generated
   */
  EClass getct_SequenceType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_SequenceType#getBegin <em>Begin</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Begin</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SequenceType#getBegin()
   * @see #getct_SequenceType()
   * @generated
   */
  EReference getct_SequenceType_Begin();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_SequenceType#getEnd <em>End</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>End</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SequenceType#getEnd()
   * @see #getct_SequenceType()
   * @generated
   */
  EReference getct_SequenceType_End();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_SequenceType#getRepetition <em>Repetition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Repetition</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SequenceType#getRepetition()
   * @see #getct_SequenceType()
   * @generated
   */
  EReference getct_SequenceType_Repetition();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_SequenceType#getStepwise <em>Stepwise</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Stepwise</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SequenceType#getStepwise()
   * @see #getct_SequenceType()
   * @generated
   */
  EReference getct_SequenceType_Stepwise();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_SequenceType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SequenceType#getXmlns()
   * @see #getct_SequenceType()
   * @generated
   */
  EReference getct_SequenceType_Xmlns();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_SymbolTypeType <em>ct Symbol Type Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Symbol Type Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SymbolTypeType
   * @generated
   */
  EClass getct_SymbolTypeType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_SymbolTypeType#getScalar <em>Scalar</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Scalar</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SymbolTypeType#getScalar()
   * @see #getct_SymbolTypeType()
   * @generated
   */
  EAttribute getct_SymbolTypeType_Scalar();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_SymbolTypeType#getDerivative <em>Derivative</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Derivative</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SymbolTypeType#getDerivative()
   * @see #getct_SymbolTypeType()
   * @generated
   */
  EAttribute getct_SymbolTypeType_Derivative();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_SymbolTypeType#getDistrib <em>Distrib</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Distrib</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SymbolTypeType#getDistrib()
   * @see #getct_SymbolTypeType()
   * @generated
   */
  EAttribute getct_SymbolTypeType_Distrib();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_SymbolTypeType#getDataSet <em>Data Set</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Data Set</em>'.
   * @see org.ddmore.pml.pharmaML.ct_SymbolTypeType#getDataSet()
   * @see #getct_SymbolTypeType()
   * @generated
   */
  EAttribute getct_SymbolTypeType_DataSet();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType <em>ct Variable Definition Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Variable Definition Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableDefinitionType
   * @generated
   */
  EClass getct_VariableDefinitionType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getSymbId()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getSymbolType <em>Symbol Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getSymbolType()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_SymbolType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getIndependentVar <em>Independent Var</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Independent Var</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getIndependentVar()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_IndependentVar();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getXmlns()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Description</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getDescription()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_Description();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getExpr()
   * @see #getct_VariableDefinitionType()
   * @generated
   */
  EReference getct_VariableDefinitionType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_VariableAssignmentType <em>ct Variable Assignment Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Variable Assignment Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableAssignmentType
   * @generated
   */
  EClass getct_VariableAssignmentType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VariableAssignmentType#getSymbId <em>Symb Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symb Id</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableAssignmentType#getSymbId()
   * @see #getct_VariableAssignmentType()
   * @generated
   */
  EReference getct_VariableAssignmentType_SymbId();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VariableAssignmentType#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableAssignmentType#getBlock()
   * @see #getct_VariableAssignmentType()
   * @generated
   */
  EReference getct_VariableAssignmentType_Block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_VariableAssignmentType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableAssignmentType#getXmlns()
   * @see #getct_VariableAssignmentType()
   * @generated
   */
  EReference getct_VariableAssignmentType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_VariableAssignmentType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.ct_VariableAssignmentType#getExpr()
   * @see #getct_VariableAssignmentType()
   * @generated
   */
  EReference getct_VariableAssignmentType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_RestrictionType <em>ct Restriction Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct Restriction Type</em>'.
   * @see org.ddmore.pml.pharmaML.ct_RestrictionType
   * @generated
   */
  EClass getct_RestrictionType();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.pml.pharmaML.ct_RestrictionType#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_RestrictionType#getXmlns()
   * @see #getct_RestrictionType()
   * @generated
   */
  EReference getct_RestrictionType_Xmlns();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.pml.pharmaML.ct_RestrictionType#getExpr <em>Expr</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expr</em>'.
   * @see org.ddmore.pml.pharmaML.ct_RestrictionType#getExpr()
   * @see #getct_RestrictionType()
   * @generated
   */
  EReference getct_RestrictionType_Expr();

  /**
   * Returns the meta object for class '{@link org.ddmore.pml.pharmaML.ct_xmlns <em>ct xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ct xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_xmlns
   * @generated
   */
  EClass getct_xmlns();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.pml.pharmaML.ct_xmlns#getXmlns <em>Xmlns</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Xmlns</em>'.
   * @see org.ddmore.pml.pharmaML.ct_xmlns#getXmlns()
   * @see #getct_xmlns()
   * @generated
   */
  EAttribute getct_xmlns_Xmlns();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  PharmaMLFactory getPharmaMLFactory();

} //PharmaMLPackage

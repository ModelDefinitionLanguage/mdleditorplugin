/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Expr Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_ExprType#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_ExprType#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_ExprType#getUniop <em>Uniop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_ExprType#getBinop <em>Binop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_ExprType#getConstant <em>Constant</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_ExprType#getFunctionCall <em>Function Call</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_ExprType()
 * @model
 * @generated
 */
public interface Math_ExprType extends EObject
{
  /**
   * Returns the value of the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scalar</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scalar</em>' containment reference.
   * @see #setScalar(Math_ScalarType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_ExprType_Scalar()
   * @model containment="true"
   * @generated
   */
  Math_ScalarType getScalar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_ExprType#getScalar <em>Scalar</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scalar</em>' containment reference.
   * @see #getScalar()
   * @generated
   */
  void setScalar(Math_ScalarType value);

  /**
   * Returns the value of the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var</em>' containment reference.
   * @see #setVar(Math_VarType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_ExprType_Var()
   * @model containment="true"
   * @generated
   */
  Math_VarType getVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_ExprType#getVar <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' containment reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Math_VarType value);

  /**
   * Returns the value of the '<em><b>Uniop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Uniop</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Uniop</em>' containment reference.
   * @see #setUniop(Math_UniopType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_ExprType_Uniop()
   * @model containment="true"
   * @generated
   */
  Math_UniopType getUniop();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_ExprType#getUniop <em>Uniop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Uniop</em>' containment reference.
   * @see #getUniop()
   * @generated
   */
  void setUniop(Math_UniopType value);

  /**
   * Returns the value of the '<em><b>Binop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Binop</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Binop</em>' containment reference.
   * @see #setBinop(Math_BinopType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_ExprType_Binop()
   * @model containment="true"
   * @generated
   */
  Math_BinopType getBinop();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_ExprType#getBinop <em>Binop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Binop</em>' containment reference.
   * @see #getBinop()
   * @generated
   */
  void setBinop(Math_BinopType value);

  /**
   * Returns the value of the '<em><b>Constant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constant</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constant</em>' containment reference.
   * @see #setConstant(Math_ConstantType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_ExprType_Constant()
   * @model containment="true"
   * @generated
   */
  Math_ConstantType getConstant();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_ExprType#getConstant <em>Constant</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constant</em>' containment reference.
   * @see #getConstant()
   * @generated
   */
  void setConstant(Math_ConstantType value);

  /**
   * Returns the value of the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Call</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Call</em>' containment reference.
   * @see #setFunctionCall(Math_FunctionCallType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_ExprType_FunctionCall()
   * @model containment="true"
   * @generated
   */
  Math_FunctionCallType getFunctionCall();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_ExprType#getFunctionCall <em>Function Call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function Call</em>' containment reference.
   * @see #getFunctionCall()
   * @generated
   */
  void setFunctionCall(Math_FunctionCallType value);

} // Math_ExprType

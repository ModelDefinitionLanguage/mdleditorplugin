/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Function Argument Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_FunctionArgumentType()
 * @model
 * @generated
 */
public interface Math_FunctionArgumentType extends EObject
{
  /**
   * Returns the value of the '<em><b>Xmlns</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.XS_xmlns}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xmlns</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Xmlns</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_FunctionArgumentType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symb Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symb Id</em>' containment reference.
   * @see #setSymbId(Math_symbId)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_FunctionArgumentType_SymbId()
   * @model containment="true"
   * @generated
   */
  Math_symbId getSymbId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(Math_symbId value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(Math_FuncExprType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_FunctionArgumentType_Expr()
   * @model containment="true"
   * @generated
   */
  Math_FuncExprType getExpr();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(Math_FuncExprType value);

} // Math_FunctionArgumentType

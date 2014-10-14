/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Dosing Variable Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.design_DosingVariableType#getExpr <em>Expr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_DosingVariableType#getAmount <em>Amount</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingVariableType()
 * @model
 * @generated
 */
public interface design_DosingVariableType extends EObject
{
  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(design_DosingVariableTypeContent)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingVariableType_Expr()
   * @model containment="true"
   * @generated
   */
  design_DosingVariableTypeContent getExpr();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_DosingVariableType#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(design_DosingVariableTypeContent value);

  /**
   * Returns the value of the '<em><b>Amount</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Amount</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Amount</em>' containment reference.
   * @see #setAmount(design_AmountType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingVariableType_Amount()
   * @model containment="true"
   * @generated
   */
  design_AmountType getAmount();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_DosingVariableType#getAmount <em>Amount</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Amount</em>' containment reference.
   * @see #getAmount()
   * @generated
   */
  void setAmount(design_AmountType value);

} // design_DosingVariableType

/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Dosing Variable Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.design_DosingVariableType#getDoseVar <em>Dose Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_DosingVariableType#getTargetVar <em>Target Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_DosingVariableType#getAmount <em>Amount</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_DosingVariableType()
 * @model
 * @generated
 */
public interface design_DosingVariableType extends EObject
{
  /**
   * Returns the value of the '<em><b>Dose Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dose Var</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dose Var</em>' containment reference.
   * @see #setDoseVar(design_DoseVarType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_DosingVariableType_DoseVar()
   * @model containment="true"
   * @generated
   */
  design_DoseVarType getDoseVar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_DosingVariableType#getDoseVar <em>Dose Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dose Var</em>' containment reference.
   * @see #getDoseVar()
   * @generated
   */
  void setDoseVar(design_DoseVarType value);

  /**
   * Returns the value of the '<em><b>Target Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Var</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Var</em>' containment reference.
   * @see #setTargetVar(design_TargetVarType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_DosingVariableType_TargetVar()
   * @model containment="true"
   * @generated
   */
  design_TargetVarType getTargetVar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_DosingVariableType#getTargetVar <em>Target Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Var</em>' containment reference.
   * @see #getTargetVar()
   * @generated
   */
  void setTargetVar(design_TargetVarType value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_DosingVariableType_Amount()
   * @model containment="true"
   * @generated
   */
  design_AmountType getAmount();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_DosingVariableType#getAmount <em>Amount</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Amount</em>' containment reference.
   * @see #getAmount()
   * @generated
   */
  void setAmount(design_AmountType value);

} // design_DosingVariableType

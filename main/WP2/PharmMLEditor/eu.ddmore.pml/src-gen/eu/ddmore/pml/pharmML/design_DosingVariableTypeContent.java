/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Dosing Variable Type Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.design_DosingVariableTypeContent#getDoseVar <em>Dose Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_DosingVariableTypeContent#getTargetVar <em>Target Var</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingVariableTypeContent()
 * @model
 * @generated
 */
public interface design_DosingVariableTypeContent extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingVariableTypeContent_DoseVar()
   * @model containment="true"
   * @generated
   */
  design_DoseVarType getDoseVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_DosingVariableTypeContent#getDoseVar <em>Dose Var</em>}' containment reference.
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingVariableTypeContent_TargetVar()
   * @model containment="true"
   * @generated
   */
  design_TargetVarType getTargetVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_DosingVariableTypeContent#getTargetVar <em>Target Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Var</em>' containment reference.
   * @see #getTargetVar()
   * @generated
   */
  void setTargetVar(design_TargetVarType value);

} // design_DosingVariableTypeContent

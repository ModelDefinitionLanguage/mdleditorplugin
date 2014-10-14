/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Group Type Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.design_GroupTypeContent#getTreatmentEpochRef <em>Treatment Epoch Ref</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_GroupTypeContent#getWashout <em>Washout</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_GroupTypeContent()
 * @model
 * @generated
 */
public interface design_GroupTypeContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Treatment Epoch Ref</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Treatment Epoch Ref</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Treatment Epoch Ref</em>' containment reference.
   * @see #setTreatmentEpochRef(design_TreatmentEpochRefType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_GroupTypeContent_TreatmentEpochRef()
   * @model containment="true"
   * @generated
   */
  design_TreatmentEpochRefType getTreatmentEpochRef();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_GroupTypeContent#getTreatmentEpochRef <em>Treatment Epoch Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Treatment Epoch Ref</em>' containment reference.
   * @see #getTreatmentEpochRef()
   * @generated
   */
  void setTreatmentEpochRef(design_TreatmentEpochRefType value);

  /**
   * Returns the value of the '<em><b>Washout</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Washout</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Washout</em>' containment reference.
   * @see #setWashout(design_WashoutType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_GroupTypeContent_Washout()
   * @model containment="true"
   * @generated
   */
  design_WashoutType getWashout();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_GroupTypeContent#getWashout <em>Washout</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Washout</em>' containment reference.
   * @see #getWashout()
   * @generated
   */
  void setWashout(design_WashoutType value);

} // design_GroupTypeContent

/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Bolus Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.design_BolusType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_BolusType#getDoseAmount <em>Dose Amount</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_BolusType#getSteadyState <em>Steady State</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_BolusType#getDosingTimes <em>Dosing Times</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_BolusType()
 * @model
 * @generated
 */
public interface design_BolusType extends EObject
{
  /**
   * Returns the value of the '<em><b>Xmlns</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.ct_xmlns}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xmlns</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Xmlns</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_BolusType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Dose Amount</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dose Amount</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dose Amount</em>' containment reference.
   * @see #setDoseAmount(design_DoseAmountType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_BolusType_DoseAmount()
   * @model containment="true"
   * @generated
   */
  design_DoseAmountType getDoseAmount();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_BolusType#getDoseAmount <em>Dose Amount</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dose Amount</em>' containment reference.
   * @see #getDoseAmount()
   * @generated
   */
  void setDoseAmount(design_DoseAmountType value);

  /**
   * Returns the value of the '<em><b>Steady State</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Steady State</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Steady State</em>' containment reference.
   * @see #setSteadyState(design_SteadyStateType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_BolusType_SteadyState()
   * @model containment="true"
   * @generated
   */
  design_SteadyStateType getSteadyState();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_BolusType#getSteadyState <em>Steady State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Steady State</em>' containment reference.
   * @see #getSteadyState()
   * @generated
   */
  void setSteadyState(design_SteadyStateType value);

  /**
   * Returns the value of the '<em><b>Dosing Times</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dosing Times</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dosing Times</em>' containment reference.
   * @see #setDosingTimes(design_DosingTimesType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_BolusType_DosingTimes()
   * @model containment="true"
   * @generated
   */
  design_DosingTimesType getDosingTimes();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_BolusType#getDosingTimes <em>Dosing Times</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dosing Times</em>' containment reference.
   * @see #getDosingTimes()
   * @generated
   */
  void setDosingTimes(design_DosingTimesType value);

} // design_BolusType

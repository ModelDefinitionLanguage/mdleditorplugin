/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Steady State Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.design_SteadyStateType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_SteadyStateType#getDosingStart <em>Dosing Start</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_SteadyStateType#getDosingPeriod <em>Dosing Period</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_SteadyStateType()
 * @model
 * @generated
 */
public interface design_SteadyStateType extends EObject
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_SteadyStateType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Dosing Start</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dosing Start</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dosing Start</em>' containment reference.
   * @see #setDosingStart(design_DosingStartType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_SteadyStateType_DosingStart()
   * @model containment="true"
   * @generated
   */
  design_DosingStartType getDosingStart();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_SteadyStateType#getDosingStart <em>Dosing Start</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dosing Start</em>' containment reference.
   * @see #getDosingStart()
   * @generated
   */
  void setDosingStart(design_DosingStartType value);

  /**
   * Returns the value of the '<em><b>Dosing Period</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dosing Period</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dosing Period</em>' containment reference.
   * @see #setDosingPeriod(design_DosingPeriodType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_SteadyStateType_DosingPeriod()
   * @model containment="true"
   * @generated
   */
  design_DosingPeriodType getDosingPeriod();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_SteadyStateType#getDosingPeriod <em>Dosing Period</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dosing Period</em>' containment reference.
   * @see #getDosingPeriod()
   * @generated
   */
  void setDosingPeriod(design_DosingPeriodType value);

} // design_SteadyStateType

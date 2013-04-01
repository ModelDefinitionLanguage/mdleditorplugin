/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>uncert Poison</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_Poison#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_Poison#getRate <em>Rate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_Poison()
 * @model
 * @generated
 */
public interface uncert_Poison extends EObject
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_Poison_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Rate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rate</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rate</em>' containment reference.
   * @see #setRate(uncert_Rate)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_Poison_Rate()
   * @model containment="true"
   * @generated
   */
  uncert_Rate getRate();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.uncert_Poison#getRate <em>Rate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rate</em>' containment reference.
   * @see #getRate()
   * @generated
   */
  void setRate(uncert_Rate value);

} // uncert_Poison

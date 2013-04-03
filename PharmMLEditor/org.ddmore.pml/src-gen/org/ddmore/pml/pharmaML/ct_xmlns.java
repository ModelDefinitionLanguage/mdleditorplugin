/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct xmlns</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_xmlns#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_xmlns()
 * @model
 * @generated
 */
public interface ct_xmlns extends EObject
{
  /**
   * Returns the value of the '<em><b>Xmlns</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xmlns</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Xmlns</em>' attribute.
   * @see #setXmlns(String)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_xmlns_Xmlns()
   * @model
   * @generated
   */
  String getXmlns();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_xmlns#getXmlns <em>Xmlns</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Xmlns</em>' attribute.
   * @see #getXmlns()
   * @generated
   */
  void setXmlns(String value);

} // ct_xmlns

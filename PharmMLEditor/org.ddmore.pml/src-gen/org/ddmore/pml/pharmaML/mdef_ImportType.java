/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Import Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ImportType#getResource <em>Resource</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ImportType#getResourceType <em>Resource Type</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ImportType#getSource <em>Source</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ImportType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ImportType#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ImportType()
 * @model
 * @generated
 */
public interface mdef_ImportType extends EObject
{
  /**
   * Returns the value of the '<em><b>Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource</em>' attribute.
   * @see #setResource(String)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ImportType_Resource()
   * @model
   * @generated
   */
  String getResource();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ImportType#getResource <em>Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resource</em>' attribute.
   * @see #getResource()
   * @generated
   */
  void setResource(String value);

  /**
   * Returns the value of the '<em><b>Resource Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource Type</em>' containment reference.
   * @see #setResourceType(mdef_ImportResourceTypeType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ImportType_ResourceType()
   * @model containment="true"
   * @generated
   */
  mdef_ImportResourceTypeType getResourceType();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ImportType#getResourceType <em>Resource Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resource Type</em>' containment reference.
   * @see #getResourceType()
   * @generated
   */
  void setResourceType(mdef_ImportResourceTypeType value);

  /**
   * Returns the value of the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' attribute.
   * @see #setSource(String)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ImportType_Source()
   * @model
   * @generated
   */
  String getSource();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ImportType#getSource <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' attribute.
   * @see #getSource()
   * @generated
   */
  void setSource(String value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ImportType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Link</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.mdef_ImportLinkType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Link</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Link</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ImportType_Link()
   * @model containment="true"
   * @generated
   */
  EList<mdef_ImportLinkType> getLink();

} // mdef_ImportType

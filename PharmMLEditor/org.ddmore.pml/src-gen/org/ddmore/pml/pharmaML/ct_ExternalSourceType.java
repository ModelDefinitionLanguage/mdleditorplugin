/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct External Source Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getFormat <em>Format</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getHeaderLine <em>Header Line</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getIgnoreLineSymbol <em>Ignore Line Symbol</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getUrl <em>Url</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ExternalSourceType()
 * @model
 * @generated
 */
public interface ct_ExternalSourceType extends EObject
{
  /**
   * Returns the value of the '<em><b>Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Format</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Format</em>' attribute.
   * @see #setFormat(String)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ExternalSourceType_Format()
   * @model
   * @generated
   */
  String getFormat();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getFormat <em>Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Format</em>' attribute.
   * @see #getFormat()
   * @generated
   */
  void setFormat(String value);

  /**
   * Returns the value of the '<em><b>Header Line</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Header Line</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Header Line</em>' containment reference.
   * @see #setHeaderLine(EObject)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ExternalSourceType_HeaderLine()
   * @model containment="true"
   * @generated
   */
  EObject getHeaderLine();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getHeaderLine <em>Header Line</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Header Line</em>' containment reference.
   * @see #getHeaderLine()
   * @generated
   */
  void setHeaderLine(EObject value);

  /**
   * Returns the value of the '<em><b>Ignore Line Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ignore Line Symbol</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ignore Line Symbol</em>' attribute.
   * @see #setIgnoreLineSymbol(String)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ExternalSourceType_IgnoreLineSymbol()
   * @model
   * @generated
   */
  String getIgnoreLineSymbol();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getIgnoreLineSymbol <em>Ignore Line Symbol</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ignore Line Symbol</em>' attribute.
   * @see #getIgnoreLineSymbol()
   * @generated
   */
  void setIgnoreLineSymbol(String value);

  /**
   * Returns the value of the '<em><b>Url</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Url</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Url</em>' attribute.
   * @see #setUrl(String)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ExternalSourceType_Url()
   * @model
   * @generated
   */
  String getUrl();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_ExternalSourceType#getUrl <em>Url</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Url</em>' attribute.
   * @see #getUrl()
   * @generated
   */
  void setUrl(String value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ExternalSourceType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

} // ct_ExternalSourceType

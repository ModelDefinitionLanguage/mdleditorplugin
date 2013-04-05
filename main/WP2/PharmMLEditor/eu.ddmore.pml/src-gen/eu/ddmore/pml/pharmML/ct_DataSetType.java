/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct Data Set Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_DataSetType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_DataSetType#getDefinition <em>Definition</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_DataSetType#getExternalSource <em>External Source</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_DataSetType#getRow <em>Row</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_DataSetType#getInternalSource <em>Internal Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_DataSetType()
 * @model
 * @generated
 */
public interface ct_DataSetType extends EObject
{
  /**
   * Returns the value of the '<em><b>Xmlns</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.XS_xmlns}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xmlns</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Xmlns</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_DataSetType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Definition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Definition</em>' containment reference.
   * @see #setDefinition(ct_DefinitionType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_DataSetType_Definition()
   * @model containment="true"
   * @generated
   */
  ct_DefinitionType getDefinition();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_DataSetType#getDefinition <em>Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Definition</em>' containment reference.
   * @see #getDefinition()
   * @generated
   */
  void setDefinition(ct_DefinitionType value);

  /**
   * Returns the value of the '<em><b>External Source</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.ct_ExternalSourceType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>External Source</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>External Source</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_DataSetType_ExternalSource()
   * @model containment="true"
   * @generated
   */
  EList<ct_ExternalSourceType> getExternalSource();

  /**
   * Returns the value of the '<em><b>Row</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.ct_RowType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Row</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Row</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_DataSetType_Row()
   * @model containment="true"
   * @generated
   */
  EList<ct_RowType> getRow();

  /**
   * Returns the value of the '<em><b>Internal Source</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.ct_InternalSourceType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Internal Source</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Internal Source</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_DataSetType_InternalSource()
   * @model containment="true"
   * @generated
   */
  EList<ct_InternalSourceType> getInternalSource();

} // ct_DataSetType

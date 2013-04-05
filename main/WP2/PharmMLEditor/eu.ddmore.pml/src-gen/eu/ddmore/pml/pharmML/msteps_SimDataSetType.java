/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Sim Data Set Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_SimDataSetType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_SimDataSetType#getDataSetRef <em>Data Set Ref</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_SimDataSetType#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_SimDataSetType()
 * @model
 * @generated
 */
public interface msteps_SimDataSetType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_SimDataSetType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Data Set Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Set Ref</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Set Ref</em>' attribute.
   * @see #setDataSetRef(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_SimDataSetType_DataSetRef()
   * @model
   * @generated
   */
  String getDataSetRef();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_SimDataSetType#getDataSetRef <em>Data Set Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Set Ref</em>' attribute.
   * @see #getDataSetRef()
   * @generated
   */
  void setDataSetRef(String value);

  /**
   * Returns the value of the '<em><b>Mapping</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapping</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapping</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_SimDataSetType_Mapping()
   * @model containment="true"
   * @generated
   */
  EList<msteps_ColumnMappingType> getMapping();

} // msteps_SimDataSetType

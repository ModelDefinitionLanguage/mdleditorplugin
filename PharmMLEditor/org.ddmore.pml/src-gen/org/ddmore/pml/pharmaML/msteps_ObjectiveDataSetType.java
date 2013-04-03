/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Objective Data Set Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getDataSetRef <em>Data Set Ref</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_ObjectiveDataSetType()
 * @model
 * @generated
 */
public interface msteps_ObjectiveDataSetType extends EObject
{
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_ObjectiveDataSetType_DataSetRef()
   * @model
   * @generated
   */
  String getDataSetRef();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType#getDataSetRef <em>Data Set Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Set Ref</em>' attribute.
   * @see #getDataSetRef()
   * @generated
   */
  void setDataSetRef(String value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_ObjectiveDataSetType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Mapping</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.msteps_MappingType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mapping</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mapping</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_ObjectiveDataSetType_Mapping()
   * @model containment="true"
   * @generated
   */
  EList<msteps_MappingType> getMapping();

} // msteps_ObjectiveDataSetType

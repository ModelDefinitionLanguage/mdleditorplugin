/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Group Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.design_GroupType#getId <em>Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_GroupType#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_GroupType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_GroupType#getTreatmentEpochRef <em>Treatment Epoch Ref</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_GroupType#getWashout <em>Washout</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_GroupType#getIndividuals <em>Individuals</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_GroupType()
 * @model
 * @generated
 */
public interface design_GroupType extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' containment reference.
   * @see #setId(ct_id)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_GroupType_Id()
   * @model containment="true"
   * @generated
   */
  ct_id getId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_GroupType#getId <em>Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' containment reference.
   * @see #getId()
   * @generated
   */
  void setId(ct_id value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(ct_Name)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_GroupType_Name()
   * @model containment="true"
   * @generated
   */
  ct_Name getName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_GroupType#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_Name value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_GroupType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Treatment Epoch Ref</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.design_TreatmentRefType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Treatment Epoch Ref</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Treatment Epoch Ref</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_GroupType_TreatmentEpochRef()
   * @model containment="true"
   * @generated
   */
  EList<design_TreatmentRefType> getTreatmentEpochRef();

  /**
   * Returns the value of the '<em><b>Washout</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.design_WashoutType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Washout</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Washout</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_GroupType_Washout()
   * @model containment="true"
   * @generated
   */
  EList<design_WashoutType> getWashout();

  /**
   * Returns the value of the '<em><b>Individuals</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Individuals</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Individuals</em>' containment reference.
   * @see #setIndividuals(design_IdividualsType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_GroupType_Individuals()
   * @model containment="true"
   * @generated
   */
  design_IdividualsType getIndividuals();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_GroupType#getIndividuals <em>Individuals</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Individuals</em>' containment reference.
   * @see #getIndividuals()
   * @generated
   */
  void setIndividuals(design_IdividualsType value);

} // design_GroupType

/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Treatment Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentType#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentType#getDosingRegimen <em>Dosing Regimen</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentType()
 * @model
 * @generated
 */
public interface design_TreatmentType extends EObject
{
  /**
   * Returns the value of the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symb Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symb Id</em>' containment reference.
   * @see #setSymbId(Math_symbId)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentType_SymbId()
   * @model containment="true"
   * @generated
   */
  Math_symbId getSymbId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_TreatmentType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(Math_symbId value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentType_Name()
   * @model containment="true"
   * @generated
   */
  ct_Name getName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_TreatmentType#getName <em>Name</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Dosing Regimen</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.design_DosingRegimenType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dosing Regimen</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dosing Regimen</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentType_DosingRegimen()
   * @model containment="true"
   * @generated
   */
  EList<design_DosingRegimenType> getDosingRegimen();

} // design_TreatmentType

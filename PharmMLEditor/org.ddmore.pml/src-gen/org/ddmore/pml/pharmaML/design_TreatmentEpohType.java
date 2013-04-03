/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Treatment Epoh Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getTreatmentRef <em>Treatment Ref</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getStart <em>Start</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getEnd <em>End</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getOccasion <em>Occasion</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentEpohType()
 * @model
 * @generated
 */
public interface design_TreatmentEpohType extends EObject
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
   * @see #setSymbId(ct_symbId)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentEpohType_SymbId()
   * @model containment="true"
   * @generated
   */
  ct_symbId getSymbId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(ct_symbId value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentEpohType_Name()
   * @model containment="true"
   * @generated
   */
  ct_Name getName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getName <em>Name</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentEpohType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Treatment Ref</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.design_TreatmentRefType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Treatment Ref</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Treatment Ref</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentEpohType_TreatmentRef()
   * @model containment="true"
   * @generated
   */
  EList<design_TreatmentRefType> getTreatmentRef();

  /**
   * Returns the value of the '<em><b>Start</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Start</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Start</em>' containment reference.
   * @see #setStart(design_StartType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentEpohType_Start()
   * @model containment="true"
   * @generated
   */
  design_StartType getStart();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getStart <em>Start</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Start</em>' containment reference.
   * @see #getStart()
   * @generated
   */
  void setStart(design_StartType value);

  /**
   * Returns the value of the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' containment reference.
   * @see #setEnd(design_EndType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentEpohType_End()
   * @model containment="true"
   * @generated
   */
  design_EndType getEnd();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getEnd <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' containment reference.
   * @see #getEnd()
   * @generated
   */
  void setEnd(design_EndType value);

  /**
   * Returns the value of the '<em><b>Occasion</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Occasion</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Occasion</em>' containment reference.
   * @see #setOccasion(design_OccasionType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getdesign_TreatmentEpohType_Occasion()
   * @model containment="true"
   * @generated
   */
  design_OccasionType getOccasion();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.design_TreatmentEpohType#getOccasion <em>Occasion</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Occasion</em>' containment reference.
   * @see #getOccasion()
   * @generated
   */
  void setOccasion(design_OccasionType value);

} // design_TreatmentEpohType

/**
 */
package eu.ddmore.pml.pharmML;

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
 *   <li>{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getId <em>Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getTreatmentRef <em>Treatment Ref</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getStart <em>Start</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getEnd <em>End</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getOccasion <em>Occasion</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TreatmentEpohType()
 * @model
 * @generated
 */
public interface design_TreatmentEpohType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TreatmentEpohType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TreatmentEpohType_Id()
   * @model containment="true"
   * @generated
   */
  ct_id getId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getId <em>Id</em>}' containment reference.
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
   * @see #setName(ct_name)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TreatmentEpohType_Name()
   * @model containment="true"
   * @generated
   */
  ct_name getName();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_name value);

  /**
   * Returns the value of the '<em><b>Treatment Ref</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.design_TreatmentRefType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Treatment Ref</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Treatment Ref</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TreatmentEpohType_TreatmentRef()
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TreatmentEpohType_Start()
   * @model containment="true"
   * @generated
   */
  design_StartType getStart();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getStart <em>Start</em>}' containment reference.
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TreatmentEpohType_End()
   * @model containment="true"
   * @generated
   */
  design_EndType getEnd();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_TreatmentEpohType#getEnd <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' containment reference.
   * @see #getEnd()
   * @generated
   */
  void setEnd(design_EndType value);

  /**
   * Returns the value of the '<em><b>Occasion</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.design_OccasionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Occasion</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Occasion</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TreatmentEpohType_Occasion()
   * @model containment="true"
   * @generated
   */
  EList<design_OccasionType> getOccasion();

} // design_TreatmentEpohType

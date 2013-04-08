/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Observation Model Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getId <em>Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getParameter <em>Parameter</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getContinuous <em>Continuous</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ObservationModelType()
 * @model
 * @generated
 */
public interface mdef_ObservationModelType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ObservationModelType_Xmlns()
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ObservationModelType_Id()
   * @model containment="true"
   * @generated
   */
  ct_id getId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getId <em>Id</em>}' containment reference.
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ObservationModelType_Name()
   * @model containment="true"
   * @generated
   */
  ct_name getName();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ObservationModelType#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_name value);

  /**
   * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.mdef_ParameterType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ObservationModelType_Parameter()
   * @model containment="true"
   * @generated
   */
  EList<mdef_ParameterType> getParameter();

  /**
   * Returns the value of the '<em><b>Continuous</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Continuous</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Continuous</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ObservationModelType_Continuous()
   * @model containment="true"
   * @generated
   */
  EList<mdef_ContinuousObsModelType> getContinuous();

} // mdef_ObservationModelType
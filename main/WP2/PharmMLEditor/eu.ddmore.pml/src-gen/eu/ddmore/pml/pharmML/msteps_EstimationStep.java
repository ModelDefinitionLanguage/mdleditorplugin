/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Estimation Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getId <em>Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getObjectiveDataSet <em>Objective Data Set</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getParametersToEstimate <em>Parameters To Estimate</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getEstimationOperation <em>Estimation Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationStep()
 * @model
 * @generated
 */
public interface msteps_EstimationStep extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationStep_Xmlns()
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationStep_Id()
   * @model containment="true"
   * @generated
   */
  ct_id getId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getId <em>Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' containment reference.
   * @see #getId()
   * @generated
   */
  void setId(ct_id value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' containment reference.
   * @see #setDescription(ct_AnnotationType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationStep_Description()
   * @model containment="true"
   * @generated
   */
  ct_AnnotationType getDescription();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getDescription <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' containment reference.
   * @see #getDescription()
   * @generated
   */
  void setDescription(ct_AnnotationType value);

  /**
   * Returns the value of the '<em><b>Initial Value</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.msteps_InitialValueType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initial Value</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initial Value</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationStep_InitialValue()
   * @model containment="true"
   * @generated
   */
  EList<msteps_InitialValueType> getInitialValue();

  /**
   * Returns the value of the '<em><b>Objective Data Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Objective Data Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Objective Data Set</em>' containment reference.
   * @see #setObjectiveDataSet(msteps_ObjectiveDataSetType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationStep_ObjectiveDataSet()
   * @model containment="true"
   * @generated
   */
  msteps_ObjectiveDataSetType getObjectiveDataSet();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getObjectiveDataSet <em>Objective Data Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Objective Data Set</em>' containment reference.
   * @see #getObjectiveDataSet()
   * @generated
   */
  void setObjectiveDataSet(msteps_ObjectiveDataSetType value);

  /**
   * Returns the value of the '<em><b>Parameters To Estimate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters To Estimate</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters To Estimate</em>' containment reference.
   * @see #setParametersToEstimate(msteps_ParametersToEstimateType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationStep_ParametersToEstimate()
   * @model containment="true"
   * @generated
   */
  msteps_ParametersToEstimateType getParametersToEstimate();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_EstimationStep#getParametersToEstimate <em>Parameters To Estimate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameters To Estimate</em>' containment reference.
   * @see #getParametersToEstimate()
   * @generated
   */
  void setParametersToEstimate(msteps_ParametersToEstimateType value);

  /**
   * Returns the value of the '<em><b>Estimation Operation</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.msteps_EstimationOperationType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Estimation Operation</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Estimation Operation</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationStep_EstimationOperation()
   * @model containment="true"
   * @generated
   */
  EList<msteps_EstimationOperationType> getEstimationOperation();

} // msteps_EstimationStep

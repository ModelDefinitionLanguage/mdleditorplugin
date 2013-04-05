/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Modeling Steps Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ModelingStepsContent#getEstimationStep <em>Estimation Step</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ModelingStepsContent#getSimulationStep <em>Simulation Step</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ModelingStepsContent()
 * @model
 * @generated
 */
public interface msteps_ModelingStepsContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Estimation Step</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Estimation Step</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Estimation Step</em>' containment reference.
   * @see #setEstimationStep(msteps_EstimationStep)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ModelingStepsContent_EstimationStep()
   * @model containment="true"
   * @generated
   */
  msteps_EstimationStep getEstimationStep();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ModelingStepsContent#getEstimationStep <em>Estimation Step</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Estimation Step</em>' containment reference.
   * @see #getEstimationStep()
   * @generated
   */
  void setEstimationStep(msteps_EstimationStep value);

  /**
   * Returns the value of the '<em><b>Simulation Step</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Simulation Step</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simulation Step</em>' containment reference.
   * @see #setSimulationStep(msteps_SimulationStep)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ModelingStepsContent_SimulationStep()
   * @model containment="true"
   * @generated
   */
  msteps_SimulationStep getSimulationStep();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ModelingStepsContent#getSimulationStep <em>Simulation Step</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Simulation Step</em>' containment reference.
   * @see #getSimulationStep()
   * @generated
   */
  void setSimulationStep(msteps_SimulationStep value);

} // msteps_ModelingStepsContent

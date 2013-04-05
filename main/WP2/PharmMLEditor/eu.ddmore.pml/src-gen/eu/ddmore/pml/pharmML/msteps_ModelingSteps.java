/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Modeling Steps</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps#getVariable <em>Variable</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps#getExpr <em>Expr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps#getStepDependencies <em>Step Dependencies</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ModelingSteps()
 * @model
 * @generated
 */
public interface msteps_ModelingSteps extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ModelingSteps_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Variable</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.ct_VariableDefinitionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ModelingSteps_Variable()
   * @model containment="true"
   * @generated
   */
  EList<ct_VariableDefinitionType> getVariable();

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.msteps_ModelingStepsContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ModelingSteps_Expr()
   * @model containment="true"
   * @generated
   */
  EList<msteps_ModelingStepsContent> getExpr();

  /**
   * Returns the value of the '<em><b>Step Dependencies</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Step Dependencies</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Step Dependencies</em>' containment reference.
   * @see #setStepDependencies(msteps_StepDependenciesType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ModelingSteps_StepDependencies()
   * @model containment="true"
   * @generated
   */
  msteps_StepDependenciesType getStepDependencies();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ModelingSteps#getStepDependencies <em>Step Dependencies</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Step Dependencies</em>' containment reference.
   * @see #getStepDependencies()
   * @generated
   */
  void setStepDependencies(msteps_StepDependenciesType value);

} // msteps_ModelingSteps

/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mSteps fixed</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mSteps_fixed#getFixed <em>Fixed</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmSteps_fixed()
 * @model
 * @generated
 */
public interface mSteps_fixed extends EObject
{
  /**
   * Returns the value of the '<em><b>Fixed</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fixed</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fixed</em>' containment reference.
   * @see #setFixed(EObject)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmSteps_fixed_Fixed()
   * @model containment="true"
   * @generated
   */
  EObject getFixed();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mSteps_fixed#getFixed <em>Fixed</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fixed</em>' containment reference.
   * @see #getFixed()
   * @generated
   */
  void setFixed(EObject value);

} // mSteps_fixed

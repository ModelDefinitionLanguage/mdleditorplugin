/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Estimation Operation Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_EstimationOperationType#getOpType <em>Op Type</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_EstimationOperationType#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationOperationType()
 * @model
 * @generated
 */
public interface msteps_EstimationOperationType extends EObject
{
  /**
   * Returns the value of the '<em><b>Op Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op Type</em>' containment reference.
   * @see #setOpType(Math_EstimationOperationType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationOperationType_OpType()
   * @model containment="true"
   * @generated
   */
  Math_EstimationOperationType getOpType();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_EstimationOperationType#getOpType <em>Op Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op Type</em>' containment reference.
   * @see #getOpType()
   * @generated
   */
  void setOpType(Math_EstimationOperationType value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_EstimationOperationType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

} // msteps_EstimationOperationType

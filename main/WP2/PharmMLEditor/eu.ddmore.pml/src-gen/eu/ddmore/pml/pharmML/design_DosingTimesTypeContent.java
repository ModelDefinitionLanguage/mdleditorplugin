/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Dosing Times Type Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.design_DosingTimesTypeContent#getSequence <em>Sequence</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_DosingTimesTypeContent#getScalar <em>Scalar</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingTimesTypeContent()
 * @model
 * @generated
 */
public interface design_DosingTimesTypeContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Sequence</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sequence</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sequence</em>' containment reference.
   * @see #setSequence(ct_SequenceType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingTimesTypeContent_Sequence()
   * @model containment="true"
   * @generated
   */
  ct_SequenceType getSequence();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_DosingTimesTypeContent#getSequence <em>Sequence</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sequence</em>' containment reference.
   * @see #getSequence()
   * @generated
   */
  void setSequence(ct_SequenceType value);

  /**
   * Returns the value of the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scalar</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scalar</em>' containment reference.
   * @see #setScalar(Math_ScalarType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingTimesTypeContent_Scalar()
   * @model containment="true"
   * @generated
   */
  Math_ScalarType getScalar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_DosingTimesTypeContent#getScalar <em>Scalar</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scalar</em>' containment reference.
   * @see #getScalar()
   * @generated
   */
  void setScalar(Math_ScalarType value);

} // design_DosingTimesTypeContent

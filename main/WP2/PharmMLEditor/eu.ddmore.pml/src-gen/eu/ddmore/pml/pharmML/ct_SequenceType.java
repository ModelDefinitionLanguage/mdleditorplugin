/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct Sequence Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_SequenceType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_SequenceType#getBegin <em>Begin</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_SequenceType#getEnd <em>End</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_SequenceType#getStepwise <em>Stepwise</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_SequenceType#getRepetition <em>Repetition</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_SequenceType()
 * @model
 * @generated
 */
public interface ct_SequenceType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_SequenceType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Begin</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Begin</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Begin</em>' attribute.
   * @see #setBegin(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_SequenceType_Begin()
   * @model
   * @generated
   */
  String getBegin();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_SequenceType#getBegin <em>Begin</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Begin</em>' attribute.
   * @see #getBegin()
   * @generated
   */
  void setBegin(String value);

  /**
   * Returns the value of the '<em><b>End</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' attribute.
   * @see #setEnd(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_SequenceType_End()
   * @model
   * @generated
   */
  String getEnd();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_SequenceType#getEnd <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' attribute.
   * @see #getEnd()
   * @generated
   */
  void setEnd(String value);

  /**
   * Returns the value of the '<em><b>Stepwise</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Stepwise</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Stepwise</em>' attribute.
   * @see #setStepwise(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_SequenceType_Stepwise()
   * @model
   * @generated
   */
  String getStepwise();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_SequenceType#getStepwise <em>Stepwise</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Stepwise</em>' attribute.
   * @see #getStepwise()
   * @generated
   */
  void setStepwise(String value);

  /**
   * Returns the value of the '<em><b>Repetition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Repetition</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Repetition</em>' attribute.
   * @see #setRepetition(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_SequenceType_Repetition()
   * @model
   * @generated
   */
  String getRepetition();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_SequenceType#getRepetition <em>Repetition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Repetition</em>' attribute.
   * @see #getRepetition()
   * @generated
   */
  void setRepetition(String value);

} // ct_SequenceType

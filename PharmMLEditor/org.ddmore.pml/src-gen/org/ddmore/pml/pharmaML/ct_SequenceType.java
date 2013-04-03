/**
 */
package org.ddmore.pml.pharmaML;

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
 *   <li>{@link org.ddmore.pml.pharmaML.ct_SequenceType#getBegin <em>Begin</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_SequenceType#getEnd <em>End</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_SequenceType#getRepetition <em>Repetition</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_SequenceType#getStepwise <em>Stepwise</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_SequenceType#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_SequenceType()
 * @model
 * @generated
 */
public interface ct_SequenceType extends EObject
{
  /**
   * Returns the value of the '<em><b>Begin</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Begin</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Begin</em>' containment reference.
   * @see #setBegin(ct_Decimal_Value)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_SequenceType_Begin()
   * @model containment="true"
   * @generated
   */
  ct_Decimal_Value getBegin();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_SequenceType#getBegin <em>Begin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Begin</em>' containment reference.
   * @see #getBegin()
   * @generated
   */
  void setBegin(ct_Decimal_Value value);

  /**
   * Returns the value of the '<em><b>End</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>End</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>End</em>' containment reference.
   * @see #setEnd(ct_Decimal_Value)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_SequenceType_End()
   * @model containment="true"
   * @generated
   */
  ct_Decimal_Value getEnd();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_SequenceType#getEnd <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>End</em>' containment reference.
   * @see #getEnd()
   * @generated
   */
  void setEnd(ct_Decimal_Value value);

  /**
   * Returns the value of the '<em><b>Repetition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Repetition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Repetition</em>' containment reference.
   * @see #setRepetition(ct_Int_Value)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_SequenceType_Repetition()
   * @model containment="true"
   * @generated
   */
  ct_Int_Value getRepetition();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_SequenceType#getRepetition <em>Repetition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Repetition</em>' containment reference.
   * @see #getRepetition()
   * @generated
   */
  void setRepetition(ct_Int_Value value);

  /**
   * Returns the value of the '<em><b>Stepwise</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Stepwise</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Stepwise</em>' containment reference.
   * @see #setStepwise(ct_Decimal_Value)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_SequenceType_Stepwise()
   * @model containment="true"
   * @generated
   */
  ct_Decimal_Value getStepwise();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_SequenceType#getStepwise <em>Stepwise</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Stepwise</em>' containment reference.
   * @see #getStepwise()
   * @generated
   */
  void setStepwise(ct_Decimal_Value value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_SequenceType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

} // ct_SequenceType

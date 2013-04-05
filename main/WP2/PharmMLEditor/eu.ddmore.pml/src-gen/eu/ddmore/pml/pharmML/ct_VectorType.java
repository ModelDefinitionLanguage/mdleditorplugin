/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct Vector Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_VectorType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_VectorType#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_VectorType#getSequence <em>Sequence</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_VectorType()
 * @model
 * @generated
 */
public interface ct_VectorType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_VectorType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Scalar</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.Math_ScalarType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scalar</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scalar</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_VectorType_Scalar()
   * @model containment="true"
   * @generated
   */
  EList<Math_ScalarType> getScalar();

  /**
   * Returns the value of the '<em><b>Sequence</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.ct_SequenceType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sequence</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sequence</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_VectorType_Sequence()
   * @model containment="true"
   * @generated
   */
  EList<ct_SequenceType> getSequence();

} // ct_VectorType

/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Estimation Operation Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstPop <em>Est Pop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstFIM <em>Est FIM</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstIndiv <em>Est Indiv</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EstimationOperationType()
 * @model
 * @generated
 */
public interface Math_EstimationOperationType extends EObject
{
  /**
   * Returns the value of the '<em><b>Est Pop</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Est Pop</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Est Pop</em>' attribute.
   * @see #setEstPop(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EstimationOperationType_EstPop()
   * @model
   * @generated
   */
  String getEstPop();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstPop <em>Est Pop</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Est Pop</em>' attribute.
   * @see #getEstPop()
   * @generated
   */
  void setEstPop(String value);

  /**
   * Returns the value of the '<em><b>Est FIM</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Est FIM</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Est FIM</em>' attribute.
   * @see #setEstFIM(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EstimationOperationType_EstFIM()
   * @model
   * @generated
   */
  String getEstFIM();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstFIM <em>Est FIM</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Est FIM</em>' attribute.
   * @see #getEstFIM()
   * @generated
   */
  void setEstFIM(String value);

  /**
   * Returns the value of the '<em><b>Est Indiv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Est Indiv</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Est Indiv</em>' attribute.
   * @see #setEstIndiv(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EstimationOperationType_EstIndiv()
   * @model
   * @generated
   */
  String getEstIndiv();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_EstimationOperationType#getEstIndiv <em>Est Indiv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Est Indiv</em>' attribute.
   * @see #getEstIndiv()
   * @generated
   */
  void setEstIndiv(String value);

} // Math_EstimationOperationType

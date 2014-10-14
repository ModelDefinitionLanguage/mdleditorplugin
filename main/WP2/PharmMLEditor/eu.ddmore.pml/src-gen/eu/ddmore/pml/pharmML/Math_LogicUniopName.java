/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Logic Uniop Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicUniopName#getIsDefined <em>Is Defined</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicUniopName#getNot <em>Not</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicUniopName()
 * @model
 * @generated
 */
public interface Math_LogicUniopName extends EObject
{
  /**
   * Returns the value of the '<em><b>Is Defined</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Is Defined</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Is Defined</em>' attribute.
   * @see #setIsDefined(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicUniopName_IsDefined()
   * @model
   * @generated
   */
  String getIsDefined();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicUniopName#getIsDefined <em>Is Defined</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Defined</em>' attribute.
   * @see #getIsDefined()
   * @generated
   */
  void setIsDefined(String value);

  /**
   * Returns the value of the '<em><b>Not</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Not</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Not</em>' attribute.
   * @see #setNot(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicUniopName_Not()
   * @model
   * @generated
   */
  String getNot();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicUniopName#getNot <em>Not</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Not</em>' attribute.
   * @see #getNot()
   * @generated
   */
  void setNot(String value);

} // Math_LogicUniopName

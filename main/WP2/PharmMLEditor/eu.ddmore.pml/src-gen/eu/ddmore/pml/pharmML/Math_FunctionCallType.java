/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Function Call Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_FunctionCallType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_FunctionCallType#getFunctionname <em>Functionname</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_FunctionCallType#getFunctionArguments <em>Function Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_FunctionCallType()
 * @model
 * @generated
 */
public interface Math_FunctionCallType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_FunctionCallType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Functionname</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Functionname</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Functionname</em>' containment reference.
   * @see #setFunctionname(Math_VarType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_FunctionCallType_Functionname()
   * @model containment="true"
   * @generated
   */
  Math_VarType getFunctionname();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_FunctionCallType#getFunctionname <em>Functionname</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Functionname</em>' containment reference.
   * @see #getFunctionname()
   * @generated
   */
  void setFunctionname(Math_VarType value);

  /**
   * Returns the value of the '<em><b>Function Arguments</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.Math_FunctionArgumentType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Arguments</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Arguments</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_FunctionCallType_FunctionArguments()
   * @model containment="true"
   * @generated
   */
  EList<Math_FunctionArgumentType> getFunctionArguments();

} // Math_FunctionCallType

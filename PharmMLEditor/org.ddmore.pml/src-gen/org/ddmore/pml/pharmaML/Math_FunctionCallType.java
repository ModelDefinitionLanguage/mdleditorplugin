/**
 */
package org.ddmore.pml.pharmaML;

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
 *   <li>{@link org.ddmore.pml.pharmaML.Math_FunctionCallType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Math_FunctionCallType#getFunctionName <em>Function Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Math_FunctionCallType#getFunctionArguments <em>Function Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_FunctionCallType()
 * @model
 * @generated
 */
public interface Math_FunctionCallType extends EObject
{
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_FunctionCallType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Function Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Name</em>' containment reference.
   * @see #setFunctionName(Math_VarType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_FunctionCallType_FunctionName()
   * @model containment="true"
   * @generated
   */
  Math_VarType getFunctionName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Math_FunctionCallType#getFunctionName <em>Function Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function Name</em>' containment reference.
   * @see #getFunctionName()
   * @generated
   */
  void setFunctionName(Math_VarType value);

  /**
   * Returns the value of the '<em><b>Function Arguments</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.Math_FunctionArgumentType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Arguments</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Arguments</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_FunctionCallType_FunctionArguments()
   * @model containment="true"
   * @generated
   */
  EList<Math_FunctionArgumentType> getFunctionArguments();

} // Math_FunctionCallType

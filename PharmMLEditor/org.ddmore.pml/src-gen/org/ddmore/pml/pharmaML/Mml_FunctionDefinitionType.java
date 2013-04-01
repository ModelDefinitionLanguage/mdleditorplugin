/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mml Function Definition Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getFunctionArguments <em>Function Arguments</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getDefinition <em>Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_FunctionDefinitionType()
 * @model
 * @generated
 */
public interface Mml_FunctionDefinitionType extends EObject
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_FunctionDefinitionType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Function Arguments</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Arguments</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Arguments</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_FunctionDefinitionType_FunctionArguments()
   * @model containment="true"
   * @generated
   */
  EList<Mml_FuncParameterDefinitionType> getFunctionArguments();

  /**
   * Returns the value of the '<em><b>Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Definition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Definition</em>' containment reference.
   * @see #setDefinition(ct_DefinitionRhsType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_FunctionDefinitionType_Definition()
   * @model containment="true"
   * @generated
   */
  ct_DefinitionRhsType getDefinition();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType#getDefinition <em>Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Definition</em>' containment reference.
   * @see #getDefinition()
   * @generated
   */
  void setDefinition(ct_DefinitionRhsType value);

} // Mml_FunctionDefinitionType

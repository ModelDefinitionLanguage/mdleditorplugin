/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Individual Variables Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.IndividualVariablesBlock#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.IndividualVariablesBlock#getStatements <em>Statements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getIndividualVariablesBlock()
 * @model
 * @generated
 */
public interface IndividualVariablesBlock extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifier</em>' attribute.
   * @see #setIdentifier(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getIndividualVariablesBlock_Identifier()
   * @model
   * @generated
   */
  String getIdentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.IndividualVariablesBlock#getIdentifier <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' attribute.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Statements</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.BlockStatement}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statements</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getIndividualVariablesBlock_Statements()
   * @model containment="true"
   * @generated
   */
  EList<BlockStatement> getStatements();

} // IndividualVariablesBlock

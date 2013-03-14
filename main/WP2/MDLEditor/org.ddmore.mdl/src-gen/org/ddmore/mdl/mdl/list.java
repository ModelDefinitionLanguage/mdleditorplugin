/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>list</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.list#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getlist()
 * @model
 * @generated
 */
public interface list extends EObject
{
  /**
   * Returns the value of the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arguments</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arguments</em>' containment reference.
   * @see #setArguments(arguments)
   * @see org.ddmore.mdl.mdl.MdlPackage#getlist_Arguments()
   * @model containment="true"
   * @generated
   */
  arguments getArguments();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.list#getArguments <em>Arguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arguments</em>' containment reference.
   * @see #getArguments()
   * @generated
   */
  void setArguments(arguments value);

} // list

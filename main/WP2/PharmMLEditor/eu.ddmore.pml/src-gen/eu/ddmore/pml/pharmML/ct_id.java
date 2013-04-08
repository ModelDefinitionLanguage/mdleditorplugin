/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct id</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_id#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_id()
 * @model
 * @generated
 */
public interface ct_id extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_id_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_id#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

} // ct_id
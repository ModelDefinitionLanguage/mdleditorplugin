/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct level Id</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_levelId#getLevelId <em>Level Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_levelId()
 * @model
 * @generated
 */
public interface ct_levelId extends EObject
{
  /**
   * Returns the value of the '<em><b>Level Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Level Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Level Id</em>' attribute.
   * @see #setLevelId(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_levelId_LevelId()
   * @model
   * @generated
   */
  String getLevelId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_levelId#getLevelId <em>Level Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Level Id</em>' attribute.
   * @see #getLevelId()
   * @generated
   */
  void setLevelId(String value);

} // ct_levelId

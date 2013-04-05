/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_transformation#getTransformation <em>Transformation</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_transformation()
 * @model
 * @generated
 */
public interface mdef_transformation extends EObject
{
  /**
   * Returns the value of the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transformation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transformation</em>' containment reference.
   * @see #setTransformation(mdef_ParameterTypeName)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_transformation_Transformation()
   * @model containment="true"
   * @generated
   */
  mdef_ParameterTypeName getTransformation();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_transformation#getTransformation <em>Transformation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Transformation</em>' containment reference.
   * @see #getTransformation()
   * @generated
   */
  void setTransformation(mdef_ParameterTypeName value);

} // mdef_transformation

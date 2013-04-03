/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Use Variability Level</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel#getLevelId <em>Level Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_UseVariabilityLevel()
 * @model
 * @generated
 */
public interface msteps_UseVariabilityLevel extends EObject
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_UseVariabilityLevel_LevelId()
   * @model
   * @generated
   */
  String getLevelId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel#getLevelId <em>Level Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Level Id</em>' attribute.
   * @see #getLevelId()
   * @generated
   */
  void setLevelId(String value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_UseVariabilityLevel_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

} // msteps_UseVariabilityLevel

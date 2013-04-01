/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Continuous Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ContinuousType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ContinuousType#getDistribution <em>Distribution</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ContinuousType#getTransformation <em>Transformation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ContinuousType()
 * @model
 * @generated
 */
public interface mdef_ContinuousType extends EObject
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ContinuousType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Distribution</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Distribution</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Distribution</em>' containment reference.
   * @see #setDistribution(uncert_DistributionType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ContinuousType_Distribution()
   * @model containment="true"
   * @generated
   */
  uncert_DistributionType getDistribution();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ContinuousType#getDistribution <em>Distribution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Distribution</em>' containment reference.
   * @see #getDistribution()
   * @generated
   */
  void setDistribution(uncert_DistributionType value);

  /**
   * Returns the value of the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transformation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transformation</em>' containment reference.
   * @see #setTransformation(mdef_TransformationType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ContinuousType_Transformation()
   * @model containment="true"
   * @generated
   */
  mdef_TransformationType getTransformation();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ContinuousType#getTransformation <em>Transformation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Transformation</em>' containment reference.
   * @see #getTransformation()
   * @generated
   */
  void setTransformation(mdef_TransformationType value);

} // mdef_ContinuousType

/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Step Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_StepType#getIdRef <em>Id Ref</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_StepType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_StepType#getDependantStep <em>Dependant Step</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_StepType()
 * @model
 * @generated
 */
public interface msteps_StepType extends EObject
{
  /**
   * Returns the value of the '<em><b>Id Ref</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id Ref</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id Ref</em>' attribute.
   * @see #setIdRef(String)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_StepType_IdRef()
   * @model
   * @generated
   */
  String getIdRef();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_StepType#getIdRef <em>Id Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id Ref</em>' attribute.
   * @see #getIdRef()
   * @generated
   */
  void setIdRef(String value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_StepType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Dependant Step</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.msteps_DependantStepType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dependant Step</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dependant Step</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_StepType_DependantStep()
   * @model containment="true"
   * @generated
   */
  EList<msteps_DependantStepType> getDependantStep();

} // msteps_StepType

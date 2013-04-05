/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Observations Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ObservationsType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ObservationsType#getTimepoints <em>Timepoints</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ObservationsType#getOutput <em>Output</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ObservationsType#getWriteTo <em>Write To</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ObservationsType()
 * @model
 * @generated
 */
public interface msteps_ObservationsType extends EObject
{
  /**
   * Returns the value of the '<em><b>Xmlns</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.XS_xmlns}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xmlns</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Xmlns</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ObservationsType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Timepoints</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Timepoints</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Timepoints</em>' containment reference.
   * @see #setTimepoints(msteps_TimepointsType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ObservationsType_Timepoints()
   * @model containment="true"
   * @generated
   */
  msteps_TimepointsType getTimepoints();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ObservationsType#getTimepoints <em>Timepoints</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Timepoints</em>' containment reference.
   * @see #getTimepoints()
   * @generated
   */
  void setTimepoints(msteps_TimepointsType value);

  /**
   * Returns the value of the '<em><b>Output</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.msteps_OutputType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ObservationsType_Output()
   * @model containment="true"
   * @generated
   */
  EList<msteps_OutputType> getOutput();

  /**
   * Returns the value of the '<em><b>Write To</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.msteps_WriteToType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Write To</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Write To</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ObservationsType_WriteTo()
   * @model containment="true"
   * @generated
   */
  EList<msteps_WriteToType> getWriteTo();

} // msteps_ObservationsType

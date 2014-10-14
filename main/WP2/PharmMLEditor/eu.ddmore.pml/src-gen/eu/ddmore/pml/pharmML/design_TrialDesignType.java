/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Trial Design Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TrialDesignType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TrialDesignType#getTreatment <em>Treatment</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TrialDesignType#getTreatmentEpoch <em>Treatment Epoch</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_TrialDesignType#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TrialDesignType()
 * @model
 * @generated
 */
public interface design_TrialDesignType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TrialDesignType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Treatment</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.design_TreatmentType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Treatment</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Treatment</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TrialDesignType_Treatment()
   * @model containment="true"
   * @generated
   */
  EList<design_TreatmentType> getTreatment();

  /**
   * Returns the value of the '<em><b>Treatment Epoch</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.design_TreatmentEpohType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Treatment Epoch</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Treatment Epoch</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TrialDesignType_TreatmentEpoch()
   * @model containment="true"
   * @generated
   */
  EList<design_TreatmentEpohType> getTreatmentEpoch();

  /**
   * Returns the value of the '<em><b>Group</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.design_GroupType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_TrialDesignType_Group()
   * @model containment="true"
   * @generated
   */
  EList<design_GroupType> getGroup();

} // design_TrialDesignType

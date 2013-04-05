/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Dosing Regimen Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.design_DosingRegimenType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_DosingRegimenType#getBolus <em>Bolus</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_DosingRegimenType#getInfusion <em>Infusion</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingRegimenType()
 * @model
 * @generated
 */
public interface design_DosingRegimenType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingRegimenType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Bolus</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bolus</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bolus</em>' containment reference.
   * @see #setBolus(design_BolusType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingRegimenType_Bolus()
   * @model containment="true"
   * @generated
   */
  design_BolusType getBolus();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_DosingRegimenType#getBolus <em>Bolus</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bolus</em>' containment reference.
   * @see #getBolus()
   * @generated
   */
  void setBolus(design_BolusType value);

  /**
   * Returns the value of the '<em><b>Infusion</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Infusion</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Infusion</em>' containment reference.
   * @see #setInfusion(design_InfusionType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_DosingRegimenType_Infusion()
   * @model containment="true"
   * @generated
   */
  design_InfusionType getInfusion();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_DosingRegimenType#getInfusion <em>Infusion</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Infusion</em>' containment reference.
   * @see #getInfusion()
   * @generated
   */
  void setInfusion(design_InfusionType value);

} // design_DosingRegimenType

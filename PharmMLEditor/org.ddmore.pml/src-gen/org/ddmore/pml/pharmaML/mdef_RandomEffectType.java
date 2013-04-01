/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Random Effect Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getLevelId <em>Level Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getDistribution <em>Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_RandomEffectType()
 * @model
 * @generated
 */
public interface mdef_RandomEffectType extends EObject
{
  /**
   * Returns the value of the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Level Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Level Id</em>' containment reference.
   * @see #setLevelId(ct_levelId)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_RandomEffectType_LevelId()
   * @model containment="true"
   * @generated
   */
  ct_levelId getLevelId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getLevelId <em>Level Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Level Id</em>' containment reference.
   * @see #getLevelId()
   * @generated
   */
  void setLevelId(ct_levelId value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(ct_Name)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_RandomEffectType_Name()
   * @model containment="true"
   * @generated
   */
  ct_Name getName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_Name value);

  /**
   * Returns the value of the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symb Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symb Id</em>' containment reference.
   * @see #setSymbId(ct_symbId)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_RandomEffectType_SymbId()
   * @model containment="true"
   * @generated
   */
  ct_symbId getSymbId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(ct_symbId value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_RandomEffectType_Xmlns()
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_RandomEffectType_Distribution()
   * @model containment="true"
   * @generated
   */
  uncert_DistributionType getDistribution();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_RandomEffectType#getDistribution <em>Distribution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Distribution</em>' containment reference.
   * @see #getDistribution()
   * @generated
   */
  void setDistribution(uncert_DistributionType value);

} // mdef_RandomEffectType

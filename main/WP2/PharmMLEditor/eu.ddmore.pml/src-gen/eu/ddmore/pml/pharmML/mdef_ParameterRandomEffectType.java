/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Parameter Random Effect Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getLevelId <em>Level Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getDistribution <em>Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterRandomEffectType()
 * @model
 * @generated
 */
public interface mdef_ParameterRandomEffectType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterRandomEffectType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterRandomEffectType_LevelId()
   * @model containment="true"
   * @generated
   */
  ct_levelId getLevelId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getLevelId <em>Level Id</em>}' containment reference.
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
   * @see #setName(ct_name)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterRandomEffectType_Name()
   * @model containment="true"
   * @generated
   */
  ct_name getName();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_name value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterRandomEffectType_SymbId()
   * @model containment="true"
   * @generated
   */
  ct_symbId getSymbId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(ct_symbId value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterRandomEffectType_Distribution()
   * @model containment="true"
   * @generated
   */
  uncert_DistributionType getDistribution();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType#getDistribution <em>Distribution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Distribution</em>' containment reference.
   * @see #getDistribution()
   * @generated
   */
  void setDistribution(uncert_DistributionType value);

} // mdef_ParameterRandomEffectType

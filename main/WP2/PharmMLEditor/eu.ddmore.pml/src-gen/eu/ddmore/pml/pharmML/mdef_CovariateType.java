/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Covariate Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_CovariateType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_CovariateType#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_CovariateType#getFixedEffect <em>Fixed Effect</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_CovariateType()
 * @model
 * @generated
 */
public interface mdef_CovariateType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_CovariateType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var</em>' containment reference.
   * @see #setVar(Math_VarType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_CovariateType_Var()
   * @model containment="true"
   * @generated
   */
  Math_VarType getVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_CovariateType#getVar <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' containment reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Math_VarType value);

  /**
   * Returns the value of the '<em><b>Fixed Effect</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.mdef_FixedEffectType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fixed Effect</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fixed Effect</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_CovariateType_FixedEffect()
   * @model containment="true"
   * @generated
   */
  EList<mdef_FixedEffectType> getFixedEffect();

} // mdef_CovariateType

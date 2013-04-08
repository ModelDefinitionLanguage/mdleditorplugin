/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Fixed Effect Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_FixedEffectType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_FixedEffectType#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_FixedEffectType#getCategory <em>Category</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_FixedEffectType()
 * @model
 * @generated
 */
public interface mdef_FixedEffectType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_FixedEffectType_Xmlns()
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_FixedEffectType_Var()
   * @model containment="true"
   * @generated
   */
  Math_VarType getVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_FixedEffectType#getVar <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' containment reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Math_VarType value);

  /**
   * Returns the value of the '<em><b>Category</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Category</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category</em>' containment reference.
   * @see #setCategory(mdef_CategoryType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_FixedEffectType_Category()
   * @model containment="true"
   * @generated
   */
  mdef_CategoryType getCategory();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_FixedEffectType#getCategory <em>Category</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category</em>' containment reference.
   * @see #getCategory()
   * @generated
   */
  void setCategory(mdef_CategoryType value);

} // mdef_FixedEffectType
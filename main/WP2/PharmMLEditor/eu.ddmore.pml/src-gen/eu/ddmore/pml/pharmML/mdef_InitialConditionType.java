/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Initial Condition Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getBlock <em>Block</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getSymbID <em>Symb ID</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_InitialConditionType()
 * @model
 * @generated
 */
public interface mdef_InitialConditionType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_InitialConditionType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Block</em>' containment reference.
   * @see #setBlock(Math_block)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_InitialConditionType_Block()
   * @model containment="true"
   * @generated
   */
  Math_block getBlock();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getBlock <em>Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Block</em>' containment reference.
   * @see #getBlock()
   * @generated
   */
  void setBlock(Math_block value);

  /**
   * Returns the value of the '<em><b>Symb ID</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symb ID</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symb ID</em>' containment reference.
   * @see #setSymbID(Math_symbId)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_InitialConditionType_SymbID()
   * @model containment="true"
   * @generated
   */
  Math_symbId getSymbID();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getSymbID <em>Symb ID</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb ID</em>' containment reference.
   * @see #getSymbID()
   * @generated
   */
  void setSymbID(Math_symbId value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(ct_Rhs)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_InitialConditionType_Expr()
   * @model containment="true"
   * @generated
   */
  ct_Rhs getExpr();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_InitialConditionType#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(ct_Rhs value);

} // mdef_InitialConditionType

/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Assign Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_AssignType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_AssignType#getBlock <em>Block</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_AssignType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_AssignType#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_AssignType()
 * @model
 * @generated
 */
public interface msteps_AssignType extends EObject
{
  /**
   * Returns the value of the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symb Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symb Id</em>' containment reference.
   * @see #setSymbId(Math_symbId)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_AssignType_SymbId()
   * @model containment="true"
   * @generated
   */
  Math_symbId getSymbId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_AssignType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(Math_symbId value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_AssignType_Block()
   * @model containment="true"
   * @generated
   */
  Math_block getBlock();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_AssignType#getBlock <em>Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Block</em>' containment reference.
   * @see #getBlock()
   * @generated
   */
  void setBlock(Math_block value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_AssignType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_AssignType_Expr()
   * @model containment="true"
   * @generated
   */
  ct_Rhs getExpr();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_AssignType#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(ct_Rhs value);

} // msteps_AssignType

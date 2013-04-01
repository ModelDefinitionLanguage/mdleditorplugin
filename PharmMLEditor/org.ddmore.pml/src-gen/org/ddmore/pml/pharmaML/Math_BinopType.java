/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Binop Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.Math_BinopType#getOp <em>Op</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Math_BinopType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Math_BinopType#getLeftExpr <em>Left Expr</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Math_BinopType#getRightExpr <em>Right Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_BinopType()
 * @model
 * @generated
 */
public interface Math_BinopType extends EObject
{
  /**
   * Returns the value of the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' containment reference.
   * @see #setOp(Math_BinopName)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_BinopType_Op()
   * @model containment="true"
   * @generated
   */
  Math_BinopName getOp();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Math_BinopType#getOp <em>Op</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' containment reference.
   * @see #getOp()
   * @generated
   */
  void setOp(Math_BinopName value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_BinopType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Left Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Left Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left Expr</em>' containment reference.
   * @see #setLeftExpr(Math_ExprType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_BinopType_LeftExpr()
   * @model containment="true"
   * @generated
   */
  Math_ExprType getLeftExpr();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Math_BinopType#getLeftExpr <em>Left Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left Expr</em>' containment reference.
   * @see #getLeftExpr()
   * @generated
   */
  void setLeftExpr(Math_ExprType value);

  /**
   * Returns the value of the '<em><b>Right Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Right Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right Expr</em>' containment reference.
   * @see #setRightExpr(Math_ExprType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_BinopType_RightExpr()
   * @model containment="true"
   * @generated
   */
  Math_ExprType getRightExpr();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Math_BinopType#getRightExpr <em>Right Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right Expr</em>' containment reference.
   * @see #getRightExpr()
   * @generated
   */
  void setRightExpr(Math_ExprType value);

} // Math_BinopType

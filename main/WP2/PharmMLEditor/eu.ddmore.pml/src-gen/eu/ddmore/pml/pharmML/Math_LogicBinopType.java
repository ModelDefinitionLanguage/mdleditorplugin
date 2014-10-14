/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Logic Binop Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getOp <em>Op</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getLeftExpr <em>Left Expr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getRightExpr <em>Right Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBinopType()
 * @model
 * @generated
 */
public interface Math_LogicBinopType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBinopType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Op</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Op</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Op</em>' containment reference.
   * @see #setOp(Math_LogicBinopName)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBinopType_Op()
   * @model containment="true"
   * @generated
   */
  Math_LogicBinopName getOp();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getOp <em>Op</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Op</em>' containment reference.
   * @see #getOp()
   * @generated
   */
  void setOp(Math_LogicBinopName value);

  /**
   * Returns the value of the '<em><b>Left Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Left Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Left Expr</em>' containment reference.
   * @see #setLeftExpr(Math_LogicExprType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBinopType_LeftExpr()
   * @model containment="true"
   * @generated
   */
  Math_LogicExprType getLeftExpr();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getLeftExpr <em>Left Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Left Expr</em>' containment reference.
   * @see #getLeftExpr()
   * @generated
   */
  void setLeftExpr(Math_LogicExprType value);

  /**
   * Returns the value of the '<em><b>Right Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Right Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Right Expr</em>' containment reference.
   * @see #setRightExpr(Math_LogicExprType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBinopType_RightExpr()
   * @model containment="true"
   * @generated
   */
  Math_LogicExprType getRightExpr();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicBinopType#getRightExpr <em>Right Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Right Expr</em>' containment reference.
   * @see #getRightExpr()
   * @generated
   */
  void setRightExpr(Math_LogicExprType value);

} // Math_LogicBinopType

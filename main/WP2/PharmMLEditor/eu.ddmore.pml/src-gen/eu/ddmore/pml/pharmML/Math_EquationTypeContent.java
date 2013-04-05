/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Equation Type Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getBinop <em>Binop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getUniop <em>Uniop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getPiecewise <em>Piecewise</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EquationTypeContent()
 * @model
 * @generated
 */
public interface Math_EquationTypeContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scalar</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scalar</em>' containment reference.
   * @see #setScalar(Math_ScalarType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EquationTypeContent_Scalar()
   * @model containment="true"
   * @generated
   */
  Math_ScalarType getScalar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getScalar <em>Scalar</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scalar</em>' containment reference.
   * @see #getScalar()
   * @generated
   */
  void setScalar(Math_ScalarType value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EquationTypeContent_Var()
   * @model containment="true"
   * @generated
   */
  Math_VarType getVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getVar <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' containment reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Math_VarType value);

  /**
   * Returns the value of the '<em><b>Binop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Binop</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Binop</em>' containment reference.
   * @see #setBinop(Math_BinopType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EquationTypeContent_Binop()
   * @model containment="true"
   * @generated
   */
  Math_BinopType getBinop();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getBinop <em>Binop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Binop</em>' containment reference.
   * @see #getBinop()
   * @generated
   */
  void setBinop(Math_BinopType value);

  /**
   * Returns the value of the '<em><b>Uniop</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Uniop</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Uniop</em>' containment reference.
   * @see #setUniop(Math_UniopType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EquationTypeContent_Uniop()
   * @model containment="true"
   * @generated
   */
  Math_UniopType getUniop();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getUniop <em>Uniop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Uniop</em>' containment reference.
   * @see #getUniop()
   * @generated
   */
  void setUniop(Math_UniopType value);

  /**
   * Returns the value of the '<em><b>Piecewise</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Piecewise</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Piecewise</em>' containment reference.
   * @see #setPiecewise(Math_PiecewiseType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_EquationTypeContent_Piecewise()
   * @model containment="true"
   * @generated
   */
  Math_PiecewiseType getPiecewise();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_EquationTypeContent#getPiecewise <em>Piecewise</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Piecewise</em>' containment reference.
   * @see #getPiecewise()
   * @generated
   */
  void setPiecewise(Math_PiecewiseType value);

} // Math_EquationTypeContent

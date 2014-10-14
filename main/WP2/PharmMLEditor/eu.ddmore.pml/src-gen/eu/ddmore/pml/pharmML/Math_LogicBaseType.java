/**
 */
package eu.ddmore.pml.pharmML;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Logic Base Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getLogicBinopType <em>Logic Binop Type</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getLogicUniopType <em>Logic Uniop Type</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getTrue <em>True</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getFalse <em>False</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getOtherwise <em>Otherwise</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBaseType()
 * @model
 * @generated
 */
public interface Math_LogicBaseType extends Math_LogicExprType
{
  /**
   * Returns the value of the '<em><b>Logic Binop Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Logic Binop Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Logic Binop Type</em>' containment reference.
   * @see #setLogicBinopType(Math_LogicBinopType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBaseType_LogicBinopType()
   * @model containment="true"
   * @generated
   */
  Math_LogicBinopType getLogicBinopType();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getLogicBinopType <em>Logic Binop Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Logic Binop Type</em>' containment reference.
   * @see #getLogicBinopType()
   * @generated
   */
  void setLogicBinopType(Math_LogicBinopType value);

  /**
   * Returns the value of the '<em><b>Logic Uniop Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Logic Uniop Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Logic Uniop Type</em>' containment reference.
   * @see #setLogicUniopType(Math_LogicUniopType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBaseType_LogicUniopType()
   * @model containment="true"
   * @generated
   */
  Math_LogicUniopType getLogicUniopType();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getLogicUniopType <em>Logic Uniop Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Logic Uniop Type</em>' containment reference.
   * @see #getLogicUniopType()
   * @generated
   */
  void setLogicUniopType(Math_LogicUniopType value);

  /**
   * Returns the value of the '<em><b>True</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>True</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>True</em>' containment reference.
   * @see #setTrue(Math_TRUE)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBaseType_True()
   * @model containment="true"
   * @generated
   */
  Math_TRUE getTrue();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getTrue <em>True</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>True</em>' containment reference.
   * @see #getTrue()
   * @generated
   */
  void setTrue(Math_TRUE value);

  /**
   * Returns the value of the '<em><b>False</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>False</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>False</em>' containment reference.
   * @see #setFalse(Math_FALSE)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBaseType_False()
   * @model containment="true"
   * @generated
   */
  Math_FALSE getFalse();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getFalse <em>False</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>False</em>' containment reference.
   * @see #getFalse()
   * @generated
   */
  void setFalse(Math_FALSE value);

  /**
   * Returns the value of the '<em><b>Otherwise</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Otherwise</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Otherwise</em>' containment reference.
   * @see #setOtherwise(Math_Otherwise)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMath_LogicBaseType_Otherwise()
   * @model containment="true"
   * @generated
   */
  Math_Otherwise getOtherwise();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Math_LogicBaseType#getOtherwise <em>Otherwise</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Otherwise</em>' containment reference.
   * @see #getOtherwise()
   * @generated
   */
  void setOtherwise(Math_Otherwise value);

} // Math_LogicBaseType

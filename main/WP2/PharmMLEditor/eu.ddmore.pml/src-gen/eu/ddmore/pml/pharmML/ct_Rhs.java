/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct Rhs</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getEquation <em>Equation</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getConstant <em>Constant</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getString <em>String</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getSequence <em>Sequence</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getVector <em>Vector</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getFunctionCall <em>Function Call</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getDataSet <em>Data Set</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_Rhs#getDistribution <em>Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs()
 * @model
 * @generated
 */
public interface ct_Rhs extends EObject
{
  /**
   * Returns the value of the '<em><b>Equation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Equation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Equation</em>' containment reference.
   * @see #setEquation(Math_EquationType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_Equation()
   * @model containment="true"
   * @generated
   */
  Math_EquationType getEquation();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getEquation <em>Equation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Equation</em>' containment reference.
   * @see #getEquation()
   * @generated
   */
  void setEquation(Math_EquationType value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_Scalar()
   * @model containment="true"
   * @generated
   */
  Math_ScalarType getScalar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getScalar <em>Scalar</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scalar</em>' containment reference.
   * @see #getScalar()
   * @generated
   */
  void setScalar(Math_ScalarType value);

  /**
   * Returns the value of the '<em><b>Constant</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constant</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constant</em>' containment reference.
   * @see #setConstant(Math_ConstantType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_Constant()
   * @model containment="true"
   * @generated
   */
  Math_ConstantType getConstant();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getConstant <em>Constant</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constant</em>' containment reference.
   * @see #getConstant()
   * @generated
   */
  void setConstant(Math_ConstantType value);

  /**
   * Returns the value of the '<em><b>String</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>String</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>String</em>' containment reference.
   * @see #setString(Math_StringType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_String()
   * @model containment="true"
   * @generated
   */
  Math_StringType getString();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getString <em>String</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>String</em>' containment reference.
   * @see #getString()
   * @generated
   */
  void setString(Math_StringType value);

  /**
   * Returns the value of the '<em><b>Sequence</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sequence</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sequence</em>' containment reference.
   * @see #setSequence(ct_SequenceType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_Sequence()
   * @model containment="true"
   * @generated
   */
  ct_SequenceType getSequence();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getSequence <em>Sequence</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sequence</em>' containment reference.
   * @see #getSequence()
   * @generated
   */
  void setSequence(ct_SequenceType value);

  /**
   * Returns the value of the '<em><b>Vector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Vector</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Vector</em>' containment reference.
   * @see #setVector(ct_VectorType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_Vector()
   * @model containment="true"
   * @generated
   */
  ct_VectorType getVector();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getVector <em>Vector</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Vector</em>' containment reference.
   * @see #getVector()
   * @generated
   */
  void setVector(ct_VectorType value);

  /**
   * Returns the value of the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Call</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Call</em>' containment reference.
   * @see #setFunctionCall(Math_FunctionCallType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_FunctionCall()
   * @model containment="true"
   * @generated
   */
  Math_FunctionCallType getFunctionCall();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getFunctionCall <em>Function Call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function Call</em>' containment reference.
   * @see #getFunctionCall()
   * @generated
   */
  void setFunctionCall(Math_FunctionCallType value);

  /**
   * Returns the value of the '<em><b>Data Set</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Set</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Set</em>' containment reference.
   * @see #setDataSet(ct_DataSetType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_DataSet()
   * @model containment="true"
   * @generated
   */
  ct_DataSetType getDataSet();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getDataSet <em>Data Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Set</em>' containment reference.
   * @see #getDataSet()
   * @generated
   */
  void setDataSet(ct_DataSetType value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_Var()
   * @model containment="true"
   * @generated
   */
  Math_VarType getVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getVar <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' containment reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Math_VarType value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_Rhs_Distribution()
   * @model containment="true"
   * @generated
   */
  uncert_DistributionType getDistribution();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_Rhs#getDistribution <em>Distribution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Distribution</em>' containment reference.
   * @see #getDistribution()
   * @generated
   */
  void setDistribution(uncert_DistributionType value);

} // ct_Rhs

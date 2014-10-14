/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Column Mapping Type Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getUseVariabilityLevel <em>Use Variability Level</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getUseVariabilityNode <em>Use Variability Node</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getDoseVar <em>Dose Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getTargetVar <em>Target Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getAssign <em>Assign</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getOutput <em>Output</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingTypeContent()
 * @model
 * @generated
 */
public interface msteps_ColumnMappingTypeContent extends EObject
{
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingTypeContent_Var()
   * @model containment="true"
   * @generated
   */
  Math_VarType getVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getVar <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' containment reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Math_VarType value);

  /**
   * Returns the value of the '<em><b>Use Variability Level</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Use Variability Level</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Use Variability Level</em>' containment reference.
   * @see #setUseVariabilityLevel(msteps_UseVariabilityLevel)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingTypeContent_UseVariabilityLevel()
   * @model containment="true"
   * @generated
   */
  msteps_UseVariabilityLevel getUseVariabilityLevel();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getUseVariabilityLevel <em>Use Variability Level</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Use Variability Level</em>' containment reference.
   * @see #getUseVariabilityLevel()
   * @generated
   */
  void setUseVariabilityLevel(msteps_UseVariabilityLevel value);

  /**
   * Returns the value of the '<em><b>Use Variability Node</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Use Variability Node</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Use Variability Node</em>' containment reference.
   * @see #setUseVariabilityNode(msteps_UseVariabilityNode)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingTypeContent_UseVariabilityNode()
   * @model containment="true"
   * @generated
   */
  msteps_UseVariabilityNode getUseVariabilityNode();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getUseVariabilityNode <em>Use Variability Node</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Use Variability Node</em>' containment reference.
   * @see #getUseVariabilityNode()
   * @generated
   */
  void setUseVariabilityNode(msteps_UseVariabilityNode value);

  /**
   * Returns the value of the '<em><b>Dose Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Dose Var</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Dose Var</em>' containment reference.
   * @see #setDoseVar(design_DoseVarType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingTypeContent_DoseVar()
   * @model containment="true"
   * @generated
   */
  design_DoseVarType getDoseVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getDoseVar <em>Dose Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Dose Var</em>' containment reference.
   * @see #getDoseVar()
   * @generated
   */
  void setDoseVar(design_DoseVarType value);

  /**
   * Returns the value of the '<em><b>Target Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Var</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Var</em>' containment reference.
   * @see #setTargetVar(msteps_TargetVarType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingTypeContent_TargetVar()
   * @model containment="true"
   * @generated
   */
  msteps_TargetVarType getTargetVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getTargetVar <em>Target Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Var</em>' containment reference.
   * @see #getTargetVar()
   * @generated
   */
  void setTargetVar(msteps_TargetVarType value);

  /**
   * Returns the value of the '<em><b>Assign</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Assign</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Assign</em>' containment reference.
   * @see #setAssign(msteps_AssignType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingTypeContent_Assign()
   * @model containment="true"
   * @generated
   */
  msteps_AssignType getAssign();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getAssign <em>Assign</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Assign</em>' containment reference.
   * @see #getAssign()
   * @generated
   */
  void setAssign(msteps_AssignType value);

  /**
   * Returns the value of the '<em><b>Output</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output</em>' containment reference.
   * @see #setOutput(msteps_OutputType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingTypeContent_Output()
   * @model containment="true"
   * @generated
   */
  msteps_OutputType getOutput();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent#getOutput <em>Output</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output</em>' containment reference.
   * @see #getOutput()
   * @generated
   */
  void setOutput(msteps_OutputType value);

} // msteps_ColumnMappingTypeContent

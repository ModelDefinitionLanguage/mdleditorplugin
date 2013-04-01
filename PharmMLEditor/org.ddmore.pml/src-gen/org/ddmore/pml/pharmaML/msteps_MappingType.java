/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getVar <em>Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getUseVariabilityLevel <em>Use Variability Level</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getUseVariabilityNode <em>Use Variability Node</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getDoseVar <em>Dose Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getTargetVar <em>Target Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getAssign <em>Assign</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getOutput <em>Output</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.msteps_MappingType#getRestriction <em>Restriction</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType()
 * @model
 * @generated
 */
public interface msteps_MappingType extends EObject
{
  /**
   * Returns the value of the '<em><b>Column Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Column Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Column Name</em>' attribute.
   * @see #setColumnName(String)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_ColumnName()
   * @model
   * @generated
   */
  String getColumnName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getColumnName <em>Column Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Column Name</em>' attribute.
   * @see #getColumnName()
   * @generated
   */
  void setColumnName(String value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_Var()
   * @model containment="true"
   * @generated
   */
  Math_VarType getVar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getVar <em>Var</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_UseVariabilityLevel()
   * @model containment="true"
   * @generated
   */
  msteps_UseVariabilityLevel getUseVariabilityLevel();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getUseVariabilityLevel <em>Use Variability Level</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_UseVariabilityNode()
   * @model containment="true"
   * @generated
   */
  msteps_UseVariabilityNode getUseVariabilityNode();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getUseVariabilityNode <em>Use Variability Node</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_DoseVar()
   * @model containment="true"
   * @generated
   */
  design_DoseVarType getDoseVar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getDoseVar <em>Dose Var</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_TargetVar()
   * @model containment="true"
   * @generated
   */
  msteps_TargetVarType getTargetVar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getTargetVar <em>Target Var</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_Assign()
   * @model containment="true"
   * @generated
   */
  msteps_AssignType getAssign();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getAssign <em>Assign</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_Output()
   * @model containment="true"
   * @generated
   */
  msteps_OutputType getOutput();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getOutput <em>Output</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output</em>' containment reference.
   * @see #getOutput()
   * @generated
   */
  void setOutput(msteps_OutputType value);

  /**
   * Returns the value of the '<em><b>Restriction</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Restriction</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Restriction</em>' containment reference.
   * @see #setRestriction(ct_RestrictionType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmsteps_MappingType_Restriction()
   * @model containment="true"
   * @generated
   */
  ct_RestrictionType getRestriction();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.msteps_MappingType#getRestriction <em>Restriction</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Restriction</em>' containment reference.
   * @see #getRestriction()
   * @generated
   */
  void setRestriction(ct_RestrictionType value);

} // msteps_MappingType

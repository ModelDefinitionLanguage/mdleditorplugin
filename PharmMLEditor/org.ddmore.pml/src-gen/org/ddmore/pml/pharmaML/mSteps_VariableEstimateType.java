/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mSteps Variable Estimate Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getSymbID <em>Symb ID</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getFixed <em>Fixed</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getBlock <em>Block</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getInitialEstimate <em>Initial Estimate</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmSteps_VariableEstimateType()
 * @model
 * @generated
 */
public interface mSteps_VariableEstimateType extends EObject
{
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmSteps_VariableEstimateType_SymbID()
   * @model containment="true"
   * @generated
   */
  Math_symbId getSymbID();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getSymbID <em>Symb ID</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb ID</em>' containment reference.
   * @see #getSymbID()
   * @generated
   */
  void setSymbID(Math_symbId value);

  /**
   * Returns the value of the '<em><b>Fixed</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fixed</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fixed</em>' containment reference.
   * @see #setFixed(mSteps_fixed)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmSteps_VariableEstimateType_Fixed()
   * @model containment="true"
   * @generated
   */
  mSteps_fixed getFixed();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getFixed <em>Fixed</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Fixed</em>' containment reference.
   * @see #getFixed()
   * @generated
   */
  void setFixed(mSteps_fixed value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmSteps_VariableEstimateType_Block()
   * @model containment="true"
   * @generated
   */
  Math_block getBlock();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getBlock <em>Block</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmSteps_VariableEstimateType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Initial Estimate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initial Estimate</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initial Estimate</em>' containment reference.
   * @see #setInitialEstimate(mSteps_InitialEstimateType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmSteps_VariableEstimateType_InitialEstimate()
   * @model containment="true"
   * @generated
   */
  mSteps_InitialEstimateType getInitialEstimate();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getInitialEstimate <em>Initial Estimate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Initial Estimate</em>' containment reference.
   * @see #getInitialEstimate()
   * @generated
   */
  void setInitialEstimate(mSteps_InitialEstimateType value);

  /**
   * Returns the value of the '<em><b>Lower Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Lower Bound</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Lower Bound</em>' containment reference.
   * @see #setLowerBound(mSteps_LowerBoundType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmSteps_VariableEstimateType_LowerBound()
   * @model containment="true"
   * @generated
   */
  mSteps_LowerBoundType getLowerBound();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getLowerBound <em>Lower Bound</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Lower Bound</em>' containment reference.
   * @see #getLowerBound()
   * @generated
   */
  void setLowerBound(mSteps_LowerBoundType value);

  /**
   * Returns the value of the '<em><b>Upper Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Upper Bound</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Upper Bound</em>' containment reference.
   * @see #setUpperBound(mSteps_UpperBoundType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmSteps_VariableEstimateType_UpperBound()
   * @model containment="true"
   * @generated
   */
  mSteps_UpperBoundType getUpperBound();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mSteps_VariableEstimateType#getUpperBound <em>Upper Bound</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Upper Bound</em>' containment reference.
   * @see #getUpperBound()
   * @generated
   */
  void setUpperBound(mSteps_UpperBoundType value);

} // mSteps_VariableEstimateType

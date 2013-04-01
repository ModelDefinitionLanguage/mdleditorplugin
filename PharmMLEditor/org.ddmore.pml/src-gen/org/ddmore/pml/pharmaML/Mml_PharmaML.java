/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mml Pharma ML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getIndependentVar <em>Independent Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getWrittenVersion <em>Written Version</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getSymbolDefinition <em>Symbol Definition</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getStructuralModel <em>Structural Model</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getModelDefinition <em>Model Definition</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getDesign <em>Design</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getModelingSteps <em>Modeling Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML()
 * @model
 * @generated
 */
public interface Mml_PharmaML extends EObject
{
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(ct_Name)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML_Name()
   * @model containment="true"
   * @generated
   */
  ct_Name getName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_Name value);

  /**
   * Returns the value of the '<em><b>Independent Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Independent Var</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Independent Var</em>' containment reference.
   * @see #setIndependentVar(ct_independentVar)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML_IndependentVar()
   * @model containment="true"
   * @generated
   */
  ct_independentVar getIndependentVar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getIndependentVar <em>Independent Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Independent Var</em>' containment reference.
   * @see #getIndependentVar()
   * @generated
   */
  void setIndependentVar(ct_independentVar value);

  /**
   * Returns the value of the '<em><b>Written Version</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Written Version</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Written Version</em>' containment reference.
   * @see #setWrittenVersion(ct_writtenVersion)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML_WrittenVersion()
   * @model containment="true"
   * @generated
   */
  ct_writtenVersion getWrittenVersion();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getWrittenVersion <em>Written Version</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Written Version</em>' containment reference.
   * @see #getWrittenVersion()
   * @generated
   */
  void setWrittenVersion(ct_writtenVersion value);

  /**
   * Returns the value of the '<em><b>Symbol Definition</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol Definition</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol Definition</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML_SymbolDefinition()
   * @model containment="true"
   * @generated
   */
  EList<Mml_SymbolDefinitionType> getSymbolDefinition();

  /**
   * Returns the value of the '<em><b>Structural Model</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.mdef_StructuralModelType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Structural Model</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Structural Model</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML_StructuralModel()
   * @model containment="true"
   * @generated
   */
  EList<mdef_StructuralModelType> getStructuralModel();

  /**
   * Returns the value of the '<em><b>Model Definition</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.mdef_ModelDefinitionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Definition</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Definition</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML_ModelDefinition()
   * @model containment="true"
   * @generated
   */
  EList<mdef_ModelDefinitionType> getModelDefinition();

  /**
   * Returns the value of the '<em><b>Design</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Design</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Design</em>' containment reference.
   * @see #setDesign(design_TrialDesignType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML_Design()
   * @model containment="true"
   * @generated
   */
  design_TrialDesignType getDesign();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getDesign <em>Design</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Design</em>' containment reference.
   * @see #getDesign()
   * @generated
   */
  void setDesign(design_TrialDesignType value);

  /**
   * Returns the value of the '<em><b>Modeling Steps</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Modeling Steps</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Modeling Steps</em>' containment reference.
   * @see #setModelingSteps(msteps_ModelingSteps)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMml_PharmaML_ModelingSteps()
   * @model containment="true"
   * @generated
   */
  msteps_ModelingSteps getModelingSteps();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Mml_PharmaML#getModelingSteps <em>Modeling Steps</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modeling Steps</em>' containment reference.
   * @see #getModelingSteps()
   * @generated
   */
  void setModelingSteps(msteps_ModelingSteps value);

} // Mml_PharmaML

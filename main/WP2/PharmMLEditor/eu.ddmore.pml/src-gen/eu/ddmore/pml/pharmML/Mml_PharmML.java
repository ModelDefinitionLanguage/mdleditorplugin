/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mml Pharm ML</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmML#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmML#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmML#getIndependentVar <em>Independent Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmML#getWrittenVersion <em>Written Version</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmML#getSymbolDefinition <em>Symbol Definition</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmML#getExpr <em>Expr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmML#getDesign <em>Design</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmML#getModelingSteps <em>Modeling Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmML()
 * @model
 * @generated
 */
public interface Mml_PharmML extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmML_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(ct_name)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmML_Name()
   * @model containment="true"
   * @generated
   */
  ct_name getName();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_name value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmML_IndependentVar()
   * @model containment="true"
   * @generated
   */
  ct_independentVar getIndependentVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getIndependentVar <em>Independent Var</em>}' containment reference.
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmML_WrittenVersion()
   * @model containment="true"
   * @generated
   */
  ct_writtenVersion getWrittenVersion();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getWrittenVersion <em>Written Version</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Written Version</em>' containment reference.
   * @see #getWrittenVersion()
   * @generated
   */
  void setWrittenVersion(ct_writtenVersion value);

  /**
   * Returns the value of the '<em><b>Symbol Definition</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol Definition</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol Definition</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmML_SymbolDefinition()
   * @model containment="true"
   * @generated
   */
  EList<Mml_SymbolDefinitionType> getSymbolDefinition();

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.Mml_PharmaMLContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmML_Expr()
   * @model containment="true"
   * @generated
   */
  EList<Mml_PharmaMLContent> getExpr();

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmML_Design()
   * @model containment="true"
   * @generated
   */
  design_TrialDesignType getDesign();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getDesign <em>Design</em>}' containment reference.
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmML_ModelingSteps()
   * @model containment="true"
   * @generated
   */
  msteps_ModelingSteps getModelingSteps();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_PharmML#getModelingSteps <em>Modeling Steps</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Modeling Steps</em>' containment reference.
   * @see #getModelingSteps()
   * @generated
   */
  void setModelingSteps(msteps_ModelingSteps value);

} // Mml_PharmML

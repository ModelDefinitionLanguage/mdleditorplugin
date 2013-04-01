/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct Variable Definition Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getSymbolType <em>Symbol Type</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getIndependentVar <em>Independent Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getDescription <em>Description</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_VariableDefinitionType()
 * @model
 * @generated
 */
public interface ct_VariableDefinitionType extends EObject
{
  /**
   * Returns the value of the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symb Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symb Id</em>' containment reference.
   * @see #setSymbId(ct_symbId)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_VariableDefinitionType_SymbId()
   * @model containment="true"
   * @generated
   */
  ct_symbId getSymbId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(ct_symbId value);

  /**
   * Returns the value of the '<em><b>Symbol Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol Type</em>' containment reference.
   * @see #setSymbolType(ct_symbolType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_VariableDefinitionType_SymbolType()
   * @model containment="true"
   * @generated
   */
  ct_symbolType getSymbolType();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getSymbolType <em>Symbol Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol Type</em>' containment reference.
   * @see #getSymbolType()
   * @generated
   */
  void setSymbolType(ct_symbolType value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_VariableDefinitionType_IndependentVar()
   * @model containment="true"
   * @generated
   */
  ct_independentVar getIndependentVar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getIndependentVar <em>Independent Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Independent Var</em>' containment reference.
   * @see #getIndependentVar()
   * @generated
   */
  void setIndependentVar(ct_independentVar value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_VariableDefinitionType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' containment reference.
   * @see #setDescription(ct_AnnotationType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_VariableDefinitionType_Description()
   * @model containment="true"
   * @generated
   */
  ct_AnnotationType getDescription();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getDescription <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' containment reference.
   * @see #getDescription()
   * @generated
   */
  void setDescription(ct_AnnotationType value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(ct_Rhs)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_VariableDefinitionType_Expr()
   * @model containment="true"
   * @generated
   */
  ct_Rhs getExpr();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_VariableDefinitionType#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(ct_Rhs value);

} // ct_VariableDefinitionType

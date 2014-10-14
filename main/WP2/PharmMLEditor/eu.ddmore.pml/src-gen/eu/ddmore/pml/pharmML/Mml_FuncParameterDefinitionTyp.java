/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mml Func Parameter Definition Typ</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getSumbId <em>Sumb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getSymbolType <em>Symbol Type</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getDecsription <em>Decsription</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_FuncParameterDefinitionTyp()
 * @model
 * @generated
 */
public interface Mml_FuncParameterDefinitionTyp extends Mml_FuncParameterDefinitionType
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_FuncParameterDefinitionTyp_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Sumb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sumb Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sumb Id</em>' containment reference.
   * @see #setSumbId(ct_symbId)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_FuncParameterDefinitionTyp_SumbId()
   * @model containment="true"
   * @generated
   */
  ct_symbId getSumbId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getSumbId <em>Sumb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sumb Id</em>' containment reference.
   * @see #getSumbId()
   * @generated
   */
  void setSumbId(ct_symbId value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_FuncParameterDefinitionTyp_SymbolType()
   * @model containment="true"
   * @generated
   */
  ct_symbolType getSymbolType();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getSymbolType <em>Symbol Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol Type</em>' containment reference.
   * @see #getSymbolType()
   * @generated
   */
  void setSymbolType(ct_symbolType value);

  /**
   * Returns the value of the '<em><b>Decsription</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Decsription</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Decsription</em>' containment reference.
   * @see #setDecsription(ct_AnnotationType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_FuncParameterDefinitionTyp_Decsription()
   * @model containment="true"
   * @generated
   */
  ct_AnnotationType getDecsription();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp#getDecsription <em>Decsription</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Decsription</em>' containment reference.
   * @see #getDecsription()
   * @generated
   */
  void setDecsription(ct_AnnotationType value);

} // Mml_FuncParameterDefinitionTyp

/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Import Link Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ImportLinkType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ImportLinkType#getSymbolPtr <em>Symbol Ptr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ImportLinkType#getVar <em>Var</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ImportLinkType()
 * @model
 * @generated
 */
public interface mdef_ImportLinkType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ImportLinkType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Symbol Ptr</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol Ptr</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol Ptr</em>' attribute.
   * @see #setSymbolPtr(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ImportLinkType_SymbolPtr()
   * @model
   * @generated
   */
  String getSymbolPtr();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ImportLinkType#getSymbolPtr <em>Symbol Ptr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol Ptr</em>' attribute.
   * @see #getSymbolPtr()
   * @generated
   */
  void setSymbolPtr(String value);

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ImportLinkType_Var()
   * @model containment="true"
   * @generated
   */
  Math_VarType getVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ImportLinkType#getVar <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' containment reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Math_VarType value);

} // mdef_ImportLinkType

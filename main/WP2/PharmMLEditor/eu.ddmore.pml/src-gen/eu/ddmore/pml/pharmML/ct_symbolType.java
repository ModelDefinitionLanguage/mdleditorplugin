/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct symbol Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_symbolType#getSymbolType <em>Symbol Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_symbolType()
 * @model
 * @generated
 */
public interface ct_symbolType extends EObject
{
  /**
   * Returns the value of the '<em><b>Symbol Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol Type</em>' containment reference.
   * @see #setSymbolType(ct_SymbolTypeType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_symbolType_SymbolType()
   * @model containment="true"
   * @generated
   */
  ct_SymbolTypeType getSymbolType();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_symbolType#getSymbolType <em>Symbol Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol Type</em>' containment reference.
   * @see #getSymbolType()
   * @generated
   */
  void setSymbolType(ct_SymbolTypeType value);

} // ct_symbolType

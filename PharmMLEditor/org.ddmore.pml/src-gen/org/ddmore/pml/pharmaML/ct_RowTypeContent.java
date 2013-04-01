/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct Row Type Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_RowTypeContent#getCell <em>Cell</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_RowTypeContent#getNull <em>Null</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_RowTypeContent()
 * @model
 * @generated
 */
public interface ct_RowTypeContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Cell</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cell</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cell</em>' containment reference.
   * @see #setCell(ct_CellType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_RowTypeContent_Cell()
   * @model containment="true"
   * @generated
   */
  ct_CellType getCell();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_RowTypeContent#getCell <em>Cell</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cell</em>' containment reference.
   * @see #getCell()
   * @generated
   */
  void setCell(ct_CellType value);

  /**
   * Returns the value of the '<em><b>Null</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Null</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Null</em>' attribute.
   * @see #setNull(String)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_RowTypeContent_Null()
   * @model
   * @generated
   */
  String getNull();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_RowTypeContent#getNull <em>Null</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Null</em>' attribute.
   * @see #getNull()
   * @generated
   */
  void setNull(String value);

} // ct_RowTypeContent

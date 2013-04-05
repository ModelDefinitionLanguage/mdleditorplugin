/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct Row Type Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_RowTypeContent#getCell <em>Cell</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_RowTypeContent#getNull <em>Null</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_RowTypeContent()
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_RowTypeContent_Cell()
   * @model containment="true"
   * @generated
   */
  ct_CellType getCell();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_RowTypeContent#getCell <em>Cell</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cell</em>' containment reference.
   * @see #getCell()
   * @generated
   */
  void setCell(ct_CellType value);

  /**
   * Returns the value of the '<em><b>Null</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Null</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Null</em>' containment reference.
   * @see #setNull(ct_NullCellType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_RowTypeContent_Null()
   * @model containment="true"
   * @generated
   */
  ct_NullCellType getNull();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_RowTypeContent#getNull <em>Null</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Null</em>' containment reference.
   * @see #getNull()
   * @generated
   */
  void setNull(ct_NullCellType value);

} // ct_RowTypeContent

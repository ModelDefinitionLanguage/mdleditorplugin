/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ct Column Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_ColumnType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_ColumnType#getColumnNum <em>Column Num</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.ct_ColumnType#getColumnVar <em>Column Var</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_ColumnType()
 * @model
 * @generated
 */
public interface ct_ColumnType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_ColumnType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Column Num</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Column Num</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Column Num</em>' attribute.
   * @see #setColumnNum(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_ColumnType_ColumnNum()
   * @model
   * @generated
   */
  String getColumnNum();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_ColumnType#getColumnNum <em>Column Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Column Num</em>' attribute.
   * @see #getColumnNum()
   * @generated
   */
  void setColumnNum(String value);

  /**
   * Returns the value of the '<em><b>Column Var</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Column Var</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Column Var</em>' attribute.
   * @see #setColumnVar(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getct_ColumnType_ColumnVar()
   * @model
   * @generated
   */
  String getColumnVar();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.ct_ColumnType#getColumnVar <em>Column Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Column Var</em>' attribute.
   * @see #getColumnVar()
   * @generated
   */
  void setColumnVar(String value);

} // ct_ColumnType

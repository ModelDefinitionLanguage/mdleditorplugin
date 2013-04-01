/**
 */
package org.ddmore.pml.pharmaML;

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
 *   <li>{@link org.ddmore.pml.pharmaML.ct_ColumnType#getColumnNum <em>Column Num</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_ColumnType#getColumnVar <em>Column Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.ct_ColumnType#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ColumnType()
 * @model
 * @generated
 */
public interface ct_ColumnType extends EObject
{
  /**
   * Returns the value of the '<em><b>Column Num</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Column Num</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Column Num</em>' containment reference.
   * @see #setColumnNum(ct_Int_Value)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ColumnType_ColumnNum()
   * @model containment="true"
   * @generated
   */
  ct_Int_Value getColumnNum();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_ColumnType#getColumnNum <em>Column Num</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Column Num</em>' containment reference.
   * @see #getColumnNum()
   * @generated
   */
  void setColumnNum(ct_Int_Value value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ColumnType_ColumnVar()
   * @model
   * @generated
   */
  String getColumnVar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.ct_ColumnType#getColumnVar <em>Column Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Column Var</em>' attribute.
   * @see #getColumnVar()
   * @generated
   */
  void setColumnVar(String value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getct_ColumnType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

} // ct_ColumnType

/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>msteps Column Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getColumnname <em>Columnname</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getExpr <em>Expr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getRestriction <em>Restriction</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingType()
 * @model
 * @generated
 */
public interface msteps_ColumnMappingType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Columnname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Columnname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Columnname</em>' attribute.
   * @see #setColumnname(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingType_Columnname()
   * @model
   * @generated
   */
  String getColumnname();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getColumnname <em>Columnname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Columnname</em>' attribute.
   * @see #getColumnname()
   * @generated
   */
  void setColumnname(String value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingType_Expr()
   * @model containment="true"
   * @generated
   */
  EList<msteps_ColumnMappingTypeContent> getExpr();

  /**
   * Returns the value of the '<em><b>Restriction</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Restriction</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Restriction</em>' containment reference.
   * @see #setRestriction(ct_RestrictionType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmsteps_ColumnMappingType_Restriction()
   * @model containment="true"
   * @generated
   */
  ct_RestrictionType getRestriction();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.msteps_ColumnMappingType#getRestriction <em>Restriction</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Restriction</em>' containment reference.
   * @see #getRestriction()
   * @generated
   */
  void setRestriction(ct_RestrictionType value);

} // msteps_ColumnMappingType

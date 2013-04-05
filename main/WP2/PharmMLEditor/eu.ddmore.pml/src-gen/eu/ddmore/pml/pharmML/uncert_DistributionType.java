/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>uncert Distribution Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_DistributionType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_DistributionType#getWrittenVersion <em>Written Version</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_DistributionType#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionType()
 * @model
 * @generated
 */
public interface uncert_DistributionType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionType_WrittenVersion()
   * @model containment="true"
   * @generated
   */
  ct_writtenVersion getWrittenVersion();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_DistributionType#getWrittenVersion <em>Written Version</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Written Version</em>' containment reference.
   * @see #getWrittenVersion()
   * @generated
   */
  void setWrittenVersion(ct_writtenVersion value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(uncert_DistributionTypeContent)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionType_Expr()
   * @model containment="true"
   * @generated
   */
  uncert_DistributionTypeContent getExpr();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_DistributionType#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(uncert_DistributionTypeContent value);

} // uncert_DistributionType

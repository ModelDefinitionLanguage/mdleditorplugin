/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>uncert Mean Param Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_MeanParamType#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_MeanParamType()
 * @model
 * @generated
 */
public interface uncert_MeanParamType extends EObject
{
  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(uncert_DistributionParamType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_MeanParamType_Expr()
   * @model containment="true"
   * @generated
   */
  uncert_DistributionParamType getExpr();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.uncert_MeanParamType#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(uncert_DistributionParamType value);

} // uncert_MeanParamType

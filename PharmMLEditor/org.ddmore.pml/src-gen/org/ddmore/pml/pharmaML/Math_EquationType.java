/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Math Equation Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.Math_EquationType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Math_EquationType#getWrittenVersion <em>Written Version</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.Math_EquationType#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_EquationType()
 * @model
 * @generated
 */
public interface Math_EquationType extends EObject
{
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_EquationType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_EquationType_WrittenVersion()
   * @model containment="true"
   * @generated
   */
  ct_writtenVersion getWrittenVersion();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Math_EquationType#getWrittenVersion <em>Written Version</em>}' containment reference.
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
   * @see #setExpr(Math_EquationTypeContent)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getMath_EquationType_Expr()
   * @model containment="true"
   * @generated
   */
  Math_EquationTypeContent getExpr();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.Math_EquationType#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(Math_EquationTypeContent value);

} // Math_EquationType

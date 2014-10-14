/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>uncert PDF</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_PDF#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_PDF#getFunctionParameter <em>Function Parameter</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_PDF#getEquation <em>Equation</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_PDF()
 * @model
 * @generated
 */
public interface uncert_PDF extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_PDF_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Function Parameter</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Parameter</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Parameter</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_PDF_FunctionParameter()
   * @model containment="true"
   * @generated
   */
  EList<uncert_ParameterDefinitionType> getFunctionParameter();

  /**
   * Returns the value of the '<em><b>Equation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Equation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Equation</em>' containment reference.
   * @see #setEquation(Math_EquationType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_PDF_Equation()
   * @model containment="true"
   * @generated
   */
  Math_EquationType getEquation();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_PDF#getEquation <em>Equation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Equation</em>' containment reference.
   * @see #getEquation()
   * @generated
   */
  void setEquation(Math_EquationType value);

} // uncert_PDF

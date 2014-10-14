/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>uncert Distribution Type Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getNormal <em>Normal</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getPDF <em>PDF</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getPoison <em>Poison</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getStudent <em>Student</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getUniform <em>Uniform</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionTypeContent()
 * @model
 * @generated
 */
public interface uncert_DistributionTypeContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Normal</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Normal</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Normal</em>' containment reference.
   * @see #setNormal(uncert_Normal)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionTypeContent_Normal()
   * @model containment="true"
   * @generated
   */
  uncert_Normal getNormal();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getNormal <em>Normal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Normal</em>' containment reference.
   * @see #getNormal()
   * @generated
   */
  void setNormal(uncert_Normal value);

  /**
   * Returns the value of the '<em><b>PDF</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>PDF</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>PDF</em>' containment reference.
   * @see #setPDF(uncert_PDF)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionTypeContent_PDF()
   * @model containment="true"
   * @generated
   */
  uncert_PDF getPDF();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getPDF <em>PDF</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>PDF</em>' containment reference.
   * @see #getPDF()
   * @generated
   */
  void setPDF(uncert_PDF value);

  /**
   * Returns the value of the '<em><b>Poison</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Poison</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Poison</em>' containment reference.
   * @see #setPoison(uncert_Poison)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionTypeContent_Poison()
   * @model containment="true"
   * @generated
   */
  uncert_Poison getPoison();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getPoison <em>Poison</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Poison</em>' containment reference.
   * @see #getPoison()
   * @generated
   */
  void setPoison(uncert_Poison value);

  /**
   * Returns the value of the '<em><b>Student</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Student</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Student</em>' containment reference.
   * @see #setStudent(uncert_Student_t)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionTypeContent_Student()
   * @model containment="true"
   * @generated
   */
  uncert_Student_t getStudent();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getStudent <em>Student</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Student</em>' containment reference.
   * @see #getStudent()
   * @generated
   */
  void setStudent(uncert_Student_t value);

  /**
   * Returns the value of the '<em><b>Uniform</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Uniform</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Uniform</em>' containment reference.
   * @see #setUniform(uncert_Uniform)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_DistributionTypeContent_Uniform()
   * @model containment="true"
   * @generated
   */
  uncert_Uniform getUniform();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_DistributionTypeContent#getUniform <em>Uniform</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Uniform</em>' containment reference.
   * @see #getUniform()
   * @generated
   */
  void setUniform(uncert_Uniform value);

} // uncert_DistributionTypeContent

/**
 */
package org.ddmore.pml.pharmaML;

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
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getWrittenVersion <em>Written Version</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getNormal <em>Normal</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getPDF <em>PDF</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getPoison <em>Poison</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getStudent <em>Student</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getUniform <em>Uniform</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_DistributionType()
 * @model
 * @generated
 */
public interface uncert_DistributionType extends EObject
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_DistributionType_Xmlns()
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_DistributionType_WrittenVersion()
   * @model containment="true"
   * @generated
   */
  ct_writtenVersion getWrittenVersion();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getWrittenVersion <em>Written Version</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Written Version</em>' containment reference.
   * @see #getWrittenVersion()
   * @generated
   */
  void setWrittenVersion(ct_writtenVersion value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_DistributionType_Normal()
   * @model containment="true"
   * @generated
   */
  uncert_Normal getNormal();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getNormal <em>Normal</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_DistributionType_PDF()
   * @model containment="true"
   * @generated
   */
  uncert_PDF getPDF();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getPDF <em>PDF</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_DistributionType_Poison()
   * @model containment="true"
   * @generated
   */
  uncert_Poison getPoison();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getPoison <em>Poison</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_DistributionType_Student()
   * @model containment="true"
   * @generated
   */
  uncert_Student_t getStudent();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getStudent <em>Student</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getuncert_DistributionType_Uniform()
   * @model containment="true"
   * @generated
   */
  uncert_Uniform getUniform();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.uncert_DistributionType#getUniform <em>Uniform</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Uniform</em>' containment reference.
   * @see #getUniform()
   * @generated
   */
  void setUniform(uncert_Uniform value);

} // uncert_DistributionType

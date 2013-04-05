/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>uncert Normal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_Normal#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_Normal#getMean <em>Mean</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_Normal#getStdDev <em>Std Dev</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_Normal#getVariance <em>Variance</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_Normal#getCoefficientOfVariance <em>Coefficient Of Variance</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Normal()
 * @model
 * @generated
 */
public interface uncert_Normal extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Normal_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Mean</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mean</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mean</em>' containment reference.
   * @see #setMean(uncert_Mean)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Normal_Mean()
   * @model containment="true"
   * @generated
   */
  uncert_Mean getMean();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_Normal#getMean <em>Mean</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mean</em>' containment reference.
   * @see #getMean()
   * @generated
   */
  void setMean(uncert_Mean value);

  /**
   * Returns the value of the '<em><b>Std Dev</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Std Dev</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Std Dev</em>' containment reference.
   * @see #setStdDev(uncert_StdDev)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Normal_StdDev()
   * @model containment="true"
   * @generated
   */
  uncert_StdDev getStdDev();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_Normal#getStdDev <em>Std Dev</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Std Dev</em>' containment reference.
   * @see #getStdDev()
   * @generated
   */
  void setStdDev(uncert_StdDev value);

  /**
   * Returns the value of the '<em><b>Variance</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variance</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variance</em>' containment reference.
   * @see #setVariance(uncert_Variance)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Normal_Variance()
   * @model containment="true"
   * @generated
   */
  uncert_Variance getVariance();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_Normal#getVariance <em>Variance</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variance</em>' containment reference.
   * @see #getVariance()
   * @generated
   */
  void setVariance(uncert_Variance value);

  /**
   * Returns the value of the '<em><b>Coefficient Of Variance</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Coefficient Of Variance</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Coefficient Of Variance</em>' containment reference.
   * @see #setCoefficientOfVariance(uncert_CoefficientOfVariance)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Normal_CoefficientOfVariance()
   * @model containment="true"
   * @generated
   */
  uncert_CoefficientOfVariance getCoefficientOfVariance();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_Normal#getCoefficientOfVariance <em>Coefficient Of Variance</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Coefficient Of Variance</em>' containment reference.
   * @see #getCoefficientOfVariance()
   * @generated
   */
  void setCoefficientOfVariance(uncert_CoefficientOfVariance value);

} // uncert_Normal

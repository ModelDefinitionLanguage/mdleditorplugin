/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>uncert Student t</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_Student_t#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_Student_t#getLocation <em>Location</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_Student_t#getScale <em>Scale</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.uncert_Student_t#getDegreesOfFreedom <em>Degrees Of Freedom</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Student_t()
 * @model
 * @generated
 */
public interface uncert_Student_t extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Student_t_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Location</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Location</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Location</em>' containment reference.
   * @see #setLocation(uncert_Location)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Student_t_Location()
   * @model containment="true"
   * @generated
   */
  uncert_Location getLocation();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_Student_t#getLocation <em>Location</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Location</em>' containment reference.
   * @see #getLocation()
   * @generated
   */
  void setLocation(uncert_Location value);

  /**
   * Returns the value of the '<em><b>Scale</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scale</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scale</em>' containment reference.
   * @see #setScale(uncert_Scale)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Student_t_Scale()
   * @model containment="true"
   * @generated
   */
  uncert_Scale getScale();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_Student_t#getScale <em>Scale</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scale</em>' containment reference.
   * @see #getScale()
   * @generated
   */
  void setScale(uncert_Scale value);

  /**
   * Returns the value of the '<em><b>Degrees Of Freedom</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Degrees Of Freedom</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Degrees Of Freedom</em>' containment reference.
   * @see #setDegreesOfFreedom(uncertDegreesOfFreedom)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getuncert_Student_t_DegreesOfFreedom()
   * @model containment="true"
   * @generated
   */
  uncertDegreesOfFreedom getDegreesOfFreedom();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.uncert_Student_t#getDegreesOfFreedom <em>Degrees Of Freedom</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Degrees Of Freedom</em>' containment reference.
   * @see #getDegreesOfFreedom()
   * @generated
   */
  void setDegreesOfFreedom(uncertDegreesOfFreedom value);

} // uncert_Student_t

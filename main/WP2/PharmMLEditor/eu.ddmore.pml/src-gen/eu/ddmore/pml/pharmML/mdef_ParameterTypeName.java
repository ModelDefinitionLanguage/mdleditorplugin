/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Parameter Type Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getLog <em>Log</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getLogit <em>Logit</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getNone <em>None</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterTypeName()
 * @model
 * @generated
 */
public interface mdef_ParameterTypeName extends EObject
{
  /**
   * Returns the value of the '<em><b>Log</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Log</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Log</em>' attribute.
   * @see #setLog(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterTypeName_Log()
   * @model
   * @generated
   */
  String getLog();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getLog <em>Log</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Log</em>' attribute.
   * @see #getLog()
   * @generated
   */
  void setLog(String value);

  /**
   * Returns the value of the '<em><b>Logit</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Logit</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Logit</em>' attribute.
   * @see #setLogit(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterTypeName_Logit()
   * @model
   * @generated
   */
  String getLogit();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getLogit <em>Logit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Logit</em>' attribute.
   * @see #getLogit()
   * @generated
   */
  void setLogit(String value);

  /**
   * Returns the value of the '<em><b>None</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>None</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>None</em>' attribute.
   * @see #setNone(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ParameterTypeName_None()
   * @model
   * @generated
   */
  String getNone();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ParameterTypeName#getNone <em>None</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>None</em>' attribute.
   * @see #getNone()
   * @generated
   */
  void setNone(String value);

} // mdef_ParameterTypeName

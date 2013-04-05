/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Import Resource Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType#getSbml <em>Sbml</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType#getPharmml <em>Pharmml</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ImportResourceTypeType()
 * @model
 * @generated
 */
public interface mdef_ImportResourceTypeType extends EObject
{
  /**
   * Returns the value of the '<em><b>Sbml</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sbml</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sbml</em>' attribute.
   * @see #setSbml(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ImportResourceTypeType_Sbml()
   * @model
   * @generated
   */
  String getSbml();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType#getSbml <em>Sbml</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sbml</em>' attribute.
   * @see #getSbml()
   * @generated
   */
  void setSbml(String value);

  /**
   * Returns the value of the '<em><b>Pharmml</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pharmml</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pharmml</em>' attribute.
   * @see #setPharmml(String)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ImportResourceTypeType_Pharmml()
   * @model
   * @generated
   */
  String getPharmml();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType#getPharmml <em>Pharmml</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pharmml</em>' attribute.
   * @see #getPharmml()
   * @generated
   */
  void setPharmml(String value);

} // mdef_ImportResourceTypeType

/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Category Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getCatId <em>Cat Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getProbability <em>Probability</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CategoryType()
 * @model
 * @generated
 */
public interface mdef_CategoryType extends EObject
{
  /**
   * Returns the value of the '<em><b>Cat Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cat Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cat Id</em>' containment reference.
   * @see #setCatId(ct_catId)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CategoryType_CatId()
   * @model containment="true"
   * @generated
   */
  ct_catId getCatId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getCatId <em>Cat Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cat Id</em>' containment reference.
   * @see #getCatId()
   * @generated
   */
  void setCatId(ct_catId value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(ct_Name)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CategoryType_Name()
   * @model containment="true"
   * @generated
   */
  ct_Name getName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_Name value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CategoryType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Probability</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Probability</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Probability</em>' containment reference.
   * @see #setProbability(mdef_ProbabilityType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CategoryType_Probability()
   * @model containment="true"
   * @generated
   */
  mdef_ProbabilityType getProbability();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_CategoryType#getProbability <em>Probability</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Probability</em>' containment reference.
   * @see #getProbability()
   * @generated
   */
  void setProbability(mdef_ProbabilityType value);

} // mdef_CategoryType

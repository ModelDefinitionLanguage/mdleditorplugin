/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Covariate Variability Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getContinuous <em>Continuous</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getCategorical <em>Categorical</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CovariateVariabilityType()
 * @model
 * @generated
 */
public interface mdef_CovariateVariabilityType extends EObject
{
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CovariateVariabilityType_Name()
   * @model containment="true"
   * @generated
   */
  ct_Name getName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_Name value);

  /**
   * Returns the value of the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symb Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symb Id</em>' containment reference.
   * @see #setSymbId(ct_symbId)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CovariateVariabilityType_SymbId()
   * @model containment="true"
   * @generated
   */
  ct_symbId getSymbId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(ct_symbId value);

  /**
   * Returns the value of the '<em><b>Transformation</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transformation</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transformation</em>' containment reference.
   * @see #setTransformation(mdef_transformation)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CovariateVariabilityType_Transformation()
   * @model containment="true"
   * @generated
   */
  mdef_transformation getTransformation();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getTransformation <em>Transformation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Transformation</em>' containment reference.
   * @see #getTransformation()
   * @generated
   */
  void setTransformation(mdef_transformation value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CovariateVariabilityType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Continuous</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Continuous</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Continuous</em>' containment reference.
   * @see #setContinuous(mdef_ContinuousType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CovariateVariabilityType_Continuous()
   * @model containment="true"
   * @generated
   */
  mdef_ContinuousType getContinuous();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getContinuous <em>Continuous</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Continuous</em>' containment reference.
   * @see #getContinuous()
   * @generated
   */
  void setContinuous(mdef_ContinuousType value);

  /**
   * Returns the value of the '<em><b>Categorical</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Categorical</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Categorical</em>' containment reference.
   * @see #setCategorical(mdef_CategoricalType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_CovariateVariabilityType_Categorical()
   * @model containment="true"
   * @generated
   */
  mdef_CategoricalType getCategorical();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType#getCategorical <em>Categorical</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Categorical</em>' containment reference.
   * @see #getCategorical()
   * @generated
   */
  void setCategorical(mdef_CategoricalType value);

} // mdef_CovariateVariabilityType

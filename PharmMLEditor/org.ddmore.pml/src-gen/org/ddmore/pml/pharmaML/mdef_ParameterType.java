/**
 */
package org.ddmore.pml.pharmaML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Parameter Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getVar <em>Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getScalar <em>Scalar</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getEquation <em>Equation</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getRandomEffect <em>Random Effect</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getCovariate <em>Covariate</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType()
 * @model
 * @generated
 */
public interface mdef_ParameterType extends EObject
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType_Name()
   * @model containment="true"
   * @generated
   */
  ct_Name getName();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getName <em>Name</em>}' containment reference.
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
   * @see #setSymbId(Math_symbId)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType_SymbId()
   * @model containment="true"
   * @generated
   */
  Math_symbId getSymbId();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(Math_symbId value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType_Transformation()
   * @model containment="true"
   * @generated
   */
  mdef_transformation getTransformation();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getTransformation <em>Transformation</em>}' containment reference.
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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<ct_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Var</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Var</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Var</em>' containment reference.
   * @see #setVar(Math_VarType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType_Var()
   * @model containment="true"
   * @generated
   */
  Math_VarType getVar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getVar <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Var</em>' containment reference.
   * @see #getVar()
   * @generated
   */
  void setVar(Math_VarType value);

  /**
   * Returns the value of the '<em><b>Scalar</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Scalar</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Scalar</em>' containment reference.
   * @see #setScalar(Math_ScalarType)
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType_Scalar()
   * @model containment="true"
   * @generated
   */
  Math_ScalarType getScalar();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getScalar <em>Scalar</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Scalar</em>' containment reference.
   * @see #getScalar()
   * @generated
   */
  void setScalar(Math_ScalarType value);

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
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType_Equation()
   * @model containment="true"
   * @generated
   */
  Math_EquationType getEquation();

  /**
   * Sets the value of the '{@link org.ddmore.pml.pharmaML.mdef_ParameterType#getEquation <em>Equation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Equation</em>' containment reference.
   * @see #getEquation()
   * @generated
   */
  void setEquation(Math_EquationType value);

  /**
   * Returns the value of the '<em><b>Random Effect</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.mdef_RandomEffectType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Random Effect</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Random Effect</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType_RandomEffect()
   * @model containment="true"
   * @generated
   */
  EList<mdef_RandomEffectType> getRandomEffect();

  /**
   * Returns the value of the '<em><b>Covariate</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.pml.pharmaML.mdef_CovariateType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Covariate</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Covariate</em>' containment reference list.
   * @see org.ddmore.pml.pharmaML.PharmaMLPackage#getmdef_ParameterType_Covariate()
   * @model containment="true"
   * @generated
   */
  EList<mdef_CovariateType> getCovariate();

} // mdef_ParameterType

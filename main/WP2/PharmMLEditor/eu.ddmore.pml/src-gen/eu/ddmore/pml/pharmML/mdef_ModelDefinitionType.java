/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Model Definition Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getVariabilityLevel <em>Variability Level</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getCovariateModel <em>Covariate Model</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getParameterModel <em>Parameter Model</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getStructuraModel <em>Structura Model</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getObservationModel <em>Observation Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ModelDefinitionType()
 * @model
 * @generated
 */
public interface mdef_ModelDefinitionType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ModelDefinitionType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Variability Level</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.mdef_VariabilityLevelDefnType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variability Level</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variability Level</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ModelDefinitionType_VariabilityLevel()
   * @model containment="true"
   * @generated
   */
  EList<mdef_VariabilityLevelDefnType> getVariabilityLevel();

  /**
   * Returns the value of the '<em><b>Covariate Model</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Covariate Model</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Covariate Model</em>' containment reference.
   * @see #setCovariateModel(mdef_CovariateModelType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ModelDefinitionType_CovariateModel()
   * @model containment="true"
   * @generated
   */
  mdef_CovariateModelType getCovariateModel();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getCovariateModel <em>Covariate Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Covariate Model</em>' containment reference.
   * @see #getCovariateModel()
   * @generated
   */
  void setCovariateModel(mdef_CovariateModelType value);

  /**
   * Returns the value of the '<em><b>Parameter Model</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter Model</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter Model</em>' containment reference.
   * @see #setParameterModel(mdef_ParameterModelType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ModelDefinitionType_ParameterModel()
   * @model containment="true"
   * @generated
   */
  mdef_ParameterModelType getParameterModel();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getParameterModel <em>Parameter Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter Model</em>' containment reference.
   * @see #getParameterModel()
   * @generated
   */
  void setParameterModel(mdef_ParameterModelType value);

  /**
   * Returns the value of the '<em><b>Structura Model</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.mdef_StructuralModelType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Structura Model</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Structura Model</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ModelDefinitionType_StructuraModel()
   * @model containment="true"
   * @generated
   */
  EList<mdef_StructuralModelType> getStructuraModel();

  /**
   * Returns the value of the '<em><b>Observation Model</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Observation Model</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Observation Model</em>' containment reference.
   * @see #setObservationModel(mdef_ObservationModelType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_ModelDefinitionType_ObservationModel()
   * @model containment="true"
   * @generated
   */
  mdef_ObservationModelType getObservationModel();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_ModelDefinitionType#getObservationModel <em>Observation Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Observation Model</em>' containment reference.
   * @see #getObservationModel()
   * @generated
   */
  void setObservationModel(mdef_ObservationModelType value);

} // mdef_ModelDefinitionType

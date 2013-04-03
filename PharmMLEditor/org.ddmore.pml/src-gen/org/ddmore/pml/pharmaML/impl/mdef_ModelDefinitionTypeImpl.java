/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.mdef_CovariateModelType;
import org.ddmore.pml.pharmaML.mdef_ModelDefinitionType;
import org.ddmore.pml.pharmaML.mdef_ObservationModelType;
import org.ddmore.pml.pharmaML.mdef_ParameterModelType;
import org.ddmore.pml.pharmaML.mdef_StructuralModelType;
import org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>mdef Model Definition Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ModelDefinitionTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ModelDefinitionTypeImpl#getVariabilityLevel <em>Variability Level</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ModelDefinitionTypeImpl#getCovariateModel <em>Covariate Model</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ModelDefinitionTypeImpl#getParameterModel <em>Parameter Model</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ModelDefinitionTypeImpl#getStructuraModel <em>Structura Model</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ModelDefinitionTypeImpl#getObservationModel <em>Observation Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_ModelDefinitionTypeImpl extends MinimalEObjectImpl.Container implements mdef_ModelDefinitionType
{
  /**
   * The cached value of the '{@link #getXmlns() <em>Xmlns</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlns()
   * @generated
   * @ordered
   */
  protected EList<ct_xmlns> xmlns;

  /**
   * The cached value of the '{@link #getVariabilityLevel() <em>Variability Level</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariabilityLevel()
   * @generated
   * @ordered
   */
  protected EList<mdef_VariabilityLevelDefnType> variabilityLevel;

  /**
   * The cached value of the '{@link #getCovariateModel() <em>Covariate Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCovariateModel()
   * @generated
   * @ordered
   */
  protected mdef_CovariateModelType covariateModel;

  /**
   * The cached value of the '{@link #getParameterModel() <em>Parameter Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameterModel()
   * @generated
   * @ordered
   */
  protected mdef_ParameterModelType parameterModel;

  /**
   * The cached value of the '{@link #getStructuraModel() <em>Structura Model</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStructuraModel()
   * @generated
   * @ordered
   */
  protected EList<mdef_StructuralModelType> structuraModel;

  /**
   * The cached value of the '{@link #getObservationModel() <em>Observation Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObservationModel()
   * @generated
   * @ordered
   */
  protected mdef_ObservationModelType observationModel;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_ModelDefinitionTypeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return PharmaMLPackage.eINSTANCE.getmdef_ModelDefinitionType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ct_xmlns> getXmlns()
  {
    if (xmlns == null)
    {
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_VariabilityLevelDefnType> getVariabilityLevel()
  {
    if (variabilityLevel == null)
    {
      variabilityLevel = new EObjectContainmentEList<mdef_VariabilityLevelDefnType>(mdef_VariabilityLevelDefnType.class, this, PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__VARIABILITY_LEVEL);
    }
    return variabilityLevel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CovariateModelType getCovariateModel()
  {
    return covariateModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCovariateModel(mdef_CovariateModelType newCovariateModel, NotificationChain msgs)
  {
    mdef_CovariateModelType oldCovariateModel = covariateModel;
    covariateModel = newCovariateModel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL, oldCovariateModel, newCovariateModel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCovariateModel(mdef_CovariateModelType newCovariateModel)
  {
    if (newCovariateModel != covariateModel)
    {
      NotificationChain msgs = null;
      if (covariateModel != null)
        msgs = ((InternalEObject)covariateModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL, null, msgs);
      if (newCovariateModel != null)
        msgs = ((InternalEObject)newCovariateModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL, null, msgs);
      msgs = basicSetCovariateModel(newCovariateModel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL, newCovariateModel, newCovariateModel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ParameterModelType getParameterModel()
  {
    return parameterModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameterModel(mdef_ParameterModelType newParameterModel, NotificationChain msgs)
  {
    mdef_ParameterModelType oldParameterModel = parameterModel;
    parameterModel = newParameterModel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL, oldParameterModel, newParameterModel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameterModel(mdef_ParameterModelType newParameterModel)
  {
    if (newParameterModel != parameterModel)
    {
      NotificationChain msgs = null;
      if (parameterModel != null)
        msgs = ((InternalEObject)parameterModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL, null, msgs);
      if (newParameterModel != null)
        msgs = ((InternalEObject)newParameterModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL, null, msgs);
      msgs = basicSetParameterModel(newParameterModel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL, newParameterModel, newParameterModel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_StructuralModelType> getStructuraModel()
  {
    if (structuraModel == null)
    {
      structuraModel = new EObjectContainmentEList<mdef_StructuralModelType>(mdef_StructuralModelType.class, this, PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__STRUCTURA_MODEL);
    }
    return structuraModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ObservationModelType getObservationModel()
  {
    return observationModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetObservationModel(mdef_ObservationModelType newObservationModel, NotificationChain msgs)
  {
    mdef_ObservationModelType oldObservationModel = observationModel;
    observationModel = newObservationModel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL, oldObservationModel, newObservationModel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setObservationModel(mdef_ObservationModelType newObservationModel)
  {
    if (newObservationModel != observationModel)
    {
      NotificationChain msgs = null;
      if (observationModel != null)
        msgs = ((InternalEObject)observationModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL, null, msgs);
      if (newObservationModel != null)
        msgs = ((InternalEObject)newObservationModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL, null, msgs);
      msgs = basicSetObservationModel(newObservationModel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL, newObservationModel, newObservationModel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__VARIABILITY_LEVEL:
        return ((InternalEList<?>)getVariabilityLevel()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL:
        return basicSetCovariateModel(null, msgs);
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL:
        return basicSetParameterModel(null, msgs);
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__STRUCTURA_MODEL:
        return ((InternalEList<?>)getStructuraModel()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL:
        return basicSetObservationModel(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__VARIABILITY_LEVEL:
        return getVariabilityLevel();
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL:
        return getCovariateModel();
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL:
        return getParameterModel();
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__STRUCTURA_MODEL:
        return getStructuraModel();
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL:
        return getObservationModel();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__VARIABILITY_LEVEL:
        getVariabilityLevel().clear();
        getVariabilityLevel().addAll((Collection<? extends mdef_VariabilityLevelDefnType>)newValue);
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL:
        setCovariateModel((mdef_CovariateModelType)newValue);
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL:
        setParameterModel((mdef_ParameterModelType)newValue);
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__STRUCTURA_MODEL:
        getStructuraModel().clear();
        getStructuraModel().addAll((Collection<? extends mdef_StructuralModelType>)newValue);
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL:
        setObservationModel((mdef_ObservationModelType)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__VARIABILITY_LEVEL:
        getVariabilityLevel().clear();
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL:
        setCovariateModel((mdef_CovariateModelType)null);
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL:
        setParameterModel((mdef_ParameterModelType)null);
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__STRUCTURA_MODEL:
        getStructuraModel().clear();
        return;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL:
        setObservationModel((mdef_ObservationModelType)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__VARIABILITY_LEVEL:
        return variabilityLevel != null && !variabilityLevel.isEmpty();
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__COVARIATE_MODEL:
        return covariateModel != null;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__PARAMETER_MODEL:
        return parameterModel != null;
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__STRUCTURA_MODEL:
        return structuraModel != null && !structuraModel.isEmpty();
      case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE__OBSERVATION_MODEL:
        return observationModel != null;
    }
    return super.eIsSet(featureID);
  }

} //mdef_ModelDefinitionTypeImpl

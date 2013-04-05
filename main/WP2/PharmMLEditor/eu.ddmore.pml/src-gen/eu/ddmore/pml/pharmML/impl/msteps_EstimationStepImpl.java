/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_AnnotationType;
import eu.ddmore.pml.pharmML.ct_id;
import eu.ddmore.pml.pharmML.msteps_EstimationOperationType;
import eu.ddmore.pml.pharmML.msteps_EstimationStep;
import eu.ddmore.pml.pharmML.msteps_InitialValueType;
import eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType;
import eu.ddmore.pml.pharmML.msteps_ParametersToEstimateType;

import java.util.Collection;

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
 * An implementation of the model object '<em><b>msteps Estimation Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationStepImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationStepImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationStepImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationStepImpl#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationStepImpl#getObjectiveDataSet <em>Objective Data Set</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationStepImpl#getParametersToEstimate <em>Parameters To Estimate</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationStepImpl#getEstimationOperation <em>Estimation Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_EstimationStepImpl extends MinimalEObjectImpl.Container implements msteps_EstimationStep
{
  /**
   * The cached value of the '{@link #getXmlns() <em>Xmlns</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlns()
   * @generated
   * @ordered
   */
  protected EList<XS_xmlns> xmlns;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected ct_id id;

  /**
   * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected ct_AnnotationType description;

  /**
   * The cached value of the '{@link #getInitialValue() <em>Initial Value</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitialValue()
   * @generated
   * @ordered
   */
  protected EList<msteps_InitialValueType> initialValue;

  /**
   * The cached value of the '{@link #getObjectiveDataSet() <em>Objective Data Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObjectiveDataSet()
   * @generated
   * @ordered
   */
  protected msteps_ObjectiveDataSetType objectiveDataSet;

  /**
   * The cached value of the '{@link #getParametersToEstimate() <em>Parameters To Estimate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParametersToEstimate()
   * @generated
   * @ordered
   */
  protected msteps_ParametersToEstimateType parametersToEstimate;

  /**
   * The cached value of the '{@link #getEstimationOperation() <em>Estimation Operation</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstimationOperation()
   * @generated
   * @ordered
   */
  protected EList<msteps_EstimationOperationType> estimationOperation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_EstimationStepImpl()
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
    return PharmMLPackage.eINSTANCE.getmsteps_EstimationStep();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XS_xmlns> getXmlns()
  {
    if (xmlns == null)
    {
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MSTEPS_ESTIMATION_STEP__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_id getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetId(ct_id newId, NotificationChain msgs)
  {
    ct_id oldId = id;
    id = newId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_STEP__ID, oldId, newId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(ct_id newId)
  {
    if (newId != id)
    {
      NotificationChain msgs = null;
      if (id != null)
        msgs = ((InternalEObject)id).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_STEP__ID, null, msgs);
      if (newId != null)
        msgs = ((InternalEObject)newId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_STEP__ID, null, msgs);
      msgs = basicSetId(newId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_STEP__ID, newId, newId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_AnnotationType getDescription()
  {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDescription(ct_AnnotationType newDescription, NotificationChain msgs)
  {
    ct_AnnotationType oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_STEP__DESCRIPTION, oldDescription, newDescription);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescription(ct_AnnotationType newDescription)
  {
    if (newDescription != description)
    {
      NotificationChain msgs = null;
      if (description != null)
        msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_STEP__DESCRIPTION, null, msgs);
      if (newDescription != null)
        msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_STEP__DESCRIPTION, null, msgs);
      msgs = basicSetDescription(newDescription, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_STEP__DESCRIPTION, newDescription, newDescription));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_InitialValueType> getInitialValue()
  {
    if (initialValue == null)
    {
      initialValue = new EObjectContainmentEList<msteps_InitialValueType>(msteps_InitialValueType.class, this, PharmMLPackage.MSTEPS_ESTIMATION_STEP__INITIAL_VALUE);
    }
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ObjectiveDataSetType getObjectiveDataSet()
  {
    return objectiveDataSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetObjectiveDataSet(msteps_ObjectiveDataSetType newObjectiveDataSet, NotificationChain msgs)
  {
    msteps_ObjectiveDataSetType oldObjectiveDataSet = objectiveDataSet;
    objectiveDataSet = newObjectiveDataSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET, oldObjectiveDataSet, newObjectiveDataSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setObjectiveDataSet(msteps_ObjectiveDataSetType newObjectiveDataSet)
  {
    if (newObjectiveDataSet != objectiveDataSet)
    {
      NotificationChain msgs = null;
      if (objectiveDataSet != null)
        msgs = ((InternalEObject)objectiveDataSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET, null, msgs);
      if (newObjectiveDataSet != null)
        msgs = ((InternalEObject)newObjectiveDataSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET, null, msgs);
      msgs = basicSetObjectiveDataSet(newObjectiveDataSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET, newObjectiveDataSet, newObjectiveDataSet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ParametersToEstimateType getParametersToEstimate()
  {
    return parametersToEstimate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParametersToEstimate(msteps_ParametersToEstimateType newParametersToEstimate, NotificationChain msgs)
  {
    msteps_ParametersToEstimateType oldParametersToEstimate = parametersToEstimate;
    parametersToEstimate = newParametersToEstimate;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE, oldParametersToEstimate, newParametersToEstimate);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParametersToEstimate(msteps_ParametersToEstimateType newParametersToEstimate)
  {
    if (newParametersToEstimate != parametersToEstimate)
    {
      NotificationChain msgs = null;
      if (parametersToEstimate != null)
        msgs = ((InternalEObject)parametersToEstimate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE, null, msgs);
      if (newParametersToEstimate != null)
        msgs = ((InternalEObject)newParametersToEstimate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE, null, msgs);
      msgs = basicSetParametersToEstimate(newParametersToEstimate, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE, newParametersToEstimate, newParametersToEstimate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_EstimationOperationType> getEstimationOperation()
  {
    if (estimationOperation == null)
    {
      estimationOperation = new EObjectContainmentEList<msteps_EstimationOperationType>(msteps_EstimationOperationType.class, this, PharmMLPackage.MSTEPS_ESTIMATION_STEP__ESTIMATION_OPERATION);
    }
    return estimationOperation;
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
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ID:
        return basicSetId(null, msgs);
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__DESCRIPTION:
        return basicSetDescription(null, msgs);
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__INITIAL_VALUE:
        return ((InternalEList<?>)getInitialValue()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET:
        return basicSetObjectiveDataSet(null, msgs);
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE:
        return basicSetParametersToEstimate(null, msgs);
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ESTIMATION_OPERATION:
        return ((InternalEList<?>)getEstimationOperation()).basicRemove(otherEnd, msgs);
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
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__XMLNS:
        return getXmlns();
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ID:
        return getId();
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__DESCRIPTION:
        return getDescription();
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__INITIAL_VALUE:
        return getInitialValue();
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET:
        return getObjectiveDataSet();
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE:
        return getParametersToEstimate();
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ESTIMATION_OPERATION:
        return getEstimationOperation();
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
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ID:
        setId((ct_id)newValue);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__DESCRIPTION:
        setDescription((ct_AnnotationType)newValue);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__INITIAL_VALUE:
        getInitialValue().clear();
        getInitialValue().addAll((Collection<? extends msteps_InitialValueType>)newValue);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET:
        setObjectiveDataSet((msteps_ObjectiveDataSetType)newValue);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE:
        setParametersToEstimate((msteps_ParametersToEstimateType)newValue);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ESTIMATION_OPERATION:
        getEstimationOperation().clear();
        getEstimationOperation().addAll((Collection<? extends msteps_EstimationOperationType>)newValue);
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
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ID:
        setId((ct_id)null);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__DESCRIPTION:
        setDescription((ct_AnnotationType)null);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__INITIAL_VALUE:
        getInitialValue().clear();
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET:
        setObjectiveDataSet((msteps_ObjectiveDataSetType)null);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE:
        setParametersToEstimate((msteps_ParametersToEstimateType)null);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ESTIMATION_OPERATION:
        getEstimationOperation().clear();
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
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ID:
        return id != null;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__DESCRIPTION:
        return description != null;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__INITIAL_VALUE:
        return initialValue != null && !initialValue.isEmpty();
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__OBJECTIVE_DATA_SET:
        return objectiveDataSet != null;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__PARAMETERS_TO_ESTIMATE:
        return parametersToEstimate != null;
      case PharmMLPackage.MSTEPS_ESTIMATION_STEP__ESTIMATION_OPERATION:
        return estimationOperation != null && !estimationOperation.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //msteps_EstimationStepImpl

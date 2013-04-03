/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_AnnotationType;
import org.ddmore.pml.pharmaML.ct_ReplicatesType;
import org.ddmore.pml.pharmaML.ct_id;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.msteps_InitialValueType;
import org.ddmore.pml.pharmaML.msteps_ObservationsType;
import org.ddmore.pml.pharmaML.msteps_SimDataSetType;
import org.ddmore.pml.pharmaML.msteps_SimulationStep;

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
 * An implementation of the model object '<em><b>msteps Simulation Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_SimulationStepImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_SimulationStepImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_SimulationStepImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_SimulationStepImpl#getReplicates <em>Replicates</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_SimulationStepImpl#getInitialValue <em>Initial Value</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_SimulationStepImpl#getSimDataSet <em>Sim Data Set</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_SimulationStepImpl#getObservations <em>Observations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_SimulationStepImpl extends MinimalEObjectImpl.Container implements msteps_SimulationStep
{
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
   * The cached value of the '{@link #getXmlns() <em>Xmlns</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlns()
   * @generated
   * @ordered
   */
  protected EList<ct_xmlns> xmlns;

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
   * The cached value of the '{@link #getReplicates() <em>Replicates</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReplicates()
   * @generated
   * @ordered
   */
  protected ct_ReplicatesType replicates;

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
   * The cached value of the '{@link #getSimDataSet() <em>Sim Data Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSimDataSet()
   * @generated
   * @ordered
   */
  protected msteps_SimDataSetType simDataSet;

  /**
   * The cached value of the '{@link #getObservations() <em>Observations</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObservations()
   * @generated
   * @ordered
   */
  protected msteps_ObservationsType observations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_SimulationStepImpl()
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
    return PharmaMLPackage.eINSTANCE.getmsteps_SimulationStep();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__ID, oldId, newId);
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
        msgs = ((InternalEObject)id).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__ID, null, msgs);
      if (newId != null)
        msgs = ((InternalEObject)newId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__ID, null, msgs);
      msgs = basicSetId(newId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__ID, newId, newId));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MSTEPS_SIMULATION_STEP__XMLNS);
    }
    return xmlns;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__DESCRIPTION, oldDescription, newDescription);
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
        msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__DESCRIPTION, null, msgs);
      if (newDescription != null)
        msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__DESCRIPTION, null, msgs);
      msgs = basicSetDescription(newDescription, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__DESCRIPTION, newDescription, newDescription));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_ReplicatesType getReplicates()
  {
    return replicates;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetReplicates(ct_ReplicatesType newReplicates, NotificationChain msgs)
  {
    ct_ReplicatesType oldReplicates = replicates;
    replicates = newReplicates;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__REPLICATES, oldReplicates, newReplicates);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReplicates(ct_ReplicatesType newReplicates)
  {
    if (newReplicates != replicates)
    {
      NotificationChain msgs = null;
      if (replicates != null)
        msgs = ((InternalEObject)replicates).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__REPLICATES, null, msgs);
      if (newReplicates != null)
        msgs = ((InternalEObject)newReplicates).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__REPLICATES, null, msgs);
      msgs = basicSetReplicates(newReplicates, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__REPLICATES, newReplicates, newReplicates));
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
      initialValue = new EObjectContainmentEList<msteps_InitialValueType>(msteps_InitialValueType.class, this, PharmaMLPackage.MSTEPS_SIMULATION_STEP__INITIAL_VALUE);
    }
    return initialValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_SimDataSetType getSimDataSet()
  {
    return simDataSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSimDataSet(msteps_SimDataSetType newSimDataSet, NotificationChain msgs)
  {
    msteps_SimDataSetType oldSimDataSet = simDataSet;
    simDataSet = newSimDataSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__SIM_DATA_SET, oldSimDataSet, newSimDataSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSimDataSet(msteps_SimDataSetType newSimDataSet)
  {
    if (newSimDataSet != simDataSet)
    {
      NotificationChain msgs = null;
      if (simDataSet != null)
        msgs = ((InternalEObject)simDataSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__SIM_DATA_SET, null, msgs);
      if (newSimDataSet != null)
        msgs = ((InternalEObject)newSimDataSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__SIM_DATA_SET, null, msgs);
      msgs = basicSetSimDataSet(newSimDataSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__SIM_DATA_SET, newSimDataSet, newSimDataSet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ObservationsType getObservations()
  {
    return observations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetObservations(msteps_ObservationsType newObservations, NotificationChain msgs)
  {
    msteps_ObservationsType oldObservations = observations;
    observations = newObservations;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__OBSERVATIONS, oldObservations, newObservations);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setObservations(msteps_ObservationsType newObservations)
  {
    if (newObservations != observations)
    {
      NotificationChain msgs = null;
      if (observations != null)
        msgs = ((InternalEObject)observations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__OBSERVATIONS, null, msgs);
      if (newObservations != null)
        msgs = ((InternalEObject)newObservations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_SIMULATION_STEP__OBSERVATIONS, null, msgs);
      msgs = basicSetObservations(newObservations, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_SIMULATION_STEP__OBSERVATIONS, newObservations, newObservations));
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
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__ID:
        return basicSetId(null, msgs);
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__DESCRIPTION:
        return basicSetDescription(null, msgs);
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__REPLICATES:
        return basicSetReplicates(null, msgs);
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__INITIAL_VALUE:
        return ((InternalEList<?>)getInitialValue()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__SIM_DATA_SET:
        return basicSetSimDataSet(null, msgs);
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__OBSERVATIONS:
        return basicSetObservations(null, msgs);
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
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__ID:
        return getId();
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__DESCRIPTION:
        return getDescription();
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__REPLICATES:
        return getReplicates();
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__INITIAL_VALUE:
        return getInitialValue();
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__SIM_DATA_SET:
        return getSimDataSet();
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__OBSERVATIONS:
        return getObservations();
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
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__ID:
        setId((ct_id)newValue);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__DESCRIPTION:
        setDescription((ct_AnnotationType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__REPLICATES:
        setReplicates((ct_ReplicatesType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__INITIAL_VALUE:
        getInitialValue().clear();
        getInitialValue().addAll((Collection<? extends msteps_InitialValueType>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__SIM_DATA_SET:
        setSimDataSet((msteps_SimDataSetType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__OBSERVATIONS:
        setObservations((msteps_ObservationsType)newValue);
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
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__ID:
        setId((ct_id)null);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__DESCRIPTION:
        setDescription((ct_AnnotationType)null);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__REPLICATES:
        setReplicates((ct_ReplicatesType)null);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__INITIAL_VALUE:
        getInitialValue().clear();
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__SIM_DATA_SET:
        setSimDataSet((msteps_SimDataSetType)null);
        return;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__OBSERVATIONS:
        setObservations((msteps_ObservationsType)null);
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
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__ID:
        return id != null;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__DESCRIPTION:
        return description != null;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__REPLICATES:
        return replicates != null;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__INITIAL_VALUE:
        return initialValue != null && !initialValue.isEmpty();
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__SIM_DATA_SET:
        return simDataSet != null;
      case PharmaMLPackage.MSTEPS_SIMULATION_STEP__OBSERVATIONS:
        return observations != null;
    }
    return super.eIsSet(featureID);
  }

} //msteps_SimulationStepImpl

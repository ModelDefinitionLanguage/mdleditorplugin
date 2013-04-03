/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_Name;
import org.ddmore.pml.pharmaML.ct_id;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.mdef_CorrelationType;
import org.ddmore.pml.pharmaML.mdef_ParameterModelType;
import org.ddmore.pml.pharmaML.mdef_ParameterType;

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
 * An implementation of the model object '<em><b>mdef Parameter Model Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterModelTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterModelTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterModelTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterModelTypeImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterModelTypeImpl#getCorrelation <em>Correlation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_ParameterModelTypeImpl extends MinimalEObjectImpl.Container implements mdef_ParameterModelType
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
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected ct_Name name;

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
   * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameter()
   * @generated
   * @ordered
   */
  protected EList<mdef_ParameterType> parameter;

  /**
   * The cached value of the '{@link #getCorrelation() <em>Correlation</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCorrelation()
   * @generated
   * @ordered
   */
  protected EList<mdef_CorrelationType> correlation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_ParameterModelTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmdef_ParameterModelType();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID, oldId, newId);
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
        msgs = ((InternalEObject)id).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID, null, msgs);
      if (newId != null)
        msgs = ((InternalEObject)newId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID, null, msgs);
      msgs = basicSetId(newId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID, newId, newId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Name getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(ct_Name newName, NotificationChain msgs)
  {
    ct_Name oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(ct_Name newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME, newName, newName));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_ParameterType> getParameter()
  {
    if (parameter == null)
    {
      parameter = new EObjectContainmentEList<mdef_ParameterType>(mdef_ParameterType.class, this, PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER);
    }
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_CorrelationType> getCorrelation()
  {
    if (correlation == null)
    {
      correlation = new EObjectContainmentEList<mdef_CorrelationType>(mdef_CorrelationType.class, this, PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION);
    }
    return correlation;
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
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        return basicSetId(null, msgs);
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        return ((InternalEList<?>)getParameter()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
        return ((InternalEList<?>)getCorrelation()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        return getId();
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        return getName();
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        return getParameter();
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
        return getCorrelation();
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
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        setId((ct_id)newValue);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        setName((ct_Name)newValue);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        getParameter().clear();
        getParameter().addAll((Collection<? extends mdef_ParameterType>)newValue);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
        getCorrelation().clear();
        getCorrelation().addAll((Collection<? extends mdef_CorrelationType>)newValue);
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
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        setId((ct_id)null);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        setName((ct_Name)null);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        getParameter().clear();
        return;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
        getCorrelation().clear();
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
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        return id != null;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        return name != null;
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        return parameter != null && !parameter.isEmpty();
      case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
        return correlation != null && !correlation.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //mdef_ParameterModelTypeImpl

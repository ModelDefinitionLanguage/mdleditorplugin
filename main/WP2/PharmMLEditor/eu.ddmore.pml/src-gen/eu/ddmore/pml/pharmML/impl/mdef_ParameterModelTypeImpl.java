/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_id;
import eu.ddmore.pml.pharmML.ct_name;
import eu.ddmore.pml.pharmML.mdef_CorrelationType;
import eu.ddmore.pml.pharmML.mdef_ParameterModelType;
import eu.ddmore.pml.pharmML.mdef_ParameterType;

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
 * An implementation of the model object '<em><b>mdef Parameter Model Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterModelTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterModelTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterModelTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterModelTypeImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterModelTypeImpl#getCorrelation <em>Correlation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_ParameterModelTypeImpl extends MinimalEObjectImpl.Container implements mdef_ParameterModelType
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
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected ct_name name;

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
    return PharmMLPackage.eINSTANCE.getmdef_ParameterModelType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS);
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID, oldId, newId);
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
        msgs = ((InternalEObject)id).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID, null, msgs);
      if (newId != null)
        msgs = ((InternalEObject)newId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID, null, msgs);
      msgs = basicSetId(newId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID, newId, newId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_name getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(ct_name newName, NotificationChain msgs)
  {
    ct_name oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(ct_name newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME, newName, newName));
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
      parameter = new EObjectContainmentEList<mdef_ParameterType>(mdef_ParameterType.class, this, PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER);
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
      correlation = new EObjectContainmentEList<mdef_CorrelationType>(mdef_CorrelationType.class, this, PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION);
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
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        return basicSetId(null, msgs);
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        return ((InternalEList<?>)getParameter()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
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
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        return getId();
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        return getName();
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        return getParameter();
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
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
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        setId((ct_id)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        setName((ct_name)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        getParameter().clear();
        getParameter().addAll((Collection<? extends mdef_ParameterType>)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
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
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        setId((ct_id)null);
        return;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        setName((ct_name)null);
        return;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        getParameter().clear();
        return;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
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
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__ID:
        return id != null;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__NAME:
        return name != null;
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__PARAMETER:
        return parameter != null && !parameter.isEmpty();
      case PharmMLPackage.MDEF_PARAMETER_MODEL_TYPE__CORRELATION:
        return correlation != null && !correlation.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //mdef_ParameterModelTypeImpl

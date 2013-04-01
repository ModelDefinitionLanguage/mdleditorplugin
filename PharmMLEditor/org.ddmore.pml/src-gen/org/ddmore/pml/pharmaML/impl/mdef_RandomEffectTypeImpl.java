/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_Name;
import org.ddmore.pml.pharmaML.ct_levelId;
import org.ddmore.pml.pharmaML.ct_symbId;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.mdef_RandomEffectType;
import org.ddmore.pml.pharmaML.uncert_DistributionType;

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
 * An implementation of the model object '<em><b>mdef Random Effect Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_RandomEffectTypeImpl#getLevelId <em>Level Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_RandomEffectTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_RandomEffectTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_RandomEffectTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_RandomEffectTypeImpl#getDistribution <em>Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_RandomEffectTypeImpl extends MinimalEObjectImpl.Container implements mdef_RandomEffectType
{
  /**
   * The cached value of the '{@link #getLevelId() <em>Level Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLevelId()
   * @generated
   * @ordered
   */
  protected ct_levelId levelId;

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
   * The cached value of the '{@link #getSymbId() <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbId()
   * @generated
   * @ordered
   */
  protected ct_symbId symbId;

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
   * The cached value of the '{@link #getDistribution() <em>Distribution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDistribution()
   * @generated
   * @ordered
   */
  protected uncert_DistributionType distribution;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_RandomEffectTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmdef_RandomEffectType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_levelId getLevelId()
  {
    return levelId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLevelId(ct_levelId newLevelId, NotificationChain msgs)
  {
    ct_levelId oldLevelId = levelId;
    levelId = newLevelId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID, oldLevelId, newLevelId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLevelId(ct_levelId newLevelId)
  {
    if (newLevelId != levelId)
    {
      NotificationChain msgs = null;
      if (levelId != null)
        msgs = ((InternalEObject)levelId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID, null, msgs);
      if (newLevelId != null)
        msgs = ((InternalEObject)newLevelId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID, null, msgs);
      msgs = basicSetLevelId(newLevelId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID, newLevelId, newLevelId));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__NAME, oldName, newName);
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
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_symbId getSymbId()
  {
    return symbId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbId(ct_symbId newSymbId, NotificationChain msgs)
  {
    ct_symbId oldSymbId = symbId;
    symbId = newSymbId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__SYMB_ID, oldSymbId, newSymbId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbId(ct_symbId newSymbId)
  {
    if (newSymbId != symbId)
    {
      NotificationChain msgs = null;
      if (symbId != null)
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__SYMB_ID, newSymbId, newSymbId));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_DistributionType getDistribution()
  {
    return distribution;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDistribution(uncert_DistributionType newDistribution, NotificationChain msgs)
  {
    uncert_DistributionType oldDistribution = distribution;
    distribution = newDistribution;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION, oldDistribution, newDistribution);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDistribution(uncert_DistributionType newDistribution)
  {
    if (newDistribution != distribution)
    {
      NotificationChain msgs = null;
      if (distribution != null)
        msgs = ((InternalEObject)distribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION, null, msgs);
      if (newDistribution != null)
        msgs = ((InternalEObject)newDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION, null, msgs);
      msgs = basicSetDistribution(newDistribution, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION, newDistribution, newDistribution));
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
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID:
        return basicSetLevelId(null, msgs);
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION:
        return basicSetDistribution(null, msgs);
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
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID:
        return getLevelId();
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__NAME:
        return getName();
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__SYMB_ID:
        return getSymbId();
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION:
        return getDistribution();
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
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID:
        setLevelId((ct_levelId)newValue);
        return;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__NAME:
        setName((ct_Name)newValue);
        return;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__SYMB_ID:
        setSymbId((ct_symbId)newValue);
        return;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION:
        setDistribution((uncert_DistributionType)newValue);
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
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID:
        setLevelId((ct_levelId)null);
        return;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__NAME:
        setName((ct_Name)null);
        return;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__SYMB_ID:
        setSymbId((ct_symbId)null);
        return;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION:
        setDistribution((uncert_DistributionType)null);
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
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__LEVEL_ID:
        return levelId != null;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__NAME:
        return name != null;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__SYMB_ID:
        return symbId != null;
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE__DISTRIBUTION:
        return distribution != null;
    }
    return super.eIsSet(featureID);
  }

} //mdef_RandomEffectTypeImpl

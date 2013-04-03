/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.Math_VarType;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_levelId;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.mdefCovarianceType;
import org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType;
import org.ddmore.pml.pharmaML.mdef_CorrelationType;

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
 * An implementation of the model object '<em><b>mdef Correlation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_CorrelationTypeImpl#getLevelId <em>Level Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_CorrelationTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_CorrelationTypeImpl#getParamVar1 <em>Param Var1</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_CorrelationTypeImpl#getParamVar2 <em>Param Var2</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_CorrelationTypeImpl#getCorrelationCoefficient <em>Correlation Coefficient</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_CorrelationTypeImpl#getCovariance <em>Covariance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_CorrelationTypeImpl extends MinimalEObjectImpl.Container implements mdef_CorrelationType
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
   * The cached value of the '{@link #getXmlns() <em>Xmlns</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlns()
   * @generated
   * @ordered
   */
  protected EList<ct_xmlns> xmlns;

  /**
   * The cached value of the '{@link #getParamVar1() <em>Param Var1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParamVar1()
   * @generated
   * @ordered
   */
  protected Math_VarType paramVar1;

  /**
   * The cached value of the '{@link #getParamVar2() <em>Param Var2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParamVar2()
   * @generated
   * @ordered
   */
  protected Math_VarType paramVar2;

  /**
   * The cached value of the '{@link #getCorrelationCoefficient() <em>Correlation Coefficient</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCorrelationCoefficient()
   * @generated
   * @ordered
   */
  protected mdef_CorrelationCoefficientType correlationCoefficient;

  /**
   * The cached value of the '{@link #getCovariance() <em>Covariance</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCovariance()
   * @generated
   * @ordered
   */
  protected mdefCovarianceType covariance;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_CorrelationTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmdef_CorrelationType();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__LEVEL_ID, oldLevelId, newLevelId);
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
        msgs = ((InternalEObject)levelId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__LEVEL_ID, null, msgs);
      if (newLevelId != null)
        msgs = ((InternalEObject)newLevelId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__LEVEL_ID, null, msgs);
      msgs = basicSetLevelId(newLevelId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__LEVEL_ID, newLevelId, newLevelId));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MDEF_CORRELATION_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_VarType getParamVar1()
  {
    return paramVar1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParamVar1(Math_VarType newParamVar1, NotificationChain msgs)
  {
    Math_VarType oldParamVar1 = paramVar1;
    paramVar1 = newParamVar1;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR1, oldParamVar1, newParamVar1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParamVar1(Math_VarType newParamVar1)
  {
    if (newParamVar1 != paramVar1)
    {
      NotificationChain msgs = null;
      if (paramVar1 != null)
        msgs = ((InternalEObject)paramVar1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR1, null, msgs);
      if (newParamVar1 != null)
        msgs = ((InternalEObject)newParamVar1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR1, null, msgs);
      msgs = basicSetParamVar1(newParamVar1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR1, newParamVar1, newParamVar1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_VarType getParamVar2()
  {
    return paramVar2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParamVar2(Math_VarType newParamVar2, NotificationChain msgs)
  {
    Math_VarType oldParamVar2 = paramVar2;
    paramVar2 = newParamVar2;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR2, oldParamVar2, newParamVar2);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParamVar2(Math_VarType newParamVar2)
  {
    if (newParamVar2 != paramVar2)
    {
      NotificationChain msgs = null;
      if (paramVar2 != null)
        msgs = ((InternalEObject)paramVar2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR2, null, msgs);
      if (newParamVar2 != null)
        msgs = ((InternalEObject)newParamVar2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR2, null, msgs);
      msgs = basicSetParamVar2(newParamVar2, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR2, newParamVar2, newParamVar2));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CorrelationCoefficientType getCorrelationCoefficient()
  {
    return correlationCoefficient;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCorrelationCoefficient(mdef_CorrelationCoefficientType newCorrelationCoefficient, NotificationChain msgs)
  {
    mdef_CorrelationCoefficientType oldCorrelationCoefficient = correlationCoefficient;
    correlationCoefficient = newCorrelationCoefficient;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT, oldCorrelationCoefficient, newCorrelationCoefficient);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCorrelationCoefficient(mdef_CorrelationCoefficientType newCorrelationCoefficient)
  {
    if (newCorrelationCoefficient != correlationCoefficient)
    {
      NotificationChain msgs = null;
      if (correlationCoefficient != null)
        msgs = ((InternalEObject)correlationCoefficient).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT, null, msgs);
      if (newCorrelationCoefficient != null)
        msgs = ((InternalEObject)newCorrelationCoefficient).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT, null, msgs);
      msgs = basicSetCorrelationCoefficient(newCorrelationCoefficient, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT, newCorrelationCoefficient, newCorrelationCoefficient));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdefCovarianceType getCovariance()
  {
    return covariance;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCovariance(mdefCovarianceType newCovariance, NotificationChain msgs)
  {
    mdefCovarianceType oldCovariance = covariance;
    covariance = newCovariance;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__COVARIANCE, oldCovariance, newCovariance);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCovariance(mdefCovarianceType newCovariance)
  {
    if (newCovariance != covariance)
    {
      NotificationChain msgs = null;
      if (covariance != null)
        msgs = ((InternalEObject)covariance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__COVARIANCE, null, msgs);
      if (newCovariance != null)
        msgs = ((InternalEObject)newCovariance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_CORRELATION_TYPE__COVARIANCE, null, msgs);
      msgs = basicSetCovariance(newCovariance, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_CORRELATION_TYPE__COVARIANCE, newCovariance, newCovariance));
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
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__LEVEL_ID:
        return basicSetLevelId(null, msgs);
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR1:
        return basicSetParamVar1(null, msgs);
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR2:
        return basicSetParamVar2(null, msgs);
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT:
        return basicSetCorrelationCoefficient(null, msgs);
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__COVARIANCE:
        return basicSetCovariance(null, msgs);
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
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__LEVEL_ID:
        return getLevelId();
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR1:
        return getParamVar1();
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR2:
        return getParamVar2();
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT:
        return getCorrelationCoefficient();
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__COVARIANCE:
        return getCovariance();
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
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__LEVEL_ID:
        setLevelId((ct_levelId)newValue);
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR1:
        setParamVar1((Math_VarType)newValue);
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR2:
        setParamVar2((Math_VarType)newValue);
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT:
        setCorrelationCoefficient((mdef_CorrelationCoefficientType)newValue);
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__COVARIANCE:
        setCovariance((mdefCovarianceType)newValue);
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
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__LEVEL_ID:
        setLevelId((ct_levelId)null);
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR1:
        setParamVar1((Math_VarType)null);
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR2:
        setParamVar2((Math_VarType)null);
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT:
        setCorrelationCoefficient((mdef_CorrelationCoefficientType)null);
        return;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__COVARIANCE:
        setCovariance((mdefCovarianceType)null);
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
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__LEVEL_ID:
        return levelId != null;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR1:
        return paramVar1 != null;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__PARAM_VAR2:
        return paramVar2 != null;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__CORRELATION_COEFFICIENT:
        return correlationCoefficient != null;
      case PharmaMLPackage.MDEF_CORRELATION_TYPE__COVARIANCE:
        return covariance != null;
    }
    return super.eIsSet(featureID);
  }

} //mdef_CorrelationTypeImpl

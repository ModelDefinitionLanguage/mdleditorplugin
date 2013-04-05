/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_VarType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_symbId;
import eu.ddmore.pml.pharmML.mdef_ContinuousObsModelType;
import eu.ddmore.pml.pharmML.mdef_ErrorModel;
import eu.ddmore.pml.pharmML.mdef_RandomEffectType;

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
 * An implementation of the model object '<em><b>mdef Continuous Obs Model Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousObsModelTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousObsModelTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousObsModelTypeImpl#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousObsModelTypeImpl#getErrorModel <em>Error Model</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousObsModelTypeImpl#getRandomEffect <em>Random Effect</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_ContinuousObsModelTypeImpl extends MinimalEObjectImpl.Container implements mdef_ContinuousObsModelType
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
   * The cached value of the '{@link #getSymbId() <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbId()
   * @generated
   * @ordered
   */
  protected ct_symbId symbId;

  /**
   * The cached value of the '{@link #getVar() <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVar()
   * @generated
   * @ordered
   */
  protected Math_VarType var;

  /**
   * The cached value of the '{@link #getErrorModel() <em>Error Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getErrorModel()
   * @generated
   * @ordered
   */
  protected mdef_ErrorModel errorModel;

  /**
   * The cached value of the '{@link #getRandomEffect() <em>Random Effect</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRandomEffect()
   * @generated
   * @ordered
   */
  protected EList<mdef_RandomEffectType> randomEffect;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_ContinuousObsModelTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmdef_ContinuousObsModelType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__XMLNS);
    }
    return xmlns;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID, oldSymbId, newSymbId);
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
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID, newSymbId, newSymbId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_VarType getVar()
  {
    return var;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVar(Math_VarType newVar, NotificationChain msgs)
  {
    Math_VarType oldVar = var;
    var = newVar;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR, oldVar, newVar);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVar(Math_VarType newVar)
  {
    if (newVar != var)
    {
      NotificationChain msgs = null;
      if (var != null)
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR, newVar, newVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ErrorModel getErrorModel()
  {
    return errorModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetErrorModel(mdef_ErrorModel newErrorModel, NotificationChain msgs)
  {
    mdef_ErrorModel oldErrorModel = errorModel;
    errorModel = newErrorModel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL, oldErrorModel, newErrorModel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setErrorModel(mdef_ErrorModel newErrorModel)
  {
    if (newErrorModel != errorModel)
    {
      NotificationChain msgs = null;
      if (errorModel != null)
        msgs = ((InternalEObject)errorModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL, null, msgs);
      if (newErrorModel != null)
        msgs = ((InternalEObject)newErrorModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL, null, msgs);
      msgs = basicSetErrorModel(newErrorModel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL, newErrorModel, newErrorModel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_RandomEffectType> getRandomEffect()
  {
    if (randomEffect == null)
    {
      randomEffect = new EObjectContainmentEList<mdef_RandomEffectType>(mdef_RandomEffectType.class, this, PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__RANDOM_EFFECT);
    }
    return randomEffect;
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
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR:
        return basicSetVar(null, msgs);
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL:
        return basicSetErrorModel(null, msgs);
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__RANDOM_EFFECT:
        return ((InternalEList<?>)getRandomEffect()).basicRemove(otherEnd, msgs);
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
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID:
        return getSymbId();
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR:
        return getVar();
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL:
        return getErrorModel();
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__RANDOM_EFFECT:
        return getRandomEffect();
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
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID:
        setSymbId((ct_symbId)newValue);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR:
        setVar((Math_VarType)newValue);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL:
        setErrorModel((mdef_ErrorModel)newValue);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__RANDOM_EFFECT:
        getRandomEffect().clear();
        getRandomEffect().addAll((Collection<? extends mdef_RandomEffectType>)newValue);
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
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID:
        setSymbId((ct_symbId)null);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR:
        setVar((Math_VarType)null);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL:
        setErrorModel((mdef_ErrorModel)null);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__RANDOM_EFFECT:
        getRandomEffect().clear();
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
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__SYMB_ID:
        return symbId != null;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__VAR:
        return var != null;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__ERROR_MODEL:
        return errorModel != null;
      case PharmMLPackage.MDEF_CONTINUOUS_OBS_MODEL_TYPE__RANDOM_EFFECT:
        return randomEffect != null && !randomEffect.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //mdef_ContinuousObsModelTypeImpl

/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.design_BolusType;
import eu.ddmore.pml.pharmML.design_DoseAmountType;
import eu.ddmore.pml.pharmML.design_DosingTimesType;
import eu.ddmore.pml.pharmML.design_SteadyStateType;

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
 * An implementation of the model object '<em><b>design Bolus Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_BolusTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_BolusTypeImpl#getDoseAmount <em>Dose Amount</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_BolusTypeImpl#getSteadyState <em>Steady State</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_BolusTypeImpl#getDosingTimes <em>Dosing Times</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_BolusTypeImpl extends MinimalEObjectImpl.Container implements design_BolusType
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
   * The cached value of the '{@link #getDoseAmount() <em>Dose Amount</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDoseAmount()
   * @generated
   * @ordered
   */
  protected design_DoseAmountType doseAmount;

  /**
   * The cached value of the '{@link #getSteadyState() <em>Steady State</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSteadyState()
   * @generated
   * @ordered
   */
  protected design_SteadyStateType steadyState;

  /**
   * The cached value of the '{@link #getDosingTimes() <em>Dosing Times</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDosingTimes()
   * @generated
   * @ordered
   */
  protected design_DosingTimesType dosingTimes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected design_BolusTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getdesign_BolusType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.DESIGN_BOLUS_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DoseAmountType getDoseAmount()
  {
    return doseAmount;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDoseAmount(design_DoseAmountType newDoseAmount, NotificationChain msgs)
  {
    design_DoseAmountType oldDoseAmount = doseAmount;
    doseAmount = newDoseAmount;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_BOLUS_TYPE__DOSE_AMOUNT, oldDoseAmount, newDoseAmount);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDoseAmount(design_DoseAmountType newDoseAmount)
  {
    if (newDoseAmount != doseAmount)
    {
      NotificationChain msgs = null;
      if (doseAmount != null)
        msgs = ((InternalEObject)doseAmount).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_BOLUS_TYPE__DOSE_AMOUNT, null, msgs);
      if (newDoseAmount != null)
        msgs = ((InternalEObject)newDoseAmount).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_BOLUS_TYPE__DOSE_AMOUNT, null, msgs);
      msgs = basicSetDoseAmount(newDoseAmount, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_BOLUS_TYPE__DOSE_AMOUNT, newDoseAmount, newDoseAmount));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_SteadyStateType getSteadyState()
  {
    return steadyState;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSteadyState(design_SteadyStateType newSteadyState, NotificationChain msgs)
  {
    design_SteadyStateType oldSteadyState = steadyState;
    steadyState = newSteadyState;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_BOLUS_TYPE__STEADY_STATE, oldSteadyState, newSteadyState);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSteadyState(design_SteadyStateType newSteadyState)
  {
    if (newSteadyState != steadyState)
    {
      NotificationChain msgs = null;
      if (steadyState != null)
        msgs = ((InternalEObject)steadyState).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_BOLUS_TYPE__STEADY_STATE, null, msgs);
      if (newSteadyState != null)
        msgs = ((InternalEObject)newSteadyState).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_BOLUS_TYPE__STEADY_STATE, null, msgs);
      msgs = basicSetSteadyState(newSteadyState, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_BOLUS_TYPE__STEADY_STATE, newSteadyState, newSteadyState));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingTimesType getDosingTimes()
  {
    return dosingTimes;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDosingTimes(design_DosingTimesType newDosingTimes, NotificationChain msgs)
  {
    design_DosingTimesType oldDosingTimes = dosingTimes;
    dosingTimes = newDosingTimes;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_BOLUS_TYPE__DOSING_TIMES, oldDosingTimes, newDosingTimes);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDosingTimes(design_DosingTimesType newDosingTimes)
  {
    if (newDosingTimes != dosingTimes)
    {
      NotificationChain msgs = null;
      if (dosingTimes != null)
        msgs = ((InternalEObject)dosingTimes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_BOLUS_TYPE__DOSING_TIMES, null, msgs);
      if (newDosingTimes != null)
        msgs = ((InternalEObject)newDosingTimes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_BOLUS_TYPE__DOSING_TIMES, null, msgs);
      msgs = basicSetDosingTimes(newDosingTimes, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_BOLUS_TYPE__DOSING_TIMES, newDosingTimes, newDosingTimes));
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
      case PharmMLPackage.DESIGN_BOLUS_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSE_AMOUNT:
        return basicSetDoseAmount(null, msgs);
      case PharmMLPackage.DESIGN_BOLUS_TYPE__STEADY_STATE:
        return basicSetSteadyState(null, msgs);
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSING_TIMES:
        return basicSetDosingTimes(null, msgs);
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
      case PharmMLPackage.DESIGN_BOLUS_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSE_AMOUNT:
        return getDoseAmount();
      case PharmMLPackage.DESIGN_BOLUS_TYPE__STEADY_STATE:
        return getSteadyState();
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSING_TIMES:
        return getDosingTimes();
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
      case PharmMLPackage.DESIGN_BOLUS_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSE_AMOUNT:
        setDoseAmount((design_DoseAmountType)newValue);
        return;
      case PharmMLPackage.DESIGN_BOLUS_TYPE__STEADY_STATE:
        setSteadyState((design_SteadyStateType)newValue);
        return;
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSING_TIMES:
        setDosingTimes((design_DosingTimesType)newValue);
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
      case PharmMLPackage.DESIGN_BOLUS_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSE_AMOUNT:
        setDoseAmount((design_DoseAmountType)null);
        return;
      case PharmMLPackage.DESIGN_BOLUS_TYPE__STEADY_STATE:
        setSteadyState((design_SteadyStateType)null);
        return;
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSING_TIMES:
        setDosingTimes((design_DosingTimesType)null);
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
      case PharmMLPackage.DESIGN_BOLUS_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSE_AMOUNT:
        return doseAmount != null;
      case PharmMLPackage.DESIGN_BOLUS_TYPE__STEADY_STATE:
        return steadyState != null;
      case PharmMLPackage.DESIGN_BOLUS_TYPE__DOSING_TIMES:
        return dosingTimes != null;
    }
    return super.eIsSet(featureID);
  }

} //design_BolusTypeImpl

/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.design_DosingPeriodType;
import eu.ddmore.pml.pharmML.design_DosingStartType;
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
 * An implementation of the model object '<em><b>design Steady State Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_SteadyStateTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_SteadyStateTypeImpl#getDosingStart <em>Dosing Start</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_SteadyStateTypeImpl#getDosingPeriod <em>Dosing Period</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_SteadyStateTypeImpl extends MinimalEObjectImpl.Container implements design_SteadyStateType
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
   * The cached value of the '{@link #getDosingStart() <em>Dosing Start</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDosingStart()
   * @generated
   * @ordered
   */
  protected design_DosingStartType dosingStart;

  /**
   * The cached value of the '{@link #getDosingPeriod() <em>Dosing Period</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDosingPeriod()
   * @generated
   * @ordered
   */
  protected design_DosingPeriodType dosingPeriod;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected design_SteadyStateTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getdesign_SteadyStateType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.DESIGN_STEADY_STATE_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingStartType getDosingStart()
  {
    return dosingStart;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDosingStart(design_DosingStartType newDosingStart, NotificationChain msgs)
  {
    design_DosingStartType oldDosingStart = dosingStart;
    dosingStart = newDosingStart;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_START, oldDosingStart, newDosingStart);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDosingStart(design_DosingStartType newDosingStart)
  {
    if (newDosingStart != dosingStart)
    {
      NotificationChain msgs = null;
      if (dosingStart != null)
        msgs = ((InternalEObject)dosingStart).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_START, null, msgs);
      if (newDosingStart != null)
        msgs = ((InternalEObject)newDosingStart).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_START, null, msgs);
      msgs = basicSetDosingStart(newDosingStart, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_START, newDosingStart, newDosingStart));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DosingPeriodType getDosingPeriod()
  {
    return dosingPeriod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDosingPeriod(design_DosingPeriodType newDosingPeriod, NotificationChain msgs)
  {
    design_DosingPeriodType oldDosingPeriod = dosingPeriod;
    dosingPeriod = newDosingPeriod;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD, oldDosingPeriod, newDosingPeriod);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDosingPeriod(design_DosingPeriodType newDosingPeriod)
  {
    if (newDosingPeriod != dosingPeriod)
    {
      NotificationChain msgs = null;
      if (dosingPeriod != null)
        msgs = ((InternalEObject)dosingPeriod).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD, null, msgs);
      if (newDosingPeriod != null)
        msgs = ((InternalEObject)newDosingPeriod).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD, null, msgs);
      msgs = basicSetDosingPeriod(newDosingPeriod, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD, newDosingPeriod, newDosingPeriod));
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
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_START:
        return basicSetDosingStart(null, msgs);
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD:
        return basicSetDosingPeriod(null, msgs);
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
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_START:
        return getDosingStart();
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD:
        return getDosingPeriod();
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
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_START:
        setDosingStart((design_DosingStartType)newValue);
        return;
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD:
        setDosingPeriod((design_DosingPeriodType)newValue);
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
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_START:
        setDosingStart((design_DosingStartType)null);
        return;
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD:
        setDosingPeriod((design_DosingPeriodType)null);
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
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_START:
        return dosingStart != null;
      case PharmMLPackage.DESIGN_STEADY_STATE_TYPE__DOSING_PERIOD:
        return dosingPeriod != null;
    }
    return super.eIsSet(featureID);
  }

} //design_SteadyStateTypeImpl

/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.msteps_ObservationsType;
import org.ddmore.pml.pharmaML.msteps_OutputType;
import org.ddmore.pml.pharmaML.msteps_TimepointsType;
import org.ddmore.pml.pharmaML.msteps_WriteToType;

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
 * An implementation of the model object '<em><b>msteps Observations Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ObservationsTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ObservationsTypeImpl#getTimepoints <em>Timepoints</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ObservationsTypeImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ObservationsTypeImpl#getWriteTo <em>Write To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_ObservationsTypeImpl extends MinimalEObjectImpl.Container implements msteps_ObservationsType
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
   * The cached value of the '{@link #getTimepoints() <em>Timepoints</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTimepoints()
   * @generated
   * @ordered
   */
  protected msteps_TimepointsType timepoints;

  /**
   * The cached value of the '{@link #getOutput() <em>Output</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutput()
   * @generated
   * @ordered
   */
  protected EList<msteps_OutputType> output;

  /**
   * The cached value of the '{@link #getWriteTo() <em>Write To</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWriteTo()
   * @generated
   * @ordered
   */
  protected EList<msteps_WriteToType> writeTo;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_ObservationsTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmsteps_ObservationsType();
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_TimepointsType getTimepoints()
  {
    return timepoints;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTimepoints(msteps_TimepointsType newTimepoints, NotificationChain msgs)
  {
    msteps_TimepointsType oldTimepoints = timepoints;
    timepoints = newTimepoints;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS, oldTimepoints, newTimepoints);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTimepoints(msteps_TimepointsType newTimepoints)
  {
    if (newTimepoints != timepoints)
    {
      NotificationChain msgs = null;
      if (timepoints != null)
        msgs = ((InternalEObject)timepoints).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS, null, msgs);
      if (newTimepoints != null)
        msgs = ((InternalEObject)newTimepoints).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS, null, msgs);
      msgs = basicSetTimepoints(newTimepoints, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS, newTimepoints, newTimepoints));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_OutputType> getOutput()
  {
    if (output == null)
    {
      output = new EObjectContainmentEList<msteps_OutputType>(msteps_OutputType.class, this, PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT);
    }
    return output;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_WriteToType> getWriteTo()
  {
    if (writeTo == null)
    {
      writeTo = new EObjectContainmentEList<msteps_WriteToType>(msteps_WriteToType.class, this, PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO);
    }
    return writeTo;
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
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        return basicSetTimepoints(null, msgs);
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        return ((InternalEList<?>)getOutput()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
        return ((InternalEList<?>)getWriteTo()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        return getTimepoints();
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        return getOutput();
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
        return getWriteTo();
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
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        setTimepoints((msteps_TimepointsType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        getOutput().clear();
        getOutput().addAll((Collection<? extends msteps_OutputType>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
        getWriteTo().clear();
        getWriteTo().addAll((Collection<? extends msteps_WriteToType>)newValue);
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
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        setTimepoints((msteps_TimepointsType)null);
        return;
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        getOutput().clear();
        return;
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
        getWriteTo().clear();
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
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        return timepoints != null;
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        return output != null && !output.isEmpty();
      case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
        return writeTo != null && !writeTo.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //msteps_ObservationsTypeImpl

/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.msteps_ObservationsType;
import eu.ddmore.pml.pharmML.msteps_OutputType;
import eu.ddmore.pml.pharmML.msteps_TimepointsType;
import eu.ddmore.pml.pharmML.msteps_WriteToType;

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
 * An implementation of the model object '<em><b>msteps Observations Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ObservationsTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ObservationsTypeImpl#getTimepoints <em>Timepoints</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ObservationsTypeImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ObservationsTypeImpl#getWriteTo <em>Write To</em>}</li>
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
  protected EList<XS_xmlns> xmlns;

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
    return PharmMLPackage.eINSTANCE.getmsteps_ObservationsType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS);
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS, oldTimepoints, newTimepoints);
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
        msgs = ((InternalEObject)timepoints).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS, null, msgs);
      if (newTimepoints != null)
        msgs = ((InternalEObject)newTimepoints).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS, null, msgs);
      msgs = basicSetTimepoints(newTimepoints, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS, newTimepoints, newTimepoints));
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
      output = new EObjectContainmentEList<msteps_OutputType>(msteps_OutputType.class, this, PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT);
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
      writeTo = new EObjectContainmentEList<msteps_WriteToType>(msteps_WriteToType.class, this, PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO);
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
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        return basicSetTimepoints(null, msgs);
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        return ((InternalEList<?>)getOutput()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
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
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        return getTimepoints();
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        return getOutput();
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
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
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        setTimepoints((msteps_TimepointsType)newValue);
        return;
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        getOutput().clear();
        getOutput().addAll((Collection<? extends msteps_OutputType>)newValue);
        return;
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
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
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        setTimepoints((msteps_TimepointsType)null);
        return;
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        getOutput().clear();
        return;
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
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
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__TIMEPOINTS:
        return timepoints != null;
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__OUTPUT:
        return output != null && !output.isEmpty();
      case PharmMLPackage.MSTEPS_OBSERVATIONS_TYPE__WRITE_TO:
        return writeTo != null && !writeTo.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //msteps_ObservationsTypeImpl

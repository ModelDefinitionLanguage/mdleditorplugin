/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.Math_EstimationOperationType;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.msteps_EstimationOperationType;

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
 * An implementation of the model object '<em><b>msteps Estimation Operation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_EstimationOperationTypeImpl#getOpType <em>Op Type</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_EstimationOperationTypeImpl#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_EstimationOperationTypeImpl extends MinimalEObjectImpl.Container implements msteps_EstimationOperationType
{
  /**
   * The cached value of the '{@link #getOpType() <em>Op Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOpType()
   * @generated
   * @ordered
   */
  protected Math_EstimationOperationType opType;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_EstimationOperationTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmsteps_EstimationOperationType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_EstimationOperationType getOpType()
  {
    return opType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOpType(Math_EstimationOperationType newOpType, NotificationChain msgs)
  {
    Math_EstimationOperationType oldOpType = opType;
    opType = newOpType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE, oldOpType, newOpType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOpType(Math_EstimationOperationType newOpType)
  {
    if (newOpType != opType)
    {
      NotificationChain msgs = null;
      if (opType != null)
        msgs = ((InternalEObject)opType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE, null, msgs);
      if (newOpType != null)
        msgs = ((InternalEObject)newOpType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE, null, msgs);
      msgs = basicSetOpType(newOpType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE, newOpType, newOpType));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS);
    }
    return xmlns;
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
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        return basicSetOpType(null, msgs);
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        return getOpType();
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
        return getXmlns();
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
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        setOpType((Math_EstimationOperationType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
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
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        setOpType((Math_EstimationOperationType)null);
        return;
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
        getXmlns().clear();
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
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        return opType != null;
      case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //msteps_EstimationOperationTypeImpl

/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_EstimationOperationType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.msteps_EstimationOperationType;

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
 * An implementation of the model object '<em><b>msteps Estimation Operation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationOperationTypeImpl#getOpType <em>Op Type</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_EstimationOperationTypeImpl#getXmlns <em>Xmlns</em>}</li>
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
  protected EList<XS_xmlns> xmlns;

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
    return PharmMLPackage.eINSTANCE.getmsteps_EstimationOperationType();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE, oldOpType, newOpType);
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
        msgs = ((InternalEObject)opType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE, null, msgs);
      if (newOpType != null)
        msgs = ((InternalEObject)newOpType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE, null, msgs);
      msgs = basicSetOpType(newOpType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE, newOpType, newOpType));
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS);
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
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        return basicSetOpType(null, msgs);
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
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
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        return getOpType();
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
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
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        setOpType((Math_EstimationOperationType)newValue);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
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
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        setOpType((Math_EstimationOperationType)null);
        return;
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
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
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__OP_TYPE:
        return opType != null;
      case PharmMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //msteps_EstimationOperationTypeImpl

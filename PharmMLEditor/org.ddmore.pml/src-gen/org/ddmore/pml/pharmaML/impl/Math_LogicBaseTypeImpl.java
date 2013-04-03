/**
 */
package org.ddmore.pml.pharmaML.impl;

import org.ddmore.pml.pharmaML.Math_FALSE;
import org.ddmore.pml.pharmaML.Math_LogicBaseType;
import org.ddmore.pml.pharmaML.Math_LogicBinopType;
import org.ddmore.pml.pharmaML.Math_LogicUniopType;
import org.ddmore.pml.pharmaML.Math_ScalarType;
import org.ddmore.pml.pharmaML.Math_TRUE;
import org.ddmore.pml.pharmaML.PharmaMLPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Math Logic Base Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicBaseTypeImpl#getLogicBinopType <em>Logic Binop Type</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicBaseTypeImpl#getLogicUniopType <em>Logic Uniop Type</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicBaseTypeImpl#getTrue <em>True</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicBaseTypeImpl#getFalse <em>False</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicBaseTypeImpl#getScalar <em>Scalar</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_LogicBaseTypeImpl extends Math_LogicExprTypeImpl implements Math_LogicBaseType
{
  /**
   * The cached value of the '{@link #getLogicBinopType() <em>Logic Binop Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLogicBinopType()
   * @generated
   * @ordered
   */
  protected Math_LogicBinopType logicBinopType;

  /**
   * The cached value of the '{@link #getLogicUniopType() <em>Logic Uniop Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLogicUniopType()
   * @generated
   * @ordered
   */
  protected Math_LogicUniopType logicUniopType;

  /**
   * The cached value of the '{@link #getTrue() <em>True</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTrue()
   * @generated
   * @ordered
   */
  protected Math_TRUE true_;

  /**
   * The cached value of the '{@link #getFalse() <em>False</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFalse()
   * @generated
   * @ordered
   */
  protected Math_FALSE false_;

  /**
   * The cached value of the '{@link #getScalar() <em>Scalar</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScalar()
   * @generated
   * @ordered
   */
  protected Math_ScalarType scalar;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_LogicBaseTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getMath_LogicBaseType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_LogicBinopType getLogicBinopType()
  {
    return logicBinopType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLogicBinopType(Math_LogicBinopType newLogicBinopType, NotificationChain msgs)
  {
    Math_LogicBinopType oldLogicBinopType = logicBinopType;
    logicBinopType = newLogicBinopType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE, oldLogicBinopType, newLogicBinopType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLogicBinopType(Math_LogicBinopType newLogicBinopType)
  {
    if (newLogicBinopType != logicBinopType)
    {
      NotificationChain msgs = null;
      if (logicBinopType != null)
        msgs = ((InternalEObject)logicBinopType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE, null, msgs);
      if (newLogicBinopType != null)
        msgs = ((InternalEObject)newLogicBinopType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE, null, msgs);
      msgs = basicSetLogicBinopType(newLogicBinopType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE, newLogicBinopType, newLogicBinopType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_LogicUniopType getLogicUniopType()
  {
    return logicUniopType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLogicUniopType(Math_LogicUniopType newLogicUniopType, NotificationChain msgs)
  {
    Math_LogicUniopType oldLogicUniopType = logicUniopType;
    logicUniopType = newLogicUniopType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE, oldLogicUniopType, newLogicUniopType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLogicUniopType(Math_LogicUniopType newLogicUniopType)
  {
    if (newLogicUniopType != logicUniopType)
    {
      NotificationChain msgs = null;
      if (logicUniopType != null)
        msgs = ((InternalEObject)logicUniopType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE, null, msgs);
      if (newLogicUniopType != null)
        msgs = ((InternalEObject)newLogicUniopType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE, null, msgs);
      msgs = basicSetLogicUniopType(newLogicUniopType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE, newLogicUniopType, newLogicUniopType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_TRUE getTrue()
  {
    return true_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTrue(Math_TRUE newTrue, NotificationChain msgs)
  {
    Math_TRUE oldTrue = true_;
    true_ = newTrue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__TRUE, oldTrue, newTrue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTrue(Math_TRUE newTrue)
  {
    if (newTrue != true_)
    {
      NotificationChain msgs = null;
      if (true_ != null)
        msgs = ((InternalEObject)true_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__TRUE, null, msgs);
      if (newTrue != null)
        msgs = ((InternalEObject)newTrue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__TRUE, null, msgs);
      msgs = basicSetTrue(newTrue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__TRUE, newTrue, newTrue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_FALSE getFalse()
  {
    return false_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFalse(Math_FALSE newFalse, NotificationChain msgs)
  {
    Math_FALSE oldFalse = false_;
    false_ = newFalse;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__FALSE, oldFalse, newFalse);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFalse(Math_FALSE newFalse)
  {
    if (newFalse != false_)
    {
      NotificationChain msgs = null;
      if (false_ != null)
        msgs = ((InternalEObject)false_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__FALSE, null, msgs);
      if (newFalse != null)
        msgs = ((InternalEObject)newFalse).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__FALSE, null, msgs);
      msgs = basicSetFalse(newFalse, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__FALSE, newFalse, newFalse));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_ScalarType getScalar()
  {
    return scalar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetScalar(Math_ScalarType newScalar, NotificationChain msgs)
  {
    Math_ScalarType oldScalar = scalar;
    scalar = newScalar;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__SCALAR, oldScalar, newScalar);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScalar(Math_ScalarType newScalar)
  {
    if (newScalar != scalar)
    {
      NotificationChain msgs = null;
      if (scalar != null)
        msgs = ((InternalEObject)scalar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__SCALAR, null, msgs);
      if (newScalar != null)
        msgs = ((InternalEObject)newScalar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_BASE_TYPE__SCALAR, null, msgs);
      msgs = basicSetScalar(newScalar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_BASE_TYPE__SCALAR, newScalar, newScalar));
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
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE:
        return basicSetLogicBinopType(null, msgs);
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE:
        return basicSetLogicUniopType(null, msgs);
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__TRUE:
        return basicSetTrue(null, msgs);
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__FALSE:
        return basicSetFalse(null, msgs);
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__SCALAR:
        return basicSetScalar(null, msgs);
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
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE:
        return getLogicBinopType();
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE:
        return getLogicUniopType();
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__TRUE:
        return getTrue();
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__FALSE:
        return getFalse();
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__SCALAR:
        return getScalar();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE:
        setLogicBinopType((Math_LogicBinopType)newValue);
        return;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE:
        setLogicUniopType((Math_LogicUniopType)newValue);
        return;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__TRUE:
        setTrue((Math_TRUE)newValue);
        return;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__FALSE:
        setFalse((Math_FALSE)newValue);
        return;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__SCALAR:
        setScalar((Math_ScalarType)newValue);
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
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE:
        setLogicBinopType((Math_LogicBinopType)null);
        return;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE:
        setLogicUniopType((Math_LogicUniopType)null);
        return;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__TRUE:
        setTrue((Math_TRUE)null);
        return;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__FALSE:
        setFalse((Math_FALSE)null);
        return;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__SCALAR:
        setScalar((Math_ScalarType)null);
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
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_BINOP_TYPE:
        return logicBinopType != null;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__LOGIC_UNIOP_TYPE:
        return logicUniopType != null;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__TRUE:
        return true_ != null;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__FALSE:
        return false_ != null;
      case PharmaMLPackage.MATH_LOGIC_BASE_TYPE__SCALAR:
        return scalar != null;
    }
    return super.eIsSet(featureID);
  }

} //Math_LogicBaseTypeImpl

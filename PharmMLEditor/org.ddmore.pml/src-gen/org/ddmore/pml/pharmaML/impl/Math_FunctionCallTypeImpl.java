/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.Math_FunctionArgumentType;
import org.ddmore.pml.pharmaML.Math_FunctionCallType;
import org.ddmore.pml.pharmaML.Math_VarType;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_xmlns;

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
 * An implementation of the model object '<em><b>Math Function Call Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_FunctionCallTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_FunctionCallTypeImpl#getFunctionName <em>Function Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_FunctionCallTypeImpl#getFunctionArguments <em>Function Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_FunctionCallTypeImpl extends MinimalEObjectImpl.Container implements Math_FunctionCallType
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
   * The cached value of the '{@link #getFunctionName() <em>Function Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionName()
   * @generated
   * @ordered
   */
  protected Math_VarType functionName;

  /**
   * The cached value of the '{@link #getFunctionArguments() <em>Function Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionArguments()
   * @generated
   * @ordered
   */
  protected EList<Math_FunctionArgumentType> functionArguments;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_FunctionCallTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getMath_FunctionCallType();
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_VarType getFunctionName()
  {
    return functionName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunctionName(Math_VarType newFunctionName, NotificationChain msgs)
  {
    Math_VarType oldFunctionName = functionName;
    functionName = newFunctionName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME, oldFunctionName, newFunctionName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunctionName(Math_VarType newFunctionName)
  {
    if (newFunctionName != functionName)
    {
      NotificationChain msgs = null;
      if (functionName != null)
        msgs = ((InternalEObject)functionName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME, null, msgs);
      if (newFunctionName != null)
        msgs = ((InternalEObject)newFunctionName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME, null, msgs);
      msgs = basicSetFunctionName(newFunctionName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME, newFunctionName, newFunctionName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Math_FunctionArgumentType> getFunctionArguments()
  {
    if (functionArguments == null)
    {
      functionArguments = new EObjectContainmentEList<Math_FunctionArgumentType>(Math_FunctionArgumentType.class, this, PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS);
    }
    return functionArguments;
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
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME:
        return basicSetFunctionName(null, msgs);
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
        return ((InternalEList<?>)getFunctionArguments()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME:
        return getFunctionName();
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
        return getFunctionArguments();
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
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME:
        setFunctionName((Math_VarType)newValue);
        return;
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
        getFunctionArguments().clear();
        getFunctionArguments().addAll((Collection<? extends Math_FunctionArgumentType>)newValue);
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
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME:
        setFunctionName((Math_VarType)null);
        return;
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
        getFunctionArguments().clear();
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
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_NAME:
        return functionName != null;
      case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
        return functionArguments != null && !functionArguments.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //Math_FunctionCallTypeImpl

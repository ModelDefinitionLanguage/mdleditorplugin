/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_FunctionArgumentType;
import eu.ddmore.pml.pharmML.Math_FunctionCallType;
import eu.ddmore.pml.pharmML.Math_VarType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;

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
 * An implementation of the model object '<em><b>Math Function Call Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_FunctionCallTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_FunctionCallTypeImpl#getFunctionname <em>Functionname</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_FunctionCallTypeImpl#getFunctionArguments <em>Function Arguments</em>}</li>
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
  protected EList<XS_xmlns> xmlns;

  /**
   * The cached value of the '{@link #getFunctionname() <em>Functionname</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionname()
   * @generated
   * @ordered
   */
  protected Math_VarType functionname;

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
    return PharmMLPackage.eINSTANCE.getMath_FunctionCallType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_VarType getFunctionname()
  {
    return functionname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunctionname(Math_VarType newFunctionname, NotificationChain msgs)
  {
    Math_VarType oldFunctionname = functionname;
    functionname = newFunctionname;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME, oldFunctionname, newFunctionname);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunctionname(Math_VarType newFunctionname)
  {
    if (newFunctionname != functionname)
    {
      NotificationChain msgs = null;
      if (functionname != null)
        msgs = ((InternalEObject)functionname).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME, null, msgs);
      if (newFunctionname != null)
        msgs = ((InternalEObject)newFunctionname).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME, null, msgs);
      msgs = basicSetFunctionname(newFunctionname, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME, newFunctionname, newFunctionname));
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
      functionArguments = new EObjectContainmentEList<Math_FunctionArgumentType>(Math_FunctionArgumentType.class, this, PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS);
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
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME:
        return basicSetFunctionname(null, msgs);
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
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
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME:
        return getFunctionname();
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
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
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME:
        setFunctionname((Math_VarType)newValue);
        return;
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
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
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME:
        setFunctionname((Math_VarType)null);
        return;
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
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
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTIONNAME:
        return functionname != null;
      case PharmMLPackage.MATH_FUNCTION_CALL_TYPE__FUNCTION_ARGUMENTS:
        return functionArguments != null && !functionArguments.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //Math_FunctionCallTypeImpl

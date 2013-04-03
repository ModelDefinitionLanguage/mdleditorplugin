/**
 */
package org.ddmore.pml.pharmaML.impl;

import org.ddmore.pml.pharmaML.Math_BinopType;
import org.ddmore.pml.pharmaML.Math_ConstantType;
import org.ddmore.pml.pharmaML.Math_FunctionCallType;
import org.ddmore.pml.pharmaML.Math_LogicExprType;
import org.ddmore.pml.pharmaML.Math_StringType;
import org.ddmore.pml.pharmaML.Math_UniopType;
import org.ddmore.pml.pharmaML.Math_VarType;
import org.ddmore.pml.pharmaML.PharmaMLPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Math Logic Expr Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicExprTypeImpl#getConstant <em>Constant</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicExprTypeImpl#getVar <em>Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicExprTypeImpl#getUniop <em>Uniop</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicExprTypeImpl#getBinop <em>Binop</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicExprTypeImpl#getFunctionCall <em>Function Call</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Math_LogicExprTypeImpl#getString <em>String</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_LogicExprTypeImpl extends MinimalEObjectImpl.Container implements Math_LogicExprType
{
  /**
   * The cached value of the '{@link #getConstant() <em>Constant</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstant()
   * @generated
   * @ordered
   */
  protected Math_ConstantType constant;

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
   * The cached value of the '{@link #getUniop() <em>Uniop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUniop()
   * @generated
   * @ordered
   */
  protected Math_UniopType uniop;

  /**
   * The cached value of the '{@link #getBinop() <em>Binop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBinop()
   * @generated
   * @ordered
   */
  protected Math_BinopType binop;

  /**
   * The cached value of the '{@link #getFunctionCall() <em>Function Call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionCall()
   * @generated
   * @ordered
   */
  protected Math_FunctionCallType functionCall;

  /**
   * The cached value of the '{@link #getString() <em>String</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected Math_StringType string;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_LogicExprTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getMath_LogicExprType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_ConstantType getConstant()
  {
    return constant;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstant(Math_ConstantType newConstant, NotificationChain msgs)
  {
    Math_ConstantType oldConstant = constant;
    constant = newConstant;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__CONSTANT, oldConstant, newConstant);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstant(Math_ConstantType newConstant)
  {
    if (newConstant != constant)
    {
      NotificationChain msgs = null;
      if (constant != null)
        msgs = ((InternalEObject)constant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__CONSTANT, null, msgs);
      if (newConstant != null)
        msgs = ((InternalEObject)newConstant).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__CONSTANT, null, msgs);
      msgs = basicSetConstant(newConstant, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__CONSTANT, newConstant, newConstant));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__VAR, oldVar, newVar);
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
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__VAR, newVar, newVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_UniopType getUniop()
  {
    return uniop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUniop(Math_UniopType newUniop, NotificationChain msgs)
  {
    Math_UniopType oldUniop = uniop;
    uniop = newUniop;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__UNIOP, oldUniop, newUniop);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUniop(Math_UniopType newUniop)
  {
    if (newUniop != uniop)
    {
      NotificationChain msgs = null;
      if (uniop != null)
        msgs = ((InternalEObject)uniop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__UNIOP, null, msgs);
      if (newUniop != null)
        msgs = ((InternalEObject)newUniop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__UNIOP, null, msgs);
      msgs = basicSetUniop(newUniop, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__UNIOP, newUniop, newUniop));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_BinopType getBinop()
  {
    return binop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBinop(Math_BinopType newBinop, NotificationChain msgs)
  {
    Math_BinopType oldBinop = binop;
    binop = newBinop;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__BINOP, oldBinop, newBinop);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBinop(Math_BinopType newBinop)
  {
    if (newBinop != binop)
    {
      NotificationChain msgs = null;
      if (binop != null)
        msgs = ((InternalEObject)binop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__BINOP, null, msgs);
      if (newBinop != null)
        msgs = ((InternalEObject)newBinop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__BINOP, null, msgs);
      msgs = basicSetBinop(newBinop, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__BINOP, newBinop, newBinop));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_FunctionCallType getFunctionCall()
  {
    return functionCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunctionCall(Math_FunctionCallType newFunctionCall, NotificationChain msgs)
  {
    Math_FunctionCallType oldFunctionCall = functionCall;
    functionCall = newFunctionCall;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL, oldFunctionCall, newFunctionCall);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunctionCall(Math_FunctionCallType newFunctionCall)
  {
    if (newFunctionCall != functionCall)
    {
      NotificationChain msgs = null;
      if (functionCall != null)
        msgs = ((InternalEObject)functionCall).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL, null, msgs);
      if (newFunctionCall != null)
        msgs = ((InternalEObject)newFunctionCall).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL, null, msgs);
      msgs = basicSetFunctionCall(newFunctionCall, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL, newFunctionCall, newFunctionCall));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_StringType getString()
  {
    return string;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetString(Math_StringType newString, NotificationChain msgs)
  {
    Math_StringType oldString = string;
    string = newString;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__STRING, oldString, newString);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setString(Math_StringType newString)
  {
    if (newString != string)
    {
      NotificationChain msgs = null;
      if (string != null)
        msgs = ((InternalEObject)string).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__STRING, null, msgs);
      if (newString != null)
        msgs = ((InternalEObject)newString).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__STRING, null, msgs);
      msgs = basicSetString(newString, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__STRING, newString, newString));
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
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__CONSTANT:
        return basicSetConstant(null, msgs);
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__VAR:
        return basicSetVar(null, msgs);
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__UNIOP:
        return basicSetUniop(null, msgs);
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__BINOP:
        return basicSetBinop(null, msgs);
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL:
        return basicSetFunctionCall(null, msgs);
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__STRING:
        return basicSetString(null, msgs);
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
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__CONSTANT:
        return getConstant();
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__VAR:
        return getVar();
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__UNIOP:
        return getUniop();
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__BINOP:
        return getBinop();
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL:
        return getFunctionCall();
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__STRING:
        return getString();
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
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__CONSTANT:
        setConstant((Math_ConstantType)newValue);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__VAR:
        setVar((Math_VarType)newValue);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__UNIOP:
        setUniop((Math_UniopType)newValue);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__BINOP:
        setBinop((Math_BinopType)newValue);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL:
        setFunctionCall((Math_FunctionCallType)newValue);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__STRING:
        setString((Math_StringType)newValue);
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
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__CONSTANT:
        setConstant((Math_ConstantType)null);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__VAR:
        setVar((Math_VarType)null);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__UNIOP:
        setUniop((Math_UniopType)null);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__BINOP:
        setBinop((Math_BinopType)null);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL:
        setFunctionCall((Math_FunctionCallType)null);
        return;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__STRING:
        setString((Math_StringType)null);
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
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__CONSTANT:
        return constant != null;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__VAR:
        return var != null;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__UNIOP:
        return uniop != null;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__BINOP:
        return binop != null;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__FUNCTION_CALL:
        return functionCall != null;
      case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE__STRING:
        return string != null;
    }
    return super.eIsSet(featureID);
  }

} //Math_LogicExprTypeImpl

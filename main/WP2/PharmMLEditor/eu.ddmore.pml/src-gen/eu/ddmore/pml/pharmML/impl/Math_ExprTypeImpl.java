/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_BinopType;
import eu.ddmore.pml.pharmML.Math_ConstantType;
import eu.ddmore.pml.pharmML.Math_ExprType;
import eu.ddmore.pml.pharmML.Math_FunctionCallType;
import eu.ddmore.pml.pharmML.Math_ScalarType;
import eu.ddmore.pml.pharmML.Math_UniopType;
import eu.ddmore.pml.pharmML.Math_VarType;
import eu.ddmore.pml.pharmML.PharmMLPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Math Expr Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ExprTypeImpl#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ExprTypeImpl#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ExprTypeImpl#getUniop <em>Uniop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ExprTypeImpl#getBinop <em>Binop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ExprTypeImpl#getConstant <em>Constant</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ExprTypeImpl#getFunctionCall <em>Function Call</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_ExprTypeImpl extends MinimalEObjectImpl.Container implements Math_ExprType
{
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
   * The cached value of the '{@link #getConstant() <em>Constant</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstant()
   * @generated
   * @ordered
   */
  protected Math_ConstantType constant;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_ExprTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_ExprType();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__SCALAR, oldScalar, newScalar);
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
        msgs = ((InternalEObject)scalar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__SCALAR, null, msgs);
      if (newScalar != null)
        msgs = ((InternalEObject)newScalar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__SCALAR, null, msgs);
      msgs = basicSetScalar(newScalar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__SCALAR, newScalar, newScalar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__VAR, oldVar, newVar);
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
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__VAR, newVar, newVar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__UNIOP, oldUniop, newUniop);
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
        msgs = ((InternalEObject)uniop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__UNIOP, null, msgs);
      if (newUniop != null)
        msgs = ((InternalEObject)newUniop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__UNIOP, null, msgs);
      msgs = basicSetUniop(newUniop, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__UNIOP, newUniop, newUniop));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__BINOP, oldBinop, newBinop);
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
        msgs = ((InternalEObject)binop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__BINOP, null, msgs);
      if (newBinop != null)
        msgs = ((InternalEObject)newBinop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__BINOP, null, msgs);
      msgs = basicSetBinop(newBinop, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__BINOP, newBinop, newBinop));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__CONSTANT, oldConstant, newConstant);
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
        msgs = ((InternalEObject)constant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__CONSTANT, null, msgs);
      if (newConstant != null)
        msgs = ((InternalEObject)newConstant).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__CONSTANT, null, msgs);
      msgs = basicSetConstant(newConstant, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__CONSTANT, newConstant, newConstant));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__FUNCTION_CALL, oldFunctionCall, newFunctionCall);
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
        msgs = ((InternalEObject)functionCall).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__FUNCTION_CALL, null, msgs);
      if (newFunctionCall != null)
        msgs = ((InternalEObject)newFunctionCall).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EXPR_TYPE__FUNCTION_CALL, null, msgs);
      msgs = basicSetFunctionCall(newFunctionCall, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EXPR_TYPE__FUNCTION_CALL, newFunctionCall, newFunctionCall));
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
      case PharmMLPackage.MATH_EXPR_TYPE__SCALAR:
        return basicSetScalar(null, msgs);
      case PharmMLPackage.MATH_EXPR_TYPE__VAR:
        return basicSetVar(null, msgs);
      case PharmMLPackage.MATH_EXPR_TYPE__UNIOP:
        return basicSetUniop(null, msgs);
      case PharmMLPackage.MATH_EXPR_TYPE__BINOP:
        return basicSetBinop(null, msgs);
      case PharmMLPackage.MATH_EXPR_TYPE__CONSTANT:
        return basicSetConstant(null, msgs);
      case PharmMLPackage.MATH_EXPR_TYPE__FUNCTION_CALL:
        return basicSetFunctionCall(null, msgs);
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
      case PharmMLPackage.MATH_EXPR_TYPE__SCALAR:
        return getScalar();
      case PharmMLPackage.MATH_EXPR_TYPE__VAR:
        return getVar();
      case PharmMLPackage.MATH_EXPR_TYPE__UNIOP:
        return getUniop();
      case PharmMLPackage.MATH_EXPR_TYPE__BINOP:
        return getBinop();
      case PharmMLPackage.MATH_EXPR_TYPE__CONSTANT:
        return getConstant();
      case PharmMLPackage.MATH_EXPR_TYPE__FUNCTION_CALL:
        return getFunctionCall();
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
      case PharmMLPackage.MATH_EXPR_TYPE__SCALAR:
        setScalar((Math_ScalarType)newValue);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__VAR:
        setVar((Math_VarType)newValue);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__UNIOP:
        setUniop((Math_UniopType)newValue);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__BINOP:
        setBinop((Math_BinopType)newValue);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__CONSTANT:
        setConstant((Math_ConstantType)newValue);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__FUNCTION_CALL:
        setFunctionCall((Math_FunctionCallType)newValue);
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
      case PharmMLPackage.MATH_EXPR_TYPE__SCALAR:
        setScalar((Math_ScalarType)null);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__VAR:
        setVar((Math_VarType)null);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__UNIOP:
        setUniop((Math_UniopType)null);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__BINOP:
        setBinop((Math_BinopType)null);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__CONSTANT:
        setConstant((Math_ConstantType)null);
        return;
      case PharmMLPackage.MATH_EXPR_TYPE__FUNCTION_CALL:
        setFunctionCall((Math_FunctionCallType)null);
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
      case PharmMLPackage.MATH_EXPR_TYPE__SCALAR:
        return scalar != null;
      case PharmMLPackage.MATH_EXPR_TYPE__VAR:
        return var != null;
      case PharmMLPackage.MATH_EXPR_TYPE__UNIOP:
        return uniop != null;
      case PharmMLPackage.MATH_EXPR_TYPE__BINOP:
        return binop != null;
      case PharmMLPackage.MATH_EXPR_TYPE__CONSTANT:
        return constant != null;
      case PharmMLPackage.MATH_EXPR_TYPE__FUNCTION_CALL:
        return functionCall != null;
    }
    return super.eIsSet(featureID);
  }

} //Math_ExprTypeImpl

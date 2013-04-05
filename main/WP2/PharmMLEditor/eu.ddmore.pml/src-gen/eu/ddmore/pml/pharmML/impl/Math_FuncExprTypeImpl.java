/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_ConstantType;
import eu.ddmore.pml.pharmML.Math_EquationType;
import eu.ddmore.pml.pharmML.Math_FuncExprType;
import eu.ddmore.pml.pharmML.Math_ScalarType;
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
 * An implementation of the model object '<em><b>Math Func Expr Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_FuncExprTypeImpl#getEquation <em>Equation</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_FuncExprTypeImpl#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_FuncExprTypeImpl#getConstant <em>Constant</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_FuncExprTypeImpl#getVar <em>Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_FuncExprTypeImpl extends MinimalEObjectImpl.Container implements Math_FuncExprType
{
  /**
   * The cached value of the '{@link #getEquation() <em>Equation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEquation()
   * @generated
   * @ordered
   */
  protected Math_EquationType equation;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_FuncExprTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_FuncExprType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_EquationType getEquation()
  {
    return equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEquation(Math_EquationType newEquation, NotificationChain msgs)
  {
    Math_EquationType oldEquation = equation;
    equation = newEquation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNC_EXPR_TYPE__EQUATION, oldEquation, newEquation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEquation(Math_EquationType newEquation)
  {
    if (newEquation != equation)
    {
      NotificationChain msgs = null;
      if (equation != null)
        msgs = ((InternalEObject)equation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNC_EXPR_TYPE__EQUATION, null, msgs);
      if (newEquation != null)
        msgs = ((InternalEObject)newEquation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNC_EXPR_TYPE__EQUATION, null, msgs);
      msgs = basicSetEquation(newEquation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNC_EXPR_TYPE__EQUATION, newEquation, newEquation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNC_EXPR_TYPE__SCALAR, oldScalar, newScalar);
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
        msgs = ((InternalEObject)scalar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNC_EXPR_TYPE__SCALAR, null, msgs);
      if (newScalar != null)
        msgs = ((InternalEObject)newScalar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNC_EXPR_TYPE__SCALAR, null, msgs);
      msgs = basicSetScalar(newScalar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNC_EXPR_TYPE__SCALAR, newScalar, newScalar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNC_EXPR_TYPE__CONSTANT, oldConstant, newConstant);
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
        msgs = ((InternalEObject)constant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNC_EXPR_TYPE__CONSTANT, null, msgs);
      if (newConstant != null)
        msgs = ((InternalEObject)newConstant).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNC_EXPR_TYPE__CONSTANT, null, msgs);
      msgs = basicSetConstant(newConstant, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNC_EXPR_TYPE__CONSTANT, newConstant, newConstant));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNC_EXPR_TYPE__VAR, oldVar, newVar);
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
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNC_EXPR_TYPE__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_FUNC_EXPR_TYPE__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_FUNC_EXPR_TYPE__VAR, newVar, newVar));
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
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__EQUATION:
        return basicSetEquation(null, msgs);
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__SCALAR:
        return basicSetScalar(null, msgs);
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__CONSTANT:
        return basicSetConstant(null, msgs);
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__VAR:
        return basicSetVar(null, msgs);
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
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__EQUATION:
        return getEquation();
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__SCALAR:
        return getScalar();
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__CONSTANT:
        return getConstant();
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__VAR:
        return getVar();
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
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__EQUATION:
        setEquation((Math_EquationType)newValue);
        return;
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__SCALAR:
        setScalar((Math_ScalarType)newValue);
        return;
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__CONSTANT:
        setConstant((Math_ConstantType)newValue);
        return;
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__VAR:
        setVar((Math_VarType)newValue);
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
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__EQUATION:
        setEquation((Math_EquationType)null);
        return;
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__SCALAR:
        setScalar((Math_ScalarType)null);
        return;
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__CONSTANT:
        setConstant((Math_ConstantType)null);
        return;
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__VAR:
        setVar((Math_VarType)null);
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
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__EQUATION:
        return equation != null;
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__SCALAR:
        return scalar != null;
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__CONSTANT:
        return constant != null;
      case PharmMLPackage.MATH_FUNC_EXPR_TYPE__VAR:
        return var != null;
    }
    return super.eIsSet(featureID);
  }

} //Math_FuncExprTypeImpl

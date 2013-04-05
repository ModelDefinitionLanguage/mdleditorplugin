/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_BinopType;
import eu.ddmore.pml.pharmML.Math_EquationTypeContent;
import eu.ddmore.pml.pharmML.Math_PiecewiseType;
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
 * An implementation of the model object '<em><b>Math Equation Type Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeContentImpl#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeContentImpl#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeContentImpl#getBinop <em>Binop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeContentImpl#getUniop <em>Uniop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeContentImpl#getPiecewise <em>Piecewise</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_EquationTypeContentImpl extends MinimalEObjectImpl.Container implements Math_EquationTypeContent
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
   * The cached value of the '{@link #getBinop() <em>Binop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBinop()
   * @generated
   * @ordered
   */
  protected Math_BinopType binop;

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
   * The cached value of the '{@link #getPiecewise() <em>Piecewise</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPiecewise()
   * @generated
   * @ordered
   */
  protected Math_PiecewiseType piecewise;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_EquationTypeContentImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_EquationTypeContent();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__SCALAR, oldScalar, newScalar);
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
        msgs = ((InternalEObject)scalar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__SCALAR, null, msgs);
      if (newScalar != null)
        msgs = ((InternalEObject)newScalar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__SCALAR, null, msgs);
      msgs = basicSetScalar(newScalar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__SCALAR, newScalar, newScalar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__VAR, oldVar, newVar);
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
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__VAR, newVar, newVar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__BINOP, oldBinop, newBinop);
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
        msgs = ((InternalEObject)binop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__BINOP, null, msgs);
      if (newBinop != null)
        msgs = ((InternalEObject)newBinop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__BINOP, null, msgs);
      msgs = basicSetBinop(newBinop, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__BINOP, newBinop, newBinop));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__UNIOP, oldUniop, newUniop);
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
        msgs = ((InternalEObject)uniop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__UNIOP, null, msgs);
      if (newUniop != null)
        msgs = ((InternalEObject)newUniop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__UNIOP, null, msgs);
      msgs = basicSetUniop(newUniop, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__UNIOP, newUniop, newUniop));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_PiecewiseType getPiecewise()
  {
    return piecewise;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPiecewise(Math_PiecewiseType newPiecewise, NotificationChain msgs)
  {
    Math_PiecewiseType oldPiecewise = piecewise;
    piecewise = newPiecewise;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__PIECEWISE, oldPiecewise, newPiecewise);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPiecewise(Math_PiecewiseType newPiecewise)
  {
    if (newPiecewise != piecewise)
    {
      NotificationChain msgs = null;
      if (piecewise != null)
        msgs = ((InternalEObject)piecewise).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__PIECEWISE, null, msgs);
      if (newPiecewise != null)
        msgs = ((InternalEObject)newPiecewise).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__PIECEWISE, null, msgs);
      msgs = basicSetPiecewise(newPiecewise, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__PIECEWISE, newPiecewise, newPiecewise));
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
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__SCALAR:
        return basicSetScalar(null, msgs);
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__VAR:
        return basicSetVar(null, msgs);
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__BINOP:
        return basicSetBinop(null, msgs);
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__UNIOP:
        return basicSetUniop(null, msgs);
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__PIECEWISE:
        return basicSetPiecewise(null, msgs);
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
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__SCALAR:
        return getScalar();
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__VAR:
        return getVar();
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__BINOP:
        return getBinop();
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__UNIOP:
        return getUniop();
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__PIECEWISE:
        return getPiecewise();
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
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__SCALAR:
        setScalar((Math_ScalarType)newValue);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__VAR:
        setVar((Math_VarType)newValue);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__BINOP:
        setBinop((Math_BinopType)newValue);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__UNIOP:
        setUniop((Math_UniopType)newValue);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__PIECEWISE:
        setPiecewise((Math_PiecewiseType)newValue);
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
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__SCALAR:
        setScalar((Math_ScalarType)null);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__VAR:
        setVar((Math_VarType)null);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__BINOP:
        setBinop((Math_BinopType)null);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__UNIOP:
        setUniop((Math_UniopType)null);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__PIECEWISE:
        setPiecewise((Math_PiecewiseType)null);
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
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__SCALAR:
        return scalar != null;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__VAR:
        return var != null;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__BINOP:
        return binop != null;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__UNIOP:
        return uniop != null;
      case PharmMLPackage.MATH_EQUATION_TYPE_CONTENT__PIECEWISE:
        return piecewise != null;
    }
    return super.eIsSet(featureID);
  }

} //Math_EquationTypeContentImpl

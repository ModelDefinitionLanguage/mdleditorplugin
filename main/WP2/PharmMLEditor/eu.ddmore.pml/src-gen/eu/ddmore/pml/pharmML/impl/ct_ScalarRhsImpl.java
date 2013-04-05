/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_EquationType;
import eu.ddmore.pml.pharmML.Math_FunctionCallType;
import eu.ddmore.pml.pharmML.Math_ScalarType;
import eu.ddmore.pml.pharmML.Math_StringType;
import eu.ddmore.pml.pharmML.Math_VarType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.ct_ScalarRhs;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ct Scalar Rhs</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ScalarRhsImpl#getEquation <em>Equation</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ScalarRhsImpl#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ScalarRhsImpl#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ScalarRhsImpl#getString <em>String</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ScalarRhsImpl#getFunctionCall <em>Function Call</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_ScalarRhsImpl extends MinimalEObjectImpl.Container implements ct_ScalarRhs
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
   * The cached value of the '{@link #getVar() <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVar()
   * @generated
   * @ordered
   */
  protected Math_VarType var;

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
  protected ct_ScalarRhsImpl()
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
    return PharmMLPackage.eINSTANCE.getct_ScalarRhs();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__EQUATION, oldEquation, newEquation);
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
        msgs = ((InternalEObject)equation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__EQUATION, null, msgs);
      if (newEquation != null)
        msgs = ((InternalEObject)newEquation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__EQUATION, null, msgs);
      msgs = basicSetEquation(newEquation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__EQUATION, newEquation, newEquation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__SCALAR, oldScalar, newScalar);
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
        msgs = ((InternalEObject)scalar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__SCALAR, null, msgs);
      if (newScalar != null)
        msgs = ((InternalEObject)newScalar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__SCALAR, null, msgs);
      msgs = basicSetScalar(newScalar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__SCALAR, newScalar, newScalar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__VAR, oldVar, newVar);
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
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__VAR, newVar, newVar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__STRING, oldString, newString);
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
        msgs = ((InternalEObject)string).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__STRING, null, msgs);
      if (newString != null)
        msgs = ((InternalEObject)newString).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__STRING, null, msgs);
      msgs = basicSetString(newString, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__STRING, newString, newString));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__FUNCTION_CALL, oldFunctionCall, newFunctionCall);
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
        msgs = ((InternalEObject)functionCall).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__FUNCTION_CALL, null, msgs);
      if (newFunctionCall != null)
        msgs = ((InternalEObject)newFunctionCall).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_SCALAR_RHS__FUNCTION_CALL, null, msgs);
      msgs = basicSetFunctionCall(newFunctionCall, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SCALAR_RHS__FUNCTION_CALL, newFunctionCall, newFunctionCall));
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
      case PharmMLPackage.CT_SCALAR_RHS__EQUATION:
        return basicSetEquation(null, msgs);
      case PharmMLPackage.CT_SCALAR_RHS__SCALAR:
        return basicSetScalar(null, msgs);
      case PharmMLPackage.CT_SCALAR_RHS__VAR:
        return basicSetVar(null, msgs);
      case PharmMLPackage.CT_SCALAR_RHS__STRING:
        return basicSetString(null, msgs);
      case PharmMLPackage.CT_SCALAR_RHS__FUNCTION_CALL:
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
      case PharmMLPackage.CT_SCALAR_RHS__EQUATION:
        return getEquation();
      case PharmMLPackage.CT_SCALAR_RHS__SCALAR:
        return getScalar();
      case PharmMLPackage.CT_SCALAR_RHS__VAR:
        return getVar();
      case PharmMLPackage.CT_SCALAR_RHS__STRING:
        return getString();
      case PharmMLPackage.CT_SCALAR_RHS__FUNCTION_CALL:
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
      case PharmMLPackage.CT_SCALAR_RHS__EQUATION:
        setEquation((Math_EquationType)newValue);
        return;
      case PharmMLPackage.CT_SCALAR_RHS__SCALAR:
        setScalar((Math_ScalarType)newValue);
        return;
      case PharmMLPackage.CT_SCALAR_RHS__VAR:
        setVar((Math_VarType)newValue);
        return;
      case PharmMLPackage.CT_SCALAR_RHS__STRING:
        setString((Math_StringType)newValue);
        return;
      case PharmMLPackage.CT_SCALAR_RHS__FUNCTION_CALL:
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
      case PharmMLPackage.CT_SCALAR_RHS__EQUATION:
        setEquation((Math_EquationType)null);
        return;
      case PharmMLPackage.CT_SCALAR_RHS__SCALAR:
        setScalar((Math_ScalarType)null);
        return;
      case PharmMLPackage.CT_SCALAR_RHS__VAR:
        setVar((Math_VarType)null);
        return;
      case PharmMLPackage.CT_SCALAR_RHS__STRING:
        setString((Math_StringType)null);
        return;
      case PharmMLPackage.CT_SCALAR_RHS__FUNCTION_CALL:
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
      case PharmMLPackage.CT_SCALAR_RHS__EQUATION:
        return equation != null;
      case PharmMLPackage.CT_SCALAR_RHS__SCALAR:
        return scalar != null;
      case PharmMLPackage.CT_SCALAR_RHS__VAR:
        return var != null;
      case PharmMLPackage.CT_SCALAR_RHS__STRING:
        return string != null;
      case PharmMLPackage.CT_SCALAR_RHS__FUNCTION_CALL:
        return functionCall != null;
    }
    return super.eIsSet(featureID);
  }

} //ct_ScalarRhsImpl

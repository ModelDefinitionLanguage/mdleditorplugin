/**
 */
package org.ddmore.pml.pharmaML.impl;

import org.ddmore.pml.pharmaML.Math_ConstantType;
import org.ddmore.pml.pharmaML.Math_EquationType;
import org.ddmore.pml.pharmaML.Math_FunctionCallType;
import org.ddmore.pml.pharmaML.Math_ScalarType;
import org.ddmore.pml.pharmaML.Math_StringType;
import org.ddmore.pml.pharmaML.Math_VarType;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_DataSetType;
import org.ddmore.pml.pharmaML.ct_Rhs;
import org.ddmore.pml.pharmaML.ct_SequenceType;
import org.ddmore.pml.pharmaML.ct_VectorType;
import org.ddmore.pml.pharmaML.uncert_DistributionType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ct Rhs</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getEquation <em>Equation</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getScalar <em>Scalar</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getConstant <em>Constant</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getString <em>String</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getSequence <em>Sequence</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getVector <em>Vector</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getFunctionCall <em>Function Call</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getDataSet <em>Data Set</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getVar <em>Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_RhsImpl#getDistribution <em>Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_RhsImpl extends MinimalEObjectImpl.Container implements ct_Rhs
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
   * The cached value of the '{@link #getString() <em>String</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected Math_StringType string;

  /**
   * The cached value of the '{@link #getSequence() <em>Sequence</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSequence()
   * @generated
   * @ordered
   */
  protected ct_SequenceType sequence;

  /**
   * The cached value of the '{@link #getVector() <em>Vector</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVector()
   * @generated
   * @ordered
   */
  protected ct_VectorType vector;

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
   * The cached value of the '{@link #getDataSet() <em>Data Set</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSet()
   * @generated
   * @ordered
   */
  protected ct_DataSetType dataSet;

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
   * The cached value of the '{@link #getDistribution() <em>Distribution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDistribution()
   * @generated
   * @ordered
   */
  protected uncert_DistributionType distribution;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_RhsImpl()
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
    return PharmaMLPackage.eINSTANCE.getct_Rhs();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__EQUATION, oldEquation, newEquation);
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
        msgs = ((InternalEObject)equation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__EQUATION, null, msgs);
      if (newEquation != null)
        msgs = ((InternalEObject)newEquation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__EQUATION, null, msgs);
      msgs = basicSetEquation(newEquation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__EQUATION, newEquation, newEquation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__SCALAR, oldScalar, newScalar);
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
        msgs = ((InternalEObject)scalar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__SCALAR, null, msgs);
      if (newScalar != null)
        msgs = ((InternalEObject)newScalar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__SCALAR, null, msgs);
      msgs = basicSetScalar(newScalar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__SCALAR, newScalar, newScalar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__CONSTANT, oldConstant, newConstant);
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
        msgs = ((InternalEObject)constant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__CONSTANT, null, msgs);
      if (newConstant != null)
        msgs = ((InternalEObject)newConstant).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__CONSTANT, null, msgs);
      msgs = basicSetConstant(newConstant, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__CONSTANT, newConstant, newConstant));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__STRING, oldString, newString);
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
        msgs = ((InternalEObject)string).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__STRING, null, msgs);
      if (newString != null)
        msgs = ((InternalEObject)newString).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__STRING, null, msgs);
      msgs = basicSetString(newString, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__STRING, newString, newString));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_SequenceType getSequence()
  {
    return sequence;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSequence(ct_SequenceType newSequence, NotificationChain msgs)
  {
    ct_SequenceType oldSequence = sequence;
    sequence = newSequence;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__SEQUENCE, oldSequence, newSequence);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSequence(ct_SequenceType newSequence)
  {
    if (newSequence != sequence)
    {
      NotificationChain msgs = null;
      if (sequence != null)
        msgs = ((InternalEObject)sequence).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__SEQUENCE, null, msgs);
      if (newSequence != null)
        msgs = ((InternalEObject)newSequence).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__SEQUENCE, null, msgs);
      msgs = basicSetSequence(newSequence, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__SEQUENCE, newSequence, newSequence));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_VectorType getVector()
  {
    return vector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVector(ct_VectorType newVector, NotificationChain msgs)
  {
    ct_VectorType oldVector = vector;
    vector = newVector;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__VECTOR, oldVector, newVector);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVector(ct_VectorType newVector)
  {
    if (newVector != vector)
    {
      NotificationChain msgs = null;
      if (vector != null)
        msgs = ((InternalEObject)vector).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__VECTOR, null, msgs);
      if (newVector != null)
        msgs = ((InternalEObject)newVector).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__VECTOR, null, msgs);
      msgs = basicSetVector(newVector, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__VECTOR, newVector, newVector));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__FUNCTION_CALL, oldFunctionCall, newFunctionCall);
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
        msgs = ((InternalEObject)functionCall).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__FUNCTION_CALL, null, msgs);
      if (newFunctionCall != null)
        msgs = ((InternalEObject)newFunctionCall).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__FUNCTION_CALL, null, msgs);
      msgs = basicSetFunctionCall(newFunctionCall, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__FUNCTION_CALL, newFunctionCall, newFunctionCall));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_DataSetType getDataSet()
  {
    return dataSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDataSet(ct_DataSetType newDataSet, NotificationChain msgs)
  {
    ct_DataSetType oldDataSet = dataSet;
    dataSet = newDataSet;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__DATA_SET, oldDataSet, newDataSet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataSet(ct_DataSetType newDataSet)
  {
    if (newDataSet != dataSet)
    {
      NotificationChain msgs = null;
      if (dataSet != null)
        msgs = ((InternalEObject)dataSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__DATA_SET, null, msgs);
      if (newDataSet != null)
        msgs = ((InternalEObject)newDataSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__DATA_SET, null, msgs);
      msgs = basicSetDataSet(newDataSet, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__DATA_SET, newDataSet, newDataSet));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__VAR, oldVar, newVar);
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
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__VAR, newVar, newVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_DistributionType getDistribution()
  {
    return distribution;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDistribution(uncert_DistributionType newDistribution, NotificationChain msgs)
  {
    uncert_DistributionType oldDistribution = distribution;
    distribution = newDistribution;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__DISTRIBUTION, oldDistribution, newDistribution);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDistribution(uncert_DistributionType newDistribution)
  {
    if (newDistribution != distribution)
    {
      NotificationChain msgs = null;
      if (distribution != null)
        msgs = ((InternalEObject)distribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__DISTRIBUTION, null, msgs);
      if (newDistribution != null)
        msgs = ((InternalEObject)newDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_RHS__DISTRIBUTION, null, msgs);
      msgs = basicSetDistribution(newDistribution, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_RHS__DISTRIBUTION, newDistribution, newDistribution));
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
      case PharmaMLPackage.CT_RHS__EQUATION:
        return basicSetEquation(null, msgs);
      case PharmaMLPackage.CT_RHS__SCALAR:
        return basicSetScalar(null, msgs);
      case PharmaMLPackage.CT_RHS__CONSTANT:
        return basicSetConstant(null, msgs);
      case PharmaMLPackage.CT_RHS__STRING:
        return basicSetString(null, msgs);
      case PharmaMLPackage.CT_RHS__SEQUENCE:
        return basicSetSequence(null, msgs);
      case PharmaMLPackage.CT_RHS__VECTOR:
        return basicSetVector(null, msgs);
      case PharmaMLPackage.CT_RHS__FUNCTION_CALL:
        return basicSetFunctionCall(null, msgs);
      case PharmaMLPackage.CT_RHS__DATA_SET:
        return basicSetDataSet(null, msgs);
      case PharmaMLPackage.CT_RHS__VAR:
        return basicSetVar(null, msgs);
      case PharmaMLPackage.CT_RHS__DISTRIBUTION:
        return basicSetDistribution(null, msgs);
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
      case PharmaMLPackage.CT_RHS__EQUATION:
        return getEquation();
      case PharmaMLPackage.CT_RHS__SCALAR:
        return getScalar();
      case PharmaMLPackage.CT_RHS__CONSTANT:
        return getConstant();
      case PharmaMLPackage.CT_RHS__STRING:
        return getString();
      case PharmaMLPackage.CT_RHS__SEQUENCE:
        return getSequence();
      case PharmaMLPackage.CT_RHS__VECTOR:
        return getVector();
      case PharmaMLPackage.CT_RHS__FUNCTION_CALL:
        return getFunctionCall();
      case PharmaMLPackage.CT_RHS__DATA_SET:
        return getDataSet();
      case PharmaMLPackage.CT_RHS__VAR:
        return getVar();
      case PharmaMLPackage.CT_RHS__DISTRIBUTION:
        return getDistribution();
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
      case PharmaMLPackage.CT_RHS__EQUATION:
        setEquation((Math_EquationType)newValue);
        return;
      case PharmaMLPackage.CT_RHS__SCALAR:
        setScalar((Math_ScalarType)newValue);
        return;
      case PharmaMLPackage.CT_RHS__CONSTANT:
        setConstant((Math_ConstantType)newValue);
        return;
      case PharmaMLPackage.CT_RHS__STRING:
        setString((Math_StringType)newValue);
        return;
      case PharmaMLPackage.CT_RHS__SEQUENCE:
        setSequence((ct_SequenceType)newValue);
        return;
      case PharmaMLPackage.CT_RHS__VECTOR:
        setVector((ct_VectorType)newValue);
        return;
      case PharmaMLPackage.CT_RHS__FUNCTION_CALL:
        setFunctionCall((Math_FunctionCallType)newValue);
        return;
      case PharmaMLPackage.CT_RHS__DATA_SET:
        setDataSet((ct_DataSetType)newValue);
        return;
      case PharmaMLPackage.CT_RHS__VAR:
        setVar((Math_VarType)newValue);
        return;
      case PharmaMLPackage.CT_RHS__DISTRIBUTION:
        setDistribution((uncert_DistributionType)newValue);
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
      case PharmaMLPackage.CT_RHS__EQUATION:
        setEquation((Math_EquationType)null);
        return;
      case PharmaMLPackage.CT_RHS__SCALAR:
        setScalar((Math_ScalarType)null);
        return;
      case PharmaMLPackage.CT_RHS__CONSTANT:
        setConstant((Math_ConstantType)null);
        return;
      case PharmaMLPackage.CT_RHS__STRING:
        setString((Math_StringType)null);
        return;
      case PharmaMLPackage.CT_RHS__SEQUENCE:
        setSequence((ct_SequenceType)null);
        return;
      case PharmaMLPackage.CT_RHS__VECTOR:
        setVector((ct_VectorType)null);
        return;
      case PharmaMLPackage.CT_RHS__FUNCTION_CALL:
        setFunctionCall((Math_FunctionCallType)null);
        return;
      case PharmaMLPackage.CT_RHS__DATA_SET:
        setDataSet((ct_DataSetType)null);
        return;
      case PharmaMLPackage.CT_RHS__VAR:
        setVar((Math_VarType)null);
        return;
      case PharmaMLPackage.CT_RHS__DISTRIBUTION:
        setDistribution((uncert_DistributionType)null);
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
      case PharmaMLPackage.CT_RHS__EQUATION:
        return equation != null;
      case PharmaMLPackage.CT_RHS__SCALAR:
        return scalar != null;
      case PharmaMLPackage.CT_RHS__CONSTANT:
        return constant != null;
      case PharmaMLPackage.CT_RHS__STRING:
        return string != null;
      case PharmaMLPackage.CT_RHS__SEQUENCE:
        return sequence != null;
      case PharmaMLPackage.CT_RHS__VECTOR:
        return vector != null;
      case PharmaMLPackage.CT_RHS__FUNCTION_CALL:
        return functionCall != null;
      case PharmaMLPackage.CT_RHS__DATA_SET:
        return dataSet != null;
      case PharmaMLPackage.CT_RHS__VAR:
        return var != null;
      case PharmaMLPackage.CT_RHS__DISTRIBUTION:
        return distribution != null;
    }
    return super.eIsSet(featureID);
  }

} //ct_RhsImpl

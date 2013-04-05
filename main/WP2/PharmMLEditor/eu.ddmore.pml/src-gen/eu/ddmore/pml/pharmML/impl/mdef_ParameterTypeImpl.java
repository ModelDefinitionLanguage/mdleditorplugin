/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_EquationType;
import eu.ddmore.pml.pharmML.Math_ScalarType;
import eu.ddmore.pml.pharmML.Math_VarType;
import eu.ddmore.pml.pharmML.Math_symbId;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_name;
import eu.ddmore.pml.pharmML.mdef_CovariateType;
import eu.ddmore.pml.pharmML.mdef_ParameterRandomEffectType;
import eu.ddmore.pml.pharmML.mdef_ParameterType;
import eu.ddmore.pml.pharmML.mdef_transformation;

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
 * An implementation of the model object '<em><b>mdef Parameter Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl#getEquation <em>Equation</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl#getRandomEffect <em>Random Effect</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ParameterTypeImpl#getCovariate <em>Covariate</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_ParameterTypeImpl extends MinimalEObjectImpl.Container implements mdef_ParameterType
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
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected ct_name name;

  /**
   * The cached value of the '{@link #getSymbId() <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbId()
   * @generated
   * @ordered
   */
  protected Math_symbId symbId;

  /**
   * The cached value of the '{@link #getTransformation() <em>Transformation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTransformation()
   * @generated
   * @ordered
   */
  protected mdef_transformation transformation;

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
   * The cached value of the '{@link #getScalar() <em>Scalar</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScalar()
   * @generated
   * @ordered
   */
  protected Math_ScalarType scalar;

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
   * The cached value of the '{@link #getRandomEffect() <em>Random Effect</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRandomEffect()
   * @generated
   * @ordered
   */
  protected EList<mdef_ParameterRandomEffectType> randomEffect;

  /**
   * The cached value of the '{@link #getCovariate() <em>Covariate</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCovariate()
   * @generated
   * @ordered
   */
  protected EList<mdef_CovariateType> covariate;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_ParameterTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmdef_ParameterType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MDEF_PARAMETER_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_name getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(ct_name newName, NotificationChain msgs)
  {
    ct_name oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(ct_name newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_symbId getSymbId()
  {
    return symbId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbId(Math_symbId newSymbId, NotificationChain msgs)
  {
    Math_symbId oldSymbId = symbId;
    symbId = newSymbId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__SYMB_ID, oldSymbId, newSymbId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbId(Math_symbId newSymbId)
  {
    if (newSymbId != symbId)
    {
      NotificationChain msgs = null;
      if (symbId != null)
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__SYMB_ID, newSymbId, newSymbId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_transformation getTransformation()
  {
    return transformation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTransformation(mdef_transformation newTransformation, NotificationChain msgs)
  {
    mdef_transformation oldTransformation = transformation;
    transformation = newTransformation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__TRANSFORMATION, oldTransformation, newTransformation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTransformation(mdef_transformation newTransformation)
  {
    if (newTransformation != transformation)
    {
      NotificationChain msgs = null;
      if (transformation != null)
        msgs = ((InternalEObject)transformation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__TRANSFORMATION, null, msgs);
      if (newTransformation != null)
        msgs = ((InternalEObject)newTransformation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__TRANSFORMATION, null, msgs);
      msgs = basicSetTransformation(newTransformation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__TRANSFORMATION, newTransformation, newTransformation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__VAR, oldVar, newVar);
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
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__VAR, newVar, newVar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__SCALAR, oldScalar, newScalar);
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
        msgs = ((InternalEObject)scalar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__SCALAR, null, msgs);
      if (newScalar != null)
        msgs = ((InternalEObject)newScalar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__SCALAR, null, msgs);
      msgs = basicSetScalar(newScalar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__SCALAR, newScalar, newScalar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__EQUATION, oldEquation, newEquation);
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
        msgs = ((InternalEObject)equation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__EQUATION, null, msgs);
      if (newEquation != null)
        msgs = ((InternalEObject)newEquation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_PARAMETER_TYPE__EQUATION, null, msgs);
      msgs = basicSetEquation(newEquation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_PARAMETER_TYPE__EQUATION, newEquation, newEquation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_ParameterRandomEffectType> getRandomEffect()
  {
    if (randomEffect == null)
    {
      randomEffect = new EObjectContainmentEList<mdef_ParameterRandomEffectType>(mdef_ParameterRandomEffectType.class, this, PharmMLPackage.MDEF_PARAMETER_TYPE__RANDOM_EFFECT);
    }
    return randomEffect;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_CovariateType> getCovariate()
  {
    if (covariate == null)
    {
      covariate = new EObjectContainmentEList<mdef_CovariateType>(mdef_CovariateType.class, this, PharmMLPackage.MDEF_PARAMETER_TYPE__COVARIATE);
    }
    return covariate;
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
      case PharmMLPackage.MDEF_PARAMETER_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MDEF_PARAMETER_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmMLPackage.MDEF_PARAMETER_TYPE__TRANSFORMATION:
        return basicSetTransformation(null, msgs);
      case PharmMLPackage.MDEF_PARAMETER_TYPE__VAR:
        return basicSetVar(null, msgs);
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SCALAR:
        return basicSetScalar(null, msgs);
      case PharmMLPackage.MDEF_PARAMETER_TYPE__EQUATION:
        return basicSetEquation(null, msgs);
      case PharmMLPackage.MDEF_PARAMETER_TYPE__RANDOM_EFFECT:
        return ((InternalEList<?>)getRandomEffect()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MDEF_PARAMETER_TYPE__COVARIATE:
        return ((InternalEList<?>)getCovariate()).basicRemove(otherEnd, msgs);
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
      case PharmMLPackage.MDEF_PARAMETER_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__NAME:
        return getName();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SYMB_ID:
        return getSymbId();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__TRANSFORMATION:
        return getTransformation();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__VAR:
        return getVar();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SCALAR:
        return getScalar();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__EQUATION:
        return getEquation();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__RANDOM_EFFECT:
        return getRandomEffect();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__COVARIATE:
        return getCovariate();
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
      case PharmMLPackage.MDEF_PARAMETER_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__NAME:
        setName((ct_name)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SYMB_ID:
        setSymbId((Math_symbId)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__TRANSFORMATION:
        setTransformation((mdef_transformation)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__VAR:
        setVar((Math_VarType)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SCALAR:
        setScalar((Math_ScalarType)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__EQUATION:
        setEquation((Math_EquationType)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__RANDOM_EFFECT:
        getRandomEffect().clear();
        getRandomEffect().addAll((Collection<? extends mdef_ParameterRandomEffectType>)newValue);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__COVARIATE:
        getCovariate().clear();
        getCovariate().addAll((Collection<? extends mdef_CovariateType>)newValue);
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
      case PharmMLPackage.MDEF_PARAMETER_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__NAME:
        setName((ct_name)null);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SYMB_ID:
        setSymbId((Math_symbId)null);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__TRANSFORMATION:
        setTransformation((mdef_transformation)null);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__VAR:
        setVar((Math_VarType)null);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SCALAR:
        setScalar((Math_ScalarType)null);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__EQUATION:
        setEquation((Math_EquationType)null);
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__RANDOM_EFFECT:
        getRandomEffect().clear();
        return;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__COVARIATE:
        getCovariate().clear();
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
      case PharmMLPackage.MDEF_PARAMETER_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__NAME:
        return name != null;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SYMB_ID:
        return symbId != null;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__TRANSFORMATION:
        return transformation != null;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__VAR:
        return var != null;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__SCALAR:
        return scalar != null;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__EQUATION:
        return equation != null;
      case PharmMLPackage.MDEF_PARAMETER_TYPE__RANDOM_EFFECT:
        return randomEffect != null && !randomEffect.isEmpty();
      case PharmMLPackage.MDEF_PARAMETER_TYPE__COVARIATE:
        return covariate != null && !covariate.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //mdef_ParameterTypeImpl

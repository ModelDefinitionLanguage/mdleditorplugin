/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_name;
import eu.ddmore.pml.pharmML.ct_symbId;
import eu.ddmore.pml.pharmML.mdef_CategoricalType;
import eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType;
import eu.ddmore.pml.pharmML.mdef_CovariateVariabilityType;
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
 * An implementation of the model object '<em><b>mdef Covariate Variability Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CovariateVariabilityTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CovariateVariabilityTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CovariateVariabilityTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CovariateVariabilityTypeImpl#getTransformation <em>Transformation</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CovariateVariabilityTypeImpl#getContinuous <em>Continuous</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CovariateVariabilityTypeImpl#getCategorical <em>Categorical</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_CovariateVariabilityTypeImpl extends MinimalEObjectImpl.Container implements mdef_CovariateVariabilityType
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
  protected ct_symbId symbId;

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
   * The cached value of the '{@link #getContinuous() <em>Continuous</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContinuous()
   * @generated
   * @ordered
   */
  protected mdef_ContinuousCovariateType continuous;

  /**
   * The cached value of the '{@link #getCategorical() <em>Categorical</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategorical()
   * @generated
   * @ordered
   */
  protected mdef_CategoricalType categorical;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_CovariateVariabilityTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmdef_CovariateVariabilityType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__XMLNS);
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__NAME, oldName, newName);
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
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_symbId getSymbId()
  {
    return symbId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbId(ct_symbId newSymbId, NotificationChain msgs)
  {
    ct_symbId oldSymbId = symbId;
    symbId = newSymbId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID, oldSymbId, newSymbId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbId(ct_symbId newSymbId)
  {
    if (newSymbId != symbId)
    {
      NotificationChain msgs = null;
      if (symbId != null)
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID, newSymbId, newSymbId));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION, oldTransformation, newTransformation);
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
        msgs = ((InternalEObject)transformation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION, null, msgs);
      if (newTransformation != null)
        msgs = ((InternalEObject)newTransformation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION, null, msgs);
      msgs = basicSetTransformation(newTransformation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION, newTransformation, newTransformation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ContinuousCovariateType getContinuous()
  {
    return continuous;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContinuous(mdef_ContinuousCovariateType newContinuous, NotificationChain msgs)
  {
    mdef_ContinuousCovariateType oldContinuous = continuous;
    continuous = newContinuous;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS, oldContinuous, newContinuous);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContinuous(mdef_ContinuousCovariateType newContinuous)
  {
    if (newContinuous != continuous)
    {
      NotificationChain msgs = null;
      if (continuous != null)
        msgs = ((InternalEObject)continuous).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS, null, msgs);
      if (newContinuous != null)
        msgs = ((InternalEObject)newContinuous).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS, null, msgs);
      msgs = basicSetContinuous(newContinuous, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS, newContinuous, newContinuous));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_CategoricalType getCategorical()
  {
    return categorical;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCategorical(mdef_CategoricalType newCategorical, NotificationChain msgs)
  {
    mdef_CategoricalType oldCategorical = categorical;
    categorical = newCategorical;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL, oldCategorical, newCategorical);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCategorical(mdef_CategoricalType newCategorical)
  {
    if (newCategorical != categorical)
    {
      NotificationChain msgs = null;
      if (categorical != null)
        msgs = ((InternalEObject)categorical).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL, null, msgs);
      if (newCategorical != null)
        msgs = ((InternalEObject)newCategorical).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL, null, msgs);
      msgs = basicSetCategorical(newCategorical, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL, newCategorical, newCategorical));
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
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION:
        return basicSetTransformation(null, msgs);
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS:
        return basicSetContinuous(null, msgs);
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL:
        return basicSetCategorical(null, msgs);
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
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__NAME:
        return getName();
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID:
        return getSymbId();
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION:
        return getTransformation();
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS:
        return getContinuous();
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL:
        return getCategorical();
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
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__NAME:
        setName((ct_name)newValue);
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID:
        setSymbId((ct_symbId)newValue);
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION:
        setTransformation((mdef_transformation)newValue);
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS:
        setContinuous((mdef_ContinuousCovariateType)newValue);
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL:
        setCategorical((mdef_CategoricalType)newValue);
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
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__NAME:
        setName((ct_name)null);
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID:
        setSymbId((ct_symbId)null);
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION:
        setTransformation((mdef_transformation)null);
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS:
        setContinuous((mdef_ContinuousCovariateType)null);
        return;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL:
        setCategorical((mdef_CategoricalType)null);
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
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__NAME:
        return name != null;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__SYMB_ID:
        return symbId != null;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__TRANSFORMATION:
        return transformation != null;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CONTINUOUS:
        return continuous != null;
      case PharmMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE__CATEGORICAL:
        return categorical != null;
    }
    return super.eIsSet(featureID);
  }

} //mdef_CovariateVariabilityTypeImpl

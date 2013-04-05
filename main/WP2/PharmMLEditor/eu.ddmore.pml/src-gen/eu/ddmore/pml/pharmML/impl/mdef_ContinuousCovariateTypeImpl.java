/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.mdef_ContinuousCovariateType;
import eu.ddmore.pml.pharmML.mdef_TransformationType;
import eu.ddmore.pml.pharmML.uncert_DistributionType;

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
 * An implementation of the model object '<em><b>mdef Continuous Covariate Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousCovariateTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousCovariateTypeImpl#getDistribution <em>Distribution</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ContinuousCovariateTypeImpl#getTransformation <em>Transformation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_ContinuousCovariateTypeImpl extends MinimalEObjectImpl.Container implements mdef_ContinuousCovariateType
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
   * The cached value of the '{@link #getDistribution() <em>Distribution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDistribution()
   * @generated
   * @ordered
   */
  protected uncert_DistributionType distribution;

  /**
   * The cached value of the '{@link #getTransformation() <em>Transformation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTransformation()
   * @generated
   * @ordered
   */
  protected mdef_TransformationType transformation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_ContinuousCovariateTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmdef_ContinuousCovariateType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__XMLNS);
    }
    return xmlns;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION, oldDistribution, newDistribution);
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
        msgs = ((InternalEObject)distribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION, null, msgs);
      if (newDistribution != null)
        msgs = ((InternalEObject)newDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION, null, msgs);
      msgs = basicSetDistribution(newDistribution, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION, newDistribution, newDistribution));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_TransformationType getTransformation()
  {
    return transformation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTransformation(mdef_TransformationType newTransformation, NotificationChain msgs)
  {
    mdef_TransformationType oldTransformation = transformation;
    transformation = newTransformation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION, oldTransformation, newTransformation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTransformation(mdef_TransformationType newTransformation)
  {
    if (newTransformation != transformation)
    {
      NotificationChain msgs = null;
      if (transformation != null)
        msgs = ((InternalEObject)transformation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION, null, msgs);
      if (newTransformation != null)
        msgs = ((InternalEObject)newTransformation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION, null, msgs);
      msgs = basicSetTransformation(newTransformation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION, newTransformation, newTransformation));
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
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION:
        return basicSetDistribution(null, msgs);
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION:
        return basicSetTransformation(null, msgs);
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
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION:
        return getDistribution();
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION:
        return getTransformation();
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
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION:
        setDistribution((uncert_DistributionType)newValue);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION:
        setTransformation((mdef_TransformationType)newValue);
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
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION:
        setDistribution((uncert_DistributionType)null);
        return;
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION:
        setTransformation((mdef_TransformationType)null);
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
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__DISTRIBUTION:
        return distribution != null;
      case PharmMLPackage.MDEF_CONTINUOUS_COVARIATE_TYPE__TRANSFORMATION:
        return transformation != null;
    }
    return super.eIsSet(featureID);
  }

} //mdef_ContinuousCovariateTypeImpl

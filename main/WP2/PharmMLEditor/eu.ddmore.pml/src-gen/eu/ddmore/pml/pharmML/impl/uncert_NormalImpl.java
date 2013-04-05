/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.uncert_CoefficientOfVariance;
import eu.ddmore.pml.pharmML.uncert_Mean;
import eu.ddmore.pml.pharmML.uncert_Normal;
import eu.ddmore.pml.pharmML.uncert_StdDev;
import eu.ddmore.pml.pharmML.uncert_Variance;

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
 * An implementation of the model object '<em><b>uncert Normal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_NormalImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_NormalImpl#getMean <em>Mean</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_NormalImpl#getStdDev <em>Std Dev</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_NormalImpl#getVariance <em>Variance</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_NormalImpl#getCoefficientOfVariance <em>Coefficient Of Variance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class uncert_NormalImpl extends MinimalEObjectImpl.Container implements uncert_Normal
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
   * The cached value of the '{@link #getMean() <em>Mean</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMean()
   * @generated
   * @ordered
   */
  protected uncert_Mean mean;

  /**
   * The cached value of the '{@link #getStdDev() <em>Std Dev</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStdDev()
   * @generated
   * @ordered
   */
  protected uncert_StdDev stdDev;

  /**
   * The cached value of the '{@link #getVariance() <em>Variance</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariance()
   * @generated
   * @ordered
   */
  protected uncert_Variance variance;

  /**
   * The cached value of the '{@link #getCoefficientOfVariance() <em>Coefficient Of Variance</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCoefficientOfVariance()
   * @generated
   * @ordered
   */
  protected uncert_CoefficientOfVariance coefficientOfVariance;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected uncert_NormalImpl()
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
    return PharmMLPackage.eINSTANCE.getuncert_Normal();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.UNCERT_NORMAL__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Mean getMean()
  {
    return mean;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMean(uncert_Mean newMean, NotificationChain msgs)
  {
    uncert_Mean oldMean = mean;
    mean = newMean;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_NORMAL__MEAN, oldMean, newMean);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMean(uncert_Mean newMean)
  {
    if (newMean != mean)
    {
      NotificationChain msgs = null;
      if (mean != null)
        msgs = ((InternalEObject)mean).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_NORMAL__MEAN, null, msgs);
      if (newMean != null)
        msgs = ((InternalEObject)newMean).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_NORMAL__MEAN, null, msgs);
      msgs = basicSetMean(newMean, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_NORMAL__MEAN, newMean, newMean));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_StdDev getStdDev()
  {
    return stdDev;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStdDev(uncert_StdDev newStdDev, NotificationChain msgs)
  {
    uncert_StdDev oldStdDev = stdDev;
    stdDev = newStdDev;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_NORMAL__STD_DEV, oldStdDev, newStdDev);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStdDev(uncert_StdDev newStdDev)
  {
    if (newStdDev != stdDev)
    {
      NotificationChain msgs = null;
      if (stdDev != null)
        msgs = ((InternalEObject)stdDev).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_NORMAL__STD_DEV, null, msgs);
      if (newStdDev != null)
        msgs = ((InternalEObject)newStdDev).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_NORMAL__STD_DEV, null, msgs);
      msgs = basicSetStdDev(newStdDev, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_NORMAL__STD_DEV, newStdDev, newStdDev));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Variance getVariance()
  {
    return variance;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariance(uncert_Variance newVariance, NotificationChain msgs)
  {
    uncert_Variance oldVariance = variance;
    variance = newVariance;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_NORMAL__VARIANCE, oldVariance, newVariance);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariance(uncert_Variance newVariance)
  {
    if (newVariance != variance)
    {
      NotificationChain msgs = null;
      if (variance != null)
        msgs = ((InternalEObject)variance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_NORMAL__VARIANCE, null, msgs);
      if (newVariance != null)
        msgs = ((InternalEObject)newVariance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_NORMAL__VARIANCE, null, msgs);
      msgs = basicSetVariance(newVariance, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_NORMAL__VARIANCE, newVariance, newVariance));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_CoefficientOfVariance getCoefficientOfVariance()
  {
    return coefficientOfVariance;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCoefficientOfVariance(uncert_CoefficientOfVariance newCoefficientOfVariance, NotificationChain msgs)
  {
    uncert_CoefficientOfVariance oldCoefficientOfVariance = coefficientOfVariance;
    coefficientOfVariance = newCoefficientOfVariance;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE, oldCoefficientOfVariance, newCoefficientOfVariance);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCoefficientOfVariance(uncert_CoefficientOfVariance newCoefficientOfVariance)
  {
    if (newCoefficientOfVariance != coefficientOfVariance)
    {
      NotificationChain msgs = null;
      if (coefficientOfVariance != null)
        msgs = ((InternalEObject)coefficientOfVariance).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE, null, msgs);
      if (newCoefficientOfVariance != null)
        msgs = ((InternalEObject)newCoefficientOfVariance).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE, null, msgs);
      msgs = basicSetCoefficientOfVariance(newCoefficientOfVariance, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE, newCoefficientOfVariance, newCoefficientOfVariance));
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
      case PharmMLPackage.UNCERT_NORMAL__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.UNCERT_NORMAL__MEAN:
        return basicSetMean(null, msgs);
      case PharmMLPackage.UNCERT_NORMAL__STD_DEV:
        return basicSetStdDev(null, msgs);
      case PharmMLPackage.UNCERT_NORMAL__VARIANCE:
        return basicSetVariance(null, msgs);
      case PharmMLPackage.UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE:
        return basicSetCoefficientOfVariance(null, msgs);
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
      case PharmMLPackage.UNCERT_NORMAL__XMLNS:
        return getXmlns();
      case PharmMLPackage.UNCERT_NORMAL__MEAN:
        return getMean();
      case PharmMLPackage.UNCERT_NORMAL__STD_DEV:
        return getStdDev();
      case PharmMLPackage.UNCERT_NORMAL__VARIANCE:
        return getVariance();
      case PharmMLPackage.UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE:
        return getCoefficientOfVariance();
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
      case PharmMLPackage.UNCERT_NORMAL__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.UNCERT_NORMAL__MEAN:
        setMean((uncert_Mean)newValue);
        return;
      case PharmMLPackage.UNCERT_NORMAL__STD_DEV:
        setStdDev((uncert_StdDev)newValue);
        return;
      case PharmMLPackage.UNCERT_NORMAL__VARIANCE:
        setVariance((uncert_Variance)newValue);
        return;
      case PharmMLPackage.UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE:
        setCoefficientOfVariance((uncert_CoefficientOfVariance)newValue);
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
      case PharmMLPackage.UNCERT_NORMAL__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.UNCERT_NORMAL__MEAN:
        setMean((uncert_Mean)null);
        return;
      case PharmMLPackage.UNCERT_NORMAL__STD_DEV:
        setStdDev((uncert_StdDev)null);
        return;
      case PharmMLPackage.UNCERT_NORMAL__VARIANCE:
        setVariance((uncert_Variance)null);
        return;
      case PharmMLPackage.UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE:
        setCoefficientOfVariance((uncert_CoefficientOfVariance)null);
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
      case PharmMLPackage.UNCERT_NORMAL__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.UNCERT_NORMAL__MEAN:
        return mean != null;
      case PharmMLPackage.UNCERT_NORMAL__STD_DEV:
        return stdDev != null;
      case PharmMLPackage.UNCERT_NORMAL__VARIANCE:
        return variance != null;
      case PharmMLPackage.UNCERT_NORMAL__COEFFICIENT_OF_VARIANCE:
        return coefficientOfVariance != null;
    }
    return super.eIsSet(featureID);
  }

} //uncert_NormalImpl

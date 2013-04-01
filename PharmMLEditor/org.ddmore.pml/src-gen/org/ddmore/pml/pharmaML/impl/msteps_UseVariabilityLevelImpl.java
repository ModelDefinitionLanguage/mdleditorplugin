/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel;

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
 * An implementation of the model object '<em><b>msteps Use Variability Level</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_UseVariabilityLevelImpl#getLevelId <em>Level Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_UseVariabilityLevelImpl#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_UseVariabilityLevelImpl extends MinimalEObjectImpl.Container implements msteps_UseVariabilityLevel
{
  /**
   * The default value of the '{@link #getLevelId() <em>Level Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLevelId()
   * @generated
   * @ordered
   */
  protected static final String LEVEL_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLevelId() <em>Level Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLevelId()
   * @generated
   * @ordered
   */
  protected String levelId = LEVEL_ID_EDEFAULT;

  /**
   * The cached value of the '{@link #getXmlns() <em>Xmlns</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlns()
   * @generated
   * @ordered
   */
  protected EList<ct_xmlns> xmlns;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_UseVariabilityLevelImpl()
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
    return PharmaMLPackage.eINSTANCE.getmsteps_UseVariabilityLevel();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLevelId()
  {
    return levelId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLevelId(String newLevelId)
  {
    String oldLevelId = levelId;
    levelId = newLevelId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__LEVEL_ID, oldLevelId, levelId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ct_xmlns> getXmlns()
  {
    if (xmlns == null)
    {
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__XMLNS);
    }
    return xmlns;
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
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__LEVEL_ID:
        return getLevelId();
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__XMLNS:
        return getXmlns();
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
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__LEVEL_ID:
        setLevelId((String)newValue);
        return;
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
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
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__LEVEL_ID:
        setLevelId(LEVEL_ID_EDEFAULT);
        return;
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__XMLNS:
        getXmlns().clear();
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
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__LEVEL_ID:
        return LEVEL_ID_EDEFAULT == null ? levelId != null : !LEVEL_ID_EDEFAULT.equals(levelId);
      case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (levelId: ");
    result.append(levelId);
    result.append(')');
    return result.toString();
  }

} //msteps_UseVariabilityLevelImpl

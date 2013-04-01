/**
 */
package org.ddmore.pml.pharmaML.impl;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_writtenVersion;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ct written Version</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_writtenVersionImpl#getWrittenVersion <em>Written Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_writtenVersionImpl extends MinimalEObjectImpl.Container implements ct_writtenVersion
{
  /**
   * The default value of the '{@link #getWrittenVersion() <em>Written Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWrittenVersion()
   * @generated
   * @ordered
   */
  protected static final String WRITTEN_VERSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWrittenVersion() <em>Written Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWrittenVersion()
   * @generated
   * @ordered
   */
  protected String writtenVersion = WRITTEN_VERSION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_writtenVersionImpl()
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
    return PharmaMLPackage.eINSTANCE.getct_writtenVersion();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getWrittenVersion()
  {
    return writtenVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWrittenVersion(String newWrittenVersion)
  {
    String oldWrittenVersion = writtenVersion;
    writtenVersion = newWrittenVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_WRITTEN_VERSION__WRITTEN_VERSION, oldWrittenVersion, writtenVersion));
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
      case PharmaMLPackage.CT_WRITTEN_VERSION__WRITTEN_VERSION:
        return getWrittenVersion();
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
      case PharmaMLPackage.CT_WRITTEN_VERSION__WRITTEN_VERSION:
        setWrittenVersion((String)newValue);
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
      case PharmaMLPackage.CT_WRITTEN_VERSION__WRITTEN_VERSION:
        setWrittenVersion(WRITTEN_VERSION_EDEFAULT);
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
      case PharmaMLPackage.CT_WRITTEN_VERSION__WRITTEN_VERSION:
        return WRITTEN_VERSION_EDEFAULT == null ? writtenVersion != null : !WRITTEN_VERSION_EDEFAULT.equals(writtenVersion);
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
    result.append(" (writtenVersion: ");
    result.append(writtenVersion);
    result.append(')');
    return result.toString();
  }

} //ct_writtenVersionImpl

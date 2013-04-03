/**
 */
package org.ddmore.pml.pharmaML.impl;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.mdef_ParameterTypeName;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>mdef Parameter Type Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterTypeNameImpl#getLog <em>Log</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterTypeNameImpl#getLogit <em>Logit</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ParameterTypeNameImpl#getNone <em>None</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_ParameterTypeNameImpl extends MinimalEObjectImpl.Container implements mdef_ParameterTypeName
{
  /**
   * The default value of the '{@link #getLog() <em>Log</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLog()
   * @generated
   * @ordered
   */
  protected static final String LOG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLog() <em>Log</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLog()
   * @generated
   * @ordered
   */
  protected String log = LOG_EDEFAULT;

  /**
   * The default value of the '{@link #getLogit() <em>Logit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLogit()
   * @generated
   * @ordered
   */
  protected static final String LOGIT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLogit() <em>Logit</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLogit()
   * @generated
   * @ordered
   */
  protected String logit = LOGIT_EDEFAULT;

  /**
   * The default value of the '{@link #getNone() <em>None</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNone()
   * @generated
   * @ordered
   */
  protected static final String NONE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNone() <em>None</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNone()
   * @generated
   * @ordered
   */
  protected String none = NONE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_ParameterTypeNameImpl()
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
    return PharmaMLPackage.eINSTANCE.getmdef_ParameterTypeName();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLog()
  {
    return log;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLog(String newLog)
  {
    String oldLog = log;
    log = newLog;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOG, oldLog, log));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLogit()
  {
    return logit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLogit(String newLogit)
  {
    String oldLogit = logit;
    logit = newLogit;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOGIT, oldLogit, logit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNone()
  {
    return none;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNone(String newNone)
  {
    String oldNone = none;
    none = newNone;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__NONE, oldNone, none));
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
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOG:
        return getLog();
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOGIT:
        return getLogit();
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__NONE:
        return getNone();
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
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOG:
        setLog((String)newValue);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOGIT:
        setLogit((String)newValue);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__NONE:
        setNone((String)newValue);
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
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOG:
        setLog(LOG_EDEFAULT);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOGIT:
        setLogit(LOGIT_EDEFAULT);
        return;
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__NONE:
        setNone(NONE_EDEFAULT);
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
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOG:
        return LOG_EDEFAULT == null ? log != null : !LOG_EDEFAULT.equals(log);
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__LOGIT:
        return LOGIT_EDEFAULT == null ? logit != null : !LOGIT_EDEFAULT.equals(logit);
      case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME__NONE:
        return NONE_EDEFAULT == null ? none != null : !NONE_EDEFAULT.equals(none);
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
    result.append(" (log: ");
    result.append(log);
    result.append(", logit: ");
    result.append(logit);
    result.append(", none: ");
    result.append(none);
    result.append(')');
    return result.toString();
  }

} //mdef_ParameterTypeNameImpl

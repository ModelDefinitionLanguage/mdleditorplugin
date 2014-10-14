/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_BinopName;
import eu.ddmore.pml.pharmML.PharmMLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Math Binop Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopNameImpl#getBinopname <em>Binopname</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopNameImpl#getMinus <em>Minus</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopNameImpl#getTimes <em>Times</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopNameImpl#getDivide <em>Divide</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopNameImpl#getPower <em>Power</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopNameImpl#getLog <em>Log</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopNameImpl#getRoot <em>Root</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_BinopNameImpl extends MinimalEObjectImpl.Container implements Math_BinopName
{
  /**
   * The default value of the '{@link #getBinopname() <em>Binopname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBinopname()
   * @generated
   * @ordered
   */
  protected static final String BINOPNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBinopname() <em>Binopname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBinopname()
   * @generated
   * @ordered
   */
  protected String binopname = BINOPNAME_EDEFAULT;

  /**
   * The default value of the '{@link #getMinus() <em>Minus</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinus()
   * @generated
   * @ordered
   */
  protected static final String MINUS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMinus() <em>Minus</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinus()
   * @generated
   * @ordered
   */
  protected String minus = MINUS_EDEFAULT;

  /**
   * The default value of the '{@link #getTimes() <em>Times</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTimes()
   * @generated
   * @ordered
   */
  protected static final String TIMES_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTimes() <em>Times</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTimes()
   * @generated
   * @ordered
   */
  protected String times = TIMES_EDEFAULT;

  /**
   * The default value of the '{@link #getDivide() <em>Divide</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDivide()
   * @generated
   * @ordered
   */
  protected static final String DIVIDE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDivide() <em>Divide</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDivide()
   * @generated
   * @ordered
   */
  protected String divide = DIVIDE_EDEFAULT;

  /**
   * The default value of the '{@link #getPower() <em>Power</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPower()
   * @generated
   * @ordered
   */
  protected static final String POWER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPower() <em>Power</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPower()
   * @generated
   * @ordered
   */
  protected String power = POWER_EDEFAULT;

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
   * The default value of the '{@link #getRoot() <em>Root</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRoot()
   * @generated
   * @ordered
   */
  protected static final String ROOT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRoot() <em>Root</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRoot()
   * @generated
   * @ordered
   */
  protected String root = ROOT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_BinopNameImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_BinopName();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBinopname()
  {
    return binopname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBinopname(String newBinopname)
  {
    String oldBinopname = binopname;
    binopname = newBinopname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_NAME__BINOPNAME, oldBinopname, binopname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMinus()
  {
    return minus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMinus(String newMinus)
  {
    String oldMinus = minus;
    minus = newMinus;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_NAME__MINUS, oldMinus, minus));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTimes()
  {
    return times;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTimes(String newTimes)
  {
    String oldTimes = times;
    times = newTimes;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_NAME__TIMES, oldTimes, times));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDivide()
  {
    return divide;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDivide(String newDivide)
  {
    String oldDivide = divide;
    divide = newDivide;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_NAME__DIVIDE, oldDivide, divide));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPower()
  {
    return power;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPower(String newPower)
  {
    String oldPower = power;
    power = newPower;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_NAME__POWER, oldPower, power));
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
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_NAME__LOG, oldLog, log));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRoot()
  {
    return root;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRoot(String newRoot)
  {
    String oldRoot = root;
    root = newRoot;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_NAME__ROOT, oldRoot, root));
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
      case PharmMLPackage.MATH_BINOP_NAME__BINOPNAME:
        return getBinopname();
      case PharmMLPackage.MATH_BINOP_NAME__MINUS:
        return getMinus();
      case PharmMLPackage.MATH_BINOP_NAME__TIMES:
        return getTimes();
      case PharmMLPackage.MATH_BINOP_NAME__DIVIDE:
        return getDivide();
      case PharmMLPackage.MATH_BINOP_NAME__POWER:
        return getPower();
      case PharmMLPackage.MATH_BINOP_NAME__LOG:
        return getLog();
      case PharmMLPackage.MATH_BINOP_NAME__ROOT:
        return getRoot();
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
      case PharmMLPackage.MATH_BINOP_NAME__BINOPNAME:
        setBinopname((String)newValue);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__MINUS:
        setMinus((String)newValue);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__TIMES:
        setTimes((String)newValue);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__DIVIDE:
        setDivide((String)newValue);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__POWER:
        setPower((String)newValue);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__LOG:
        setLog((String)newValue);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__ROOT:
        setRoot((String)newValue);
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
      case PharmMLPackage.MATH_BINOP_NAME__BINOPNAME:
        setBinopname(BINOPNAME_EDEFAULT);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__MINUS:
        setMinus(MINUS_EDEFAULT);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__TIMES:
        setTimes(TIMES_EDEFAULT);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__DIVIDE:
        setDivide(DIVIDE_EDEFAULT);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__POWER:
        setPower(POWER_EDEFAULT);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__LOG:
        setLog(LOG_EDEFAULT);
        return;
      case PharmMLPackage.MATH_BINOP_NAME__ROOT:
        setRoot(ROOT_EDEFAULT);
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
      case PharmMLPackage.MATH_BINOP_NAME__BINOPNAME:
        return BINOPNAME_EDEFAULT == null ? binopname != null : !BINOPNAME_EDEFAULT.equals(binopname);
      case PharmMLPackage.MATH_BINOP_NAME__MINUS:
        return MINUS_EDEFAULT == null ? minus != null : !MINUS_EDEFAULT.equals(minus);
      case PharmMLPackage.MATH_BINOP_NAME__TIMES:
        return TIMES_EDEFAULT == null ? times != null : !TIMES_EDEFAULT.equals(times);
      case PharmMLPackage.MATH_BINOP_NAME__DIVIDE:
        return DIVIDE_EDEFAULT == null ? divide != null : !DIVIDE_EDEFAULT.equals(divide);
      case PharmMLPackage.MATH_BINOP_NAME__POWER:
        return POWER_EDEFAULT == null ? power != null : !POWER_EDEFAULT.equals(power);
      case PharmMLPackage.MATH_BINOP_NAME__LOG:
        return LOG_EDEFAULT == null ? log != null : !LOG_EDEFAULT.equals(log);
      case PharmMLPackage.MATH_BINOP_NAME__ROOT:
        return ROOT_EDEFAULT == null ? root != null : !ROOT_EDEFAULT.equals(root);
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
    result.append(" (binopname: ");
    result.append(binopname);
    result.append(", minus: ");
    result.append(minus);
    result.append(", times: ");
    result.append(times);
    result.append(", divide: ");
    result.append(divide);
    result.append(", power: ");
    result.append(power);
    result.append(", log: ");
    result.append(log);
    result.append(", root: ");
    result.append(root);
    result.append(')');
    return result.toString();
  }

} //Math_BinopNameImpl

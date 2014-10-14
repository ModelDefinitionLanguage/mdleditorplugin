/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_ConstantName;
import eu.ddmore.pml.pharmML.PharmMLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Math Constant Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ConstantNameImpl#getNotanumber <em>Notanumber</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ConstantNameImpl#getPi <em>Pi</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ConstantNameImpl#getExponentiale <em>Exponentiale</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_ConstantNameImpl#getInfinity <em>Infinity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_ConstantNameImpl extends MinimalEObjectImpl.Container implements Math_ConstantName
{
  /**
   * The default value of the '{@link #getNotanumber() <em>Notanumber</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNotanumber()
   * @generated
   * @ordered
   */
  protected static final String NOTANUMBER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNotanumber() <em>Notanumber</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNotanumber()
   * @generated
   * @ordered
   */
  protected String notanumber = NOTANUMBER_EDEFAULT;

  /**
   * The default value of the '{@link #getPi() <em>Pi</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPi()
   * @generated
   * @ordered
   */
  protected static final String PI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPi() <em>Pi</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPi()
   * @generated
   * @ordered
   */
  protected String pi = PI_EDEFAULT;

  /**
   * The default value of the '{@link #getExponentiale() <em>Exponentiale</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExponentiale()
   * @generated
   * @ordered
   */
  protected static final String EXPONENTIALE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getExponentiale() <em>Exponentiale</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExponentiale()
   * @generated
   * @ordered
   */
  protected String exponentiale = EXPONENTIALE_EDEFAULT;

  /**
   * The default value of the '{@link #getInfinity() <em>Infinity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInfinity()
   * @generated
   * @ordered
   */
  protected static final String INFINITY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInfinity() <em>Infinity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInfinity()
   * @generated
   * @ordered
   */
  protected String infinity = INFINITY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_ConstantNameImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_ConstantName();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNotanumber()
  {
    return notanumber;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNotanumber(String newNotanumber)
  {
    String oldNotanumber = notanumber;
    notanumber = newNotanumber;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_CONSTANT_NAME__NOTANUMBER, oldNotanumber, notanumber));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPi()
  {
    return pi;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPi(String newPi)
  {
    String oldPi = pi;
    pi = newPi;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_CONSTANT_NAME__PI, oldPi, pi));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getExponentiale()
  {
    return exponentiale;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExponentiale(String newExponentiale)
  {
    String oldExponentiale = exponentiale;
    exponentiale = newExponentiale;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_CONSTANT_NAME__EXPONENTIALE, oldExponentiale, exponentiale));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInfinity()
  {
    return infinity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInfinity(String newInfinity)
  {
    String oldInfinity = infinity;
    infinity = newInfinity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_CONSTANT_NAME__INFINITY, oldInfinity, infinity));
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
      case PharmMLPackage.MATH_CONSTANT_NAME__NOTANUMBER:
        return getNotanumber();
      case PharmMLPackage.MATH_CONSTANT_NAME__PI:
        return getPi();
      case PharmMLPackage.MATH_CONSTANT_NAME__EXPONENTIALE:
        return getExponentiale();
      case PharmMLPackage.MATH_CONSTANT_NAME__INFINITY:
        return getInfinity();
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
      case PharmMLPackage.MATH_CONSTANT_NAME__NOTANUMBER:
        setNotanumber((String)newValue);
        return;
      case PharmMLPackage.MATH_CONSTANT_NAME__PI:
        setPi((String)newValue);
        return;
      case PharmMLPackage.MATH_CONSTANT_NAME__EXPONENTIALE:
        setExponentiale((String)newValue);
        return;
      case PharmMLPackage.MATH_CONSTANT_NAME__INFINITY:
        setInfinity((String)newValue);
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
      case PharmMLPackage.MATH_CONSTANT_NAME__NOTANUMBER:
        setNotanumber(NOTANUMBER_EDEFAULT);
        return;
      case PharmMLPackage.MATH_CONSTANT_NAME__PI:
        setPi(PI_EDEFAULT);
        return;
      case PharmMLPackage.MATH_CONSTANT_NAME__EXPONENTIALE:
        setExponentiale(EXPONENTIALE_EDEFAULT);
        return;
      case PharmMLPackage.MATH_CONSTANT_NAME__INFINITY:
        setInfinity(INFINITY_EDEFAULT);
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
      case PharmMLPackage.MATH_CONSTANT_NAME__NOTANUMBER:
        return NOTANUMBER_EDEFAULT == null ? notanumber != null : !NOTANUMBER_EDEFAULT.equals(notanumber);
      case PharmMLPackage.MATH_CONSTANT_NAME__PI:
        return PI_EDEFAULT == null ? pi != null : !PI_EDEFAULT.equals(pi);
      case PharmMLPackage.MATH_CONSTANT_NAME__EXPONENTIALE:
        return EXPONENTIALE_EDEFAULT == null ? exponentiale != null : !EXPONENTIALE_EDEFAULT.equals(exponentiale);
      case PharmMLPackage.MATH_CONSTANT_NAME__INFINITY:
        return INFINITY_EDEFAULT == null ? infinity != null : !INFINITY_EDEFAULT.equals(infinity);
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
    result.append(" (notanumber: ");
    result.append(notanumber);
    result.append(", pi: ");
    result.append(pi);
    result.append(", exponentiale: ");
    result.append(exponentiale);
    result.append(", infinity: ");
    result.append(infinity);
    result.append(')');
    return result.toString();
  }

} //Math_ConstantNameImpl

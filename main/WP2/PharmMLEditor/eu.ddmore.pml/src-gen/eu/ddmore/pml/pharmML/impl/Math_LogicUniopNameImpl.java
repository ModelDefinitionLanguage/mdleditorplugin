/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_LogicUniopName;
import eu.ddmore.pml.pharmML.PharmMLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Math Logic Uniop Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicUniopNameImpl#getIsDefined <em>Is Defined</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicUniopNameImpl#getNot <em>Not</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_LogicUniopNameImpl extends MinimalEObjectImpl.Container implements Math_LogicUniopName
{
  /**
   * The default value of the '{@link #getIsDefined() <em>Is Defined</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIsDefined()
   * @generated
   * @ordered
   */
  protected static final String IS_DEFINED_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIsDefined() <em>Is Defined</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIsDefined()
   * @generated
   * @ordered
   */
  protected String isDefined = IS_DEFINED_EDEFAULT;

  /**
   * The default value of the '{@link #getNot() <em>Not</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNot()
   * @generated
   * @ordered
   */
  protected static final String NOT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNot() <em>Not</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNot()
   * @generated
   * @ordered
   */
  protected String not = NOT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_LogicUniopNameImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_LogicUniopName();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIsDefined()
  {
    return isDefined;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIsDefined(String newIsDefined)
  {
    String oldIsDefined = isDefined;
    isDefined = newIsDefined;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_UNIOP_NAME__IS_DEFINED, oldIsDefined, isDefined));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNot()
  {
    return not;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNot(String newNot)
  {
    String oldNot = not;
    not = newNot;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_UNIOP_NAME__NOT, oldNot, not));
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
      case PharmMLPackage.MATH_LOGIC_UNIOP_NAME__IS_DEFINED:
        return getIsDefined();
      case PharmMLPackage.MATH_LOGIC_UNIOP_NAME__NOT:
        return getNot();
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
      case PharmMLPackage.MATH_LOGIC_UNIOP_NAME__IS_DEFINED:
        setIsDefined((String)newValue);
        return;
      case PharmMLPackage.MATH_LOGIC_UNIOP_NAME__NOT:
        setNot((String)newValue);
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
      case PharmMLPackage.MATH_LOGIC_UNIOP_NAME__IS_DEFINED:
        setIsDefined(IS_DEFINED_EDEFAULT);
        return;
      case PharmMLPackage.MATH_LOGIC_UNIOP_NAME__NOT:
        setNot(NOT_EDEFAULT);
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
      case PharmMLPackage.MATH_LOGIC_UNIOP_NAME__IS_DEFINED:
        return IS_DEFINED_EDEFAULT == null ? isDefined != null : !IS_DEFINED_EDEFAULT.equals(isDefined);
      case PharmMLPackage.MATH_LOGIC_UNIOP_NAME__NOT:
        return NOT_EDEFAULT == null ? not != null : !NOT_EDEFAULT.equals(not);
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
    result.append(" (isDefined: ");
    result.append(isDefined);
    result.append(", not: ");
    result.append(not);
    result.append(')');
    return result.toString();
  }

} //Math_LogicUniopNameImpl

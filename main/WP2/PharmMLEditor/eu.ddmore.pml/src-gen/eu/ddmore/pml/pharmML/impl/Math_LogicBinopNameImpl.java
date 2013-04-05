/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_LogicBinopName;
import eu.ddmore.pml.pharmML.PharmMLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Math Logic Binop Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl#getLt <em>Lt</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl#getLeq <em>Leq</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl#getGt <em>Gt</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl#getGeq <em>Geq</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl#getEq <em>Eq</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl#getNeq <em>Neq</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl#getAnd <em>And</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl#getOr <em>Or</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_LogicBinopNameImpl#getXor <em>Xor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_LogicBinopNameImpl extends MinimalEObjectImpl.Container implements Math_LogicBinopName
{
  /**
   * The default value of the '{@link #getLt() <em>Lt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLt()
   * @generated
   * @ordered
   */
  protected static final String LT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLt() <em>Lt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLt()
   * @generated
   * @ordered
   */
  protected String lt = LT_EDEFAULT;

  /**
   * The default value of the '{@link #getLeq() <em>Leq</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeq()
   * @generated
   * @ordered
   */
  protected static final String LEQ_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLeq() <em>Leq</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeq()
   * @generated
   * @ordered
   */
  protected String leq = LEQ_EDEFAULT;

  /**
   * The default value of the '{@link #getGt() <em>Gt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGt()
   * @generated
   * @ordered
   */
  protected static final String GT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGt() <em>Gt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGt()
   * @generated
   * @ordered
   */
  protected String gt = GT_EDEFAULT;

  /**
   * The default value of the '{@link #getGeq() <em>Geq</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGeq()
   * @generated
   * @ordered
   */
  protected static final String GEQ_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGeq() <em>Geq</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGeq()
   * @generated
   * @ordered
   */
  protected String geq = GEQ_EDEFAULT;

  /**
   * The default value of the '{@link #getEq() <em>Eq</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEq()
   * @generated
   * @ordered
   */
  protected static final String EQ_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEq() <em>Eq</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEq()
   * @generated
   * @ordered
   */
  protected String eq = EQ_EDEFAULT;

  /**
   * The default value of the '{@link #getNeq() <em>Neq</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNeq()
   * @generated
   * @ordered
   */
  protected static final String NEQ_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNeq() <em>Neq</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNeq()
   * @generated
   * @ordered
   */
  protected String neq = NEQ_EDEFAULT;

  /**
   * The default value of the '{@link #getAnd() <em>And</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnd()
   * @generated
   * @ordered
   */
  protected static final String AND_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAnd() <em>And</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnd()
   * @generated
   * @ordered
   */
  protected String and = AND_EDEFAULT;

  /**
   * The default value of the '{@link #getOr() <em>Or</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOr()
   * @generated
   * @ordered
   */
  protected static final String OR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOr() <em>Or</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOr()
   * @generated
   * @ordered
   */
  protected String or = OR_EDEFAULT;

  /**
   * The default value of the '{@link #getXor() <em>Xor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXor()
   * @generated
   * @ordered
   */
  protected static final String XOR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getXor() <em>Xor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXor()
   * @generated
   * @ordered
   */
  protected String xor = XOR_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_LogicBinopNameImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_LogicBinopName();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLt()
  {
    return lt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLt(String newLt)
  {
    String oldLt = lt;
    lt = newLt;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_BINOP_NAME__LT, oldLt, lt));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLeq()
  {
    return leq;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLeq(String newLeq)
  {
    String oldLeq = leq;
    leq = newLeq;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_BINOP_NAME__LEQ, oldLeq, leq));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getGt()
  {
    return gt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGt(String newGt)
  {
    String oldGt = gt;
    gt = newGt;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_BINOP_NAME__GT, oldGt, gt));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getGeq()
  {
    return geq;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGeq(String newGeq)
  {
    String oldGeq = geq;
    geq = newGeq;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_BINOP_NAME__GEQ, oldGeq, geq));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEq()
  {
    return eq;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEq(String newEq)
  {
    String oldEq = eq;
    eq = newEq;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_BINOP_NAME__EQ, oldEq, eq));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNeq()
  {
    return neq;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNeq(String newNeq)
  {
    String oldNeq = neq;
    neq = newNeq;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_BINOP_NAME__NEQ, oldNeq, neq));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAnd()
  {
    return and;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnd(String newAnd)
  {
    String oldAnd = and;
    and = newAnd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_BINOP_NAME__AND, oldAnd, and));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOr()
  {
    return or;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOr(String newOr)
  {
    String oldOr = or;
    or = newOr;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_BINOP_NAME__OR, oldOr, or));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getXor()
  {
    return xor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setXor(String newXor)
  {
    String oldXor = xor;
    xor = newXor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_LOGIC_BINOP_NAME__XOR, oldXor, xor));
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
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__LT:
        return getLt();
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__LEQ:
        return getLeq();
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__GT:
        return getGt();
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__GEQ:
        return getGeq();
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__EQ:
        return getEq();
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__NEQ:
        return getNeq();
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__AND:
        return getAnd();
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__OR:
        return getOr();
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__XOR:
        return getXor();
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
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__LT:
        setLt((String)newValue);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__LEQ:
        setLeq((String)newValue);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__GT:
        setGt((String)newValue);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__GEQ:
        setGeq((String)newValue);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__EQ:
        setEq((String)newValue);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__NEQ:
        setNeq((String)newValue);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__AND:
        setAnd((String)newValue);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__OR:
        setOr((String)newValue);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__XOR:
        setXor((String)newValue);
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
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__LT:
        setLt(LT_EDEFAULT);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__LEQ:
        setLeq(LEQ_EDEFAULT);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__GT:
        setGt(GT_EDEFAULT);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__GEQ:
        setGeq(GEQ_EDEFAULT);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__EQ:
        setEq(EQ_EDEFAULT);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__NEQ:
        setNeq(NEQ_EDEFAULT);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__AND:
        setAnd(AND_EDEFAULT);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__OR:
        setOr(OR_EDEFAULT);
        return;
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__XOR:
        setXor(XOR_EDEFAULT);
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
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__LT:
        return LT_EDEFAULT == null ? lt != null : !LT_EDEFAULT.equals(lt);
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__LEQ:
        return LEQ_EDEFAULT == null ? leq != null : !LEQ_EDEFAULT.equals(leq);
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__GT:
        return GT_EDEFAULT == null ? gt != null : !GT_EDEFAULT.equals(gt);
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__GEQ:
        return GEQ_EDEFAULT == null ? geq != null : !GEQ_EDEFAULT.equals(geq);
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__EQ:
        return EQ_EDEFAULT == null ? eq != null : !EQ_EDEFAULT.equals(eq);
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__NEQ:
        return NEQ_EDEFAULT == null ? neq != null : !NEQ_EDEFAULT.equals(neq);
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__AND:
        return AND_EDEFAULT == null ? and != null : !AND_EDEFAULT.equals(and);
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__OR:
        return OR_EDEFAULT == null ? or != null : !OR_EDEFAULT.equals(or);
      case PharmMLPackage.MATH_LOGIC_BINOP_NAME__XOR:
        return XOR_EDEFAULT == null ? xor != null : !XOR_EDEFAULT.equals(xor);
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
    result.append(" (lt: ");
    result.append(lt);
    result.append(", leq: ");
    result.append(leq);
    result.append(", gt: ");
    result.append(gt);
    result.append(", geq: ");
    result.append(geq);
    result.append(", eq: ");
    result.append(eq);
    result.append(", neq: ");
    result.append(neq);
    result.append(", and: ");
    result.append(and);
    result.append(", or: ");
    result.append(or);
    result.append(", xor: ");
    result.append(xor);
    result.append(')');
    return result.toString();
  }

} //Math_LogicBinopNameImpl

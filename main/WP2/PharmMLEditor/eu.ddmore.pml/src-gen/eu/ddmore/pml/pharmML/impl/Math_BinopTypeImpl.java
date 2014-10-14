/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_BinopName;
import eu.ddmore.pml.pharmML.Math_BinopType;
import eu.ddmore.pml.pharmML.Math_ExprType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;

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
 * An implementation of the model object '<em><b>Math Binop Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopTypeImpl#getOp <em>Op</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopTypeImpl#getLeftExpr <em>Left Expr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_BinopTypeImpl#getRightExpr <em>Right Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_BinopTypeImpl extends MinimalEObjectImpl.Container implements Math_BinopType
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
   * The cached value of the '{@link #getOp() <em>Op</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOp()
   * @generated
   * @ordered
   */
  protected Math_BinopName op;

  /**
   * The cached value of the '{@link #getLeftExpr() <em>Left Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLeftExpr()
   * @generated
   * @ordered
   */
  protected Math_ExprType leftExpr;

  /**
   * The cached value of the '{@link #getRightExpr() <em>Right Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRightExpr()
   * @generated
   * @ordered
   */
  protected Math_ExprType rightExpr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_BinopTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_BinopType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MATH_BINOP_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_BinopName getOp()
  {
    return op;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOp(Math_BinopName newOp, NotificationChain msgs)
  {
    Math_BinopName oldOp = op;
    op = newOp;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_TYPE__OP, oldOp, newOp);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOp(Math_BinopName newOp)
  {
    if (newOp != op)
    {
      NotificationChain msgs = null;
      if (op != null)
        msgs = ((InternalEObject)op).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_BINOP_TYPE__OP, null, msgs);
      if (newOp != null)
        msgs = ((InternalEObject)newOp).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_BINOP_TYPE__OP, null, msgs);
      msgs = basicSetOp(newOp, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_TYPE__OP, newOp, newOp));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_ExprType getLeftExpr()
  {
    return leftExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLeftExpr(Math_ExprType newLeftExpr, NotificationChain msgs)
  {
    Math_ExprType oldLeftExpr = leftExpr;
    leftExpr = newLeftExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_TYPE__LEFT_EXPR, oldLeftExpr, newLeftExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLeftExpr(Math_ExprType newLeftExpr)
  {
    if (newLeftExpr != leftExpr)
    {
      NotificationChain msgs = null;
      if (leftExpr != null)
        msgs = ((InternalEObject)leftExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_BINOP_TYPE__LEFT_EXPR, null, msgs);
      if (newLeftExpr != null)
        msgs = ((InternalEObject)newLeftExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_BINOP_TYPE__LEFT_EXPR, null, msgs);
      msgs = basicSetLeftExpr(newLeftExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_TYPE__LEFT_EXPR, newLeftExpr, newLeftExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_ExprType getRightExpr()
  {
    return rightExpr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRightExpr(Math_ExprType newRightExpr, NotificationChain msgs)
  {
    Math_ExprType oldRightExpr = rightExpr;
    rightExpr = newRightExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_TYPE__RIGHT_EXPR, oldRightExpr, newRightExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRightExpr(Math_ExprType newRightExpr)
  {
    if (newRightExpr != rightExpr)
    {
      NotificationChain msgs = null;
      if (rightExpr != null)
        msgs = ((InternalEObject)rightExpr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_BINOP_TYPE__RIGHT_EXPR, null, msgs);
      if (newRightExpr != null)
        msgs = ((InternalEObject)newRightExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_BINOP_TYPE__RIGHT_EXPR, null, msgs);
      msgs = basicSetRightExpr(newRightExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_BINOP_TYPE__RIGHT_EXPR, newRightExpr, newRightExpr));
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
      case PharmMLPackage.MATH_BINOP_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MATH_BINOP_TYPE__OP:
        return basicSetOp(null, msgs);
      case PharmMLPackage.MATH_BINOP_TYPE__LEFT_EXPR:
        return basicSetLeftExpr(null, msgs);
      case PharmMLPackage.MATH_BINOP_TYPE__RIGHT_EXPR:
        return basicSetRightExpr(null, msgs);
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
      case PharmMLPackage.MATH_BINOP_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MATH_BINOP_TYPE__OP:
        return getOp();
      case PharmMLPackage.MATH_BINOP_TYPE__LEFT_EXPR:
        return getLeftExpr();
      case PharmMLPackage.MATH_BINOP_TYPE__RIGHT_EXPR:
        return getRightExpr();
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
      case PharmMLPackage.MATH_BINOP_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MATH_BINOP_TYPE__OP:
        setOp((Math_BinopName)newValue);
        return;
      case PharmMLPackage.MATH_BINOP_TYPE__LEFT_EXPR:
        setLeftExpr((Math_ExprType)newValue);
        return;
      case PharmMLPackage.MATH_BINOP_TYPE__RIGHT_EXPR:
        setRightExpr((Math_ExprType)newValue);
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
      case PharmMLPackage.MATH_BINOP_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MATH_BINOP_TYPE__OP:
        setOp((Math_BinopName)null);
        return;
      case PharmMLPackage.MATH_BINOP_TYPE__LEFT_EXPR:
        setLeftExpr((Math_ExprType)null);
        return;
      case PharmMLPackage.MATH_BINOP_TYPE__RIGHT_EXPR:
        setRightExpr((Math_ExprType)null);
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
      case PharmMLPackage.MATH_BINOP_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MATH_BINOP_TYPE__OP:
        return op != null;
      case PharmMLPackage.MATH_BINOP_TYPE__LEFT_EXPR:
        return leftExpr != null;
      case PharmMLPackage.MATH_BINOP_TYPE__RIGHT_EXPR:
        return rightExpr != null;
    }
    return super.eIsSet(featureID);
  }

} //Math_BinopTypeImpl

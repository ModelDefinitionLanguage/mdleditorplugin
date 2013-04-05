/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_Rhs;
import eu.ddmore.pml.pharmML.ct_block;
import eu.ddmore.pml.pharmML.ct_symbId;
import eu.ddmore.pml.pharmML.msteps_InitialValueType;

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
 * An implementation of the model object '<em><b>msteps Initial Value Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_InitialValueTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_InitialValueTypeImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_InitialValueTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_InitialValueTypeImpl#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_InitialValueTypeImpl extends MinimalEObjectImpl.Container implements msteps_InitialValueType
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
   * The cached value of the '{@link #getBlock() <em>Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlock()
   * @generated
   * @ordered
   */
  protected ct_block block;

  /**
   * The cached value of the '{@link #getSymbId() <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbId()
   * @generated
   * @ordered
   */
  protected ct_symbId symbId;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected ct_Rhs expr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_InitialValueTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmsteps_InitialValueType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_block getBlock()
  {
    return block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBlock(ct_block newBlock, NotificationChain msgs)
  {
    ct_block oldBlock = block;
    block = newBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__BLOCK, oldBlock, newBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBlock(ct_block newBlock)
  {
    if (newBlock != block)
    {
      NotificationChain msgs = null;
      if (block != null)
        msgs = ((InternalEObject)block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__BLOCK, null, msgs);
      if (newBlock != null)
        msgs = ((InternalEObject)newBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__BLOCK, null, msgs);
      msgs = basicSetBlock(newBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__BLOCK, newBlock, newBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_symbId getSymbId()
  {
    return symbId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbId(ct_symbId newSymbId, NotificationChain msgs)
  {
    ct_symbId oldSymbId = symbId;
    symbId = newSymbId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID, oldSymbId, newSymbId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbId(ct_symbId newSymbId)
  {
    if (newSymbId != symbId)
    {
      NotificationChain msgs = null;
      if (symbId != null)
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID, newSymbId, newSymbId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Rhs getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(ct_Rhs newExpr, NotificationChain msgs)
  {
    ct_Rhs oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpr(ct_Rhs newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__EXPR, newExpr, newExpr));
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
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__BLOCK:
        return basicSetBlock(null, msgs);
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__EXPR:
        return basicSetExpr(null, msgs);
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
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__BLOCK:
        return getBlock();
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        return getSymbId();
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__EXPR:
        return getExpr();
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
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__BLOCK:
        setBlock((ct_block)newValue);
        return;
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        setSymbId((ct_symbId)newValue);
        return;
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__EXPR:
        setExpr((ct_Rhs)newValue);
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
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__BLOCK:
        setBlock((ct_block)null);
        return;
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        setSymbId((ct_symbId)null);
        return;
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__EXPR:
        setExpr((ct_Rhs)null);
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
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__BLOCK:
        return block != null;
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        return symbId != null;
      case PharmMLPackage.MSTEPS_INITIAL_VALUE_TYPE__EXPR:
        return expr != null;
    }
    return super.eIsSet(featureID);
  }

} //msteps_InitialValueTypeImpl

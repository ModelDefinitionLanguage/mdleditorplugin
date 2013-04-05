/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_block;
import eu.ddmore.pml.pharmML.Math_symbId;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_Rhs;
import eu.ddmore.pml.pharmML.mdef_InitialConditionType;

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
 * An implementation of the model object '<em><b>mdef Initial Condition Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_InitialConditionTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_InitialConditionTypeImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_InitialConditionTypeImpl#getSymbID <em>Symb ID</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_InitialConditionTypeImpl#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_InitialConditionTypeImpl extends MinimalEObjectImpl.Container implements mdef_InitialConditionType
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
  protected Math_block block;

  /**
   * The cached value of the '{@link #getSymbID() <em>Symb ID</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbID()
   * @generated
   * @ordered
   */
  protected Math_symbId symbID;

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
  protected mdef_InitialConditionTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmdef_InitialConditionType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_block getBlock()
  {
    return block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBlock(Math_block newBlock, NotificationChain msgs)
  {
    Math_block oldBlock = block;
    block = newBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__BLOCK, oldBlock, newBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBlock(Math_block newBlock)
  {
    if (newBlock != block)
    {
      NotificationChain msgs = null;
      if (block != null)
        msgs = ((InternalEObject)block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__BLOCK, null, msgs);
      if (newBlock != null)
        msgs = ((InternalEObject)newBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__BLOCK, null, msgs);
      msgs = basicSetBlock(newBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__BLOCK, newBlock, newBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_symbId getSymbID()
  {
    return symbID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbID(Math_symbId newSymbID, NotificationChain msgs)
  {
    Math_symbId oldSymbID = symbID;
    symbID = newSymbID;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__SYMB_ID, oldSymbID, newSymbID);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbID(Math_symbId newSymbID)
  {
    if (newSymbID != symbID)
    {
      NotificationChain msgs = null;
      if (symbID != null)
        msgs = ((InternalEObject)symbID).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__SYMB_ID, null, msgs);
      if (newSymbID != null)
        msgs = ((InternalEObject)newSymbID).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbID(newSymbID, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__SYMB_ID, newSymbID, newSymbID));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__EXPR, oldExpr, newExpr);
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
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__EXPR, newExpr, newExpr));
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
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__BLOCK:
        return basicSetBlock(null, msgs);
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__SYMB_ID:
        return basicSetSymbID(null, msgs);
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__EXPR:
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
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__BLOCK:
        return getBlock();
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__SYMB_ID:
        return getSymbID();
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__EXPR:
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
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__BLOCK:
        setBlock((Math_block)newValue);
        return;
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__SYMB_ID:
        setSymbID((Math_symbId)newValue);
        return;
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__EXPR:
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
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__BLOCK:
        setBlock((Math_block)null);
        return;
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__SYMB_ID:
        setSymbID((Math_symbId)null);
        return;
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__EXPR:
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
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__BLOCK:
        return block != null;
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__SYMB_ID:
        return symbID != null;
      case PharmMLPackage.MDEF_INITIAL_CONDITION_TYPE__EXPR:
        return expr != null;
    }
    return super.eIsSet(featureID);
  }

} //mdef_InitialConditionTypeImpl

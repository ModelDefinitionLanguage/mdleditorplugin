/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_block;
import eu.ddmore.pml.pharmML.Math_symbId;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.mSteps_InitialEstimateType;
import eu.ddmore.pml.pharmML.mSteps_LowerBoundType;
import eu.ddmore.pml.pharmML.mSteps_UpperBoundType;
import eu.ddmore.pml.pharmML.mSteps_VariableEstimateType;
import eu.ddmore.pml.pharmML.mSteps_fixed;

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
 * An implementation of the model object '<em><b>mSteps Variable Estimate Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mSteps_VariableEstimateTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mSteps_VariableEstimateTypeImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mSteps_VariableEstimateTypeImpl#getFixed <em>Fixed</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mSteps_VariableEstimateTypeImpl#getSymbID <em>Symb ID</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mSteps_VariableEstimateTypeImpl#getInitialEstimate <em>Initial Estimate</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mSteps_VariableEstimateTypeImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mSteps_VariableEstimateTypeImpl#getUpperBound <em>Upper Bound</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mSteps_VariableEstimateTypeImpl extends MinimalEObjectImpl.Container implements mSteps_VariableEstimateType
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
   * The cached value of the '{@link #getFixed() <em>Fixed</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFixed()
   * @generated
   * @ordered
   */
  protected mSteps_fixed fixed;

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
   * The cached value of the '{@link #getInitialEstimate() <em>Initial Estimate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitialEstimate()
   * @generated
   * @ordered
   */
  protected mSteps_InitialEstimateType initialEstimate;

  /**
   * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLowerBound()
   * @generated
   * @ordered
   */
  protected mSteps_LowerBoundType lowerBound;

  /**
   * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUpperBound()
   * @generated
   * @ordered
   */
  protected mSteps_UpperBoundType upperBound;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mSteps_VariableEstimateTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmSteps_VariableEstimateType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__XMLNS);
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK, oldBlock, newBlock);
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
        msgs = ((InternalEObject)block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK, null, msgs);
      if (newBlock != null)
        msgs = ((InternalEObject)newBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK, null, msgs);
      msgs = basicSetBlock(newBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK, newBlock, newBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mSteps_fixed getFixed()
  {
    return fixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFixed(mSteps_fixed newFixed, NotificationChain msgs)
  {
    mSteps_fixed oldFixed = fixed;
    fixed = newFixed;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED, oldFixed, newFixed);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFixed(mSteps_fixed newFixed)
  {
    if (newFixed != fixed)
    {
      NotificationChain msgs = null;
      if (fixed != null)
        msgs = ((InternalEObject)fixed).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED, null, msgs);
      if (newFixed != null)
        msgs = ((InternalEObject)newFixed).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED, null, msgs);
      msgs = basicSetFixed(newFixed, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED, newFixed, newFixed));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID, oldSymbID, newSymbID);
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
        msgs = ((InternalEObject)symbID).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID, null, msgs);
      if (newSymbID != null)
        msgs = ((InternalEObject)newSymbID).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbID(newSymbID, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID, newSymbID, newSymbID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mSteps_InitialEstimateType getInitialEstimate()
  {
    return initialEstimate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInitialEstimate(mSteps_InitialEstimateType newInitialEstimate, NotificationChain msgs)
  {
    mSteps_InitialEstimateType oldInitialEstimate = initialEstimate;
    initialEstimate = newInitialEstimate;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE, oldInitialEstimate, newInitialEstimate);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInitialEstimate(mSteps_InitialEstimateType newInitialEstimate)
  {
    if (newInitialEstimate != initialEstimate)
    {
      NotificationChain msgs = null;
      if (initialEstimate != null)
        msgs = ((InternalEObject)initialEstimate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE, null, msgs);
      if (newInitialEstimate != null)
        msgs = ((InternalEObject)newInitialEstimate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE, null, msgs);
      msgs = basicSetInitialEstimate(newInitialEstimate, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE, newInitialEstimate, newInitialEstimate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mSteps_LowerBoundType getLowerBound()
  {
    return lowerBound;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLowerBound(mSteps_LowerBoundType newLowerBound, NotificationChain msgs)
  {
    mSteps_LowerBoundType oldLowerBound = lowerBound;
    lowerBound = newLowerBound;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND, oldLowerBound, newLowerBound);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLowerBound(mSteps_LowerBoundType newLowerBound)
  {
    if (newLowerBound != lowerBound)
    {
      NotificationChain msgs = null;
      if (lowerBound != null)
        msgs = ((InternalEObject)lowerBound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND, null, msgs);
      if (newLowerBound != null)
        msgs = ((InternalEObject)newLowerBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND, null, msgs);
      msgs = basicSetLowerBound(newLowerBound, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND, newLowerBound, newLowerBound));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mSteps_UpperBoundType getUpperBound()
  {
    return upperBound;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUpperBound(mSteps_UpperBoundType newUpperBound, NotificationChain msgs)
  {
    mSteps_UpperBoundType oldUpperBound = upperBound;
    upperBound = newUpperBound;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND, oldUpperBound, newUpperBound);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUpperBound(mSteps_UpperBoundType newUpperBound)
  {
    if (newUpperBound != upperBound)
    {
      NotificationChain msgs = null;
      if (upperBound != null)
        msgs = ((InternalEObject)upperBound).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND, null, msgs);
      if (newUpperBound != null)
        msgs = ((InternalEObject)newUpperBound).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND, null, msgs);
      msgs = basicSetUpperBound(newUpperBound, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND, newUpperBound, newUpperBound));
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
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK:
        return basicSetBlock(null, msgs);
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED:
        return basicSetFixed(null, msgs);
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID:
        return basicSetSymbID(null, msgs);
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE:
        return basicSetInitialEstimate(null, msgs);
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND:
        return basicSetLowerBound(null, msgs);
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND:
        return basicSetUpperBound(null, msgs);
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
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK:
        return getBlock();
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED:
        return getFixed();
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID:
        return getSymbID();
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE:
        return getInitialEstimate();
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND:
        return getLowerBound();
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND:
        return getUpperBound();
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
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK:
        setBlock((Math_block)newValue);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED:
        setFixed((mSteps_fixed)newValue);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID:
        setSymbID((Math_symbId)newValue);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE:
        setInitialEstimate((mSteps_InitialEstimateType)newValue);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND:
        setLowerBound((mSteps_LowerBoundType)newValue);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND:
        setUpperBound((mSteps_UpperBoundType)newValue);
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
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK:
        setBlock((Math_block)null);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED:
        setFixed((mSteps_fixed)null);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID:
        setSymbID((Math_symbId)null);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE:
        setInitialEstimate((mSteps_InitialEstimateType)null);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND:
        setLowerBound((mSteps_LowerBoundType)null);
        return;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND:
        setUpperBound((mSteps_UpperBoundType)null);
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
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__BLOCK:
        return block != null;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__FIXED:
        return fixed != null;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__SYMB_ID:
        return symbID != null;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__INITIAL_ESTIMATE:
        return initialEstimate != null;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__LOWER_BOUND:
        return lowerBound != null;
      case PharmMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE__UPPER_BOUND:
        return upperBound != null;
    }
    return super.eIsSet(featureID);
  }

} //mSteps_VariableEstimateTypeImpl

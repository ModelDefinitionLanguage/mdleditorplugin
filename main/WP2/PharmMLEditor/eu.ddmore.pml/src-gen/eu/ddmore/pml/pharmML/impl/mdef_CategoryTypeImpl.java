/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_catId;
import eu.ddmore.pml.pharmML.ct_name;
import eu.ddmore.pml.pharmML.mdef_CategoryType;
import eu.ddmore.pml.pharmML.mdef_ProbabilityType;

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
 * An implementation of the model object '<em><b>mdef Category Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CategoryTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CategoryTypeImpl#getCatId <em>Cat Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CategoryTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_CategoryTypeImpl#getProbability <em>Probability</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_CategoryTypeImpl extends MinimalEObjectImpl.Container implements mdef_CategoryType
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
   * The cached value of the '{@link #getCatId() <em>Cat Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCatId()
   * @generated
   * @ordered
   */
  protected ct_catId catId;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected ct_name name;

  /**
   * The cached value of the '{@link #getProbability() <em>Probability</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProbability()
   * @generated
   * @ordered
   */
  protected mdef_ProbabilityType probability;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_CategoryTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmdef_CategoryType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MDEF_CATEGORY_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_catId getCatId()
  {
    return catId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCatId(ct_catId newCatId, NotificationChain msgs)
  {
    ct_catId oldCatId = catId;
    catId = newCatId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CATEGORY_TYPE__CAT_ID, oldCatId, newCatId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCatId(ct_catId newCatId)
  {
    if (newCatId != catId)
    {
      NotificationChain msgs = null;
      if (catId != null)
        msgs = ((InternalEObject)catId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CATEGORY_TYPE__CAT_ID, null, msgs);
      if (newCatId != null)
        msgs = ((InternalEObject)newCatId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CATEGORY_TYPE__CAT_ID, null, msgs);
      msgs = basicSetCatId(newCatId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CATEGORY_TYPE__CAT_ID, newCatId, newCatId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_name getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(ct_name newName, NotificationChain msgs)
  {
    ct_name oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CATEGORY_TYPE__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(ct_name newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CATEGORY_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CATEGORY_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CATEGORY_TYPE__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ProbabilityType getProbability()
  {
    return probability;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetProbability(mdef_ProbabilityType newProbability, NotificationChain msgs)
  {
    mdef_ProbabilityType oldProbability = probability;
    probability = newProbability;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CATEGORY_TYPE__PROBABILITY, oldProbability, newProbability);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProbability(mdef_ProbabilityType newProbability)
  {
    if (newProbability != probability)
    {
      NotificationChain msgs = null;
      if (probability != null)
        msgs = ((InternalEObject)probability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CATEGORY_TYPE__PROBABILITY, null, msgs);
      if (newProbability != null)
        msgs = ((InternalEObject)newProbability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MDEF_CATEGORY_TYPE__PROBABILITY, null, msgs);
      msgs = basicSetProbability(newProbability, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_CATEGORY_TYPE__PROBABILITY, newProbability, newProbability));
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
      case PharmMLPackage.MDEF_CATEGORY_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MDEF_CATEGORY_TYPE__CAT_ID:
        return basicSetCatId(null, msgs);
      case PharmMLPackage.MDEF_CATEGORY_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmMLPackage.MDEF_CATEGORY_TYPE__PROBABILITY:
        return basicSetProbability(null, msgs);
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
      case PharmMLPackage.MDEF_CATEGORY_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MDEF_CATEGORY_TYPE__CAT_ID:
        return getCatId();
      case PharmMLPackage.MDEF_CATEGORY_TYPE__NAME:
        return getName();
      case PharmMLPackage.MDEF_CATEGORY_TYPE__PROBABILITY:
        return getProbability();
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
      case PharmMLPackage.MDEF_CATEGORY_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MDEF_CATEGORY_TYPE__CAT_ID:
        setCatId((ct_catId)newValue);
        return;
      case PharmMLPackage.MDEF_CATEGORY_TYPE__NAME:
        setName((ct_name)newValue);
        return;
      case PharmMLPackage.MDEF_CATEGORY_TYPE__PROBABILITY:
        setProbability((mdef_ProbabilityType)newValue);
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
      case PharmMLPackage.MDEF_CATEGORY_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MDEF_CATEGORY_TYPE__CAT_ID:
        setCatId((ct_catId)null);
        return;
      case PharmMLPackage.MDEF_CATEGORY_TYPE__NAME:
        setName((ct_name)null);
        return;
      case PharmMLPackage.MDEF_CATEGORY_TYPE__PROBABILITY:
        setProbability((mdef_ProbabilityType)null);
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
      case PharmMLPackage.MDEF_CATEGORY_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MDEF_CATEGORY_TYPE__CAT_ID:
        return catId != null;
      case PharmMLPackage.MDEF_CATEGORY_TYPE__NAME:
        return name != null;
      case PharmMLPackage.MDEF_CATEGORY_TYPE__PROBABILITY:
        return probability != null;
    }
    return super.eIsSet(featureID);
  }

} //mdef_CategoryTypeImpl

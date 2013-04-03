/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_Name;
import org.ddmore.pml.pharmaML.ct_symbId;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.design_EndType;
import org.ddmore.pml.pharmaML.design_OccasionType;
import org.ddmore.pml.pharmaML.design_StartType;
import org.ddmore.pml.pharmaML.design_TreatmentEpohType;
import org.ddmore.pml.pharmaML.design_TreatmentRefType;

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
 * An implementation of the model object '<em><b>design Treatment Epoh Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentEpohTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentEpohTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentEpohTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentEpohTypeImpl#getTreatmentRef <em>Treatment Ref</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentEpohTypeImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentEpohTypeImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentEpohTypeImpl#getOccasion <em>Occasion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_TreatmentEpohTypeImpl extends MinimalEObjectImpl.Container implements design_TreatmentEpohType
{
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
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected ct_Name name;

  /**
   * The cached value of the '{@link #getXmlns() <em>Xmlns</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlns()
   * @generated
   * @ordered
   */
  protected EList<ct_xmlns> xmlns;

  /**
   * The cached value of the '{@link #getTreatmentRef() <em>Treatment Ref</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTreatmentRef()
   * @generated
   * @ordered
   */
  protected EList<design_TreatmentRefType> treatmentRef;

  /**
   * The cached value of the '{@link #getStart() <em>Start</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStart()
   * @generated
   * @ordered
   */
  protected design_StartType start;

  /**
   * The cached value of the '{@link #getEnd() <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected design_EndType end;

  /**
   * The cached value of the '{@link #getOccasion() <em>Occasion</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOccasion()
   * @generated
   * @ordered
   */
  protected design_OccasionType occasion;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected design_TreatmentEpohTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getdesign_TreatmentEpohType();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID, oldSymbId, newSymbId);
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
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID, newSymbId, newSymbId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Name getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(ct_Name newName, NotificationChain msgs)
  {
    ct_Name oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(ct_Name newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ct_xmlns> getXmlns()
  {
    if (xmlns == null)
    {
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<design_TreatmentRefType> getTreatmentRef()
  {
    if (treatmentRef == null)
    {
      treatmentRef = new EObjectContainmentEList<design_TreatmentRefType>(design_TreatmentRefType.class, this, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF);
    }
    return treatmentRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_StartType getStart()
  {
    return start;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStart(design_StartType newStart, NotificationChain msgs)
  {
    design_StartType oldStart = start;
    start = newStart;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START, oldStart, newStart);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStart(design_StartType newStart)
  {
    if (newStart != start)
    {
      NotificationChain msgs = null;
      if (start != null)
        msgs = ((InternalEObject)start).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START, null, msgs);
      if (newStart != null)
        msgs = ((InternalEObject)newStart).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START, null, msgs);
      msgs = basicSetStart(newStart, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START, newStart, newStart));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_EndType getEnd()
  {
    return end;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEnd(design_EndType newEnd, NotificationChain msgs)
  {
    design_EndType oldEnd = end;
    end = newEnd;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END, oldEnd, newEnd);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnd(design_EndType newEnd)
  {
    if (newEnd != end)
    {
      NotificationChain msgs = null;
      if (end != null)
        msgs = ((InternalEObject)end).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END, null, msgs);
      if (newEnd != null)
        msgs = ((InternalEObject)newEnd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END, null, msgs);
      msgs = basicSetEnd(newEnd, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END, newEnd, newEnd));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_OccasionType getOccasion()
  {
    return occasion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOccasion(design_OccasionType newOccasion, NotificationChain msgs)
  {
    design_OccasionType oldOccasion = occasion;
    occasion = newOccasion;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION, oldOccasion, newOccasion);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOccasion(design_OccasionType newOccasion)
  {
    if (newOccasion != occasion)
    {
      NotificationChain msgs = null;
      if (occasion != null)
        msgs = ((InternalEObject)occasion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION, null, msgs);
      if (newOccasion != null)
        msgs = ((InternalEObject)newOccasion).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION, null, msgs);
      msgs = basicSetOccasion(newOccasion, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION, newOccasion, newOccasion));
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
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        return ((InternalEList<?>)getTreatmentRef()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        return basicSetStart(null, msgs);
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        return basicSetEnd(null, msgs);
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
        return basicSetOccasion(null, msgs);
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
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID:
        return getSymbId();
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        return getName();
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        return getTreatmentRef();
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        return getStart();
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        return getEnd();
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
        return getOccasion();
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
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID:
        setSymbId((ct_symbId)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        setName((ct_Name)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        getTreatmentRef().clear();
        getTreatmentRef().addAll((Collection<? extends design_TreatmentRefType>)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        setStart((design_StartType)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        setEnd((design_EndType)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
        setOccasion((design_OccasionType)newValue);
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
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID:
        setSymbId((ct_symbId)null);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        setName((ct_Name)null);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        getTreatmentRef().clear();
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        setStart((design_StartType)null);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        setEnd((design_EndType)null);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
        setOccasion((design_OccasionType)null);
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
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__SYMB_ID:
        return symbId != null;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        return name != null;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        return treatmentRef != null && !treatmentRef.isEmpty();
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        return start != null;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        return end != null;
      case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
        return occasion != null;
    }
    return super.eIsSet(featureID);
  }

} //design_TreatmentEpohTypeImpl

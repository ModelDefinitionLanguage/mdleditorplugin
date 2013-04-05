/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_id;
import eu.ddmore.pml.pharmML.ct_name;
import eu.ddmore.pml.pharmML.design_EndType;
import eu.ddmore.pml.pharmML.design_OccasionType;
import eu.ddmore.pml.pharmML.design_StartType;
import eu.ddmore.pml.pharmML.design_TreatmentEpohType;
import eu.ddmore.pml.pharmML.design_TreatmentRefType;

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
 * An implementation of the model object '<em><b>design Treatment Epoh Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentEpohTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentEpohTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentEpohTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentEpohTypeImpl#getTreatmentRef <em>Treatment Ref</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentEpohTypeImpl#getStart <em>Start</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentEpohTypeImpl#getEnd <em>End</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentEpohTypeImpl#getOccasion <em>Occasion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_TreatmentEpohTypeImpl extends MinimalEObjectImpl.Container implements design_TreatmentEpohType
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
   * The cached value of the '{@link #getId() <em>Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected ct_id id;

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
   * The cached value of the '{@link #getOccasion() <em>Occasion</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOccasion()
   * @generated
   * @ordered
   */
  protected EList<design_OccasionType> occasion;

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
    return PharmMLPackage.eINSTANCE.getdesign_TreatmentEpohType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_id getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetId(ct_id newId, NotificationChain msgs)
  {
    ct_id oldId = id;
    id = newId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__ID, oldId, newId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(ct_id newId)
  {
    if (newId != id)
    {
      NotificationChain msgs = null;
      if (id != null)
        msgs = ((InternalEObject)id).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__ID, null, msgs);
      if (newId != null)
        msgs = ((InternalEObject)newId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__ID, null, msgs);
      msgs = basicSetId(newId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__ID, newId, newId));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME, oldName, newName);
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
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME, newName, newName));
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
      treatmentRef = new EObjectContainmentEList<design_TreatmentRefType>(design_TreatmentRefType.class, this, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF);
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START, oldStart, newStart);
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
        msgs = ((InternalEObject)start).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START, null, msgs);
      if (newStart != null)
        msgs = ((InternalEObject)newStart).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START, null, msgs);
      msgs = basicSetStart(newStart, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START, newStart, newStart));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END, oldEnd, newEnd);
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
        msgs = ((InternalEObject)end).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END, null, msgs);
      if (newEnd != null)
        msgs = ((InternalEObject)newEnd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END, null, msgs);
      msgs = basicSetEnd(newEnd, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END, newEnd, newEnd));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<design_OccasionType> getOccasion()
  {
    if (occasion == null)
    {
      occasion = new EObjectContainmentEList<design_OccasionType>(design_OccasionType.class, this, PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION);
    }
    return occasion;
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
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__ID:
        return basicSetId(null, msgs);
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        return ((InternalEList<?>)getTreatmentRef()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        return basicSetStart(null, msgs);
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        return basicSetEnd(null, msgs);
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
        return ((InternalEList<?>)getOccasion()).basicRemove(otherEnd, msgs);
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
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__ID:
        return getId();
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        return getName();
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        return getTreatmentRef();
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        return getStart();
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        return getEnd();
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
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
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__ID:
        setId((ct_id)newValue);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        setName((ct_name)newValue);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        getTreatmentRef().clear();
        getTreatmentRef().addAll((Collection<? extends design_TreatmentRefType>)newValue);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        setStart((design_StartType)newValue);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        setEnd((design_EndType)newValue);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
        getOccasion().clear();
        getOccasion().addAll((Collection<? extends design_OccasionType>)newValue);
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
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__ID:
        setId((ct_id)null);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        setName((ct_name)null);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        getTreatmentRef().clear();
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        setStart((design_StartType)null);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        setEnd((design_EndType)null);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
        getOccasion().clear();
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
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__ID:
        return id != null;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__NAME:
        return name != null;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__TREATMENT_REF:
        return treatmentRef != null && !treatmentRef.isEmpty();
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__START:
        return start != null;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__END:
        return end != null;
      case PharmMLPackage.DESIGN_TREATMENT_EPOH_TYPE__OCCASION:
        return occasion != null && !occasion.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //design_TreatmentEpohTypeImpl

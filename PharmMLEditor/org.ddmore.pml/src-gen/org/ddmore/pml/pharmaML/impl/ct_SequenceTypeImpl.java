/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_Decimal_Value;
import org.ddmore.pml.pharmaML.ct_Int_Value;
import org.ddmore.pml.pharmaML.ct_SequenceType;
import org.ddmore.pml.pharmaML.ct_xmlns;

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
 * An implementation of the model object '<em><b>ct Sequence Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_SequenceTypeImpl#getBegin <em>Begin</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_SequenceTypeImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_SequenceTypeImpl#getRepetition <em>Repetition</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_SequenceTypeImpl#getStepwise <em>Stepwise</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_SequenceTypeImpl#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_SequenceTypeImpl extends MinimalEObjectImpl.Container implements ct_SequenceType
{
  /**
   * The cached value of the '{@link #getBegin() <em>Begin</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBegin()
   * @generated
   * @ordered
   */
  protected ct_Decimal_Value begin;

  /**
   * The cached value of the '{@link #getEnd() <em>End</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected ct_Decimal_Value end;

  /**
   * The cached value of the '{@link #getRepetition() <em>Repetition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRepetition()
   * @generated
   * @ordered
   */
  protected ct_Int_Value repetition;

  /**
   * The cached value of the '{@link #getStepwise() <em>Stepwise</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStepwise()
   * @generated
   * @ordered
   */
  protected ct_Decimal_Value stepwise;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_SequenceTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getct_SequenceType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Decimal_Value getBegin()
  {
    return begin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBegin(ct_Decimal_Value newBegin, NotificationChain msgs)
  {
    ct_Decimal_Value oldBegin = begin;
    begin = newBegin;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SEQUENCE_TYPE__BEGIN, oldBegin, newBegin);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBegin(ct_Decimal_Value newBegin)
  {
    if (newBegin != begin)
    {
      NotificationChain msgs = null;
      if (begin != null)
        msgs = ((InternalEObject)begin).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SEQUENCE_TYPE__BEGIN, null, msgs);
      if (newBegin != null)
        msgs = ((InternalEObject)newBegin).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SEQUENCE_TYPE__BEGIN, null, msgs);
      msgs = basicSetBegin(newBegin, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SEQUENCE_TYPE__BEGIN, newBegin, newBegin));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Decimal_Value getEnd()
  {
    return end;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEnd(ct_Decimal_Value newEnd, NotificationChain msgs)
  {
    ct_Decimal_Value oldEnd = end;
    end = newEnd;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SEQUENCE_TYPE__END, oldEnd, newEnd);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnd(ct_Decimal_Value newEnd)
  {
    if (newEnd != end)
    {
      NotificationChain msgs = null;
      if (end != null)
        msgs = ((InternalEObject)end).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SEQUENCE_TYPE__END, null, msgs);
      if (newEnd != null)
        msgs = ((InternalEObject)newEnd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SEQUENCE_TYPE__END, null, msgs);
      msgs = basicSetEnd(newEnd, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SEQUENCE_TYPE__END, newEnd, newEnd));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Int_Value getRepetition()
  {
    return repetition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRepetition(ct_Int_Value newRepetition, NotificationChain msgs)
  {
    ct_Int_Value oldRepetition = repetition;
    repetition = newRepetition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SEQUENCE_TYPE__REPETITION, oldRepetition, newRepetition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRepetition(ct_Int_Value newRepetition)
  {
    if (newRepetition != repetition)
    {
      NotificationChain msgs = null;
      if (repetition != null)
        msgs = ((InternalEObject)repetition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SEQUENCE_TYPE__REPETITION, null, msgs);
      if (newRepetition != null)
        msgs = ((InternalEObject)newRepetition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SEQUENCE_TYPE__REPETITION, null, msgs);
      msgs = basicSetRepetition(newRepetition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SEQUENCE_TYPE__REPETITION, newRepetition, newRepetition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Decimal_Value getStepwise()
  {
    return stepwise;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStepwise(ct_Decimal_Value newStepwise, NotificationChain msgs)
  {
    ct_Decimal_Value oldStepwise = stepwise;
    stepwise = newStepwise;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SEQUENCE_TYPE__STEPWISE, oldStepwise, newStepwise);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStepwise(ct_Decimal_Value newStepwise)
  {
    if (newStepwise != stepwise)
    {
      NotificationChain msgs = null;
      if (stepwise != null)
        msgs = ((InternalEObject)stepwise).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SEQUENCE_TYPE__STEPWISE, null, msgs);
      if (newStepwise != null)
        msgs = ((InternalEObject)newStepwise).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SEQUENCE_TYPE__STEPWISE, null, msgs);
      msgs = basicSetStepwise(newStepwise, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SEQUENCE_TYPE__STEPWISE, newStepwise, newStepwise));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.CT_SEQUENCE_TYPE__XMLNS);
    }
    return xmlns;
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
      case PharmaMLPackage.CT_SEQUENCE_TYPE__BEGIN:
        return basicSetBegin(null, msgs);
      case PharmaMLPackage.CT_SEQUENCE_TYPE__END:
        return basicSetEnd(null, msgs);
      case PharmaMLPackage.CT_SEQUENCE_TYPE__REPETITION:
        return basicSetRepetition(null, msgs);
      case PharmaMLPackage.CT_SEQUENCE_TYPE__STEPWISE:
        return basicSetStepwise(null, msgs);
      case PharmaMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.CT_SEQUENCE_TYPE__BEGIN:
        return getBegin();
      case PharmaMLPackage.CT_SEQUENCE_TYPE__END:
        return getEnd();
      case PharmaMLPackage.CT_SEQUENCE_TYPE__REPETITION:
        return getRepetition();
      case PharmaMLPackage.CT_SEQUENCE_TYPE__STEPWISE:
        return getStepwise();
      case PharmaMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        return getXmlns();
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
      case PharmaMLPackage.CT_SEQUENCE_TYPE__BEGIN:
        setBegin((ct_Decimal_Value)newValue);
        return;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__END:
        setEnd((ct_Decimal_Value)newValue);
        return;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__REPETITION:
        setRepetition((ct_Int_Value)newValue);
        return;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__STEPWISE:
        setStepwise((ct_Decimal_Value)newValue);
        return;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
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
      case PharmaMLPackage.CT_SEQUENCE_TYPE__BEGIN:
        setBegin((ct_Decimal_Value)null);
        return;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__END:
        setEnd((ct_Decimal_Value)null);
        return;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__REPETITION:
        setRepetition((ct_Int_Value)null);
        return;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__STEPWISE:
        setStepwise((ct_Decimal_Value)null);
        return;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        getXmlns().clear();
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
      case PharmaMLPackage.CT_SEQUENCE_TYPE__BEGIN:
        return begin != null;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__END:
        return end != null;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__REPETITION:
        return repetition != null;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__STEPWISE:
        return stepwise != null;
      case PharmaMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ct_SequenceTypeImpl

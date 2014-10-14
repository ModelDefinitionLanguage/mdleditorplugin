/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.design_GroupTypeContent;
import eu.ddmore.pml.pharmML.design_TreatmentEpochRefType;
import eu.ddmore.pml.pharmML.design_WashoutType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>design Group Type Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_GroupTypeContentImpl#getTreatmentEpochRef <em>Treatment Epoch Ref</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_GroupTypeContentImpl#getWashout <em>Washout</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_GroupTypeContentImpl extends MinimalEObjectImpl.Container implements design_GroupTypeContent
{
  /**
   * The cached value of the '{@link #getTreatmentEpochRef() <em>Treatment Epoch Ref</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTreatmentEpochRef()
   * @generated
   * @ordered
   */
  protected design_TreatmentEpochRefType treatmentEpochRef;

  /**
   * The cached value of the '{@link #getWashout() <em>Washout</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWashout()
   * @generated
   * @ordered
   */
  protected design_WashoutType washout;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected design_GroupTypeContentImpl()
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
    return PharmMLPackage.eINSTANCE.getdesign_GroupTypeContent();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_TreatmentEpochRefType getTreatmentEpochRef()
  {
    return treatmentEpochRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTreatmentEpochRef(design_TreatmentEpochRefType newTreatmentEpochRef, NotificationChain msgs)
  {
    design_TreatmentEpochRefType oldTreatmentEpochRef = treatmentEpochRef;
    treatmentEpochRef = newTreatmentEpochRef;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF, oldTreatmentEpochRef, newTreatmentEpochRef);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTreatmentEpochRef(design_TreatmentEpochRefType newTreatmentEpochRef)
  {
    if (newTreatmentEpochRef != treatmentEpochRef)
    {
      NotificationChain msgs = null;
      if (treatmentEpochRef != null)
        msgs = ((InternalEObject)treatmentEpochRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF, null, msgs);
      if (newTreatmentEpochRef != null)
        msgs = ((InternalEObject)newTreatmentEpochRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF, null, msgs);
      msgs = basicSetTreatmentEpochRef(newTreatmentEpochRef, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF, newTreatmentEpochRef, newTreatmentEpochRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_WashoutType getWashout()
  {
    return washout;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWashout(design_WashoutType newWashout, NotificationChain msgs)
  {
    design_WashoutType oldWashout = washout;
    washout = newWashout;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__WASHOUT, oldWashout, newWashout);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWashout(design_WashoutType newWashout)
  {
    if (newWashout != washout)
    {
      NotificationChain msgs = null;
      if (washout != null)
        msgs = ((InternalEObject)washout).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__WASHOUT, null, msgs);
      if (newWashout != null)
        msgs = ((InternalEObject)newWashout).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__WASHOUT, null, msgs);
      msgs = basicSetWashout(newWashout, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__WASHOUT, newWashout, newWashout));
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
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF:
        return basicSetTreatmentEpochRef(null, msgs);
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__WASHOUT:
        return basicSetWashout(null, msgs);
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
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF:
        return getTreatmentEpochRef();
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__WASHOUT:
        return getWashout();
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
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF:
        setTreatmentEpochRef((design_TreatmentEpochRefType)newValue);
        return;
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__WASHOUT:
        setWashout((design_WashoutType)newValue);
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
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF:
        setTreatmentEpochRef((design_TreatmentEpochRefType)null);
        return;
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__WASHOUT:
        setWashout((design_WashoutType)null);
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
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__TREATMENT_EPOCH_REF:
        return treatmentEpochRef != null;
      case PharmMLPackage.DESIGN_GROUP_TYPE_CONTENT__WASHOUT:
        return washout != null;
    }
    return super.eIsSet(featureID);
  }

} //design_GroupTypeContentImpl

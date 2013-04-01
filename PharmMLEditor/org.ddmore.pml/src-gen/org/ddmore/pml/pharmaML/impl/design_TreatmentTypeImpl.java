/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.Math_symbId;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_Name;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.design_DosingRegimenType;
import org.ddmore.pml.pharmaML.design_TreatmentType;

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
 * An implementation of the model object '<em><b>design Treatment Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentTypeImpl#getDosingRegimen <em>Dosing Regimen</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_TreatmentTypeImpl extends MinimalEObjectImpl.Container implements design_TreatmentType
{
  /**
   * The cached value of the '{@link #getSymbId() <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbId()
   * @generated
   * @ordered
   */
  protected Math_symbId symbId;

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
   * The cached value of the '{@link #getDosingRegimen() <em>Dosing Regimen</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDosingRegimen()
   * @generated
   * @ordered
   */
  protected EList<design_DosingRegimenType> dosingRegimen;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected design_TreatmentTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getdesign_TreatmentType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_symbId getSymbId()
  {
    return symbId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbId(Math_symbId newSymbId, NotificationChain msgs)
  {
    Math_symbId oldSymbId = symbId;
    symbId = newSymbId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_TYPE__SYMB_ID, oldSymbId, newSymbId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbId(Math_symbId newSymbId)
  {
    if (newSymbId != symbId)
    {
      NotificationChain msgs = null;
      if (symbId != null)
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_TYPE__SYMB_ID, newSymbId, newSymbId));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_TYPE__NAME, oldName, newName);
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
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.DESIGN_TREATMENT_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_TYPE__NAME, newName, newName));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.DESIGN_TREATMENT_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<design_DosingRegimenType> getDosingRegimen()
  {
    if (dosingRegimen == null)
    {
      dosingRegimen = new EObjectContainmentEList<design_DosingRegimenType>(design_DosingRegimenType.class, this, PharmaMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN);
    }
    return dosingRegimen;
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
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
        return ((InternalEList<?>)getDosingRegimen()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__SYMB_ID:
        return getSymbId();
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        return getName();
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
        return getDosingRegimen();
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
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__SYMB_ID:
        setSymbId((Math_symbId)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        setName((ct_Name)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
        getDosingRegimen().clear();
        getDosingRegimen().addAll((Collection<? extends design_DosingRegimenType>)newValue);
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
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__SYMB_ID:
        setSymbId((Math_symbId)null);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        setName((ct_Name)null);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
        getDosingRegimen().clear();
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
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__SYMB_ID:
        return symbId != null;
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        return name != null;
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
        return dosingRegimen != null && !dosingRegimen.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //design_TreatmentTypeImpl

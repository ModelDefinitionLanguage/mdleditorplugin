/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_id;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_name;
import eu.ddmore.pml.pharmML.design_DosingRegimenType;
import eu.ddmore.pml.pharmML.design_TreatmentType;

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
 * An implementation of the model object '<em><b>design Treatment Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TreatmentTypeImpl#getDosingRegimen <em>Dosing Regimen</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_TreatmentTypeImpl extends MinimalEObjectImpl.Container implements design_TreatmentType
{
  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected Math_id id;

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
   * The cached value of the '{@link #getXmlns() <em>Xmlns</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlns()
   * @generated
   * @ordered
   */
  protected EList<XS_xmlns> xmlns;

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
    return PharmMLPackage.eINSTANCE.getdesign_TreatmentType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_id getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetId(Math_id newId, NotificationChain msgs)
  {
    Math_id oldId = id;
    id = newId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_TYPE__ID, oldId, newId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(Math_id newId)
  {
    if (newId != id)
    {
      NotificationChain msgs = null;
      if (id != null)
        msgs = ((InternalEObject)id).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_TYPE__ID, null, msgs);
      if (newId != null)
        msgs = ((InternalEObject)newId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_TYPE__ID, null, msgs);
      msgs = basicSetId(newId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_TYPE__ID, newId, newId));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_TYPE__NAME, oldName, newName);
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
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_TREATMENT_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_TREATMENT_TYPE__NAME, newName, newName));
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.DESIGN_TREATMENT_TYPE__XMLNS);
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
      dosingRegimen = new EObjectContainmentEList<design_DosingRegimenType>(design_DosingRegimenType.class, this, PharmMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN);
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
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__ID:
        return basicSetId(null, msgs);
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
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
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__ID:
        return getId();
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        return getName();
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
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
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__ID:
        setId((Math_id)newValue);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        setName((ct_name)newValue);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
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
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__ID:
        setId((Math_id)null);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        setName((ct_name)null);
        return;
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
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
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__ID:
        return id != null;
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__NAME:
        return name != null;
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.DESIGN_TREATMENT_TYPE__DOSING_REGIMEN:
        return dosingRegimen != null && !dosingRegimen.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //design_TreatmentTypeImpl

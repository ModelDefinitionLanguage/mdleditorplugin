/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.design_BolusType;
import eu.ddmore.pml.pharmML.design_DosingRegimenType;
import eu.ddmore.pml.pharmML.design_InfusionType;

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
 * An implementation of the model object '<em><b>design Dosing Regimen Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_DosingRegimenTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_DosingRegimenTypeImpl#getBolus <em>Bolus</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_DosingRegimenTypeImpl#getInfusion <em>Infusion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_DosingRegimenTypeImpl extends MinimalEObjectImpl.Container implements design_DosingRegimenType
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
   * The cached value of the '{@link #getBolus() <em>Bolus</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBolus()
   * @generated
   * @ordered
   */
  protected design_BolusType bolus;

  /**
   * The cached value of the '{@link #getInfusion() <em>Infusion</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInfusion()
   * @generated
   * @ordered
   */
  protected design_InfusionType infusion;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected design_DosingRegimenTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getdesign_DosingRegimenType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_BolusType getBolus()
  {
    return bolus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBolus(design_BolusType newBolus, NotificationChain msgs)
  {
    design_BolusType oldBolus = bolus;
    bolus = newBolus;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__BOLUS, oldBolus, newBolus);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBolus(design_BolusType newBolus)
  {
    if (newBolus != bolus)
    {
      NotificationChain msgs = null;
      if (bolus != null)
        msgs = ((InternalEObject)bolus).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__BOLUS, null, msgs);
      if (newBolus != null)
        msgs = ((InternalEObject)newBolus).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__BOLUS, null, msgs);
      msgs = basicSetBolus(newBolus, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__BOLUS, newBolus, newBolus));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_InfusionType getInfusion()
  {
    return infusion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInfusion(design_InfusionType newInfusion, NotificationChain msgs)
  {
    design_InfusionType oldInfusion = infusion;
    infusion = newInfusion;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__INFUSION, oldInfusion, newInfusion);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInfusion(design_InfusionType newInfusion)
  {
    if (newInfusion != infusion)
    {
      NotificationChain msgs = null;
      if (infusion != null)
        msgs = ((InternalEObject)infusion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__INFUSION, null, msgs);
      if (newInfusion != null)
        msgs = ((InternalEObject)newInfusion).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__INFUSION, null, msgs);
      msgs = basicSetInfusion(newInfusion, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__INFUSION, newInfusion, newInfusion));
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
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__BOLUS:
        return basicSetBolus(null, msgs);
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__INFUSION:
        return basicSetInfusion(null, msgs);
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
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__BOLUS:
        return getBolus();
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__INFUSION:
        return getInfusion();
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
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__BOLUS:
        setBolus((design_BolusType)newValue);
        return;
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__INFUSION:
        setInfusion((design_InfusionType)newValue);
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
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__BOLUS:
        setBolus((design_BolusType)null);
        return;
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__INFUSION:
        setInfusion((design_InfusionType)null);
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
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__BOLUS:
        return bolus != null;
      case PharmMLPackage.DESIGN_DOSING_REGIMEN_TYPE__INFUSION:
        return infusion != null;
    }
    return super.eIsSet(featureID);
  }

} //design_DosingRegimenTypeImpl

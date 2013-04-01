/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_xmlns;
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
 * An implementation of the model object '<em><b>design Treatment Ref Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentRefTypeImpl#getIdRef <em>Id Ref</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.design_TreatmentRefTypeImpl#getXmlns <em>Xmlns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_TreatmentRefTypeImpl extends MinimalEObjectImpl.Container implements design_TreatmentRefType
{
  /**
   * The default value of the '{@link #getIdRef() <em>Id Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdRef()
   * @generated
   * @ordered
   */
  protected static final String ID_REF_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIdRef() <em>Id Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdRef()
   * @generated
   * @ordered
   */
  protected String idRef = ID_REF_EDEFAULT;

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
  protected design_TreatmentRefTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getdesign_TreatmentRefType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIdRef()
  {
    return idRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdRef(String newIdRef)
  {
    String oldIdRef = idRef;
    idRef = newIdRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__ID_REF, oldIdRef, idRef));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__XMLNS);
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
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__XMLNS:
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
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__ID_REF:
        return getIdRef();
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__XMLNS:
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
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__ID_REF:
        setIdRef((String)newValue);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__XMLNS:
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
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__ID_REF:
        setIdRef(ID_REF_EDEFAULT);
        return;
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__XMLNS:
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
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__ID_REF:
        return ID_REF_EDEFAULT == null ? idRef != null : !ID_REF_EDEFAULT.equals(idRef);
      case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (idRef: ");
    result.append(idRef);
    result.append(')');
    return result.toString();
  }

} //design_TreatmentRefTypeImpl

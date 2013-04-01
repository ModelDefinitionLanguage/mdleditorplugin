/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.msteps_MappingType;
import org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType;

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
 * An implementation of the model object '<em><b>msteps Objective Data Set Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ObjectiveDataSetTypeImpl#getDataSetRef <em>Data Set Ref</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ObjectiveDataSetTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ObjectiveDataSetTypeImpl#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_ObjectiveDataSetTypeImpl extends MinimalEObjectImpl.Container implements msteps_ObjectiveDataSetType
{
  /**
   * The default value of the '{@link #getDataSetRef() <em>Data Set Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSetRef()
   * @generated
   * @ordered
   */
  protected static final String DATA_SET_REF_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDataSetRef() <em>Data Set Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSetRef()
   * @generated
   * @ordered
   */
  protected String dataSetRef = DATA_SET_REF_EDEFAULT;

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
   * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMapping()
   * @generated
   * @ordered
   */
  protected EList<msteps_MappingType> mapping;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_ObjectiveDataSetTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmsteps_ObjectiveDataSetType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDataSetRef()
  {
    return dataSetRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataSetRef(String newDataSetRef)
  {
    String oldDataSetRef = dataSetRef;
    dataSetRef = newDataSetRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF, oldDataSetRef, dataSetRef));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_MappingType> getMapping()
  {
    if (mapping == null)
    {
      mapping = new EObjectContainmentEList<msteps_MappingType>(msteps_MappingType.class, this, PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING);
    }
    return mapping;
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
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
        return ((InternalEList<?>)getMapping()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF:
        return getDataSetRef();
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
        return getMapping();
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
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF:
        setDataSetRef((String)newValue);
        return;
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
        getMapping().clear();
        getMapping().addAll((Collection<? extends msteps_MappingType>)newValue);
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
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF:
        setDataSetRef(DATA_SET_REF_EDEFAULT);
        return;
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
        getMapping().clear();
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
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF:
        return DATA_SET_REF_EDEFAULT == null ? dataSetRef != null : !DATA_SET_REF_EDEFAULT.equals(dataSetRef);
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
        return mapping != null && !mapping.isEmpty();
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
    result.append(" (dataSetRef: ");
    result.append(dataSetRef);
    result.append(')');
    return result.toString();
  }

} //msteps_ObjectiveDataSetTypeImpl

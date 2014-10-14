/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.msteps_ColumnMappingType;
import eu.ddmore.pml.pharmML.msteps_ObjectiveDataSetType;

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
 * An implementation of the model object '<em><b>msteps Objective Data Set Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ObjectiveDataSetTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ObjectiveDataSetTypeImpl#getDataSetRef <em>Data Set Ref</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ObjectiveDataSetTypeImpl#getMapping <em>Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_ObjectiveDataSetTypeImpl extends MinimalEObjectImpl.Container implements msteps_ObjectiveDataSetType
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
   * The cached value of the '{@link #getMapping() <em>Mapping</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMapping()
   * @generated
   * @ordered
   */
  protected EList<msteps_ColumnMappingType> mapping;

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
    return PharmMLPackage.eINSTANCE.getmsteps_ObjectiveDataSetType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS);
    }
    return xmlns;
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
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF, oldDataSetRef, dataSetRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_ColumnMappingType> getMapping()
  {
    if (mapping == null)
    {
      mapping = new EObjectContainmentEList<msteps_ColumnMappingType>(msteps_ColumnMappingType.class, this, PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING);
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
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
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
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF:
        return getDataSetRef();
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
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
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF:
        setDataSetRef((String)newValue);
        return;
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
        getMapping().clear();
        getMapping().addAll((Collection<? extends msteps_ColumnMappingType>)newValue);
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
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF:
        setDataSetRef(DATA_SET_REF_EDEFAULT);
        return;
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
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
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__DATA_SET_REF:
        return DATA_SET_REF_EDEFAULT == null ? dataSetRef != null : !DATA_SET_REF_EDEFAULT.equals(dataSetRef);
      case PharmMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE__MAPPING:
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

/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.mdef_ImportLinkType;
import org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType;
import org.ddmore.pml.pharmaML.mdef_ImportType;

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
 * An implementation of the model object '<em><b>mdef Import Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ImportTypeImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ImportTypeImpl#getResourceType <em>Resource Type</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ImportTypeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ImportTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_ImportTypeImpl#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_ImportTypeImpl extends MinimalEObjectImpl.Container implements mdef_ImportType
{
  /**
   * The default value of the '{@link #getResource() <em>Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResource()
   * @generated
   * @ordered
   */
  protected static final String RESOURCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getResource() <em>Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResource()
   * @generated
   * @ordered
   */
  protected String resource = RESOURCE_EDEFAULT;

  /**
   * The cached value of the '{@link #getResourceType() <em>Resource Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResourceType()
   * @generated
   * @ordered
   */
  protected mdef_ImportResourceTypeType resourceType;

  /**
   * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected static final String SOURCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected String source = SOURCE_EDEFAULT;

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
   * The cached value of the '{@link #getLink() <em>Link</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLink()
   * @generated
   * @ordered
   */
  protected EList<mdef_ImportLinkType> link;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_ImportTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmdef_ImportType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getResource()
  {
    return resource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResource(String newResource)
  {
    String oldResource = resource;
    resource = newResource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE, oldResource, resource));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ImportResourceTypeType getResourceType()
  {
    return resourceType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetResourceType(mdef_ImportResourceTypeType newResourceType, NotificationChain msgs)
  {
    mdef_ImportResourceTypeType oldResourceType = resourceType;
    resourceType = newResourceType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE_TYPE, oldResourceType, newResourceType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResourceType(mdef_ImportResourceTypeType newResourceType)
  {
    if (newResourceType != resourceType)
    {
      NotificationChain msgs = null;
      if (resourceType != null)
        msgs = ((InternalEObject)resourceType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE_TYPE, null, msgs);
      if (newResourceType != null)
        msgs = ((InternalEObject)newResourceType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE_TYPE, null, msgs);
      msgs = basicSetResourceType(newResourceType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE_TYPE, newResourceType, newResourceType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSource()
  {
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSource(String newSource)
  {
    String oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_IMPORT_TYPE__SOURCE, oldSource, source));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MDEF_IMPORT_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_ImportLinkType> getLink()
  {
    if (link == null)
    {
      link = new EObjectContainmentEList<mdef_ImportLinkType>(mdef_ImportLinkType.class, this, PharmaMLPackage.MDEF_IMPORT_TYPE__LINK);
    }
    return link;
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
      case PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE_TYPE:
        return basicSetResourceType(null, msgs);
      case PharmaMLPackage.MDEF_IMPORT_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_IMPORT_TYPE__LINK:
        return ((InternalEList<?>)getLink()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE:
        return getResource();
      case PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE_TYPE:
        return getResourceType();
      case PharmaMLPackage.MDEF_IMPORT_TYPE__SOURCE:
        return getSource();
      case PharmaMLPackage.MDEF_IMPORT_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MDEF_IMPORT_TYPE__LINK:
        return getLink();
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
      case PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE:
        setResource((String)newValue);
        return;
      case PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE_TYPE:
        setResourceType((mdef_ImportResourceTypeType)newValue);
        return;
      case PharmaMLPackage.MDEF_IMPORT_TYPE__SOURCE:
        setSource((String)newValue);
        return;
      case PharmaMLPackage.MDEF_IMPORT_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MDEF_IMPORT_TYPE__LINK:
        getLink().clear();
        getLink().addAll((Collection<? extends mdef_ImportLinkType>)newValue);
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
      case PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE:
        setResource(RESOURCE_EDEFAULT);
        return;
      case PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE_TYPE:
        setResourceType((mdef_ImportResourceTypeType)null);
        return;
      case PharmaMLPackage.MDEF_IMPORT_TYPE__SOURCE:
        setSource(SOURCE_EDEFAULT);
        return;
      case PharmaMLPackage.MDEF_IMPORT_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MDEF_IMPORT_TYPE__LINK:
        getLink().clear();
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
      case PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE:
        return RESOURCE_EDEFAULT == null ? resource != null : !RESOURCE_EDEFAULT.equals(resource);
      case PharmaMLPackage.MDEF_IMPORT_TYPE__RESOURCE_TYPE:
        return resourceType != null;
      case PharmaMLPackage.MDEF_IMPORT_TYPE__SOURCE:
        return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
      case PharmaMLPackage.MDEF_IMPORT_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MDEF_IMPORT_TYPE__LINK:
        return link != null && !link.isEmpty();
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
    result.append(" (resource: ");
    result.append(resource);
    result.append(", source: ");
    result.append(source);
    result.append(')');
    return result.toString();
  }

} //mdef_ImportTypeImpl

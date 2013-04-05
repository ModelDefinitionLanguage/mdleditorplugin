/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_DataSetType;
import eu.ddmore.pml.pharmML.ct_DefinitionType;
import eu.ddmore.pml.pharmML.ct_ExternalSourceType;
import eu.ddmore.pml.pharmML.ct_InternalSourceType;
import eu.ddmore.pml.pharmML.ct_RowType;

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
 * An implementation of the model object '<em><b>ct Data Set Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_DataSetTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_DataSetTypeImpl#getDefinition <em>Definition</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_DataSetTypeImpl#getExternalSource <em>External Source</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_DataSetTypeImpl#getRow <em>Row</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_DataSetTypeImpl#getInternalSource <em>Internal Source</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_DataSetTypeImpl extends MinimalEObjectImpl.Container implements ct_DataSetType
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
   * The cached value of the '{@link #getDefinition() <em>Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefinition()
   * @generated
   * @ordered
   */
  protected ct_DefinitionType definition;

  /**
   * The cached value of the '{@link #getExternalSource() <em>External Source</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExternalSource()
   * @generated
   * @ordered
   */
  protected EList<ct_ExternalSourceType> externalSource;

  /**
   * The cached value of the '{@link #getRow() <em>Row</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRow()
   * @generated
   * @ordered
   */
  protected EList<ct_RowType> row;

  /**
   * The cached value of the '{@link #getInternalSource() <em>Internal Source</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInternalSource()
   * @generated
   * @ordered
   */
  protected EList<ct_InternalSourceType> internalSource;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_DataSetTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getct_DataSetType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.CT_DATA_SET_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_DefinitionType getDefinition()
  {
    return definition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDefinition(ct_DefinitionType newDefinition, NotificationChain msgs)
  {
    ct_DefinitionType oldDefinition = definition;
    definition = newDefinition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_DATA_SET_TYPE__DEFINITION, oldDefinition, newDefinition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDefinition(ct_DefinitionType newDefinition)
  {
    if (newDefinition != definition)
    {
      NotificationChain msgs = null;
      if (definition != null)
        msgs = ((InternalEObject)definition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_DATA_SET_TYPE__DEFINITION, null, msgs);
      if (newDefinition != null)
        msgs = ((InternalEObject)newDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_DATA_SET_TYPE__DEFINITION, null, msgs);
      msgs = basicSetDefinition(newDefinition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_DATA_SET_TYPE__DEFINITION, newDefinition, newDefinition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ct_ExternalSourceType> getExternalSource()
  {
    if (externalSource == null)
    {
      externalSource = new EObjectContainmentEList<ct_ExternalSourceType>(ct_ExternalSourceType.class, this, PharmMLPackage.CT_DATA_SET_TYPE__EXTERNAL_SOURCE);
    }
    return externalSource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ct_RowType> getRow()
  {
    if (row == null)
    {
      row = new EObjectContainmentEList<ct_RowType>(ct_RowType.class, this, PharmMLPackage.CT_DATA_SET_TYPE__ROW);
    }
    return row;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ct_InternalSourceType> getInternalSource()
  {
    if (internalSource == null)
    {
      internalSource = new EObjectContainmentEList<ct_InternalSourceType>(ct_InternalSourceType.class, this, PharmMLPackage.CT_DATA_SET_TYPE__INTERNAL_SOURCE);
    }
    return internalSource;
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
      case PharmMLPackage.CT_DATA_SET_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.CT_DATA_SET_TYPE__DEFINITION:
        return basicSetDefinition(null, msgs);
      case PharmMLPackage.CT_DATA_SET_TYPE__EXTERNAL_SOURCE:
        return ((InternalEList<?>)getExternalSource()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.CT_DATA_SET_TYPE__ROW:
        return ((InternalEList<?>)getRow()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.CT_DATA_SET_TYPE__INTERNAL_SOURCE:
        return ((InternalEList<?>)getInternalSource()).basicRemove(otherEnd, msgs);
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
      case PharmMLPackage.CT_DATA_SET_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.CT_DATA_SET_TYPE__DEFINITION:
        return getDefinition();
      case PharmMLPackage.CT_DATA_SET_TYPE__EXTERNAL_SOURCE:
        return getExternalSource();
      case PharmMLPackage.CT_DATA_SET_TYPE__ROW:
        return getRow();
      case PharmMLPackage.CT_DATA_SET_TYPE__INTERNAL_SOURCE:
        return getInternalSource();
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
      case PharmMLPackage.CT_DATA_SET_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.CT_DATA_SET_TYPE__DEFINITION:
        setDefinition((ct_DefinitionType)newValue);
        return;
      case PharmMLPackage.CT_DATA_SET_TYPE__EXTERNAL_SOURCE:
        getExternalSource().clear();
        getExternalSource().addAll((Collection<? extends ct_ExternalSourceType>)newValue);
        return;
      case PharmMLPackage.CT_DATA_SET_TYPE__ROW:
        getRow().clear();
        getRow().addAll((Collection<? extends ct_RowType>)newValue);
        return;
      case PharmMLPackage.CT_DATA_SET_TYPE__INTERNAL_SOURCE:
        getInternalSource().clear();
        getInternalSource().addAll((Collection<? extends ct_InternalSourceType>)newValue);
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
      case PharmMLPackage.CT_DATA_SET_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.CT_DATA_SET_TYPE__DEFINITION:
        setDefinition((ct_DefinitionType)null);
        return;
      case PharmMLPackage.CT_DATA_SET_TYPE__EXTERNAL_SOURCE:
        getExternalSource().clear();
        return;
      case PharmMLPackage.CT_DATA_SET_TYPE__ROW:
        getRow().clear();
        return;
      case PharmMLPackage.CT_DATA_SET_TYPE__INTERNAL_SOURCE:
        getInternalSource().clear();
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
      case PharmMLPackage.CT_DATA_SET_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.CT_DATA_SET_TYPE__DEFINITION:
        return definition != null;
      case PharmMLPackage.CT_DATA_SET_TYPE__EXTERNAL_SOURCE:
        return externalSource != null && !externalSource.isEmpty();
      case PharmMLPackage.CT_DATA_SET_TYPE__ROW:
        return row != null && !row.isEmpty();
      case PharmMLPackage.CT_DATA_SET_TYPE__INTERNAL_SOURCE:
        return internalSource != null && !internalSource.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ct_DataSetTypeImpl

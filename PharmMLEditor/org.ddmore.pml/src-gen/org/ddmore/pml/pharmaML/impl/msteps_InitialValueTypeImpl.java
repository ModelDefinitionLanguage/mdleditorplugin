/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.Math_symbId;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_VariableAssignmentType;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.msteps_InitialValueType;

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
 * An implementation of the model object '<em><b>msteps Initial Value Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_InitialValueTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_InitialValueTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_InitialValueTypeImpl#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_InitialValueTypeImpl extends MinimalEObjectImpl.Container implements msteps_InitialValueType
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
   * The cached value of the '{@link #getXmlns() <em>Xmlns</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlns()
   * @generated
   * @ordered
   */
  protected EList<ct_xmlns> xmlns;

  /**
   * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContent()
   * @generated
   * @ordered
   */
  protected ct_VariableAssignmentType content;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_InitialValueTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmsteps_InitialValueType();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID, oldSymbId, newSymbId);
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
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID, newSymbId, newSymbId));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_VariableAssignmentType getContent()
  {
    return content;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContent(ct_VariableAssignmentType newContent, NotificationChain msgs)
  {
    ct_VariableAssignmentType oldContent = content;
    content = newContent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__CONTENT, oldContent, newContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContent(ct_VariableAssignmentType newContent)
  {
    if (newContent != content)
    {
      NotificationChain msgs = null;
      if (content != null)
        msgs = ((InternalEObject)content).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__CONTENT, null, msgs);
      if (newContent != null)
        msgs = ((InternalEObject)newContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__CONTENT, null, msgs);
      msgs = basicSetContent(newContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__CONTENT, newContent, newContent));
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
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__CONTENT:
        return basicSetContent(null, msgs);
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
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        return getSymbId();
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__CONTENT:
        return getContent();
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
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        setSymbId((Math_symbId)newValue);
        return;
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__CONTENT:
        setContent((ct_VariableAssignmentType)newValue);
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
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        setSymbId((Math_symbId)null);
        return;
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__CONTENT:
        setContent((ct_VariableAssignmentType)null);
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
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__SYMB_ID:
        return symbId != null;
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE__CONTENT:
        return content != null;
    }
    return super.eIsSet(featureID);
  }

} //msteps_InitialValueTypeImpl

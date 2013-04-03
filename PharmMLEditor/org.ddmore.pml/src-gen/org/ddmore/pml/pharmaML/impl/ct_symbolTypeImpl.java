/**
 */
package org.ddmore.pml.pharmaML.impl;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_SymbolTypeType;
import org.ddmore.pml.pharmaML.ct_symbolType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ct symbol Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_symbolTypeImpl#getSymbolType <em>Symbol Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_symbolTypeImpl extends MinimalEObjectImpl.Container implements ct_symbolType
{
  /**
   * The cached value of the '{@link #getSymbolType() <em>Symbol Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbolType()
   * @generated
   * @ordered
   */
  protected ct_SymbolTypeType symbolType;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_symbolTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getct_symbolType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_SymbolTypeType getSymbolType()
  {
    return symbolType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbolType(ct_SymbolTypeType newSymbolType, NotificationChain msgs)
  {
    ct_SymbolTypeType oldSymbolType = symbolType;
    symbolType = newSymbolType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SYMBOL_TYPE__SYMBOL_TYPE, oldSymbolType, newSymbolType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbolType(ct_SymbolTypeType newSymbolType)
  {
    if (newSymbolType != symbolType)
    {
      NotificationChain msgs = null;
      if (symbolType != null)
        msgs = ((InternalEObject)symbolType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SYMBOL_TYPE__SYMBOL_TYPE, null, msgs);
      if (newSymbolType != null)
        msgs = ((InternalEObject)newSymbolType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.CT_SYMBOL_TYPE__SYMBOL_TYPE, null, msgs);
      msgs = basicSetSymbolType(newSymbolType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SYMBOL_TYPE__SYMBOL_TYPE, newSymbolType, newSymbolType));
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
      case PharmaMLPackage.CT_SYMBOL_TYPE__SYMBOL_TYPE:
        return basicSetSymbolType(null, msgs);
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
      case PharmaMLPackage.CT_SYMBOL_TYPE__SYMBOL_TYPE:
        return getSymbolType();
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
      case PharmaMLPackage.CT_SYMBOL_TYPE__SYMBOL_TYPE:
        setSymbolType((ct_SymbolTypeType)newValue);
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
      case PharmaMLPackage.CT_SYMBOL_TYPE__SYMBOL_TYPE:
        setSymbolType((ct_SymbolTypeType)null);
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
      case PharmaMLPackage.CT_SYMBOL_TYPE__SYMBOL_TYPE:
        return symbolType != null;
    }
    return super.eIsSet(featureID);
  }

} //ct_symbolTypeImpl

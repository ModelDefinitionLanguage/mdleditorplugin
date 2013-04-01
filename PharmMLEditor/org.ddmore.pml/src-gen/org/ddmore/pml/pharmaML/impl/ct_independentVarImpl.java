/**
 */
package org.ddmore.pml.pharmaML.impl;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_independentVar;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ct independent Var</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_independentVarImpl#getIndependentVar <em>Independent Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_independentVarImpl extends MinimalEObjectImpl.Container implements ct_independentVar
{
  /**
   * The default value of the '{@link #getIndependentVar() <em>Independent Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndependentVar()
   * @generated
   * @ordered
   */
  protected static final String INDEPENDENT_VAR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIndependentVar() <em>Independent Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndependentVar()
   * @generated
   * @ordered
   */
  protected String independentVar = INDEPENDENT_VAR_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_independentVarImpl()
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
    return PharmaMLPackage.eINSTANCE.getct_independentVar();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIndependentVar()
  {
    return independentVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIndependentVar(String newIndependentVar)
  {
    String oldIndependentVar = independentVar;
    independentVar = newIndependentVar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_INDEPENDENT_VAR__INDEPENDENT_VAR, oldIndependentVar, independentVar));
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
      case PharmaMLPackage.CT_INDEPENDENT_VAR__INDEPENDENT_VAR:
        return getIndependentVar();
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
      case PharmaMLPackage.CT_INDEPENDENT_VAR__INDEPENDENT_VAR:
        setIndependentVar((String)newValue);
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
      case PharmaMLPackage.CT_INDEPENDENT_VAR__INDEPENDENT_VAR:
        setIndependentVar(INDEPENDENT_VAR_EDEFAULT);
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
      case PharmaMLPackage.CT_INDEPENDENT_VAR__INDEPENDENT_VAR:
        return INDEPENDENT_VAR_EDEFAULT == null ? independentVar != null : !INDEPENDENT_VAR_EDEFAULT.equals(independentVar);
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
    result.append(" (independentVar: ");
    result.append(independentVar);
    result.append(')');
    return result.toString();
  }

} //ct_independentVarImpl

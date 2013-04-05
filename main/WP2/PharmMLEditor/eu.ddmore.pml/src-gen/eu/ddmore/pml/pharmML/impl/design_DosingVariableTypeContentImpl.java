/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.design_DoseVarType;
import eu.ddmore.pml.pharmML.design_DosingVariableTypeContent;
import eu.ddmore.pml.pharmML.design_TargetVarType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>design Dosing Variable Type Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_DosingVariableTypeContentImpl#getDoseVar <em>Dose Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_DosingVariableTypeContentImpl#getTargetVar <em>Target Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_DosingVariableTypeContentImpl extends MinimalEObjectImpl.Container implements design_DosingVariableTypeContent
{
  /**
   * The cached value of the '{@link #getDoseVar() <em>Dose Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDoseVar()
   * @generated
   * @ordered
   */
  protected design_DoseVarType doseVar;

  /**
   * The cached value of the '{@link #getTargetVar() <em>Target Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetVar()
   * @generated
   * @ordered
   */
  protected design_TargetVarType targetVar;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected design_DosingVariableTypeContentImpl()
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
    return PharmMLPackage.eINSTANCE.getdesign_DosingVariableTypeContent();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_DoseVarType getDoseVar()
  {
    return doseVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDoseVar(design_DoseVarType newDoseVar, NotificationChain msgs)
  {
    design_DoseVarType oldDoseVar = doseVar;
    doseVar = newDoseVar;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR, oldDoseVar, newDoseVar);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDoseVar(design_DoseVarType newDoseVar)
  {
    if (newDoseVar != doseVar)
    {
      NotificationChain msgs = null;
      if (doseVar != null)
        msgs = ((InternalEObject)doseVar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR, null, msgs);
      if (newDoseVar != null)
        msgs = ((InternalEObject)newDoseVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR, null, msgs);
      msgs = basicSetDoseVar(newDoseVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR, newDoseVar, newDoseVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_TargetVarType getTargetVar()
  {
    return targetVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTargetVar(design_TargetVarType newTargetVar, NotificationChain msgs)
  {
    design_TargetVarType oldTargetVar = targetVar;
    targetVar = newTargetVar;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR, oldTargetVar, newTargetVar);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetVar(design_TargetVarType newTargetVar)
  {
    if (newTargetVar != targetVar)
    {
      NotificationChain msgs = null;
      if (targetVar != null)
        msgs = ((InternalEObject)targetVar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR, null, msgs);
      if (newTargetVar != null)
        msgs = ((InternalEObject)newTargetVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR, null, msgs);
      msgs = basicSetTargetVar(newTargetVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR, newTargetVar, newTargetVar));
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
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR:
        return basicSetDoseVar(null, msgs);
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR:
        return basicSetTargetVar(null, msgs);
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
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR:
        return getDoseVar();
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR:
        return getTargetVar();
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
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR:
        setDoseVar((design_DoseVarType)newValue);
        return;
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR:
        setTargetVar((design_TargetVarType)newValue);
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
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR:
        setDoseVar((design_DoseVarType)null);
        return;
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR:
        setTargetVar((design_TargetVarType)null);
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
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__DOSE_VAR:
        return doseVar != null;
      case PharmMLPackage.DESIGN_DOSING_VARIABLE_TYPE_CONTENT__TARGET_VAR:
        return targetVar != null;
    }
    return super.eIsSet(featureID);
  }

} //design_DosingVariableTypeContentImpl

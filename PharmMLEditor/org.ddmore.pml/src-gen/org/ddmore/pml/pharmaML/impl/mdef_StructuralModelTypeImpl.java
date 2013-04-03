/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_AnnotationType;
import org.ddmore.pml.pharmaML.ct_VariableDefinitionType;
import org.ddmore.pml.pharmaML.ct_id;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.mdef_ImportType;
import org.ddmore.pml.pharmaML.mdef_InitialConditionType;
import org.ddmore.pml.pharmaML.mdef_ParameterType;
import org.ddmore.pml.pharmaML.mdef_StructuralModelType;

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
 * An implementation of the model object '<em><b>mdef Structural Model Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_StructuralModelTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_StructuralModelTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_StructuralModelTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_StructuralModelTypeImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_StructuralModelTypeImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_StructuralModelTypeImpl#getImport <em>Import</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.mdef_StructuralModelTypeImpl#getInitiaCondition <em>Initia Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_StructuralModelTypeImpl extends MinimalEObjectImpl.Container implements mdef_StructuralModelType
{
  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected ct_id id;

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
   * The cached value of the '{@link #getDescription() <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDescription()
   * @generated
   * @ordered
   */
  protected ct_AnnotationType description;

  /**
   * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameter()
   * @generated
   * @ordered
   */
  protected EList<mdef_ParameterType> parameter;

  /**
   * The cached value of the '{@link #getVariable() <em>Variable</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariable()
   * @generated
   * @ordered
   */
  protected EList<ct_VariableDefinitionType> variable;

  /**
   * The cached value of the '{@link #getImport() <em>Import</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImport()
   * @generated
   * @ordered
   */
  protected EList<mdef_ImportType> import_;

  /**
   * The cached value of the '{@link #getInitiaCondition() <em>Initia Condition</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitiaCondition()
   * @generated
   * @ordered
   */
  protected EList<mdef_InitialConditionType> initiaCondition;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_StructuralModelTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmdef_StructuralModelType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_id getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetId(ct_id newId, NotificationChain msgs)
  {
    ct_id oldId = id;
    id = newId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__ID, oldId, newId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(ct_id newId)
  {
    if (newId != id)
    {
      NotificationChain msgs = null;
      if (id != null)
        msgs = ((InternalEObject)id).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__ID, null, msgs);
      if (newId != null)
        msgs = ((InternalEObject)newId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__ID, null, msgs);
      msgs = basicSetId(newId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__ID, newId, newId));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_AnnotationType getDescription()
  {
    return description;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDescription(ct_AnnotationType newDescription, NotificationChain msgs)
  {
    ct_AnnotationType oldDescription = description;
    description = newDescription;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION, oldDescription, newDescription);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDescription(ct_AnnotationType newDescription)
  {
    if (newDescription != description)
    {
      NotificationChain msgs = null;
      if (description != null)
        msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION, null, msgs);
      if (newDescription != null)
        msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION, null, msgs);
      msgs = basicSetDescription(newDescription, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION, newDescription, newDescription));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_ParameterType> getParameter()
  {
    if (parameter == null)
    {
      parameter = new EObjectContainmentEList<mdef_ParameterType>(mdef_ParameterType.class, this, PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__PARAMETER);
    }
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ct_VariableDefinitionType> getVariable()
  {
    if (variable == null)
    {
      variable = new EObjectContainmentEList<ct_VariableDefinitionType>(ct_VariableDefinitionType.class, this, PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__VARIABLE);
    }
    return variable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_ImportType> getImport()
  {
    if (import_ == null)
    {
      import_ = new EObjectContainmentEList<mdef_ImportType>(mdef_ImportType.class, this, PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__IMPORT);
    }
    return import_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_InitialConditionType> getInitiaCondition()
  {
    if (initiaCondition == null)
    {
      initiaCondition = new EObjectContainmentEList<mdef_InitialConditionType>(mdef_InitialConditionType.class, this, PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__INITIA_CONDITION);
    }
    return initiaCondition;
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
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__ID:
        return basicSetId(null, msgs);
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION:
        return basicSetDescription(null, msgs);
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__PARAMETER:
        return ((InternalEList<?>)getParameter()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__VARIABLE:
        return ((InternalEList<?>)getVariable()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__IMPORT:
        return ((InternalEList<?>)getImport()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__INITIA_CONDITION:
        return ((InternalEList<?>)getInitiaCondition()).basicRemove(otherEnd, msgs);
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
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__ID:
        return getId();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION:
        return getDescription();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__PARAMETER:
        return getParameter();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__VARIABLE:
        return getVariable();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__IMPORT:
        return getImport();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__INITIA_CONDITION:
        return getInitiaCondition();
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
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__ID:
        setId((ct_id)newValue);
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION:
        setDescription((ct_AnnotationType)newValue);
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__PARAMETER:
        getParameter().clear();
        getParameter().addAll((Collection<? extends mdef_ParameterType>)newValue);
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__VARIABLE:
        getVariable().clear();
        getVariable().addAll((Collection<? extends ct_VariableDefinitionType>)newValue);
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__IMPORT:
        getImport().clear();
        getImport().addAll((Collection<? extends mdef_ImportType>)newValue);
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__INITIA_CONDITION:
        getInitiaCondition().clear();
        getInitiaCondition().addAll((Collection<? extends mdef_InitialConditionType>)newValue);
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
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__ID:
        setId((ct_id)null);
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION:
        setDescription((ct_AnnotationType)null);
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__PARAMETER:
        getParameter().clear();
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__VARIABLE:
        getVariable().clear();
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__IMPORT:
        getImport().clear();
        return;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__INITIA_CONDITION:
        getInitiaCondition().clear();
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
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__ID:
        return id != null;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__DESCRIPTION:
        return description != null;
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__PARAMETER:
        return parameter != null && !parameter.isEmpty();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__VARIABLE:
        return variable != null && !variable.isEmpty();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__IMPORT:
        return import_ != null && !import_.isEmpty();
      case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE__INITIA_CONDITION:
        return initiaCondition != null && !initiaCondition.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //mdef_StructuralModelTypeImpl

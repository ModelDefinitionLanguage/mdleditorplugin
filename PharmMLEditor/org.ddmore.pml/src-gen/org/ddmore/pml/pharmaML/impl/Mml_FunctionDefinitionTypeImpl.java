/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType;
import org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_DefinitionRhsType;
import org.ddmore.pml.pharmaML.ct_xmlns;

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
 * An implementation of the model object '<em><b>Mml Function Definition Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_FunctionDefinitionTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_FunctionDefinitionTypeImpl#getFunctionArguments <em>Function Arguments</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_FunctionDefinitionTypeImpl#getDefinition <em>Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Mml_FunctionDefinitionTypeImpl extends MinimalEObjectImpl.Container implements Mml_FunctionDefinitionType
{
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
   * The cached value of the '{@link #getFunctionArguments() <em>Function Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionArguments()
   * @generated
   * @ordered
   */
  protected EList<Mml_FuncParameterDefinitionType> functionArguments;

  /**
   * The cached value of the '{@link #getDefinition() <em>Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefinition()
   * @generated
   * @ordered
   */
  protected ct_DefinitionRhsType definition;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Mml_FunctionDefinitionTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getMml_FunctionDefinitionType();
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Mml_FuncParameterDefinitionType> getFunctionArguments()
  {
    if (functionArguments == null)
    {
      functionArguments = new EObjectContainmentEList<Mml_FuncParameterDefinitionType>(Mml_FuncParameterDefinitionType.class, this, PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__FUNCTION_ARGUMENTS);
    }
    return functionArguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_DefinitionRhsType getDefinition()
  {
    return definition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDefinition(ct_DefinitionRhsType newDefinition, NotificationChain msgs)
  {
    ct_DefinitionRhsType oldDefinition = definition;
    definition = newDefinition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__DEFINITION, oldDefinition, newDefinition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDefinition(ct_DefinitionRhsType newDefinition)
  {
    if (newDefinition != definition)
    {
      NotificationChain msgs = null;
      if (definition != null)
        msgs = ((InternalEObject)definition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__DEFINITION, null, msgs);
      if (newDefinition != null)
        msgs = ((InternalEObject)newDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__DEFINITION, null, msgs);
      msgs = basicSetDefinition(newDefinition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__DEFINITION, newDefinition, newDefinition));
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
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__FUNCTION_ARGUMENTS:
        return ((InternalEList<?>)getFunctionArguments()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__DEFINITION:
        return basicSetDefinition(null, msgs);
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
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__FUNCTION_ARGUMENTS:
        return getFunctionArguments();
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__DEFINITION:
        return getDefinition();
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
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__FUNCTION_ARGUMENTS:
        getFunctionArguments().clear();
        getFunctionArguments().addAll((Collection<? extends Mml_FuncParameterDefinitionType>)newValue);
        return;
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__DEFINITION:
        setDefinition((ct_DefinitionRhsType)newValue);
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
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__FUNCTION_ARGUMENTS:
        getFunctionArguments().clear();
        return;
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__DEFINITION:
        setDefinition((ct_DefinitionRhsType)null);
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
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__FUNCTION_ARGUMENTS:
        return functionArguments != null && !functionArguments.isEmpty();
      case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE__DEFINITION:
        return definition != null;
    }
    return super.eIsSet(featureID);
  }

} //Mml_FunctionDefinitionTypeImpl

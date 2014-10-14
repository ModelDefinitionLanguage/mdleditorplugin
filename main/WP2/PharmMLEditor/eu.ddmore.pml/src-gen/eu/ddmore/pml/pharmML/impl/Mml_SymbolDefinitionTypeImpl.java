/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Mml_FunctionDefinitionType;
import eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_AnnotationType;
import eu.ddmore.pml.pharmML.ct_name;
import eu.ddmore.pml.pharmML.ct_symbId;
import eu.ddmore.pml.pharmML.ct_symbolType;

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
 * An implementation of the model object '<em><b>Mml Symbol Definition Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_SymbolDefinitionTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_SymbolDefinitionTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_SymbolDefinitionTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_SymbolDefinitionTypeImpl#getSymbolType <em>Symbol Type</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_SymbolDefinitionTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_SymbolDefinitionTypeImpl#getFunctionDefinition <em>Function Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Mml_SymbolDefinitionTypeImpl extends MinimalEObjectImpl.Container implements Mml_SymbolDefinitionType
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
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected ct_name name;

  /**
   * The cached value of the '{@link #getSymbId() <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbId()
   * @generated
   * @ordered
   */
  protected ct_symbId symbId;

  /**
   * The cached value of the '{@link #getSymbolType() <em>Symbol Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbolType()
   * @generated
   * @ordered
   */
  protected ct_symbolType symbolType;

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
   * The cached value of the '{@link #getFunctionDefinition() <em>Function Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionDefinition()
   * @generated
   * @ordered
   */
  protected Mml_FunctionDefinitionType functionDefinition;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Mml_SymbolDefinitionTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getMml_SymbolDefinitionType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_name getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(ct_name newName, NotificationChain msgs)
  {
    ct_name oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(ct_name newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_symbId getSymbId()
  {
    return symbId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbId(ct_symbId newSymbId, NotificationChain msgs)
  {
    ct_symbId oldSymbId = symbId;
    symbId = newSymbId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMB_ID, oldSymbId, newSymbId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbId(ct_symbId newSymbId)
  {
    if (newSymbId != symbId)
    {
      NotificationChain msgs = null;
      if (symbId != null)
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMB_ID, newSymbId, newSymbId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_symbolType getSymbolType()
  {
    return symbolType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbolType(ct_symbolType newSymbolType, NotificationChain msgs)
  {
    ct_symbolType oldSymbolType = symbolType;
    symbolType = newSymbolType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE, oldSymbolType, newSymbolType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbolType(ct_symbolType newSymbolType)
  {
    if (newSymbolType != symbolType)
    {
      NotificationChain msgs = null;
      if (symbolType != null)
        msgs = ((InternalEObject)symbolType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE, null, msgs);
      if (newSymbolType != null)
        msgs = ((InternalEObject)newSymbolType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE, null, msgs);
      msgs = basicSetSymbolType(newSymbolType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE, newSymbolType, newSymbolType));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION, oldDescription, newDescription);
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
        msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION, null, msgs);
      if (newDescription != null)
        msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION, null, msgs);
      msgs = basicSetDescription(newDescription, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION, newDescription, newDescription));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mml_FunctionDefinitionType getFunctionDefinition()
  {
    return functionDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunctionDefinition(Mml_FunctionDefinitionType newFunctionDefinition, NotificationChain msgs)
  {
    Mml_FunctionDefinitionType oldFunctionDefinition = functionDefinition;
    functionDefinition = newFunctionDefinition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION, oldFunctionDefinition, newFunctionDefinition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunctionDefinition(Mml_FunctionDefinitionType newFunctionDefinition)
  {
    if (newFunctionDefinition != functionDefinition)
    {
      NotificationChain msgs = null;
      if (functionDefinition != null)
        msgs = ((InternalEObject)functionDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION, null, msgs);
      if (newFunctionDefinition != null)
        msgs = ((InternalEObject)newFunctionDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION, null, msgs);
      msgs = basicSetFunctionDefinition(newFunctionDefinition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION, newFunctionDefinition, newFunctionDefinition));
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
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__NAME:
        return basicSetName(null, msgs);
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE:
        return basicSetSymbolType(null, msgs);
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION:
        return basicSetDescription(null, msgs);
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION:
        return basicSetFunctionDefinition(null, msgs);
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
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__NAME:
        return getName();
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMB_ID:
        return getSymbId();
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE:
        return getSymbolType();
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION:
        return getDescription();
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION:
        return getFunctionDefinition();
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
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__NAME:
        setName((ct_name)newValue);
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMB_ID:
        setSymbId((ct_symbId)newValue);
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE:
        setSymbolType((ct_symbolType)newValue);
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION:
        setDescription((ct_AnnotationType)newValue);
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION:
        setFunctionDefinition((Mml_FunctionDefinitionType)newValue);
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
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__NAME:
        setName((ct_name)null);
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMB_ID:
        setSymbId((ct_symbId)null);
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE:
        setSymbolType((ct_symbolType)null);
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION:
        setDescription((ct_AnnotationType)null);
        return;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION:
        setFunctionDefinition((Mml_FunctionDefinitionType)null);
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
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__NAME:
        return name != null;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMB_ID:
        return symbId != null;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__SYMBOL_TYPE:
        return symbolType != null;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__DESCRIPTION:
        return description != null;
      case PharmMLPackage.MML_SYMBOL_DEFINITION_TYPE__FUNCTION_DEFINITION:
        return functionDefinition != null;
    }
    return super.eIsSet(featureID);
  }

} //Mml_SymbolDefinitionTypeImpl

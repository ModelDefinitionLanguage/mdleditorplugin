/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_AnnotationType;
import eu.ddmore.pml.pharmML.ct_Rhs;
import eu.ddmore.pml.pharmML.ct_VariableDefinitionType;
import eu.ddmore.pml.pharmML.ct_independentVar;
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
 * An implementation of the model object '<em><b>ct Variable Definition Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_VariableDefinitionTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_VariableDefinitionTypeImpl#getIndependentVar <em>Independent Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_VariableDefinitionTypeImpl#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_VariableDefinitionTypeImpl#getSymbolType <em>Symbol Type</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_VariableDefinitionTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_VariableDefinitionTypeImpl#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_VariableDefinitionTypeImpl extends MinimalEObjectImpl.Container implements ct_VariableDefinitionType
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
   * The cached value of the '{@link #getIndependentVar() <em>Independent Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndependentVar()
   * @generated
   * @ordered
   */
  protected ct_independentVar independentVar;

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
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected ct_Rhs expr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_VariableDefinitionTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getct_VariableDefinitionType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_independentVar getIndependentVar()
  {
    return independentVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIndependentVar(ct_independentVar newIndependentVar, NotificationChain msgs)
  {
    ct_independentVar oldIndependentVar = independentVar;
    independentVar = newIndependentVar;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR, oldIndependentVar, newIndependentVar);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIndependentVar(ct_independentVar newIndependentVar)
  {
    if (newIndependentVar != independentVar)
    {
      NotificationChain msgs = null;
      if (independentVar != null)
        msgs = ((InternalEObject)independentVar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR, null, msgs);
      if (newIndependentVar != null)
        msgs = ((InternalEObject)newIndependentVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR, null, msgs);
      msgs = basicSetIndependentVar(newIndependentVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR, newIndependentVar, newIndependentVar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMB_ID, oldSymbId, newSymbId);
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
        msgs = ((InternalEObject)symbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMB_ID, null, msgs);
      if (newSymbId != null)
        msgs = ((InternalEObject)newSymbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMB_ID, null, msgs);
      msgs = basicSetSymbId(newSymbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMB_ID, newSymbId, newSymbId));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE, oldSymbolType, newSymbolType);
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
        msgs = ((InternalEObject)symbolType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE, null, msgs);
      if (newSymbolType != null)
        msgs = ((InternalEObject)newSymbolType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE, null, msgs);
      msgs = basicSetSymbolType(newSymbolType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE, newSymbolType, newSymbolType));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION, oldDescription, newDescription);
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
        msgs = ((InternalEObject)description).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION, null, msgs);
      if (newDescription != null)
        msgs = ((InternalEObject)newDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION, null, msgs);
      msgs = basicSetDescription(newDescription, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION, newDescription, newDescription));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Rhs getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(ct_Rhs newExpr, NotificationChain msgs)
  {
    ct_Rhs oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpr(ct_Rhs newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__EXPR, newExpr, newExpr));
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
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR:
        return basicSetIndependentVar(null, msgs);
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMB_ID:
        return basicSetSymbId(null, msgs);
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE:
        return basicSetSymbolType(null, msgs);
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION:
        return basicSetDescription(null, msgs);
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__EXPR:
        return basicSetExpr(null, msgs);
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
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR:
        return getIndependentVar();
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMB_ID:
        return getSymbId();
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE:
        return getSymbolType();
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION:
        return getDescription();
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__EXPR:
        return getExpr();
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
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR:
        setIndependentVar((ct_independentVar)newValue);
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMB_ID:
        setSymbId((ct_symbId)newValue);
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE:
        setSymbolType((ct_symbolType)newValue);
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION:
        setDescription((ct_AnnotationType)newValue);
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__EXPR:
        setExpr((ct_Rhs)newValue);
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
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR:
        setIndependentVar((ct_independentVar)null);
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMB_ID:
        setSymbId((ct_symbId)null);
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE:
        setSymbolType((ct_symbolType)null);
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION:
        setDescription((ct_AnnotationType)null);
        return;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__EXPR:
        setExpr((ct_Rhs)null);
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
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__INDEPENDENT_VAR:
        return independentVar != null;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMB_ID:
        return symbId != null;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__SYMBOL_TYPE:
        return symbolType != null;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__DESCRIPTION:
        return description != null;
      case PharmMLPackage.CT_VARIABLE_DEFINITION_TYPE__EXPR:
        return expr != null;
    }
    return super.eIsSet(featureID);
  }

} //ct_VariableDefinitionTypeImpl

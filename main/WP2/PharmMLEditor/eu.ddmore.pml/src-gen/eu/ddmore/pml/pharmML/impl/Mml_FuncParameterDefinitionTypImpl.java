/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Mml_FuncParameterDefinitionTyp;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_AnnotationType;
import eu.ddmore.pml.pharmML.ct_symbId;
import eu.ddmore.pml.pharmML.ct_symbolType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mml Func Parameter Definition Typ</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_FuncParameterDefinitionTypImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_FuncParameterDefinitionTypImpl#getSumbId <em>Sumb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_FuncParameterDefinitionTypImpl#getSymbolType <em>Symbol Type</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_FuncParameterDefinitionTypImpl#getDecsription <em>Decsription</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Mml_FuncParameterDefinitionTypImpl extends Mml_FuncParameterDefinitionTypeImpl implements Mml_FuncParameterDefinitionTyp
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
   * The cached value of the '{@link #getSumbId() <em>Sumb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSumbId()
   * @generated
   * @ordered
   */
  protected ct_symbId sumbId;

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
   * The cached value of the '{@link #getDecsription() <em>Decsription</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDecsription()
   * @generated
   * @ordered
   */
  protected ct_AnnotationType decsription;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Mml_FuncParameterDefinitionTypImpl()
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
    return PharmMLPackage.eINSTANCE.getMml_FuncParameterDefinitionTyp();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_symbId getSumbId()
  {
    return sumbId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSumbId(ct_symbId newSumbId, NotificationChain msgs)
  {
    ct_symbId oldSumbId = sumbId;
    sumbId = newSumbId;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID, oldSumbId, newSumbId);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSumbId(ct_symbId newSumbId)
  {
    if (newSumbId != sumbId)
    {
      NotificationChain msgs = null;
      if (sumbId != null)
        msgs = ((InternalEObject)sumbId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID, null, msgs);
      if (newSumbId != null)
        msgs = ((InternalEObject)newSumbId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID, null, msgs);
      msgs = basicSetSumbId(newSumbId, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID, newSumbId, newSumbId));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE, oldSymbolType, newSymbolType);
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
        msgs = ((InternalEObject)symbolType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE, null, msgs);
      if (newSymbolType != null)
        msgs = ((InternalEObject)newSymbolType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE, null, msgs);
      msgs = basicSetSymbolType(newSymbolType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE, newSymbolType, newSymbolType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_AnnotationType getDecsription()
  {
    return decsription;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDecsription(ct_AnnotationType newDecsription, NotificationChain msgs)
  {
    ct_AnnotationType oldDecsription = decsription;
    decsription = newDecsription;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION, oldDecsription, newDecsription);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDecsription(ct_AnnotationType newDecsription)
  {
    if (newDecsription != decsription)
    {
      NotificationChain msgs = null;
      if (decsription != null)
        msgs = ((InternalEObject)decsription).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION, null, msgs);
      if (newDecsription != null)
        msgs = ((InternalEObject)newDecsription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION, null, msgs);
      msgs = basicSetDecsription(newDecsription, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION, newDecsription, newDecsription));
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
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID:
        return basicSetSumbId(null, msgs);
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE:
        return basicSetSymbolType(null, msgs);
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION:
        return basicSetDecsription(null, msgs);
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
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__XMLNS:
        return getXmlns();
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID:
        return getSumbId();
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE:
        return getSymbolType();
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION:
        return getDecsription();
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
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID:
        setSumbId((ct_symbId)newValue);
        return;
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE:
        setSymbolType((ct_symbolType)newValue);
        return;
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION:
        setDecsription((ct_AnnotationType)newValue);
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
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID:
        setSumbId((ct_symbId)null);
        return;
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE:
        setSymbolType((ct_symbolType)null);
        return;
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION:
        setDecsription((ct_AnnotationType)null);
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
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SUMB_ID:
        return sumbId != null;
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__SYMBOL_TYPE:
        return symbolType != null;
      case PharmMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYP__DECSRIPTION:
        return decsription != null;
    }
    return super.eIsSet(featureID);
  }

} //Mml_FuncParameterDefinitionTypImpl

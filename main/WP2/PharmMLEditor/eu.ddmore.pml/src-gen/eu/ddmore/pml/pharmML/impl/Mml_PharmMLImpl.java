/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Mml_PharmML;
import eu.ddmore.pml.pharmML.Mml_PharmaMLContent;
import eu.ddmore.pml.pharmML.Mml_SymbolDefinitionType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_independentVar;
import eu.ddmore.pml.pharmML.ct_name;
import eu.ddmore.pml.pharmML.ct_writtenVersion;
import eu.ddmore.pml.pharmML.design_TrialDesignType;
import eu.ddmore.pml.pharmML.msteps_ModelingSteps;

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
 * An implementation of the model object '<em><b>Mml Pharm ML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl#getIndependentVar <em>Independent Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl#getWrittenVersion <em>Written Version</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl#getSymbolDefinition <em>Symbol Definition</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl#getDesign <em>Design</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmMLImpl#getModelingSteps <em>Modeling Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Mml_PharmMLImpl extends MinimalEObjectImpl.Container implements Mml_PharmML
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
   * The cached value of the '{@link #getIndependentVar() <em>Independent Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndependentVar()
   * @generated
   * @ordered
   */
  protected ct_independentVar independentVar;

  /**
   * The cached value of the '{@link #getWrittenVersion() <em>Written Version</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWrittenVersion()
   * @generated
   * @ordered
   */
  protected ct_writtenVersion writtenVersion;

  /**
   * The cached value of the '{@link #getSymbolDefinition() <em>Symbol Definition</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbolDefinition()
   * @generated
   * @ordered
   */
  protected EList<Mml_SymbolDefinitionType> symbolDefinition;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected EList<Mml_PharmaMLContent> expr;

  /**
   * The cached value of the '{@link #getDesign() <em>Design</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDesign()
   * @generated
   * @ordered
   */
  protected design_TrialDesignType design;

  /**
   * The cached value of the '{@link #getModelingSteps() <em>Modeling Steps</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelingSteps()
   * @generated
   * @ordered
   */
  protected msteps_ModelingSteps modelingSteps;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Mml_PharmMLImpl()
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
    return PharmMLPackage.eINSTANCE.getMml_PharmML();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MML_PHARM_ML__XMLNS);
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__NAME, oldName, newName);
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
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__NAME, newName, newName));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__INDEPENDENT_VAR, oldIndependentVar, newIndependentVar);
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
        msgs = ((InternalEObject)independentVar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__INDEPENDENT_VAR, null, msgs);
      if (newIndependentVar != null)
        msgs = ((InternalEObject)newIndependentVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__INDEPENDENT_VAR, null, msgs);
      msgs = basicSetIndependentVar(newIndependentVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__INDEPENDENT_VAR, newIndependentVar, newIndependentVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_writtenVersion getWrittenVersion()
  {
    return writtenVersion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWrittenVersion(ct_writtenVersion newWrittenVersion, NotificationChain msgs)
  {
    ct_writtenVersion oldWrittenVersion = writtenVersion;
    writtenVersion = newWrittenVersion;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__WRITTEN_VERSION, oldWrittenVersion, newWrittenVersion);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWrittenVersion(ct_writtenVersion newWrittenVersion)
  {
    if (newWrittenVersion != writtenVersion)
    {
      NotificationChain msgs = null;
      if (writtenVersion != null)
        msgs = ((InternalEObject)writtenVersion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__WRITTEN_VERSION, null, msgs);
      if (newWrittenVersion != null)
        msgs = ((InternalEObject)newWrittenVersion).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__WRITTEN_VERSION, null, msgs);
      msgs = basicSetWrittenVersion(newWrittenVersion, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__WRITTEN_VERSION, newWrittenVersion, newWrittenVersion));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Mml_SymbolDefinitionType> getSymbolDefinition()
  {
    if (symbolDefinition == null)
    {
      symbolDefinition = new EObjectContainmentEList<Mml_SymbolDefinitionType>(Mml_SymbolDefinitionType.class, this, PharmMLPackage.MML_PHARM_ML__SYMBOL_DEFINITION);
    }
    return symbolDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Mml_PharmaMLContent> getExpr()
  {
    if (expr == null)
    {
      expr = new EObjectContainmentEList<Mml_PharmaMLContent>(Mml_PharmaMLContent.class, this, PharmMLPackage.MML_PHARM_ML__EXPR);
    }
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_TrialDesignType getDesign()
  {
    return design;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDesign(design_TrialDesignType newDesign, NotificationChain msgs)
  {
    design_TrialDesignType oldDesign = design;
    design = newDesign;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__DESIGN, oldDesign, newDesign);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDesign(design_TrialDesignType newDesign)
  {
    if (newDesign != design)
    {
      NotificationChain msgs = null;
      if (design != null)
        msgs = ((InternalEObject)design).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__DESIGN, null, msgs);
      if (newDesign != null)
        msgs = ((InternalEObject)newDesign).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__DESIGN, null, msgs);
      msgs = basicSetDesign(newDesign, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__DESIGN, newDesign, newDesign));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_ModelingSteps getModelingSteps()
  {
    return modelingSteps;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModelingSteps(msteps_ModelingSteps newModelingSteps, NotificationChain msgs)
  {
    msteps_ModelingSteps oldModelingSteps = modelingSteps;
    modelingSteps = newModelingSteps;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__MODELING_STEPS, oldModelingSteps, newModelingSteps);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelingSteps(msteps_ModelingSteps newModelingSteps)
  {
    if (newModelingSteps != modelingSteps)
    {
      NotificationChain msgs = null;
      if (modelingSteps != null)
        msgs = ((InternalEObject)modelingSteps).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__MODELING_STEPS, null, msgs);
      if (newModelingSteps != null)
        msgs = ((InternalEObject)newModelingSteps).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARM_ML__MODELING_STEPS, null, msgs);
      msgs = basicSetModelingSteps(newModelingSteps, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARM_ML__MODELING_STEPS, newModelingSteps, newModelingSteps));
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
      case PharmMLPackage.MML_PHARM_ML__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MML_PHARM_ML__NAME:
        return basicSetName(null, msgs);
      case PharmMLPackage.MML_PHARM_ML__INDEPENDENT_VAR:
        return basicSetIndependentVar(null, msgs);
      case PharmMLPackage.MML_PHARM_ML__WRITTEN_VERSION:
        return basicSetWrittenVersion(null, msgs);
      case PharmMLPackage.MML_PHARM_ML__SYMBOL_DEFINITION:
        return ((InternalEList<?>)getSymbolDefinition()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MML_PHARM_ML__EXPR:
        return ((InternalEList<?>)getExpr()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MML_PHARM_ML__DESIGN:
        return basicSetDesign(null, msgs);
      case PharmMLPackage.MML_PHARM_ML__MODELING_STEPS:
        return basicSetModelingSteps(null, msgs);
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
      case PharmMLPackage.MML_PHARM_ML__XMLNS:
        return getXmlns();
      case PharmMLPackage.MML_PHARM_ML__NAME:
        return getName();
      case PharmMLPackage.MML_PHARM_ML__INDEPENDENT_VAR:
        return getIndependentVar();
      case PharmMLPackage.MML_PHARM_ML__WRITTEN_VERSION:
        return getWrittenVersion();
      case PharmMLPackage.MML_PHARM_ML__SYMBOL_DEFINITION:
        return getSymbolDefinition();
      case PharmMLPackage.MML_PHARM_ML__EXPR:
        return getExpr();
      case PharmMLPackage.MML_PHARM_ML__DESIGN:
        return getDesign();
      case PharmMLPackage.MML_PHARM_ML__MODELING_STEPS:
        return getModelingSteps();
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
      case PharmMLPackage.MML_PHARM_ML__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MML_PHARM_ML__NAME:
        setName((ct_name)newValue);
        return;
      case PharmMLPackage.MML_PHARM_ML__INDEPENDENT_VAR:
        setIndependentVar((ct_independentVar)newValue);
        return;
      case PharmMLPackage.MML_PHARM_ML__WRITTEN_VERSION:
        setWrittenVersion((ct_writtenVersion)newValue);
        return;
      case PharmMLPackage.MML_PHARM_ML__SYMBOL_DEFINITION:
        getSymbolDefinition().clear();
        getSymbolDefinition().addAll((Collection<? extends Mml_SymbolDefinitionType>)newValue);
        return;
      case PharmMLPackage.MML_PHARM_ML__EXPR:
        getExpr().clear();
        getExpr().addAll((Collection<? extends Mml_PharmaMLContent>)newValue);
        return;
      case PharmMLPackage.MML_PHARM_ML__DESIGN:
        setDesign((design_TrialDesignType)newValue);
        return;
      case PharmMLPackage.MML_PHARM_ML__MODELING_STEPS:
        setModelingSteps((msteps_ModelingSteps)newValue);
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
      case PharmMLPackage.MML_PHARM_ML__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MML_PHARM_ML__NAME:
        setName((ct_name)null);
        return;
      case PharmMLPackage.MML_PHARM_ML__INDEPENDENT_VAR:
        setIndependentVar((ct_independentVar)null);
        return;
      case PharmMLPackage.MML_PHARM_ML__WRITTEN_VERSION:
        setWrittenVersion((ct_writtenVersion)null);
        return;
      case PharmMLPackage.MML_PHARM_ML__SYMBOL_DEFINITION:
        getSymbolDefinition().clear();
        return;
      case PharmMLPackage.MML_PHARM_ML__EXPR:
        getExpr().clear();
        return;
      case PharmMLPackage.MML_PHARM_ML__DESIGN:
        setDesign((design_TrialDesignType)null);
        return;
      case PharmMLPackage.MML_PHARM_ML__MODELING_STEPS:
        setModelingSteps((msteps_ModelingSteps)null);
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
      case PharmMLPackage.MML_PHARM_ML__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MML_PHARM_ML__NAME:
        return name != null;
      case PharmMLPackage.MML_PHARM_ML__INDEPENDENT_VAR:
        return independentVar != null;
      case PharmMLPackage.MML_PHARM_ML__WRITTEN_VERSION:
        return writtenVersion != null;
      case PharmMLPackage.MML_PHARM_ML__SYMBOL_DEFINITION:
        return symbolDefinition != null && !symbolDefinition.isEmpty();
      case PharmMLPackage.MML_PHARM_ML__EXPR:
        return expr != null && !expr.isEmpty();
      case PharmMLPackage.MML_PHARM_ML__DESIGN:
        return design != null;
      case PharmMLPackage.MML_PHARM_ML__MODELING_STEPS:
        return modelingSteps != null;
    }
    return super.eIsSet(featureID);
  }

} //Mml_PharmMLImpl

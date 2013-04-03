/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.Mml_PharmaML;
import org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_Name;
import org.ddmore.pml.pharmaML.ct_independentVar;
import org.ddmore.pml.pharmaML.ct_writtenVersion;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.design_TrialDesignType;
import org.ddmore.pml.pharmaML.mdef_ModelDefinitionType;
import org.ddmore.pml.pharmaML.mdef_StructuralModelType;
import org.ddmore.pml.pharmaML.msteps_ModelingSteps;

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
 * An implementation of the model object '<em><b>Mml Pharma ML</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl#getIndependentVar <em>Independent Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl#getWrittenVersion <em>Written Version</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl#getSymbolDefinition <em>Symbol Definition</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl#getStructuralModel <em>Structural Model</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl#getModelDefinition <em>Model Definition</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl#getDesign <em>Design</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.Mml_PharmaMLImpl#getModelingSteps <em>Modeling Steps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Mml_PharmaMLImpl extends MinimalEObjectImpl.Container implements Mml_PharmaML
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
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected ct_Name name;

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
   * The cached value of the '{@link #getStructuralModel() <em>Structural Model</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStructuralModel()
   * @generated
   * @ordered
   */
  protected EList<mdef_StructuralModelType> structuralModel;

  /**
   * The cached value of the '{@link #getModelDefinition() <em>Model Definition</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelDefinition()
   * @generated
   * @ordered
   */
  protected EList<mdef_ModelDefinitionType> modelDefinition;

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
  protected Mml_PharmaMLImpl()
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
    return PharmaMLPackage.eINSTANCE.getMml_PharmaML();
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MML_PHARMA_ML__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_Name getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(ct_Name newName, NotificationChain msgs)
  {
    ct_Name oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(ct_Name newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__NAME, newName, newName));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__INDEPENDENT_VAR, oldIndependentVar, newIndependentVar);
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
        msgs = ((InternalEObject)independentVar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__INDEPENDENT_VAR, null, msgs);
      if (newIndependentVar != null)
        msgs = ((InternalEObject)newIndependentVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__INDEPENDENT_VAR, null, msgs);
      msgs = basicSetIndependentVar(newIndependentVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__INDEPENDENT_VAR, newIndependentVar, newIndependentVar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__WRITTEN_VERSION, oldWrittenVersion, newWrittenVersion);
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
        msgs = ((InternalEObject)writtenVersion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__WRITTEN_VERSION, null, msgs);
      if (newWrittenVersion != null)
        msgs = ((InternalEObject)newWrittenVersion).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__WRITTEN_VERSION, null, msgs);
      msgs = basicSetWrittenVersion(newWrittenVersion, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__WRITTEN_VERSION, newWrittenVersion, newWrittenVersion));
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
      symbolDefinition = new EObjectContainmentEList<Mml_SymbolDefinitionType>(Mml_SymbolDefinitionType.class, this, PharmaMLPackage.MML_PHARMA_ML__SYMBOL_DEFINITION);
    }
    return symbolDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_StructuralModelType> getStructuralModel()
  {
    if (structuralModel == null)
    {
      structuralModel = new EObjectContainmentEList<mdef_StructuralModelType>(mdef_StructuralModelType.class, this, PharmaMLPackage.MML_PHARMA_ML__STRUCTURAL_MODEL);
    }
    return structuralModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<mdef_ModelDefinitionType> getModelDefinition()
  {
    if (modelDefinition == null)
    {
      modelDefinition = new EObjectContainmentEList<mdef_ModelDefinitionType>(mdef_ModelDefinitionType.class, this, PharmaMLPackage.MML_PHARMA_ML__MODEL_DEFINITION);
    }
    return modelDefinition;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__DESIGN, oldDesign, newDesign);
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
        msgs = ((InternalEObject)design).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__DESIGN, null, msgs);
      if (newDesign != null)
        msgs = ((InternalEObject)newDesign).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__DESIGN, null, msgs);
      msgs = basicSetDesign(newDesign, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__DESIGN, newDesign, newDesign));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__MODELING_STEPS, oldModelingSteps, newModelingSteps);
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
        msgs = ((InternalEObject)modelingSteps).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__MODELING_STEPS, null, msgs);
      if (newModelingSteps != null)
        msgs = ((InternalEObject)newModelingSteps).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MML_PHARMA_ML__MODELING_STEPS, null, msgs);
      msgs = basicSetModelingSteps(newModelingSteps, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MML_PHARMA_ML__MODELING_STEPS, newModelingSteps, newModelingSteps));
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
      case PharmaMLPackage.MML_PHARMA_ML__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MML_PHARMA_ML__NAME:
        return basicSetName(null, msgs);
      case PharmaMLPackage.MML_PHARMA_ML__INDEPENDENT_VAR:
        return basicSetIndependentVar(null, msgs);
      case PharmaMLPackage.MML_PHARMA_ML__WRITTEN_VERSION:
        return basicSetWrittenVersion(null, msgs);
      case PharmaMLPackage.MML_PHARMA_ML__SYMBOL_DEFINITION:
        return ((InternalEList<?>)getSymbolDefinition()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MML_PHARMA_ML__STRUCTURAL_MODEL:
        return ((InternalEList<?>)getStructuralModel()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MML_PHARMA_ML__MODEL_DEFINITION:
        return ((InternalEList<?>)getModelDefinition()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MML_PHARMA_ML__DESIGN:
        return basicSetDesign(null, msgs);
      case PharmaMLPackage.MML_PHARMA_ML__MODELING_STEPS:
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
      case PharmaMLPackage.MML_PHARMA_ML__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MML_PHARMA_ML__NAME:
        return getName();
      case PharmaMLPackage.MML_PHARMA_ML__INDEPENDENT_VAR:
        return getIndependentVar();
      case PharmaMLPackage.MML_PHARMA_ML__WRITTEN_VERSION:
        return getWrittenVersion();
      case PharmaMLPackage.MML_PHARMA_ML__SYMBOL_DEFINITION:
        return getSymbolDefinition();
      case PharmaMLPackage.MML_PHARMA_ML__STRUCTURAL_MODEL:
        return getStructuralModel();
      case PharmaMLPackage.MML_PHARMA_ML__MODEL_DEFINITION:
        return getModelDefinition();
      case PharmaMLPackage.MML_PHARMA_ML__DESIGN:
        return getDesign();
      case PharmaMLPackage.MML_PHARMA_ML__MODELING_STEPS:
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
      case PharmaMLPackage.MML_PHARMA_ML__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__NAME:
        setName((ct_Name)newValue);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__INDEPENDENT_VAR:
        setIndependentVar((ct_independentVar)newValue);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__WRITTEN_VERSION:
        setWrittenVersion((ct_writtenVersion)newValue);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__SYMBOL_DEFINITION:
        getSymbolDefinition().clear();
        getSymbolDefinition().addAll((Collection<? extends Mml_SymbolDefinitionType>)newValue);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__STRUCTURAL_MODEL:
        getStructuralModel().clear();
        getStructuralModel().addAll((Collection<? extends mdef_StructuralModelType>)newValue);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__MODEL_DEFINITION:
        getModelDefinition().clear();
        getModelDefinition().addAll((Collection<? extends mdef_ModelDefinitionType>)newValue);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__DESIGN:
        setDesign((design_TrialDesignType)newValue);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__MODELING_STEPS:
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
      case PharmaMLPackage.MML_PHARMA_ML__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MML_PHARMA_ML__NAME:
        setName((ct_Name)null);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__INDEPENDENT_VAR:
        setIndependentVar((ct_independentVar)null);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__WRITTEN_VERSION:
        setWrittenVersion((ct_writtenVersion)null);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__SYMBOL_DEFINITION:
        getSymbolDefinition().clear();
        return;
      case PharmaMLPackage.MML_PHARMA_ML__STRUCTURAL_MODEL:
        getStructuralModel().clear();
        return;
      case PharmaMLPackage.MML_PHARMA_ML__MODEL_DEFINITION:
        getModelDefinition().clear();
        return;
      case PharmaMLPackage.MML_PHARMA_ML__DESIGN:
        setDesign((design_TrialDesignType)null);
        return;
      case PharmaMLPackage.MML_PHARMA_ML__MODELING_STEPS:
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
      case PharmaMLPackage.MML_PHARMA_ML__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MML_PHARMA_ML__NAME:
        return name != null;
      case PharmaMLPackage.MML_PHARMA_ML__INDEPENDENT_VAR:
        return independentVar != null;
      case PharmaMLPackage.MML_PHARMA_ML__WRITTEN_VERSION:
        return writtenVersion != null;
      case PharmaMLPackage.MML_PHARMA_ML__SYMBOL_DEFINITION:
        return symbolDefinition != null && !symbolDefinition.isEmpty();
      case PharmaMLPackage.MML_PHARMA_ML__STRUCTURAL_MODEL:
        return structuralModel != null && !structuralModel.isEmpty();
      case PharmaMLPackage.MML_PHARMA_ML__MODEL_DEFINITION:
        return modelDefinition != null && !modelDefinition.isEmpty();
      case PharmaMLPackage.MML_PHARMA_ML__DESIGN:
        return design != null;
      case PharmaMLPackage.MML_PHARMA_ML__MODELING_STEPS:
        return modelingSteps != null;
    }
    return super.eIsSet(featureID);
  }

} //Mml_PharmaMLImpl

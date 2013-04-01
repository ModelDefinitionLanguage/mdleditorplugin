/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.Math_VarType;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_RestrictionType;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.design_DoseVarType;
import org.ddmore.pml.pharmaML.msteps_AssignType;
import org.ddmore.pml.pharmaML.msteps_MappingType;
import org.ddmore.pml.pharmaML.msteps_OutputType;
import org.ddmore.pml.pharmaML.msteps_TargetVarType;
import org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel;
import org.ddmore.pml.pharmaML.msteps_UseVariabilityNode;

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
 * An implementation of the model object '<em><b>msteps Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getColumnName <em>Column Name</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getVar <em>Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getUseVariabilityLevel <em>Use Variability Level</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getUseVariabilityNode <em>Use Variability Node</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getDoseVar <em>Dose Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getTargetVar <em>Target Var</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getAssign <em>Assign</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getOutput <em>Output</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_MappingTypeImpl#getRestriction <em>Restriction</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_MappingTypeImpl extends MinimalEObjectImpl.Container implements msteps_MappingType
{
  /**
   * The default value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnName()
   * @generated
   * @ordered
   */
  protected static final String COLUMN_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getColumnName() <em>Column Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnName()
   * @generated
   * @ordered
   */
  protected String columnName = COLUMN_NAME_EDEFAULT;

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
   * The cached value of the '{@link #getVar() <em>Var</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVar()
   * @generated
   * @ordered
   */
  protected Math_VarType var;

  /**
   * The cached value of the '{@link #getUseVariabilityLevel() <em>Use Variability Level</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUseVariabilityLevel()
   * @generated
   * @ordered
   */
  protected msteps_UseVariabilityLevel useVariabilityLevel;

  /**
   * The cached value of the '{@link #getUseVariabilityNode() <em>Use Variability Node</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUseVariabilityNode()
   * @generated
   * @ordered
   */
  protected msteps_UseVariabilityNode useVariabilityNode;

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
  protected msteps_TargetVarType targetVar;

  /**
   * The cached value of the '{@link #getAssign() <em>Assign</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAssign()
   * @generated
   * @ordered
   */
  protected msteps_AssignType assign;

  /**
   * The cached value of the '{@link #getOutput() <em>Output</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutput()
   * @generated
   * @ordered
   */
  protected msteps_OutputType output;

  /**
   * The cached value of the '{@link #getRestriction() <em>Restriction</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRestriction()
   * @generated
   * @ordered
   */
  protected ct_RestrictionType restriction;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_MappingTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getmsteps_MappingType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getColumnName()
  {
    return columnName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColumnName(String newColumnName)
  {
    String oldColumnName = columnName;
    columnName = newColumnName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__COLUMN_NAME, oldColumnName, columnName));
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MSTEPS_MAPPING_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_VarType getVar()
  {
    return var;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVar(Math_VarType newVar, NotificationChain msgs)
  {
    Math_VarType oldVar = var;
    var = newVar;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__VAR, oldVar, newVar);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVar(Math_VarType newVar)
  {
    if (newVar != var)
    {
      NotificationChain msgs = null;
      if (var != null)
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__VAR, newVar, newVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_UseVariabilityLevel getUseVariabilityLevel()
  {
    return useVariabilityLevel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUseVariabilityLevel(msteps_UseVariabilityLevel newUseVariabilityLevel, NotificationChain msgs)
  {
    msteps_UseVariabilityLevel oldUseVariabilityLevel = useVariabilityLevel;
    useVariabilityLevel = newUseVariabilityLevel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL, oldUseVariabilityLevel, newUseVariabilityLevel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUseVariabilityLevel(msteps_UseVariabilityLevel newUseVariabilityLevel)
  {
    if (newUseVariabilityLevel != useVariabilityLevel)
    {
      NotificationChain msgs = null;
      if (useVariabilityLevel != null)
        msgs = ((InternalEObject)useVariabilityLevel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL, null, msgs);
      if (newUseVariabilityLevel != null)
        msgs = ((InternalEObject)newUseVariabilityLevel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL, null, msgs);
      msgs = basicSetUseVariabilityLevel(newUseVariabilityLevel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL, newUseVariabilityLevel, newUseVariabilityLevel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_UseVariabilityNode getUseVariabilityNode()
  {
    return useVariabilityNode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUseVariabilityNode(msteps_UseVariabilityNode newUseVariabilityNode, NotificationChain msgs)
  {
    msteps_UseVariabilityNode oldUseVariabilityNode = useVariabilityNode;
    useVariabilityNode = newUseVariabilityNode;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE, oldUseVariabilityNode, newUseVariabilityNode);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUseVariabilityNode(msteps_UseVariabilityNode newUseVariabilityNode)
  {
    if (newUseVariabilityNode != useVariabilityNode)
    {
      NotificationChain msgs = null;
      if (useVariabilityNode != null)
        msgs = ((InternalEObject)useVariabilityNode).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE, null, msgs);
      if (newUseVariabilityNode != null)
        msgs = ((InternalEObject)newUseVariabilityNode).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE, null, msgs);
      msgs = basicSetUseVariabilityNode(newUseVariabilityNode, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE, newUseVariabilityNode, newUseVariabilityNode));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__DOSE_VAR, oldDoseVar, newDoseVar);
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
        msgs = ((InternalEObject)doseVar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__DOSE_VAR, null, msgs);
      if (newDoseVar != null)
        msgs = ((InternalEObject)newDoseVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__DOSE_VAR, null, msgs);
      msgs = basicSetDoseVar(newDoseVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__DOSE_VAR, newDoseVar, newDoseVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_TargetVarType getTargetVar()
  {
    return targetVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTargetVar(msteps_TargetVarType newTargetVar, NotificationChain msgs)
  {
    msteps_TargetVarType oldTargetVar = targetVar;
    targetVar = newTargetVar;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__TARGET_VAR, oldTargetVar, newTargetVar);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetVar(msteps_TargetVarType newTargetVar)
  {
    if (newTargetVar != targetVar)
    {
      NotificationChain msgs = null;
      if (targetVar != null)
        msgs = ((InternalEObject)targetVar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__TARGET_VAR, null, msgs);
      if (newTargetVar != null)
        msgs = ((InternalEObject)newTargetVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__TARGET_VAR, null, msgs);
      msgs = basicSetTargetVar(newTargetVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__TARGET_VAR, newTargetVar, newTargetVar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_AssignType getAssign()
  {
    return assign;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAssign(msteps_AssignType newAssign, NotificationChain msgs)
  {
    msteps_AssignType oldAssign = assign;
    assign = newAssign;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__ASSIGN, oldAssign, newAssign);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAssign(msteps_AssignType newAssign)
  {
    if (newAssign != assign)
    {
      NotificationChain msgs = null;
      if (assign != null)
        msgs = ((InternalEObject)assign).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__ASSIGN, null, msgs);
      if (newAssign != null)
        msgs = ((InternalEObject)newAssign).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__ASSIGN, null, msgs);
      msgs = basicSetAssign(newAssign, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__ASSIGN, newAssign, newAssign));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_OutputType getOutput()
  {
    return output;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOutput(msteps_OutputType newOutput, NotificationChain msgs)
  {
    msteps_OutputType oldOutput = output;
    output = newOutput;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__OUTPUT, oldOutput, newOutput);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutput(msteps_OutputType newOutput)
  {
    if (newOutput != output)
    {
      NotificationChain msgs = null;
      if (output != null)
        msgs = ((InternalEObject)output).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__OUTPUT, null, msgs);
      if (newOutput != null)
        msgs = ((InternalEObject)newOutput).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__OUTPUT, null, msgs);
      msgs = basicSetOutput(newOutput, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__OUTPUT, newOutput, newOutput));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_RestrictionType getRestriction()
  {
    return restriction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRestriction(ct_RestrictionType newRestriction, NotificationChain msgs)
  {
    ct_RestrictionType oldRestriction = restriction;
    restriction = newRestriction;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__RESTRICTION, oldRestriction, newRestriction);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRestriction(ct_RestrictionType newRestriction)
  {
    if (newRestriction != restriction)
    {
      NotificationChain msgs = null;
      if (restriction != null)
        msgs = ((InternalEObject)restriction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__RESTRICTION, null, msgs);
      if (newRestriction != null)
        msgs = ((InternalEObject)newRestriction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MAPPING_TYPE__RESTRICTION, null, msgs);
      msgs = basicSetRestriction(newRestriction, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MAPPING_TYPE__RESTRICTION, newRestriction, newRestriction));
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
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__VAR:
        return basicSetVar(null, msgs);
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL:
        return basicSetUseVariabilityLevel(null, msgs);
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE:
        return basicSetUseVariabilityNode(null, msgs);
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__DOSE_VAR:
        return basicSetDoseVar(null, msgs);
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__TARGET_VAR:
        return basicSetTargetVar(null, msgs);
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__ASSIGN:
        return basicSetAssign(null, msgs);
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__OUTPUT:
        return basicSetOutput(null, msgs);
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__RESTRICTION:
        return basicSetRestriction(null, msgs);
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
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__COLUMN_NAME:
        return getColumnName();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__VAR:
        return getVar();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL:
        return getUseVariabilityLevel();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE:
        return getUseVariabilityNode();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__DOSE_VAR:
        return getDoseVar();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__TARGET_VAR:
        return getTargetVar();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__ASSIGN:
        return getAssign();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__OUTPUT:
        return getOutput();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__RESTRICTION:
        return getRestriction();
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
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__COLUMN_NAME:
        setColumnName((String)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__VAR:
        setVar((Math_VarType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL:
        setUseVariabilityLevel((msteps_UseVariabilityLevel)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE:
        setUseVariabilityNode((msteps_UseVariabilityNode)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__DOSE_VAR:
        setDoseVar((design_DoseVarType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__TARGET_VAR:
        setTargetVar((msteps_TargetVarType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__ASSIGN:
        setAssign((msteps_AssignType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__OUTPUT:
        setOutput((msteps_OutputType)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__RESTRICTION:
        setRestriction((ct_RestrictionType)newValue);
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
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__COLUMN_NAME:
        setColumnName(COLUMN_NAME_EDEFAULT);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__VAR:
        setVar((Math_VarType)null);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL:
        setUseVariabilityLevel((msteps_UseVariabilityLevel)null);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE:
        setUseVariabilityNode((msteps_UseVariabilityNode)null);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__DOSE_VAR:
        setDoseVar((design_DoseVarType)null);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__TARGET_VAR:
        setTargetVar((msteps_TargetVarType)null);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__ASSIGN:
        setAssign((msteps_AssignType)null);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__OUTPUT:
        setOutput((msteps_OutputType)null);
        return;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__RESTRICTION:
        setRestriction((ct_RestrictionType)null);
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
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__COLUMN_NAME:
        return COLUMN_NAME_EDEFAULT == null ? columnName != null : !COLUMN_NAME_EDEFAULT.equals(columnName);
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__VAR:
        return var != null;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_LEVEL:
        return useVariabilityLevel != null;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__USE_VARIABILITY_NODE:
        return useVariabilityNode != null;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__DOSE_VAR:
        return doseVar != null;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__TARGET_VAR:
        return targetVar != null;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__ASSIGN:
        return assign != null;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__OUTPUT:
        return output != null;
      case PharmaMLPackage.MSTEPS_MAPPING_TYPE__RESTRICTION:
        return restriction != null;
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
    result.append(" (columnName: ");
    result.append(columnName);
    result.append(')');
    return result.toString();
  }

} //msteps_MappingTypeImpl

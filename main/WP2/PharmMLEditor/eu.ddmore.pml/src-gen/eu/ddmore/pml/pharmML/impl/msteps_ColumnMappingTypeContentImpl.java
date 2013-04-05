/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_VarType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.design_DoseVarType;
import eu.ddmore.pml.pharmML.msteps_AssignType;
import eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent;
import eu.ddmore.pml.pharmML.msteps_OutputType;
import eu.ddmore.pml.pharmML.msteps_TargetVarType;
import eu.ddmore.pml.pharmML.msteps_UseVariabilityLevel;
import eu.ddmore.pml.pharmML.msteps_UseVariabilityNode;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>msteps Column Mapping Type Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeContentImpl#getVar <em>Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeContentImpl#getUseVariabilityLevel <em>Use Variability Level</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeContentImpl#getUseVariabilityNode <em>Use Variability Node</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeContentImpl#getDoseVar <em>Dose Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeContentImpl#getTargetVar <em>Target Var</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeContentImpl#getAssign <em>Assign</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeContentImpl#getOutput <em>Output</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_ColumnMappingTypeContentImpl extends MinimalEObjectImpl.Container implements msteps_ColumnMappingTypeContent
{
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_ColumnMappingTypeContentImpl()
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
    return PharmMLPackage.eINSTANCE.getmsteps_ColumnMappingTypeContent();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR, oldVar, newVar);
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
        msgs = ((InternalEObject)var).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR, null, msgs);
      if (newVar != null)
        msgs = ((InternalEObject)newVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR, null, msgs);
      msgs = basicSetVar(newVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR, newVar, newVar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL, oldUseVariabilityLevel, newUseVariabilityLevel);
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
        msgs = ((InternalEObject)useVariabilityLevel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL, null, msgs);
      if (newUseVariabilityLevel != null)
        msgs = ((InternalEObject)newUseVariabilityLevel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL, null, msgs);
      msgs = basicSetUseVariabilityLevel(newUseVariabilityLevel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL, newUseVariabilityLevel, newUseVariabilityLevel));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE, oldUseVariabilityNode, newUseVariabilityNode);
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
        msgs = ((InternalEObject)useVariabilityNode).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE, null, msgs);
      if (newUseVariabilityNode != null)
        msgs = ((InternalEObject)newUseVariabilityNode).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE, null, msgs);
      msgs = basicSetUseVariabilityNode(newUseVariabilityNode, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE, newUseVariabilityNode, newUseVariabilityNode));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR, oldDoseVar, newDoseVar);
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
        msgs = ((InternalEObject)doseVar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR, null, msgs);
      if (newDoseVar != null)
        msgs = ((InternalEObject)newDoseVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR, null, msgs);
      msgs = basicSetDoseVar(newDoseVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR, newDoseVar, newDoseVar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR, oldTargetVar, newTargetVar);
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
        msgs = ((InternalEObject)targetVar).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR, null, msgs);
      if (newTargetVar != null)
        msgs = ((InternalEObject)newTargetVar).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR, null, msgs);
      msgs = basicSetTargetVar(newTargetVar, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR, newTargetVar, newTargetVar));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN, oldAssign, newAssign);
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
        msgs = ((InternalEObject)assign).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN, null, msgs);
      if (newAssign != null)
        msgs = ((InternalEObject)newAssign).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN, null, msgs);
      msgs = basicSetAssign(newAssign, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN, newAssign, newAssign));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT, oldOutput, newOutput);
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
        msgs = ((InternalEObject)output).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT, null, msgs);
      if (newOutput != null)
        msgs = ((InternalEObject)newOutput).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT, null, msgs);
      msgs = basicSetOutput(newOutput, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT, newOutput, newOutput));
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR:
        return basicSetVar(null, msgs);
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL:
        return basicSetUseVariabilityLevel(null, msgs);
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE:
        return basicSetUseVariabilityNode(null, msgs);
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR:
        return basicSetDoseVar(null, msgs);
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR:
        return basicSetTargetVar(null, msgs);
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN:
        return basicSetAssign(null, msgs);
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT:
        return basicSetOutput(null, msgs);
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR:
        return getVar();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL:
        return getUseVariabilityLevel();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE:
        return getUseVariabilityNode();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR:
        return getDoseVar();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR:
        return getTargetVar();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN:
        return getAssign();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT:
        return getOutput();
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR:
        setVar((Math_VarType)newValue);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL:
        setUseVariabilityLevel((msteps_UseVariabilityLevel)newValue);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE:
        setUseVariabilityNode((msteps_UseVariabilityNode)newValue);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR:
        setDoseVar((design_DoseVarType)newValue);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR:
        setTargetVar((msteps_TargetVarType)newValue);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN:
        setAssign((msteps_AssignType)newValue);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT:
        setOutput((msteps_OutputType)newValue);
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR:
        setVar((Math_VarType)null);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL:
        setUseVariabilityLevel((msteps_UseVariabilityLevel)null);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE:
        setUseVariabilityNode((msteps_UseVariabilityNode)null);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR:
        setDoseVar((design_DoseVarType)null);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR:
        setTargetVar((msteps_TargetVarType)null);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN:
        setAssign((msteps_AssignType)null);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT:
        setOutput((msteps_OutputType)null);
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__VAR:
        return var != null;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_LEVEL:
        return useVariabilityLevel != null;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__USE_VARIABILITY_NODE:
        return useVariabilityNode != null;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__DOSE_VAR:
        return doseVar != null;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__TARGET_VAR:
        return targetVar != null;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__ASSIGN:
        return assign != null;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE_CONTENT__OUTPUT:
        return output != null;
    }
    return super.eIsSet(featureID);
  }

} //msteps_ColumnMappingTypeContentImpl

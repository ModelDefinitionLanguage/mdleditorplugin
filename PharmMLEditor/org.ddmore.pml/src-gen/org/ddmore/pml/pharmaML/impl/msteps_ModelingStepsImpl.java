/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_VariableDefinitionType;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.msteps_EstimationStep;
import org.ddmore.pml.pharmaML.msteps_ModelingSteps;
import org.ddmore.pml.pharmaML.msteps_SimulationStep;
import org.ddmore.pml.pharmaML.msteps_StepDependenciesType;

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
 * An implementation of the model object '<em><b>msteps Modeling Steps</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ModelingStepsImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ModelingStepsImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ModelingStepsImpl#getEstimationStep <em>Estimation Step</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ModelingStepsImpl#getSimulationStep <em>Simulation Step</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.msteps_ModelingStepsImpl#getStepDependencies <em>Step Dependencies</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_ModelingStepsImpl extends MinimalEObjectImpl.Container implements msteps_ModelingSteps
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
   * The cached value of the '{@link #getVariable() <em>Variable</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariable()
   * @generated
   * @ordered
   */
  protected EList<ct_VariableDefinitionType> variable;

  /**
   * The cached value of the '{@link #getEstimationStep() <em>Estimation Step</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstimationStep()
   * @generated
   * @ordered
   */
  protected EList<msteps_EstimationStep> estimationStep;

  /**
   * The cached value of the '{@link #getSimulationStep() <em>Simulation Step</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSimulationStep()
   * @generated
   * @ordered
   */
  protected EList<msteps_SimulationStep> simulationStep;

  /**
   * The cached value of the '{@link #getStepDependencies() <em>Step Dependencies</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStepDependencies()
   * @generated
   * @ordered
   */
  protected msteps_StepDependenciesType stepDependencies;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_ModelingStepsImpl()
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
    return PharmaMLPackage.eINSTANCE.getmsteps_ModelingSteps();
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.MSTEPS_MODELING_STEPS__XMLNS);
    }
    return xmlns;
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
      variable = new EObjectContainmentEList<ct_VariableDefinitionType>(ct_VariableDefinitionType.class, this, PharmaMLPackage.MSTEPS_MODELING_STEPS__VARIABLE);
    }
    return variable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_EstimationStep> getEstimationStep()
  {
    if (estimationStep == null)
    {
      estimationStep = new EObjectContainmentEList<msteps_EstimationStep>(msteps_EstimationStep.class, this, PharmaMLPackage.MSTEPS_MODELING_STEPS__ESTIMATION_STEP);
    }
    return estimationStep;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_SimulationStep> getSimulationStep()
  {
    if (simulationStep == null)
    {
      simulationStep = new EObjectContainmentEList<msteps_SimulationStep>(msteps_SimulationStep.class, this, PharmaMLPackage.MSTEPS_MODELING_STEPS__SIMULATION_STEP);
    }
    return simulationStep;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_StepDependenciesType getStepDependencies()
  {
    return stepDependencies;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStepDependencies(msteps_StepDependenciesType newStepDependencies, NotificationChain msgs)
  {
    msteps_StepDependenciesType oldStepDependencies = stepDependencies;
    stepDependencies = newStepDependencies;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES, oldStepDependencies, newStepDependencies);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStepDependencies(msteps_StepDependenciesType newStepDependencies)
  {
    if (newStepDependencies != stepDependencies)
    {
      NotificationChain msgs = null;
      if (stepDependencies != null)
        msgs = ((InternalEObject)stepDependencies).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES, null, msgs);
      if (newStepDependencies != null)
        msgs = ((InternalEObject)newStepDependencies).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES, null, msgs);
      msgs = basicSetStepDependencies(newStepDependencies, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES, newStepDependencies, newStepDependencies));
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
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        return ((InternalEList<?>)getVariable()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__ESTIMATION_STEP:
        return ((InternalEList<?>)getEstimationStep()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__SIMULATION_STEP:
        return ((InternalEList<?>)getSimulationStep()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
        return basicSetStepDependencies(null, msgs);
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
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        return getXmlns();
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        return getVariable();
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__ESTIMATION_STEP:
        return getEstimationStep();
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__SIMULATION_STEP:
        return getSimulationStep();
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
        return getStepDependencies();
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
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        getVariable().clear();
        getVariable().addAll((Collection<? extends ct_VariableDefinitionType>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__ESTIMATION_STEP:
        getEstimationStep().clear();
        getEstimationStep().addAll((Collection<? extends msteps_EstimationStep>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__SIMULATION_STEP:
        getSimulationStep().clear();
        getSimulationStep().addAll((Collection<? extends msteps_SimulationStep>)newValue);
        return;
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
        setStepDependencies((msteps_StepDependenciesType)newValue);
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
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        getVariable().clear();
        return;
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__ESTIMATION_STEP:
        getEstimationStep().clear();
        return;
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__SIMULATION_STEP:
        getSimulationStep().clear();
        return;
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
        setStepDependencies((msteps_StepDependenciesType)null);
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
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        return variable != null && !variable.isEmpty();
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__ESTIMATION_STEP:
        return estimationStep != null && !estimationStep.isEmpty();
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__SIMULATION_STEP:
        return simulationStep != null && !simulationStep.isEmpty();
      case PharmaMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
        return stepDependencies != null;
    }
    return super.eIsSet(featureID);
  }

} //msteps_ModelingStepsImpl

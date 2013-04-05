/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.msteps_EstimationStep;
import eu.ddmore.pml.pharmML.msteps_ModelingStepsContent;
import eu.ddmore.pml.pharmML.msteps_SimulationStep;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>msteps Modeling Steps Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsContentImpl#getEstimationStep <em>Estimation Step</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsContentImpl#getSimulationStep <em>Simulation Step</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_ModelingStepsContentImpl extends MinimalEObjectImpl.Container implements msteps_ModelingStepsContent
{
  /**
   * The cached value of the '{@link #getEstimationStep() <em>Estimation Step</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstimationStep()
   * @generated
   * @ordered
   */
  protected msteps_EstimationStep estimationStep;

  /**
   * The cached value of the '{@link #getSimulationStep() <em>Simulation Step</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSimulationStep()
   * @generated
   * @ordered
   */
  protected msteps_SimulationStep simulationStep;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_ModelingStepsContentImpl()
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
    return PharmMLPackage.eINSTANCE.getmsteps_ModelingStepsContent();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_EstimationStep getEstimationStep()
  {
    return estimationStep;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEstimationStep(msteps_EstimationStep newEstimationStep, NotificationChain msgs)
  {
    msteps_EstimationStep oldEstimationStep = estimationStep;
    estimationStep = newEstimationStep;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP, oldEstimationStep, newEstimationStep);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEstimationStep(msteps_EstimationStep newEstimationStep)
  {
    if (newEstimationStep != estimationStep)
    {
      NotificationChain msgs = null;
      if (estimationStep != null)
        msgs = ((InternalEObject)estimationStep).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP, null, msgs);
      if (newEstimationStep != null)
        msgs = ((InternalEObject)newEstimationStep).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP, null, msgs);
      msgs = basicSetEstimationStep(newEstimationStep, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP, newEstimationStep, newEstimationStep));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public msteps_SimulationStep getSimulationStep()
  {
    return simulationStep;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSimulationStep(msteps_SimulationStep newSimulationStep, NotificationChain msgs)
  {
    msteps_SimulationStep oldSimulationStep = simulationStep;
    simulationStep = newSimulationStep;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP, oldSimulationStep, newSimulationStep);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSimulationStep(msteps_SimulationStep newSimulationStep)
  {
    if (newSimulationStep != simulationStep)
    {
      NotificationChain msgs = null;
      if (simulationStep != null)
        msgs = ((InternalEObject)simulationStep).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP, null, msgs);
      if (newSimulationStep != null)
        msgs = ((InternalEObject)newSimulationStep).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP, null, msgs);
      msgs = basicSetSimulationStep(newSimulationStep, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP, newSimulationStep, newSimulationStep));
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP:
        return basicSetEstimationStep(null, msgs);
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP:
        return basicSetSimulationStep(null, msgs);
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP:
        return getEstimationStep();
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP:
        return getSimulationStep();
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP:
        setEstimationStep((msteps_EstimationStep)newValue);
        return;
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP:
        setSimulationStep((msteps_SimulationStep)newValue);
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP:
        setEstimationStep((msteps_EstimationStep)null);
        return;
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP:
        setSimulationStep((msteps_SimulationStep)null);
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__ESTIMATION_STEP:
        return estimationStep != null;
      case PharmMLPackage.MSTEPS_MODELING_STEPS_CONTENT__SIMULATION_STEP:
        return simulationStep != null;
    }
    return super.eIsSet(featureID);
  }

} //msteps_ModelingStepsContentImpl

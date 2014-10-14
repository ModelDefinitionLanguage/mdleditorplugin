/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_VariableDefinitionType;
import eu.ddmore.pml.pharmML.msteps_ModelingSteps;
import eu.ddmore.pml.pharmML.msteps_ModelingStepsContent;
import eu.ddmore.pml.pharmML.msteps_StepDependenciesType;

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
 * An implementation of the model object '<em><b>msteps Modeling Steps</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ModelingStepsImpl#getStepDependencies <em>Step Dependencies</em>}</li>
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
  protected EList<XS_xmlns> xmlns;

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
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected EList<msteps_ModelingStepsContent> expr;

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
    return PharmMLPackage.eINSTANCE.getmsteps_ModelingSteps();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MSTEPS_MODELING_STEPS__XMLNS);
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
      variable = new EObjectContainmentEList<ct_VariableDefinitionType>(ct_VariableDefinitionType.class, this, PharmMLPackage.MSTEPS_MODELING_STEPS__VARIABLE);
    }
    return variable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_ModelingStepsContent> getExpr()
  {
    if (expr == null)
    {
      expr = new EObjectContainmentEList<msteps_ModelingStepsContent>(msteps_ModelingStepsContent.class, this, PharmMLPackage.MSTEPS_MODELING_STEPS__EXPR);
    }
    return expr;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES, oldStepDependencies, newStepDependencies);
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
        msgs = ((InternalEObject)stepDependencies).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES, null, msgs);
      if (newStepDependencies != null)
        msgs = ((InternalEObject)newStepDependencies).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES, null, msgs);
      msgs = basicSetStepDependencies(newStepDependencies, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES, newStepDependencies, newStepDependencies));
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        return ((InternalEList<?>)getVariable()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_MODELING_STEPS__EXPR:
        return ((InternalEList<?>)getExpr()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        return getXmlns();
      case PharmMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        return getVariable();
      case PharmMLPackage.MSTEPS_MODELING_STEPS__EXPR:
        return getExpr();
      case PharmMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        getVariable().clear();
        getVariable().addAll((Collection<? extends ct_VariableDefinitionType>)newValue);
        return;
      case PharmMLPackage.MSTEPS_MODELING_STEPS__EXPR:
        getExpr().clear();
        getExpr().addAll((Collection<? extends msteps_ModelingStepsContent>)newValue);
        return;
      case PharmMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        getVariable().clear();
        return;
      case PharmMLPackage.MSTEPS_MODELING_STEPS__EXPR:
        getExpr().clear();
        return;
      case PharmMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
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
      case PharmMLPackage.MSTEPS_MODELING_STEPS__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MSTEPS_MODELING_STEPS__VARIABLE:
        return variable != null && !variable.isEmpty();
      case PharmMLPackage.MSTEPS_MODELING_STEPS__EXPR:
        return expr != null && !expr.isEmpty();
      case PharmMLPackage.MSTEPS_MODELING_STEPS__STEP_DEPENDENCIES:
        return stepDependencies != null;
    }
    return super.eIsSet(featureID);
  }

} //msteps_ModelingStepsImpl

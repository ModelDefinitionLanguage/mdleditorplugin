/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.msteps_DependantStepType;
import eu.ddmore.pml.pharmML.msteps_StepType;

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
 * An implementation of the model object '<em><b>msteps Step Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_StepTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_StepTypeImpl#getIdRef <em>Id Ref</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_StepTypeImpl#getDependantStep <em>Dependant Step</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_StepTypeImpl extends MinimalEObjectImpl.Container implements msteps_StepType
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
   * The default value of the '{@link #getIdRef() <em>Id Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdRef()
   * @generated
   * @ordered
   */
  protected static final String ID_REF_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIdRef() <em>Id Ref</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdRef()
   * @generated
   * @ordered
   */
  protected String idRef = ID_REF_EDEFAULT;

  /**
   * The cached value of the '{@link #getDependantStep() <em>Dependant Step</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDependantStep()
   * @generated
   * @ordered
   */
  protected EList<msteps_DependantStepType> dependantStep;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_StepTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmsteps_StepType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MSTEPS_STEP_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIdRef()
  {
    return idRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdRef(String newIdRef)
  {
    String oldIdRef = idRef;
    idRef = newIdRef;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_STEP_TYPE__ID_REF, oldIdRef, idRef));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_DependantStepType> getDependantStep()
  {
    if (dependantStep == null)
    {
      dependantStep = new EObjectContainmentEList<msteps_DependantStepType>(msteps_DependantStepType.class, this, PharmMLPackage.MSTEPS_STEP_TYPE__DEPENDANT_STEP);
    }
    return dependantStep;
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
      case PharmMLPackage.MSTEPS_STEP_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_STEP_TYPE__DEPENDANT_STEP:
        return ((InternalEList<?>)getDependantStep()).basicRemove(otherEnd, msgs);
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
      case PharmMLPackage.MSTEPS_STEP_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MSTEPS_STEP_TYPE__ID_REF:
        return getIdRef();
      case PharmMLPackage.MSTEPS_STEP_TYPE__DEPENDANT_STEP:
        return getDependantStep();
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
      case PharmMLPackage.MSTEPS_STEP_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MSTEPS_STEP_TYPE__ID_REF:
        setIdRef((String)newValue);
        return;
      case PharmMLPackage.MSTEPS_STEP_TYPE__DEPENDANT_STEP:
        getDependantStep().clear();
        getDependantStep().addAll((Collection<? extends msteps_DependantStepType>)newValue);
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
      case PharmMLPackage.MSTEPS_STEP_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MSTEPS_STEP_TYPE__ID_REF:
        setIdRef(ID_REF_EDEFAULT);
        return;
      case PharmMLPackage.MSTEPS_STEP_TYPE__DEPENDANT_STEP:
        getDependantStep().clear();
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
      case PharmMLPackage.MSTEPS_STEP_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MSTEPS_STEP_TYPE__ID_REF:
        return ID_REF_EDEFAULT == null ? idRef != null : !ID_REF_EDEFAULT.equals(idRef);
      case PharmMLPackage.MSTEPS_STEP_TYPE__DEPENDANT_STEP:
        return dependantStep != null && !dependantStep.isEmpty();
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
    result.append(" (idRef: ");
    result.append(idRef);
    result.append(')');
    return result.toString();
  }

} //msteps_StepTypeImpl

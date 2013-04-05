/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.design_GroupType;
import eu.ddmore.pml.pharmML.design_TreatmentEpohType;
import eu.ddmore.pml.pharmML.design_TreatmentType;
import eu.ddmore.pml.pharmML.design_TrialDesignType;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>design Trial Design Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TrialDesignTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TrialDesignTypeImpl#getTreatment <em>Treatment</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TrialDesignTypeImpl#getTreatmentEpoch <em>Treatment Epoch</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.design_TrialDesignTypeImpl#getGroup <em>Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class design_TrialDesignTypeImpl extends MinimalEObjectImpl.Container implements design_TrialDesignType
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
   * The cached value of the '{@link #getTreatment() <em>Treatment</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTreatment()
   * @generated
   * @ordered
   */
  protected EList<design_TreatmentType> treatment;

  /**
   * The cached value of the '{@link #getTreatmentEpoch() <em>Treatment Epoch</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTreatmentEpoch()
   * @generated
   * @ordered
   */
  protected EList<design_TreatmentEpohType> treatmentEpoch;

  /**
   * The cached value of the '{@link #getGroup() <em>Group</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroup()
   * @generated
   * @ordered
   */
  protected EList<design_GroupType> group;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected design_TrialDesignTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getdesign_TrialDesignType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<design_TreatmentType> getTreatment()
  {
    if (treatment == null)
    {
      treatment = new EObjectContainmentEList<design_TreatmentType>(design_TreatmentType.class, this, PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT);
    }
    return treatment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<design_TreatmentEpohType> getTreatmentEpoch()
  {
    if (treatmentEpoch == null)
    {
      treatmentEpoch = new EObjectContainmentEList<design_TreatmentEpohType>(design_TreatmentEpohType.class, this, PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT_EPOCH);
    }
    return treatmentEpoch;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<design_GroupType> getGroup()
  {
    if (group == null)
    {
      group = new EObjectContainmentEList<design_GroupType>(design_GroupType.class, this, PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__GROUP);
    }
    return group;
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
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT:
        return ((InternalEList<?>)getTreatment()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT_EPOCH:
        return ((InternalEList<?>)getTreatmentEpoch()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__GROUP:
        return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
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
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT:
        return getTreatment();
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT_EPOCH:
        return getTreatmentEpoch();
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__GROUP:
        return getGroup();
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
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT:
        getTreatment().clear();
        getTreatment().addAll((Collection<? extends design_TreatmentType>)newValue);
        return;
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT_EPOCH:
        getTreatmentEpoch().clear();
        getTreatmentEpoch().addAll((Collection<? extends design_TreatmentEpohType>)newValue);
        return;
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__GROUP:
        getGroup().clear();
        getGroup().addAll((Collection<? extends design_GroupType>)newValue);
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
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT:
        getTreatment().clear();
        return;
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT_EPOCH:
        getTreatmentEpoch().clear();
        return;
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__GROUP:
        getGroup().clear();
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
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT:
        return treatment != null && !treatment.isEmpty();
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__TREATMENT_EPOCH:
        return treatmentEpoch != null && !treatmentEpoch.isEmpty();
      case PharmMLPackage.DESIGN_TRIAL_DESIGN_TYPE__GROUP:
        return group != null && !group.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //design_TrialDesignTypeImpl

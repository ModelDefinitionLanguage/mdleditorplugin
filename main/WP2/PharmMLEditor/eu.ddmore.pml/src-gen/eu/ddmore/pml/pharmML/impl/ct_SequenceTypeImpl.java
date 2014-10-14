/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_SequenceType;

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
 * An implementation of the model object '<em><b>ct Sequence Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_SequenceTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_SequenceTypeImpl#getBegin <em>Begin</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_SequenceTypeImpl#getEnd <em>End</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_SequenceTypeImpl#getStepwise <em>Stepwise</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_SequenceTypeImpl#getRepetition <em>Repetition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_SequenceTypeImpl extends MinimalEObjectImpl.Container implements ct_SequenceType
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
   * The default value of the '{@link #getBegin() <em>Begin</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBegin()
   * @generated
   * @ordered
   */
  protected static final String BEGIN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBegin() <em>Begin</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBegin()
   * @generated
   * @ordered
   */
  protected String begin = BEGIN_EDEFAULT;

  /**
   * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected static final String END_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEnd() <em>End</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEnd()
   * @generated
   * @ordered
   */
  protected String end = END_EDEFAULT;

  /**
   * The default value of the '{@link #getStepwise() <em>Stepwise</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStepwise()
   * @generated
   * @ordered
   */
  protected static final String STEPWISE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStepwise() <em>Stepwise</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStepwise()
   * @generated
   * @ordered
   */
  protected String stepwise = STEPWISE_EDEFAULT;

  /**
   * The default value of the '{@link #getRepetition() <em>Repetition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRepetition()
   * @generated
   * @ordered
   */
  protected static final String REPETITION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRepetition() <em>Repetition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRepetition()
   * @generated
   * @ordered
   */
  protected String repetition = REPETITION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_SequenceTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getct_SequenceType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.CT_SEQUENCE_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBegin()
  {
    return begin;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBegin(String newBegin)
  {
    String oldBegin = begin;
    begin = newBegin;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SEQUENCE_TYPE__BEGIN, oldBegin, begin));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEnd()
  {
    return end;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnd(String newEnd)
  {
    String oldEnd = end;
    end = newEnd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SEQUENCE_TYPE__END, oldEnd, end));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStepwise()
  {
    return stepwise;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStepwise(String newStepwise)
  {
    String oldStepwise = stepwise;
    stepwise = newStepwise;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SEQUENCE_TYPE__STEPWISE, oldStepwise, stepwise));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRepetition()
  {
    return repetition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRepetition(String newRepetition)
  {
    String oldRepetition = repetition;
    repetition = newRepetition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_SEQUENCE_TYPE__REPETITION, oldRepetition, repetition));
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
      case PharmMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
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
      case PharmMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.CT_SEQUENCE_TYPE__BEGIN:
        return getBegin();
      case PharmMLPackage.CT_SEQUENCE_TYPE__END:
        return getEnd();
      case PharmMLPackage.CT_SEQUENCE_TYPE__STEPWISE:
        return getStepwise();
      case PharmMLPackage.CT_SEQUENCE_TYPE__REPETITION:
        return getRepetition();
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
      case PharmMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.CT_SEQUENCE_TYPE__BEGIN:
        setBegin((String)newValue);
        return;
      case PharmMLPackage.CT_SEQUENCE_TYPE__END:
        setEnd((String)newValue);
        return;
      case PharmMLPackage.CT_SEQUENCE_TYPE__STEPWISE:
        setStepwise((String)newValue);
        return;
      case PharmMLPackage.CT_SEQUENCE_TYPE__REPETITION:
        setRepetition((String)newValue);
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
      case PharmMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.CT_SEQUENCE_TYPE__BEGIN:
        setBegin(BEGIN_EDEFAULT);
        return;
      case PharmMLPackage.CT_SEQUENCE_TYPE__END:
        setEnd(END_EDEFAULT);
        return;
      case PharmMLPackage.CT_SEQUENCE_TYPE__STEPWISE:
        setStepwise(STEPWISE_EDEFAULT);
        return;
      case PharmMLPackage.CT_SEQUENCE_TYPE__REPETITION:
        setRepetition(REPETITION_EDEFAULT);
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
      case PharmMLPackage.CT_SEQUENCE_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.CT_SEQUENCE_TYPE__BEGIN:
        return BEGIN_EDEFAULT == null ? begin != null : !BEGIN_EDEFAULT.equals(begin);
      case PharmMLPackage.CT_SEQUENCE_TYPE__END:
        return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
      case PharmMLPackage.CT_SEQUENCE_TYPE__STEPWISE:
        return STEPWISE_EDEFAULT == null ? stepwise != null : !STEPWISE_EDEFAULT.equals(stepwise);
      case PharmMLPackage.CT_SEQUENCE_TYPE__REPETITION:
        return REPETITION_EDEFAULT == null ? repetition != null : !REPETITION_EDEFAULT.equals(repetition);
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
    result.append(" (begin: ");
    result.append(begin);
    result.append(", end: ");
    result.append(end);
    result.append(", stepwise: ");
    result.append(stepwise);
    result.append(", repetition: ");
    result.append(repetition);
    result.append(')');
    return result.toString();
  }

} //ct_SequenceTypeImpl

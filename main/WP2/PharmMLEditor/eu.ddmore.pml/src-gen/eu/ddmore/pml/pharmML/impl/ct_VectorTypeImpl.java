/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_ScalarType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_SequenceType;
import eu.ddmore.pml.pharmML.ct_VectorType;

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
 * An implementation of the model object '<em><b>ct Vector Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_VectorTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_VectorTypeImpl#getScalar <em>Scalar</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_VectorTypeImpl#getSequence <em>Sequence</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_VectorTypeImpl extends MinimalEObjectImpl.Container implements ct_VectorType
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
   * The cached value of the '{@link #getScalar() <em>Scalar</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScalar()
   * @generated
   * @ordered
   */
  protected EList<Math_ScalarType> scalar;

  /**
   * The cached value of the '{@link #getSequence() <em>Sequence</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSequence()
   * @generated
   * @ordered
   */
  protected EList<ct_SequenceType> sequence;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_VectorTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getct_VectorType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.CT_VECTOR_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Math_ScalarType> getScalar()
  {
    if (scalar == null)
    {
      scalar = new EObjectContainmentEList<Math_ScalarType>(Math_ScalarType.class, this, PharmMLPackage.CT_VECTOR_TYPE__SCALAR);
    }
    return scalar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ct_SequenceType> getSequence()
  {
    if (sequence == null)
    {
      sequence = new EObjectContainmentEList<ct_SequenceType>(ct_SequenceType.class, this, PharmMLPackage.CT_VECTOR_TYPE__SEQUENCE);
    }
    return sequence;
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
      case PharmMLPackage.CT_VECTOR_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.CT_VECTOR_TYPE__SCALAR:
        return ((InternalEList<?>)getScalar()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.CT_VECTOR_TYPE__SEQUENCE:
        return ((InternalEList<?>)getSequence()).basicRemove(otherEnd, msgs);
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
      case PharmMLPackage.CT_VECTOR_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.CT_VECTOR_TYPE__SCALAR:
        return getScalar();
      case PharmMLPackage.CT_VECTOR_TYPE__SEQUENCE:
        return getSequence();
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
      case PharmMLPackage.CT_VECTOR_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.CT_VECTOR_TYPE__SCALAR:
        getScalar().clear();
        getScalar().addAll((Collection<? extends Math_ScalarType>)newValue);
        return;
      case PharmMLPackage.CT_VECTOR_TYPE__SEQUENCE:
        getSequence().clear();
        getSequence().addAll((Collection<? extends ct_SequenceType>)newValue);
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
      case PharmMLPackage.CT_VECTOR_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.CT_VECTOR_TYPE__SCALAR:
        getScalar().clear();
        return;
      case PharmMLPackage.CT_VECTOR_TYPE__SEQUENCE:
        getSequence().clear();
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
      case PharmMLPackage.CT_VECTOR_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.CT_VECTOR_TYPE__SCALAR:
        return scalar != null && !scalar.isEmpty();
      case PharmMLPackage.CT_VECTOR_TYPE__SEQUENCE:
        return sequence != null && !sequence.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //ct_VectorTypeImpl

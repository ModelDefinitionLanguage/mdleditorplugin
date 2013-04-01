/**
 */
package org.ddmore.pml.pharmaML.impl;

import java.util.Collection;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_writtenVersion;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.uncert_DistributionType;
import org.ddmore.pml.pharmaML.uncert_Normal;
import org.ddmore.pml.pharmaML.uncert_PDF;
import org.ddmore.pml.pharmaML.uncert_Poison;
import org.ddmore.pml.pharmaML.uncert_Student_t;
import org.ddmore.pml.pharmaML.uncert_Uniform;

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
 * An implementation of the model object '<em><b>uncert Distribution Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.uncert_DistributionTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.uncert_DistributionTypeImpl#getWrittenVersion <em>Written Version</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.uncert_DistributionTypeImpl#getNormal <em>Normal</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.uncert_DistributionTypeImpl#getPDF <em>PDF</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.uncert_DistributionTypeImpl#getPoison <em>Poison</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.uncert_DistributionTypeImpl#getStudent <em>Student</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.uncert_DistributionTypeImpl#getUniform <em>Uniform</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class uncert_DistributionTypeImpl extends MinimalEObjectImpl.Container implements uncert_DistributionType
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
   * The cached value of the '{@link #getWrittenVersion() <em>Written Version</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWrittenVersion()
   * @generated
   * @ordered
   */
  protected ct_writtenVersion writtenVersion;

  /**
   * The cached value of the '{@link #getNormal() <em>Normal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNormal()
   * @generated
   * @ordered
   */
  protected uncert_Normal normal;

  /**
   * The cached value of the '{@link #getPDF() <em>PDF</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPDF()
   * @generated
   * @ordered
   */
  protected uncert_PDF pdf;

  /**
   * The cached value of the '{@link #getPoison() <em>Poison</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPoison()
   * @generated
   * @ordered
   */
  protected uncert_Poison poison;

  /**
   * The cached value of the '{@link #getStudent() <em>Student</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStudent()
   * @generated
   * @ordered
   */
  protected uncert_Student_t student;

  /**
   * The cached value of the '{@link #getUniform() <em>Uniform</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUniform()
   * @generated
   * @ordered
   */
  protected uncert_Uniform uniform;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected uncert_DistributionTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getuncert_DistributionType();
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
      xmlns = new EObjectContainmentEList<ct_xmlns>(ct_xmlns.class, this, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__XMLNS);
    }
    return xmlns;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION, oldWrittenVersion, newWrittenVersion);
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
        msgs = ((InternalEObject)writtenVersion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION, null, msgs);
      if (newWrittenVersion != null)
        msgs = ((InternalEObject)newWrittenVersion).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION, null, msgs);
      msgs = basicSetWrittenVersion(newWrittenVersion, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION, newWrittenVersion, newWrittenVersion));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Normal getNormal()
  {
    return normal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNormal(uncert_Normal newNormal, NotificationChain msgs)
  {
    uncert_Normal oldNormal = normal;
    normal = newNormal;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__NORMAL, oldNormal, newNormal);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNormal(uncert_Normal newNormal)
  {
    if (newNormal != normal)
    {
      NotificationChain msgs = null;
      if (normal != null)
        msgs = ((InternalEObject)normal).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__NORMAL, null, msgs);
      if (newNormal != null)
        msgs = ((InternalEObject)newNormal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__NORMAL, null, msgs);
      msgs = basicSetNormal(newNormal, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__NORMAL, newNormal, newNormal));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_PDF getPDF()
  {
    return pdf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPDF(uncert_PDF newPDF, NotificationChain msgs)
  {
    uncert_PDF oldPDF = pdf;
    pdf = newPDF;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__PDF, oldPDF, newPDF);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPDF(uncert_PDF newPDF)
  {
    if (newPDF != pdf)
    {
      NotificationChain msgs = null;
      if (pdf != null)
        msgs = ((InternalEObject)pdf).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__PDF, null, msgs);
      if (newPDF != null)
        msgs = ((InternalEObject)newPDF).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__PDF, null, msgs);
      msgs = basicSetPDF(newPDF, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__PDF, newPDF, newPDF));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Poison getPoison()
  {
    return poison;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPoison(uncert_Poison newPoison, NotificationChain msgs)
  {
    uncert_Poison oldPoison = poison;
    poison = newPoison;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__POISON, oldPoison, newPoison);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPoison(uncert_Poison newPoison)
  {
    if (newPoison != poison)
    {
      NotificationChain msgs = null;
      if (poison != null)
        msgs = ((InternalEObject)poison).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__POISON, null, msgs);
      if (newPoison != null)
        msgs = ((InternalEObject)newPoison).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__POISON, null, msgs);
      msgs = basicSetPoison(newPoison, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__POISON, newPoison, newPoison));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Student_t getStudent()
  {
    return student;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStudent(uncert_Student_t newStudent, NotificationChain msgs)
  {
    uncert_Student_t oldStudent = student;
    student = newStudent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__STUDENT, oldStudent, newStudent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStudent(uncert_Student_t newStudent)
  {
    if (newStudent != student)
    {
      NotificationChain msgs = null;
      if (student != null)
        msgs = ((InternalEObject)student).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__STUDENT, null, msgs);
      if (newStudent != null)
        msgs = ((InternalEObject)newStudent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__STUDENT, null, msgs);
      msgs = basicSetStudent(newStudent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__STUDENT, newStudent, newStudent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Uniform getUniform()
  {
    return uniform;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUniform(uncert_Uniform newUniform, NotificationChain msgs)
  {
    uncert_Uniform oldUniform = uniform;
    uniform = newUniform;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__UNIFORM, oldUniform, newUniform);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUniform(uncert_Uniform newUniform)
  {
    if (newUniform != uniform)
    {
      NotificationChain msgs = null;
      if (uniform != null)
        msgs = ((InternalEObject)uniform).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__UNIFORM, null, msgs);
      if (newUniform != null)
        msgs = ((InternalEObject)newUniform).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__UNIFORM, null, msgs);
      msgs = basicSetUniform(newUniform, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__UNIFORM, newUniform, newUniform));
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
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION:
        return basicSetWrittenVersion(null, msgs);
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__NORMAL:
        return basicSetNormal(null, msgs);
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__PDF:
        return basicSetPDF(null, msgs);
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__POISON:
        return basicSetPoison(null, msgs);
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__STUDENT:
        return basicSetStudent(null, msgs);
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__UNIFORM:
        return basicSetUniform(null, msgs);
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
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__XMLNS:
        return getXmlns();
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION:
        return getWrittenVersion();
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__NORMAL:
        return getNormal();
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__PDF:
        return getPDF();
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__POISON:
        return getPoison();
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__STUDENT:
        return getStudent();
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__UNIFORM:
        return getUniform();
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
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends ct_xmlns>)newValue);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION:
        setWrittenVersion((ct_writtenVersion)newValue);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__NORMAL:
        setNormal((uncert_Normal)newValue);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__PDF:
        setPDF((uncert_PDF)newValue);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__POISON:
        setPoison((uncert_Poison)newValue);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__STUDENT:
        setStudent((uncert_Student_t)newValue);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__UNIFORM:
        setUniform((uncert_Uniform)newValue);
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
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION:
        setWrittenVersion((ct_writtenVersion)null);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__NORMAL:
        setNormal((uncert_Normal)null);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__PDF:
        setPDF((uncert_PDF)null);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__POISON:
        setPoison((uncert_Poison)null);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__STUDENT:
        setStudent((uncert_Student_t)null);
        return;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__UNIFORM:
        setUniform((uncert_Uniform)null);
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
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__WRITTEN_VERSION:
        return writtenVersion != null;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__NORMAL:
        return normal != null;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__PDF:
        return pdf != null;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__POISON:
        return poison != null;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__STUDENT:
        return student != null;
      case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE__UNIFORM:
        return uniform != null;
    }
    return super.eIsSet(featureID);
  }

} //uncert_DistributionTypeImpl

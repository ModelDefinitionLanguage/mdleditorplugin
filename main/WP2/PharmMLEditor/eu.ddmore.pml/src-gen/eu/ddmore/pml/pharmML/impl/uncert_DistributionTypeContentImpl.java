/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.uncert_DistributionTypeContent;
import eu.ddmore.pml.pharmML.uncert_Normal;
import eu.ddmore.pml.pharmML.uncert_PDF;
import eu.ddmore.pml.pharmML.uncert_Poison;
import eu.ddmore.pml.pharmML.uncert_Student_t;
import eu.ddmore.pml.pharmML.uncert_Uniform;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>uncert Distribution Type Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_DistributionTypeContentImpl#getNormal <em>Normal</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_DistributionTypeContentImpl#getPDF <em>PDF</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_DistributionTypeContentImpl#getPoison <em>Poison</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_DistributionTypeContentImpl#getStudent <em>Student</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_DistributionTypeContentImpl#getUniform <em>Uniform</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class uncert_DistributionTypeContentImpl extends MinimalEObjectImpl.Container implements uncert_DistributionTypeContent
{
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
  protected uncert_DistributionTypeContentImpl()
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
    return PharmMLPackage.eINSTANCE.getuncert_DistributionTypeContent();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL, oldNormal, newNormal);
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
        msgs = ((InternalEObject)normal).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL, null, msgs);
      if (newNormal != null)
        msgs = ((InternalEObject)newNormal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL, null, msgs);
      msgs = basicSetNormal(newNormal, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL, newNormal, newNormal));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF, oldPDF, newPDF);
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
        msgs = ((InternalEObject)pdf).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF, null, msgs);
      if (newPDF != null)
        msgs = ((InternalEObject)newPDF).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF, null, msgs);
      msgs = basicSetPDF(newPDF, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF, newPDF, newPDF));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON, oldPoison, newPoison);
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
        msgs = ((InternalEObject)poison).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON, null, msgs);
      if (newPoison != null)
        msgs = ((InternalEObject)newPoison).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON, null, msgs);
      msgs = basicSetPoison(newPoison, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON, newPoison, newPoison));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT, oldStudent, newStudent);
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
        msgs = ((InternalEObject)student).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT, null, msgs);
      if (newStudent != null)
        msgs = ((InternalEObject)newStudent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT, null, msgs);
      msgs = basicSetStudent(newStudent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT, newStudent, newStudent));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM, oldUniform, newUniform);
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
        msgs = ((InternalEObject)uniform).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM, null, msgs);
      if (newUniform != null)
        msgs = ((InternalEObject)newUniform).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM, null, msgs);
      msgs = basicSetUniform(newUniform, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM, newUniform, newUniform));
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
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL:
        return basicSetNormal(null, msgs);
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF:
        return basicSetPDF(null, msgs);
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON:
        return basicSetPoison(null, msgs);
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT:
        return basicSetStudent(null, msgs);
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM:
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
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL:
        return getNormal();
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF:
        return getPDF();
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON:
        return getPoison();
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT:
        return getStudent();
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM:
        return getUniform();
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
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL:
        setNormal((uncert_Normal)newValue);
        return;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF:
        setPDF((uncert_PDF)newValue);
        return;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON:
        setPoison((uncert_Poison)newValue);
        return;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT:
        setStudent((uncert_Student_t)newValue);
        return;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM:
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
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL:
        setNormal((uncert_Normal)null);
        return;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF:
        setPDF((uncert_PDF)null);
        return;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON:
        setPoison((uncert_Poison)null);
        return;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT:
        setStudent((uncert_Student_t)null);
        return;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM:
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
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__NORMAL:
        return normal != null;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__PDF:
        return pdf != null;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__POISON:
        return poison != null;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__STUDENT:
        return student != null;
      case PharmMLPackage.UNCERT_DISTRIBUTION_TYPE_CONTENT__UNIFORM:
        return uniform != null;
    }
    return super.eIsSet(featureID);
  }

} //uncert_DistributionTypeContentImpl

/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_EquationType;
import eu.ddmore.pml.pharmML.Math_EquationTypeContent;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_writtenVersion;

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
 * An implementation of the model object '<em><b>Math Equation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeImpl#getWrittenVersion <em>Written Version</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EquationTypeImpl#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_EquationTypeImpl extends MinimalEObjectImpl.Container implements Math_EquationType
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
   * The cached value of the '{@link #getWrittenVersion() <em>Written Version</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWrittenVersion()
   * @generated
   * @ordered
   */
  protected ct_writtenVersion writtenVersion;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected Math_EquationTypeContent expr;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_EquationTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_EquationType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MATH_EQUATION_TYPE__XMLNS);
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE__WRITTEN_VERSION, oldWrittenVersion, newWrittenVersion);
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
        msgs = ((InternalEObject)writtenVersion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE__WRITTEN_VERSION, null, msgs);
      if (newWrittenVersion != null)
        msgs = ((InternalEObject)newWrittenVersion).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE__WRITTEN_VERSION, null, msgs);
      msgs = basicSetWrittenVersion(newWrittenVersion, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE__WRITTEN_VERSION, newWrittenVersion, newWrittenVersion));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_EquationTypeContent getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(Math_EquationTypeContent newExpr, NotificationChain msgs)
  {
    Math_EquationTypeContent oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpr(Math_EquationTypeContent newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MATH_EQUATION_TYPE__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_EQUATION_TYPE__EXPR, newExpr, newExpr));
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
      case PharmMLPackage.MATH_EQUATION_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MATH_EQUATION_TYPE__WRITTEN_VERSION:
        return basicSetWrittenVersion(null, msgs);
      case PharmMLPackage.MATH_EQUATION_TYPE__EXPR:
        return basicSetExpr(null, msgs);
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
      case PharmMLPackage.MATH_EQUATION_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MATH_EQUATION_TYPE__WRITTEN_VERSION:
        return getWrittenVersion();
      case PharmMLPackage.MATH_EQUATION_TYPE__EXPR:
        return getExpr();
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
      case PharmMLPackage.MATH_EQUATION_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE__WRITTEN_VERSION:
        setWrittenVersion((ct_writtenVersion)newValue);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE__EXPR:
        setExpr((Math_EquationTypeContent)newValue);
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
      case PharmMLPackage.MATH_EQUATION_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE__WRITTEN_VERSION:
        setWrittenVersion((ct_writtenVersion)null);
        return;
      case PharmMLPackage.MATH_EQUATION_TYPE__EXPR:
        setExpr((Math_EquationTypeContent)null);
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
      case PharmMLPackage.MATH_EQUATION_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MATH_EQUATION_TYPE__WRITTEN_VERSION:
        return writtenVersion != null;
      case PharmMLPackage.MATH_EQUATION_TYPE__EXPR:
        return expr != null;
    }
    return super.eIsSet(featureID);
  }

} //Math_EquationTypeImpl

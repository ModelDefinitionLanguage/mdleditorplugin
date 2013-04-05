/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_EquationType;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.uncert_PDF;
import eu.ddmore.pml.pharmML.uncert_ParameterDefinitionType;

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
 * An implementation of the model object '<em><b>uncert PDF</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_PDFImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_PDFImpl#getFunctionParameter <em>Function Parameter</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_PDFImpl#getEquation <em>Equation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class uncert_PDFImpl extends MinimalEObjectImpl.Container implements uncert_PDF
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
   * The cached value of the '{@link #getFunctionParameter() <em>Function Parameter</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionParameter()
   * @generated
   * @ordered
   */
  protected EList<uncert_ParameterDefinitionType> functionParameter;

  /**
   * The cached value of the '{@link #getEquation() <em>Equation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEquation()
   * @generated
   * @ordered
   */
  protected Math_EquationType equation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected uncert_PDFImpl()
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
    return PharmMLPackage.eINSTANCE.getuncert_PDF();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.UNCERT_PDF__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<uncert_ParameterDefinitionType> getFunctionParameter()
  {
    if (functionParameter == null)
    {
      functionParameter = new EObjectContainmentEList<uncert_ParameterDefinitionType>(uncert_ParameterDefinitionType.class, this, PharmMLPackage.UNCERT_PDF__FUNCTION_PARAMETER);
    }
    return functionParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Math_EquationType getEquation()
  {
    return equation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEquation(Math_EquationType newEquation, NotificationChain msgs)
  {
    Math_EquationType oldEquation = equation;
    equation = newEquation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_PDF__EQUATION, oldEquation, newEquation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEquation(Math_EquationType newEquation)
  {
    if (newEquation != equation)
    {
      NotificationChain msgs = null;
      if (equation != null)
        msgs = ((InternalEObject)equation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_PDF__EQUATION, null, msgs);
      if (newEquation != null)
        msgs = ((InternalEObject)newEquation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_PDF__EQUATION, null, msgs);
      msgs = basicSetEquation(newEquation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_PDF__EQUATION, newEquation, newEquation));
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
      case PharmMLPackage.UNCERT_PDF__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.UNCERT_PDF__FUNCTION_PARAMETER:
        return ((InternalEList<?>)getFunctionParameter()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.UNCERT_PDF__EQUATION:
        return basicSetEquation(null, msgs);
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
      case PharmMLPackage.UNCERT_PDF__XMLNS:
        return getXmlns();
      case PharmMLPackage.UNCERT_PDF__FUNCTION_PARAMETER:
        return getFunctionParameter();
      case PharmMLPackage.UNCERT_PDF__EQUATION:
        return getEquation();
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
      case PharmMLPackage.UNCERT_PDF__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.UNCERT_PDF__FUNCTION_PARAMETER:
        getFunctionParameter().clear();
        getFunctionParameter().addAll((Collection<? extends uncert_ParameterDefinitionType>)newValue);
        return;
      case PharmMLPackage.UNCERT_PDF__EQUATION:
        setEquation((Math_EquationType)newValue);
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
      case PharmMLPackage.UNCERT_PDF__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.UNCERT_PDF__FUNCTION_PARAMETER:
        getFunctionParameter().clear();
        return;
      case PharmMLPackage.UNCERT_PDF__EQUATION:
        setEquation((Math_EquationType)null);
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
      case PharmMLPackage.UNCERT_PDF__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.UNCERT_PDF__FUNCTION_PARAMETER:
        return functionParameter != null && !functionParameter.isEmpty();
      case PharmMLPackage.UNCERT_PDF__EQUATION:
        return equation != null;
    }
    return super.eIsSet(featureID);
  }

} //uncert_PDFImpl

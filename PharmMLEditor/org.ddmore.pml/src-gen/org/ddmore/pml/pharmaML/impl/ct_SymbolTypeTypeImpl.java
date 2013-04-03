/**
 */
package org.ddmore.pml.pharmaML.impl;

import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_SymbolTypeType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ct Symbol Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_SymbolTypeTypeImpl#getScalar <em>Scalar</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_SymbolTypeTypeImpl#getDerivative <em>Derivative</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_SymbolTypeTypeImpl#getDistrib <em>Distrib</em>}</li>
 *   <li>{@link org.ddmore.pml.pharmaML.impl.ct_SymbolTypeTypeImpl#getDataSet <em>Data Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_SymbolTypeTypeImpl extends MinimalEObjectImpl.Container implements ct_SymbolTypeType
{
  /**
   * The default value of the '{@link #getScalar() <em>Scalar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScalar()
   * @generated
   * @ordered
   */
  protected static final String SCALAR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getScalar() <em>Scalar</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScalar()
   * @generated
   * @ordered
   */
  protected String scalar = SCALAR_EDEFAULT;

  /**
   * The default value of the '{@link #getDerivative() <em>Derivative</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDerivative()
   * @generated
   * @ordered
   */
  protected static final String DERIVATIVE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDerivative() <em>Derivative</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDerivative()
   * @generated
   * @ordered
   */
  protected String derivative = DERIVATIVE_EDEFAULT;

  /**
   * The default value of the '{@link #getDistrib() <em>Distrib</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDistrib()
   * @generated
   * @ordered
   */
  protected static final String DISTRIB_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDistrib() <em>Distrib</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDistrib()
   * @generated
   * @ordered
   */
  protected String distrib = DISTRIB_EDEFAULT;

  /**
   * The default value of the '{@link #getDataSet() <em>Data Set</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSet()
   * @generated
   * @ordered
   */
  protected static final String DATA_SET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDataSet() <em>Data Set</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataSet()
   * @generated
   * @ordered
   */
  protected String dataSet = DATA_SET_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_SymbolTypeTypeImpl()
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
    return PharmaMLPackage.eINSTANCE.getct_SymbolTypeType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getScalar()
  {
    return scalar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScalar(String newScalar)
  {
    String oldScalar = scalar;
    scalar = newScalar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__SCALAR, oldScalar, scalar));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDerivative()
  {
    return derivative;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDerivative(String newDerivative)
  {
    String oldDerivative = derivative;
    derivative = newDerivative;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DERIVATIVE, oldDerivative, derivative));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDistrib()
  {
    return distrib;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDistrib(String newDistrib)
  {
    String oldDistrib = distrib;
    distrib = newDistrib;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DISTRIB, oldDistrib, distrib));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDataSet()
  {
    return dataSet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataSet(String newDataSet)
  {
    String oldDataSet = dataSet;
    dataSet = newDataSet;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DATA_SET, oldDataSet, dataSet));
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
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__SCALAR:
        return getScalar();
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DERIVATIVE:
        return getDerivative();
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DISTRIB:
        return getDistrib();
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DATA_SET:
        return getDataSet();
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
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__SCALAR:
        setScalar((String)newValue);
        return;
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DERIVATIVE:
        setDerivative((String)newValue);
        return;
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DISTRIB:
        setDistrib((String)newValue);
        return;
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DATA_SET:
        setDataSet((String)newValue);
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
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__SCALAR:
        setScalar(SCALAR_EDEFAULT);
        return;
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DERIVATIVE:
        setDerivative(DERIVATIVE_EDEFAULT);
        return;
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DISTRIB:
        setDistrib(DISTRIB_EDEFAULT);
        return;
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DATA_SET:
        setDataSet(DATA_SET_EDEFAULT);
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
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__SCALAR:
        return SCALAR_EDEFAULT == null ? scalar != null : !SCALAR_EDEFAULT.equals(scalar);
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DERIVATIVE:
        return DERIVATIVE_EDEFAULT == null ? derivative != null : !DERIVATIVE_EDEFAULT.equals(derivative);
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DISTRIB:
        return DISTRIB_EDEFAULT == null ? distrib != null : !DISTRIB_EDEFAULT.equals(distrib);
      case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE__DATA_SET:
        return DATA_SET_EDEFAULT == null ? dataSet != null : !DATA_SET_EDEFAULT.equals(dataSet);
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
    result.append(" (scalar: ");
    result.append(scalar);
    result.append(", derivative: ");
    result.append(derivative);
    result.append(", distrib: ");
    result.append(distrib);
    result.append(", dataSet: ");
    result.append(dataSet);
    result.append(')');
    return result.toString();
  }

} //ct_SymbolTypeTypeImpl

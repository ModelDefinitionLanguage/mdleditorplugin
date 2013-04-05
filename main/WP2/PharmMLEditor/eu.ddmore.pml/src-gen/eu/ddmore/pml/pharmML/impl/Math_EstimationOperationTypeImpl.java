/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Math_EstimationOperationType;
import eu.ddmore.pml.pharmML.PharmMLPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Math Estimation Operation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EstimationOperationTypeImpl#getEstPop <em>Est Pop</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EstimationOperationTypeImpl#getEstFIM <em>Est FIM</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Math_EstimationOperationTypeImpl#getEstIndiv <em>Est Indiv</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Math_EstimationOperationTypeImpl extends MinimalEObjectImpl.Container implements Math_EstimationOperationType
{
  /**
   * The default value of the '{@link #getEstPop() <em>Est Pop</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstPop()
   * @generated
   * @ordered
   */
  protected static final String EST_POP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEstPop() <em>Est Pop</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstPop()
   * @generated
   * @ordered
   */
  protected String estPop = EST_POP_EDEFAULT;

  /**
   * The default value of the '{@link #getEstFIM() <em>Est FIM</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstFIM()
   * @generated
   * @ordered
   */
  protected static final String EST_FIM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEstFIM() <em>Est FIM</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstFIM()
   * @generated
   * @ordered
   */
  protected String estFIM = EST_FIM_EDEFAULT;

  /**
   * The default value of the '{@link #getEstIndiv() <em>Est Indiv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstIndiv()
   * @generated
   * @ordered
   */
  protected static final String EST_INDIV_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getEstIndiv() <em>Est Indiv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstIndiv()
   * @generated
   * @ordered
   */
  protected String estIndiv = EST_INDIV_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Math_EstimationOperationTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getMath_EstimationOperationType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEstPop()
  {
    return estPop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEstPop(String newEstPop)
  {
    String oldEstPop = estPop;
    estPop = newEstPop;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_POP, oldEstPop, estPop));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEstFIM()
  {
    return estFIM;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEstFIM(String newEstFIM)
  {
    String oldEstFIM = estFIM;
    estFIM = newEstFIM;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_FIM, oldEstFIM, estFIM));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEstIndiv()
  {
    return estIndiv;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEstIndiv(String newEstIndiv)
  {
    String oldEstIndiv = estIndiv;
    estIndiv = newEstIndiv;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_INDIV, oldEstIndiv, estIndiv));
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
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_POP:
        return getEstPop();
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_FIM:
        return getEstFIM();
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_INDIV:
        return getEstIndiv();
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
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_POP:
        setEstPop((String)newValue);
        return;
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_FIM:
        setEstFIM((String)newValue);
        return;
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_INDIV:
        setEstIndiv((String)newValue);
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
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_POP:
        setEstPop(EST_POP_EDEFAULT);
        return;
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_FIM:
        setEstFIM(EST_FIM_EDEFAULT);
        return;
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_INDIV:
        setEstIndiv(EST_INDIV_EDEFAULT);
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
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_POP:
        return EST_POP_EDEFAULT == null ? estPop != null : !EST_POP_EDEFAULT.equals(estPop);
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_FIM:
        return EST_FIM_EDEFAULT == null ? estFIM != null : !EST_FIM_EDEFAULT.equals(estFIM);
      case PharmMLPackage.MATH_ESTIMATION_OPERATION_TYPE__EST_INDIV:
        return EST_INDIV_EDEFAULT == null ? estIndiv != null : !EST_INDIV_EDEFAULT.equals(estIndiv);
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
    result.append(" (estPop: ");
    result.append(estPop);
    result.append(", estFIM: ");
    result.append(estFIM);
    result.append(", estIndiv: ");
    result.append(estIndiv);
    result.append(')');
    return result.toString();
  }

} //Math_EstimationOperationTypeImpl

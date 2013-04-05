/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_ColumnType;

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
 * An implementation of the model object '<em><b>ct Column Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ColumnTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ColumnTypeImpl#getColumnNum <em>Column Num</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ColumnTypeImpl#getColumnVar <em>Column Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_ColumnTypeImpl extends MinimalEObjectImpl.Container implements ct_ColumnType
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
   * The default value of the '{@link #getColumnNum() <em>Column Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnNum()
   * @generated
   * @ordered
   */
  protected static final String COLUMN_NUM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getColumnNum() <em>Column Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnNum()
   * @generated
   * @ordered
   */
  protected String columnNum = COLUMN_NUM_EDEFAULT;

  /**
   * The default value of the '{@link #getColumnVar() <em>Column Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnVar()
   * @generated
   * @ordered
   */
  protected static final String COLUMN_VAR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getColumnVar() <em>Column Var</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnVar()
   * @generated
   * @ordered
   */
  protected String columnVar = COLUMN_VAR_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_ColumnTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getct_ColumnType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.CT_COLUMN_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getColumnNum()
  {
    return columnNum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColumnNum(String newColumnNum)
  {
    String oldColumnNum = columnNum;
    columnNum = newColumnNum;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_COLUMN_TYPE__COLUMN_NUM, oldColumnNum, columnNum));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getColumnVar()
  {
    return columnVar;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColumnVar(String newColumnVar)
  {
    String oldColumnVar = columnVar;
    columnVar = newColumnVar;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_COLUMN_TYPE__COLUMN_VAR, oldColumnVar, columnVar));
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
      case PharmMLPackage.CT_COLUMN_TYPE__XMLNS:
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
      case PharmMLPackage.CT_COLUMN_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.CT_COLUMN_TYPE__COLUMN_NUM:
        return getColumnNum();
      case PharmMLPackage.CT_COLUMN_TYPE__COLUMN_VAR:
        return getColumnVar();
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
      case PharmMLPackage.CT_COLUMN_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.CT_COLUMN_TYPE__COLUMN_NUM:
        setColumnNum((String)newValue);
        return;
      case PharmMLPackage.CT_COLUMN_TYPE__COLUMN_VAR:
        setColumnVar((String)newValue);
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
      case PharmMLPackage.CT_COLUMN_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.CT_COLUMN_TYPE__COLUMN_NUM:
        setColumnNum(COLUMN_NUM_EDEFAULT);
        return;
      case PharmMLPackage.CT_COLUMN_TYPE__COLUMN_VAR:
        setColumnVar(COLUMN_VAR_EDEFAULT);
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
      case PharmMLPackage.CT_COLUMN_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.CT_COLUMN_TYPE__COLUMN_NUM:
        return COLUMN_NUM_EDEFAULT == null ? columnNum != null : !COLUMN_NUM_EDEFAULT.equals(columnNum);
      case PharmMLPackage.CT_COLUMN_TYPE__COLUMN_VAR:
        return COLUMN_VAR_EDEFAULT == null ? columnVar != null : !COLUMN_VAR_EDEFAULT.equals(columnVar);
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
    result.append(" (columnNum: ");
    result.append(columnNum);
    result.append(", columnVar: ");
    result.append(columnVar);
    result.append(')');
    return result.toString();
  }

} //ct_ColumnTypeImpl

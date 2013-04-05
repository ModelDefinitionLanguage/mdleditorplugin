/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.ct_CellType;
import eu.ddmore.pml.pharmML.ct_NullCellType;
import eu.ddmore.pml.pharmML.ct_RowTypeContent;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ct Row Type Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_RowTypeContentImpl#getCell <em>Cell</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_RowTypeContentImpl#getNull <em>Null</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_RowTypeContentImpl extends MinimalEObjectImpl.Container implements ct_RowTypeContent
{
  /**
   * The cached value of the '{@link #getCell() <em>Cell</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCell()
   * @generated
   * @ordered
   */
  protected ct_CellType cell;

  /**
   * The cached value of the '{@link #getNull() <em>Null</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNull()
   * @generated
   * @ordered
   */
  protected ct_NullCellType null_;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_RowTypeContentImpl()
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
    return PharmMLPackage.eINSTANCE.getct_RowTypeContent();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_CellType getCell()
  {
    return cell;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCell(ct_CellType newCell, NotificationChain msgs)
  {
    ct_CellType oldCell = cell;
    cell = newCell;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_ROW_TYPE_CONTENT__CELL, oldCell, newCell);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCell(ct_CellType newCell)
  {
    if (newCell != cell)
    {
      NotificationChain msgs = null;
      if (cell != null)
        msgs = ((InternalEObject)cell).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_ROW_TYPE_CONTENT__CELL, null, msgs);
      if (newCell != null)
        msgs = ((InternalEObject)newCell).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_ROW_TYPE_CONTENT__CELL, null, msgs);
      msgs = basicSetCell(newCell, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_ROW_TYPE_CONTENT__CELL, newCell, newCell));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_NullCellType getNull()
  {
    return null_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNull(ct_NullCellType newNull, NotificationChain msgs)
  {
    ct_NullCellType oldNull = null_;
    null_ = newNull;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_ROW_TYPE_CONTENT__NULL, oldNull, newNull);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNull(ct_NullCellType newNull)
  {
    if (newNull != null_)
    {
      NotificationChain msgs = null;
      if (null_ != null)
        msgs = ((InternalEObject)null_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_ROW_TYPE_CONTENT__NULL, null, msgs);
      if (newNull != null)
        msgs = ((InternalEObject)newNull).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_ROW_TYPE_CONTENT__NULL, null, msgs);
      msgs = basicSetNull(newNull, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_ROW_TYPE_CONTENT__NULL, newNull, newNull));
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
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__CELL:
        return basicSetCell(null, msgs);
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__NULL:
        return basicSetNull(null, msgs);
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
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__CELL:
        return getCell();
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__NULL:
        return getNull();
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
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__CELL:
        setCell((ct_CellType)newValue);
        return;
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__NULL:
        setNull((ct_NullCellType)newValue);
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
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__CELL:
        setCell((ct_CellType)null);
        return;
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__NULL:
        setNull((ct_NullCellType)null);
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
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__CELL:
        return cell != null;
      case PharmMLPackage.CT_ROW_TYPE_CONTENT__NULL:
        return null_ != null;
    }
    return super.eIsSet(featureID);
  }

} //ct_RowTypeContentImpl

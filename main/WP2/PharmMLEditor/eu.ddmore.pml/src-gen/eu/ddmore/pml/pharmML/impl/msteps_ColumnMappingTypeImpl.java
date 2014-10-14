/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_RestrictionType;
import eu.ddmore.pml.pharmML.msteps_ColumnMappingType;
import eu.ddmore.pml.pharmML.msteps_ColumnMappingTypeContent;

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
 * An implementation of the model object '<em><b>msteps Column Mapping Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeImpl#getColumnname <em>Columnname</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.msteps_ColumnMappingTypeImpl#getRestriction <em>Restriction</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class msteps_ColumnMappingTypeImpl extends MinimalEObjectImpl.Container implements msteps_ColumnMappingType
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
   * The default value of the '{@link #getColumnname() <em>Columnname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnname()
   * @generated
   * @ordered
   */
  protected static final String COLUMNNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getColumnname() <em>Columnname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getColumnname()
   * @generated
   * @ordered
   */
  protected String columnname = COLUMNNAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected EList<msteps_ColumnMappingTypeContent> expr;

  /**
   * The cached value of the '{@link #getRestriction() <em>Restriction</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRestriction()
   * @generated
   * @ordered
   */
  protected ct_RestrictionType restriction;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected msteps_ColumnMappingTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmsteps_ColumnMappingType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getColumnname()
  {
    return columnname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setColumnname(String newColumnname)
  {
    String oldColumnname = columnname;
    columnname = newColumnname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__COLUMNNAME, oldColumnname, columnname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<msteps_ColumnMappingTypeContent> getExpr()
  {
    if (expr == null)
    {
      expr = new EObjectContainmentEList<msteps_ColumnMappingTypeContent>(msteps_ColumnMappingTypeContent.class, this, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__EXPR);
    }
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ct_RestrictionType getRestriction()
  {
    return restriction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRestriction(ct_RestrictionType newRestriction, NotificationChain msgs)
  {
    ct_RestrictionType oldRestriction = restriction;
    restriction = newRestriction;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION, oldRestriction, newRestriction);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRestriction(ct_RestrictionType newRestriction)
  {
    if (newRestriction != restriction)
    {
      NotificationChain msgs = null;
      if (restriction != null)
        msgs = ((InternalEObject)restriction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION, null, msgs);
      if (newRestriction != null)
        msgs = ((InternalEObject)newRestriction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION, null, msgs);
      msgs = basicSetRestriction(newRestriction, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION, newRestriction, newRestriction));
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__EXPR:
        return ((InternalEList<?>)getExpr()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION:
        return basicSetRestriction(null, msgs);
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__COLUMNNAME:
        return getColumnname();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__EXPR:
        return getExpr();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION:
        return getRestriction();
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__COLUMNNAME:
        setColumnname((String)newValue);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__EXPR:
        getExpr().clear();
        getExpr().addAll((Collection<? extends msteps_ColumnMappingTypeContent>)newValue);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION:
        setRestriction((ct_RestrictionType)newValue);
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__COLUMNNAME:
        setColumnname(COLUMNNAME_EDEFAULT);
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__EXPR:
        getExpr().clear();
        return;
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION:
        setRestriction((ct_RestrictionType)null);
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
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__COLUMNNAME:
        return COLUMNNAME_EDEFAULT == null ? columnname != null : !COLUMNNAME_EDEFAULT.equals(columnname);
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__EXPR:
        return expr != null && !expr.isEmpty();
      case PharmMLPackage.MSTEPS_COLUMN_MAPPING_TYPE__RESTRICTION:
        return restriction != null;
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
    result.append(" (columnname: ");
    result.append(columnname);
    result.append(')');
    return result.toString();
  }

} //msteps_ColumnMappingTypeImpl

/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.mdef_ImportResourceTypeType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>mdef Import Resource Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ImportResourceTypeTypeImpl#getSbml <em>Sbml</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.mdef_ImportResourceTypeTypeImpl#getPharmml <em>Pharmml</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mdef_ImportResourceTypeTypeImpl extends MinimalEObjectImpl.Container implements mdef_ImportResourceTypeType
{
  /**
   * The default value of the '{@link #getSbml() <em>Sbml</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSbml()
   * @generated
   * @ordered
   */
  protected static final String SBML_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSbml() <em>Sbml</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSbml()
   * @generated
   * @ordered
   */
  protected String sbml = SBML_EDEFAULT;

  /**
   * The default value of the '{@link #getPharmml() <em>Pharmml</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPharmml()
   * @generated
   * @ordered
   */
  protected static final String PHARMML_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPharmml() <em>Pharmml</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPharmml()
   * @generated
   * @ordered
   */
  protected String pharmml = PHARMML_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mdef_ImportResourceTypeTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getmdef_ImportResourceTypeType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSbml()
  {
    return sbml;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSbml(String newSbml)
  {
    String oldSbml = sbml;
    sbml = newSbml;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__SBML, oldSbml, sbml));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPharmml()
  {
    return pharmml;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPharmml(String newPharmml)
  {
    String oldPharmml = pharmml;
    pharmml = newPharmml;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__PHARMML, oldPharmml, pharmml));
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
      case PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__SBML:
        return getSbml();
      case PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__PHARMML:
        return getPharmml();
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
      case PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__SBML:
        setSbml((String)newValue);
        return;
      case PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__PHARMML:
        setPharmml((String)newValue);
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
      case PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__SBML:
        setSbml(SBML_EDEFAULT);
        return;
      case PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__PHARMML:
        setPharmml(PHARMML_EDEFAULT);
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
      case PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__SBML:
        return SBML_EDEFAULT == null ? sbml != null : !SBML_EDEFAULT.equals(sbml);
      case PharmMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE__PHARMML:
        return PHARMML_EDEFAULT == null ? pharmml != null : !PHARMML_EDEFAULT.equals(pharmml);
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
    result.append(" (sbml: ");
    result.append(sbml);
    result.append(", pharmml: ");
    result.append(pharmml);
    result.append(')');
    return result.toString();
  }

} //mdef_ImportResourceTypeTypeImpl

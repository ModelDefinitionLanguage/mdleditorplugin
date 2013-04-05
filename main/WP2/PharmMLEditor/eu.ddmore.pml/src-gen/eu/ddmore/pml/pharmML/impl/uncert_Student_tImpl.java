/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.uncertDegreesOfFreedom;
import eu.ddmore.pml.pharmML.uncert_Location;
import eu.ddmore.pml.pharmML.uncert_Scale;
import eu.ddmore.pml.pharmML.uncert_Student_t;

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
 * An implementation of the model object '<em><b>uncert Student t</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_Student_tImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_Student_tImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_Student_tImpl#getScale <em>Scale</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.uncert_Student_tImpl#getDegreesOfFreedom <em>Degrees Of Freedom</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class uncert_Student_tImpl extends MinimalEObjectImpl.Container implements uncert_Student_t
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
   * The cached value of the '{@link #getLocation() <em>Location</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocation()
   * @generated
   * @ordered
   */
  protected uncert_Location location;

  /**
   * The cached value of the '{@link #getScale() <em>Scale</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScale()
   * @generated
   * @ordered
   */
  protected uncert_Scale scale;

  /**
   * The cached value of the '{@link #getDegreesOfFreedom() <em>Degrees Of Freedom</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDegreesOfFreedom()
   * @generated
   * @ordered
   */
  protected uncertDegreesOfFreedom degreesOfFreedom;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected uncert_Student_tImpl()
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
    return PharmMLPackage.eINSTANCE.getuncert_Student_t();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.UNCERT_STUDENT_T__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Location getLocation()
  {
    return location;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLocation(uncert_Location newLocation, NotificationChain msgs)
  {
    uncert_Location oldLocation = location;
    location = newLocation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_STUDENT_T__LOCATION, oldLocation, newLocation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLocation(uncert_Location newLocation)
  {
    if (newLocation != location)
    {
      NotificationChain msgs = null;
      if (location != null)
        msgs = ((InternalEObject)location).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_STUDENT_T__LOCATION, null, msgs);
      if (newLocation != null)
        msgs = ((InternalEObject)newLocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_STUDENT_T__LOCATION, null, msgs);
      msgs = basicSetLocation(newLocation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_STUDENT_T__LOCATION, newLocation, newLocation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncert_Scale getScale()
  {
    return scale;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetScale(uncert_Scale newScale, NotificationChain msgs)
  {
    uncert_Scale oldScale = scale;
    scale = newScale;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_STUDENT_T__SCALE, oldScale, newScale);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScale(uncert_Scale newScale)
  {
    if (newScale != scale)
    {
      NotificationChain msgs = null;
      if (scale != null)
        msgs = ((InternalEObject)scale).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_STUDENT_T__SCALE, null, msgs);
      if (newScale != null)
        msgs = ((InternalEObject)newScale).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_STUDENT_T__SCALE, null, msgs);
      msgs = basicSetScale(newScale, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_STUDENT_T__SCALE, newScale, newScale));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public uncertDegreesOfFreedom getDegreesOfFreedom()
  {
    return degreesOfFreedom;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDegreesOfFreedom(uncertDegreesOfFreedom newDegreesOfFreedom, NotificationChain msgs)
  {
    uncertDegreesOfFreedom oldDegreesOfFreedom = degreesOfFreedom;
    degreesOfFreedom = newDegreesOfFreedom;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_STUDENT_T__DEGREES_OF_FREEDOM, oldDegreesOfFreedom, newDegreesOfFreedom);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDegreesOfFreedom(uncertDegreesOfFreedom newDegreesOfFreedom)
  {
    if (newDegreesOfFreedom != degreesOfFreedom)
    {
      NotificationChain msgs = null;
      if (degreesOfFreedom != null)
        msgs = ((InternalEObject)degreesOfFreedom).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_STUDENT_T__DEGREES_OF_FREEDOM, null, msgs);
      if (newDegreesOfFreedom != null)
        msgs = ((InternalEObject)newDegreesOfFreedom).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.UNCERT_STUDENT_T__DEGREES_OF_FREEDOM, null, msgs);
      msgs = basicSetDegreesOfFreedom(newDegreesOfFreedom, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.UNCERT_STUDENT_T__DEGREES_OF_FREEDOM, newDegreesOfFreedom, newDegreesOfFreedom));
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
      case PharmMLPackage.UNCERT_STUDENT_T__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.UNCERT_STUDENT_T__LOCATION:
        return basicSetLocation(null, msgs);
      case PharmMLPackage.UNCERT_STUDENT_T__SCALE:
        return basicSetScale(null, msgs);
      case PharmMLPackage.UNCERT_STUDENT_T__DEGREES_OF_FREEDOM:
        return basicSetDegreesOfFreedom(null, msgs);
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
      case PharmMLPackage.UNCERT_STUDENT_T__XMLNS:
        return getXmlns();
      case PharmMLPackage.UNCERT_STUDENT_T__LOCATION:
        return getLocation();
      case PharmMLPackage.UNCERT_STUDENT_T__SCALE:
        return getScale();
      case PharmMLPackage.UNCERT_STUDENT_T__DEGREES_OF_FREEDOM:
        return getDegreesOfFreedom();
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
      case PharmMLPackage.UNCERT_STUDENT_T__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.UNCERT_STUDENT_T__LOCATION:
        setLocation((uncert_Location)newValue);
        return;
      case PharmMLPackage.UNCERT_STUDENT_T__SCALE:
        setScale((uncert_Scale)newValue);
        return;
      case PharmMLPackage.UNCERT_STUDENT_T__DEGREES_OF_FREEDOM:
        setDegreesOfFreedom((uncertDegreesOfFreedom)newValue);
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
      case PharmMLPackage.UNCERT_STUDENT_T__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.UNCERT_STUDENT_T__LOCATION:
        setLocation((uncert_Location)null);
        return;
      case PharmMLPackage.UNCERT_STUDENT_T__SCALE:
        setScale((uncert_Scale)null);
        return;
      case PharmMLPackage.UNCERT_STUDENT_T__DEGREES_OF_FREEDOM:
        setDegreesOfFreedom((uncertDegreesOfFreedom)null);
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
      case PharmMLPackage.UNCERT_STUDENT_T__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.UNCERT_STUDENT_T__LOCATION:
        return location != null;
      case PharmMLPackage.UNCERT_STUDENT_T__SCALE:
        return scale != null;
      case PharmMLPackage.UNCERT_STUDENT_T__DEGREES_OF_FREEDOM:
        return degreesOfFreedom != null;
    }
    return super.eIsSet(featureID);
  }

} //uncert_Student_tImpl

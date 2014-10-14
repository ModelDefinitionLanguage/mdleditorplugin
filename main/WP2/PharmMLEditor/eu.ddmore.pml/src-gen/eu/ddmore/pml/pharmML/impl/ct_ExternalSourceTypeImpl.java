/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.XS_xmlns;
import eu.ddmore.pml.pharmML.ct_ExternalSourceType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>ct External Source Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ExternalSourceTypeImpl#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ExternalSourceTypeImpl#getFormat <em>Format</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ExternalSourceTypeImpl#getHeaderLine <em>Header Line</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ExternalSourceTypeImpl#getIgnoreLineSymbol <em>Ignore Line Symbol</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.ct_ExternalSourceTypeImpl#getUrl <em>Url</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ct_ExternalSourceTypeImpl extends MinimalEObjectImpl.Container implements ct_ExternalSourceType
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
   * The default value of the '{@link #getFormat() <em>Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormat()
   * @generated
   * @ordered
   */
  protected static final String FORMAT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFormat() <em>Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormat()
   * @generated
   * @ordered
   */
  protected String format = FORMAT_EDEFAULT;

  /**
   * The cached value of the '{@link #getHeaderLine() <em>Header Line</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHeaderLine()
   * @generated
   * @ordered
   */
  protected EObject headerLine;

  /**
   * The default value of the '{@link #getIgnoreLineSymbol() <em>Ignore Line Symbol</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIgnoreLineSymbol()
   * @generated
   * @ordered
   */
  protected static final String IGNORE_LINE_SYMBOL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIgnoreLineSymbol() <em>Ignore Line Symbol</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIgnoreLineSymbol()
   * @generated
   * @ordered
   */
  protected String ignoreLineSymbol = IGNORE_LINE_SYMBOL_EDEFAULT;

  /**
   * The default value of the '{@link #getUrl() <em>Url</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUrl()
   * @generated
   * @ordered
   */
  protected static final String URL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUrl() <em>Url</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUrl()
   * @generated
   * @ordered
   */
  protected String url = URL_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ct_ExternalSourceTypeImpl()
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
    return PharmMLPackage.eINSTANCE.getct_ExternalSourceType();
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
      xmlns = new EObjectContainmentEList<XS_xmlns>(XS_xmlns.class, this, PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__XMLNS);
    }
    return xmlns;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFormat()
  {
    return format;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFormat(String newFormat)
  {
    String oldFormat = format;
    format = newFormat;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__FORMAT, oldFormat, format));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getHeaderLine()
  {
    return headerLine;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHeaderLine(EObject newHeaderLine, NotificationChain msgs)
  {
    EObject oldHeaderLine = headerLine;
    headerLine = newHeaderLine;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE, oldHeaderLine, newHeaderLine);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHeaderLine(EObject newHeaderLine)
  {
    if (newHeaderLine != headerLine)
    {
      NotificationChain msgs = null;
      if (headerLine != null)
        msgs = ((InternalEObject)headerLine).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE, null, msgs);
      if (newHeaderLine != null)
        msgs = ((InternalEObject)newHeaderLine).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE, null, msgs);
      msgs = basicSetHeaderLine(newHeaderLine, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE, newHeaderLine, newHeaderLine));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIgnoreLineSymbol()
  {
    return ignoreLineSymbol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIgnoreLineSymbol(String newIgnoreLineSymbol)
  {
    String oldIgnoreLineSymbol = ignoreLineSymbol;
    ignoreLineSymbol = newIgnoreLineSymbol;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__IGNORE_LINE_SYMBOL, oldIgnoreLineSymbol, ignoreLineSymbol));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUrl()
  {
    return url;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUrl(String newUrl)
  {
    String oldUrl = url;
    url = newUrl;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__URL, oldUrl, url));
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
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__XMLNS:
        return ((InternalEList<?>)getXmlns()).basicRemove(otherEnd, msgs);
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE:
        return basicSetHeaderLine(null, msgs);
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
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__XMLNS:
        return getXmlns();
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__FORMAT:
        return getFormat();
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE:
        return getHeaderLine();
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__IGNORE_LINE_SYMBOL:
        return getIgnoreLineSymbol();
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__URL:
        return getUrl();
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
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__XMLNS:
        getXmlns().clear();
        getXmlns().addAll((Collection<? extends XS_xmlns>)newValue);
        return;
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__FORMAT:
        setFormat((String)newValue);
        return;
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE:
        setHeaderLine((EObject)newValue);
        return;
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__IGNORE_LINE_SYMBOL:
        setIgnoreLineSymbol((String)newValue);
        return;
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__URL:
        setUrl((String)newValue);
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
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__XMLNS:
        getXmlns().clear();
        return;
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__FORMAT:
        setFormat(FORMAT_EDEFAULT);
        return;
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE:
        setHeaderLine((EObject)null);
        return;
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__IGNORE_LINE_SYMBOL:
        setIgnoreLineSymbol(IGNORE_LINE_SYMBOL_EDEFAULT);
        return;
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__URL:
        setUrl(URL_EDEFAULT);
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
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__XMLNS:
        return xmlns != null && !xmlns.isEmpty();
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__FORMAT:
        return FORMAT_EDEFAULT == null ? format != null : !FORMAT_EDEFAULT.equals(format);
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__HEADER_LINE:
        return headerLine != null;
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__IGNORE_LINE_SYMBOL:
        return IGNORE_LINE_SYMBOL_EDEFAULT == null ? ignoreLineSymbol != null : !IGNORE_LINE_SYMBOL_EDEFAULT.equals(ignoreLineSymbol);
      case PharmMLPackage.CT_EXTERNAL_SOURCE_TYPE__URL:
        return URL_EDEFAULT == null ? url != null : !URL_EDEFAULT.equals(url);
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
    result.append(" (format: ");
    result.append(format);
    result.append(", ignoreLineSymbol: ");
    result.append(ignoreLineSymbol);
    result.append(", url: ");
    result.append(url);
    result.append(')');
    return result.toString();
  }

} //ct_ExternalSourceTypeImpl

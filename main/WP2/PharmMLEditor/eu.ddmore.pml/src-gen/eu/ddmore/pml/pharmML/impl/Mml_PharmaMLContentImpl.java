/**
 */
package eu.ddmore.pml.pharmML.impl;

import eu.ddmore.pml.pharmML.Mml_PharmaMLContent;
import eu.ddmore.pml.pharmML.PharmMLPackage;
import eu.ddmore.pml.pharmML.mdef_ModelDefinitionType;
import eu.ddmore.pml.pharmML.mdef_StructuralModelType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mml Pharma ML Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmaMLContentImpl#getStructuralModel <em>Structural Model</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.impl.Mml_PharmaMLContentImpl#getModelDefinition <em>Model Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Mml_PharmaMLContentImpl extends MinimalEObjectImpl.Container implements Mml_PharmaMLContent
{
  /**
   * The cached value of the '{@link #getStructuralModel() <em>Structural Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStructuralModel()
   * @generated
   * @ordered
   */
  protected mdef_StructuralModelType structuralModel;

  /**
   * The cached value of the '{@link #getModelDefinition() <em>Model Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelDefinition()
   * @generated
   * @ordered
   */
  protected mdef_ModelDefinitionType modelDefinition;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Mml_PharmaMLContentImpl()
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
    return PharmMLPackage.eINSTANCE.getMml_PharmaMLContent();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_StructuralModelType getStructuralModel()
  {
    return structuralModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStructuralModel(mdef_StructuralModelType newStructuralModel, NotificationChain msgs)
  {
    mdef_StructuralModelType oldStructuralModel = structuralModel;
    structuralModel = newStructuralModel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL, oldStructuralModel, newStructuralModel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStructuralModel(mdef_StructuralModelType newStructuralModel)
  {
    if (newStructuralModel != structuralModel)
    {
      NotificationChain msgs = null;
      if (structuralModel != null)
        msgs = ((InternalEObject)structuralModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL, null, msgs);
      if (newStructuralModel != null)
        msgs = ((InternalEObject)newStructuralModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL, null, msgs);
      msgs = basicSetStructuralModel(newStructuralModel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL, newStructuralModel, newStructuralModel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mdef_ModelDefinitionType getModelDefinition()
  {
    return modelDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModelDefinition(mdef_ModelDefinitionType newModelDefinition, NotificationChain msgs)
  {
    mdef_ModelDefinitionType oldModelDefinition = modelDefinition;
    modelDefinition = newModelDefinition;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARMA_ML_CONTENT__MODEL_DEFINITION, oldModelDefinition, newModelDefinition);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelDefinition(mdef_ModelDefinitionType newModelDefinition)
  {
    if (newModelDefinition != modelDefinition)
    {
      NotificationChain msgs = null;
      if (modelDefinition != null)
        msgs = ((InternalEObject)modelDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARMA_ML_CONTENT__MODEL_DEFINITION, null, msgs);
      if (newModelDefinition != null)
        msgs = ((InternalEObject)newModelDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PharmMLPackage.MML_PHARMA_ML_CONTENT__MODEL_DEFINITION, null, msgs);
      msgs = basicSetModelDefinition(newModelDefinition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PharmMLPackage.MML_PHARMA_ML_CONTENT__MODEL_DEFINITION, newModelDefinition, newModelDefinition));
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
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL:
        return basicSetStructuralModel(null, msgs);
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__MODEL_DEFINITION:
        return basicSetModelDefinition(null, msgs);
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
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL:
        return getStructuralModel();
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__MODEL_DEFINITION:
        return getModelDefinition();
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
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL:
        setStructuralModel((mdef_StructuralModelType)newValue);
        return;
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__MODEL_DEFINITION:
        setModelDefinition((mdef_ModelDefinitionType)newValue);
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
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL:
        setStructuralModel((mdef_StructuralModelType)null);
        return;
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__MODEL_DEFINITION:
        setModelDefinition((mdef_ModelDefinitionType)null);
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
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__STRUCTURAL_MODEL:
        return structuralModel != null;
      case PharmMLPackage.MML_PHARMA_ML_CONTENT__MODEL_DEFINITION:
        return modelDefinition != null;
    }
    return super.eIsSet(featureID);
  }

} //Mml_PharmaMLContentImpl

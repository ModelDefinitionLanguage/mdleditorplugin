/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>mdef Structural Model Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getId <em>Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getDescription <em>Description</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getParameter <em>Parameter</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getVariable <em>Variable</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getImport <em>Import</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getInitiaCondition <em>Initia Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_StructuralModelType()
 * @model
 * @generated
 */
public interface mdef_StructuralModelType extends EObject
{
  /**
   * Returns the value of the '<em><b>Xmlns</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.XS_xmlns}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xmlns</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Xmlns</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_StructuralModelType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' containment reference.
   * @see #setId(ct_id)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_StructuralModelType_Id()
   * @model containment="true"
   * @generated
   */
  ct_id getId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getId <em>Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' containment reference.
   * @see #getId()
   * @generated
   */
  void setId(ct_id value);

  /**
   * Returns the value of the '<em><b>Description</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Description</em>' containment reference.
   * @see #setDescription(ct_AnnotationType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_StructuralModelType_Description()
   * @model containment="true"
   * @generated
   */
  ct_AnnotationType getDescription();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.mdef_StructuralModelType#getDescription <em>Description</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Description</em>' containment reference.
   * @see #getDescription()
   * @generated
   */
  void setDescription(ct_AnnotationType value);

  /**
   * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.mdef_ParameterType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_StructuralModelType_Parameter()
   * @model containment="true"
   * @generated
   */
  EList<mdef_ParameterType> getParameter();

  /**
   * Returns the value of the '<em><b>Variable</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.ct_VariableDefinitionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_StructuralModelType_Variable()
   * @model containment="true"
   * @generated
   */
  EList<ct_VariableDefinitionType> getVariable();

  /**
   * Returns the value of the '<em><b>Import</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.mdef_ImportType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Import</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Import</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_StructuralModelType_Import()
   * @model containment="true"
   * @generated
   */
  EList<mdef_ImportType> getImport();

  /**
   * Returns the value of the '<em><b>Initia Condition</b></em>' containment reference list.
   * The list contents are of type {@link eu.ddmore.pml.pharmML.mdef_InitialConditionType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initia Condition</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initia Condition</em>' containment reference list.
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getmdef_StructuralModelType_InitiaCondition()
   * @model containment="true"
   * @generated
   */
  EList<mdef_InitialConditionType> getInitiaCondition();

} // mdef_StructuralModelType

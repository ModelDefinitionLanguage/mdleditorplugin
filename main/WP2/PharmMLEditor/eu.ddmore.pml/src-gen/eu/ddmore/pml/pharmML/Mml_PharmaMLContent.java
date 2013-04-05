/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mml Pharma ML Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmaMLContent#getStructuralModel <em>Structural Model</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.Mml_PharmaMLContent#getModelDefinition <em>Model Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmaMLContent()
 * @model
 * @generated
 */
public interface Mml_PharmaMLContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Structural Model</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Structural Model</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Structural Model</em>' containment reference.
   * @see #setStructuralModel(mdef_StructuralModelType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmaMLContent_StructuralModel()
   * @model containment="true"
   * @generated
   */
  mdef_StructuralModelType getStructuralModel();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_PharmaMLContent#getStructuralModel <em>Structural Model</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Structural Model</em>' containment reference.
   * @see #getStructuralModel()
   * @generated
   */
  void setStructuralModel(mdef_StructuralModelType value);

  /**
   * Returns the value of the '<em><b>Model Definition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Definition</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Definition</em>' containment reference.
   * @see #setModelDefinition(mdef_ModelDefinitionType)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getMml_PharmaMLContent_ModelDefinition()
   * @model containment="true"
   * @generated
   */
  mdef_ModelDefinitionType getModelDefinition();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.Mml_PharmaMLContent#getModelDefinition <em>Model Definition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Definition</em>' containment reference.
   * @see #getModelDefinition()
   * @generated
   */
  void setModelDefinition(mdef_ModelDefinitionType value);

} // Mml_PharmaMLContent

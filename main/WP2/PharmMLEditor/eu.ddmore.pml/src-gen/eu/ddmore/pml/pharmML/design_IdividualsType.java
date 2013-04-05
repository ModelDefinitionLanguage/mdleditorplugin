/**
 */
package eu.ddmore.pml.pharmML;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>design Idividuals Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.ddmore.pml.pharmML.design_IdividualsType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_IdividualsType#getLevelId <em>Level Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_IdividualsType#getName <em>Name</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_IdividualsType#getSymbId <em>Symb Id</em>}</li>
 *   <li>{@link eu.ddmore.pml.pharmML.design_IdividualsType#getExpr <em>Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_IdividualsType()
 * @model
 * @generated
 */
public interface design_IdividualsType extends EObject
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
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_IdividualsType_Xmlns()
   * @model containment="true"
   * @generated
   */
  EList<XS_xmlns> getXmlns();

  /**
   * Returns the value of the '<em><b>Level Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Level Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Level Id</em>' containment reference.
   * @see #setLevelId(ct_levelId)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_IdividualsType_LevelId()
   * @model containment="true"
   * @generated
   */
  ct_levelId getLevelId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_IdividualsType#getLevelId <em>Level Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Level Id</em>' containment reference.
   * @see #getLevelId()
   * @generated
   */
  void setLevelId(ct_levelId value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(ct_name)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_IdividualsType_Name()
   * @model containment="true"
   * @generated
   */
  ct_name getName();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_IdividualsType#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(ct_name value);

  /**
   * Returns the value of the '<em><b>Symb Id</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symb Id</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symb Id</em>' containment reference.
   * @see #setSymbId(ct_symbId)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_IdividualsType_SymbId()
   * @model containment="true"
   * @generated
   */
  ct_symbId getSymbId();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_IdividualsType#getSymbId <em>Symb Id</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symb Id</em>' containment reference.
   * @see #getSymbId()
   * @generated
   */
  void setSymbId(ct_symbId value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(ct_Rhs)
   * @see eu.ddmore.pml.pharmML.PharmMLPackage#getdesign_IdividualsType_Expr()
   * @model containment="true"
   * @generated
   */
  ct_Rhs getExpr();

  /**
   * Sets the value of the '{@link eu.ddmore.pml.pharmML.design_IdividualsType#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(ct_Rhs value);

} // design_IdividualsType

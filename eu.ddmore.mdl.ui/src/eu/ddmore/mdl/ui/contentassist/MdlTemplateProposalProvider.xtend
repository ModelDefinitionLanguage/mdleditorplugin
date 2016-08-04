package eu.ddmore.mdl.ui.contentassist

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.provider.BlockListDefinition
import eu.ddmore.mdl.type.ListTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.MdlLibUtils
import java.util.Collections
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.jface.text.templates.ContextTypeRegistry
import org.eclipse.jface.text.templates.Template
import org.eclipse.jface.text.templates.TemplateContext
import org.eclipse.jface.text.templates.TemplateContextType
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ITemplateAcceptor
import org.eclipse.xtext.ui.editor.templates.ContextTypeIdHelper
import org.eclipse.xtext.ui.editor.templates.DefaultTemplateProposalProvider

@Singleton
class MdlTemplateProposalProvider extends DefaultTemplateProposalProvider {
	
//	public val STATEMENT_ID = "eu.ddmore.mdl.Mdl.Statement"
	public val EQN_DEFN_ID = "eu.ddmore.mdl.Mdl.EquationDefinition"
	public val TRANS_EQN_DEFN_ID = "eu.ddmore.mdl.Mdl.TransformedDefinition"
	public val ANONYMOUS_LIST_ID = "eu.ddmore.mdl.Mdl.AnonymousListStatement"
	public val LIST_DEFN_ID = "eu.ddmore.mdl.Mdl.ListDefinition"
	public val RV_DEFN_ID = "eu.ddmore.mdl.Mdl.RandomVariableDefinition"
	public val ENUM_DEFN_ID = "eu.ddmore.mdl.Mdl.EnumerationDefinition"

	public val EXPRESSION_ID = "eu.ddmore.mdl.Mdl.Expression"
	public val OR_EXPRESSION_ID = "eu.ddmore.mdl.Mdl.OrExpression"
	public val AND_EXPRESSION_ID = "eu.ddmore.mdl.Mdl.AndExpression"
	public val EQUALITY_EXPRESSION_ID = "eu.ddmore.mdl.Mdl.EqualityExpression"
	public val RELATION_EXPRESSION_ID = "eu.ddmore.mdl.Mdl.RelationExpression"
	public val ADDITIVE_EXPRESSION_ID = "eu.ddmore.mdl.Mdl.AdditiveExpression"
	public val MULTI_EXPRESSION_ID = "eu.ddmore.mdl.Mdl.MultiplacativeExpression"
	public val POWER_EXPRESSION_ID = "eu.ddmore.mdl.Mdl.PowerExpression"
	public val UNI_EXPRESSION_ID = "eu.ddmore.mdl.Mdl.UnaryExpression"
	public val PRIM_EXPRESSION_ID = "eu.ddmore.mdl.Mdl.PrimaryExpression"
	public val SYMB_REF_ID = "eu.ddmore.mdl.Mdl.SymbolReference"
	public val FUNC_REF_ID = "eu.ddmore.mdl.Mdl.FunctionReference"
	public val IF_EXPR_ID = "eu.ddmore.mdl.Mdl.IfExpression"
	public val PW_EXPR_ID = "eu.ddmore.mdl.Mdl.PiecewiseExpression"
	public val PAR_EXPR_ID = "eu.ddmore.mdl.Mdl.ParExpression"
	
	public val STATEMENTS = #[  
		EQN_DEFN_ID, TRANS_EQN_DEFN_ID, ANONYMOUS_LIST_ID, LIST_DEFN_ID, RV_DEFN_ID, ENUM_DEFN_ID
	]
	
	public val EXPRESSIONS = #[
		EXPRESSION_ID,
		OR_EXPRESSION_ID,
		AND_EXPRESSION_ID,
		EQUALITY_EXPRESSION_ID,
		RELATION_EXPRESSION_ID,
		ADDITIVE_EXPRESSION_ID,
		MULTI_EXPRESSION_ID,
		POWER_EXPRESSION_ID,
		UNI_EXPRESSION_ID,
		PRIM_EXPRESSION_ID,
		SYMB_REF_ID,
		FUNC_REF_ID,
		IF_EXPR_ID,
		PW_EXPR_ID,
		PAR_EXPR_ID
	]
	
	extension MdlLibUtils mlu = new MdlLibUtils
	extension TypeSystemProvider tsp = new TypeSystemProvider
	
	val MdlDynamicTemplateStore ts
	
	@Inject
	new(MdlDynamicTemplateStore templateStore, ContextTypeRegistry registry, ContextTypeIdHelper helper) {
		super(templateStore, registry, helper)
		ts = templateStore
	}


	override protected void createTemplates(TemplateContext templateContext, ContentAssistContext context, ITemplateAcceptor acceptor) {
		val TemplateContextType contextType = templateContext.getContextType();
		val obj = EcoreUtil2.getContainerOfType(context.currentModel, MclObject)
		val lib = obj.libraryForObject
		ts.libDefns = lib
		val templates = ts.getTemplates(contextType.getId());
		var List<TypeInfo> expectedTypes = Collections.emptyList
		if(STATEMENTS.exists[id| id == contextType.getId]){
			expectedTypes = getExpectedTypesForStatement(context.currentModel)
		}
		else if(EXPRESSIONS.exists[id| id == contextType.getId]){
			expectedTypes = #[ context.currentModel.typeFor ]
		}
		val owningBlock = EcoreUtil2.getContainerOfType(context.currentModel, BlockStatement)
		val blkDefn = BlockListDefinition::create(owningBlock)
		for (Template template : templates) {
			if (!acceptor.canAcceptMoreTemplates())
				return;
			if (validate(template, templateContext)) {
				if(template instanceof TypefulTemplate){
					if(template.matchType instanceof ListTypeInfo){
						if(blkDefn.listDefns.exists[listType == template.matchType]){
							acceptor.accept(createProposal(template, templateContext, context, getImage(template), getRelevance(template)));
						}
					}
					else if(expectedTypes.exists[isCompatible(template.matchType)]){
						acceptor.accept(createProposal(template, templateContext, context, getImage(template), getRelevance(template)));
					}
				}
				else
					acceptor.accept(createProposal(template, templateContext, context, getImage(template), getRelevance(template)));
			}
		}
	}

	
	private def List<TypeInfo> getExpectedTypesForStatement(EObject stmt){
		switch(stmt){
			EquationTypeDefinition:
				#[ TypeSystemProvider::REAL_TYPE, TypeSystemProvider::INT_TYPE, TypeSystemProvider::STRING_TYPE, TypeSystemProvider::BOOLEAN_TYPE,
					TypeSystemProvider::REAL_VECTOR_TYPE, TypeSystemProvider::REAL_MATRIX_TYPE
				]
			RandomVariableDefinition:
				#[ TypeSystemProvider::PDF_TYPE, TypeSystemProvider::PDF_TYPE.makeVector, TypeSystemProvider::PDF_TYPE.makeMatrix,
					TypeSystemProvider::PMF_TYPE, TypeSystemProvider::PMF_TYPE.makeVector, TypeSystemProvider::PMF_TYPE.makeMatrix
				]
			EnumerationDefinition:
				#[ TypeSystemProvider::PMF_TYPE, TypeSystemProvider::PMF_TYPE.makeVector, TypeSystemProvider::PMF_TYPE.makeMatrix
				]
			default:
				#[ TypeSystemProvider::UNDEFINED_TYPE ]
		}
	}
	
	
}
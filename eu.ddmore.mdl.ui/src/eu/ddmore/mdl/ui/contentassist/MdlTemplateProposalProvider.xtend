package eu.ddmore.mdl.ui.contentassist

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.utils.MdlLibUtils
import org.eclipse.jface.text.templates.ContextTypeRegistry
import org.eclipse.jface.text.templates.Template
import org.eclipse.jface.text.templates.TemplateContext
import org.eclipse.jface.text.templates.TemplateContextType
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ITemplateAcceptor
import org.eclipse.xtext.ui.editor.templates.ContextTypeIdHelper
import org.eclipse.xtext.ui.editor.templates.DefaultTemplateProposalProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import org.eclipse.emf.ecore.EObject
import eu.ddmore.mdl.type.TypeInfo
import java.util.List
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdllib.mdllib.Expression
import java.util.Collections
import eu.ddmore.mdl.type.ListTypeInfo
import eu.ddmore.mdl.provider.BlockListDefinition
import eu.ddmore.mdl.mdl.BlockStatement

@Singleton
class MdlTemplateProposalProvider extends DefaultTemplateProposalProvider {
	
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
//		if(contextType.name == "eu.ddmore.mdl.Mdl.AttributeList"){
//			createAttListTemplates()
//		}
		val obj = EcoreUtil2.getContainerOfType(context.currentModel, MclObject)
		val lib = obj.libraryForObject
		ts.libDefns = lib
		val templates = ts.getTemplates(contextType.getId());
		var List<TypeInfo> expectedTypes = Collections.emptyList
		if(context.currentModel instanceof Statement){
			expectedTypes = getExpectedTypesForStatement(context.currentModel)
		}
		else if(context.currentModel instanceof Expression){
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

//	def private void createAttributeListTemplates(TemplateContext templateContext, ContentAssistContext context, ITemplateAcceptor acceptor){
//	}
	
	def private void createExpressionTypeTemplates(TemplateContext templateContext, ContentAssistContext context, ITemplateAcceptor acceptor){
		val TemplateContextType contextType = templateContext.getContextType();
		val obj = EcoreUtil2.getContainerOfType(context.currentModel, MclObject)
		val lib = obj.libraryForObject
		ts.libDefns = lib
		val templates = ts.getTemplates(contextType.getId());
		var List<TypeInfo> expectedTypes = Collections.emptyList
		if(context.currentModel instanceof Statement){
			expectedTypes = getExpectedTypesForStatement(context.currentModel)
		}
		else if(context.currentModel instanceof Expression){
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
/*
 
* generated by Xtext
 */
package eu.ddmore.mdl.ui.contentassist

import com.google.common.base.Predicate
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.PropertyDefinitionProvider
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.BlockContainer
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import eu.ddmore.mdllib.mdllib.TypeDefinition
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.jface.viewers.StyledString
import org.eclipse.swt.graphics.Image
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.CrossReference
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.Keyword
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor

import static extension org.eclipse.xtext.EcoreUtil2.getContainerOfType

/**
 * see http://www.eclipse.org/Xtext/documentation.html#contentAssist on how to customize content assistant
 */
class MdlProposalProvider extends AbstractMdlProposalProvider {

	extension ListDefinitionProvider listHelper = new ListDefinitionProvider
	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	extension TypeSystemProvider mtp = new TypeSystemProvider
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension MdlLibUtils mlu = new MdlLibUtils


	 public override void completeSymbolReference_Ref(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	 	val owningObj = model.getContainerOfType(MclObject)
	 	val expectedType = if(model instanceof SymbolDefinition || model instanceof Expression
	 							 || model instanceof ValuePair) model.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
	 						else TypeSystemProvider::UNDEFINED_TYPE
	 	val booleanFilter = new Predicate<IEObjectDescription>()
	 	{
				override apply(IEObjectDescription input) {
					val s = input.EObjectOrProxy
//					if(s instanceof SymbolDefinition){
						val sOwningObj = s.eContainer.getContainerOfType(MclObject)
						if(sOwningObj != null){
							if(sOwningObj == owningObj){
								if(expectedType.isCompatible(s.typeFor)){
									true
								}
								else if(expectedType.isVector || expectedType.isMatrix){
									expectedType.isCompatibleElement(s.typeFor)
								}
								else false
							}
							else false
						}
//						else if(s instanceof FunctionDefnBody){
//							val retType = s.funcSpec.returnType.typeInfo
//							retType.isCompatible(expectedType)
//  						}
  						else false
//					}
//					else false
				}
				
	 	}
	 	lookupCrossReference((assignment.getTerminal() as CrossReference), context, acceptor, booleanFilter)
	}

	public override void completeCategoryValueReference_Ref(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	 	val booleanFilter = new Predicate<IEObjectDescription>()
	 	{
				override apply(IEObjectDescription input) {
					false
				}
				
	 	}
		lookupCrossReference((assignment.getTerminal() as CrossReference), context, acceptor, booleanFilter);
	}
	
	
 
//	override complete_IS(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
//		var node = context.lastCompleteNode
//		val owningBlock = EcoreUtil2.getContainerOfType(model, BlockStatement) 
//		while(node != null && owningBlock != null){
//			val nodeTxt = node.text
//			val matchingAtts = owningBlock.getAllMatchingListDefns(nodeTxt)
//			if(matchingAtts.exists[attType.typeClass == TypeInfoClass.Builtin ]){
//				addProposals(context, acceptor, #['is'], null)
//				node = null
//			}
//			else
//				node = node.nextSibling
//		}
//	}
//	
//	
//	override complete_ASSIGN(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
//		var node = context.lastCompleteNode
//		val owningBlock = EcoreUtil2.getContainerOfType(model, BlockStatement) 
//		while(node != null && owningBlock != null){
//			val nodeTxt = node.text
//			val matchingAtts = owningBlock.getAllMatchingListDefns(nodeTxt)
//			if(matchingAtts.exists[attType.typeClass != TypeInfoClass.Builtin ]){
//				addProposals(context, acceptor, #['='], null)
//				node = null
//			}
//			else
//				node = node.nextSibling
//		}
//	}

	private def createFuncEnumProposal(SymbolReference fCall, EnumPair model, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		val enumType = model.getNamedArgumentType
		val attributes = new ArrayList<String>
		if(enumType instanceof BuiltinEnumTypeInfo){
			attributes.addAll((enumType as BuiltinEnumTypeInfo).expectedValues)
		}
		addProposals(context, acceptor, attributes, null);
	} 


	private def createListEnumProposal(EnumPair model, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		val parentBlock = model.getContainerOfType(BlockStatement)
		var TypeInfo enumType = TypeSystemProvider::UNDEFINED_TYPE
		if(parentBlock.blkId.keyAttName == model.argumentName){
			// this is the key so need to get the type in a different way
			val firstKeyMapping =  parentBlock.blkId.listTypeMappings.head
			if(firstKeyMapping != null){
				// there is a mapping so use it to 
				val enumVal = firstKeyMapping.attDefn
				// get the type for the specific enum
				val typeDefn = EcoreUtil2.getContainerOfType(enumVal, TypeDefinition)
				enumType = typeDefn.typeInfo
			}
		}
		else{
			// not the key so can get the type definition as the list will be identified. 
			enumType = getAttributeType(model)
		}
		val attributes = new ArrayList<String>
		if(enumType instanceof BuiltinEnumTypeInfo){
			attributes.addAll((enumType as BuiltinEnumTypeInfo).expectedValues)
		}
		addProposals(context, acceptor, attributes, null);
	} 

	private def createPropertyEnumProposal(EnumPair model, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		val enumType = model.typeForProperty
		val attributes = new ArrayList<String>
		if(enumType instanceof BuiltinEnumTypeInfo){
			attributes.addAll((enumType as BuiltinEnumTypeInfo).expectedValues)
		}
		addProposals(context, acceptor, attributes, null);
	} 

	override completeEnumPair_Expression(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		val listParent = model.getContainerOfType(AttributeList)
		if(model instanceof EnumPair){
			if(listParent != null){
				createListEnumProposal(model, context, acceptor)
			}
			else{
				val funcParent = model.getContainerOfType(SymbolReference)
				if(funcParent != null){
						createFuncEnumProposal(funcParent, model, context, acceptor)
				}
				else{
					val propParent = model.getContainerOfType(PropertyStatement)
					if(propParent != null){
						createPropertyEnumProposal(model, context, acceptor)
					}
				}
			}
		}
	}
	
	def addProposals(ContentAssistContext context, ICompletionProposalAcceptor acceptor, List<String> attributes, Image img){
		for (String proposal: attributes){
			val displayedString = new StyledString();
			displayedString.append(proposal);
			val p = createCompletionProposal(proposal, displayedString, img, context)
//			val p = doCreateProposal(proposal, displayedString, img, 1000, context)
			acceptor.accept(p);
		}
	}
	
	def private getParentBlock(EObject model){
		// in some cases for example when first block in object the parent of Mcl.
		// in this return the model as the parent
		val retVal = model.eContainer
		if(retVal instanceof MclObject || retVal instanceof BlockStatement){
			retVal
		}
		else if(model instanceof MclObject){
			model
		}
		else{
			null
		}
	}
	
	override completeBlockStatement_BlkId(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		val parent = getParentBlock(model)
		if(parent != null){
			val BlockContainer container = switch(parent){
				MclObject:
					parent.objId
				BlockStatement:
					parent.blkId
				default:
					null
			}
			val Predicate<IEObjectDescription> filter = new Predicate<IEObjectDescription>(){
				
				override apply(IEObjectDescription input) {
					if(container != null)
						container.canContainBlock(input.name.lastSegment)
					else false						
				}
				
			}
			lookupCrossReference((assignment.getTerminal() as CrossReference), context, acceptor, filter)
		}
	}
	
	override completeTransformedDefinition_Transform(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
//		addProposals(context, acceptor, #['ln', 'logit', 'probit'], null)
////		lookupCrossReference(((CrossReference)assignment.getTerminal()), context, acceptor);
		
	}

	
	override void completeKeyword(Keyword keyword, ContentAssistContext contentAssistContext,
			ICompletionProposalAcceptor acceptor) {
//		val ICompletionProposal proposal = createCompletionProposal(keyword.getValue(), getKeywordDisplayString(keyword),
//				getImage(keyword), contentAssistContext);
//		getPriorityHelper().adjustKeywordPriority(proposal, contentAssistContext.getPrefix());
//		acceptor.accept(proposal);
//		return
	}

}

/*
 * generated by Xtext
 */
package eu.ddmore.mdl.formatting2

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.services.MdlGrammarAccess
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument

class MdlFormatter extends AbstractFormatter2 {
	
	@Inject extension MdlGrammarAccess

	def dispatch void format(Mcl mcl, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		for (MclObject mclObject : mcl.getObjects()) {
			mclObject.format;
		}
	}

	def dispatch void format(MclObject mclObject, extension IFormattableDocument document) {
		// TODO: format HiddenRegions around keywords, attributes, cross references, etc. 
		mclObject.getBlkArgs.format;
		for (BlockStatement blockStatement : mclObject.getBlocks()) {
			blockStatement.format;
		}
	}
	
	// TODO: implement for BlockStatement, BlockStatementBody, BlockArguments, UnnamedFuncArgs, FuncArgumentDefinition, EquationDefinition, FunctionSpec, TransformedDefinition, RandomVariableDefinition, TypeSpec, EnumerationDefinition, ListDefinition, ListPiecewiseExpression, ListPWClause, ListIfExpression, ListIfClause, ListElifClause, ListElseClause, AnonymousListStatement, PropertyStatement, CategoricalDefinitionExpr, CategoryValueDefinition, AttributeList, AssignPair, EnumPair, EnumExpression, MappingExpression, SubListExpression, CatValRefMappingExpression, CatValRefMapping, MappingPair, OrExpression, AndExpression, EqualityExpression, RelationalExpression, AdditiveExpression, MultiplicativeExpression, PowerExpression, UnaryExpression, ParExpression, IfExpression, PiecewiseExpression, PWClause, IfClause, ElifClause, ElseClause, SymbolReference, IndexSpec, IndexRange, NamedFuncArguments, UnnamedFuncArguments, UnnamedArgument, MatrixLiteral, MatrixRow, MatrixElement, VectorLiteral, VectorElement
}

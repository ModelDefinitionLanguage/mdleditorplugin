/*
 * generated by Xtext
 */
package org.ddmore.mdl.formatting;

import org.ddmore.mdl.services.MdlGrammarAccess;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;

import com.google.inject.Inject;

/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it 
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
public class MdlFormatter extends AbstractDeclarativeFormatter {
	
	@Inject
	private MdlGrammarAccess f = (MdlGrammarAccess) getGrammarAccess();
	
	@Override
	protected void configureFormatting(FormattingConfig c) {
	// It's usually a good idea to activate the following three statements.
	// They will add and preserve newlines around comments

		c.setAutoLinewrap(120);
		//c.setLinewrap(1).before(f.getSL_COMMENTRule());
		//c.setLinewrap(1).before(f.getML_COMMENTRule());
		//c.setLinewrap(1).after(f.getML_COMMENTRule());
	
		for (Pair<Keyword, Keyword> pair: f.findKeywordPairs("(", ")")) {
			c.setNoSpace().after(pair.getFirst());
			c.setNoSpace().before(pair.getSecond());
		}		
		
		for (Keyword comma : f.findKeywords(",")) {
			c.setNoSpace().before(comma);
		}
		
		for (Keyword openBracket : f.findKeywords("(")) {
			c.setNoSpace().before(openBracket);
		}

		c.setLinewrap(2).after(f.getMclObjectRule());
		
		//c.setLinewrap(1).before(f.getENDRule());
		c.setIndentationIncrement().after(f.getBEGINRule());
		c.setIndentationDecrement().before(f.getENDRule());
		
		c.setLinewrap(2).before(f.getIndividualVariablesBlockRule());
		c.setLinewrap(2).before(f.getModelPredictionBlockRule());
		c.setLinewrap(2).before(f.getRandomVariableDefinitionBlockRule());
		c.setLinewrap(2).before(f.getInputVariablesBlockRule());
		c.setLinewrap(2).before(f.getStructuralParametersBlockRule());
		c.setLinewrap(2).before(f.getVariabilityParametersBlockRule());
		c.setLinewrap(2).before(f.getOutputVariablesBlockRule());
		c.setLinewrap(2).before(f.getGroupVariablesBlockRule());
		c.setLinewrap(2).before(f.getObservationBlockRule());
		c.setLinewrap(2).before(f.getEstimationBlockRule());
		c.setLinewrap(2).before(f.getSimulationBlockRule());

		c.setLinewrap(2).before(f.getStructuralBlockRule());
		c.setLinewrap(2).before(f.getVariabilityBlockRule());
		c.setLinewrap(2).before(f.getPriorParametersBlockRule());

		c.setLinewrap(2).before(f.getDataInputBlockRule());
		c.setLinewrap(2).before(f.getSourceBlockRule());

		c.setLinewrap(2).before(f.getEstimateTaskRule());
		c.setLinewrap(2).before(f.getSimulateTaskRule());
		c.setLinewrap(2).before(f.getOptimiseTaskRule());
		c.setLinewrap(2).before(f.getEvaluateTaskRule());
		c.setLinewrap(2).before(f.getDataBlockRule());
		c.setLinewrap(2).before(f.getModelBlockRule());
	
		c.setLinewrap(1).before(f.getTargetBlockRule());
		c.setIndentation(f.getTargetBlockAccess().getExternalCodeAssignment_3(), f.getTargetBlockAccess().getENDTerminalRuleCall_4());

		c.setLinewrap(1).before(f.getSymbolDeclarationRule());
		
		c.setLinewrap(1).before(f.getModelPredictionBlockStatementRule());
		
		c.setLinewrap(1).before(f.getStructuralParametersBlockAccess().getParametersAssignment_2());
		c.setLinewrap(1).before(f.getVariabilityParametersBlockAccess().getParametersAssignment_2());
		c.setLinewrap(1).before(f.getOutputVariablesBlockAccess().getVariablesAssignment_2());
		
		c.setLinewrap(1).before(f.getGroupVariablesBlockStatementRule());
		c.setLinewrap(1).before(f.getVariabilityBlockStatementRule());
		c.setLinewrap(1).before(f.getFunctionCallStatementRule());
		c.setLinewrap(1).before(f.getLibraryBlockRule());
		c.setLinewrap(1).before(f.getOdeBlockRule());

		c.setLinewrap(1).before(f.getInlineBlockAccess().getVariablesAssignment_2());
			
		c.setLinewrap(1).before(f.getMatrixBlockAccess().getParametersAssignment_4_1());
		c.setLinewrap(1).before(f.getDiagBlockAccess().getParametersAssignment_4_1());
		c.setLinewrap(1).before(f.getSameBlockAccess().getParametersAssignment_4_1());

	}
}

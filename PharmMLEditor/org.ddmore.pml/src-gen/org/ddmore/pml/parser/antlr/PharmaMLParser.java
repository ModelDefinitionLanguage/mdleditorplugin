/*
* generated by Xtext
*/
package org.ddmore.pml.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.ddmore.pml.services.PharmaMLGrammarAccess;

public class PharmaMLParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private PharmaMLGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.ddmore.pml.parser.antlr.internal.InternalPharmaMLParser createParser(XtextTokenStream stream) {
		return new org.ddmore.pml.parser.antlr.internal.InternalPharmaMLParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "Mml_PharmaML";
	}
	
	public PharmaMLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(PharmaMLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}

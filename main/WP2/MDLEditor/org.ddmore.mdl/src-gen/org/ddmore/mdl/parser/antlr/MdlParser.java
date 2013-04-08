/*
* generated by Xtext
*/
package org.ddmore.mdl.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.ddmore.mdl.services.MdlGrammarAccess;

public class MdlParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private MdlGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.ddmore.mdl.parser.antlr.internal.InternalMdlParser createParser(XtextTokenStream stream) {
		return new org.ddmore.mdl.parser.antlr.internal.InternalMdlParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "mcl";
	}
	
	public MdlGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(MdlGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
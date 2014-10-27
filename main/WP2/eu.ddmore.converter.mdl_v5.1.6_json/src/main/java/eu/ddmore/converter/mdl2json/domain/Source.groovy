package eu.ddmore.converter.mdl2json.domain;

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;
import eu.ddmore.converter.mdl2json.utils.XtextWrapper;
import groovy.util.Expando;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.SourceBlock;
import org.ddmore.mdl.mdl.PropertyDeclaration;

/**
 * @author jchard
 *
 */
public class Source extends Expando implements MDLPrintable {

	private static final String SOURCE = "SOURCE"
	
	/**
	 * Constructor which unpacks all the content of the SourceBlock and adds them to the properties of this object
	 * 
	 * @param sourceBlock
	 */
	public Source(SourceBlock sourceBlock) {
		setProperty("identifier", sourceBlock.getIdentifier() );
		sourceBlock.getStatements().each{ PropertyDeclaration pd ->
			def expr = pd.getExpression()
			setProperty(pd.getPropertyName().getName(), XtextWrapper.unwrap(expr))
		}
		if (sourceBlock.getInlineBlock() != null) {
			setProperty("inlineBlock", sourceBlock.getInlineBlock());
		}
	}


	/**
	 * Constructor from a JSON object
	 * @param json
	 */
	public Source(Map json) {
		getProperties().putAll(json)
	}
	
	/**
	 * Returns the MDL equivalent of this object
	 * 
	 * @return
	 */
	public String toMDL() {
		List properties = []
		getProperties().each{ k, v ->
			if(!k.equals("identifier")) { 
				properties.add("${k}=${v}")
			} 
		}
		
		"""${properties.join("\n        ")}"""
	}
}

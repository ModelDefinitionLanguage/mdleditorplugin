/**
 * 
 */
package eu.ddmore.converter.mdl2json.domain.data;

import eu.ddmore.converter.mdl2json.utils.XtextWrapper;
import groovy.util.Expando;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.SourceBlock;

/**
 * @author jchard
 *
 */
public class Source extends Expando {

	private static final String SOURCE = "SOURCE"
	
	/**
	 * Constructor which unpacks all the content of the SourceBlock and adds them to the properties of this object
	 * 
	 * @param sourceBlock
	 */
	public Source(SourceBlock sourceBlock) {
	
		setProperty("identifier", sourceBlock.getIdentifier() );
		setProperty("symbolName", sourceBlock.getSymbolName().getName());
		if(sourceBlock.getInlineBlock()!=null) {
			setProperty("inlineBlock", sourceBlock.getInlineBlock());
		}
		for( Argument a : sourceBlock.getList().getArguments().getArguments() ) {
			setProperty(a.getArgumentName().getName(), XtextWrapper.unwrap(a.getExpression()));
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
			if(!k.equals("identifier") && !k.equals("symbolName")) { 
				properties.add("${k}=${v}")
			} 
		}
		
		"""
    ${SOURCE}{
        ${getProperty("symbolName")}=list(
            ${properties.join(",\n            ")}
        )
    }
"""
	}
}

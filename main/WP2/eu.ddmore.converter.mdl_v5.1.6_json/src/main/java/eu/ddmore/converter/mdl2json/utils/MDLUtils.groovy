package eu.ddmore.converter.mdl2json.utils

import org.apache.commons.lang.StringUtils

import static eu.ddmore.converter.mdl2json.domain.MDLPrintable.IDT

import org.ddmore.mdl.mdl.SymbolDeclaration

public class MDLUtils {

	/**
	 * Given a list of {@link SymbolDeclaration}s from the MDL object model,
	 * convert these to JSON, by converting each {@link SymbolDeclaration} into
	 * a Map of its parameters, augmented with the name of the Symbol.
	 * <p>
	 * A Map of Symbol name to Parameter map would be easier to manipulate, but
	 * then the ordering would be lost.
	 * <p>
	 * The "reverse" method is {@link #makeMDLFromSymbolNamedList(List)}/
	 * {@link #makeMDLFromSymbolNamedList(List, String)}.
	 * <p>
	 * @param symbolDeclList List of {@link SymbolDeclaration}
	 * @return JSON representation i.e. List of Maps
	 */
	public static List<Map> makeSymbolNamedList(List<SymbolDeclaration> symbolDeclList) {
		def symbList = []
		for (SymbolDeclaration sd : symbolDeclList) {
			Map params = [:]
			if (sd.getExpression() != null) {
				params.putAll(XtextWrapper.unwrap(sd.getExpression()))
			} else if (sd.getRandomList() != null) {
				params.putAll(XtextWrapper.unwrap(sd.getRandomList().getArguments()))
			}
			params.put("name", sd.getSymbolName().getName())
			symbList.add(params)
		}
		symbList
	}
	
	/**
	 * Given a fragment of JSON, namely a List of Maps each representing a {@link SymbolDeclaration},
	 * process each entry in the List, extracting its "name" mapentry (which will become the name of the
	 * Symbol) and converting the remaining mapentries into the parameter list in MDL form, to produce
	 * a list where each entry is of the required MDL form
	 * <code>MYVAR=list(myparam1=myvalue1,myparam2=myvalue2,...)</code>).
	 * Concatenate all these and return the resulting string.
	 * <p>
	 * For {@link SymbolDeclaration}s that have no parameters, the generated empty "=list()" is dropped,
	 * so this method can be used for blocks whose variables don't accept parameters.
	 * <p>
	 * The "reverse" method is {@link #makeSymbolNamedList(List)}.
	 * <p>
	 * @param listOfMaps - JSON content representing a List of {@link SymbolDeclaration}s
	 * @return the MDL equivalent
	 */
	public static String makeMDLFromSymbolNamedList(List<Map> listOfMaps) {
		makeMDLFromSymbolNamedList(listOfMaps, null)
	}
	
	/**
	 * As per {@link #makeMDLFromSymbolNamedList(List)} but enclosing the MDL fragment within
	 * <code>BLOCKNAME { ... }</code>.
	 * <p> 
	 * @param listOfMaps - JSON content representing a List of {@link SymbolDeclaration}s
	 * @param blockName - the name of the MDL block
	 * @return the MDL equivalent
	 */
	public static String makeMDLFromSymbolNamedList(List<Map> listOfMaps, final String blockName) {
		List strbuf = []
		listOfMaps.each {parameterAttributes ->
			// Sorry!
			// Iterate over the Map of parameterAttributes, removing the 'name' attribute (which is put on the LHS
			// of the written out MDL expression); turn it into the format "x = y"; then join them together with ","
			def paramAttrName = parameterAttributes['name']
			def realParameterAttributes = parameterAttributes.minus(['name':paramAttrName])
			if (realParameterAttributes.isEmpty()) {
				strbuf.add("${paramAttrName}") // otherwise get empty list i.e. "MY_ID=list()"
			} else {
				strbuf.add("${paramAttrName}=list(${realParameterAttributes.collect{ key,value -> "${key}=${value}" }.join(",")})")
			}
		}
		final String str = strbuf.join("\n${IDT*2}")
		if (StringUtils.isEmpty(blockName)) {
			return str;
		}
		return "\n${IDT}${blockName} {\n${IDT*2}" + str + "\n${IDT}}"
	}

}

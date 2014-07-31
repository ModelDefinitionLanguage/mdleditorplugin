package eu.ddmore.converter.mdl2json.utils
import java.util.Map;

import org.ddmore.mdl.mdl.SymbolDeclaration

public class MDLUtils {

	public static Map makeSymbolMap( List<SymbolDeclaration> symbolList ) {
		def retVal = [:]
		for( SymbolDeclaration sd : symbolList ) {
			String symbol = sd.getSymbolName().getName()
			retVal[symbol] = sd.getExpression() != null ? XtextWrapper.unwrap(sd.getExpression()) : null
		}
		retVal
	}

	/**
	 * Turn a symbol declaration into a map of "name" = "expression"
	 */
	public static Map makeSymbol(SymbolDeclaration symbolDeclaration) {
		Map m = [:]
		Map val = [:]
		if(symbolDeclaration.getExpression()!= null) {
			val = XtextWrapper.unwrap(symbolDeclaration.getExpression())	
		} else if(symbolDeclaration.getRandomList()!=null) {
			val = XtextWrapper.unwrap(symbolDeclaration.getRandomList().getArguments())
		}
		m[symbolDeclaration.getSymbolName().getName()] = val
		m
	}
	
	/**
	 * Turn a symbol declaration into a map of "name" = "expression"
	 */
//	private Map makeSymbol(SymbolDeclaration symbolDeclaration) {
//		Map m = [:]
//		m[symbolDeclaration.getSymbolName().getName()] = XtextWrapper.unwrap(symbolDeclaration.getExpression())
//		m
//	}
}

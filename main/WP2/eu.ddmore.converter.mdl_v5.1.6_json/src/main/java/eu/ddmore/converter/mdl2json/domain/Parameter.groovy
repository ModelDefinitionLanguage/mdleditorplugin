package eu.ddmore.converter.mdl2json.domain;

import javax.management.MBeanAttributeInfo;

import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.DiagBlock
import org.ddmore.mdl.mdl.MatrixBlock
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.ParameterObjectBlock
import org.ddmore.mdl.mdl.PriorParametersBlock
import org.ddmore.mdl.mdl.SameBlock
import org.ddmore.mdl.mdl.StructuralBlock
import org.ddmore.mdl.mdl.Symbol
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.SymbolName
import org.ddmore.mdl.mdl.SymbolNames
import org.ddmore.mdl.mdl.Symbols
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.VariabilityBlock
import org.ddmore.mdl.mdl.VariabilityBlockStatement

import eu.ddmore.converter.mdl2json.utils.XtextWrapper;
import eu.ddmore.converter.mdlprinting.MdlPrinter;

public class Parameter extends Expando {
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	static final String IDENTIFIER = "parobj"
	static final String VARIABILITY = "VARIABILITY"
	static final String TARGET = "TARGET"
	static final String STRUCTURAL = "STRUCTURAL"
	static final String PRIOR = "PRIOR";

	public Parameter(ParameterObject paramObject) {
		this.setProperty("identifier", IDENTIFIER)
		
		for( ParameterObjectBlock pob : paramObject.getBlocks()) {
			if(pob.getPriorBlock()) {
				setProperty(PRIOR, makePrior(pob.getPriorBlock()))
			} else if(pob.getStructuralBlock()) {
				setProperty(STRUCTURAL, makeStructuralModel(pob.getStructuralBlock()))
			} else if(pob.getTargetBlock()) {
				setProperty(TARGET, makeTarget(pob.getTargetBlock()))
			} else if(pob.getVariabilityBlock()) {
				setProperty(VARIABILITY, makeVariability(pob.getVariabilityBlock()))
			}
		}
	}
	
	public Parameter(Object json) {
		setProperty("identifier", IDENTIFIER)
	}
	
	private Map makeStructuralModel(StructuralBlock sb) {
		def retVal = [:]
		for( SymbolDeclaration sd : sb.getParameters() ) {
			String symbol = sd.getSymbolName().getName()
			retVal[symbol] = XtextWrapper.unwrap(sd.getExpression())	
		}	
		retVal
	}
	
	private List makeVariability(VariabilityBlock vb) {
		List retVal = []
		for(VariabilityBlockStatement s : vb.getStatements()) {
			if(s.getDiagBlock()) {
				Map diag = makeDiag(s.getDiagBlock())
				retVal.add(["${s.getDiagBlock().getIdentifier()}" : diag])
			}
			if(s.getMatrixBlock()) {
				MatrixBlock mb = s.getMatrixBlock()
				Map matrixMap = makeMatrix(mb) 
				retVal.add([ "${mb.getIdentifier()}" : matrixMap ])
			}
			if(s.getParameter()) {
				Map symbols = makeSymbol(s.getParameter())
				retVal.add(symbols)
			}
			if(s.getSameBlock()){
				Map same = makeSame(s.getSameBlock())
				retVal.add(["${s.getSameBlock().getIdentifier()}" : same ])
			} 
		}
		return retVal
	}
	
	private Map makeSymbol(SymbolDeclaration symbolDeclaration) {
		Map m = [:]
		m[symbolDeclaration.getSymbolName().getName()] = XtextWrapper.unwrap(symbolDeclaration.getExpression())
		m
	}

	private Map makeMatrix(MatrixBlock mb) {
		Map matrix = createVariabilityMatrix(mb.getIdentifier(), mb.getArguments())	
		matrix.put("content", getContentFromParameters(mb.getParameters()) )

		return matrix
	}

	private Map makeDiag(DiagBlock block) {
		Map matrix = createVariabilityMatrix(block.getIdentifier(), block.getArguments())	
		matrix.put("content", getContentFromParameters(block.getParameters()) )
		return matrix
	}	
	
	private Map makeSame( SameBlock block ) {
		Map matrix = createVariabilityMatrix(block.getIdentifier(), block.getArguments())	
		matrix.put("content", getContentFromSymbolNames(block.getParameters()))
		return matrix
	}
	
	private Map makeTarget(TargetBlock tb) {
		return [:]
	}
	
	private Map makePrior(PriorParametersBlock ppb) {
		
		return [:]
	}
	
	private Map createVariabilityMatrix( String identifier, Arguments arguments) {
		
		Map matrixArguments = [:]
		for(Argument a : arguments.getArguments()) {
			matrixArguments[a.getArgumentName().getName()] = mdlPrinter.toStr(a.getExpression())
		}
		matrixArguments
	}	

	private String getContentFromParameters(Symbols symbols) {
		StringBuffer content = new StringBuffer()
		int rowLength = 1
		int colNum = 1
		List rows = []
		for(Symbol s : symbols.getSymbols()) {
			String symbolString = ""
			if(colNum > rowLength) {
				symbolString += "\n"
				rowLength++
				colNum=1
			}
			String symbol = s.getSymbolName()?.getName()
			String expression = mdlPrinter.toStr(s.getExpression())
			symbolString += ( symbol ? "${symbol}=${expression}" : "${expression}" )

			rows.add(symbolString)
			colNum++
		}
		rows.join(",")
	}
		
	private List getContentFromSymbolNames(SymbolNames symbolNames) {
		List symbols = []
		for(SymbolName s : symbolNames.getSymbolNames()) {
			symbols.add(s.getName())
		}
		symbols
	}

	public String toMDL() {
		return """ ${IDENTIFIER} {
			}
		
		"""
	}

}

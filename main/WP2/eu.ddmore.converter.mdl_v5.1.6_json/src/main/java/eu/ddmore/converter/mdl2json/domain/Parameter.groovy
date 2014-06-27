package eu.ddmore.converter.mdl2json.domain;

import javax.management.MBeanAttributeInfo;

import org.apache.log4j.Logger
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
	private static Logger logger = Logger.getLogger(Parameter.class)
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	static final String IDENTIFIER = "parobj"
	static final String VARIABILITY = "VARIABILITY"
	static final String TARGET = "TARGET"
	static final String STRUCTURAL = "STRUCTURAL"
	static final String PRIOR = "PRIOR";
	
	static final String IDT = "    "

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
		if(json[VARIABILITY]) {
			setProperty(VARIABILITY, json[VARIABILITY])
		}
		if(json[STRUCTURAL]) {
			setProperty(STRUCTURAL, json[STRUCTURAL])
		}
	}
	
	/**
	 * Parse the structural model block
	 */
	private Map makeStructuralModel(StructuralBlock sb) {
		def retVal = [:]
		for( SymbolDeclaration sd : sb.getParameters() ) {
			String symbol = sd.getSymbolName().getName()
			retVal[symbol] = XtextWrapper.unwrap(sd.getExpression())	
		}	
		retVal
	}
	
	/**
	 * Parse the variability model block
	 */
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
				retVal.add(makeSymbol(s.getParameter()))
			}
			if(s.getSameBlock()){
				Map same = makeSame(s.getSameBlock())
				retVal.add(["${s.getSameBlock().getIdentifier()}" : same ])
			} 
		}
		return retVal
	}
	
	/**
	 * Parse the target block
	 */
	private Map makeTarget(TargetBlock tb) {
		logger.warn("Target not supported yet")
		return [:]
	}
	
	/**
	 * Parse the prior block
	 */
	private Map makePrior(PriorParametersBlock ppb) {
		logger.warn("Prior not supported yet")
		return [:]
	}
	
	/*
	 * Parse the MatrixBlock
	 */
	private Map makeMatrix(MatrixBlock mb) {
		Map matrix = createVariabilityMatrix(mb.getArguments())	
		matrix.put("content", getLowerTriangularMatrixFromSymbols(mb.getParameters()) )
		return matrix
	}

	/**
	 * Parse the DiagBlock
	 */
	private Map makeDiag(DiagBlock block) {
		Map matrix = createVariabilityMatrix(block.getArguments())	
		matrix.put("content", getLowerTriangularMatrixFromSymbols(block.getParameters()) )
		return matrix
	}	
	
	/**
	 * Parse the SameBlock
	 */
	private Map makeSame( SameBlock block ) {
		Map matrix = createVariabilityMatrix(block.getArguments())	
		matrix.put("content", getContentFromSymbolNames(block.getParameters()))
		return matrix
	}
	
	/**
	 * Parses Arguments. 
	 * 
	 * @param arguments
	 * @return Returns a Map containing, for each Argument, an entry for "argument = expression"
	 */
	private Map createVariabilityMatrix( Arguments arguments ) {
		Map matrixArguments = [:]
		for(Argument a : arguments.getArguments()) {
			matrixArguments[a.getArgumentName().getName()] = mdlPrinter.toStr(a.getExpression())
		}
		matrixArguments
	}	

	/**
	 * Extract the lower triangular matrix from a list of Symbol
	 * 
	 * @param symbols
	 * @return A String, representing the lower triangular matrix, separated by newlines
	 */
	private String getLowerTriangularMatrixFromSymbols(Symbols symbols) {
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
		
	/**
	 * Turn a symbol declaration into a map of "name" = "expression"
	 */
	private Map makeSymbol(SymbolDeclaration symbolDeclaration) {
		Map m = [:]
		m[symbolDeclaration.getSymbolName().getName()] = XtextWrapper.unwrap(symbolDeclaration.getExpression())
		m
	}

	/**
	 * Returns a list of symbol names
	 */
	private List getContentFromSymbolNames(SymbolNames symbolNames) {
		List symbols = []
		for(SymbolName s : symbolNames.getSymbolNames()) {
			symbols.add(s.getName())
		}
		symbols
	}

	/**
	 * Create MDL from a Map of JSON that represents the structural model 
	 * @param structural
	 * @return
	 */
	public String makeStructuralMDL(Map structural) {
		StringBuffer strucStr = new StringBuffer()
		strucStr.append("\n${IDT}STRUCTURAL{\n")
		structural.each {parameterName, parameterAttributes ->
			// Sorry!
			// v is a map of attributes - iterate over them and turn it into the format "x = y"
			// then join them together with ","
			strucStr.append("${IDT*2}${parameterName}=list(${parameterAttributes.collect{ key,value->"${key}=${value}"}.join(",")})\n")
		}
		strucStr.append("${IDT}}")
		strucStr.toString()
	}
	
	public String makeVariabilityMDL(List variability) {
		StringBuffer strucStr = new StringBuffer()
		strucStr.append("\n${IDT}VARIABILITY{\n")
		variability.each { it ->
			it.each { k,v ->
				// block could be a matrix, a diag, a same or a named parameter
				switch(k) {
					case "matrix":
						// matrix is lower triangular, example "matrix(name="struc2", type="VAR") { matrix }
						String mx = v['content']
						mx = mx.split("\n").join("\n${IDT*3}")
						strucStr.append("${IDT*2}matrix(name=\"${v['name']}\", type=\"${v['type']}\"){\n${IDT*3}${mx}\n${IDT*2}}\n")
						break;
					case "diag":
						// example "diag(name="struc2", type="VAR") { list of parameters }"
						strucStr.append("${IDT*2}diag(name=\"${v['name']}\", type=\"${v['type']}\"){\n${IDT*3}${v['content']}\n${IDT*2}}\n")
						break;
					case "same":
						// example "same(name="struc2") { PPV_IOV_IN_PRL0_2 }"
						strucStr.append("${IDT*2}same(name=\"${v['name']}\"){\n${IDT*3}${v['content'].join(IDT*3+"\n")}\n${IDT*2}}\n")
						break;
					default:
						// Otherwise 'key' is the variability parameter name
						strucStr.append("${IDT*2}${k}=list(${v.collect{ key,value->"${key}=${value}"}.join(",")})\n")
						break;	
				}
			}
		}
		strucStr.append("${IDT}}")
		strucStr.toString()
	}
	
	/**
	 * Convert this parameter into MDL
	 * @return
	 */
	public String toMDL() {
		Properties p = getProperties()
		
		StringBuffer mdl = new StringBuffer()
		if(p.containsKey(STRUCTURAL)) {
			mdl.append(makeStructuralMDL(p.get(STRUCTURAL)))
		}
		if(p.containsKey(VARIABILITY)) {
			mdl.append(makeVariabilityMDL(p.get(VARIABILITY)))
		}
		
		return """${IDENTIFIER} {${mdl.toString()}
}
"""
	}

}

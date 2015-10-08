/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain.legacy;

import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.ParameterObjectBlock
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.VariabilityBlock

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.interfaces.TopLevelBlock
import eu.ddmore.converter.mdl2json.utils.legacy.XtextWrapper;

public class Parameter extends Expando implements MDLPrintable, MDLAsJSON, TopLevelBlock {

    public static final TopLevelBlock.Identifier IDENTIFIER = TopLevelBlock.Identifier.parobj

    public static final String STRUCTURAL = "STRUCTURAL"
    public static final String VARIABILITY = "VARIABILITY"
    public static final String DECLARED_VARIABLES = "DECLARED_VARIABLES"

    public Parameter(ParameterObject paramObject) {

        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)

        for (ParameterObjectBlock block : paramObject.getBlocks()) {

            if (block.getStructuralBlock()) {
                setProperty(STRUCTURAL, VariablesList.buildFromSymbolDeclarations(block.getStructuralBlock().getParameters()))
			}
            if (block.getVariabilityBlock()) {
                setProperty(VARIABILITY, makeVariability(block.getVariabilityBlock()))
            }
            if (block.getDeclaredVariables()) {
                setProperty(DECLARED_VARIABLES, VariablesList.buildFromSymbolDeclarations(block.getDeclaredVariables().getVariables()))
            }
            if (block.getPriorBlock()) {
                throw new UnsupportedOperationException("Prior Parameters block within Parameter Object not supported yet")
            }

        }
    }

    public Parameter(Map json) {

        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)

        if (json[STRUCTURAL]) {
            setProperty(STRUCTURAL, VariablesList.buildFromJSON(json[STRUCTURAL]))
        }
        if (json[VARIABILITY]) {
            setProperty(VARIABILITY, json[VARIABILITY])
        }
        if (json[DECLARED_VARIABLES]) {
            setProperty(DECLARED_VARIABLES, VariablesList.buildFromJSON(json[DECLARED_VARIABLES]))
        }

    }

    /**
     * Parse the variability model block
     */
    private List makeVariability(VariabilityBlock vb) {
        List retVal = []

        for (SymbolDeclaration s : vb.getParameters()) {
            def v = new Variable(s)
            final String varName = v.getName()

            // Parentheses required around Variable.NAME_KEY so is treated as string variable not string literal
            retVal.add([ "${varName}" : v.getProperties().minus((Variable.NAME_KEY) : "${varName}") ])
        }

        // TODO: DDMORE-957: This used to be the following. As of Product 4 development phase, the MDL grammar is
        // still in development with more features being added, so it is quite possible that these Diag, Matrix and
        // Same blocks will reappear in some form. Once MDL grammar has stabilised (Product 4 / Public Release ?)
        // then we will know if we have to reinstate and rewrite this, or remove it.
//      for (VariabilityBlockStatement s : vb.getStatements()) {
//          if (s.getDiagBlock()) {
//              Map diag = makeDiag(s.getDiagBlock())
//              retVal.add([ "${s.getDiagBlock().getIdentifier()}" : diag ])
//          }
//          if (s.getMatrixBlock()) {
//              MatrixBlock mb = s.getMatrixBlock()
//              Map matrixMap = makeMatrix(mb)
//              retVal.add([ "${mb.getIdentifier()}" : matrixMap ])
//          }
//          if (s.getParameter()) {
//              def v = new Variable(s.getParameter())
//              retVal.add([ "${v['name']}" : v.getProperties().minus('name':"${v['name']}") ])
//          }
//          if (s.getSameBlock()){
//              Map same = makeSame(s.getSameBlock())
//              retVal.add([ "${s.getSameBlock().getIdentifier()}" : same ])
//          }
//      }
        return retVal
    }

    //
    // TODO: DDMORE-957: See above comment.
    //

//  /**
//   * Parse the MatrixBlock
//   */
//  private Map makeMatrix(MatrixBlock mb) {
//      Map matrix = createVariabilityMatrix(mb.getArguments())
//      matrix.put(CONTENT_PROPNAME, getLowerTriangularMatrixFromParameters(mb.getParameters()) )
//      return matrix
//  }

//  /**
//   * Parse the DiagBlock
//   */
//  private Map makeDiag(DiagBlock block) {
//      Map matrix = createVariabilityMatrix(block.getArguments())
//      matrix.put(CONTENT_PROPNAME, getLowerTriangularMatrixFromParameters(block.getParameters()) )
//      return matrix
//  }

//  /**
//   * Parse the SameBlock
//   */
//  private Map makeSame(SameBlock block) {
//      Map matrix = createVariabilityMatrix(block.getArguments())
//      matrix.put(CONTENT_PROPNAME, getContentFromSymbolNames(block.getParameters()))
//      return matrix
//  }

    /**
     * Parses Arguments.
     *
     * @param arguments
     * @return Returns a Map containing, for each Argument, an entry for "argument = expression"
     */
    private Map createVariabilityMatrix( Arguments arguments ) {
        Map matrixArguments = [:]
        for(Argument a : arguments.getArguments()) {
            matrixArguments[a.getArgumentName().getName()] = XtextWrapper.unwrap(a.getExpression())
        }
        matrixArguments
    }

    /**
     * Extract the lower triangular matrix from a list of Arguments.
     *
     * @param parameters
     * @return A String, representing the lower triangular matrix, separated by newlines
     */
    private String getLowerTriangularMatrixFromParameters(Arguments parameters) {
        StringBuffer content = new StringBuffer()
        int rowLength = 1
        int colNum = 1
        List rows = []
        for (Argument p : parameters.getArguments()) {
            String symbolString = ""
            if (colNum > rowLength) {
                symbolString += "\n"
                rowLength++
                colNum=1
            } else if (colNum > 1) {
                symbolString += " "
            }
            String symbol = p.getArgumentName()?.getName()
            String expression = XtextWrapper.unwrap(p.getExpression())
            symbolString += ( symbol ? "${symbol}=${expression}" : "${expression}" )

            rows.add(symbolString)
            colNum++
        }
        rows.join(",")
    }

    public String makeVariabilityMDL(List variability) {
        StringBuffer strBuf = new StringBuffer()
        strBuf.append("\n${IDT}VARIABILITY {\n")
        variability.each { Map<String, Map> m ->

            /*
             * Block could be a matrix, a diag, a same or a named parameter
             */

            def matrixParam = m['matrix']
            def diagParam = m['diag']
            def sameParam = m['same']

            strBuf.append("${IDT*2}")
            if (matrixParam) {
                // matrix is lower triangular, example "matrix(name="struc2", type="VAR") { matrix }
                String content = matrixParam[CONTENT_PROPNAME].split("\n").join("\n${IDT*3}")
                strBuf.append("matrix(name=${matrixParam['name']}, type=${matrixParam['type']}) {\n${IDT*3}${content}\n${IDT*2}}")
            } else if (diagParam) {
                // example "diag(name="struc2", type="VAR") { list of parameters }"
                String content = diagParam[CONTENT_PROPNAME].split("\n").join("\n${IDT*3}")
                strBuf.append("diag(name=${diagParam['name']}, type=${diagParam['type']}) {\n${IDT*3}${content}\n${IDT*2}}")
            } else if (sameParam) {
                // example "same(name="struc2") { PPV_IOV_IN_PRL0_2 }"
                String content = sameParam[CONTENT_PROPNAME].split("\n").join("\n${IDT*3}")
                strBuf.append("same(name=${sameParam['name']}) {\n${IDT*3}${content}\n${IDT*2}}")
            } else {
                // Otherwise is a named parameter, example "RUV_CVCP : {value=0.0118, type=VAR}"
                m.each{String varName, Map var -> // Only actually one entry in the map
                    var.put(Variable.NAME_KEY, varName)
                    strBuf.append(new Variable(var).toMDL())
                }
            }
            strBuf.append("\n")

        }
        strBuf.append("${IDT}}\n")
        strBuf.toString()
    }
    
    /**
     * {@inheritDoc}
     */
    public TopLevelBlock.Identifier getIdentifier() {
        return IDENTIFIER
    }

    /**
     * {@inheritDoc}
     */
    public String toMDL() {
        def p = getProperties()

        StringBuffer mdl = new StringBuffer()
        if (p.containsKey(DECLARED_VARIABLES)) {
            mdl.append("\n${IDT}DECLARED_VARIABLES {\n${IDT*2}${getProperty(DECLARED_VARIABLES).toMDL()}\n${IDT}}\n")
        }
        if (p.containsKey(STRUCTURAL)) {
            mdl.append("\n${IDT}STRUCTURAL {\n${IDT*2}${getProperty(STRUCTURAL).toMDL()}\n${IDT}}\n")
        }
        if (p.containsKey(VARIABILITY)) {
            mdl.append(makeVariabilityMDL(getProperty(VARIABILITY)))
        }
        return """${IDENTIFIER} {
${mdl.toString()}
}
"""
    }

}
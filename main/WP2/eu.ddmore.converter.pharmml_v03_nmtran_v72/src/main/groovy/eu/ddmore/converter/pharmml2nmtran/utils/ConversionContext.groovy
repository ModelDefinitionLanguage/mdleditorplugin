/*******************************************************************************
 * Copyright (C) 2014 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.pharmml2nmtran.utils

import java.util.Map;

import javax.xml.bind.JAXBElement;

import eu.ddmore.converter.pharmml2nmtran.equivalence.EquivalenceClass
import eu.ddmore.converter.pharmml2nmtran.equivalence.EquivalenceClassesComputer;
import eu.ddmore.converter.pharmml2nmtran.model.CorrelationKey
import eu.ddmore.converter.pharmml2nmtran.model.Omega
import eu.ddmore.converter.pharmml2nmtran.model.Theta;
import eu.ddmore.converter.pharmml2nmtran.statements.EstimationStatement
import eu.ddmore.converter.pharmml2nmtran.statements.NMTranFormatter
import eu.ddmore.converter.pharmml2nmtran.statements.OmegasStatement
import eu.ddmore.converter.pharmml2nmtran.statements.PredStatement
import eu.ddmore.converter.pharmml2nmtran.statements.SigmasStatement
import eu.ddmore.converter.pharmml2nmtran.statements.SimulationStatement
import eu.ddmore.converter.pharmml2nmtran.statements.TableStatement
import eu.ddmore.converter.pharmml2nmtran.statements.ThetasStatement;
import eu.ddmore.libpharmml.dom.PharmML;
import eu.ddmore.libpharmml.dom.commontypes.CommonVariableDefinitionType
import eu.ddmore.libpharmml.dom.commontypes.DerivativeVariableType
import eu.ddmore.libpharmml.dom.commontypes.FuncParameterDefinitionType
import eu.ddmore.libpharmml.dom.commontypes.FunctionDefinitionType
import eu.ddmore.libpharmml.dom.commontypes.IntValueType
import eu.ddmore.libpharmml.dom.commontypes.RealValueType;
import eu.ddmore.libpharmml.dom.commontypes.Rhs;
import eu.ddmore.libpharmml.dom.commontypes.ScalarRhs
import eu.ddmore.libpharmml.dom.commontypes.SymbolRefType;
import eu.ddmore.libpharmml.dom.commontypes.VariableAssignmentType
import eu.ddmore.libpharmml.dom.commontypes.VariableDefinitionType
import eu.ddmore.libpharmml.dom.maths.BinopType;
import eu.ddmore.libpharmml.dom.maths.Condition;
import eu.ddmore.libpharmml.dom.maths.ConstantType
import eu.ddmore.libpharmml.dom.maths.Equation;
import eu.ddmore.libpharmml.dom.maths.FunctionCallType
import eu.ddmore.libpharmml.dom.maths.LogicBinOpType;
import eu.ddmore.libpharmml.dom.maths.PieceType;
import eu.ddmore.libpharmml.dom.maths.PiecewiseType;
import eu.ddmore.libpharmml.dom.maths.UniopType;
import eu.ddmore.libpharmml.dom.maths.FunctionCallType.FunctionArgument
import eu.ddmore.libpharmml.dom.modeldefn.CommonParameterType
import eu.ddmore.libpharmml.dom.modeldefn.CorrelationType
import eu.ddmore.libpharmml.dom.modeldefn.CovariateDefinitionType
import eu.ddmore.libpharmml.dom.modeldefn.CovariateModelType
import eu.ddmore.libpharmml.dom.modeldefn.CovariateRelationType
import eu.ddmore.libpharmml.dom.modeldefn.GaussianObsError
import eu.ddmore.libpharmml.dom.modeldefn.GeneralObsError
import eu.ddmore.libpharmml.dom.modeldefn.IndividualParameterType
import eu.ddmore.libpharmml.dom.modeldefn.ObservationErrorType
import eu.ddmore.libpharmml.dom.modeldefn.ObservationModelType
import eu.ddmore.libpharmml.dom.modeldefn.ParameterModelType
import eu.ddmore.libpharmml.dom.modeldefn.ParameterRandomVariableType
import eu.ddmore.libpharmml.dom.modeldefn.SimpleParameterType;
import eu.ddmore.libpharmml.dom.modeldefn.StructuralModelType
import eu.ddmore.libpharmml.dom.modellingsteps.EstimationStepType
import eu.ddmore.libpharmml.dom.modellingsteps.InitialEstimateType
import eu.ddmore.libpharmml.dom.modellingsteps.ParameterEstimateType
import eu.ddmore.libpharmml.dom.modellingsteps.SimulationStepType
import eu.ddmore.libpharmml.dom.modellingsteps.ToEstimateType
import eu.ddmore.libpharmml.dom.trialdesign.PopulationMappingType
import eu.ddmore.libpharmml.dom.uncertml.NormalDistribution

/**
 * This is a main conversion class that provides overloaded 'convert' methods 
 * that convert libPharmML types to NMTRAN. 
 * The first argument of each convert method is a libPharmML type we want to convert.
 * In many cases maps are passed as arguments for string substitutions 
 * (inspired by the substitution evaluation strategy in lamda-calculus).
 */
public class ConversionContext extends NMTranFormatter {

    private PharmML pmlDOM
    private Parameters parameters
    private Set<String> symbolRefs
    private Map<String, String> epsilonToSigma
    private Map<String, FunctionDefinitionType> functions
	private PredStatement predStatement
	SigmasStatement sigmasStatement
    private String inputHeaders
    private String fileBase
    private List<String> omegasInPrintOrder
    private Map<String,String> simpleParameterToNmtran

    public ConversionContext(PharmML pmlDOM, File src) {
        this.pmlDOM = pmlDOM
        fileBase = src.name.substring(0, src.name.indexOf('.'))
        symbolRefs = new HashSet<String>()
        epsilonToSigma = new HashMap<String, String>()
        functions = new HashMap<String, FunctionDefinitionType>()
        omegasInPrintOrder = new ArrayList<String>()
        simpleParameterToNmtran = new HashMap<String,String>()
        parameters = new Parameters(pmlDOM)
        parameters.init()
        findFunctions()
        sigmasStatement = new SigmasStatement("pmlDOM":pmlDOM, "conversionContext":this)
        epsilonToSigma = sigmasStatement.epsilonToSigma

		predStatement = new PredStatement(pmlDOM, parameters, this)
		simpleParameterToNmtran = predStatement.simpleParameterToNmtran
    }

    private void findFunctions() {
        pmlDOM.functionDefinition.each {
            FunctionDefinitionType type = it;
            functions.put(type.symbId, type)
        }
    }

    def getProblemStatement() {
        "\$PROBLEM ${pmlDOM.name.value}\n"
    }

    def getInputStatement(List<String> headers) {
        inputHeaders = headers.join(' ')
        "\$INPUT ${inputHeaders}\n"
    }

    def getEstimationStatement() {
        new EstimationStatement().getStatement(pmlDOM)
    }

    def getSimulationStatement() {
        new SimulationStatement().getStatement(pmlDOM)
    }

    def getPred() {
        predStatement.getStatement()
    }

    def getTableStatement() {
        new TableStatement("parameters":parameters, "inputHeaders":inputHeaders, conversionContext:this).getStatement(fileBase)
    }

    def getThetasStatement() {
        new ThetasStatement("parameters":parameters, "pmlDOM":pmlDOM, conversionContext:this).getStatement()
    }

    def getOmegasStatement() {
        new OmegasStatement("parameters":parameters, "pmlDOM":pmlDOM, "conversionContext":this).getStatement()
    }

    public StringBuilder getSigmasStatement() {
        sigmasStatement.getStatement()
    }

    public StringBuilder convert(VariableAssignmentType type) {
        return convert(type, true)
    }

    /**
     * 
     * @param type the VariableAssignmentType to convert
     * @param verbose if true, NMTRAN comments are appended in the end of the line
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(VariableAssignmentType type, boolean verbose) {
        def sb = new StringBuilder();
        sb << convert(type.assign, false)
        if (verbose) {
            sb << " ; ${rename(type.symbRef.symbIdRef.toUpperCase())}\n"
        }
        sb
    }

    public StringBuilder convert(FunctionCallType type) {
        convert(type, new HashMap<String, String>())
    }

    /**
     * 
     * @param type the FunctionCallType to convert
     * @param inputNameToValue a string substitution map (inspired by the substitution evaluation strategy in lamda-calculus).
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(FunctionCallType type, Map<String, String> inputNameToValue) {
        StringBuilder sb = new StringBuilder();
        String functionName = type.symbRef.symbIdRef
        List<String> args = []
        type.functionArgument.each {
            if (it.constant) {
                args.add(convert(it.constant))
            } else if (it.equation) {
                args.add(convert(it.equation, inputNameToValue) )
            } else if (it.scalar) {
                args.add(convert(it.scalar.value) )
            } else if (it.symbRef) {
                args.add(toTheta(convert(it.symbRef).toString() ))
            }
        }
        sb << convert(functions.get(functionName), args)
    }

    /**
     * 
     * @param type the FunctionDefinitionType to convert
     * @param args the list of function arguments
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(FunctionDefinitionType type, List<String> args) {
        StringBuilder sb = new StringBuilder();
        Map<String, String> inputNameToValue = new HashMap<String, String>()
        for (int i=0; i<type.functionArgument.size(); i++) {
            String argName = type.functionArgument[i].symbId
            String argValue = args[i]
            inputNameToValue.put(argName, argValue)
        }
        if (type.definition.equation) {
            sb.append( convert(type.definition.equation, inputNameToValue) )
        } else {
            sb.append( convert(type.definition.symbRef, inputNameToValue) )
        }
        sb
    }

    /**
     * 
     * @param s
     * @return a Theta NMTRAN representation of s, if s is a Theta, otherwise s
     */
    private String toTheta(String s) {
        Theta theta = parameters.isTheta(s)
        theta ? "THETA(${theta.index})" : s
    }

    public StringBuilder convert(ParameterEstimateType type) {
        return convert(type, true)
    }

    /**
     * 
     * @param type the ParameterEstimateType type to convert
     * @param verbose if true NMTRAN comment will be appended in the end of the line and 
     *                if the parameter is fixed the 'FIX' label will be appended
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(ParameterEstimateType type, boolean verbose) {
        String name = type.symbRef.symbIdRef

        def sb = new StringBuilder("(")
        if (type.lowerBound) {
            sb << "${convert(type.lowerBound)},"
        }
        sb << convert(type.initialEstimate)
        if (type.upperBound) {
            sb << ",${convert(type.upperBound)}"
        }

        if (!verbose || !type.initialEstimate.fixed) {
            sb << ")"
        }

        if (verbose) {
            if (type.initialEstimate.fixed) {
                sb << " FIX)"
            }
            sb << " ; ${name}\n"
        }
        sb
    }

    public StringBuilder convert(ScalarRhs type) {
        StringBuilder sb = new StringBuilder();
        type.scalar.value
        if (type.scalar) {
            sb << convert(type.scalar.value)
        }
        sb
    }

    public StringBuilder convert(InitialEstimateType type) {
        StringBuilder sb = new StringBuilder();
        if (type.equation) {
            sb.append( convert(type.equation) )
        }

        if (type.scalar) {
            sb.append( convert(type.scalar.value) )
        }
        sb
    }

    public StringBuilder convert(PiecewiseType piecewise, Map<String,String> simpleParameterToNmtran) {
        convert(piecewise, null, simpleParameterToNmtran)
    }

    /**
     * 
     * @param piecewise the PiecewiseType type to convert
     * @param variableName the name of the variable that will be assigned to different values dependening on the condition 
     * @param simpleParameterToNmtran a map of precomputed nmtran representations of parameters
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(PiecewiseType piecewise, String variableName, Map<String,String> simpleParameterToNmtran) {
        StringBuilder sb = new StringBuilder();
        piecewise.piece.each {
            PieceType pieceIt = (PieceType)it
            if (sb) {
                sb << endline("ELSE");
            }
            StringBuilder pieceBuilder = convert(pieceIt, variableName);

            for (String symbolRef : symbolRefs) {
                String nmTranSymbolDefinition = simpleParameterToNmtran.get(symbolRef);
                if (nmTranSymbolDefinition && !nmTranSymbolDefinition.equalsIgnoreCase(rename(symbolRef))) {
                    sb << endline(indent("${nmTranSymbolDefinition}"))
                }
            }
            sb << pieceBuilder
        }
        sb << endline("ENDIF");
    }

    public StringBuilder convert(VariableDefinitionType type) {
        convert(type, new HashMap<String, String>())
    }

    /**
     * 
     * @param type the VariableDefinitionType type to convert
     * @param inputNameToVariable a string substitution map
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(VariableDefinitionType type, Map<String, String> inputNameToVariable) {
        def sb = new StringBuilder()
        String name = type.symbId
        if (inputNameToVariable.containsKey(name)) {
            name = inputNameToVariable[name]
        }
        sb << "\t${rename(name.toUpperCase())}${convert(type.assign, true, inputNameToVariable)}"
    }

    /**
     * 
     * @param piece the PieceType to convert
     * @param variableName the name of the variable to assign a value
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(PieceType piece, String variableName) {
        StringBuilder sb = new StringBuilder();
        if (piece.condition) {
            sb << convert(piece.condition) << "\n";
        }
        if (piece.scalar) {
            sb << convert(piece.scalar.value, variableName) << "\n";
        }
        if (piece.binop) {
            sb << endline("\t${rename(variableName)}=${convert(piece.binop)}")
        } else if(piece.symbRef) {
			sb << endline("\t${rename(variableName)}=${convert(piece.symbRef)}")
        }
        sb
    }

    /**
     * 
     * @param type the RealValueType type to convert
     * @param variableName the name of the variable to assign a value
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(RealValueType type, String variableName) {
        def sb = new StringBuilder()
        sb << " THEN\n\t${rename(variableName)}=${type.value}\n"
    }

    public String convert(RealValueType type) {
        return convert(type, new HashMap<String, String>())
    }

    public String convert(RealValueType type, Map<String, String> inputNameToValue) {
        type.value
    }

    public StringBuilder convert(Condition condition) {
        StringBuilder sb = new StringBuilder();
        if (condition.logicBinop) {
            sb << convert(condition.logicBinop)
        }
        sb
    }

    public StringBuilder convert(LogicBinOpType logicBinaryOperator) {
        def sb = "IF(${convert(logicBinaryOperator.content.get(0).value)}.${getMathRepresentationOf(logicBinaryOperator.op)}"
        sb << ".${convert(logicBinaryOperator.content.get(1).value)})"
    }

    public String convert(IntValueType type) {
        return type.value
    }

    public StringBuilder convert(BinopType binopType) {
        return convert(binopType, new HashMap<String, String>())
    }

    /**
     * 
     * @param binopType the BinopType to convert
     * @param inputNameToValue a string substitution map
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(BinopType binopType, Map<String, String> inputNameToValue) {
        def sb = new StringBuilder()
        String left = convert(binopType.content.get(0).value, inputNameToValue)
        String operator = getMathRepresentationOf(binopType.op)
        String right = convert(binopType.content.get(1).value, inputNameToValue)
        switch(binopType.op) {
            case 'plus':
                sb << sum(left, operator, right)
            break;
            case 'minus':
                sb << subtraction(left, operator, right)
            break;
            case 'divide':
                sb << division(left, operator, right)
            break;
            default:
                sb << genericMathOperation(left, operator, right)
            break
        }
        return sb
    }
    
    private String sum(String left, String operator, String right) {
        left + operator + right
    }
    
    private String subtraction(String left, String operator, String right) {
        '(' + addParenthesesIfNeeded(left) + operator + addParenthesesIfNeeded(right) + ')'
    }
    
    private String division(String left, String operator, String right) {
        addParenthesesIfNeeded(left) + operator + "(${right})"
    }
    
    private String genericMathOperation(String left, String operator, String right) {
        addParenthesesIfNeeded(left) + operator + addParenthesesIfNeeded(right)
    }
    private String addParenthesesIfNeeded(String operand) {
        if ( (operand.contains('+') || operand.contains('-')) && !operand.startsWith('(')) {
            return '('+ operand +')'
        } else {
            return operand
        }
    }

    public StringBuilder convert(UniopType type) {
        return convert(type, new HashMap<String, String>())
    }

    /**
     * 
     * @param type the UniopType type to convert
     * @param inputNameToValue a string substitution map
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(UniopType type, Map<String, String> inputNameToValue) {
        StringBuilder sb = new StringBuilder()

        sb.append(getMathRepresentationOf(type.op))

        if (type.symbRef) {
            sb.append( convert(type.symbRef, inputNameToValue));
        } else {
            sb.append('(')

            if (type.binop) {
                sb.append( convert(type.binop, inputNameToValue) );
            }
            if (type.constant) {
                sb.append(  convert(type.constant, inputNameToValue) );
            }
            if (type.functionCall) {
                sb.append( convert(type.functionCall, inputNameToValue));
            }
            if (type.scalar) {
                sb.append( convert(type.scalar.value));
            }
            if (type.uniop) {
                sb.append( convert(type.uniop, inputNameToValue));
            }
            sb.append(')')
        }
        sb
    }


    public StringBuilder convert(SymbolRefType type) {
        return convert(type, new HashMap<String, String>())
    }

    /**
     * 
     * @param type the SymbolRefType type to convert
     * @param inputNameToValue a string substitution map
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(SymbolRefType type, Map<String, String> inputNameToValue) {
        StringBuilder sb = new StringBuilder()
        String name = type.symbIdRef

        CommonVariableDefinitionType structVar = parameters.getStructuralVariable(name)
        Theta theta = parameters.isTheta(name)
        if (theta) {
            sb.append(theta.toIndexString())
        } else if (parameters.etas.contains(name)) {
            sb << "ETA(${parameters.getOmegaIndexFromEta(name)})"
        } else if (epsilonToSigma.get(name)) {
            String sigmaName = epsilonToSigma.get(name)
            int sigmaIndex = parameters.isSigma(sigmaName).index
            sb << "EPS(${sigmaIndex})"
        } else if (parameters.getDosingTimeVarname().equals(name)) {
            sb.append(parameters.getDosingTime())
        } else if (name.equals("t")) {
            sb.append('TIME')
        } else {
            if (inputNameToValue.containsKey(name)) {
                name = inputNameToValue.get(name)
            } else if (parameters.varToName.containsKey(name)) {
                name = parameters.varToName.get(name)
            }
            if (!name.contains("A(")) {
                name = rename(name)
            }
            sb.append(name.toUpperCase())
        }
        symbolRefs.add(type.symbIdRef);
        sb
    }

    /**
     * 
     * @param type the DerivativeVariableType type to convert
     * @param index the order of type among all the derivative variables of the model
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(DerivativeVariableType type, int index) {
        StringBuilder sb = new StringBuilder()
        sb << "\tDADT(${index})${convert(type.assign)}\n"
        sb
    }

    public StringBuilder convert(SimpleParameterType type) {
        def sb = new StringBuilder()
        sb << "${rename(type.symbId.toUpperCase())}"
        if (type.assign) {
            sb << convert(type.assign)
        }
        sb
    }

    public StringBuilder convert(Rhs type) {
        convert(type, true, new HashMap<String, String>())
    }

    /**
     * 
     * @param type the Rhs type to convert
     * @param verbose if true '=' symbol will be printed before the assignment
     * @param inputNameToValue a string substitution map
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(Rhs type, boolean verbose, Map<String, String> inputNameToValue) {
        def sb = new StringBuilder()
        if (verbose) {
            sb << "="
        }
        if (type.equation) {
            sb << convert(type.equation, inputNameToValue)
        } else if (type.symbRef) {
            sb << convert(type.symbRef, inputNameToValue)
        } else if (type.scalar) {
            sb << type.scalar.value.value
        }
        sb
    }

    public StringBuilder convert(Equation type) {
        return convert(type, new HashMap<String, String>())
    }

    /**
     * 
     * @param type the Equation type to convert
     * @param inputNameToValue a string substitution map
     * @return he NMTRAN representation of the type
     */
    public StringBuilder convert(Equation type, Map<String, String> inputNameToValue) {
        StringBuilder sb = new StringBuilder()
        if (type.binop) {
            sb << convert(type.binop, inputNameToValue)
        } else if (type.symbRef) {
            sb << convert(type.symbRef, inputNameToValue)
        } else if(type.piecewise) {
        	sb << convert(type.piecewise, inputNameToValue)
		}
        
        sb
    }

    /**
     * 
     * @param type the ConstantType to convert
     * @param inputNameToValue a string substitution map
     * @return the NMTRAN representation of the type
     */
    public StringBuilder convert(ConstantType type, Map<String, String> inputNameToValue) {
        StringBuilder sb = new StringBuilder()
        sb << type.op
        sb
    }

    public StringBuilder convert(CovariateDefinitionType type) {
        StringBuilder sb = new StringBuilder()
        String name = type.symbId
        if (type.continuous && type.continuous.abstractContinuousUnivariateDistribution) {
            String mean = type.continuous.abstractContinuousUnivariateDistribution.value.mean.var.varId
            String stddev = type.continuous.abstractContinuousUnivariateDistribution.value.stddev.var.varId
            Omega omega = parameters.isOmega(stddev)

            sb << "${rename(name.toUpperCase())} = ${mean.toUpperCase()}*EXP(ETA(${omega.index}))"
        }
        sb
    }

	
    private String getMathRepresentationOf(String s) {
        if (s.equals("minus")) {
            return "-";
        } else if (s.equals("plus")) {
            return "+";
        } else if (s.equals("times")) {
            return "*";
        } else if (s.equals("divide")) {
            return "/";
        } else if (s.equals("exp")) {
            return "EXP";
        } else if (s.equals("lt")) {
            return "LT";
        } else if (s.equals("leq")) {
            return "LE";
        } else if (s.equals("gt")) {
            return "GT";
        } else if (s.equals("geq")) {
            return "GE";
        } else if (s.equals("eq")) {
            return "EQ";
        } else if (s.equals("neq")) {
            return "NE";
        } else if (s.equals("and")) {
            return "AND";
        } else if (s.equals("or")) {
            return "OR";
        } else if (s.equals("log")) {
            return "log";
        } else if (s.equals("power")) {
            return "**";
        } else if (s.equals("cos")) {
            return "COS";
        } else {
            throw new IllegalArgumentException("Not supported operand: "+ s);
        }
    }

    private boolean isSimulation() {
        boolean simulationStepFound=false
        pmlDOM.modellingSteps.commonModellingStep.each {
            if (it.value instanceof SimulationStepType) {
                simulationStepFound=true;
            }
        }
        simulationStepFound
    }

    private boolean isEstimation() {
        boolean estimationStepFound=false
        pmlDOM.modellingSteps.commonModellingStep.each {
            if (it.value instanceof EstimationStepType) {
                estimationStepFound=true;
            }
        }
        estimationStepFound
    }
}

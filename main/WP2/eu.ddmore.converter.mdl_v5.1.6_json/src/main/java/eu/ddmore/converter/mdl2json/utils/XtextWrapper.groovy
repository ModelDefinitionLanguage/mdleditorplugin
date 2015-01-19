package eu.ddmore.converter.mdl2json.utils;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.EnumType
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.Primary
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.Vector

import eu.ddmore.converter.mdlprinting.MdlPrinter

public class XtextWrapper {

	private static Logger logger = Logger.getLogger(XtextWrapper.class)
	
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	public static Object unwrap(AnyExpression expression) {
		if (expression == null) {
			return null
		}
		if (expression.getExpression()) {
			return unwrap(expression.getExpression());
		} else if (expression.getList()) {
			logger.info("Calling argumentToMapOrList")
			return argumentsToMapOrList(expression.getList().getArguments())
		} else if (expression.getVector()) {
			return unwrap(expression.getVector())
		} else if (expression.getType()) {
			return mdlPrinter.toStr(expression.getType())
		}
		logger.error("Encountered an unhandled AnyExpression: " + expression)
		return null
	}

	public static Object unwrap(EnumType enumType) {
		logger.error("Encountered an unhandled EnumType: " + enumType)
		return null
	}
	
	public static Object unwrap(Expression expression) {
		if (expression.getExpression()) {
			return unwrap(expression.getExpression())
		}
		else if (expression.getCondition()) {
			logger.error("Encountered an unhandled Expression with a Condition: ")
			logger.error(" condition = " + expression.getCondition())
			logger.error(" else = " + expression.getElseExpression())
		}
		logger.error("Encountered an unhandled Expression: " + expression)
		return null
	}

	public static Object unwrap(OrExpression expression) {
		if (expression.getOperator().size() == 0 && expression.getExpression().size() == 1) {
			return unwrap(expression.getExpression().get(0))
		}
		logger.error("Encountered an unhandled OrExpression: " + expression)
		return null
	}
	
	public static Object unwrap(LogicalExpression expression) {
		if (expression.getBoolean()) {
			return expression.getBoolean()
		} else if (expression.getOperator()) {
			return unwrap(expression.getExpression1()) + expression.getOperator() + unwrap(expression.getExpression2())
		} else if (expression.getExpression1()) {
			return unwrap(expression.getExpression1())
		}
		logger.error("Encountered an unhandled LogicalExpression: " + expression)
		return null
	}

	public static Object unwrap(AdditiveExpression expression) {
		if (expression.getString()) {
			return "\"" + expression.getString() + "\"";
		}
		
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < expression.getExpression().size(); i++) {
			sb.append(unwrap(expression.getExpression().get(i)))
			if ( (!expression.getOperator().isEmpty()) && i < expression.getExpression().size()-1 ) {
				sb.append(expression.getOperator().get(0))
			}
		}
		return sb.toString()
	}
	
	public static Object unwrap(MultiplicativeExpression expression) {
		final StringBuffer sb = new StringBuffer();
		for (int i = 0; i < expression.getExpression().size(); i++) {
			sb.append(unwrap(expression.getExpression().get(i)))
			if ( (!expression.getOperator().isEmpty()) && i < expression.getExpression().size()-1 ) {
				sb.append(expression.getOperator().get(0))
			}
		}
		return sb.toString()
	}
	
	public static Object unwrap(PowerExpression expression) {
		return unwrap(expression.getExpression().get(0))
	}
	
	public static Object unwrap(UnaryExpression expression) {
		if (expression.getOperator()) {
			return expression.getOperator() + unwrap(expression.getExpression())
		} else if (expression.getNumber()) {
			return expression.getNumber()
		} else if (expression.getSymbol()) {
			return expression.getSymbol().getName()
		} else if (expression.getFunctionCall()) {
			return unwrap(expression.getFunctionCall())
		} else if (expression.getConstant()) {
			logger.error("Encountered an unhandled UnaryExpression with unexpected content: Constant: " + expression.getConstant())
			return null
		} else if (expression.getParExpression()) {
			logger.error("Encountered an unhandled UnaryExpression with unexpected content: ParExpression: " + expression.getParExpression())
			return null
		} else if (expression.getAttribute()) {
			logger.error("Encountered an unhandled UnaryExpression with unexpected content: Attribute: " + expression.getAttribute())
			return null
		} else {
			logger.error("Encountered an unhandled UnaryExpression with unexpected content: Expression: " + expression.getExpression)
			return null
		}
	}
	
	public static Object unwrap(AndExpression expression) {
		if (expression.getOperator().size() == 0 && expression.getExpression().size() == 1) {
			return unwrap(expression.getExpression().get(0))
		}
		logger.error("Encountered an unhandled AndExpression: " + expression)
		return null
	}
	
	public static Object unwrap(FunctionCall fc) {
		final String functionName = fc.getIdentifier().getName()
		final String argsStr = fc.getArguments().getArguments().collect { Argument a ->
			if (a.getArgumentName()) {
				a.getArgumentName().getName() + "=" + unwrap(a.getExpression())
			} else {
				unwrap(a.getExpression())
			}
		}.join(", ")
		return functionName + "(" + argsStr + ")"
	}
	
	public static Object unwrap(Vector v) {
		"[".concat(v.getValues().collect {Primary p ->
			return unwrap(p.getExpression())
		}.join(", ")).concat("]")
	}

//	public static Object unwrap(DistributionArguments distributionArgs) {
//		Map arguments = [:]
//		distributionArgs.getArguments().each { DistributionArgument da ->
//			if(da.getDistribution()!=null) {
//				arguments["type"] = da.getDistribution().getIdentifier()
//			} else if(da.getComponent()!=null) {
//			  	arguments[da.getArgumentName()] = unwrap(da.getComponent().getArguments())
//			} else if( da.getValue() != null ){
//				String val = ""
//				if( da.getValue().getNumber()!=null ) val = da.getValue().getNumber()
//				else if (da.getValue().getSymbol()!=null) val = da.getValue().getSymbol().getName()
//				else if(da.getValue().getVector()!=null) val = mdlPrinter.toStr(da.getValue().getVector())
//				arguments[da.getArgumentName().getName()] = val
//			}
//		}
//		arguments
//	}
	
	/**
	 * Sometimes there is an argument name and sometimes there is not...
	 * In the former case we will return a Map and in the latter we will return a List.
	 */
	private static argumentsToMapOrList(final Arguments args) {
		Map m = [:]
		List l = []
		for (Argument a : args.getArguments() ) {
			def unwrappedExpr = unwrap(a.getExpression())
			if (a.getArgumentName()) {
				m.put(a.getArgumentName().getName(), unwrappedExpr);
			}
			l.add(unwrappedExpr)
		}
		if (m.size() == l.size()) {
			// There are no unnamed arguments so return the populated Map
			return m
		}
		// Unnamed arguments were encountered so we have to return the List instead
		return l
	}
	
}

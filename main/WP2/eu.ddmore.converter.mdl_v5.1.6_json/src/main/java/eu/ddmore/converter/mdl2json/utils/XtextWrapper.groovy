package eu.ddmore.converter.mdl2json.utils;

import org.apache.log4j.Logger;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.DistributionArgument
import org.ddmore.mdl.mdl.DistributionArguments;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FunctionName;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.UnaryExpression;

import eu.ddmore.converter.mdlprinting.MdlPrinter;

public class XtextWrapper {

	private static Logger logger = Logger.getLogger(XtextWrapper.class)
	
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	public static Object unwrap(AnyExpression expression) {
		if (expression == null) {
			return null
		}
		if (expression.getExpression() != null) {
			return unwrap(expression.getExpression());
		} else if (expression.getList() != null) {
			return argumentsToMapOrList(expression.getList().getArguments())
		} else if (expression.getOdeList() != null) {
			return argumentsToMapOrList(expression.getOdeList().getArguments())
		} else if (expression.getVector() != null) {
			return mdlPrinter.toStr(expression.getVector())
		} else if (expression.getType() != null) {
			return mdlPrinter.toStr(expression.getType())
		}
		logger.debug(expression)
		return null;
	}

	public static Object unwrap(EnumType enumType) {
		if(enumType.getInput()) {
			return enumType.getInput().getIdentifier()
		}
		logger.debug(enumType)
		return null
	}
	
	public static Object unwrap(Expression expression) {
		return unwrap(expression.getConditionalExpression());
	}

	public static Object unwrap(ConditionalExpression conditionalExpression) {
		if(conditionalExpression.getExpression()!=null) {
			return unwrap(conditionalExpression.getExpression());
		} else if(conditionalExpression.getExpression1()!=null) {
			return unwrap(conditionalExpression.getExpression1());
		} else if(conditionalExpression.getExpression2()!=null) {
			return unwrap(conditionalExpression.getExpression2());
		}
		logger.debug(conditionalExpression)
		return null;
	}

	public static Object unwrap(OrExpression expression) {
		if(expression.getOperator().size() == 0 && expression.getExpression().size() == 1 ) {
			return unwrap(expression.getExpression().get(0))
		}
		logger.debug(expression)
		return null;//expression.getOperator().get(0);
	}
	
	public static Object unwrap(LogicalExpression expression) {
		if (expression.getBoolean()) {
			return expression.getBoolean()
		} else if (expression.getOperator()) {
			return unwrap(expression.getExpression1()) + expression.getOperator() + unwrap(expression.getExpression2())
		} else if (expression.getExpression1()) {
			return unwrap(expression.getExpression1())
		}
		logger.debug(expression)
		return null
	}

	public static Object unwrap(AdditiveExpression expression) {
		if (expression.getExpression().size() != 0 && expression.getOperator() != null) {
			logger.debug(expression)
			return unwrap(expression.getExpression().get(0))
		}
		if (expression.getString() != null) {
            return "\"" + expression.getString() + "\"";
		}
        return "";
	}
	
	public static Object unwrap(MultiplicativeExpression expression) {
		return unwrap(expression.getExpression().get(0))
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
		}
		throw new UnsupportedOperationException("Attempted to unwrap a UnaryExpression with unexpected content")
	}
	
	public static Object unwrap(AndExpression expression) {
		if(expression.getOperator().size() == 0 && expression.getExpression().size() == 1 ) {
			return unwrap(expression.getExpression().get(0))
		}
		return null;
	}
	
	public static Object unwrap(FunctionName functionName) {
		logger.debug(functionName)
		// TODO Auto-generated method stub
		return null;
	}

	public static Object unwrap(DistributionArguments distributionArgs) {
		Map arguments = [:]
		distributionArgs.getArguments().each { DistributionArgument da ->
			if(da.getDistribution()!=null) {
				arguments["type"] = da.getDistribution().getIdentifier()
			} else if(da.getComponent()!=null) {
			  	arguments[da.getArgumentName()] = unwrap(da.getComponent().getArguments())
			} else if( da.getValue() != null ){
				String val = ""
				if( da.getValue().getNumber()!=null ) val = da.getValue().getNumber()
				else if (da.getValue().getSymbol()!=null) val = da.getValue().getSymbol().getName()
				else if(da.getValue().getVector()!=null) val = mdlPrinter.toStr(da.getValue().getVector())
				arguments[da.getArgumentName().getName()] = val
			}
		}
		arguments
	}
	
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

package eu.ddmore.converter.mdl2json.utils;

import eu.ddmore.converter.mdlprinting.MdlPrinter
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.DistributionArgument
import org.ddmore.mdl.mdl.DistributionArguments
import org.ddmore.mdl.mdl.EnumType
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FunctionName;
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.UnaryExpression

public class XtextWrapper {

	private static Logger logger = Logger.getLogger(XtextWrapper.class)
	
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	public static Object unwrap(AnyExpression expression) {
		
		if(expression==null) {
			return null
		}
		if(expression.getExpression()!=null) {
			return unwrap(expression.getExpression());
		} else if(expression.getList() !=null ) {
			Map m = argumentsToMap(expression.getList().getArguments())
			return m;
		} else if(expression.getOdeList()!=null) {
			Map m = argumentsToMap(expression.getOdeList().getArguments())
			return m
		} else if(expression.getVector()!=null) {
			return mdlPrinter.toStr(expression.getVector())
		} else if(expression.getType()!=null) {
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
		if(expression.getBoolean()) {
			return expression.getBoolean()
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
		def ret
		if(expression.getOperator()) {
			ret = expression.getOperator() + unwrap(expression.getExpression())
			return ret
		}
		return expression.getNumber();
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
	
	private static Map argumentsToMap(Arguments args) {
		Map m = [:]
		for(Argument a : args.getArguments() ) {
			m.put(a.getArgumentName().getName(), mdlPrinter.toStr(a.getExpression()));
		}
		return m
	}
	
}

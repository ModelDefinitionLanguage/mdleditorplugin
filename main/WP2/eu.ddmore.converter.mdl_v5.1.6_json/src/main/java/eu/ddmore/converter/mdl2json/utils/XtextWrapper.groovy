package eu.ddmore.converter.mdl2json.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.EnumType
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FunctionName;
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.OrExpression;

public class XtextWrapper {

	private static Logger logger = Logger.getLogger(XtextWrapper.class)
	
	public static Object unwrap(AnyExpression expression) {
		
		if(expression.getExpression()!=null) {
			return unwrap(expression.getExpression());
		} else if(expression.getList() !=null ) {
			Map m = new HashMap();
			for(Argument a : expression.getList().getArguments().getArguments() ) {
				m.put(a.getArgumentName().getName(), unwrap(a.getExpression()));
			}
			return m;
		} else if(expression.getOdeList()!=null) {
			
		} else if(expression.getVector()!=null) {
			
		} else if(expression.getType()!=null) {
			if( expression.getType().getType()!=null) {
				if(expression.getType().getType().getContinuous()!=null) {
					return expression.getType().getType().getContinuous()
				} else if(expression.getType().getType().getCategorical()!=null) {
					return expression.getType().getType().getCategorical()
				} else if (expression.getType().getType().getLikelihood()!=null) {
					return expression.getType().getType().getLikelihood()
				}
			} else {
				return unwrap(expression.getType())
			}
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
		// Needs to do more than this in case there are more expressions in here
		if(expression.getExpression().size() != 0 || expression.getOperator() != null) {
			logger.debug(expression)
		}
		return expression.getString()	
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

}

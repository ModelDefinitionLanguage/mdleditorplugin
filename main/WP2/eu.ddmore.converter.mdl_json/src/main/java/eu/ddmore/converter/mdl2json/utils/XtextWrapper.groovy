package eu.ddmore.converter.mdl2json.utils;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.ArgumentExpression
import org.ddmore.mdl.mdl.EnumType
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.ExpressionBranch
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.NamedArguments
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.UnnamedArguments
import org.ddmore.mdl.mdl.Vector
import org.eclipse.emf.common.util.EList

import eu.ddmore.converter.mdlprinting.MdlPrinter

public class XtextWrapper {

	private static Logger logger = Logger.getLogger(XtextWrapper.class)
	
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	public static unwrap(AnyExpression expression) {
		if (expression == null) {
			return null
		}
		if (expression.getExpression()) {
			return unwrap(expression.getExpression());
		} else if (expression.getList()) {
            // TODO: cater for unnamed arguments too?
			return namedArgumentsToMap(expression.getList().getArguments().getNamedArguments())
		} else if (expression.getVector()) {
            return unwrap(expression.getVector())
		} else if (expression.getType()) {
			return mdlPrinter.toStr(expression.getType())
		}
		logger.error("Encountered an unhandled AnyExpression: " + expression)
		return null
	}

	public static unwrap(EnumType enumType) {
		logger.error("Encountered an unhandled EnumType: " + enumType)
		return null
	}
	
	public static unwrap(Expression expression) {
        def StringBuffer strExpr = new StringBuffer()
		if (expression.getExpression()) {
			strExpr.append(unwrap(expression.getExpression()))
		}
        if (expression.getCondition()) {
            strExpr.append(" when ")
            strExpr.append(unwrap(expression.getCondition()))
            if (expression.getElseExpression()) {
                strExpr.append(" otherwise ")
                strExpr.append(unwrap(expression.getElseExpression()))
            }
            // Assumption: Can't have both an "else" expression and a set of "when" branches
            else if (expression.getWhenBranches()) {
                for (ExpressionBranch whenExpr : expression.getWhenBranches()) {
                    strExpr.append(", ")
                    strExpr.append(unwrap(whenExpr.getExpression()))
                    strExpr.append(" when ")
                    strExpr.append(unwrap(whenExpr.getCondition()))
                }
            }
            strExpr.append(";")
        }
        return strExpr.toString()
	}

	public static unwrap(OrExpression expression) {
		if (expression.getOperator().size() == 0 && expression.getExpression().size() == 1) {
			return unwrap(expression.getExpression().get(0))
		}
		logger.error("Encountered an unhandled OrExpression: " + expression)
		return null
	}
	
	public static unwrap(LogicalExpression expression) {
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

	public static unwrap(AdditiveExpression expression) {
		if (expression.getString()) {
			return "\"" + expression.getString() + "\"";
		} else {
            return unwrap(expression.getExpression(), expression.getOperator())
		}
	}
	
	public static unwrap(MultiplicativeExpression expression) {
        return unwrap(expression.getExpression(), expression.getOperator())
	}
	
	public static unwrap(PowerExpression expression) {
        return unwrap(expression.getExpression(), expression.getOperator())
	}
    
    // Would be good if EList could be parameterised by a common superclass of all expression classes but no such suitable class exists.
    // TODO: This should be private but generates an Eclipse warning about mixing public and private methods of the same name
    public static unwrap(EList<Object> expressionList, EList<String> operatorList) {
        
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < expressionList.size(); i++) {
            sb.append(unwrap(expressionList.get(i)))
            if ( (!operatorList.isEmpty()) && i < expressionList.size()-1 ) {
                sb.append(operatorList.get(i))
            }
        }
        return sb.toString()
    }
	
	public static unwrap(UnaryExpression expression) {
		if (expression.getOperator()) {
			return expression.getOperator() + unwrap(expression.getExpression())
		} else if (expression.getNumber()) {
			return expression.getNumber()
		} else if (expression.getSymbol()) {
			return expression.getSymbol().getName()
		} else if (expression.getFunctionCall()) {
			return unwrap(expression.getFunctionCall())
        } else if (expression.getParExpression()) {
            return "(" + XtextWrapper.unwrap(expression.getParExpression().getExpression()) + ")"
		} else if (expression.getConstant()) {
			logger.error("Encountered an unhandled UnaryExpression with unexpected content: Constant: " + expression.getConstant())
			return null
		} else {
			logger.error("Encountered an unhandled UnaryExpression with unexpected content: Expression: " + expression.getExpression)
			return null
		}
	}
	
	public static unwrap(AndExpression expression) {
		if (expression.getOperator().size() == 0 && expression.getExpression().size() == 1) {
			return unwrap(expression.getExpression().get(0))
		}
		logger.error("Encountered an unhandled AndExpression: " + expression)
		return null
	}
	
	public static unwrap(FunctionCall fc) {
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
	
	public static unwrap(Vector v) {
		"[".concat(v.getExpression().getExpressions().collect { unwrap(it) }.join(", ")).concat("]")
	}

//	public static unwrap(DistributionArguments distributionArgs) {
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
    
    public static unwrap(ArgumentExpression argExpr) {
        if (argExpr.getExpression()) {
            return unwrap(argExpr.getExpression())
        } else {
            logger.error("Encountered an unhandled RandomList of an ArgumentExpression: " + argExpr.getRandomList())
            return null
        }
    }
	
	private static List unnamedArgumentsToList(final UnnamedArguments args) {
		List l = []
		for (Argument a : args.getArguments()) {
			l.add(unwrap(a.getExpression()))
		}
		return l
	}
    
    private static Map namedArgumentsToMap(final NamedArguments args) {
        Map m = [:]
        for (Argument a : args.getArguments()) {
            m.put(a.getArgumentName().getName(), unwrap(a.getExpression()))
        }
        return m
    }
	
}

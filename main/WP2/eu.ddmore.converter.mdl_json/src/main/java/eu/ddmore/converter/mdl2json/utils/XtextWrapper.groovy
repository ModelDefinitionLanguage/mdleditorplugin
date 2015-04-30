package eu.ddmore.converter.mdl2json.utils;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.ArgumentExpression
import org.ddmore.mdl.mdl.Arguments
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
            return unwrap(expression.getList().getArguments())
		} else if (expression.getVector()) {
            return unwrap(expression.getVector())
		} else if (expression.getType()) {
			return mdlPrinter.toStr(expression.getType())
		}
		throw new UnsupportedOperationException("Encountered an unhandled AnyExpression: " + mdlPrinter.toStr(expression))
	}

	public static unwrap(EnumType enumType) {
		throw new UnsupportedOperationException("Encountered an unhandled EnumType: " + mdlPrinter.toStr(enumType))
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
                strExpr.append(";")
            }
            else { // Neither an "otherwise" branch nor a set of "when" branches
                strExpr.append(";")
            }
        }
        return strExpr.toString()
	}

	public static unwrap(OrExpression expression) {
		if (expression.getOperator().size() == 0 && expression.getExpression().size() == 1) {
			return unwrap(expression.getExpression().get(0))
		}
		throw new UnsupportedOperationException("Encountered an unhandled OrExpression: " + mdlPrinter.toStr(expression))
	}
	
	public static unwrap(LogicalExpression expression) {
		if (expression.getBoolean()) {
			return expression.getBoolean()
		} else if (expression.getOperator()) {
			return unwrap(expression.getExpression1()) + expression.getOperator() + unwrap(expression.getExpression2())
		} else if (expression.getExpression1()) {
			return unwrap(expression.getExpression1())
		}
		throw new UnsupportedOperationException("Encountered an unhandled LogicalExpression: " + mdlPrinter.toStr(expression))
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
			return expression.getConstant()
		} else {
			throw new UnsupportedOperationException("Encountered an unhandled UnaryExpression with unexpected content: " + mdlPrinter.toStr(expression))
		}
	}
	
	public static unwrap(AndExpression expression) {
		if (expression.getOperator().size() == 0 && expression.getExpression().size() == 1) {
			return unwrap(expression.getExpression().get(0))
		}
		throw new UnsupportedOperationException("Encountered an unhandled AndExpression: " + mdlPrinter.toStr(expression))
	}
	
	public static unwrap(FunctionCall fc) {
		final String functionName = fc.getIdentifier().getName()
		final String argsStr
        if (fc.getArguments().getUnnamedArguments()) {
            argsStr = fc.getArguments().getUnnamedArguments().getArguments().collect { ArgumentExpression a ->
                unwrap(a.getExpression())
            }.join(", ")
        } else if (fc.getArguments().getNamedArguments()) {
            argsStr = fc.getArguments().getNamedArguments().getArguments().collect { Argument a ->
                a.getArgumentName().getName() + "=" + unwrap(a.getExpression())
            }.join(", ")
        }
		return functionName + "(" + argsStr + ")"
	}
	
	public static unwrap(Vector v) {
        if (v.getExpression().getExpressions()) {
            return "[".concat(v.getExpression().getExpressions().collect { Expression expr ->
                unwrap(expr)
            }.join(", ")).concat("]")
        } else {
            return "[".concat(v.getExpression().getLists().collect {  org.ddmore.mdl.mdl.List lst ->
                unwrap(lst.getArguments())
            }.join(", ")).concat("]")
        }
	}
    
    public static unwrap(final Arguments args) {
        if (args.getNamedArguments()) {
            return unwrap(args.getNamedArguments())
        } else {
            return unwrap(args.getUnnamedArguments())
        }
    }
    
    public static unwrap(final NamedArguments args) {
        "{".concat(args.getArguments().collect{ Argument a ->
            a.getArgumentName().getName() + "=" + unwrap(a.getExpression())
        }.join(", ")).concat("}")
    }
    
    public static unwrap(final UnnamedArguments args) {
        "{".concat(args.getArguments().collect{ ArgumentExpression ae ->
            unwrap(ae.getExpression())
        }.join(", ")).concat("}")
    }
    
    public static unwrap(ArgumentExpression argExpr) {
        if (argExpr.getExpression()) {
            return unwrap(argExpr.getExpression())
        } else {
            throw new UnsupportedOperationException("Encountered an unhandled RandomList of an ArgumentExpression: " + mdlPrinter.toStr(argExpr.getRandomList()))
        }
    }
	
}

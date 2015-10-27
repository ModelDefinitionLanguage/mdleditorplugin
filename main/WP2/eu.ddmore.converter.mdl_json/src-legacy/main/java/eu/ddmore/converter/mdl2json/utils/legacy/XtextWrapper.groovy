/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.utils.legacy

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

import com.google.common.base.Preconditions

import eu.ddmore.converter.mdl2json.utils.legacy.XtextWrapper;
import eu.ddmore.converter.mdlprinting.MdlPrinter

public class XtextWrapper {

    private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()

    /**
     * Expand an {@link AnyExpression} expression into its string representation.
     * <p>
     * @param expression - {@link AnyExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(AnyExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null AnyExpression")
        if (expression.getExpression()) {
            return unwrap(expression.getExpression())
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

    /**
     * Expand an {@link Expression} expression into its string representation.
     * <p>
     * @param expression - {@link Expression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(Expression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null Expression")
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

    /**
     * Expand a {@link LogicalExpression} expression into its string representation.
     * <p>
     * @param expression - {@link LogicalExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(LogicalExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null LogicalExpression")
        if (expression.getBoolean()) {
            return expression.getBoolean()
        } else if (expression.getOperator()) {
            return unwrap(expression.getExpression1()) + expression.getOperator() + unwrap(expression.getExpression2())
        } else if (expression.getExpression1()) {
            return unwrap(expression.getExpression1())
        }
        throw new UnsupportedOperationException("Encountered an unhandled LogicalExpression: " + mdlPrinter.toStr(expression))
    }

    /**
     * Expand an {@link AdditiveExpression} expression into its string representation.
     * <p>
     * @param expression - {@link AdditiveExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(AdditiveExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null AdditiveExpression")
        if (expression.getString()) {
            return "\"" + expression.getString() + "\""
        } else {
            return unwrap(expression.getExpression(), expression.getOperator())
        }
    }

    /**
     * Expand a {@link MultiplicativeExpression} expression into its string representation.
     * <p>
     * @param expression - {@link MultiplicativeExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(MultiplicativeExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null MultiplicativeExpression")
        return unwrap(expression.getExpression(), expression.getOperator())
    }

    /**
     * Expand a {@link PowerExpression} expression into its string representation.
     * <p>
     * @param expression - {@link PowerExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(PowerExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null PowerExpression")
        return unwrap(expression.getExpression(), expression.getOperator())
    }

    /**
     * Expand a {@link UnaryExpression} expression into its string representation.
     * <p>
     * @param expression - {@link UnaryExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(UnaryExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null UnaryExpression")
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

    /**
     * Expand an {@link AndExpression} expression into its string representation.
     * <p>
     * @param expression - {@link AndExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(AndExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null AndExpression")
        return unwrap(expression.getExpression(), expression.getOperator().collect {" "+it+" "} as EList<String>) // Pedantic: formatting - add some space padding either side of the "&&" operators
    }
    
    /**
     * Expand an {@link OrExpression} expression into its string representation.
     * <p>
     * @param expression - {@link OrExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(OrExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null OrExpression")
        return unwrap(expression.getExpression(), expression.getOperator().collect {" "+it+" "} as EList<String>) // Pedantic: formatting - add some space padding either side of the "||" operators
    }
    
    // Would be good if EList could be parameterised by a common superclass of all expression classes but no such suitable class exists.
    // TODO: This should be private but generates an Eclipse warning about mixing public and private methods of the same name
    public static unwrap(EList<Object> expressionList, EList<String> operatorList) {
        Preconditions.checkNotNull(expressionList, "Cannot unwrap a null espressionList")
        Preconditions.checkNotNull(operatorList, "Cannot unwrap a null operatorList")
        Preconditions.checkArgument(expressionList.size() == operatorList.size() + 1, "There must be one more Expression in the expressionList than there are Operators in the operatorList")
        
        final StringBuffer sb = new StringBuffer()
        for (int i = 0; i < expressionList.size(); i++) {
            sb.append(unwrap(expressionList.get(i)))
            if ( (!operatorList.isEmpty()) && i < expressionList.size()-1 ) {
                sb.append(operatorList.get(i))
            }
        }
        return sb.toString()
    }

    /**
     * Expand a {@link FunctionCall} expression into its string representation.
     * <p>
     * @param expression - {@link FunctionCall}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(FunctionCall fc) {
        Preconditions.checkNotNull(fc, "Cannot unwrap a null FunctionCall expression")
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

    /**
     * Expand a {@link Vector} expression into its string representation.
     * <p>
     * @param expression - {@link Vector}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static unwrap(Vector v) {
        Preconditions.checkNotNull(v, "Cannot unwrap a null Vector expression")
        if (v.getExpression().getExpressions()) {
            return "[".concat(v.getExpression().getExpressions().collect { Expression expr ->
                unwrap(expr)
            }.join(", ")).concat("]")
        } else {
            return "[".concat(v.getExpression().getLists().collect { org.ddmore.mdl.mdl.List lst ->
                unwrap(lst.getArguments())
            }.join(", ")).concat("]")
        }
    }
    
    public static unwrap(final Arguments args) {
        unwrap(args, '{', '}')
    }

    public static unwrap(final Arguments args, final String bracketCharL, final String bracketCharR) {
        Preconditions.checkNotNull(bracketCharL, "bracketCharL must not be null");
        Preconditions.checkNotNull(bracketCharR, "bracketCharR must not be null");
        if (args.getNamedArguments()) {
            return unwrapArgs(args.getNamedArguments(), bracketCharL, bracketCharR)
        } else {
            return unwrapArgs(args.getUnnamedArguments(), bracketCharL, bracketCharR)
        }
    }

    private static unwrapArgs(final NamedArguments args, final String bracketCharL, final String bracketCharR) {
        bracketCharL + args.getArguments().collect{ Argument a ->
            a.getArgumentName().getName() + "=" + unwrap(a.getExpression())
        }.join(", ") + bracketCharR
    }

    private static unwrapArgs(final UnnamedArguments args, final String bracketCharL, final String bracketCharR) {
        bracketCharL + args.getArguments().collect{ ArgumentExpression ae ->
            unwrap(ae.getExpression())
        }.join(", ") + bracketCharR
    }

    public static unwrap(ArgumentExpression argExpr) {
        Preconditions.checkNotNull(argExpr, "Cannot unwrap a null ArgumentExpression")
        if (argExpr.getExpression()) {
            return unwrap(argExpr.getExpression())
        } else if (argExpr.getRandomList()) {
            return "~" + argExpr.getRandomList().getType().getName() + unwrap(argExpr.getRandomList().getArguments(), '(', ')')
        } else {
            throw new UnsupportedOperationException("Encountered an ArgumentExpression without either an Expression nor a RandomList: " + mdlPrinter.toStr(argExpr))
        }
    }

}
/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
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
import org.eclipse.emf.common.util.EList

import com.google.common.base.Preconditions;

import eu.ddmore.converter.mdlprinting.MdlPrinter

public class XtextWrapper {

    private static Logger logger = Logger.getLogger(XtextWrapper.class)

    private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()

    /**
     * Expand an {@link AnyExpression} expression into its string representation.
     * <p>
     * @param expression - {@link AnyExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(AnyExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null AnyExpression");
        if (expression.getExpression()) {
            return unwrap(expression.getExpression());
        } else if (expression.getList()) {
            throw new UnsupportedOperationException("Encountered an unhandled AnyExpression containing a List: " + expression.getList())
        } else if (expression.getVector()) {
            return unwrap(expression.getVector())
        } else if (expression.getType()) {
            return mdlPrinter.toStr(expression.getType())
        }
        throw new UnsupportedOperationException("Encountered an unhandled AnyExpression: " + expression)
    }

    public static Object unwrap(EnumType enumType) {
        throw new UnsupportedOperationException("Encountered an unhandled EnumType: " + enumType)
    }

    /**
     * Expand an {@link Expression} expression into its string representation.
     * <p>
     * @param expression - {@link Expression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(Expression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null Expression");
        if (expression.getExpression()) {
            return unwrap(expression.getExpression())
        }
        else if (expression.getCondition()) {
            throw new UnsupportedOperationException("Encountered an unhandled Expression with a Condition: condition = " + expression.getCondition() + " else = " + expression.getElseExpression())
        }
        throw new UnsupportedOperationException("Encountered an unhandled Expression: " + expression)
    }

    /**
     * Expand an {@link OrExpression} expression into its string representation.
     * <p>
     * @param expression - {@link OrExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(OrExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null OrExpression");
        if (expression.getOperator().size() == 0 && expression.getExpression().size() == 1) {
            return unwrap(expression.getExpression().get(0))
        }
        throw new UnsupportedOperationException("Encountered an unhandled OrExpression: " + expression)
    }

    /**
     * Expand a {@link LogicalExpression} expression into its string representation.
     * <p>
     * @param expression - {@link LogicalExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(LogicalExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null LogicalExpression");
        if (expression.getBoolean()) {
            return expression.getBoolean()
        } else if (expression.getOperator()) {
            return unwrap(expression.getExpression1()) + expression.getOperator() + unwrap(expression.getExpression2())
        } else if (expression.getExpression1()) {
            return unwrap(expression.getExpression1())
        }
        throw new UnsupportedOperationException("Encountered an unhandled LogicalExpression: " + expression)
    }

    /**
     * Expand an {@link AdditiveExpression} expression into its string representation.
     * <p>
     * @param expression - {@link AdditiveExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(AdditiveExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null AdditiveExpression");
        if (expression.getString()) {
            return "\"" + expression.getString() + "\"";
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
    public static Object unwrap(MultiplicativeExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null MultiplicativeExpression");
        return unwrap(expression.getExpression(), expression.getOperator())
    }

    /**
     * Expand a {@link PowerExpression} expression into its string representation.
     * <p>
     * @param expression - {@link PowerExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(PowerExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null PowerExpression");
        return unwrap(expression.getExpression(), expression.getOperator())
    }

    // Would be good if EList could be parameterised by a common superclass of all expression classes but no such suitable class exists.
    // TODO: This should be private but generates an Eclipse warning about mixing public and private methods of the same name
    public static Object unwrap(EList<Object> expressionList, EList<String> operatorList) {

        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < expressionList.size(); i++) {
            sb.append(unwrap(expressionList.get(i)))
            if ( (!operatorList.isEmpty()) && i < expressionList.size()-1 ) {
                sb.append(operatorList.get(i))
            }
        }
        return sb.toString()
    }

    /**
     * Expand a {@link UnaryExpression} expression into its string representation.
     * <p>
     * @param expression - {@link UnaryExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(UnaryExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null UnaryExpression");
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
            throw new UnsupportedOperationException("Encountered an unhandled UnaryExpression with unexpected content: Constant: " + expression.getConstant())
        } else if (expression.getAttribute()) {
            throw new UnsupportedOperationException("Encountered an unhandled UnaryExpression with unexpected content: Attribute: " + expression.getAttribute())
        } else {
            throw new UnsupportedOperationException("Encountered an unhandled UnaryExpression with unexpected content: Expression: " + expression.getExpression)
        }
    }

    /**
     * Expand an {@link AndExpression} expression into its string representation.
     * <p>
     * @param expression - {@link AndExpression}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(AndExpression expression) {
        Preconditions.checkNotNull(expression, "Cannot unwrap a null AndExpression");
        if (expression.getOperator().size() == 0 && expression.getExpression().size() == 1) {
            return unwrap(expression.getExpression().get(0))
        }
        throw new UnsupportedOperationException("Encountered an unhandled AndExpression: " + expression)
    }

    /**
     * Expand a {@link FunctionCall} expression into its string representation.
     * <p>
     * @param expression - {@link FunctionCall}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(FunctionCall fc) {
        Preconditions.checkNotNull(fc, "Cannot unwrap a null FunctionCall expression");
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

    /**
     * Expand a {@link Vector} expression into its string representation.
     * <p>
     * @param expression - {@link Vector}
     * @return String representation of the expression
     * @throws NullPointerException if expression is null
     */
    public static Object unwrap(Vector v) {
        Preconditions.checkNotNull(v, "Cannot unwrap a null Vector expression");
        "[".concat(v.getValues().collect {Primary p ->
            return unwrap(p.getExpression())
        }.join(", ")).concat("]")
    }

}

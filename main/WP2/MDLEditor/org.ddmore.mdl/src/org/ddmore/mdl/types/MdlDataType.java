/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * MDL type checking
 */
package org.ddmore.mdl.types;

import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.DistributionArgument;
import org.ddmore.mdl.mdl.DistributionType;
import org.ddmore.mdl.mdl.EnumType; 
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.IndividualVarType;
import org.ddmore.mdl.mdl.InputFormatType;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.TargetType;
import org.ddmore.mdl.mdl.TrialType;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VariabilityType;
import org.ddmore.mdl.mdl.Vector;
import org.ddmore.mdl.validation.FunctionValidator;
import org.ddmore.mdl.validation.Utils;
import eu.ddmore.converter.mdlprinting.MdlPrinter;

public enum MdlDataType {

    TYPE_UNDEFINED,
    TYPE_VOID,
    /*Basic*/
    TYPE_STRING, TYPE_INT, TYPE_REAL, TYPE_BOOLEAN,
    //Restrictions of basic (to comply with PharmML)
    TYPE_NAT, TYPE_PNAT, TYPE_PREAL, TYPE_PROBABILITY,
    //References to variables and mathematical expressions
	TYPE_REF, TYPE_EXPR,  
	//References to objects
	TYPE_OBJ_REF, TYPE_OBJ_REF_MODEL, TYPE_OBJ_REF_DATA, TYPE_OBJ_REF_PARAM, TYPE_OBJ_REF_TASK,
	//Nested lists
	TYPE_LIST, TYPE_RANDOM_LIST, 
    
	/*Vectors*/
    //Numeric vectors
    TYPE_VECTOR_STRING, TYPE_VECTOR_INT, TYPE_VECTOR_REAL, //TYPE_VECTOR_BOOLEAN, 
    //Restricted vectors
    TYPE_VECTOR_NAT, TYPE_VECTOR_PNAT, TYPE_VECTOR_PREAL, TYPE_VECTOR_PROBABILITY,
    //Reference vectors
    TYPE_VECTOR_REF, TYPE_VECTOR_EXPR,

	/*String restrictions*/
	TYPE_TRANS,          //{log, logit, probit}

    /*Enumerations*/
	TYPE_VAR_TYPE,       //{continuous, categorical, likelihood, M2LL}
	TYPE_USE,            //see 'UseType' in MDL grammar
	TYPE_TARGET,         //{NMTRAN_CODE, MLXTRAN_CODE, PML_CODE, BUGS_CODE, R_CODE MATLAB_CODE}
	TYPE_RANDOM_EFFECT,  //{VAR, SD} 
	TYPE_INPUT_FORMAT,   //{nonmemFormat, eventFormat}
	TYPE_DISTRIBUTION,   //see 'Distribution' in MDL grammar
	TYPE_INDIVIDUAL_VAR, //{linear, gaussian}
	TYPE_CONTINUOUS, 	 //{continuous}
	TYPE_TRIAL           //{simple, sequential, combined}
	;
    
	//Validates required type or reference
	static public boolean validateType(MdlDataType type, DistributionArgument arg){
		if (arg.getExpression() != null)
			return validateType(type, arg.getExpression());
		if (arg.getComponent() != null)
			if (type.equals(MdlDataType.TYPE_RANDOM_LIST)) return true;
		if (arg.getDistribution() != DistributionType.NONE)
			if (type.equals(MdlDataType.TYPE_DISTRIBUTION)) return true;
		return false;
	}
	
	static public boolean validateType(MdlDataType type, Expression expr){
		switch(type){
			case TYPE_UNDEFINED: return true;
			//Basic
			case TYPE_STRING: return isString(expr);
			case TYPE_INT:  return isInteger(expr);   
			case TYPE_REAL: return isReal(expr);     
			case TYPE_BOOLEAN: return isBoolean(expr);
			//Restrictions
			case TYPE_NAT:   return isNatural(expr);  
			case TYPE_PNAT:  return isPositiveNatural(expr);  
			case TYPE_PREAL: return isPositiveReal(expr); 
			case TYPE_PROBABILITY: return isProbability(expr);
			//References
			case TYPE_REF: return isReference(expr);  
			case TYPE_EXPR: return true;  
			//References to objects
			case TYPE_OBJ_REF: return isObjectReference(expr);
			case TYPE_OBJ_REF_MODEL: validObjectTypeReference(expr, TYPE_OBJ_REF_MODEL);
			case TYPE_OBJ_REF_DATA: return validObjectTypeReference(expr, TYPE_OBJ_REF_DATA);
			case TYPE_OBJ_REF_PARAM: return validObjectTypeReference(expr, TYPE_OBJ_REF_PARAM);
			case TYPE_OBJ_REF_TASK: return validObjectTypeReference(expr, TYPE_OBJ_REF_TASK);
			//String restriction
			case TYPE_TRANS: return isTransformationOperator(expr);  
			default: return false;
		}
	}
	
	static public boolean validateType(MdlDataType type, Vector expr){
		switch(type){
			case TYPE_VECTOR_INT: return isVectorInteger(expr);
			case TYPE_VECTOR_REAL: return isVectorReal(expr);
			case TYPE_VECTOR_STRING: return isVectorString(expr);  
			//Vectors of references
			case TYPE_VECTOR_REF: return isVectorReference(expr);  
			case TYPE_VECTOR_EXPR: return true;
			//Vectors of restrictions
			case TYPE_VECTOR_NAT: return isVectorNat(expr);
			case TYPE_VECTOR_PNAT: return isVectorPNat(expr);
			case TYPE_VECTOR_PREAL: return isVectorPReal(expr);
			case TYPE_VECTOR_PROBABILITY: return isVectorProbability(expr);
			default: return false;
		}
	}

	static public boolean validateType(MdlDataType type, EnumType expr){
		switch(type){
			case TYPE_VAR_TYPE: return expr.getType() != null;
			case TYPE_CONTINUOUS: return (expr.getType() != null && (expr.getType().getContinuous() != null));
			case TYPE_USE: return (expr.getUse() != UseType.NONE);
			case TYPE_TARGET: return (expr.getTarget() != TargetType.NONE);
			case TYPE_RANDOM_EFFECT: return (expr.getVariability() != VariabilityType.NONE);
			case TYPE_INPUT_FORMAT: return (expr.getInput() != InputFormatType.NONE);
			case TYPE_TRIAL: return (expr.getTrial() != TrialType.NONE);
			case TYPE_INDIVIDUAL_VAR: return (expr.getIndividualVar() != IndividualVarType.NONE);
			default: return false; 
		}
	}

	static public boolean validateType(MdlDataType type, AnyExpression expr){
		if (expr.getExpression() != null)
			return validateType(type, expr.getExpression());
		if ((expr.getList() != null) && (type == TYPE_LIST)) return true;
		if (expr.getVector() != null)
			return validateType(type, expr.getVector());
		if (expr.getType() != null)
			return validateType(type, expr.getType());
		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//Validate vector types
	//Note: references in numeric vectors are allowed and not checked!
	////////////////////////////////////////////////////////////////////////////////
	private static boolean isVectorReal(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorReal(p.getVector());
				if (!ok) return false;
			}
		}
		return true;
	}
	
	private static boolean isVectorNat(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorNat(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() != null)
					try{
						Integer x = Integer.parseInt(p.getNumber());
						if (x < 0) return false;
					} catch (NumberFormatException e){
						return false;
					}
			}
		}
		return true;
	}
	
	private static boolean isVectorPNat(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorNat(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() != null)
					try{
						Integer x = Integer.parseInt(p.getNumber());
						if (x <= 0) return false;
					} catch (NumberFormatException e){
						return false;
					}
			}
		}
		return true;
	}
	
	private static boolean isVectorPReal(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorPReal(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() != null)
				try{
					Double x = Double.parseDouble(p.getNumber());
					if (x <= 0) return false;
				} catch (NumberFormatException e){
					return false;
				}
			}
		}
		return true;
	}
	
	
	private static boolean isVectorProbability(Vector v) {
		Double total = 0.;
		boolean containsReference = false;
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorPReal(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() != null)
					try{
						Double x = Double.parseDouble(p.getNumber());
						if ((x < 0) || (x > 1)) return false;
						total += x;
					} catch (NumberFormatException e){
						return false;
					}
				else containsReference = true;
			}
		}
		return ((containsReference && (total <= 1.)) || (total == 1.));
	}
		
	private static boolean isVectorInteger(Vector v) {
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorInteger(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getNumber() != null) 
					try{
						Integer.parseInt(p.getNumber());
					} catch (NumberFormatException e){
						return false;
					}
			}
		}
		return true;
	}
	
	private static boolean isVectorReference(Vector v){
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorReference(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getSymbol() == null) return false;
			}
		}
		return true;	
	}

	private static boolean isVectorString(Vector v){
		for (Primary p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorString(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getString() == null) return false;
			}
		}
		return true;	
	}

	/////////////////////////////////////////////////////////////////////////////////////
	//Validate references
	/////////////////////////////////////////////////////////////////////////////////////
	
	private static boolean isObjectReference(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() == null){
			SymbolName s = getReference(expr.getConditionalExpression().getExpression());
			if (s!= null) {
				Mcl mcl = (Mcl) expr.eResource().getContents().get(0);
				if (mcl != null)
					return Utils.getDeclaredObjects(mcl).containsKey(s.getName());
			}
		}
		return false;
	}
	
	private static boolean validObjectTypeReference(Expression expr, MdlDataType type) {
		if (expr.getConditionalExpression().getExpression1() == null){
			SymbolName s = getReference(expr.getConditionalExpression().getExpression());
			if (s!= null) {
				Mcl mcl = (Mcl) expr.eResource().getContents().get(0);
				if (mcl != null)
					return (Utils.getDeclaredObjects(mcl).get(s.getName()) == type);
			}
		}
		return false;
	}

	private static boolean isReference(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null){
				return 
					isReference(expr.getConditionalExpression().getExpression1()) && 
					isReference(expr.getConditionalExpression().getExpression2());
			}
			return isReference(expr.getConditionalExpression().getExpression1());
		} else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			if (getReference(orExpr) != null) return true;
		}
		return false;
	}

	public static SymbolName getReference(OrExpression orExpr) {
		if (orExpr.getExpression().size() > 1) return null;
		AndExpression andExpr = orExpr.getExpression().get(0);
		if (andExpr.getExpression().size() > 1) return null;
		LogicalExpression logicExpr = andExpr.getExpression().get(0);
		if (logicExpr.getExpression1() != null){
			if (logicExpr.getExpression2() != null) return null;
			if (logicExpr.getExpression1().getString() != null) return null;
			if(logicExpr.getExpression1().getExpression().size() > 1) return null;
			MultiplicativeExpression multExpr = logicExpr.getExpression1().getExpression().get(0);
			if (multExpr.getExpression().size() > 1) return null;
			PowerExpression powerExpr = multExpr.getExpression().get(0);
			if (powerExpr.getExpression().size() > 1) return null;
			UnaryExpression unaryExpr = powerExpr.getExpression().get(0);
			if (unaryExpr.getSymbol() != null) return unaryExpr.getSymbol();
		}
		return null;
	}

	//////////////////////////////////////////////////////////////////////////////////
	//Validate basic types
	//////////////////////////////////////////////////////////////////////////////////
	private static boolean isBoolean(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isBoolean(expr.getConditionalExpression().getExpression1()) 
						&& isBoolean(expr.getConditionalExpression().getExpression2());
			return isBoolean(expr.getConditionalExpression().getExpression1());
		}
		else {
			
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			if (orExpr.getExpression().size() > 1) return true;
			AndExpression andExpr = orExpr.getExpression().get(0);
			if (andExpr.getExpression().size() > 1) return true;
			LogicalExpression logicExpr = andExpr.getExpression().get(0);
			if (logicExpr.getBoolean() != null) return true;
			if ((logicExpr.getExpression1() != null) && (logicExpr.getExpression2() != null)) return true;
			if (logicExpr.getExpression1().getExpression().size() == 1){
				if (logicExpr.getExpression1().getExpression().get(0).getExpression().size() == 1){
					PowerExpression p = logicExpr.getExpression1().getExpression().get(0).getExpression().get(0);
					if (p.getExpression().get(0).getParExpression() != null)
						return isBoolean(p.getExpression().get(0).getParExpression().getExpression());
				}
			}
		}
		return false;
	}
	
	private static boolean isPositiveNatural(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isPositiveNatural(expr.getConditionalExpression().getExpression1()) 
						&& isPositiveNatural(expr.getConditionalExpression().getExpression2());
			return isPositiveNatural(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isPositiveNatural(MdlPrinter.getInstance().toStr(orExpr));
		}
	}
	
	private static boolean isPositiveNatural(String value) {
		try{
			Integer x = Integer.parseInt(value);
			if (x > 0) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
	}

	private static boolean isNatural(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isNatural(expr.getConditionalExpression().getExpression1()) 
						&& isNatural(expr.getConditionalExpression().getExpression2());
			return isNatural(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isNatural(MdlPrinter.getInstance().toStr(orExpr));
		}
	}	
	
	private static boolean isNatural(String value) {
		try{
			Integer x = Integer.parseInt(value);
			if (x >= 0) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
	}

	private static boolean isInteger(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isInteger(expr.getConditionalExpression().getExpression1()) 
						&& isInteger(expr.getConditionalExpression().getExpression2());
			return isInteger(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isInteger(MdlPrinter.getInstance().toStr(orExpr));
		}
	}
	
	private static boolean isNumericConstant(String value) {
		if (value.equals("INF")) return true;
		return false;
	}
		
	private static boolean isInteger(String value) {
		try{
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e){
			return isNumericConstant(value);
		}
	}
	
	private static boolean isProbability(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isProbability(expr.getConditionalExpression().getExpression1()) 
					&& isProbability(expr.getConditionalExpression().getExpression2());
			return isProbability(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isProbability(MdlPrinter.getInstance().toStr(orExpr));
		}
	}
	
	private static boolean isProbability(String value) {
		try{
			Double x = Double.parseDouble(value);
			if ((x >= 0) && (x <=1)) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
	}
	
	private static boolean isPositiveReal(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isPositiveReal(expr.getConditionalExpression().getExpression1()) 
					&& isPositiveReal(expr.getConditionalExpression().getExpression2());
			return isPositiveReal(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isPositiveReal(MdlPrinter.getInstance().toStr(orExpr));
		}
	}
	
	private static boolean isPositiveReal(String value) {
		try{
			Double x = Double.parseDouble(value);
			if (x > 0 ) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
	}
	
	private static boolean isReal(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isReal(expr.getConditionalExpression().getExpression1()) 
					&& isReal(expr.getConditionalExpression().getExpression2());
			return isReal(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			return isReal(MdlPrinter.getInstance().toStr(orExpr));
		}
	}
	
	private static boolean isReal(String value) {
		try{
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e){
			return isNumericConstant(value);
		}
	}	
	
	private static boolean isString(Expression expr) {
		if (expr.getConditionalExpression().getExpression1() != null){
			if (expr.getConditionalExpression().getExpression2() != null)
				return isString(expr.getConditionalExpression().getExpression1()) 
						&& isString(expr.getConditionalExpression().getExpression2());
			return isString(expr.getConditionalExpression().getExpression1());
		}
		else {
			OrExpression orExpr = expr.getConditionalExpression().getExpression();
			if (orExpr.getExpression().size() > 1) return false;
			AndExpression andExpr = orExpr.getExpression().get(0);
			if (andExpr.getExpression().size() > 1) return false;
			LogicalExpression logicExpr = andExpr.getExpression().get(0);
			if (logicExpr.getExpression1() != null){
				if (logicExpr.getExpression2() != null) return false;
				if (logicExpr.getExpression1().getString() != null) return true;
				return false;
			}
		}
		return false;	
	}	
	
	private static boolean isTransformationOperator(Expression expr){
		String trans = MdlPrinter.getInstance().toStr(expr);
		return (FunctionValidator.funct_standard1.contains(trans));
	}
}

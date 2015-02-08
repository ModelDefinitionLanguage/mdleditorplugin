/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * MDL type checking
 */
package org.ddmore.mdl.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.ddmore.mdl.domain.FunctionSignature;
import org.ddmore.mdl.domain.Variable;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.DistributionType;
import org.ddmore.mdl.mdl.EnumType; 
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.ExpressionBranch;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.IndividualVarType;
import org.ddmore.mdl.mdl.InputFormatType;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.ObjectName;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.PkParameterType;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.TargetType;
import org.ddmore.mdl.mdl.TrialType;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VariabilityType;
import org.ddmore.mdl.mdl.Vector;
import org.ddmore.mdl.validation.AttributeValidator;
import org.ddmore.mdl.validation.FunctionValidator;
import org.ddmore.mdl.validation.MdlJavaValidator;
import org.ddmore.mdl.validation.Utils;
import eu.ddmore.converter.mdlprinting.MdlPrinter;

public enum MdlDataType {	
    TYPE_UNDEFINED,
    /*Basic*/
    TYPE_STRING, TYPE_INT, TYPE_REAL, TYPE_BOOLEAN,
    //Restrictions of basic (to comply with PharmML)
    TYPE_NAT, TYPE_PNAT, TYPE_PREAL, TYPE_PROBABILITY,
    //References to variables and mathematical expressions
	TYPE_REF, TYPE_REF_DERIV, TYPE_EXPR,  
	//Matrix
	TYPE_MATRIX, TYPE_DIAG,
	//References to objects
	TYPE_OBJ_REF, TYPE_OBJ_REF_MODEL, TYPE_OBJ_REF_DATA, TYPE_OBJ_REF_PARAM, TYPE_OBJ_REF_TASK, TYPE_OBJ_REF_DESIGN,
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
	TYPE_RANDOM_EFFECT,  //{VAR, SD, CORR, COV} 
	TYPE_INPUT_FORMAT,   //{nonmemFormat, eventFormat}
	TYPE_DISTRIBUTION,   //see 'Distribution' in MDL grammar
	TYPE_INDIVIDUAL_VAR, //{linear, general}
	TYPE_CONTINUOUS, 	 //{continuous} 
	TYPE_PK_PARAMETER,   //{v_cl, v_k, vss_cl, a_b}
	TYPE_TRIAL           //{simple, sequential, combined}
	;
    
	static public boolean validateType(MdlDataType type, Expression expr){
		Boolean res = validateType(type, expr.getExpression());
		if (expr.getWhenBranches() != null){
			for (ExpressionBranch e: expr.getWhenBranches()){
				res = res && validateType(type, e.getExpression());
			}
		}
		if (expr.getElseExpression() != null){
			res = res && validateType(type, expr.getElseExpression());
		}
		return res;
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
		MdlDataType actualType = getDerivedType(expr);
		if (type == TYPE_VAR_TYPE && actualType == TYPE_CONTINUOUS) return true;
		return (actualType == type);
	}
	
	static public boolean validateType(MdlDataType type, RandomList expr){
		return (type == TYPE_RANDOM_LIST);
	}	
	
	public static boolean isEnumType(AnyExpression expr) {
		List<MdlDataType> types = Arrays.asList(
			TYPE_VAR_TYPE,
			TYPE_CONTINUOUS,
			TYPE_USE,
			TYPE_TARGET,
			TYPE_RANDOM_EFFECT,
			TYPE_INPUT_FORMAT,
			TYPE_TRIAL,
			TYPE_INDIVIDUAL_VAR,
			TYPE_DISTRIBUTION,
			TYPE_TRIAL,
			TYPE_PK_PARAMETER
		);
		return validateType(types, expr);
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
	
	static public boolean validateType(List<MdlDataType> types, AnyExpression expr){
		for (MdlDataType type: types){
			if (validateType(type, expr)) return true;
		}
		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	public static boolean validateType(MdlDataType type, OrExpression expr){
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
			case TYPE_REF_DERIV: return isDerivative(expr);
			case TYPE_EXPR: return true;  
			//References to objects
			case TYPE_OBJ_REF: return isObjectReference(expr);
			case TYPE_OBJ_REF_MODEL: isObjectReference(expr, TYPE_OBJ_REF_MODEL);
			case TYPE_OBJ_REF_DATA: return isObjectReference(expr, TYPE_OBJ_REF_DATA);
			case TYPE_OBJ_REF_PARAM: return isObjectReference(expr, TYPE_OBJ_REF_PARAM);
			case TYPE_OBJ_REF_TASK: return isObjectReference(expr, TYPE_OBJ_REF_TASK);
			case TYPE_OBJ_REF_DESIGN: return isObjectReference(expr, TYPE_OBJ_REF_DESIGN);
			//String restriction
			case TYPE_TRANS: return isTransformationOperator(expr);  
			default: return false;
		}
	}
	
	private static boolean isTransformationOperator(OrExpression expr){
		String trans = MdlPrinter.getInstance().toStr(expr);
		return (FunctionValidator.funct_standard1.contains(trans));
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//Validate vector types
	//Note: references in numeric vectors are allowed and not checked!
	////////////////////////////////////////////////////////////////////////////////
	
	private static boolean isVectorReal(Vector v) {
		for (AnyExpression p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorReal(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getExpression() != null){
					if (p.getExpression().getExpression() != null){
						boolean ok = isReal(p.getExpression().getExpression());
						if (!ok) return false;
					}
				}					
			}
		}
		return true;
	}
	
	private static boolean isVectorNat(Vector v) {
		for (AnyExpression p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorNat(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getExpression() != null){
					if (p.getExpression().getExpression() != null){
						boolean ok = isNatural(p.getExpression().getExpression());
						if (!ok) return false;
					}
				}
			}
		}
		return true;
	}
	
	private static boolean isVectorPNat(Vector v) {
		for (AnyExpression p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorNat(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getExpression() != null){
					if (p.getExpression().getExpression() != null){
						boolean ok = isPositiveNatural(p.getExpression().getExpression());
						if (!ok) return false;
					}
				}
			}
		}
		return true;
	}
	
	private static boolean isVectorPReal(Vector v) {
		for (AnyExpression p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorPReal(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getExpression() != null){
					if (p.getExpression().getExpression() != null){
						boolean ok = isPositiveReal(p.getExpression().getExpression());
						if (!ok) return false;
					}
				}
			}
		}
		return true;
	}
	
	
	private static boolean isVectorProbability(Vector v) {
		Double total = 0.;
		boolean containsReference = false;
		for (AnyExpression p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorPReal(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getExpression() != null){
					if (p.getExpression().getExpression() != null){
						boolean ok = isProbability(p.getExpression().getExpression());
						if (!ok) return false;
						String value = MdlPrinter.getInstance().toStr(p.getExpression());
						try{
							Double x = Double.parseDouble(value);
							total += x;	
						} catch (NumberFormatException e){
							containsReference = true; //probably contains a reference - validate
						}
					}
				}
			}
		}
		return ((containsReference && (total <= 1.)) || (total == 1.));
	}
		
	private static boolean isVectorInteger(Vector v) {
		for (AnyExpression p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorInteger(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getExpression() != null){
					if (p.getExpression().getExpression() != null){
						boolean ok = isInteger(p.getExpression().getExpression());
						if (!ok) return false;
					}
				}
			}
		}
		return true;
	}
	
	private static boolean isVectorReference(Vector v){
		for (AnyExpression p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorReference(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getExpression() != null){
					if (p.getExpression().getExpression() != null){
						return (isReference(p.getExpression().getExpression()));
					}
				}
			}
		}
		return true;	
	}

	private static boolean isVectorString(Vector v){
		for (AnyExpression p: v.getValues()){
			if (p.getVector() != null) {
				boolean ok = isVectorString(p.getVector());
				if (!ok) return false;
			} else {
				if (p.getExpression() != null){
					if (p.getExpression().getExpression() != null){
						return isString(p.getExpression().getExpression());
					}
				}
			}
		}
		return true;	
	}

	/////////////////////////////////////////////////////////////////////////////////////
	//Validate references
	/////////////////////////////////////////////////////////////////////////////////////
	
	private static boolean isObjectReference(OrExpression expr) {
		SymbolName s = getReference(expr);
		if (s != null) {
			Mcl mcl = (Mcl) expr.eResource().getContents().get(0);
			if (mcl != null)
				return Utils.getDeclaredObjects(mcl).containsKey(s.getName());
		}
		return false;
	}
	
	private static boolean isObjectReference(OrExpression orExpr, MdlDataType type) {
		SymbolName s = getReference(orExpr);
		if (s != null) {
			Mcl mcl = (Mcl) orExpr.eResource().getContents().get(0);
			if (mcl != null)
				return (Utils.getDeclaredObjects(mcl).get(s.getName()) == type);
		}
		return false;
	}

	private static boolean isReference(OrExpression orExpr) {
		if (getReference(orExpr) != null) return true;
		//Consider constant 'T' also a reference
		String constant = MdlPrinter.getInstance().toStr(orExpr);
		if (constant.equals("T")) return true;
		return false;
	}
	
	private static boolean isDerivative(OrExpression orExpr) {
		SymbolName s = getReference(orExpr);
		if (s != null) {
			Mcl mcl = (Mcl) orExpr.eResource().getContents().get(0);
			if (mcl != null){
				for (MclObject obj: mcl.getObjects()){
					if (obj.getModelObject() != null){
						HashSet<String> deriv_vars = Utils.getDerivativeVariables(obj.getModelObject());
						if (deriv_vars.contains(s.getName())) return true;
					}
				}
			}
		}
		return false;
	}


	//////////////////////////////////////////////////////////////////////////////////
	//Validate basic types
	//////////////////////////////////////////////////////////////////////////////////
	private static boolean isBoolean(OrExpression orExpr) {
		MdlDataType type = getDerivedType(orExpr);
		return (type == TYPE_BOOLEAN);
	}
	
	private static boolean isString(OrExpression orExpr) {
		MdlDataType type = getDerivedType(orExpr);
		return (type == TYPE_STRING);
	}	
	
	///////////////////////////////////////////////
	//TODO: Override to derive types
	///////////////////////////////////////////////
	private static boolean isPositiveNatural(OrExpression orExpr) {
		String str = MdlPrinter.getInstance().toStr(orExpr);
		if (isPositiveNatural(str)) return true;
		if (getDerivedType(orExpr) == TYPE_PNAT) return true;
		return false;
	}
	
	private static boolean isNatural(OrExpression orExpr) {
		String str = MdlPrinter.getInstance().toStr(orExpr);
		if (isNatural(str)) return true;
		MdlDataType type = getDerivedType(orExpr);
		if (type == TYPE_PNAT || type == TYPE_NAT) return true;
		return false;
	}	

	private static boolean isInteger(OrExpression orExpr) {
		String str = MdlPrinter.getInstance().toStr(orExpr);
		if (isInteger(str)) return true;
		MdlDataType type = getDerivedType(orExpr);
		if (type == TYPE_PNAT || type == TYPE_NAT || type == TYPE_INT) return true;
		return false;
	}
	
	private static boolean isProbability(OrExpression orExpr) {
		return isProbability(MdlPrinter.getInstance().toStr(orExpr));
	}

	private static boolean isPositiveReal(OrExpression orExpr) {
		String str = MdlPrinter.getInstance().toStr(orExpr);
		if (isPositiveReal(str)) return true;
		MdlDataType type = getDerivedType(orExpr);
		if (type == TYPE_PNAT || type == TYPE_PROBABILITY || type == TYPE_PREAL) return true;
		return false;
	}
	
	private static boolean isReal(OrExpression orExpr) {
		String str = MdlPrinter.getInstance().toStr(orExpr);
		if (isReal(str)) return true;
		MdlDataType type = getDerivedType(orExpr);
		if (type == TYPE_PNAT || type == TYPE_NAT || type == TYPE_INT || 
			type == TYPE_PROBABILITY ||type == TYPE_PREAL || type == TYPE_REAL) return true;
		return false;
	}
	
	////////////////////////////////////////////////////////
	//String-based type derivation
	////////////////////////////////////////////////////////
	private static boolean isPositiveNatural(String value) {
		try{
			Integer x = Integer.parseInt(value);
			if (x > 0) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
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

	private static boolean isInteger(String value) {
		try{
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e){
			return false;
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
	
	private static boolean isPositiveReal(String value) {
		try{
			Double x = Double.parseDouble(value);
			if (x > 0 ) return true;
		} catch (NumberFormatException e){
			return false;
		}
		return false;
	}
	
	private static boolean isReal(String value) {
		try{
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}	
	
	///////////////////////////////////////////////////////////
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
	
	///////////////////////////////////////////////////////////
	//Infer type
	///////////////////////////////////////////////////////////
	//Define type of a variable depending on its expression or container!
	public static MdlDataType getExpectedType(SymbolDeclaration s){
		if (s.getList() != null)
			return getExpectedType(s.getList());		
		return TYPE_REAL;
	}
	
	public static MdlDataType getExpectedType(org.ddmore.mdl.mdl.List l){
		//List contains type attribute - derive based on type
		AnyExpression type = MdlPrinter.getInstance().getAttributeExpression(l.getArguments(), AttributeValidator.attr_req_type.getName());
		if (type != null){
			if (type.getType() != null && type.getType().getType() != null){
				if (type.getType().getType().getCategorical() != null)
					return TYPE_INT;
			}
		}	
		return TYPE_REAL;
	}

	public static MdlDataType getDerivedType(org.ddmore.mdl.mdl.List l){
		//Derive the type of value
		AnyExpression value = MdlPrinter.getInstance().getAttributeExpression(l.getArguments(), AttributeValidator.attr_value.getName());
		if (value != null){
			return getDerivedType(value);
		}			
		return TYPE_REAL;
	}

	public static MdlDataType getDerivedType(AnyExpression expr){
		if (expr.getExpression() != null) 
			return getDerivedType(expr.getExpression());
		if (expr.getList() != null) 
			return getDerivedType(expr.getList());
		if (expr.getType() != null)
			return getDerivedType(expr.getType());
		if (expr.getVector() != null)
			return getDerivedType(expr.getVector());
		return TYPE_UNDEFINED;
	}
	
	public static MdlDataType getDerivedType(Vector expr){
		//Int and restrictions
		if (isVectorPNat(expr)) return TYPE_VECTOR_PNAT;
		if (isVectorNat(expr)) return TYPE_VECTOR_NAT;
		if (isVectorInteger(expr)) return TYPE_VECTOR_INT;
		//Real and restrictions
		if (isVectorProbability(expr)) return TYPE_VECTOR_PROBABILITY;
		if (isVectorPReal(expr)) return TYPE_VECTOR_PREAL;
		if (isVectorReal(expr)) return TYPE_VECTOR_REAL; 
		//Other
		if (isVectorString(expr)) return TYPE_VECTOR_STRING;  
		if (isVectorReference(expr)) return TYPE_VECTOR_REF;  
		return TYPE_VECTOR_EXPR;
	}
	
	public static MdlDataType getDerivedType(EnumType expr){
		if (expr.getType() != null) {
			if (expr.getType().getContinuous() != null) 
				return TYPE_CONTINUOUS;
			else 
				return TYPE_VAR_TYPE;
		}
		if (expr.getUse() != UseType.NO_USE) return TYPE_USE;
		if (expr.getTarget() != TargetType.NO_TARGET) return TYPE_TARGET;
		if (expr.getVariability() != VariabilityType.NO_VARIABILITY) return TYPE_RANDOM_EFFECT;
		if (expr.getInput() != InputFormatType.NO_INPUT_FORMAT) return TYPE_INPUT_FORMAT;
		if (expr.getIndividualVar() != IndividualVarType.NO_INDIVIDUAL_VAR) return TYPE_INDIVIDUAL_VAR;
		if (expr.getDistribution() != DistributionType.NO_DISTRIBUTION) return TYPE_DISTRIBUTION;
		if (expr.getTrial() != TrialType.NO_TRIAL) return TYPE_TRIAL;
		if (expr.getPkParameter() != PkParameterType.NO_PARAM) return TYPE_PK_PARAMETER;
		return TYPE_UNDEFINED;
	}

	public static MdlDataType getDerivedType(Expression expr){
		MdlDataType type = getDerivedType(expr.getExpression());
		//Compute type for all branches
		if (expr.getWhenBranches() != null){
			List<MdlDataType> subTypes = new ArrayList<MdlDataType>();
			for (ExpressionBranch branch: expr.getWhenBranches()){
				subTypes.add(getDerivedType(branch.getExpression()));
			}
			if (expr.getElseExpression() != null){
				subTypes.add(getDerivedType(expr.getElseExpression()));
			}
			//select less restrictive
			boolean[] typeRange = new boolean[5];
			for (MdlDataType subType: subTypes){
				if (subType == TYPE_REAL) return TYPE_REAL;
				if (subType == TYPE_PNAT) typeRange[0] = true; 
				if (subType == TYPE_NAT) typeRange[1] = true; 
				if (subType == TYPE_INT) typeRange[2] = true; 
				if (subType == TYPE_PROBABILITY) typeRange[3] = true; 
				if (subType == TYPE_PREAL) typeRange[4] = true; 
			}
			if (!typeRange[3]){// no probability
				if (!typeRange[4]){//all integer
					if (typeRange[2]) return TYPE_INT;
					if (typeRange[1]) return TYPE_NAT;
					return TYPE_PNAT;
				} else 
					return TYPE_PREAL;
			} else {//probability 0 <= x < 1
				if (typeRange[2]) //not all positive
					return TYPE_REAL;
				if (typeRange[4]) //not all < 1
					return TYPE_PREAL;
			}				
		}
		return type;
	}
	
	private static MdlDataType getDerivedType(OrExpression orExpr) {
		if (orExpr.getExpression().size() > 1) return TYPE_BOOLEAN;
		AndExpression andExpr = orExpr.getExpression().get(0);
		if (andExpr.getExpression().size() > 1) return TYPE_BOOLEAN;
		LogicalExpression logicExpr = andExpr.getExpression().get(0);
		if (logicExpr.getExpression1() != null){
			if (logicExpr.getExpression2() != null) return TYPE_BOOLEAN;
			//Additive expressions
			return getDerivedType(logicExpr.getExpression1());
		} else 
			return TYPE_BOOLEAN;
	}
	
	private static MdlDataType getDerivedType(AdditiveExpression addExpr) {
		if (addExpr.getString() != null) return TYPE_STRING;
		List<MdlDataType> subTypes = new ArrayList<MdlDataType>();
		for (MultiplicativeExpression multExpr: addExpr.getExpression()){
			MdlDataType subType = getDerivedType(multExpr);	
			subTypes.add(subType);
		}
		for (MdlDataType subType: subTypes){
			if (subType == TYPE_BOOLEAN) return TYPE_BOOLEAN;
			if (subType == TYPE_STRING) return TYPE_STRING;
		}
		//Numeric type
		MdlDataType type = TYPE_PNAT;
		for (String op: addExpr.getOperator()){
			if (op.equals("-")) type = TYPE_INT;
			break;
		}
		for (MdlDataType subType: subTypes){
			if (subType == TYPE_NAT && type == TYPE_PNAT) type = TYPE_NAT;
			if (subType == TYPE_PROBABILITY || subType == TYPE_PREAL || subType == TYPE_REAL) return TYPE_REAL;
		}
		return type;
	}
	
	private static MdlDataType getDerivedType(MultiplicativeExpression multExpr) {
		List<MdlDataType> subTypes = new ArrayList<MdlDataType>();
		for (PowerExpression powerExpr: multExpr.getExpression()){
			MdlDataType subType = getDerivedType(powerExpr);	
			subTypes.add(subType);
		}		
		for (MdlDataType subType: subTypes){
			if (subType == TYPE_BOOLEAN) return TYPE_BOOLEAN;
			if (subType == TYPE_STRING) return TYPE_STRING;
		}
		//Numeric type
		MdlDataType type = TYPE_PNAT;
		for (String op: multExpr.getOperator()){
			if (op.equals("/")) type = TYPE_PREAL;
			break;
		}
		for (MdlDataType subType: subTypes){
			if (subType == TYPE_PROBABILITY) type = TYPE_PROBABILITY;
			if (subType == TYPE_PREAL) type = TYPE_PREAL;
			if (subType == TYPE_REAL) return TYPE_REAL;
		}
		return type;
	}
	
	private static MdlDataType getDerivedType(PowerExpression powerExpr) {
		List<MdlDataType> subTypes = new ArrayList<MdlDataType>();
		for (UnaryExpression unaryExpr: powerExpr.getExpression()){
			MdlDataType subType = getDerivedType(unaryExpr);	
			subTypes.add(subType);
		}
		for (MdlDataType subType: subTypes){
			if (subType == TYPE_BOOLEAN) return TYPE_BOOLEAN;
			if (subType == TYPE_STRING) return TYPE_STRING;
		}
		MdlDataType type = TYPE_PNAT;
		for (MdlDataType subType: subTypes){
			if (subType == TYPE_REAL) type = TYPE_REAL;
		}
		return type;
	}
	
	private static MdlDataType getDerivedType(UnaryExpression unaryExpr) {
		if (unaryExpr.getParExpression() != null)
			return getDerivedType(unaryExpr.getParExpression().getExpression());
		if (unaryExpr.getExpression() != null)
			return getDerivedType(unaryExpr.getExpression());
		if (unaryExpr.getFunctionCall() != null) 
			return getDerivedType(unaryExpr.getFunctionCall());
		if (unaryExpr.getConstant() != null){
			String value = unaryExpr.getConstant();
			if (value.equals("INF")) return TYPE_PNAT;
			return TYPE_UNDEFINED;
		}
		if (unaryExpr.getNumber() != null){
			if (isInteger(unaryExpr.getNumber())) return TYPE_INT;
			return TYPE_REAL;
		}
		if (unaryExpr.getSymbol() != null){
			//Careful with recursive call!
			ObjectName mclObj = Utils.getObjectName(unaryExpr.getSymbol());
			if (mclObj != null && MdlJavaValidator.declaredVariables.containsKey(mclObj.getName()))
				for (Variable var: MdlJavaValidator.declaredVariables.get(mclObj.getName())){
					if (var.getName() == unaryExpr.getSymbol().getName())
						return var.getType();
				}
			return TYPE_REAL;
		}
		if (unaryExpr.getAttribute() != null){
			//Find attribute definition and type its value
		}
		return TYPE_REAL;	
	}
	
	public static MdlDataType getDerivedType(FunctionCall call){
		FunctionSignature functSig = FunctionValidator.standardFunctions.get(call.getIdentifier().getName());
		if (functSig != null)
			return functSig.getType();
		return TYPE_REAL;
	}
}

/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to parse unit expression (may be extended later to perform ontology-based unit validation and/or conversion)
 */
package org.ddmore.mdl.validation;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.MdlPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

public class UnitValidator extends AbstractDeclarativeValidator{

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}
	
	public final static String MSG_UNIT_UNKNOWN = "Failed to parse the unit value";	

	final static List<String> units = Arrays.asList("L", "l", "m", "y", "h", "s", "kg", "g", "mg");
	final int ID = 1;
	final int NUMBER = 2;
	final int OPEN_BRACKET = 3;
	final int CLOSE_BRACKET = 4;
	final int MULT_OP = 5;
	final int POWER_OP = 6;

	//Check the unit measurement format
	//Returns an incorrect token or null otherwise
	String parseUnitExpression(String str){
		String[] tokens = str.split("(?<=[*/^\\(\\)])|(?=[*/^\\(\\)])");
		//TEST: return Arrays.toString(tokens);
		Stack<Integer> stack = new Stack<Integer>(); 
		for (int i = 0; i < tokens.length; i++){
			String next = tokens[i];
			if (next.length() > 0){
				int lexem = -1;
				if (units.contains(next)) lexem = ID; //ID
				if (next.equals("(")) lexem = OPEN_BRACKET;
				if (next.equals(")")) lexem = CLOSE_BRACKET; 
				if (next.equals("*") || next.equals("/")) lexem = MULT_OP; 
				if (next.equals("^")) lexem = POWER_OP; 	
				if (next.matches("(-?[1-9][0-9]*)")) lexem = NUMBER;
				
				if (lexem < 0) return next; //unrecognized symbol
				
				if (lexem == ID){
					if (!stack.isEmpty()){
						if ((stack.peek() != MULT_OP) && (stack.peek() != OPEN_BRACKET)) return next;
					}
					stack.push(ID);
				}
				if (lexem == OPEN_BRACKET){
					if (!stack.isEmpty()) 
						if ((stack.peek() != MULT_OP) && (stack.peek() != OPEN_BRACKET)) return next;					
					stack.push(OPEN_BRACKET);
				}
				if (lexem == CLOSE_BRACKET){
					if (stack.isEmpty()) return next;
					if ((stack.peek() != ID) && (stack.peek() != OPEN_BRACKET)) return next;	
					while (stack.peek() != OPEN_BRACKET) {
						stack.pop();
						if (stack.isEmpty()) return next;
					}
					stack.pop();
					stack.push(ID);
				}
				if (lexem == MULT_OP){
					if (stack.isEmpty()) return next;
					if (stack.peek() != ID) return next;
					if (i == tokens.length - 1) return next; //can't be the last character
					stack.push(MULT_OP);
				}
				if (lexem == POWER_OP){
					if (stack.isEmpty()) return next;
					if (stack.peek() != ID) return next;					
					if (i == tokens.length - 1) return next; //can't be the last character
					stack.push(POWER_OP);
				}
				if (lexem == NUMBER){
					if (stack.isEmpty()) return next;
					if (stack.peek() != POWER_OP) return next;					
					stack.pop();
				}
			}
		}
		while (!stack.isEmpty()){
			if (stack.pop() == OPEN_BRACKET) return "(";
		}
		return null;
	}

	//Check whether the attribute "units" defines a correct unit measurement
	@Check
	public void checkUnitValue(Argument arg){
		if (arg.getArgumentName() != null){
			if (arg.getArgumentName().getIdentifier().equals("units")){
				String unitValue = Utils.getAttributeValue(arg);
				if (unitValue.length() > 0){
					unitValue = unitValue.replaceAll("\\s+","");
					String wrongToken = parseUnitExpression(unitValue);
					if (wrongToken != null){
						warning(MSG_UNIT_UNKNOWN + ": " + wrongToken, 
						MdlPackage.Literals.ARGUMENT_NAME__IDENTIFIER,
						MSG_UNIT_UNKNOWN, arg.getArgumentName().getIdentifier());
					}
				}
			}
		}
	}
}
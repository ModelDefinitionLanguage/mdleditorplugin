/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks references
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.mdl.*;
import org.ddmore.mdl.mdl.impl.FunctionCallImpl;
import org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl;
import org.ddmore.mdl.mdl.impl.FunctionCallStatementImpl;
import org.ddmore.mdl.mdl.impl.MclObjectImpl;
import org.ddmore.mdl.mdl.impl.SourceBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl;
import org.ddmore.mdl.mdl.impl.TaskFunctionBlockImpl;
import org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ComposedChecks;

@ComposedChecks(validators = { 
		AttributeValidator.class, 
		FunctionValidator.class, 
		DistributionValidator.class, 
		UnitValidator.class})
public class MdlJavaValidator extends AbstractMdlJavaValidator {

	public final static String MSG_SYMBOL_DEFINED  = "A variable or parameter with such name already exists";
	public final static String MSG_SYMBOL_UNKNOWN  = "Unresolved reference: parameter, variable, object or formal argument not declared";
		
	public final static String MSG_UNRESOLVED_ATTRIBUTE_REF = "Unresolved reference to a list attribute";
	public final static String MSG_UNRESOLVED_FUNC_ARGUMENT_REF = "Unresolved reference to a function output parameter";
	public final static String MSG_UNRESOLVED_SAME_BLOCK_NAME = "No corresponding matrix or diag block found";

	public final static String MSG_TARGET_LOCATION = "Target code block is not used inline, "
		+ "please specify location";

	//List of objects
	Map<String, MdlDataType> declaredObjects = new HashMap<String, MdlDataType>();	
	//List of declared variables per object
	Map<String, List<String>> declaredVariables = new HashMap<String, List<String>>();
	
	//List of declared variability subblocks diag and matrix (to match with same blocks)
	Map<String, List<String>> variabilitySubblockNames = new HashMap<String, List<String>>();

	List<ModellingObjectGroup> mogs = new ArrayList<ModellingObjectGroup>(); 
	
	@Check
	public void updateObjectList(Mcl mcl){
		declaredObjects = Utils.getDeclaredObjects(mcl);
	}

	//Update the list of recognised variables
	@Check
	public void updateDeclaredVariableList(Mcl mcl){
		//System.out.println("Updating variables...");
		declaredVariables.clear();
		for (MclObject obj: mcl.getObjects()){
			ArrayList<String> varList = new ArrayList<String>();
			TreeIterator<EObject> symbolIterator = obj.eAllContents();
			while (symbolIterator.hasNext()) {
				EObject container = symbolIterator.next();
				if (container instanceof SymbolDeclarationImpl) {
					SymbolDeclaration s = (SymbolDeclaration) container;
					if (s.getSymbolName() != null)
						varList.add(s.getSymbolName().getName());
				}
				if (container instanceof FunctionCallStatementImpl) {
					FunctionCallStatement s = (FunctionCallStatement) container;
					if (s.getSymbolName() != null)
						varList.add(s.getSymbolName().getName());
				}
				//DataObject -> SOURCE
		    	if (container instanceof SourceBlockImpl){
		    		SourceBlock block = (SourceBlock) container;
		    		if (block.getSymbolName() != null)
						varList.add(block.getSymbolName().getName());
				}
				//ParameterObject -> VARIABILITY, matrix, diag, same
		    	if (container instanceof VariabilityBlockStatementImpl){
					VariabilityBlockStatement s = (VariabilityBlockStatement)container;
					if (s.getParameter() != null && s.getParameter().getSymbolName() != null)
						varList.add(s.getParameter().getSymbolName().getName());
					if (s.getMatrixBlock() != null)
						Utils.addSymbol(varList, s.getMatrixBlock().getParameters());
					if (s.getDiagBlock() != null)
						Utils.addSymbol(varList, s.getDiagBlock().getParameters());
					if (s.getSameBlock() != null)
						Utils.addSymbol(varList, s.getSameBlock().getParameters());
				}
		    	if (container instanceof FunctionCallImpl){
		    		FunctionCall functCall = (FunctionCall) container;
		    		String functName = functCall.getIdentifier().getFunction().getName();
		    		if (functCall.getArguments() != null){
		    			if (FunctionValidator.libraries.contains(functName))
		    				varList.addAll(Utils.extractSymbolNames(functCall.getArguments(), 
		    						FunctionValidator.param_output.name));
			    		if (FunctionValidator.funct_standardWithOutputParams.contains(functName)){
							//when parameters passed by place, assume default set of attributes
			    			//and validate all references assigned to output parameters
			    			FunctionSignature signature = FunctionValidator.standardFunctions.get(functName);
				    		if (signature != null){
				    			for (int i = 0; i < signature.getDefaultParams().size(); i++){
				    				FunctionParameter p = signature.getDefaultParams().get(i);
				    				if (p.isOutputParameter()){
					    				if (functCall.getArguments().getArguments().size() > i) {
						    				Argument arg = functCall.getArguments().getArguments().get(i);
						    				varList.addAll(Utils.extractSymbolNames(arg));
					    				}
				    				}
				    			}
				    		}
			    		}
    	    		}
		    	}
			}			
		    if (varList.size() > 0)
		    	declaredVariables.put(obj.getObjectName().getName(), varList);
		}
		//System.out.println("Declared variables:");
    	//for (String key: declaredVariables.keySet()){
    	//	System.out.println(Utils.printList(declaredVariables.get(key)));
    	//}
	}
	
	//Update the list of declared variability subblock names
	@Check
	public void updateVariabilitySubblockNames(Mcl mcl){
		variabilitySubblockNames.clear();
		for (MclObject obj: mcl.getObjects()){
			//Parameter object
			if (obj.getParameterObject() != null){
				ArrayList<String> paramList = new ArrayList<String>();
				for (ParameterObjectBlock block: obj.getParameterObject().getBlocks()){
					//VARIABILITY sub-blocks matrix & diag
					if (block.getVariabilityBlock() != null){
						for (VariabilityBlockStatement s: block.getVariabilityBlock().getStatements()){
							if (s.getMatrixBlock() != null){
								String name = Utils.getAttributeValue(s.getMatrixBlock().getArguments(), AttributeValidator.attr_name.getName());
								if (name.length() > 0) paramList.add(name);
							}
							if (s.getDiagBlock() != null){
								String name = Utils.getAttributeValue(s.getDiagBlock().getArguments(), AttributeValidator.attr_name.getName());
								if (name.length() > 0) paramList.add(name);
							}
						}
					}
				}
				variabilitySubblockNames.put(obj.getObjectName().getName(), paramList);
			}
		}
	}
	
	@Check
	public void updateLinkedObjects(Mcl mcl){
		mogs = Utils.getMOGs(mcl);
	}
	
	@Check
	public void checkTargetLocation(TargetBlock t){
		EObject container = t.eContainer();
		if (!(container instanceof BlockStatement)){
			//external target blocks should have location defined
			String location = Utils.getAttributeValue(t.getArguments(), AttributeValidator.attr_location.name);
			if (location.length() == 0){
				warning(MSG_TARGET_LOCATION, 
						MdlPackage.Literals.TARGET_BLOCK__ARGUMENTS,
						MSG_TARGET_LOCATION, t.getIdentifier());
			}
		}
	}

	//Match the name of the same block with the name of a matrix or a diag block
	@Check
	public void validateSameSubblockName(SameBlock b){
		String name = Utils.getAttributeValue(b.getArguments(), AttributeValidator.attr_name.getName());
		if (name.length() > 0){
			ObjectName objName = Utils.getObjectName(b.eContainer());
			if (!Utils.isIdentifierDeclared(variabilitySubblockNames, name, objName.getName()))
				warning(MSG_UNRESOLVED_SAME_BLOCK_NAME, 
						MdlPackage.Literals.SAME_BLOCK__IDENTIFIER,
						MSG_UNRESOLVED_SAME_BLOCK_NAME, b.getIdentifier());
		}
	}

	////////////////////////////////////////////////////////////////
	//Check references
	////////////////////////////////////////////////////////////////

	@Check
	public void checkReference(SymbolName ref) {
		EObject container = ref.eContainer();
		//Skip validation of symbol names which are not references 
		if (container instanceof SymbolDeclarationImpl ||
			container instanceof SourceBlockImpl ||
			container instanceof FunctionCallStatement) {
			return;
		}
		
		if (container instanceof FullyQualifiedArgumentNameImpl){
			if (!Utils.isSymbolDeclared(declaredVariables, ref))
				warning(MSG_SYMBOL_UNKNOWN, MdlPackage.Literals.SYMBOL_NAME__NAME,
						MSG_SYMBOL_UNKNOWN, ref.getName());
		}
		else {
			//TODO: for MOG validation, collect declared variables for a given object name instead of all
			if (!(Utils.isSymbolDeclared(declaredVariables, ref, mogs) ||
					declaredObjects.containsKey(ref.getName()) ||
					isFormalParameter(ref))){
				warning(MSG_SYMBOL_UNKNOWN, MdlPackage.Literals.SYMBOL_NAME__NAME,
						MSG_SYMBOL_UNKNOWN, ref.getName());
			}
		}
	}

	//Check whether the reference is a formal parameter or local variable (property) in the function declaration
	private boolean isFormalParameter(SymbolName ref) {
		//Look for TaskFunctionDeclaration
		EObject container = ref.eContainer();
		while (!(container instanceof TaskFunctionBlockImpl)){
			if (container instanceof MclObjectImpl) return false;
			container = container.eContainer();
		}
		if (container instanceof TaskFunctionBlockImpl){
			ArrayList<String> varList = new ArrayList<String>();
			TaskFunctionBlock tfb = (TaskFunctionBlock)container; 
			if (tfb.getEstimateBlock() != null){
				for (BlockStatement b: tfb.getEstimateBlock().getStatements())
					Utils.addSymbol(varList, b);
			}
			if (tfb.getSimulateBlock() != null){
				for (BlockStatement b: tfb.getSimulateBlock().getStatements())
					Utils.addSymbol(varList, b);
			}
			if (tfb.getExecuteBlock() != null){
				for (BlockStatement b: tfb.getExecuteBlock().getStatements())
					Utils.addSymbol(varList, b);
			}
			if (varList.contains(ref.getName())) return true;
		}
		while (!(container instanceof TaskFunctionDeclarationImpl)){
			if (container instanceof MclObjectImpl) return false;
			container = container.eContainer();
		}
		if (container instanceof TaskFunctionDeclarationImpl) {
			//TaskFunctionDeclaration found
			TaskFunctionDeclaration func = (TaskFunctionDeclaration)container;
			if (func.getFormalArguments() != null){
				for (ArgumentName arg: func.getFormalArguments().getArguments())
					if (arg.getName().equals(ref.getName())) return true;
			}
		}
		return false;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	//Check references to list attributes
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	private boolean checkAttributes(FullyQualifiedArgumentName attrName, List<Argument> arguments) {
		List <Argument> currArgs = arguments; 
		for (Selector x: attrName.getSelectors()){
			if (currArgs != null){
				int index = -1;
				if (x.getSelector() != null){
					index = Integer.parseInt(x.getSelector());
					if (!((index >= 1) && (index < currArgs.size() + 1))) return false;
					index = 1;	
				}
				if (x.getArgumentName() != null){
					int i = 0;
					for (Argument arg: currArgs){
						if (arg.getArgumentName().getName().equals(x.getArgumentName().getName())){
							index = i + 1;
							break;
						}
						i++; 
					}
				}
				if (index > 0) {
					if (currArgs.get(index - 1).getExpression().getList() != null)
						if (arguments.get(index).getExpression().getList().getArguments() != null)
							currArgs = arguments.get(index).getExpression().getList().getArguments().getArguments();
					if (currArgs.get(index - 1).getExpression().getOdeList() != null) 
						if (arguments.get(index).getExpression().getOdeList().getArguments() != null)
							currArgs = arguments.get(index).getExpression().getOdeList().getArguments().getArguments();
					
				} else return false;
			} 
		}
		return true;		
	}
	

	@Check
	public void checkReference(FullyQualifiedArgumentName ref) {
		//The reference is to the symbol with assigned expression which is a function call
		//We check that attributes refer to function arguments
		if (checkReferenceToFuctionOutput(ref)) return;

		String varName = ref.getParent().getName();
		Resource resource = ref.eResource();
		LinkedList<Argument> args = new LinkedList<Argument>();		

		TreeIterator<EObject> iterator = resource.getAllContents();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof SymbolDeclarationImpl){
	    		SymbolDeclaration s = (SymbolDeclaration) obj;
	    		if (s.getSymbolName() != null){
		    		if (s.getSymbolName().getName().equals(varName)) {
		    			if (s.getExpression() != null){
		       				if (s.getExpression().getList() != null)
		       					if (s.getExpression().getList().getArguments() != null)
		       						for (Argument x: s.getExpression().getList().getArguments().getArguments())
		           						args.add(x);
		       				if (s.getExpression().getOdeList() != null)
		       					if (s.getExpression().getOdeList().getArguments() != null)
		       						for (Argument x: s.getExpression().getOdeList().getArguments().getArguments())
		           						args.add(x);
		    			}
		    			//TODO: validate random lists
	       				//if (s.getRandomList() != null)
	       				//	if (s.getRandomList().getArguments() != null)...
		    		}
	    		}
	    	}
			//DataObject -> SOURCE
	    	if (obj instanceof SourceBlockImpl){
			}
	    }
	    if (args.size() != 0 && !checkAttributes(ref, args)){
			warning(MSG_UNRESOLVED_ATTRIBUTE_REF, 
					MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
					MSG_UNRESOLVED_ATTRIBUTE_REF, ref.getParent().getName());
	    }
	}

	//Validate a fully qualified argument whose parent refers to a variable declared as a function 
	//It is assumed that attribute selectors will refer to symbols in attribute "output" of a function call 
	public boolean checkReferenceToFuctionOutput(FullyQualifiedArgumentName ref) {
		String varName = ref.getParent().getName();		
		Resource resource = ref.eResource();
		TreeIterator<EObject> iterator = resource.getAllContents();
		ArrayList<String> params = new ArrayList<String>();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof FunctionCallStatementImpl){
	    		FunctionCallStatement s = (FunctionCallStatement) obj;
	    		if (s.getSymbolName() != null && s.getSymbolName().getName().equals(varName)) {	    			
	    			//Compare reference with references in FunctionCall param attribute
	    			//Does not guarantee the correctness as references may occur in expressions
	    			FunctionCall funcCall = s.getExpression();
	    			params.addAll(Utils.extractSymbolNames(funcCall.getArguments(), FunctionValidator.param_output.getName()));
	       			ArgumentName paramRef = ref.getSelectors().get(0).getArgumentName();
	       			if (paramRef != null){
	       				if (!params.contains(paramRef.getName())){
	       					warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       							paramRef.getName() + " is not in the reference set " + Utils.printList(params), 
	       							MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       							MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getName());
	       				}
	       			} else {
	       				String selector = ref.getSelectors().get(0).getSelector();
	       				int index = Integer.parseInt(selector);
	       				if (index < 1 || index > params.size()){
	       					warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       							"wrong index [" + index + "]. " + 
	       							"Reference set " + Utils.printList(params) + " contains " + params.size() + " items.", 
	       							MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       							MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getName());
	       				}	       					
	       			}
	       			return true; //skip list attribute check
	    		}
	    	}
	    }
	    return false;
	}
}

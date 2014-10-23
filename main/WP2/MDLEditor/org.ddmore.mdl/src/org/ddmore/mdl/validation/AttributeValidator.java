/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks generic list attributes for various blocks
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VariabilityType;
import org.ddmore.mdl.mdl.impl.ActionBlockImpl;
import org.ddmore.mdl.mdl.impl.AdministrationBlockImpl;
import org.ddmore.mdl.mdl.impl.ArgumentImpl;
import org.ddmore.mdl.mdl.impl.ArgumentsImpl;
import org.ddmore.mdl.mdl.impl.CompartmentBlockImpl;
import org.ddmore.mdl.mdl.impl.DataDerivedBlockImpl;
import org.ddmore.mdl.mdl.impl.DataInputBlockImpl;
import org.ddmore.mdl.mdl.impl.DesignSpaceBlockImpl;
import org.ddmore.mdl.mdl.impl.DiagBlockImpl;
import org.ddmore.mdl.mdl.impl.EstimationBlockImpl;
import org.ddmore.mdl.mdl.impl.FunctionCallImpl;
import org.ddmore.mdl.mdl.impl.HyperSpaceBlockImpl;
import org.ddmore.mdl.mdl.impl.IndividualVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.LibraryBlockImpl;
import org.ddmore.mdl.mdl.impl.ListImpl;
import org.ddmore.mdl.mdl.impl.MatrixBlockImpl;
import org.ddmore.mdl.mdl.impl.MclObjectImpl;
import org.ddmore.mdl.mdl.impl.ObservationBlockImpl;
import org.ddmore.mdl.mdl.impl.OdeBlockImpl;
import org.ddmore.mdl.mdl.impl.DeqBlockImpl;
import org.ddmore.mdl.mdl.impl.RandomListImpl;
import org.ddmore.mdl.mdl.impl.RandomVariableDefinitionBlockImpl;
import org.ddmore.mdl.mdl.impl.SameBlockImpl;
import org.ddmore.mdl.mdl.impl.SamplingBlockImpl;
import org.ddmore.mdl.mdl.impl.SimulationBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.StudyDesignBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl;
import org.ddmore.mdl.types.DefaultValues;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

public class AttributeValidator extends AbstractDeclarativeValidator{

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}
	
	public final static String MSG_ATTRIBUTE_UNKNOWN    = "Unknown attribute";
	public final static String MSG_ATTRIBUTE_MISSING    = "Required attribute is missing";
	public final static String MSG_ATTRIBUTE_DEFINED    = "Attribute defined more than once";
	public final static String MSG_ATTRIBUTE_WRONG_TYPE = "Type error";
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	/*Common*/
	//name
	final public static Attribute attr_name = new Attribute("name", MdlDataType.TYPE_STRING, false, "");		
	final public static Attribute attr_name_ref = new Attribute("name", MdlDataType.TYPE_REF, true);		
	//value
	final public static Attribute attr_req_value = new Attribute("value", MdlDataType.TYPE_REAL, true, "0");
	final public static Attribute attr_value = new Attribute("value", MdlDataType.TYPE_REAL, false, "0");
	final public static Attribute attr_expr_value = new Attribute("value", MdlDataType.TYPE_EXPR, true, "0");
	//type
	final public static Attribute attr_req_type = new Attribute("type", MdlDataType.TYPE_VAR_TYPE, true, DefaultValues.VAR_TYPE);
	final public static Attribute attr_type = new Attribute("type", MdlDataType.TYPE_VAR_TYPE, false, DefaultValues.VAR_TYPE);
	final public static Attribute attr_re_type = new Attribute("type", MdlDataType.TYPE_RANDOM_EFFECT, false, VariabilityType.SD.toString());
	final public static Attribute attr_continuous_type = new Attribute("type", MdlDataType.TYPE_CONTINUOUS, true, DefaultValues.VAR_CONTINUOUS);
	
	//VARIABILITY
	final public static Attribute attr_hi = new Attribute("hi", MdlDataType.TYPE_REAL, false, "0");
	final public static Attribute attr_lo = new Attribute("lo", MdlDataType.TYPE_REAL, false, "1");
	final public static Attribute attr_fix = new Attribute("fix", MdlDataType.TYPE_BOOLEAN, false, "false");
	final public static Attribute attr_units = new Attribute("units", MdlDataType.TYPE_STRING, false, "kg");
	
	//RANDOM_VARIABLES_DEFINITION
	final public static Attribute attr_rv1 = new Attribute("rv1", MdlDataType.TYPE_REF, true);
	final public static Attribute attr_rv2 = new Attribute("rv2", MdlDataType.TYPE_REF, true);
	final public static Attribute attr_level_ref = new Attribute("level", MdlDataType.TYPE_REF, true);
	
	/*MODEL_INPUT_VARIABLES*/
	final public static Attribute attr_use = new Attribute("use", MdlDataType.TYPE_USE, false, UseType.ID.toString());
	final public static Attribute attr_level = new Attribute("level", MdlDataType.TYPE_NAT, false, DefaultValues.LEVEL);
	final public static Attribute attr_alias = new Attribute("alias", MdlDataType.TYPE_REF, false);

	/*OBSERVATION*/
	final public static Attribute attr_error = new Attribute("error", MdlDataType.TYPE_EXPR, true);
	final public static Attribute attr_eps = new Attribute("eps", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_prediction_ref = new Attribute("prediction", MdlDataType.TYPE_REF, false);

	/*ESTIMATION*/
	final public static Attribute attr_prediction = new Attribute("prediction", MdlDataType.TYPE_EXPR, false);
	final public static Attribute attr_ruv = new Attribute("ruv", MdlDataType.TYPE_EXPR, false);
	
	/*ODE*/
	final public static Attribute attr_req_deriv = new Attribute("deriv", MdlDataType.TYPE_EXPR, true, DefaultValues.VAR_NAME);	
	final public static Attribute attr_init = new Attribute("init", MdlDataType.TYPE_EXPR, false, "0");	
	final public static Attribute attr_x0 = new Attribute("x0", MdlDataType.TYPE_REAL, false, "0");	
	final public static Attribute attr_wrt = new Attribute("wrt", MdlDataType.TYPE_REF, false, DefaultValues.INDEPENDENT_VAR);	
	
	/*DATA_INPUT_VARIABLES*/
	final public static Attribute attr_define = new Attribute("define", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_recode = new Attribute("recode", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_boundaries = new Attribute("boundaries", MdlDataType.TYPE_VECTOR_REAL, false);
	final public static Attribute attr_missing = new Attribute("missing", MdlDataType.TYPE_INT, false);
	
	/*INDIVIDUAL_VARIABLES*/
	final public static Attribute attr_g_type = new Attribute("type", MdlDataType.TYPE_INDIVIDUAL_VAR, true);
	final public static Attribute attr_trans = new Attribute("trans", MdlDataType.TYPE_TRANS, true);
	final public static Attribute attr_fixEff = new Attribute("fixEff", 
			Arrays.asList(MdlDataType.TYPE_REF, MdlDataType.TYPE_VECTOR_REF), false);
	final public static Attribute attr_cov = new Attribute("cov", 
			Arrays.asList(MdlDataType.TYPE_REF, MdlDataType.TYPE_VECTOR_REF), false);
	final public static Attribute attr_pop = new Attribute("pop", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_ranEff = new Attribute("ranEff", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_group = new Attribute("group", MdlDataType.TYPE_REF, false);

	/*Deaign object*/
	final public static Attribute attr_trial_type = new Attribute("type", MdlDataType.TYPE_TRIAL, false);

	/*ADMINISTRATION*/
	final public static Attribute attr_start = new Attribute("start", MdlDataType.TYPE_INT, false);
	final public static Attribute attr_end = new Attribute("end", MdlDataType.TYPE_INT, false);
	final public static Attribute attr_administration = new Attribute("administration", 
			MdlDataType.TYPE_INT, false);
	final public static Attribute attr_amount = new Attribute("amount", 
			Arrays.asList(MdlDataType.TYPE_NAT, MdlDataType.TYPE_VECTOR_NAT), false);
	final public static Attribute attr_doseTime = new Attribute("doseTime", 
			Arrays.asList(MdlDataType.TYPE_NAT, MdlDataType.TYPE_VECTOR_NAT), false);
	final public static Attribute attr_duration = new Attribute("duration", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_steadyState = new Attribute("steadyState", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_interval = new Attribute("interval", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_combination = new Attribute("combination", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_treatment = new Attribute("treatment", MdlDataType.TYPE_REF, false);
	
	/*ACTION*/
	final public static Attribute attr_reset = new Attribute("reset", MdlDataType.TYPE_LIST, true);
	
	/*SAMPLING*/
	final public static Attribute attr_samplingTime = new Attribute("samplingTime", 
			Arrays.asList(MdlDataType.TYPE_NAT, MdlDataType.TYPE_VECTOR_NAT), true);
	final public static Attribute attr_outcome = new Attribute("outcome", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_bql = new Attribute("bql", MdlDataType.TYPE_REAL, false);
	
	/*STUDY_DESIGN (ARMS)*/
	final public static Attribute attr_groupSize = new Attribute("groupSize", MdlDataType.TYPE_INT, true);
	final public static Attribute attr_inteventionSequence = new Attribute("inteventionSequence", 
			MdlDataType.TYPE_LIST, true);
	final public static Attribute attr_occasionValue = new Attribute("occasionValue", 
			MdlDataType.TYPE_LIST, true);
	final public static Attribute attr_samplingSequence = new Attribute("samplingSequence", MdlDataType.TYPE_LIST, true);
	final public static Attribute attr_covariates = new Attribute("covariates", 
			Arrays.asList(MdlDataType.TYPE_REF, MdlDataType.TYPE_VECTOR_REF), false);
	
	/*DESIGN_SPACE, HYPER_SPACE*/
	final public static Attribute attr_admTime = new Attribute("admTime", MdlDataType.TYPE_RANDOM_LIST, false);
	final public static Attribute attr_min = new Attribute("min", MdlDataType.TYPE_RANDOM_LIST, false);
	final public static Attribute attr_max = new Attribute("max", MdlDataType.TYPE_RANDOM_LIST, false);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*Data object*/
	final public static List<Attribute> attrs_dataInput = Arrays.asList(attr_req_type, attr_define, attr_units, 
			attr_recode, attr_boundaries, attr_missing);
	final public static List<Attribute> attrs_dataDerived = Arrays.asList(attr_req_type, attr_expr_value, attr_units);

	/*Parameter object*/
	final public static List<Attribute> attrs_structural = Arrays.asList(attr_req_value, attr_lo, attr_hi, 
			attr_fix, attr_units);
	final public static List<Attribute> attrs_variability = Arrays.asList(attr_req_value, attr_re_type, attr_fix, 
			attr_lo, attr_hi, attr_units);
	final public static List<Attribute> attrs_variability_subblock = Arrays.asList(attr_name, attr_re_type, attr_fix);
	
	/*Model object*/
	final public static List<Attribute> attrs_randomVars = Arrays.asList(attr_re_type, attr_rv1, attr_rv2, attr_level_ref);
	final public static List<Attribute> attrs_inputVariables = Arrays.asList(attr_value, attr_use, attr_units, 
			attr_type, attr_level, attr_alias);
	final public static List<Attribute> attrs_ode = Arrays.asList(attr_req_deriv, attr_init, attr_x0, attr_wrt);
	final public static List<Attribute> attrs_deq = Arrays.asList(attr_req_deriv, attr_init, attr_x0, attr_wrt);
	final public static List<Attribute> attrs_estimation = Arrays.asList(attr_type, attr_prediction, attr_ruv);
	final public static List<Attribute> attrs_simulation = Arrays.asList();
	final public static List<Attribute> attrs_observation = Arrays.asList(attr_continuous_type, attr_error, attr_eps, attr_prediction_ref);
	final public static List<Attribute> attrs_structuralParams = Arrays.asList(attr_units);
	final public static List<Attribute> attrs_variabilityParams = Arrays.asList(attr_units);
	final public static List<Attribute> attrs_individualVariables = Arrays.asList(
			attr_g_type, attr_trans, attr_pop, attr_cov, attr_fixEff, attr_ranEff, attr_group);
	
	/*Design object*/
	final public static List<Attribute> attrs_administration = Arrays.asList(
		attr_trial_type, attr_administration, attr_amount, attr_doseTime, attr_start, 
		attr_end, attr_steadyState, attr_interval, attr_treatment, attr_combination);
	final public static List<Attribute> attrs_action = Arrays.asList(attr_start, attr_end, attr_reset);
	final public static List<Attribute> attrs_sampling = Arrays.asList(
			attr_trial_type, attr_start, attr_end, attr_samplingTime, attr_outcome, attr_bql, attr_combination);
	final public static List<Attribute> attrs_studyDesign = Arrays.asList(
			attr_groupSize, attr_inteventionSequence, attr_occasionValue, attr_samplingSequence, attr_covariates);
	final public static List<Attribute> attrs_designSpace = Arrays.asList(attr_name_ref);
	final public static List<Attribute> attrs_hyperSpace = Arrays.asList(attr_name_ref);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//TODO: find how to substitute with identifiers in grammar (statically)
	final public static HashMap<String, Attribute> allAttributes = new HashMap<String, Attribute>(){
		private static final long serialVersionUID = -4512048801509444272L;
		{
			/*Data object*/
			for (Attribute attr: attrs_dataInput) put("DATA_INPUT_VARIABLES:" + attr.getName(), attr);
			for (Attribute attr: attrs_dataDerived) put("DATA_DERIVED_VARIABLES:" + attr.getName(), attr);
			/*Parameter object*/
			for (Attribute attr: attrs_structural) put("STRUCTURAL:" + attr.getName(), attr);
			for (Attribute attr: attrs_variability) put("VARIABILITY:" + attr.getName(), attr);
			for (Attribute attr: attrs_variability_subblock){
				put("matrix:" + attr.getName(), attr);
				put("diag:" + attr.getName(), attr);
				put("same:" + attr.getName(), attr);
			}
			/*Model object*/
			for (Attribute attr: attrs_inputVariables) put("MODEL_INPUT_VARIABLES:" + attr.getName(), attr);
			for (Attribute attr: attrs_individualVariables) put("INDIVIDUAL_VARIABLES:" + attr.getName(), attr);
			for (Attribute attr: attrs_ode) put("ODE:" + attr.getName(), attr);
			for (Attribute attr: attrs_deq) put("DEQ:" + attr.getName(), attr);
			for (Attribute attr: attrs_estimation) put("ESTIMATION:" + attr.getName(), attr);
			for (Attribute attr: attrs_simulation) put("SIMULATION:" + attr.getName(), attr);
			for (Attribute attr: attrs_observation) put("OBSERVATION:" + attr.getName(), attr);
			for (Attribute attr: attrs_structuralParams) put("STRUCTURAL_PARAMETERS:" + attr.getName(), attr);
			for (Attribute attr: attrs_variabilityParams) put("VARIABILITY_PARAMETERS:" + attr.getName(), attr);
			for (Attribute attr: attrs_randomVars) put("RANDOM_VARIABLE_DEFINITION:" + attr.getName(), attr);
			/*Design object*/
			for (Attribute attr: attrs_studyDesign)    put("STUDY_DESIGN:" + attr.getName(), attr);
			for (Attribute attr: attrs_administration) put("ADMINISTRATION:" + attr.getName(), attr);
			for (Attribute attr: attrs_action)         put("ACTION:" + attr.getName(), attr);
			for (Attribute attr: attrs_sampling)       put("SAMPLING:" + attr.getName(), attr);
			for (Attribute attr: attrs_designSpace)    put("DESIGN_SPACE:" + attr.getName(), attr);
			for (Attribute attr: attrs_hyperSpace)     put("HYPER_SPACE:" + attr.getName(), attr);
		}
	};
	
	public static List<Attribute> getAllAttributes(EObject obj){
		/*Data object*/
		if (obj instanceof DataInputBlockImpl) return attrs_dataInput; 
		if (obj instanceof DataDerivedBlockImpl) return attrs_dataDerived; 
		/*Parameter object*/
		if (obj instanceof StructuralBlockImpl) return attrs_structural;
		if (obj instanceof VariabilityBlockImpl) return attrs_variability;
		if (obj instanceof MatrixBlockImpl || obj instanceof DiagBlockImpl || obj instanceof SameBlockImpl) return attrs_variability_subblock;
		/*Model object*/
		if (obj instanceof InputVariablesBlockImpl) return attrs_inputVariables;
		if (obj instanceof IndividualVariablesBlockImpl) return attrs_individualVariables;
		if (obj instanceof OdeBlockImpl) return attrs_ode; 
		if (obj instanceof DeqBlockImpl) return attrs_deq; 
		if (obj instanceof EstimationBlockImpl) return attrs_estimation; 
		if (obj instanceof SimulationBlockImpl) return attrs_simulation; 
		if (obj instanceof ObservationBlockImpl) return attrs_observation; 
		if (obj instanceof StructuralParametersBlockImpl) return attrs_structuralParams;
		if (obj instanceof VariabilityParametersBlockImpl) return attrs_variabilityParams;
		if (obj instanceof RandomVariableDefinitionBlockImpl) return attrs_randomVars;
		/*Design object*/
		if (obj instanceof StudyDesignBlockImpl) return attrs_studyDesign;  
		if (obj instanceof AdministrationBlockImpl) return attrs_administration;
		if (obj instanceof ActionBlockImpl) return attrs_action;        
		if (obj instanceof SamplingBlockImpl) return attrs_sampling;      
		if (obj instanceof DesignSpaceBlockImpl) return attrs_designSpace;   
		if (obj instanceof HyperSpaceBlockImpl) return attrs_hyperSpace; 
		return null;
	}
	
	public static Attribute getAttributeById(String id){
        return allAttributes.get(id);
    }
	
	//Do not validate arguments in matrix/diag definition and function calls
	private Boolean skipAttributeValidation(EObject container, Arguments args){
		if (container == null) return true;
		//Skip distributions
		if (args.eContainer() instanceof RandomListImpl) return true;
		//Skip nested lists
		if (args.eContainer().eContainer().eContainer() instanceof ArgumentImpl) return true;
		if (isVariabilitySubblock(container, args) || (args.eContainer() instanceof FunctionCallImpl)) return true;		
		return false;
	}

	@Check
	public void checkRequiredArguments(Arguments args){
		//Do not enforce required attributes for blocks to nested lists in this block
		EObject container = findAttributeContainer(args.eContainer());
		if (skipAttributeValidation(container, args)) return;

		String prefix = Utils.getBlockName(container) + ":";		
		List<String> argumentNames = Utils.getArgumentNames(args);	

		//getRequiredAttributes contains lists of required attributes for each container
		for (String attrName: Utils.getRequiredNames(getAllAttributes(container))){
			if (!argumentNames.contains(attrName)) {
				warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, 
				MdlPackage.Literals.ARGUMENTS__ARGUMENTS, MSG_ATTRIBUTE_MISSING, prefix + attrName);
			}
		}
	}
	
	@Check
	public void checkAllArguments(Argument argument){
		EObject argsContainer = argument.eContainer();
		if (!(argsContainer instanceof ArgumentsImpl)) return;
		EObject container = findAttributeContainer(argsContainer.eContainer());
		Arguments args = (Arguments)argsContainer;
		if (skipAttributeValidation(container, args)) return;

		//Check that the attribute name is valid
		List<Attribute> knownAttributes = getAllAttributes(container);
		if (knownAttributes != null){
			List<String> attributeNames = new ArrayList<String>();
			attributeNames.addAll(Utils.getAllNames(knownAttributes));
			//for categorical values, recognise user defined categories as attributes
			if (container instanceof DataInputBlockImpl){
				EObject parentArgsContainer = argsContainer;
				if (args.eContainer() instanceof ListImpl){
					//Arguments -> List -> AnyExpression -> Argument
					if (args.eContainer().eContainer().eContainer() instanceof ArgumentImpl)
						parentArgsContainer = args.eContainer().eContainer().eContainer().eContainer();
				}
				if (parentArgsContainer instanceof ArgumentsImpl){
					Arguments parentArgs = (Arguments) parentArgsContainer;
					List<String> categoricalNames = getCategoricalNames(parentArgs);
					attributeNames.addAll(categoricalNames);
				}
			}
			if (!attributeNames.contains(argument.getArgumentName().getName())){
				warning(MSG_ATTRIBUTE_UNKNOWN + ": " + argument.getArgumentName().getName(), 
				MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
				MSG_ATTRIBUTE_UNKNOWN, argument.getArgumentName().getName());		
			}
			for (Attribute x: knownAttributes){
				if (x.getName().equals(argument.getArgumentName().getName())){
					boolean isValid = false;
					for (MdlDataType type: x.getTypes())
						isValid = isValid || MdlDataType.validateType(type, argument.getExpression());
					if (!isValid){
						warning(MSG_ATTRIBUTE_WRONG_TYPE + 
							": attribute \"" + argument.getArgumentName().getName() + "\" expects value of type " + 
								Utils.printList(x.getTypeNames()), 
							MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
							MSG_ATTRIBUTE_WRONG_TYPE, argument.getArgumentName().getName());
					}
				}
			}
		}
		//Check that each attribute is defined once
		HashSet<String> argumentNames = new HashSet<String>();	
		for (Argument arg: args.getArguments()){
			if (!argumentNames.contains(arg.getArgumentName().getName())){
				argumentNames.add(arg.getArgumentName().getName());
			} else {
				warning(MSG_ATTRIBUTE_DEFINED + ": " + arg.getArgumentName().getName(), 
						MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME, MSG_ATTRIBUTE_DEFINED, 
						arg.getArgumentName().getName());				
			}
		}
	}
	

	private List<String> getCategoricalNames(Arguments parentArgs){
		List<String> categoricalNames = new ArrayList<String>();
		for (Argument parentArg: parentArgs.getArguments()){
			if (parentArg.getArgumentName() != null){
				if (parentArg.getArgumentName().getName().equals(attr_req_type.getName())){
					if (parentArg.getExpression().getType() != null && 
						parentArg.getExpression().getType().getType() != null &&
						parentArg.getExpression().getType().getType().getCategorical() != null){
						if (parentArg.getExpression().getType().getType().getArguments() != null){
							Utils.addSymbol(categoricalNames, parentArg.getExpression().getType().getType().getArguments());
						}	
					}
				}
			}
		}
		return categoricalNames;
	}
	
	//Do not validate parameters of variability subblocks
	private Boolean isVariabilitySubblock(EObject container, Arguments args){
		//Exclude content of Diag and Matrix check from attribute checks
		if (container instanceof MatrixBlockImpl){
			MatrixBlockImpl block = (MatrixBlockImpl)container;
			if (block.getParameters().equals(args)) return true;
		} 
		if(container instanceof DiagBlockImpl){
			DiagBlockImpl block = (DiagBlockImpl)container;
			if (block.getParameters().equals(args)) return true;
		}	
		return false;
	}
	
	//Look for the parent block containing lists with attributes
	public static EObject findAttributeContainer(EObject obj){
		EObject container = obj;
		while (!isAttributeContainer(container)){
			if (container instanceof MclObjectImpl) return null;
			container = container.eContainer();
		}
		return container;
	}	
	
	public static Boolean isAttributeContainer(EObject obj){
		return (
			//Data object	
			obj instanceof DataInputBlockImpl ||
			obj instanceof DataDerivedBlockImpl ||
			//Model object
			obj instanceof InputVariablesBlockImpl ||
			obj instanceof IndividualVariablesBlockImpl ||
			obj instanceof LibraryBlockImpl ||
			obj instanceof OdeBlockImpl ||
			obj instanceof DeqBlockImpl ||
			obj instanceof CompartmentBlockImpl ||
			obj instanceof EstimationBlockImpl ||
			obj instanceof SimulationBlockImpl ||
			obj instanceof ObservationBlockImpl ||
			obj instanceof StructuralParametersBlockImpl || 
			obj instanceof VariabilityParametersBlockImpl ||
			obj instanceof RandomVariableDefinitionBlockImpl ||
			//Parameter object
			obj instanceof StructuralBlockImpl ||
			obj instanceof VariabilityBlockImpl ||
			obj instanceof MatrixBlockImpl || obj instanceof DiagBlockImpl || obj instanceof SameBlockImpl ||
			//Design object
			obj instanceof StudyDesignBlockImpl ||
			obj instanceof AdministrationBlockImpl || 
			obj instanceof ActionBlockImpl ||
			obj instanceof SamplingBlockImpl ||
			obj instanceof DesignSpaceBlockImpl ||
			obj instanceof HyperSpaceBlockImpl);
	}

}

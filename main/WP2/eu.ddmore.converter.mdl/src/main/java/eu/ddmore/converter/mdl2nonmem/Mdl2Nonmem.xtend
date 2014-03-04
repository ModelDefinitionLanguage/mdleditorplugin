/*
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package eu.ddmore.converter.mdl2nonmem

import eu.ddmore.converter.mdlprinting.MdlPrinter
import java.util.ArrayList
import java.util.HashMap
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.ConditionalStatement
import org.ddmore.mdl.mdl.DataBlockStatement
import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.DiagBlock
import org.ddmore.mdl.mdl.EstimateTask
import org.ddmore.mdl.mdl.ExecuteTask
import org.ddmore.mdl.mdl.FileBlock
import org.ddmore.mdl.mdl.FileBlockStatement
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.ImportBlock
import org.ddmore.mdl.mdl.ImportedFunction
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.MatrixBlock
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MixtureBlock
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ModelPredictionBlock
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.ParameterDeclaration
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.SameBlock
import org.ddmore.mdl.mdl.Selector
import org.ddmore.mdl.mdl.SimulateTask
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.TaskObjectBlock

class Mdl2Nonmem extends MdlPrinter{
	
	protected val TARGET = "NMTRAN_CODE";
	protected var Mcl mcl = null;
	
	//Print file name and analyse MCL objects in the source file
  	def convertToNMTRAN(Mcl m){
  		mcl = m;
  		
  		//Prepare external functions  		
  		m.prepareExternals;
  		
  		//Create a map of variables
  		m.prepareCollections;

  		var version = "1.02";
  		var date = "24.08.2013"
  		
  		var java.util.List<DataObject> dataObjects = new ArrayList<DataObject>
  		var java.util.List<TaskObject> taskObjects = new ArrayList<TaskObject>
  		
        for (o:m.objects) {
            if (o.dataObject != null)
                dataObjects.add(o.dataObject);
            if (o.taskObject != null)
                taskObjects.add(o.taskObject);
        }
	
		'''
		;mdl2nt «version» beta, last modification «date», Natallia Kokash (natallia.kokash@gmail.com)  
		«printSIZES»
		«printPROB»
		«FOR d:dataObjects»
			«convertToNMTRAN(d, taskObjects)»
	  	«ENDFOR»
		«FOR t:taskObjects»
			«t.printIGNORE»
	  	«ENDFOR»
		«printABBREVIATED»
		«FOR o:m.objects»
			«IF o.modelObject != null»«o.modelObject.convertToNMTRAN»«ENDIF»
	  	«ENDFOR»
		«printAES»
	  	«FOR o:m.objects»
			«IF o.parameterObject != null»«o.parameterObject.convertToNMTRAN»«ENDIF»
	  	«ENDFOR»
		«FOR o:m.objects»
			«IF o.taskObject != null»«o.taskObject.convertToNMTRAN»«ENDIF»
		«ENDFOR»
		«FOR o:m.objects»
			«IF o.modelObject != null»«o.modelObject.printTABLE»«ENDIF»
	  	«ENDFOR»
		'''
	}
	
////////////////////////////////////	
//convertToNonmem MCL
////////////////////////////////////
	//Print NM-TRAN record $SIZES
	def printSIZES()'''
	«IF "$SIZES".isTargetDefined»
	
	«getExternalCodeStart("$SIZES")»
	«getExternalCodeEnd("$SIZES")»
	«ENDIF»	
	'''

	//Print NM-TRAN record $PROB/$PROBLEM
	def printPROB()'''
	«IF "$PROBLEM".isTargetDefined || "$PROB".isTargetDefined»
	
	«getExternalCodeStart("$PROBLEM")»
	«getExternalCodeStart("$PROB")»
	«getExternalCodeEnd("$PROBLEM")»
	«getExternalCodeEnd("$PROB")»
	«ELSE»
	
	$PROB «mcl.eResource.fileName.toUpperCase»
	«ENDIF»
	'''

	//Print NM-TRAN record $ABB/$ABBREVIATED
	def printABBREVIATED()'''
	«IF "$ABB".isTargetDefined || "$ABBREVIATED".isTargetDefined»

	«getExternalCodeStart("$ABB")»
	«getExternalCodeStart("$ABBREVIATED")»
	«getExternalCodeEnd("$ABB")»
	«getExternalCodeEnd("$ABBREVIATED")»
	«ENDIF»
	'''
	
	//Print NM-TRAN record $AES
	def printAES()'''
	«IF "$AES".isTargetDefined»
	
	«getExternalCodeStart("$AES")»
	«getExternalCodeEnd("$AES")»
	«ENDIF»
	'''

////////////////////////////////////	
//convertToNonmem Model Object
////////////////////////////////////	
	def convertToNMTRAN(ModelObject o){
		val isLibraryDefined = o.isLibraryDefined;
		val isPKDefined = o.isGroupOrIndividualDefined;
		val isErrorDefined = o.isErrorDefined;
		val isODEDefined = o.isODEDefined;
		'''
		«IF isLibraryDefined»
			
			«o.printSUBR(isPKDefined)»
		«ENDIF»
		«o.printMODEL(isODEDefined)»
		«o.generateMODEL»
		«IF isLibraryDefined»

			«o.printPK(isPKDefined)»
			«o.printMIX»
			«o.printERROR(isErrorDefined)»
		«ELSE» 
			«o.printPRED(isPKDefined, isErrorDefined)»
		«ENDIF»
		«o.printDES(isODEDefined)»
		'''
	}
	
	//Print NM-TRAN record $PRED
	def printPRED(ModelObject o, Boolean isPKDefined, Boolean isErrorDefined)'''
	«IF isPKDefined || isErrorDefined»		
	
	$PRED
	«ENDIF»
	«getExternalCodeStart("$PRED")»
	«o.printPKContent»
	«o.printMIXContent»
	«o.printErrorContent»
	«getExternalCodeEnd("$PRED")»
	'''

	//Print NM-TRAN  record $PK
	def printPK(ModelObject o, Boolean isPKDefined)'''
	«IF isPKDefined»
	
	$PK
	«ENDIF»
	«getExternalCodeStart("$PK")»
	«o.printPKContent»
	«getExternalCodeEnd("$PK")»
	'''
	
	//Processing GROUP_VARIABLES, INDIVIDUAL_VARIABLES, MODEL_PREDICTION (init conditions) for $PK
	def printPKContent(ModelObject o)'''
	«FOR b:o.blocks»
		«IF	b.groupVariablesBlock != null»
			«FOR st: b.groupVariablesBlock.statements»
				«IF st.statement != null»
					«st.statement.print»
				«ENDIF»
			«ENDFOR»
		«ENDIF»
		«IF b.individualVariablesBlock != null»
			«FOR s: b.individualVariablesBlock.statements SEPARATOR ' '»
				«s.print»
			«ENDFOR»
		«ENDIF»
		«IF b.modelPredictionBlock != null»		
			«IF init_vars.entrySet.size > 0»

				;initial conditions
				«FOR e: init_vars.entrySet»
					A_0(«e.key») = «e.value»
				«ENDFOR»
			«ENDIF»
		«ENDIF»
	«ENDFOR»
	'''
	
	//Print NM-TRAN record $MIX 
	def printMIX(ModelObject o)'''
	«IF o.isMixDefined»
	
	$MIX
	«ENDIF»
	«getExternalCodeStart("$MIX")»
	«getExternalCodeStart("$MIXTURE")»
	«o.printMIXContent»
	«getExternalCodeEnd("$MIX")»
	«getExternalCodeEnd("$MIXTURE")»
	'''

	def printMIXContent(ModelObject o)'''
	«FOR b:o.blocks»
		«IF	b.groupVariablesBlock != null»
			«FOR st: b.groupVariablesBlock.statements»
				«IF st.mixtureBlock != null»
					«st.mixtureBlock.print»
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	«ENDFOR»
	'''
	
	//Print block
	def print(MixtureBlock b){
		var nspop = 0;
		for (BlockStatement st: b.statements){
			if (st.symbol != null){
				if (st.symbol.expression.list != null){
					nspop = st.symbol.expression.list.arguments.arguments.size;
				}
			}
		}		
		var res = "";
		if (nspop > 0){
			res  = "NSPOP = " + nspop + "\n";
			var i = 1;
			for (BlockStatement st: b.statements){
				if (st.symbol != null){
					if (st.symbol.expression.expression != null){
						res = res + "P(" + i + ") = " + st.symbol.expression.expression.toStr + "\n";
						i = i + 1;
					}
				}
			}		
		}
		'''«res»'''
	}
	
	//Print NM-TRAN record $ERROR
	def printERROR(ModelObject o, Boolean isErrorDefined)'''
	«IF isErrorDefined»	
	
	$ERROR
	«ENDIF»
	«getExternalCodeStart("$ERROR")»
	«o.printErrorContent»
	«getExternalCodeEnd("$ERROR")»
	'''	
	
	//Processing MODEL_PREDICTION, OBSERVATION for $ERROR
	def printErrorContent(ModelObject o)'''
		«FOR mob:o.blocks»
			«IF mob.modelPredictionBlock != null»
				«FOR s: mob.modelPredictionBlock.statements»
					«IF s.statement != null»
						«s.statement.print»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
			«IF mob.observationBlock != null»
				«mob.observationBlock.print»
			«ENDIF»
			«IF mob.simulationBlock != null»
				«mob.simulationBlock.print»
			«ENDIF»
			«IF mob.estimationBlock != null»
				«mob.estimationBlock.print»
			«ENDIF»			
		«ENDFOR»
	'''
	
	//If there is a definition with ncmt=N, define N compartment names
	def generateMODEL(ModelObject o){
		var res = "";
		var nmct =  o.numberOfCompartments;		
		//return '''Testing: «nmct»''';		
		if (nmct > 0) {
			res = res + "\n$MODEL\n";
			for (i : 1 ..nmct) {
 			   res = res + "COMP (comp" + i + ")\n"
			}
		}
		'''«res»'''
	}

	//Processing MODEL_PREDICTION for $MODEL
	def printMODEL(ModelObject o, Boolean isODEDefined)'''
	«IF isODEDefined»
	
	$MODEL
	«ENDIF»
	«getExternalCodeStart("$MODEL")»
	«FOR b:o.blocks»
		«IF b.modelPredictionBlock != null»
			«var bb = b.modelPredictionBlock»
			«FOR s: bb.statements»
				«IF s.odeBlock != null»
					«FOR ss: s.odeBlock.statements»
						«var x = ss.symbol»
						«IF x != null»
							«IF x.expression != null»
								«IF x.expression.odeList != null»
									COMP(«x.identifier»)
								«ENDIF»
							«ENDIF»
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	«ENDFOR»
	«getExternalCodeEnd("$MODEL")»
	'''

	//Processing MODEL_PREDICTION for $DES
	def printDES(ModelObject o, Boolean isODEDefined)'''
	«IF isODEDefined»

	$DES
	«ENDIF»
	«getExternalCodeStart("$DES")»
	«FOR b:o.blocks»
		«IF b.modelPredictionBlock != null»
			«var bb = b.modelPredictionBlock»
			«FOR s: bb.statements»
				«IF s.odeBlock != null»
					«FOR ss: s.odeBlock.statements»
						«var x = ss.symbol»
						«IF x != null»
							«IF x.expression != null»
								«IF x.expression.expression != null»
									«x.print»
								«ENDIF»
								«IF x.expression.odeList != null»
									«var deriv = x.expression.odeList.arguments.getAttribute("deriv")»
									«IF !deriv.equals("")»
										«var id = x.identifier»
										«IF dadt_vars.get(id) != null»
											DADT(«dadt_vars.get(id)») = «deriv»
										«ENDIF»	
									«ENDIF»
								«ENDIF»
							«ENDIF»
						«ENDIF»
						«IF ss.statement != null»
							«ss.statement.print»
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	«ENDFOR»
	«getExternalCodeEnd("$DES")»
	'''    
    
	//Processing MODEL_PREDICTION for $SUBR
	def printSUBR(ModelObject o, Boolean isPKDefined)'''
	«IF isPKDefined»

	$SUBR
	«ENDIF»
	«getExternalCodeStart("$SUBR")»
	«getExternalCodeStart("$SUBROUTINE")»
	«FOR b:o.blocks»
		«IF b.modelPredictionBlock != null»
			«b.modelPredictionBlock.printSUBR»
		«ENDIF»
	«ENDFOR»
	«getExternalCodeEnd("$SUBR")»
	«getExternalCodeEnd("$SUBROUTINE")»
    ''' 
    
    //Processing MODEL_PREDICTION for $SUBR
    //Find an imported function name and attributes "model", "trans"
	def printSUBR(ModelPredictionBlock b){
		for (ss: b.statements){
			if (ss.libraryBlock != null){
				for (st: ss.libraryBlock.statements){
					var libraryRef = st.expression.identifier;
					var attributes = libraryRef.getExternalFunctionAttributes();
					var library = libraryRef.identifier;
					if (attributes != null){
						var name = attributes.get("name");
						if (name != null) library = name;
					}
					val model = st.expression.arguments.getAttribute("model");
					val trans = st.expression.arguments.getAttribute("trans");
					val tol = getTOL;
					return '''«IF !model.equals("")»«library.toUpperCase()»«model»«ENDIF» «IF !trans.equals("")»TRANS«trans»«ENDIF» «IF !tol.equals("")»TOL = «tol»«ENDIF»'''
				}
			}
		}
	}
		
	//Print NM-TRAN record $TABLE
	def printTABLE(ModelObject o)'''
	«IF o.isOutputVariablesDefined»

	$TABLE 
	«ENDIF»	
	«getExternalCodeStart("$TABLE")»
	«FOR b:o.blocks»
		«IF b.outputVariablesBlock != null»
			«var bb = b.outputVariablesBlock»
			«IF bb.variables.size > 0»
				«FOR st: bb.variables SEPARATOR ' '»«st.toStr»«ENDFOR»
				«val file = getTaskObjectName»
				ONEHEADER NOPRINT «IF !file.equals("")»FILE=«file».fit«ENDIF» 
			«ENDIF»
		«ENDIF»	
	«ENDFOR»
	«getExternalCodeEnd("$TABLE")»
	'''
	
 
////////////////////////////////////
//convertToNonmem PARAMETER OBJECT
/////////////////////////////////////		
	def convertToNMTRAN(ParameterObject obj){
	obj.collectDimensionsForSame;	
	'''
	«obj.printPRIOR»
	«obj.printTHETA»
	«obj.printOMEGA»
	«obj.printSIGMA»
	'''
	}

	//Copy statements from PRIOR block to $PRIOR
	def printPRIOR(ParameterObject obj)'''
	«IF obj.isPriorDefined»
	
	$PRIOR
	«ENDIF»	
	«getExternalCodeStart("$PRIOR")»
	«FOR b:obj.blocks»			
		«IF b.priorBlock != null»
			«FOR st: b.priorBlock.statements»
				«st.print»
			«ENDFOR»
		«ENDIF»
	«ENDFOR»
	«getExternalCodeEnd("$PRIOR")»
	'''
		
	//Processing STRUCTURAL for $THETA
	def printTHETA(ParameterObject obj)'''
	«IF obj.isStructuralDefined»

	$THETA
	«ENDIF»
	«getExternalCodeStart("$THETA")»
	«FOR b:obj.blocks»			
		«IF b.structuralBlock != null»
			«FOR st: b.structuralBlock.parameters»
				«st.printTheta»
			«ENDFOR»
		«ENDIF»
	«ENDFOR»
	«getExternalCodeEnd("$THETA")»
	«IF "$THETAI".isTargetDefined»
	
	«externalCodeStart.get("$THETAI")»
	«externalCodeEnd.get("$THETAI")»
	«ENDIF»
	«IF "$THETAR".isTargetDefined»
	
	«externalCodeStart.get("$THETAR")»
	«externalCodeEnd.get("$THETAR")»
	«ENDIF»
	'''
	
	//Processing VARIABILITY for $OMEGA
	def printOMEGA(ParameterObject obj)'''
	«externalCodeStart.get("$OMEGA")»
	«obj.printVariabilityBlock("$OMEGA")»
	«externalCodeEnd.get("$OMEGA")»
	'''
	
	//Processing VARIABILITY for $SIGMA
	def printSIGMA(ParameterObject obj)'''
	«getExternalCodeStart("$SIGMA")»
	«obj.printVariabilityBlock("$SIGMA")»
	«getExternalCodeEnd("$SIGMA")»
	'''
	
	//Return VARIABILITY block statements for $SIGMA or $OMEGA
	def printVariabilityBlock(ParameterObject obj, String section){
		var res = "";
		for (b:obj.blocks){
			if (b.variabilityBlock != null){
				var printSectionName = true;
				for (c: b.variabilityBlock.statements){
					if (c.parameter != null){
						var tmp = c.parameter.printVariabilityParameter(section);
						if (!tmp.equals("")){
							if (printSectionName){
								res = res + "\n" + section + "\n";
								printSectionName = false;
							}
							res = res + tmp;
						}
					}
					if (c.diagBlock != null){
						res  = res + c.diagBlock.printDiag(section);
						printSectionName = true;
					}
					if (c.matrixBlock != null){
						res  = res + c.matrixBlock.printMatrix(section);
						printSectionName = true;
					}
					if (c.sameBlock != null){
						res  = res + c.sameBlock.printSame(section);
						printSectionName = true;
					}
				}
			}
		}
		return res;
	}
	
	//$OMEGA BLOCK(dim) SAME ; varName
	//$SIGMA BLOCK(dim) SAME ; varName
	def printSame(SameBlock b, String section) { 
		var name = b.arguments.getAttribute("name");
		if (name.equals("")) return '''''';
		val isOmega = section.equals("$OMEGA") && (namedOmegaBlocks.get(name) != null);
		val isSigma = section.equals("$SIGMA") && (namedSigmaBlocks.get(name) != null);
		if (isOmega || isSigma)	{			
			var k = 0;
			if (isOmega) k = namedOmegaBlocks.get(name);
			if (isSigma) k = namedSigmaBlocks.get(name);
			return 
			'''
			
			«section» «IF k > 0»BLOCK («k») SAME«ENDIF»
			«IF b.parameters != null»
				«FOR p: b.parameters.arguments»
					; «p.identifier»
				«ENDFOR»
			«ENDIF»
			'''
		}
		return "";
	}	
	
	//Create maps with dimensions for same blocks
	def collectDimensionsForSame(ParameterObject obj){
		for (b:obj.blocks){
			if (b.variabilityBlock != null){
				for (c: b.variabilityBlock.statements){
					if (c.diagBlock != null)
						c.diagBlock.collectDimensionsForSame;
					if (c.matrixBlock != null)
						c.matrixBlock.collectDimensionsForSame;
				}
			}
		}
	}
	
	//Create maps with dimensions for same blocks corresponding to diag(...){...}
	def collectDimensionsForSame(DiagBlock b)
	{
		var k = 0; 
		var name = b.arguments.getAttribute("name");
		var isOmega = false;
		var isSigma = false;
		if (name != null){
			if (b.parameters != null)		
				for (p: b.parameters.arguments) {
					if (p.expression != null){
						k = k + 1;
						if (p.identifier != null){
							if (eta_vars.get("eta_" + p.identifier) != null) isOmega = true;
							if (eps_vars.get("eps_" + p.identifier) != null) isSigma = true;
						} 
					}
				}		
			if (isOmega) namedOmegaBlocks.put(name, k);
			if (isSigma) namedSigmaBlocks.put(name, k);
		}
	}
	
	//Create maps with dimensions for same blocks corresponding to matrix(...){...}
	def collectDimensionsForSame(MatrixBlock b)
	{
		var k = 0;
		var name = b.arguments.getAttribute("name");
		var isOmega = false;
		var isSigma = false;
		if (b.parameters != null)
			for (p: b.parameters.arguments) {
				if (p.expression != null){
					if (p.identifier != null){
						if (eta_vars.get("eta_" + p.identifier) != null) isOmega = true;
						if (eps_vars.get("eps_" + p.identifier) != null) isSigma = true;
						k = k + 1;
					}
				}
			}
		if (isOmega) namedOmegaBlocks.put(name, k);
		if (isSigma) namedSigmaBlocks.put(name, k);
	}

	//Print diag(...){...} subblock of VARIABILITY
	def printDiag(DiagBlock b, String section)
	{
		var result = "";
		var printFix = false;
		var k = 0; 
		for (a: b.arguments.arguments){
			if (a.identifier != null){ 
				if (a.identifier.equals("fix")){ 
					if (a.expression != null){
						printFix = a.expression.isTrue	
					}
				}				
			}
		}	
		if (b.parameters != null)		
			for (p: b.parameters.arguments) {
				if (p.expression != null){
					var  i = 0;
					var tmpRes = "";
					while (i < k){
						tmpRes = tmpRes + "0 ";
						i = i + 1;
					}
					k = k + 1;
					if (p.identifier != null){
						val isOmega = section.equals("$OMEGA") && (eta_vars.get("eta_" + p.identifier) != null);
						val isSigma = section.equals("$SIGMA") && (eps_vars.get("eps_" + p.identifier) != null);
						if (isOmega || isSigma)	{
							result = result + tmpRes + p.expression.toStr + " ";
							result = result + "; " + p.identifier + "\n";
						}
					} 
					else
						if (!result.equals("")) result = result + p.expression.toStr + " ";
				}
			}		
		if (printFix && !result.equals("")) result = result + "FIX\n";
		if (result.equals("")) return "";		
		return
		'''
		
		«section» «IF k > 0»BLOCK («k») «ENDIF»
		«result»
		'''
	}
	
	//Print matrix(...){...} subblock of VARIABILITY
	def printMatrix(MatrixBlock b, String section)
	{
		var result = "";
		var printFix = false;
		var k = 0; 
		for (a: b.arguments.arguments){
			if (a.identifier != null){ 
				if (a.identifier.equals("fix")) 
					if (a.expression != null){
						printFix = a.expression.isTrue		
					}
			}
		}
		if (b.parameters != null)
			for (p: b.parameters.arguments) {
				if (p.expression != null){
					if (p.identifier != null){
						val isOmega = section.equals("$OMEGA") && (eta_vars.get("eta_" + p.identifier) != null);
						val isSigma = section.equals("$SIGMA") && (eps_vars.get("eps_" + p.identifier) != null);
						if (isOmega || isSigma)	{
							result = result + p.expression.toStr + " ";
							result = result + "; " + p.identifier + "\n";
							k = k + 1;
						}
					} 
					else
						if (!result.equals("")) result = result + p.expression.toStr + " ";
				}
			}
		if (printFix && !result.equals("")) result = result + "FIX\n";
		if (result.equals("")) return "";
		return 
		'''
		
		«section» «IF k > 0»BLOCK («k») «ENDIF»
		«result»
		'''; 
	}
	

	//Print VARIABILITY parameter in $SIGMA or $OMEGA
	def printVariabilityParameter(ParameterDeclaration s, String section){
		var name = s.identifier;
		//SIGMA <=> EPS, OMEGA <=> ETA	
		var isOmega = (section.equals("$OMEGA") && eta_vars.get("eta_" + name) != null);
		var isSigma = (section.equals("$SIGMA") && eps_vars.get("eps_" + name) != null);
		if (isOmega || isSigma)
		{
			val value = s.list.arguments.getAttribute("value");
			val printFix = s.list.arguments.isAttributeTrue("fix");
			return							
			'''
			«IF !value.equals("")»«value»«IF printFix» FIX«ENDIF»«ENDIF» ; «name»
			'''
		}
		return "";
	}
	
	
	//Find attributes in STRUCTURAL_VARIABLES and form an NMTRAN statement
	def printTheta(ParameterDeclaration s){
		if (s.list != null){		
			var name = s.identifier;
			val value = s.list.arguments.getAttribute("value");
			val lo = s.list.arguments.getAttribute("lo");
			val hi = s.list.arguments.getAttribute("hi");
			val printFix = s.list.arguments.isAttributeTrue("fix");
			if (value.equals("")) return "";
			if (lo.equals("") && hi.equals("")) return '''«value»«IF printFix» FIX«ENDIF» ; «name»'''
			if (lo.equals("")) return '''(-INF, «value», «hi»)«IF printFix» FIX«ENDIF» ; «name»'''
			if (hi.equals("")) return '''(«lo», «value», INF)«IF printFix» FIX«ENDIF» ; «name»'''
			return '''(«lo», «value», «hi»)«IF printFix» FIX«ENDIF» ; «name»'''
		}
	}	
	
////////////////////////////////////	
//convertToNonmem DATA OBJECT
////////////////////////////////////
	def convertToNMTRAN(DataObject d, java.util.List<TaskObject> t)'''
	«printINPUT(d,t)»
	«d.printDATA»
	'''

	//Print NM-TRAN record $INPUT
	def printINPUT(DataObject d, java.util.List<TaskObject> t)'''
	«IF d.isHeaderDefined»
		
	$INPUT
	«ENDIF»
	«getExternalCodeStart("$INPUT")»
	«FOR b:d.blocks»
	    «IF b.headerBlock != null»
			«FOR st: b.headerBlock.variables SEPARATOR ' '»«IF isDrop(st.identifier, t)»«st.identifier»=DROP«ELSE»«st.identifier»«ENDIF»«ENDFOR»
        «ENDIF»
	«ENDFOR»
	«getExternalCodeEnd("$INPUT")»
	'''
	
	//Note: We drop now all the variables in the specification regardless of the object they refer to
    def isDrop(String id, java.util.List<TaskObject> t) {
        for (TaskObject tObj :t) {
            for (b: tObj.blocks) {
                if (b.dataBlock !=  null) {
                    for (DataBlockStatement block: b.dataBlock.statements) {
                        if (block.dropList != null) {
                            for (FullyQualifiedSymbolName symbol : block.dropList.list.symbols) {
                                if (id.equals(symbol.identifier))
                                    return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
	
	//Print NM-TRAN record $DATA
	def printDATA(DataObject o)'''
	«FOR b:o.blocks»
		«IF b.fileBlock != null» 
			«var data = b.fileBlock.getData»
			«IF !data.equals("")»
			
			$DATA «data»
			«ENDIF»
			«getExternalCodeStart("$DATA")»
			«FOR s: b.fileBlock.statements»
				«s.printIGNORE»
			«ENDFOR»
			«getExternalCodeEnd("$DATA")»
		«ENDIF»
	«ENDFOR»
	'''
	
	//if (st.inlineBlock != null) st.inlineBlock.convertToNonmem
	//if (st.designBlock != null) st.designBlock.convertToNonmem
	//if (st.rscriptBlock != null) st.rscriptBlock.convertToNonmem
	
	//Processing FILE block statement for $DATA
	def printIGNORE(FileBlockStatement s){
		if (s.variable != null){
			if (s.variable.identifier.equals("data")){
				if (s.variable.expression != null){
					if (s.variable.expression.list != null){
						var ignore = s.variable.expression.list.arguments.getAttribute("ignore");
						return '''«IF !ignore.equals("")»IGNORE=«ignore»«ENDIF»'''
					}
				}
			}
		}
	}
	
	def getData(FileBlock b){
		for (s: b.statements){
			if (s.variable.identifier.equals("data")){
				if (s.variable.expression != null){
					if (s.variable.expression.list != null){
						var data = s.variable.expression.list.arguments.getAttribute("source");
						return data;
					}
				}
			}
		}
	}
	
	
/////////////////////////////////////////////////
//convertToNonmem TASK OBJECT
/////////////////////////////////////////////////

	//Processing task object for $EST and $SIM
	def convertToNMTRAN(TaskObject o)'''
	«FOR b:o.blocks»
	«IF b.functionDeclaration != null»
		«val body = b.functionDeclaration.functionBody»
		«IF body != null»
			«FOR bb: body.blocks»
			«IF bb.estimateBlock != null»
				«bb.estimateBlock.print»
			«ENDIF»
			«IF bb.simulateBlock != null»
				«bb.simulateBlock.print»
			«ENDIF»
			«IF bb.executeBlock != null»
				«bb.executeBlock.print»
			«ENDIF»
			«ENDFOR»
		«ENDIF»
	«ENDIF»
	«ENDFOR»
	'''
	
	//Print NM-TRAN IGNORE=... statements
	def printIGNORE(TaskObject o)'''
	«FOR b: o.blocks»
		«IF b.dataBlock !=  null»
			«FOR DataBlockStatement block: b.dataBlock.statements»
				«IF block.ignoreList != null»
					«block.ignoreList.identifier» («block.ignoreList.expression.toCommaSeparatedStr»)
				«ENDIF»
			«ENDFOR»
		«ENDIF»
	«ENDFOR»
	''' 
	
	//Processing SIMULATE block for $SIM 
	def print(SimulateTask b)'''
		«var isInlineTargetDefined = TARGET.isInlineTargetDefined(b.statements)»
		«IF !isInlineTargetDefined»
		
		$SIM 
		«ENDIF»
		«getExternalCodeStart("$SIM")»
		«getExternalCodeStart("$SIMULATION")»
		«IF !isInlineTargetDefined»
			«FOR s: b.statements»
				«IF s.symbol != null»«s.symbol.printDefaultSimulate»«ENDIF»
			«ENDFOR»
			NOABORT
		«ELSE»
			«FOR s: b.statements»
				«IF s.targetBlock != null»«s.targetBlock.print»«ENDIF»
			«ENDFOR»
		«ENDIF» 
		«getExternalCodeEnd("$SIM")»
		«getExternalCodeEnd("$SIMULATION")»	
	'''
	
	//Processing ESTIMATE block for $EST
	def print(EstimateTask b)'''
		«var isInlineTargetDefined = TARGET.isInlineTargetDefined(b.statements)»
		«IF !isInlineTargetDefined»
		
		$EST 
		«ENDIF»
		«getExternalCodeStart("$EST")»
		«getExternalCodeStart("$ESTIMATION")»
		«IF !isInlineTargetDefined»
			«FOR s: b.statements»
				«IF s.symbol != null»«s.symbol.printDefaultEstimate»«ENDIF»
			«ENDFOR»
			NOABORT 
			«b.printCovariance»
		«ELSE»
			«FOR s: b.statements»
				«IF s.targetBlock != null»«s.targetBlock.print»«ENDIF»
			«ENDFOR»
		«ENDIF»
		«getExternalCodeEnd("$EST")»
		«getExternalCodeEnd("$ESTIMATION")»
	'''
	
	//Print attributes for default $EST record
	def printDefaultEstimate(SymbolDeclaration s) { 
		if (s.identifier.equals("algo")){
			if (s.expression.expression != null)
				''' METHOD=«s.expression.expression.toStr»'''
			else {
				//print first attribute of the list!?
				if (s.expression.list != null){
					var args = s.expression.list.arguments;
					if (args != null){
						if (args.arguments.size > 0)
							''' METHOD=«args.arguments.get(0).expression.toStr»'''
					}
				}
			}	
		}
		else
			if (s.identifier.equals("max"))
			''' MAX=«s.expression.print»'''
		else
			if (s.identifier.equals("sig"))
			''' SIG=«s.expression.print»'''
	}
	
	//Print attributes for default $SIM record
	def printDefaultSimulate(SymbolDeclaration s)'''
	'''
	
	//Print $COV 
	def printCovariance(EstimateTask b)'''
	«IF b.isCovarianceDefined»
	
	$COV 
	«ENDIF»
	«getExternalCodeStart("$COV")»
	«getExternalCodeStart("$COVARIANCE")»
	«FOR s: b.statements»«IF s.symbol != null»«s.symbol.printCovariance»«ENDIF»«ENDFOR»
	«getExternalCodeEnd("$COV")»
	«getExternalCodeEnd("$COVARIANCE")»
	'''	
	
	//Print "cov" attribute for $COVARIATE record 
	def printCovariance(SymbolDeclaration s) { 
		if (s.identifier.equals("cov")){
			if (s.expression != null){
				if (s.expression.toStr.replaceAll("\\s","").equals(""))
				'''«s.expression.print»'''
			}
		}
	}

	
	def print(ExecuteTask b)'''
	
	«FOR s: b.statements»
		«IF s.targetBlock != null»«s.targetBlock.print»«ENDIF»
	«ENDFOR»
	'''
		
	//Get task object name 
	def getTaskObjectName(){
		for (obj: mcl.objects){
  			if (obj.taskObject != null)
  				return obj.identifier.name;
  		}
		return "";
	}
	
	//Get $TOL attribute
	def getTOL(){
		for (obj: mcl.objects){
  			if (obj.taskObject != null){
  				val tol = obj.taskObject.getTOL;
				if (tol.length > 0) return tol;
  			}
  		}
		return "";
	}
	
	//Get $TOL attribute
	def getTOL(TaskObject obj){
		for (TaskObjectBlock b: obj.blocks){
			if (b.modelBlock != null){
				for (ss: b.modelBlock.statements){
					var x = ss.statement.symbol;
					if (x != null){
						if (x.identifier.equals("tolrel")){
							if (x.expression != null)
								return x.expression.toStr;
						}
					}
				}
			}
		}
		return "";
	}	
	
	///////////////////////////////////////////////////////////////////////
	//Overridden converter functions
	///////////////////////////////////////////////////////////////////////
	
	//Convert variable names to NM-TRAN versions
	override convertID(String id){
		if (id.indexOf('_') > 0){			

			if (eta_vars.get(id) != null){
				return "ETA(" + eta_vars.get(id) + ")";
			}
			if (eps_vars.get(id) != null){
				return "EPS(" + eps_vars.get(id) + ")";
			}
			if (theta_vars.get(id) != null){
				return "THETA(" + theta_vars.get(id) + ")"; 
			}
		}
		if (dadt_vars.get(id) != null){
			return "A(" + dadt_vars.get(id) + ")"; 
		}
		if (id.equalsIgnoreCase("ln"))
			return "LOG";
		return id.toUpperCase();	
	}	
	
	//Override MDL operators with NM-TRAN operators
	override convertOperator(String op){
		if (op.equals("<")) return ".LT.";
		if (op.equals(">")) return ".GT.";
		if (op.equals("<=")) return ".LE.";
		if (op.equals(">=")) return ".GE.";
		if (op.equals("==")) return ".EQ.";
		if (op.equals("!=")) return ".NE.";		
		if (op.equals("^")) return "**";
		if (op.equals("||")) return ".OR.";
		if (op.equals("&&")) return ".AND.";
		return op;	
	}
	
	//This is needed because of a bug in NONMEM x||y -> x, y for IGNORE statement
	//toStr OR expression
	def toCommaSeparatedStr(OrExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		if (iterator.hasNext ) res = iterator.next.toStr;
		while (iterator.hasNext) res  = res + ', ' + iterator.next.toStr;	
		return res;
	}
	
	//This is needed because of a bug in NONMEM x&&y -> x, y for ACCEPT statement
	//toStr AND expression
	def toCommaSeparatedStr(AndExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		if (iterator.hasNext ) res = iterator.next.toStr;
		while (iterator.hasNext)
			res  = res + ', ' + iterator.next.toStr;
		return res;	
	}
	
	override print(TargetBlock b){
		var target = "";
		if (b.arguments != null) target = b.arguments.getAttribute("target");
		if (target.equals(TARGET)) {
		'''
		«var printedCode = b.externalCode.substring(3, b.externalCode.length - 3)»
		«printedCode»
		'''
		}
	}
		
	//Override statement printing to substitute MDL conditional operators with NM-TRAN operators
	override print(ConditionalStatement s)'''
	«IF s.expression != null»
		IF «s.expression.print» THEN
			«IF s.ifStatement != null»
				«s.ifStatement.print»
			«ENDIF»
			«IF s.ifBlock != null»
				«s.ifBlock.print»
			«ENDIF»
		«IF s.elseStatement != null || s.elseBlock != null»
		ELSE 
			«IF s.elseStatement != null»
				«s.elseStatement.print»
			«ENDIF»
			«IF s.elseBlock != null»
				«s.elseBlock.print»
			«ENDIF»
		«ENDIF»
		ENDIF
	«ENDIF»
	'''
	
	//(==, !=, <, > etc.) - here we skip boolean values!
	override toStr(LogicalExpression e){
		if (e.boolean != null) 	return "";
		return super.toStr(e);
	}				
	
	//Print variableDeclaration substituting ID with "Y" if it is a list with LIKELIHOOD or continuous type
	override toStr(SymbolDeclaration v){
		if (v.expression != null){
			if (v.expression.list != null){
				var type = v.expression.list.arguments.getAttribute("type");
				var res = "";
				if (type.equals("continuous")){
					res = "F_FLAG = 0\n" 
				}	
				if (type.equals("LIKELIHOOD")){
					res = "F_FLAG = 1\n"	
				}		
				//substitute variable name with Y
				var listExpr  = v.expression.list.toStr;
				if (!listExpr.equals("") && !res.equals("")){
					return res + "Y = " + listExpr + "\n"; 	
				}
			}
		}
		return super.toStr(v);
	}
	
	//Instead of list(...) we print an expression from a certain attribute (depends on the type)
	override toStr(List l){		
		var type = l.arguments.getAttribute("type");
		var res = "";
		if (type.equals("LIKELIHOOD")){
			res = l.arguments.getAttribute("likelihood");
		} else if (type.equals("continuous")){
			var ruv = l.arguments.getAttribute("ruv");
			var prediction = l.arguments.getAttribute("prediction")
			res = prediction + ruv
		}			
		return res;
	}

	//Prepare a list of external function declarations to define their NMTRAN names 
	 override void prepareExternalFunctions(ImportBlock b, String objName){
		for (ImportedFunction f: b.functions){
			var args = new HashMap<String, String>();
			var target = f.list.arguments.getAttribute("target");
		 	if (target != null){ 
				if (target.equals(TARGET)) {
					for (Argument arg: f.list.arguments.arguments){
						if (arg.identifier != null)
							args.put(arg.identifier, arg.expression.toStr)
					}
					externalFunctions.put(objName + "$" + f.identifier, args);
				}
			}
		}
	}	
	
	 //Prepare a map of section with corresponding target blocks
	 override void prepareExternalCode(TargetBlock b){
		val target = b.arguments.getAttribute("target");
		if (target != null){ 
			if (target.equals(TARGET)) {
				val location = b.arguments.getAttribute("location");
				if (b.arguments.isAttributeTrue("first")){
					var codeSnippets = externalCodeStart.get(location);
					if (codeSnippets == null) codeSnippets = new ArrayList<String>();
					codeSnippets.add(b.toStr);
					externalCodeStart.put(location, codeSnippets);
				} else {
					var codeSnippets = externalCodeEnd.get(location);
					if (codeSnippets == null) codeSnippets = new ArrayList<String>();
					codeSnippets.add(b.toStr);
					externalCodeEnd.put(location, codeSnippets);
				} 
			}
		}
	}	
	
	//References to attributes: skip variable name and replace selectors, e.g,  amount.A[2] -> A(2)
	override toStr(FullyQualifiedArgumentName name) { 
		var res = "";
		for (s: name.selectors){
			res = res + s.toStr
		}
		return res;
	}
	
	//Selectors [] -> (), e.g., A[2] -> A(2)
	override toStr(Selector s) { 
		if (s.identifier != null)
			return s.identifier.identifier;
		if (s.selector != null)
			return "(" + s.selector + ")";
	}	
	
	//toStr function call
	override toStr(FunctionCall call){
		if (call.identifier.toStr.trim.equalsIgnoreCase("errorexit"))
			return "EXIT" + call.arguments.toStrWithoutCommas;
		return super.toStr(call);	
	}
	
	//toStr list arguments without commas
	def toStrWithoutCommas(Arguments arg){
		var res  = "";
		var iterator = arg.arguments.iterator();
		while (iterator.hasNext){
			var a = iterator.next; 
			res = res + " " + a.expression.toStr;
		}
		return res;
	}			
}
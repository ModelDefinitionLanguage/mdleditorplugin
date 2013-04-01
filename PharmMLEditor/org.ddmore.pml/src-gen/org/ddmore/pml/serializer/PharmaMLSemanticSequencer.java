package org.ddmore.pml.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.ddmore.pml.pharmaML.Math_BinopName;
import org.ddmore.pml.pharmaML.Math_BinopType;
import org.ddmore.pml.pharmaML.Math_ConditionType;
import org.ddmore.pml.pharmaML.Math_ConstantName;
import org.ddmore.pml.pharmaML.Math_ConstantType;
import org.ddmore.pml.pharmaML.Math_EquationType;
import org.ddmore.pml.pharmaML.Math_EquationTypeContent;
import org.ddmore.pml.pharmaML.Math_EstimationOperationType;
import org.ddmore.pml.pharmaML.Math_ExprType;
import org.ddmore.pml.pharmaML.Math_FALSE;
import org.ddmore.pml.pharmaML.Math_FuncExprType;
import org.ddmore.pml.pharmaML.Math_FunctionArgumentType;
import org.ddmore.pml.pharmaML.Math_FunctionCallType;
import org.ddmore.pml.pharmaML.Math_LogicBaseType;
import org.ddmore.pml.pharmaML.Math_LogicBinopName;
import org.ddmore.pml.pharmaML.Math_LogicBinopType;
import org.ddmore.pml.pharmaML.Math_LogicExprType;
import org.ddmore.pml.pharmaML.Math_LogicUniopName;
import org.ddmore.pml.pharmaML.Math_LogicUniopType;
import org.ddmore.pml.pharmaML.Math_PieceType;
import org.ddmore.pml.pharmaML.Math_PiecewiseType;
import org.ddmore.pml.pharmaML.Math_ScalarType;
import org.ddmore.pml.pharmaML.Math_StringType;
import org.ddmore.pml.pharmaML.Math_TRUE;
import org.ddmore.pml.pharmaML.Math_UniopName;
import org.ddmore.pml.pharmaML.Math_UniopType;
import org.ddmore.pml.pharmaML.Math_VarType;
import org.ddmore.pml.pharmaML.Math_block;
import org.ddmore.pml.pharmaML.Math_id;
import org.ddmore.pml.pharmaML.Math_symbId;
import org.ddmore.pml.pharmaML.Mml_FuncParameterDefinitionType;
import org.ddmore.pml.pharmaML.Mml_FunctionDefinitionType;
import org.ddmore.pml.pharmaML.Mml_PharmaML;
import org.ddmore.pml.pharmaML.Mml_SymbolDefinitionType;
import org.ddmore.pml.pharmaML.PharmaMLPackage;
import org.ddmore.pml.pharmaML.ct_AnnotationType;
import org.ddmore.pml.pharmaML.ct_CellType;
import org.ddmore.pml.pharmaML.ct_ColumnType;
import org.ddmore.pml.pharmaML.ct_DataSetType;
import org.ddmore.pml.pharmaML.ct_Decimal_Value;
import org.ddmore.pml.pharmaML.ct_DefinitionRhsType;
import org.ddmore.pml.pharmaML.ct_DefinitionType;
import org.ddmore.pml.pharmaML.ct_ExternalSourceType;
import org.ddmore.pml.pharmaML.ct_Int_Value;
import org.ddmore.pml.pharmaML.ct_InternalSourceType;
import org.ddmore.pml.pharmaML.ct_Name;
import org.ddmore.pml.pharmaML.ct_ReplicatesType;
import org.ddmore.pml.pharmaML.ct_RestrictionType;
import org.ddmore.pml.pharmaML.ct_Rhs;
import org.ddmore.pml.pharmaML.ct_RowType;
import org.ddmore.pml.pharmaML.ct_RowTypeContent;
import org.ddmore.pml.pharmaML.ct_ScalarRhs;
import org.ddmore.pml.pharmaML.ct_SequenceType;
import org.ddmore.pml.pharmaML.ct_String_Value;
import org.ddmore.pml.pharmaML.ct_SymbolTypeType;
import org.ddmore.pml.pharmaML.ct_VariableAssignmentType;
import org.ddmore.pml.pharmaML.ct_VariableDefinitionType;
import org.ddmore.pml.pharmaML.ct_VectorType;
import org.ddmore.pml.pharmaML.ct_block;
import org.ddmore.pml.pharmaML.ct_catId;
import org.ddmore.pml.pharmaML.ct_id;
import org.ddmore.pml.pharmaML.ct_independentVar;
import org.ddmore.pml.pharmaML.ct_levelId;
import org.ddmore.pml.pharmaML.ct_symbId;
import org.ddmore.pml.pharmaML.ct_symbolType;
import org.ddmore.pml.pharmaML.ct_writtenVersion;
import org.ddmore.pml.pharmaML.ct_xmlns;
import org.ddmore.pml.pharmaML.design_AmountType;
import org.ddmore.pml.pharmaML.design_BeginType;
import org.ddmore.pml.pharmaML.design_BolusType;
import org.ddmore.pml.pharmaML.design_DoseAmountType;
import org.ddmore.pml.pharmaML.design_DoseVarType;
import org.ddmore.pml.pharmaML.design_DosingPeriodType;
import org.ddmore.pml.pharmaML.design_DosingRegimenType;
import org.ddmore.pml.pharmaML.design_DosingStartType;
import org.ddmore.pml.pharmaML.design_DosingTimesType;
import org.ddmore.pml.pharmaML.design_DosingVariableType;
import org.ddmore.pml.pharmaML.design_DurationType;
import org.ddmore.pml.pharmaML.design_EndType;
import org.ddmore.pml.pharmaML.design_GroupType;
import org.ddmore.pml.pharmaML.design_IdividualsType;
import org.ddmore.pml.pharmaML.design_InfusionType;
import org.ddmore.pml.pharmaML.design_OccasionType;
import org.ddmore.pml.pharmaML.design_StartType;
import org.ddmore.pml.pharmaML.design_SteadyStateType;
import org.ddmore.pml.pharmaML.design_TargetVarType;
import org.ddmore.pml.pharmaML.design_TreatmentEpohType;
import org.ddmore.pml.pharmaML.design_TreatmentRefType;
import org.ddmore.pml.pharmaML.design_TreatmentType;
import org.ddmore.pml.pharmaML.design_TrialDesignType;
import org.ddmore.pml.pharmaML.design_WashoutType;
import org.ddmore.pml.pharmaML.mSteps_InitialEstimateType;
import org.ddmore.pml.pharmaML.mSteps_LowerBoundType;
import org.ddmore.pml.pharmaML.mSteps_UpperBoundType;
import org.ddmore.pml.pharmaML.mSteps_VariableEstimateType;
import org.ddmore.pml.pharmaML.mSteps_fixed;
import org.ddmore.pml.pharmaML.mdefCovarianceType;
import org.ddmore.pml.pharmaML.mdef_CategoricalType;
import org.ddmore.pml.pharmaML.mdef_CategoryType;
import org.ddmore.pml.pharmaML.mdef_ContinuousType;
import org.ddmore.pml.pharmaML.mdef_CorrelationCoefficientType;
import org.ddmore.pml.pharmaML.mdef_CorrelationType;
import org.ddmore.pml.pharmaML.mdef_CovariateModelType;
import org.ddmore.pml.pharmaML.mdef_CovariateType;
import org.ddmore.pml.pharmaML.mdef_CovariateVariabilityType;
import org.ddmore.pml.pharmaML.mdef_FixedEffectType;
import org.ddmore.pml.pharmaML.mdef_ImportLinkType;
import org.ddmore.pml.pharmaML.mdef_ImportResourceTypeType;
import org.ddmore.pml.pharmaML.mdef_ImportType;
import org.ddmore.pml.pharmaML.mdef_InitialConditionType;
import org.ddmore.pml.pharmaML.mdef_ModelDefinitionType;
import org.ddmore.pml.pharmaML.mdef_ObservationModelType;
import org.ddmore.pml.pharmaML.mdef_ParameterModelType;
import org.ddmore.pml.pharmaML.mdef_ParameterType;
import org.ddmore.pml.pharmaML.mdef_ParameterTypeName;
import org.ddmore.pml.pharmaML.mdef_ProbabilityType;
import org.ddmore.pml.pharmaML.mdef_RandomEffectType;
import org.ddmore.pml.pharmaML.mdef_StructuralModelType;
import org.ddmore.pml.pharmaML.mdef_TransformationType;
import org.ddmore.pml.pharmaML.mdef_VariabilityLevelDefnType;
import org.ddmore.pml.pharmaML.mdef_transformation;
import org.ddmore.pml.pharmaML.msteps_AssignType;
import org.ddmore.pml.pharmaML.msteps_DependantStepType;
import org.ddmore.pml.pharmaML.msteps_EstimationOperationType;
import org.ddmore.pml.pharmaML.msteps_EstimationStep;
import org.ddmore.pml.pharmaML.msteps_InitialValueType;
import org.ddmore.pml.pharmaML.msteps_MappingType;
import org.ddmore.pml.pharmaML.msteps_ModelingSteps;
import org.ddmore.pml.pharmaML.msteps_ObjectiveDataSetType;
import org.ddmore.pml.pharmaML.msteps_ObservationsType;
import org.ddmore.pml.pharmaML.msteps_OutputType;
import org.ddmore.pml.pharmaML.msteps_ParametersToEstimateType;
import org.ddmore.pml.pharmaML.msteps_SimDataSetType;
import org.ddmore.pml.pharmaML.msteps_SimulationStep;
import org.ddmore.pml.pharmaML.msteps_StepDependenciesType;
import org.ddmore.pml.pharmaML.msteps_StepType;
import org.ddmore.pml.pharmaML.msteps_TargetVarType;
import org.ddmore.pml.pharmaML.msteps_TimepointsType;
import org.ddmore.pml.pharmaML.msteps_UseVariabilityLevel;
import org.ddmore.pml.pharmaML.msteps_UseVariabilityNode;
import org.ddmore.pml.pharmaML.msteps_WriteToType;
import org.ddmore.pml.pharmaML.uncertDegreesOfFreedom;
import org.ddmore.pml.pharmaML.uncert_CoefficientOfVariance;
import org.ddmore.pml.pharmaML.uncert_DistributionParamType;
import org.ddmore.pml.pharmaML.uncert_DistributionType;
import org.ddmore.pml.pharmaML.uncert_Location;
import org.ddmore.pml.pharmaML.uncert_Mean;
import org.ddmore.pml.pharmaML.uncert_MeanParamType;
import org.ddmore.pml.pharmaML.uncert_Normal;
import org.ddmore.pml.pharmaML.uncert_PDF;
import org.ddmore.pml.pharmaML.uncert_ParameterDefinitionType;
import org.ddmore.pml.pharmaML.uncert_Poison;
import org.ddmore.pml.pharmaML.uncert_Rate;
import org.ddmore.pml.pharmaML.uncert_Scale;
import org.ddmore.pml.pharmaML.uncert_StdDev;
import org.ddmore.pml.pharmaML.uncert_Student_t;
import org.ddmore.pml.pharmaML.uncert_Uniform;
import org.ddmore.pml.pharmaML.uncert_Variance;
import org.ddmore.pml.services.PharmaMLGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class PharmaMLSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private PharmaMLGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == PharmaMLPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case PharmaMLPackage.MATH_BINOP_NAME:
				if(context == grammarAccess.getMath_BinopNameRule()) {
					sequence_Math_BinopName(context, (Math_BinopName) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_BINOP_TYPE:
				if(context == grammarAccess.getMath_BinopTypeRule()) {
					sequence_Math_BinopType(context, (Math_BinopType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_CONDITION_TYPE:
				if(context == grammarAccess.getMath_ConditionTypeRule()) {
					sequence_Math_ConditionType(context, (Math_ConditionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_CONSTANT_NAME:
				if(context == grammarAccess.getMath_ConstantNameRule()) {
					sequence_Math_ConstantName(context, (Math_ConstantName) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_CONSTANT_TYPE:
				if(context == grammarAccess.getMath_ConstantTypeRule()) {
					sequence_Math_ConstantType(context, (Math_ConstantType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_EQUATION_TYPE:
				if(context == grammarAccess.getMath_EquationTypeRule()) {
					sequence_Math_EquationType(context, (Math_EquationType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_EQUATION_TYPE_CONTENT:
				if(context == grammarAccess.getMath_EquationTypeContentRule()) {
					sequence_Math_EquationTypeContent(context, (Math_EquationTypeContent) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_ESTIMATION_OPERATION_TYPE:
				if(context == grammarAccess.getMath_EstimationOperationTypeRule()) {
					sequence_Math_EstimationOperationType(context, (Math_EstimationOperationType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_EXPR_TYPE:
				if(context == grammarAccess.getMath_ExprTypeRule()) {
					sequence_Math_ExprType(context, (Math_ExprType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_FALSE:
				if(context == grammarAccess.getMath_FALSERule()) {
					sequence_Math_FALSE(context, (Math_FALSE) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_FUNC_EXPR_TYPE:
				if(context == grammarAccess.getMath_FuncExprTypeRule()) {
					sequence_Math_FuncExprType(context, (Math_FuncExprType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_FUNCTION_ARGUMENT_TYPE:
				if(context == grammarAccess.getMath_FunctionArgumentTypeRule()) {
					sequence_Math_FunctionArgumentType(context, (Math_FunctionArgumentType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_FUNCTION_CALL_TYPE:
				if(context == grammarAccess.getMath_FunctionCallTypeRule()) {
					sequence_Math_FunctionCallType(context, (Math_FunctionCallType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_LOGIC_BASE_TYPE:
				if(context == grammarAccess.getMath_LogicBaseTypeRule()) {
					sequence_Math_LogicBaseType(context, (Math_LogicBaseType) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getMath_LogicExprTypeRule()) {
					sequence_Math_LogicBaseType_Math_LogicExprType(context, (Math_LogicBaseType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_LOGIC_BINOP_NAME:
				if(context == grammarAccess.getMath_LogicBinopNameRule()) {
					sequence_Math_LogicBinopName(context, (Math_LogicBinopName) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_LOGIC_BINOP_TYPE:
				if(context == grammarAccess.getMath_LogicBinopTypeRule()) {
					sequence_Math_LogicBinopType(context, (Math_LogicBinopType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_LOGIC_EXPR_TYPE:
				if(context == grammarAccess.getMath_LogicExprTypeRule()) {
					sequence_Math_LogicExprType(context, (Math_LogicExprType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_LOGIC_UNIOP_NAME:
				if(context == grammarAccess.getMath_LogicUniopNameRule()) {
					sequence_Math_LogicUniopName(context, (Math_LogicUniopName) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_LOGIC_UNIOP_TYPE:
				if(context == grammarAccess.getMath_LogicUniopTypeRule()) {
					sequence_Math_LogicUniopType(context, (Math_LogicUniopType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_PIECE_TYPE:
				if(context == grammarAccess.getMath_PieceTypeRule()) {
					sequence_Math_PieceType(context, (Math_PieceType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_PIECEWISE_TYPE:
				if(context == grammarAccess.getMath_PiecewiseTypeRule()) {
					sequence_Math_PiecewiseType(context, (Math_PiecewiseType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_SCALAR_TYPE:
				if(context == grammarAccess.getMath_ScalarTypeRule()) {
					sequence_Math_ScalarType(context, (Math_ScalarType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_STRING_TYPE:
				if(context == grammarAccess.getMath_StringTypeRule()) {
					sequence_Math_StringType(context, (Math_StringType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_TRUE:
				if(context == grammarAccess.getMath_TRUERule()) {
					sequence_Math_TRUE(context, (Math_TRUE) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_UNIOP_NAME:
				if(context == grammarAccess.getMath_UniopNameRule()) {
					sequence_Math_UniopName(context, (Math_UniopName) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_UNIOP_TYPE:
				if(context == grammarAccess.getMath_UniopTypeRule()) {
					sequence_Math_UniopType(context, (Math_UniopType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_VAR_TYPE:
				if(context == grammarAccess.getMath_VarTypeRule()) {
					sequence_Math_VarType(context, (Math_VarType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_BLOCK:
				if(context == grammarAccess.getMath_blockRule()) {
					sequence_Math_block(context, (Math_block) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_ID:
				if(context == grammarAccess.getMath_idRule()) {
					sequence_Math_id(context, (Math_id) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MATH_SYMB_ID:
				if(context == grammarAccess.getMath_symbIdRule()) {
					sequence_Math_symbId(context, (Math_symbId) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MML_FUNC_PARAMETER_DEFINITION_TYPE:
				if(context == grammarAccess.getMml_FuncParameterDefinitionTypeRule()) {
					sequence_Mml_FuncParameterDefinitionType(context, (Mml_FuncParameterDefinitionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MML_FUNCTION_DEFINITION_TYPE:
				if(context == grammarAccess.getMml_FunctionDefinitionTypeRule()) {
					sequence_Mml_FunctionDefinitionType(context, (Mml_FunctionDefinitionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MML_PHARMA_ML:
				if(context == grammarAccess.getMml_PharmaMLRule()) {
					sequence_Mml_PharmaML(context, (Mml_PharmaML) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MML_SYMBOL_DEFINITION_TYPE:
				if(context == grammarAccess.getMml_SymbolDefinitionTypeRule()) {
					sequence_Mml_SymbolDefinitionType(context, (Mml_SymbolDefinitionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_ANNOTATION_TYPE:
				if(context == grammarAccess.getCt_AnnotationTypeRule()) {
					sequence_ct_AnnotationType(context, (ct_AnnotationType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_CELL_TYPE:
				if(context == grammarAccess.getCt_CellTypeRule()) {
					sequence_ct_CellType(context, (ct_CellType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_COLUMN_TYPE:
				if(context == grammarAccess.getCt_ColumnTypeRule()) {
					sequence_ct_ColumnType(context, (ct_ColumnType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_DATA_SET_TYPE:
				if(context == grammarAccess.getCt_DataSetTypeRule()) {
					sequence_ct_DataSetType(context, (ct_DataSetType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_DECIMAL_VALUE:
				if(context == grammarAccess.getCt_Decimal_ValueRule()) {
					sequence_ct_Decimal_Value(context, (ct_Decimal_Value) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_DEFINITION_RHS_TYPE:
				if(context == grammarAccess.getCt_DefinitionRhsTypeRule()) {
					sequence_ct_DefinitionRhsType(context, (ct_DefinitionRhsType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_DEFINITION_TYPE:
				if(context == grammarAccess.getCt_DefinitionTypeRule()) {
					sequence_ct_DefinitionType(context, (ct_DefinitionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_EXTERNAL_SOURCE_TYPE:
				if(context == grammarAccess.getCt_ExternalSourceTypeRule()) {
					sequence_ct_ExternalSourceType(context, (ct_ExternalSourceType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_INT_VALUE:
				if(context == grammarAccess.getCt_Int_ValueRule()) {
					sequence_ct_Int_Value(context, (ct_Int_Value) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_INTERNAL_SOURCE_TYPE:
				if(context == grammarAccess.getCt_InternalSourceTypeRule()) {
					sequence_ct_InternalSourceType(context, (ct_InternalSourceType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_NAME:
				if(context == grammarAccess.getCt_NameRule()) {
					sequence_ct_Name(context, (ct_Name) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_REPLICATES_TYPE:
				if(context == grammarAccess.getCt_ReplicatesTypeRule()) {
					sequence_ct_ReplicatesType(context, (ct_ReplicatesType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_RESTRICTION_TYPE:
				if(context == grammarAccess.getCt_RestrictionTypeRule()) {
					sequence_ct_RestrictionType(context, (ct_RestrictionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_RHS:
				if(context == grammarAccess.getCt_RhsRule()) {
					sequence_ct_Rhs(context, (ct_Rhs) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_ROW_TYPE:
				if(context == grammarAccess.getCt_RowTypeRule()) {
					sequence_ct_RowType(context, (ct_RowType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_ROW_TYPE_CONTENT:
				if(context == grammarAccess.getCt_RowTypeContentRule()) {
					sequence_ct_RowTypeContent(context, (ct_RowTypeContent) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_SCALAR_RHS:
				if(context == grammarAccess.getCt_ScalarRhsRule()) {
					sequence_ct_ScalarRhs(context, (ct_ScalarRhs) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_SEQUENCE_TYPE:
				if(context == grammarAccess.getCt_SequenceTypeRule()) {
					sequence_ct_SequenceType(context, (ct_SequenceType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_STRING_VALUE:
				if(context == grammarAccess.getCt_String_ValueRule()) {
					sequence_ct_String_Value(context, (ct_String_Value) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_SYMBOL_TYPE_TYPE:
				if(context == grammarAccess.getCt_SymbolTypeTypeRule()) {
					sequence_ct_SymbolTypeType(context, (ct_SymbolTypeType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_VARIABLE_ASSIGNMENT_TYPE:
				if(context == grammarAccess.getCt_VariableAssignmentTypeRule()) {
					sequence_ct_VariableAssignmentType(context, (ct_VariableAssignmentType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_VARIABLE_DEFINITION_TYPE:
				if(context == grammarAccess.getCt_VariableDefinitionTypeRule()) {
					sequence_ct_VariableDefinitionType(context, (ct_VariableDefinitionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_VECTOR_TYPE:
				if(context == grammarAccess.getCt_VectorTypeRule()) {
					sequence_ct_VectorType(context, (ct_VectorType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_BLOCK:
				if(context == grammarAccess.getCt_blockRule()) {
					sequence_ct_block(context, (ct_block) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_CAT_ID:
				if(context == grammarAccess.getCt_catIdRule()) {
					sequence_ct_catId(context, (ct_catId) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_ID:
				if(context == grammarAccess.getCt_idRule()) {
					sequence_ct_id(context, (ct_id) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_INDEPENDENT_VAR:
				if(context == grammarAccess.getCt_independentVarRule()) {
					sequence_ct_independentVar(context, (ct_independentVar) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_LEVEL_ID:
				if(context == grammarAccess.getCt_levelIdRule()) {
					sequence_ct_levelId(context, (ct_levelId) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_SYMB_ID:
				if(context == grammarAccess.getCt_symbIdRule()) {
					sequence_ct_symbId(context, (ct_symbId) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_SYMBOL_TYPE:
				if(context == grammarAccess.getCt_symbolTypeRule()) {
					sequence_ct_symbolType(context, (ct_symbolType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_WRITTEN_VERSION:
				if(context == grammarAccess.getCt_writtenVersionRule()) {
					sequence_ct_writtenVersion(context, (ct_writtenVersion) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.CT_XMLNS:
				if(context == grammarAccess.getCt_xmlnsRule()) {
					sequence_ct_xmlns(context, (ct_xmlns) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_AMOUNT_TYPE:
				if(context == grammarAccess.getDesign_AmountTypeRule()) {
					sequence_design_AmountType(context, (design_AmountType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_BEGIN_TYPE:
				if(context == grammarAccess.getDesign_BeginTypeRule()) {
					sequence_design_BeginType(context, (design_BeginType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_BOLUS_TYPE:
				if(context == grammarAccess.getDesign_BolusTypeRule()) {
					sequence_design_BolusType(context, (design_BolusType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_DOSE_AMOUNT_TYPE:
				if(context == grammarAccess.getDesign_DoseAmountTypeRule()) {
					sequence_design_DoseAmountType(context, (design_DoseAmountType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_DOSE_VAR_TYPE:
				if(context == grammarAccess.getDesign_DoseVarTypeRule()) {
					sequence_design_DoseVarType(context, (design_DoseVarType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_DOSING_PERIOD_TYPE:
				if(context == grammarAccess.getDesign_DosingPeriodTypeRule()) {
					sequence_design_DosingPeriodType(context, (design_DosingPeriodType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_DOSING_REGIMEN_TYPE:
				if(context == grammarAccess.getDesign_DosingRegimenTypeRule()) {
					sequence_design_DosingRegimenType(context, (design_DosingRegimenType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_DOSING_START_TYPE:
				if(context == grammarAccess.getDesign_DosingStartTypeRule()) {
					sequence_design_DosingStartType(context, (design_DosingStartType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_DOSING_TIMES_TYPE:
				if(context == grammarAccess.getDesign_DosingTimesTypeRule()) {
					sequence_design_DosingTimesType(context, (design_DosingTimesType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_DOSING_VARIABLE_TYPE:
				if(context == grammarAccess.getDesign_DosingVariableTypeRule()) {
					sequence_design_DosingVariableType(context, (design_DosingVariableType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_DURATION_TYPE:
				if(context == grammarAccess.getDesign_DurationTypeRule()) {
					sequence_design_DurationType(context, (design_DurationType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_END_TYPE:
				if(context == grammarAccess.getDesign_EndTypeRule()) {
					sequence_design_EndType(context, (design_EndType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_GROUP_TYPE:
				if(context == grammarAccess.getDesign_GroupTypeRule()) {
					sequence_design_GroupType(context, (design_GroupType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_IDIVIDUALS_TYPE:
				if(context == grammarAccess.getDesign_IdividualsTypeRule()) {
					sequence_design_IdividualsType(context, (design_IdividualsType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_INFUSION_TYPE:
				if(context == grammarAccess.getDesign_InfusionTypeRule()) {
					sequence_design_InfusionType(context, (design_InfusionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_OCCASION_TYPE:
				if(context == grammarAccess.getDesign_OccasionTypeRule()) {
					sequence_design_OccasionType(context, (design_OccasionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_START_TYPE:
				if(context == grammarAccess.getDesign_StartTypeRule()) {
					sequence_design_StartType(context, (design_StartType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_STEADY_STATE_TYPE:
				if(context == grammarAccess.getDesign_SteadyStateTypeRule()) {
					sequence_design_SteadyStateType(context, (design_SteadyStateType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_TARGET_VAR_TYPE:
				if(context == grammarAccess.getDesign_TargetVarTypeRule()) {
					sequence_design_TargetVarType(context, (design_TargetVarType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_TREATMENT_EPOH_TYPE:
				if(context == grammarAccess.getDesign_TreatmentEpohTypeRule()) {
					sequence_design_TreatmentEpohType(context, (design_TreatmentEpohType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_TREATMENT_REF_TYPE:
				if(context == grammarAccess.getDesign_TreatmentRefTypeRule()) {
					sequence_design_TreatmentRefType(context, (design_TreatmentRefType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_TREATMENT_TYPE:
				if(context == grammarAccess.getDesign_TreatmentTypeRule()) {
					sequence_design_TreatmentType(context, (design_TreatmentType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_TRIAL_DESIGN_TYPE:
				if(context == grammarAccess.getDesign_TrialDesignTypeRule()) {
					sequence_design_TrialDesignType(context, (design_TrialDesignType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.DESIGN_WASHOUT_TYPE:
				if(context == grammarAccess.getDesign_WashoutTypeRule()) {
					sequence_design_WashoutType(context, (design_WashoutType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_INITIAL_ESTIMATE_TYPE:
				if(context == grammarAccess.getMSteps_InitialEstimateTypeRule()) {
					sequence_mSteps_InitialEstimateType(context, (mSteps_InitialEstimateType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_LOWER_BOUND_TYPE:
				if(context == grammarAccess.getMSteps_LowerBoundTypeRule()) {
					sequence_mSteps_LowerBoundType(context, (mSteps_LowerBoundType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_UPPER_BOUND_TYPE:
				if(context == grammarAccess.getMSteps_UpperBoundTypeRule()) {
					sequence_mSteps_UpperBoundType(context, (mSteps_UpperBoundType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_VARIABLE_ESTIMATE_TYPE:
				if(context == grammarAccess.getMSteps_VariableEstimateTypeRule()) {
					sequence_mSteps_VariableEstimateType(context, (mSteps_VariableEstimateType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_FIXED:
				if(context == grammarAccess.getMSteps_fixedRule()) {
					sequence_mSteps_fixed(context, (mSteps_fixed) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_COVARIANCE_TYPE:
				if(context == grammarAccess.getMdefCovarianceTypeRule()) {
					sequence_mdefCovarianceType(context, (mdefCovarianceType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_CATEGORICAL_TYPE:
				if(context == grammarAccess.getMdef_CategoricalTypeRule()) {
					sequence_mdef_CategoricalType(context, (mdef_CategoricalType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_CATEGORY_TYPE:
				if(context == grammarAccess.getMdef_CategoryTypeRule()) {
					sequence_mdef_CategoryType(context, (mdef_CategoryType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_CONTINUOUS_TYPE:
				if(context == grammarAccess.getMdef_ContinuousTypeRule()) {
					sequence_mdef_ContinuousType(context, (mdef_ContinuousType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_CORRELATION_COEFFICIENT_TYPE:
				if(context == grammarAccess.getMdef_CorrelationCoefficientTypeRule()) {
					sequence_mdef_CorrelationCoefficientType(context, (mdef_CorrelationCoefficientType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_CORRELATION_TYPE:
				if(context == grammarAccess.getMdef_CorrelationTypeRule()) {
					sequence_mdef_CorrelationType(context, (mdef_CorrelationType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_COVARIATE_MODEL_TYPE:
				if(context == grammarAccess.getMdef_CovariateModelTypeRule()) {
					sequence_mdef_CovariateModelType(context, (mdef_CovariateModelType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_COVARIATE_TYPE:
				if(context == grammarAccess.getMdef_CovariateTypeRule()) {
					sequence_mdef_CovariateType(context, (mdef_CovariateType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_COVARIATE_VARIABILITY_TYPE:
				if(context == grammarAccess.getMdef_CovariateVariabilityTypeRule()) {
					sequence_mdef_CovariateVariabilityType(context, (mdef_CovariateVariabilityType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_FIXED_EFFECT_TYPE:
				if(context == grammarAccess.getMdef_FixedEffectTypeRule()) {
					sequence_mdef_FixedEffectType(context, (mdef_FixedEffectType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_IMPORT_LINK_TYPE:
				if(context == grammarAccess.getMdef_ImportLinkTypeRule()) {
					sequence_mdef_ImportLinkType(context, (mdef_ImportLinkType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_IMPORT_RESOURCE_TYPE_TYPE:
				if(context == grammarAccess.getMdef_ImportResourceTypeTypeRule()) {
					sequence_mdef_ImportResourceTypeType(context, (mdef_ImportResourceTypeType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_IMPORT_TYPE:
				if(context == grammarAccess.getMdef_ImportTypeRule()) {
					sequence_mdef_ImportType(context, (mdef_ImportType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_INITIAL_CONDITION_TYPE:
				if(context == grammarAccess.getMdef_InitialConditionTypeRule()) {
					sequence_mdef_InitialConditionType(context, (mdef_InitialConditionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_MODEL_DEFINITION_TYPE:
				if(context == grammarAccess.getMdef_ModelDefinitionTypeRule()) {
					sequence_mdef_ModelDefinitionType(context, (mdef_ModelDefinitionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_OBSERVATION_MODEL_TYPE:
				if(context == grammarAccess.getMdef_ObservationModelTypeRule()) {
					sequence_mdef_ObservationModelType(context, (mdef_ObservationModelType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_PARAMETER_MODEL_TYPE:
				if(context == grammarAccess.getMdef_ParameterModelTypeRule()) {
					sequence_mdef_ParameterModelType(context, (mdef_ParameterModelType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_PARAMETER_TYPE:
				if(context == grammarAccess.getMdef_ParameterTypeRule()) {
					sequence_mdef_ParameterType(context, (mdef_ParameterType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_PARAMETER_TYPE_NAME:
				if(context == grammarAccess.getMdef_ParameterTypeNameRule()) {
					sequence_mdef_ParameterTypeName(context, (mdef_ParameterTypeName) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_PROBABILITY_TYPE:
				if(context == grammarAccess.getMdef_ProbabilityTypeRule()) {
					sequence_mdef_ProbabilityType(context, (mdef_ProbabilityType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_RANDOM_EFFECT_TYPE:
				if(context == grammarAccess.getMdef_RandomEffectTypeRule()) {
					sequence_mdef_RandomEffectType(context, (mdef_RandomEffectType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_STRUCTURAL_MODEL_TYPE:
				if(context == grammarAccess.getMdef_StructuralModelTypeRule()) {
					sequence_mdef_StructuralModelType(context, (mdef_StructuralModelType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_TRANSFORMATION_TYPE:
				if(context == grammarAccess.getMdef_TransformationTypeRule()) {
					sequence_mdef_TransformationType(context, (mdef_TransformationType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_VARIABILITY_LEVEL_DEFN_TYPE:
				if(context == grammarAccess.getMdef_VariabilityLevelDefnTypeRule()) {
					sequence_mdef_VariabilityLevelDefnType(context, (mdef_VariabilityLevelDefnType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MDEF_TRANSFORMATION:
				if(context == grammarAccess.getMdef_transformationRule()) {
					sequence_mdef_transformation(context, (mdef_transformation) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_ASSIGN_TYPE:
				if(context == grammarAccess.getMsteps_AssignTypeRule()) {
					sequence_msteps_AssignType(context, (msteps_AssignType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_DEPENDANT_STEP_TYPE:
				if(context == grammarAccess.getMsteps_DependantStepTypeRule()) {
					sequence_msteps_DependantStepType(context, (msteps_DependantStepType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_ESTIMATION_OPERATION_TYPE:
				if(context == grammarAccess.getMsteps_EstimationOperationTypeRule()) {
					sequence_msteps_EstimationOperationType(context, (msteps_EstimationOperationType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_ESTIMATION_STEP:
				if(context == grammarAccess.getMsteps_EstimationStepRule()) {
					sequence_msteps_EstimationStep(context, (msteps_EstimationStep) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_INITIAL_VALUE_TYPE:
				if(context == grammarAccess.getMsteps_InitialValueTypeRule()) {
					sequence_msteps_InitialValueType(context, (msteps_InitialValueType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_MAPPING_TYPE:
				if(context == grammarAccess.getMsteps_MappingTypeRule()) {
					sequence_msteps_MappingType(context, (msteps_MappingType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_MODELING_STEPS:
				if(context == grammarAccess.getMsteps_ModelingStepsRule()) {
					sequence_msteps_ModelingSteps(context, (msteps_ModelingSteps) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_OBJECTIVE_DATA_SET_TYPE:
				if(context == grammarAccess.getMsteps_ObjectiveDataSetTypeRule()) {
					sequence_msteps_ObjectiveDataSetType(context, (msteps_ObjectiveDataSetType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_OBSERVATIONS_TYPE:
				if(context == grammarAccess.getMsteps_ObservationsTypeRule()) {
					sequence_msteps_ObservationsType(context, (msteps_ObservationsType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_OUTPUT_TYPE:
				if(context == grammarAccess.getMsteps_OutputTypeRule()) {
					sequence_msteps_OutputType(context, (msteps_OutputType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_PARAMETERS_TO_ESTIMATE_TYPE:
				if(context == grammarAccess.getMsteps_ParametersToEstimateTypeRule()) {
					sequence_msteps_ParametersToEstimateType(context, (msteps_ParametersToEstimateType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_SIM_DATA_SET_TYPE:
				if(context == grammarAccess.getMsteps_SimDataSetTypeRule()) {
					sequence_msteps_SimDataSetType(context, (msteps_SimDataSetType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_SIMULATION_STEP:
				if(context == grammarAccess.getMsteps_SimulationStepRule()) {
					sequence_msteps_SimulationStep(context, (msteps_SimulationStep) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_STEP_DEPENDENCIES_TYPE:
				if(context == grammarAccess.getMsteps_StepDependenciesTypeRule()) {
					sequence_msteps_StepDependenciesType(context, (msteps_StepDependenciesType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_STEP_TYPE:
				if(context == grammarAccess.getMsteps_StepTypeRule()) {
					sequence_msteps_StepType(context, (msteps_StepType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_TARGET_VAR_TYPE:
				if(context == grammarAccess.getMsteps_TargetVarTypeRule()) {
					sequence_msteps_TargetVarType(context, (msteps_TargetVarType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_TIMEPOINTS_TYPE:
				if(context == grammarAccess.getMsteps_TimepointsTypeRule()) {
					sequence_msteps_TimepointsType(context, (msteps_TimepointsType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_USE_VARIABILITY_LEVEL:
				if(context == grammarAccess.getMsteps_UseVariabilityLevelRule()) {
					sequence_msteps_UseVariabilityLevel(context, (msteps_UseVariabilityLevel) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_USE_VARIABILITY_NODE:
				if(context == grammarAccess.getMsteps_UseVariabilityNodeRule()) {
					sequence_msteps_UseVariabilityNode(context, (msteps_UseVariabilityNode) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.MSTEPS_WRITE_TO_TYPE:
				if(context == grammarAccess.getMsteps_WriteToTypeRule()) {
					sequence_msteps_WriteToType(context, (msteps_WriteToType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_DEGREES_OF_FREEDOM:
				if(context == grammarAccess.getUncertDegreesOfFreedomRule()) {
					sequence_uncertDegreesOfFreedom(context, (uncertDegreesOfFreedom) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_COEFFICIENT_OF_VARIANCE:
				if(context == grammarAccess.getUncert_CoefficientOfVarianceRule()) {
					sequence_uncert_CoefficientOfVariance(context, (uncert_CoefficientOfVariance) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_DISTRIBUTION_PARAM_TYPE:
				if(context == grammarAccess.getUncert_DistributionParamTypeRule()) {
					sequence_uncert_DistributionParamType(context, (uncert_DistributionParamType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_DISTRIBUTION_TYPE:
				if(context == grammarAccess.getUncert_DistributionTypeRule()) {
					sequence_uncert_DistributionType(context, (uncert_DistributionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_LOCATION:
				if(context == grammarAccess.getUncert_LocationRule()) {
					sequence_uncert_Location(context, (uncert_Location) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_MEAN:
				if(context == grammarAccess.getUncert_MeanRule()) {
					sequence_uncert_Mean(context, (uncert_Mean) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_MEAN_PARAM_TYPE:
				if(context == grammarAccess.getUncert_MeanParamTypeRule()) {
					sequence_uncert_MeanParamType(context, (uncert_MeanParamType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_NORMAL:
				if(context == grammarAccess.getUncert_NormalRule()) {
					sequence_uncert_Normal(context, (uncert_Normal) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_PDF:
				if(context == grammarAccess.getUncert_PDFRule()) {
					sequence_uncert_PDF(context, (uncert_PDF) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_PARAMETER_DEFINITION_TYPE:
				if(context == grammarAccess.getUncert_ParameterDefinitionTypeRule()) {
					sequence_uncert_ParameterDefinitionType(context, (uncert_ParameterDefinitionType) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_POISON:
				if(context == grammarAccess.getUncert_PoisonRule()) {
					sequence_uncert_Poison(context, (uncert_Poison) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_RATE:
				if(context == grammarAccess.getUncert_RateRule()) {
					sequence_uncert_Rate(context, (uncert_Rate) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_SCALE:
				if(context == grammarAccess.getUncert_ScaleRule()) {
					sequence_uncert_Scale(context, (uncert_Scale) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_STD_DEV:
				if(context == grammarAccess.getUncert_StdDevRule()) {
					sequence_uncert_StdDev(context, (uncert_StdDev) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_STUDENT_T:
				if(context == grammarAccess.getUncert_Student_tRule()) {
					sequence_uncert_Student_t(context, (uncert_Student_t) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_UNIFORM:
				if(context == grammarAccess.getUncert_UniformRule()) {
					sequence_uncert_Uniform(context, (uncert_Uniform) semanticObject); 
					return; 
				}
				else break;
			case PharmaMLPackage.UNCERT_VARIANCE:
				if(context == grammarAccess.getUncert_VarianceRule()) {
					sequence_uncert_Variance(context, (uncert_Variance) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         binopName='"plus"' | 
	 *         minus='"minus"' | 
	 *         times='"times"' | 
	 *         divide='"divide"' | 
	 *         power='"power"' | 
	 *         log='"log"' | 
	 *         root='"root"'
	 *     )
	 */
	protected void sequence_Math_BinopName(EObject context, Math_BinopName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (op=Math_BinopName xmlns+=ct_xmlns* LeftExpr=Math_ExprType RightExpr=Math_ExprType)
	 */
	protected void sequence_Math_BinopType(EObject context, Math_BinopType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (writtenVersion=ct_writtenVersion xmlns+=ct_xmlns* Expr=Math_LogicBaseType)
	 */
	protected void sequence_Math_ConditionType(EObject context, Math_ConditionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (notanumber='notanumber' | pi='pi' | exponentiale='exponentiale' | infinity='infinity')
	 */
	protected void sequence_Math_ConstantName(EObject context, Math_ConstantName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (op=Math_ConstantName xmlns+=ct_xmlns*)
	 */
	protected void sequence_Math_ConstantType(EObject context, Math_ConstantType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Scalar=Math_ScalarType | Var=Math_VarType | Binop=Math_BinopType | Uniop=Math_UniopType | Piecewise=Math_PiecewiseType)
	 */
	protected void sequence_Math_EquationTypeContent(EObject context, Math_EquationTypeContent semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* writtenVersion=ct_writtenVersion Expr=Math_EquationTypeContent)
	 */
	protected void sequence_Math_EquationType(EObject context, Math_EquationType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (estPop='"estPop"' | estFIM='"estFIM"' | estIndiv='"estIndiv"')
	 */
	protected void sequence_Math_EstimationOperationType(EObject context, Math_EstimationOperationType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         Scalar=Math_ScalarType | 
	 *         Var=Math_VarType | 
	 *         Uniop=Math_UniopType | 
	 *         Binop=Math_BinopType | 
	 *         Constant=Math_ConstantType | 
	 *         FunctionCall=Math_FunctionCallType
	 *     )
	 */
	protected void sequence_Math_ExprType(EObject context, Math_ExprType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (false='"False"' | false='"false"' | false='"FALSE"')
	 */
	protected void sequence_Math_FALSE(EObject context, Math_FALSE semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Equation=Math_EquationType | Scalar=Math_ScalarType | Constant=Math_ConstantType | Var=Math_VarType)
	 */
	protected void sequence_Math_FuncExprType(EObject context, Math_FuncExprType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=Math_symbId xmlns+=ct_xmlns* Expr=Math_FuncExprType)
	 */
	protected void sequence_Math_FunctionArgumentType(EObject context, Math_FunctionArgumentType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* FunctionName=Math_VarType FunctionArguments+=Math_FunctionArgumentType*)
	 */
	protected void sequence_Math_FunctionCallType(EObject context, Math_FunctionCallType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (LogicBinopType=Math_LogicBinopType | LogicUniopType=Math_LogicUniopType | True=Math_TRUE | False=Math_FALSE)
	 */
	protected void sequence_Math_LogicBaseType(EObject context, Math_LogicBaseType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((LogicBinopType=Math_LogicBinopType | LogicUniopType=Math_LogicUniopType | True=Math_TRUE | False=Math_FALSE) Scalar=Math_ScalarType)
	 */
	protected void sequence_Math_LogicBaseType_Math_LogicExprType(EObject context, Math_LogicBaseType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         lt='lt' | 
	 *         leq='leq' | 
	 *         gt='gt' | 
	 *         geq='geq' | 
	 *         eq='eq' | 
	 *         neq='neq' | 
	 *         and='and' | 
	 *         or='or' | 
	 *         xor='xor'
	 *     )
	 */
	protected void sequence_Math_LogicBinopName(EObject context, Math_LogicBinopName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (op=Math_LogicBinopName xmlns+=ct_xmlns* LeftExpr=Math_LogicExprType RightExpr=Math_LogicExprType)
	 */
	protected void sequence_Math_LogicBinopType(EObject context, Math_LogicBinopType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         Constant=Math_ConstantType | 
	 *         Var=Math_VarType | 
	 *         Uniop=Math_UniopType | 
	 *         Binop=Math_BinopType | 
	 *         FunctionCall=Math_FunctionCallType | 
	 *         String=Math_StringType
	 *     )
	 */
	protected void sequence_Math_LogicExprType(EObject context, Math_LogicExprType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (isDefined='isDefined' | not='not')
	 */
	protected void sequence_Math_LogicUniopName(EObject context, Math_LogicUniopName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (op=Math_LogicUniopName xmlns+=ct_xmlns* Expr=Math_LogicExprType)
	 */
	protected void sequence_Math_LogicUniopType(EObject context, Math_LogicUniopType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Content=Math_ExprType Condition=Math_ConditionType)
	 */
	protected void sequence_Math_PieceType(EObject context, Math_PieceType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Piecewise+=Math_PieceType*)
	 */
	protected void sequence_Math_PiecewiseType(EObject context, Math_PiecewiseType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (value=ct_Decimal_Value xmlns+=ct_xmlns*)
	 */
	protected void sequence_Math_ScalarType(EObject context, Math_ScalarType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (value=ct_String_Value xmlns+=ct_xmlns*)
	 */
	protected void sequence_Math_StringType(EObject context, Math_StringType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (true='"True"' | true='"true"' | true='"TRUE"')
	 */
	protected void sequence_Math_TRUE(EObject context, Math_TRUE semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         exp='"exp"' | 
	 *         ln='"ln"' | 
	 *         minus='"minus"' | 
	 *         factorial='"factorial"' | 
	 *         sin='"sin"' | 
	 *         cos='"cos"' | 
	 *         tan='"tan"' | 
	 *         sec='"sec"' | 
	 *         csc='"csc"' | 
	 *         cot='"cot"' | 
	 *         sinh='"sinh"' | 
	 *         csch='"csch"' | 
	 *         coth='"coth"' | 
	 *         arcsin='"arcsin"' | 
	 *         arccos='"arccos"' | 
	 *         arctan='"arctan"' | 
	 *         arcsec='"arcsec"' | 
	 *         arccsc='"arccsc"' | 
	 *         arccot='"arccot"' | 
	 *         arcsinh='"arcsinh"' | 
	 *         arccosh='"arccosh"' | 
	 *         arctanh='"arctanh"' | 
	 *         arcsech='"arcsech"' | 
	 *         arccsch='"arccsch"' | 
	 *         arccoth='"arccoth"' | 
	 *         floor='"floor"' | 
	 *         abs='"abs"' | 
	 *         ceiling='"ceiling"' | 
	 *         logit='"logit"'
	 *     )
	 */
	protected void sequence_Math_UniopName(EObject context, Math_UniopName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (op=Math_UniopName xmlns+=ct_xmlns* Expr=Math_ExprType)
	 */
	protected void sequence_Math_UniopType(EObject context, Math_UniopType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (block=Math_block? symbId=Math_symbId xmlns+=ct_xmlns*)
	 */
	protected void sequence_Math_VarType(EObject context, Math_VarType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     block=Math_BlockNameType
	 */
	protected void sequence_Math_block(EObject context, Math_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getMath_block_Block()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getMath_block_Block()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMath_blockAccess().getBlockMath_BlockNameTypeParserRuleCall_2_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     id=Math_BlockNameType
	 */
	protected void sequence_Math_id(EObject context, Math_id semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getMath_id_Id()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getMath_id_Id()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMath_idAccess().getIdMath_BlockNameTypeParserRuleCall_2_0(), semanticObject.getId());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     symbId=Math_SymbolNameType
	 */
	protected void sequence_Math_symbId(EObject context, Math_symbId semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getMath_symbId_SymbId()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getMath_symbId_SymbId()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMath_symbIdAccess().getSymbIdMath_SymbolNameTypeParserRuleCall_2_0(), semanticObject.getSymbId());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (sumbId=ct_symbId symbolType=ct_symbolType? xmlns+=ct_xmlns* Decsription=ct_AnnotationType?)
	 */
	protected void sequence_Mml_FuncParameterDefinitionType(EObject context, Mml_FuncParameterDefinitionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* FunctionArguments+=Mml_FuncParameterDefinitionType* Definition=ct_DefinitionRhsType)
	 */
	protected void sequence_Mml_FunctionDefinitionType(EObject context, Mml_FunctionDefinitionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         xmlns+=ct_xmlns* 
	 *         Name=ct_Name 
	 *         IndependentVar=ct_independentVar 
	 *         writtenVersion=ct_writtenVersion 
	 *         SymbolDefinition+=Mml_SymbolDefinitionType* 
	 *         StructuralModel+=mdef_StructuralModelType* 
	 *         ModelDefinition+=mdef_ModelDefinitionType* 
	 *         Design=design_TrialDesignType? 
	 *         ModelingSteps=msteps_ModelingSteps?
	 *     )
	 */
	protected void sequence_Mml_PharmaML(EObject context, Mml_PharmaML semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         symbId=ct_symbId 
	 *         symbolType=ct_symbolType 
	 *         Name=ct_Name? 
	 *         xmlns+=ct_xmlns* 
	 *         Description=ct_AnnotationType? 
	 *         FunctionDefinition=Mml_FunctionDefinitionType
	 *     )
	 */
	protected void sequence_Mml_SymbolDefinitionType(EObject context, Mml_SymbolDefinitionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Description=STRING)
	 */
	protected void sequence_ct_AnnotationType(EObject context, ct_AnnotationType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (value=ct_Decimal_Value xmlns+=ct_xmlns*)
	 */
	protected void sequence_ct_CellType(EObject context, ct_CellType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (columnNum=ct_Int_Value columnVar=ct_SymbolNameType xmlns+=ct_xmlns*)
	 */
	protected void sequence_ct_ColumnType(EObject context, ct_ColumnType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Definition=ct_DefinitionType ExternalSource+=ct_ExternalSourceType* Row+=ct_RowType* InternalSource+=ct_InternalSourceType*)
	 */
	protected void sequence_ct_DataSetType(EObject context, ct_DataSetType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=XS_DECIMAL
	 */
	protected void sequence_ct_Decimal_Value(EObject context, ct_Decimal_Value semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_Decimal_Value_Value()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_Decimal_Value_Value()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_Decimal_ValueAccess().getValueXS_DECIMALParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Definition=ct_Rhs)
	 */
	protected void sequence_ct_DefinitionRhsType(EObject context, ct_DefinitionRhsType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Definition+=ct_ColumnType)
	 */
	protected void sequence_ct_DefinitionType(EObject context, ct_DefinitionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (format=ct_SymbolNameType (headerLine=Math_TRUE | headerLine=Math_FALSE)? ignoreLineSymbol=ct_SymbolNameType? url=XS_ANY_URI xmlns+=ct_xmlns*)
	 */
	protected void sequence_ct_ExternalSourceType(EObject context, ct_ExternalSourceType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=XS_INT
	 */
	protected void sequence_ct_Int_Value(EObject context, ct_Int_Value semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_Int_Value_Value()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_Int_Value_Value()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_Int_ValueAccess().getValueXS_INTParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=Math_symbId block=Math_block? xmlns+=ct_xmlns*)
	 */
	protected void sequence_ct_InternalSourceType(EObject context, ct_InternalSourceType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Name=STR
	 */
	protected void sequence_ct_Name(EObject context, ct_Name semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_Name_Name()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_Name_Name()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_NameAccess().getNameSTRTerminalRuleCall_2_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=ct_symbId xmlns+=ct_xmlns* Expr=ct_Rhs)
	 */
	protected void sequence_ct_ReplicatesType(EObject context, ct_ReplicatesType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=Math_ConditionType)
	 */
	protected void sequence_ct_RestrictionType(EObject context, ct_RestrictionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         Equation=Math_EquationType | 
	 *         Scalar=Math_ScalarType | 
	 *         Constant=Math_ConstantType | 
	 *         String=Math_StringType | 
	 *         Sequence=ct_SequenceType | 
	 *         Vector=ct_VectorType | 
	 *         FunctionCall=Math_FunctionCallType | 
	 *         DataSet=ct_DataSetType | 
	 *         Var=Math_VarType | 
	 *         Distribution=uncert_DistributionType
	 *     )
	 */
	protected void sequence_ct_Rhs(EObject context, ct_Rhs semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Cell=ct_CellType | Null=ct_NullCellType)
	 */
	protected void sequence_ct_RowTypeContent(EObject context, ct_RowTypeContent semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Row+=ct_RowTypeContent*)
	 */
	protected void sequence_ct_RowType(EObject context, ct_RowType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Equation=Math_EquationType | Scalar=Math_ScalarType | Var=Math_VarType | String=Math_StringType | FunctionCall=Math_FunctionCallType)
	 */
	protected void sequence_ct_ScalarRhs(EObject context, ct_ScalarRhs semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (begin=ct_Decimal_Value end=ct_Decimal_Value? repetition=ct_Int_Value? stepwise=ct_Decimal_Value xmlns+=ct_xmlns*)
	 */
	protected void sequence_ct_SequenceType(EObject context, ct_SequenceType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=XS_STRING
	 */
	protected void sequence_ct_String_Value(EObject context, ct_String_Value semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_String_Value_Value()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_String_Value_Value()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_String_ValueAccess().getValueXS_STRINGParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (scalar='"scalar"' | derivative='"derivative"' | distrib='"distrib"' | dataSet='"dataSet"')
	 */
	protected void sequence_ct_SymbolTypeType(EObject context, ct_SymbolTypeType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=ct_symbId block=ct_block? xmlns+=ct_xmlns* Expr=ct_Rhs)
	 */
	protected void sequence_ct_VariableAssignmentType(EObject context, ct_VariableAssignmentType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         symbId=ct_symbId 
	 *         symbolType=ct_symbolType 
	 *         independentVar=ct_independentVar 
	 *         xmlns+=ct_xmlns* 
	 *         Description=ct_AnnotationType? 
	 *         Expr=ct_Rhs
	 *     )
	 */
	protected void sequence_ct_VariableDefinitionType(EObject context, ct_VariableDefinitionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Sequence=ct_SequenceType? Scalar=Math_ScalarType?)
	 */
	protected void sequence_ct_VectorType(EObject context, ct_VectorType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     block=ct_BlockNameType
	 */
	protected void sequence_ct_block(EObject context, ct_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_block_Block()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_block_Block()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_blockAccess().getBlockCt_BlockNameTypeParserRuleCall_2_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     catId=ct_SymbolNameType
	 */
	protected void sequence_ct_catId(EObject context, ct_catId semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_catId_CatId()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_catId_CatId()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_catIdAccess().getCatIdCt_SymbolNameTypeParserRuleCall_2_0(), semanticObject.getCatId());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     id=ct_BlockNameType
	 */
	protected void sequence_ct_id(EObject context, ct_id semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_id_Id()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_id_Id()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_idAccess().getIdCt_BlockNameTypeParserRuleCall_2_0(), semanticObject.getId());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     independentVar=ct_SymbolNameType
	 */
	protected void sequence_ct_independentVar(EObject context, ct_independentVar semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_independentVar_IndependentVar()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_independentVar_IndependentVar()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_independentVarAccess().getIndependentVarCt_SymbolNameTypeParserRuleCall_2_0(), semanticObject.getIndependentVar());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     levelId=ct_BlockNameType
	 */
	protected void sequence_ct_levelId(EObject context, ct_levelId semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_levelId_LevelId()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_levelId_LevelId()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_levelIdAccess().getLevelIdCt_BlockNameTypeParserRuleCall_2_0(), semanticObject.getLevelId());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     symbId=ct_SymbolNameType
	 */
	protected void sequence_ct_symbId(EObject context, ct_symbId semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_symbId_SymbId()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_symbId_SymbId()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_symbIdAccess().getSymbIdCt_SymbolNameTypeParserRuleCall_2_0(), semanticObject.getSymbId());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     symbolType=ct_SymbolTypeType
	 */
	protected void sequence_ct_symbolType(EObject context, ct_symbolType semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_symbolType_SymbolType()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_symbolType_SymbolType()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_symbolTypeAccess().getSymbolTypeCt_SymbolTypeTypeParserRuleCall_2_0(), semanticObject.getSymbolType());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     writtenVersion=XS_STRING
	 */
	protected void sequence_ct_writtenVersion(EObject context, ct_writtenVersion semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_writtenVersion_WrittenVersion()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_writtenVersion_WrittenVersion()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_writtenVersionAccess().getWrittenVersionXS_STRINGParserRuleCall_2_0(), semanticObject.getWrittenVersion());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     xmlns=XS_STRING
	 */
	protected void sequence_ct_xmlns(EObject context, ct_xmlns semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getct_xmlns_Xmlns()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getct_xmlns_Xmlns()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCt_xmlnsAccess().getXmlnsXS_STRINGParserRuleCall_3_0(), semanticObject.getXmlns());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_Rhs)
	 */
	protected void sequence_design_AmountType(EObject context, design_AmountType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_ScalarRhs)
	 */
	protected void sequence_design_BeginType(EObject context, design_BeginType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* DoseAmount=design_DoseAmountType SteadyState=design_SteadyStateType? DosingTimes=design_DosingTimesType?)
	 */
	protected void sequence_design_BolusType(EObject context, design_BolusType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=design_DosingVariableType)
	 */
	protected void sequence_design_DoseAmountType(EObject context, design_DoseAmountType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=Math_symbId block=Math_block? xmlns+=ct_xmlns*)
	 */
	protected void sequence_design_DoseVarType(EObject context, design_DoseVarType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=design_DosingVariableType)
	 */
	protected void sequence_design_DosingPeriodType(EObject context, design_DosingPeriodType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Bolus=design_BolusType? Infusion=design_InfusionType?)
	 */
	protected void sequence_design_DosingRegimenType(EObject context, design_DosingRegimenType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=design_DosingVariableType)
	 */
	protected void sequence_design_DosingStartType(EObject context, design_DosingStartType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_VectorType)
	 */
	protected void sequence_design_DosingTimesType(EObject context, design_DosingTimesType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (DoseVar=design_DoseVarType? TargetVar=design_TargetVarType? Amount=design_AmountType)
	 */
	protected void sequence_design_DosingVariableType(EObject context, design_DosingVariableType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=design_DosingVariableType)
	 */
	protected void sequence_design_DurationType(EObject context, design_DurationType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_ScalarRhs)
	 */
	protected void sequence_design_EndType(EObject context, design_EndType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=ct_id 
	 *         Name=ct_Name? 
	 *         xmlns+=ct_xmlns* 
	 *         TreatmentEpochRef+=design_TreatmentRefType* 
	 *         Washout+=design_WashoutType* 
	 *         Individuals=design_IdividualsType
	 *     )
	 */
	protected void sequence_design_GroupType(EObject context, design_GroupType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=ct_symbId Name=ct_Name? levelId=ct_levelId xmlns+=ct_xmlns* Expr=ct_Rhs)
	 */
	protected void sequence_design_IdividualsType(EObject context, design_IdividualsType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         xmlns+=ct_xmlns* 
	 *         DoseAmount=design_DoseAmountType 
	 *         SteadyState=design_SteadyStateType? 
	 *         DosingTimes=design_DosingTimesType? 
	 *         Duration=design_DurationType
	 *     )
	 */
	protected void sequence_design_InfusionType(EObject context, design_InfusionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         symbId=ct_symbId 
	 *         Name=ct_Name? 
	 *         levelId=ct_levelId 
	 *         xmlns+=ct_xmlns* 
	 *         Begin=design_BeginType 
	 *         End=design_EndType
	 *     )
	 */
	protected void sequence_design_OccasionType(EObject context, design_OccasionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_ScalarRhs)
	 */
	protected void sequence_design_StartType(EObject context, design_StartType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* DosingStart=design_DosingStartType DosingPeriod=design_DosingPeriodType)
	 */
	protected void sequence_design_SteadyStateType(EObject context, design_SteadyStateType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=Math_symbId block=Math_block? xmlns+=ct_xmlns*)
	 */
	protected void sequence_design_TargetVarType(EObject context, design_TargetVarType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         symbId=ct_symbId 
	 *         Name=ct_Name? 
	 *         xmlns+=ct_xmlns* 
	 *         TreatmentRef+=design_TreatmentRefType+ 
	 *         Start=design_StartType 
	 *         End=design_EndType 
	 *         Occasion=design_OccasionType
	 *     )
	 */
	protected void sequence_design_TreatmentEpohType(EObject context, design_TreatmentEpohType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (idRef=Math_BlockNameType xmlns+=ct_xmlns*)
	 */
	protected void sequence_design_TreatmentRefType(EObject context, design_TreatmentRefType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=Math_symbId Name=ct_Name? xmlns+=ct_xmlns* DosingRegimen+=design_DosingRegimenType+)
	 */
	protected void sequence_design_TreatmentType(EObject context, design_TreatmentType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Treatment+=design_TreatmentType+ TreatmentEpoch+=design_TreatmentEpohType+ Group+=design_GroupType+)
	 */
	protected void sequence_design_TrialDesignType(EObject context, design_TrialDesignType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns*)
	 */
	protected void sequence_design_WashoutType(EObject context, design_WashoutType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_ScalarRhs)
	 */
	protected void sequence_mSteps_InitialEstimateType(EObject context, mSteps_InitialEstimateType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_ScalarRhs)
	 */
	protected void sequence_mSteps_LowerBoundType(EObject context, mSteps_LowerBoundType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_ScalarRhs)
	 */
	protected void sequence_mSteps_UpperBoundType(EObject context, mSteps_UpperBoundType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         symbID=Math_symbId 
	 *         fixed=mSteps_fixed 
	 *         block=Math_block? 
	 *         xmlns+=ct_xmlns* 
	 *         InitialEstimate=mSteps_InitialEstimateType 
	 *         LowerBound=mSteps_LowerBoundType 
	 *         UpperBound=mSteps_UpperBoundType
	 *     )
	 */
	protected void sequence_mSteps_VariableEstimateType(EObject context, mSteps_VariableEstimateType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (fixed=Math_TRUE | fixed=Math_FALSE)
	 */
	protected void sequence_mSteps_fixed(EObject context, mSteps_fixed semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_ScalarRhs)
	 */
	protected void sequence_mdefCovarianceType(EObject context, mdefCovarianceType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Category+=mdef_CategoryType*)
	 */
	protected void sequence_mdef_CategoricalType(EObject context, mdef_CategoricalType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (catId=ct_catId Name=ct_Name? xmlns+=ct_xmlns* Probability=mdef_ProbabilityType)
	 */
	protected void sequence_mdef_CategoryType(EObject context, mdef_CategoryType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Distribution=uncert_DistributionType Transformation=mdef_TransformationType)
	 */
	protected void sequence_mdef_ContinuousType(EObject context, mdef_ContinuousType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_ScalarRhs)
	 */
	protected void sequence_mdef_CorrelationCoefficientType(EObject context, mdef_CorrelationCoefficientType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         levelId=ct_levelId 
	 *         xmlns+=ct_xmlns* 
	 *         ParamVar1=Math_VarType 
	 *         ParamVar2=Math_VarType 
	 *         CorrelationCoefficient=mdef_CorrelationCoefficientType? 
	 *         Covariance=mdefCovarianceType?
	 *     )
	 */
	protected void sequence_mdef_CorrelationType(EObject context, mdef_CorrelationType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=ct_id Name=ct_Name? xmlns+=ct_xmlns* Parameter+=mdef_ParameterType* Covariate+=mdef_CovariateVariabilityType+)
	 */
	protected void sequence_mdef_CovariateModelType(EObject context, mdef_CovariateModelType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Var=Math_VarType FixedEffect+=mdef_FixedEffectType+)
	 */
	protected void sequence_mdef_CovariateType(EObject context, mdef_CovariateType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         Name=ct_Name? 
	 *         symbId=ct_symbId 
	 *         transformation=mdef_transformation? 
	 *         xmlns+=ct_xmlns* 
	 *         (Continuous=mdef_ContinuousType? Categorical=mdef_CategoricalType?)?
	 *     )
	 */
	protected void sequence_mdef_CovariateVariabilityType(EObject context, mdef_CovariateVariabilityType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Var=Math_VarType Category=mdef_CategoryType?)
	 */
	protected void sequence_mdef_FixedEffectType(EObject context, mdef_FixedEffectType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbolPtr=XS_STRING xmlns+=ct_xmlns* Var=Math_VarType)
	 */
	protected void sequence_mdef_ImportLinkType(EObject context, mdef_ImportLinkType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (sbml='sbml' | pharmml='pharmml')
	 */
	protected void sequence_mdef_ImportResourceTypeType(EObject context, mdef_ImportResourceTypeType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (resource=XS_ANY_URI resourceType=mdef_ImportResourceTypeType source=XS_TOKEN xmlns+=ct_xmlns* Link+=mdef_ImportLinkType+)
	 */
	protected void sequence_mdef_ImportType(EObject context, mdef_ImportType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbID=Math_symbId block=Math_block? xmlns+=ct_xmlns* Expr=ct_Rhs)
	 */
	protected void sequence_mdef_InitialConditionType(EObject context, mdef_InitialConditionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         xmlns+=ct_xmlns* 
	 *         VariabilityLevel+=mdef_VariabilityLevelDefnType* 
	 *         CovariateModel=mdef_CovariateModelType? 
	 *         ParameterModel=mdef_ParameterModelType? 
	 *         StructuraModel+=mdef_StructuralModelType* 
	 *         ObservationModel=mdef_ObservationModelType?
	 *     )
	 */
	protected void sequence_mdef_ModelDefinitionType(EObject context, mdef_ModelDefinitionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=ct_id Name=ct_Name? xmlns+=ct_xmlns* Parameter+=mdef_ParameterType* Continuous+=mdef_ContinuousType*)
	 */
	protected void sequence_mdef_ObservationModelType(EObject context, mdef_ObservationModelType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=ct_id Name=ct_Name? xmlns+=ct_xmlns* Parameter+=mdef_ParameterType+ Correlation+=mdef_CorrelationType*)
	 */
	protected void sequence_mdef_ParameterModelType(EObject context, mdef_ParameterModelType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (log='"log"' | logit='"logit"' | none='"none"')
	 */
	protected void sequence_mdef_ParameterTypeName(EObject context, mdef_ParameterTypeName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         Name=ct_Name? 
	 *         symbId=Math_symbId 
	 *         transformation=mdef_transformation? 
	 *         xmlns+=ct_xmlns* 
	 *         (Var=Math_VarType? Scalar=Math_ScalarType? Equation=Math_EquationType? RandomEffect+=mdef_RandomEffectType* Covariate+=mdef_CovariateType*)?
	 *     )
	 */
	protected void sequence_mdef_ParameterType(EObject context, mdef_ParameterType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_Rhs)
	 */
	protected void sequence_mdef_ProbabilityType(EObject context, mdef_ProbabilityType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (levelId=ct_levelId Name=ct_Name? symbId=ct_symbId xmlns+=ct_xmlns* Distribution=uncert_DistributionType)
	 */
	protected void sequence_mdef_RandomEffectType(EObject context, mdef_RandomEffectType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=ct_id 
	 *         xmlns+=ct_xmlns* 
	 *         Description=ct_AnnotationType 
	 *         Parameter+=mdef_ParameterType* 
	 *         Variable+=ct_VariableDefinitionType* 
	 *         Import+=mdef_ImportType* 
	 *         InitiaCondition+=mdef_InitialConditionType*
	 *     )
	 */
	protected void sequence_mdef_StructuralModelType(EObject context, mdef_StructuralModelType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Expr=ct_Rhs
	 */
	protected void sequence_mdef_TransformationType(EObject context, mdef_TransformationType semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getmdef_TransformationType_Expr()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getmdef_TransformationType_Expr()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMdef_TransformationTypeAccess().getExprCt_RhsParserRuleCall_3_0(), semanticObject.getExpr());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (id=ct_id Name=ct_Name? xmlns+=ct_xmlns*)
	 */
	protected void sequence_mdef_VariabilityLevelDefnType(EObject context, mdef_VariabilityLevelDefnType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     transformation=mdef_ParameterTypeName
	 */
	protected void sequence_mdef_transformation(EObject context, mdef_transformation semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getmdef_transformation_Transformation()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getmdef_transformation_Transformation()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMdef_transformationAccess().getTransformationMdef_ParameterTypeNameParserRuleCall_2_0(), semanticObject.getTransformation());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=Math_symbId block=Math_block? xmlns+=ct_xmlns* Expr=ct_Rhs)
	 */
	protected void sequence_msteps_AssignType(EObject context, msteps_AssignType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (idRef=ct_BlockNameType xmlns+=ct_xmlns*)
	 */
	protected void sequence_msteps_DependantStepType(EObject context, msteps_DependantStepType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (opType=Math_EstimationOperationType xmlns+=ct_xmlns*)
	 */
	protected void sequence_msteps_EstimationOperationType(EObject context, msteps_EstimationOperationType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=ct_id 
	 *         xmlns+=ct_xmlns* 
	 *         Description=ct_AnnotationType? 
	 *         InitialValue+=msteps_InitialValueType+ 
	 *         ObjectiveDataSet=msteps_ObjectiveDataSetType 
	 *         ParametersToEstimate=msteps_ParametersToEstimateType 
	 *         EstimationOperation+=msteps_EstimationOperationType+
	 *     )
	 */
	protected void sequence_msteps_EstimationStep(EObject context, msteps_EstimationStep semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=Math_symbId xmlns+=ct_xmlns* Content=ct_VariableAssignmentType)
	 */
	protected void sequence_msteps_InitialValueType(EObject context, msteps_InitialValueType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         columnName=ct_SymbolNameType 
	 *         xmlns+=ct_xmlns* 
	 *         Var=Math_VarType? 
	 *         UseVariabilityLevel=msteps_UseVariabilityLevel? 
	 *         UseVariabilityNode=msteps_UseVariabilityNode? 
	 *         DoseVar=design_DoseVarType? 
	 *         TargetVar=msteps_TargetVarType? 
	 *         Assign=msteps_AssignType? 
	 *         Output=msteps_OutputType? 
	 *         Restriction=ct_RestrictionType
	 *     )
	 */
	protected void sequence_msteps_MappingType(EObject context, msteps_MappingType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         xmlns+=ct_xmlns* 
	 *         Variable+=ct_VariableDefinitionType* 
	 *         EstimationStep+=msteps_EstimationStep* 
	 *         SimulationStep+=msteps_SimulationStep* 
	 *         StepDependencies=msteps_StepDependenciesType
	 *     )
	 */
	protected void sequence_msteps_ModelingSteps(EObject context, msteps_ModelingSteps semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (dataSetRef=ct_SymbolNameType xmlns+=ct_xmlns* Mapping+=msteps_MappingType+)
	 */
	protected void sequence_msteps_ObjectiveDataSetType(EObject context, msteps_ObjectiveDataSetType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Timepoints=msteps_TimepointsType Output+=msteps_OutputType* WriteTo+=msteps_WriteToType*)
	 */
	protected void sequence_msteps_ObservationsType(EObject context, msteps_ObservationsType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Vars=Math_VarType)
	 */
	protected void sequence_msteps_OutputType(EObject context, msteps_OutputType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Variable+=mSteps_VariableEstimateType)
	 */
	protected void sequence_msteps_ParametersToEstimateType(EObject context, msteps_ParametersToEstimateType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (dataSetRef=ct_SymbolNameType xmlns+=ct_xmlns* Mapping+=msteps_MappingType+)
	 */
	protected void sequence_msteps_SimDataSetType(EObject context, msteps_SimDataSetType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         id=ct_id 
	 *         xmlns+=ct_xmlns* 
	 *         Description=ct_AnnotationType? 
	 *         Replicates=ct_ReplicatesType 
	 *         InitialValue+=msteps_InitialValueType+ 
	 *         SimDataSet=msteps_SimDataSetType 
	 *         Observations=msteps_ObservationsType
	 *     )
	 */
	protected void sequence_msteps_SimulationStep(EObject context, msteps_SimulationStep semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Description=ct_AnnotationType? Step+=msteps_StepType+)
	 */
	protected void sequence_msteps_StepDependenciesType(EObject context, msteps_StepDependenciesType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (idRef=XS_STRING xmlns+=ct_xmlns* DependantStep+=msteps_DependantStepType+)
	 */
	protected void sequence_msteps_StepType(EObject context, msteps_StepType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=Math_symbId block=Math_block? xmlns+=ct_xmlns*)
	 */
	protected void sequence_msteps_TargetVarType(EObject context, msteps_TargetVarType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=ct_Rhs)
	 */
	protected void sequence_msteps_TimepointsType(EObject context, msteps_TimepointsType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (levelId=ct_BlockNameType xmlns+=ct_xmlns*)
	 */
	protected void sequence_msteps_UseVariabilityLevel(EObject context, msteps_UseVariabilityLevel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbId=Math_symbId block=Math_block? xmlns+=ct_xmlns*)
	 */
	protected void sequence_msteps_UseVariabilityNode(EObject context, msteps_UseVariabilityNode semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Var=Math_VarType Mapping+=msteps_MappingType+)
	 */
	protected void sequence_msteps_WriteToType(EObject context, msteps_WriteToType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=uncert_DistributionParamType)
	 */
	protected void sequence_uncertDegreesOfFreedom(EObject context, uncertDegreesOfFreedom semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=uncert_DistributionParamType)
	 */
	protected void sequence_uncert_CoefficientOfVariance(EObject context, uncert_CoefficientOfVariance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (Equation=Math_EquationType | Scalar=Math_ScalarType | Var=Math_VarType)
	 */
	protected void sequence_uncert_DistributionParamType(EObject context, uncert_DistributionParamType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         xmlns+=ct_xmlns* 
	 *         writtenVersion=ct_writtenVersion 
	 *         Normal=uncert_Normal? 
	 *         PDF=uncert_PDF? 
	 *         Poison=uncert_Poison? 
	 *         Student=uncert_Student_t? 
	 *         Uniform=uncert_Uniform?
	 *     )
	 */
	protected void sequence_uncert_DistributionType(EObject context, uncert_DistributionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=uncert_DistributionParamType)
	 */
	protected void sequence_uncert_Location(EObject context, uncert_Location semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     Expr=uncert_DistributionParamType
	 */
	protected void sequence_uncert_MeanParamType(EObject context, uncert_MeanParamType semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PharmaMLPackage.eINSTANCE.getuncert_MeanParamType_Expr()) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PharmaMLPackage.eINSTANCE.getuncert_MeanParamType_Expr()));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getUncert_MeanParamTypeAccess().getExprUncert_DistributionParamTypeParserRuleCall_0(), semanticObject.getExpr());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=uncert_MeanParamType)
	 */
	protected void sequence_uncert_Mean(EObject context, uncert_Mean semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Mean=uncert_Mean StdDev=uncert_StdDev? Variance=uncert_Variance? CoefficientOfVariance=uncert_CoefficientOfVariance?)
	 */
	protected void sequence_uncert_Normal(EObject context, uncert_Normal semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* FunctionParameter+=uncert_ParameterDefinitionType* Equation=Math_EquationType)
	 */
	protected void sequence_uncert_PDF(EObject context, uncert_PDF semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (id=Math_id Name=ct_Name? xmlns+=ct_xmlns*)
	 */
	protected void sequence_uncert_ParameterDefinitionType(EObject context, uncert_ParameterDefinitionType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Rate=uncert_Rate)
	 */
	protected void sequence_uncert_Poison(EObject context, uncert_Poison semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=uncert_DistributionParamType)
	 */
	protected void sequence_uncert_Rate(EObject context, uncert_Rate semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=uncert_DistributionParamType)
	 */
	protected void sequence_uncert_Scale(EObject context, uncert_Scale semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=uncert_DistributionParamType)
	 */
	protected void sequence_uncert_StdDev(EObject context, uncert_StdDev semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Location=uncert_Location Scale=uncert_Scale DegreesOfFreedom=uncertDegreesOfFreedom)
	 */
	protected void sequence_uncert_Student_t(EObject context, uncert_Student_t semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns*)
	 */
	protected void sequence_uncert_Uniform(EObject context, uncert_Uniform semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (xmlns+=ct_xmlns* Expr=uncert_DistributionParamType)
	 */
	protected void sequence_uncert_Variance(EObject context, uncert_Variance semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}

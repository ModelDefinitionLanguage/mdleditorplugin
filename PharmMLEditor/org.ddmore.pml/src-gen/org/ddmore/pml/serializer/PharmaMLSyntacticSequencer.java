package org.ddmore.pml.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.ddmore.pml.services.PharmaMLGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class PharmaMLSyntacticSequencer extends AbstractSyntacticSequencer {

	protected PharmaMLGrammarAccess grammarAccess;
	protected AbstractElementAlias match_Math_ConstantType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_ConstantKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__;
	protected AbstractElementAlias match_Math_ScalarType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_ScalarKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__;
	protected AbstractElementAlias match_Math_StringType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_StringKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__;
	protected AbstractElementAlias match_Math_VarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__;
	protected AbstractElementAlias match_Mml_FuncParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_2_FunctionArgumentKeyword_5_1_3_TAG_CLOSEParserRuleCall_5_1_4__;
	protected AbstractElementAlias match_ct_ColumnType_TAG_CLOSE2ParserRuleCall_9_0_or___TAG_CLOSEParserRuleCall_9_1_0_TAG_OPEN2ParserRuleCall_9_1_1_ColumnKeyword_9_1_2_TAG_CLOSEParserRuleCall_9_1_3__;
	protected AbstractElementAlias match_ct_ExternalSourceType_TAG_CLOSE2ParserRuleCall_11_0_or___TAG_CLOSEParserRuleCall_11_1_0_TAG_OPEN2ParserRuleCall_11_1_1_ExternalSourceKeyword_11_1_2_TAG_CLOSEParserRuleCall_11_1_3__;
	protected AbstractElementAlias match_ct_InternalSourceType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_InternalSourceKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__;
	protected AbstractElementAlias match_ct_SequenceType_TAG_CLOSE2ParserRuleCall_15_0_or___TAG_CLOSEParserRuleCall_15_1_0_TAG_OPEN2ParserRuleCall_15_1_1_SequenceKeyword_15_1_2_TAG_CLOSEParserRuleCall_15_1_3__;
	protected AbstractElementAlias match_ct_xmlns___ColonKeyword_1_0_IDTerminalRuleCall_1_1__q;
	protected AbstractElementAlias match_design_DoseVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_DoseVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__;
	protected AbstractElementAlias match_design_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__;
	protected AbstractElementAlias match_design_TreatmentRefType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_TreatmentRefKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__;
	protected AbstractElementAlias match_design_WashoutType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_WashoutKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__;
	protected AbstractElementAlias match_mdef_CovariateVariabilityType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_3_CovariateKeyword_6_1_4_TAG_CLOSEParserRuleCall_6_1_5__;
	protected AbstractElementAlias match_mdef_ParameterType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_6_ParameterKeyword_6_1_7_TAG_CLOSEParserRuleCall_6_1_8__;
	protected AbstractElementAlias match_mdef_VariabilityLevelDefnType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VariabilityLevelKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__;
	protected AbstractElementAlias match_msteps_DependantStepType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_DependantStepKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__;
	protected AbstractElementAlias match_msteps_EstimationOperationType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_EstimationOperationKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__;
	protected AbstractElementAlias match_msteps_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__;
	protected AbstractElementAlias match_msteps_UseVariabilityLevel_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_UseVariabilityLevelKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__;
	protected AbstractElementAlias match_msteps_UseVariabilityNode_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_UseVariabilityNodeKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__;
	protected AbstractElementAlias match_uncert_ParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_FunctionParameterKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__;
	protected AbstractElementAlias match_uncert_Uniform_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_UniformKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (PharmaMLGrammarAccess) access;
		match_Math_ConstantType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_ConstantKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMath_ConstantTypeAccess().getTAG_CLOSEParserRuleCall_6_1_0()), new TokenAlias(false, false, grammarAccess.getMath_ConstantTypeAccess().getTAG_OPEN2ParserRuleCall_6_1_1()), new TokenAlias(false, false, grammarAccess.getMath_ConstantTypeAccess().getConstantKeyword_6_1_2()), new TokenAlias(false, false, grammarAccess.getMath_ConstantTypeAccess().getTAG_CLOSEParserRuleCall_6_1_3())), new TokenAlias(false, false, grammarAccess.getMath_ConstantTypeAccess().getTAG_CLOSE2ParserRuleCall_6_0()));
		match_Math_ScalarType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_ScalarKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMath_ScalarTypeAccess().getTAG_CLOSEParserRuleCall_4_1_0()), new TokenAlias(false, false, grammarAccess.getMath_ScalarTypeAccess().getTAG_OPEN2ParserRuleCall_4_1_1()), new TokenAlias(false, false, grammarAccess.getMath_ScalarTypeAccess().getScalarKeyword_4_1_2()), new TokenAlias(false, false, grammarAccess.getMath_ScalarTypeAccess().getTAG_CLOSEParserRuleCall_4_1_3())), new TokenAlias(false, false, grammarAccess.getMath_ScalarTypeAccess().getTAG_CLOSE2ParserRuleCall_4_0()));
		match_Math_StringType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_StringKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMath_StringTypeAccess().getTAG_CLOSEParserRuleCall_4_1_0()), new TokenAlias(false, false, grammarAccess.getMath_StringTypeAccess().getTAG_OPEN2ParserRuleCall_4_1_1()), new TokenAlias(false, false, grammarAccess.getMath_StringTypeAccess().getStringKeyword_4_1_2()), new TokenAlias(false, false, grammarAccess.getMath_StringTypeAccess().getTAG_CLOSEParserRuleCall_4_1_3())), new TokenAlias(false, false, grammarAccess.getMath_StringTypeAccess().getTAG_CLOSE2ParserRuleCall_4_0()));
		match_Math_VarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMath_VarTypeAccess().getTAG_CLOSEParserRuleCall_5_1_0()), new TokenAlias(false, false, grammarAccess.getMath_VarTypeAccess().getTAG_OPEN2ParserRuleCall_5_1_1()), new TokenAlias(false, false, grammarAccess.getMath_VarTypeAccess().getVarKeyword_5_1_2()), new TokenAlias(false, false, grammarAccess.getMath_VarTypeAccess().getTAG_CLOSEParserRuleCall_5_1_3())), new TokenAlias(false, false, grammarAccess.getMath_VarTypeAccess().getTAG_CLOSE2ParserRuleCall_5_0()));
		match_Mml_FuncParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_2_FunctionArgumentKeyword_5_1_3_TAG_CLOSEParserRuleCall_5_1_4__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMml_FuncParameterDefinitionTypeAccess().getTAG_CLOSEParserRuleCall_5_1_0()), new TokenAlias(false, false, grammarAccess.getMml_FuncParameterDefinitionTypeAccess().getTAG_OPEN2ParserRuleCall_5_1_2()), new TokenAlias(false, false, grammarAccess.getMml_FuncParameterDefinitionTypeAccess().getFunctionArgumentKeyword_5_1_3()), new TokenAlias(false, false, grammarAccess.getMml_FuncParameterDefinitionTypeAccess().getTAG_CLOSEParserRuleCall_5_1_4())), new TokenAlias(false, false, grammarAccess.getMml_FuncParameterDefinitionTypeAccess().getTAG_CLOSE2ParserRuleCall_5_0()));
		match_ct_ColumnType_TAG_CLOSE2ParserRuleCall_9_0_or___TAG_CLOSEParserRuleCall_9_1_0_TAG_OPEN2ParserRuleCall_9_1_1_ColumnKeyword_9_1_2_TAG_CLOSEParserRuleCall_9_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getCt_ColumnTypeAccess().getTAG_CLOSEParserRuleCall_9_1_0()), new TokenAlias(false, false, grammarAccess.getCt_ColumnTypeAccess().getTAG_OPEN2ParserRuleCall_9_1_1()), new TokenAlias(false, false, grammarAccess.getCt_ColumnTypeAccess().getColumnKeyword_9_1_2()), new TokenAlias(false, false, grammarAccess.getCt_ColumnTypeAccess().getTAG_CLOSEParserRuleCall_9_1_3())), new TokenAlias(false, false, grammarAccess.getCt_ColumnTypeAccess().getTAG_CLOSE2ParserRuleCall_9_0()));
		match_ct_ExternalSourceType_TAG_CLOSE2ParserRuleCall_11_0_or___TAG_CLOSEParserRuleCall_11_1_0_TAG_OPEN2ParserRuleCall_11_1_1_ExternalSourceKeyword_11_1_2_TAG_CLOSEParserRuleCall_11_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getCt_ExternalSourceTypeAccess().getTAG_CLOSEParserRuleCall_11_1_0()), new TokenAlias(false, false, grammarAccess.getCt_ExternalSourceTypeAccess().getTAG_OPEN2ParserRuleCall_11_1_1()), new TokenAlias(false, false, grammarAccess.getCt_ExternalSourceTypeAccess().getExternalSourceKeyword_11_1_2()), new TokenAlias(false, false, grammarAccess.getCt_ExternalSourceTypeAccess().getTAG_CLOSEParserRuleCall_11_1_3())), new TokenAlias(false, false, grammarAccess.getCt_ExternalSourceTypeAccess().getTAG_CLOSE2ParserRuleCall_11_0()));
		match_ct_InternalSourceType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_InternalSourceKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getCt_InternalSourceTypeAccess().getTAG_CLOSEParserRuleCall_5_1_0()), new TokenAlias(false, false, grammarAccess.getCt_InternalSourceTypeAccess().getTAG_OPEN2ParserRuleCall_5_1_1()), new TokenAlias(false, false, grammarAccess.getCt_InternalSourceTypeAccess().getInternalSourceKeyword_5_1_2()), new TokenAlias(false, false, grammarAccess.getCt_InternalSourceTypeAccess().getTAG_CLOSEParserRuleCall_5_1_3())), new TokenAlias(false, false, grammarAccess.getCt_InternalSourceTypeAccess().getTAG_CLOSE2ParserRuleCall_5_0()));
		match_ct_SequenceType_TAG_CLOSE2ParserRuleCall_15_0_or___TAG_CLOSEParserRuleCall_15_1_0_TAG_OPEN2ParserRuleCall_15_1_1_SequenceKeyword_15_1_2_TAG_CLOSEParserRuleCall_15_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getCt_SequenceTypeAccess().getTAG_CLOSEParserRuleCall_15_1_0()), new TokenAlias(false, false, grammarAccess.getCt_SequenceTypeAccess().getTAG_OPEN2ParserRuleCall_15_1_1()), new TokenAlias(false, false, grammarAccess.getCt_SequenceTypeAccess().getSequenceKeyword_15_1_2()), new TokenAlias(false, false, grammarAccess.getCt_SequenceTypeAccess().getTAG_CLOSEParserRuleCall_15_1_3())), new TokenAlias(false, false, grammarAccess.getCt_SequenceTypeAccess().getTAG_CLOSE2ParserRuleCall_15_0()));
		match_ct_xmlns___ColonKeyword_1_0_IDTerminalRuleCall_1_1__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getCt_xmlnsAccess().getColonKeyword_1_0()), new TokenAlias(false, false, grammarAccess.getCt_xmlnsAccess().getIDTerminalRuleCall_1_1()));
		match_design_DoseVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_DoseVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getDesign_DoseVarTypeAccess().getTAG_CLOSEParserRuleCall_5_1_0()), new TokenAlias(false, false, grammarAccess.getDesign_DoseVarTypeAccess().getTAG_OPEN2ParserRuleCall_5_1_1()), new TokenAlias(false, false, grammarAccess.getDesign_DoseVarTypeAccess().getDoseVarKeyword_5_1_2()), new TokenAlias(false, false, grammarAccess.getDesign_DoseVarTypeAccess().getTAG_CLOSEParserRuleCall_5_1_3())), new TokenAlias(false, false, grammarAccess.getDesign_DoseVarTypeAccess().getTAG_CLOSE2ParserRuleCall_5_0()));
		match_design_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getDesign_TargetVarTypeAccess().getTAG_CLOSEParserRuleCall_5_1_0()), new TokenAlias(false, false, grammarAccess.getDesign_TargetVarTypeAccess().getTAG_OPEN2ParserRuleCall_5_1_1()), new TokenAlias(false, false, grammarAccess.getDesign_TargetVarTypeAccess().getTargetVarKeyword_5_1_2()), new TokenAlias(false, false, grammarAccess.getDesign_TargetVarTypeAccess().getTAG_CLOSEParserRuleCall_5_1_3())), new TokenAlias(false, false, grammarAccess.getDesign_TargetVarTypeAccess().getTAG_CLOSE2ParserRuleCall_5_0()));
		match_design_TreatmentRefType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_TreatmentRefKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getDesign_TreatmentRefTypeAccess().getTAG_CLOSEParserRuleCall_6_1_0()), new TokenAlias(false, false, grammarAccess.getDesign_TreatmentRefTypeAccess().getTAG_OPEN2ParserRuleCall_6_1_1()), new TokenAlias(false, false, grammarAccess.getDesign_TreatmentRefTypeAccess().getTreatmentRefKeyword_6_1_2()), new TokenAlias(false, false, grammarAccess.getDesign_TreatmentRefTypeAccess().getTAG_CLOSEParserRuleCall_6_1_3())), new TokenAlias(false, false, grammarAccess.getDesign_TreatmentRefTypeAccess().getTAG_CLOSE2ParserRuleCall_6_0()));
		match_design_WashoutType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_WashoutKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getDesign_WashoutTypeAccess().getTAG_CLOSEParserRuleCall_4_1_0()), new TokenAlias(false, false, grammarAccess.getDesign_WashoutTypeAccess().getTAG_OPEN2ParserRuleCall_4_1_1()), new TokenAlias(false, false, grammarAccess.getDesign_WashoutTypeAccess().getWashoutKeyword_4_1_2()), new TokenAlias(false, false, grammarAccess.getDesign_WashoutTypeAccess().getTAG_CLOSEParserRuleCall_4_1_3())), new TokenAlias(false, false, grammarAccess.getDesign_WashoutTypeAccess().getTAG_CLOSE2ParserRuleCall_4_0()));
		match_mdef_CovariateVariabilityType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_3_CovariateKeyword_6_1_4_TAG_CLOSEParserRuleCall_6_1_5__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMdef_CovariateVariabilityTypeAccess().getTAG_CLOSEParserRuleCall_6_1_0()), new TokenAlias(false, false, grammarAccess.getMdef_CovariateVariabilityTypeAccess().getTAG_OPEN2ParserRuleCall_6_1_3()), new TokenAlias(false, false, grammarAccess.getMdef_CovariateVariabilityTypeAccess().getCovariateKeyword_6_1_4()), new TokenAlias(false, false, grammarAccess.getMdef_CovariateVariabilityTypeAccess().getTAG_CLOSEParserRuleCall_6_1_5())), new TokenAlias(false, false, grammarAccess.getMdef_CovariateVariabilityTypeAccess().getTAG_CLOSE2ParserRuleCall_6_0()));
		match_mdef_ParameterType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_6_ParameterKeyword_6_1_7_TAG_CLOSEParserRuleCall_6_1_8__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMdef_ParameterTypeAccess().getTAG_CLOSEParserRuleCall_6_1_0()), new TokenAlias(false, false, grammarAccess.getMdef_ParameterTypeAccess().getTAG_OPEN2ParserRuleCall_6_1_6()), new TokenAlias(false, false, grammarAccess.getMdef_ParameterTypeAccess().getParameterKeyword_6_1_7()), new TokenAlias(false, false, grammarAccess.getMdef_ParameterTypeAccess().getTAG_CLOSEParserRuleCall_6_1_8())), new TokenAlias(false, false, grammarAccess.getMdef_ParameterTypeAccess().getTAG_CLOSE2ParserRuleCall_6_0()));
		match_mdef_VariabilityLevelDefnType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VariabilityLevelKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMdef_VariabilityLevelDefnTypeAccess().getTAG_CLOSEParserRuleCall_5_1_0()), new TokenAlias(false, false, grammarAccess.getMdef_VariabilityLevelDefnTypeAccess().getTAG_OPEN2ParserRuleCall_5_1_1()), new TokenAlias(false, false, grammarAccess.getMdef_VariabilityLevelDefnTypeAccess().getVariabilityLevelKeyword_5_1_2()), new TokenAlias(false, false, grammarAccess.getMdef_VariabilityLevelDefnTypeAccess().getTAG_CLOSEParserRuleCall_5_1_3())), new TokenAlias(false, false, grammarAccess.getMdef_VariabilityLevelDefnTypeAccess().getTAG_CLOSE2ParserRuleCall_5_0()));
		match_msteps_DependantStepType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_DependantStepKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMsteps_DependantStepTypeAccess().getTAG_CLOSEParserRuleCall_6_1_0()), new TokenAlias(false, false, grammarAccess.getMsteps_DependantStepTypeAccess().getTAG_OPEN2ParserRuleCall_6_1_1()), new TokenAlias(false, false, grammarAccess.getMsteps_DependantStepTypeAccess().getDependantStepKeyword_6_1_2()), new TokenAlias(false, false, grammarAccess.getMsteps_DependantStepTypeAccess().getTAG_CLOSEParserRuleCall_6_1_3())), new TokenAlias(false, false, grammarAccess.getMsteps_DependantStepTypeAccess().getTAG_CLOSE2ParserRuleCall_6_0()));
		match_msteps_EstimationOperationType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_EstimationOperationKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMsteps_EstimationOperationTypeAccess().getTAG_CLOSEParserRuleCall_6_1_0()), new TokenAlias(false, false, grammarAccess.getMsteps_EstimationOperationTypeAccess().getTAG_OPEN2ParserRuleCall_6_1_1()), new TokenAlias(false, false, grammarAccess.getMsteps_EstimationOperationTypeAccess().getEstimationOperationKeyword_6_1_2()), new TokenAlias(false, false, grammarAccess.getMsteps_EstimationOperationTypeAccess().getTAG_CLOSEParserRuleCall_6_1_3())), new TokenAlias(false, false, grammarAccess.getMsteps_EstimationOperationTypeAccess().getTAG_CLOSE2ParserRuleCall_6_0()));
		match_msteps_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMsteps_TargetVarTypeAccess().getTAG_CLOSEParserRuleCall_5_1_0()), new TokenAlias(false, false, grammarAccess.getMsteps_TargetVarTypeAccess().getTAG_OPEN2ParserRuleCall_5_1_1()), new TokenAlias(false, false, grammarAccess.getMsteps_TargetVarTypeAccess().getTargetVarKeyword_5_1_2()), new TokenAlias(false, false, grammarAccess.getMsteps_TargetVarTypeAccess().getTAG_CLOSEParserRuleCall_5_1_3())), new TokenAlias(false, false, grammarAccess.getMsteps_TargetVarTypeAccess().getTAG_CLOSE2ParserRuleCall_5_0()));
		match_msteps_UseVariabilityLevel_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_UseVariabilityLevelKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityLevelAccess().getTAG_CLOSEParserRuleCall_6_1_0()), new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityLevelAccess().getTAG_OPEN2ParserRuleCall_6_1_1()), new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityLevelAccess().getUseVariabilityLevelKeyword_6_1_2()), new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityLevelAccess().getTAG_CLOSEParserRuleCall_6_1_3())), new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityLevelAccess().getTAG_CLOSE2ParserRuleCall_6_0()));
		match_msteps_UseVariabilityNode_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_UseVariabilityNodeKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityNodeAccess().getTAG_CLOSEParserRuleCall_5_1_0()), new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityNodeAccess().getTAG_OPEN2ParserRuleCall_5_1_1()), new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityNodeAccess().getUseVariabilityNodeKeyword_5_1_2()), new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityNodeAccess().getTAG_CLOSEParserRuleCall_5_1_3())), new TokenAlias(false, false, grammarAccess.getMsteps_UseVariabilityNodeAccess().getTAG_CLOSE2ParserRuleCall_5_0()));
		match_uncert_ParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_FunctionParameterKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getUncert_ParameterDefinitionTypeAccess().getTAG_CLOSEParserRuleCall_5_1_0()), new TokenAlias(false, false, grammarAccess.getUncert_ParameterDefinitionTypeAccess().getTAG_OPEN2ParserRuleCall_5_1_1()), new TokenAlias(false, false, grammarAccess.getUncert_ParameterDefinitionTypeAccess().getFunctionParameterKeyword_5_1_2()), new TokenAlias(false, false, grammarAccess.getUncert_ParameterDefinitionTypeAccess().getTAG_CLOSEParserRuleCall_5_1_3())), new TokenAlias(false, false, grammarAccess.getUncert_ParameterDefinitionTypeAccess().getTAG_CLOSE2ParserRuleCall_5_0()));
		match_uncert_Uniform_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_UniformKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__ = new AlternativeAlias(false, false, new GroupAlias(false, false, new TokenAlias(false, false, grammarAccess.getUncert_UniformAccess().getTAG_CLOSEParserRuleCall_4_1_0()), new TokenAlias(false, false, grammarAccess.getUncert_UniformAccess().getTAG_OPEN2ParserRuleCall_4_1_1()), new TokenAlias(false, false, grammarAccess.getUncert_UniformAccess().getUniformKeyword_4_1_2()), new TokenAlias(false, false, grammarAccess.getUncert_UniformAccess().getTAG_CLOSEParserRuleCall_4_1_3())), new TokenAlias(false, false, grammarAccess.getUncert_UniformAccess().getTAG_CLOSE2ParserRuleCall_4_0()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getIDRule())
			return getIDToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getTAG_CLOSERule())
			return getTAG_CLOSEToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getTAG_CLOSE2Rule())
			return getTAG_CLOSE2Token(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getTAG_OPENRule())
			return getTAG_OPENToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getTAG_OPEN2Rule())
			return getTAG_OPEN2Token(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * terminal ID  		: '^'?('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;
	 */
	protected String getIDToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	
	/**
	 * TAG_CLOSE: '>';
	 */
	protected String getTAG_CLOSEToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return ">";
	}
	
	/**
	 * TAG_CLOSE2: '/>';
	 */
	protected String getTAG_CLOSE2Token(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "/>";
	}
	
	/**
	 * TAG_OPEN: '<'Namespace?;
	 */
	protected String getTAG_OPENToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "<";
	}
	
	/**
	 * TAG_OPEN2: '</'Namespace?;
	 */
	protected String getTAG_OPEN2Token(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "</";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if(match_Math_ConstantType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_ConstantKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__.equals(syntax))
				emit_Math_ConstantType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_ConstantKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Math_ScalarType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_ScalarKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__.equals(syntax))
				emit_Math_ScalarType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_ScalarKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Math_StringType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_StringKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__.equals(syntax))
				emit_Math_StringType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_StringKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Math_VarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__.equals(syntax))
				emit_Math_VarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_Mml_FuncParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_2_FunctionArgumentKeyword_5_1_3_TAG_CLOSEParserRuleCall_5_1_4__.equals(syntax))
				emit_Mml_FuncParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_2_FunctionArgumentKeyword_5_1_3_TAG_CLOSEParserRuleCall_5_1_4__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_ct_ColumnType_TAG_CLOSE2ParserRuleCall_9_0_or___TAG_CLOSEParserRuleCall_9_1_0_TAG_OPEN2ParserRuleCall_9_1_1_ColumnKeyword_9_1_2_TAG_CLOSEParserRuleCall_9_1_3__.equals(syntax))
				emit_ct_ColumnType_TAG_CLOSE2ParserRuleCall_9_0_or___TAG_CLOSEParserRuleCall_9_1_0_TAG_OPEN2ParserRuleCall_9_1_1_ColumnKeyword_9_1_2_TAG_CLOSEParserRuleCall_9_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_ct_ExternalSourceType_TAG_CLOSE2ParserRuleCall_11_0_or___TAG_CLOSEParserRuleCall_11_1_0_TAG_OPEN2ParserRuleCall_11_1_1_ExternalSourceKeyword_11_1_2_TAG_CLOSEParserRuleCall_11_1_3__.equals(syntax))
				emit_ct_ExternalSourceType_TAG_CLOSE2ParserRuleCall_11_0_or___TAG_CLOSEParserRuleCall_11_1_0_TAG_OPEN2ParserRuleCall_11_1_1_ExternalSourceKeyword_11_1_2_TAG_CLOSEParserRuleCall_11_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_ct_InternalSourceType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_InternalSourceKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__.equals(syntax))
				emit_ct_InternalSourceType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_InternalSourceKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_ct_SequenceType_TAG_CLOSE2ParserRuleCall_15_0_or___TAG_CLOSEParserRuleCall_15_1_0_TAG_OPEN2ParserRuleCall_15_1_1_SequenceKeyword_15_1_2_TAG_CLOSEParserRuleCall_15_1_3__.equals(syntax))
				emit_ct_SequenceType_TAG_CLOSE2ParserRuleCall_15_0_or___TAG_CLOSEParserRuleCall_15_1_0_TAG_OPEN2ParserRuleCall_15_1_1_SequenceKeyword_15_1_2_TAG_CLOSEParserRuleCall_15_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_ct_xmlns___ColonKeyword_1_0_IDTerminalRuleCall_1_1__q.equals(syntax))
				emit_ct_xmlns___ColonKeyword_1_0_IDTerminalRuleCall_1_1__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_design_DoseVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_DoseVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__.equals(syntax))
				emit_design_DoseVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_DoseVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_design_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__.equals(syntax))
				emit_design_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_design_TreatmentRefType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_TreatmentRefKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__.equals(syntax))
				emit_design_TreatmentRefType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_TreatmentRefKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_design_WashoutType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_WashoutKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__.equals(syntax))
				emit_design_WashoutType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_WashoutKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_mdef_CovariateVariabilityType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_3_CovariateKeyword_6_1_4_TAG_CLOSEParserRuleCall_6_1_5__.equals(syntax))
				emit_mdef_CovariateVariabilityType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_3_CovariateKeyword_6_1_4_TAG_CLOSEParserRuleCall_6_1_5__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_mdef_ParameterType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_6_ParameterKeyword_6_1_7_TAG_CLOSEParserRuleCall_6_1_8__.equals(syntax))
				emit_mdef_ParameterType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_6_ParameterKeyword_6_1_7_TAG_CLOSEParserRuleCall_6_1_8__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_mdef_VariabilityLevelDefnType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VariabilityLevelKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__.equals(syntax))
				emit_mdef_VariabilityLevelDefnType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VariabilityLevelKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_msteps_DependantStepType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_DependantStepKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__.equals(syntax))
				emit_msteps_DependantStepType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_DependantStepKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_msteps_EstimationOperationType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_EstimationOperationKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__.equals(syntax))
				emit_msteps_EstimationOperationType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_EstimationOperationKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_msteps_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__.equals(syntax))
				emit_msteps_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_msteps_UseVariabilityLevel_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_UseVariabilityLevelKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__.equals(syntax))
				emit_msteps_UseVariabilityLevel_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_UseVariabilityLevelKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_msteps_UseVariabilityNode_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_UseVariabilityNodeKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__.equals(syntax))
				emit_msteps_UseVariabilityNode_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_UseVariabilityNodeKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_uncert_ParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_FunctionParameterKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__.equals(syntax))
				emit_uncert_ParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_FunctionParameterKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else if(match_uncert_Uniform_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_UniformKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__.equals(syntax))
				emit_uncert_Uniform_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_UniformKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'Constant' TAG_CLOSE)
	 */
	protected void emit_Math_ConstantType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_ConstantKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'Scalar' TAG_CLOSE)
	 */
	protected void emit_Math_ScalarType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_ScalarKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'String' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_Math_StringType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_StringKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'Var' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_Math_VarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'FunctionArgument' TAG_CLOSE)
	 */
	protected void emit_Mml_FuncParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_2_FunctionArgumentKeyword_5_1_3_TAG_CLOSEParserRuleCall_5_1_4__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'Column' TAG_CLOSE)
	 */
	protected void emit_ct_ColumnType_TAG_CLOSE2ParserRuleCall_9_0_or___TAG_CLOSEParserRuleCall_9_1_0_TAG_OPEN2ParserRuleCall_9_1_1_ColumnKeyword_9_1_2_TAG_CLOSEParserRuleCall_9_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'ExternalSource' TAG_CLOSE)
	 */
	protected void emit_ct_ExternalSourceType_TAG_CLOSE2ParserRuleCall_11_0_or___TAG_CLOSEParserRuleCall_11_1_0_TAG_OPEN2ParserRuleCall_11_1_1_ExternalSourceKeyword_11_1_2_TAG_CLOSEParserRuleCall_11_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'InternalSource' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_ct_InternalSourceType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_InternalSourceKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'Sequence' TAG_CLOSE)
	 */
	protected void emit_ct_SequenceType_TAG_CLOSE2ParserRuleCall_15_0_or___TAG_CLOSEParserRuleCall_15_1_0_TAG_OPEN2ParserRuleCall_15_1_1_SequenceKeyword_15_1_2_TAG_CLOSEParserRuleCall_15_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (':' ID)?
	 */
	protected void emit_ct_xmlns___ColonKeyword_1_0_IDTerminalRuleCall_1_1__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'DoseVar' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_design_DoseVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_DoseVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'TargetVar' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_design_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'TreatmentRef' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_design_TreatmentRefType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_TreatmentRefKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'Washout' TAG_CLOSE)
	 */
	protected void emit_design_WashoutType_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_WashoutKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'Covariate' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_mdef_CovariateVariabilityType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_3_CovariateKeyword_6_1_4_TAG_CLOSEParserRuleCall_6_1_5__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'Parameter' TAG_CLOSE)
	 */
	protected void emit_mdef_ParameterType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_6_ParameterKeyword_6_1_7_TAG_CLOSEParserRuleCall_6_1_8__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'VariabilityLevel' TAG_CLOSE)
	 */
	protected void emit_mdef_VariabilityLevelDefnType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_VariabilityLevelKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'DependantStep' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_msteps_DependantStepType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_DependantStepKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'EstimationOperation' TAG_CLOSE)
	 */
	protected void emit_msteps_EstimationOperationType_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_EstimationOperationKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     TAG_CLOSE2 | (TAG_CLOSE TAG_OPEN2 'TargetVar' TAG_CLOSE)
	 */
	protected void emit_msteps_TargetVarType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_TargetVarKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'UseVariabilityLevel' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_msteps_UseVariabilityLevel_TAG_CLOSE2ParserRuleCall_6_0_or___TAG_CLOSEParserRuleCall_6_1_0_TAG_OPEN2ParserRuleCall_6_1_1_UseVariabilityLevelKeyword_6_1_2_TAG_CLOSEParserRuleCall_6_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'UseVariabilityNode' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_msteps_UseVariabilityNode_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_UseVariabilityNodeKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'FunctionParameter' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_uncert_ParameterDefinitionType_TAG_CLOSE2ParserRuleCall_5_0_or___TAG_CLOSEParserRuleCall_5_1_0_TAG_OPEN2ParserRuleCall_5_1_1_FunctionParameterKeyword_5_1_2_TAG_CLOSEParserRuleCall_5_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Syntax:
	 *     (TAG_CLOSE TAG_OPEN2 'Uniform' TAG_CLOSE) | TAG_CLOSE2
	 */
	protected void emit_uncert_Uniform_TAG_CLOSE2ParserRuleCall_4_0_or___TAG_CLOSEParserRuleCall_4_1_0_TAG_OPEN2ParserRuleCall_4_1_1_UniformKeyword_4_1_2_TAG_CLOSEParserRuleCall_4_1_3__(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}

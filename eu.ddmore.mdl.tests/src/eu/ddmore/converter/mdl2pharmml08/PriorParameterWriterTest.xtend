package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.utils.MDLBuildFixture
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.Library
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class PriorParameterWriterTest {
	@Inject extension MDLBuildFixture
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension MdlLibUtils
	@Inject extension LibraryUtils
	
	var Library libDefns
	var PriorParameterWriter testInstance
	
	@Before
	def void setUp(){
				val dummyMdl = '''
			foo = mdlObj {
				
			}
		'''.parse
		
		libDefns = dummyMdl.objects.head.libraryForObject
		
	}
	
	@After
	def void tearDown(){
		libDefns = null
	}

	@Test
	def void testWritePriorDistn(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		priorBlk.createRandVar('p1',
							createNamedFunction(libDefns.getFunctionDefinition('Gamma2'),
									createAssignPair('shape', createRealLiteral(2.0)),
									createAssignPair('rate', createRealLiteral(3.0))
							)
						)
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('p1')
		
		
		this.testInstance = new PriorParameterWriter(null, priorObj, [null])
		val actual = testInstance.writeParameter(parmStmt)
		val expected = '''
			<PopulationParameter symbId="p1">
				<ct:VariabilityReference>
					<ct:SymbRef blkIdRef="vm_mdl" symbIdRef="MDL__prior"/>
				</ct:VariabilityReference>
				<Distribution>
					<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Gamma2">
						<Parameter name="shape">
							<ct:Assign>
								<ct:Real>2.0</ct:Real>
							</ct:Assign>
						</Parameter>
						<Parameter name="rate">
							<ct:Assign>
								<ct:Real>3.0</ct:Real>
							</ct:Assign>
						</Parameter>
					</ProbOnto>
				</Distribution>
			</PopulationParameter>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWritePriorWithMathsExpr(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		val symbRef = priorBlk.createEqnDefn("anOther", createRealLiteral(2.0))
		priorBlk.createEqnDefn('p1', symbRef.createSymbolRef)
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('p1')
		
		
		this.testInstance = new PriorParameterWriter(null, priorObj, [null])
		val actual = testInstance.writeParameter(parmStmt)
		val expected = '''
			<PopulationParameter symbId="p1">
				<ct:Assign>
					<ct:SymbRef blkIdRef="pm" symbIdRef="anOther"/>
				</ct:Assign>
			</PopulationParameter>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWritePriorWithLiteral(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		priorBlk.createEqnDefn('p1',createRealLiteral(2.0))
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('p1')
		
		
		this.testInstance = new PriorParameterWriter(null, priorObj, [null])
		val actual = testInstance.writeParameter(parmStmt)
		val expected = '''
			<PopulationParameter symbId="p1">
				<ct:Assign>
					<ct:Real>2.0</ct:Real>
				</ct:Assign>
			</PopulationParameter>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWriteParamNotInPriorObj(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		priorBlk.createEqnDefn('p111',createRealLiteral(2.0))
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('p1', createRealLiteral(22.0))
		
		
		this.testInstance = new PriorParameterWriter(null, priorObj, [null])
		val actual = testInstance.writeParameter(parmStmt)
		val expected = '''
			<PopulationParameter symbId="p1">
				<ct:Assign>
					<ct:Real>22.0</ct:Real>
				</ct:Assign>
			</PopulationParameter>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteParamFromData(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorSrcBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_SOURCE_BLK))
		val dataList = priorSrcBlk.createListDefn('PriorSourceCsv', createAssignPair('file', createStringLiteral("simple3_prior.csv")),
														createEnumPair('inputFormat', 'csv'),
														createAssignPair('column',
																		createVectorLiteral(createStringLiteral("bin_ka"),
																							createStringLiteral("bin_v"),
																							createStringLiteral("bin_tlag")
																		)
														)
													)
													
		val priorVariableBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		val kaVBins = priorVariableBlk.createEqnDefn('KA_V_BINS')
		val empRv = priorVariableBlk.createRandVar('empVar', createNamedFunction(
																libDefns.getFunctionDefinition('Empirical'),
																createAssignPair('data', kaVBins.createSymbolRef)
															)
													)
													
		val priorInputBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_INPUT_DATA))
		priorInputBlk.createAnonList(createAssignPair('matrixVar', kaVBins.createSymbolRef),
										createAssignPair('data', dataList.createSymbolRef),
										createAssignPair('column', createVectorLiteral(createStringLiteral("bin_ka"),
																						createStringLiteral("bin_v"))
														)
										)
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('empVar')
		
		
		this.testInstance = new PriorParameterWriter(mdlObj, priorObj, [null])
		val actual = testInstance.writeParameterModel
		val expected = '''
			<ParameterModel blkId="pm">
				<PopulationParameter symbId="empVar">
					<ct:VariabilityReference>
						<ct:SymbRef blkIdRef="vm_mdl" symbIdRef="MDL__prior"/>
					</ct:VariabilityReference>
					<Distribution>
						<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="RandomSample">
						</ProbOnto>
					</Distribution>
				</PopulationParameter>
			</ParameterModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteParamWithWeightedEmpirical(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		val priorSrcBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_SOURCE_BLK))
		val dataList = priorSrcBlk.createListDefn('PriorSourceCsv', createAssignPair('file', createStringLiteral("simple3_prior.csv")),
														createEnumPair('inputFormat', 'csv'),
														createAssignPair('column',
																		createVectorLiteral(createStringLiteral("p_ka_v"),
																							createStringLiteral("bin_ka"),
																							createStringLiteral("bin_v"),
																							createStringLiteral("bin_tlag")
																		)
														)
													)
													
		val priorVariableBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		val kaVBins = priorVariableBlk.createEqnDefn('KA_V_BINS')
		val kaVProbs = priorVariableBlk.createEqnDefn('KA_V_PROBS')
		val empRv = priorVariableBlk.createRandVar('empVar', createNamedFunction(
																libDefns.getFunctionDefinition('MultiNonParametric'),
																createAssignPair('bins', kaVBins.createSymbolRef),
																createAssignPair('probability', kaVProbs.createSymbolRef)
															)
													)
													
		val priorInputBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_INPUT_DATA))
		priorInputBlk.createAnonList(createAssignPair('matrixVar', kaVBins.createSymbolRef),
										createAssignPair('data', dataList.createSymbolRef),
										createAssignPair('column', createVectorLiteral(createStringLiteral("bin_ka"),
																						createStringLiteral("bin_v"))
														)
										)
		priorInputBlk.createAnonList(createAssignPair('vectorVar', kaVProbs.createSymbolRef),
										createAssignPair('data', dataList.createSymbolRef),
										createAssignPair('column', createStringLiteral("p_ka_v"))
										)
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('empVar')
		
		
		this.testInstance = new PriorParameterWriter(mdlObj, priorObj, [null])
		val actual = testInstance.writeParameterModel
		val expected = '''
			<ParameterModel blkId="pm">
				<PopulationParameter symbId="KA_V_PROBS">
				</PopulationParameter>
				<PopulationParameter symbId="empVar">
					<ct:VariabilityReference>
						<ct:SymbRef blkIdRef="vm_mdl" symbIdRef="MDL__prior"/>
					</ct:VariabilityReference>
					<Distribution>
						<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="RandomSample">
							<Parameter name="weight">
								<ct:Assign>
									<ct:SymbRef symbIdRef="MDL__weight_empVar"/>
								</ct:Assign>
							</Parameter>
						</ProbOnto>
					</Distribution>
				</PopulationParameter>
			</ParameterModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteParamFromInlineData(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
													
		val priorVariableBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		val kaVBins = priorVariableBlk.createEqnDefn('KA_V_BINS', createMatrixLiteral(
																	#[createRealLiteral(12), createRealLiteral(33)],
																	#[createRealLiteral(34), createRealLiteral(67)],
																	#[createRealLiteral(35), createRealLiteral(78)]
																	))
		val empRv = priorVariableBlk.createRandVar('empVar', createNamedFunction(
																libDefns.getFunctionDefinition('Empirical'),
																createAssignPair('data', kaVBins.createSymbolRef)
															)
													)
													
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('empVar')
		
		
		this.testInstance = new PriorParameterWriter(mdlObj, priorObj, [null])
		val actual = testInstance.writeParameterModel
		val expected = '''
			<ParameterModel blkId="pm">
				<PopulationParameter symbId="empVar">
					<ct:VariabilityReference>
						<ct:SymbRef blkIdRef="vm_mdl" symbIdRef="MDL__prior"/>
					</ct:VariabilityReference>
					<Distribution>
						<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="RandomSample">
							<ColumnMapping>
								<ds:ColumnRef columnIdRef="lPOP_TLAG1"/>
								<ct:Assign>
									<ct:VectorSelector>
										<ct:SymbRef symbIdRef="lPOP_TLAG"/>
										<ct:Cell>
											<ct:Int>1</ct:Int>
										</ct:Cell>
									</ct:VectorSelector>
								</ct:Assign>
							</ColumnMapping>
							<ColumnMapping>
								<ds:ColumnRef columnIdRef="lPOP_TLAG2"/>
								<ct:Assign>
									<ct:VectorSelector>
										<ct:SymbRef symbIdRef="lPOP_TLAG"/>
										<ct:Cell>
											<ct:Int>2</ct:Int>
										</ct:Cell>
									</ct:VectorSelector>
								</ct:Assign>
							</ColumnMapping>
							<ds:DataSet>
								<ds:Definition>
									<ds:Column columnId="lPOP_TLAG1" valueType="real" columnNum="1"/>
									<ds:Column columnId="lPOP_TLAG2" valueType="real" columnNum="2"/>
								</ds:Definition>
								<ds:Table>
									<ds:Row><ct:Real>12</ct:Real><ct:Real>33</ct:Real></ds:Row>
									<ds:Row><ct:Real>34</ct:Real><ct:Real>67</ct:Real></ds:Row>
									<ds:Row><ct:Real>35</ct:Real><ct:Real>78</ct:Real></ds:Row>
								</ds:Table>
							</ds:DataSet>
						</ProbOnto>
					</Distribution>
				</PopulationParameter>
			</ParameterModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteParamWithWeightedEmpiricalInlineData(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
													
		val priorVariableBlk = priorObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::PRIOR_VAR_DEFN))
		val kaVBins = priorVariableBlk.createEqnDefn('KA_V_BINS', createMatrixLiteral(
																	#[createRealLiteral(12), createRealLiteral(33)],
																	#[createRealLiteral(34), createRealLiteral(67)],
																	#[createRealLiteral(35), createRealLiteral(78)]
																	))
		val kaVProbs = priorVariableBlk.createEqnDefn('KA_V_PROBS', createVectorLiteral(
																	createRealLiteral(0.12), createRealLiteral(0.23),
																	createRealLiteral(0.24)
																	)
													)
		val empRv = priorVariableBlk.createRandVar('empVar', createNamedFunction(
																libDefns.getFunctionDefinition('MultiNonParametric'),
																createAssignPair('bins', kaVBins.createSymbolRef),
																createAssignPair('probability', kaVProbs.createSymbolRef)
															)
													)
													
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val paramBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::MDL_STRUCT_PARAMS))
		val parmStmt = paramBlk.createEqnDefn('empVar')
		
		
		this.testInstance = new PriorParameterWriter(mdlObj, priorObj, [null])
		val actual = testInstance.writeParameterModel
		val expected = '''
			<ParameterModel blkId="pm">
				<PopulationParameter symbId="KA_V_PROBS">
				</PopulationParameter>
				<PopulationParameter symbId="lPOP_TLAG">
					<ct:VariabilityReference>
						<ct:SymbRef blkIdRef="vm_mdl" symbIdRef="MDL__prior"/>
					</ct:VariabilityReference>
					<Distribution>
						<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="RandomSample">
							<ColumnMapping>
								<ds:ColumnRef columnIdRef="lPOP_TLAG1"/>
								<ct:Assign>
									<ct:VectorSelector>
										<ct:SymbRef symbIdRef="lPOP_TLAG"/>
										<ct:Cell>
											<ct:Int>1</ct:Int>
										</ct:Cell>
									</ct:VectorSelector>
								</ct:Assign>
							</ColumnMapping>
							<ColumnMapping>
								<ds:ColumnRef columnIdRef="lPOP_TLAG2"/>
								<ct:Assign>
									<ct:VectorSelector>
										<ct:SymbRef symbIdRef="lPOP_TLAG"/>
										<ct:Cell>
											<ct:Int>2</ct:Int>
										</ct:Cell>
									</ct:VectorSelector>
								</ct:Assign>
							</ColumnMapping>
							<ColumnMapping>
								<ds:ColumnRef columnIdRef="KA_V_PROBS"/>
								<ct:Assign>
									<ct:SymbRef symbIdRef="KA_V_PROBS"/>
								</ct:Assign>
							</ColumnMapping>
							<ds:DataSet>
								<ds:Definition>
									<ds:Column columnId="lPOP_TLAG1" valueType="real" columnNum="1"/>
									<ds:Column columnId="lPOP_TLAG2" valueType="real" columnNum="2"/>
									<ds:Column columnId="KA_V_PROBS" valueType="real" columnNum="3"/>
								</ds:Definition>
								<ds:Table>
									<ds:Row><ct:Real>12</ct:Real><ct:Real>33</ct:Real><ct:Real>0.12</ct:Real></ds:Row>
									<ds:Row><ct:Real>34</ct:Real><ct:Real>67</ct:Real><ct:Real>0.23</ct:Real></ds:Row>
									<ds:Row><ct:Real>35</ct:Real><ct:Real>78</ct:Real><ct:Real>0.24</ct:Real></ds:Row>
								</ds:Table>
							</ds:DataSet>
						</ProbOnto>
					</Distribution>
				</PopulationParameter>
			</PopulationModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteVarLevels(){
		val root = createRoot
		val priorObj = root.createObject("pObj", libDefns.getObjectDefinition('priorObj'))
		
		val mdlObj = root.createObject("mObj", libDefns.getObjectDefinition('mdlObj'))
		val varLvlBlk = mdlObj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::VAR_LVL_BLK_NAME))
		varLvlBlk.createListDefn("bsv", createEnumPair('type', 'parameter'), createAssignPair('level', createIntLiteral(1)))
		
		this.testInstance = new PriorParameterWriter(mdlObj, priorObj, [null])
		val actual = testInstance.writeVariabilityModel
		val expected = '''
			<VariabilityModel blkId="vm_mdl" type="parameterVariability">
				<Level referenceLevel="false" symbId="MDL__prior"/>
				<Level referenceLevel="false" symbId="bsv">
					<ParentLevel>
						<ct:SymbRef symbIdRef="MDL__prior"/>
					</ParentLevel>
				</Level>
			</VariabilityModel>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}



}
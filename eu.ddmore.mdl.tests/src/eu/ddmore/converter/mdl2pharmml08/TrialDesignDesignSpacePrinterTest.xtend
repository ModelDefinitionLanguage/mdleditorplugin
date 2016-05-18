package eu.ddmore.converter.mdl2pharmml08

import com.google.inject.Inject
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.MdlTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.utils.MDLBuildFixture
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Library
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class TrialDesignDesignSpacePrinterTest {
	@Inject extension MDLBuildFixture
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension MdlLibUtils
	@Inject extension MdlUtils
	@Inject extension LibraryUtils
	
	var Library libDefns
	
	@Before
	def void setUp(){
				val dummyMdl = '''
			foo = mdlObj {
				
			}
		'''.parse
		
		libDefns = dummyMdl.objects.head.libraryForObject
		
	}

	@Test
	def void testWriteDesignSpaceParameterDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val rsVar1 = desParamsBlk.createEqnDefn("Y") 
		val rsVar2 = desParamsBlk.createEqnDefn("Pa") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_PARAM_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1),	
																						createSymbolRef(rsVar2)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ct:SymbRef symbIdRef="Y"/>
				<ct:SymbRef symbIdRef="Pa"/>
				<ct:Assign>
					<ct:Sequence>
						<ct:Begin>
							<ct:Int>0</ct:Int>
						</ct:Begin>
						<ct:StepSize>
							<ct:Int>1</ct:Int>
						</ct:StepSize>
						<ct:End>
							<ct:Int>20</ct:Int>
						</ct:End>
					</ct:Sequence>
				</ct:Assign>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceParameterRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_PARAMS))
		val rsVar1 = desParamsBlk.createEqnDefn("Y") 
		val rsVar2 = desParamsBlk.createEqnDefn("Pa") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_PARAM_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1),	
																						createSymbolRef(rsVar2)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ct:SymbRef symbIdRef="Y"/>
				<ct:SymbRef symbIdRef="Pa"/>
				<ct:Assign>
					<ct:Vector>
						<ct:VectorElements>
							<ct:Real>0.0</ct:Real>
							<ct:Real>20.0</ct:Real>
						</ct:VectorElements>
					</ct:Vector>
				</ct:Assign>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceBolusAmtDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_BOLUSAMT_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<InterventionRef oidRef="admin1"/>
				<DoseAmount>
					<ct:Assign>
						<ct:Sequence>
							<ct:Begin>
								<ct:Int>0</ct:Int>
							</ct:Begin>
							<ct:StepSize>
								<ct:Int>1</ct:Int>
							</ct:StepSize>
							<ct:End>
								<ct:Int>20</ct:Int>
							</ct:End>
						</ct:Sequence>
					</ct:Assign>
				</DoseAmount>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceBolusAmtRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_BOLUSAMT_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<InterventionRef oidRef="admin1"/>
				<DoseAmount>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.0</ct:Real>
								<ct:Real>20.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</DoseAmount>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDesignSpaceInfusionAmtDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_INFAMT_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<InterventionRef oidRef="admin1"/>
				<DoseAmount>
					<ct:Assign>
						<ct:Sequence>
							<ct:Begin>
								<ct:Int>0</ct:Int>
							</ct:Begin>
							<ct:StepSize>
								<ct:Int>1</ct:Int>
							</ct:StepSize>
							<ct:End>
								<ct:Int>20</ct:Int>
							</ct:End>
						</ct:Sequence>
					</ct:Assign>
				</DoseAmount>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceInfusionAmtRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_INFAMT_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<InterventionRef oidRef="admin1"/>
				<DoseAmount>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.0</ct:Real>
								<ct:Real>20.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</DoseAmount>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceDurationDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_DURATION_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<InterventionRef oidRef="admin1"/>
				<Duration>
					<ct:Assign>
						<ct:Sequence>
							<ct:Begin>
								<ct:Int>0</ct:Int>
							</ct:Begin>
							<ct:StepSize>
								<ct:Int>1</ct:Int>
							</ct:StepSize>
							<ct:End>
								<ct:Int>20</ct:Int>
							</ct:End>
						</ct:Sequence>
					</ct:Assign>
				</Duration>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceDurationRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_DURATION_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<InterventionRef oidRef="admin1"/>
				<Duration>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.0</ct:Real>
								<ct:Real>20.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</Duration>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWriteDesignSpaceSampleTimesDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_SAMPLE_TIMES_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ObservationRef oidRef="admin1"/>
				<ObservationTimes>
					<ct:Assign>
						<ct:Sequence>
							<ct:Begin>
								<ct:Int>0</ct:Int>
							</ct:Begin>
							<ct:StepSize>
								<ct:Int>1</ct:Int>
							</ct:StepSize>
							<ct:End>
								<ct:Int>20</ct:Int>
							</ct:End>
						</ct:Sequence>
					</ct:Assign>
				</ObservationTimes>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceSampleTimesRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_SAMPLE_TIMES_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ObservationRef oidRef="admin1"/>
				<ObservationTimes>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.0</ct:Real>
								<ct:Real>20.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</ObservationTimes>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}


	@Test
	def void testWriteDesignSpaceNumberTimesDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_NUMBER_TIMES_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ObservationRef oidRef="admin1"/>
				<NumberTimes>
					<ct:Assign>
						<ct:Sequence>
							<ct:Begin>
								<ct:Int>0</ct:Int>
							</ct:Begin>
							<ct:StepSize>
								<ct:Int>1</ct:Int>
							</ct:StepSize>
							<ct:End>
								<ct:Int>20</ct:Int>
							</ct:End>
						</ct:Sequence>
					</ct:Assign>
				</NumberTimes>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceNumberTimesRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_NUMBER_TIMES_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ObservationRef oidRef="admin1"/>
				<NumberTimes>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.0</ct:Real>
								<ct:Real>20.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</NumberTimes>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDesignSpaceNumberArmsDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_NUMBER_ARMS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ArmRef oidRef="admin1"/>
				<NumberArms>
					<ct:Assign>
						<ct:Sequence>
							<ct:Begin>
								<ct:Int>0</ct:Int>
							</ct:Begin>
							<ct:StepSize>
								<ct:Int>1</ct:Int>
							</ct:StepSize>
							<ct:End>
								<ct:Int>20</ct:Int>
							</ct:End>
						</ct:Sequence>
					</ct:Assign>
				</NumberArms>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceNumberArmsRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_NUMBER_ARMS_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ArmRef oidRef="admin1"/>
				<NumberArms>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.0</ct:Real>
								<ct:Real>20.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</NumberArms>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDesignSpaceArmsSizeDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_ARM_SIZE_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ArmRef oidRef="admin1"/>
				<ArmSize>
					<ct:Assign>
						<ct:Sequence>
							<ct:Begin>
								<ct:Int>0</ct:Int>
							</ct:Begin>
							<ct:StepSize>
								<ct:Int>1</ct:Int>
							</ct:StepSize>
							<ct:End>
								<ct:Int>20</ct:Int>
							</ct:End>
						</ct:Sequence>
					</ct:Assign>
				</ArmSize>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceArmSizeRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_ARM_SIZE_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<ArmRef oidRef="admin1"/>
				<ArmSize>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.0</ct:Real>
								<ct:Real>20.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</ArmSize>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDesignSpaceDoseTimeDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_DOSE_TIME_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<InterventionRef oidRef="admin1"/>
				<DosingTimes>
					<ct:Assign>
						<ct:Sequence>
							<ct:Begin>
								<ct:Int>0</ct:Int>
							</ct:Begin>
							<ct:StepSize>
								<ct:Int>1</ct:Int>
							</ct:StepSize>
							<ct:End>
								<ct:Int>20</ct:Int>
							</ct:End>
						</ct:Sequence>
					</ct:Assign>
				</DosingTimes>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceDoseTimeRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val rsVar1 = desParamsBlk.createListDefn("admin1") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_DOSE_TIME_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<InterventionRef oidRef="admin1"/>
				<DosingTimes>
					<ct:Assign>
						<ct:Vector>
							<ct:VectorElements>
								<ct:Real>0.0</ct:Real>
								<ct:Real>20.0</ct:Real>
							</ct:VectorElements>
						</ct:Vector>
					</ct:Assign>
				</DosingTimes>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDesignSpaceCovariateDiscrete(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val rsVar1 = desParamsBlk.createEqnDefn("W") 
		val rsVar2 = desParamsBlk.createEqnDefn("Y") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_COVARIATE_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1),
																						createSymbolRef(rsVar2)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<CovariateModelRef oidRef="«TrialDesignDesignObjectPrinter::COV_MOD_OID»"/>
				<CovariateRef symbIdRef="W">
					<mdef:Continuous>
						<ct:Assign>
							<ct:Sequence>
								<ct:Begin>
									<ct:Int>0</ct:Int>
								</ct:Begin>
								<ct:StepSize>
									<ct:Int>1</ct:Int>
								</ct:StepSize>
								<ct:End>
									<ct:Int>20</ct:Int>
								</ct:End>
							</ct:Sequence>
						</ct:Assign>
					</mdef:Continuous>
				</CovariateRef>
				</DesignSpace>
				<DesignSpace>
				<CovariateModelRef oidRef="«TrialDesignDesignObjectPrinter::COV_MOD_OID»"/>
				<CovariateRef symbIdRef="Y">
					<mdef:Continuous>
						<ct:Assign>
							<ct:Sequence>
								<ct:Begin>
									<ct:Int>0</ct:Int>
								</ct:Begin>
								<ct:StepSize>
									<ct:Int>1</ct:Int>
								</ct:StepSize>
								<ct:End>
									<ct:Int>20</ct:Int>
								</ct:End>
							</ct:Sequence>
						</ct:Assign>
					</mdef:Continuous>
				</CovariateRef>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}
	
	@Test
	def void testWriteDesignSpaceCovariateRange(){
		val mdl = createRoot
		
		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))
		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val rsVar1 = desParamsBlk.createEqnDefn("W") 
		val rsVar2 = desParamsBlk.createEqnDefn("Y") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		val dsList = desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_COVARIATE_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1),
																						createSymbolRef(rsVar2)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpace(dsList.firstAttributeList)
		val expected = '''
			<DesignSpace>
				<CovariateModelRef oidRef="«TrialDesignDesignObjectPrinter::COV_MOD_OID»"/>
				<CovariateRef symbIdRef="W">
					<mdef:Continuous>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
									<ct:Real>20.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</mdef:Continuous>
				</CovariateRef>
				</DesignSpace>
				<DesignSpace>
				<CovariateModelRef oidRef="«TrialDesignDesignObjectPrinter::COV_MOD_OID»"/>
				<CovariateRef symbIdRef="Y">
					<mdef:Continuous>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
									<ct:Real>20.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</mdef:Continuous>
				</CovariateRef>
			</DesignSpace>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

	@Test
	def void testWriteDesignSpaces(){
		val mdl = createRoot
		




		val obj = mdl.createObject("foo", libDefns.getObjectDefinition("designObj"))

		val desIntvnBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_INTERVENTION_BLK))
		val admin1 = desIntvnBlk.createListDefn("admin1") 

		val desStudDesBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_STUDY_DESIGN))
		val arm1 = desStudDesBlk.createListDefn("arm1") 

		val desParamsBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::COVARIATE_BLK_NAME))
		val rsVar1 = desParamsBlk.createEqnDefn("W") 
		val rsVar2 = desParamsBlk.createEqnDefn("Y") 

		val desBlk = obj.createBlock(libDefns.getBlockDefinition(BlockDefinitionTable::DES_DESIGN_SPACE_BLK))
		desBlk.createListDefn("ds1",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_COVARIATE_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(rsVar1),
																						createSymbolRef(rsVar2)
																					)
																				)
									)
		desBlk.createListDefn("ds2",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_DURATION_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_RANGE_ATT, createVectorLiteral( 
																									createRealLiteral(0),
																									createRealLiteral(20)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(admin1)
																					)
																				)
									)
		desBlk.createListDefn("ds3",
									createEnumPair(TrialDesignDesignObjectPrinter::DS_ELEMENT_ATT, TrialDesignDesignObjectPrinter::DS_ELEMENT_ARM_SIZE_VALUE),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_DISCRETE_ATT, createFunctionCall(libDefns.getFunctionDefinition('dseq'), 
																									createIntLiteral(0),
																									createIntLiteral(20),
																									createIntLiteral(1)
																								)
													),
									createAssignPair(TrialDesignDesignObjectPrinter::DS_OBJREF_ATT, createVectorLiteral(
																						createSymbolRef(arm1)
																					)
																				)
									)
		
		val tdow = new TrialDesignDesignObjectPrinter(mdl, new StandardParameterWriter(null))
		val actual = tdow.writeDesignSpaces(desBlk)
		val expected = '''
			<DesignSpaces>
				<DesignSpace>
					<CovariateModelRef oidRef="«TrialDesignDesignObjectPrinter::COV_MOD_OID»"/>
					<CovariateRef symbIdRef="W">
						<mdef:Continuous>
							<ct:Assign>
								<ct:Vector>
									<ct:VectorElements>
										<ct:Real>0.0</ct:Real>
										<ct:Real>20.0</ct:Real>
									</ct:VectorElements>
								</ct:Vector>
							</ct:Assign>
						</mdef:Continuous>
					</CovariateRef>
					</DesignSpace>
					<DesignSpace>
					<CovariateModelRef oidRef="«TrialDesignDesignObjectPrinter::COV_MOD_OID»"/>
					<CovariateRef symbIdRef="Y">
						<mdef:Continuous>
							<ct:Assign>
								<ct:Vector>
									<ct:VectorElements>
										<ct:Real>0.0</ct:Real>
										<ct:Real>20.0</ct:Real>
									</ct:VectorElements>
								</ct:Vector>
							</ct:Assign>
						</mdef:Continuous>
					</CovariateRef>
				</DesignSpace>
				<DesignSpace>
					<InterventionRef oidRef="admin1"/>
					<Duration>
						<ct:Assign>
							<ct:Vector>
								<ct:VectorElements>
									<ct:Real>0.0</ct:Real>
									<ct:Real>20.0</ct:Real>
								</ct:VectorElements>
							</ct:Vector>
						</ct:Assign>
					</Duration>
				</DesignSpace>
				<DesignSpace>
					<ArmRef oidRef="arm1"/>
					<ArmSize>
						<ct:Assign>
							<ct:Sequence>
								<ct:Begin>
									<ct:Int>0</ct:Int>
								</ct:Begin>
								<ct:StepSize>
									<ct:Int>1</ct:Int>
								</ct:StepSize>
								<ct:End>
									<ct:Int>20</ct:Int>
								</ct:End>
							</ct:Sequence>
						</ct:Assign>
					</ArmSize>
				</DesignSpace>
			</DesignSpaces>
		'''
		assertEquals("Output as expected", expected, actual.toString)
	}

}
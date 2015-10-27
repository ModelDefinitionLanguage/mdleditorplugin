/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.legacy;

import static org.junit.Assert.*

import static eu.ddmore.converter.mdl2json.MdlAndJsonFileUtils.*
import static eu.ddmore.converter.mdl2json.testutils.MdlFileContentTestUtils.*

import org.apache.log4j.Logger
import org.junit.Ignore
import org.junit.Test

import eu.ddmore.converter.mdl2json.MdlAndJsonFileUtils;
import eu.ddmore.converter.mdl2json.domain.Task

class TaskObjectToJSONTest {
    private static final Logger LOGGER = Logger.getLogger(TaskObjectToJSONTest.class)

    @Test
    public void testEstimateBlock_ContainingUnquotedAttributeValue() {
        def json = getJsonFromMDLFile("UseCase16_TaskObject.mdl")

        def taskObj = json[0].Hansson2013_task // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        LOGGER.debug(taskObj)

        def expectedEstimateStr = '''target=NMTRAN_CODE
cov=true
algo=["FOCE"]'''
        assertEquals("Checking the content of the Estimate block", expectedEstimateStr, taskObj[Task.ESTIMATE])
    }

    @Test
    public void testEstimateBlock_ContainingQuotedAttributeValue() {
        def json = getJsonFromMDLFile("UseCase2_TaskObject.mdl")

        def taskObj = json[0].warfarin_PK_ODE_task // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        LOGGER.debug(taskObj)

        def expectedEstimateStr = '''target=MLXTRAN_CODE
version="4.3.2"
algo=["SAEM"]'''
        assertEquals("Checking the content of the Estimate block", expectedEstimateStr, taskObj[Task.ESTIMATE])
    }

    @Test
    @Ignore
    public void testReadSimpleTask() {
        def json = getJsonFromMDLFile("simpleTask.mdl")

        def taskObject = json.ex_model7_prolactin_Jan2014_task

        assertEquals( Task.IDENTIFIER, taskObject[Task.IDENTIFIER_PROPNAME][0])

        def expectedTaskStr = """
TARGET_CODE(target=NMTRAN_CODE,location="\$PROBLEM",first=true){***
\$PROB AGONIST-ANTAGONIST INTERACTION MODEL FOR PROLACTIN
; FRIBERG ET AL. CPT 2009
***}

MODEL{
tolrel = 5
}

myEST=function(t,m,p,d) {

EXECUTE{
command = "call nmgo ex_model7_prolactin_Jan2014"
}

ESTIMATE{
target = t
model = m
parameter = p
data = d

TARGET_CODE(target=NMTRAN_CODE,location="\$ESTIMATION"){***
\$EST PRINT=5 MAX=0 SIGDIG=3 METHOD=1
;\$COV MATRIX=S
***}
algo = list("FOCE ")
}

}
"""
        String actual = taskObject.content[0]

        assertEquals(expectedTaskStr, actual.replaceAll("\r", ""))
    }

    @Test
    @Ignore
    public void testWarfarinTask() {
        def File origMdlFile = getFileFromModelsProject("warfarin_PK_PRED/warfarin_PK_PRED.mdl")

        def json = getJsonFromMDLFile(origMdlFile)[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]

        def taskObject = json.warfarin_PK_PRED_task

        assertEquals(Task.IDENTIFIER, taskObject[Task.IDENTIFIER_PROPNAME])

        def Task taskFromJson = new Task(taskObject)

        LOGGER.debug(taskFromJson.toMDL())

        assertTrue("Checking TARGET_CODE block", taskFromJson.toMDL().replace("\r\n","\n").contains(
'''taskobj {
    
TARGET_CODE(target=NMTRAN_CODE,location="$PROBLEM",first=true){***
$PROB WARFARIN PK
;O'Reilly RA, Aggeler PM, Leong LS. Studies of the coumarin anticoagulant
;drugs: The pharmacodynamics of warfarin in man.
;Journal of Clinical Investigation 1963;42(10):1542-1551
;O'Reilly RA, Aggeler PM. Studies on coumarin anticoagulant drugs
;Initiation of warfarin therapy without a loading dose.
;Circulation 1968;38:169-177
***}'''))
        assertMDLBlockEqualityIgnoringWhitespaceAndComments(origMdlFile, "DATA", taskFromJson.toMDL())
        assertMDLBlockEqualityIgnoringWhitespaceAndComments(origMdlFile, "EXECUTE", taskFromJson.toMDL())
        assertMDLBlockEqualityIgnoringWhitespaceAndComments(origMdlFile, "ESTIMATE", taskFromJson.toMDL())
        assertTrue("Checking estimation function is in the right place in the MDL", taskFromJson.toMDL().replace("\r\n","\n").contains(
'''myEST=function(t,m,p,d) {

EXECUTE{
command = "call nmgo warfarin_PK_PRED"
}

ESTIMATE{'''))

    }

}
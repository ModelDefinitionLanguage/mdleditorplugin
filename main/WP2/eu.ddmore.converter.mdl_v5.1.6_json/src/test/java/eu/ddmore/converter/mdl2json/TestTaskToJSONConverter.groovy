package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import eu.ddmore.converter.mdl2json.domain.Task

class TestTaskToJSONConverter extends MDLToJSONTest {
	private static Logger logger = Logger.getLogger(TestTaskToJSONConverter.class)
	
	@Test
	public void testReadSimpleTask() {
		def json = getJsonFromMDLFile("simpleTask.mdl")

		def taskObject = json.ex_model7_prolactin_Jan2014_task
		
		assertEquals( Task.IDENTIFIER, taskObject[Task.IDENTIFIER_PROPNAME][0])
		
		def expectedTaskStr = """TARGET_CODE(target=NMTRAN_CODE,location="\$PROBLEM",first=true){***
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
	public void testTumourTask() {
		def json = getJsonFromMDLFile("tumourTask.mdl")
		
		def taskObject = json.tumour_size_TABLES_ORG_task
				
		assertEquals( Task.IDENTIFIER, taskObject[Task.IDENTIFIER_PROPNAME][0])
		
		Task taskFromJson = new Task(taskObject)
		
		logger.debug(taskFromJson.toMDL())
		
	}
}

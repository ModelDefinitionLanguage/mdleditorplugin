package eu.ddmore.pharmacometrics.model.modellingsteps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import eu.ddmore.pharmacometrics.model.data.DataSet;

public class ObjectiveDataSetTest {

	@Test
	public void shouldConstructClassWithCorrectProperties() {
		ObjectiveDataSet ods = new ObjectiveDataSet(new DataSet());

		assertNotNull("Instance should not be null.", ods);
		assertNotNull("individualMappings should not be null.", ods.getIndividualMappings());
		assertEquals("individualMappings should be empty.", 0, ods.getIndividualMappings().size());
		assertNotNull("variableMappings should not be null.", ods.getVariableMappings());
		assertEquals("variableMappings should be empty.", 0, ods.getVariableMappings().size());
		assertNotNull("dataSet should not be null.", ods.getDataSet());
	}
}
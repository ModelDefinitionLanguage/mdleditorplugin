package eu.ddmore.pharmacometrics.model.modellingsteps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class EstimationStepTest {

	private static final String STEP_NAME = "Test Estimation Step";

	@Test
	public void shouldConstructClassWithCorrectProperties() {
		EstimationStep es = new EstimationStep(STEP_NAME);

		assertNotNull("Instance should not be null.", es);
		assertEquals("Name should be correct.", STEP_NAME, es.getName());
		
		List<ObjectiveDataSet> ods = es.getObjectiveDataSets();
		assertNotNull("Objective Data Sets should not be null.", ods);
		assertEquals("Objective Data Sets should not be empty.", 0, ods.size());
	}
}
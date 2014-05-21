package eu.ddmore.pharmacometrics.model.modellingsteps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ModellingStepsTest {

	@Test
	public void shouldConstructClassWithCorrectProperties() {
		ModellingSteps steps = new ModellingSteps();

		assertNotNull("Instance should not be null.", steps);
		assertNotNull("estimationSteps should not be null.", steps.getEstimationSteps());
		assertEquals("estimationSteps should be empty.", 0, steps.getEstimationSteps().size());
		assertNotNull("nonmemDataSets should not be null.", steps.getNonmemDataSets());
		assertEquals("nonmemDataSets should be empty.", 0, steps.getNonmemDataSets().size());
	}
}
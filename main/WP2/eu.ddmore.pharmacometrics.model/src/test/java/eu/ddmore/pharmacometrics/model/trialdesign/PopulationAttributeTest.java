package eu.ddmore.pharmacometrics.model.trialdesign;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class PopulationAttributeTest {

	private static final String POPULATION_ATTRIBUTE_NAME = "Test Population Attribute";

	@Test
	public void shouldConstructClassWithCorrectProperties() {
		PopulationAttribute pa = new PopulationAttribute(POPULATION_ATTRIBUTE_NAME);

		assertNotNull("Instance should not be null.", pa);
		assertEquals("Name should be correct.", POPULATION_ATTRIBUTE_NAME, pa.getName());
	}
}
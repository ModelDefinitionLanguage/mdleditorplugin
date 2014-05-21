package eu.ddmore.pharmacometrics.model.trialdesign.structure;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import eu.ddmore.pharmacometrics.model.data.DataSet;

public class IndividualDosingTest {

	@Test
	public void shouldConstructClassWithCorrectProperties() {
		IndividualDosing individualDosing = new IndividualDosing(new DataSet());

		assertNotNull("Instance should not be null.", individualDosing);
		assertNotNull("dataSet should be null.", individualDosing.getDataSet());
	}
}
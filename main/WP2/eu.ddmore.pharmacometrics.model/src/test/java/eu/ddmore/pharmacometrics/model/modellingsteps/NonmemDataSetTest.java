package eu.ddmore.pharmacometrics.model.modellingsteps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class NonmemDataSetTest {

	private static final String NAME = "Test Name";
	
	@Test
	public void shouldConstructClassWithCorrectProperties() {
		NonmemDataSet nds = new NonmemDataSet(NAME);

		assertNotNull("Instance should not be null.", nds);
		assertEquals("name should be correct.", NAME, nds.getName());
		assertNotNull("columnMappings should not be null.", nds.getColumnMappings());
		assertEquals("columnMappings should be empty.", 0, nds.getColumnMappings().size());
		assertNull("dataSet should be null.", nds.getDataSet());
	}
}
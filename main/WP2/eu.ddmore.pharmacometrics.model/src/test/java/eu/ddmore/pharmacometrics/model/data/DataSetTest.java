package eu.ddmore.pharmacometrics.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class DataSetTest {

	@Test
	public void shouldConstructClassWithCorrectProperties() {
		DataSet dataSet = new DataSet();
		assertNotNull("Instance should not be null.", dataSet);
		assertNotNull("definition (columns) should not be null.", dataSet.getColumns());
		assertEquals("definition (columns) should be empty.", 0, dataSet.getColumns().size());
		assertNotNull("table (rows) should not be null.", dataSet.getRows());
		assertEquals("table (rows) should be empty.", 0, dataSet.getRows().size());
		assertNull("importData should be null.", dataSet.getImportData());
	}
}
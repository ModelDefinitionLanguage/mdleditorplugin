package eu.ddmore.pharmacometrics.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RowTest {

	private static final String DATA_1 = "Test Data 1";
	private static final String DATA_2 = "Test Data 2";

	// test string representation
	private static final String rowToString = "Row [data=[" + DATA_1 + ", " + DATA_2 + "]]";
	
	@Test
	public void shouldConstructClassWithCorrectProperties() {
		Row row = new Row();
		assertNotNull("Instance should not be null.", row);
		assertNotNull("data should not be null.", row.getData());
		assertEquals("data should be empty.", 0, row.getData().size());
	}

	@Test
	public void shouldAddSingleRowCorrectly() {
		Row row = new Row();
		row.add(DATA_1);
		assertEquals("data should contain correct number of items.", 1, row.getData().size());
		assertEquals("data should be correct.", DATA_1, row.getData().get(0));
		assertEquals("data should be correct.", DATA_1, row.getDatumAt(0));
	}

	@Test
	public void shouldAddMulitpleRowsCorrectly() {
		Row row = new Row();
		row.add(DATA_1);
		row.add(DATA_2);
		assertEquals("data should contain correct number of items.", 2, row.getData().size());
		assertEquals("data should be correct.", DATA_1, row.getData().get(0));
		assertEquals("data should be correct.", DATA_1, row.getDatumAt(0));
		assertEquals("data should be correct.", DATA_2, row.getData().get(1));
		assertEquals("data should be correct.", DATA_2, row.getDatumAt(1));
	}

	@Test
	public void shouldGiveCorrectToString() {
		Row row = new Row();
		row.add(DATA_1);
		row.add(DATA_2);
		assertEquals("toString should give correct value.", rowToString, row.toString());
	}
}
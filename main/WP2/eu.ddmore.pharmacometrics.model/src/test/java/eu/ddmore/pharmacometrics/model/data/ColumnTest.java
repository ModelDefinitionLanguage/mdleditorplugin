package eu.ddmore.pharmacometrics.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;

import org.junit.Test;

public class ColumnTest {

	private static final String COLUMN_ID = "Test Column ID";
	private static final String COLUMN_TYPE = "Test Column Type";
	private static final String VALUE_TYPE = "Test Value Type";
	private static final BigInteger COLUMN_NUM = new BigInteger("0");

	// test column and string representation
	private static final Column col = new Column(COLUMN_ID, COLUMN_TYPE, VALUE_TYPE, COLUMN_NUM);
	private static final String colToString =
		"Column [columnId=" + COLUMN_ID + ", columnType=" + COLUMN_TYPE + ", valueType=" + VALUE_TYPE + ", columnNum=" + COLUMN_NUM + "]";
	
	@Test
	public void shouldConstructClassWithCorrectProperties() {
		assertNotNull("Instance should not be null.", col);
		assertEquals("columnId should have the correct value.", COLUMN_ID, col.getColumnId());
		assertEquals("columnType should have the correct value.", COLUMN_TYPE, col.getColumnType());
		assertEquals("valueType should have the correct value.", VALUE_TYPE, col.getValueType());
		assertEquals("columnNum should have the correct value.", COLUMN_NUM, col.getColumnNum());
	}
	
	@Test
	public void shouldGiveCorrectToString() {
		assertEquals("toString should give correct value.", colToString, col.toString());
	}
}

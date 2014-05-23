package eu.ddmore.pharmacometrics.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ImportDataTest {

	private static final String NAME = "Test Name";
	private static final String URL = "Test URL";
	private static final String FORMAT = "Test Format";
	private static final String DELIMITER = "Test Delimiter";

	// test import-data and string representation
	private static final ImportData importData = new ImportData(NAME, URL, FORMAT, DELIMITER);
	private static final String importDataToString =
		"ImportData [name=" + NAME + ", url=" + URL + ", format=" + FORMAT + ", delimiter=" + DELIMITER + "]";
	
	@Test
	public void shouldConstructClassWithCorrectProperties() {
		assertNotNull("Instance should not be null.", importData);
		assertEquals("name should have the correct value.", NAME, importData.getName());
		assertEquals("url should have the correct value.", URL, importData.getUrl());
		assertEquals("format should have the correct value.", FORMAT, importData.getFormat());
		assertEquals("delimiter should have the correct value.", DELIMITER, importData.getDelimiter());
	}
	
	@Test
	public void shouldGiveCorrectToString() {
		assertEquals("toString should give correct value.", importDataToString, importData.toString());
	}
}
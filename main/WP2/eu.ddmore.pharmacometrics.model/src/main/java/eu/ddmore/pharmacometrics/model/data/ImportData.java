package eu.ddmore.pharmacometrics.model.data;

public class ImportData {

	private final String name;
	private final String url;
	private final String format;
	private final String delimiter;
	
	public ImportData(String name, String url, String format, String delimiter) {
		this.name = name;
		this.url = url;
		this.format = format;
		this.delimiter = delimiter;
	}

	/*
	 * The 'name' and 'url' attributes will be replaced by a single
	 * 'path' attribute that points directly to the data file (ie
	 * including the folder path and name) *relative* to the PharmML
	 * file in version 0.3.1.  
	 */
	public String getName() {
		return name;
	}

	/* See comment above */
	public String getUrl() {
		return url;
	}

	public String getFormat() {
		return format;
	}

	public String getDelimiter() {
		return delimiter;
	}

	@Override
	public String toString() {
		return "ImportData [name=" + name + ", url=" + url + ", format="
				+ format + ", delimiter=" + delimiter + "]";
	}
}
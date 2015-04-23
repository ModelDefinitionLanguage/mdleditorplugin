package eu.ddmore.converter.mdl2json.interfaces;

public interface MDLPrintable {
	
	public static final String IDT = "    ";

	/**
	 * @return the MDL textual representation of this object, i.e. as per the original MDL file
	 */
	public String toMDL();

}

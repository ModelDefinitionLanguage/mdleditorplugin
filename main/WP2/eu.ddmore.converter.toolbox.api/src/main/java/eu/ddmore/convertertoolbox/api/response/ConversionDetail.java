/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.response;

import java.util.Map;

/**
 * Bean representing details generated by a conversion activity
 */
public interface ConversionDetail {
	
	/**
	 * Models the severity levels.
	 */
	public enum Severity {
		ERROR(1), WARNING(2), INFO(3), DEBUG(4), ALL(5);
		private int rank;
		private Severity(int rank) {
		    this.rank = rank;
		}
		public int getRank() {
		    return rank;
		}
	}

    /**
     * @return the severity level associated with this ConversionDetail
     */
    Severity getServerity();

    /**
     * Sets the severity level.
     * @param severity the severity level to set
     */
    void setSeverity(Severity severity);

    /**
     * @return the map representing the information associated with this ConversionDetail
     */
    Map<String, String> getInfo();

    /**
     * Sets the information map.
     * @param info the information map to set
     */
    void setInfo(Map<String, String> info);

    /**
     * Adds key-value pair on the information map associated with this ConversionDetail.
     * @param key the key of the information to add
     * @param value the value of the information to add
     */
    void addInfo(String key, String value);

    /**
     * @return the message associated with this ConversionDetail
     */
    String getMessage();

    /**
     * Sets the message associated with this ConversionDetail.
     * @param message the message to set
     */
    void setMessage(String message);
}

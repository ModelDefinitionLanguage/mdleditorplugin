/* File			:  CommonUtils.java
 * Project		:  MIFServer
 * Created on	:  Sep 18, 2012
 */
package com.mango.mif.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Common functions
 */
public class CommonUtils {
    /**
     * Converts given string to milliseconds
     * @param startTime
     * @return
     * @throws ParseException
     */
    public static Long convertToMilliseconds(String startTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(MIFProperties.getInstance().getProperty("sgeconnector.qstatTimeFormat"));
        return formatter.parse(startTime).getTime();
    }
}

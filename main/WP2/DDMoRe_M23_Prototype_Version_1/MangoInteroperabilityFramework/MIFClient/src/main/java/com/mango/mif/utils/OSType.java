/* File			:  IdentifyOS.java
 * Project		:  navigator
 * Created on	:  Mar 2, 2012
 */
package com.mango.mif.utils;
/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class OSType {
    
    private static final String OS_PROPERTY = "os.name";
    private static final String OS_UNKNOWN = "unknown";

    public static final String LINUX_OS = "nux";
    public static final String WINDOWS_OS = "win";
    public static final String SUN_OS = "sunos";
    public static final String MAC_OS = "mac";
    public static final String UNIX_OS = "nix";
        
    /**
     * Grab the value of os.name from the system properties.  Having never depended on this
     * before I have no idea how reliable it is.  Never return null, if the property is not
     * set, return OS_UNKNOWN instead.
     *
     * @return The value of OS_PROPERTY from the system properties or OS_UNKNOWN. 
     */
    public static String getOsString() {

        String result = System.getProperty(OS_PROPERTY);
        if (result != null) {
            result = result.toLowerCase();
        } else {
            result = OS_UNKNOWN;
        }
        return result;
    }

    public static boolean isWindows() {
 
        return (getOsString().indexOf(WINDOWS_OS) >= 0);
 
    }
 
    public static boolean isMac() {
 
        return (getOsString().indexOf(MAC_OS) >= 0);
 
    }
 
    public static boolean isUnix() {
 
        String os = getOsString();
        // linux or unix
        return (os.indexOf(UNIX_OS) >= 0 || os.indexOf(LINUX_OS) >= 0);
 
    }
 
    public static boolean isSolaris() {
 
        // Solaris
        return (getOsString().indexOf(SUN_OS) >= 0);
    }

    /**
     * @return true if any flavour of Unix, i.e. Unix, Linux or Solaris
     */
    public static boolean isUnixFlavoured() {
        return isUnix() || isSolaris();
    }
    
    /**
     * @return true if this is posix flavoured.
     */
    public static boolean isPosixFlavoured() {
        return isUnix() || isSolaris() || isMac();
    }
    
    /**
     * @return true if we don't recognise our own operating system, this is BAAAAAD.
     */
    public static boolean isNotKnown() {
        return (getOsString().equals(OS_UNKNOWN));
    }
}

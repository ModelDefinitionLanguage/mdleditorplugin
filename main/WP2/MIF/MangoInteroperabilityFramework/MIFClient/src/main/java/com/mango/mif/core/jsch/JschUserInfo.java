/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.jsch;

import org.apache.log4j.Logger;

import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

/**
 * The most important thing here is we implement UIKeyboardInteractive, which means the
 * "keyboard interactive" protocol is fired.  This bypasses problems at Merck where they
 * turn off password authentication (i.e. ordinary common or garden password authentication
 * over the network) but allow interactive keyboard authentication.
 */
class JschUserInfo implements UserInfo, UIKeyboardInteractive {

    private static Logger logger = Logger.getLogger(JschUserInfo.class);

    private String password;

    public JschUserInfo(String password) {
        this.password = password;
    }

    @Override
    public String[] promptKeyboardInteractive(String destination,
            String name,
            String instruction,
            String[] prompt,
            boolean[] echo) {

        String[] response = new String[1];
        response[0] = this.password;
        return response;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getPassphrase() {
        return null;
    }

    @Override
    public boolean promptPassphrase(String arg0) {
        logger.info(this.getClass().getName() + ".promptPassphrase: " + arg0);
        return false;
    }

    @Override
    public boolean promptPassword(String arg0) {
        logger.info(this.getClass().getName() +  ".promptPassword: " + arg0);
        return true;
    }

    @Override
    public boolean promptYesNo(String arg0) {
        logger.info(this.getClass().getName() + ".promptYesNo: " + arg0);
        return true;
    }

    @Override
    public void showMessage(String arg0) {
        logger.info(this.getClass().getName() + ".showMessage: " + arg0);
    }
}
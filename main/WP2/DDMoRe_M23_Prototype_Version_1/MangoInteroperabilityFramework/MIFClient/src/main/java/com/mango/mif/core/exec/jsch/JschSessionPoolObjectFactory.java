/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.jsch;

import java.util.Map;

import javax.annotation.PreDestroy;

import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.log4j.Logger;

import com.google.common.collect.Maps;

/**
 * Object factory (i.e. JschSession creator) for the JschSessionPool
 */
public class JschSessionPoolObjectFactory implements KeyedPoolableObjectFactory<JschParameters, JschSession> {

    private static final Logger logger = Logger.getLogger(JschSessionPoolObjectFactory.class);

    // We manually maintain a map of all objects currently in the pool ("swimmers"), because when we
    // shut down the pool we can kill them off immediately.  Ideally the pool should do it, however it
    // is far too polite, leaving each of the swimmers 'til they expire naturally (which is about
    // 10 minutes in the case of JschSession objects).
    //
    private Map<JschSession, JschSession> swimmers;

    /**
     * the public default constructor adds a shutdown hook 
     */
    public JschSessionPoolObjectFactory() {
        swimmers = Maps.newConcurrentMap();
    }

    @Override
    public void activateObject(JschParameters parameters, JschSession session) throws Exception {
        if (session == null) {
            logger.error(":POOL: activateObject called to activate null session!");
            return;
        }
        if (parameters != null) {
            logger.debug(":POOL: activateObject " + session + " " + parameters);
        }
        if (session.isInUse()) {
            logger.error(":POOL: activateObject called to activate a session " + session + " which is already in use!");
        }
        // It is tempting to set the "in use" flag on the session to "true" here, but using the session
        // should do that anyway.
    }

    /**
     * destroyObject is invoked on every instance when it is being "dropped" from the pool (whether due to the response
     * from validateObject, or for reasons specific to the pool implementation.) There is no guarantee that the instance
     * being destroyed will be considered active, passive or in a generally consistent state.
     */
    @Override
    public void destroyObject(JschParameters parameters, JschSession session) throws Exception {
        if (session == null) {
            logger.warn(":POOL: destroyObject called to destroy null session!");
            return;
        }
        logger.debug(":POOL: DESTROY session " + session);
        if (session.isInUse()) {
            logger.error(":POOL: destroyObject is destroying a session which is in use! " + session);
        }
        removeSwimmer(session);
        session.close();
    }

    /**
     * Making a session is simple as the parameters contain everything we can possibly need.
     */
    @Override
    public JschSession makeObject(JschParameters parameters) throws Exception {
        JschSession result = new JschSession(parameters);
        storeSwimmer(result);
        logger.debug(":POOL: CREATE session " + result);
        return result;
    }

    /**
     * Nothing to do here.  The session we try to add to the pool should not be "in use".
     */
    @Override
    public void passivateObject(JschParameters parameters, JschSession session) throws Exception {
        if (session == null) {
            logger.warn(":POOL: Attempt to passivate a null session!");
            return;
        }
        logger.debug(":POOL: passivateObject " + session + " " + parameters + ".");
        if (session.isInUse()) {
            logger.error(":POOL: Attempt to passivate a session " + session + " that is still in use!");
        }
    }

    /**
     * validateObject is invoked on activated instances to make sure they can be borrowed from the pool.
     * validateObject may also be used to test an instance being returned to the pool before it is passivated.
     * It will only be invoked on an activated instance
     */
    @Override
    public boolean validateObject(JschParameters parameters, JschSession session) {
        boolean isValid = session.isConnected();
        logger.debug(":POOL: validateObject " + session + " " + parameters + " returning " + isValid);
        return isValid;
    }

    /**
     * Create the hashmap for the swimmers.
     */
    private void createSwimmers() {
        logger.debug("Creating swimmers for jsch session pool object factory " + this.hashCode());
    }

    /**
     * Store a session, if not already stored.
     * @param session The session to store.
     */
    private void storeSwimmer(JschSession session) {
        if (swimmers == null) {
            createSwimmers();
        }
        swimmers.put(session, session);
    }

    /**
     * @param session the session to remove from the pool
     */
    private void removeSwimmer(JschSession session) {
        if (swimmers == null) {
            createSwimmers();
        }
        swimmers.remove(session);
    }

    public Map<JschSession, JschSession> getSwimmers() {
        return this.swimmers;
    }

    /**
     * Richard O noticed a number of problems with Tomcat shutdown because of the JschSessionPool.
     * 
     * Firstly, there were problems with JschSession objects trying to log stuff when being disconnected.
     * This is because during shutdown, Tomcat blows away everyone's static data in order to free memory.
     * Thus logging things via log4j throws null pointer exceptions.
     * 
     * So, the first thing we do is to turn off debugging in all JschSession objects
     * 
     * The next problem was that the pool was being far too lenient with the swimmers, leaving them to
     * expire naturally before evicting them (10 minutes for JschSession objects).  Thus - having kept a
     * hash of all the current swimmers - we kill them all off with extreme prejudice.
     * 
     * As there is some doubt about Tomcat and PreDestroy, I am also taking the precaution of using a
     * shutdown hook.
     */
    @PreDestroy
    public void killTheSwimmers() {

        // Stop JschSession objects from logging, and thus hurting themselves
        //      JSch.setLogger(null);

        //      if (swimmers != null) {
        // We need to copy the map since it may be being modified as we try to iterate over it
        // (giving us concurrent modification exceptions).
        //
        //          HashMap<JschSession, JschSession> modificationSafeMap = new HashMap<JschSession, JschSession>(swimmers);

        //          for (JschSession session : modificationSafeMap.keySet()) {
        //              try {
        //                  if (session.isConnected()) {
        //                      session.close();
        //                  }
        //              } catch (Exception ignored) {
        //                  // Ignore any exceptions and plough on to kill the next swimmer
        //              }
        //          }
        //      }
    }
}

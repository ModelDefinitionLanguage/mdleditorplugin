/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.jsch;

import javax.annotation.PreDestroy;

import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.apache.log4j.Logger;
import org.vngx.jsch.JSch;

import com.google.common.base.Stopwatch;
import com.mango.mif.core.exec.ExecutionException;

/**
 * This is a pool of JschSessions, keyed by the JschParameters class.  The parameters class has all the information
 * we need to create (and maintain) sessions - user name, password, machine name, port, etc. etc. etc.
 * 
 * The pool itself is static so we can ensure there is only one per class loader and/or Navigator or MIF instance.
 */
public class JschSessionPool {

    // There is only one pool, therefore there can be only one set of parameters.
    private static JschSessionPoolParameters jschSessionPoolParameters;

    private static GenericKeyedObjectPool<JschParameters, JschSession> pool;

    private static final JschSessionPoolObjectFactory poolObjectFactory = new JschSessionPoolObjectFactory();

    private static final Logger logger = Logger.getLogger(JschSessionPool.class);

    /**
     * Initialise the pool, using the members we have already set up.
     * 
     * Note we don't do all this initialisation in a static block, or Spring wouldn't be able to
     * keep up.  We delay the actual initialisation until the first time we want the pool.
     */
    private void initialisePool() {

        if (jschSessionPoolParameters == null) {
            logger.warn("Avoiding disaster on pool creation by creating default parameters");
            jschSessionPoolParameters = new JschSessionPoolParameters();
        }

        // GenericKeyedObjectPool is not usable without a KeyedPoolableObjectFactory. A non-null factory must be provided
        // either as a constructor argument or via a call to setFactory before the pool is used.
        pool = new GenericKeyedObjectPool<JschParameters, JschSession>(poolObjectFactory);

        // When non-positive, there is no limit to the number of objects per key.
        pool.setMaxActive(jschSessionPoolParameters.getMaxActive());

        // When negative, there is no limit to the number of objects that may be idle per key.
        pool.setMaxIdle(jschSessionPoolParameters.getMaxIdle());

        pool.setMaxTotal(jschSessionPoolParameters.getMaxTotal());

        pool.setMinIdle(jschSessionPoolParameters.getMinIdle());

        // When whenExhaustedAction is WHEN_EXHAUSTED_GROW, borrowObject will create a new object and return it (essentially making maxActive meaningless.)
        pool.setWhenExhaustedAction(jschSessionPoolParameters.getWhenExhaustedAction());

        // timeBetweenEvictionRunsMillis indicates how long the eviction thread should sleep before "runs" of examining idle objects.
        pool.setTimeBetweenEvictionRunsMillis(jschSessionPoolParameters.getTimeBetweenEvictionRunsMillis());

        // minEvictableIdleTimeMillis specifies the minimum amount of time that an object may sit idle in the pool before it is eligible for eviction due to
        // idle time. This setting has no effect unless timeBetweenEvictionRunsMillis > 0. The default setting for this parameter is 30 minutes.
        pool.setMinEvictableIdleTimeMillis(jschSessionPoolParameters.getMinEvictableIdleTimeMillis());

        // testWhileIdle indicates whether or not idle objects should be validated using the factory's validateObject method during idle object eviction runs.
        // Objects that fail to validate will be dropped from the pool. This setting has no effect unless timeBetweenEvictionRunsMillis > 0.
        pool.setTestWhileIdle(jschSessionPoolParameters.isTestWhileIdle());

        // When a negative value is supplied, ceil(getNumIdle())/abs(getNumTestsPerEvictionRun()) tests will be run, i.e., when the value is -n, roughly one nth
        // of the idle objects will be tested per run. When the value is positive, the number of tests actually performed in each run will be the minimum of this
        // value and the number of instances idle in the pools. 
        pool.setNumTestsPerEvictionRun(jschSessionPoolParameters.getNumTestsPerEvictionRun());

        // When true, objects will be validated before being returned to the pool within the returnObject(K, V)
        pool.setTestOnReturn(jschSessionPoolParameters.isTestOnReturn());

        // When true, objects will be validated by the idle object evictor (if any). If an object fails to validate, it will be dropped from the pool.
        pool.setTestWhileIdle(jschSessionPoolParameters.isTestWhileIdle());

        // When true, objects will be validated before being returned by the borrowObject(K) method. If the object fails to validate, it will be dropped
        // from the pool, and we will attempt to borrow another.
        pool.setTestOnBorrow(jschSessionPoolParameters.isTestOnBorrow());

        // Sets the cap on the total number of instances from all pools combined. When maxTotal is set to a positive value and borrowObject is invoked when at the
        // limit with no idle instances available, an attempt is made to create room by clearing the oldest 15% of the elements from the keyed pools. 
        pool.setMaxTotal(jschSessionPoolParameters.getMaxTotal());

        // The pools can be configured to behave as LIFO queues with respect to idle objects - always returning the most recently used object from the pool,
        // or as FIFO queues, where borrowObject always returns the oldest object in the idle object pool.
        //
        // Lifo determines whether or not the pools return idle objects in last-in-first-out order.
        pool.setLifo(jschSessionPoolParameters.isLifo());
    }

    /**
     * Get a session from the pool, creating it if necessary, using the parameters provided.
     * @param parameters all the parameters we need to create a session
     * @return the session
     */
    public JschSession get(JschParameters parameters) throws ExecutionException {
        Stopwatch watch = new Stopwatch().start();
        try {
            JschSession session = getPool().borrowObject(parameters);
            logger.debug(":POOL: retrieving " + session + " from pool [" + Integer.toHexString(pool.hashCode()) + "]");
            return session;
        } catch (Exception e) {
            logger.error("Caught "
                    + e.getClass().getName()
                    + " with message: \""
                    + e.getMessage()
                    + "\" while trying to borrow a session from the pool");
            throw new ExecutionException("Caught exception while trying to borrow a session from the pool ["
                    + Integer.toHexString(pool.hashCode())
                    + "]",
                    e);
        } finally {
            logger.debug("get JSch session took " + watch.elapsedMillis() + " ms");
            watch.stop();
        }
    }

    /**
     * Return a session to the pool.
     * @param parameters The parameters originally used to create this session.
     * @param session The session itself.
     * @throws ExecutionException We map any exceptions thrown into one of these.
     */
    public void returnObject(JschParameters parameters, JschSession session) throws ExecutionException {
        Stopwatch watch = new Stopwatch().start();
        try {
            if (pool == null) {
                // Trying to return a session to a non existent pool.  Kill the session, or it will be
                // lost forever.
                logger.warn("Trying to return session " + session + " to a closed (i.e. null) pool - killing the session");
                session.close();
                return;
            }
            logger.debug(":POOL: returning " + session + " to pool [" + Integer.toHexString(pool.hashCode()) + "]");
            getPool().returnObject(parameters, session);
        } catch (Exception e) {
            throw new ExecutionException("Caught an exception while trying to return session "
                    + session
                    + " to the pool "
                    + parameters.toString(), e);
        } finally {
            logger.debug("returnObject for JSch session took " + watch.elapsedMillis() + " ms");
            watch.stop();
        }
    }

    public GenericKeyedObjectPool<JschParameters, JschSession> getPool() {
        if (pool == null) {
            initialisePool();
            logger.debug(":POOL: created pool [" + Integer.toHexString(pool.hashCode()) + "]");
        }
        return pool;
    }

    public static JschSessionPoolObjectFactory getPoolObjectFactory() {
        return poolObjectFactory;
    }

    public JschSessionPoolParameters getJschSessionPoolParameters() {
        return jschSessionPoolParameters;
    }

    /**
     * Set the session pool parameters.  There are two different things to do here depending on
     * whether the pool has been created yet.  If it hasn't, our job is easy, we set the parameters
     * and return.  If the pool has been created, we must force all of these parameters into the
     * existing pool.
     *  
     * @param jschSessionPoolParameters The session pool parameters.
     */
    public void setJschSessionPoolParameters(JschSessionPoolParameters jschSessionPoolParameters) {
        JschSessionPool.jschSessionPoolParameters = jschSessionPoolParameters;
        if (pool != null) {
            pool.setMaxActive(jschSessionPoolParameters.getMaxActive());
            pool.setMaxIdle(jschSessionPoolParameters.getMaxIdle());
            pool.setWhenExhaustedAction(jschSessionPoolParameters.getWhenExhaustedAction());
            pool.setTimeBetweenEvictionRunsMillis(jschSessionPoolParameters.getTimeBetweenEvictionRunsMillis());
            pool.setMinEvictableIdleTimeMillis(jschSessionPoolParameters.getMinEvictableIdleTimeMillis());
            pool.setTestWhileIdle(jschSessionPoolParameters.isTestWhileIdle());
            pool.setLifo(jschSessionPoolParameters.isLifo());
        }
    }

    public int getMaxActive() {
        return getPool().getMaxActive();
    }

    public int getMaxIdle() {
        return getPool().getMaxIdle();
    }

    public byte getWhenExhaustedAction() {
        return getPool().getWhenExhaustedAction();
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return getPool().getTimeBetweenEvictionRunsMillis();
    }

    public long getMinEvictableIdleTimeMillis() {
        return getPool().getMinEvictableIdleTimeMillis();
    }

    public boolean getTestWhenIdle() {
        return getPool().getTestWhileIdle();
    }

    public boolean getLifo() {
        return getPool().getLifo();
    }

    /**
     * As mentioned in JschSessionPoolObjectFactory, Richard O discovered various problems when Tomcat is shutdown
     * and the pool errs on the side of caution, giving the pooled objects their allotted interval before killing them
     * off.  This appears to make Tomcat hang, although at the time of writing we aren't sure if this is due to the
     * session disconnect being interrupted by null pointer exceptions thrown while attempting to log.
     * 
     * This code attempts to immediately kill every session the pool knows about with extreme prejudice and whether
     * the session is in use or not.
     */
    @PreDestroy
    public void closeThePool() {

        try {
            // logging may be error prone (see comments elsewhere), hence the try/catch around here.
            logger.warn(":POOL: closing pool [" + Integer.toHexString(pool.hashCode()) + "]");
        } catch (Exception ignored) {
            // really, really ignored.
        }

        // turn off the logging of JschSession objects.  We can't do this enough.
        JSch.setLogger(null);
        if (pool != null) {
            try {
                pool.close();
                getPoolObjectFactory().killTheSwimmers();
            } catch (Exception e) {
                try {
                    logger.error("Caught exception while trying to close the jsch session pool", e);
                } catch (Exception ignored) {
                    // really, really ignored.
                }
            }
        }

        // I believe this step to be inherently unwise.  Doing this will close this pool, but cause
        // the next call to open an entirely new one.  Thus our singleton becomes... well... not a
        // singleton.  The reason I'm doing this is because the tests fail if I don't.  Basically
        // since they all run in the same JVM, the first one that closes the pool stuffs all the other
        // tests that are run after it (they all fail with "pool closed").
        //
        // I considered adding some kind of "test mode".  That is left as an exercise for the reader.
        pool = null;
    }

    /**
     * We return a string representation of this pool as a unique number so we can keep track of this sucker.
     * This is only for debugging purposes.
     * Fortunately our Object's default hashcode is smart enough to return a unique number for each pool
     * (supposedly based upon its address).  This is basically here so we can check how many pools are
     * actually in use - should be one, but you can't be too sure.
     */
    //@VisibleForDebugging
    @Override
    public String toString() {
        return "[" + Integer.toHexString(hashCode()) + "]";
    }
}

/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.jsch;

import org.apache.commons.pool.impl.GenericKeyedObjectPool;

/**
 * These are the parameters used to create the JschSessionPool.  This object is managed by
 * Spring, thus allowing the way the pool works to be varied.  The pool itself is created
 * statically by Java.
 */
public class JschSessionPoolParameters {

    private final long SECOND = 1000L;
    private final long MINUTE = 60 * SECOND;
    private final long EVICTION_INTERVAL_DEFAULT = 30 * SECOND;
    private final long MIN_EVICTABLE_IDLE_TIME = 2 * MINUTE;

    private int maxActive = 10;

    private int maxIdle = 5;

    private byte whenExhaustedAction = GenericKeyedObjectPool.WHEN_EXHAUSTED_BLOCK;

    private long timeBetweenEvictionRunsMillis = EVICTION_INTERVAL_DEFAULT;

    private long minEvictableIdleTimeMillis = MIN_EVICTABLE_IDLE_TIME;

    private boolean testWhileIdle = true;

    private boolean lifo = true;

    private int numTestsPerEvictionRun = 50;

    private boolean testOnReturn = false;

    private boolean testOnBorrow = true;

    private int maxTotal = 10;

    private int minIdle = 5;

    //================================================================================

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public byte getWhenExhaustedAction() {
        return whenExhaustedAction;
    }

    public void setWhenExhaustedAction(byte whenExhaustedAction) {
        this.whenExhaustedAction = whenExhaustedAction;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRuns) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRuns;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTime) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTime;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public boolean isLifo() {
        return lifo;
    }

    public void setLifo(boolean lifo) {
        this.lifo = lifo;
    }

    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
}

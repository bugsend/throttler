package org.serge.lib.throttler;

/**
 * Base interface for throttlers
 *
 * @author Serge < comp1986@gmail.com />
 * @version 0.1
 */
public interface Throttler {

    /**
     * Job for throttler to perform
     *
     * @throws InterruptedException
     */
    void doJob() throws InterruptedException;

}

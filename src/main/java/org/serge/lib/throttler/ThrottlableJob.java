package org.serge.lib.throttler;

/**
 * Interface for jobs to be throttled
 *
 * @author Serge < comp1986@gmail.com />
 * @version 0.1
 */
public interface ThrottlableJob {

    /**
     * A job to perform under the narrow eye of a throttle mechanism.
     */
    void job();

}

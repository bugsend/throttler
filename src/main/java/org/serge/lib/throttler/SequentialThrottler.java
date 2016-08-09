package org.serge.lib.throttler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Sequential throttler implementation.
 * Jobs are executed one by one during specified time range ( in milliseconds )
 * and not exceeding specified number of executions.
 *
 * @author Serge < comp1986@gmail.com />
 * @version 0.1
 *
 * Note: this is a big subject for further improvements.
 */
public class SequentialThrottler<T extends ThrottlableJob> implements Throttler {

    private Lock lock = new ReentrantLock();
    private Condition waitForNewTimeFrame = lock.newCondition();
    private volatile int count = 0;
    private final T job;
    private final int N;
    private final int period;

    public SequentialThrottler(int threshold, int period, T job) {
        this.N = threshold;
        this.job = job;
        this.period = period;
        handleForGate();
    }

    public void doJob() throws InterruptedException {
        lock.lock();
        try {

            while (true) {

                job.job();
                count++;

                if ( count == N) {
                    waitForNewTimeFrame.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void handleForGate() {
        Thread handler = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(period);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.lock();
                    try {
                        waitForNewTimeFrame.signal();
                        count = 0;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        handler.start();
    }
}

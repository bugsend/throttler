package org.serge.lib.throttle;

import org.junit.Test;
import org.serge.lib.throttler.SequentialThrottler;
import org.serge.lib.throttler.Throttler;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by szaytsev on 09/08/2016.
 */
public class SequentialThrottlerTest {

    @Test
    public void testSequentialThrottler() throws InterruptedException {

        Throttler t = new SequentialThrottler<>(13, 900, () -> {
            try {
                Thread.sleep(Math.abs(ThreadLocalRandom.current().nextLong(12, 98)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.print(" J. ");
        });
        t.doJob();

    }

}

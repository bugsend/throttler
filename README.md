### Throttler
 
This project helps its users to implement throttling functionality.

Right now it only supports sequential job's calling. Concurrent jobs exection is
a subject for further work since it needs to create efficient job's termination logic.

So, the whole point of throttling is to perform some action not more than N times 
during some period of time T.

The following is a usage example:
<code>
Throttler t = new SequentialThrottler<>(13, 900, () -> {
            try {
                Thread.sleep(Math.abs(ThreadLocalRandom.current().nextLong(12, 98)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.print(" J. ");
        });
        t.doJob();
</code>

Here: 13 - is how many times job is about to execute
      900 - time range in milliseconds for job to being executed.

That's it.
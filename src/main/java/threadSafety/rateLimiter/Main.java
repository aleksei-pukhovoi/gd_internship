package threadSafety.rateLimiter;

import collections.pattern.ratelimite.RateLimit;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimit fw = new FixedWindowParallel(2, ChronoUnit.SECONDS);
        test(fw);
        System.out.println("+++++++");
        RateLimit sw = new SlidingWindowParallel(2, ChronoUnit.SECONDS);
        test(sw);
    }

    public static void test(RateLimit rm) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Runnable task = () -> System.out.println(rm.isAllowed("Anna") + " - " + Thread.currentThread().getName());
        es.execute(task);
        es.execute(task);
        es.execute(task);
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
    }
}

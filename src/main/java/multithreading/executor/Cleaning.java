package multithreading.executor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Cleaning {
    public static void main(String[] args) throws Exception {
        ExecutorService es1 = Executors.newCachedThreadPool();
        ExecutorService es2 = Executors.newFixedThreadPool(2);
        ExecutorService es3 = Executors.newSingleThreadExecutor();
        System.out.println("Time of execution using multithreading:");
        executeWithMultiThreads(es1);
        executeWithMultiThreads(es2);
        executeWithMultiThreads(es3);
        System.out.println("+++++++++++++++");
        System.out.println("Time of execution using one thread only:");
        executeWithOneThread();
    }

    private static void executeWithMultiThreads(ExecutorService es) throws InterruptedException {
        System.out.println("Starting cleaning up: " + es.getClass().getSimpleName());
        LocalTime start = LocalTime.now();
        es.execute(new HomeWork(CleaningType.CLOTHES));
        es.execute(new HomeWork(CleaningType.DUST));
        es.execute(new HomeWork(CleaningType.MESS));
        es.execute(new HomeWork(CleaningType.FLOOR));
        es.shutdown();
        es.awaitTermination(10, TimeUnit.SECONDS);
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start,end);
        System.out.println("Everything is done for " + duration.toMillis());
    }

    private static void executeWithOneThread() throws InterruptedException {
        System.out.println("Starting cleaning up");
        LocalTime start = LocalTime.now();
        CleaningType.CLOTHES.clean();
        CleaningType.MESS.clean();
        CleaningType.DUST.clean();
        CleaningType.FLOOR.clean();
        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(start,end);
        System.out.println("Everything is done for " + duration.toMillis());
    }
}

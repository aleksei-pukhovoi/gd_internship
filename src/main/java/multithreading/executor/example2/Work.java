package multithreading.executor.example2;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Work {
    private int count;

    public Work(int count) {
        this.count = count;
    }

    public void execute() throws InterruptedException {
        long prepareTime = prepare();
        System.out.println("Preparing: " + prepareTime);
        long communicateTime = communicate(count);
        System.out.println("Talking with another workers: " + communicateTime);
        long mowTime = mow();
        System.out.println("Mowing: " + mowTime);
        System.out.printf("Total time of %d worker(s): %d\n", count, (prepareTime + communicateTime + mowTime));
    }

    private long mow() throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        LocalTime start = LocalTime.now();
        for (int i = 0; i < count; i++) {
            es.execute(new Task(count));
        }
        es.shutdown();
        es.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        LocalTime end = LocalTime.now();
        return Duration.between(start,end).toMillis();
    }

    private long communicate(int count) {
        LocalTime start = LocalTime.now();
        if (count > 1) {
            try {
//                System.out.println("Talking with another worker");
                Thread.sleep(100L * count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LocalTime end = LocalTime.now();
        return Duration.between(start,end).toMillis();
    }

    private long prepare() {
        LocalTime start = LocalTime.now();
        try {
//            System.out.println("Preparing for work");
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime end = LocalTime.now();
        return Duration.between(start,end).toMillis();
    }
    
}

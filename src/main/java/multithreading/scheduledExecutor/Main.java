package multithreading.scheduledExecutor;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
//        schedule();
//        System.out.println("+++++++++++");
//        scheduleAtFixedRate();
//        System.out.println("+++++++++++");
        scheduleWithFixedDelay();
    }

    private static void schedule() throws InterruptedException {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        System.out.println("Current time = " + LocalTime.now());
        for (int i = 0; i < 3; i++) {
            ses.schedule(new Work(), 5, TimeUnit.SECONDS);
            Thread.sleep(1000);
        }
        ses.shutdown();
        ses.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("Finished all threads");
    }

   private static void scheduleAtFixedRate() throws InterruptedException {
       ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
       System.out.println("Current time = " + LocalTime.now());

       //next execution will start after the current execution finishes
           ses.scheduleAtFixedRate(new Work(), 2,3, TimeUnit.SECONDS);

       //next execution will start in period value
//         ses.scheduleAtFixedRate(new Work(), 2, 8, TimeUnit.SECONDS);
    }

    private static void scheduleWithFixedDelay() throws InterruptedException {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        System.out.println("Current time = " + LocalTime.now());

        //the next execution will start in the delay time after the end of the current thread
        ses.scheduleWithFixedDelay(new Work(), 2,3, TimeUnit.SECONDS);
    }
}

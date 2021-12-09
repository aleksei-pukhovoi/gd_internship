package multithreading.scheduledExecutor;

import java.time.LocalTime;

public class Work implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + LocalTime.now());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End. Time = " + LocalTime.now());
    }
}

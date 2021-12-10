package multithreading.executor.example2;

public class Task implements Runnable {

    private int count;

    public Task(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000L / count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

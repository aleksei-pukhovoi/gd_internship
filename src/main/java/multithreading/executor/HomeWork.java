package multithreading.executor;

public class HomeWork implements Runnable{
    CleaningType type;

    public HomeWork(CleaningType type) {
        this.type = type;
    }

    @Override
    public void run() {
        type.clean();
    }
}

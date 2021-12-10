package multithreading.executor.example2;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("1 worker mows lawn");
        new Work(1).execute();
        System.out.println("++++++++++");
        System.out.println("10 workers mow lawn");
        new Work(10).execute();
        System.out.println("++++++++++");
        System.out.println("40 workers mow lawn");
        new Work(40).execute();
    }
}

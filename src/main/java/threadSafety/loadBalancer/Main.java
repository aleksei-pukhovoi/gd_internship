package threadSafety.loadBalancer;

import collections.pattern.loadbalance.LoadBalance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Integer> queries = Arrays.asList(10,20,30,40,50,60,70,80,90,100);
        for (int i = 0; i < 10; i++) {
            System.out.println(multiThreadLoadBalance(new RoundRobinParallel(), queries.get(i)));
        }
    }

    private static boolean multiThreadLoadBalance(LoadBalance loadBalance, int queries) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < queries; i++) {
            Future<String> result = es.submit(new MyTask(loadBalance, i));
            list.add(result);
        }
        AtomicInteger count = new AtomicInteger();
        list.forEach((future) -> {
            try {
                String serverId = future.get();
                if (serverId.contains("192.168.1.10")) {
                    count.incrementAndGet();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        es.shutdown();
        es.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        return count.get() == queries/10;
    }

    static class MyTask implements Callable<String> {
        private LoadBalance lb;
        private int index;

        public MyTask(LoadBalance lb, int index) {
            this.lb = lb;
            this.index = index;
        }

        @Override
        public String call() throws Exception {
            return new StringBuilder()
                    .append("[")
                    .append(lb.getClass().getSimpleName())
                    .append("[")
                    .append(" query: ")
                    .append(Thread.currentThread().getName())
                    .append(", server IP = ")
                    .append(lb.getServer(String.valueOf(index))).toString();
        }
    }
}

package io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Promotion {
    private static boolean flag;
    private ScheduledExecutorService ses;
    private ByteArrayOutputStream outputStream;
    private List<Purchase> events;
    private ObjectMapper mapper;
    private int index;
    private int count;
    private List<Double> prices;
    private String pathName;

    public Promotion(String pathName, List<Double> prices) {
        this.prices = prices;
        this.pathName = pathName;
        ses = Executors.newScheduledThreadPool(2);
        outputStream = new ByteArrayOutputStream();
        events = new ArrayList<>();
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public void startPromotion(int delay) {
        ses.submit(new StoppingPurchasesTask());
        while (!flag) {
            doShopping(delay);
        }
        try {
            stopShopping();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void filterData() throws IOException {
        byte[] buffer = outputStream.toByteArray();
        Purchase purchase = mapper.readValue(buffer, index, buffer.length, Purchase.class);
        index = buffer.length;
        if (!purchase.getCategory().equals(Category.A)) {
            events.add(purchase);
        }
    }

    private void writeResult(String pathName) {
        if (events.size() == 10) {
            Map<Category, Double> map = getTotalInfoByPurchases(events);
            AtomicReference<String> line = new AtomicReference<>("");
            File file = new File(pathName);
            try (OutputStream fos = new FileOutputStream(file, true);
                 BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
                writer.write((++count) + "\n");
                map.forEach((category, revenue) -> {
                    line.set(category + ": " + revenue);
                    try {
                        writer.write(line + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            events.clear();
        }
    }

    private Map<Category, Double> getTotalInfoByPurchases(List<Purchase> list) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        Purchase::getCategory,
                        Collectors.summingDouble(purchase -> purchase.getAmount() * purchase.getItemPrice())
                ));
    }

    private void doShopping(int delay) {
        Callable<ByteArrayOutputStream> task = () -> {
            Purchase purchase = getPurchase(prices);
            try {
                mapper.writeValue(outputStream, purchase);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return outputStream;
        };
        try {
            outputStream = ses.schedule(task, delay, TimeUnit.MILLISECONDS).get();
            filterData();
            writeResult(pathName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Purchase getPurchase(List<Double> prices) {
        Random random = new Random();
        int index = random.nextInt(3);
        Category category = Category.getCategory(index);
        long amount = random.nextInt(100) + 1;
        return new Purchase(amount, category, prices.get(index));
    }

    private void stopShopping() throws InterruptedException {
        ses.shutdown();
        ses.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }

    private static class StoppingPurchasesTask implements Runnable {
        @Override
        public void run() {
            Scanner charScanner = new Scanner(System.in);
            while (charScanner.hasNext()) {
                String c = charScanner.next();
                if (!c.isEmpty()) {
                    flag = true;
                    break;
                }
            }
        }
    }
}

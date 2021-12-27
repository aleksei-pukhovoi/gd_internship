package io;

import java.util.Arrays;
import java.util.List;

public class Store {

    public void startBlackFriday(String path, List<Double> prices, int delayPurchase){
        Promotion promotionBlackFriday = new Promotion(path, prices);
        System.out.println("Starting promotion... Press any key to stop");
        promotionBlackFriday.startPromotion(delayPurchase);
    }

    public static void main(String[] args) {
        String pathResult = "src/main/resources/<batch_number>.txt";
        List<Double> prices = Arrays.asList(10.0, 20.0, 30.0);
        int delayPurchase = 500;
        new Store().startBlackFriday(pathResult,prices, delayPurchase);
    }
}

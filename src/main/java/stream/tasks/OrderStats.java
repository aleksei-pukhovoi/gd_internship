package stream.tasks;

//source code: https://github.com/vladkanash/java8-tasks

import stream.model.*;
import stream.util.AveragingBigDecimalCollector;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class provides several methods to collect statistical information from customers and orders of an e-shop.
 * Your task is to implement methods of this class using Java 8 Stream API.
 * Each method is covered with a number of unit tests. You can use <code>mvn test</code> call to check your implementation.
 *
 * Refer to the <code>com.expertsoft.model</code> package to observe the domain model of the shop.
 */
public class OrderStats {

    /**
     * Task 1 (⚫⚫⚪⚪⚪)
     *
     * Given a stream of customers, return the list of orders, paid using provided credit card type (Visa or MasterCard).
     *
     * @param customers stream of customers
     * @param cardType credit card type
     * @return list, containing orders paid with provided card type
     */
    public static List<Order> ordersForCardType(final Stream<Customer> customers, PaymentInfo.CardType cardType) {
        return customers.flatMap(customer-> customer.getOrders().stream())
                .filter(order -> cardType.equals(order.getPaymentInfo().getCardType()))
                .collect(Collectors.toList());
    }

    /**
     * Task 2 (⚫⚫⚪⚪⚪)
     *
     * Given a stream of orders, return a map, where keys are different order sizes and values are lists of orders,
     * referring to this sizes. Order size here is just a total number of products in the order.
     *
     * @param orders stream of orders
     * @return map, where order size values mapped to lists of orders
     */
    public static Map<Integer, List<Order>> orderSizes(final Stream<Order> orders) {
        return orders.collect(Collectors.groupingBy(
                order -> order.getOrderItems().stream()
                        .map(OrderItem::getQuantity)
                        .mapToInt(Integer::intValue).sum())
        );
    }

    /**
     * Task 3 (⚫⚫⚫⚪⚪)
     *
     * Given a stream of orders, return true only if EVERY order in the stream contains at least
     * one product of the provided color and false otherwise.
     *
     * @param orders stream of orders
     * @param color product color to test
     * @return boolean, representing if every order in the stream contains product of specified color
     */
    public static Boolean hasColorProduct(final Stream<Order> orders, final Product.Color color) {
        return orders.allMatch(order -> order.getOrderItems().stream()
                .anyMatch(orderItem -> color.equals(orderItem.getProduct().getColor())));
    }

    /**
     * Task 4 (⚫⚫⚫⚫⚪)
     *
     * Given a stream of customers, return the map, where customer email is mapped to a number of different credit cards he/she used by the customer.
     *
     * @param customers stream of customers
     * @return map, where for each customer email there is a long referencing a number of different credit cards this customer uses.
     */
    public static Map<String, Long> cardsCountForCustomer(final Stream<Customer> customers) {
        return customers.collect(Collectors.toMap(
                Customer::getEmail,
                customer -> customer.getOrders().stream()
                        .map(order -> order.getPaymentInfo().getCardNumber())
                        .distinct()
                        .count()
        ));
    }

    /**
     * Task 5 (⚫⚫⚫⚫⚫)
     *
     * Given a stream of customers, return the optional, containing the most popular country name,
     * that is, the name of the country set in addressInfo by the biggest amount of customers.
     * If there are two or more countries with the same amount of customers, return the country name that has a smallest length.
     * If customer stream is empty, Optional.empty should be returned.
     *
     * Example: For the stream, containing
     *      Customer#1 -> USA
     *      Customer#2 -> France
     *      Customer#3 -> Japan
     *      Customer#4 -> USA
     *      Customer#5 -> Japan
     *
     *      "USA" should be returned.
     *
     * @param customers stream of customers
     * @return java.util.Optional containing the name of the most popular country
     */
    public static Optional<String> mostPopularCountry(final Stream<Customer> customers) {
        return customers.map(customer -> customer.getAddress().getCountry())
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()))
                .entrySet().stream()
                .max((entry1,entry2)->
                        Objects.equals(entry1.getValue(), entry2.getValue()) ?
                                entry2.getKey().length() - entry1.getKey().length() :
                                entry1.getValue().compareTo(entry2.getValue()))
                .map(Map.Entry::getKey);
    }

    /**
     * Task 6 (⚫⚫⚫⚫⚫)
     *
     * Given a stream of customers, return the average product price for the provided credit card number.
     *
     * Info: If order contains the following order items:
     *  [
     *      Product1(price = 100$, quantity = 2),
     *      Product2(price = 160$, quantity = 1)
     *  ]
     * then the average product price for this order will be 120$ ((100 * 2 + 160 * 1) / 3)
     *
     * Hint: Since product prices are represented as BigDecimal objects, you are provided with the collector implementation
     *       to compute the average value of BigDecimal stream.
     *
     * @param customers stream of customers
     * @param cardNumber card number to check
     * @return average price of the product, ordered with the provided card
     */
    public static BigDecimal averageProductPriceForCreditCard(final Stream<Customer> customers, final String cardNumber) {
        final AveragingBigDecimalCollector collector = new AveragingBigDecimalCollector();
        return customers
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> cardNumber.equals(order.getPaymentInfo().getCardNumber()))
                .flatMap(order -> order.getOrderItems().stream())
                .flatMap(item-> Collections.nCopies(item.getQuantity(), item.getProduct().getPrice()).stream())
                .collect(collector);
    }
}

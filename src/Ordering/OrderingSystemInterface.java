package Ordering;

import java.util.Map;

/**
 * Created by Mitziu on 11/3/16.
 * Group B4
 */
public interface OrderingSystemInterface {

    /**
     * Method that is used to place an order / Starts the order process
     * @param items Map of items ID and quantity
     * @param address Address the order is being shipped to
     * @return Boolean. True means the order is able to be fulfilled, false otherwise
     */
    Boolean placeOrder(Map<Integer, Integer> items, String address);

}

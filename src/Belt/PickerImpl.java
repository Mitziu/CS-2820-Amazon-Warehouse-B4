package Belt;

import Ordering.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Picker acts as a middle man between Orders and Belt.
 * Orders passes the information of the items that need to be filled.
 * Until the order is completely fulfilled it does not get passed to Belt
 * Created by Mitziu on 11/17/16.
 */
public class PickerImpl implements Picker, Observer {

    private List<OrderToFulfill> ongoingOrders;
    private Belt myBelt;

    private Queue<OrderToFulfill> finishedOrdersQueue;

    public PickerImpl (Belt belt) {
        this.ongoingOrders = new ArrayList<>();
        this.myBelt = belt;
        finishedOrdersQueue = new LinkedList<>();
    }

    /**
     * Informs the Picker of a new order that will need to be fulfilled
     * @author Mitziu
     * @param newOrder Order to be fulfilled
     */
    @Override
    public void newOrder(Order newOrder) {
        Map<Integer, Integer> items = new HashMap<>(newOrder.getItemIDList());
        Integer orderID = newOrder.getOrderID();
        ongoingOrders.add(new OrderToFulfill(items, orderID));
    }

    /**
     * Informs the Picker that a new shelf has arrived with items to fulfill an order
     * @author Mitziu
     * @param shelf Belt.Shelf has arrived
     */
    @Override
    public void shelfArrived(Shelf shelf) {
        shelf.itemsOnShelf().stream()
                .forEach(itemID -> pickItemsFromShelf(itemID));
    }

    /**
     * Picks the item(s) off of the shelf
     * @param itemID ID of item
     */
    private void pickItemsFromShelf(Integer itemID) {
        ongoingOrders.stream()
                .filter(currentOrder -> currentOrder.orderNeedsItem(itemID))
                .forEach(currentOrder -> currentOrder.removeItem(itemID));
    }

    /**
     * Gets a list of orders that are ready to be sent to the belt
     * @return List of orders that are ready to be sent to the belt
     */
    private List<OrderToFulfill> getFinishedOrders() {
       return ongoingOrders.stream()
                .filter(order -> order.orderFinished())
                .collect(Collectors.toList());
    }

    /**
     * Adds the orderID to the queue of orders ready to be sent to the belt
     * @param ordersReady List of orders that are ready to be sent to the belt
     */
    private void addFinishedOrders(List<OrderToFulfill> ordersReady) {
        ordersReady.stream()
                .forEach(order -> finishedOrdersQueue.add(order));
    }

    /**
     * Removes the Orders that are finished from the ongoingOrders List
     * @param ordersReady List of orders that are ready to be sent to the belt
     */
    private void removeFinishedOrders(List<OrderToFulfill> ordersReady) {
        ongoingOrders.removeAll(ordersReady);
    }

    /**
     * Sends the head of the queue to the Belt
     */
    private void sendToBelt () {
        myBelt.pack(finishedOrdersQueue.poll().getOrderID());
    }

    /**
     * Method that gets called every tick, this will make sure that the fulfilled orders keep
     * advancing
     */
    @Override
    public void update(Observable o, Object arg) {
        List<OrderToFulfill>  finishedOrdersList = getFinishedOrders();
        addFinishedOrders(finishedOrdersList);
        removeFinishedOrders(finishedOrdersList);

        sendToBelt();
    }

    /**
     * Private class to fulfill Orders that are passed to Picker
     */
    private class OrderToFulfill {

        private Map<Integer, Integer> items;
        private Integer orderID;

        public OrderToFulfill (Map<Integer, Integer> items, Integer orderID) {
            this.items = items;
            this.orderID = orderID;
        }

        public Integer getOrderID() {
            return orderID;
        }

        /**
         * Checks if the Order needs that specific item
         * @param itemID ID of item to check
         * @return Whether or not that order needs that specific item
         */
        public Boolean orderNeedsItem (Integer itemID) {
            return items.keySet().contains(itemID);
        }

        /**
         * Removes the items from the on going Order
         * @param itemID ID of the item to remove
         */
        public void removeItem(Integer itemID) {
            items.remove(itemID);
        }

        /**
         * Checks to see if the order has been fulfilled
         * @return Whether or not the order has been fulfilled
         */
        public Boolean orderFinished() {
            return items.isEmpty();
        }
    }

}

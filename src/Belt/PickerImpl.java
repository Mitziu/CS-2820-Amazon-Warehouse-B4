package Belt;

import Floor.ObjectInWarehouse;
import Floor.Point;
import Inventory.S_Manager;
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
public class PickerImpl implements Picker, Observer, ObjectInWarehouse {

    //TODO: Pass Shelf Manager and deduct items from it

    private List<OrderToFulfill> ongoingOrders;
    private Belt myBelt;
    private S_Manager shelfManager;
    private Integer shelfID;

    private Queue<OrderToFulfill> finishedOrdersQueue;
    private Point location;

    public PickerImpl (Belt belt, S_Manager shelfManager) {
        this.ongoingOrders = new ArrayList<>();
        this.myBelt = belt;
        this.shelfManager = shelfManager;
        finishedOrdersQueue = new LinkedList<>();
    }

    /**
     * Informs the Picker of a new order that will need to be fulfilled
     * @author Mitziu
     * @param newOrder Order to be fulfilled
     */
    @Override
    public void newOrder(Order newOrder) {
        Map<Integer, Integer> items = new HashMap<>(newOrder.getItemIDMap());
        Integer orderID = newOrder.getOrderID();
        ongoingOrders.add(new OrderToFulfill(items, orderID));
    }

    /**
     * Informs the Picker that a new shelf has arrived with items to fulfill an order
     * @author Mitziu
     * @param shelfID Belt.Shelf has arrived
     */
    @Override
    public void shelfArrived(Integer shelfID) {
        this.shelfID = shelfID;

        shelfManager.Item_List(shelfID).stream()
                .forEach(itemID -> pickItemsFromShelf((Integer) itemID));
    }

    /**
     * @author Matt McCan
     * @return location
     */
    @Override
    public Point getPosition () {
        return location;
    }

    /**
     * Picks the item(s) off of the shelf
     * @param itemID ID of item
     */
    private void pickItemsFromShelf(Integer itemID) {
        List<OrderToFulfill> ongoingOrdersThatNeedItem = ongoingOrders.stream()
                .filter(currentOrder -> currentOrder.orderNeedsItem(itemID))
                .collect(Collectors.toList());

        ongoingOrdersThatNeedItem.stream()
                .forEach(orderToFulfill -> {
                    Integer itemsNeeded = orderToFulfill.qtyNeeded(itemID);
                    if (shelfManager.Container_Count(itemID, shelfID) >= itemsNeeded) {
                        shelfManager.Take_Container(itemID, shelfID, itemsNeeded);
                        orderToFulfill.removeItem(itemID);
                    }
                });
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
        if (!finishedOrdersQueue.isEmpty()) {
            System.out.println("DEBUG: Picker, Sending Order # " + finishedOrdersQueue.peek().getOrderID() + " to Belt");
            myBelt.pick(finishedOrdersQueue.poll().getOrderID());
        }
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

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void move(Integer x, Integer y) {
        location = new Point(x , y);
    }

    //UNUSED, HAVE TO OVERRIDE DUE TO OBJECTINWAREHOUSE INTERFACE
    @Override
    public Integer getID() {
        return null;
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

        public Integer qtyNeeded (Integer itemID) {
            return items.get(itemID);
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

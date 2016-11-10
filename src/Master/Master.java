package Master;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

/**
 * Created by Mitziu on 11/3/16.
 * Group B4
 */
public class Master implements Observer {

    private Queue<UpcomingOrder> upcomingOrders;
    private Integer time;

    private OrderingSystemInterface orderSystem;

    @Override
    public void update(Observable o, Object arg) {
        //Initializes the timer
        if (time == null) {
            time = 0;
        } else {
            time ++;
        }

        checkNewOrders();
    }

    public Master() {
        upcomingOrders = new LinkedList<>();
        orderSystem = new MockOrderingSystem();

        loadOrderQueue();
    }

    public Queue<UpcomingOrder> getUpcomingOrders() {
        return this.upcomingOrders;
    }

    public Integer getTime() {
        return this.time;
    }

    /**
     * Loads the Simulated Ordering.OrderingSystemInterface from the upcomingOrders CSV file
     */
    private void loadOrderQueue() {

        String file = "CSVFiles/upcomingOrders";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String currentLine;

            while ( (currentLine = br.readLine()) != null ) {
                loadCurrentOrder(currentLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Adds an order to the queue
     * @param orderInformation Information per order
     */
    private void loadCurrentOrder(String orderInformation) {
        upcomingOrders.add(createOrder(orderInformation));
    }

    /**
     * Creates an upcomingOrder object
     * @param orderInformation Information per order
     * @return
     */
    private UpcomingOrder createOrder(String orderInformation) {
        String[] orderInfo = orderInformation.split(",");

        if ( ! (orderInfo.length >= 4) || (orderInfo.length % 2 != 0) ){
            throw new IllegalArgumentException("Illegal number of Arguments");
        }

        Integer timeToOrder = Integer.valueOf(orderInfo[0]);
        Map<Integer, Integer> items = buildItemsMap(orderInfo);
        String address = orderInfo[orderInfo.length - 1];

        return new UpcomingOrder(timeToOrder, items, address);
    }

    /**
     * Builds the map of ID and quantity
     * @param orderInfo Array of Strings read from the upcomingOrders file
     * @return Map. ID is key and quantity is the value
     */
    private Map<Integer, Integer> buildItemsMap (String[] orderInfo) {
        Map<Integer, Integer> items = new HashMap<>();

        for (int i = 1; i < orderInfo.length - 2; i += 2) {
            Integer ID = Integer.valueOf(orderInfo[i]);
            Integer quantity = Integer.valueOf(orderInfo[i + 1]);
            items.put(ID, quantity);
        }

        return items;
    }

    /**
     * Checks for new orders when the clock ticks
     */
    private void checkNewOrders() {
        while (upcomingOrders.peek().getTimeToOrder() == time) {
            UpcomingOrder currentOrder = upcomingOrders.poll();
            orderSystem.placeOrder(currentOrder.getItems() , currentOrder.getAddress());
        }
    }

}









import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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

    private Orders orderSystem;

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
        orderSystem = new MockOrders();

        loadOrderQueue();
    }

    public Queue<UpcomingOrder> getUpcomingOrders() {
        return this.upcomingOrders;
    }

    public Integer getTime() {
        return this.time;
    }

    /**
     * Loads the Simulated Orders from the upcomingOrders CSV file
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
        List<String> stringOrderInfo = new LinkedList<>(Arrays.asList(orderInformation.split(",")));

        Integer timeToOrder = Integer.valueOf(stringOrderInfo.get(0));
        stringOrderInfo.remove(0);

        List<Integer> IDs = new ArrayList<>();
        stringOrderInfo.stream()
                .forEachOrdered(item -> IDs.add(Integer.valueOf(item)));

        return new UpcomingOrder(timeToOrder, IDs);
    }

    /**
     * Checks for new orders when the clock ticks
     */
    private void checkNewOrders() {
        while (upcomingOrders.peek().getTimeToOrder() == time) {
            orderSystem.placeOrder(upcomingOrders.poll().getItemLists());
        }
    }

}









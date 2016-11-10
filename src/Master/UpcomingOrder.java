package Master;

import java.util.Map;

/**
 * Created by Mitziu on 11/3/16.
 */
public class UpcomingOrder {
    private Integer timeToOrder;
    private Map<Integer, Integer> items;
    private String address;

    public UpcomingOrder(Integer timeToOrder, Map<Integer, Integer> items, String address) {
        if (items.isEmpty()) {
            throw new IllegalStateException("Ordering.Order has to include at least 1 item");
        }

        if (timeToOrder < 0) {
            throw new IllegalStateException("Time to Ordering.Order has to be greater than or equal to zero");
        }

        if (address == "") {
            throw new IllegalArgumentException("Address cannot be empty");
        }

        this.timeToOrder = timeToOrder;
        this.items = items;
        this.address = address;
    }

    /**
     * Returns the time when that order will be activated
     * @return Time of activation
     */
    public Integer getTimeToOrder() {
        return timeToOrder;
    }

    /**
     * Returns the map of items and quantity
     * @return map of item ID and quantity
     */
    public Map<Integer, Integer> getItems() {
        return items;
    }

    public String getAddress () {
        return this.address;
    }
}

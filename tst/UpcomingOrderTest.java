import Master.UpcomingOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mitziu on 11/3/16.
 * B4
 */
public class UpcomingOrderTest {

    UpcomingOrder upcomingOrder;
    Map<Integer, Integer> items;

    final private String fakeAddress = "100 Testing Address Iowa City IA";
    final private Integer ID = 10;
    final private Integer quantity = 1;
    final private Integer timeToOrder = 0;

    @Before
    public void setup() {
        items = new HashMap<>();
    }

    @Test
    public void correctParameters() {
        items.put(ID,1);

        upcomingOrder = new UpcomingOrder(timeToOrder, items, fakeAddress);

        Assert.assertTrue(upcomingOrder.getTimeToOrder() == timeToOrder);

        Assert.assertTrue(upcomingOrder.getItems().size() == 1);
        Assert.assertTrue(upcomingOrder.getItems().get(ID) == quantity);

        Assert.assertTrue(upcomingOrder.getAddress().equals(fakeAddress));
    }

    @Test (expected = IllegalStateException.class)
    public void emptyListException() {
        upcomingOrder = new UpcomingOrder(timeToOrder, items, "Testing Address");
    }

    @Test (expected = IllegalStateException.class)
    public void negativeTimeException() {
        items.put(ID , quantity);
        upcomingOrder = new UpcomingOrder(-1, items, "Testing Address");
    }

    @Test (expected = IllegalArgumentException.class)
    public void emptyAddressException() {
        items.put(ID , quantity);
        upcomingOrder = new UpcomingOrder(timeToOrder, items, "");
    }

}

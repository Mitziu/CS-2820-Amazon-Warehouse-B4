import Clock.Clock;
import Master.Master;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Master.UpcomingOrder;

import java.util.Queue;



/**
 * Created by Mitziu on 11/3/16.
 * Group B4
 */
public class MasterTest {

    Master master;
    Clock clock;

    private final Integer[] expectedTimeToArrive = {0,10,15};

    @Before
    public void setup() {
        clock = new Clock();
        master = new Master();
        clock.addObserver(master);
    }

    /**
     * Verifies that Master loads the upcoming orders from the CSV file
     */
    @Test
    public void masterLoadOrders() {
        Assert.assertTrue(!master.getUpcomingOrders().isEmpty());
    }

    /**
     * Verifies that the mock orders loaded from the CSV file match was was expected
     */
    @Test
    public void loadCorrectValues() {
        Queue<UpcomingOrder> upcomingOrders = master.getUpcomingOrders();

        for (int i = 0; i < expectedTimeToArrive.length; i++) {
            Assert.assertTrue(upcomingOrders.poll().getTimeToOrder() == expectedTimeToArrive[i]);
        }
    }

    /**
     * Verifies that time inside Master is working properly
     */
    @Test
    public void tickAdvancesTime() {
        clock.tick();

        Assert.assertTrue(master.getTime() == 0);
    }

    /**
     * Verifies that the order is sent to order when the time of arrival has come
     */
    @Test
    public void orderGoesToProcess() {
        clock.tick();

        Queue<UpcomingOrder> upcomingOrders = master.getUpcomingOrders();
        for (int i = 1; i < expectedTimeToArrive.length; i++) {
            Assert.assertTrue(upcomingOrders.poll().getTimeToOrder() == expectedTimeToArrive[i]);
        }

    }

}

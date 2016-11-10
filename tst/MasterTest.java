import Clock.Clock;
import Master.Master;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void masterLoadOrders() {
        Assert.assertTrue(!master.getUpcomingOrders().isEmpty());
    }

    @Test
    public void loadCorrectValues() {
        Queue<Master.UpcomingOrder> upcomingOrders = master.getUpcomingOrders();

        for (int i = 0; i < expectedTimeToArrive.length; i++) {
            Assert.assertTrue(upcomingOrders.poll().getTimeToOrder() == expectedTimeToArrive[i]);
        }
    }

    @Test
    public void tickAdvancesTime() {
        clock.tick();

        Assert.assertTrue(master.getTime() == 0);
    }

    @Test
    public void orderGoesToProcess() {
        clock.tick();

        Queue<Master.UpcomingOrder> upcomingOrders = master.getUpcomingOrders();
        for (int i = 1; i < expectedTimeToArrive.length; i++) {
            Assert.assertTrue(upcomingOrders.poll().getTimeToOrder() == expectedTimeToArrive[i]);
        }

    }

}

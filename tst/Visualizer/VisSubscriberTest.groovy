package Visualizer

import Clock.Clock
import Floor.Point
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Matt on 11/14/2016.
 * @author Matt McCan
 */
class VisSubscriberTest extends GroovyTestCase {
    VisSubscriber vs;
    Clock clk;
    Map<String, Point> dumVals;

    @Before
    void setUp() {
        super.setUp()
        VisSubscriber vs = new VisSubscriber();
        Clock clk = new Clock();
        clk.addObserver(vs);
        Map<String, Point> dumVals = new HashMap<>();

        dumVals.put("Bender_Is_The_Greatest_Robot_Ever", new Point(3, 5));
        dumVals.put("A_Shelf", new Point(3, 6));
        dumVals.put("Any_Other_Object_In_Warehouse", new Point(1,1));
    }

    @Test
    /**
     * @author Matt
     * Ensure that visualizer gets the new map of floor
     */
    void testUpdate() {
        clk.notifyObservers(dumVals);
        Assert.assertEquals(dumVals, Visualizer.floorMap);
    }

}

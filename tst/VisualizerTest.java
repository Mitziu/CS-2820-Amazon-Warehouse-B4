import Visualizer.*;
import Clock.Clock;
import Floor.FloorPositions;
import Floor.MockFloor;
import Floor.Point;
import Visualizer.Visualizer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Mitziu on 11/15/16.
 * @author Mitziu Echeverria
 * @author Matthew McCan
 */
public class VisualizerTest {

    VisRunner vs;
    Clock clk;

    Map<String, Point> dumVals;
    FloorPositions floorPositions;

    @Before
    public void setup() {
        floorPositions = new MockFloor();
        vs = new VisRunner(floorPositions);
        clk = new Clock();
        clk.addObserver(vs);
        dumVals = new HashMap<>();

        dumVals.put("Bender_Is_The_Greatest_Robot_Ever", new Point(3, 5));
        dumVals.put("A_Shelf", new Point(3, 6));
        dumVals.put("Any_Other_Object_In_Warehouse", new Point(1,1));
    }

    @Test
    /**
     * @author Matt
     * Ensure that visualizer gets the new map of floor
     */
    public void testUpdate() {
        clk.tick();
        //Assert.assertEquals(vs.floorPositions.getAllPositions(), vs.visualizer.floorMap); //Check output to see if it's correct.
    }
}

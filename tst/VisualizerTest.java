import Clock.Clock;
import Floor.MockFloor;
import Floor.Point;
import Visualizer.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;



/**
 * Created by Mitziu on 11/15/16.
 * @author Mitziu Echeverria
 * @author Matthew McCan
 */
public class VisualizerTest {

    VisualizerRecorder vs;
    Clock clk;

    HashMap<String, Point> dumVals;
    HashMap<String, Point> dumVals2;
    HashMap<String, Point> dumVals3;
    MockFloor floorPositions;

    @Before
    public void setup() {
        floorPositions = new MockFloor();
        vs = new VisualizerRecorder(floorPositions);
        clk = new Clock();
        clk.addObserver(vs);
        dumVals = new HashMap<>();
        dumVals2 = new HashMap<>();
        dumVals3 = new HashMap<>();

        dumVals.put("Bender_Is_The_Greatest_Robot_Ever", new Point(3, 5));
        dumVals.put("A_Shelf", new Point(3, 6));
        dumVals.put("Any_Other_Object_In_Warehouse", new Point(1,1));

        dumVals2.put("Bender_Is_The_Greatest_Robot_Ever", new Point(2, 5));
        dumVals2.put("A_Shelf", new Point(3, 6));
        dumVals2.put("Any_Other_Object_In_Warehouse", new Point(1,1));
    }

    @Test
    /**
     * @author Matt
     * Ensure that visualizer gets the new map of floor
     */
    public void testUpdate() {
        floorPositions.changeFloorMap(dumVals);
        clk.tick();
        floorPositions.changeFloorMap(dumVals2);
        clk.tick();
        floorPositions.changeFloorMap(dumVals3);
        clk.tick();
        //Assert.assertEquals(vs.floorPositions.getAllPositions(), vs.visualizer.floorMap); //Check output to see if it's correct.
    }
}

package Visualizer;

import java.util.*;
import Floor.*;

/**
 * Created by Matt on 11/14/2016.
 * @author Matt
 *
 * Note: this will be refactored to work better with observer
 */
public class VisSubscriber implements Observer {
    private long timeDiff;
    private long time;
    Visualizer myVis; //possible code smell? (contains visualizer, visualizer contains this)

    public VisSubscriber (Visualizer v) {
        myVis = v;
    }

    @Override
    public void update (Observable o, Object arg) {
        if (timeDiff == 0) {
            time = System.currentTimeMillis();
        } else {
            time = System.currentTimeMillis() - time;
            timeDiff += time;

            if (timeDiff > 1000) {
                this.givePositions();
                timeDiff = 0;
            }
    }
    }

    public void givePositions () {
        //FILL IN WITH information from the floor, but for now, use dummy values
        Map<String, Point> dumVals = new HashMap<>();
        dumVals.put("Bender_Is_The_Greatest_Robot_Ever", new Point(3, 5));
        dumVals.put("A_Shelf", new Point(3, 6));
        dumVals.put("Any_Other_Object_In_Warehouse", new Point(1,1));
        myVis.updateFloor(dumVals);
    }
}

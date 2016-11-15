package Visualizer;

import java.util.*;
import Floor.*;

/**
 * Created by Matt on 11/14/2016.
 * @author Matt McCan
 *
 * Note: this will be refactored to work better with observer
 */
public class VisSubscriber implements Observer {
    private long timeDiff;
    private long time;
    Visualizer visualizer;
    FloorPositions floorPositions;

    /**
     * @author Matt
     */
    public VisSubscriber (FloorPositions floorPositions) {
        this.floorPositions = floorPositions;
        visualizer = new Visualizer();
    }

    /**
     * @param o
     * @param arg
     */
    @Override
    public void update (Observable o, Object arg) {
        //TODO: Get latest map from Floor
        givePositions(floorPositions.getAllPositions());
    }

    /**
     * @author Matt
     */
    public void givePositions (Map<String, Point> newMap) {
        //Currently uses dummy values
        visualizer.updateFloor(newMap);
    }
}

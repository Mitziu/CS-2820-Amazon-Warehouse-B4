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

    /**
     * @author Matt
     */
    public VisSubscriber () {
        visualizer = new Visualizer();
    }

    /**
     * @param o
     * @param arg
     */
    @Override
    public void update (Observable o, Object arg) {
        this.givePositions((Map)arg);
    }

    /**
     * @author Matt
     */
    public void givePositions (Map<String, Point> newMap) {
        //Currently uses dummy values
        visualizer.updateFloor(newMap);
    }
}

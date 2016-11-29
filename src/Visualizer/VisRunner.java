package Visualizer;

import java.util.*;
import Floor.*;

/**
 * Created by Matt on 11/14/2016.
 * @author Matt McCan
 *
 * Note: this will be refactored to work better with observer
 */
public class VisRunner implements Observer {
    private long timeDiff;
    private long time;
    Visualizer visualizer;
    FloorPositions floorPositions;
    List warehouseHistory;
    int counter = 0;

    /**
     * @author Matt
     */
    public VisRunner(FloorPositions floorPositions) {
        this.floorPositions = floorPositions;
        visualizer = new Visualizer();
        warehouseHistory = new LinkedList<HashMap<String, Point>>();
    }

    /**
     * @param o
     * @param arg
     */
    @Override
    public void update (Observable o, Object arg) {
        //TODO: Get latest map from Floor
        Map tempMap = new HashMap<>(floorPositions.getAllPositions());
        givePositions(tempMap);
        storePositions(tempMap);
    }

    /**
     * @author Matt
     */
    public void givePositions (Map<String, Point> newMap) {
        //Currently uses dummy values
        visualizer.updateFloor(newMap);
    }

    public void storePositions (Map<String, Point> newMap) {
        warehouseHistory.add(newMap);
    }

    public HashMap<String, Point> getNextMap() {
        counter++;
        if (warehouseHistory.size() - 1 <= counter) {
            return (HashMap<String, Point>) warehouseHistory.get(counter - 1);
        }
        else {
            counter--;
            System.out.println("You have reached the end of the simulation!");
            return (HashMap<String, Point>) warehouseHistory.get(counter - 1);
        }

    }
}

package Visualizer;

import java.util.*;
import Floor.*;

/**
 * Created by Matt on 11/14/2016.
 * @author Matt McCan
 *
 */
public class VisRunner implements Observer {
    Visualizer visualizer;
    FloorPositions floorPositions;
    List warehouseHistory;

    /**
     * @author Matt
     */
    public VisRunner(FloorPositions floorPositions) {
        this.floorPositions = floorPositions;
        visualizer = new Visualizer();
        warehouseHistory = new LinkedList<HashMap<String, Point>>();
    }

    /**
     * @author Matt
     * @param o
     * @param arg
     */
    @Override
    public void update (Observable o, Object arg) {
        //TODO: Get latest map from Floor
        Map tempMap = new HashMap<>(floorPositions.getAllPositions());
        storePositions(tempMap);
    }

    /**
     * @author Matt
     * @param newMap
     * unused at the moment
     */ /*
    public void givePositions (Map<String, Point> newMap) {
        //Currently uses dummy values
        visualizer.updateFloor(newMap);
    }*/

    /**
     * @author Matt
     * @param newMap
     * Stores positions in warehouseHistory
     */
    public void storePositions (Map<String, Point> newMap) {
        if (!newMap.isEmpty()) warehouseHistory.add(newMap);
        else visualizer.run((LinkedList<HashMap<String, Point>>)warehouseHistory);
    }


}

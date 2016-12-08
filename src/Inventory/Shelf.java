package Inventory;

import Floor.ObjectInWarehouse;
import Floor.Point;

import java.util.Map;

/**
 * Created by Mitziu on 12/4/16.
 */
public class Shelf implements ObjectInWarehouse {

    public Map<Integer, Integer> items;
    public Integer ID;
    public Point currentPosition;
    public Point originalLocation;

    public boolean loadedOnRobot;
    public boolean beingUsed;

    public Shelf(Integer ID, Map<Integer, Integer> items) {
        this.items = items;
        this.ID = ID;
        loadedOnRobot = false;
        beingUsed = false;
    }

    public void setOriginalLocation(Point originalLocation) {
        this.originalLocation = originalLocation;
    }

    public Point getOriginalLocation() {
        return originalLocation;
    }

    public void setPoint(Point newLocation) {
        currentPosition = newLocation;
    } // This method overlaps with the method move(x, y) for returning the same variable.

    @Override
    public Point getLocation() {
        return currentPosition;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void move(Integer x, Integer y) {
        currentPosition = new Point(x, y);
    }


}

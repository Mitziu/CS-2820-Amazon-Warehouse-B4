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

    public Shelf(Integer ID, Map<Integer, Integer> items) {
        this.items = items;
        this.ID = ID;
    }

    public void setPoint(Point newLocation) {
        currentPosition = newLocation;
    }

    @Override
    public Point getLocation() {
        return currentPosition;
    }

    @Override
    public Integer getID() {
        return ID;
    }
}

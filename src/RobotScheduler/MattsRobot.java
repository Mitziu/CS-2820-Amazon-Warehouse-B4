package RobotScheduler;
import java.util.*;

import Floor.FloorImpl;
import Inventory.*;
import Floor.ObjectInWarehouse;
import Floor.Point;

/**
 * @author Matt
 * @author Mitziu
 * @author zuoyuan
 * Created by Matt on 12/4/2016.
 */
public class MattsRobot implements ObjectInWarehouse {
    private boolean idle = true;
    private Queue<Point> path;
    private Point location;
    private boolean loaded = false;
    private Shelf loadedShelf;
    private Integer ID;
    private Integer shelfID;
    private String currentTask;
    private FloorImpl floor;
    private Shelf_Manager shelfManager;

    /**
     * @author Matt
     * constructor
     */
    public MattsRobot (Point location, Integer ID, FloorImpl floor, Shelf_Manager shelfManager) {
        this.location = location;
        this.ID = ID;
        this.floor = floor;
        this.shelfManager = shelfManager;
    }

    /**
     * @author Matt
     * @param newStatus
     * setter for idle
     */
    public void setIdle(boolean newStatus) {
        idle = newStatus;
    }

    /**
     * @author Matt
     * @return
     * getter for idle
     */
    public boolean isIdle() {
        return idle;
    }

    /**
     * @author Matt
     * @param newShelf
     * setter for currentOrder
     */
    public void setShelfID(Integer newShelf) {
        shelfID = newShelf;
        idle = false;
    }

    /**
     * @author Matt
     * @param task
     * sets the task for this robot
     */
    public void setCurrentTask (String task) {
        currentTask = task;
    }

    /**
     * @author Matt
     * @return the current task this robot is working on
     */
    public String getCurrentTask () {
        return currentTask;
    }

    /**
     * @author Matt
     * @return ID of shelf on this robot
     */
    public Integer getShelfID () {
        return shelfID;
    }

    /**
     * @author matt
     * @param newPath
     * Setter for path
     */
    private void setPath(Queue<Point> newPath) {
        path = newPath;
    }

    /**
     * @author Matt
     * @author Mitziu
     * @return next place robot is going
     */
    public Point nextLocation () {
        if (!path.isEmpty()) return path.peek();

        return null;
    }

    /**
     * @author Matt
     * @return if robot is done
     */
    public boolean hasArrived() {
        return path.isEmpty();
    }

    /**
     * @author Matt
     * @return the robots location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @author Matt
     * @author Mitziu
     * moves the robot to the next place
     */
    public void move () {
        if (path.isEmpty()) {
            if (currentTask == "Get Shelf") {
                if (getLocation().GetX() == shelfManager.getShelf(getShelfID()).getLocation().GetX() && getLocation().GetY() == shelfManager.getShelf(getShelfID()).getLocation().GetY()){
                    path = floor.shelfToPicker(getLocation());
                }
                else {
                    path = floor.robotToShelf(getLocation(), shelfManager.getShelf(getShelfID()).getLocation());
                }
            }
            else if (currentTask == "Return Shelf" && !idle) {
                path = floor.returnShelf(getLocation(), loadedShelf.getOriginalLocation());
            }
        }

        if (!path.isEmpty()) {
            location = path.poll();
        }

        if (loaded) {
            loadedShelf.move(location.GetX(), location.GetY());
        }
    }

    /**
     * @author Matt
     * @param x
     * @param y
     * Fulfills interface requirements, probably won't be used.
     */
    public void move(Integer x, Integer y) {
        location = new Point(x, y);
    }

    /**
     * @author Matt
     * @param shelf
     * Load shelf on robot
     */
    public void loadShelf(Shelf shelf) {
        loadedShelf = shelf;
        loaded = true;
    }

    public Shelf getLoadedShelf () {
        return loadedShelf;
    }

    /**
     * @author Matt
     * @return if robot is loaded
     * Getter for loaded
     */
    public boolean isLoaded () {
        return loaded;
    }

    /**
     * @author Matt
     * Setter for loaded
     */
    public void unloadShelf () {
        loaded = false;
        setIdle(true);
    }

    /**
     * @author Matt
     * @return object ID of this robot
     */
    public Integer getID() {
        return ID;
    }
}

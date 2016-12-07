package RobotScheduler;

import Belt.PickerImpl;
import Floor.ObjectInWarehouse;
import Floor.Point;
import Inventory.Shelf;
import Inventory.Shelf_Manager;

import java.util.LinkedList;
import java.util.Queue;

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
    private String currentTask = " ";
    private Shelf_Manager shelfManager;
    private RouteFinder routeFinder;

    public Point originalLocation;

    /**
     * @author Matt
     * Updated by Wayne on 12/7/2016
     * constructor
     */
    public MattsRobot (Point location, Integer ID, Shelf_Manager shelfManager, RouteFinder routeFinder, PickerImpl picker) {
        this.location = location;
        this.ID = ID;
        this.shelfManager = shelfManager;
        this.routeFinder = routeFinder;
        path = new LinkedList<>();
        //path.add(location);
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
    public void setPath(Queue<Point> newPath) {
        path = newPath;
    }

    /**
     * @author Matt
     * @author Mitziu
     * @return next place robot is going
     */
    public Point nextLocation () {
        if (path != null) {
            if (!path.isEmpty()) return path.peek();
        }
        return new Point(1000, 1000);
    }

    /**
     * @author Matt
     * @return if robot is done
     */
    public boolean hasArrived() {
        if (path != null) {
            return path.isEmpty();
        }

        return true;


    }

    /**
     * @author Matt
     * @return boolean if path is empty
     */
    public boolean pathEmpty () {
        if (path != null) {
            if (!path.isEmpty()) return false;
        }

        return true;
    }

    /**
     * @author Matt
     * @return the robots location
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @return the original location of a robot
     * @author Wayne Lei aka Virocider
     */
    public Point getOriginalLocation() {
        return originalLocation;
    }

    /**
     * @author Matt
     * @author Mitziu
     * moves the robot to the next place
     */
    public void move () {

//        if (path != null) {
//            if (currentTask == "Goto Shelf") {
//                if (location.isEqual(loadedShelf.getLocation())) {
//                    loadShelf(shelfManager.getShelf(shelfID));
//                    currentTask = "To Picker";
//                }
//            }
//
//            if (currentTask == "To Picker") {
//
//            }
//
//        }
//
//        if (path != null) { //nested if statements are messy, but prevent nullPointerException
//               if (currentTask == "Goto Shelf") {
//                   if (shelfManager.getShelf(shelfID).getLocation().isEqual(location)) {
//                       loadShelf(shelfManager.getShelf(shelfID));
//                   }
//               }
//               else if (currentTask == "Return Shelf") {
//                   if (location.isEqual(loadedShelf.getOriginalLocation())) {
//                       unloadShelf();
//                   }
//               }
//        }

        if (path == null) return;


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
    // This method shares the same name with another move method, which has no argument. It may be better to differentiate them with different names.
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
        loadedShelf.loadedOnRobot = true;
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
        loadedShelf.loadedOnRobot = false;
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

package RobotScheduler;
import java.util.*;
import Inventory.*;
import Floor.ObjectInWarehouse;
import Floor.Point;
import Ordering.*;

/**
 * @author Matt
 * @author Mitziu
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

    /**
     * @author Matt
     * constructor
     */
    public MattsRobot (Point location, Integer ID) {
        this.location = location;
        this.ID = ID;
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

    public void setCurrentTask (String task) {
        currentTask = task;
    }

    public String getCurrentTask () {
        return currentTask;
    }

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
        if (!path.isEmpty()) return path.peek();

        return null;
    }

    /**
     * @author Matt
     * @return if robot is done
     */
    public boolean isDone () {
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
        location = path.poll();
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
    }

    public Integer getID() {
        return ID;
    }
}

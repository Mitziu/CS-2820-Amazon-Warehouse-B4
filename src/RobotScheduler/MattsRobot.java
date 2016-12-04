package RobotScheduler;
import java.util.*;
import Floor.Point;
import Floor.Shelf;
import Ordering.*;

/**
 * @author Matt
 * @author Mitziu
 * Created by Matt on 12/4/2016.
 */
public class MattsRobot {
    private boolean idle = true;
    private Order currentOrder;
    private Queue<Point> path;
    private Point location;
    private boolean loaded = false;
    private Shelf loadedShelf;

    /**
     * @author Matt
     * constructor
     */
    public MattsRobot (Point location) {
        this.location = location;
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
     * @param newOrder
     * setter for currentOrder
     */
    public void setCurrentTask(Order newOrder) {
        currentOrder = newOrder;
        idle = false;
    }

    /**
     * @author Matt
     * @return
     * getter for currentOrder
     */
    public Order getCurrentOrder () {
        return currentOrder;
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

}
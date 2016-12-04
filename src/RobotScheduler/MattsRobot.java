package RobotScheduler;
import java.util.*;
import Floor.Point;
import Ordering.*;

/**
 * Created by Matt on 12/4/2016.
 */
public class MattsRobot {
    public boolean idle = true;
    Order currentOrder;
    Queue<Point> path;
    Point location;
    public boolean loaded = false;

    /**
     * @author Matt
     * constructor
     */
    public MattsRobot (Point location) {
        this.location = location;
    }

    //SETTERS AND GETTERS FOR VARIABLES BELOW:

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
    public void setCurrentOrder (Order newOrder) {
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
     * moves the robot to the next place
     */
    public void move () {
        location = path.poll();
    }

}

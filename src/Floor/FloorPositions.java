package Floor;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Matt on 11/10/2016.
 * @author Matt, Zuoyuan Zhao
 * Please implement this so that the visualizer can see your stuff.
 */
public interface FloorPositions {

    /**
     *
     * @return A map | Keys are objects on the floor--perhaps object Id | Values are a data structure |
     * Containing positions, type of object | Later, we can add more info, but for now that should be enough
     *
     *  Feel free to change the Key/Value to suit your needs
     */
    //0. create the object
    // 1. run Initialize();
     //2. run others;
    void Initialize();
    HashMap<String, Point> getAllPositions(); // for visualizer
    ArrayList<Shelf> getShelves(); //for invention and robots
    ArrayList<route> getRoutes(); //for robot just in case
    ArrayList<Point> RouteFinding(Point target, Point RobotLoc); // for robot
    ArrayList<Robots> getRobots(); // for robot
    m_belt getBelt();// for belt
    Station getPicker();// for belt
    Station GetPackager(); // for belt
    ChargingStation getChargingStation(); // for robot
    void clearPath();// for robot to re-run the routefinding method
    ArrayList<Point> getAllPoints();
    

/* User Manual:
    0. how visualizer gonna get location: 
            use getAllPosition method and this should return a map to you, which contains all the (name, location) pairs of all of our objects in floor part
    1. how to get location of anything in the floor :
            in each class, we have a method called getLoc(), which will return a point. if you need any location for any object, just call this method
    2. how robot gonna move and how to record the data: 
            for robot, we have method called move(int x, int y) for all classes, however, only robots and shelves are allowed to move. so move method is for you
    3. how robot gonna grab shelf:
            a. in Robots Class, we have a method called HasShelf() that could check if the robot is occupied, and if not we can 
            b.Use a method called Carryup(Shelf input)in Robots class, which will carry one shelf on robot
    .....
   TO BE CONTINUED
    */
}

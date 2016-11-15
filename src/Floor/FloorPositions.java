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
    HashMap<String, Point> getAllPositions();
    ArrayList<Shelf> getShelves();
    ArrayList<route> getRoutes();
    ArrayList<Point> RouteFinding(Point target, Point RobotLoc);
    ArrayList<Robots> getRobots(); 
    m_belt getBelt();
    Station getPicker();
    Station GetPackager();
    ChargingStation getChargingStation();
    // 
}

package Floor;

import java.util.*;

/**
 * Created by Mitziu on 11/15/16.
 *
 * @author Mitziu
 * @author Matthew McCan
 */
public class MockFloor implements FloorPositions {

    HashMap<String, Point> floorMap;

    public MockFloor() {
        Initialize();
    }

    @Override
    public void Initialize() {
        floorMap = new HashMap<>();
    }

    public void changeFloorMap (HashMap<String, Point> newMap) {
        floorMap = newMap;
    }

    @Override
    public HashMap<String, Point> getAllPositions() {
        return floorMap;
    }

    @Override
    public ArrayList<Shelf> getShelves() {
        return null;
    }

    @Override
    public ArrayList<route> getRoutes() {
        return null;
    }

    @Override
    public ArrayList<Point> RouteFinding(Point target, Point RobotLoc) {
        return null;
    }

    @Override
    public ArrayList<Robots> getRobots() {
        return null;
    }

    @Override
    public m_belt getBelt() {
        return null;
    }

    @Override
    public Station getPicker() {
        return null;
    }

    @Override
    public Station GetPackager() {
        return null;
    }

    @Override
    public ChargingStation getChargingStation() {
        return null;
    }

    @Override
    public void clearPath() {
    }

    @Override
    public ArrayList<Point> getAllPoints() {
        return null;
    }
}

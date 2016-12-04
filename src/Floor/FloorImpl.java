package Floor;

import Inventory.S_Manager;
import RobotScheduler.RobotScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mitziu on 12/4/16.
 */
public class FloorImpl implements FloorPositions {

    List<Robots> robots;
    List<Shelf> shelves;

    public FloorImpl(S_Manager shelfManager, RobotScheduler robotScheduler) {


    }



    @Override
    public void Initialize() {
    }

    @Override
    public HashMap<String, Point> getAllPositions() {
        return null;
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

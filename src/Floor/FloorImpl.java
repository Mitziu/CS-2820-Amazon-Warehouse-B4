package Floor;

import Belt.BeltImpl;
import Belt.BeltPiece;
import Belt.PickerImpl;
import Inventory.S_Manager;
import RobotScheduler.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mitziu on 12/4/16.
 * Class to represent floor simplified.
 * @author Mitziu
 * @author Matthew
 * @author zuoyuan
 */
public class FloorImpl implements FloorPositions {

    private final Integer HWYSOUTH = 5;
    private final Integer HWYNORTH = 10;
    private final Integer HWYEAST = 5;
    private final Integer HWYWEST = 95;

    List<MattsRobot> robots;
    List<Inventory.Shelf> shelves;
    List<BeltPiece> piecesOfBelt;
    ObjectInWarehouse picker;
    BeltImpl belt;

    public FloorImpl(S_Manager shelfManager, RobotScheduler robotScheduler, PickerImpl picker, BeltImpl belt) {
        robots = robotScheduler.listRobots();
        shelves = shelfManager.listShelves();
        piecesOfBelt = new ArrayList<>();

        this.picker = picker;
        this.belt = belt;

        setupLocations();
    }

    /**
     * Set up method to place everything in their corresponding spots
     * @author Mitziu
     * @author Zuoyuan
     */
    private void setupLocations () {
        //Creates representation of belt for visualizer
        for (int i = 0; i < belt.getSize(); i++) {
            BeltPiece piece = new BeltPiece(i);
            piece.setEmpty(true);
            piece.move(0, (100 - (belt.getSize() - 1 - i)));
            piecesOfBelt.add(i, piece);
        }

        //Positions picker next to the belt at the beginning
        picker.move(1, (100 - (belt.getSize() - 1)));

        Integer shelfCtr = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (shelfCtr == shelves.size())
                    break;
                shelves.get(shelfCtr).move(20 + (i  * 10), 20 + (j  * 10));
                shelves.get(shelfCtr).originalLocation = new Point((20 + (i * 10)), (20 + (j * 10)));
                shelfCtr++;
            }
        }

        for (int i = 0; i < robots.size(); i++) {
            //robots.get(i).move(160, 100 - i);
            robots.get(i).move(30,30);
            robots.get(i).originalLocation = new Point(30,30);
        }
    }

    /**
     * Method to update the belt piece (Use for visualizer)
     * @author Mitziu
     * @author Matthew
     */
    private void updateBelt() {
        for (int i = 0; i < piecesOfBelt.size(); i++) {
            piecesOfBelt.get(i).setEmpty(true);
        }

        for (int i = 0; i < piecesOfBelt.size(); i++) {
            if (belt.getOrdersOnBelt().get(i) != null)
                piecesOfBelt.get(i).setEmpty(false);
        }
    }

    /**
     * Creates a map representing the state of the floor. Used by Visualizer
     * @author Mitziu
     * @return Map representing the entire state of the floor
     */
    @Override
    public HashMap<String, Point> getAllPositions() {
        updateBelt();
        HashMap<String, Point> positionsMap = new HashMap<>();

        piecesOfBelt.stream()
                .filter(piece -> piece.getEmpty())
                .forEach(piece -> positionsMap.put("BeltEmpty-" + piece.getID(), piece.getLocation()));

        piecesOfBelt.stream()
                .filter(piece -> !piece.getEmpty())
                .forEach(piece -> positionsMap.put("BeltNotEmpty-" + piece.getID(), piece.getLocation()));

        robots.stream()
                .forEach(robot -> positionsMap.put("Robot-"+ robot.getID(), robot.getLocation()));

        shelves.stream()
                .forEach(shelf -> positionsMap.put("Shelf-" + shelf.getID(), shelf.getLocation()));

        positionsMap.put("Picker", picker.getLocation());

        return positionsMap;
    }


    @Override
    public void Initialize() {
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

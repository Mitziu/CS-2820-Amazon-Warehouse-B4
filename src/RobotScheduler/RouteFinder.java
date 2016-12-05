package RobotScheduler;

import Belt.PickerImpl;
import Floor.Point;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Mitziu on 12/5/16.
 */
public class RouteFinder {

    private final Integer HWYSOUTH = 5;
    private final Integer HWYNORTH = 10;
    private final Integer HWYEAST = 5;
    private final Integer HWYWEST = 95;

    private PickerImpl picker;

    public RouteFinder(PickerImpl picker){
        this.picker = picker;
    }

    public Queue<Point> robotToShelf(Point src, Point dest) {
        Queue<Point> route = new LinkedList<>();
        Point lastPoint;

        lastPoint = goWest(src, HWYNORTH, route);
        lastPoint = goNorth(lastPoint, dest.GetY(), route);
        lastPoint = goEast(lastPoint, dest.GetX(), route);

        return route;
    }

    public Queue<Point> shelfToPicker(Point src) {
        Queue<Point> route = new LinkedList<>();
        Point lastPoint;

        lastPoint = goSouth(src, src.GetY() + 1, route);
        lastPoint = goWest(lastPoint, HWYNORTH, route);
        lastPoint = goNorth(lastPoint, HWYWEST, route);
        lastPoint = goWest(lastPoint, HWYSOUTH, route);
        lastPoint = goSouth(lastPoint, picker.getLocation().GetY() - 1, route);
        lastPoint = goWest(lastPoint, picker.getLocation().GetX(), route);
        lastPoint = goSouth(lastPoint, picker.getLocation().GetY(), route);
        return route;
    }

    public Queue<Point> returnShelf(Point src, Point dest) {
        Queue<Point> route = new LinkedList<>();
        Point lastPoint;

        lastPoint = goEast(src, HWYSOUTH, route);
        lastPoint = goSouth(lastPoint, HWYEAST, route);
        lastPoint = goEast(lastPoint, HWYNORTH, route);
        lastPoint = goNorth(lastPoint, dest.GetY() -1, route);
        lastPoint = goEast(lastPoint, dest.GetX(), route);
        lastPoint = goSouth(lastPoint, dest.GetY(), route);

        return null;
    }

    private Point goWest(Point currentLocation, Integer limit, Queue<Point> route) {
        Integer currentX = currentLocation.GetX();
        Point lastPoint = new Point(currentLocation.GetX(), currentLocation.GetY());

        while (currentX >= limit) {
            route.add(new Point(currentX, currentLocation.GetY()));
            lastPoint = new Point(currentX, currentLocation.GetY());
            currentX--;
        }

        return lastPoint;
    }

    private Point goEast(Point currentLocation, Integer limit, Queue<Point> route) {
        Integer currentX = currentLocation.GetX();
        Point lastPoint = new Point(currentLocation.GetX(), currentLocation.GetY());

        while (currentX <= limit) {
            route.add(new Point(currentX, currentLocation.GetY()));
            lastPoint = new Point(currentX, currentLocation.GetY());
            currentX ++;
        }

        return lastPoint;
    }

    private Point goNorth(Point currentLocation, Integer limit, Queue<Point> route) {
        Integer currentY = currentLocation.GetY();
        Point lastPoint = new Point(currentLocation.GetX(), currentLocation.GetY());

        while (currentY >= limit) {
            route.add(new Point(currentLocation.GetX(), currentY));
            lastPoint = new Point(currentLocation.GetX(), currentY);
            currentY--;
        }

        return lastPoint;
    }

    private Point goSouth(Point currentLocation, Integer limit, Queue<Point> route) {
        Integer currentY = currentLocation.GetY();
        Point lastPoint = new Point(currentLocation.GetX(), currentLocation.GetY());

        while (currentY <= limit) {
            route.add(new Point(currentLocation.GetX(), currentY));
            lastPoint = new Point(currentLocation.GetX(), currentY);
            currentY++;
        }

        return lastPoint;
    }
}

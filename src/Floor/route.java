package Floor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class route implements Thing {

    public String name;
    public int id;
    public int x_loc;
    public int y_loc;
    public int width;
    public int length;
    public boolean moveable;

    public route(int x, int y, int w, int l, int id) { //x,y here is the left start point of the Floor.route
        x_loc = x;
        y_loc = y;
        width = w;
        length = l;
        moveable = false;
        this.id = id;
        name = "Hawk Highway" + id;
    }

    public ArrayList<Point> range() {
        ArrayList<Point> result = new ArrayList<Point>();
        for (int x = x_loc; x < x_loc + width; x++) {
            for (int y = y_loc; y < y_loc + length; y++) {
                Point temp = new Point(x, y);
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public int getx() { //x coordinate
        return x_loc;
    }

    @Override
    public int gety() { //y coordinate
        return y_loc;
    }

    @Override
    public int getW() {	//width
        return width;
    }

    @Override
    public int getL() {	//length
        return length;
    }

    @Override
    public void AllowToMove() {
        moveable = true;
    }

    @Override
    public void move(int x, int y) {
        if (moveable) {
            x_loc = x;
            y_loc = y;
        }

    }

    public Point getLoc() {
        return new Point(x_loc, y_loc);
    }

}

package Belt;

import Floor.ObjectInWarehouse;
import Floor.Point;

/**
 * Created by Mitziu on 12/4/16.
 * Class to help floor and Visualizer represent the belt
 * @author Mitziu
 * @author Matthew
 */
public class BeltPiece implements ObjectInWarehouse {

    private Point location;
    private boolean empty;
    private Integer ID;

    public BeltPiece(Integer ID) {
        this.ID = ID;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void move(Integer x, Integer y) {
        location = new Point(x,y);
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean getEmpty () {
        return this.empty;
    }
}

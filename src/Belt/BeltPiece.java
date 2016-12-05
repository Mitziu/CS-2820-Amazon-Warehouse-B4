package Belt;

import Floor.ObjectInWarehouse;
import Floor.Point;

/**
 * Created by Mitziu on 12/4/16.
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
        return null;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void move(Integer x, Integer y) {
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean getEmpty () {
        return this.empty;
    }
}

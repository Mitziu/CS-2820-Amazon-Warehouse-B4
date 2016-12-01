package Floor;

public class ChargingStation implements Thing {

    public int width = 2;
    public int length = 1;
    public int x_loc;
    public int y_loc;
    public boolean moveable; //some of them may be able to move, others do not

    public Point getLoc() {
        return new Point(x_loc, y_loc);
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

    public ChargingStation(int x, int y) {
        x_loc = x;
        y_loc = y;
        moveable = false;
    }

    public boolean IsCharging(int x, int y) {
        boolean result = false;
        if ((x < x_loc + width && x > x_loc) && (y > y_loc && y < y_loc + length)) {
            result = true;
        }
        return result;
    }
}

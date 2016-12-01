package Floor;

public class Station implements Thing {

    public int w = 1;
    public int l = 1;
    public int x_loc;
    public int y_loc;
    public int width;
    public int length;
    public boolean moveable;

    public Station(int x, int y) {
        x_loc = x;
        y_loc = y;
        width = w;
        length = l;
    }

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

    public boolean HitMeBro(int x, int y) {
        boolean result = false;
        if ((x < x_loc + width && x > x_loc) && (y > y_loc && y < y_loc + length)) {
            result = true;
            //belt.runit;
        }
        return result;
    }
}

package Floor;

public class Shelf implements Thing {

    public String name;
    //public  HashMap<Integer, String> items;
    public int id;
    public int x_loc;
    public int y_loc;
    public int width;
    public int length;
    public boolean moveable = true;

    public Shelf(int x, int y, int id) {
        x_loc = x;
        y_loc = y;
        width = 1;
        length = 1;
        name = "Great Shelf NO:" + id;
        this.id = id;
        
        //items.put();
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
}

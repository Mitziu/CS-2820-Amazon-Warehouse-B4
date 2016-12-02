package Floor;

import java.util.ArrayList;

public class Robots implements Thing { //contains robots and shelves

    public int w = 1;
    public int h = 1;
    public int id;
    public Shelf m_shelf;
    public boolean Carried = false;
    public int x_loc;
    public int y_loc;
    public int width;
    public int length;
    public boolean moveable = true;
    public boolean needCharge = false;

    public double battery = 1.00;
    public ArrayList<Point> commands;
    public Point OrginOfShelf;
    public Setup m_setup;
    public boolean picked = false;

    public Robots(int x, int y, int s, Setup input) {
        x_loc = x;
        y_loc = y;
        width = w;
        length = h;
        id = s;

        m_setup = input;

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
        if (!needCharge) {
            if (this.battery > 0.1) {
                if (moveable) {
                    x_loc = x;
                    y_loc = y;
                    if (this.Carried) {
                        m_shelf.move(x, y);
                    }
                }
                commands.remove(0);
                this.battery = this.battery - 0.0001;

            } else {
                needCharge = true;
                Charging();
            }
        } else {
            LowPowerMode(x, y);
        }
    }

    public void LowPowerMode(int x, int y) {
        if (moveable) {
            x_loc = x;
            y_loc = y;
            if (this.Carried) {
                m_shelf.move(x, y);
            }
            commands.remove(0);
        }
    }

    public Point getLoc() {
        return new Point(x_loc, y_loc);
    }

    public boolean HasShelf() {
        return this.Carried;
    }

    public void IsPicked() {
        picked = true;
    }

    public void CarryUp(Shelf input) {
        this.Carried = true;
        this.picked = false;
        m_shelf = input;
    }

    public void Drop() {
        this.Carried = false;
        m_shelf = null;
    }

    public void Charging() {
        ArrayList<Point> temp = m_setup.RouteFinding(m_setup.getChargingStation().getLoc(), this.getLoc());
        for (int i = 0; i < temp.size(); i++) {
            commands.add(0, temp.get(i));
        }
        for (int i = temp.size() - 1; i > 0; i--) {
            commands.add(0, temp.get(i));
        }

    }
}

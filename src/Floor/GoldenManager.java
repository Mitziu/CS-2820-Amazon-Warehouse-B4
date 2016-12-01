/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floor;

import Inventory.S_Manager;
import Ordering.Order;
import java.util.Observer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Observable;

/**
 *
 * @author zuoyuan
 */
// I'm a golden manager, here to take the offer from robots' world! :) Let's go!
//THIS IS "another robot scheduler".
public class GoldenManager implements RobotManager, Observer {

    public S_Manager m_inventory;
    Robots King;
    Robots Queen;
    public FloorPositions src;
    public ArrayList<Integer> PlaceNeedToGo;
    public ArrayList<Robots> m_robots;
    public ArrayList<Shelf> m_shelves;
    public Station picker;
    public Order order;
    public boolean done = false;

    @Override

    /* QUESTIONS: not sure about how to get amount of the item on one shelf( so just made it 1)
                                   not sure about getItemIDList(), perhaps, (itemId, qty)?
     */
    public void Background(S_Manager input, FloorPositions srcInput) {
        //COMMENT: order handling, split orders into the shelves that robots need to visit
        m_inventory = input;
        src = srcInput;
        done = false;
        Map<Integer, Integer> Items;
        Items = order.getItemIDList();
        LinkedList<Integer> places;
        PlaceNeedToGo = new ArrayList<>();
        for (Integer i : Items.keySet()) {
            places = m_inventory.Contained_In(i);
            ListIterator<Integer> m_iterator = places.listIterator();
            while (m_iterator.hasNext()) {
                if (Items.isEmpty()) {
                    break;
                }
                if (Items.get(i) <= 0) {
                    Items.remove(i);
                } else {
                    PlaceNeedToGo.add(m_iterator.next());
                    Items.put(i, Items.get(i) - m_inventory.Container_Count(i, m_iterator.next()));
                }
            }
        }
        // initiate all the objects we need for later use
        m_robots = src.getRobots();
        m_shelves = src.getShelves();
        picker = src.getPicker();
        King = m_robots.get(0);
        Queen = m_robots.get(1);
        // find the shelf we need then calclulate all the points for running 
        makeTrip();
        // not really sure about how to do it in runtime, efficency or accuracy?
        /*  for(int j =0;j<King.commands.size();j++){
                     Point next = King.commands.get(j);
                     King.move(next.GetX(), next.GetY());
                 }
             for(int j =0;j<Queen.commands.size();j++){
                     Point next = Queen.commands.get(j);
                     Queen.move(next.GetX(), next.GetY());
                 }*/

    }
// calculate the whole route for both robots

    public void makeTrip() {
        for (int i = 1; i < PlaceNeedToGo.size(); i++) {
            int index = MatchShelf(PlaceNeedToGo.get(i), m_shelves);
            Shelf target;
            if (index != -1) {
                target = m_shelves.get(index);
                TripForShelf(King, target);
            }
            index = MatchShelf(PlaceNeedToGo.get(i - 1), m_shelves);
            if (index != -1) {
                target = m_shelves.get(index);
                TripForShelf(Queen, target);
            }
        }
    }
//tell me, which shelf should i go to? 

    public Integer MatchShelf(int id, ArrayList<Shelf> inputList) {
        int result = -1;
        for (int i = 0; i < inputList.size(); i++) {
            if (id == inputList.get(i).id) {
                result = i;
            }
        }
        return result;
    }
// trip for shelf, and we shall begin now. dont forget to send it back though:)

    public void TripForShelf(Robots r, Shelf s) {
        ArrayList<Point> temp;
        temp = src.RouteFinding(s.getLoc(), r.getLoc());
        for (int i = 0; i < temp.size(); i++) {
            r.commands.add(temp.get(i));
        }
        ArrayList<Point> GoToPicker;
        GoToPicker = src.RouteFinding(picker.getLoc(), s.getLoc());
        ArrayList<Point> GoBack;
        GoBack = src.RouteFinding(s.getLoc(), picker.getLoc());
        for (int i = 0; i < GoToPicker.size(); i++) {
            r.commands.add(GoToPicker.get(i));
        }
        for (int i = 0; i < GoBack.size(); i++) {
            r.commands.add(GoBack.get(i));
        }
    }
// one step one sec.

    public void OneStep() {
        if (King.commands.isEmpty() && Queen.commands.isEmpty()) {
            done = true;
        } // not yet, it is for next order
        Point NextForKing = King.commands.get(0);
        Point NextForQueen = Queen.commands.get(0);
        if (King.getLoc() == src.getChargingStation().getLoc()) {
            King.battery = 1.00;
            King.needCharge = false;
        }
        if (Queen.getLoc() == src.getChargingStation().getLoc()) {
            Queen.battery = 1.00;
            Queen.needCharge = false;
        }
        if (!King.commands.isEmpty()) {
            if (NextForKing.GetX() == NextForQueen.GetX() && NextForKing.GetY() == NextForQueen.GetY()) {
                collisionHandle(King, Queen, NextForKing);
                King.move(NextForKing.GetX(), NextForKing.GetY());

            } else {
                King.move(NextForKing.GetX(), NextForKing.GetY());

            }
        }
        if (!Queen.commands.isEmpty()) {
            if (NextForKing.GetX() == NextForQueen.GetX() && NextForKing.GetY() == NextForQueen.GetY()) {
                collisionHandle(King, Queen, NextForKing);

                Queen.move(NextForQueen.GetX(), NextForQueen.GetY());
            } else {

                Queen.move(NextForQueen.GetX(), NextForQueen.GetY());
            }
        }
        //pass by charging station, get charged in a second

    }
// what if we are gonna hit each other? Queen, you shall yeild!

    public void collisionHandle(Robots r1, Robots r2, Point p) {
        ArrayList<Point> all = src.getAllPoints();
        // r2 make way for r1
        Point temp;
        if (r2.getLoc().GetX() == p.GetX()) {
            temp = new Point(r2.getLoc().GetX() - 1, r2.getLoc().GetY());
            if (all.contains(temp)) {
                r2.commands.add(0, temp);
                r2.commands.add(1, temp);
                r2.commands.add(2, r2.getLoc());
            } else {
                temp = new Point(r2.getLoc().GetX() + 1, r2.getLoc().GetY());
                r2.commands.add(0, temp);
                r2.commands.add(1, temp);
                r2.commands.add(2, r2.getLoc());
            }
        } else {
            temp = new Point(r2.getLoc().GetX(), r2.getLoc().GetY() - 1);
            if (all.contains(temp)) {
                r2.commands.add(0, temp);
                r2.commands.add(1, temp);
                r2.commands.add(2, r2.getLoc());
            } else {
                temp = new Point(r2.getLoc().GetX(), r2.getLoc().GetY() + 1);
                r2.commands.add(0, temp);
                r2.commands.add(1, temp);
                r2.commands.add(2, r2.getLoc());
            }
        }
    }

    @Override
    // runtime movement
    public void update(Observable o, Object arg) {
        OneStep();
        // step by step:)
    }

    @Override
    public void SetOrder(Order input) {
        order = input;
        if (IsDone()) {
            Background(this.m_inventory, this.src);
        }
    }

    @Override
    public boolean IsDone() {
        return done;
    }
}

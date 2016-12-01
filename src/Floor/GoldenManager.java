/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floor;

import Inventory.Inventory_Interface;
import Ordering.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author zuoyuan
 */

// I'm a golden manager, here to take the offer from robots' world! :) Let's go!
//THIS IS "another robot scheduler".
public class GoldenManager implements RobotManager {
    public Inventory_Interface m_inventory;
    public FloorPositions src;
    public ArrayList<Integer> PlaceNeedToGo ;
    public  ArrayList<Robots> m_robots;
    public  ArrayList<Shelf> m_shelves;
    public  Station picker;
    @Override
   
    /* QUESTIONS: not sure about how to get amount of the item on one shelf( so just made it 1)
                                   not sure about getItemIDList(), perhaps, (itemId, qty)?
                                   not sure about Contained_In(), perhaps, all shelves that contain the item?*/
    public void OrderIsComing(Order order, Inventory_Interface input , FloorPositions srcInput) {
         //COMMENT: order handling, split orders into the shelves that robots need to visit
        m_inventory = input;
        src = srcInput;
        Map<Integer,Integer> Items =  new HashMap<Integer,Integer>();
        Items = order.getItemIDList();
        LinkedList<String> places = new LinkedList<String>();
        PlaceNeedToGo = new ArrayList<Integer>();
        for(Integer i: Items.keySet()){
            places = m_inventory.Contained_In(i); 
            ListIterator<String> m_iterator = places.listIterator();
            while (m_iterator.hasNext()){
                if(Items.get(i)<=0){
                    break;
                }else{
                    PlaceNeedToGo.add(Integer.valueOf(m_iterator.next()));
                    Items.put(i,Items.get(i)-1);
                }
            }
        }
        // initiate all the objects we need for later use
        m_robots = src.getRobots(); 
        m_shelves = src.getShelves();
        picker = src.getPicker();
        Robots King = m_robots.get(0);
        Robots Queen = m_robots.get(1);
        // find the shelf we need then calclulate all the points for running 
        for(int i =1;i<PlaceNeedToGo.size();i++){
            int index  = MatchShelf(PlaceNeedToGo.get(i),m_shelves);
            Shelf target;
            if(index!=-1){
                target = m_shelves.get(index);
                TripForShelf(King,target);
            }
            index  = MatchShelf(PlaceNeedToGo.get(i-1),m_shelves);
             if(index!=-1){
                target = m_shelves.get(index);
                TripForShelf(Queen,target);
            } 
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
    }
    public Integer MatchShelf(int id, ArrayList<Shelf> inputList){
        int result = -1;
        for(int i =0;i<inputList.size();i++){
            if(id ==inputList.get(i).id){
                result = i;
            }
        }
        return result;
    }
    public void TripForShelf(Robots r, Shelf s){
        r.commands = src.RouteFinding(s.getLoc(), r.getLoc());
        ArrayList<Point> GoToPicker = new  ArrayList<Point>();
        GoToPicker = src.RouteFinding(picker.getLoc(), s.getLoc());
        ArrayList<Point> GoBack = new  ArrayList<Point>();
        GoBack = src.RouteFinding(s.getLoc(), picker.getLoc());
        for(int i =0;i<GoToPicker.size();i++){
            r.commands.add(GoToPicker.get(i));
        }
        for(int i =0;i<GoBack.size();i++){
            r.commands.add(GoBack.get(i));
        }
    }
    // TO BE CONTINUED
    public void collisionHandle(Robots r1, Robots r2, Point p){
         ArrayList<Point> all = src.getAllPoints();
        if(r1.priority>=r2.priority){
                
        }
    }
}

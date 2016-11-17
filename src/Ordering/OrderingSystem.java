package Ordering;
//Makes a list of Ordering.OrderingInterface so we have an overview of all Ordering.OrderingInterface currently being worked on,
//And also lets you remove completed orders and add new orders

import Belt.BeltImpl;
import Inventory.Inventory;
import RobotScheduler.RobotScheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//Need class to be able to pass floor, inventory, belt, and robot objects
//Use interfaces to be able to test
public class OrderingSystem implements OrderInterface {

    //Instance variable is a map, the Key being orderIDs
    public Map<Integer, Order> OngoingOrders;
    public Inventory inventory;
    public BeltImpl belt;
    public RobotScheduler robot;


    public void OrderingSystem(Inventory inventory, BeltImpl belt, RobotScheduler robot){
        this.OngoingOrders = new HashMap<>();
        this.inventory = inventory;
        this.belt = belt;
        this.robot = robot;
    }

    //Lets you add order with just Items wanted and address, creates OrderID and makes key with it
    //Check with inventory class to make sure there is enough item in stock
    public Boolean placeOrder(Map<Integer,Integer> newItemList, String newAddress){
        boolean hasenough = true;
        for (Integer key : newItemList.keySet()){
            if(this.inventory.Get_Item_Qty(key) < newItemList.get(key)){
                hasenough = false;
            }
        }
        if(hasenough){
            Integer uniqueID = Integer.valueOf(UUID.randomUUID().toString());
            Order newOrder = new Order(uniqueID, newItemList, newAddress);
            this.OngoingOrders.put(uniqueID, newOrder);

            //need a method to send information to robot and say "hey pick up these things"
            //Can send items and quantities individually or send the entire order
            //In interface, function getItem

            return true;
        }
        else{
            return false;
        }
    }


    //Need to work with robot to send it item number or shelf location to have it pick up items
    public void getItem(Order order){

    }

    //Need to work with floor to get location of item
    //(11/17) do I need to even do this anymore?
    public void getItemLocation(int itemid){

    }

    //used as a basis to see if item is in stock, may not be needed though..
    public boolean isItemInStock(Integer itemid){
        if(this.inventory.Get_Item_Qty(itemid) > 0){
            return true;
        }
        else{
            return false;
        }
    }

    //Tells belt that order is ready to move on belt
    //(11/17) should I send an integer of orderID, or just an order?
    public void startBelt(Integer orderID){
        belt.pack(orderID);

    }


    //removes item by orderID
    public void finishOrder(Integer OrderID){
        this.OngoingOrders.remove(OrderID);
    }

}

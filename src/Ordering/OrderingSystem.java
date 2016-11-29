package Ordering;
//Makes a list of Ordering.OrderingInterface so we have an overview of all Ordering.OrderingInterface currently being worked on,
//And also lets you remove completed orders and add new orders

import Belt.BeltImpl;
import Inventory.Inventory;
import RobotScheduler.RobotScheduler;
import Belt.Picker;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.*;

//Need class to be able to pass floor, inventory, belt, and robot objects
//Use interfaces to be able to test
public class OrderingSystem implements OrderInterface {

    //Instance variable is a map, the Key being orderIDs
    public Map<Integer, Order> OngoingOrders;
    public Inventory inventory;
    public BeltImpl belt;
    public RobotScheduler robot;
	public Picker picker;
	public ArrayList<Order> orderHistory;


    public OrderingSystem(Inventory inventory, BeltImpl belt, RobotScheduler robot, Picker picker){
        this.OngoingOrders = new HashMap<>();
        this.inventory = inventory;
        this.belt = belt;
        this.robot = robot;
		this.picker = picker;
		this.orderHistory = new ArrayList<>();
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
			//Sends order to Picker
			picker.newOrder(newOrder);
			//Sends order to Robot
			getItem(newOrder);

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
	//(11/17) commented out because picker will now tell belt to start, not orders
    /*public void startBelt(Integer orderID){
        belt.pack(orderID);

    }*/


    //removes item by orderID
    public void finishOrder(Integer OrderID){
		Order holderOrder = this.OngoingOrders.get(OrderID);
        this.OngoingOrders.remove(OrderID);
		this.addToHistory(holderOrder);
    }
	
	public void addToHistory(Order order){
		if( this.orderHistory.size() < 30 ){
			this.orderHistory.add(order);
		}
		else{
			this.orderHistory.remove(0);
			this.orderHistory.add(order);
		}
	}
	
	public ArrayList<Order> getHistory(){
		return this.orderHistory;
		
	}

}

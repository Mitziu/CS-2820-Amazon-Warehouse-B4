package Ordering;


import Ordering.OrderInterface;
import Belt.Belt;
import Belt.BeltImpl;

//Need import statement for inventory when it is fixed
import java.util.*;

//Max Riley Class Ordering.Order

class Order {
	
	//instance variables for order ID, Address, Locations, and a hashmap that will use Item ID's as keys
	//and a list of integers to count the amount they want of a certain object
	public Integer OrderID;
	public Map<Integer, Integer> ItemIDList;
	public String Address;
	public String OrderLocation;
	
	
	public Order(Integer OrderID, Map<Integer, Integer> ItemIDList, String Address){
		this.OrderID = OrderID;
		this.ItemIDList = ItemIDList;
		this.Address = Address;
		this.OrderLocation = "Start";
	}
	
	
	public Map<Integer,Integer> getItemIDList(){
		return this.ItemIDList;
	}
	//Gets order ID from Ordering.Order
	public Integer getOrderID(){
		return this.OrderID;
	}
	
	//Gets Address from Ordering.Order
	public String getAddress(){
		return this.Address;
	}
	
	//Gets Locations of Ordering.Order
	public String getLocation(){
		return this.OrderLocation;
	}
	
	//Set Location of a certain order
	public void setLocation(String Location){
		this.OrderLocation = Location;
	}
	
	
}


//Makes a list of Ordering.OrderingSystemInterface so we have an overview of all Ordering.OrderingSystemInterface currently being worked on,
//And also lets you remove completed orders and add new orders

//Need class to be able to pass floor, inventory, belt, and robot objects
//Use interfaces to be able to test
class OrderingSystem implements OrderInterface {
	
	//Instance variable is a map, the Key being orderIDs
	public Map<Integer, Order> OngoingOrders;
	public Inventory inventory;
	public BeltImpl belt;
	
	
	public void OrderingSystem(Inventory inventory, BeltImpl belt){
		this.OngoingOrders = new HashMap<>();
		this.inventory = inventory;
		this.belt = belt;
	}
	
	//Lets you add order with just Items wanted and address, creates OrderID and makes key with it
	//Check with inventory class to make sure there is enough item in stock
	public Boolean placeOrder(Map<Integer,Integer> newItemList, String newAddress){
		boolean hasenough;
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
	public void getItem(int itemid, int shelfLocation){
		
	}
	//Need to work with floor to get location of item
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
	public void startBelt(Integer orderID){
		//belt.pack(orderID);
		
	}
	
	
	//removes item by orderID
	public void finishOrder(Integer OrderID){
		this.OngoingOrders.remove(OrderID);
	}

}

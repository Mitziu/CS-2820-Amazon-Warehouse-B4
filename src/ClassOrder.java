//Max Riley Class Order

//Needs import statements!!
//Oops
//Btw this is untested

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

class Order implements Orders{
	
	//instance variables for order ID, Address, Locations, and a hashmap that will use Item ID's as keys
	//and a list of integers to count the amount they want of a certain object
	public Integer OrderID;
	public HashMap<String, Integer> ItemIDList;
	public String Address;
	public String OrderLocation;
	
	
	public void Order(Integer OrderID, HashMap<String, Integer> ItemIDList, String Address, String OrderLocation){
		this.OrderID = OrderID;
		this.ItemIDList = ItemIDList;
		this.Address = Address;
		this.OrderLocation = OrderLocation;
	}
	
	//Not sure what this interface is for...
	public Boolean placeOrder(List<Integer> items){
		return true;
	}
	
	//Gets order ID from Order
	public Integer getOrderID(){
		return this.OrderID;
	}
	
	//Gets Address from Order
	public String getAddress(){
		return this.Address;
	}
	
	//Gets Locations of Order
	public String getLocation(){
		return this.OrderLocation;
	}
	
	//Set Location of a certain order
	public void setLocation(String Location){
		this.OrderLocation = Location;
	}
	
	
}


//Makes a list of Orders so we have an overview of all Orders currently being worked on,
//And also lets you remove completed orders and add new orders
class MainOrder{
	
	//Instance variable is a queue. Now thinking about it, may just want a regular list or something
	public Queue<Order> OrderList;
	
	public void MainOrder(Queue<Order> OrderList){
		this.OrderList = OrderList;
	}
	
	//Lets you add order
	public void placeOrder(Order newOrder){
		this.OrderList.add(newOrder);
	}
	
	//Pops first item in queue, assuming all orders are first in first out, but should change cus that is unrealistic
	
	public void finishOrder(){
		
		//could use .remove here
		this.OrderList.poll();
	}
}

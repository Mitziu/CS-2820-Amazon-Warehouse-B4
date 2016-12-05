package Ordering;

//Need import statement for inventory when it is fixed
import java.util.*;

//Max Riley Class Ordering.Order

public class Order {
	
	//instance variables for order ID, Address, Locations, and a hashmap that will use Item ID's as keys
	//and a list of integers to count the amount they want of a certain object
	public Integer OrderID;
	public Map<Integer, Integer> itemIDMap;
	public String Address;
	public String OrderLocation;
	
	
	public Order(Integer OrderID, Map<Integer, Integer> itemIDMap, String Address){
		this.OrderID = OrderID;
		this.itemIDMap = itemIDMap;
		this.Address = Address;
		this.OrderLocation = "Start";
	}
	
	
	public Map<Integer,Integer> getItemIDMap(){
		return this.itemIDMap;
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
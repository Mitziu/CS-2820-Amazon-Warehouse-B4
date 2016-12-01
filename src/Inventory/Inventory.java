package Inventory;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
/**
 * @author William Anderson
 * @author Matt McCan
 */

/**
 * Main class for Inventory
 * @author William Anderson
 *
 */
public class Inventory{

	/**
	 * Nested class to hold the details of items.  Will be stored in the HashMap Main_Inventory.
	 * Item Quantity is added when the item is placed in a container/shelf for the first time and is update
	 * whenever items are added to or removed from containers/shelves.  
	 * @author William Anderson
	 *
	 */
	class Item_Details{
		public HashMap <Integer, Integer> Containers;
		int Item_ID;
		String Item_Name;
		int Item_Quantity;

		Item_Details(int Item_ID, String Item_Name){
			this.Item_ID = Item_ID;
			this.Item_Name = Item_Name;
			this.Item_Quantity = 0;
			this.Containers = new HashMap<Integer, Integer>();
		}

		/** 
		 * Updates the quantity of an item by adding up all of the quantities stored in the shelves
		 * @author William Anderson
		 */
		public void Qty_Update(int New_Qty){
			this.Item_Quantity = New_Qty;
		}
	}
	
	public HashMap <Integer, Item_Details> Main_Inventory;
	public HashMap <String, Integer> Converter; 
	
	public Inventory(){
		Main_Inventory = new HashMap<Integer, Item_Details>();
		Converter = new HashMap<String, Integer>();
	}
	
	/**
	 * Returns how many of a particular item are stocked in the warehouse
	 * @param int Item ID number
	 * @return int Amount of Item currently in stock
	 */
	public int Get_Item_Qty(int ID){
		return Main_Inventory.get(ID).Item_Quantity;
	}

	/**
	 * Returns the name of the item when given the item ID number
	 * @param int Item ID number
	 * @return String Item's name
	 * @author William Anderson
	 */
	public String Get_Item_Name(int ID){
		return Main_Inventory.get(ID).Item_Name;
	}

	/**
	 * Returns the Item's ID number given the Item name
	 * @param String Name of Item
	 * @return int Item ID number
	 * @author William Anderson
	 */
	public int Get_Inventory_ID(String name){
		return Converter.get(name);
	}

	/**
	 * Adds an Item to the Main_Inventory HashMap.
	 * @param int Item ID number
	 * @param Item Name
	 * @author William Anderson
	 */
	//Currently this assumes that no one will try to add the same Item or Item ID number twice.
	public void Add_Inventory(int ID, String Name){
		Item_Details New_Item = new Item_Details(ID, Name);
		Main_Inventory.put(New_Item.Item_ID, New_Item);
		Converter.put(New_Item.Item_Name, New_Item.Item_ID);
	}

	/**
	 * Returns a LinkedList of every Item ID number currently in the inventory
	 * @return LinkedList of every Item ID number currently in the inventory
	 * @author William Anderson
	 */
	public LinkedList<Integer> Pass_Inventory(){
		Set<Integer> Tempset = Main_Inventory.keySet();
		LinkedList<Integer> RetList = new LinkedList<Integer>();
		RetList.addAll(Tempset);
		return RetList;
	}
	
	/**
	 * Fills the Main_Inventory Hashmap with data from a CSV file
	 * @param Filename Name of the CSV file to be read
	 * @throws ParseException
	 * @throws IOException
	 */
	public void Inventory_Intialize(String Filename) throws ParseException, IOException{
		 BufferedReader br = new BufferedReader(new FileReader(Filename));
		    String line =  null;

		    while((line=br.readLine())!=null){
		            String arr[] = line.split(",");
		            Add_Inventory(Integer.valueOf(arr[0]), arr[1]);
		            Main_Inventory.get(Integer.valueOf(arr[0])).Qty_Update(100);
		    }
	}
	
	/**
	 * Removes the specified quantity of the item from the Main Inventory therefore reserving some items
	 * for that order.  It does not, however, update the quantity of that item anywhere in the shelves.
	 * @param Item_ID
	 * @param Qty
	 */
	public void Order_Claim(int Item_ID, int Qty){
		//Assuming there will be no orders for items not in inventory
		Item_Details Item_Use = Main_Inventory.get(Item_ID);
		int tempvar = Item_Use.Item_Quantity;
		tempvar -= Qty;
	}
}	

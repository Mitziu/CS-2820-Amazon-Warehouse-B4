package Inventory;
import java.util.*;

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
	
	//We should make this a seperate class outside of inventory so we have a baseline Item class
	//That way Orders, Inventory, and Floor can all just use one item class together
	//@Max

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
		public void Qty_Update(){
			Item_Quantity = 0;
			for (int value : Containers.values()) {
				Item_Quantity += value;
			}
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
	 * @param ID Item ID number
	 * @return int Amount of Item currently in stock
	 */
	public int Get_Item_Qty(int ID){
		return Main_Inventory.get(ID).Item_Quantity;
	}

	/**
	 * Returns the name of the item when given the item ID number
	 * @param ID Item ID number
	 * @return String Item's name
	 * @author William Anderson
	 */
	public String Get_Item_Name(int ID){
		return Main_Inventory.get(ID).Item_Name;
	}

	/**
	 * Returns the Item's ID number given the Item name
	 * @param name Name of Item
	 * @return int Item ID number
	 * @author William Anderson
	 */
	public int Get_Inventory_ID(String name){
		return Converter.get(name);
	}
	
	/** 
	 * Returns a LinkedList containg all of the ID numbers of the containers that are currently
	 * holding this Item.  If no containers currently hold this item then it returns a single 
	 * element list with the value 0 in it.
	 * @param ID Item ID number
 	 * @return LinkedList of Container ID numbers
 	 * @author William Anderson
	 */
	public LinkedList<Integer> Contained_In(int ID){
		LinkedList<Integer> RetList = new LinkedList<Integer>();
		boolean empty_check = Main_Inventory.get(ID).Containers.isEmpty();
		if (empty_check == true){
			RetList.add(0);
		}
		
		else{
		Set<Integer> Tempset = Main_Inventory.get(ID).Containers.keySet();
		RetList.addAll(Tempset);
		}
		
		return RetList;
	}

	/**
	 * Adds an Item to the Main_Inventory HashMap.
	 * @param ID Item ID number
	 * @param Name Name
	 * @author William Anderson
	 */
	//Currently this assumes that no one will try to add the same Item or Item ID number twice.
	public void Add_Inventory(int ID, String Name){
		Item_Details New_Item = new Item_Details(ID, Name);
		Main_Inventory.put(New_Item.Item_ID, New_Item);
		Converter.put(New_Item.Item_Name, New_Item.Item_ID);
	}

	/**
	 * Adds an Item to the container by either adding that container and the amount contained to the 
	 * item's HashMap of containers or updates the value for that container in the HashMap
	 * @param Item_ID Item ID number
	 * @param Container_ID Container ID number
	 * @param Qty Quantity to be added
	 * @author William Anderson
	 */
	public void Put_Container(int Item_ID, int Container_ID, int Qty){
		Item_Details Item_Use = Main_Inventory.get(Item_ID);
		
		if (Item_Use.Containers.get(Container_ID) != null){
			int tempvar = Item_Use.Containers.get(Container_ID);
			tempvar +=  Qty;
			Item_Use.Containers.put(Container_ID, tempvar);
			Item_Use.Qty_Update();
		}

		else {
			Item_Use.Containers.put(Container_ID, Qty);
			Item_Use.Qty_Update();
		}
		
	}
	
	/**
	 * Takes an item out of a container by editing the quantity for that container in the Item's HashMap
	 * Also it will remove the container from the item's HashMap if the amount in the container drops to 0
	 * @param Item_ID Item ID number
	 * @param Container_ID Container ID number
	 * @param Qty Quantity to be taken
	 * @author William Anderson
	 */
	public void Take_Container(int Item_ID, int Container_ID, int Qty){
		//Making an assumption that a robot will never attempt to take an item from a shelf
		//not holding that item
		Item_Details Item_Use = Main_Inventory.get(Item_ID);
		int tempvar = Item_Use.Containers.get(Container_ID);
		tempvar -= Qty;
		
		if (tempvar == 0){
			Item_Use.Containers.remove(Container_ID);
			Item_Use.Qty_Update();
		}
		
		else{	
		Item_Use.Containers.put(Container_ID, tempvar);
		Item_Use.Qty_Update();
		}	
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
}	

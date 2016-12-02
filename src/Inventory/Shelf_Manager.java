package Inventory;
import java.util.*;

/**
 * Main Class for the Shelf Manager
 * @author William Anderson
 *
 */
public class Shelf_Manager implements S_Manager{
	
	public HashMap <Integer, Shelf_Details> Shelf;
	
	public Shelf_Manager(){
		Shelf = new HashMap <Integer, Shelf_Details>();
	}
	
	/**
	 * Nested Class to store details of individual shelves.  Namely, a HashMap of items contained
	 * within the shelf and their quantities.
	 * @author William Anderson
	 *
	 */
	class Shelf_Details{
		public HashMap <Integer, Integer> Items;
		int Shelf_ID;

		Shelf_Details(int Shelf_ID){
			this.Shelf_ID = Shelf_ID;
			this.Items = new HashMap<Integer, Integer>();
		}
		
	}	
	
	/**
	 * Provides a list of items the shelf holds
	 * @param Shelf_ID ID number for the shelf
	 * @return LinkedList of items contained in the shelf
	 */
	public LinkedList Item_List(int Shelf_ID){
		Set<Integer> Tempset = Shelf.get(Shelf_ID).Items.keySet();
		LinkedList<Integer> RetList = new LinkedList<Integer>();
		RetList.addAll(Tempset);
		return RetList;
	}
	
	/**
	 * Returns the Number of a specific Item in a specific Container
	 * @param Item_ID ID number for the Item
	 * @param Container_ID	ID number for the Container
	 * @return Number of Items in that container
	 */
	public int Container_Count(int Item_ID, int Shelf_ID){
		if (Shelf.get(Shelf_ID).Items.get(Item_ID) != null){
			int count = Shelf.get(Shelf_ID).Items.get(Item_ID);
			return count;
		}
		else{
			int count = 0;
			return count;
		}
	}
	
	/**
	 * Adds an Item to the container by either adding that item and the amount contained to the 
	 * Shelf's HashMap of items or updates the value for that item in the HashMap
	 * @param Item_ID Item ID number
	 * @param Container_ID Container ID number
	 * @param Qty Quantity to be added
	 * @author William Anderson
	 */
	public void Put_Container(int Item_ID, int Shelf_ID, int Qty){
		Shelf_Details Shelf_Use = Shelf.get(Shelf_ID);
		
		if (Shelf_Use.Items.get(Item_ID) != null){
			int tempvar = Shelf_Use.Items.get(Item_ID);
			tempvar +=  Qty;
			Shelf_Use.Items.put(Item_ID, tempvar);
		}

		else {
			Shelf_Use.Items.put(Item_ID, Qty);
		}
		
	}
	
	/**
	 * Takes an item out of a container by editing the quantity for that item in the Shelf's HashMap
	 * Also it will add more of that specific item to that shelf if the quantity drops too low
	 * @param Item_ID Item ID number
	 * @param Container_ID Container ID number
	 * @param Qty Quantity to be taken
	 * @author William Anderson
	 */
	public void Take_Container(int Item_ID, int Shelf_ID, int Qty){
		//Making an assumption that a robot will never attempt to take an item from a shelf
		//not holding that item
		Shelf_Details Shelf_Use = Shelf.get(Shelf_ID);
		int tempvar = Shelf_Use.Items.get(Item_ID);
		tempvar -= Qty;
		
		//Checks if the new quantity for the shelf is 0 and then removes the item from the list 
		//of items contained in the Shelf if it is.
		if (tempvar == 0){
			Shelf_Use.Items.remove(Item_ID);
		}
		
		else{
			Shelf_Use.Items.put(Item_ID, tempvar);	
		}
	}
	
	/**
	 * Returns a LinkedList containing all of the ID numbers of the containers that are currently
	 * holding this Item.  If no containers currently hold this item then it returns a single 
	 * element list with the value 0 in it.
	 * @param Item_ID ID of the item that each shelf will be checked for.  
	 * @return LinkedList of Shelf IDs that contain the item.
	 * @author William Anderson
	 */
	public LinkedList<Integer> Contained_In(int Item_ID){
		//Creates List_Use which is a List of all the Shelf IDs
		//Also Creates Retlist which is a list that will be filled with all the 
		//Shelf IDs of the Shelves that contain the specified Item
		Set<Integer> Tempset = Shelf.keySet();
		LinkedList<Integer> List_Use = new LinkedList<Integer>();
		List_Use.addAll(Tempset);
		LinkedList<Integer> RetList = new LinkedList<Integer>();
		
		//Checking Shelf by Shelf if it contains the item
		for(int i=0; i < List_Use.size(); i++){
			//Shelf_Use is the current Shelf that will be checked if it contains the item
			Shelf_Details Shelf_Use = Shelf.get(List_Use.get(i)); 
			
			if (Shelf_Use.Items.containsKey(Item_ID)){
				RetList.add(Shelf_Use.Shelf_ID);
			}
		}
		
		//if no Shelf contains the item it returns 0
		if (RetList.size() == 0){
			RetList.add(0);
		}
		
		return RetList;
	}
	
	/**
	 * Takes a list of ID number for the shelves and creates a reference HashMap to check quantity
	 * of items on a shelf by shelf basis. As well as places items already in the Main Inventory on shelves.
	 * @param Shelf_IDs ID number for the shelves to be generated
	 */
	//This assumes that the Inventory has been generated and Initialized before this method
	//is called.
	public void Shelf_Manager_Init(LinkedList<Integer> Shelf_IDs, LinkedList<Integer> Inv, LinkedList<Integer> Qty){
		//Adding shelf entries to the Shelf HashMap 
		
		for (int i=0; i < Shelf_IDs.size(); i++){
			Shelf_Details New_Shelf = new Shelf_Details(Shelf_IDs.get(i));
			Shelf.put(New_Shelf.Shelf_ID, New_Shelf);
	      }
		
		//If there are as many or more shelves than Items in inventory it simply cycles through
		//each item and places it on a shelf 
		if (Shelf_IDs.size() >= Inv.size()){
			for (int i = 0; i < Inv.size(); i++){
				Put_Container(Inv.get(i), Shelf_IDs.get(i), Qty.get(i));
			
			}
		}
		
		//If there are more items than shelves then it will cycle through the items and shelves
		//placing one item per shelf until it runs out of shelves then it starts back at the
		//beginning of the list of shelves and adds another item to each shelf.  Then it repeats
		//until every item has been placed on a shelf
		else{
			int y= 0;
			for (int x = 0; x < Inv.size(); x++){
				if (y < Shelf_IDs.size() && x < Inv.size()){
					Put_Container(Inv.get(x), Shelf_IDs.get(y), Qty.get(x));
					y++;
				}
				
				else if(x < Inv.size()) {
					y = 0;
					Put_Container(Inv.get(x), Shelf_IDs.get(y), Qty.get(x));
					y++;
				}
			}
		}
	}
}
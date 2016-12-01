package Inventory;
import java.util.*;
/**
 * 
 * @author William Anderson
 *
 */
public interface S_Manager {
	
	//Returns list of items contained in that shelf
	public LinkedList Item_List(int Shelf_ID);
	
	//Returns amount of Item in that shelf
	public int Container_Count(int Item_ID, int Shelf_ID);
	
	//Adds specified amount of item to that shelf
	public void Put_Container(int Item_ID, int Shelf_ID, int Qty);
	
	//Takes specified amount away from that shelf
	public void Take_Container(int Item_ID, int Shelf_ID, int Qty);
	
	//Returns a List of every shelf that contains that item
	public LinkedList<Integer> Contained_In(int Item_ID);
}

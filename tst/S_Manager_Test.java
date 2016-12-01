import Inventory.Inventory;
import Inventory.Shelf_Manager;

import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
public class S_Manager_Test {
	
	//Before//
	Inventory Inv = new Inventory();
	Shelf_Manager SM = new Shelf_Manager(Inv);
	LinkedList<Integer> MockShelf_IDs =	new LinkedList<Integer>();
	
	@Test
	public void testconstructor(){
		for (int i = 1; i <=9; i++){
			MockShelf_IDs.add(i);
		}

		SM.Shelf_Manager_Init(MockShelf_IDs);
		SM.Put_Container(75450, 1, 100);
		assertEquals(100, SM.Container_Count(75450, 1));
		assertEquals(1, SM.Contained_In(75450).get(0).intValue());
		assertEquals(75450, SM.Item_List(1).get(0));
		
		
	}

	@Test
	public void testconstructor1()throws ParseException, IOException{
		for (int i = 1; i <=5; i++){
			MockShelf_IDs.add(i);
		}
		
		Inv.Inventory_Intialize("CSVFiles/Inventory_CSV");
		SM.Shelf_Manager_Init(MockShelf_IDs);
		SM.Put_Container(75450, 1, 100);
		assertEquals(100, SM.Container_Count(75450, 1));
		assertEquals(1, SM.Contained_In(75450).get(0).intValue());
		assertEquals(3, SM.Item_List(1).size());
		assertEquals(2, SM.Item_List(2).size());
		
	}
}

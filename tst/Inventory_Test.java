import Inventory.Inventory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;

public class Inventory_Test {
	
	//Before//
	Inventory Inv = new Inventory();
	Shelf_Manager SM = new Shelf_Manager(Inv);
	
	@Test
	public void testconstructor(){
		Inv.Add_Inventory(75450, "Baseball");
		assertEquals(75450, Inv.Get_Inventory_ID("Baseball"));
		assertEquals("Baseball", Inv.Get_Item_Name(75450));
		assertEquals(0, Inv.Get_Item_Qty(75450));
	}
	
	/**
	 * Before anythings loaded
	 */
	@Test
	public void testContained_In1(){
		Inv.Add_Inventory(75450, "Baseball");
		int testvar = SM.Contained_In(75450).get(0);
		assertEquals(0, testvar);
	}
	
	/**
	 * After loading
	 */
	@Test
	public void testContained_In2(){
		Inv.Add_Inventory(75450, "Baseball");
		SM.Put_Container(75450, 11, 13);
		assertEquals(11, SM.Contained_In(75450).get(0).intValue());
		assertEquals(13, Inv.Get_Item_Qty(75450));
	}
	/**
	 * Testing after multiple different items are loaded
	 */
	@Test
	public void testContained_In3(){
		Inv.Add_Inventory(75450, "Baseball");
		SM.Put_Container(75450, 11, 13);
		Inv.Add_Inventory(75451, "Softball");
		Inv.Add_Inventory(75452, "Ballcap");
		SM.Put_Container(75451, 12, 27);
		SM.Put_Container(75452, 13, 38);
		
		
		assertEquals(11, SM.Contained_In(75450).get(0).intValue());
		assertEquals(13, Inv.Get_Item_Qty(75450));
		assertEquals(12, SM.Contained_In(75451).get(0).intValue());
		assertEquals(27, Inv.Get_Item_Qty(75451));
		assertEquals(13, SM.Contained_In(75452).get(0).intValue());
		assertEquals(38, Inv.Get_Item_Qty(75452));
	}
	
	/**
	 * Testing after multiple of the same item are loaded
	 */
	@Test
	public void testContained_in4(){
		Inv.Add_Inventory(75450, "Baseball");
		SM.Put_Container(75450, 11, 13);
		
		SM.Put_Container(75450, 12, 17);
		assertEquals(12, SM.Contained_In(75450).get(1).intValue());
		assertEquals(30, Inv.Get_Item_Qty(75450));
		
		SM.Put_Container(75450, 33, 27);
		assertTrue(SM.Contained_In(75450).get(0).intValue() == 11 || SM.Contained_In(75450).get(0).intValue() == 12 || SM.Contained_In(75450).get(0).intValue() == 33);
		assertTrue(SM.Contained_In(75450).get(1).intValue() == 11 || SM.Contained_In(75450).get(1).intValue() == 12 || SM.Contained_In(75450).get(1).intValue() == 33);
		assertTrue(SM.Contained_In(75450).get(2).intValue() == 11 || SM.Contained_In(75450).get(2).intValue() == 12 || SM.Contained_In(75450).get(2).intValue() == 33);
		assertEquals(57, Inv.Get_Item_Qty(75450));
	}
	/**
	 * Testing after one item is loaded
	 */
	@Test
	public void testTake_Container1(){
		Inv.Add_Inventory(75450, "Baseball");
		SM.Put_Container(75450, 11, 13);
		SM.Take_Container(75450, 11, 7);
		assertEquals(6, Inv.Get_Item_Qty(75450));
	}
	
	/**
	 * Testing after multiple different items are loaded
	 */
	@Test
	public void testTake_Container2(){
		Inv.Add_Inventory(75450, "Baseball");
		SM.Put_Container(75450, 11, 13);
		Inv.Add_Inventory(75451, "Softball");
		Inv.Add_Inventory(75452, "Ballcap");
		SM.Put_Container(75451, 12, 27);
		SM.Put_Container(75452, 13, 38);
		SM.Take_Container(75450, 11, 7);
		SM.Take_Container(75451, 12, 7);
		SM.Take_Container(75452, 13, 7);
		
		assertEquals(11, SM.Contained_In(75450).get(0).intValue());
		assertEquals(6, Inv.Get_Item_Qty(75450));
		assertEquals(12, SM.Contained_In(75451).get(0).intValue());
		assertEquals(20, Inv.Get_Item_Qty(75451));
		assertEquals(13, SM.Contained_In(75452).get(0).intValue());
		assertEquals(31, Inv.Get_Item_Qty(75452));
	}
	
	/**
	 * Testing after multiple of the same item are loaded
	 */
	@Test
	public void testTake_Container3(){
		Inv.Add_Inventory(75450, "Baseball");
		SM.Put_Container(75450, 11, 13);
		SM.Put_Container(75450, 12, 17);
		SM.Take_Container(75450, 12, 15);
		assertEquals(12, SM.Contained_In(75450).get(1).intValue());
		assertEquals(15, Inv.Get_Item_Qty(75450));
		
		SM.Put_Container(75450, 33, 27);
		SM.Take_Container(75450, 33, 15);
		assertTrue(SM.Contained_In(75450).get(0).intValue() == 11 || SM.Contained_In(75450).get(0).intValue() == 12 || SM.Contained_In(75450).get(0).intValue() == 33);
		assertTrue(SM.Contained_In(75450).get(1).intValue() == 11 || SM.Contained_In(75450).get(1).intValue() == 12 || SM.Contained_In(75450).get(1).intValue() == 33);
		assertTrue(SM.Contained_In(75450).get(2).intValue() == 11 || SM.Contained_In(75450).get(2).intValue() == 12 || SM.Contained_In(75450).get(2).intValue() == 33);
		assertEquals(27, Inv.Get_Item_Qty(75450));
	}
	/**
	 * tests that the container is removed from the list if it is emptied of an item
	 */
	@Test
	public void testEmpty_Container(){
		Inv.Add_Inventory(75450, "Baseball");
		SM.Put_Container(75450, 11, 13);
		SM.Put_Container(75450, 12, 17);
		
		assertTrue(SM.Contained_In(75450).get(0).intValue() == 11 || SM.Contained_In(75450).get(0).intValue() == 12);
		assertTrue(SM.Contained_In(75450).get(1).intValue() == 11 || SM.Contained_In(75450).get(1).intValue() == 12);
		assertTrue(SM.Contained_In(75450).size() == 2);
		SM.Take_Container(75450, 11, 13);
		
		assertTrue(SM.Contained_In(75450).get(0).intValue() == 12);
		assertTrue(SM.Contained_In(75450).size() == 1);
		
		SM.Take_Container(75450, 12, 17);
		
		assertTrue(SM.Contained_In(75450).get(0).intValue() == 0);
		
		assertEquals(0, Inv.Get_Item_Qty(75450));
	}
	
	@Test
	public void testInitializer() throws ParseException, IOException{
		Inv.Inventory_Intialize("Inventory_CSV");
		assertEquals("Cat Food", Inv.Get_Item_Name(7));
		assertEquals("Poster1", Inv.Get_Item_Name(9));
		assertEquals(7, Inv.Get_Inventory_ID("Cat Food"));
		assertEquals(1, Inv.Get_Inventory_ID("Baseball"));
		SM.Put_Container(6, 11, 100);
		SM.Put_Container(10, 22, 200);
		assertEquals(100, Inv.Get_Item_Qty(6));
		assertEquals(200, Inv.Get_Item_Qty(10));
		assertEquals(11, SM.Contained_In(6).get(0).intValue());
		assertEquals(22, SM.Contained_In(10).get(0).intValue());
		SM.Take_Container(6, 11, 50);
		SM.Take_Container(10, 22, 100);
		assertEquals(50, Inv.Get_Item_Qty(6));
		assertEquals(100, Inv.Get_Item_Qty(10));
		assertEquals(11, SM.Contained_In(6).get(0).intValue());
		assertEquals(22, SM.Contained_In(10).get(0).intValue());
		SM.Take_Container(6, 11, 50);
		SM.Take_Container(10, 22, 100);
		assertEquals(0, Inv.Get_Item_Qty(6));
		assertEquals(0, Inv.Get_Item_Qty(10));
		assertEquals(0, SM.Contained_In(6).get(0).intValue());
		assertEquals(0, SM.Contained_In(10).get(0).intValue());
	}
	@Test
	public void testContainer_Count() throws ParseException, IOException{
		Inv.Inventory_Intialize("Inventory_CSV");
		SM.Put_Container(6, 11, 100);
		SM.Put_Container(6, 22, 200);
		assertEquals(100, SM.Container_Count(6, 11));
		assertEquals(200, SM.Container_Count(6, 22));
		assertEquals(300, Inv.Get_Item_Qty(6));
		SM.Take_Container(6, 11, 50);
		SM.Take_Container(6, 22, 100);
		assertEquals(50, SM.Container_Count(6, 11));
		assertEquals(100, SM.Container_Count(6, 22));
		assertEquals(150, Inv.Get_Item_Qty(6));
		SM.Take_Container(6, 22, 100);
		assertEquals(0, SM.Container_Count(6, 22));
	}
	
	@Test (expected = IOException.class)
	public void initializerExceptionThrown() throws IOException{
		Inv.Inventory_Intialize("Fake_CSV");
	}
	
}


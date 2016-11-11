package Floor;

import java.util.ArrayList;
import java.util.HashMap;
public class Setup implements FloorPositions {
	floor f1;
	Station picker ;
	Station packager ;
	ChargingStation charge ;
	m_belt f_belt ; 
	ArrayList<Shelf> SA ;
	ArrayList<route> Routes;
	public Setup(){	
	}
	public void Initialize(){
		f1 = new floor();
		picker = new Station(1,10);
		packager =new Station(1,1);
		charge = new ChargingStation(2,1);
		f_belt = new m_belt();
		SA = new ArrayList<Shelf>(); 
		Routes = new ArrayList<route>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		ArrayList<Integer> routesindex = new ArrayList<Integer>();
		index.add(1);
		index.add(4);
		index.add(7);
		index.add(10);
		for(int i = 0; i< index.size();i++){
			for(int j = 0; j<7;j++){
				int y = index.get(i);
				Shelf temp = new Shelf(4+j,y,i);
				SA.add(temp);
			}
		}
	}
	public HashMap<String, ArrayList<Point>> getAllPositions(){
		HashMap<String, ArrayList<Point>> m_map = new HashMap<String, ArrayList<Point>>();
		return m_map;
	}
}

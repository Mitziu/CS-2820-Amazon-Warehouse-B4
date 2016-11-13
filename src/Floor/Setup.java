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
                                                                                String s = String.valueOf( 4+j )+ String.valueOf(i);
				Shelf temp = new Shelf(4+j,y,Integer.valueOf(s));
				SA.add(temp);
			}
		}
	}
	public HashMap<String, Point> getAllPositions(){
		HashMap<String, Point> m_map = new HashMap<String, Point>();
                                        Point f = new Point(f1.getx(),f1.gety());
                                        m_map.put("floor", f);
                                        f = new Point(picker.getx(),picker.gety());
                                        m_map.put("picker", f) ;
                                        f = new Point(packager.getx(),packager.gety());
                                        m_map.put("packager", f) ;
                                        f = new Point(charge.getx(),charge.gety());
                                        m_map.put("charge", f) ;
                                        f = new Point(f_belt.getx(),f_belt.gety());
                                        m_map.put("belt", f) ;
                                        for(int i = 0;i<SA.size();i++){
                                                  f = new Point(SA.get(i).getx(),SA.get(i).gety());
                                                  m_map.put(SA.get(i).name, f) ;
                                         }
		return m_map;
	}
}

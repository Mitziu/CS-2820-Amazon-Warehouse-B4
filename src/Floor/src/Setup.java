import java.util.ArrayList;
public class Setup {
	floor f1;
	Station picker ;
	Station packager ;
	ChargingStation charge ;
	m_belt f_belt ; 
	ArrayList<ShelfArea> SA ;
	public Setup(){
		f1 = new floor();
		picker = new Station(1,10);
		packager =new Station(1,1);
		charge = new ChargingStation(2,1);
		f_belt = new m_belt();
		SA = new ArrayList<ShelfArea>(); 
		ArrayList<Integer> index = new ArrayList<Integer>();
		index.add(1);
		index.add(4);
		index.add(7);
		index.add(10);
		for(int i = 0; i< index.size();i++){
			int y = index.get(i);
			ShelfArea temp = new ShelfArea(4,y,i);
			SA.add(temp);
		}
	}
}

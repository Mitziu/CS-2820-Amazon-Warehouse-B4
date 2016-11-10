package Floor;

public class ChargingStation extends Thing {
	public static int width= 2;
	public static int length=1;
	public ChargingStation(int x_loc, int y_loc){
		super(x_loc,y_loc,width,length);
	}
	public boolean IsCharging(int x,  int y){
		boolean result = false;
		if((x<x_loc+width&&x>x_loc)&&(y>y_loc&&y<y_loc+length)){
			result = true;
		}
		return result;
	}
}

package Floor;

public class Station extends Thing {
	public static int w = 1;
	public static int l = 1;
	public Station(int x, int y){
		super(x,y,w,l);
	}
	public boolean HitMeBro(int x, int y){
		boolean result = false;
		if((x<x_loc+width&&x>x_loc)&&(y>y_loc&&y<y_loc+length)){
			result =  true;
			//belt.runit;
		}
		return result;
	}
}

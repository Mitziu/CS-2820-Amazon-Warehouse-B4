package Floor;

public class Thing {
	public static int x_loc;
	public static int y_loc;
	public static int width;
	public static int length;
	public static boolean moveable; //some of them may be able to move, others do not
	public Thing(int x, int y, int w, int h){
		x_loc= x; 
		y_loc= y ;
		width= w;
		length= h;
		moveable = false;
	}
	public int getx(){ //x coordinate
		return x_loc;
	}
	public int gety(){ //y coordinate
		return y_loc;
	}
	public int getW(){	//width
		return width;
	}
	public int getL(){	//length
		return length;
	}
	public void AllowToMove(){
		moveable = true;
	}
	public void move(int x, int y){
		if(moveable){
			x_loc = x;
			y_loc = y;
		}
		
	}
}

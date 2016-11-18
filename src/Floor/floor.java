package Floor;

public class floor implements Thing {
	public  int x= 1 ;
	public  int y= 1;
	public  int w= 10;
	public  int l=10;
                     public  int x_loc;
	public  int y_loc;
	public  int width;
	public  int length;
	public  boolean moveable; 
	public floor(){
		x_loc= x; 
		y_loc= y ;
		width= w;
		length= l;
		moveable = false;
	}
         @Override
                      public int getx(){ //x coordinate
		return x_loc;
	}
        @Override
	public int gety(){ //y coordinate
		return y_loc;
	}
        @Override
	public int getW(){	//width
		return width;
	}
        @Override
	public int getL(){	//length
		return length;
	}
        @Override
	public void AllowToMove(){
		moveable = true;
	}
        @Override
	public void move(int x, int y){
		if(moveable){
			x_loc = x;
			y_loc = y;
		}
		
	}
                    public Point getLoc(){
		return new Point(x_loc,y_loc);
	}
}

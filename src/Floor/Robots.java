package Floor;

import java.util.ArrayList;
public class Robots implements Thing{ //contains robots and shelves
	public  int w= 1;
	public  int h=1;
	public  int id;
	public  Shelf m_shelf;
                     public boolean Carried= false;
                     public  int x_loc;
	public  int y_loc;
	public  int width;
	public  int length;
	public  boolean moveable; 
	public Robots(int x, int y, int s){
                                           x_loc= x; 
		y_loc= y ;
		width= w;
		length= h;
		id = s;
                                           this.AllowToMove();
		
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
                    public boolean HasShelf(){
                                return this.Carried;
                    }
                    public void CarryUp(Shelf input){
                                this.Carried = true;
                                m_shelf = input;
                    }
}

package Floor;

public class route extends Thing{
	public String name;
	public route(int x_loc,int y_loc, int w, int l, int id){ //x,y here is the left start point of the Floor.route
		super(x_loc,y_loc,width,length);
		name = "Hawk Highway" + id;
	}
}

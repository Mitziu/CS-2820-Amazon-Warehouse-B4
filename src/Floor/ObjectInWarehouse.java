package Floor;

/**
 * Created by Mitziu on 12/4/16.
 */
public interface ObjectInWarehouse {

    public Point getLocation ();
    public Integer getID ();
    public void move(Integer x, Integer y);

}

package Floor;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Matt on 11/10/2016.
 * @author Matt
 * Please implement this so that the visualizer can see your stuff.
 */
public interface FloorPositions {

    /**
     *
     * @return A map | Keys are objects on the floor--perhaps object Id | Values are a data structure |
     * Containing positions, type of object | Later, we can add more info, but for now that should be enough
     *
     *  Feel free to change the Key/Value to suit your needs
     */
    HashMap<String, ArrayList<Point>> getAllPositions();
    void Initialize();
    // 
}

package Floor;

import java.util.HashMap;

/**
 * Created by Mitziu on 11/15/16.
 * @author Mitziu
 * @author Matthew McCan
 */
public class MockFloor implements FloorPositions{

    HashMap<String, Point> floorMap;

    public MockFloor() {
        Initialize();
    }

    @Override
    public void Initialize() {
        floorMap = new HashMap<>();

        floorMap.put("Bender_Is_The_Greatest_Robot_Ever", new Point(3, 5));
        floorMap.put("A_Shelf", new Point(3, 6));
        floorMap.put("Any_Other_Object_In_Warehouse", new Point(1,1));
    }

    @Override
    public HashMap<String, Point> getAllPositions() {
        return floorMap;
    }
}

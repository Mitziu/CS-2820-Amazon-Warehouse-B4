package Clock;

import java.util.Observable;

/**
 * Created by Mitziu on 11/3/16.
 * Group B4
 */
public class Clock extends Observable{

    public void tick() {
        this.setChanged();
        this.notifyObservers();
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floor;

import Inventory.S_Manager;
import Ordering.Order;

/**
 *
 * @author zuoyuan
 */
//For saving robots' world, we shall get a manager!!
public interface RobotManager {

    //WARNING: INITIALIZE FloorPositions FIRST!!!!!!!!!!!!!!
    /*DOCUMENTAION: OrderIsComing is the "MAIN" function for RobotManager, no other functions are needed*/
    public void Background(S_Manager input, Setup src);

    public void SetOrder(Order input); // set order

    public boolean IsDone(); // show if order is done, will return true if everything is finished
    /*USER MANUAL(right now can only run single order):
      0. initialize shelfmaneger and floorpositions, and create the object with its own constructor
      1. SetOrder        (warining : just a single Order)
      
      2. run, while keep checking IsDone()
      3. if IsDone() == true, get a new order and SetOrder again!
    TO BE CONTINUED
     */
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Floor;

import Inventory.Inventory_Interface;
import Ordering.Order;

/**
 *
 * @author zuoyuan
 */
//For saving robots' world, we shall get a manager!!
public interface RobotManager {
     
    //WARNING: INITIALIZE FloorPositions FIRST!!!!!!!!!!!!!!
    /*DOCUMENTAION: OrderIsComing is the "MAIN" function for RobotManager, no other functions are needed*/
    public void OrderIsComing(Order order, Inventory_Interface input , FloorPositions src);
}

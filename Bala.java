/*
 * Project: Ant Colony
 * This: Bala.java
 * Author: C Robinson
 * Date: 05 May 2018
 * Purpose: Subclass for bala ants to extend AntObject
 */

import java.util.*;

public class Bala extends AntObject {
    Random rand = new Random();
    // ====================== default constructor() ==========================
    public Bala() {
    }
    // ====================== constructor() ==================================  
    public Bala(ColonyNode node) {
        this.location = node;
        this.lastTurn = -1;
    }
    // ====================== turn method ====================================
    public void nextTurn(int thisTurn) {
        // check if turn taken
        if (lastTurn == thisTurn)
            return;
        // Max age 1 year
        if ((thisTurn - start) > 10 * lifespan) {
            death();
            return;
        }
        lastTurn = thisTurn;
        // fight nonbala ants if present
        if (location.getNonBalaList().size() > 0) {
            attack();
        }
        else {
            move(location.getAdjacentNodes().get(rand.nextInt(location.
                    getAdjacentNodes().size())));
        }
    }
    // ====================== attack method ==================================    
    public void attack() {
        ArrayList<AntObject> nonBala = location.getNonBalaList();        
        if (rand.nextInt(2) == 0)
            nonBala.get(0).death();
    }
}

/*
 * Project: Ant Colony
 * This: Scout.java
 * Author: C Robinson
 * Date: 05 May 2018
 * Purpose: Subclass for scout ants to extend AntObject
 */

import java.util.*;

public class Scout extends AntObject {    
    // ====================== default constructor() ==========================
    public Scout() {
    }
    // ====================== constructor() ==================================      
    public Scout(ColonyNode node) {
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
        Random rand = new Random();
        move(location.getAdjacentNodes().get(rand.nextInt(location.
                getAdjacentNodes().size())));
    }
    // ====================== move scout method ==============================
    @Override
    public void move(ColonyNode node) {
        location.killAnt(this);
        location = node;
        location.addAnt(this);
        if (!(location.isVisible())) {
            location.setVisible(true);
        }
    }
}

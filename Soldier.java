/*
 * Project: Ant Colony
 * This: Soldier.java
 * Author: C Robinson
 * Date: 05 May 2018
 * Purpose: Subclass for soldier ants to extend AntObject
 */

import java.util.*;

public class Soldier extends AntObject {   
    // ====================== default constructor() ==========================
    public Soldier() {
    }
    // ====================== constructor() ==================================    
    public Soldier(ColonyNode node) {
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
        if (location.getAntsOfType(new Bala()).size() > 0) {
            // If bala present, attack it
            attack();
        } else {
            Random rand = new Random();
            ArrayList<ColonyNode> visibleList = new ArrayList<>();            
            for (ColonyNode n : location.getAdjacentNodes()) {
                if (n.isVisible()) {
                    visibleList.add(n);
                }
            }
            // Set to random visible location
            ColonyNode moveLocation = 
                    visibleList.get(rand.nextInt(visibleList.size()));
            // If bala visible, move to it
            for (ColonyNode n : visibleList) {
                if (n.getAntsOfType(new Bala()).size() > 0) {
                    moveLocation = n;
                }
            }
            move(moveLocation);
        }
    }
    // ====================== attack method ==================================   
    public void attack() {
        ArrayList<AntObject> balaList = location.getAntsOfType(new Bala());
        Random rand = new Random();
        if (rand.nextInt(2) == 0) {
            balaList.get(0).death();
        }
    }
}

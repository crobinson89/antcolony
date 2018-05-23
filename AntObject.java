/*
 * Project: Ant Colony
 * This: AntObject.java
 * Author: C R
 * Date: 05 May 2018
 * Purpose: Class for all types of ants to extend from
 */

public class AntObject {
    // ====================== variables ======================================
    public int ID;
    public int lifespan = 365;
    public int lastTurn;
    public int start;
    public boolean alive;
    public ColonyNode location;
    // ====================== default constructor ============================
    public AntObject(){        
    }
    // ====================== turn method ====================================    
    public void nextTurn(int thisTurn) {
    }
    // ====================== move ant method ================================
    public void move(ColonyNode newLocation) {
        location.killAnt(this);
        location = newLocation;
        location.addAnt(this);
    }
    // ====================== death method ===================================
    public void death() {
        location.killAnt(this);
    }
    // ====================== set start turn method ==========================
    public void setStart(int thisTurn) {
        this.start = thisTurn;
    }
    // ====================== set ID method ==================================
    public void setID(int id) {
        this.ID = id;
    }
}

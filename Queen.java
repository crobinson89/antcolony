/*
 * Project: Ant Colony
 * This: Queen.java
 * Author: C R
 * Date: 05 May 2018
 * Purpose: Subclass for queen ants to extend AntObject
 */

import java.util.*;

public class Queen extends AntObject {
    // ====================== variables ======================================
    int thisTurn;
    int lastID = 0;
    // ====================== default constructor() ==========================
    public Queen() {
    }
    // ====================== constructor() ==================================  
    public Queen(ColonyNode node) {
        lifespan = 20 * 365;
        this.location = node;        
    }
    // ====================== turn method ====================================
    public void nextTurn(int thisTurn) {
        this.thisTurn = thisTurn;
        // the queen dies after 20 years
        if ((thisTurn > (10 * lifespan)) || (location.getFood() < 1)) {
            death();
            return;
        }
        // every 10 turns start new ant
        if ((thisTurn % 10) == 0) {
            this.hatch(null);
        }
        // check if bala is made on queen turn
        makeBala();
        // eats every turn
        this.eat();
    }
    // ====================== set ant lifespan method ========================    
    public void setLifespan(){
        this.lifespan = 20 * lifespan;
    }
    // ====================== hatch new ant method ===========================     
    public void hatch(AntObject ant) {
        Random gen = new Random();
        int rand = gen.nextInt(4);
        AntObject newAnt = new Forager(location);
            switch (rand){
            case 0: newAnt = new Forager(location); break;
            case 1: newAnt = new Forager(location); break;
            case 2: newAnt = new Soldier(location); break;
            case 3: newAnt = new Scout(location); break;
            }
        if (ant != null) {
            newAnt = ant;
        }
        newAnt.setStart(thisTurn);
        lastID++;
        newAnt.setID(lastID);
        location.addAnt(newAnt);
    }
    // ====================== eat method ===================================== 
    public void eat() {
        int food = location.getFood();
        if (food < 1) {
            death();
        }
        location.setFood(food - 1);
    }
    // ====================== have queen die method ========================== 
    public void death() {
        System.out.println("The queen is dead.");
        location.thisNode.hideQueenIcon();
        location.colony.sim.endSim();
    }
    // ====================== spawn Bala method ============================== 
    public void makeBala() {
        Random rand = new Random();
        // Location to spawn bala
        ColonyNode balaLocation = location.colony.colonyGrid[0][0];
        if (rand.nextInt(100) <= 3) {
            Bala newBala = new Bala(balaLocation);
            balaLocation.addAnt(newBala);
            newBala.setStart(thisTurn);
            lastID++;
            newBala.setID(lastID);
        }
    }
}

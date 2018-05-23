/*
 * Project: Ant Colony
 * This: ColonyNode.java
 * Author: C Robinson
 * Date: 05 May 2018
 * Purpose: Holds simulation and is used to run/manage actual events for sim
 */

import java.util.*;

public class ColonyNode {
    // ====================== variables ======================================
    int food, pheromone, x, y, antCount;
    boolean isVisible, isEntrance, isStepping, isQueen;
    ArrayList<AntObject> antList, removeList, addList;
    ColonyNodeView thisNode;
    SimColony colony;
    // ====================== constructor ====================================
    public ColonyNode(ColonyNodeView node, int x, int y) {
        Random rand = new Random();
        if (rand.nextInt(4) == 0) {
            this.food = rand.nextInt(501) + 500;
        } else {
            food = 0;
        }
        this.thisNode = node;
        this.x = x;
        this.y = y;
        this.pheromone = 0;
        this.antCount = 0;
        this.isQueen = false;        
        this.isVisible = false;
        this.isEntrance = false;
        this.antList = new ArrayList<>();
        this.removeList = new ArrayList<>();
        this.addList = new ArrayList<>();        
    }
    // ====================== sets ===========================================
    public void setFood(int food) {
        this.food = food;
    }
    public void setPheromone(int pheromoneLevel) {
        this.pheromone = pheromoneLevel;
    }
    public void setColony(SimColony colony) {
        this.colony = colony;
    }
    public void setVisible(boolean v) {
        this.isVisible = v;
        if (v == true)
            thisNode.showNode();
        if (v == false)
            thisNode.hideNode();
    }    
    // ====================== gets ===========================================
    public int getFood() {
        return food;
    }
    public int getPheromone() {
            return pheromone;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public ArrayList<ColonyNode> getAdjacentNodes() {
        return colony.getAdjacentNodes(this);
    }
    public ArrayList<AntObject> getAntsOfType(AntObject type) {
        ArrayList<AntObject> antType = new ArrayList<>();
        for (AntObject a : antList) {
            if (a.getClass() == type.getClass()) {
                antType.add(a);
            }
        }
        return antType;
    }
    public ArrayList<AntObject> getNonBalaList() {
        ArrayList<AntObject> nonBala = new ArrayList<>();
        for (AntObject a : antList) {
            if (a.getClass() != new Bala().getClass()) {
                nonBala.add(a);
            }
        }
        return nonBala;
    }    
    // ====================== check queen ====================================
    public boolean containsQueen() {
        return isQueen;
    }
    // ====================== check visibility ===============================
    public boolean isVisible() {
        return isVisible;
    }    
    // ====================== add ant method =================================
    public void addAnt(AntObject newAnt) {
        if (isStepping)
            addList.add(newAnt);
        else
            antList.add(newAnt);        
        updateView();
    }
    // ====================== kill ant method ================================

    public void killAnt(AntObject newAnt) {
        if (isStepping) 
            removeList.add(newAnt);
        else
            antList.remove(newAnt);
        updateView();
    }
    // ====================== count ant method ===============================
    public int countAnts(AntObject myAntType) {
        int count = 0;
        for (AntObject a : antList) {
            if (a.getClass() == myAntType.getClass()) {
                count++;
            }
        }
        return count;
    }
    // ====================== update node view method ========================
    public void updateView() {
        int queenCount = countAnts(new Queen());
        if (queenCount == 1) {
            isQueen = true;
            thisNode.setQueen(isQueen);
            thisNode.showQueenIcon();        
        }
        thisNode.setScoutCount(countAnts(new Scout()));
        if (countAnts(new Scout()) > 0){
            thisNode.showScoutIcon();
        } else {
            thisNode.hideScoutIcon();
        }
        thisNode.setForagerCount(countAnts(new Forager()));
        if (countAnts(new Forager()) > 0) {
            thisNode.showForagerIcon();
        } else {
            thisNode.hideForagerIcon();
        }
        thisNode.setSoldierCount(countAnts(new Soldier()));
        if (countAnts(new Soldier()) > 0) {
            thisNode.showSoldierIcon();
        } else {
            thisNode.hideSoldierIcon();
        }
        thisNode.setBalaCount(countAnts(new Bala()));
        if (countAnts(new Bala()) > 0) {
            thisNode.showBalaIcon();
        } else {
            thisNode.hideBalaIcon();            
        } 
        thisNode.setFoodAmount(food);
        thisNode.setPheromoneLevel(pheromone);       
    }
    // ====================== update ant list method =========================
    public void updateList() {
        for (AntObject a : removeList) {
            antList.remove(a);
        }
        removeList.clear();
        for (AntObject a : addList) {
            antList.add(a);
        }
        addList.clear();
    }
    // ====================== next turn method ===============================
    public void nextTurn(int thisTurn) {
        if((colony.sim.day > 0) && colony.sim.turn % 10 == 0){
            this.setPheromone(getPheromone() / 2);
        }
        isStepping = true;
        for (AntObject a : antList) {
            a.nextTurn(thisTurn);
        }
        isStepping = false;
        updateList();
        updateView();
    }
    
    
}



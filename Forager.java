/*
 * Project: Ant Colony
 * This: Forager.java
 * Author: C Robinson
 * Date: 05 May 2018
 * Purpose: Subclass for forager ants to extend AntObject
 */

import java.util.*;

public class Forager extends AntObject {
    // ====================== variables ======================================
    int retrace;
    boolean returnToQueen;
    ArrayList<ColonyNode> locationHistory;    
    // ====================== default constructor() ==========================
    public Forager() {
    }
    // ====================== constructor() ==================================  
    public Forager(ColonyNode node) {
        this.location = node;
        this.lastTurn = -1;
        this.returnToQueen = false;
        this.locationHistory = new ArrayList<>();
        this.locationHistory.add(location);        
    }
    // ====================== turn method ====================================
    public void nextTurn(int thisTurn) {        
        // check if turn taken        
        if (lastTurn == thisTurn){
            return;
        }
        // Max age 1 year
        if ((thisTurn - start) > 10 * lifespan) {
            death();
        }
        lastTurn = thisTurn;
        if (returnToQueen) {
            // Return if no food
            retrace--;
            depositPheromones();
            move(locationHistory.get(retrace));
        } else {
            // Explore until food found
            move(findHighestPheromone());
            isFoodFound();
        }      
    }
    // ====================== move forager method ============================
    @Override
    public void move(ColonyNode node) {
        location.killAnt(this);
        location = node;
        location.addAnt(this);
        if (location.containsQueen() && returnToQueen) {
            location.setFood(location.getFood() + 1);
            returnToQueen = false;
            locationHistory.clear();
        }
        locationHistory.add(location);
    }
    // ====================== find pheromones method =========================
    public ColonyNode findHighestPheromone() {
        Random rand = new Random();
        ArrayList<ColonyNode> adjacentList = location.getAdjacentNodes();
        ArrayList<ColonyNode> randomNode = new ArrayList<>();
        // remove nodes that have already been visited        
        Iterator<ColonyNode> nodeIterator = adjacentList.iterator();
        while(nodeIterator.hasNext()){
            ColonyNode visited = nodeIterator.next();
            if(locationHistory.contains(visited) || !visited.isVisible()){
                nodeIterator.remove();
            }
        }
        // check if all nodes have been visited
        if (adjacentList.isEmpty()) {
            adjacentList = location.getAdjacentNodes();
        }
        // Set first element as max
        ColonyNode max = adjacentList.get(0);
        // Traverse to find largest
        for (ColonyNode a : adjacentList) {
            if (max.isVisible() && max.getPheromone() 
                    < a.getPheromone()) {
                max = a;
            }
        }
        for (ColonyNode a : adjacentList) {
            if ((a.getPheromone() == max.getPheromone()) 
                    && a.isVisible()) {
                randomNode.add(a);
            }
        }
        // Catch for if node size is out of bounds       
        if(randomNode.isEmpty())
            max = randomNode.get(rand.nextInt(randomNode.size())+1);
        else
            max = randomNode.get(rand.nextInt(randomNode.size()));
        // return highest pheromone
        return max;
    }
    // ====================== check if food is found method ==================
    public void isFoodFound() {
        if (location.getFood() > 0 && !(location.containsQueen())) {
            location.setFood(location.getFood() - 1);
            returnToQueen = true;
            retrace = locationHistory.size() - 1;
        } 
    }
    // ====================== drop off pheromones method =====================
    public void depositPheromones() {
        if (!location.containsQueen()) {
            if (location.getPheromone() < 1000) {
                location.setPheromone(location.getPheromone() + 10);
            }
        }
    }
    // ====================== have forager die method ========================
    public void death() {
        if (returnToQueen) {
            location.setFood(location.getFood() + 1);
        }
        location.killAnt(this);
    }
}

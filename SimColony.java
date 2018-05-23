/*
 * Project: Ant Colony
 * This: Colony.java
 * Author: C R
 * Date: 05 May 2018
 * Purpose: Creates colony and sets every node, sets starting ants and food
 * for colony at grid (13,13). 
 */

import java.util.*;

public class SimColony {
    // ====================== variables ======================================
    ColonyNode [][] colonyGrid;
    ColonyView colonyView;
    ColonyNodeView cnodeview;
    ColonyNode cnode;
    SimTest sim;
    // ====================== constructor() ==================================
    public SimColony(ColonyView view, SimTest sim) {
        this.sim = sim;
        colonyGrid = new ColonyNode [27][27];
        this.colonyView = view;
    }
    // ====================== turn method ====================================   
    public void nextTurn(int thisTurn) {
        for (int x = 0; x < 27; x++) {
            for (int y = 0; y < 27; y++) {
                colonyGrid[x][y].nextTurn(thisTurn);
            }
        }
    }
    // ====================== gets ============================================  
    public ColonyView getColonyView() {
        return colonyView;
    }
    public ArrayList<ColonyNode> getAdjacentNodes(ColonyNode n) {
        int x = n.getX();
        int y = n.getY();
        ArrayList<ColonyNode> adjacentNodes = new ArrayList<>();
        for (int dx = (x > 0 ? -1 : 0); dx <= (x < 26 ? 1 : 0); ++dx) {
            for (int dy = (y > 0 ? -1 : 0); dy <= (y < 26 ? 1 : 0); ++dy) {
                if (dx != 0 || dy != 0) {
                        adjacentNodes.add(colonyGrid[x + dx][y + dy]);
                } 
            }
        }
        return adjacentNodes;
    }
    // ====================== initialize ant colony method ===================
    public void initColony() {
        for (int x = 0; x < 27; x++) {
            for (int y = 0; y < 27; y++) {
                cnodeview = new ColonyNodeView();
                cnode = new ColonyNode(cnodeview, x, y);
                cnode.setColony(this);                
                colonyView.addColonyNodeView(cnodeview, x, y);
                colonyGrid[x][y] = cnode;
                cnodeview.setID("(" + x + "," + y + ")");
                if (x == 13 && y == 13) {
                    Queen q = new Queen(cnode);
                    cnode.setFood(1000);
                    cnode.addAnt(q);
                    for (int f = 0; f < 50; f++)
                        q.hatch(new Forager(cnode));
                    for (int sc = 0; sc < 4; sc++)
                        q.hatch(new Scout(cnode));
                    for (int s = 0; s < 10; s++)
                        q.hatch(new Soldier(cnode));
                }
                if ((x > 11 && x < 15) && (y > 11 && y < 15))
                    cnode.setVisible(true);                
            }
        }
    }
}

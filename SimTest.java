/*
 * Project: Ant Colony
 * This: SimTest.java
 * Author: C Robinson
 * Date: 05 May 2018
 * Purpose: Holds simulation and is used to run/manage actual events for sim
 */

import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class SimTest implements SimulationEventListener {
    // ====================== variables ======================================
    public int turn = 0, day = 1;
    public boolean isQueenAlive, isStepping;
    SimColony colonySim;
    AntSimGUI gui;
    Thread thread;
    // ====================== constructor() ==================================
    public SimTest(AntSimGUI gui) {
        isQueenAlive = true;
        isStepping = true;
        this.gui = gui;
        colonySim = new SimColony(new ColonyView(27, 27), this);
        gui.initGUI(colonySim.getColonyView());
        this.thread = null;
        this.gui.setTime(day);
    }
    // ====================== turn method ====================================  
    public void nextTurn() {        
        do{
            this.gui.setTime(day);
            colonySim.nextTurn(turn);
            turn++;
            try{
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException ex){
                System.out.println("Interruption! Error!");
            }
            if((turn % 10) == 0){
                day++;
            }
        }while (!isStepping && isQueenAlive);
    }
    // ====================== end simulation method ========================== 
    public void endSim() {
        isQueenAlive = false;
        JOptionPane.showMessageDialog(null, ""
                    + "The queen is dead.", "Simulation Message.", 
                    JOptionPane.WARNING_MESSAGE);
        System.exit(0);
    }
    // ====================== trigger events using buttons method ============ 
    public void simulationEventOccurred(SimulationEvent simEvent) {
        switch (simEvent.getEventType()){
            case SimulationEvent.NORMAL_SETUP_EVENT:
                colonySim.initColony();
                JOptionPane.showMessageDialog(null, ""
                        + "Normal Setup Event", "Simulation Message.",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
            case SimulationEvent.RUN_EVENT:
                // Prevent error if button clicked while running
                if(isStepping == false){
                    break;
                }
                isStepping = false;
                thread = new Thread(){
                    public void run(){
                        nextTurn();
                    }
                }; 
                this.thread.start();
                break;
            case SimulationEvent.STEP_EVENT:
                // Prevent error if button clicked while running
                if(isStepping == false){
                    isStepping = true;
                    break;
                }
                nextTurn();
                break;
            case SimulationEvent.EXIT_EVENT:
                isQueenAlive = false;
                JOptionPane.showMessageDialog(null, ""
                        + "The simulation has ended!", "Simulation Message.",
                        JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            default:
                break;
        }
    }    
}

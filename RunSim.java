/*
 * Project: Ant Colony
 * This: RunSim.java
 * Author: C Robinson
 * Date: 05 May 2018
 * Purpose: Runs sim... Could be used to implement a menu system for other stuff
 */

public class RunSim {
    public static void main(String [] args) {        
        AntSimGUI gui = new AntSimGUI();
        gui.addSimulationEventListener(new SimTest(gui));
    }
}

package main;

import javax.swing.SwingUtilities;

import results.ResultList;
import connections.BTConnection;
import visualization.AngleCalculator;
import visualization.SettingsWindow;
import visualization.VisualizationWindow;

public class Main {
	
	/**
	 * Creates the connection to the robot, starts the engine and opens the visualization windows.
	 * @param args
	 */

	public static void main(String[] args) {
		
		System.out.println("Visualizer!");

		BTConnection connection = new BTConnection("00:16:53:0a:90:b0"); //Bluetooth address of robot
		connection.open();
		connection.flushOutput();
		
		ResultList list = new ResultList(connection);
		AngleCalculator angleCalc = new AngleCalculator(20, 400,300);

		SettingsWindow settings = new SettingsWindow(connection, angleCalc);
		
		
		VisualizationWindow win = new VisualizationWindow(list,angleCalc);
		
		
		SwingUtilities.invokeLater(win);
		SwingUtilities.invokeLater(settings);
		Engine engine = new Engine(win, list);
		engine.run();
		

	}

}

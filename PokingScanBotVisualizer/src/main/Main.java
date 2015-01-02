package main;

import javax.swing.SwingUtilities;

import results.ResultList;
import visualization.VisualizationWindow;
import connections.BTConnection;

public class Main {

	public static void main(String[] args) {
		System.out.println("Visualizer!");

		BTConnection connection = new BTConnection();
		connection.open();
		ResultList list = new ResultList(connection);
		
		VisualizationWindow win = new VisualizationWindow(list);
		SwingUtilities.invokeLater(win);
		
		
		Engine engine = new Engine(win, list);
		engine.run();
		
		

	}

}

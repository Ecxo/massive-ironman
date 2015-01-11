package main;

import results.ResultList;


import visualization.VisualizationWindow;

/**
 * Handles the updating of the visualization windows and fetching of data from the robot.
 * @author petri
 *
 */

public class Engine {
	private ResultList results;
	private VisualizationWindow window;
	private boolean running;
	private int waitTime;

	public Engine(VisualizationWindow win, ResultList list) {
		window = win;
		results = list;
		running = true;
		waitTime = 1;
	}

	public void run() {	
		while (running == true) {
			results.fetchData();
			window.reDraw();
			waitFor(waitTime);
		}
		
	}
	
	/**
	 * 
	 * @param waitTime time in seconds
	 */

	private void waitFor(int waitTime) {
		try {
			Thread.sleep(waitTime*1000);
		} catch (InterruptedException e) {
			System.out.println("Error sleeping");
			e.printStackTrace();
		}
		
	}

}

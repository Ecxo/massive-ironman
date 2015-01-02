package main;

import results.ResultList;
import visualization.VisualizationWindow;

public class Engine {
	private ResultList results;
	private VisualizationWindow window;
	private boolean running;

	public Engine(VisualizationWindow win, ResultList list) {
		window = win;
		results = list;
		running = true;
	}

	public void run() {	
		while (running == true) {
			results.fetchData();
			window.reDraw();
			waitFor(1);
		}
		
	}

	private void waitFor(int i) {
		try {
			Thread.sleep(i*1000);
		} catch (InterruptedException e) {
			System.out.println("Error sleeping");
			e.printStackTrace();
		}
		
	}

}

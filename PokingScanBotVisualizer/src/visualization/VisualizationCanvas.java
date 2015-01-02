package visualization;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import results.ResultList;

public class VisualizationCanvas {
	
	private Canvas canvas;
	private ResultList list;
	
	public VisualizationCanvas(ResultList l) {
		list = l;
		canvas = new Canvas();
		canvas.setBackground(Color.black);
		canvas.setSize(800, 600);
		
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public void update() {
		for (int i = 0; i < list.getArrayList().size(); i++) {
			
		}
	}
	
	

}

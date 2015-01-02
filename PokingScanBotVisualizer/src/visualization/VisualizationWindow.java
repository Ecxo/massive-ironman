package visualization;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

import results.ResultList;

public class VisualizationWindow implements Runnable {
	
	private JFrame frame;
	private VisualizationCanvas canvas;
	private ResultList list;
	
	public VisualizationWindow(ResultList l) {
		list = l;		
	}

	@Override
	public void run() {
		
		frame = new JFrame("PokingScanBot");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setResizable(false);
		
		createComponents(frame);
		frame.pack();
		frame.setVisible(true);
		
	}

	private void createComponents(JFrame frame2) {
		// TODO Auto-generated method stub
		
	}
	



}

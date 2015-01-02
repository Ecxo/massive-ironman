package visualization;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import results.ResultList;

public class VisualizationWindow implements Runnable {
	
	private JFrame frame;
	private VisualizationGraphics graph;
	private ResultList results;
	
	public VisualizationWindow(ResultList l) {
		results = l;		
	}

	@Override
	public void run() {
		
		frame = new JFrame("PokingScanBot");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setResizable(false);

		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
		
	}

	private void createComponents(Container container) {
		graph = new VisualizationGraphics(results);
		frame.getContentPane().setBackground(Color.BLACK);
		container.add(graph);
	}
	
	public void reDraw() {
        if (graph == null) {
            return;
        }
		frame.repaint();
		
	}





}

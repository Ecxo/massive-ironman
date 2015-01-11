package visualization;

import java.awt.Color;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

import results.ResultList;

/**
 * Creates the window for visualization.
 */

public class VisualizationWindow implements Runnable {

	private JFrame frame;
	private VisualizationGraphics graph;
	private ResultList results;
	private AngleCalculator angleCalc;

	public VisualizationWindow(ResultList l, AngleCalculator c) {
		results = l;
		angleCalc = c;
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
		graph = new VisualizationGraphics(results, angleCalc);
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

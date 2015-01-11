package visualization;

import java.awt.Color;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import results.ResultList;

/**
 * Draws the graphics of the measurement window. Reads results list for distance and uses anglecalc to calculate the correct position.
 * @author petri
 *
 */

public class VisualizationGraphics extends JPanel {

	private static final long serialVersionUID = 1L;
	private ResultList results;
	private ArrayList<Integer> list;
	private AngleCalculator angleCalc;

	public VisualizationGraphics(ResultList r, AngleCalculator c) {
		list = null;
		results = r;
		setBackground(Color.BLACK);
		angleCalc = c;
	}

	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		g.setColor(Color.GREEN);
		g.fillOval(angleCalc.getCenterX(), angleCalc.getCenterY(), 10, 10);
		g.setColor(Color.red);
		angleCalc.resetAngle();
		this.list = results.getArrayList();
		for (int i = 0; i < list.size(); i++) {
			angleCalc.calculateCoordinates(list.get(i));
			
			g.fillOval(angleCalc.getRoundedX(),angleCalc.getRoundedY(),10, 10);
		}

	}
}

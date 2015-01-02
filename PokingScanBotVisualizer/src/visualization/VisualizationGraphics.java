package visualization;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import results.ResultList;

public class VisualizationGraphics extends JPanel {

	private static final long serialVersionUID = 1L;
	private ResultList results;
	private ArrayList<Integer> list;

	public VisualizationGraphics(ResultList r) {
		list = null;
		results = r;
		setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GREEN);
		g.fillOval(10, 10, 10, 10);
		g.setColor(Color.red);
		this.list = results.getArrayList();
		for (int i = 0; i < list.size(); i++) {
			g.fillOval(30 + 20 * i, list.get(i), 20, 20);
			System.out.println("This is g shouting: " + list.get(i));
		}

	}
}

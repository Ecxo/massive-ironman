package visualization;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import connections.BTConnection;

/**
 * Window for showign settings and starting the scan.
 * @author petri
 *
 */

public class SettingsWindow implements Runnable, ActionListener {
	private JFrame frame;
	private BTConnection connection;
	private ButtonGroup bGroup;
	private AngleCalculator angle;

	
	public SettingsWindow (BTConnection con, AngleCalculator a) {
		connection = con;
		angle = a;
		
	}

	@Override
	public void run() {
		
		frame = new JFrame("Settings");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(200, 200));
		frame.setResizable(false);
		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
		
	}

	private void createComponents(Container container) {
		container.setLayout(new FlowLayout());
		JLabel accText = new JLabel("Amount of measurements:");
		container.add(accText);
		createButtons(container);
		JLabel measuringText = new JLabel("Method of measuring:");
		container.add(measuringText);
		
		JButton sonic = new JButton("Sonic");
		sonic.addActionListener(this);
		sonic.setActionCommand("sonic");
		JButton poker = new JButton("Touch");
		poker.addActionListener(this);
		poker.setActionCommand("poker");
		
		container.add(sonic);

		container.add(poker);
		
	}

	private void createButtons(Container container) {
		JRadioButton acc20 = new JRadioButton("20");
		acc20.setSelected(true);
		acc20.setActionCommand("20");
		
		JRadioButton acc40 = new JRadioButton("40");
		acc40.setActionCommand("40");
		
		JRadioButton acc60 = new JRadioButton("60");
		acc60.setActionCommand("60");
	
		JRadioButton acc90 = new JRadioButton("90");
		acc90.setActionCommand("90");
		
		bGroup = new ButtonGroup();
		bGroup.add(acc20);
		bGroup.add(acc40);
		bGroup.add(acc60);
		bGroup.add(acc90);
		container.add(acc20);
		container.add(acc40);
		container.add(acc60);
		container.add(acc90);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("sonic")) {
            System.out.println("Selected accuracy: " + bGroup.getSelection().getActionCommand());
            sendSelectedCommand(1);
            frame.dispose();
        }
        
        if (e.getActionCommand().equals("poker")) {
            System.out.println("Selected accuracy: " + bGroup.getSelection().getActionCommand());
            sendSelectedCommand(2);
            frame.dispose();
        }

		
	}
	
	public void sendSelectedCommand(int cmd) {
        connection.sendCommand(cmd);
        connection.sendCommand(Integer.parseInt(bGroup.getSelection().getActionCommand()));
        angle.setAccuracy(Integer.parseInt(bGroup.getSelection().getActionCommand()));
        connection.flushOutput();
	}
	

}

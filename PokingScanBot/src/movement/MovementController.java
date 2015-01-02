package movement;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import logging.DistanceLogger;
import logging.PcConnection;
import sensors.Toucher;

public class MovementController {

	private PokerMotor poker;
	private NXTRegulatedMotor table;
	private DistanceLogger logger;
	private UltrasonicSensor sonic;
	private PcConnection connection;
	private int fullTableRotation = 20000;

	public MovementController(PokerMotor p, NXTRegulatedMotor tt,
			DistanceLogger log, UltrasonicSensor s, PcConnection c) {
		poker = p;
		table = tt;
		logger = log;
		sonic = s;
		connection = c;
	}

	public void startPoking() {

		for (int angle = 0; angle < 20000; angle = angle + fullTableRotation
				/ 20) {
			int distance = poker.measure();
			System.out.println(distance);
			table.rotate(fullTableRotation / 20);
			connection.sendData(distance);
		}
		connection.close();
	}

	public void startOptical() {
		for (int i = 0; i < 20; i++) { // Sensor always gives an incorrect first
										// measurement unless I do this
			sonic.getDistance();
		}

		for (int angle = 0; angle < 20000; angle = angle + fullTableRotation
				/ 20) {
			int distance = sonic.getDistance();
			table.rotate(fullTableRotation / 20);
			System.out.println(distance);
			connection.sendData(distance);
		}
		
	}
}

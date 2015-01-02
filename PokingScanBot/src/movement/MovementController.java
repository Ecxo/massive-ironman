package movement;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.addon.OpticalDistanceSensor;
import logging.DistanceLogger;
import logging.PcConnection;
import sensors.Toucher;

public class MovementController {

	private PokerMotor poker;
	private NXTRegulatedMotor table;
	private DistanceLogger logger;
	private OpticalDistanceSensor optical;
	private PcConnection connection;
	private int fullTableRotation = 20000;

	public MovementController(PokerMotor p, NXTRegulatedMotor tt,
			DistanceLogger log, OpticalDistanceSensor op, PcConnection c) {
		poker = p;
		table = tt;
		logger = log;
		optical = op;
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
			optical.getDistance();
		}

		for (int angle = 0; angle < 20000; angle = angle + fullTableRotation
				/ 20) {
			int distance = optical.getDistance();
			table.rotate(fullTableRotation / 20);
			System.out.println(distance);
			connection.sendData(distance);
		}
		
	}
}

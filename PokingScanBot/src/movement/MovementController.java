package movement;

import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.UltrasonicSensor;
import logging.DistanceLogger;
import logging.PcConnection;

/**
 * Controls the measuring of item on the turn table.
 * @author petri
 *
 */

public class MovementController {

	private PokerMotor poker;
	private NXTRegulatedMotor table;
	private DistanceLogger logger;
	private UltrasonicSensor sonic;
	private PcConnection connection;
	private int fullTableRotation = 20000; // 20000 is approximately one full rotation of the turn table.
	private int accuracy; //How many times the object on the turntable is measured during a full revolution.

	public MovementController(PokerMotor p, NXTRegulatedMotor tt,
			DistanceLogger log, UltrasonicSensor s, PcConnection c, int acc) {
		poker = p;
		table = tt;
		logger = log;
		sonic = s;
		connection = c;
		accuracy = acc;
	}
	
	/**
	 * Get measurements using the touch sensor and send the over the the BT connection.
	 */

	public void startPoking() {

		
		for (int angle = 0; angle < 20000; angle = angle + fullTableRotation
				/ accuracy) {
			int distance = poker.measure();
			System.out.println(distance);
			table.rotate(fullTableRotation / accuracy);
			connection.sendData(distance);
		}
		connection.close();
	}
	
	/**
	 * Get measurements using the sonic sensor.
	 */

	public void startSonic() {
		for (int i = 0; i < 20; i++) { // For calibration. Sensor often gives an incorrect first
										// measurement unless I do this
			sonic.getDistance();
		}
		for (int angle = 0; angle < 20000; angle = angle + fullTableRotation
				/ accuracy) {
			int distance = sonic.getDistance()*10;
			table.rotate(fullTableRotation / accuracy);
			System.out.println(distance);
			connection.sendData(distance);
		}
		
	}
}

package movement;

import sensors.Toucher;

import lejos.nxt.NXTRegulatedMotor;

/**
 * Controls the motor moving the touch sensor.
 * @author petri
 *
 */

public class PokerMotor {

	private NXTRegulatedMotor poker;
	private Toucher toucher;

	public PokerMotor(NXTRegulatedMotor a, Toucher t) {
		this.poker = a;
		this.poker.setSpeed(130);
		this.toucher = t;

	}
	
	/**
	 * Drives the motor forward, when touch sensor is pressed, checks the tacho count and drive to the opposite direction the same amount of revolutions
	 * @return
	 */

	public int measure() {
		poker.resetTachoCount();
		System.out.println(poker.getTachoCount());
		while (toucher.isPressed() == false) {
			poker.backward();
		}
		poker.stop();
		int distance = poker.getTachoCount();
		poker.rotate(0 - distance);
		return Math.abs(distance);

	}

}

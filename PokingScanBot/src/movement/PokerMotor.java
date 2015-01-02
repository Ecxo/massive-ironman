package movement;

import sensors.Toucher;
import lejos.nxt.NXTRegulatedMotor;

public class PokerMotor {

	private NXTRegulatedMotor poker;
	private Toucher toucher;

	public PokerMotor(NXTRegulatedMotor a, Toucher t) {
		this.poker = a;
		this.poker.setSpeed(130);
		this.toucher = t;

	}

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

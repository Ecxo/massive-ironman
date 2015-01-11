package movement;

import lejos.nxt.NXTRegulatedMotor;
/**
 * Controls the turn table engine.
 * @author petri
 *
 */
public class TurnTableMotor {

	private NXTRegulatedMotor turnTable;

	public TurnTableMotor(NXTRegulatedMotor b) {
		this.turnTable = b;
	}

	public void rotate(int degrees) {
		turnTable.rotate(degrees);
	}

}

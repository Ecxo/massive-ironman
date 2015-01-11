package sensors;

import lejos.nxt.TouchSensor;

/**
 * Checks the touch sensor. This class was created because using the sensor directly caused some problems that I could not figure out.
 * @author petri
 *
 */

public class Toucher {
	
	private TouchSensor sensor;
	
	public Toucher(TouchSensor t) {
		this.sensor = t;
		
	}

	public boolean isPressed() {
		if (sensor.isPressed()) {
			return true;
		}
		return false;
	}


}

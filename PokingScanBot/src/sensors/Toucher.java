package sensors;

import lejos.nxt.TouchSensor;



public class Toucher {
	
	private TouchSensor sensor;
	
	public Toucher(TouchSensor t) {
		this.sensor = t;
		
	}

	public boolean isTouching() {
		if (sensor.isPressed()) {
			return true;
		}
		return false;
	}


}

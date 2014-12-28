package movement;

import sensors.Toucher;
import lejos.nxt.ADSensorPort;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;


public class MovementController {
	
	private PokerMotor poker;
	private TurnTableMotor table;
	private HeightMotor height;
	private Toucher touch;
	
	
	public MovementController() {
		touch = new Toucher(new TouchSensor(SensorPort.S1));
		poker = new PokerMotor(Motor.A, touch);
		table = new TurnTableMotor(Motor.B);
		
	}
	
	public void start() {
		int distance = poker.measure();
		table.rotate(1000);
		System.out.println(distance);
		
	}
	

}

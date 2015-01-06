package main;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import logging.DistanceLogger;
import logging.PcConnection;
import movement.MovementController;
import movement.PokerMotor;
import sensors.Toucher;

public class Main {

	public static void main(String[] args) {

		System.out.println("Waiting for BT");
		PcConnection connection = new PcConnection();
		System.out.println("Waiting for command");
		int command = connection.readInputStream();
		
		while (command != 1 && command != 2) {
			command = connection.readInputStream();
			System.out.println(command);
		}
		System.out.println("Command: " + command);

		Toucher touch = new Toucher(new TouchSensor(SensorPort.S1));
		PokerMotor poker = new PokerMotor(Motor.A, touch);
		NXTRegulatedMotor table = Motor.B;
		DistanceLogger log = new DistanceLogger();
		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S2);
		int accuracy = connection.readInputStream();
		System.out.println("Accuracy: " + accuracy);
		
		MovementController move = new MovementController(poker, table, log,
				sonic, connection, accuracy);
		
		if (command == 1) {
		move.startSonic();
		}
		if (command == 2) {
			move.startPoking();
		}
		
		System.out.println("Finished, press key to stop");
		Button.waitForAnyPress();

	}

}

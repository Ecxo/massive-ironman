package main;

import java.io.DataOutputStream;

import sensors.Toucher;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.addon.OpticalDistanceSensor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import logging.DistanceLogger;
import logging.PcConnection;
import movement.MovementController;
import movement.PokerMotor;
import movement.TurnTableMotor;

public class Main {

	public static void main(String[] args) {

		System.out.println("Waiting for BT");
		PcConnection connection = new PcConnection();
		System.out.println("Press to start");
		Button.waitForAnyPress(10000);

		Toucher touch = new Toucher(new TouchSensor(SensorPort.S1));
		PokerMotor poker = new PokerMotor(Motor.A, touch);
		NXTRegulatedMotor table = Motor.B;
		DistanceLogger log = new DistanceLogger();
		OpticalDistanceSensor optical = new OpticalDistanceSensor(SensorPort.S2);

		MovementController move = new MovementController(poker, table, log,
				optical, connection);
		move.startOptical();
		
		System.out.println("Finished, press key to stop");
		Button.waitForAnyPress();

	}

}

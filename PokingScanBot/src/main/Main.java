package main;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import movement.MovementController;
import movement.TurnTableMotor;

public class Main {

	public static void main(String[] args) {

		System.out.println("Hello, press the big key to start!");
		Button.waitForAnyPress();
		
		
		
		MovementController move = new MovementController();
		move.start();

		move.start();
		move.start();

		move.start();
		move.start();

		move.start();
		Button.waitForAnyPress();



	}

}

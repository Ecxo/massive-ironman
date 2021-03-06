package logging;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.Sound;

/**
 * Class for saving distance measurements to file. Currently unused due to the robot using bluetooth connection to communicate with the PC.
 * 
 * @author petri
 *
 */

public class DistanceLogger {

	private DataOutputStream out;
	private File destFile;

	public DistanceLogger() {

		try {
			destFile = new File("result");
			destFile.createNewFile();
			out = new DataOutputStream(new FileOutputStream(destFile));

		} catch (IOException e) {
			System.out.println("Error in file generation!");
			Sound.twoBeeps();
			Button.waitForAnyEvent(10000);
			e.printStackTrace();
		}

	}
	/**
	 * attemps to insert values into the file
	 * @param distance distance data to be saved
	 * @param angle angle of the turntable
	 */

	public void insert(int distance, int angle) {
		try {
			out.writeDouble(distance);
		} catch (IOException e) {
			System.out.println("Error in file writing!");
			Sound.twoBeeps();
			Button.waitForAnyEvent(10000);
			e.printStackTrace();
		}
	}
	
	/**
	 * closes the stream
	 */

	public void close() {
		try {
			out.close();
			Sound.buzz();
		} catch (IOException e) {
			System.out.println("Error in closing file!");
			Sound.twoBeeps();
			Button.waitForAnyEvent(10000);
			e.printStackTrace();
		}
	}
}

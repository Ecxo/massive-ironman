package connections;

import java.io.*;

/**
 * Bluetooth connection for sending and receiving data from the robot.
 */

import lejos.pc.comm.*;

public class BTConnection {

	private NXTComm BTConnection;
	private NXTInfo info;
	private InputStream inputstream;
	private DataInputStream input;
	private OutputStream outputstream;
	private DataOutputStream output;
	
	/**
	 * Create the connection.
	 * @param NXTname bluetooth address of the robot.
	 */

	public BTConnection(String NXTname) {
		try {
			BTConnection = NXTCommFactory
					.createNXTComm(NXTCommFactory.BLUETOOTH);
		} catch (NXTCommException e) {
			System.out.println("Failed creating NXTCommFactory");
			e.printStackTrace();
		}
		info = new NXTInfo(NXTCommFactory.BLUETOOTH, "nxt-auto",
				NXTname);
	}
	
	/**
	 * Opens connection for input and output.
	 */

	public void open() {
		try {
			BTConnection.open(this.info);
			inputstream = BTConnection.getInputStream();
			input = new DataInputStream(inputstream);
			outputstream = BTConnection.getOutputStream();
			output = new DataOutputStream(outputstream);
		} catch (NXTCommException e) {
			System.out.println("Failed opening connection");
			e.printStackTrace();
		}
	}
	
	/**
	 * Attempts to read an int from the robot. Returns -1 if reading fails, for example if the robot is turned off.
	 * @return
	 */

	public int read() {

		int value = -1;
			try {
				value = input.readInt();
			} catch (IOException e) {
				System.out.println("Error reading");
			}
		return value;

	}
	/**
	 * Sends a command to the robot.
	 * @param cmd command, used values are 1 = sonic, 2 = touch sensor.
	 */
	public void sendCommand(int cmd) {
		try {
			output.writeInt(cmd);
			System.out.println("Sent command: " + cmd);
		} catch (IOException e) {
			System.out.println("Failed sending command");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * saves the command so it can be sent.
	 */
	
	public void flushOutput() {
		try {
			output.flush();
		} catch (IOException e) {
			System.out.println("Failed flushing output");
			e.printStackTrace();
		}
	}
}
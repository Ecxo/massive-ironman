package logging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class PcConnection {

	private NXTConnection connection;
	private DataOutputStream output;
	private DataInputStream input;

	public PcConnection() {
		connection = Bluetooth.waitForConnection();

		System.out.println("Connected");
		output = connection.openDataOutputStream();
		input = connection.openDataInputStream();
	}

	public void sendData(int distance) {
		try {
			output.writeInt(distance);
		} catch (IOException e) {
			System.out.println("Error sending distance to pc");
			Button.waitForAnyPress(10000);
			e.printStackTrace();
		}
		flush();
	}

	public void flush() {
		try {
			output.flush();
		} catch (IOException e) {
			System.out.println("Error flushing");
			Button.waitForAnyPress(10000);
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			output.close();
			connection.close();
		} catch (IOException e) {
			System.out.println("Error closing connection");
			e.printStackTrace();
		}

	}

	public int readInputStream() {
		try {
			int cmd = input.readInt();
			return cmd;
		} catch (IOException e) {
			System.out.println("Error reading input");
			e.printStackTrace();
		}
		return 0;

	}

}

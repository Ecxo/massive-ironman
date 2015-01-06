package connections;

import java.io.*;

import lejos.pc.comm.*;

public class BTConnection {

	private NXTComm BTConnection;
	private NXTInfo info;
	private InputStream inputstream;
	private DataInputStream input;
	private OutputStream outputstream;
	private DataOutputStream output;

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

	public int read() {

		int value = -1;
			try {
				value = input.readInt();
			} catch (IOException e) {
				System.out.println("Error reading");
			}
		return value;

	}
	
	public void sendCommand(int cmd) {
		try {
			output.writeInt(cmd);
			System.out.println("Sent command: " + cmd);
		} catch (IOException e) {
			System.out.println("Failed sending command");
			e.printStackTrace();
		}
		
	}
	
	public void flushOutput() {
		try {
			output.flush();
		} catch (IOException e) {
			System.out.println("Failed flushing output");
			e.printStackTrace();
		}
	}
}
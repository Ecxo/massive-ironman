package connections;

import java.io.*;

import lejos.pc.comm.*;

public class BTConnection {

	private NXTComm BTConnection;
	private NXTInfo info;
	private InputStream inputstream;
	private DataInputStream input;

	public BTConnection() {
		try {
			BTConnection = NXTCommFactory
					.createNXTComm(NXTCommFactory.BLUETOOTH);
		} catch (NXTCommException e) {
			System.out.println("Failed creating NXTCommFactory");
			e.printStackTrace();
		}
		info = new NXTInfo(NXTCommFactory.BLUETOOTH, "nxt-auto",
				"00:16:53:0a:90:b0");
	}

	public void open() {
		try {
			this.BTConnection.open(this.info);
			inputstream = BTConnection.getInputStream();
			input = new DataInputStream(inputstream);
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
				e.printStackTrace();
			}
		return value;

	}
}
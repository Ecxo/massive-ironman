package results;

import java.util.ArrayList;

import connections.BTConnection;

public class ResultList {
	
	private BTConnection connection;
	private ArrayList<Integer> results;


	public ResultList(BTConnection c) {
		connection = c;
		results = new ArrayList<Integer>();
		
	}
	
	public void fetchData() {
		int distance = connection.read();
		System.out.println(distance);
		results.add(distance);
	}

	public ArrayList<Integer> getArrayList() {		
		return results;
	}
	
	
	
}

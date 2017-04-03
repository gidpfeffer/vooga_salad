package util;

public class IDGenerator {
	
	private int currentID;
	
	public IDGenerator() {
		currentID = 0;
	}
	
	public int getNewID() {
		currentID++;
		return currentID;
	}

}

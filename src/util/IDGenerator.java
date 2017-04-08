package util;

import java.util.function.Supplier;

public class IDGenerator {
	
	private int currentID;
	
	public IDGenerator() {
		currentID = 0;
	}
	
	public Supplier<Integer> getNextID() {
		return () -> getNewID();
	}
	
	private int getNewID() {
		return currentID++;
	}

}

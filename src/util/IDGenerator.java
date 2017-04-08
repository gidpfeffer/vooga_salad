package util;

import java.util.function.Supplier;

public class IDGenerator {
	
	private static int currentID = 0;
	
	public static Supplier<Integer> getNextID() {
		return () -> getNewID();
	}
	
	public static int getNewID() {
		return currentID++;
	}

}

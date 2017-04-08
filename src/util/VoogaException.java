package util;

public class VoogaException extends Exception {
	
	public static final String ILLEGAL_UPDATE = "Illegal update";
	public static final String INVALID_ACTORTYPE = "Invalid actor type";
	public static final String INVALID_LOCATION = "Illegal location";
	
	private static final long serialVersionUID = 1L;
	
	public VoogaException(String message) {
		super(message);
	}
}

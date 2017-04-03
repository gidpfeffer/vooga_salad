package util;

public class VoogaException extends Exception {
	
	public static final String ILLEGAL_UPGRADE = "Illegal upgrade";
	
	private static final long serialVersionUID = 1L;
	
	public VoogaException(String message) {
		super(message);
	}
}

package util.inputhandler.utils;

public class InputHandleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InputHandleException(String message) {
        super(message);
    }
	
    public InputHandleException(String message, Object ... values) {
        super(String.format(message, values));
    }

    public InputHandleException(Throwable cause) {
        super(cause);
    }
}

/**
 * 
 */
package socialcenter;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @author harirajan
 *
 */
public class Client extends NetworkConnection {
	
	private String ip;
	private int port;

	/**
	 * @param onReceiveCallback
	 */
	public Client(String ip, int port, Consumer<Serializable> onReceiveCallback) {
		super(onReceiveCallback);
		this.ip = ip;
		this.port = port;
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see main.NetworkConnection#isServer()
	 */
	@Override
	protected boolean isServer() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see main.NetworkConnection#getIP()
	 */
	@Override
	protected String getIP() {
		// TODO Auto-generated method stub
		return ip;
	}

	/* (non-Javadoc)
	 * @see main.NetworkConnection#getPort()
	 */
	@Override
	protected int getPort() {
		// TODO Auto-generated method stub
		return port;
	}

}

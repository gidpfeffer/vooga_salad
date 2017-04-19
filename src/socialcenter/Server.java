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
public class Server extends NetworkConnection {

	private int port;
	
	/**
	 * @param onReceiveCallback
	 */
	public Server(int port, Consumer<Serializable> onReceiveCallback) {
		super(onReceiveCallback);
		this.port = port;
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see main.NetworkConnection#isServer()
	 */
	@Override
	protected boolean isServer() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see main.NetworkConnection#getIP()
	 */
	@Override
	protected String getIP() {
		// TODO Auto-generated method stub
		return null;
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

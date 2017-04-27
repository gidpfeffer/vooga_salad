package ui.player.listener;

public class SceneListen {

	ListenQueue myQueue;
	
	public SceneListen() {
		myQueue = new ListenQueue();
	}
	
	public ListenQueue pollQueue() {
		ListenQueue retQueue = myQueue.clone();
		myQueue = new ListenQueue();
		return retQueue;
	}
}

package ui.player.listener;

import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Modular class that is takes in a scene and reports an ListenQueue to the end
 * client when polled.
 * 
 * @author Moses Wayne
 *
 */
public class SceneListen implements IListen{

	private ListenQueue myQueue;
	private Grid2D myMouse;

	public SceneListen(Scene listen) {
		myMouse = new Coordinates(0, 0);
		generateNewQueue();
		listen.addEventHandler(KeyEvent.KEY_PRESSED, handleKeys());
		listen.addEventHandler(MouseEvent.MOUSE_MOVED, pollMouse(listen));
	}

	private EventHandler<KeyEvent> handleKeys() {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				myQueue.addEvent(event.getCode());
			}
		};
	}

	private EventHandler<MouseEvent> pollMouse(Scene scene) {
		return new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				myMouse = new Coordinates(event.getSceneX() / scene.getWidth(), event.getSceneY() / scene.getHeight());
			}
		};
	}

	private void generateNewQueue() {
		myQueue = new ListenQueue(myMouse.getX(), myMouse.getY());
	}
	
	public ListenQueue getQueue() {
		return myQueue;
	}

	public void pollQueue() {
		//ListenQueue retQueue = myQueue.clone();
		generateNewQueue();
		//return retQueue;
	}
}

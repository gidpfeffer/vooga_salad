package util.inputhandler;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Main class for the InputHandler utility. This class returns a UI Node element
 * that can be added to any JavaFX layout. When the showMenu() method is called,
 * the utility starts running immediately. The Node returned contains a menu in
 * the form of a VBox that contains the button selection. No formatting is added
 * to the utility purposefully so that it can adopt the styling of whichever
 * program is running it. Uses a timeline to run the querying of input from the
 * input devices. Two constructors are provided, one that allows for the control
 * mapping of keys that the user would like to map, and the other is empty,
 * defaulting the program to files from the resources package
 * 
 * @author Moses Wayne
 *
 */
public class IOUtil {

	private MultiInputHandler myHandler;

	public IOUtil(String controls) {
		myHandler = new MultiInputHandler(controls);
	}

	public IOUtil() {
		myHandler = new MultiInputHandler();
	}

	/**
	 * private method that starts the program timeline, querying the device for
	 * input
	 */
	private void run() {
		KeyFrame frame = new KeyFrame(Duration.millis(10), d -> step());
		Timeline listener = new Timeline();
		listener.setCycleCount(Timeline.INDEFINITE);
		listener.getKeyFrames().add(frame);
		listener.play();
	}

	/**
	 * step function for the timeline of the utility. This polls the device for
	 * data
	 */
	private void step() {
		myHandler.poll();
	}

	/**
	 * Main API of this InputHandler utility. This method returns the UI menu in
	 * the form of a Node that can be added to any JavaFX layout. When this
	 * method is called, the utility starts running immediately.
	 * 
	 * @return Node of the selection menu for devices that can be added to any
	 *         JavaFX layout.
	 */
	public Node showMenu() {
		run();
		return myHandler.getMenu();
	}

}

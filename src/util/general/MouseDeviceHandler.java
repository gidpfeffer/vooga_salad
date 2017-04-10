package util.general;

import java.awt.AWTException;
import java.awt.Robot;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MouseDeviceHandler implements DeviceHandler {
	
	private Robot myRobot;
	private Scene listenScene;

	public MouseDeviceHandler(Scene scene) {
		listenScene = scene;
		try {
			myRobot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setUp();
	}
	
	@Override
	public Robot getInput() {
		return myRobot;
	}
	
	private void setUp() {
		spaceBar();
	}
	
	private void spaceBar() {
		listenScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ESCAPE) {
					myRobot.mouseMove(30, 30);
				}
			}
			
		});
	}

}

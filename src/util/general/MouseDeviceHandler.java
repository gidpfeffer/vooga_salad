package util.general;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
		listenScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
				int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
				if(event.getCode()==KeyCode.UP) {
					myRobot.mouseMove(x, y-5);
				}
			}
			
		});
	}
	
	private void spaceBar() {
		listenScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ESCAPE) {
					//myRobot.mouseMove(30, 30);
					myRobot.keyPress('C');
				}
			}
			
		});
	}

}

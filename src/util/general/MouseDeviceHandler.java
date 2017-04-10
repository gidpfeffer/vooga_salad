package util.general;

import java.awt.AWTException;
import java.awt.Robot;

import javafx.scene.Scene;

public class MouseDeviceHandler implements DeviceHandler {
	
	private Robot myRobot;

	public MouseDeviceHandler(Scene scene) {
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
		
	}

}

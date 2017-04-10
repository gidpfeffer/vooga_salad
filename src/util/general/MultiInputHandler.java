package util.general;

import java.awt.Robot;
import java.lang.reflect.Constructor;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MultiInputHandler {
	
	private final String classTag = "DeviceHandler";
	
	private DeviceHandler myDevice;
	private Robot myInputRobot;
	private ResourceBundle myResource;
	private VBox controlMenu;

	public MultiInputHandler(String resourceFile) {
		myResource = ResourceBundle.getBundle(resourceFile);
		controlMenu = new VBox();
		setUpMenu();
	}
	
	private void getDevice(String device) {
		DeviceHandler handler = null;
		try {
			Class<?> deviceClass = Class.forName(device+classTag);
			Constructor<?> deviceConstructor = deviceClass.getConstructor();
			handler = (DeviceHandler) deviceConstructor.newInstance();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		myDevice = handler;
	}
	
	private void setUpMenu() {
		Enumeration<String> devices = myResource.getKeys();
		while(devices.hasMoreElements()) {
			String thisDevice = myResource.getString(devices.nextElement());
			Button deviceButton = new Button(thisDevice);
			deviceButton.setOnAction(e -> {
				getDevice(thisDevice);
				myInputRobot = myDevice.getInput();
				myInputRobot.setAutoWaitForIdle(true);
			});
			controlMenu.getChildren().add(deviceButton);
		}
	}
	
	public Node getMenu() {
		return controlMenu;
	}
}

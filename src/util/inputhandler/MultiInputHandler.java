package util.inputhandler;

import java.awt.Robot;
import java.util.Enumeration;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import util.inputhandler.devices.DeviceHandler;
import util.inputhandler.utils.InputHandleException;
import util.reflection.Reflection;

/**
 * 
 * 
 * @author Moses Wayne
 *
 */
public class MultiInputHandler {
	
	private final String classTag = "DeviceHandler";
	private final String dirTag = "voogasalad.util.inputhandler.devices.";
	private final String fileTag = "voogasalad.util.inputhandler.resources.";
	private static final String defaultDevices = "voogasalad.util.inputhandler.resources.devices";
	private static final String defaultControls = "voogasalad.util.inputhandler.resources.controls";
	
	private String controls;
	private Optional<DeviceHandler> myDevice;
	private Robot myInputRobot;
	private ResourceBundle myResource;
	private VBox controlMenu;

	public MultiInputHandler(String defControls) {
		myResource = ResourceBundle.getBundle(defaultDevices);
		controls =  defControls;
		controlMenu = new VBox();
		myDevice = Optional.empty();
		setUpMenu();
	}
	
	public MultiInputHandler() {
		this(defaultControls);
	}

	private void getDevice(String device) {
		try {
			myDevice = Optional.of((DeviceHandler) Reflection.createInstance(dirTag+device+classTag,fileTag+device,controls));
		} catch(InputHandleException e) {
			throw new InputHandleException(device);
		}
	}
	
	private void setUpMenu() {
		Enumeration<String> devices = myResource.getKeys();
		while(devices.hasMoreElements()) {
			String thisDevice = myResource.getString(devices.nextElement());
			Button deviceButton = new Button(thisDevice);
			deviceButton.setOnAction(e -> {
				getDevice(thisDevice);
				myInputRobot = myDevice.get().getInput();
				myInputRobot.setAutoWaitForIdle(true);
			});
			controlMenu.getChildren().add(deviceButton);
		}
	}
	
	public Node getMenu() {
		return controlMenu;
	}
	
	public void poll() {
		myDevice.ifPresent((device) -> device.execute());
	}
}

package util.inputhandler.devices;

import java.awt.MouseInfo;
import java.awt.Robot;
import java.util.Enumeration;
import java.util.ResourceBundle;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Component.Identifier;
import util.inputhandler.controls.ControlMapping;
import util.inputhandler.utils.ControllerUtil;
import util.reflection.Reflection;

public class GamepadDeviceHandler extends DeviceHandler {

	private static final Controller.Type type = Controller.Type.GAMEPAD;
	private Integer sensitivity = 5;
	
	public GamepadDeviceHandler(String fileName, String defaultControls) {
		super(fileName,defaultControls);
	}
	
	@Override
	protected void setUp(ResourceBundle file, ControlMapping map) {
		Enumeration<String> commands = file.getKeys();
		Identifier genericKey = Component.Identifier.Button.UNKNOWN;
		while(commands.hasMoreElements()) {
			String listenKey = commands.nextElement();
			String mapping = file.getString(listenKey);
			addNewMapping((Identifier) Reflection.getFieldValue(genericKey, listenKey), map.getAction(mapping));
		}
	}
	
	private double getX() {
		if (Math.abs(pollMove(Component.Identifier.Axis.X))<(2.0/sensitivity)) {
			return 0;
		}
		return pollMove(Component.Identifier.Axis.X)*sensitivity;
	}
	
	private double getY() {
		if (Math.abs(pollMove(Component.Identifier.Axis.Y))<(2.0/sensitivity)) {
			return 0;
		}
		return pollMove(Component.Identifier.Axis.Y)*sensitivity;
	}
	
	@Override
	protected Controller setController() {
		return ControllerUtil.get(ControllerEnvironment.getDefaultEnvironment(), type);
	}

	@Override
	protected void handleMove(ControlMapping map, Robot myRobot) {
		map.moveAction().accept(myRobot, (int) (MouseInfo.getPointerInfo().getLocation().getX()+getX()), (int) (MouseInfo.getPointerInfo().getLocation().getY()+getY()));
	}

}

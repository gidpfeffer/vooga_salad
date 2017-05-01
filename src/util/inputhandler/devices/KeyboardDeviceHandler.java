package util.inputhandler.devices;

import java.awt.Robot;
import java.util.Enumeration;
import java.util.ResourceBundle;

import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import util.inputhandler.controls.ControlMapping;
import util.inputhandler.utils.ControllerUtil;
import util.reflection.Reflection;

public class KeyboardDeviceHandler extends DeviceHandler {
	
	private static final Controller.Type type = Controller.Type.KEYBOARD;

	public KeyboardDeviceHandler(String fileName, String defaultControls) {
		super(fileName, defaultControls);
	}
	
	@Override
	protected void setUp(ResourceBundle file, ControlMapping map) {
		Enumeration<String> commands = file.getKeys();
		Identifier genericKey = Component.Identifier.Key.UNLABELED;
		while(commands.hasMoreElements()) {
			String listenKey = commands.nextElement();
			String mapping = file.getString(listenKey);
			addNewMapping((Identifier) Reflection.getFieldValue(genericKey, listenKey), map.getAction(mapping));
		}
	}

	@Override
	protected Controller setController() {
		return ControllerUtil.get(ControllerEnvironment.getDefaultEnvironment(), type);	
	}

	@Override
	protected void handleMove(ControlMapping map, Robot myRobot) {
		// TODO Auto-generated method stub
		
	}

}

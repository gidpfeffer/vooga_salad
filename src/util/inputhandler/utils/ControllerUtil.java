package util.inputhandler.utils;

import com.sun.glass.events.KeyEvent;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import util.reflection.Reflection;
import util.reflection.ReflectionException;

public class ControllerUtil {
	
	private static final String virtualTag = "VK_";

	public static Controller get(ControllerEnvironment env, Controller.Type name) {
		for(Controller c:env.getControllers()) {
			if(c.getType().equals(name)) {
				return c;
			}
		}
		throw new InputHandleException("The requested input device "+ name.toString() +" was not found");
	}
	
	public static int convertKeyCode(String key) {
		try {
			return (Integer) Reflection.getFieldValue(KeyEvent.class.newInstance(), virtualTag+key.toUpperCase());
		} catch (ReflectionException | InstantiationException | IllegalAccessException e) {
			throw new InputHandleException(e);
		}
	}
}

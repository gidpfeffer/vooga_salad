package util.inputhandler.devices;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.Component.Identifier;
import util.inputhandler.controls.ControlMapping;
import util.inputhandler.utils.InputHandleException;

public abstract class DeviceHandler {

	private ResourceBundle defaultFile;
	private Robot myRobot;
	private ControlMapping myControlMap;
	private Controller myGamePad;
	private Map<Identifier,Consumer<Robot>> myControls;
	
	public DeviceHandler(String fileName, String defaultControls) {
		defaultFile = ResourceBundle.getBundle(fileName);
		myControlMap = new ControlMapping(defaultControls);
		try {
			myRobot = new Robot();
		} catch (AWTException e) {
			throw new InputHandleException(e);
		}
		myGamePad = setController();
		myControls = new HashMap<>();
		setUp(defaultFile,myControlMap);
	}
	
	public Robot getInput() {
		return myRobot;
	}
	
	public void execute() {
		myGamePad.poll();
		Event ioEvent = new Event();
		while(myGamePad.getEventQueue().getNextEvent(ioEvent)) {
			if(ioEvent.getValue()!=0 && myControls.containsKey(ioEvent.getComponent().getIdentifier())) {
				System.out.println(ioEvent.toString());
				myControls.get(ioEvent.getComponent().getIdentifier()).accept(myRobot);
			}
		}
		handleMove(myControlMap, myRobot);
	}
	
	protected double pollMove(Identifier axis) {
		return myGamePad.getComponent(axis).getPollData();
	}
	
	protected abstract void setUp(ResourceBundle commands, ControlMapping map);
	
	protected void addNewMapping(Identifier key, Consumer<Robot> action) {
		myControls.put(key, action);
	}
	
	protected abstract Controller setController();
	
	protected abstract void handleMove(ControlMapping map, Robot myRobot);
	
}

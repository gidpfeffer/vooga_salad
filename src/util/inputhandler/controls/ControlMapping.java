package util.inputhandler.controls;

import java.awt.Robot;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import util.inputhandler.controls.GameControls.TriConsumer;
import util.reflection.Reflection;

public class ControlMapping {
	
	private final String methodTag = "virtual";
	
	private GameControls myControls;
	private Map<String,Consumer<Robot>> controlMap;
	private ResourceBundle myDefaultControls;

	public ControlMapping(String resource) {
		controlMap = new HashMap<>();
		myControls = new GameControls();
		myDefaultControls = ResourceBundle.getBundle(resource);
		generateMap();
	}
	
	@SuppressWarnings("unchecked")
	private void generateMap() {
		Enumeration<String> controls = myDefaultControls.getKeys();
		while(controls.hasMoreElements()) {
			String controlKey = myDefaultControls.getString(controls.nextElement());
			System.out.println(methodTag+controlKey);
			controlMap.put(controlKey, (Consumer<Robot>) Reflection.callMethod(myControls, methodTag+controlKey));
		}
	}
	
	public Consumer<Robot> getAction(String control) {
		return controlMap.get(control);
	}
	
	public TriConsumer<Robot,Integer,Integer> moveAction() {
		return myControls.virtualMove();
	}
}

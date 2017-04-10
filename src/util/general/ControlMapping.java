package util.general;

import java.awt.Robot;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ControlMapping {
	
	private final String methodTag = "virtual";
	
	private GameControls myControls;
	private Map<String,Consumer<Robot>> controlMap;
	private ResourceBundle myDefaultControls;

	public ControlMapping(String resource) {
		myControls = new GameControls();
		myDefaultControls = ResourceBundle.getBundle(resource);
		generateMap();
	}
	
	@SuppressWarnings("unchecked")
	private void generateMap() {
		Enumeration<String> controls = myDefaultControls.getKeys();
		while(controls.hasMoreElements()) {
			String controlKey = myDefaultControls.getString(controls.nextElement());
			Method controlConsumer;
			try {
				controlConsumer = myControls.getClass().getDeclaredMethod(methodTag+controlKey, new Class[] {});
				controlMap.put(controlKey, (Consumer<Robot>) controlConsumer.invoke(null, new Object[] {}));
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	public Consumer<Robot> getAction(String control) {
		return controlMap.get(control);
	}
}

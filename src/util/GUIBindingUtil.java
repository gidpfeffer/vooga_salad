package util;

import javafx.scene.Node;

public class GUIBindingUtil {
	
	public static void bindVisisble(Node thisNode, Object nullCheck) {
		thisNode.visibleProperty().bind(thisNode.managedProperty());
		thisNode.setManaged(nullCheck!=null);
	}

}

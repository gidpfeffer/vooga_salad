package util.general;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class GameControls {

	public Consumer<Robot> virtualUp() {
		return (thisRobot) -> thisRobot.keyPress(KeyEvent.VK_UP);
	}
	
	public Consumer<Robot> virtualDown() {
		return (thisRobot) -> thisRobot.keyPress(KeyEvent.VK_DOWN);
	}
	
	public Consumer<Robot> virtualLeft() {
		return (thisRobot) -> thisRobot.keyPress(KeyEvent.VK_LEFT);
	}
	
	public Consumer<Robot> virtualRight() {
		return (thisRobot) -> thisRobot.keyPress(KeyEvent.VK_RIGHT);
	}
}

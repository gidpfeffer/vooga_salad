package util.inputhandler.controls;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.function.Consumer;

public class GameControls {
	
	public Consumer<Robot> virtualPress(int keyCode) {
		return (thisRobot) -> thisRobot.keyPress(keyCode);
	}

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
	
	public Consumer<Robot> virtualLeftClick() {
		return (thisRobot) -> {
			thisRobot.mousePress(InputEvent.BUTTON1_MASK);
			thisRobot.mouseRelease(InputEvent.BUTTON1_MASK);
		};
	}
	
	public Consumer<Robot> virtualRightClick() {
		return (thisRobot) -> {
			thisRobot.mousePress(InputEvent.BUTTON3_MASK);
			thisRobot.mouseRelease(InputEvent.BUTTON3_MASK);
		};
	}
	
	public TriConsumer<Robot, Integer, Integer> virtualMove() {
		return (thisRobot,x,y) -> {
			thisRobot.mouseMove(x, y);
		};
	}
	
	public interface TriConsumer<T1,T2,T3> {
		public void accept(T1 var1, T2 var2, T3 var3);
	}
	
}

package ui.player.listener;

import java.util.HashSet;
import java.util.Set;

import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.scene.input.KeyCode;

/**
 * Data structure class wrapping mouse position and key stroke history into 
 * @author Moses Wayne
 *
 */
public class ListenQueue {

	private Grid2D myPointer;
	private Set<KeyCode> myEvents;
	
	public ListenQueue(double xLoc, double yLoc) {
		myPointer = new Coordinates(xLoc,yLoc);
		myEvents = new HashSet<>();
	}
	
	public Grid2D getPointer() {
		return myPointer;
	}
	
	public void addEvent(KeyCode code) {
		myEvents.add(code);
	}
	
	public boolean queryKey(String key) {
		return myEvents.contains(KeyCode.getKeyCode(key));
	}
	
	@Override
	public ListenQueue clone() {
		return this.clone();
	}
}

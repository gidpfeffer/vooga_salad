package ui.player.listener;

import java.util.HashSet;
import java.util.Set;

import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;

/**
 * Data structure class wrapping mouse position and key stroke history into 
 * @author Moses Wayne
 *
 */
public class ListenEventQueue<E> {

	private Grid2D myPointer;
	private Set<E> myEvents;
	
	public ListenQueue(double xLoc, double yLoc) {
		myPointer = new Coordinates(xLoc,yLoc);
		myEvents = new HashSet<>();
	}
	
	public Grid2D getLocation() {
		return myPointer;
	}
	
	public void addEvent(E code) {
		myEvents.add(code);
	}
	
	public boolean queryKey(E key) {
		return myEvents.contains(key);
	}
	
	@Override
	public ListenQueue<E> clone() {
		return this.clone();
	}
}

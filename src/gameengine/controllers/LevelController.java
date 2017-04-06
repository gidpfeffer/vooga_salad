package gameengine.controllers;

import gameengine.grid.ActorGrid;

/**
 * Controls information about/behavior of a single level
 * 
 * @author maddiebriere
 */

public class LevelController {
	public int myLevel;
	public ActorGrid myMap;
	
	public LevelController(ActorGrid grid, int level) {
		myLevel = level;
		myMap = grid;
	}
	public int getMyLevel() {
		return myLevel;
	}
	public void setMyLevel(int myLevel) {
		this.myLevel = myLevel;
	}
	public ActorGrid getMyMap() {
		return myMap;
	}
	public void setMyMap(ActorGrid myMap) {
		this.myMap = myMap;
	}
	
	
}

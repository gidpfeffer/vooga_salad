package gameengine.controllers;

import gamedata.ActorData;
import gamedata.GameData;
import gamedata.LevelData;
import gamedata.PreferencesData;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;

/**
 * Controls information about/behavior of a single level
 * 
 * @author maddiebriere, sarahzhou
 * 
 */

public class LevelController {
	public int myLevel;
	public ControllableGrid myGrid;
	
	public LevelController(ControllableGrid grid, int level) {
		myLevel = level;
		myGrid = grid;
	}
	public int getMyLevel() {
		return myLevel;
	}
	public void setMyLevel(int myLevel) {
		this.myLevel = myLevel;
	}
	public ControllableGrid getMyMap() {
		return myGrid;
	}
	public void setMyMap(ControllableGrid myMap) {
		this.myGrid = myMap;
	}
	
	public void changeLevel(GameData current, int level){
		myLevel = level;
		LevelData curr = current.getLevel(level);
		PreferencesData preferences = curr.getMyPreferences();
		if(!preferences.cleanLevel()){ //add old actors
			
		}
		addPieces(curr);//add new level actors
		
		//TODO: Add-on other LevelData measures like difficulty
	}
	
	public void levelUp(GameData current) {
		myLevel++;
		changeLevel(current,myLevel);
	}

	
	/**
	 * Use Actor factory to add all of the actors
	 * to the grid
	 * 
	 * @param curr LevelData from which to collect Actor information
	 * @param grid Grid to modify (add actors)
	 */
	private void addPieces(LevelData curr){
		for (ActorData troop: curr.getTroops().keySet()) {
			
		}
		
	}
	
	
}

package gameengine.controllers;

import gamedata.GameData;
import gamedata.LevelData;
import gamedata.PreferencesData;
import gameengine.grid.ActorGrid;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;

/**
 * Controls information about/behavior of a single level
 * 
 * @author maddiebriere
 */

public class LevelController {
	public int myLevel;
	public ControllableGrid myMap;
	
	public LevelController(ControllableGrid grid, int level) {
		myLevel = level;
		myMap = grid;
	}
	public int getMyLevel() {
		return myLevel;
	}
	public void setMyLevel(int myLevel) {
		this.myLevel = myLevel;
	}
	public ControllableGrid getMyMap() {
		return myMap;
	}
	public void setMyMap(ControllableGrid myMap) {
		this.myMap = myMap;
	}
	
	public void changeLevel(GameData current, int level){
		LevelData curr = current.getLevel(level);
		PreferencesData preferences = curr.getMyPreferences();
		if(!preferences.cleanLevel()){ //add old actors
			grid = prev.getMyMap();
			//TODO: filter out enemies?
		}
		addPieces(curr, grid);//add new level actors
		
		//TODO: Add-on other LevelData measures like difficulty
		
		LevelController toRet = new LevelController(grid, level);
	}

	
	/**
	 * Use Actor factory to add all of the actors
	 * to the grid
	 * 
	 * @param curr LevelData from which to collect Actor information
	 * @param grid Grid to modify (add actors)
	 */
	private void addPieces(LevelData curr, ControllableGrid grid){
		//TODO: Implement
		//Need ID Generator here
	}
	
	
}

package factories;

import gamedata.GameData;
import gamedata.LevelData;
import gamedata.PreferencesData;
import gameengine.controllers.LevelController;
import gameengine.grid.ActorGrid;

/**
 * Generates a new level from the current game data
 * 
 * @author maddiebriere
 *
 */

public class LevelFactory {

	//TODO: How do we want to build a level?
	//TODO: Remove parameters from ActorGrid
	public LevelController buildLevel(GameData current, LevelController prev, int level){
		LevelData curr = current.getLevel(level);
		PreferencesData preferences = curr.getMyPreferences();
		ActorGrid grid = new ActorGrid(0,1);
		
		if(!preferences.cleanLevel()){ //add old actors
			grid = prev.getMyMap();
			//TODO: filter out enemies?
		}
		addPieces(curr, grid);//add new level actors
		
		//TODO: Add-on other LevelData measures like difficulty
		
		LevelController toRet = new LevelController(grid, level);
		return toRet;
	}

	
	/**
	 * Use Actor factory to add all of the actors
	 * to the grid
	 * 
	 * @param curr LevelData from which to collect Actor information
	 * @param toModify Grid to modify (add actors)
	 */
	private void addPieces(LevelData curr, ActorGrid toModify){
		//TODO: Implement
		//Need ID Generator here
	}
	
}

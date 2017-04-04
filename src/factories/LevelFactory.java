package factories;

import java.util.List;

import gamedata.GameData;
import gamedata.LevelData;
import gamedata.composition.Data;
import gameengine.controllers.LevelController;
import gameengine.grid.ActorGrid;

/**
 * Generates a new level from the current game data
 * 
 * @author maddiebriere
 *
 */

public class LevelFactory {

	public LevelController buildLevel(GameData current, int level){
		LevelData curr = current.getLevel(level);
		ActorGrid grid = new ActorGrid(curr.getMyMaxX(), curr.getMyMaxY());
		addPieces(curr, grid);
		//TODO: complete
		
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
	}
	
}

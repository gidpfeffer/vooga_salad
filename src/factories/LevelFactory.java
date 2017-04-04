package factories;

import java.util.Map;

import gamedata.GameData;
import gamedata.LevelData;
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
		ActorGrid data = curr.getMyGrid();
		
		//TODO: complete
		return null;
	}
}

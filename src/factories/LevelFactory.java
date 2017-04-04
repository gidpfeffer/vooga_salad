package factories;

import java.util.Map;

import gamedata.ActorData;
import gamedata.GameData;
import gamedata.GridData;
import gamedata.LevelData;
import gameengine.controllers.LevelController;
import gameengine.grid.classes.Coordinates;

/**
 * Generates a new level from the current game data
 * 
 * @author maddiebriere
 *
 */

public class LevelFactory {

	public LevelController buildLevel(GameData current, int level){
		LevelData curr = current.getLevel(level);
		GridData data = curr.getMyGrid();
		//TODO: Make better data structure for this
		Map<ActorData, Coordinates> myGrid = data.getActorMap();
		
		//TODO: complete
		return null;
	}
}

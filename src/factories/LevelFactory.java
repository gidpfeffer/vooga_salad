package factories;

import gamedata.GameData;
import gameengine.controllers.LevelController;

/**
 * Generates a new level from the current game data
 * 
 * @author maddiebriere
 *
 */

public interface LevelFactory {

	public LevelController buildLevel(GameData current, int level);
}

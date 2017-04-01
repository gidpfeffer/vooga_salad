package gameengine.player.levels;

import gamedata.GameData;
import gameengine.controllers.LevelController;

public interface LevelFactory {

	public LevelController buildLevel(GameData current);
}

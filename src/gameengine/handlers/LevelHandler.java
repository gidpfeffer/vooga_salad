package gameengine.handlers;

import gameengine.grid.interfaces.controllergrid.ControllableGrid;

public interface LevelHandler {
	ControllableGrid getMyGrid();
	void displayWinAlert();
	void levelUp();
}

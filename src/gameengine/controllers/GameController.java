package gameengine.controllers;

import gamedata.GameData;
import gameengine.player.GameStatus;
import ui.handlers.UIHandler;

public class GameController {
	private GameStatus myGameStatus;
	private GameData myGameData;
	private UIHandler myUIHandler;
	private LevelController myLevelController;

	public GameController() {
		
	}
	public void step() {
	}
	
	public void updateLevel(LevelController levelController) {
		myLevelController = levelController;
	}
}
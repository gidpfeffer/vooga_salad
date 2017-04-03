package gameengine.controllers;

import gamedata.GameData;
import gameengine.player.GameStatus;
import ui.handlers.UIHandler;
import util.IDGenerator;

public class GameController {
	private GameStatus myGameStatus;
	private GameData myGameData;
	private UIHandler myUIHandler;
	private LevelController myLevelController;
	private IDGenerator myIDGenerator;

	public GameController() {
		myIDGenerator = new IDGenerator();
		myLevelController = new LevelController();
		myGameData = new GameData();
		myGameStatus = new GameStatus();
		initializeUIHandler();
	}
	
	public void step() {
	}
	
	private void initializeUIHandler() {
		
	}
	
	public void updateLevel() {
	}
}
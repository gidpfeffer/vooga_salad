package gameengine.controllers;

import gamedata.GameData;
import gameengine.player.GameStatus;
import types.Actor;
import ui.handlers.UIHandler;
import util.IDGenerator;
import util.VoogaException;

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
		myUIHandler = new UIHandler(){

			@Override
			public int addGameObject(Actor actor, double x, double y) {
				int id = myIDGenerator.getNewID();
				return id;
			}

			@Override
			public void deleteGameObject(int id) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void updateGameObjectType(int id, Actor currentActor,Actor newActor) throws VoogaException {
				if (currentActor.isSameType(newActor)) {
					//update
				} else {
					throw new VoogaException(VoogaException.ILLEGAL_UPGRADE);
				}
			}

			@Override
			public void updateGameObjectLocation(double x, double y) {
				// TODO Auto-generated method stub
				
			}
			
		};
	}
	
	public void updateLevel() {
	}
}
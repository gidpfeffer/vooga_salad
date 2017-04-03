package gameengine.controllers;

import gamedata.GameData;
import gameengine.player.GameStatus;
import ui.handlers.UIHandler;

public class GameController {
	private GameStatus myGameStatus;
	private GameData myGameData;
	private UIHandler myUIHandler;
	//possible problem = trying to upgrade tower into a smart enemy?
	//map enum Actors to Actor<G> instance 
	public GameController() {
		
	}
	public void step() {
	}
	
	public void updateLevel(LevelController levelController) {
		
	}
}

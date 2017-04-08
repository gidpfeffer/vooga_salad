package gameengine.controllers;

import java.util.List;

import factories.ActorGenerator;
import gamedata.ActorData;
import gamedata.GameData;
import gamedata.composition.Data;
import gameengine.actors.management.Actor;
import gameengine.grid.ActorGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.Identifiers.MovableActor;
import gameengine.player.GameStatus;
import types.ActorType;
import ui.handlers.UIHandler;
import util.IDGenerator;
import util.VoogaException;

public class GameController {
	private GameStatus myGameStatus;
	private GameData myGameData;
	
	private UIHandler myUIHandler;
	private LevelController myLevelController;
	private IDGenerator myIDGenerator;
	private ActorGrid myActorGrid;
	
	private final int MAX_X = 1000;
	private final int MAX_Y =1000;

	public GameController() {
		myIDGenerator = new IDGenerator();
		myGameData = new GameData();
		myGameStatus = new GameStatus();
		myActorGrid = new ActorGrid(MAX_X,MAX_Y);
		initializeUIHandler();
	}
	
	public void step() {
		myActorGrid.step();
	}
	
	private void initializeUIHandler() {
		myUIHandler = new UIHandler() {

			@Override
			public void deleteGameObject(int id) {
				myActorGrid.removeActor(id);
			}

			@Override
			public void updateGameObjectType(int id, Integer currentOption, Integer newOption) throws VoogaException {
				if (myGameData.getOption(currentOption).getActor().equals(myGameData.getOption(newOption))) {
					Grid2D location = myActorGrid.getActorFromID(id).getLocation();
					addGameObject(newOption,location.getX(),location.getY());
					deleteGameObject(id);
				} else {
					throw new VoogaException(VoogaException.ILLEGAL_UPDATE);
				}
				
			}

			@Override
			public void updateGameObjectLocation(int id, double x, double y) throws VoogaException {
				if (myActorGrid.isValidLoc(x, y)) {
					myActorGrid.getActorFromID(id).setLocation(x, y);
				} else {
					throw new VoogaException(VoogaException.INVALID_LOCATION);
				}
				
			}

			@Override
			public int addGameObject(Integer option, double x, double y) throws VoogaException{
				ActorData actorData = myGameData.getOption(option);
				int ID = myIDGenerator.getNewID();
				Actor actor = ActorGenerator.makeActor(ID, actorData);
				if (myActorGrid.isValidLoc(x, y)) {
					myActorGrid.spawn(actor, x, y);
				} else {
					throw new VoogaException(VoogaException.INVALID_LOCATION);
				}
				
				return ID;
			}
			
		};
	}
	
	public void updateLevel() {
	}
}
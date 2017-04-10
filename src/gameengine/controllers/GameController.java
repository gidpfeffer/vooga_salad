package gameengine.controllers;

import java.util.Map;

import factories.ActorGenerator;
import gamedata.ActorData;
import gamedata.GameData;
import gameengine.actors.management.Actor;
import gameengine.grid.ActorGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import gameengine.player.GameStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ui.UIMain;
import ui.handlers.UIHandler;
import util.IDGenerator;
import util.Location;
import util.RatioToLocationTransformer;
import util.VoogaException;

public class GameController {
	private Timeline animation;
	
	private GameStatus myGameStatus;
	private GameData myGameData;
	
	private UIHandler myUIHandler;
	private LevelController myLevelController;
	private ControllableGrid myGrid;
	
	private UIMain myUIMain;
	
	private final int MAX_X = 1;
	private final int MAX_Y =1;
	
	private final double MILLISECOND_DELAY=17;

	public GameController() {
		myGameData = new GameData();
		myGameStatus = new GameStatus();
		myGrid = getNewActorGrid();
		myLevelController = new LevelController(1, ()-> this.getNewActorGrid());
		initializeUIHandler();
		intitializeTimeline();
	}
	
	public ActorGrid getNewActorGrid() {
		return new ActorGrid(MAX_X,MAX_Y,
				i -> ActorGenerator.makeActor(i,myGameData.getOption(i)));
	}
	
	public void start() {
		myUIMain = new UIMain("English",myUIHandler);
		intitializeTimeline();
	}
	
	public void intitializeTimeline() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				d -> step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}
	
	public void step() {
		myGrid.step();
	}
	
	private double getMapSizeX() {
		//need getMap method in front end
		return myUIMain.getScene().getWidth();
	}
	
	private double getMapSizeY() {
		//need getMap method in front end
		return myUIMain.getScene().getHeight();
	}

	private void initializeUIHandler() {
		myUIHandler = new UIHandler() {

			@Override
			public void deleteGameObject(int id) {
				myGrid.removeActor(id);
			}

			@Override
			public void updateGameObjectType(int id, Integer currentOption, Integer newOption) throws VoogaException {
				if (myGameData.getOption(currentOption).getActor().equals(myGameData.getOption(newOption).getActor())) {
					Grid2D location = myGrid.getLocationOf(id);
					addGameObject(newOption,location.getX(),location.getY());
					deleteGameObject(id);
				} else {
					throw new VoogaException(VoogaException.ILLEGAL_UPDATE);
				}
				
			}

			@Override
			public void updateGameObjectLocation(int id, double x, double y) throws VoogaException {
				if (myGrid.isValidLoc(x, y)) {
					myGrid.move(id,x, y);
				} else {
					throw new VoogaException(VoogaException.INVALID_LOCATION);
				}
				
			}

			@Override
			public int addGameObject(Integer option, double xRatio, double yRatio) throws VoogaException{
				ActorData actorData = myGameData.getOption(option);
				Actor actor = ActorGenerator.makeActor(option,actorData);
				Location location = RatioToLocationTransformer.getLocation(xRatio, yRatio, getMapSizeX(), getMapSizeY());
				if (myGrid.isValidLoc(location.getX(), location.getY())) {
					myGrid.controllerSpawnActor(actor, location.getX(), location.getY());
				} else {
					throw new VoogaException(VoogaException.INVALID_LOCATION);
				}
				
				return actor.getID();
			}

			@Override
			public void pause() {
				animation.pause();
			}

			@Override
			public void play() {
				animation.play();
			}

			@Override
			public void stop() {
				animation.stop();
			}

			@Override
			public void exit() {
				System.exit(0);
			}

			@Override
			public Map<Integer, ActorData> getOptions() {
				return myGameData.getOptions();
			}

			@Override
			public Map<Integer, ActorData> getTroopOptions() {
				return myGameData.getTroopOptions();
			}

			@Override
			public Map<Integer, ActorData> getShotOptions() {
				return myGameData.getShotOptions();
			}

			@Override
			public Map<Integer, ActorData> getBaseOptions() {
				return myGameData.getBaseOptions();
			}

			@Override
			public void changeLevel(int level) throws VoogaException {
				myLevelController.changeLevel(myGameData, level);
			}
		};
	}
	
}
package gameengine.controllers;

import java.util.Map;

import builders.ActorGenerator;
import builders.GameDataGenerator;
import gamedata.ActorData;
import gamedata.GameData;
import gamedata.LevelData;
import gameengine.actors.management.Actor;
import gameengine.grid.ActorGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.handlers.UIHandler;
import ui.player.inGame.GameScreen;
import util.VoogaException;
import util.observerobservable.VoogaObserver;

/**
 * GameController is the controller layer between the front end display and the back end game engine
 * Implements UIHandler and initializes all necessary back end and front end components of game engine
 * @author sarahzhou
 *
 */
public class GameController {
	private Timeline animation;
	
	private GameData myGameData;
	
	private UIHandler myUIHandler;
	private LevelController myLevelController;
	private ControllableGrid myGrid;
	
	private GameScreen myGameScreen;
	
	private final int MAX_X = 1;
	private final int MAX_Y = 1;
	
	private final double MILLISECOND_DELAY=17;

	public GameController() {
		myGameData = GameDataGenerator.getSampleGame();//new GameData();
		initializeUIHandler();
	}

	/**
	 * @param UIObserver
	 * @return a new clean instance of ActorGrid
	 */
	public ActorGrid getNewActorGrid(VoogaObserver<Map<Integer,FrontEndInformation>> UIObserver) {
		ActorGrid actorGrid = new ActorGrid(MAX_X,MAX_Y,
				i -> ActorGenerator.makeActor(i,myGameData.getOption(i)));
		actorGrid.addObserver(UIObserver);
		return actorGrid;
	}
	
	public GameScreen getGameScreen() {
		return myGameScreen;
	}
	
	public void start(Stage stage) {
		myGameScreen = new GameScreen(stage,myUIHandler);
		myGrid = getNewActorGrid(myGameScreen);
		myLevelController = new LevelController(1,() -> getNewActorGrid(myGameScreen));
		intitializeTimeline();
	}
	
	private void intitializeTimeline() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				d -> step());
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}
	
	private void step() {
		myGrid.step();
	}
	
	private double getMapSizeX() {
		return myGameScreen.getWindow().get(0);
	}
	
	private double getMapSizeY() {
		return myGameScreen.getWindow().get(1);
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
			public int addGameObject(Integer option, double xCoor, double yCoor) throws VoogaException{
				ActorData actorData = myGameData.getOption(option);
				Actor actor = ActorGenerator.makeActor(option,actorData);
				double xRatio = util.Transformer.coordinateToRatio(xCoor, getMapSizeX());
				double yRatio = util.Transformer.coordinateToRatio(yCoor, getMapSizeY());
				if (myGrid.isValidLoc(xRatio, yRatio)) {
					myGrid.controllerSpawnActor(actor, xRatio, yRatio);
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

			@Override
			public void addLevel(LevelData levelData, int level) {
				myGameData.addLevel(levelData, level);
			}

			@Override
			public Map<Integer, ActorData> getTowerOptions() {
				return myGameData.getTowerOptions();
			}
		
		};
	}
	
}
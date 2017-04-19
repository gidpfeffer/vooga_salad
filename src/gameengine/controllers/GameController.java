package gameengine.controllers;

import java.util.List;
import java.util.Map;

import builders.ActorGenerator;
import builders.GameDataGenerator;
import gamedata.ActorData;
import gamedata.GameData;
import gamedata.map.LayerData;
import gamedata.map.PolygonData;
import gameengine.actors.management.Actor;
import gameengine.grid.ActorGrid;
import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;
import gamestatus.GameStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.handlers.UIHandler;
import ui.player.inGame.GameScreen;
import ui.player.inGame.SimpleHUD;
import util.PathUtil;
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
	private GameStatus myGameStatus;
	
	private UIHandler myUIHandler;
	private LevelController myLevelController;
	private ControllableGrid myGrid;
	
	private GameScreen myGameScreen;
	private SimpleHUD mySimpleHUD;
	
	private final int MAX_X = 1;
	private final int MAX_Y = 1;
	
	private final double MILLISECOND_DELAY=17;

	public GameController() {
		myGameData = GameDataGenerator.getComplexSampleGame();//new GameData();
		initializeUIHandler();
		setupGameStatus();
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
	
	private void setupGameStatus() {
		mySimpleHUD = new SimpleHUD();
		myGameStatus = new GameStatus();
		myGameStatus.addObserver(mySimpleHUD);
	}
	
	public void start(Stage stage) {
		myGameScreen = new GameScreen(myUIHandler);
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

	private void initializeUIHandler() {
		myUIHandler = new UIHandler() {

			@Override
			public void deleteGameObject(int id) {
				myGrid.removeActor(id);
			}

			@Override
			public void updateGameObjectType(int id, Integer currentOption, Integer newOption) throws VoogaException {
				if (myGameData.getOption(currentOption).getType().equals(myGameData.getOption(newOption).getType())) {
					Grid2D location = myGrid.getLocationOf(id);
					addGameObject(newOption,location.getX(),location.getY());
					deleteGameObject(id);
				} else {
					throw new VoogaException(VoogaException.ILLEGAL_UPDATE);
				}
				
			}

			@Override
			public void updateGameObjectLocation(int id, double xRatio, double yRatio) throws VoogaException {
				if (myGrid.isValidLoc(xRatio, yRatio)) {
					myGrid.move(id,xRatio, yRatio);
				} else {
					throw new VoogaException(VoogaException.INVALID_LOCATION);
				}
				
			}
			/**
			 * method to check if actor is being placed in the right layer
			 * x, y is from 0 -1 ?
			 * @return
			 */
			private boolean isPlaceable(List<LayerData> layers, double x, double y){
				for (LayerData layer:layers){
					for (PolygonData poly: layer.getMyPolygons()){
						if (!PathUtil.isWithinPolygon(poly.getMyPoints(), x,y)){
							return false;
						}
					}
				}
				return true;
			}

			@Override
			public int addGameObject(Integer option, double xRatio, double yRatio) throws VoogaException{
				ActorData actorData = myGameData.getOption(option);
				//check for placeable here 
				//isPlaceable(myGameData.getLayers().getAssignedLayers(actorData.getLayerIndexes()),xRatio, yRatio)
				
				Actor actor = ActorGenerator.makeActor(option,actorData);
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
			public void changeLevel(int level) throws VoogaException {
				myLevelController.changeLevel(myGameData, level);
			}

			@Override	
			public SimpleHUD getSimpleHUD() {
				return mySimpleHUD;
			}
		};
	}
	
}
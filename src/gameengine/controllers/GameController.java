package gameengine.controllers;
import java.util.Map;		
import builders.ActorGenerator;
import gamedata.ActorData;
import gamedata.GameData;
import gameengine.grid.ActorGrid;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;
import gamestatus.GameStatus;
import gamestatus.WriteableGameStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import ui.handlers.AnimationHandler;
import ui.handlers.UIHandler;
import ui.player.inGame.GameScreen;
import ui.player.inGame.SimpleHUD;
import util.GameObjectUtil;
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
	private AnimationHandler myAnimationHandler;
	private WriteableGameStatus myWriteableGameStatus;
	private LevelController myLevelController;
	private ControllableGrid myGrid;
	
	private GameScreen myGameScreen;
	private SimpleHUD mySimpleHUD;
	
	private final int MAX_X = 1;
	private final int MAX_Y = 1;
	
	private final double MILLISECOND_DELAY=17;
	public GameController(GameData gameData) {
		myGameData = gameData;
		initializeUIHandler();
		initializeAnimationHandler();
		setupGameStatus();
		myGameScreen = new GameScreen(myUIHandler,myAnimationHandler,() -> mySimpleHUD);
		myGameScreen.setAnimationHandler(myAnimationHandler);
		myGameScreen.setSong(myGameData.getPreferences().getMusicFilePath()); //set music for game
	}
	/**
	 * @param UIObserver
	 * @return a new clean instance of ActorGrid
	 */
	public ActorGrid getNewActorGrid(VoogaObserver<Map<Integer,FrontEndInformation>> UIObserver) {
		ActorGrid actorGrid = new ActorGrid(MAX_X,MAX_Y,myWriteableGameStatus,
				i -> ActorGenerator.makeActor(i,myGameData.getOption(i)));
		actorGrid.addObserver(UIObserver);
		return actorGrid;
	}
	
	public ControllableGrid getMyGrid() {
		return myGrid;
	}
	
	public GameScreen getGameScreen() {
		return myGameScreen;
	}
	
	private void setupGameStatus() {
		mySimpleHUD = new SimpleHUD();
		myGameStatus = new GameStatus();
		myGameStatus.addObserver(mySimpleHUD);
	}
	
	public void start() {
		myGrid = getNewActorGrid(myGameScreen);
		myLevelController = new LevelController(() -> getMyGrid(),() -> displayWinAlert());
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
	
	private void displayWinAlert() {
		myGameScreen.notifyWin();
	}
	
	private void initializeAnimationHandler() {
		myAnimationHandler = new AnimationHandler() {
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
		};
	}

	private void initializeUIHandler() {
		myUIHandler = new UIHandler() {
			@Override
			public void deleteGameObject(int id) {
				GameObjectUtil.deleteGameObject(id, myGrid);
			}
			@Override
			public void updateGameObjectType(int id, Integer currentOption, Integer newOption) throws VoogaException {
				GameObjectUtil.updateGameObjectType(id, currentOption, newOption, myGrid, myGameData);
			}
			@Override
			public void updateGameObjectLocation(int id, double xRatio, double yRatio) throws VoogaException {
				GameObjectUtil.updateGameObjectLocation(id, xRatio, yRatio, myGrid);
			}
			@Override
			public int addGameObject(Integer option, double xRatio, double yRatio) throws VoogaException{
				return GameObjectUtil.addGameObject(option, xRatio, yRatio, myGameData, myGrid);
			}
			@Override
			public Map<Integer, ActorData> getOptions() {
				return myGameData.getOptions();
			}
			@Override
			public void changeLevel(int level) throws VoogaException {
				myLevelController.changeLevel(myGameData, level);
			}
			
			public void launchGame() throws VoogaException {
				myLevelController.changeLevel(myGameData, 1);
			}
		};
	}
	
	
	
}
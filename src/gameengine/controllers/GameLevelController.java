package gameengine.controllers;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Supplier;
import gamedata.ActorData;
import gamedata.EnemyInWaveData;
import gamedata.GameData;
import gamedata.LevelData;
import gamedata.PathData;
import gamedata.WaveData;
import gameengine.actors.management.Actor;
import gameengine.conditions.EnduranceCondition;
import gameengine.conditionsgen.Condition;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import gameengine.handlers.LevelHandler;
import gamestatus.GameStatus;
import gamestatus.ReadableGameStatus;
import types.BasicActorType;
import util.Delay;
/**
 * Controls information about/behavior of a single level
 * 
 * @author sarahzhou
 * 
 */

public class GameLevelController {
	private ControllableGrid myGrid;
	
	private GameData myGameData;
	
	private ReadableGameStatus myReadableGameStatus;
	
	private GameStatus myGameStatus;
	
	private LevelHandler myLevelHandler;
	
	private Delay delay;
	
	private final int DELAY_CONSTANT = 2;
	
	private Condition myEnduranceCondition;
	
	private int level;
	
	private Queue<Supplier<Boolean>> enemiesInWave;
	
	private int enemiesLeft = 0; 
	
//	private int wavesLeft;

	public GameLevelController(LevelHandler levelHandler,GameData gameData,GameStatus gameStatus) {
		myLevelHandler = levelHandler;
		myGrid = myLevelHandler.getMyGrid();
		delay = new Delay(DELAY_CONSTANT);
		myGameData = gameData;
		enemiesInWave = new ArrayDeque<>();
		myReadableGameStatus = gameStatus;
		myEnduranceCondition = new EnduranceCondition(10);
	}
	
	private BasicActorType getBasicActorEnemyType() {
		return myGameData.getLevel(1).getMyWaves().get(0).getWaveEnemies().get(0).getMyActor().getType();
	}
	
	private void countEnemies(WaveData waveData) {
		waveData.getWaveEnemies().stream().forEach(enemy -> enemiesLeft+=enemy.getOption());
	}
	
	private void setEnemiesLeft(int numEnemies) {
		enemiesLeft = numEnemies;
		myGameStatus.setMyEnemiesLeft(numEnemies);
	}
	
	@SuppressWarnings("unchecked")
	public void update() {
		if(delay.delayAction()&&!enemiesInWave.isEmpty()) {
			enemiesInWave.poll().get();
		}
		int enemiesLeft = enemiesInWave.size()+myLevelHandler.actorCounts().apply(getBasicActorEnemyType());
		setEnemiesLeft(enemiesLeft);
		Optional<Boolean> myWin = myEnduranceCondition.conditionSatisfied(myReadableGameStatus);
		myWin.ifPresent(win -> winCondition(win).run());
		
	}
	
	private Runnable winCondition(Boolean win) {
		return win ? () -> levelUp():()->myLevelHandler.displayLoseAlert();
	}
	
	public int getLevel() {
		return level;
	}
	
	public void levelUp() {
		if (!(myGameData.getLevels().get(level+1)==null)) {
			changeLevel(level+1);
			myLevelHandler.levelUp();
		} else {
			myLevelHandler.displayWinAlert();
		}
	}
	
	public void changeLevel(int level) {
		this.level = level;
		LevelData levelData = myGameData.getLevel(level);
		loadLevel(levelData);
	}
	
	private void loadLevel(LevelData levelData) {
		addPieces(levelData);
	}
	
	/**
	 * Use Actor factory to add all of the actors
	 * to the grid
	 * 
	 * @param curr LevelData from which to collect Actor information
	 * @param grid Grid to modify (add actors)
	 */
	private void addPieces(LevelData curr){
		Grid2D firstPathCoor =getFirstPathCoor(myGameData.getMyPaths());
		curr.getMyWaves().forEach(wave -> processWave(wave,firstPathCoor));
	}
	
	private void processWave(WaveData waveData,Grid2D firstPathCoor) {
		processEnemyWaves(waveData.getWaveEnemies(),firstPathCoor);
	}
	
	private void processEnemyWaves(List<EnemyInWaveData> enemyInWaveDatas,Grid2D firstPathCoor) {
		enemyInWaveDatas.forEach(data -> {
			for(int i = 0; i<data.getOption(); i++) enemiesInWave.add(() -> {
				spawnEnemy(data,firstPathCoor);
				return true;
			});
		});
	}
	
	public int getEnemiesLeft() {
		return enemiesLeft;
	}

	private void spawnEnemy(EnemyInWaveData enemyData, Grid2D firstPathCoor) {
		ActorData actorData = enemyData.getMyActor();
		Actor actor = builders.objectgen.ActorGenerator.makeActor(myGameData.getOptionKey(actorData), actorData);
		myGrid.controllerSpawnActor(actor, firstPathCoor.getX(),firstPathCoor.getY());
	}
	
	private Grid2D getFirstPathCoor(PathData pathData) {
		int numPaths = pathData.getMyPaths().size();
		int rand = (int) Math.random()*numPaths;
		return pathData.getPathByIndex(rand).get(0);
	}
	

	
}

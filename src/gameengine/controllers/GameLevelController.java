package gameengine.controllers;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;

import gamedata.ActorData;
import gamedata.EnemyInWaveData;
import gamedata.GameData;
import gamedata.LevelData;
import gamedata.PathData;
import gamedata.WaveData;
import gameengine.actors.management.Actor;
import gameengine.conditions.Condition;
import gameengine.conditions.EnduranceCondition;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import gameengine.handlers.LevelHandler;
import gamestatus.ReadableGameStatus;
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
	
	private LevelHandler myLevelHandler;
	
	private Delay delay;
	
	private final int DELAY_CONSTANT = 2;
	
	private Condition<ReadableGrid> myEnduranceCondition;
	
	private int level;
	
	private Queue<Supplier<Boolean>> enemiesInWave;
	
	public GameLevelController(LevelHandler levelHandler,GameData gameData,ReadableGameStatus readableGameStatus) {
		myLevelHandler = levelHandler;
		myGrid = myLevelHandler.getMyGrid();
		delay = new Delay(DELAY_CONSTANT);
		myGameData = gameData;
		enemiesInWave = new ArrayDeque<>();
		myEnduranceCondition = new EnduranceCondition<ReadableGrid>(100);
	}
	
	@SuppressWarnings("unchecked")
	public void update() {
		if(delay.delayAction()&&!enemiesInWave.isEmpty()) {
			enemiesInWave.poll().get();
		}
		myEnduranceCondition.conditionSatisfied((ReadableGrid)myGrid, myReadableGameStatus).ifPresent((win) -> winCondition((Boolean) win));
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

	private void spawnEnemy(EnemyInWaveData enemyData, Grid2D firstPathCoor) {
		ActorData actorData = enemyData.getMyActor();
		Actor actor = builders.ActorGenerator.makeActor(myGameData.getOptionKey(actorData), actorData);
		myGrid.controllerSpawnActor(actor, firstPathCoor.getX(),firstPathCoor.getY());
	}
	
	private Grid2D getFirstPathCoor(PathData pathData) {
		int numPaths = pathData.getMyPaths().size();
		int rand = (int) Math.random()*numPaths;
		return pathData.getPathByIndex(rand).get(0);
	}
	

	
}

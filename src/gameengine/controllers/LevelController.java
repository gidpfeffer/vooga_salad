package gameengine.controllers;

import java.util.List;
import java.util.function.Supplier;	
import gamedata.ActorData;
import gamedata.EnemyInWaveData;
import gamedata.GameData;
import gamedata.LevelData;
import gamedata.PathData;
import gamedata.PreferencesData;
import gamedata.WaveData;
import gameengine.actors.management.Actor;
import gameengine.grid.ActorGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import util.Delay;
import util.IDGenerator;
import util.VoogaException;

/**
 * Controls information about/behavior of a single level
 * 
 * @author sarahzhou
 * 
 */

public class LevelController {
	private ControllableGrid myGrid;
	private Supplier<ActorGrid> getActorGrid;
	
	private Delay delay;
	
	private final int DELAY_CONSTANT = 2;
	
	private int level;
	
	public LevelController(Supplier<ActorGrid> getActorGrid) {
		this.getActorGrid = getActorGrid;
		myGrid = getActorGrid.get();
		delay = new Delay(DELAY_CONSTANT);
	}
	
	public int getLevel() {
		return level;
	}
	
	public void levelUp (GameData gameData) {
		if (!(gameData.getLevels().get(level+1)==null)) {
			
		} else {
			//user has won
		}
	}
	
	public void changeLevel(GameData gameData, int level) throws VoogaException{
		this.level = level;
		PreferencesData preferences = gameData.getPreferences();
		System.out.println(preferences);
		LevelData levelData = gameData.getLevel(level);
		if (levelData!=null) loadLevel(preferences,levelData,gameData);
		else throw new VoogaException(VoogaException.NONEXISTANT_LEVEL);
	}
	
	private void loadLevel(PreferencesData preferences, LevelData levelData, GameData gameData) {
		if(preferences.cleanLevel()) myGrid = getActorGrid.get();
		addPieces(gameData,levelData,preferences);
	}
	
	/**
	 * Use Actor factory to add all of the actors
	 * to the grid
	 * 
	 * @param curr LevelData from which to collect Actor information
	 * @param grid Grid to modify (add actors)
	 */
	private void addPieces(GameData gameData, LevelData curr,PreferencesData preferences){
		System.out.println("adding pieces");
		curr.getMyWaves().forEach(wave -> processWave(wave,gameData.getMyPaths(),preferences));
	}
	
	private void processWave(WaveData waveData,PathData pathData,PreferencesData preferences) {
		System.out.println("processing enemy waves");
		processEnemyWaves(waveData.getWaveEnemies(),pathData);
		System.out.println("processed enemy waves");
		if (preferences.pauseBetweenWaves()) delay.delayAction();
	}
	
	private void spawnEnemy(EnemyInWaveData enemyData, PathData pathData) {
		ActorData actorData = enemyData.getMyActor();
		Actor actor = builders.ActorGenerator.makeActor(IDGenerator.getNewID(), actorData);
		System.out.println("spawning enemy"+ IDGenerator.getNewID());
		Grid2D firstPathCoor = getFirstPathCoor(pathData);
		myGrid.controllerSpawnActor(actor, firstPathCoor.getX(),firstPathCoor.getY());
		System.out.println("enemy spawned"+ IDGenerator.getNewID());
	}
	
	private Grid2D getFirstPathCoor(PathData pathData) {
		int numPaths = pathData.getMyPaths().size();
		int rand = (int) Math.random()*numPaths;
		return pathData.getPathByIndex(rand).get(0);
	}
	
	private void processEnemyWaves(List<EnemyInWaveData> enemyInWaveDatas,PathData pathData) {
		enemyInWaveDatas.forEach(e -> spawnEnemy(e,pathData));
	}
	
	
}

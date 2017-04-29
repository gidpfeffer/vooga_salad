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
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import util.Delay;
import util.VoogaException;

/**
 * Controls information about/behavior of a single level
 * 
 * @author sarahzhou
 * 
 */

public class LevelController {
	private ControllableGrid myGrid;
	
	private GameData myGameData;
	
	private PreferencesData myPreferences;
	
	private Runnable win;
	private Runnable updateGameStatusLevel;
	
	private Delay delay;
	
	private final int DELAY_CONSTANT = 2;
	
	private int level;
	
	public LevelController(Supplier<ControllableGrid> getControllableGrid, Runnable win,Runnable updateGameStatusLevel,GameData gameData) {
		myGrid = getControllableGrid.get();
		delay = new Delay(DELAY_CONSTANT);
		this.win = win;
		this.updateGameStatusLevel = updateGameStatusLevel;
		myPreferences = myGameData.getPreferences();
	}
	
	public int getLevel() {
		return level;
	}
	
	public void levelUp() throws VoogaException {
		if (!(myGameData.getLevels().get(level+1)==null)) {
			changeLevel(level+1);
			updateGameStatusLevel.run();
		} else {
			win.run();
		}
	}
	
	public void changeLevel(int level) throws VoogaException{
		this.level = level;
		LevelData levelData = myGameData.getLevel(level);
		if (levelData!=null) loadLevel(levelData);
		else throw new VoogaException(VoogaException.NONEXISTANT_LEVEL);
	}
	
	private void loadLevel(LevelData levelData) {
		//if(preferences.cleanLevel()) ;
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
		curr.getMyWaves().forEach(wave -> processWave(wave,myGameData.getMyPaths()));
	}
	
	private void processWave(WaveData waveData,PathData pathData) {
		System.out.println("processing enemy waves");
		processEnemyWaves(waveData.getWaveEnemies(),pathData);
		System.out.println("processed enemy waves");
	}
	
	private void spawnEnemy(EnemyInWaveData enemyData, PathData pathData) {
		ActorData actorData = enemyData.getMyActor();
		System.out.println(enemyData==null);
		Actor actor = builders.ActorGenerator.makeActor(enemyData.getOption(), actorData);
		System.out.println("here"+actor.getType().toString()+" "+actor.getID());
		Grid2D firstPathCoor = getFirstPathCoor(pathData);
		System.out.println("got coordinate");
		myGrid.controllerSpawnActor(actor, firstPathCoor.getX(),firstPathCoor.getY());
		System.out.println("enemy spawned");
		if (myPreferences.pauseBetweenWaves()) delay.delayAction();
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

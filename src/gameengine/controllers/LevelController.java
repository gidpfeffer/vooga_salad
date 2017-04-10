package gameengine.controllers;

import java.util.function.Supplier;

import factories.ActorGenerator;
import gamedata.ActorData;
import gamedata.EnemyInWaveData;
import gamedata.GameData;
import gamedata.LevelData;
import gamedata.PreferencesData;
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
 * @author maddiebriere, sarahzhou
 * 
 */

public class LevelController {
	private ControllableGrid myGrid;
	private Supplier<ActorGrid> getActorGrid;
	
	private int level;

	public ControllableGrid getMyGrid() {
		return myGrid;
	}
	public Supplier<ActorGrid> getGetActorGrid() {
		return getActorGrid;
	}
	public int getLevel() {
		return level;
	}
	public LevelController(int level, Supplier<ActorGrid> getActorGrid) {
		this.getActorGrid = getActorGrid;
		myGrid = getActorGrid.get();
		this.level = level;
	}
	public ControllableGrid getMyMap() {
		return myGrid;
	}
	public void setMyMap(ControllableGrid myMap) {
		this.myGrid = myMap;
	}

	public void changeLevel(GameData gameData, int level) throws VoogaException{
		this.level = level;
		LevelData levelData = gameData.getLevel(level);
		if (levelData!=null) {
			PreferencesData preferences = levelData.getMyPreferences();
			if(preferences.cleanLevel()){ 
				//get clean actor grid if preferences indicate clean level
				myGrid = getActorGrid.get();
			} 
			addPieces(gameData,levelData,preferences);
		} else {
			throw new VoogaException(VoogaException.NONEXISTANT_LEVEL);
		}
	}
	
	/**
	 * Use Actor factory to add all of the actors
	 * to the grid
	 * 
	 * @param curr LevelData from which to collect Actor information
	 * @param grid Grid to modify (add actors)
	 */
	private void addPieces(GameData gameData, LevelData curr,PreferencesData preferences){
		Delay delay = new Delay(2);
		for (EnemyInWaveData data: curr.getTroops()) {
			for (int i = 0;i<data.getMyNumber();i++) {
				ActorData actorData = data.getMyData();
				Actor actor = ActorGenerator.makeActor(IDGenerator.getNewID(), actorData);
				int numPaths = data.getMyPaths().size();
				int rand = (int) Math.random()*numPaths;
				Grid2D firstPathCoor = gameData.getPathOptions().get(rand).get(0);
				myGrid.controllerSpawnActor(actor, firstPathCoor.getX(),firstPathCoor.getY());
			}
			if (preferences.getPauseBetweenWaves().get()) {
				delay.delayAction();
			}
			
		}
		
	}
	
	
}

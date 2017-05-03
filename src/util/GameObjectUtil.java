package util;

import java.util.stream.Collectors;

import builders.objectgen.ActorGenerator;
import gamedata.ActorData;
import gamedata.GameData;
import gamedata.LayerData;
import gamedata.LineageData;
import gamedata.PreferencesData;
import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import gamestatus.GameStatus;
import gamestatus.ReadableGameStatus;
import gamestatus.WriteableGameStatus;

/**
 * Util class for updating Game Objects in back end grid (updating type, spawning, deleting, updating location)
 * @author sarahzhou
 *
 */
public class GameObjectUtil {
	public void deleteGameObject(int id,ControllableGrid grid) {
		grid.removeActor(id);
	}

	public void updateGameObjectType(int id, LineageData lineageData,ControllableGrid grid,GameData gameData,GameStatus gameStatus) throws InsufficientMoneyException {
		lineageData.upgrade();
		ActorData actorData = lineageData.getCurrent();
		if (enoughMoney(gameData.getPreferences(),gameStatus.getMoney(),actorData.getCost())) {
			Grid2D location = grid.getLocationOf(id);
			generateActor(id,gameData,actorData, location.getX(), location.getY(),grid,gameStatus);
			deleteGameObject(id,grid);
		} else {
			throw new InsufficientMoneyException();
		}
	}

	public void updateGameObjectLocation(int id, double xRatio, double yRatio,ControllableGrid grid) throws VoogaException {
		if (grid.isValidLoc(xRatio, yRatio)) grid.move(id,xRatio, yRatio);
		else throw new VoogaException(VoogaException.INVALID_LOCATION);
	}
	
	public boolean isPlaceable(LayerData layer, double x, double y){
		layer.getMyPolygons().forEach(p -> System.out.println("layer " + PathUtil.isWithinPolygon(p.getMyPoints(), x,y)));
		
		if (!layer.getMyPolygons().stream()
				.filter(poly -> PathUtil.isWithinPolygon(poly.getMyPoints(), x,y))
				.collect(Collectors.toList()).isEmpty()) return true;
		return false;
	}

	public int addGameObject(Integer option, double xRatio, double yRatio,GameData gameData,GameStatus gameStatus, ControllableGrid grid) throws LayerNotPlaceableException, InsufficientMoneyException {
		ActorData actorData = gameData.getOption(option); 
		if (enoughMoney(gameData.getPreferences(),gameStatus.getMoney(),actorData.getCost())) {
			if (isPlaceable(actorData.getLayer(),xRatio, yRatio)) {
				return generateActor(gameData, actorData, xRatio, yRatio,grid,gameStatus);
			} else {
				throw new LayerNotPlaceableException();
			}
		} else {
			throw new InsufficientMoneyException();
		}
	}
	
//	private boolean isAddable(ControllableGrid grid, LayerData layerData, double xRatio, double yRatio, PreferencesData preferences, double moneyLeft, double cost) {
//		return (isPlaceable(layerData,xRatio, yRatio) && grid.isValidLoc(xRatio, yRatio) && enoughMoney(preferences,moneyLeft,cost));
//	}
	
	private boolean enoughMoney(PreferencesData preferences, double moneyLeft, double cost) {
		return ((preferences.wantMoney() && moneyLeft>=cost) || !preferences.wantMoney());
	}
	
	private int generateActor(GameData gameData, ActorData actorData, double xRatio, double yRatio,ControllableGrid grid,WriteableGameStatus gameStatus) {
		gameStatus.spendMoney(actorData.getCost());
		Actor actor = ActorGenerator.makeActor(gameData.getOptionKey(actorData),actorData);
		grid.controllerSpawnActor(actor, xRatio, yRatio);
		return actor.getID();
	}
	
	private void generateActor(int id, GameData gameData, ActorData actorData, double xRatio, double yRatio,ControllableGrid grid,WriteableGameStatus gameStatus) {
		gameStatus.spendMoney(actorData.getCost());
		Actor actor = ActorGenerator.makeActor(id,gameData.getOptionKey(actorData),actorData);
		grid.controllerSpawnActor(actor, xRatio, yRatio);
	}
	
	
}

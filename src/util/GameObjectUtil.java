package util;

import java.util.stream.Collectors;

import builders.objectgen.ActorGenerator;
import gamedata.ActorData;
import gamedata.GameData;
import gamedata.LayerData;
import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;

/**
 * Util class for updating Game Objects in back end grid (updating type, spawning, deleting, updating location)
 * @author sarahzhou
 *
 */
public class GameObjectUtil {
	public void deleteGameObject(int id,ControllableGrid grid) {
		grid.removeActor(id);
	}

	public void updateGameObjectType(int id, Integer currentOption, Integer newOption,ControllableGrid grid,GameData gameData) throws VoogaException {
		if (isValidUpdate(currentOption, newOption,gameData)) {
			Grid2D location = grid.getLocationOf(id);
			addGameObject(newOption,location.getX(),location.getY(),gameData,grid);
			deleteGameObject(id,grid);
		} else {
			throw new VoogaException(VoogaException.ILLEGAL_UPDATE);
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

	public int addGameObject(Integer option, double xRatio, double yRatio,GameData gameData,ControllableGrid grid) throws VoogaException{
		ActorData actorData = gameData.getOption(option); 
		if (isPlaceable(actorData.getLayer(),xRatio, yRatio) && grid.isValidLoc(xRatio, yRatio)) return generateActor(actorData, xRatio, yRatio,grid);
		else throw new VoogaException(VoogaException.INVALID_LOCATION);
	}
	
	public boolean isValidUpdate(Integer currentOption, Integer newOption,GameData gameData) {
		if (gameData.getOption(currentOption).getType().equals(gameData.getOption(newOption).getType())) return true;
		return false;
	}
	
	public int generateActor(ActorData actorData, double xRatio, double yRatio,ControllableGrid grid) {
		Actor actor = ActorGenerator.makeActor(IDGenerator.getNewID(),actorData);
		grid.controllerSpawnActor(actor, xRatio, yRatio);
		return actor.getID();
	}
}

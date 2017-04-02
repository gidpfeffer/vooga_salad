package ui.handlers;

import gameengine.actors.management.Actor;
import gameengine.controllers.GameController;

public interface UIHandler {
	/**
	 * @return unique id of actor
	 */
	public int addGameObject(GameController.Actors actor, double x, double y);
	//question: how should we transfer information about the type of actor from front end to back end?
	public void deleteGameObject(int id);
	public void updateGameObjectType(int id,GameController.Actors actor);
	public void updateGameObjectLocation(double x, double y);
	
}

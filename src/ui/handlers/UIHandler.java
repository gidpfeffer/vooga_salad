package ui.handlers;

import gameengine.actors.management.Actor;
import gameengine.controllers.GameController;
import util.VoogaException;

public interface UIHandler {
	/**
	 * @return unique id of actor
	 */
	public int addGameObject(types.ActorType actor, double x, double y);
	//question: how should we transfer information about the type of actor from front end to back end?
	public void deleteGameObject(int id);
	public void updateGameObjectType(int id, types.ActorType currentActor, types.ActorType newActor) throws VoogaException;
	void updateGameObjectLocation(int id, double x, double y);
	
}
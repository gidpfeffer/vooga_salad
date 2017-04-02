package ui.handlers;

import gameengine.actors.management.Actor;

public interface UIHandler {
	/**
	 * @return unique id of actor
	 */
	public int addGameObject(Actor actorType, double x, double y);
	//question: how should we transfer information about the type of actor from front end to back end?
	public void deleteGameObject(int id);
	public void updateGameObjectType(int id,Actor actorType);
	public void updateGameObjectLocation(double x, double y);
	
}

package ui.handlers;

import java.util.List;

import gamedata.compositiongen.Data;
import gameengine.actors.management.Actor;
import gameengine.controllers.GameController;
import util.VoogaException;

public interface UIHandler {
	/**
	 * @return unique id of actor
	 */
	//question: how should we transfer information about the type of actor from front end to back end?
	public void deleteGameObject(int id);
	public void updateGameObjectType(int id, Integer currentOption, Integer newOption) throws VoogaException;
	void updateGameObjectLocation(int id, double x, double y);
	int addGameObject(Integer option, double x, double y);
	
}
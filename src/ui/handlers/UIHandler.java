package ui.handlers;

import java.util.List;
import java.util.Map;

import gamedata.ActorData;
import gamedata.composition.Data;
import gameengine.actors.management.Actor;
import gameengine.controllers.GameController;
import util.VoogaException;

public interface UIHandler {
	/**
	 * @return unique id of actor
	 */
	//question: how should we transfer information about the type of actor from front end to back end?
	void deleteGameObject(int id);
	void updateGameObjectType(int id, Integer currentOption, Integer newOption) throws VoogaException;
	void updateGameObjectLocation(int id, double x, double y) throws VoogaException;
	int addGameObject(Integer option, double x, double y) throws VoogaException;
	Map<Integer,ActorData> getOptions();
	Map<Integer,ActorData> getTroopOptions();
	Map<Integer,ActorData> getShotOptions();
	Map<Integer,ActorData> getBaseOptions();
	void pause();
	void play();
	void stop();
	void exit();
	
}
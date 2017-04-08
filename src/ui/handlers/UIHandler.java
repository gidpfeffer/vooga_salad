package ui.handlers;

import java.util.Map;

import gamedata.ActorData;
import util.VoogaException;

public interface UIHandler {
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
	void changeLevel(int level);
	
}
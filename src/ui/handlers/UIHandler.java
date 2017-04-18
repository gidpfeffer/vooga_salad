package ui.handlers;

import java.util.Map;

import gamedata.ActorData; 
import ui.player.inGame.SimpleHUD;
import util.VoogaException;

/**
 * UIHandler that specifies methods front end can call to evoke changes in back end, and get information from back end
 * @author sarahzhou
 *
 */
public interface UIHandler {
	
	void deleteGameObject(int id);
	void updateGameObjectType(int id, Integer currentOption, Integer newOption) throws VoogaException;
	void updateGameObjectLocation(int id, double x, double y) throws VoogaException;
	int addGameObject(Integer option, double x, double y) throws VoogaException;
	Map<Integer,ActorData> getOptions();
	void pause();
	void play();
	void stop();
	void exit();
	void changeLevel(int level) throws VoogaException;
	SimpleHUD getSimpleHUD();
}
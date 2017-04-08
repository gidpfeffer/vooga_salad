package ui.player;

import java.util.Map;

import gamedata.ActorData;

public class InGameModel {

	private Map<Integer, ActorData> map;
	
	public  Map<Integer, ActorData> getMap() {
		return map;
	}
	
	public  void setMap(Map<Integer, ActorData> map) {
		this.map = map;
	}
}

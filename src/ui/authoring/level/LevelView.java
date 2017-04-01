package ui.authoring.level;

import gamedata.LevelData;

public class LevelView {
	
	private LevelData myLevelData;
	
	public LevelView(){
		myLevelData = new LevelData(); //default object with 
	}
	
	public LevelView(LevelData levelData){
		myLevelData = levelData;
	}
	
}

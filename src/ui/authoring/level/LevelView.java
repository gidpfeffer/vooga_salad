package ui.authoring.level;

import gamedata.LevelData;

public class LevelView {
	
	private LevelData myLevelData;
	//
	public LevelView(){
		myLevelData = new LevelData(0,0); //default object with 
	}
	
	public LevelView(LevelData levelData){
		myLevelData = levelData;
	}
	
}

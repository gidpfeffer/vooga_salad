package gamedata;

import gameengine.grid.ActorGrid;

/**
 * holds all the data that is encoded for a level
 * such as enemies, towers, and map
 * @author Maddie
 *
 */
public class LevelData {
	private ActorGrid myGrid;
	//TODO: add other variables

	//Better way to do this?
	public LevelData(double maxX, double maxY){
		myGrid = new ActorGrid(maxX, maxY);
	}
	
	public ActorGrid getMyGrid() {
		return myGrid;
	}

	public void setMyGrid(ActorGrid myGrid) {
		this.myGrid = myGrid;
	}
	
}

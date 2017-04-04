package gamedata;

/**
 * holds all the data that is encoded for a level
 * such as enemies, towers, and map
 * @author Maddie
 *
 */
public class LevelData {
	private GridData myGrid;
	//TODO: add other variables

	public LevelData(){
		myGrid = new GridData();
	}
	
	public GridData getMyGrid() {
		return myGrid;
	}

	public void setMyGrid(GridData myGrid) {
		this.myGrid = myGrid;
	}
	
}

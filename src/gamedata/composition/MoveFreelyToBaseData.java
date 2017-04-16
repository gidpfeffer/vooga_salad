package gamedata.composition;

import java.util.List;


import gamedata.compositiongen.MoveData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import java.util.Random;

public class MoveFreelyToBaseData extends MoveData {

	private List<Grid2D> allBaseLocations;
	public MoveFreelyToBaseData(List<Grid2D> allBases, double mySpeed) {
		super(mySpeed);
		// TODO Auto-generated constructor stub
		allBaseLocations = allBases; 
	}
	
	public Grid2D getRandomBase(){
		int rand = (new Random()).nextInt(allBaseLocations.size());
		return allBaseLocations.get(rand);
	}
	
	public List<Grid2D> getBaseLocations(){
		return allBaseLocations; 
	}

}

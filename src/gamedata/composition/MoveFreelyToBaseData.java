package gamedata.composition;

import java.util.ArrayList;
import java.util.List;


import gamedata.compositiongen.MoveData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import java.util.Random;

public class MoveFreelyToBaseData extends MoveData {

	private List<Grid2D> allBaseLocations;
	
	public MoveFreelyToBaseData(){
		super(0.0);
		allBaseLocations= new ArrayList<Grid2D>();
		
	}
	
	public MoveFreelyToBaseData(List<Grid2D> allBases, double mySpeed) {
		super(mySpeed);
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

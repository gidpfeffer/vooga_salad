package gameengine.actors.properties;

import java.util.LinkedList;
import java.util.Queue;

import gamedata.composition.MoveWithSetPathData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
/**
 * this property needs the path as list of coordinates. The pathCoordinates list comes 
 * from PathFinder.getPathCoordinates(Integer enemy Path Index,  double increment)  
 * turn that list into a queue, and poll a coordinate to move to at every
 * step. 
 * 
 * @author Anh
 * @author Maddie
 *
 */
public class MoveWithSetPathProperty<G extends ReadAndMoveGrid> implements IActProperty<G>{
	private Queue<Grid2D> myPathCoordinates;
	
	public MoveWithSetPathProperty(MoveWithSetPathData data){
		//Apply random path to current actor
		myPathCoordinates = new LinkedList<>(data.getPath());
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		// TODO Auto-generated method stub
		if (!myPathCoordinates.isEmpty()){
			// poll a coordinate from myPathCoordinates to set the enemy location to
			Grid2D newLoc = myPathCoordinates.poll();
			grid.move(actorID, newLoc.getX(), newLoc.getY()); 
		}
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.properties.IActProperty#isOn()
	 */
	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}

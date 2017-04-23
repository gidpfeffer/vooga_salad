package gameengine.actors.properties;

import gamedata.composition.MoveFreelyToBaseData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class MoveFreelyToBaseProperty<G extends ReadAndMoveGrid> implements IActProperty<G> {
	
	private Grid2D myTarget;
	public MoveFreelyToBaseProperty(MoveFreelyToBaseData data){
		myTarget = data.getRandomBase();
	}
	
	
	@Override
	public void action(G grid, Integer actorID) {
		// TODO Auto-generated method stub
		//move randomly to myTarget 
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return true;
	}

}

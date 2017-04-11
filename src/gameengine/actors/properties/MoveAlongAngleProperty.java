package gameengine.actors.properties;

import gamedata.composition.MoveAlongAngleData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

public class MoveAlongAngleProperty<G extends ReadAndMoveGrid> implements IActProperty<G>{

	public double myPathAngle;
	public double myRange;
	public double mySpeed;
	
	public MoveAlongAngleProperty(MoveAlongAngleData data) {
		myPathAngle = data.getAngle();
		myRange = data.getRange();
		mySpeed = data.getMySpeed();
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}

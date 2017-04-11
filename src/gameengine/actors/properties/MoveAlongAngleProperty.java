package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import util.PathUtil;

public class MoveAlongAngleProperty<G extends MasterGrid> implements IActProperty<G>{

	private double myPathAngle;
	private double myRange;
	private double mySpeed;
	private double distanceTraveled;
	
	public MoveAlongAngleProperty(double angle, double range, double speed) {
		myPathAngle = angle;
		myRange = range;
		mySpeed = speed;
		distanceTraveled = 0;
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		grid.move(actorID, grid.getLocationOf(actorID).getX()+(PathUtil.getXByAngle(myPathAngle)*mySpeed), grid.getLocationOf(actorID).getY()+(PathUtil.getYByAngle(myPathAngle)*mySpeed));
		if((distanceTraveled+=mySpeed)>myRange){
			grid.getMyDamageable(actorID).accept(Double.POSITIVE_INFINITY);
		};
	}

	@Override
	public boolean isOn() {
		return true;
	}

}

package gameengine.actors.properties;

import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import util.PathUtil;

public class MoveAlongAngleProperty<G extends MasterGrid> extends MoveWithRangeProperty<G>{

	private double myPathAngle;
	
	public MoveAlongAngleProperty(double angle, double range, double speed) {
		super(range,speed);
		myPathAngle = angle;
	}
	
	protected void moveProj(G grid, Integer actorID, double speed) {
		grid.move(actorID, grid.getLocationOf(actorID).getX()+(PathUtil.getXByAngle(myPathAngle)*speed), grid.getLocationOf(actorID).getY()+(PathUtil.getYByAngle(myPathAngle)*speed));
	}

}

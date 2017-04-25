package gameengine.actors.properties.move;

import gameengine.grid.interfaces.ActorGrid.MasterGrid;

public class MoveAlongAngleProperty<G extends MasterGrid> extends MoveWithRangeProperty<G>{

	private double myPathAngle;
	
	public MoveAlongAngleProperty(double angle, double range, double speed) {
		super(range,speed);
		myPathAngle = angle;
	}
	
	protected void moveProj(G grid, Integer actorID, double speed) {
		xyDist(grid,actorID,speed,myPathAngle);
	}

}

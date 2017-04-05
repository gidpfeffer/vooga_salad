package gameengine.actors;

import java.util.List;

import gameengine.actors.management.Actor;
import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.actors.properties.MoveWithDestinationProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;

public class Projectile implements Actor {


	private double mySpeed;
	private double finalX;
	private double finalY;
	//private Path myPath;
	
	public Projectile(MoveWithDestinationProperty m, HealthProperty h, List<IActProperty> properties) {	
		//TODO: Complete
		
	}
	
	public Projectile(double endX, double endY) {
		finalX = endX;
		finalY = endY;
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#isActive()
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#act(java.lang.Object)
	 */
	@Override
	public void act(Object grid) {
		// TODO Auto-generated method stub
		
	}
}

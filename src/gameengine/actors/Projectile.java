package gameengine.actors;

import gameengine.grid.interfaces.ActorGrid.MasterGrid;

public class Projectile extends Shot{

	private double mySpeed;
	private double finalX;
	private double finalY;
	//private Path myPath;
	
	public Projectile(double endX, double endY) {
		finalX = endX;
		finalY = endY;
	}
	
	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#act(java.lang.Object)
	 */
	@Override
	public void act(MasterGrid grid) {
		// TODO Auto-generated method stub
		
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
}

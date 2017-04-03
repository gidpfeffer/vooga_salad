package gameengine.actors;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ReadAndMoveGrid;

public class Projectile implements Actor<ReadAndMoveGrid> {

	private double mySpeed;
	//private Path myPath;
	
	public void Projectile() {
		//DO PROJECTILES NEED HEALTH?
	}
	
	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#act(java.lang.Object)
	 */
	@Override
	public void act(ReadAndMoveGrid grid) {
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

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#getHealth()
	 */
	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#setHealth(double)
	 */
	@Override
	public void setHealth(double health) {
		// TODO Auto-generated method stub
		
	}



}

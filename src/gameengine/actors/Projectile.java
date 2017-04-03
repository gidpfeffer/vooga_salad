package gameengine.actors;

import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

import gamedata.ProjectileData;

public class Projectile extends AbstractActor<ReadAndMoveGrid>{

	private double mySpeed;
	//private Path myPath;
	
	public Projectile(ProjectileData proj) {
		//DO PROJECTILES NEED HEALTH?
		//TODO: @Maddie complete
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

	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}
}

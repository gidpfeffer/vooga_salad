package gameengine.actors.towers;

import java.util.List;

import gameengine.actors.Projectile;
import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ReadAndShootGrid;
import gameengine.grid.interfaces.ReadableGrid;

public class ShootTower extends ATower<ReadAndShootGrid> {
	
	private double myShootRadius;
	
	public ShootTower(double shootRadius) {
		super();
		myShootRadius = shootRadius;
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#act(java.lang.Object)
	 */
	@Override
	public void act(ReadAndShootGrid grid) {
		// TODO Auto-generated method stub
		
		Actor<? extends ReadableGrid> target = 
				getEnemyToShoot(grid.getInRadius(grid.getLocationOf(this).getX(), 
												grid.getLocationOf(this).getY(), 
												myShootRadius), grid);
		if (target != null) {
			grid.addProjectile(new Projectile(), grid.getLocationOf(this).getX(), grid.getLocationOf(this).getY());
		} 
	}
	
	@Override
	public void applyDamage(double health) {
		
	}

	private Actor<? extends ReadableGrid> getEnemyToShoot(List<Actor <? extends ReadableGrid>> actorList, ReadableGrid grid) {
		//implementation for getting closes shooter
		double shortestDistance = Double.POSITIVE_INFINITY;
		Actor <? extends ReadableGrid> enemyToShoot = null;
		grid.getLocationOf(this).getX();
		for (Actor<? extends ReadableGrid> actor : actorList) {
			double distanceToActor = Math.sqrt(Math.pow(grid.getLocationOf(this).getX() - grid.getLocationOf(actor).getX(), 2) 
					+ Math.pow(grid.getLocationOf(this).getY() - grid.getLocationOf(actor).getY(), 2));
			if (distanceToActor < shortestDistance) { //also need to check if actor is an enemy
				shortestDistance = 0;
				enemyToShoot = actor;
			}
		}
		return enemyToShoot;
		
	}



}

package gameengine.actors;

import java.util.List;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ReadAndShootGrid;
import gameengine.grid.interfaces.ReadableGrid;

public class Tower implements Actor <ReadAndShootGrid> {
	
	private double myShootRadius;
	private boolean isActive;
	private double myHealth;
	
	public Tower(double shootRadius, double startingHealth) {
		myShootRadius = shootRadius;
		myHealth = startingHealth;
		isActive = true;
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

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#isActive()
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return isActive;
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#getHealth()
	 */
	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return myHealth;
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#setHealth(double)
	 */
	@Override
	public void setHealth(double health) {
		// TODO Auto-generated method stub
		myHealth = health;
		
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

package gameengine.actors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import gameengine.actors.management.Actor;
import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.actors.properties.MoveWithDestinationProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import types.ActorType;

public class Projectile extends MainActor {
	
	private boolean attacksEnemy;
	private boolean attacksTower;
	private boolean attacksBase;
	private final Integer myID;
	private double myHitRadius;
	private double myPower;
	private HealthProperty myHealth;
	private MoveWithDestinationProperty myMovement;
	
	
	public Projectile(Integer id, MoveWithDestinationProperty movement, HealthProperty health, 
			boolean attacksEnemy, boolean attacksTower, boolean attacksBase, double power, double hitRadius) {
		
		//IS THIS RIGHT??
		super((BasicActorType) ActorType.PROJECTILE, id, health, new IActProperty<MasterGrid>[] { movement });
		
		myHitRadius = hitRadius;
		myPower = power;
		this.attacksBase = attacksBase;
		this.attacksEnemy = attacksEnemy;
		this.attacksTower = attacksTower;
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#act(java.lang.Object)
	 */
	@Override
	public void act(MasterGrid grid) {
		// TODO Auto-generated method stub
		if (attacksEnemy) {
			Collection<Actor> enemies = new ArrayList<>();// grid.getEnemiesInRadius() to apply damage
			attack(enemies);
		}
		if (attacksTower) {
			Collection<Actor> towers = new ArrayList<>();// grid.getTowersInRadius() to apply damage
			attack(towers);
		}
		if (attacksBase) {
			Collection<Actor> base = new ArrayList<>();// grid.getTowersInRadius() to apply damage
			attack(base);
		}
		
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#isActive()
	 */
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return myHealth.isAlive();
	}

	private void attack(Collection<Actor> actors) {
		if (!isActive()) return;
		for (Actor a : actors) {
			if (isActive()) {
				a.applyDamage();
				myHealth.apply(1);
			} else {
				break;
			}
		}
	}
}
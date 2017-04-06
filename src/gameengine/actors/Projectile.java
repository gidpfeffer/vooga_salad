package gameengine.actors;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import gameengine.actors.management.Actor;
import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.MoveWithDestinationProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadShootMoveGrid;

public class Projectile extends AbstractActor {
	
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
		myID = id;
		myHealth = health;
		myMovement = movement;
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
			Collection<Actor<?>> enemies = new ArrayList<>();// grid.getEnemiesInRadius() to apply damage
			attack(enemies);
		}
		if (attacksTower) {
			Collection<Actor<?>> towers = new ArrayList<>();// grid.getTowersInRadius() to apply damage
			attack(towers);
		}
		if (attacksBase) {
			Collection<Actor<?>> base = new ArrayList<>();// grid.getTowersInRadius() to apply damage
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

	/* (non-Javadoc)
	 * @see gameengine.actors.management.Actor#applyDamage(double)
	 */
	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}
	
	private void attack(Collection<Actor<?>> actors) {
		if (!isActive()) return;
		for (Actor<?> a : actors) {
			if (isActive()) {
				a.applyDamage(myPower);
				myHealth.apply(1);
			} else {
				break;
			}
		}
	}
}
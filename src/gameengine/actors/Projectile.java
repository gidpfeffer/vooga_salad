package gameengine.actors;
import java.util.Collection;

import gameengine.actors.properties.MoveWithDestinationProperty;
import gameengine.actors.propertygen.HealthProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;

public class Projectile extends MainActor {
	
	private boolean attacksEnemy;
	private boolean attacksTower;
	private boolean attacksBase;
	private double myHitRadius;
	private double myPower;
	private HealthProperty myHealth;
	private MoveWithDestinationProperty myMovement;
	
	
	@SuppressWarnings("unchecked")
	public Projectile(Integer id, HealthProperty health, MoveWithDestinationProperty movement, 
			boolean attacksEnemy, boolean attacksTower, boolean attacksBase, double power, double hitRadius) {
		
		//IS THIS RIGHT??
		super(BasicActorType.Shot, id, health, movement);
		
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
			Collection<Grid2D> enemies = grid.getActorLocationsInRadius(grid.getLocationOf(myID).getX(), grid.getLocationOf(myID).getY(),
					myHitRadius, BasicActorType.Troop);
			attack(enemies);
		}
		if (attacksTower) {
			Collection<Grid2D> towers = grid.getActorLocationsInRadius(grid.getLocationOf(myID).getX(), grid.getLocationOf(myID).getY(),
					myHitRadius, BasicActorType.Tower);
			attack(towers);
		}
		if (attacksBase) {
			Collection<Grid2D> base = grid.getActorLocationsInRadius(grid.getLocationOf(myID).getX(), grid.getLocationOf(myID).getY(),
					myHitRadius, BasicActorType.Base);
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

	private void attack(Collection<Grid2D> actors) {
		if (!isActive()) return;
		for (Grid2D a : actors) {
			if (isActive()) {
				//apply damage to actor
				myHealth.apply(1);
			} else {
				break;
			}
		}
	}
}
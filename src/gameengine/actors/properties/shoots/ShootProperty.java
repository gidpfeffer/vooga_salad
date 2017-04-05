package gameengine.actors.properties.shoots;

import java.util.List;

import gamedata.composition.ShootData;
import gameengine.actors.Projectile;
import gameengine.actors.management.Actor;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndShootGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class ShootProperty<G extends ReadAndShootGrid> implements IActProperty<G>{

	private ShootData myData;
	
	public ShootProperty(ShootData data) {
		myData = data;
	}
	@Override
	public void action(G grid, Integer actorID) {
		Grid2D dirCoordinates= getEnemyToShoot(grid.getEnemiesInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getX(), myRange));
		grid.addProjectile(new Projectile(dirCoordinates.getX(), dirCoordinates.getY()), drid., startY);
	}
	
	private Actor<? extends ReadableGrid> getEnemyToShoot(List<Actor <? extends ReadableGrid>> actorList, ReadableGrid grid) {
		//implementation for getting closes shooter
		double shortestDistance = Double.POSITIVE_INFINITY;
		Actor <? extends ReadableGrid> enemyToShoot = null;
		//grid.getLocationOf(this).getX();
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
	
	public double getRange(){
		return myData.getMyRange();
	}

}

package gameengine.actors.properties;

import java.util.Collection;

import gamedata.composition.MoveWithDestinationData;
import gamedata.composition.ShootData;
import gameengine.actors.Projectile;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;

public class ShootProperty implements IActProperty<MasterGrid>{

	private ShootData myData;
	
	public ShootProperty(ShootData data) {
		myData = data;
	}
	
	
	public double getRange(){
		return myData.getRange();
	}
	/* (non-Javadoc)
	 * @see gameengine.actors.properties.IActProperty#action(gameengine.grid.interfaces.ActorGrid.ReadableGrid, java.lang.Integer)
	 */
	@Override
	public void action(MasterGrid grid, Integer actorID) {
		//HOW DOE WE KNOW WHICH TYPE OF ACTOR TO PASS TO GET ENEMY TO SHOOT
		Grid2D dirCoordinates = getActorToShoot(grid.getActorLocationsInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getX(), myData.getRange(), BasicActorType.Troop), grid.getLocationOf(actorID));
		
		MoveWithDestinationProperty movement = new MoveWithDestinationProperty
				(new MoveWithDestinationData(myData.getSpeed(), grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getY(), 
						dirCoordinates.getX(), dirCoordinates.getY()));
		//HOW DO WE SPAWN A PROJECTILE WITH THE CORRECT PARAMETERS
		grid.spawn(new Projectile(actorID, null, movement, false, false, false, actorID, actorID), grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getX());
		
	}
	
	private Grid2D getActorToShoot(Collection<Grid2D> actorLocations, Grid2D myLocation) {
		/*
		 * if (targetType == closest)
		 * 		return getClosest
		 * else if (targetType == strongest)
		 * 		return getStrongest
		 * else if (targetType == first)
		 * 		return first
		 * else if (targetType == last)
		 * 		return last
		 */
		//implementation for getting closest shooter
		return getClosestActorToShoot(actorLocations, myLocation);
		
	}
	
	private Grid2D getClosestActorToShoot(Collection<Grid2D> actorLocations, Grid2D myLocation) {
		double shortestDistance = Double.POSITIVE_INFINITY;
		Grid2D closestTarget = null;
		for (Grid2D loc : actorLocations) {
			double distanceToActor = Math.sqrt(Math.pow(loc.getX() - myLocation.getX(), 2) 
					+ Math.pow(loc.getY() - myLocation.getY(), 2));
			if (distanceToActor < shortestDistance) {
				shortestDistance = distanceToActor;
				closestTarget = loc;
			}
		}
		return closestTarget;
		
	}
	
	
	/* (non-Javadoc)
	 * @see gameengine.actors.properties.IActProperty#isOn()
	 */
	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

}

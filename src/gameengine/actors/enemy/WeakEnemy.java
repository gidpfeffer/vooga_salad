package gameengine.actors.enemy;

import gameengine.ActorResult;

public class WeakEnemy extends Enemy {
	private List<Point2D> myPathCoordinates;

	/** 
	 * In the constructor, the pathCoordinates list comes from 
	 * PathFinder.getPathCoordinates(Integer enemy Path Index,  double enemySpeed) 
	 * @param pathCoordinates
	 */
	public WeakEnemy (List<Point2D> pathCoordinates){ 
		myPathCoordinates = pathCoordinates;	
	}
	
	@Override
	public ActorResult act(Object out) {
		// TODO Auto-generated method stub
		
		// pop a coordinate from myPathCoordinates to set the enemy location to 
		
		
		return null;
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHealth(double health) {
		// TODO Auto-generated method stub
		
	}
	
}

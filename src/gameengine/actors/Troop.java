package gameengine.actors;

import gameengine.actors.management.Actor;

public abstract class Troop<G> implements Actor<G>{

	@Override
	public void act(Object out) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public double getHealth() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void setHealth(double health) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}

}

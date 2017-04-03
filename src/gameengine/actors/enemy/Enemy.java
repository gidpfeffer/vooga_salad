package gameengine.actors.enemy;

import java.util.List;

import gameengine.ActorResult;
import usecases.IActor;

public abstract class Enemy <O> implements IActor<O> {
		
	@Override
	public abstract ActorResult act(O out) ;
		// TODO Auto-generated method stub
	

	@Override
	public abstract void deactivate() ;
		// TODO Auto-generated method stub
		
	

	@Override
	public abstract boolean isActive();

	@Override
	public abstract double getHealth() ;
		// TODO Auto-generated method stub


	@Override
	public abstract void setHealth(double health);
		// TODO Auto-generated method stub
	
}

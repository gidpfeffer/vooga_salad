package gameengine.grid.testers;

import java.util.function.Consumer;

import gameengine.actors.management.Actor;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import types.BasicActorType;

public class TestActor implements Actor{
	boolean alive;
	
	public TestActor(){
		alive = true;
	}

	@Override
	public void act(MasterGrid grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		return alive;
	}

	@Override
	public Consumer<Double> applyDamage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consumer<IActProperty<MasterGrid>> addProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BasicActorType getType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deactivate(){
		
	}

	@Override
	public Integer getMyOption() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPercentHealth() {
		// TODO Auto-generated method stub
		return null;
	}

}

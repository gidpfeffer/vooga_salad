package gameengine.actors;

import java.util.List;

import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public class Base<G extends ReadableGrid> extends AbstractActor<G>{
	
	public Base(Integer id, HealthProperty h, List<IActProperty> properties){
		super(id, h);
	}

	@Override
	public void act(G grid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}
}

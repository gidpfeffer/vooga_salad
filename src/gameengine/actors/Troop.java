package gameengine.actors;

import java.util.ArrayList;
import java.util.List;

import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public class Troop<G extends ReadableGrid> extends AbstractActor<G> {

	private List<IActProperty<G>> myProperties; 
	private int myID;
	
	public Troop(Integer id, HealthProperty h, IActProperty<G>... properties) {
		super(id, h);
		myProperties = new ArrayList<>();
		for (IActProperty<G> p:properties) {
			myProperties.add(p);
		}
	}



	@Override
	public void act(G grid) {
		myProperties.forEach(e -> e.action(grid,myID));
	}
	
	
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}




	
}


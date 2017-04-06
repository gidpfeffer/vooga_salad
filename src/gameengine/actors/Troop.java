package gameengine.actors;

import java.util.ArrayList;
import java.util.List;

import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;

public class Troop extends AbstractActor {

	private List<IActProperty<MasterGrid>> myProperties; 
	private int myID;
	
	public Troop(Integer id, HealthProperty h, IActProperty<MasterGrid>... properties) {
		super(id, h);
		myProperties = new ArrayList<>();
		for (IActProperty<MasterGrid> p:properties) {
			myProperties.add(p);
		}
	}



	@Override
	public void act(MasterGrid grid) {
		System.out.println("I tried");
		myProperties.forEach(e -> e.action(grid,myID));
	}
	
	
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}




	
}


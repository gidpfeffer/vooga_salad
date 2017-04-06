package gameengine.actors;

import gameengine.actors.properties.HealthProperty;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
public class Base extends AbstractActor {
	
	public Base(Integer id, HealthProperty h, IActProperty<MasterGrid>... properties){
		super(id, h);
	}
	
	@Override
	public void applyDamage(double health) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void act(MasterGrid grid) {
		// TODO Auto-generated method stub
		
	}
}

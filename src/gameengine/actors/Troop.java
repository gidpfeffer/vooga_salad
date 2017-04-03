package gameengine.actors;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gamedata.TroopData;

public abstract class Troop <T extends ReadableGrid> extends AbstractActor<T> {

	public Troop(TroopData troop){
		//TODO: @Maddie complete
	}
	
	@Override
	public void act(T out) {
		// TODO Auto-generated method stub
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
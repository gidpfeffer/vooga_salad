package gameengine.grid.Test;


import gameengine.actors.Troop;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

public class TestEnemy extends Troop<ReadAndMoveGrid>{

	public TestEnemy(){
		
	}
	
	@Override
	public void act(ReadAndMoveGrid grid) {
		
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
package gameengine.conditions;

import gameengine.conditionsgen.WinLoseCondition;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gamestatus.ReadableGameStatus;

public class KillAllCondition<G extends ReadableGrid> extends WinLoseCondition<G> {

	public KillAllCondition(){
		//TODO: Complete
	}
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean winCondition(G grid, ReadableGameStatus status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean loseCondition(G grid, ReadableGameStatus status) {
		// TODO Auto-generated method stub
		return false;
	}

}

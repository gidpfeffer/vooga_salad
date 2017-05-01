package gameengine.conditions;

import gameengine.conditionsgen.WinLoseCondition;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gamestatus.ReadableGameStatus;

public class KillAllCondition extends WinLoseCondition {
	
	@Override
	protected void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean winCondition(ReadableGameStatus status) {
		return status.getEnemiesLeft()==0;
	}

	@Override
	protected boolean loseCondition(ReadableGameStatus status) {
		return status.getLives()==0;
	}

}

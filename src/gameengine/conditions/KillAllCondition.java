package gameengine.conditions;

import gameengine.conditionsgen.WinLoseCondition;
import gamestatus.ReadableGameStatus;

public class KillAllCondition extends WinLoseCondition {
	
	@Override
	protected void update() {
		
	}

	@Override
	protected boolean winCondition(ReadableGameStatus status) {
		return status.getEnemiesLeft()<1;
	}

	@Override
	protected boolean loseCondition(ReadableGameStatus status) {
		return status.getLives()<1;
	}

}

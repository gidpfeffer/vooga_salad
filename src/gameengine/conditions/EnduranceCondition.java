package gameengine.conditions;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gamestatus.GameStatus;

public class EnduranceCondition<G extends ReadableGrid> extends WinLoseCondition<G> {

	private Integer myFinalFrame;
	private Integer myCurrentFrame;
	
	public EnduranceCondition(Integer duration) {
		myFinalFrame = duration;
		myCurrentFrame = 0;
	}
	
	@Override
	protected void update() {
		myCurrentFrame++;
	}

	@Override
	protected boolean winCondition(G grid, GameStatus status) {
		return myCurrentFrame>myFinalFrame;
	}

	@Override
	protected boolean loseCondition(G grid, GameStatus status) {
		return status.getLives()>0;
	}

}

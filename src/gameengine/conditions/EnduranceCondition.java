package gameengine.conditions;

import gameengine.conditionsgen.WinLoseCondition;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gamestatus.ReadableGameStatus;

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
	protected boolean winCondition(G grid, ReadableGameStatus status) {
		return myCurrentFrame>myFinalFrame;
	}

	@Override
	protected boolean loseCondition(G grid, ReadableGameStatus status) {
		return status.getLives()>0;
	}

}

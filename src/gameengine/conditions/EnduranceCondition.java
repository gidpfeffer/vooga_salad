package gameengine.conditions;

import gameengine.conditionsgen.WinLoseCondition;
import gamestatus.ReadableGameStatus;

public class EnduranceCondition extends WinLoseCondition {

	private Integer myFinalFrame;
	private Integer myCurrentFrame;
	
	public EnduranceCondition(){
		this(400);
	}
	
	public EnduranceCondition(Integer duration) {
		myFinalFrame = duration;
		myCurrentFrame = 0;
	}
	
	@Override
	protected void update() {
		myCurrentFrame++;
	}

	@Override
	protected boolean winCondition(ReadableGameStatus status) {
		return myCurrentFrame>myFinalFrame;
	}

	@Override
	protected boolean loseCondition(ReadableGameStatus status) {
		return status.getLives()<1;
	}

}

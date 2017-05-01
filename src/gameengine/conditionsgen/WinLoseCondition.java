package gameengine.conditionsgen;

import java.util.Optional;

import gamestatus.ReadableGameStatus;

public abstract class WinLoseCondition implements Condition{
	
	public Optional<Boolean> conditionSatisfied(ReadableGameStatus status) {
		update();
		return (winCondition(status)||loseCondition(status) ? Optional.of(winCondition(status)):Optional.empty());
	} 
	
	protected abstract void update();
	
	protected abstract boolean winCondition(ReadableGameStatus status);
	
	protected abstract boolean loseCondition(ReadableGameStatus status);

}

package gameengine.conditionsgen;

import java.util.Optional;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gamestatus.GameStatus;
import gamestatus.ReadableGameStatus;

public abstract class WinLoseCondition<G extends ReadableGrid> implements Condition<G>{
	
	public Optional<Boolean> conditionSatisfied(G grid, ReadableGameStatus status) {
		update();
		return (winCondition(grid,status)||loseCondition(grid,status) ? Optional.of(winCondition(grid,status)):Optional.empty());
	} 
	
	protected abstract void update();
	
	protected abstract boolean winCondition(G grid, ReadableGameStatus status);
	
	protected abstract boolean loseCondition(G grid, ReadableGameStatus status);

}

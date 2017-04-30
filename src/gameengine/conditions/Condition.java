package gameengine.conditions;

import java.util.Optional;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gamestatus.GameStatus;

/**
 * Condition class defining 
 * 
 * @author Moses Wayne
 * @author maddiebriere
 */
public interface Condition<G extends ReadableGrid> {
	public Optional<Boolean> conditionSatisfied(G grid, GameStatus status);
}

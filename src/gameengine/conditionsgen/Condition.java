package gameengine.conditionsgen;

import java.util.Optional;

import gamestatus.ReadableGameStatus;

/**
 * Condition class defining 
 * 
 * @author Moses Wayne
 * @author maddiebriere
 */
public interface Condition {
	public Optional<Boolean> conditionSatisfied(ReadableGameStatus status);
}

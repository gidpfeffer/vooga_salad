package gameengine.actors.management;

import java.util.Collection;
import java.util.function.Consumer;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import types.BasicActorType;

/**
 * Interface delineating the basic functionality of a given Actor in the
 * VOOGASalad game suite. Actors are the basic unit of gameplay in the game
 * suite, indicating individual entities, such as towers, enemies, projectiles,
 * etc. Actors determine their type when constructed, allowing for
 * differentiation through the getType method.
 * 
 * @author Moses Wayne
 *
 */
public interface Actor {

	/**
	 * Primary method for the Actor interface that specifies how the Actor
	 * interacts with the grid.
	 * 
	 * @param grid
	 *            all access grid that is limited in permissions when the
	 *            composition elements of the Actor class are called
	 */
	public void act(MasterGrid grid);
	
	public void exit(MasterGrid grid);
	
	public void changeExit(Consumer<Collection<IActProperty<MasterGrid>>> action);

	/**
	 * Method to specify if an actor is still active in the game
	 * 
	 * @return boolean on whether the Actor is still an active element of the
	 *         game
	 */
	public boolean isActive();

	/**
	 * Method that delivers a consumer with access to the health property of a
	 * given Actor. The Consumer delivered can be used with the function call of
	 * .accept(double). Prevents having to send entire actor to deal damage
	 * 
	 * @return consumer that applies damage to this Actor
	 */
	public Consumer<Double> applyDamage();

	/**
	 * Method that delivers a consumer with access to the IActProperties of this
	 * Actor. Allows receiver of the consumer to add properties to the current
	 * number in the Actor. Encapsulates the actor.
	 * 
	 * @return consumer that allows for the addition of properties
	 */
	public Consumer<IActProperty<MasterGrid>> addProperty();

	/**
	 * Method that allows the grid to identify various actors
	 * 
	 * @return Integer representation of the ID of a given Actor
	 */
	public Integer getID();

	/**
	 * Method that allows the grid and other Actors to identify the "type" of
	 * actor that a given actor is. Useful for when games have "sides".
	 * 
	 * @return proprietary enum type of the actor
	 */
	public BasicActorType getType();

	/**
	 * 
	 * @return Integer denoting the "type" of actor that a given actor is with
	 *         respect to the gamedata
	 */
	public Integer getMyOption();
	
	public double getPercentHealth();
	
	public double getRemainingHealth();
}

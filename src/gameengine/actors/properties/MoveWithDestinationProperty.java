package gameengine.actors.properties;

import gamedata.composition.MoveWithDestinationData;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
/**
 * Used in projectiles -- for items that move with a set final
 * location and action at that point
 * 
 * @author maddiebriere
 *
 */

public class MoveWithDestinationProperty implements IActProperty<ReadAndMoveGrid>{
	
	public MoveWithDestinationProperty(MoveWithDestinationData data){
		//TODO: Implement
	}
	
	@Override
	public void action(ReadAndMoveGrid grid, Integer actorID) {
		// TODO Auto-generated method stub
		
	}
	
	

}

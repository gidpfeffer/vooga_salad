package gameengine.grid.Test;

import gameengine.actors.Troop;
import gameengine.grid.ActorGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;

public class Test {
	
	public static void main(String[] args){
		ActorGrid grid = new ActorGrid(100, 100);
		Troop<ReadAndMoveGrid> enemy = new TestEnemy();
		grid.addEnemy(enemy, 100, 10.0, 10.0);
	}

}


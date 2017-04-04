package gameengine.grid.Test;

import gameengine.actors.Troop;
import gameengine.grid.ActorGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class Test {
	
	public static void main(String[] args){
		Test test = new Test();
		
		ActorGrid grid = new ActorGrid(100, 100);
		
		// won't work because enemy implements Actor<ReadAndMoveGrid>
		
		// Troop<ReadableGrid> enemy = new TestEnemy();
		
		//will work because enemy 
		Troop<ReadAndMoveGrid> enemy = new TestEnemy();
		grid.addEnemy(enemy, 100, 10.0, 10.0);
		Grid2D loc = grid.getLocationOf(100);
		
		//test.Print2D(loc);
		for(Grid2D g: grid.getTowersInRadius(10.0, 12.0, 5)){
			test.Print2D(g);
		}
	}
	
	public void Print2D(Grid2D loc){
		System.out.println("x: " + loc.getX() + ", y: " + loc.getY());
	}

}


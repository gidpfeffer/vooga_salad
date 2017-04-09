package gameengine.grid.testers.actorgridtesters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gameengine.grid.ActorGrid;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.testers.TestActor;
import types.BasicActorType;

public class ReadableGridTest {
	
	private MasterGrid myGrid = null;
	private final int actorID = 3;
	
    public void setUp () {
       myGrid = new ActorGrid(1, 1, i -> new TestActor(i));
       myGrid.actorSpawnActor(actorID, 5, 20);
    }
    
    // tests the locational ability of finding actors on the grid
	@Test
	public void locationTest() {
		setUp();
		ReadableGrid grid = myGrid;
		assertEquals(grid.getLocationOf(actorID).getX(), 5, 0);
		assertEquals(grid.getLocationOf(actorID).getY(), 20, 0);
	}
	
    // tests the getActorLocations
	@Test
	public void actorTypeFilteringTest() {
		setUp();
		ReadableGrid grid = myGrid;
		assertEquals(grid.getActorLocations(BasicActorType.Troop).size(), 1);
		assertEquals(grid.getActorLocations(BasicActorType.Tower).size(), 0);
		assertEquals(grid.getActorLocations(BasicActorType.Troop).iterator().next().getX(), 5, 0);
		assertEquals(grid.getActorLocations(BasicActorType.Troop).iterator().next().getY(), 20, 0);
	}
	
    // tests the getActorLocationsInRadius
	@Test
	public void radiusTest() {
		setUp();
		ReadableGrid grid = myGrid;
		assertEquals(grid.getActorLocationsInRadius(5, 25, 5, BasicActorType.Troop).size(), 1);
		assertEquals(grid.getActorLocationsInRadius(5, 25, 4.999, BasicActorType.Troop).size(), 0);
		assertEquals(grid.getActorLocationsInRadius(5, 20, 100, BasicActorType.Tower).size(), 0);
	}
	
    // tests the boundary system
	@Test
	public void boundaryTest() {
		setUp();
		ReadableGrid grid = myGrid;
		assertEquals(grid.isValidLoc(-1, -1), false);
		assertEquals(grid.isValidLoc(1.5, 0), false);
		assertEquals(grid.isValidLoc(0, 0), true);
		assertEquals(grid.isValidLoc(1, 1), true);
		assertEquals(grid.isValidLoc(1, 0), true);
		assertEquals(grid.isValidLoc(0, 1), true);
	}

}

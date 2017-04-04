package gameengine.grid.interfaces.controllergrid;


import gameengine.actors.Base;
import gameengine.actors.Shot;
import gameengine.actors.Troop;
import gameengine.actors.towers.ATower;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface ControllableGrid {

	void addEnemy(Troop<ReadableGrid> enemy, int ID, double startX, double startY);
	void addProjectile(Shot<ReadableGrid> shot, int ID, double startX, double startY);
	void addBase(Base<ReadableGrid> base, int ID, double startX, double startY);
	void addTower(ATower<ReadableGrid> tower, int ID, double startX, double startY);
	
	
}

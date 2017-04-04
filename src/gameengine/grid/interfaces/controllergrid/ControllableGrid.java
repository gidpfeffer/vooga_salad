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
	void removeActor(int ID);
	void upgradeEnemy(Troop<ReadableGrid> newEnemy, int ID);
	void upgradeProjectile(Shot<ReadableGrid> newShot, int ID);
	void upgradeBase(Base<ReadableGrid> newBase, int ID);
	void upgradeTower(ATower<ReadableGrid> newTower, int ID);
	
}

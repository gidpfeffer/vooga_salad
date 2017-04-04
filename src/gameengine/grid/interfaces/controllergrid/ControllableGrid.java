package gameengine.grid.interfaces.controllergrid;


import gameengine.actors.Base;
import gameengine.actors.Shot;
import gameengine.actors.Troop;
import gameengine.actors.towers.ATower;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface ControllableGrid {

	void addEnemy(Troop<? extends ReadableGrid> enemy, int ID, double startX, double startY);
	void addProjectile(Shot<? extends ReadableGrid> shot, int ID, double startX, double startY);
	void addBase(Base<? extends ReadableGrid> base, int ID, double startX, double startY);
	void addTower(ATower<? extends ReadableGrid> tower, int ID, double startX, double startY);
	void removeActor(int ID);
	void upgradeEnemy(Troop<? extends ReadableGrid> newEnemy, int ID);
	void upgradeProjectile(Shot<? extends ReadableGrid> newShot, int ID);
	void upgradeBase(Base<? extends ReadableGrid> newBase, int ID);
	void upgradeTower(ATower<? extends ReadableGrid> newTower, int ID);
	
}

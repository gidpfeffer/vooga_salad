package gameengine.grid.interfaces.controllergrid;


import gameengine.actors.Base;
import gameengine.actors.Shot;
import gameengine.actors.Troop;
import gameengine.actors.towers.Tower;

public interface ControllableGrid {

	void addEnemy(Troop enemy, int ID, double startX, double startY);
	void addProjectile(Shot shot, int ID, double startX, double startY);
	void addBase(Base base, int ID, double startX, double startY);
	void addTower(Tower tower, int ID, double startX, double startY);
	void removeActor(int ID);
	void upgradeEnemy(Troop newEnemy, int ID);
	void upgradeProjectile(Shot newShot, int ID);
	void upgradeBase(Base newBase, int ID);
	void upgradeTower(Tower newTower, int ID);
	
}

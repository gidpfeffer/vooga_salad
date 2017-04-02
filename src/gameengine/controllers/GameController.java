package gameengine.controllers;

public interface GameController {
	public enum Actors {TOWER,SMART_ENEMY,DUMB_ENEMY};
	//possible problem = trying to upgrade tower into a smart enemy?
	public void step();
}

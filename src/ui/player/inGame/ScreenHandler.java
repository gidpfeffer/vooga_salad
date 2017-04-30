package ui.player.inGame;

import gamedata.ActorData;

public interface ScreenHandler {
	public void createActor(double x, double y, int option,ActorData actorData );
	public void showError(String msg);
	public void addActorToMap(int id, Actor actor);
	public void deleteActorFromScreen(int id);
	public boolean isActorInMap(int id);
}

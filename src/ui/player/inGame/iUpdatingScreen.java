package ui.player.inGame;

import java.util.Map;

import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;

public interface iUpdatingScreen {

	public void update(Map<Integer, FrontEndInformation> arg);
	
}

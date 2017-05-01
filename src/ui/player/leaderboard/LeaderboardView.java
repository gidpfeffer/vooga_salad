package ui.player.leaderboard;

import javafx.scene.Scene;
import ui.handlers.LoginHandler;
import ui.player.login.LoginElement;

public class LeaderboardView implements LoginElement {

	private Scene scene;
	private LoginHandler loginhandler;
	
	public LeaderboardView(LoginHandler loginhandler) {
		this.loginhandler = loginhandler;
		scene = new Scene(null);
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}

}

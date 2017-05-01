package ui.player.leaderboard;

import java.util.Iterator;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import ui.general.CustomColors;
import ui.handlers.LoginHandler;
import ui.player.login.LoginElement;
import ui.player.users.User;

public class LeaderboardView implements LoginElement {

	private Scene scene;
	private LoginHandler loginhandler;
	
	public LeaderboardView(LoginHandler loginhandler) {
		this.loginhandler = loginhandler;
		
		scene = new Scene(setupGrid());
	}
	
	private GridPane setupGrid() {
		GridPane scores = new GridPane();
		scores.setVgap(10);
		scores.setHgap(20);
		scores.setAlignment(Pos.TOP_CENTER);
		scores.setBackground(new Background(new BackgroundFill(CustomColors.INDIGO, new CornerRadii(3.5), null));
		//scores.setBackground(value);
		
		Text username = new Text("Username");
		username.setStyle("-fx-font-size: 40; -fx-fill: black");
		Text experience = new Text("Experience");
		experience.setStyle("-fx-font-size: 40; -fx-fill: black");
		scores.add(username, 0, 0);
		scores.add(experience, 1, 0);
		
		Iterator<User> users = loginhandler.getUsersInExpOrder();
		
		int row = 1;
		while(users.hasNext()) {
			User user = users.next();
			username = new Text(user.getUsername());
			username.setStyle("-fx-font-size: 30; -fx-fill: black");
			experience = new Text(user.getExperience() + "");
			experience.setStyle("-fx-font-size: 30; -fx-fill: black");
			
			scores.add(username, 0, row);
			scores.add(experience, 1, row);
			row++;
		}
		
		return scores;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}

}

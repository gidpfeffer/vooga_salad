package ui.player.leaderboard;

import java.util.Iterator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
	
	private ScrollPane setupGrid() {
		
		GridPane scores = new GridPane();
		scores.setAlignment(Pos.TOP_CENTER);
		scores.setVgap(10);
		scores.setHgap(40);
		
		Text username = new Text("Username");
		username.setStyle("-fx-font-size: 40; -fx-fill: black");
		username.setUnderline(true);
		scores.add(username, 0, 0);
		
		Text experience = new Text("Experience");
		experience.setStyle("-fx-font-size: 40; -fx-fill: black");
		experience.setUnderline(true);
		scores.add(experience, 1, 0);
		
		Iterator<User> users = loginhandler.getUsersInExpOrder();
		
		int row = 1;
		while(users.hasNext()) {
			User user = users.next();
			if(user.getUsername().equals("Guest")) {
				continue;
			}
			
			username = new Text(user.getUsername());
			username.setStyle("-fx-font-size: 30; -fx-fill: black");
			scores.add(username, 0, row);

			experience = new Text(user.getExperience() + "");
			experience.setStyle("-fx-font-size: 30; -fx-fill: black");
			scores.add(experience, 1, row);
			
			row++;
		}
		Hyperlink returnToMain = new Hyperlink("Return to main");
		returnToMain.setOnAction(e -> loginhandler.returnToMain());
		scores.add(returnToMain, 0, row);
		
		ScrollPane board = new ScrollPane(scores);
		board.setFitToHeight(true);
		board.setFitToWidth(true);
		board.setBackground(new Background(
				new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
		
		return board;
	}
	
	@Override
	public Scene getScene() {
		return scene;
	}
}

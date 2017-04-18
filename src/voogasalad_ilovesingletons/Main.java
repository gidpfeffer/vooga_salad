package voogasalad_ilovesingletons;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.player.login.Login;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		s.getIcons().add(new Image("tower_icon.png"));
		Login login = new Login(s, "loginScreen.css", "login");
		s.setScene(login.getScene());
		s.setTitle("Login");
		s.setResizable(true);
		s.show();
	}
}

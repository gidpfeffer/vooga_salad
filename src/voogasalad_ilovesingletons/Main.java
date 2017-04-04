package voogasalad_ilovesingletons;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.UIMain;
import ui.player.Login;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage s) throws Exception {
		Login login = new Login(s, "loginScreen.css", "login");
		s.setScene(login.getScene());
		s.setTitle("Login");
		s.show();
	}
}

package voogasalad_ilovesingletons;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ui.player.UserDatabase;
import ui.player.XStreamFileChooser;
import ui.player.login.LoginMain;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage s) throws Exception {
		s.getIcons().add(new Image("tower_icon.png"));
		UserDatabase database = getUserDatabase();
		new LoginMain(database, s, "loginScreen.css", "login");
		s.setTitle("Login");
		s.setResizable(true);
		s.show();
	}

	private UserDatabase getUserDatabase() {
		XStream mySerializer = new XStream(new DomDriver());
		XStreamFileChooser fileChooser = new XStreamFileChooser("userDatabase.xml");
		return (UserDatabase) mySerializer.fromXML(fileChooser.readInClass());
	}
}

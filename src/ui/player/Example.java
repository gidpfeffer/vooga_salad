package ui.player;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.UIMain;

public class Example extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage s) throws Exception {
		s.setScene(new Scene(new Group(), 200 ,200));
		s.setTitle("Title");
		s.setResizable(false);
		s.show();
	}
}


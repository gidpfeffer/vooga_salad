package voogasalad_ilovesingletons;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.UIMain;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	

	@Override
	public void start(Stage s) throws Exception {
		UIMain view = new UIMain("English");
		s.setScene(view.getScene());
		s.setTitle("VOOGASalad");
		//s.setResizable(false);
		s.show();
	}
}

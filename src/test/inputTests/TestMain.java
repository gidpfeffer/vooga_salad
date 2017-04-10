package test.inputTests;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.general.MultiInputHandler;

public class TestMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		MultiInputHandler handler = new MultiInputHandler("devices");
		StackPane myPane = new StackPane();
		myPane.getChildren().add(handler.getMenu());
		Scene myScene = new Scene(myPane, 500, 500);
		primaryStage.setScene(myScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

package gameengine.controllers;

import javafx.stage.Stage;

public class TestGameController {

	public static void main(String[] args) {
		GameController controller = new GameController();
		controller.start(new Stage());
		controller.step();
	}
}

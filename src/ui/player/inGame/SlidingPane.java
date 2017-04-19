package ui.player.inGame;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class SlidingPane {
	
	public void slidePane(Pane pane, double xValue) {
		TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
		t.setNode(pane);
		t.setToX(xValue);
		t.play();
	}
}

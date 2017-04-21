package ui.player.inGame;

import java.util.Optional;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class SlidingPane extends AnchorPane{
	
	private VBox vbox = new VBox(40);
	
	public VBox getVBox() {
		return vbox;
	}
	
	public SlidingPane() {
		
	}
	
	public SlidingPane(Optional<String> backImage, double slideTo, Corner corner) {
		addBackButton(backImage, slideTo, corner);
	}
	
	private void addBackButton(Optional<String> backImage, double slideTo, Corner corner) {
		OptionButton back = new OptionButton(0, "", backImage.orElse("back_icon.png"), e -> slidePane(this, slideTo));
		this.getChildren().add(back.getButton());
		if (corner == Corner.TOPRIGHT) AnchorPane.setRightAnchor(back.getButton(), 10.0);
		if (corner == Corner.TOPLEFT) AnchorPane.setLeftAnchor(back.getButton(), 10.0);
		AnchorPane.setTopAnchor(back.getButton(), 10.0);
		vbox.getChildren().add(back.getButton());
	}
	
	public void slidePane(Pane pane, double xValue) {
		TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
		t.setNode(pane);
		t.setToX(xValue);
		t.play();
	}
}

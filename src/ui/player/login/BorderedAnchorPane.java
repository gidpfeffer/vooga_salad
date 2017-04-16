package ui.player.login;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class BorderedAnchorPane {

	Pane root;
	BorderPane borderPane;
	
	public Pane getRoot() {
		return root;
	}
	
	public BorderPane getBorderPane() {
		return borderPane;
	}
	
	public BorderedAnchorPane() {
		this.root = new AnchorPane();
		this.borderPane = new BorderPane();
		root.getChildren().add(borderPane);
		anchor();
	}
	
	private void anchor(){
		AnchorPane.setBottomAnchor(borderPane, 0.0);
		AnchorPane.setLeftAnchor(borderPane, 0.0);
		AnchorPane.setRightAnchor(borderPane, 0.0);
		AnchorPane.setTopAnchor(borderPane, 0.0);
	}
	
	
}

package ui.player;

import javafx.scene.layout.Pane;

public class Root extends Pane{
	private Pane pane;
	
	public Pane getRoot(){
		return pane;
	}
	
	public Root(){
		pane = new Pane();
	}
}

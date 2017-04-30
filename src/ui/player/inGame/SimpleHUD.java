package ui.player.inGame;

import java.util.Map;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import util.observerobservable.VoogaObserver;

/**
 * Creates a simple grid pane HUD
 * @author anngelyque, sarahzhou
 *
 */
public class SimpleHUD implements VoogaObserver<Map<String,String>>{

	private GridPane hud;
	int rowIdx = 0;
	
	public GridPane getGrid(){
		return hud;
	}
	
	public SimpleHUD(){
		hud = new GridPane();
		System.out.println("GameStatus initialized and dipslayed");
		hud.setStyle("-fx-background-color: slateblue; -fx-opacity: .5");
	}
	
	@Override
	public void update(Map<String, String> arg) {
		System.out.println("clearing");
		hud.getChildren().clear();
		arg.forEach((k,v) -> display(k,v,rowIdx));
		System.out.println("update GameStatus");
	}
	
	private void display(String key, String val, int row) {
		hud.add(new Text(key), 0, row);
		hud.add(new Text(val), 1, row);
		rowIdx++;
	}
}

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
	int colIdx = 0, rowIdx = 0;
	
	public GridPane getGrid(){
		return hud;
	}
	
	public SimpleHUD(){
		hud = new GridPane();
		hud.setStyle("-fx-background-color: slateblue; -fx-opacity: .5");
	}
	
	@Override
	public void update(Map<String, String> arg) {
		arg.forEach((k,v) -> display(k,v,colIdx,rowIdx));
	}
	
	private void display(String key, String val, int colIdx,int rowIdx) {
		hud.add(new Text(key+ " "+val), colIdx, rowIdx);
		colIdx++;
		rowIdx++;
	}
}

package ui.player.inGame;

import gamestatus.GameStatus;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * Creates a simple grid pane HUD
 * @author anngelyque
 *
 */
public class SimpleHUD {

	private GridPane hud;
	private GameStatus gameStatus;
	private int x;
	private int y;
	
	public GridPane getGrid(){
		return hud;
	}
	
	public SimpleHUD(){
		hud = new GridPane();
		hud.setStyle("-fx-background-color: slateblue; -fx-opacity: .5");
		hud.add(new Text("GAME STATUS"), 0, 0);
		hud.add(new Text("LEVEL: "), 0, 1);
		hud.add(new Text("1"), 1, 1);
		x = 0;
		y = 0;
		setup();
	}
	
	public void setup() {
		addHealth();
		addScore();
		addExperience();
		addMoney();
		addLevel();
	}

	private void addLevel() {
		// TODO Auto-generated method stub
		
	}

	private void addMoney() {
		// TODO Auto-generated method stub
		
	}

	private void addExperience() {
		// TODO Auto-generated method stub
		
	}

	private void addScore() {
		// TODO Auto-generated method stub
		
	}

	private void addHealth() {
		// TODO Auto-generated method stub
		
	}
}

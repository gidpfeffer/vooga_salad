package ui.player.inGame;

import javafx.scene.layout.GridPane;

public class SimpleHUD {

	private GridPane hud;
	private int x;
	private int y;
	
	public GridPane getGrid(){
		return hud;
	}
	
	public SimpleHUD(){
		hud = new GridPane();
		hud.setStyle("-fx-background-color: slateblue; -fx-opacity: .5");
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

package ui.authoring.level;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class LevelEditorMenu extends Dialog<ButtonType>{
TextField numEnemies;
TextField healthMultiplier;
	public LevelEditorMenu(){
super();
ButtonType applyChanges=new ButtonType("Apply Changes",ButtonData.APPLY);

this.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
GridPane grid=new GridPane();
grid.setHgap(10);
grid.setVgap(10);
grid.setPadding(new Insets(20, 150, 10, 10));
 numEnemies = new TextField();
numEnemies.setPromptText("How many enemies in this wave?");
healthMultiplier = new TextField();
healthMultiplier.setPromptText("What health multiplier should be applied to enemies in this wave");
grid.add(new Label("Number of Enemies:"), 0, 0);
grid.add(numEnemies, 1, 0);
grid.add(new Label("Health Multiplier"), 0, 1);
grid.add(healthMultiplier, 1, 1);

this.getDialogPane().setContent(grid);
}
public String getNumEnemies(){
	return numEnemies.getText();
}
public String getHealthMultiplier(){
	return healthMultiplier.getText();
}

}

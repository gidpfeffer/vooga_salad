package ui.player.login;

import java.util.Map;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class DataEntryGrid {

	GridPane grid = new GridPane();

	
	public abstract void addValues();
	public abstract Map<Text, TextField> getEntryMap();
	
	
	public GridPane getGrid() {
		return grid;
	}
	
	public DataEntryGrid(ResourceBundle resource) {
		setGridProperties();
	}
	
	public DataEntryGrid(Map<Text, TextField> entries) {
		setGridProperties();
	}
	
	private void setGridProperties() {
		grid.setHgap(10);
		grid.setVgap(10);
		//grid.setPadding(new Insets(25, 25, 25, 25));
	}
	
	public void addToGrid(Map<Text, TextField> entries) {
		int i = 0; //col
		int k = 0; //row
		for (Map.Entry<Text, TextField> entry : entries.entrySet()){
		    grid.add(entry.getKey(), i, k);
		    grid.add(entry.getValue(), i + 1, k);
		    k++;
		}
	}
}

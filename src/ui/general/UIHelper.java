package ui.general;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class UIHelper {
	
	public static void setBackgroundColor(Pane pane, Color c){
		pane.setBackground(new Background(new BackgroundFill[] { new BackgroundFill(c, new CornerRadii(3.5), null)}));
	}
	
	
}

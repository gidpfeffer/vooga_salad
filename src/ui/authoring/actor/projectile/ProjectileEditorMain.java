package ui.authoring.actor.projectile;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.ImageButton;
import util.Location;

public class ProjectileEditorMain extends AnchorPane{
	private PopViewDelegate delegate;
	
	public ProjectileEditorMain(PopViewDelegate delegate){
		super();
		this.delegate = delegate;
		this.widthProperty().divide(5);
		setupBackButton();
	}
	
	private void setupBackButton() {
		ImageButton b = new ImageButton("back_icon.png", new Location(30., 30.));
		AnchorPane.setTopAnchor(b, 4.0);
		AnchorPane.setLeftAnchor(b, 4.0);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> delegate.closeView(this));
		this.getChildren().add(b);
	}
	
	

}

package ui.authoring.actor.projectile;

import javafx.scene.layout.AnchorPane;
import ui.authoring.delegates.PopViewDelegate;

public class ProjectileEditorMain extends AnchorPane{
	private PopViewDelegate delegate;
	
	public ProjectileEditorMain(PopViewDelegate delegate){
		this.delegate = delegate;
		this.widthProperty().divide(5);
	}
	
	public void close(){
		delegate.closeView(this);
	}
	
	

}

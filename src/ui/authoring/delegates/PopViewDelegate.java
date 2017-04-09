package ui.authoring.delegates;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public interface PopViewDelegate {
	public abstract void openView(Pane pane);
	public abstract void closeView(Pane pane);
}

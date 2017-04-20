package ui.player.login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

public interface LoginElement {
	public void setLoginReturn(EventHandler<ActionEvent> value);
	public Scene getScene();
	public EventHandler<ActionEvent> getAction();
}


package gameengine.actors;

import javafx.scene.shape.Path;

public interface SmartActor {

	public void move(Path path);
	
	public void attack(IGameObject gameObject);
}

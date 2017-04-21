package ui.player;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class ProfileCornerPicture extends Circle{

	public ProfileCornerPicture(ImageView image) {
		image.setClip(this);
	}
	
}

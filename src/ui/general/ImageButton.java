package ui.general;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import util.Location;

/**
 * A button with an image
 * 
 * @author TNK
 *
 */
public class ImageButton extends Button{
	
	public ImageButton(String imagePath,Location size){
		this(imagePath, imagePath, size);
	}
	
	public ImageButton(String selected, String unselected, Location size){
		super();
		updateImages(new Image(selected, size.getX(),size.getY(),false,false),
				new Image(unselected,size.getX(),size.getY(),false,false));
	}
	
	public void updateImages(final Image selected, final Image unselected) {
		this.setBackground(Background.EMPTY);
		this.setBorder(Border.EMPTY);
		final ImageView iv = new ImageView(selected);
		this.getChildren().add(iv);
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			iv.setImage(unselected);
			iv.setScaleX(1.2);
			iv.setScaleY(1.2);
		});
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
			iv.setImage(selected);
			iv.setScaleX(1.);
			iv.setScaleY(1.);
		});
		super.setGraphic(iv);
	}

}
/**
 * 
 */
package socialcenter;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ui.Preferences;
import javafx.scene.input.MouseEvent;

/**
 * @author harirajan
 *
 */
public class RatingStars extends HBox {

	private List<ImageView> myStars;
	private int myRating;

	public RatingStars() {
		super();
		myRating = 0;
		Image img = new Image(getClass().getClassLoader().getResourceAsStream("empty_star.png"));
		myStars = new ArrayList<>(Arrays.asList(new ImageView(img),
												new ImageView(img),
												new ImageView(img),
												new ImageView(img),
												new ImageView(img)));
		setUpStars();
		Label lbl = new Label("Your Rating: ");
		lbl.setFont(Preferences.FONT_MEDIUM);
		getChildren().add(lbl);
		getChildren().addAll(myStars);
	}

	private void setUpStars() {
		for (int i = 0; i < myStars.size(); i++) {
			myStars.get(i).setFitHeight(20);
			myStars.get(i).setFitWidth(20);
			myStars.get(i).addEventHandler(MouseEvent.MOUSE_ENTERED, new StarMouseHoverHandler(i));
			myStars.get(i).addEventHandler(MouseEvent.MOUSE_CLICKED, new StarMouseClickHandler(i));
			myStars.get(i).addEventHandler(MouseEvent.MOUSE_EXITED, new StarMouseExitHandler(i));
		}
	}
	
	private void fillStars(int index) {
		for (int i = 0; i < myStars.size(); i++) {
			if (i <= index)
				myStars.get(i).setImage(new Image(getClass().getClassLoader().getResourceAsStream("filled_star.png")));
			else {
				myStars.get(i).setImage(new Image(getClass().getClassLoader().getResourceAsStream("empty_star.png")));
			}
				
		}
	}
	
	private void restoreRating() {
		fillStars(myRating - 1);
	}
	
	public int getRating() {
		return myRating;
	}
	
	private abstract class StarMouseEventHandler implements EventHandler<MouseEvent> {
		
		protected int myIndex;
		
		public StarMouseEventHandler(int index) {
			myIndex = index;
		}
		
	}
	
	private class StarMouseHoverHandler extends StarMouseEventHandler {

		public StarMouseHoverHandler(int index) {
			super(index);
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			fillStars(myIndex);
			
		}
	}
	
	private class StarMouseClickHandler extends StarMouseEventHandler {

		public StarMouseClickHandler(int index) {
			super(index);
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			myRating = myIndex + 1;
			
		}
	}
	
	private class StarMouseExitHandler extends StarMouseEventHandler {

		public StarMouseExitHandler(int index) {
			super(index);
			// TODO Auto-generated constructor stub
		}

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(MouseEvent event) {
			restoreRating();
		}
	}

}

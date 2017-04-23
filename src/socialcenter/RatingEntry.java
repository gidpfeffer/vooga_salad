/**
 * 
 */
package socialcenter;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author harirajan
 *
 */
public class RatingEntry extends VBox {
	
	private TextArea myReviewText;
	private TextField myUserNameText;
	private Button mySubmitButton;
	private RatingStars myRatingStars;
	
	public RatingEntry() {
		myRatingStars = new RatingStars();
		myReviewText = new TextArea();
		myReviewText.prefHeight(100);
		myReviewText.maxWidth(200);
		HBox myUsernameEntry = new HBox();
		myUserNameText = new TextField();
		myUserNameText.prefWidth(100);
		myUsernameEntry.getChildren().addAll(new Label("Username: "), myUserNameText);
		mySubmitButton = new Button("Submit");
		getChildren().addAll(myRatingStars, myUsernameEntry, myReviewText, mySubmitButton);
	}
	
	public void addSubmitEventHandler(EventHandler<ActionEvent> e) {
		mySubmitButton.addEventFilter(ActionEvent.ACTION, e);
	}
	
	public String getUser() {
		return myUserNameText.getText();
	}
	
	public String getReview() {
		return myReviewText.getText();
	}
	
	public int getRating() {
		return myRatingStars.getRating();
	}
	
}

/**
 * 
 */
package ui.ratings;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ui.Preferences;
import ui.general.CustomColors;
import ui.general.UIHelper;

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
		
		UIHelper.setDropShadow(this);

		
		setSpacing(5);
		
		setBackground(new Background(new BackgroundFill(CustomColors.INDIGO, new CornerRadii(3.5), null)));
		setPadding(new Insets(10,10,10,10));
		
		myRatingStars = new RatingStars(0, true);
		myReviewText = new TextArea();
		myReviewText.setPrefHeight(200);
		HBox myUsernameEntry = new HBox();
		myUserNameText = new TextField();
		myUserNameText.prefWidth(100);
		Label usernameLabel = new Label("Username: ");
		usernameLabel.setFont(Preferences.FONT_MEDIUM_BOLD);
		usernameLabel.setTextFill(Color.WHITE);
		myUsernameEntry.getChildren().addAll(usernameLabel, myUserNameText);
		mySubmitButton = new Button("Submit");
		mySubmitButton.setPrefSize(975, 50);
		mySubmitButton.setTextFill(Color.WHITE);
		mySubmitButton.setFont(Preferences.FONT_SMALL_BOLD);
		mySubmitButton.setBackground(new Background(new BackgroundFill(CustomColors.INDIGO, null, null)));
		UIHelper.addClickAnimation(mySubmitButton);
		getChildren().addAll(myRatingStars, myUsernameEntry, myReviewText, mySubmitButton);
	}
	
	public void addSubmitEventHandler(EventHandler<ActionEvent> e) {
		mySubmitButton.addEventHandler(ActionEvent.ACTION, e);
		mySubmitButton.addEventHandler(ActionEvent.ACTION, event -> clear());
	}
	
	private void clear() {
		myRatingStars.clear();
		myUserNameText.clear();
		myReviewText.clear();
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

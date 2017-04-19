/**
 * 
 */
package socialcenter;


import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

/**
 * @author harirajan
 *
 */
public class RatingEntry extends VBox {
	
	private TextArea myReviewText;
	
	public RatingEntry() {
		getChildren().add(new RatingStars());
		myReviewText = new TextArea();
		myReviewText.prefHeight(100);
		myReviewText.maxWidth(200);
		getChildren().add(myReviewText);
		Button submit = new Button("Submit");
		submit.setOnAction(e -> submit());
		getChildren().add(submit);
	}
	
	private void submit() {
		System.out.println(myReviewText.getText());
	}

}

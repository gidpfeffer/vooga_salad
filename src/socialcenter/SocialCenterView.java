/**
 * 
 */
package socialcenter;

import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ui.Preferences;
import ui.general.StackButton;
import ui.general.UIHelper;

/**
 * @author harirajan
 *
 */
public class SocialCenterView {
	
	private BorderPane myRoot;
	private Stage myStage;
	
	public SocialCenterView(Stage s) {
		myRoot = new BorderPane();
		myRoot.setLeft(createLeftPane());
		myRoot.setCenter(new Rectangle());
		myStage = s;
		myStage.setScene(new Scene(myRoot));
		myStage.setWidth(800);
		myStage.setHeight(800);
		myStage.show();
	}
	
	private Node createLeftPane() {
		VBox vb = new VBox();
		Label reviewsLabel = new Label("Reviews");
		reviewsLabel.setTextFill(Color.WHITE);
		reviewsLabel.setFont(Preferences.FONT_MEDIUM_BOLD);
		Label chatLabel = new Label("Chat");
		chatLabel.setTextFill(Color.WHITE);
		chatLabel.setFont(Preferences.FONT_MEDIUM_BOLD);
		StackPane reviews = UIHelper.buttonStack(e -> reviewsButtonHandler(), Optional.of(reviewsLabel), Optional.ofNullable(null), Pos.CENTER_LEFT, true);
		StackPane chat = UIHelper.buttonStack(e -> reviewsButtonHandler(), Optional.of(chatLabel), Optional.ofNullable(null), Pos.CENTER_LEFT, true);
		reviews.setPrefWidth(125);
		chat.setPrefWidth(125);
		vb.getChildren().addAll(reviews, chat);
		return vb;
	}

	/**
	 * @return
	 */
	private void chatButtonHandler() {
		// TODO Auto-generated method stub
	}

	/**
	 * @return
	 */
	private void reviewsButtonHandler() {
		// TODO Auto-generated method stub
		myRoot.setCenter(new RatingEntry());
		System.out.println("CLICK");
	}

}

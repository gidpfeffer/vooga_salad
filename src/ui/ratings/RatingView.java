/**
 * 
 */
package ui.ratings;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import ui.Preferences;
import ui.general.UIHelper;
import ui.handlers.LoginHandler;

/**
 * @author harirajan
 *
 */
public class RatingView extends BorderPane {
	
	LoginHandler loginhandler;
	
	public RatingView(LoginHandler loginhandler, String lang) {
		
		this.loginhandler = loginhandler;
		
		ScrollPane sp = new ScrollPane();
		
		sp.setContent(new RatingDisplay("reviews.xml"));
		setCenter(sp);
		setTop(setUpTopNode());
	}

	/**
	 * @return
	 */
	private Node setUpTopNode() {
		// TODO Auto-generated method stub
		HBox hb = new HBox();
		hb.getChildren().add(setUpBackButton());
		hb.getChildren().add(setUpTitle());
		hb.setSpacing(10);
		return hb;
	}

	/**
	 * @return
	 */
	private Node setUpTitle() {
		// TODO Auto-generated method stub
		Label lbl = new Label("Ratings and Reviews");
		Button b = new Button();
		UIHelper.setDropShadow(b);
		UIHelper.setDropShadow(lbl);
		lbl.setFont(Preferences.FONT_BIG_BOLD);
		b.setGraphic(lbl);
		b.setPrefSize(1000, 60);
		b.setBackground(new Background(new BackgroundFill(Color.LAVENDER, new CornerRadii(5), null)));
		return b;
	}

	/**
	 * @return
	 */
	private Node setUpBackButton() {
		// TODO Auto-generated method stub
		Button b = new Button();
		UIHelper.setDropShadow(b);
		ImageView backimg = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("back_icon.png")));
		backimg.setFitHeight(50);
		backimg.setFitWidth(50);
		UIHelper.addClickAnimation(b);
		b.setBackground(new Background(new BackgroundFill(Color.LAVENDER, new CornerRadii(5), null)));
		b.setGraphic(backimg);
		b.setOnAction(e -> loginhandler.returnToMain());
		b.setPrefSize(5, 5);
		return b;
	}
}

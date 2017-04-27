/**
 * 
 */
package ui.ratings;

import java.util.ResourceBundle;

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
import ui.general.CustomColors;
import ui.general.UIHelper;
import ui.handlers.LoginHandler;

/**
 * @author harirajan
 *
 */
public class RatingView extends BorderPane {
	
	private static final String BACK_ICON = "back_icon.png"; 
	private static final int DEFAULT_BACK_BUTTON_SIZE = 50;
	private static final int TITLE_BAR_SPACING = 10;
	private static final int SCREEN_WIDTH = 1000;
	private static final Background DEFAULT_TITE_BACKGROUND = 
			new Background(new BackgroundFill(Color.LAVENDER, new CornerRadii(5), null));
	
	private LoginHandler loginhandler;
	private ResourceBundle resource;
	
	public RatingView(LoginHandler loginhandler, String lang) {
		this.loginhandler = loginhandler;
		resource = ResourceBundle.getBundle(lang);
		ScrollPane sp = new ScrollPane();
		sp.setContent(new RatingDisplay(lang));
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
		hb.setSpacing(TITLE_BAR_SPACING);
		return hb;
	}

	/**
	 * @return
	 */
	private Node setUpTitle() {
		// TODO Auto-generated method stub
		Label lbl = new Label(resource.getString("ratingreviewtitle"));
		Button b = new Button();
		UIHelper.setDropShadow(b);
		UIHelper.setDropShadow(lbl);
		lbl.setFont(Preferences.FONT_BIG_BOLD);
		b.setGraphic(lbl);
		b.setPrefSize(SCREEN_WIDTH, DEFAULT_BACK_BUTTON_SIZE + 10);
		b.setBackground(DEFAULT_TITE_BACKGROUND);
		return b;
	}

	/**
	 * @return
	 */
	private Node setUpBackButton() {
		Button b = new Button();
		UIHelper.setDropShadow(b);
		ImageView backimg = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(BACK_ICON)));
		backimg.setFitHeight(DEFAULT_BACK_BUTTON_SIZE);
		backimg.setFitWidth(DEFAULT_BACK_BUTTON_SIZE);
		UIHelper.addClickAnimation(b);
		b.setBackground(DEFAULT_TITE_BACKGROUND);
		b.setGraphic(backimg);
		b.setOnAction(e -> loginhandler.returnToMain());
		return b;
	}
}

/**
 * 
 */
package socialcenter;

import java.io.File;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import XML.XMLParser;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ui.Preferences;
import ui.general.CustomColors;

/**
 * @author harirajan
 *
 */
public class RatingDisplay extends VBox {
	
	public RatingDisplay(String fileName) {
		
		
		setPrefWidth(800);
		setMinWidth(800);
		setMaxWidth(800);
		setSpacing(8);
		
		XMLParser reader = new XMLParser(new File("reviews.xml"));
		
		NodeList reviewElementList = reader.getElementsByName("user_review");
		for (int i = 0; i < reviewElementList.getLength(); i++) {
			Element reviewElement = (Element) reviewElementList.item(i);
			addRating(Integer.parseInt(reader.getTextValue(reviewElement, "rating")),
					reader.getTextValue(reviewElement, "username"),
					reader.getTextValue(reviewElement, "review"));
		}
	}
	
	public void addRating(int rating, String username, String review) {
		VBox vb = new VBox(5);
		Text usernameText = new Text(username);
		Text reviewText = new Text(review);
		usernameText.setFont(Preferences.FONT_SMALL_BOLD);
		reviewText.setFont(Preferences.FONT_SMALL);
		usernameText.wrappingWidthProperty().bind(widthProperty());
		reviewText.wrappingWidthProperty().bind(widthProperty());
		vb.getChildren().add(usernameText);
		vb.getChildren().add(new RatingStars(rating, false));
		vb.getChildren().add(reviewText);
		vb.setBackground(new Background(new BackgroundFill(CustomColors.INDIGO, new CornerRadii(3.5), null)));
		vb.setPadding(new Insets(10,10,10,10));
		getChildren().add(vb);
	}

}

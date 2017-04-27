/**
 * 
 */
package ui.ratings;

import java.io.File;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import XML.XMLParser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ui.Preferences;
import ui.general.CustomColors;
import ui.general.UIHelper;

/**
 * @author harirajan
 *
 */
public class RatingDisplay extends VBox {
	
	private static final int SCREEN_WIDTH = 975;
	private static final int TOTAL_NUM_STARS = 5;
	private static final int DEFAULT_SPACING = 8;
	private static final Insets DEFAULT_PADDING_INSETS = new Insets(10,10,10,10);
	private static final int SINGLE_RATING_DISPLAY_SPACING = 5;
	private static final File REVIEWS_FILE = new File("reviews.xml");
	private static final String XML_REVIEW_NODE = "user_review";
	private static final String XML_USER_TAG = "username";
	private static final String XML_RATING_TAG = "rating";
	private static final String XML_REVIEW_TAG = "review";
	private static final String ADD_ICON = "add_icon.png"; 
	private static final Background DEFAULT_NODE_BACKGROUND = 
			new Background(new BackgroundFill(CustomColors.INDIGO, new CornerRadii(3.5), null));
	
	private XMLParser parser;
	private ResourceBundle resource;
	private String lang;
	
	public RatingDisplay(String lang) {
		this.lang = lang;
		resource = ResourceBundle.getBundle(lang);
		setPrefWidth(SCREEN_WIDTH);
		setMinWidth(SCREEN_WIDTH);
		setMaxWidth(SCREEN_WIDTH);
		setSpacing(DEFAULT_SPACING);
		loadContents();
	}
	
	private void loadContents() {
		getChildren().clear();
		getChildren().add(setUpAddReview());
		parser = new XMLParser(REVIEWS_FILE);
		NodeList reviewElementList = parser.getElementsByName(XML_REVIEW_NODE);
		for (int i = 0; i < reviewElementList.getLength(); i++) {
			Element reviewElement = (Element) reviewElementList.item(i);
			addRating(Integer.parseInt(parser.getTextValue(reviewElement, XML_RATING_TAG)),
					parser.getTextValue(reviewElement, XML_USER_TAG),
					parser.getTextValue(reviewElement, XML_REVIEW_TAG));
		}
	}
	
	/**
	 * @return
	 */
	private Node setUpAddReview() {
		// TODO Auto-generated method stub
		Label lbl = new Label(resource.getString("addreviewprompt"));
		lbl.setFont(Preferences.FONT_MEDIUM_BOLD);
		lbl.setTextFill(Color.WHITE);
		return UIHelper.buttonStack(e -> addReview(), Optional.of(lbl), Optional.of(new ImageView(new Image(ADD_ICON))), Pos.CENTER_LEFT, true);
	}

	/**
	 * @return
	 */
	private void addReview() {
		// TODO Auto-generated method stub
		getChildren().remove(0);
		RatingEntry re = new RatingEntry(TOTAL_NUM_STARS, lang);
		re.addSubmitEventHandler(e -> submit(re.getUser(), re.getRating(),re.getReview()));
		getChildren().add(0, re);
	}

	private void addRating(int rating, String username, String review) {
		VBox vb = new VBox(SINGLE_RATING_DISPLAY_SPACING);
		Text usernameText = new Text(username);
		Text reviewText = new Text(review);
		usernameText.setFont(Preferences.FONT_SMALL_BOLD);
		usernameText.setFill(Color.WHITE);
		reviewText.setFont(Preferences.FONT_SMALL);
		reviewText.setFill(Color.WHITE);
		vb.setPrefWidth(SCREEN_WIDTH);
		vb.setMaxWidth(SCREEN_WIDTH);
		vb.setMinWidth(SCREEN_WIDTH);
		usernameText.wrappingWidthProperty().bind(widthProperty());
		reviewText.wrappingWidthProperty().bind(widthProperty());
		vb.getChildren().add(usernameText);
		vb.getChildren().add(new RatingStars(rating, false, TOTAL_NUM_STARS, lang));
		vb.getChildren().add(reviewText);
		vb.setBackground(DEFAULT_NODE_BACKGROUND);
		vb.setPadding(DEFAULT_PADDING_INSETS);
		UIHelper.setDropShadow(vb);
		getChildren().add(vb);
	}

	private void submit(String user, int rating, String review) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(REVIEWS_FILE);
			Element reviewElement = parser.addElement(doc, XML_REVIEW_NODE, "", doc.getDocumentElement());
			parser.addElement(doc, XML_USER_TAG, user, reviewElement);
			parser.addElement(doc, XML_RATING_TAG, Integer.toString(rating), reviewElement);
			parser.addElement(doc, XML_REVIEW_TAG, review, reviewElement);
			parser.saveXML(REVIEWS_FILE.getName(), doc);			
		} catch (Exception e) {
			e.printStackTrace();
			//THROW ALERT TO UNABLE TO LOAD REVIEW
		}
		loadContents();
		
	}

}

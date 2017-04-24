/**
 * 
 */
package ui.ratings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import XML.XMLParser;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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
	
	public RatingDisplay(String fileName) {
	
		setPrefWidth(800);
		setMinWidth(800);
		setMaxWidth(800);
		setSpacing(8);
		
		loadContents();
	}
	
	private void loadContents() {
		getChildren().clear();
		setPrefWidth(800);
		
		RatingEntry re = new RatingEntry();
		re.addSubmitEventHandler(e -> submit(re.getUser(), re.getRating(), re.getReview()));
		
		getChildren().add(re);
		
		XMLParser reader = new XMLParser(new File("reviews.xml"));
		
		NodeList reviewElementList = reader.getElementsByName("user_review");
		for (int i = 0; i < reviewElementList.getLength(); i++) {
			Element reviewElement = (Element) reviewElementList.item(i);
			addRating(Integer.parseInt(reader.getTextValue(reviewElement, "rating")),
					reader.getTextValue(reviewElement, "username"),
					reader.getTextValue(reviewElement, "review"));
		}
	}
	
	private void addRating(int rating, String username, String review) {
		VBox vb = new VBox(5);
		Text usernameText = new Text(username);
		Text reviewText = new Text(review);
		usernameText.setFont(Preferences.FONT_SMALL_BOLD);
		usernameText.setFill(Color.WHITE);
		reviewText.setFont(Preferences.FONT_SMALL);
		reviewText.setFill(Color.WHITE);
		usernameText.wrappingWidthProperty().bind(widthProperty());
		reviewText.wrappingWidthProperty().bind(widthProperty());
		vb.getChildren().add(usernameText);
		vb.getChildren().add(new RatingStars(rating, false));
		vb.getChildren().add(reviewText);
		vb.setBackground(new Background(new BackgroundFill(CustomColors.INDIGO, new CornerRadii(3.5), null)));
		vb.setPadding(new Insets(10,10,10,10));
		UIHelper.setDropShadow(vb);
		getChildren().add(vb);
	}
	
	private void submit(String user, int rating, String review) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("reviews.xml"));
			Element reviewElement = addElement(doc, "user_review", "", doc.getDocumentElement());
			addElement(doc, "username", user, reviewElement);
			addElement(doc, "rating", Integer.toString(rating), reviewElement);
			addElement(doc, "review", review, reviewElement);
			
			saveXML("reviews.xml", doc);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		loadContents();
		
	}

	private Element addElement(Document doc, String elementTitle, String elementData, Element root) {
		Element newElement = doc.createElement(elementTitle);
		newElement.appendChild(doc.createTextNode(elementData));
		root.appendChild(newElement);
		return newElement;
	}
	
	private void saveXML(String filePath, Document doc) throws TransformerException, IOException {
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer transformer = transfac.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter stringWriter = new StringWriter();
		StreamResult result = new StreamResult(stringWriter);
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		String xmlString = stringWriter.toString();
		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(xmlString);
		fileWriter.close();
	}

}

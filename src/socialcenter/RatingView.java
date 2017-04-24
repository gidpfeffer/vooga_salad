/**
 * 
 */
package socialcenter;

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

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author harirajan
 *
 */
public class RatingView extends ScrollPane {
	
	Stage myStage;
	
	public RatingView(Stage s) {
		setupRatingDisplay();
		setFitToWidth(true);
		myStage = s;
		myStage.setScene(new Scene(this));
		myStage.setWidth(800);
		myStage.setHeight(800);
		myStage.show();
	}
	
	private void setupRatingDisplay() {
		VBox vb = new VBox();
		RatingEntry re = new RatingEntry();
		re.addSubmitEventHandler(e -> submit(re.getUser(), re.getRating(), re.getReview()));
		RatingDisplay rd = new RatingDisplay("reviews.xml");
		vb.getChildren().add(re);
		vb.getChildren().add(rd);
		setContent(vb);
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
		
		setupRatingDisplay();
		
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
